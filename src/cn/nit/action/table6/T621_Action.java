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
import cn.nit.bean.table4.T411_Bean;
import cn.nit.bean.table6.T617_Bean;
import cn.nit.bean.table6.T621_Bean;
import cn.nit.bean.table6.T631_Bean;
import cn.nit.dao.table6.T621_Dao;
import cn.nit.dao.table6.T631_Dao;
import cn.nit.dbconnection.DBConnection;
import cn.nit.service.CheckService;
import cn.nit.service.table6.T621_Service;
import cn.nit.util.DAOUtil;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;

/**
 * 待完成！！！！！！！
 * 
 * @author Yuan
 */
public class T621_Action {

	/** T621的Service*/
	private T621_Service UndergraAdmiInfoSer = new T621_Service();

	/** T621的Bean实体*/
	T621_Bean UndergraAdmiInfo = new T621_Bean();
	
//	private T621_Dao T621_dao = new T621_Dao();
	
	private CheckService check_services = new CheckService();
	
	
	/**  哪一年数据  */
	private String selectYear;

	/**  导出的excelName名 */
	private String excelName ;
	
	/**  待审核数据的要删除的序列集  */
	private String ids; //删除的id
	
	/**  审核状态显示判别标志  */
	private int checkNum ;

//	/** 待审核数据的查询的序列号 */
//	private Integer seqNum;
//
//	/** 待审核数据查询的起始时间 */
//	private Date startTime;
//
//	/** 待审核数据查询的结束时间 */
//	private Date endTime;
//	
//	//待查询的专业名称
//	private String searchItem;
//	
//
//	/** 当前查询的是第几*/
//	private String page;
//
//	/** 每页显示的条*/
//	private String rows;
//	
//	/**所属教学单*/
//	private String fromTeaUnit;
//	
//	/**专业名称*/
//	private String majorName;
	
	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;

//	public T621_Dao getT621_dao() {
//		return T621_dao;
//	}
//
//	public void setT621_dao(T621_Dao t621Dao) {
//		T621_dao = t621Dao;
//	}

	public CheckService getCheck_services() {
		return check_services;
	}

	public void setCheck_services(CheckService checkServices) {
		check_services = checkServices;
	}

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

	/** 逐条插入数据 */
	public void loadInfo() {
		
		System.out.println("haha");
	
		List<T621_Bean> list=UndergraAdmiInfoSer.getYearInfo(this.getSelectYear());
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
		boolean flag = UndergraAdmiInfoSer.insert(UndergraAdmiInfo, year);
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
	
	/**  编辑数据  */
	public void edit(){
		int flag = UndergraAdmiInfoSer.update(UndergraAdmiInfo,this.getSelectYear()) ;
		if(flag == 2){
			int year = Integer.parseInt(this.getSelectYear());
			check_services.delete("T621", year);
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
		boolean flag = UndergraAdmiInfoSer.deleteByIds(ids, this.getSelectYear()) ;
		//删除审核不通过信息
		int year = Integer.parseInt(this.getSelectYear());
		check_services.delete("T621", year);
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
		String FromTeaUnit = "全校合计";
		boolean flag = UndergraAdmiInfoSer.updateCheck(this.getSelectYear(), FromTeaUnit, this.getCheckNum());
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
	
	
//
//	/** 为界面加载数�?
//	 * 
//	 * 2014-6-20 修改
//	 * */
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
//			List<T621_Bean> list = UndergraAdmiInfoSer.getPageInfoList(cond, null, this.getRows(), this.getPage()) ;
//			String TeaInfoJson = this.toBeJson(list,UndergraAdmiInfoSer.getTotal(cond, null));
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
//	private String toBeJson(List<T621_Bean> list, int total) throws Exception {
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
//		boolean flag = UndergraAdmiInfoSer.update(UndergraAdmiInfo);
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
//
//	/** 根据数据的id删除数据 */
//	public void deleteByIds() {
//		System.out.println("ids=" +this.getIds());
//		boolean flag = UndergraAdmiInfoSer.deleteItemsByIds(ids);
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
		
		try {
/*			response.reset();
			response.addHeader("Content-Disposition", "attachment;fileName="
                      + java.net.URLEncoder.encode(excelName,"UTF-8"));*/
			
			List<T621_Bean> list = UndergraAdmiInfoSer.getYearInfo(this.selectYear);
		
			if(list==null){
				if(list.size()==0){
					PrintWriter out = null ;
					response.setContentType("text/html;charset=utf-8") ;
					out = response.getWriter() ;
					out.print("后台传入的数据为空") ;
					System.out.println("后台传入的数据为空");
					return null;
				}
			}
			if(list!=null){
				
				
				String sheetName = this.excelName;
				
				List<String> columns = new ArrayList<String>();
				
				columns.add("序号");columns.add("所属教学单");columns.add("单位");columns.add("专业名称");
				columns.add("专业代码");columns.add("招生计划");columns.add("实际录取");columns.add("实际报到");
				columns.add("自主招生");columns.add("招收特长生数");columns.add("招收本省学生");columns.add("新办专业招生");
				columns.add("招生录取平均分（分）");
				

				Map<String,Integer> maplist = new HashMap<String,Integer>();
				
				maplist.put("seqNumber", 0);maplist.put("fromTeaUnit", 1);maplist.put("unitId", 2);
				maplist.put("majorName", 3);maplist.put("majorId", 4);maplist.put("amisPlanNum", 5);
				maplist.put("actulEnrollNum", 6);maplist.put("actulRegisterNum", 7);maplist.put("autoEnrollNum", 8);
				maplist.put("specialtyEnrollNum", 9);maplist.put("inProviEnrollNum", 10);maplist.put("newMajEnrollNum", 11);
				maplist.put("AvgScore", 12);
				
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
						ws.addCell(new Label(0, 0,sheetName, wcf)); 
						ws.mergeCells(0, 0, 1, 0);
						
						//写表头
						if(columns != null && columns.size() > 0){
							for(int i =0;i<columns.size();i++){
								ws.addCell(new Label(i, 2, columns.get(i), wcf));
							}
						}
						
						//向表中写数据
						int k=3;//从第4行开始写数据,第3行为全校合计数
						for(int j=0;j<list.size();j++){
							T621_Bean bean1 =  list.get(j);
							if(j==0){
								ws.addCell(new Label(0,3, bean1.getFromTeaUnit(), wcf1));
								ws.mergeCells(0, 3, 4, 3);
								ws.addCell(new Label(5, 3, bean1.getAmisPlanNum()+"", wcf1));
								ws.addCell(new Label(6, 3, bean1.getActulEnrollNum()+"", wcf1));
								ws.addCell(new Label(7, 3, bean1.getActulRegisterNum()+"", wcf1));
								ws.addCell(new Label(8, 3, bean1.getAutoEnrollNum()+"", wcf1));
								ws.addCell(new Label(9, 3, bean1.getSpecialtyEnrollNum()+"", wcf1));
								ws.addCell(new Label(10, 3, bean1.getInProviEnrollNum()+"", wcf1));
								ws.addCell(new Label(11, 3, bean1.getNewMajEnrollNum()+"", wcf1));
								ws.addCell(new Label(12, 3, bean1.getAvgScore()+"", wcf1));
//								ws.addCell(new Label(12, 3, bean1.getTime()+"", wcf));
//								ws.addCell(new Label(13, 3, bean1.getNote()+"", wcf));
							}else{
								ws.addCell(new Label(0, k,j+"", wcf1));
								ws.addCell(new Label(1, k, bean1.getFromTeaUnit(), wcf1));
								ws.addCell(new Label(2, k, bean1.getUnitId(), wcf1));
								ws.addCell(new Label(3, k, bean1.getMajorName(), wcf1));
								ws.addCell(new Label(4, k, bean1.getMajorId(), wcf1));
								ws.addCell(new Label(5, k, bean1.getAmisPlanNum()+"", wcf1));
								ws.addCell(new Label(6, k, bean1.getActulEnrollNum()+"", wcf1));
								ws.addCell(new Label(7, k, bean1.getActulRegisterNum()+"", wcf1));
								ws.addCell(new Label(8, k, bean1.getAutoEnrollNum()+"", wcf1));
								ws.addCell(new Label(9, k, bean1.getSpecialtyEnrollNum()+"", wcf1));
								ws.addCell(new Label(10, k, bean1.getInProviEnrollNum()+"", wcf1));
								ws.addCell(new Label(11, k, bean1.getNewMajEnrollNum()+"", wcf1));
								ws.addCell(new Label(12, k, bean1.getAvgScore()+"", wcf1));
//								ws.addCell(new Label(12, k, bean1.getTime()+"", wcf));
//								ws.addCell(new Label(13, k, bean1.getNote()+"", wcf));
							}
							k++;
						}
						    wwb.write();
				            wwb.close();

				} catch (IOException e){
		        } catch (RowsExceededException e){
		        } catch (WriteException e){}
				
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}

		inputStream = new ByteArrayInputStream(fos.toByteArray());
		return inputStream ;
	}

	public String execute() throws Exception {

		getResponse().setContentType("application/vnd.ms-excel;charset=UTF-8");
		System.out.println("excelName=============" + this.excelName) ;
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

	public T621_Service getUndergraAdmiInfoSer() {
		return UndergraAdmiInfoSer;
	}

	public void setUndergraAdmiInfoSer(T621_Service undergraAdmiInfoSer) {
		UndergraAdmiInfoSer = undergraAdmiInfoSer;
	}

	public T621_Bean getUndergraAdmiInfo() {
		return UndergraAdmiInfo;
	}

	public void setUndergraAdmiInfo(T621_Bean undergraAdmiInfo) {
		UndergraAdmiInfo = undergraAdmiInfo;
	}



	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
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
