package cn.nit.action.table5;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
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

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.UserinfoBean;
import cn.nit.bean.table2.T21_Bean;
import cn.nit.bean.table5.T551_Bean;
import cn.nit.constants.Constants;
import cn.nit.dao.table5.T551DAO;
import cn.nit.excel.imports.table5.T551Excel;
import cn.nit.service.CheckService;
import cn.nit.service.table5.T551Service;
import cn.nit.util.DateUtil;
import cn.nit.util.TimeUtil;

public class T551Action {

//	/**  表T551的数据库操作类  */
//	private T551DAO t551Dao = new T551DAO() ;
	
	private T551Excel t551Excel=new T551Excel();

	/**  表551的Service类  */
	private T551Service t551Ser = new T551Service() ;
	
	/**  表551的Bean实体类  */
	private T551_Bean t551Bean = new T551_Bean() ;
	
	/**取得某个表的审核信息*/
	private CheckService check_services = new CheckService();
	
	/**  审核状态显示判别标志  */
	private int checkNum ;
	
	public int getCheckNum() {
		return checkNum;
	}

	public void setCheckNum(int checkNum) {
		this.checkNum = checkNum;
	}

	/**excel导出名字*/
	private String excelName; //
	
	/**导出数据说要的年份*/
	private String Year;//
	

	/**  待审核数据的查询的序列号  */
	private Integer seqNum ;
	
	/**  待审核数据查询的起始时间  */
	private Date startTime ;
	
	/**  待审核数据查询的结束时间  */
	private Date endTime ;
	
	/**  数据的SeqNumber编号  */
	private String ids ;
	
	/**  当前查询的是第几页  */
	private String page ;
	
	/**每页显示的条数  */
	private String rows ;
	
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
	
	public String getSelectYear() {
		return selectYear;
	}

	public void setSelectYear(String selectYear) {
		this.selectYear = selectYear;
	}
	
	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;

	/**  逐条插入数据  */
	public void insert(){
//		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		t551Bean.setTime(new Date());
		t551Bean.setCheckState(Constants.WAIT_CHECK);
		//这还没确定,设置填报者的职工号与部门号
//		UserRoleBean userinfo = (UserRoleBean)getSession().getAttribute("userinfo") ;
//		undergraCSBaseTea.setFillTeaID(userinfo.getTeaID()) ;
		
		boolean flag = t551Ser.insert(t551Bean) ;
		PrintWriter out = null ;
		
		try{
			getResponse().setContentType("text/html; charset=UTF-8") ;
//			getResponse().setHeader("Content-type", "text/html");  
			out = getResponse().getWriter() ;
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
	
	/**  为界面加载数据  */
	public void auditingData(){
			
//			System.out.println("輸出輸出輸出");
			
			if(this.page == null || this.page.equals("") || !page.matches("[\\d]+")){
				return ;
			}
			
			if(this.rows == null || this.rows.equals("") || !rows.matches("[\\d]+")){
				return ;
			}
			
			String cond = null;
			StringBuffer conditions = new StringBuffer();
			
			if(this.getSeqNum() == null && this.getStartTime() == null && this.getEndTime() == null && this.getCheckNum() == 0){			
				cond = null;	
			}else{		
				//查询条件判断
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
				
//				System.out.println("审核状态："+this.getCheckNum());
				
//				System.out.println(this.queryYear);
				//审核状态判断
				if(this.getCheckNum() == Constants.WAIT_CHECK ){
					conditions.append(" and CheckState=" + this.getCheckNum()) ;
				}else if(this.getCheckNum() == (Constants.PASS_CHECK)){
					conditions.append(" and CheckState=" + this.getCheckNum()) ;
					if(this.getQueryYear() != null){
						conditions.append(" and Time like '" + this.queryYear + "%'");
					}else{
						 Calendar now = Calendar.getInstance();  
						 this.setQueryYear(now.get(Calendar.YEAR)+"");
						 conditions.append(" and Time like '" + this.queryYear + "%'");
					}
				}else if(this.getCheckNum() == (Constants.NOPASS_CHECK)){
					conditions.append(" and CheckState=" + this.getCheckNum()) ;
				}else if(this.getCheckNum() == (Constants.NO_CHECK)){
					conditions.append(" and CheckState!=" + Constants.PASS_CHECK) ;
				}
				
				cond = conditions.toString();
			}

//			System.out.println(this.page);
//			System.out.println(this.rows);
//			System.out.println(this.getSeqNum());
			String pages = t551Ser.auditingData(cond, null, Integer.parseInt(page), Integer.parseInt(rows)) ;
			
//			System.out.println("pages:"+pages);
			PrintWriter out = null ;
			
			try{
				getResponse().setContentType("text/html; charset=UTF-8") ;
				out = getResponse().getWriter() ;
				out.print(pages) ;
			}catch(Exception e){
				e.printStackTrace() ;
				return ;
			}finally{
				if(out != null){
					out.close() ;
				}
			}
		}


	
	/**  编辑数据  */
	public void edit(){

		
		boolean flag = false;
		int tag = 0;
		//获得该条审数据审核状态
		int state = t551Ser.getCheckState(t551Bean.getSeqNumber());
		 
		//如果是待审核，直接修改
		if(state == Constants.WAIT_CHECK){
			t551Bean.setCheckState(Constants.WAIT_CHECK);
			flag = t551Ser.update(t551Bean) ;
			if(flag) tag = 1;
		}
		
		//如果是审核不通过，则修改该条数据，并将审核状态调节为待审核，同时删除该条数据在checkInfo表的信息
		if(state == Constants.NOPASS_CHECK){
			t551Bean.setCheckState(Constants.WAIT_CHECK);
			boolean flag1 = t551Ser.update(t551Bean) ;
			boolean flag2 = check_services.delete("T551",t551Bean.getSeqNumber());
			if(flag1&&flag2){
				flag = true;
				tag = 2;
			}
		}
		
		PrintWriter out = null ;
		
		try{
			getResponse().setContentType("text/html; charset=UTF-8") ;
			out = getResponse().getWriter() ;
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
	
		boolean flag = t551Ser.updateCheck(this.getSeqNum(),this.getCheckNum());
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
	
		boolean flag = t551Ser.checkAll();
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
	public void deleteCoursesByIds(){
//		System.out.println("ids=" + ids) ;
		boolean flag = t551Ser.deleteCoursesByIds(ids) ;
		
		//删除审核不通过信息
		check_services.delete("T551", ids);
		PrintWriter out = null ;
		
		try{
			out = getResponse().getWriter() ;
			
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
	
	
	/**数据导出
	 * @throws IOException */
	public InputStream getInputStream() throws IOException{
		

		UserinfoBean userBean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		List<T551_Bean> list= null;
		String sheetName = null;
		
		if("111".equals(userBean.getRoleID())){
			String year = (String)request.getSession().getAttribute("allYear") ;
			list = t551Ser.totalList(year);
			sheetName = "表5-5-1学风概况（学工处）";
		}else{
			list = t551Ser.totalList(this.getSelectYear());
			sheetName = this.excelName;
		}
		
		//List<T551_Bean> list = t551Ser.totalList(this.getSelectYear());
		ByteArrayOutputStream fos = null;
		 
			//String sheetName=this.excelName;	
		    WritableWorkbook wwb;
		    //统计合计
		    T551_Bean beanAll = new T551_Bean();
		    beanAll.setTeaUnit("全校合计");
		    int PartyMemNum=0; int CheatNum=0;double GoodClassRatio=0.0;
		    if(list!=null && list.size()>0){
		    	 for(int i=0;i<list.size();i++){
				    	T551_Bean bean = list.get(i);
				    	PartyMemNum+=bean.getPartyMemNum();
				    	CheatNum+=bean.getCheatNum();
//				    	GoodClassRatio+=bean.getGoodClassRatio();
				    }
//				    GoodClassRatio = GoodClassRatio/list.size();
				    
				    beanAll.setPartyMemNum(PartyMemNum);
				    beanAll.setCheatNum(CheatNum);
				    beanAll.setGoodClassRatio(GoodClassRatio);
				    list.add(0, beanAll);//将合计加入到list的第一个位置
		    }
		   
//		    beanAll.setGoodClassRatio(DateUtil.doubleTwo(""+GoodClassRatio));
		    
		  
		    
		    try {    
		           fos = new ByteArrayOutputStream();
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
		           wcf.setAlignment(jxl.write.Alignment.LEFT);
		           ws.setRowView(1, 500);
		           
		           //设置格式
				   WritableCellFormat wcf1 = new WritableCellFormat();
				   wcf1.setBorder(Border.ALL, BorderLineStyle.THIN,
						     jxl.format.Colour.BLACK); 
		           
		           ws.addCell(new Label(0, 0, sheetName, wcf)); 
		           ws.mergeCells(0, 0, 2, 0);
		             
		           ws.addCell(new Label(0, 2, "序号", wcf)); 
		           ws.addCell(new Label(1, 2, "教学单位", wcf));
		           ws.addCell(new Label(2,2,"单位号",wcf));
		           ws.addCell(new Label(3,2,"专业名称",wcf));
		           ws.addCell(new Label(4,2,"专业代码",wcf));
		           ws.addCell(new Label(5,2," 入校年份",wcf));
		           ws.addCell(new Label(6,2," 本科生党员数（个）",wcf));
		           ws.addCell(new Label(7,2," 考试违纪、作弊及受处分（人次）",wcf));
		           ws.addCell(new Label(8,2,"优良学风班的比例（%）",wcf));
		           
		           
		           if(list!=null && list.size()>0){
		        	   int j = 4;//第3行写合计，4行开始写数据
			           for(int i =0;i<list.size();i++){
			        	   T551_Bean bean = list.get(i);
			        	   if(i==0){
			        		   ws.addCell(new Label(0,3,bean.getTeaUnit(),wcf));
			        		   ws.mergeCells(0, 3, 5, 3);
			        		   ws.addCell(new Label(6,3,""+bean.getPartyMemNum(),wcf1));
			        		   ws.addCell(new Label(7,3,""+bean.getCheatNum(),wcf1));
			        		   ws.addCell(new Label(8,3,""+bean.getGoodClassRatio(),wcf1));
			        	   }else{
			        		   ws.addCell(new Label(0,j,""+(j-3),wcf1));
			        		   ws.addCell(new Label(1,j,bean.getTeaUnit(),wcf1));
			        		   ws.addCell(new Label(2,j,bean.getUnitID(),wcf1));
			        		   ws.addCell(new Label(3,j,bean.getMajorName(),wcf1));
			        		   ws.addCell(new Label(4,j,bean.getMajorID(),wcf1));
			        		   ws.addCell(new Label(5,j,bean.getAdmisSchYear(),wcf1));
			        		   ws.addCell(new Label(6,j,""+bean.getPartyMemNum(),wcf1));
			        		   ws.addCell(new Label(7,j,""+bean.getCheatNum(),wcf1));
			        		   ws.addCell(new Label(8,j,""+bean.getGoodClassRatio(),wcf1));
			        		   j++;
			        	   }
			           }
		           }
		          
		           
		           
		          wwb.write();
		          wwb.close();

		        } catch (IOException e){
		        } catch (RowsExceededException e){
		        } catch (WriteException e){}


		return 	 new ByteArrayInputStream(fos.toByteArray());
	}
	

	public String execute() throws Exception{

		getResponse().setContentType("application/octet-stream;charset=UTF-8") ;
		return "success" ;
	}
	
	public HttpServletRequest getRequest(){
		return ServletActionContext.getRequest() ;
	}
	
	public HttpSession getSession(){
		return getRequest().getSession() ;
	}
	
	public HttpServletResponse getResponse(){
		return ServletActionContext.getResponse() ;
	}

	public T551_Bean getT551Bean() {
		return t551Bean;
	}

	public void setT551Bean(T551_Bean t551Bean) {
		this.t551Bean = t551Bean;
	}

	
	public Integer getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(Integer seqNum) {
		this.seqNum = seqNum;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
	
	public String getYear() {
		return Year;
	}

	public void setYear(String year) {
		this.Year = year;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
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
	

}
