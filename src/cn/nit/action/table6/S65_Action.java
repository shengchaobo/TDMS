package cn.nit.action.table6;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;


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

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;


import cn.nit.bean.table6.S65_Bean;


import cn.nit.service.table6.S65_Service;
import cn.nit.service.table6.T652_Service;
import cn.nit.service.table6.T653_Service;
import cn.nit.service.table6.T654_Service;
import cn.nit.service.table6.T655_Service;
import cn.nit.service.table6.T656_Service;
import cn.nit.service.table6.T657_Service;
import cn.nit.service.table6.T658_Service;
import cn.nit.service.table6.T651_Service;

import cn.nit.util.ExcelUtil;
import cn.nit.util.JsonUtil;
import cn.nit.util.TimeUtil;
import cn.nit.util.ToBeanUtil;


public class S65_Action {
	
	private S65_Service S65_services = new S65_Service();
	private T652_Service T652_services = new T652_Service();
	private T653_Service T653_services = new T653_Service();
	private T654_Service T654_services = new T654_Service();
	private T655_Service T655_services = new T655_Service();
	private T656_Service T656_services = new T656_Service();
	private T657_Service T657_services = new T657_Service();
	private T658_Service T658_services = new T658_Service();
	private T651_Service T651_services = new T651_Service();
//	private S65_Service S65_services = new S65_Service();
	

	
	private S65_Bean S65_bean = new S65_Bean();	
	
	/**  哪一年数据  */
	private String selectYear; //删除的id
	
	/**  导出的excelName名 */
	private String excelName ;
	
	/**  前台获数据 */
	private String data ;
	
	//save的字段
	private String fields;
	
	
	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	
	//查询出所有
	public void loadInfo() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse() ;	
		
		boolean flag0 = true; 
		S65_Bean bean = new S65_Bean();
		
		//T651中的统计数据
		
		bean = T651_services.getStatic(this.getSelectYear());
		System.out.println("bean:"+JsonUtil.beanToJson(bean));
		
		
		
		//T652，统计学生发表论文数		
		int paperNum = T652_services.getPaper(this.getSelectYear());
		if(paperNum == 0){	
			flag0 = false;
			System.out.println("T652empty");
		}else{
			bean.setPaperNum(paperNum);
		}
		
		//T653，统计学生发表作品数
		int workNum = T653_services.getWork(this.getSelectYear());
		if(workNum == 0){	
			flag0 = false;
			System.out.println("T653empty");
		}else{
			bean.setWorkNum(workNum);
		}
		
		//T654，统计学生专利数
		int patentNum = T654_services.getPatent(this.getSelectYear());
		if(patentNum == 0){	
			flag0 = false;
			System.out.println("T654empty");
		}else{
			bean.setPatentNum(patentNum);
		}
		
		//T655,英语四级考试累计通过率
		double cet4 = T655_services.getCET4PassRate(this.getSelectYear());
		if(cet4 == 0){	
			flag0 = false;
			System.out.println("T655empty");
		}else{
			bean.setCET4(cet4);
		}
		
		//T655,英语六级考试累计通过率
		double cet6 = T655_services.getCET6PassRate(this.getSelectYear());
		if(cet6 == 0){	
			flag0 = false;
			System.out.println("T655empty");
		}else{
			bean.setCET6(cet6);
		}
		
		//T656,全国计算机等级考试累计通过率（%）
		double ncre = T656_services.getNPassRate(this.getSelectYear());
		if(ncre == 0){	
			flag0 = false;
			System.out.println("T656empty");
		}else{
			bean.setNCRE(ncre);
		}

		//T655,英语六级考试累计通过率
		double jncre = T655_services.getJPassRate(this.getSelectYear());
		if(jncre == 0){	
			flag0 = false;
			System.out.println("T655empty");
		}else{
			bean.setJingxiNCRE(jncre);
		}
		
		//T657,体质合格率
		double conQualify = T657_services.getQualifiedRate(this.getSelectYear());
		if(conQualify == 0){	
			flag0 = false;
			System.out.println("T657empty");
		}else{
			bean.setConQualify(conQualify);
		}
		
		//T657,体质测试达标率
		double conReach = T657_services.getTestReachRate(this.getSelectYear());
		if(conReach == 0){	
			flag0 = false;
			System.out.println("T657empty");
		}else{
			bean.setConReach(conReach);
		}
		
		//T658,参加国际会议学生人数
		int interCon = T658_services.getInterConference(this.getSelectYear());
		if(interCon == 0){	
			flag0 = false;
			System.out.println("T658empty");
		}else{
			bean.setInterConference(interCon);
		}
//		//数据不为空相加
//		boolean flag = true;
		String json = null;
		
		bean.setTime(null);
		json = JsonUtil.beanToJson(bean);
		
				
		
		PrintWriter out = null ;

		if(flag0 == false){
			response.setContentType("text/html;charset=UTF-8") ;
			out = response.getWriter() ;
			out.print("{\"data\":\"该统计表数据不全，请填写相关数据后再进行统计!!!\"}") ;
			System.out.println("统计数据不全");
		}
//		else if(flag == false ){
//			System.out.println("统计数据保存失败");
//			response.setContentType("text/html;charset=UTF-8") ;
//			out = response.getWriter() ;
//			out.println("{\"data\":\"统计数据保存失败\"}"); 
//		}
		else{
			try {				
				System.out.println(json) ;
				response.setContentType("application/json;charset=UTF-8") ;
				out = response.getWriter() ;
				out.print(json) ;
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
	
		
	public InputStream getInputStream() throws Exception{

		System.out.println(this.getSelectYear());
		S65_Bean bean = S65_services.getYearInfo(this.getSelectYear());
		
	    ByteArrayOutputStream fos = null;
		
		if(bean==null){
			PrintWriter out = null ;
			response.setContentType("text/html;charset=utf-8") ;
			out = response.getWriter() ;
			out.print("后台传入的数据为空") ;
			System.out.println("后台传入的数据为空");
			return null;
		}else{
			String sheetName = this.getExcelName();
						
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
		           ws.mergeCells(0, 0, 1, 0);
		           
		           ws.addCell(new Label(0, 2, "项目", wcf)); 
		           ws.addCell(new Label(1, 2, "内容", wcf)); 
		           ws.addCell(new Label(0, 3, "1.教学科研及辅助用房（平方米）", wcf)); 
		           ws.addCell(new Label(0, 4, "其中：教室", wcf));  
		           ws.addCell(new Label(0, 5, "图书馆", wcf)); 
		           ws.addCell(new Label(0, 6, "实验室、实习场所", wcf)); 
		           ws.addCell(new Label(0, 7, "专用科研用房", wcf)); 
		           ws.addCell(new Label(0, 8, "体育馆", wcf)); 
		           ws.addCell(new Label(0, 9, "会堂", wcf)); 
		           ws.addCell(new Label(0, 10, "2.行政用房（平方米）", wcf)); 
		           
		           		           
//		           ws.addCell(new Label(1, 3, bean.getSumTeaArea().toString(), wcf1)); 
//		           ws.addCell(new Label(1, 4, bean.getClassrmArea().toString(), wcf1));  
//		           ws.addCell(new Label(1, 5, bean.getLibArea().toString(), wcf1)); 
//		           ws.addCell(new Label(1, 6, bean.getLabArea().toString(), wcf1)); 
//		           ws.addCell(new Label(1, 7, bean.getResArea().toString(), wcf1)); 
//		           ws.addCell(new Label(1, 8, bean.getPhyArea().toString(), wcf1)); 
//		           ws.addCell(new Label(1, 9, bean.getHallArea().toString(), wcf1)); 
//		           ws.addCell(new Label(1, 10, bean.getSumAdminArea().toString(), wcf1)); 
		             

		          wwb.write();
		          wwb.close();

		        } catch (IOException e){
		        } catch (RowsExceededException e){
		        } catch (WriteException e){}
		        
		}
		return new ByteArrayInputStream(fos.toByteArray());
	}
	
	public String execute() throws Exception{
		response.setContentType("text/html;charset=utf-8"); 
		System.out.println("excelName=============" + this.getExcelName()) ;
		return "success" ;
	}
	

	public S65_Bean getS65_bean() {
		return S65_bean;
	}

	public void setS22_bean(S65_Bean S22Bean) {
		S65_bean = S22Bean;
	}
	
	public String getExcelName() {
		return excelName;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}

	public void setSelectYear(String selectYear) {
		this.selectYear = selectYear;
	}

	public String getSelectYear() {
		return selectYear;
	}



	public void setData(String data) {
		this.data = data;
	}



	public String getData() {
		return data;
	}



	public void setFields(String fields) {
		this.fields = fields;
	}



	public String getFields() {
		return fields;
	}
	
	public static void main(String arg[]) throws Exception{
		T651_Service T651services = new T651_Service();
		S65_Bean b = T651services.getStatic("2014");
		//b.setPaperNum(11);
		String json = JsonUtil.beanToJson(b);
		System.out.println(json);
		//S65_Action a = new S65_Action();
		//a.loadInfo();
	}
}