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
import cn.nit.bean.table5.T512_Bean;
import cn.nit.constants.Constants;
import cn.nit.dao.table5.T512_DAO;
import cn.nit.pojo.table5.T512POJO;
import cn.nit.pojo.table5.T513POJO;
import cn.nit.service.CheckService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table5.T512_Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;

public class T512_Action {
	
	private T512_Service t512_Sr=new T512_Service();
	
	private T512_Bean t512_Bean=new T512_Bean();
	
//	private T512_DAO t512_DAO=new T512_DAO();
	
	/**取得某个表的审核信息*/
	private CheckService check_services = new CheckService();
	
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
	
	/**  下载的excelName  */
	private String excelName ;
	
	/**导出选择年份*/
	private String selectYear;
	
	/**  审核状态显示判别标志  */
	private int checkNum ;
	
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
	
	/**  部门管理Service类  */
	private DiDepartmentService deSer = new DiDepartmentService() ;
	
	UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
	String fillUnitID = bean.getUnitID();
	
	public void insert(){
//		System.out.println("+++++++++++++++++++++++++++++++");
		t512_Bean.setTime(new Date());
//		System.out.println(new Date());
		//具体教学单位
		t512_Bean.setFillUnitID(fillUnitID);
		t512_Bean.setFillTeaID(bean.getTeaID());
		String unitName = deSer.getName(fillUnitID);
		t512_Bean.setCSUnit(unitName);//开课单位
		t512_Bean.setUnitID(fillUnitID);//开课单位号
		t512_Bean.setCheckState(Constants.WAIT_CHECK);
		boolean flag= t512_Sr.insert(t512_Bean);
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
		
            System.out.println("-------------------------------");
		if(this.page == null || this.page.equals("") || !page.matches("[\\d]+")){
			return ;
		}
		
		if(this.rows == null || this.rows.equals("") || !rows.matches("[\\d]+")){
			return ;
		}
		
		String cond = null;
		StringBuffer conditions = new StringBuffer();
		
		if(this.getSeqNum() == null && this.getStartTime() == null && this.getEndTime() == null && this.getCheckNum()==0){			
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
			}	else if(this.getCheckNum() == (Constants.PASS_CHECK)){
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
//			System.out.println("++++++++++++++++++++++++++++");
//			System.out.println("checkNum:"+this.getCheckNum());
			cond = conditions.toString();
		}
		
		
		
		//具体教学单位
		String tempUnitID = bean.getUnitID().substring(0,1);
		if(!"3".equals(tempUnitID)){
			fillUnitID = null;
		}
		
		
		String pages = t512_Sr.auditingData(cond, fillUnitID, Integer.parseInt(page), Integer.parseInt(rows)) ;
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

	public int getCheckNum() {
		return checkNum;
	}

	public void setCheckNum(int checkNum) {
		this.checkNum = checkNum;
	}

	/**  编辑数据  */
	public void edit(){
		
		boolean flag = false;
		int tag = 0;
		//获得该条数据审核状态
		int state = t512_Sr.getCheckState(t512_Bean.getSeqNumber());
		System.out.println("test"+state);
		//如果审核状态是待审核，则直接修改
		if(state == Constants.WAIT_CHECK){
			System.out.println("test"+state);
			t512_Bean.setCheckState(Constants.WAIT_CHECK);
			flag = t512_Sr.update(t512_Bean) ;
			if(flag) tag = 1;
		}
		//如果是审核不通过，则修改该条数据，并将审核状态调节为待审核，同时删除该条数据在checkInfo表的信息
		if(state == Constants.NOPASS_CHECK){
			t512_Bean.setCheckState(Constants.WAIT_CHECK);
			boolean flag1 = t512_Sr.update(t512_Bean) ;
			boolean flag2 = check_services.delete("T512",t512_Bean.getSeqNumber());
			if(flag1&&flag2){
				flag = true;
				tag = 2;
			}
		}
		
		PrintWriter out=null;
		
		try {
			out=getResponse().getWriter();
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("{\"state\":false,data:\"系统错误，请联系管理员!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
	/**  根据数据的id删除数据  */
	public void deleteCoursesByIds(){
		System.out.println("ids=" + ids) ;
		boolean flag = t512_Sr.deleteCoursesByIds(ids) ;
		//删除审核不通过信息
		check_services.delete("T512", ids);
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
	
	/**  修改某条数据的审核状态  */
	public void updateCheck(){
		HttpServletResponse response = ServletActionContext.getResponse();
	
		boolean flag = t512_Sr.updateCheck(this.getSeqNum(),this.getCheckNum());
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
	
		boolean flag = t512_Sr.checkAll();
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
	
	
	/**数据导出
	 * @throws IOException */
	public InputStream getInputStream() throws IOException{
		
			//具体教学单位
		    UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
			String fillUnitID = bean.getUnitID();
			List<T512POJO> list = t512_Sr.totalList(this.getSelectYear(),fillUnitID,Constants.PASS_CHECK);
		
			ByteArrayOutputStream fos = null;
	    

			String sheetName=this.excelName;	
		    WritableWorkbook wwb;
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
		           wcf.setAlignment(jxl.write.Alignment.CENTRE);
		           ws.setRowView(1, 500);
		     
		           //设置格式
				   WritableCellFormat wcf1 = new WritableCellFormat();
				   wcf1.setBorder(Border.ALL, BorderLineStyle.THIN,
						     jxl.format.Colour.BLACK); 
		           
		           ws.addCell(new Label(0, 0, sheetName, wcf)); 
		           ws.mergeCells(0, 0, 3, 0);
		             
		           ws.addCell(new Label(0, 2, "序号", wcf)); 
		           ws.addCell(new Label(1, 2, "学期", wcf)); 
		           ws.addCell(new Label(2, 2, "开课单位", wcf)); 
		           ws.addCell(new Label(3, 2, "单位号", wcf)); 
		           ws.addCell(new Label(4, 2, "上课专业名称", wcf)); 
		           ws.addCell(new Label(5, 2, "上课专业代码", wcf));
		           ws.mergeCells(0, 2, 0, 3); ws.mergeCells(1, 2, 1, 3);
		           ws.mergeCells(2, 2, 2, 3); ws.mergeCells(3, 2, 3, 3);
		           ws.mergeCells(4, 2, 4, 3); ws.mergeCells(5, 2, 5, 3);
		           
		           ws.addCell(new Label(6, 2, "1.本科课程情况", wcf));
		           ws.addCell(new Label(18, 2, "2.本科授课情况", wcf)); 
		           ws.addCell(new Label(27, 2, "3.使用教材", wcf)); 
		           
		           ws.mergeCells(6, 2, 17, 2); ws.mergeCells(18, 2, 26, 2); ws.mergeCells(27, 2, 29, 2);
		           
		           
		           ws.addCell(new Label(6, 3, "课程名称", wcf));
		           ws.addCell(new Label(7, 3, "课程编号", wcf)); 
		           ws.addCell(new Label(8, 3, "课程类别", wcf)); 
		           ws.addCell(new Label(9, 3, "课程性质", wcf)); 
		           ws.addCell(new Label(10, 3, "公选课类别", wcf)); 
		           ws.addCell(new Label(11, 3, "是否双语授课", wcf)); 
		           ws.addCell(new Label(12, 3, "学分", wcf)); 
		           ws.addCell(new Label(13, 3, "总学时", wcf)); 
		           ws.addCell(new Label(14, 3, "理论学时", wcf)); 
		           ws.addCell(new Label(15, 3, "实践学时", wcf)); 
		           ws.addCell(new Label(16, 3, "考核学时", wcf)); 
		           ws.addCell(new Label(17, 3, "实习、设计学时", wcf)); 
		           ws.addCell(new Label(18, 3, "授课年级", wcf)); 
		           ws.addCell(new Label(19, 3, "理论班级", wcf)); 
		           ws.addCell(new Label(20, 3, "开课班号", wcf)); 
		           ws.addCell(new Label(21, 3, "合班情况", wcf)); 
		           ws.addCell(new Label(22, 3, "学生人数", wcf)); 
		           ws.addCell(new Label(23, 3, "授课教师", wcf)); 
		           ws.addCell(new Label(24, 3, "授课教师工号", wcf)); 
		           ws.addCell(new Label(25, 3, "是否符合岗位资格", wcf)); 
		           ws.addCell(new Label(26, 3, "教师职称", wcf)); 
		           ws.addCell(new Label(27, 3, "使用情况", wcf)); 
		           ws.addCell(new Label(28, 3, "是否规划教材", wcf)); 
		           ws.addCell(new Label(29, 3, "是否获奖教材", wcf)); 
		           


		           
		           int n = list.size();
		           int j = 4;
		           int seq = 1;//序号
		           String isDoubleCs;
		           String isAccordJob;
		           String isPlanbook;
		           String isAwardbook;
		           if(list!=null && list.size()>0){
		        	   
		        	   for(int i = 0 ; i<n;i++){
		        		       T512POJO  pojo = list.get(i);
			        		   ws.addCell(new Label(0, j, seq+"", wcf1)); 
//			       
			        		   ws.addCell(new Label(1, j, pojo.getTerm()+"", wcf1)); 
			        		   ws.addCell(new Label(2, j, ""+pojo.getCSUnit(), wcf1)); 
			        		   ws.addCell(new Label(3, j, ""+pojo.getUnitID(), wcf1)); 
			        		   ws.addCell(new Label(4, j, ""+pojo.getCSMajorName(), wcf1)); 
			        		   ws.addCell(new Label(5, j, ""+pojo.getCSMajorID(), wcf1)); 
			        		   ws.addCell(new Label(6, j, ""+pojo.getCSName(), wcf1)); 
			        		   ws.addCell(new Label(7, j, ""+pojo.getCSID(), wcf1)); 
			        		   ws.addCell(new Label(8, j, ""+pojo.getCSType(), wcf1)); 
			        		   ws.addCell(new Label(9, j, ""+pojo.getCSNature(), wcf1)); 
			        		   ws.addCell(new Label(10, j, ""+pojo.getPubCSType(), wcf1)); 
			        		   if(pojo.isIsDoubleCS()==false){
			        			   isDoubleCs ="否";
			        		   }else{
			        			   isDoubleCs ="是";
			        		   }
			        		   ws.addCell(new Label(11, j, ""+isDoubleCs, wcf1)); 
			        		   ws.addCell(new Label(12, j, ""+pojo.getCredit(), wcf1));

//								maplist.put("IsAccordJob", 25);maplist.put("TeaTitle", 26);maplist.put("BookUseInfo", 27);maplist.put("IsPlanbook", 28);
//								maplist.put("IsAwardbook", 29);
			        		   ws.addCell(new Label(13, j, pojo.getSumCSHour()+"", wcf1)); 
			        		   ws.addCell(new Label(14, j, ""+pojo.getTheoryCSHour(), wcf1)); 
			        		   ws.addCell(new Label(15, j, ""+pojo.getPraCSHour(), wcf1)); 
			        		   ws.addCell(new Label(16, j, ""+pojo.getExamWay(), wcf1)); 
			        		   ws.addCell(new Label(17, j, ""+pojo.getPlanTime(), wcf1)); 
			        		   ws.addCell(new Label(18, j, ""+pojo.getCSGrade(), wcf1)); 
			        		   ws.addCell(new Label(19, j, ""+pojo.getCSClass(), wcf1)); 
			        		   ws.addCell(new Label(20, j, ""+pojo.getCSID(), wcf1)); 
			        		   ws.addCell(new Label(21, j, ""+pojo.getClassInfo(), wcf1)); 
			        		   ws.addCell(new Label(22, j, ""+pojo.getStuNum(), wcf1)); 
			        		   ws.addCell(new Label(23, j, ""+pojo.getCSTea(), wcf1)); 
			        		   ws.addCell(new Label(24, j, ""+pojo.getTeaID(), wcf1)); 
			        		   if(pojo.isIsAccordJob()==false){
			        			   isAccordJob ="否";
			        		   }else{
			        			   isAccordJob ="是";
			        		   }
			        		   ws.addCell(new Label(25, j, ""+isAccordJob, wcf1)); 
			        		   ws.addCell(new Label(26, j, ""+pojo.getTeaTitle(), wcf1)); 
			        		   ws.addCell(new Label(27, j, ""+pojo.getBookUseInfo(), wcf1));
			        		   if(pojo.isIsPlanbook()==false){
			        			   isPlanbook ="否";
			        		   }else{
			        			   isPlanbook ="是";
			        		   }
			        		   ws.addCell(new Label(28, j, ""+isPlanbook, wcf1));
			        		   if(pojo.isIsAwardbook()==false){
			        			   isAwardbook ="否";
			        		   }else{
			        			   isAwardbook ="是";
			        		   }
			        		   ws.addCell(new Label(29, j, ""+isAwardbook, wcf1)); 
			         
			        		   j++;seq++;
			        	   }
			        	  
			           }else{
		        	   System.out.println("后台传入的数据为空");
		           }
		          wwb.write();
		          wwb.close();

		        } catch (IOException e){
		        } catch (RowsExceededException e){
		        } catch (WriteException e){}

		return new ByteArrayInputStream(fos.toByteArray());
	}
	
	
//	public InputStream getInputStream(){
//		
//		//System.out.println("export");
//
//		InputStream inputStream = null ;
//		
//		try {
////			//具体教学单位
////		    UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
////			String fillUnitID = bean.getUnitID();
//			List<T512POJO> list = t512_Sr.totalList(this.getSelectYear(),fillUnitID,Constants.PASS_CHECK);
//			String sheetName = this.excelName;
//			
//			List<String> columns = new ArrayList<String>();
//			columns.add("序号");
//			columns.add("学期");columns.add("开课单位");columns.add("单位号");columns.add("上课专业名称");columns.add("上课专业代码");
//			columns.add("课程名称");columns.add("课程编号");columns.add("课程类别");columns.add("课程性质");columns.add("公选课类别");
//			columns.add("是否双语授课");columns.add("学分");columns.add("总学时");columns.add("理论学时");columns.add("实践学时");
//			columns.add("考核方式");columns.add("实习、设计时间");columns.add("授课年级");columns.add("授课班级");columns.add("开课班号");
//			columns.add("合班情况");columns.add("学生人数");columns.add("授课教师");columns.add("授课教工号");
//             columns.add("是否符合岗位资格");columns.add("教师职称");
//			columns.add("使用情况");columns.add("是否规划教材");columns.add("是否获奖教材");
//			
//			Map<String,Integer> maplist = new HashMap<String,Integer>();
//			maplist.put("SeqNum", 0);
//			maplist.put("Term", 1);maplist.put("CSUnit", 2);maplist.put("UnitID", 3);maplist.put("CSMajorName", 4);maplist.put("CSMajorID", 5);
//			maplist.put("CSName", 6);maplist.put("CSID", 7);maplist.put("CSType", 8);maplist.put("CSNature", 9);maplist.put("PubCSType", 10);
//			maplist.put("IsDoubleCS", 11);maplist.put("Credit", 12);maplist.put("SumCSHour", 13);maplist.put("TheoryCSHour", 14);
//			maplist.put("PraCSHour", 15);maplist.put("ExamWay", 16);maplist.put("PlanTime", 17);maplist.put("CSGrade", 18);
//			maplist.put("CSClass", 19);maplist.put("ClassID", 20);maplist.put("ClassInfo",21);maplist.put("StuNum", 22);
//			maplist.put("CSTea", 23);	maplist.put("TeaID", 24);
//			maplist.put("IsAccordJob", 25);maplist.put("TeaTitle", 26);maplist.put("BookUseInfo", 27);maplist.put("IsPlanbook", 28);
//			maplist.put("IsAwardbook", 29);
//			
//			inputStream = new ByteArrayInputStream(ExcelUtil.exportExcel(list, sheetName, maplist,columns).toByteArray());
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null ;
//		}
//
//		return inputStream ;
//	}
	
	
	public String execute() throws Exception{
		request.setCharacterEncoding("UTF-8") ;
		System.out.println("excelName=============" + this.excelName) ;
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

	public T512_Bean getT512_Bean() {
		return t512_Bean;
	}

	public void setT512_Bean(T512_Bean t512_Bean) {
		this.t512_Bean = t512_Bean;
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

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
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

	public String getSelectYear() {
		return selectYear;
	}

	public void setSelectYear(String selectYear) {
		this.selectYear = selectYear;
	}
	

}
