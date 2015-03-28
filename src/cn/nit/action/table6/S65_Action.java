package cn.nit.action.table6;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;


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


import cn.nit.bean.table2.T21_Bean;
import cn.nit.bean.table6.S65_Bean;
import cn.nit.bean.table6.T651_Bean;


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
		

		boolean flag = true;
		S65_Bean bean = new S65_Bean();
		
		//T651中的统计数据

		if(T651_services.getYearInfo(this.getSelectYear()).size() == 0){
			flag = false;
//			System.out.println("T651empty");
		}else{
			bean = T651_services.getStatic(this.getSelectYear());
		}
		
		//System.out.println("bean:"+JsonUtil.beanToJson(bean));
		
		
		
		//T652，统计学生发表论文数		
		
		if(T652_services.getYearInfo(this.getSelectYear()).size() == 0){	
			flag = false;
//			System.out.println("T652empty");
		}else{
			int paperNum = T652_services.getPaper(this.getSelectYear());
			bean.setPaperNum(paperNum);
		}
		
		//T653，统计学生发表作品数
		
		if(T653_services.getYearInfo(this.getSelectYear()).size() == 0){	
			flag = false;
//			System.out.println("T653empty");
		}else{
			int workNum = T653_services.getWork(this.getSelectYear());
			bean.setWorkNum(workNum);
		}
		
		//T654，统计学生专利数
		
		if(T654_services.getYearInfo(this.getSelectYear()).size() == 0){	
			flag = false;
//			System.out.println("T654empty");
		}else{
			int patentNum = T654_services.getPatent(this.getSelectYear());
			bean.setPatentNum(patentNum);
		}
		
		//T655,英语四六级考试累计通过率
		
		if(T655_services.getYearInfo(this.getSelectYear()).size() == 0){	
			flag = false;
//			System.out.println("T655empty");
		}else{
			double cet4 = this.toTwo(T655_services.getCET4PassRate(this.getSelectYear()));
			double cet6 =this.toTwo( T655_services.getCET6PassRate(this.getSelectYear()));
			double jncre = this.toTwo(T655_services.getJPassRate(this.getSelectYear()));
			bean.setCET4(cet4);
			bean.setCET6(cet6);
			bean.setJingxiNCRE(jncre);
		}
		
		
		
		//T656,全国计算机等级考试累计通过率（%）
		if(T656_services.getYearInfo(this.getSelectYear()).size() == 0){	
			flag = false;
//			System.out.println("T656empty");
		}else{
			double ncre = this.toTwo(T656_services.getNPassRate(this.getSelectYear()));
			bean.setNCRE(ncre);
		}

				
		//T657,体质合格率、体质测试达标率
		
		if(T657_services.getYearInfo(this.getSelectYear()).size() == 0){	
			flag = false;
			System.out.println("T657empty");
		}else{
			double conQualify = this.toTwo(T657_services.getQualifiedRate(this.getSelectYear()));
			double conReach = this.toTwo(T657_services.getTestReachRate(this.getSelectYear()));
			bean.setConQualify(conQualify);
			bean.setConReach(conReach);
		}
		
		
		
		//T658,参加国际会议学生人数
		 
		if(T658_services.getYearInfo(this.getSelectYear()).size() == 0){	
			flag = false;
//			System.out.println("T658empty");
		}else{
			int interCon = T658_services.getInterConference(this.getSelectYear());
			bean.setInterConference(interCon);
		}

		if(flag == true){
			S65_services.save(bean, this.getSelectYear());
		}

//		//数据不为空相加
		String json = null;
		
		bean.setTime(null);
		json = JsonUtil.beanToJson(bean);
		
		
		PrintWriter out = null ;

		if(flag == false){
//			System.out.println("++++");
			response.setContentType("text/html;charset=UTF-8");
			out = response.getWriter();
			out.print("{\"data\":\"该统计表数据不全，请填写相关数据后再进行统计！！！\"}");
			System.out.println("统计数据不全");
		}
		else{
			try {				
//				System.out.println(json) ;
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

//		System.out.println(this.getSelectYear());
		S65_Bean bean = S65_services.getYearInfo(this.getSelectYear());
		
	    ByteArrayOutputStream fos = null;
		
		if(bean==null){
			
			PrintWriter out = null ;
			response.setContentType("text/html;charset=utf-8") ;
			out = response.getWriter() ;
			out.print("该统计表数据不全，请填写相关数据后再进行导出!!!") ;
			System.out.println("后台传入的数据为空");
			return null;
		}else{
			String sheetName = this.excelName;
						
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
		           ws.addCell(new Label(2, 2, "内容", wcf)); 
		           ws.addCell(new Label(0, 3, "1.学生发表学术论文（篇）", wcf)); 
		           ws.addCell(new Label(0, 4, "2.学生发表作品数（篇、册）", wcf));
		           ws.addCell(new Label(0, 5, "3.学生获准专利数（项）", wcf)); 
		           ws.addCell(new Label(0, 6, "4.英语等级考试", wcf)); 
		           ws.addCell(new Label(0, 8, "5.计算机等级考试", wcf)); 
		           ws.addCell(new Label(0, 10, "6.体质合格率（%）", wcf));
		           ws.addCell(new Label(0, 11, "7.体质测试达标率（%）", wcf)); 
		           ws.addCell(new Label(0, 12, "8.参加国际会议（人次）", wcf)); 
		           ws.addCell(new Label(0, 13, "9.学科竞赛获奖（项）", wcf)); 
		           ws.addCell(new Label(0, 19, "10.本科生创新活动、技能竞赛获奖（项）", wcf)); 
		           ws.addCell(new Label(0, 25, "11.文艺、体育竞赛获奖（项）", wcf)); 

		           ws.addCell(new Label(1, 6, "英语四级考试累计通过率（%）", wcf)); 
		           ws.addCell(new Label(1, 7, "英语六级考试累计通过率（%）", wcf)); 
		           ws.addCell(new Label(1, 8, "全国计算机等级考试累计通过率（%）", wcf)); 
		           ws.addCell(new Label(1, 9, "江西省计算机等级考试累计通过率（%）", wcf)); 
		           ws.addCell(new Label(1, 13, "总数", wcf)); 
		           ws.addCell(new Label(1, 14, "其中：国际级", wcf)); 
		           ws.addCell(new Label(1, 15, "国家级", wcf)); 
		           ws.addCell(new Label(1, 16, "省部级", wcf)); 
		           ws.addCell(new Label(1, 17, "市级", wcf)); 
		           ws.addCell(new Label(1, 18, "校级", wcf)); 
		           ws.addCell(new Label(1, 19, "总数", wcf)); 
		           ws.addCell(new Label(1, 20, "其中：国际级", wcf)); 
		           ws.addCell(new Label(1, 21, "国家级", wcf)); 
		           ws.addCell(new Label(1, 22, "省部级", wcf)); 
		           ws.addCell(new Label(1, 23, "市级", wcf)); 
		           ws.addCell(new Label(1, 24, "校级", wcf)); 
		           ws.addCell(new Label(1, 25, "总数", wcf)); 
		           ws.addCell(new Label(1, 26, "其中：国际级", wcf)); 
		           ws.addCell(new Label(1, 27, "国家级", wcf)); 
		           ws.addCell(new Label(1, 28, "省部级", wcf)); 
		           ws.addCell(new Label(1, 29, "市级", wcf)); 
		           ws.addCell(new Label(1, 30, "校级", wcf)); 

		           ws.mergeCells(0, 2, 1, 2);
		           ws.mergeCells(0, 3, 1, 3);
		           ws.mergeCells(0, 4, 1, 4);
		           ws.mergeCells(0, 5, 1, 5);
		           ws.mergeCells(0, 6, 0, 7);
		           ws.mergeCells(0, 8, 0, 9);
		           ws.mergeCells(0, 10, 1, 10);
		           ws.mergeCells(0, 11, 1, 11);
		           ws.mergeCells(0, 12, 1, 12);
		           ws.mergeCells(0, 13, 0, 18);
		           ws.mergeCells(0, 19, 0, 24);
		           ws.mergeCells(0, 25, 0, 30);
		           		           
		           ws.addCell(new Label(2, 3, ""+bean.getPaperNum(), wcf1)); 
		           ws.addCell(new Label(2, 4, ""+bean.getWorkNum(), wcf1));  
		           ws.addCell(new Label(2, 5, ""+bean.getPatentNum(), wcf1)); 
		           ws.addCell(new Label(2, 6, ""+bean.getCET4(), wcf1)); 
		           ws.addCell(new Label(2, 7, ""+bean.getCET6(), wcf1)); 
		           ws.addCell(new Label(2, 8, ""+bean.getNCRE(), wcf1)); 
		           ws.addCell(new Label(2, 9, ""+bean.getJingxiNCRE(), wcf1)); 
		           ws.addCell(new Label(2, 10, ""+bean.getConQualify(), wcf1)); 
		           ws.addCell(new Label(2, 11, ""+bean.getConReach(), wcf1)); 
		           ws.addCell(new Label(2, 12, ""+bean.getInterConference(), wcf1)); 
		           ws.addCell(new Label(2, 13, ""+bean.getSumDiscipAward(), wcf1)); 
		           ws.addCell(new Label(2, 14, ""+bean.getInterD(), wcf1));
		           ws.addCell(new Label(2, 15, ""+bean.getNationD(), wcf1)); 
		           ws.addCell(new Label(2, 16, ""+bean.getProviD(), wcf1));
		           ws.addCell(new Label(2, 17, ""+bean.getCityD(), wcf1));
		           ws.addCell(new Label(2, 18, ""+bean.getSchD(), wcf1)); 
		           ws.addCell(new Label(2, 19, ""+bean.getSumActAward(), wcf1)); 
		           ws.addCell(new Label(2, 20, ""+bean.getInterA(), wcf1)); 
		           ws.addCell(new Label(2, 21, ""+bean.getNationA(), wcf1)); 
		           ws.addCell(new Label(2, 22, ""+bean.getProviA(), wcf1)); 
		           ws.addCell(new Label(2, 23, ""+bean.getCityA(), wcf1)); 
		           ws.addCell(new Label(2, 24, ""+bean.getSchA(), wcf1));
		           ws.addCell(new Label(2, 25, ""+bean.getSumLiterSportAward(), wcf1)); 
		           ws.addCell(new Label(2, 26, ""+bean.getInterLS(), wcf1));
		           ws.addCell(new Label(2, 27, ""+bean.getNationLS(), wcf1));
		           ws.addCell(new Label(2, 28, ""+bean.getProviLS(), wcf1)); 
		           ws.addCell(new Label(2, 29, ""+bean.getCityLS(), wcf1)); 
		           ws.addCell(new Label(2, 30, ""+bean.getSchLS(), wcf1));
		             

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
		System.out.println("excelName=============" + this.excelName) ;
		return "success" ;
	}
	

	public S65_Bean getS65_bean() {
		return S65_bean;
	}

	public void setS22_bean(S65_Bean S22Bean) {
		S65_bean = S22Bean;
	}
	
	public String getExcelName() {
		try{
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
	
	/**double保留两位小数*/
	public double toTwo(double num){
		
		 DecimalFormat df = new DecimalFormat("#.00");
		 String str = df.format(num);
		 double num1 = Double.valueOf(str);
		 return num1;
		 
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