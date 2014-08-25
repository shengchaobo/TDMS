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

import cn.nit.bean.table6.T621_Bean;
import cn.nit.bean.table6.T631_Bean;
import cn.nit.bean.table6.T632_Bean;
import cn.nit.dao.table6.T632_Dao;
import cn.nit.dbconnection.DBConnection;
import cn.nit.service.table6.T632_Service;
import cn.nit.util.DAOUtil;
import cn.nit.util.ExcelUtil;

/**
 * 待完成！！！！！！！
 * 
 * @author Yuan
 */
public class T632_Action {

	/** �?32的Service�?*/
	private T632_Service T632_service = new T632_Service();

	/** �?32的Bean实体�?*/
	T632_Bean T632_bean = new T632_Bean();
	
	T632_Dao T632_dao = new T632_Dao();

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

	/** 当前查询的是第几*/
	private String page;

	/** 每页显示的条*/
	private String rows;
	
	/**所属教学单*/
	private String fromTeaUnit;
	
	/**专业名称*/
	private String majorName;
	
	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;

	/** 逐条插入数据 */
	public void insert() {
		System.out
				.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		T632_bean.setSumGoOnHighStudyNum(T632_bean.getRecommendGraNum()+T632_bean.getExamGraInSch()+T632_bean.getExamGraOutSch()+T632_bean.getAbroadNum());//统计生成-应届升学总人�?
		T632_bean.setExamGraEnrollNum(T632_bean.getExamGraInSch()+T632_bean.getExamGraOutSch());//统计生成-考研录取总人�?
		T632_bean.setGoOnHighStudy(T632_bean.getSumGoOnHighStudyNum());//引用生成--升学
		
		T632_bean.setSumEmployNum(T632_bean.getGovermentNum()+T632_bean.getPubInstiNum()+T632_bean.getEnterpriseNum()+T632_bean.getForceNum()
				+T632_bean.getFlexibleEmploy()+T632_bean.getNationItemEmploy()+T632_bean.getOtherEmploy()+T632_bean.getGoOnHighStudy());//统计生成-应届就业总人�?
		
		boolean flag = T632_service.insert(T632_bean);
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

	/** 为界面加载数�?*/
	public void loadData() throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();
		
		
		// private JSONObject jsonObj;
		
		String cond = "1=1";
		if(this.getSearchItem()!= null){
			cond += " and MajorName LIKE '" + this.getSearchItem() + "%'";
			System.out.println(cond);
		}
		List<T632_Bean> list = T632_service.getPageInfoList(cond,null,this.getRows(), this.getPage());
		String TeaInfoJson = this.toBeJson(list, T632_service.getTotal(cond,null));

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

	// 将分页系统的总数以及当前页的list转化一个json传页面显�?
	private String toBeJson(List<T632_Bean> list, int total) throws Exception {
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
		
		T632_bean.setSumGoOnHighStudyNum(T632_bean.getRecommendGraNum()+T632_bean.getExamGraInSch()+T632_bean.getExamGraOutSch()+T632_bean.getAbroadNum());//统计生成-应届升学总人�?
		T632_bean.setExamGraEnrollNum(T632_bean.getExamGraInSch()+T632_bean.getExamGraOutSch());//统计生成-考研录取总人�?
		T632_bean.setGoOnHighStudy(T632_bean.getSumGoOnHighStudyNum());//引用生成--升学
		
		T632_bean.setSumEmployNum(T632_bean.getGovermentNum()+T632_bean.getPubInstiNum()+T632_bean.getEnterpriseNum()+T632_bean.getForceNum()
				+T632_bean.getFlexibleEmploy()+T632_bean.getNationItemEmploy()+T632_bean.getOtherEmploy()+T632_bean.getGoOnHighStudy());//统计生成-应届就业总人�?

		boolean flag = T632_service.update(T632_bean);
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
			out.print("{\"state\":false,data:\"系统错误，请联系管理�?!!\"}");
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/** 根据数据的id删除数据 */
	public void deleteByIds() {
		System.out.println("ids=" +this.getIds());
		boolean flag = T632_service.deleteItemsByIds(ids);
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
			out.print("{\"state\":false,data:\"系统错误，请联系管理�?!!\"}");
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
			
			List<T632_Bean> list = T632_dao.getAllList("1=1", null);
			
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
				int SumEmployNum=0; int GovermentNum=0; int PubInstiNum=0; int EnterpriseNum=0;
				int ForceNum = 0; int FlexibleEmploy = 0; int GoOnHighStudy = 0;
				int NationItemEmploy = 0;int OtherEmploy = 0; int SumGoOnHighStudyNum = 0;
				int RecommendGraNum = 0; int ExamGraApplyNum = 0; int ExamGraEnrollNum = 0;
				int ExamGraInSch = 0; int ExamGraOutSch = 0; int AbroadNum = 0;
				//统计全校合计
				for(T632_Bean bean : list){
					SumEmployNum+=bean.getSumEmployNum();
					GovermentNum+=bean.getGovermentNum();
					PubInstiNum+=bean.getPubInstiNum();
					EnterpriseNum+=bean.getEnterpriseNum();
					ForceNum+=bean.getForceNum();
					FlexibleEmploy+=bean.getFlexibleEmploy();
					GoOnHighStudy+=bean.getGoOnHighStudy();
					NationItemEmploy+=bean.getNationItemEmploy();
					OtherEmploy+=bean.getOtherEmploy();
					SumGoOnHighStudyNum+=bean.getSumGoOnHighStudyNum();
					RecommendGraNum+=bean.getRecommendGraNum();
					ExamGraApplyNum+=bean.getExamGraApplyNum();
					ExamGraEnrollNum+=bean.getExamGraEnrollNum();
					ExamGraInSch+=bean.getExamGraInSch();
					ExamGraOutSch+=bean.getExamGraOutSch();
					AbroadNum+=bean.getAbroadNum();
				}
				
				T632_Bean bean = new T632_Bean();
				bean.setSumEmployNum(SumEmployNum);
				bean.setGovermentNum(GovermentNum);
				bean.setPubInstiNum(PubInstiNum);
				bean.setEnterpriseNum(EnterpriseNum);
				bean.setForceNum(ForceNum);
				bean.setFlexibleEmploy(FlexibleEmploy);
				
				bean.setGoOnHighStudy(GoOnHighStudy);
				bean.setNationItemEmploy(NationItemEmploy);
				bean.setOtherEmploy(OtherEmploy);
				bean.setSumGoOnHighStudyNum(SumGoOnHighStudyNum);
				bean.setRecommendGraNum(RecommendGraNum);
				bean.setExamGraApplyNum(ExamGraApplyNum);
				
				bean.setExamGraEnrollNum(ExamGraEnrollNum);
				bean.setExamGraInSch(ExamGraInSch);
				bean.setExamGraOutSch(ExamGraOutSch);
				bean.setAbroadNum(AbroadNum);
				bean.setTeaUnit("全校合计：");
				list.add(0, bean);
				
				String sheetName = this.getExcelName();
				
			
				WritableWorkbook wwb;
				try{
					
					 fos = new ByteArrayOutputStream();
			            wwb = Workbook.createWorkbook(fos);
			            WritableSheet ws = wwb.createSheet("表6-3-2分专业应届本科毕业生就业情况（招就处）", 0);        // 创建一个工作表

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
						ws.addCell(new Label(0, 0, "表6-3-2分专业应届本科毕业生就业情况（招就处）", wcf)); 
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
