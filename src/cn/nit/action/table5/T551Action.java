package cn.nit.action.table5;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.table5.T551Bean;
import cn.nit.dao.table5.T551DAO;
import cn.nit.excel.imports.table5.T551Excel;
import cn.nit.service.table5.T551Service;
import cn.nit.util.DateUtil;
import cn.nit.util.TimeUtil;

public class T551Action {

	/**  表T551的数据库操作类  */
	private T551DAO t551Dao = new T551DAO() ;
	
	private T551Excel t551Excel=new T551Excel();

	/**  表551的Service类  */
	private T551Service t551Ser = new T551Service() ;
	
	/**  表551的Bean实体类  */
	private T551Bean t551Bean = new T551Bean() ;
	
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
	
	/**  逐条插入数据  */
	public void insert(){
//		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		t551Bean.setTime(new Date()) ;

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
			
			if(this.getSeqNum() == null && this.getStartTime() == null && this.getEndTime() == null){			
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
				cond = conditions.toString();
			}


			String pages = t551Ser.auditingData(cond, null, Integer.parseInt(page), Integer.parseInt(rows)) ;

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

		 
		t551Bean.setTime(new Date()) ;
		boolean flag = t551Ser.update(t551Bean) ;
		PrintWriter out = null ;
		
		try{
			out = getResponse().getWriter() ;
			if(flag){
				out.print("{\"state\":true,data:\"修改成功!!!\"}") ;
			}else{
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
	
	/**  根据数据的id删除数据  */
	public void deleteCoursesByIds(){
//		System.out.println("ids=" + ids) ;
		boolean flag = t551Ser.deleteCoursesByIds(ids) ;
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
		

		List<T551Bean> list = t551Dao.totalList();
		ByteArrayOutputStream fos = null;
		 
		if(list.size()<1){
			PrintWriter out = null ;
			getResponse().setContentType("text/html; charset=UTF-8") ;
			out = getResponse().getWriter() ;
			out.print("后台传入的数据为空!!!") ;
			System.out.println("后台传入的数据为空");
		}else{
			String sheetName=this.excelName;	
		    WritableWorkbook wwb;
		    //统计合计
		    T551Bean beanAll = new T551Bean();
		    beanAll.setTeaUnit("全校合计");
		    int PartyMemNum=0; int CheatNum=0;double GoodClassRatio=0.0;
		    for(int i=0;i<list.size();i++){
		    	T551Bean bean = list.get(i);
		    	PartyMemNum+=bean.getPartyMemNum();
		    	CheatNum+=bean.getCheatNum();
//		    	GoodClassRatio+=bean.getGoodClassRatio();
		    }
//		    GoodClassRatio = GoodClassRatio/list.size();
		    
		    beanAll.setPartyMemNum(PartyMemNum);
		    beanAll.setCheatNum(CheatNum);
		    beanAll.setGoodClassRatio(GoodClassRatio);
//		    beanAll.setGoodClassRatio(DateUtil.doubleTwo(""+GoodClassRatio));
		    
		    list.add(0, beanAll);//将合计加入到list的第一个位置
		    
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
		           
		           int j = 4;//第3行写合计，4行开始写数据
		           for(int i =0;i<list.size();i++){
		        	   T551Bean bean = list.get(i);
		        	   if(i==0){
		        		   ws.addCell(new Label(0,3,bean.getTeaUnit(),wcf));
		        		   ws.mergeCells(0, 3, 5, 3);
		        		   ws.addCell(new Label(6,3,""+bean.getPartyMemNum(),wcf1));
		        		   ws.addCell(new Label(7,3,""+bean.getCheatNum(),wcf1));
		        		   ws.addCell(new Label(8,3,""+bean.getGoodClassRatio()+"%",wcf1));
		        	   }else{
		        		   ws.addCell(new Label(0,j,""+(j-3),wcf1));
		        		   ws.addCell(new Label(1,j,bean.getTeaUnit(),wcf1));
		        		   ws.addCell(new Label(2,j,bean.getUnitID(),wcf1));
		        		   ws.addCell(new Label(3,j,bean.getMajorName(),wcf1));
		        		   ws.addCell(new Label(4,j,bean.getMajorID(),wcf1));
		        		   ws.addCell(new Label(5,j,bean.getAdmisSchYear(),wcf1));
		        		   ws.addCell(new Label(6,j,""+bean.getPartyMemNum(),wcf1));
		        		   ws.addCell(new Label(7,j,""+bean.getCheatNum(),wcf1));
		        		   ws.addCell(new Label(8,j,""+bean.getGoodClassRatio()+"%",wcf1));
		        		   j++;
		        	   }
		           }
		           
		           
		          wwb.write();
		          wwb.close();

		        } catch (IOException e){
		        } catch (RowsExceededException e){
		        } catch (WriteException e){}
		}
//
//		try {
//			
//			List<T551Bean> list = t551Dao.totalList();
//			
//			String sheetName = this.excelName;
//			
//			List<String> columns = new ArrayList<String>();
//			columns.add("序号");
//			columns.add("教学单位");columns.add("单位号");columns.add("专业名称");columns.add("专业代码");
//			columns.add("入校年份");columns.add("本科生党员数（个）");columns.add("考试违纪、作弊及受处分（人次）");
//			columns.add("优良学风班的比例（%）");columns.add("备注");
//
//			
//			Map<String,Integer> maplist = new HashMap<String,Integer>();
//			maplist.put("SeqNum", 0);
//			maplist.put("TeaUnit", 1);maplist.put("UnitID", 2);maplist.put("MajorName", 3);maplist.put("MajorID", 4);
//			maplist.put("AdmisSchYear", 5);maplist.put("PartyMemNum", 6);maplist.put("CheatNum", 7);maplist.put("GoodClassRatio", 8);
//			maplist.put("Note", 9);
//			
//			//inputStream = new ByteArrayInputStream(ExcelUtil.exportExcel(list, sheetName, maplist,columns).toByteArray());
//			inputStream = new ByteArrayInputStream(t551Excel.batchExport(list, sheetName, maplist, columns).toByteArray());
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null ;
//		}
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

	public T551Bean getT551Bean() {
		return t551Bean;
	}

	public void setT551Bean(T551Bean t551Bean) {
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
