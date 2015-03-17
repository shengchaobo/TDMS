package cn.nit.action.table4;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;

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

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.table2.S28_Bean;
import cn.nit.bean.table2.T281_Bean;
import cn.nit.bean.table2.T282_Bean;
import cn.nit.bean.table2.T283_Bean;
import cn.nit.bean.table2.T284_Bean;
import cn.nit.bean.table2.T285_Bean;
import cn.nit.bean.table4.A411_Bean;
import cn.nit.service.table2.S28_Service;
import cn.nit.service.table2.T281_Service;
import cn.nit.service.table2.T282_Service;
import cn.nit.service.table2.T283_Service;
import cn.nit.service.table2.T284_Service;
import cn.nit.service.table2.T285_Service;
import cn.nit.service.table4.A411_Service;
import cn.nit.service.table4.T411_Service;
import cn.nit.util.JsonUtil;

public class A411_Action {
	

	
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
	A411_Service a411_service= new A411_Service();
	
	//查询出所有
	public void loadInfo() throws Exception{
		
		HttpServletResponse response = ServletActionContext.getResponse() ;	
		T411_Service t411_service = new T411_Service();

		
		boolean flag0 = true; 
		int num,num1,num2,num3,num4,num5,num6,num7,num8,num9,num10,num11,num12,num13,num14,num15,num16,num17,num18,num19,num20,num21,num22,num23,num24;
		double ratio1,ratio2,ratio3,ratio4,ratio5,ratio6,ratio7,ratio8,ratio9,ratio10,ratio11,ratio12,ratio13,ratio14,ratio15,ratio16,ratio17,ratio18,ratio19,ratio20,ratio21,ratio22,ratio23,ratio24;
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(2);
		DecimalFormat dec = new DecimalFormat("0.0000");
		num = t411_service.getTotalNum();
		num1 = t411_service.getTitleNum("41000");
		num2 = t411_service.getTitleNum("41001");
		num3 = t411_service.getTitleNum("41002");
		num4 = num - num1 - num2 - num3;
		num5 = t411_service.getDegreeNum("81000");
		num6 = t411_service.getDegreeNum("81001");
		num7 = t411_service.getDegreeNum("81002");
		num8 = t411_service.getDegreeNum("81004");
		num9 = t411_service.getAgeNum(selectYear, 0, 35);
		num10 = t411_service.getAgeNum(selectYear, 36, 45);
		num11 = t411_service.getAgeNum(selectYear, 46, 55);
		num12 = num - num9 - num10 - num11;
		num13 = t411_service.getSourceNum("83000");
		num15 = t411_service.getSourceNum("83003");
		num14 = num - num13 - num15;
		num16 = t411_service.getDoubleNum("doubleTea");
		num17 = t411_service.getDoubleNum("industry");
		num18 = t411_service.getDoubleNum("engineer");
		num19 = t411_service.getIDNum(0);
		num20 = t411_service.getIDNum(1);
		num21 = t411_service.getIDNum(2);
		num22 = t411_service.getIDNum(3);
		num23 = t411_service.getIDNum(4);
		num24 = t411_service.getIDNum(5);
		

	
		ratio1 = Double.parseDouble(nf.format((double)num1*100/num));
		ratio2 = Double.parseDouble(nf.format((double)num2*100/num));
		
		
		
		ratio3 = Double.parseDouble(nf.format((double)num3*100/num));
		ratio4 = Double.parseDouble(nf.format((double)num4*100/num));
		ratio5 = Double.parseDouble(nf.format((double)num5*100/num));
		ratio6 = Double.parseDouble(nf.format((double)num6*100/num));
		ratio7 = Double.parseDouble(nf.format((double)num7*100/num));
		ratio8 = Double.parseDouble(nf.format((double)num8*100/num));
		ratio9 = Double.parseDouble(nf.format((double)num9*100/num));
		ratio10 = Double.parseDouble(nf.format((double)num10*100/num));
		ratio11 = Double.parseDouble(nf.format((double)num11*100/num));
		ratio12 = Double.parseDouble(nf.format((double)num12*100/num));
		ratio13 = Double.parseDouble(nf.format((double)num13*100/num));
		ratio14 = Double.parseDouble(nf.format((double)num14*100/num));
		ratio15 = Double.parseDouble(nf.format((double)num15*100/num));
		ratio16 = Double.parseDouble(nf.format((double)num16*100/num));
		ratio17 = Double.parseDouble(nf.format((double)num17*100/num));
		ratio18 = Double.parseDouble(nf.format((double)num18*100/num));
		ratio19 = Double.parseDouble(nf.format((double)num19*100/num));
		ratio20 = Double.parseDouble(nf.format((double)num20*100/num));
		ratio21 = Double.parseDouble(nf.format((double)num21*100/num));
		ratio22 = Double.parseDouble(nf.format((double)num22*100/num));
		ratio23 = Double.parseDouble(nf.format((double)num23*100/num));
		ratio24 = Double.parseDouble(nf.format((double)num24*100/num));
		
		
		
		
		
		A411_Bean bean = new A411_Bean();
				

		
		//T285_Bean bean285 = T285_services.getYearInfo(this.getSelectYear());
		
		if(num == 0){	
			flag0 = false;
			System.out.println("bean281-5empty");
		}else{
			
			bean.setSeniorNum(num1);
			bean.setSeniorRatio(ratio1);
			bean.setSubSenior(num2);
			bean.setSubSeniorRatio(ratio2);
			bean.setMiddleNum(num3);
			bean.setMiddleRatio(ratio3);
			bean.setPrimaryNum(num4);
			bean.setPrimaryRatio(ratio4);
			bean.setDoctorNum(num5);
			bean.setDoctorRatio(ratio5);
			bean.setMasterNum(num6);
			bean.setMasterRatio(ratio6);
			bean.setBachelorNum(num7);
			bean.setBachelorRatio(ratio7);
			bean.setNotDegreeNum(num8);
			bean.setNotDegreeRatio(ratio8);
			bean.setBelow35Num(num9);
			bean.setBelow35Ratio(ratio9);
			bean.setIn36To45Num(num10);
			bean.setIn36To45Ratio(ratio10);
			bean.setIn46To55Num(num11);
			bean.setIn46To55Ratio(ratio11);
			bean.setAbove56Num(num12);
			bean.setAbove56Ratio(ratio12);
			bean.setThisSchNum(num13);
			bean.setThisSchRatio(ratio13);
			bean.setOutSchInNum(num14);
			bean.setOutSchInRatio(ratio14);
			bean.setOutSchOutNum(num15);
			bean.setOutSchOutRatio(ratio15);
			bean.setDuTeaNum(num16);
			bean.setDuTeaRatio(ratio16);
			bean.setIndustryNum(num17);
			bean.setIndustryRatio(ratio17);
			bean.setEngineerNum(num18);
			bean.setEngineerRatio(ratio18);
			bean.setFullTimeTeaNum(num19);
			bean.setFullTimeRatio(ratio19);
			bean.setTeaManageNum(num20);
			bean.setTeaManageRatio(ratio20);
			bean.setStuManageNum(num21);
			bean.setStuManageRatio(ratio21);
			bean.setTeaMonitorNum(num22);
			bean.setTeaMonitorRatio(ratio22);
			bean.setExpTeaNum(num23);
			bean.setExpTeaRatio(ratio23);
			bean.setOtherTeaNum(num24);
			bean.setOtherTeaRatio(ratio24);
			

		}
		

		
		//数据不为空相加
		boolean flag = false;
		String json = null;
		if(flag0 == true){
			flag = a411_service.save(bean);
			json = JsonUtil.beanToJson(bean);
			System.out.println(json) ;
		}
				
		

		PrintWriter out = null ;

		if(flag0 == false){
			response.setContentType("text/html;charset=UTF-8") ;
			out = response.getWriter() ;
			out.print("{\"data\":\"该统计表数据不全，请填写相关数据后再进行统计!!!\"}") ;
			System.out.println("统计数据不全");
		}
		else if(flag == false ){
			System.out.println("统计数据保存失败");
			response.setContentType("text/html;charset=UTF-8") ;
			out = response.getWriter() ;
			out.println("{\"data\":\"统计数据保存失败\"}"); 
		}
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

		A411_Bean bean = a411_service.getInfo();
		
	    ByteArrayOutputStream fos = null;
		
		if(bean==null){
			PrintWriter out = null ;
			response.setContentType("text/html;charset=utf-8") ;
			out = response.getWriter() ;
			out.print("后台传入的数据为空") ;
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
		           ws.mergeCells(0, 0, 2, 0);
		           
		           ws.addCell(new Label(0, 2, "项目", wcf)); 
		           ws.addCell(new Label(1, 2, "内容", wcf)); 
		           ws.addCell(new Label(0, 3, "1.职称结构", wcf)); 
		           ws.addCell(new Label(1, 3, "正高级", wcf));  
		           ws.addCell(new Label(3, 3, "副高级", wcf)); 
		           ws.addCell(new Label(5, 3, "中级", wcf)); 
		           ws.addCell(new Label(7, 3, "初级及以下", wcf)); 
		           ws.addCell(new Label(1, 4, "人数", wcf)); 
		           ws.addCell(new Label(2, 4, "比例", wcf)); 
		           ws.addCell(new Label(3, 4, "人数", wcf)); 
		           ws.addCell(new Label(4, 4, "比例", wcf)); 
		           ws.addCell(new Label(5, 4, "人数", wcf)); 
		           ws.addCell(new Label(6, 4, "比例", wcf)); 
		           ws.addCell(new Label(7, 4, "人数", wcf)); 
		           ws.addCell(new Label(8, 4, "比例", wcf)); 
		           
		           ws.addCell(new Label(1, 5, bean.getSeniorNum()+"", wcf1)); 
		           ws.addCell(new Label(2, 5, bean.getSeniorRatio()+"%", wcf1));  
		           ws.addCell(new Label(3, 5, bean.getSubSenior()+"", wcf1));
		           ws.addCell(new Label(4, 5, bean.getSubSeniorRatio()+"%", wcf1)); 
		           ws.addCell(new Label(5, 5, bean.getMiddleNum()+"", wcf1));  
		           ws.addCell(new Label(6, 5, bean.getMiddleRatio()+"%", wcf1));
		           ws.addCell(new Label(7, 5, bean.getPrimaryNum()+"", wcf1)); 
		           ws.addCell(new Label(8, 5, bean.getPrimaryRatio()+"%", wcf1));  

		           ws.addCell(new Label(0, 6, "2.学位结构", wcf)); 
		           ws.addCell(new Label(1, 6, "博士", wcf));  
		           ws.addCell(new Label(3, 6, "硕士", wcf)); 
		           ws.addCell(new Label(5, 6, "学士", wcf)); 
		           ws.addCell(new Label(7, 6, "无学位", wcf)); 
		           ws.addCell(new Label(1, 7, "人数", wcf)); 
		           ws.addCell(new Label(2, 7, "比例", wcf)); 
		           ws.addCell(new Label(3, 7, "人数", wcf)); 
		           ws.addCell(new Label(4, 7, "比例", wcf)); 
		           ws.addCell(new Label(5, 7, "人数", wcf)); 
		           ws.addCell(new Label(6, 7, "比例", wcf)); 
		           ws.addCell(new Label(7, 7, "人数", wcf)); 
		           ws.addCell(new Label(8, 7, "比例", wcf)); 
		           
		           ws.addCell(new Label(1, 8, bean.getDoctorNum()+"", wcf1)); 
		           ws.addCell(new Label(2, 8, bean.getDoctorRatio()+"%", wcf1));  
		           ws.addCell(new Label(3, 8, bean.getMasterNum()+"", wcf1));
		           ws.addCell(new Label(4, 8, bean.getMasterRatio()+"%", wcf1)); 
		           ws.addCell(new Label(5, 8, bean.getBachelorNum()+"", wcf1));  
		           ws.addCell(new Label(6, 8, bean.getBachelorRatio()+"%", wcf1));
		           ws.addCell(new Label(7, 8, bean.getNotDegreeNum()+"", wcf1)); 
		           ws.addCell(new Label(8, 8, bean.getNotDegreeRatio()+"%", wcf1)); 
		           
		           ws.addCell(new Label(0, 9, "3.年龄结构", wcf)); 
		           ws.addCell(new Label(1, 9, "35岁及以下", wcf));  
		           ws.addCell(new Label(3, 9, "36~45岁", wcf)); 
		           ws.addCell(new Label(5, 9, "46~55岁", wcf)); 
		           ws.addCell(new Label(7, 9, "56岁及以上", wcf)); 
		           ws.addCell(new Label(1, 10, "人数", wcf)); 
		           ws.addCell(new Label(2, 10, "比例", wcf)); 
		           ws.addCell(new Label(3, 10, "人数", wcf)); 
		           ws.addCell(new Label(4, 10, "比例", wcf)); 
		           ws.addCell(new Label(5, 10, "人数", wcf)); 
		           ws.addCell(new Label(6, 10, "比例", wcf)); 
		           ws.addCell(new Label(7, 10, "人数", wcf)); 
		           ws.addCell(new Label(8, 10, "比例", wcf)); 
		           
		           ws.addCell(new Label(1, 11, bean.getBelow35Num()+"", wcf1)); 
		           ws.addCell(new Label(2, 11, bean.getBelow35Ratio()+"%", wcf1));  
		           ws.addCell(new Label(3, 11, bean.getIn36To45Num()+"", wcf1));
		           ws.addCell(new Label(4, 11, bean.getIn36To45Ratio()+"%", wcf1)); 
		           ws.addCell(new Label(5, 11, bean.getIn46To55Num()+"", wcf1));  
		           ws.addCell(new Label(6, 11, bean.getIn46To55Ratio()+"%", wcf1));
		           ws.addCell(new Label(7, 11, bean.getAbove56Num()+"", wcf1)); 
		           ws.addCell(new Label(8, 11, bean.getAbove56Ratio()+"%", wcf1)); 
		           
		           ws.addCell(new Label(0, 12, "4.学缘结构", wcf)); 
		           ws.addCell(new Label(1, 12, "本校", wcf));  
		           ws.addCell(new Label(3, 12, "外校（境内）", wcf)); 
		           ws.addCell(new Label(5, 12, "外校（境外）", wcf)); 
		           ws.addCell(new Label(1, 13, "人数", wcf)); 
		           ws.addCell(new Label(2, 13, "比例", wcf)); 
		           ws.addCell(new Label(3, 13, "人数", wcf)); 
		           ws.addCell(new Label(4, 13, "比例", wcf)); 
		           ws.addCell(new Label(5, 13, "人数", wcf)); 
		           ws.addCell(new Label(6, 13, "比例", wcf)); 

		           
		           ws.addCell(new Label(1, 14, bean.getThisSchNum()+"", wcf1)); 
		           ws.addCell(new Label(2, 14, bean.getThisSchRatio()+"%", wcf1));  
		           ws.addCell(new Label(3, 14, bean.getOutSchInNum()+"", wcf1));
		           ws.addCell(new Label(4, 14, bean.getOutSchInRatio()+"%", wcf1)); 
		           ws.addCell(new Label(5, 14, bean.getOutSchOutNum()+"", wcf1));  
		           ws.addCell(new Label(6, 14, bean.getOutSchOutRatio()+"%", wcf1));
		           
		           ws.addCell(new Label(0, 15, "5.双师型", wcf)); 
		           ws.addCell(new Label(1, 15, "双师型教师", wcf));  
		           ws.addCell(new Label(3, 15, "具有行业背景", wcf)); 
		           ws.addCell(new Label(5, 15, "具有工程背景", wcf)); 
		           ws.addCell(new Label(1, 16, "人数", wcf)); 
		           ws.addCell(new Label(2, 16, "比例", wcf)); 
		           ws.addCell(new Label(3, 16, "人数", wcf)); 
		           ws.addCell(new Label(4, 16, "比例", wcf)); 
		           ws.addCell(new Label(5, 16, "人数", wcf)); 
		           ws.addCell(new Label(6, 16, "比例", wcf)); 

		           
		           ws.addCell(new Label(1, 17, bean.getDuTeaNum()+"", wcf1)); 
		           ws.addCell(new Label(2, 17, bean.getDuTeaRatio()+"%", wcf1));  
		           ws.addCell(new Label(3, 17, bean.getIndustryNum()+"", wcf1));
		           ws.addCell(new Label(4, 17, bean.getIndustryRatio()+"%", wcf1)); 
		           ws.addCell(new Label(5, 17, bean.getEngineerNum()+"", wcf1));  
		           ws.addCell(new Label(6, 17, bean.getEngineerRatio()+"%", wcf1));
		           
		           ws.addCell(new Label(0, 18, "6.任职类别", wcf)); 
		           ws.addCell(new Label(1, 18, "专任教师", wcf));  
		           ws.addCell(new Label(3, 18, "教学管理人员", wcf)); 
		           ws.addCell(new Label(5, 18, "学生管理人员", wcf)); 
		           ws.addCell(new Label(7, 18, "教学质量监控人员", wcf));  
		           ws.addCell(new Label(9, 18, "实验技术人员", wcf)); 
		           ws.addCell(new Label(11, 18, "其他人员", wcf)); 
		           ws.addCell(new Label(1, 19, "人数", wcf)); 
		           ws.addCell(new Label(2, 19, "比例", wcf)); 
		           ws.addCell(new Label(3, 19, "人数", wcf)); 
		           ws.addCell(new Label(4, 19, "比例", wcf)); 
		           ws.addCell(new Label(5, 19, "人数", wcf)); 
		           ws.addCell(new Label(6, 19, "比例", wcf)); 
		           ws.addCell(new Label(7, 19, "人数", wcf)); 
		           ws.addCell(new Label(8, 19, "比例", wcf)); 
		           ws.addCell(new Label(9, 19, "人数", wcf)); 
		           ws.addCell(new Label(10, 19, "比例", wcf)); 
		           ws.addCell(new Label(11, 19, "人数", wcf)); 
		           ws.addCell(new Label(12, 19, "比例", wcf)); 

		           
		           ws.addCell(new Label(1, 20, bean.getFullTimeTeaNum()+"", wcf1)); 
		           ws.addCell(new Label(2, 20, bean.getFullTimeRatio()+"%", wcf1));  
		           ws.addCell(new Label(3, 20, bean.getTeaManageNum()+"", wcf1));
		           ws.addCell(new Label(4, 20, bean.getTeaManageRatio()+"%", wcf1)); 
		           ws.addCell(new Label(5, 20, bean.getStuManageNum()+"", wcf1));  
		           ws.addCell(new Label(6, 20, bean.getStuManageRatio()+"%", wcf1));
		           ws.addCell(new Label(7, 20, bean.getTeaMonitorNum()+"", wcf1)); 
		           ws.addCell(new Label(8, 20, bean.getTeaMonitorRatio()+"%", wcf1));  
		           ws.addCell(new Label(9, 20, bean.getExpTeaNum()+"", wcf1));
		           ws.addCell(new Label(10, 20, bean.getExpTeaRatio()+"%", wcf1)); 
		           ws.addCell(new Label(11, 20, bean.getOtherTeaNum()+"", wcf1));  
		           ws.addCell(new Label(12, 20, bean.getOtherTeaRatio()+"%", wcf1));
		           

		           
		           

		           
		           ws.mergeCells(1, 2, 8, 2);
		           ws.mergeCells(0, 3, 0, 5);
		           ws.mergeCells(1, 3, 2, 3);
		           ws.mergeCells(3, 3, 4, 3);
		           ws.mergeCells(5, 3, 6, 3);
		           ws.mergeCells(7, 3, 8, 3);
		           
		           ws.mergeCells(0, 6, 0, 8);
		           ws.mergeCells(1, 6, 2, 6);
		           ws.mergeCells(3, 6, 4, 6);
		           ws.mergeCells(5, 6, 6, 6);
		           ws.mergeCells(7, 6, 8, 6);
		           
		           ws.mergeCells(0, 9, 0, 11);
		           ws.mergeCells(1, 9, 2, 9);
		           ws.mergeCells(3, 9, 4, 9);
		           ws.mergeCells(5, 9, 6, 9);
		           ws.mergeCells(7, 9, 8, 9);
		           
		           ws.mergeCells(0, 12, 0, 14);
		           ws.mergeCells(1, 12, 2, 12);
		           ws.mergeCells(3, 12, 4, 12);
		           ws.mergeCells(5, 12, 6, 12);
		           
		           ws.mergeCells(0, 15, 0, 17);
		           ws.mergeCells(1, 15, 2, 15);
		           ws.mergeCells(3, 15, 4, 15);
		           ws.mergeCells(5, 15, 6, 15);
		           
		           ws.mergeCells(0, 18, 0, 20);
		           ws.mergeCells(1, 18, 2, 18);
		           ws.mergeCells(3, 18, 4, 18);
		           ws.mergeCells(5, 18, 6, 18);
		           ws.mergeCells(7, 18, 8, 18);
		           ws.mergeCells(9, 18, 10, 18);
		           ws.mergeCells(11, 18, 12, 18);
		           		           

		             

		          wwb.write();
		          wwb.close();

		        } catch (IOException e){
		        } catch (RowsExceededException e){
		        } catch (WriteException e){}
		        
		}
		return new ByteArrayInputStream(fos.toByteArray());
	}
	
	public String execute() throws Exception{
		System.out.println("excelName=============" + this.excelName) ;
		return "success" ;
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

}
