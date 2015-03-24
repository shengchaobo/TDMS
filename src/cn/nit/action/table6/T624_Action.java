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
import cn.nit.bean.table6.T624_Bean;
import cn.nit.dao.table6.T624_Dao;
import cn.nit.dbconnection.DBConnection;
import cn.nit.service.CheckService;
import cn.nit.service.table6.T624_Service;
import cn.nit.util.DAOUtil;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;

/**
 * 待完成！！！！！！！
 * 
 * @author Yuan
 */
public class T624_Action {

	/** �?24的Service�?*/
	private T624_Service T624_service = new T624_Service();
	
	private CheckService check_services = new CheckService();


	/** �?24的Bean实体�?*/
	T624_Bean T624_bean = new T624_Bean();

	
//	private T624_Dao T624_dao = new T624_Dao();
	
	
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

//	public T624_Dao getT624_dao() {
//		return T624_dao;
//	}
//
//	public void setT624_dao(T624_Dao t624Dao) {
//		T624_dao = t624Dao;
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


	
	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;

	

	public void loadInfo() {
		
		System.out.println("haha");
	
		List<T624_Bean> list=T624_service.getYearInfo(this.getSelectYear());
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
		boolean flag = T624_service.insert(T624_bean, year);
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
		int flag = T624_service.update(T624_bean,this.getSelectYear()) ;
		if(flag == 2){
			int year = Integer.parseInt(this.getSelectYear());
			check_services.delete("T624", year);
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
		boolean flag = T624_service.deleteByIds(ids, this.getSelectYear()) ;
		//删除审核不通过信息
		int year = Integer.parseInt(this.getSelectYear());
		check_services.delete("T624", year);
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
		boolean flag = T624_service.updateCheck(this.getSelectYear(), TeaUnit, this.getCheckNum());
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
//			List<T624_Bean> list = T624_service.getPageInfoList(cond, null, this.getRows(), this.getPage()) ;
//			String TeaInfoJson = this.toBeJson(list,T624_service.getTotal(cond, null));
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
//	private String toBeJson(List<T624_Bean> list, int total) throws Exception {
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
//		boolean flag = T624_service.update(T624_bean);
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
//		boolean flag = T624_service.deleteItemsByIds(ids);
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
			
			List<T624_Bean> list = T624_service.getAllList("", null);
			
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
				int PlanAdmisNum=0; int ActualAdmisNum=0; int ActualRegisterNum=0; int GenHignSchNum=0;
				int SecondVocationNum = 0; int OtherNum = 0; 
				//统计全校合计
				for(T624_Bean bean : list){
					PlanAdmisNum+=bean.getPlanAdmisNum();
					ActualAdmisNum+=bean.getActualAdmisNum();
					ActualRegisterNum+=bean.getActualRegisterNum();
					GenHignSchNum+=bean.getGenHignSchNum();
					SecondVocationNum+=bean.getSecondVocationNum();
					OtherNum+=bean.getOtherNum();
				}
				
				T624_Bean bean = new T624_Bean();
				bean.setPlanAdmisNum(PlanAdmisNum);
				bean.setActualAdmisNum(ActualAdmisNum);
				bean.setActualRegisterNum(ActualRegisterNum);
				bean.setGenHignSchNum(GenHignSchNum);
				bean.setSecondVocationNum(SecondVocationNum);
				bean.setOtherNum(OtherNum);
				bean.setTeaUnit("全校合计：");
				list.add(0, bean);
				
				String sheetName = this.excelName;
				
				List<String> columns = new ArrayList<String>();
				
				columns.add("序号");columns.add("所属教学单位");columns.add("单位号");
				columns.add("专业名称");columns.add("专业代码");columns.add("专业方向名称");
				columns.add("当年是否招生（含方向）");columns.add("当年计划招生数（人）");columns.add("实际录取数（人）");
				columns.add("实际报到数（人）");columns.add("普通高中起点（人）");columns.add("中职起点（人）");
				columns.add("其他（人）");
//				columns.add("时间");columns.add("备注");
				

				Map<String,Integer> maplist = new HashMap<String,Integer>();
				
				maplist.put("seqNumber", 0);maplist.put("teaUnit", 1);maplist.put("unitId", 2);
				maplist.put("majorName", 3);maplist.put("majorId", 4);maplist.put("majorFieldName", 5);
				maplist.put("isCurrentYearAdmis", 6);maplist.put("planAdmisNum", 7);maplist.put("actualAdmisNum", 8);
				maplist.put("actualRegisterNum", 9);maplist.put("genHignSchNum", 10);maplist.put("secondVocationNum", 11);
				maplist.put("otherNum", 12);
//				maplist.put("time", 13);maplist.put("note", 14);
				
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
						
						//写表头
						if(columns != null && columns.size() > 0){
							for(int i =0;i<columns.size();i++){
								ws.addCell(new Label(i, 2, columns.get(i), wcf));
							}
						}
						
						//向表中写数据
						int k=3;//从第4行开始写数据,第3行为全校合计数
						for(int j=0;j<list.size();j++){
							T624_Bean bean1 =  list.get(j);
							if(j==0){
								ws.addCell(new Label(0,3, bean1.getTeaUnit(), wcf1));
								ws.mergeCells(0, 3, 6, 3);
								ws.addCell(new Label(7, 3, bean1.getPlanAdmisNum()+"", wcf1));
								ws.addCell(new Label(8, 3, bean1.getActualAdmisNum()+"", wcf1));
								ws.addCell(new Label(9, 3, bean1.getActualRegisterNum()+"", wcf1));
								ws.addCell(new Label(10, 3, bean1.getGenHignSchNum()+"", wcf1));
								ws.addCell(new Label(11, 3, bean1.getSecondVocationNum()+"", wcf1));
								ws.addCell(new Label(12, 3, bean1.getOtherNum()+"", wcf1));
//								ws.addCell(new Label(13, 3, bean1.getNote()+"", wcf));
							}else{
								ws.addCell(new Label(0, k,j+"", wcf1));
								ws.addCell(new Label(1, k, bean1.getTeaUnit(), wcf1));
								ws.addCell(new Label(2, k, bean1.getUnitId(), wcf1));
								ws.addCell(new Label(3, k, bean1.getMajorName(), wcf1));
								ws.addCell(new Label(4, k, bean1.getMajorId(), wcf1));
								ws.addCell(new Label(5, k, bean1.getMajorFieldName(), wcf1));
								ws.addCell(new Label(6, k, this.BooleanToString(bean1.getIsCurrentYearAdmis()), wcf1));
								ws.addCell(new Label(7, k, bean1.getPlanAdmisNum()+"", wcf1));
								ws.addCell(new Label(8, k, bean1.getActualAdmisNum()+"", wcf1));
								ws.addCell(new Label(9, k, bean1.getActualRegisterNum()+"", wcf1));
								ws.addCell(new Label(10, k, bean1.getGenHignSchNum()+"", wcf1));
								ws.addCell(new Label(11, k, bean1.getSecondVocationNum()+"", wcf1));
								ws.addCell(new Label(12, k, bean1.getOtherNum()+"", wcf1));
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
	
	/**转换boolean 为是否*/
	public String BooleanToString(boolean flag){
		String str;
		if(flag){
			str = "是";
		}else{
			str = "否";
		}
		return str;
	}



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




	public T624_Service getT624_service() {
		return T624_service;
	}

	public void setT624_service(T624_Service t624Service) {
		T624_service = t624Service;
	}

	public T624_Bean getT624_bean() {
		return T624_bean;
	}

	public void setT624_bean(T624_Bean t624Bean) {
		T624_bean = t624Bean;
	}



	public static void main(String args[]) {
		String match = "[\\d]+";
		System.out.println("23gfhf4".matches(match));
	}


}
