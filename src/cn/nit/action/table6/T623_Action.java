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
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import cn.nit.bean.table6.T611_Bean;
import cn.nit.bean.table6.T612_Bean;
import cn.nit.bean.table6.T613_Bean;
import cn.nit.bean.table6.T614_Bean;
import cn.nit.bean.table6.T615_Bean;
import cn.nit.bean.table6.T617_Bean;
import cn.nit.bean.table6.T621_Bean;
import cn.nit.bean.table6.T622_Bean;
import cn.nit.bean.table6.T623_Bean;
import cn.nit.bean.table6.T631_Bean;
import cn.nit.bean.table6.T632_Bean;
import cn.nit.constants.Constants;
import cn.nit.dao.table6.T611_Dao;
import cn.nit.dao.table6.T612_Dao;
import cn.nit.dao.table6.T613_Dao;
import cn.nit.dao.table6.T614_Dao;
import cn.nit.dao.table6.T615_Dao;
import cn.nit.dao.table6.T617_Dao;
import cn.nit.dao.table6.T622_Dao;
import cn.nit.dao.table6.T623_Dao;
import cn.nit.dao.table6.T632_Dao;
import cn.nit.dbconnection.DBConnection;
import cn.nit.service.CheckService;
import cn.nit.service.table6.T611_Service;
import cn.nit.service.table6.T612_Service;
import cn.nit.service.table6.T613_Service;
import cn.nit.service.table6.T614_Service;
import cn.nit.service.table6.T615_Service;
import cn.nit.service.table6.T617_Service;
import cn.nit.service.table6.T622_Service;
import cn.nit.service.table6.T623_Service;
import cn.nit.service.table6.T632_Service;
import cn.nit.util.DAOUtil;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;

/**
 * 待完成！！！！！！！
 * 
 * @author Yuan
 */
public class T623_Action {

	/** 表的Service类 */
	private T623_Service T623_service = new T623_Service();

	/** 表的Bean实体类 */
	T623_Bean T623_bean = new T623_Bean();
	
//	T623_Dao T623_dao = new T623_Dao();
	
	private CheckService check_services = new CheckService();

	/** 待审核数据的查询的序列号 */
	private Integer seqNum;

	/** 待审核数据查询的起始时间 */
	private Date startTime;

	/** 待审核数据查询的结束时间 */
	private Date endTime;
	
	private String excelName; //excel导出名字
	
	//待查询的专业名称
	private String searchItem;

	/** 数据的SeqNumber编号 */
	private String ids;

	/** 当前查询的是第几页 */
	private String page;

	/** 每页显示的条数 */
	private String rows;
	
	/**所属教学单位*/
	private String fromTeaUnit;
	
	/**专业名称*/
	private String majorName;
	
	/**  审核状态显示判别标志  */
	private int checkNum ;
	
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

	
	public int getCheckNum() {
		return checkNum;
	}

	public void setCheckNum(int checkNum) {
		this.checkNum = checkNum;
	}

	public String getSelectYear() {
		return selectYear;
	}

	public void setSelectYear(String selectYear) {
		this.selectYear = selectYear;
	}

	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;

	/** 逐条插入数据 */
	public void insert() {
		System.out
				.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		T623_bean.setCheckState(Constants.WAIT_CHECK);
		boolean flag = T623_service.insert(T623_bean);
		PrintWriter out = null;

		try {
			getResponse().setContentType("text/html; charset=UTF-8");
			// getResponse().setHeader("Content-type", "text/html");
			out = getResponse().getWriter();
			if (flag) {
				out.print("{\"state\":true,data:\"录入成功!!!\"}");
			} else {
				out.print("{\"state\":false,data:\"录入失败!!!\"}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.print("{\"state\":false,data:\"录入失败!!!\"}");
		} finally {
			if (out != null) {
				out.close();
			}
		}
		out.flush();
	}

	/** 为界面加载数据 */
	public void loadData() throws Exception {
		  HttpServletResponse response = ServletActionContext.getResponse() ;	
			
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
			
			List<T623_Bean> list = T623_service.getPageInfoList(cond, null, this.getRows(), this.getPage()) ;
			String TeaInfoJson = this.toBeJson(list,T623_service.getTotal(cond, null));
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

	// 将分页系统的总数以及当前页的list转化一个json传页面显示
	private String toBeJson(List<T623_Bean> list, int total) throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();

		JSONObject testjson = new JSONObject();
		testjson.accumulate("total", total);
		testjson.accumulate("rows", list);

		String json = testjson.toString();
		System.out.println(json);
		return json;
	}


	/** 编辑数据 */
	public void edit() {
		
		boolean flag = false;
		int tag = 0;
		//获得该条数据审核状态
		int state = T623_service.getCheckState(T623_bean.getSeqNumber());
		
		//如果审核状态是待审核，则直接修改
		if(state == Constants.WAIT_CHECK){
			T623_bean.setCheckState(Constants.WAIT_CHECK);
			flag = T623_service.update(T623_bean) ;
			if(flag) tag = 1;
		}
		//如果是审核不通过，则修改该条数据，并将审核状态调节为待审核，同时删除该条数据在checkInfo表的信息
		if(state == Constants.NOPASS_CHECK){
			T623_bean.setCheckState(Constants.WAIT_CHECK);
			boolean flag1 = T623_service.update(T623_bean) ;
			boolean flag2 = check_services.delete("T623",T623_bean.getSeqNumber());
			if(flag1&&flag2){
				flag = true;
				tag = 2;
			}
		}
		PrintWriter out = null;

		try {
			out = getResponse().getWriter();
			if(tag == 1){
				out.print("{\"state\":true,data:\"修改成功!!!\"}") ;
			}
			else if(tag == 2){
				out.print("{\"state\":true,data:\"修改成功!!!\",tag:2}") ;
			}
			else{
				out.print("{\"state\":true,data:\"修改失败!!!\"}") ;
			}
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			out.print("{\"state\":false,data:\"系统错误，请联系管理员!!!\"}");
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**  修改某条数据的审核状态  */
	public void updateCheck(){
		HttpServletResponse response = ServletActionContext.getResponse();
	
		boolean flag = T623_service.updateCheck(this.getSeqNum(),this.getCheckNum());
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
	
		boolean flag = T623_service.checkAll();
		
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
	
	/** 根据数据的id删除数据 */
	public void deleteByIds() {
		System.out.println("ids=" +this.getIds());
		boolean flag = T623_service.deleteItemsByIds(ids);
		
		//删除审核不通过信息
		check_services.delete("T623", ids);
		PrintWriter out = null;

		try {
			out = getResponse().getWriter();

			if (flag) {
				out.print("{\"state\":true,data:\"数据删除成功!!!\"}");
			} else {
				out.print("{\"state\":false,data:\"数据删除失败!!!\"}");
			}

			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			out.print("{\"state\":false,data:\"系统错误，请联系管理员!!!\"}");
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	public InputStream getInputStream() {

		InputStream inputStream = null ;
		ByteArrayOutputStream fos = new ByteArrayOutputStream();
		
		try {
/*			response.reset();
			response.addHeader("Content-Disposition", "attachment;fileName="
                      + java.net.URLEncoder.encode(excelName,"UTF-8"));*/
			
			List<T623_Bean> list = T623_service.totalList(this.getSelectYear(),Constants.PASS_CHECK);
			String sheetName = this.excelName;
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
			else if(list!=null)
			{WritableWorkbook wwb;
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
					//表头
					ws.addCell(new Label(0, 2, "序号", wcf)); ws.addCell(new Label(1, 2, "省份", wcf));
					ws.addCell(new Label(2, 2, "类型", wcf));
					ws.addCell(new Label(3, 2, "批次", wcf)); ws.addCell(new Label(13, 2, "说明", wcf));
					
					ws.mergeCells(0, 2, 0, 3);ws.mergeCells(1, 2, 1, 3);ws.mergeCells(2, 2, 2, 3);
					ws.mergeCells(3, 2, 3, 3);ws.mergeCells(13, 2, 13, 3);
					
					ws.addCell(new Label(4, 2, "1.录取数（个）", wcf));
					ws.addCell(new Label(7, 2, "2.批次最低控制线（分）", wcf));
					ws.addCell(new Label(10, 2, "3.当年录取平均分数（分）", wcf));
					
					ws.mergeCells(4, 2, 6, 2);ws.mergeCells(7, 2, 9, 2);ws.mergeCells(10, 2,12, 2);
					
					ws.addCell(new Label(4, 3, "文科", wcf));ws.addCell(new Label(5, 3, "理科", wcf));ws.addCell(new Label(6, 3, "综合", wcf));
					ws.addCell(new Label(7, 3, "文科", wcf));ws.addCell(new Label(8, 3, "理科", wcf));ws.addCell(new Label(9, 3, "综合", wcf));
					ws.addCell(new Label(10, 3, "文科", wcf));ws.addCell(new Label(11, 3, "理科", wcf));ws.addCell(new Label(12, 3, "综合", wcf));
					
					//向表中写数据
					int k=4;//从第4行开始写数据,第3行为全校合计数
					int count = 1;//序号计数
					for(int j=0;j<list.size();j++){
						T623_Bean bean =  list.get(j);
					
							ws.addCell(new Label(0, k,count+"", wcf1));
							ws.addCell(new Label(1, k, bean.getProvince(), wcf1));
							ws.addCell(new Label(2, k, bean.getArtType(), wcf1));
							ws.addCell(new Label(3, k, bean.getBatch(), wcf1));
							ws.addCell(new Label(4, k, bean.getLibEnrollNum()+"", wcf1));
							ws.addCell(new Label(5, k, bean.getSciEnrollNum()+"", wcf1));
							ws.addCell(new Label(6, k, bean.getSumEnrollNum()+"", wcf1));
							
							ws.addCell(new Label(7, k, bean.getSciEnrollNum()+"", wcf1));
							ws.addCell(new Label(8, k, bean.getLibLowestScore()+"", wcf1));
							ws.addCell(new Label(9, k, bean.getSumLowestScore()+"", wcf1));
							ws.addCell(new Label(10, k, bean.getSciLowestScore()+"", wcf1));
							ws.addCell(new Label(11, k, bean.getLibAvgScore()+"", wcf1));
							ws.addCell(new Label(12, k, bean.getSumAvgScore()+"", wcf1));

							ws.addCell(new Label(13, k, bean.getNote()+"", wcf1));
						
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

	public String getFromTeaUnit() {
		return fromTeaUnit;
	}

	public void setFromTeaUnit(String fromTeaUnit) {
		this.fromTeaUnit = fromTeaUnit;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public T623_Service getT623_service() {
		return T623_service;
	}

	public void setT623_service(T623_Service t631Service) {
		T623_service = t631Service;
	}

	public T623_Bean getT623_bean() {
		return T623_bean;
	}

	public void setT623_bean(T623_Bean T623Bean) {
		T623_bean = T623Bean;
	}

	public String getSearchItem() {
		return searchItem;
	}

	public void setSearchItem(String searchItem) {
		this.searchItem = searchItem;
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
