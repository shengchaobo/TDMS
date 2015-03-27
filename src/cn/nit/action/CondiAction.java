package cn.nit.action;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
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

import cn.nit.bean.SCondiMornit;
import cn.nit.bean.table2.S22_Bean;
import cn.nit.bean.table2.S28_Bean;
import cn.nit.bean.table2.T21_Bean;
import cn.nit.bean.table2.T22_Bean;
import cn.nit.bean.table2.T231_Bean;
import cn.nit.bean.table2.T232_Bean;
import cn.nit.bean.table2.T241_Bean;
import cn.nit.bean.table2.T242_Bean;
import cn.nit.bean.table2.T292_Bean;
import cn.nit.bean.table4.A411_Bean;
import cn.nit.bean.table4.A412_Bean;
import cn.nit.bean.table5.S512_Bean;
import cn.nit.bean.table6.T611_Bean;
import cn.nit.bean.table6.T612_Bean;
import cn.nit.bean.table6.T613_Bean;
import cn.nit.bean.table6.T614_Bean;
import cn.nit.bean.table6.T631_Bean;
import cn.nit.bean.table6.T632_Bean;
import cn.nit.service.CondiService;
import cn.nit.service.table2.S22_Service;
import cn.nit.service.table2.S28_Service;
import cn.nit.service.table2.T21_Service;
import cn.nit.service.table2.T22_Service;
import cn.nit.service.table2.T231_Service;
import cn.nit.service.table2.T232_Service;
import cn.nit.service.table2.T241_Service;
import cn.nit.service.table2.T242_Service;
import cn.nit.service.table2.T292_Service;
import cn.nit.service.table3.T322_Service;
import cn.nit.service.table4.A411_Service;
import cn.nit.service.table4.A412_Service;
import cn.nit.service.table4.T413_Service;
import cn.nit.service.table5.S512_Service;
import cn.nit.service.table6.T611_Service;
import cn.nit.service.table6.T612_Service;
import cn.nit.service.table6.T613_Service;
import cn.nit.service.table6.T614_Service;
import cn.nit.service.table6.T615_Service;
import cn.nit.service.table6.T631_Service;
import cn.nit.service.table6.T632_Service;
import cn.nit.util.JsonUtil;

public class CondiAction {
	
	private CondiService condi_services = new CondiService();
	private T611_Service T611_services = new T611_Service();
	private T612_Service T612_services = new T612_Service();
	private T613_Service T613_services = new T613_Service();
	private T614_Service T614_services = new T614_Service();
	private T615_Service T615_services = new T615_Service();
	
	private T631_Service T631_services = new T631_Service();
	private T632_Service T632_services = new T632_Service();
	
	private A411_Service A411_services = new A411_Service();
	private A412_Service A412_services = new A412_Service();
	private T413_Service T413_services = new T413_Service();
	
	private T322_Service T322_services = new T322_Service();
	
	private S512_Service S512_services = new S512_Service();
	
	private T21_Service T21_services = new T21_Service();
	private T22_Service T22_services = new T22_Service();
	private T231_Service T231_services = new T231_Service();
	private T232_Service T232_services = new T232_Service();
	private T241_Service T241_services = new T241_Service();
	private T242_Service T242_services = new T242_Service();
	private T292_Service T292_services = new T292_Service();
	private S22_Service S22_services = new S22_Service();
	private S28_Service S28_services = new S28_Service();

	
	/**  哪一年数据  */
	private String selectYear; //删除的id
	
	/**  导出的excelName名 */
	private String excelName ;
	
	/**  前台获数据 */
	private String data ;
		
	
	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	
	//查询出所有
	public void loadCondiInfo() throws Exception{
		
		HttpServletResponse response = ServletActionContext.getResponse() ;	
		
		boolean flag0 = true; 
		SCondiMornit condiBean = new SCondiMornit();
				
		T611_Bean bean611 = T611_services.getYearInfo(this.getSelectYear());
		if(bean611 == null){	
			flag0 = false;
			System.out.println("bean611empty");
		}
		T612_Bean bean612 = T612_services.getYearInfo(this.getSelectYear());
		if(bean612 == null){	
			flag0 = false;
			System.out.println("bean612empty");
		}
		T613_Bean bean613 = T613_services.getYearInfo(this.getSelectYear());
		if(bean613 == null){	
			flag0 = false;
			System.out.println("bean613empty");
		}
		T614_Bean bean614 = T614_services.getYearInfo(this.getSelectYear());
		if(bean614 == null){	
			flag0 = false;
			System.out.println("bean614empty");
		}
		int inNum = T615_services.getInNum(this.getSelectYear());		
		System.out.println("inNum:" + inNum);
		int outNum = T615_services.getOutNum(this.getSelectYear());		
		System.out.println("outNum:" + outNum);
		int minorNum = T615_services.getMinorNum(this.getSelectYear());
		System.out.println("minorNum:" + minorNum);
		
		T631_Bean bean631 = T631_services.getYearInfo(this.getSelectYear(),"全校合计");
		if(bean631 == null){	
			flag0 = false;
			System.out.println("bean631empty");
		}
		
		T632_Bean bean632 = T632_services.getYearInfo(this.getSelectYear(),"全校合计");
		if(bean632 == null){	
			flag0 = false;
			System.out.println("bean632empty");
		}
		
		A411_Bean beanA411 = A411_services.getInfo();
		if(beanA411 == null){	
			flag0 = false;
			System.out.println("beanA411empty");
		}
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		A412_Bean beanA412 = A412_services.getYearInfo(year);
		if(beanA412 == null){	
			flag0 = false;
			System.out.println("beanA412empty");
		}
		
		int outHireTeaNum = T413_services.getOutHireTeaNum();
		System.out.println("outHireTeaNum:" + outHireTeaNum);
		
		int totalFieldNum = T322_services.getTotalFieldNum(this.getSelectYear());
		System.out.println("totalFieldNum:" + totalFieldNum);
		int sumPraCredit = T322_services.getSumPraCredit(this.getSelectYear());
		System.out.println("sumPraCredit:" + sumPraCredit);
		int sumExpCredit = T322_services.getSumExpCredit(this.getSelectYear());
		System.out.println("sumExpCredit:" + sumExpCredit);
		int sumOptionCredit = T322_services.getSumOptionCredit(this.getSelectYear());
		System.out.println("sumOptionCredit:" + sumOptionCredit);
		int sumOutClassCredit = T322_services.getSumOutClassCredit(this.getSelectYear());
		System.out.println("sumOutClassCredit:" + sumOutClassCredit);
		int sumTotalCredit = T322_services.getSumTotalCredit(this.getSelectYear());
		System.out.println("sumTotalCredit:" + sumTotalCredit);
		
		S512_Bean beanS512 = S512_services.getYearInfo(this.getSelectYear(),"总计：");
		if(beanS512 == null){	
			flag0 = false;
			System.out.println("beanS512empty");
		}
		T21_Bean bean21 = T21_services.getYearInfo(this.getSelectYear());
		if(bean21 == null){
			flag0 = false;
			System.out.println("bean21empty");
		}
		T22_Bean bean22 = T22_services.getYearInfo(this.getSelectYear());
		if(bean22 == null){
			flag0 = false;
			System.out.println("bean22empty");
		}
		S22_Bean beanS22 = S22_services.getYearInfo(this.getSelectYear());
		if(beanS22 == null){
			flag0 = false;
			System.out.println("beanS22empty");
		}
		S28_Bean beanS28 = S28_services.getYearInfo(this.getSelectYear());
		if(beanS28 == null){
			flag0 = false;
			System.out.println("beanS28empty");
		}
		T241_Bean bean241 = T241_services.getYearInfo(this.getSelectYear());
		if(bean241 == null){
			flag0 = false;
			System.out.println("bean241empty");
		}
		T242_Bean bean242 = T242_services.getYearInfo(this.getSelectYear());
		if(bean242 == null){
			flag0 = false;
			System.out.println("bean242empty");
		}
		T231_Bean bean231 = T231_services.getYearInfo(this.getSelectYear());
		if(bean231 == null){
			flag0 = false;
			System.out.println("bean231empty");
		}
		T232_Bean bean232 = T232_services.getYearInfo(this.getSelectYear());
		if(bean232 == null){
			flag0 = false;
			System.out.println("bean232empty");
		}
		T292_Bean bean292 = T292_services.getYearInfo(this.getSelectYear());
		if(bean292 == null){
			flag0 = false;
			System.out.println("bean292empty");
		}
				
		//数据不为空相加
		boolean flag = false;
		String json = null;
		if(flag0 == true){
			int UndergraNum = bean611.getUndergraLastYearNum()+bean613.getCoTrainStuLastYearNum();
			condiBean.setUndergraNum(UndergraNum);
			
			int FulltimeStuNum = bean611.getUndergraLastYearNum()+bean613.getCoTrainStuLastYearNum()+bean611.getJuniorLastYearNum()+
			bean612.getFullTimeMasterLastYearNum()+bean612.getFullTimeDoctorLastYearNum()+ bean613.getForeignStuLastYearNum()+
			bean614.getPreppyLastYearNum()+bean614.getAdvStuLastYearNum()+bean614.getAdultLastYearNum();			
			condiBean.setFulltimeStuNum(FulltimeStuNum);
			
			double UndergraRatio = Math.round((double)UndergraNum/FulltimeStuNum * 100) / 100.0;
			condiBean.setUndergraRatio(UndergraRatio);
			
			int totalStuNum = (int)(bean611.getUndergraLastYearNum()+bean613.getCoTrainStuLastYearNum()+bean611.getJuniorLastYearNum()+
			bean612.getFullTimeMasterLastYearNum()*1.5+bean612.getFullTimeDoctorLastYearNum()*2+ bean613.getForeignStuLastYearNum()*3+
			bean614.getPreppyLastYearNum()+bean614.getAdvStuLastYearNum()+bean614.getAdultLastYearNum()+
			bean614.getNightUniLastYearNum()*0.3+bean614.getCorrespdCoLastYearNum()*0.1+1);	
			condiBean.setTotalStuNum(totalStuNum);
			
			condiBean.setInNum(inNum);
			
			condiBean.setOutNum(outNum);
			
			double inOutRatio = Math.round((double)inNum/UndergraNum * 100) / 100.0;
			condiBean.setInOutRatio(inOutRatio);
			
			condiBean.setMinorNum(minorNum);
			
			double minorNumRatio = Math.round((double)minorNum/UndergraNum * 100 ) / 100.0;
			condiBean.setMinorNumRatio(minorNumRatio);
			
			double graduRatio = Math.round((double)(bean631.getThisYearGraduNum()-bean631.getThisYearNotGraduNum())/bean631.getThisYearGraduNum() * 100 ) / 100.0;
			condiBean.setGraduRatio(graduRatio);
			
			double degreeRatio = Math.round((double)bean631.getAwardDegreeNum()/bean631.getThisYearGraduNum() * 100 ) / 100.0;
			condiBean.setDegreeRatio(degreeRatio);
			
			double stuEmployRatio = Math.round((double)bean632.getSumEmployNum()/bean631.getThisYearGraduNum() * 100 ) / 100.0;
			condiBean.setStuEmployRatio(stuEmployRatio);
			
			condiBean.setFullTimeTeachNum(beanA411.getFullTimeTeaNum());
			
			double graduDegreeRatio = Math.round((double)(beanA412.getDoctorNum()+beanA412.getMasterNum())/beanA411.getFullTimeTeaNum() * 100 ) / 100.0;
			condiBean.setGraduDegreeRatio(graduDegreeRatio);
			
			double adminLevelRatio = Math.round((double)(beanA412.getSeniorNum()+beanA412.getSubSenior())/beanA411.getFullTimeTeaNum() * 100 ) / 100.0;
			condiBean.setAdminLevelRatio(adminLevelRatio);
			
			condiBean.setOutHireTeaNum(outHireTeaNum);
			
			int teacherNum = (int)(beanA411.getFullTimeTeaNum() + outHireTeaNum*0.5 + 1);
			condiBean.setTeacherNum(teacherNum);
			
			double stuTeaRatio = Math.round((double)totalStuNum/teacherNum * 100 ) / 100.0;
			condiBean.setStuTeaRatio(stuTeaRatio);
			
			condiBean.setTotalFieldNum(totalFieldNum);
			
			int totalScoreNum = beanS512.getSumCS();
			condiBean.setTotalScoreNum(totalScoreNum);
			
			double praRatio = Math.round((double)(sumPraCredit + sumExpCredit)/sumTotalCredit * 100 ) / 100.0;
			condiBean.setPraRatio(praRatio);
			
			double optionRatio = Math.round((double)(sumOptionCredit + sumOutClassCredit)/sumTotalCredit * 100 ) / 100.0;
			condiBean.setOptionRatio(optionRatio);
			
			double profNumRatio = Math.round((double)(beanS512.getProfessor() + beanS512.getViceProfessor()) / (beanA412.getSeniorNum()+beanA412.getSubSenior())* 100 ) / 100.0;
			condiBean.setProfNumRatio(profNumRatio);
			
			double profCourseRatio = Math.round((double)(beanS512.getCSProfNum()+beanS512.getCSViceProfNum()) / totalScoreNum* 100 ) / 100.0;
			condiBean.setProfCourseRatio(profCourseRatio);
			
			double areaPerStu = Math.round(bean21.getSumArea()/FulltimeStuNum* 100 ) / 100.0;
			condiBean.setAreaPerStu(areaPerStu);
			
			double housePerStu = Math.round((beanS22.getSumTeaArea() + beanS22.getSumAdminArea()) / FulltimeStuNum * 100 ) / 100.0;
			condiBean.setHousePerStu(housePerStu);
			
			double labPerStu = Math.round(beanS22.getLabArea() / FulltimeStuNum * 100 ) / 100.0;
			condiBean.setLabPerStu(labPerStu);
			
			double dormPerStu = Math.round(bean22.getStuDormiArea()/ FulltimeStuNum * 100 ) / 100.0;
			condiBean.setDormPerStu(dormPerStu);
			
			double equPerStu = Math.round(beanS28.getPlantAsset()/totalStuNum * 100 ) / 100.0;
			condiBean.setEquPerStu(equPerStu);
			
			double newEquRatio = Math.round(beanS28.getNewAddAsset()/(beanS28.getPlantAsset()-beanS28.getNewAddAsset()) * 100 ) / 100.0;
			condiBean.setNewEquRatio(newEquRatio);
			
			double booksPerStu = Math.round((double)bean241.getPaperBookNum()/totalStuNum * 100 ) / 100.0 ;
			condiBean.setBooksPerStu(booksPerStu);
			
			double inbooksPerStu = Math.round((double)bean242.getAddPaperBookNum()/totalStuNum * 100 ) / 100.0;
			condiBean.setInbooksPerStu(inbooksPerStu);
			
			double comPerStu = Math.round((double)bean232.getComputerNum()/(FulltimeStuNum*100) * 100 ) / 100.0;
			condiBean.setComPerStu(comPerStu);
			
			double mediaPerStu = Math.round((double)bean231.getClassSeatNum()/(FulltimeStuNum*100) * 100 ) / 100.0;
			condiBean.setMediaPerStu(mediaPerStu);
			
			double teachFeePerStu = Math.round(bean292.getDayTeaExp()/UndergraNum * 100 ) / 100.0;
		    condiBean.setTeachFeePerStu(teachFeePerStu);
			
			double labFeePerStu = Math.round(bean292.getExpTeaExp()/UndergraNum * 100 ) / 100.0;
		    condiBean.setLabFeePerStu(labFeePerStu);
			
			double practiceFeePerStu = Math.round(bean292.getPraTeaExp()/UndergraNum * 100 ) / 100.0;
		    condiBean.setPracticeFeePerStu(practiceFeePerStu);
						
			flag = condi_services.save(condiBean,this.getSelectYear());
			//转化为前台所需要json
			condiBean.setTime(null);
			json = JsonUtil.beanToJson(condiBean);
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

		System.out.println(this.getSelectYear());
		SCondiMornit condiBean = condi_services.getYearInfo(this.getSelectYear());
		
	    ByteArrayOutputStream fos = null;
		
		if(condiBean == null){
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
		           ws.mergeCells(0, 0, 1, 0);
		           
		           ws.addCell(new Label(0, 2, "指标分类", wcf)); 
		           ws.addCell(new Label(1, 2, "指标名称", wcf));
		           ws.addCell(new Label(2, 2, "数据", wcf)); 
		           ws.addCell(new Label(0, 3, "学生人数", wcf));
		           ws.addCell(new Label(1, 3, "本科在校生数（人）", wcf));
		           ws.addCell(new Label(1, 4, "全日制在校生数（人）", wcf));
		           ws.addCell(new Label(1, 5, "本科生占全日制在校生总数的比例", wcf));
		           ws.addCell(new Label(1, 6, "折合在校生数（人）", wcf));
		           ws.addCell(new Label(1, 7, "转专业:转入人数（人）", wcf));
		           ws.addCell(new Label(1, 8, "转专业:转出人数（人）", wcf));
		           ws.addCell(new Label(1, 9, "转专业学生占本科在校生比例", wcf));
		           ws.addCell(new Label(1, 10, "辅修专业在读学生数（人）", wcf));
		           ws.addCell(new Label(1, 11, "辅修专业在读学生占本科生在校生比例", wcf));

		           
		           ws.addCell(new Label(0, 12, "学生毕业", wcf));  
		           ws.addCell(new Label(1, 12, "应届本科生毕业率", wcf));  
		           ws.addCell(new Label(1, 13, "应届本科生学位授予率", wcf)); 
		           ws.addCell(new Label(1, 14, "专任教师总数（人）", wcf));  
		           
		           ws.addCell(new Label(0, 15, "教师人数", wcf)); 
		           ws.addCell(new Label(1, 15, "专任教师总数（人）", wcf)); 
		           ws.addCell(new Label(1, 16, "具有研究生学位教师占专任教师的比例", wcf)); 
		           ws.addCell(new Label(1, 17, "具有高级职务教师占专任教师的比例", wcf)); 
		           ws.addCell(new Label(1, 18, "外聘教师数（人）", wcf)); 
		           ws.addCell(new Label(1, 19, "教师总数（人）", wcf)); 
		           ws.addCell(new Label(1, 20, "生师比", wcf)); 
		           
		           ws.addCell(new Label(0, 21, "课程情况", wcf)); 
		           ws.addCell(new Label(1, 21, "当年本科招生专业总数（个）", wcf));
		           ws.addCell(new Label(1, 22, "全校开设课程总门数（门）", wcf)); 
		           ws.addCell(new Label(1, 23, "实践教学学分占总学分比例", wcf)); 
		           ws.addCell(new Label(1, 24, "选修课学分占总学分比例", wcf)); 
		           ws.addCell(new Label(1, 25, "主讲本科课程的教授（含副教授）占教授（含副教授）总数的比例", wcf)); 
		           ws.addCell(new Label(1, 26, "教授（含副教授）授本科课程占课程总门次数的比例", wcf)); 
		           
		           ws.addCell(new Label(0, 27, "教学条件", wcf)); 
		           ws.addCell(new Label(1, 27, "生均占地面积（平方米）", wcf)); 
		           ws.addCell(new Label(1, 28, "生均教学行政用房面积（平方米）", wcf));
		           ws.addCell(new Label(1, 29, "其中生均实验室面积（平方米)", wcf));
		           ws.addCell(new Label(1, 30, "其中生均宿舍面积（平方米）", wcf));
		           ws.addCell(new Label(1, 31, "生均教学科研仪器设备值（元/生）", wcf));
		           ws.addCell(new Label(1, 32, "新增教学科研仪器设备所占比例", wcf));
		           ws.addCell(new Label(1, 33, "生均图书（册/生）", wcf));
		           ws.addCell(new Label(1, 34, "生均年进书量（册/生）", wcf));
		           ws.addCell(new Label(1, 35, "百名学生配教学计算机台数(台)", wcf));
		           ws.addCell(new Label(1, 36, "百名学生配多媒体教室和语音实验室座位数(个)", wcf));
		           
		           ws.addCell(new Label(0, 37, "教学经费", wcf));
		           ws.addCell(new Label(1, 37, "生均本科教学日常运行支出（元/生）", wcf));
		           ws.addCell(new Label(1, 38, "生均本科实验经费（元/生）", wcf));
		           ws.addCell(new Label(1, 39, "生均本科实习经费（元/生）", wcf));
		           
		           ws.mergeCells(0, 3, 0, 11);
		           ws.mergeCells(0, 12, 0, 15);
		           ws.mergeCells(0, 16, 0, 20);
		           ws.mergeCells(0, 21, 0, 26);
		           ws.mergeCells(0, 27, 0, 36);
		           ws.mergeCells(0, 37, 0, 39);
		           

		           ws.addCell(new Label(2, 3, condiBean.getUndergraNum().toString(), wcf1));
		           ws.addCell(new Label(2, 4, condiBean.getFulltimeStuNum().toString(), wcf1));
		           ws.addCell(new Label(2, 5, condiBean.getUndergraRatio().toString(), wcf1));
		           ws.addCell(new Label(2, 6, condiBean.getTotalStuNum().toString(), wcf1));
		           ws.addCell(new Label(2, 7, condiBean.getInNum().toString(), wcf1));
		           ws.addCell(new Label(2, 8, condiBean.getOutNum().toString(), wcf1));
		           ws.addCell(new Label(2, 9, condiBean.getInOutRatio().toString(), wcf1));
		           ws.addCell(new Label(2, 10, condiBean.getMinorNum().toString(), wcf1));
		           ws.addCell(new Label(2, 11, condiBean.getMinorNumRatio().toString(), wcf1));
		           		           		           
		           ws.addCell(new Label(2, 12, condiBean.getGraduRatio().toString(), wcf1));  
		           ws.addCell(new Label(2, 13, condiBean.getDegreeRatio().toString(), wcf1)); 
		           ws.addCell(new Label(2, 14, condiBean.getStuEmployRatio().toString(), wcf1));  
		           
		           ws.addCell(new Label(2, 15, condiBean.getFullTimeTeachNum().toString(), wcf1)); 
		           ws.addCell(new Label(2, 16, condiBean.getGraduDegreeRatio().toString(), wcf1)); 
		           ws.addCell(new Label(2, 17, condiBean.getAdminLevelRatio().toString(), wcf1)); 
		           ws.addCell(new Label(2, 18, condiBean.getOutHireTeaNum().toString(), wcf1)); 
		           ws.addCell(new Label(2, 19, condiBean.getTeacherNum().toString(), wcf1)); 
		           ws.addCell(new Label(2, 20, condiBean.getStuTeaRatio().toString(), wcf1)); 
		           
		           ws.addCell(new Label(2, 21, condiBean.getTotalFieldNum().toString(), wcf1));
		           ws.addCell(new Label(2, 22, condiBean.getTotalScoreNum().toString(), wcf1)); 
		           ws.addCell(new Label(2, 23, condiBean.getPraRatio().toString(), wcf1)); 
		           ws.addCell(new Label(2, 24, condiBean.getOptionRatio().toString(), wcf1)); 
		           ws.addCell(new Label(2, 25, condiBean.getProfNumRatio().toString(), wcf1)); 
		           ws.addCell(new Label(2, 26, condiBean.getProfCourseRatio().toString(), wcf1)); 
		           
		           ws.addCell(new Label(2, 27, condiBean.getAreaPerStu().toString(), wcf1)); 
		           ws.addCell(new Label(2, 28, condiBean.getHousePerStu().toString(), wcf1));
		           ws.addCell(new Label(2, 29, condiBean.getLabPerStu().toString(), wcf1));
		           ws.addCell(new Label(2, 30, condiBean.getDormPerStu().toString(), wcf1));
		           ws.addCell(new Label(2, 31, condiBean.getEquPerStu().toString(), wcf1));
		           ws.addCell(new Label(2, 32, condiBean.getNewEquRatio().toString(), wcf1));
		           ws.addCell(new Label(2, 33, condiBean.getBooksPerStu().toString(), wcf1));
		           ws.addCell(new Label(2, 34, condiBean.getInbooksPerStu().toString(), wcf1));
		           ws.addCell(new Label(2, 35, condiBean.getComPerStu().toString(), wcf1));
		           ws.addCell(new Label(2, 36, condiBean.getMediaPerStu().toString(), wcf1));
		           
		           ws.addCell(new Label(2, 37, condiBean.getTeachFeePerStu().toString(), wcf1));
		           ws.addCell(new Label(2, 38, condiBean.getLabFeePerStu().toString(), wcf1));
		           ws.addCell(new Label(2, 39, condiBean.getPracticeFeePerStu().toString(), wcf1));
		             

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


}