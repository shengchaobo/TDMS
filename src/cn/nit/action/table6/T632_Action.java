package cn.nit.action.table6;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import cn.nit.bean.UserinfoBean;
import cn.nit.bean.table6.T621_Bean;
import cn.nit.bean.table6.T622_Bean;
import cn.nit.bean.table6.T631_Bean;
import cn.nit.bean.table6.T632_Bean;
import cn.nit.constants.Constants;
import cn.nit.dao.table6.T632_Dao;
import cn.nit.dbconnection.DBConnection;
import cn.nit.service.CheckService;
import cn.nit.service.table6.T632_Service;
import cn.nit.util.DAOUtil;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;

/**
 * 待完成！！！！！！！
 * 
 * @author Yuan
 */
public class T632_Action {

	/** �?32的Service�?*/
	private T632_Service T632_service = new T632_Service();

	private CheckService check_services = new CheckService();
	
	/** �?32的Bean实体�?*/
	T632_Bean T632_bean = new T632_Bean();
//	
//	T632_Dao T632_dao = new T632_Dao();
	
	
	/**  哪一年数据  */
	private String selectYear;

	/**  导出的excelName名 */
	private String excelName ;
	
	/**  待审核数据的要删除的序列集  */
	private String ids; //删除的id
	
	/**  审核状态显示判别标志  */
	private int checkNum ;



	
	public CheckService getCheck_services() {
		return check_services;
	}

	public void setCheck_services(CheckService checkServices) {
		check_services = checkServices;
	}

//	public T632_Dao getT632_dao() {
//		return T632_dao;
//	}
//
//	public void setT632_dao(T632_Dao t632Dao) {
//		T632_dao = t632Dao;
//	}

	public String getSelectYear() {
		return selectYear;
	}

	public void setSelectYear(String selectYear) {
		this.selectYear = selectYear;
	}

	public int getCheckNum() {
		return checkNum;
	}

	public void setCheckNum(int checkNum) {
		this.checkNum = checkNum;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	

	public void loadInfo() {
		List<T632_Bean> list=T632_service.getYearInfo(this.getSelectYear());
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
	}
	
	
	//插入一个新的信息
	public void insert(){
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		HttpServletResponse response = ServletActionContext.getResponse();
		
		//插入时间
		String year = this.getSelectYear();
				
		int ExamGraEnrollNum = T632_bean.getExamGraInSch()+T632_bean.getExamGraOutSch();
		T632_bean.setExamGraEnrollNum(ExamGraEnrollNum);
		T632_bean.setSumGoOnHighStudyNum(T632_bean.getRecommendGraNum()+T632_bean.getExamGraInSch()+T632_bean.getExamGraOutSch()+T632_bean.getAbroadNum());
		T632_bean.setGoOnHighStudy(T632_bean.getSumGoOnHighStudyNum());
		T632_bean.setSumEmployNum(T632_bean.getGovermentNum()+T632_bean.getPubInstiNum()+T632_bean.getEnterpriseNum()+T632_bean.getForceNum()
				+T632_bean.getFlexibleEmploy()+T632_bean.getNationItemEmploy()+T632_bean.getOtherEmploy()+T632_bean.getGoOnHighStudy());//统计生成-应届就业总人�?
		boolean flag = T632_service.insert(T632_bean, year);
		PrintWriter out = null ;
		
		try{
			response.setContentType("text/html; charset=UTF-8") ;
			out = response.getWriter() ;
			if(flag){
				out.print("{\"state\":true,data:\"录入成功!!!\"}") ;
			}else{
				out.print("{\"state\":false,data:\"录入失败!!!\"}") ;
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
	
//
//	/** 逐条插入数据 */
//	public void insert() {
//
//		
//		T632_bean.setSumGoOnHighStudyNum(T632_bean.getRecommendGraNum()+T632_bean.getExamGraInSch()+T632_bean.getExamGraOutSch()+T632_bean.getAbroadNum());//统计生成-应届升学总人�?
//		T632_bean.setExamGraEnrollNum(T632_bean.getExamGraInSch()+T632_bean.getExamGraOutSch());//统计生成-考研录取总人�?
//		T632_bean.setGoOnHighStudy(T632_bean.getSumGoOnHighStudyNum());//引用生成--升学
//		
//		T632_bean.setSumEmployNum(T632_bean.getGovermentNum()+T632_bean.getPubInstiNum()+T632_bean.getEnterpriseNum()+T632_bean.getForceNum()
//				+T632_bean.getFlexibleEmploy()+T632_bean.getNationItemEmploy()+T632_bean.getOtherEmploy()+T632_bean.getGoOnHighStudy());//统计生成-应届就业总人�?
//		
//		boolean flag = T632_service.insert(T632_bean);
//		PrintWriter out = null;
//
//		try {
//			getResponse().setContentType("text/html; charset=UTF-8");
//			// getResponse().setHeader("Content-type", "text/html");
//			out = getResponse().getWriter();
//			if (flag) {
//				out.print("{\"state\":true,data:\"录入成功!!!\"}");
//			} else {
//				out.print("{\"state\":false,data:\"录入失败!!!\"}");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			out.print("{\"state\":false,data:\"录入失败!!!\"}");
//		} finally {
//			if (out != null) {
//				out.close();
//			}
//		}
//		out.flush();
//	}

	/**  编辑数据  */
	public void edit(){
		int ExamGraEnrollNum = T632_bean.getExamGraInSch()+T632_bean.getExamGraOutSch();
		T632_bean.setExamGraEnrollNum(ExamGraEnrollNum);
		T632_bean.setSumGoOnHighStudyNum(T632_bean.getRecommendGraNum()+T632_bean.getExamGraInSch()+T632_bean.getExamGraOutSch()+T632_bean.getAbroadNum());
		T632_bean.setGoOnHighStudy(T632_bean.getSumGoOnHighStudyNum());
		T632_bean.setSumEmployNum(T632_bean.getGovermentNum()+T632_bean.getPubInstiNum()+T632_bean.getEnterpriseNum()+T632_bean.getForceNum()
				+T632_bean.getFlexibleEmploy()+T632_bean.getNationItemEmploy()+T632_bean.getOtherEmploy()+T632_bean.getGoOnHighStudy());//统计生成-应届就业总人�?
		int flag = T632_service.update(T632_bean,this.getSelectYear()) ;
		if(flag == 2){
			int year = Integer.parseInt(this.getSelectYear());
			check_services.delete("T632", year);
		}
		
		PrintWriter out = null ;
	
		try{
			response.setContentType("text/html; charset=UTF-8") ;
			out = response.getWriter() ;
			if(flag == 2){
				//out.print("{\"state\":true,data:\"修改成功!!!\"}") ;
				out.print("{\"state\":true,data:\"修改成功!!!\",tag:2}") ;
			}else if(flag == 1){
				out.print("{\"state\":true,data:\"修改成功!!!\"}") ;
			}else{
				out.print("{\"state\":true,data:\"修改失败!!!\"}") ;
			}			
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"系统错误，请联系管理员!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
		out.flush() ;
	}
	

	/**  根据数据的id删除数据  */
	public void deleteByIds(){
		System.out.println("ids=" + this.getIds()) ;
		boolean flag = T632_service.deleteByIds(ids, this.getSelectYear()) ;
		//删除审核不通过信息
		int year = Integer.parseInt(this.getSelectYear());
		check_services.delete("T617", year);
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
	
	
	/**  修改某条数据的审核状态  */
	public void updateCheck(){
		
		HttpServletResponse response = ServletActionContext.getResponse();
		String TeaUnit = "全校合计";
		boolean flag = T632_service.updateCheck(this.getSelectYear(), TeaUnit, this.getCheckNum());
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
	
	
//	/** 为界面加载数�?*/
//	public void loadData() throws Exception {
//
//		  HttpServletResponse response = ServletActionContext.getResponse() ;	
//			
//			String cond = null;
//			StringBuffer conditions = new StringBuffer();
//			
//			if(this.getSeqNum() == null && this.getStartTime() == null && this.getEndTime() == null){			
//				cond = null;	
//			}else{			
//				if(this.getSeqNum()!=null){
//					conditions.append(" and SeqNumber=" + this.getSeqNum()) ;
//				}
//				
//				if(this.getStartTime() != null){
//					conditions.append(" and cast(CONVERT(DATE, Time)as datetime)>=cast(CONVERT(DATE, '" 
//							+ TimeUtil.changeFormat4(this.startTime) + "')as datetime)") ;
//				}
//				
//				if(this.getEndTime() != null){
//					conditions.append(" and cast(CONVERT(DATE, Time)as datetime)<=cast(CONVERT(DATE, '" 
//							+ TimeUtil.changeFormat4(this.getEndTime()) + "')as datetime)") ;
//				}
//				cond = conditions.toString();
//			}
//			
//			List<T632_Bean> list = T632_service.getPageInfoList(cond, null, this.getRows(), this.getPage()) ;
//			String TeaInfoJson = this.toBeJson(list,T632_service.getTotal(cond, null));
//			//private JSONObject jsonObj;
//			
//			PrintWriter out = null ;
//
//			if(TeaInfoJson == null){			
//				return ;
//			}else{
//				try {
//					
//					System.out.println(TeaInfoJson) ;
//					response.setContentType("application/json;charset=UTF-8") ;
//					out = response.getWriter() ;
//					out.print(TeaInfoJson) ;
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}finally{
//					if(out != null){
//						out.flush() ;
//						out.close() ;
//					}
//				}
//			}
//	}

//	// 将分页系统的总数以及当前页的list转化一个json传页面显�?
//	private String toBeJson(List<T632_Bean> list, int total) throws Exception {
//		// TODO Auto-generated method stub
//		HttpServletResponse response = ServletActionContext.getResponse();
//		HttpServletRequest request = ServletActionContext.getRequest();
//
//		JSONObject testjson = new JSONObject();
//		testjson.accumulate("total", total);
//		testjson.accumulate("rows", list);
//
//		String json = testjson.toString();
//		System.out.println(json);
//		return json;
//	}


//	/** 编辑数据 */
//	public void edit() {
//		
//		T632_bean.setSumGoOnHighStudyNum(T632_bean.getRecommendGraNum()+T632_bean.getExamGraInSch()+T632_bean.getExamGraOutSch()+T632_bean.getAbroadNum());//统计生成-应届升学总人�?
//		T632_bean.setExamGraEnrollNum(T632_bean.getExamGraInSch()+T632_bean.getExamGraOutSch());//统计生成-考研录取总人�?
//		T632_bean.setGoOnHighStudy(T632_bean.getSumGoOnHighStudyNum());//引用生成--升学
//		
//		T632_bean.setSumEmployNum(T632_bean.getGovermentNum()+T632_bean.getPubInstiNum()+T632_bean.getEnterpriseNum()+T632_bean.getForceNum()
//				+T632_bean.getFlexibleEmploy()+T632_bean.getNationItemEmploy()+T632_bean.getOtherEmploy()+T632_bean.getGoOnHighStudy());//统计生成-应届就业总人�?
//
//		boolean flag = T632_service.update(T632_bean);
//		PrintWriter out = null;
//
//		try {
//			out = getResponse().getWriter();
//			if (flag) {
//				out.print("{\"state\":true,data:\"编辑成功!!!\"}");
//			} else {
//				out.print("{\"state\":true,data:\"编辑失败!!!\"}");
//			}
//			out.flush();
//		} catch (Exception e) {
//			e.printStackTrace();
//			out.print("{\"state\":false,data:\"系统错误，请联系管理�?!!\"}");
//		} finally {
//			if (out != null) {
//				out.close();
//			}
//		}
//	}

//	/** 根据数据的id删除数据 */
//	public void deleteByIds() {
//		System.out.println("ids=" +this.getIds());
//		boolean flag = T632_service.deleteItemsByIds(ids);
//		PrintWriter out = null;
//
//		try {
//			out = getResponse().getWriter();
//
//			if (flag) {
//				out.print("{\"state\":true,data:\"数据删除成功!!!\"}");
//			} else {
//				out.print("{\"state\":false,data:\"数据删除失败!!!\"}");
//			}
//
//			out.flush();
//		} catch (Exception e) {
//			e.printStackTrace();
//			out.print("{\"state\":false,data:\"系统错误，请联系管理�?!!\"}");
//		} finally {
//			if (out != null) {
//				out.close();
//			}
//		}
//	}

	public InputStream getInputStream() {

		InputStream inputStream = null ;
		ByteArrayOutputStream fos = new ByteArrayOutputStream();
		
		UserinfoBean userBean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		String sheetName = null;
		List<T632_Bean> list = null;
		
		if("111".equals(userBean.getRoleID())){
			String year = (String)request.getSession().getAttribute("allYear") ;
			list = T632_service.getYearInfo(year,Constants.PASS_CHECK);
			sheetName = "表6-3-2分专业应届本科毕业生就业情况（招就处）";
		}else{					
			list = T632_service.getYearInfo(this.getSelectYear(),Constants.PASS_CHECK);						
			sheetName = this.excelName;
		}
		
		try {
			
				WritableWorkbook wwb;
				try{
					
					 fos = new ByteArrayOutputStream();
			            wwb = Workbook.createWorkbook(fos);
			            WritableSheet ws = wwb.createSheet(sheetName, 0);        // 创建一个工作表

			            //    设置表头的文字格式
			            
			            WritableFont wf = new WritableFont(WritableFont.ARIAL,12,WritableFont.BOLD,false,
			                    UnderlineStyle.NO_UNDERLINE,Colour.BLACK);    
			            WritableCellFormat wcf = new WritableCellFormat(wf);
			            wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
			            wcf.setAlignment(Alignment.CENTRE);
			            wcf.setBorder(Border.ALL, BorderLineStyle.THIN,
				        		     jxl.format.Colour.BLACK);
			            
			            //    设置内容单无格的文字格式
			            WritableFont wf1 = new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD,false,
				                    UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
			            WritableCellFormat wcf1 = new WritableCellFormat(wf1);       
			            wcf1.setVerticalAlignment(VerticalAlignment.CENTRE);
			            wcf1.setAlignment(Alignment.CENTRE);
			            wcf1.setBorder(Border.ALL, BorderLineStyle.THIN,
				        		     jxl.format.Colour.BLACK);
			            ws.setRowView(1, 500);
						//第一行存表名
						ws.addCell(new Label(0, 0, sheetName, wcf)); 
						ws.mergeCells(0, 0, 1, 0);
						
						ws.addCell(new Label(0, 2, "", wcf)); 
						ws.mergeCells(0, 2, 4, 2);
						ws.addCell(new Label(5, 2, "1.应届毕业生就业基本情况(人)", wcf)); 
						ws.mergeCells(5, 2, 13, 2);
						ws.addCell(new Label(14, 2, "2.应届毕业生升学基本情况(人)", wcf)); 
						ws.mergeCells(14, 2, 20, 2);
						
						ws.addCell(new Label(0, 3, "序号", wcf)); ws.mergeCells(0, 3, 0, 4);
						ws.addCell(new Label(1, 3, "教学单位", wcf)); ws.mergeCells(1, 3, 1, 4);
						ws.addCell(new Label(2, 3, "单位号", wcf)); ws.mergeCells(2, 3, 2, 4);
						ws.addCell(new Label(3, 3, "专业名称", wcf)); ws.mergeCells(3, 3, 3, 4);
						ws.addCell(new Label(4, 3, "专业代码", wcf)); ws.mergeCells(4, 3, 4, 4);
						ws.addCell(new Label(5, 3, "应届就业总人数", wcf)); ws.mergeCells(5, 3, 5, 4);
						ws.addCell(new Label(6, 3, "政府机构", wcf)); ws.mergeCells(6, 3, 6, 4);
						ws.addCell(new Label(7, 3, "事业单位", wcf)); ws.mergeCells(7, 3, 7, 4);
						ws.addCell(new Label(8, 3, "企业", wcf)); ws.mergeCells(8, 3, 8, 4);
						ws.addCell(new Label(9, 3, "部队", wcf)); ws.mergeCells(9, 3, 9, 4);
						ws.addCell(new Label(10, 3, "灵活就业", wcf)); ws.mergeCells(10, 3, 10, 4);
						ws.addCell(new Label(11, 3, "升学", wcf)); ws.mergeCells(11, 3, 11, 4);
						ws.addCell(new Label(12, 3, "参加国家地方项目就业", wcf)); ws.mergeCells(12, 3, 12, 4);
						ws.addCell(new Label(13, 3, "其他", wcf)); ws.mergeCells(13, 3, 13, 4);
						ws.addCell(new Label(14, 3, "应届升学总人数", wcf)); ws.mergeCells(14, 3, 14, 4);
						ws.addCell(new Label(15, 3, "免试推荐研究生", wcf)); ws.mergeCells(15, 3, 15, 4);
						ws.addCell(new Label(16, 3, "考研报名人数", wcf)); ws.mergeCells(16, 3, 16, 4);
						ws.addCell(new Label(17, 3, "考研录取", wcf)); ws.mergeCells(17, 3, 19, 3);
						ws.addCell(new Label(20, 3, "出国（境）留学", wcf)); ws.mergeCells(20, 3, 20, 4);
						ws.addCell(new Label(17, 4, "总人数", wcf));
						ws.addCell(new Label(18, 4, "考取本校", wcf));
						ws.addCell(new Label(19, 4, "考取外校", wcf));
						
						//向表中写数据
						if(list.size()>0&&list!=null){
						int k=5;//从第6行开始写数据、（其中第6行是全校统计）
						for(int j=0;j<list.size();j++){
							T632_Bean bean1 =  list.get(j);
							if(j==0){
								ws.addCell(new Label(0,5, bean1.getTeaUnit(), wcf));
								ws.mergeCells(0, 5, 4, 5);
								ws.addCell(new Label(5, 5, bean1.getSumEmployNum()+"", wcf1));
								ws.addCell(new Label(6, 5, bean1.getGovermentNum()+"", wcf1));
								ws.addCell(new Label(7, 5, bean1.getPubInstiNum()+"", wcf1));
								ws.addCell(new Label(8, 5, bean1.getEnterpriseNum()+"", wcf1));
								ws.addCell(new Label(9, 5, bean1.getForceNum()+"", wcf1));
								ws.addCell(new Label(10, 5, bean1.getFlexibleEmploy()+"", wcf1));
								ws.addCell(new Label(11, 5, bean1.getGoOnHighStudy()+"", wcf1));
								ws.addCell(new Label(12, 5, bean1.getNationItemEmploy()+"", wcf1));
								ws.addCell(new Label(13, 5, bean1.getOtherEmploy()+"", wcf1));
								ws.addCell(new Label(14, 5, bean1.getSumGoOnHighStudyNum()+"", wcf1));
								ws.addCell(new Label(15, 5, bean1.getRecommendGraNum()+"", wcf1));
								ws.addCell(new Label(16, 5, bean1.getExamGraApplyNum()+"", wcf1));
								ws.addCell(new Label(17, 5, bean1.getExamGraEnrollNum()+"", wcf1));
								ws.addCell(new Label(18, 5, bean1.getExamGraInSch()+"", wcf1));
								ws.addCell(new Label(19, 5, bean1.getExamGraOutSch()+"", wcf1));
								ws.addCell(new Label(20, 5, bean1.getAbroadNum()+"", wcf1));

							}else{
								ws.addCell(new Label(0, k,j+"", wcf1));
								ws.addCell(new Label(1, k, bean1.getTeaUnit(), wcf1));
								ws.addCell(new Label(2, k, bean1.getUnitId(), wcf1));
								ws.addCell(new Label(3, k, bean1.getMajorName(), wcf1));
								ws.addCell(new Label(4, k, bean1.getMajorId(), wcf1));
								ws.addCell(new Label(5, k, bean1.getSumEmployNum()+"", wcf1));
								ws.addCell(new Label(6, k, bean1.getGovermentNum()+"", wcf1));
								ws.addCell(new Label(7, k, bean1.getPubInstiNum()+"", wcf1));
								ws.addCell(new Label(8, k, bean1.getEnterpriseNum()+"", wcf1));
								ws.addCell(new Label(9, k, bean1.getForceNum()+"", wcf1));
								ws.addCell(new Label(10, k, bean1.getFlexibleEmploy()+"", wcf1));
								ws.addCell(new Label(11, k, bean1.getGoOnHighStudy()+"", wcf1));
								ws.addCell(new Label(12, k, bean1.getNationItemEmploy()+"", wcf1));
								ws.addCell(new Label(13, k, bean1.getOtherEmploy()+"", wcf1));
								ws.addCell(new Label(14, k, bean1.getSumGoOnHighStudyNum()+"", wcf1));
								ws.addCell(new Label(15, k, bean1.getRecommendGraNum()+"", wcf1));
								ws.addCell(new Label(16, k, bean1.getExamGraApplyNum()+"", wcf1));
								ws.addCell(new Label(17, k, bean1.getExamGraEnrollNum()+"", wcf1));
								ws.addCell(new Label(18, k, bean1.getExamGraInSch()+"", wcf1));
								ws.addCell(new Label(19, k, bean1.getExamGraOutSch()+"", wcf1));
								ws.addCell(new Label(20, k, bean1.getAbroadNum()+"", wcf1));
							}
							k++;
						}
						}
						    wwb.write();
				            wwb.close();

				} catch (IOException e){
		        } catch (RowsExceededException e){
		        } catch (WriteException e){}
				
			
		
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}

		inputStream = new ByteArrayInputStream(fos.toByteArray());
		return inputStream ;
	}
			
						
//			String sheetName = this.getExcelName();
//			
//			List<String> columns = new ArrayList<String>();
//			
//		
//			
//			columns.add("序号");
//			columns.add("教学单位");
//			columns.add("单位");
//			columns.add("专业名称");
//			columns.add("专业代码");
//			columns.add("应届就业总人");
//			columns.add("政府机构就业人数");
//			columns.add("事业单位就业人数");
//			columns.add("企业就业人数");
//			columns.add("部队人数");
//			columns.add("灵活就业人数");
//			columns.add("升学人数");
//			columns.add("参加国家地方项目就业人数");
//			columns.add("其他人数");
//			
//			columns.add("应届升学总人");
//			columns.add("免试推荐研究生人");
//			columns.add("考研报名人数");
//			columns.add("考研录取总人");
//			columns.add("考取本校人数");
//			columns.add("考取外校人数");
//			columns.add("出国（境）留学人");
//			
//			columns.add("时间");
//			columns.add("备注");
//			
//
//			Map<String,Integer> maplist = new HashMap<String,Integer>();
//		
//
//			
//			maplist.put("seqNumber", 0);
//			maplist.put("teaUnit", 1);
//			maplist.put("unitId", 2);
//			maplist.put("majorName", 3);
//			maplist.put("majorId", 4);
//			
//			maplist.put("sumEmployNum", 5);
//			maplist.put("govermentNum", 6);
//			maplist.put("pubInstiNum", 7);
//			maplist.put("enterpriseNum", 8);
//			maplist.put("forceNum", 9);
//			maplist.put("flexibleEmploy", 10);
//			maplist.put("goOnHighStudy", 11);
//			maplist.put("nationItemEmploy", 12);
//			maplist.put("otherEmploy", 13);
//			
//			maplist.put("sumGoOnHighStudyNum", 14);
//			maplist.put("recommendGraNum", 15);
//			maplist.put("examGraApplyNum", 16);
//			maplist.put("examGraEnrollNum", 17);
//			maplist.put("examGraInSch", 18);
//			maplist.put("examGraOutSch", 19);
//			maplist.put("abroadNum", 20);
//						
//			maplist.put("time", 21);
//			maplist.put("note", 22);
//				
//			inputStream = new ByteArrayInputStream(ExcelUtil.exportExcel(list, "表6-3-2分专业应届本科毕业生就业情况（招就处）", maplist,columns).toByteArray());
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null ;
//		}
//
//		return inputStream ;
//	}

	public String execute() throws Exception {

		getResponse().setContentType("application/octet-stream;charset=UTF-8");
		return "success";
	}

	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public HttpSession getSession() {
		return getRequest().getSession();
	}

	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}


	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}





	public T632_Service getT632_service() {
		return T632_service;
	}

	public void setT632_service(T632_Service t631Service) {
		T632_service = t631Service;
	}

	public T632_Bean getT632_bean() {
		return T632_bean;
	}

	public void setT632_bean(T632_Bean t632Bean) {
		T632_bean = t632Bean;
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

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}

	public static void main(String args[]) {
		String match = "[\\d]+";
		System.out.println("23gfhf4".matches(match));
	}




}
