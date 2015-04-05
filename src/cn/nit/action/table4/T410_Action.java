package cn.nit.action.table4;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanWrapperImpl;

import cn.nit.bean.UserinfoBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.table2.T21_Bean;
import cn.nit.bean.table4.T410_Bean;
import cn.nit.bean.table4.T49_Bean;
import cn.nit.constants.Constants;
import cn.nit.dao.table4.T410_Dao;
import cn.nit.dao.table4.T49_Dao;
import cn.nit.service.CheckService;
import cn.nit.service.table4.T410_Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;

public class T410_Action {
	
	private String rows; //每页显示的记录数
	
	private String page; //当前第几页
	
	private T410_Service T410_services = new T410_Service();
	
	private CheckService check_services = new CheckService();
	
	private T410_Bean T410_bean = new T410_Bean();
	
//	private T410_Dao T410_dao = new T410_Dao();
	
	/**  待审核数据的要删除的序列集  */
	private String ids; //删除的id
	
	/**  待审核数据的查询的序列号  */
	private Integer seqNum ;
	
	/**  待审核数据查询的起始时间  */
	private Date startTime ;
	
	/**  待审核数据查询的结束时间  */
	private Date endTime ;
	
	/**  下载的excelName  */
	private String excelName ;
	
	/**  审核状态显示判别标志  */
	private int checkNum ;
	
	/**  该标志是用来区分显示审核通过数据的年为空时的两种情况，
	 * 一种是被审核用户的当前年数据显示，另一种是审核用户审核通过数据的显示  
	 * 用0来代表后者
	 * */
	private int checkFlag ;
	
	/**  导出时间  */
	private String selectYear ;
	
	/**  审核通过数据按年时间查询  */
	private String queryYear ;
	public String getQueryYear() {
		return queryYear;
	}

	public void setQueryYear(String queryYear) {
		this.queryYear = queryYear;
	}


	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	
	
	//查询出所有教师信息
	public void loadResInfo() throws Exception{
		
		HttpServletResponse response = ServletActionContext.getResponse() ;	
		
		String cond = null;
		StringBuffer conditions = new StringBuffer();
		
		if(this.getSeqNum() == null && this.getStartTime() == null && this.getEndTime() == null && this.getCheckNum() == 0){					
			cond = null;	
		}else{			
			if(this.getSeqNum()!=null){
				conditions.append(" and SeqNumber=" + this.getSeqNum()) ;
			}
			
			if(this.getStartTime() != null){
				conditions.append(" and cast(CONVERT(DATE, Time)as datetime)>=cast(CONVERT(DATE, '" 
						+ TimeUtil.changeFormat4(this.startTime) + "')as datetime)") ;
			}
			
			if(this.getEndTime() != null){
				conditions.append(" and cast(CONVERT(DATE, Time)as datetime)<=cast(CONVERT(DATE, '" 
						+ TimeUtil.changeFormat4(this.getEndTime()) + "')as datetime)") ;
			}
			
			
			//审核状态判断
			if(this.getCheckNum() == Constants.WAIT_CHECK ){
				conditions.append(" and CheckState=" + this.getCheckNum()) ;
			}else if(this.getCheckNum() == (Constants.PASS_CHECK)){
				conditions.append(" and CheckState=" + this.getCheckNum()) ;
				if(this.getQueryYear() != null){
					conditions.append(" and Time like '" + this.queryYear + "%'");
				}else{
					if(this.getCheckFlag()!=0){
						 Calendar now = Calendar.getInstance();  
						 this.setQueryYear(now.get(Calendar.YEAR)+"");
						 conditions.append(" and Time like '" + this.queryYear + "%'");
					}
				}
			}else if(this.getCheckNum() == (Constants.NOPASS_CHECK)){
				conditions.append(" and CheckState=" + this.getCheckNum()) ;
			}else if(this.getCheckNum() == (Constants.NO_CHECK)){
				conditions.append(" and CheckState!=" + Constants.PASS_CHECK) ;
			}
			
			cond = conditions.toString();
		}
		List<T410_Bean> list = T410_services.getPageResList(cond, null, this.getRows(), this.getPage()) ;
		String TeaInfoJson = this.toBeJson(list,T410_services.getTotal(cond, null));
		//private JSONObject jsonObj;
		
		PrintWriter out = null ;

		if(TeaInfoJson == null){			
			return ;
		}else{
			try {
				
				System.out.println(TeaInfoJson) ;
				response.setContentType("application/json;charset=UTF-8") ;
				out = response.getWriter() ;
				out.print(TeaInfoJson) ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(out != null){
					out.flush() ;
					out.close() ;
				}
			}
		}
	}

    //将分页系统的总数以及当前页的list转化一个json传页面显示
	private String toBeJson(List<T410_Bean> list, int total) throws Exception{
		// TODO Auto-generated method stub
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();

		JSONObject testjson = new JSONObject();
		testjson.accumulate("total", total);
		testjson.accumulate("rows", list);
		
        String json = testjson.toString();
        System.out.println(json) ;
		return json;
	}
	
/*
	//查出所有
	public void loadT410() throws Exception{
		
		List<T410_Bean> list = T410_services.getList();
		//将数据转换为json格式
		JSON json = JSONSerializer.toJSON(list) ;
		PrintWriter out = null ;
		
		try {
			//设置输出内容的格式为json
			response.setContentType("application/json; charset=UTF-8") ;
			out = response.getWriter() ;
			//设置数据的内容的编码格式
			String outPrint = URLDecoder.decode(json.toString(), "UTF-8") ;
			out.print(outPrint) ;
			out.flush() ;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}*/
	
	//插入一个新的教职工
	public void insert(){
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		HttpServletResponse response = ServletActionContext.getResponse();
		
		//插入之前检查是否存在该年数据
		Calendar c = Calendar.getInstance();
		String year = String.valueOf(c.get(Calendar.YEAR)) ;
		boolean flag0 = T410_services.check(year);
		
		boolean flag = false;
		if(!flag0){	
			//插入时间
			T410_bean.setTime(new Date());	
			//插入审核状态
			T410_bean.setCheckState(Constants.WAIT_CHECK);
			T410_bean.setResItemNum(T410_bean.getHresItemNum()+T410_bean.getZresItemNum());
			T410_bean.setResAwardNum(T410_bean.getNationResAward()+T410_bean.getProviResAward()+T410_bean.getCityResAward()+T410_bean.getSchResAward());
			T410_bean.setResItemFund(T410_bean.getHitemFund()+T410_bean.getZitemFund());
			T410_bean.setPaperNum(T410_bean.getSci()+T410_bean.getSsci()+T410_bean.getEi()+T410_bean.getCscd()+T410_bean.getIstp()+T410_bean.getOtherPaper()+T410_bean.getCssci()+T410_bean.getInlandCoreJnal());
			T410_bean.setPublicationNum(T410_bean.getTranslation()+T410_bean.getTreatises());
			T410_bean.setPatentNum(T410_bean.getDesignPatent()+T410_bean.getUtilityPatent()+T410_bean.getInventPatent());
			flag = T410_services.insert(T410_bean);
		}
		
		PrintWriter out = null ;
		
		try{
			response.setContentType("text/html; charset=UTF-8") ;
			out = response.getWriter() ;
			if(flag0){
				out.print("{\"state\":false,data:\"已经存在该年数据!!!\"}") ;
			}else{
				if(flag){
					out.print("{\"state\":true,data:\"录入成功!!!\"}") ;
				}else{
					out.print("{\"state\":false,data:\"录入失败!!!\"}") ;
				}
			}

		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"录入失败!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
		out.flush() ;
	}
	
	
	
/*	*//** 检查是否存在该年数据
	 * @throws IOException *//*
	public void check( ) throws IOException{
		Calendar c = Calendar.getInstance();
		String year = String.valueOf(c.get(Calendar.YEAR)) ;
		System.out.println("时间啊");
		System.out.println(year);
		HttpServletResponse response = ServletActionContext.getResponse() ;
		PrintWriter out = null ;
		boolean flag = T410_services.check(year);
		try{			
			response.setContentType("application/json; charset=UTF-8") ;
			out = response.getWriter() ;			
			if(flag){
				out.print("{\"state\":true,data:\"已经存在该年数据!!!\"}") ;
			}else{
				out.print("{\"state\":\false}") ;
			}
			
			out.flush() ;
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":true,data:\"系统错误，请联系管理员!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}*/
	
	
	
	/**  编辑数据  */
	public void edit(){
		
		T410_bean.setResItemNum(T410_bean.getHresItemNum()+T410_bean.getZresItemNum());
		T410_bean.setResAwardNum(T410_bean.getNationResAward()+T410_bean.getProviResAward()+T410_bean.getCityResAward()+T410_bean.getSchResAward());
		T410_bean.setResItemFund(T410_bean.getHitemFund()+T410_bean.getZitemFund());
		T410_bean.setPaperNum(T410_bean.getSci()+T410_bean.getSsci()+T410_bean.getEi()+T410_bean.getCscd()+T410_bean.getIstp()+T410_bean.getOtherPaper()+T410_bean.getCssci()+T410_bean.getInlandCoreJnal());
		T410_bean.setPublicationNum(T410_bean.getTranslation()+T410_bean.getTreatises());
		T410_bean.setPatentNum(T410_bean.getDesignPatent()+T410_bean.getUtilityPatent()+T410_bean.getInventPatent());
		
		boolean flag = false;
		
		int tag = 0;
		//获得该条数据审核状态
		int state = T410_services.getCheckState(T410_bean.getSeqNumber());
		
		//如果审核状态是待审核，则直接修改
		if(state == Constants.WAIT_CHECK){
			T410_bean.setCheckState(Constants.WAIT_CHECK);
			flag = T410_services.update(T410_bean) ;
			if(flag) tag = 1;
		}
		//如果是审核不通过，则修改该条数据，并将审核状态调节为待审核，同时删除该条数据在checkInfo表的信息
		if(state == Constants.NOPASS_CHECK){
			T410_bean.setCheckState(Constants.WAIT_CHECK);
			boolean flag1 = T410_services.update(T410_bean) ;
			boolean flag2 = check_services.delete("T410",T410_bean.getSeqNumber());
			if(flag1&&flag2){
				flag = true;
				tag = 2;
			}
		}
		
		PrintWriter out = null ;
	
		try{
			response.setContentType("text/html; charset=UTF-8") ;
			out = response.getWriter() ;
			if(tag == 1){
				out.print("{\"state\":true,data:\"修改成功!!!\"}") ;
			}
			else if(tag == 2){
				out.print("{\"state\":true,data:\"修改成功!!!\",tag:2}") ;
			}
			else{
				out.print("{\"state\":true,data:\"修改失败!!!\"}") ;
			}
			out.flush() ;
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"系统错误，请联系管理员!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}

	/**  修改某条数据的审核状态  */
	public void updateCheck(){
		HttpServletResponse response = ServletActionContext.getResponse();
	
		boolean flag = T410_services.updateCheck(this.getSeqNum(),this.getCheckNum());
		PrintWriter out = null ;
		
		try{
			response.setContentType("text/html; charset=UTF-8") ;
			out = response.getWriter() ;
			if(flag){
				out.print("{\"state\":true,data:\"修改审核状态成功!!!\"}") ;
			}else{
				out.print("{\"state\":false,data:\"修改审核状态失败!!!\"}") ;
			}
			out.flush() ;
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"修改审核状态失败!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
	/**  全部审核通过  */
	public void checkAll(){
		HttpServletResponse response = ServletActionContext.getResponse();
	
		boolean flag = T410_services.checkAll();
		
		PrintWriter out = null ;
		
		try{
			response.setContentType("text/html; charset=UTF-8") ;
			out = response.getWriter() ;
			if(flag){
				out.print("{\"state\":true,data:\"一键审核成功!!!\"}") ;
			}else{
				out.print("{\"state\":false,data:\"一键审核失败!!!\"}") ;
			}
			out.flush() ;
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"一键审核失败!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}

	/**  根据数据的id删除数据  */
	public void deleteByIds(){
		System.out.println("ids=" + this.getIds()) ;
		
		boolean flag = T410_services.deleteByIds(ids) ;
		
		//删除审核不通过信息
		check_services.delete("T410", ids);
		
		PrintWriter out = null ;
		
		try{			
			response.setContentType("application/json; charset=UTF-8") ;
			out = response.getWriter() ;			
			if(flag){
				out.print("{\"state\":true,data:\"数据删除成功!!!\"}") ;
			}else{
				out.print("{\"state\":false,data:\"数据删除失败!!!\"}") ;
			}
			
			out.flush() ;
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"系统错误，请联系管理员!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
	public InputStream getInputStream() throws Exception{
		UserinfoBean userBean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		T410_Bean bean = null;
		String sheetName = null;
		
		if("111".equals(userBean.getRoleID())){
			String year = (String)request.getSession().getAttribute("allYear") ;
			bean = T410_services.totalList(year,Constants.PASS_CHECK);
			sheetName = "表4-10教师科研情况（科研处）";
		}else{
			bean = T410_services.totalList(this.getSelectYear(),Constants.PASS_CHECK);
			sheetName = this.excelName;
		}
	
		ByteArrayOutputStream fos = new ByteArrayOutputStream();
		WritableWorkbook wwb;	
		InputStream inputStream = null ;
		
		if(bean == null){
		    try {    
		    	  
		           wwb = Workbook.createWorkbook(fos);
		           WritableSheet ws = wwb.createSheet(sheetName, 0);        // 创建一个工作表
		
		            //    设置单元格的文字格式
		           WritableFont wf = new WritableFont(WritableFont.ARIAL,12,WritableFont.BOLD,false,
		                    UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
		           WritableCellFormat wcf = new WritableCellFormat(wf);
		           wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
		           wcf.setAlignment(Alignment.CENTRE);
		           wcf.setBorder(Border.ALL, BorderLineStyle.THIN,
		        		     jxl.format.Colour.BLACK);
		           ws.setRowView(1, 500);
		           
		            //    设置内容单无格的文字格式
		           WritableFont wf1 = new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD,false,
		                    UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
		            WritableCellFormat wcf1 = new WritableCellFormat(wf1);        
		            wcf1.setVerticalAlignment(VerticalAlignment.CENTRE);
		            wcf1.setAlignment(Alignment.CENTRE);
		            wcf1.setBorder(Border.ALL, BorderLineStyle.THIN,
			        		     jxl.format.Colour.BLACK);
		           
		           ws.addCell(new Label(0, 0, sheetName, wcf)); 
		           ws.mergeCells(0, 0, 3, 0);
		             
		           ws.addCell(new Label(0, 2, "", wcf)); 
		           ws.addCell(new Label(0, 5, "项目数（项）", wcf)); 
		           ws.addCell(new Label(0, 6, "经费（万元）", wcf)); 
		           ws.addCell(new Label(1, 2, "1.教师科研项目", wcf)); 
		           ws.addCell(new Label(1, 3, "总计", wcf)); 
		           ws.addCell(new Label(2, 3, "横向", wcf)); 
		           ws.addCell(new Label(4, 3, "纵向", wcf)); 
		           ws.addCell(new Label(2, 4, "横向总数", wcf)); 
		           ws.addCell(new Label(3, 4, "其中：人文社会科学", wcf)); 
		           ws.addCell(new Label(4, 4, "纵向总数", wcf)); 
		           ws.addCell(new Label(5, 4, "其中：人文社会科学", wcf));
		           ws.mergeCells(0, 2, 0, 4);
		           ws.mergeCells(1, 2, 5, 2);
		           ws.mergeCells(1, 3, 1, 4);
		           ws.mergeCells(2, 3, 3, 3);
		           ws.mergeCells(4, 3, 5, 3);
		           
		           	           
		           ws.addCell(new Label(1, 8, "2.近一届科研成果奖数（项）", wcf)); 
		           ws.addCell(new Label(1, 9, "总数", wcf)); 
		           ws.addCell(new Label(2, 9, "其中", wcf)); 
		           ws.addCell(new Label(2, 10, "国家级", wcf)); 
		           ws.addCell(new Label(3, 10, "省部级", wcf)); 
		           ws.addCell(new Label(4, 10, "市厅级", wcf)); 
		           ws.addCell(new Label(5, 10, "校级", wcf)); 	           
		           ws.mergeCells(1, 8, 5, 8);
		           ws.mergeCells(1, 9, 1, 10);
		           ws.mergeCells(2, 9, 5, 9);	           
		           
		           ws.addCell(new Label(1, 13, "3.发表论文数（篇）", wcf)); 
		           ws.addCell(new Label(1, 14, "总数", wcf)); 
		           ws.addCell(new Label(2, 14, "其中", wcf)); 
		           ws.addCell(new Label(2, 15, "SCI", wcf)); 
		           ws.addCell(new Label(3, 15, "SSCI", wcf)); 
		           ws.addCell(new Label(4, 15, "EI", wcf)); 
		           ws.addCell(new Label(5, 15, "ISTP", wcf)); 
		           ws.addCell(new Label(6, 15, "国内核心期刊", wcf)); 
		           ws.addCell(new Label(7, 15, "CSSCI", wcf)); 
		           ws.addCell(new Label(8, 15, "CSCD", wcf)); 
		           ws.addCell(new Label(9, 15, "其他", wcf)); 
		           ws.mergeCells(1, 13, 9, 13);
		           ws.mergeCells(1, 14, 1, 15);
		           ws.mergeCells(2, 14, 9, 14);
		           
		           
		           ws.addCell(new Label(1, 18, "4.出版专译著（册）", wcf)); 
		           ws.addCell(new Label(1, 19, "总数", wcf)); 
		           ws.addCell(new Label(2, 19, "专著", wcf)); 
		           ws.addCell(new Label(3, 19, "译著", wcf)); 
		           ws.mergeCells(1, 18, 3, 18);	           
		           
		           
		           ws.addCell(new Label(1, 22, "5.获准专利（项）", wcf)); 
		           ws.addCell(new Label(1, 23, "总数", wcf)); 
		           ws.addCell(new Label(2, 23, "发明专利", wcf)); 
		           ws.addCell(new Label(3, 23, "实用新型专利", wcf)); 
		           ws.addCell(new Label(4, 23, "外观设计专利", wcf)); 
		           ws.mergeCells(1, 22, 6, 22);
		           ws.mergeCells(4, 23, 6, 23);
		           ws.mergeCells(4, 24, 6, 24);
		           
		          wwb.write();
		          wwb.close();

		        } catch (IOException e){
		        } catch (RowsExceededException e){
		        } catch (WriteException e){}
		}else{
		    try {    
		    	  
		           wwb = Workbook.createWorkbook(fos);
		           WritableSheet ws = wwb.createSheet(sheetName, 0);        // 创建一个工作表
		
		            //    设置单元格的文字格式
		           WritableFont wf = new WritableFont(WritableFont.ARIAL,12,WritableFont.BOLD,false,
		                    UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
		           WritableCellFormat wcf = new WritableCellFormat(wf);
		           wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
		           wcf.setAlignment(Alignment.CENTRE);
		           wcf.setBorder(Border.ALL, BorderLineStyle.THIN,
		        		     jxl.format.Colour.BLACK);
		           ws.setRowView(1, 500);
		           
		            //    设置内容单无格的文字格式
		           WritableFont wf1 = new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD,false,
		                    UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
		            WritableCellFormat wcf1 = new WritableCellFormat(wf1);        
		            wcf1.setVerticalAlignment(VerticalAlignment.CENTRE);
		            wcf1.setAlignment(Alignment.CENTRE);
		            wcf1.setBorder(Border.ALL, BorderLineStyle.THIN,
			        		     jxl.format.Colour.BLACK);
		           
		           ws.addCell(new Label(0, 0, sheetName, wcf)); 
		           ws.mergeCells(0, 0, 3, 0);
		             
		           ws.addCell(new Label(0, 2, "", wcf)); 
		           ws.addCell(new Label(0, 5, "项目数（项）", wcf)); 
		           ws.addCell(new Label(0, 6, "经费（万元）", wcf)); 
		           ws.addCell(new Label(1, 2, "1.教师科研项目", wcf)); 
		           ws.addCell(new Label(1, 3, "总计", wcf)); 
		           ws.addCell(new Label(2, 3, "横向", wcf)); 
		           ws.addCell(new Label(4, 3, "纵向", wcf)); 
		           ws.addCell(new Label(2, 4, "横向总数", wcf)); 
		           ws.addCell(new Label(3, 4, "其中：人文社会科学", wcf)); 
		           ws.addCell(new Label(4, 4, "纵向总数", wcf)); 
		           ws.addCell(new Label(5, 4, "其中：人文社会科学", wcf));
		           ws.mergeCells(0, 2, 0, 4);
		           ws.mergeCells(1, 2, 5, 2);
		           ws.mergeCells(1, 3, 1, 4);
		           ws.mergeCells(2, 3, 3, 3);
		           ws.mergeCells(4, 3, 5, 3);
		           ws.addCell(new Label(1, 5, bean.getResItemNum().toString(), wcf1)); 
		           ws.addCell(new Label(2, 5, bean.getHresItemNum().toString(), wcf1));
		           ws.addCell(new Label(3, 5, bean.getHhumanItemNum().toString(), wcf1));
		           ws.addCell(new Label(4, 5, bean.getZresItemNum().toString(), wcf1));
		           ws.addCell(new Label(5, 5, bean.getZhumanItemNum().toString(), wcf1));
		           ws.addCell(new Label(1, 6, bean.getResItemFund().toString(), wcf1)); 
		           ws.addCell(new Label(2, 6, bean.getHitemFund().toString(), wcf1));
		           ws.addCell(new Label(3, 6, bean.getHhumanItemFund().toString(), wcf1));
		           ws.addCell(new Label(4, 6, bean.getZitemFund().toString(), wcf1));
		           ws.addCell(new Label(5, 6, bean.getZhumanItemFund().toString(), wcf1));
		           
		           	           
		           ws.addCell(new Label(1, 8, "2.近一届科研成果奖数（项）", wcf)); 
		           ws.addCell(new Label(1, 9, "总数", wcf)); 
		           ws.addCell(new Label(2, 9, "其中", wcf)); 
		           ws.addCell(new Label(2, 10, "国家级", wcf)); 
		           ws.addCell(new Label(3, 10, "省部级", wcf)); 
		           ws.addCell(new Label(4, 10, "市厅级", wcf)); 
		           ws.addCell(new Label(5, 10, "校级", wcf)); 	           
		           ws.mergeCells(1, 8, 5, 8);
		           ws.mergeCells(1, 9, 1, 10);
		           ws.mergeCells(2, 9, 5, 9);	           
		           ws.addCell(new Label(1, 11, bean.getResAwardNum().toString(), wcf1)); 
		           ws.addCell(new Label(2, 11, bean.getNationResAward().toString(), wcf1));
		           ws.addCell(new Label(3, 11, bean.getProviResAward().toString(), wcf1));
		           ws.addCell(new Label(4, 11, bean.getCityResAward().toString(), wcf1));
		           ws.addCell(new Label(5, 11, bean.getSchResAward().toString(), wcf1));
		           
		           
		           ws.addCell(new Label(1, 13, "3.发表论文数（篇）", wcf)); 
		           ws.addCell(new Label(1, 14, "总数", wcf)); 
		           ws.addCell(new Label(2, 14, "其中", wcf)); 
		           ws.addCell(new Label(2, 15, "SCI", wcf)); 
		           ws.addCell(new Label(3, 15, "SSCI", wcf)); 
		           ws.addCell(new Label(4, 15, "EI", wcf)); 
		           ws.addCell(new Label(5, 15, "ISTP", wcf)); 
		           ws.addCell(new Label(6, 15, "国内核心期刊", wcf)); 
		           ws.addCell(new Label(7, 15, "CSSCI", wcf)); 
		           ws.addCell(new Label(8, 15, "CSCD", wcf)); 
		           ws.addCell(new Label(9, 15, "其他", wcf)); 
		           ws.mergeCells(1, 13, 9, 13);
		           ws.mergeCells(1, 14, 1, 15);
		           ws.mergeCells(2, 14, 9, 14);
		           ws.addCell(new Label(1, 16, bean.getPaperNum().toString(), wcf1)); 
		           ws.addCell(new Label(2, 16, bean.getSci().toString(), wcf1));
		           ws.addCell(new Label(3, 16, bean.getSsci().toString(), wcf1));
		           ws.addCell(new Label(4, 16, bean.getEi().toString(), wcf1));
		           ws.addCell(new Label(5, 16, bean.getIstp().toString(), wcf1));
		           ws.addCell(new Label(6, 16, bean.getInlandCoreJnal().toString(), wcf1)); 
		           ws.addCell(new Label(7, 16, bean.getCssci().toString(), wcf1));
		           ws.addCell(new Label(8, 16, bean.getCscd().toString(), wcf1));
		           ws.addCell(new Label(9, 16, bean.getOtherPaper().toString(), wcf1));

		           
		           
		           ws.addCell(new Label(1, 18, "4.出版专译著（册）", wcf)); 
		           ws.addCell(new Label(1, 19, "总数", wcf)); 
		           ws.addCell(new Label(2, 19, "专著", wcf)); 
		           ws.addCell(new Label(3, 19, "译著", wcf)); 
		           ws.mergeCells(1, 18, 3, 18);	           
		           ws.addCell(new Label(1, 20, bean.getPublicationNum().toString(), wcf1)); 
		           ws.addCell(new Label(2, 20, bean.getTreatises().toString(), wcf1));
		           ws.addCell(new Label(3, 20, bean.getTranslation().toString(), wcf1));
		           
		           ws.addCell(new Label(1, 22, "5.获准专利（项）", wcf)); 
		           ws.addCell(new Label(1, 23, "总数", wcf)); 
		           ws.addCell(new Label(2, 23, "发明专利", wcf)); 
		           ws.addCell(new Label(3, 23, "实用新型专利", wcf)); 
		           ws.addCell(new Label(4, 23, "外观设计专利", wcf)); 
		           ws.mergeCells(1, 22, 6, 22);
		           ws.mergeCells(4, 23, 6, 23);
		           ws.mergeCells(4, 24, 6, 24);
		           ws.addCell(new Label(1, 24, bean.getPatentNum().toString(), wcf1)); 
		           ws.addCell(new Label(2, 24, bean.getInventPatent().toString(), wcf1));
		           ws.addCell(new Label(3, 24, bean.getUtilityPatent().toString(), wcf1));
		           ws.addCell(new Label(4, 24, bean.getDesignPatent().toString(), wcf1));
		           
		          wwb.write();
		          wwb.close();

		        } catch (IOException e){
		        } catch (RowsExceededException e){
		        } catch (WriteException e){}
		}
        inputStream = new ByteArrayInputStream(fos.toByteArray());	
        return inputStream;
	}
	
	public String execute() throws Exception{
		System.out.println("excelName=============" + this.excelName) ;
		if(this.getInputStream() == null){
			return "test";
		}else{
			return "success" ;
		}
		
	}

	public String getRows() {
		return rows;
	}


	public void setRows(String rows) {
		this.rows = rows;
	}


	public String getPage() {
		return page;
	}


	public void setPage(String page) {
		this.page = page;
	}
	
	public T410_Bean getT410_bean() {
		return T410_bean;
	}

	public void setT410_bean(T410_Bean T410Bean) {
		T410_bean = T410Bean;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getIds() {
		return ids;
	}

	public void setSeqNum(Integer seqNum) {
		this.seqNum = seqNum;
	}

	public Integer getSeqNum() {
		return seqNum;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}

	public String getExcelName() {
		try {
			this.excelName = URLEncoder.encode(excelName, "UTF-8");
			//this.saveFile = new String(saveFile.getBytes("ISO-8859-1"),"UTF-8");// 中文乱码解决
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return excelName;
	}

	public void setSelectYear(String selectYear) {
		this.selectYear = selectYear;
	}

	public String getSelectYear() {
		return selectYear;
	}

	public void setCheckNum(int checkNum) {
		this.checkNum = checkNum;
	}

	public int getCheckNum() {
		return checkNum;
	}

	public void setCheckFlag(int checkFlag) {
		this.checkFlag = checkFlag;
	}

	public int getCheckFlag() {
		return checkFlag;
	}
}