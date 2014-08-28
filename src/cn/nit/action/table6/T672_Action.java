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
import cn.nit.bean.table6.T631_Bean;
import cn.nit.bean.table6.T632_Bean;
import cn.nit.bean.table6.T641_Bean;
import cn.nit.bean.table6.T651_Bean;
import cn.nit.bean.table6.T652_Bean;
import cn.nit.bean.table6.T653_Bean;
import cn.nit.bean.table6.T654_Bean;
import cn.nit.bean.table6.T655_Bean;
import cn.nit.bean.table6.T657_Bean;
import cn.nit.bean.table6.T658_Bean;
import cn.nit.bean.table6.T659_Bean;
import cn.nit.bean.table6.T66_Bean;
import cn.nit.bean.table6.T671_Bean;
import cn.nit.bean.table6.T672_Bean;
import cn.nit.dao.table6.T611_Dao;
import cn.nit.dao.table6.T612_Dao;
import cn.nit.dao.table6.T613_Dao;
import cn.nit.dao.table6.T614_Dao;
import cn.nit.dao.table6.T615_Dao;
import cn.nit.dao.table6.T617_Dao;
import cn.nit.dao.table6.T622_Dao;
import cn.nit.dao.table6.T632_Dao;
import cn.nit.dao.table6.T641_Dao;
import cn.nit.dao.table6.T651_Dao;
import cn.nit.dao.table6.T652_Dao;
import cn.nit.dao.table6.T653_Dao;
import cn.nit.dao.table6.T654_Dao;
import cn.nit.dao.table6.T655_Dao;
import cn.nit.dao.table6.T657_Dao;
import cn.nit.dao.table6.T658_Dao;
import cn.nit.dao.table6.T659_Dao;
import cn.nit.dao.table6.T66_Dao;
import cn.nit.dao.table6.T671_Dao;
import cn.nit.dao.table6.T672_Dao;
import cn.nit.dbconnection.DBConnection;
import cn.nit.service.table6.T611_Service;
import cn.nit.service.table6.T612_Service;
import cn.nit.service.table6.T613_Service;
import cn.nit.service.table6.T614_Service;
import cn.nit.service.table6.T615_Service;
import cn.nit.service.table6.T617_Service;
import cn.nit.service.table6.T622_Service;
import cn.nit.service.table6.T632_Service;
import cn.nit.service.table6.T641_Service;
import cn.nit.service.table6.T651_Service;
import cn.nit.service.table6.T652_Service;
import cn.nit.service.table6.T653_Service;
import cn.nit.service.table6.T654_Service;
import cn.nit.service.table6.T655_Service;
import cn.nit.service.table6.T657_Service;
import cn.nit.service.table6.T658_Service;
import cn.nit.service.table6.T659_Service;
import cn.nit.service.table6.T66_Service;
import cn.nit.service.table6.T671_Service;
import cn.nit.service.table6.T672_Service;
import cn.nit.util.DAOUtil;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;

/**
 * 待完成！！！！！！！
 * 
 * @author Yuan
 */
public class T672_Action {

	/** 表的Service类 */
	private T672_Service T672_service = new T672_Service();

	/** 表的Bean实体类 */
	T672_Bean T672_bean = new T672_Bean();
	
	T672_Dao T672_dao = new T672_Dao();

	/** 待审核数据的查询的序列号 */
	private int seqNum;

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
	
	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;

	/** 逐条插入数据 */
	public void insert() {
		System.out
				.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		boolean flag = T672_service.insert(T672_bean);
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

		HttpServletResponse response = ServletActionContext.getResponse();
		
		
		// private JSONObject jsonObj;
		
		String cond = "1=1";
		if(this.getSearchItem()!= null){
			cond += " and stuName LIKE '" + this.getSearchItem() + "%'";
			System.out.println(cond);
		}
		List<T672_Bean> list = T672_service.getPageInfoList(cond,null,this.getRows(), this.getPage());
		String TeaInfoJson = this.toBeJson(list, T672_service.getTotal(cond,null));

		PrintWriter out = null;

		if (TeaInfoJson == null) {
			return;
		} else {
			try {

				System.out.println(TeaInfoJson);
				response.setContentType("application/json;charset=UTF-8");
				out = response.getWriter();
				out.print(TeaInfoJson);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (out != null) {
					out.flush();
					out.close();
				}
			}
		}
	}

	// 将分页系统的总数以及当前页的list转化一个json传页面显示
	private String toBeJson(List<T672_Bean> list, int total) throws Exception {
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
		boolean flag = T672_service.update(T672_bean);
		PrintWriter out = null;

		try {
			out = getResponse().getWriter();
			if (flag) {
				out.print("{\"state\":true,data:\"编辑成功!!!\"}");
			} else {
				out.print("{\"state\":true,data:\"编辑失败!!!\"}");
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

	/** 根据数据的id删除数据 */
	public void deleteByIds() {
		System.out.println("ids=" +this.getIds());
		boolean flag = T672_service.deleteItemsByIds(ids);
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
			
			List<T672_Bean> list = T672_dao.getAllList("1=1", null);
						
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
						
						ws.addCell(new Label(0, 2, "序号", wcf)); ws.mergeCells(0, 2, 0, 3);
						ws.addCell(new Label(1, 2, "学生姓名", wcf)); ws.mergeCells(1, 2, 1, 3);
						ws.addCell(new Label(2, 2, "学号", wcf)); ws.mergeCells(2, 2, 2, 3);
						
						ws.addCell(new Label(3, 2, "1.学生基本信息", wcf)); ws.mergeCells(3, 2, 7, 2);
						ws.addCell(new Label(8, 2, "2.学生辅修信息", wcf)); ws.mergeCells(8, 2, 13, 2);
						
						ws.addCell(new Label(3, 3, "所在教学单位", wcf));ws.addCell(new Label(4, 3, "单位号", wcf));
						ws.addCell(new Label(5, 3, "所在专业", wcf));ws.addCell(new Label(6, 3, "所在专业代码", wcf));
						ws.addCell(new Label(7, 3, "所在班级", wcf));ws.addCell(new Label(8, 3, "双学位所在教学单位", wcf));
						ws.addCell(new Label(9, 3, "单位号", wcf));ws.addCell(new Label(10, 3, "双学位专业", wcf));
						ws.addCell(new Label(11, 3, "双学位专业代码", wcf));ws.addCell(new Label(12, 3, "起始时间", wcf));
						ws.addCell(new Label(13, 3, "预计毕业时间", wcf));
						
						//向表中写数据
						int k=4;//从第4行开始写数据,第3行为全校合计数
						for(int j=0;j<list.size();j++){
							T672_Bean bean1 =  list.get(j);
							
								ws.addCell(new Label(0, k,(j+1)+"", wcf));
								ws.addCell(new Label(1, k, bean1.getStuName(), wcf));
								ws.addCell(new Label(2, k, bean1.getStuId(), wcf));
								ws.addCell(new Label(3, k, bean1.getFromTeaUnit(), wcf));
								ws.addCell(new Label(4, k, bean1.getUnitId(), wcf));
								ws.addCell(new Label(5, k, bean1.getFromMaj(), wcf));
								ws.addCell(new Label(6, k, bean1.getMajId(), wcf));
								ws.addCell(new Label(7, k, bean1.getFromClass(), wcf));
								ws.addCell(new Label(8, k, bean1.getDualDegreeFromTeaUnit(), wcf));
								ws.addCell(new Label(9, k, bean1.getDualDegreeUnitId(), wcf));
								ws.addCell(new Label(10, k, bean1.getDualDegreeMaj(), wcf));
								ws.addCell(new Label(11, k, bean1.getDualDegreeId(), wcf));
								ws.addCell(new Label(12, k, TimeUtil.changeFormat4(bean1.getBeginTime()), wcf));
								ws.addCell(new Label(13, k, TimeUtil.changeFormat4(bean1.getGraduateTime()), wcf));
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

	public int getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(int seqNum) {
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

	public T672_Service getT672_service() {
		return T672_service;
	}

	public void setT672_service(T672_Service t631Service) {
		T672_service = t631Service;
	}

	public T672_Bean getT672_bean() {
		return T672_bean;
	}

	public void setT672_bean(T672_Bean T672Bean) {
		T672_bean = T672Bean;
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
