package cn.nit.action.upload;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts2.ServletActionContext;

import cn.nit.action.table1.T11_Action;
import cn.nit.action.table1.T12_Action;
import cn.nit.action.table1.T13_Action;
import cn.nit.action.table1.T14_Action;
import cn.nit.action.table1.T151_Action;
import cn.nit.action.table1.T152_Action;
import cn.nit.action.table1.T16_Action;
import cn.nit.action.table1.T172_Action;
import cn.nit.action.table1.T17_Action;
import cn.nit.action.table1.T181_Action;
import cn.nit.action.table1.T182_Action;
import cn.nit.action.table1.T183_Action;
import cn.nit.action.table1.T19_Action;
import cn.nit.action.table2.T2101_Action;
import cn.nit.action.table2.T2102_Action;
import cn.nit.action.table2.T2103_Action;
import cn.nit.action.table2.T21_Action;
import cn.nit.action.table3.S31_Action;
import cn.nit.action.table3.T311_Action;
import cn.nit.action.table3.T312_Action;
import cn.nit.action.table3.T313_Action;
import cn.nit.action.table3.T321_Action;
import cn.nit.action.table3.T322_Action;
import cn.nit.action.table3.T33_Action;
import cn.nit.action.table4.T410_Action;
import cn.nit.action.table4.T411_Action;
import cn.nit.action.table4.T412_Action;
import cn.nit.action.table4.T413_Action;
import cn.nit.action.table4.T42_Action;
import cn.nit.action.table4.T435_Action;
import cn.nit.action.table4.T441_Action;
import cn.nit.action.table4.T442_Action;
import cn.nit.action.table4.T443_Action;
import cn.nit.action.table4.T444_Action;
import cn.nit.action.table4.T451_Action;
import cn.nit.action.table4.T452_Action;
import cn.nit.action.table4.T453_Action;
import cn.nit.action.table4.T461_Action;
import cn.nit.action.table4.T47_Action;
import cn.nit.action.table4.T48_Action;
import cn.nit.action.table4.T49_Action;
import cn.nit.action.table4.T4_11_Action;
import cn.nit.action.table6.T611_Action;
import cn.nit.action.table6.T612_Action;
import cn.nit.action.table6.T613_Action;
import cn.nit.action.table6.T614_Action;
import cn.nit.action.table6.T615_Action;
import cn.nit.action.table6.T616_Action;
import cn.nit.action.table6.T617_Action;
import cn.nit.action.table6.T621_Action;
import cn.nit.action.table6.T622_Action;
import cn.nit.action.table6.T623_Action;
import cn.nit.action.table6.T624_Action;
import cn.nit.action.table6.T631_Action;
import cn.nit.action.table6.T632_Action;
import cn.nit.action.table6.T641_Action;
import cn.nit.action.table6.T651_Action;
import cn.nit.action.table6.T652_Action;
import cn.nit.action.table6.T653_Action;
import cn.nit.action.table6.T654_Action;
import cn.nit.action.table6.T655_Action;
import cn.nit.action.table6.T656_Action;
import cn.nit.action.table6.T657_Action;
import cn.nit.action.table6.T658_Action;
import cn.nit.action.table6.T659_Action;
import cn.nit.action.table6.T66_Action;
import cn.nit.action.table6.T671_Action;
import cn.nit.action.table6.T672_Action;
import cn.nit.bean.UserinfoBean;
import cn.nit.bean.table3.T321_Bean;
import cn.nit.bean.table4.T431_Bean;
import cn.nit.bean.table4.T461_Bean;
import cn.nit.constants.Constants;
import cn.nit.dao.table3.T321_DAO;
import cn.nit.dao.table4.T461_Dao;
import cn.nit.action.table2.T22_Action;
import cn.nit.action.table2.T231_Action;
import cn.nit.action.table2.T232_Action;
import cn.nit.action.table2.T241_Action;
import cn.nit.action.table2.T242_Action;
import cn.nit.action.table2.T251_Action;
import cn.nit.action.table2.T252_Action;
import cn.nit.action.table2.T26_Action;
import cn.nit.action.table2.T27_Action;
import cn.nit.action.table2.T281_Action;
import cn.nit.action.table2.T282_Action;
import cn.nit.action.table2.T283_Action;
import cn.nit.action.table2.T284_Action;
import cn.nit.action.table2.T285_Action;
import cn.nit.action.table2.T291_Action;
import cn.nit.action.table2.T292_Action;
import cn.nit.action.table2.T293_Action;
import cn.nit.action.table2.T294_Action;
import cn.nit.action.table4.T441_Action;
import cn.nit.action.table5.T511_Action;
import cn.nit.action.table5.T512_Action;
import cn.nit.action.table5.T513Action;
import cn.nit.action.table5.T521Action;
import cn.nit.action.table5.T522Action;
import cn.nit.action.table5.T531Action;
import cn.nit.action.table5.T532Action;
import cn.nit.action.table5.T533Action;
import cn.nit.action.table5.T534Action;
import cn.nit.action.table5.T54_Action;
import cn.nit.action.table5.T551Action;
import cn.nit.action.table5.T552_Action;
import cn.nit.action.table5.T553_Action;
import cn.nit.action.table7.T711_Action;
import cn.nit.action.table7.T712_Action;
import cn.nit.action.table7.T721_Action;
import cn.nit.action.table7.T722_Action;
import cn.nit.action.table7.T731_Action;
import cn.nit.action.table7.T732_Action;
import cn.nit.action.table7.T733_Action;
import cn.nit.action.table7.T734_Action;
import cn.nit.action.table7.T735_Action;
import cn.nit.action.table7.T741_Action;
import cn.nit.action.table7.T742_Action;
import cn.nit.action.table7.T743_Action;
import cn.nit.action.table7.T744_Action;
import cn.nit.action.table7.T745_Action;
import cn.nit.education.download.J732_Excel;
import cn.nit.service.table4.T411_Service;
import cn.nit.service.table4.T461_Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.ZipFileUtil;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 处理多个附件下载
 * @author Luxh
 */
public class AllFileDownloadAction extends ActionSupport {

	private static final long serialVersionUID = 2743077909387361587L;
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse() ;	
	
	
	private FileInputStream inputStream;
	
	//接收压缩文件的路径
	private String filePath; 
	
	//最终压缩后的zip文件的路径，传递给通用的下载Action
	private String fileName;
	
	//选择导出的年
	private String selectYear;

	
	/**
	 * 下载多个附件
	 * 实现：将多个附近压缩成zip包,然后再下载zip包
	 */
	public InputStream getDownloadAllFile() {
		
		SimpleDateFormat sfm = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");  
		String filename = "全校所有表";
		
		//使用当前时间生成文件名称
		String name = filename + "_" + sfm.format(new Date()).toString();
		
		//压缩后的zip文件存放路径
		this.setFileName(name + ".zip");		
		
	    // 把上传的文件放到指定的路径下     	  
	    String path = ServletActionContext.getServletContext().getRealPath(filePath);
	    
	    File dirFile = new File(path);
	    
	    if(dirFile.exists()){
	    	File[] files = dirFile.listFiles();	  
    	    ZipFileUtil.compressFiles2Zip(files,fileName);
	    }
	    
	    File compressFile = new File(name + ".zip");
	    try {
			inputStream = new FileInputStream(compressFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		return inputStream;
	}
	
    @Override
    public String execute() {
     // TODO Auto-generated method stub
    	String path = ServletActionContext.getServletContext().getRealPath(filePath);
    	
		String dir = path + "\\";	
		
		File dirFile = new File(path);		
        if(!dirFile.exists()){
        	dirFile.mkdirs();
        }
        
    	String errorMessasge = preDownloadFile(dir,this.getSelectYear());
    	
	    if (errorMessasge != null) {
	    	request.setAttribute("error", errorMessasge);
	    	System.out.println(errorMessasge);
	    	return "error";	    	 
	    } else {
	    	return "success";
	    }	    	   
    }
	
	
	private String preDownloadFile(String filePath,String year) {
		
		request.getSession().setAttribute("allYear", year) ;
		
//		//1字头
		if(!export11(filePath)){
			return "export11 has a error!";
			}
		if(!export12(filePath)){
			return "export12 has a error!";
			}
		if(!export13(filePath)){
			return "export13 has a error!";
			}
		if(!export14(filePath)){
			return "export14 has a error!";
			}
		if(!export151(filePath)){
			return "export151 has a error!";
			}
		if(!export152(filePath)){
			return "export152 has a error!";
			}
		if(!export16(filePath)){
			return "export16 has a error!";
			}
		if(!export17(filePath)){
			return "export17 has a error!";
			}
		if(!export172(filePath)){
			return "export172 has a error!";
			}
		if(!export181(filePath)){
			return "export181 has a error!";
			}
		if(!export182(filePath)){
			return "export182 has a error!";
			}
		if(!export183(filePath)){
			return "export183 has a error!";
			}
		if(!export19(filePath)){
			return "export19 has a error!";
			}
		
		
		//2字头
		if(!export21(filePath)){
			return "export21 has a error!";
		}
		if(!export2101(filePath)){
			return "export2101 has a error!";
		}
		if(!export2102(filePath)){
			return "export2102 has a error!";
		}
		if(!export2103(filePath)){
			return "export2103 has a error!";
		}
		if(!export22(filePath)){
			return "export22 has a error!";
		}
		if(!export231(filePath)){
			return "export231 has a error!";
		}
		
		if(!export232(filePath)){
			return "export232 has a error!";
		}
		
		if(!export241(filePath)){
			return "export241 has a error!";
		}
		
		if(!export242(filePath)){
			return "export242 has a error!";
		}
		if(!export251(filePath)){
			return "export251 has a error!";
		}
		
		if(!export252(filePath)){
			return "export252 has a error!";
		}
		if(!export26(filePath)){
			return "export26 has a error!";
		}
		if(!export27(filePath)){
			return "export27 has a error!";
		}
		if(!export281(filePath)){
			return "export281 has a error!";
		}
		if(!export282(filePath)){
			return "export282 has a error!";
		}
		if(!export283(filePath)){
			return "export283 has a error!";
		}
		if(!export284(filePath)){
			return "export284 has a error!";
		}
		if(!export285(filePath)){
			return "export285 has a error!";
		}
		if(!export291(filePath)){
			return "export291 has a error!";
		}
		if(!export292(filePath)){
			return "export292 has a error!";
		}
		if(!export293(filePath)){
			return "export293 has a error!";
		}
		if(!export294(filePath)){
			return "export294 has a error!";
		}
		//3字头

		
		if(!exportT311(filePath)){
			return "exportT311 has a error!";
		}
		
		if(!exportT312(filePath)){
			return "exportT312 has a error!";
		}
		
		if(!exportT313(filePath)){
			return "exportT313 has a error!";
		}
		
		if(!exportT321(filePath)){
			return "exportT321 has a error!";
		}
		
		if(!exportT322(filePath)){
			return "exportT322 has a error!";
		}
		
		if(!exportT33(filePath)){
			return "exportT33 has a error!";
		}

		
		
		//4字头
		if(!export441(filePath)){
			return "export441 has a error!";
		}	
		
		if(!export411(filePath)){
			return "exportT411 has a error!";
		}
		
		if(!export412(filePath)){
			return "exportT412 has a error!";
		}
		
		
		if(!export413(filePath)){
			return "exportT413 has a error!";
		}
		
		if(!export42(filePath)){
			return "exportT42 has a error!";
		}
		
		if(!export431(filePath,1,"表4-3-1实验技术人员（设备处）")){
			return "exportT431 has a error!";
		}
		
		if(!export431(filePath,2,"表4-3-2教学管理人员（教务处）")){
			return "exportT432 has a error!";
		}
		
		if(!export431(filePath,3,"表4-3-3学生管理人员（学工处）")){
			return "exportT433 has a error!";
		}
		
		if(!export431(filePath,4,"表4-3-4教学质量监控人员（评估中心）")){
			return "exportT434 has a error!";
		}
		
		if(!export435(filePath)){
			return "exportT435 has a error!";
		}
		
		if(!export442(filePath)){
			return "exportT442 has a error!";
		}
		if(!export443(filePath)){
			return "exportT443 has a error!";
		}
		
		if(!export444(filePath)){
			return "exportT444 has a error!";
		}
		
		if(!export451(filePath)){
			return "exportT451 has a error!";
		}
		
		if(!export452(filePath)){
			return "exportT452 has a error!";
		}
		if(!export453(filePath)){
			return "exportT453 has a error!";
		}
		if(!export461("1",filePath,"表4-6-1教师所获荣誉-教学名师（人事处）")){
			return "exportT461 has a error!";
		}
		if(!export461("2",filePath,"表4-6-2教师所获荣誉-师德先进个人、研究与创作奖（宣传部）")){
			return "exportT462 has a error!";
		}
		if(!export461("3",filePath,"表4-6-3教师所获荣誉-学生思政队伍工作成果奖（学工处）")){
			return "exportT463 has a error!";
		}
		if(!export461("4",filePath,"表4-6-4教师所获荣誉-优秀教师（党院办）")){
			return "exportT464 has a error!";
		}
		if(!export461("5",filePath,"表4-6-5教师所获荣誉-教学活动月获奖（教务处）")){
			return "exportT465 has a error!";
		}
		if(!export461("6",filePath,"表4-6-6教师所获荣誉-其他（教学单位-校数据采集工作组）")){
			return "exportT466 has a error!";
		}
		if(!export47(filePath)){
			return "exportT47 has a error!";
		}
		if(!export48(filePath)){
			return "exportT48 has a error!";
		}
		if(!export49(filePath)){
			return "exportT49 has a error!";
		}
		if(!export410(filePath)){
			return "exportT410 has a error!";
		}
		if(!export4_11(filePath)){
			return "exportT4-11 has a error!";
		}
		//6字头
		if(!export611(filePath)){
			return "exportT611 has a error!";
		}
		if(!export612(filePath)){
			return "exportT612 has a error!";
		}
		if(!export613(filePath)){
			return "exportT613 has a error!";
		}
		if(!export614(filePath)){
			return "exportT614 has a error!";
		}
		if(!export615(filePath)){
			return "exportT615 has a error!";
		}
		if(!export616(filePath)){
			return "exportT616 has a error!";
		}
		if(!export617(filePath)){
			return "exportT617 has a error!";
		}
		if(!export621(filePath)){
			return "exportT621 has a error!";
		}
		
		if(!export622(filePath)){
			return "exportT622 has a error!";
		}
		if(!export623(filePath)){
			return "exportT623 has a error!";
		}
		if(!export624(filePath)){
			return "exportT624 has a error!";
		}
		if(!export631(filePath)){
			return "exportT631 has a error!";
		}
		if(!export631(filePath)){
			return "exportT631 has a error!";
		}
		if(!export632(filePath)){
			return "exportT632 has a error!";
		}
		if(!export641(filePath)){
			return "exportT641 has a error!";
		}
		if(!export651(filePath)){
			return "exportT651 has a error!";
		}
		if(!export652(filePath)){
			return "exportT652 has a error!";
		}
		if(!export653(filePath)){
			return "exportT653 has a error!";
		}
		if(!export654(filePath)){
			return "exportT654 has a error!";
		}
		if(!export655(filePath)){
			return "exportT655 has a error!";
		}
		if(!export656(filePath)){
			return "exportT656 has a error!";
		}
		if(!export657(filePath)){
			return "exportT657 has a error!";
		}
		if(!export658(filePath)){
			return "exportT658 has a error!";
		}
		if(!export659(filePath)){
			return "exportT659 has a error!";
		}
		if(!export66(filePath)){
			return "exportT66 has a error!";
		}
		if(!export671(filePath)){
			return "exportT671 has a error!";
		}
		if(!export672(filePath)){
			return "exportT672 has a error!";
		}
		//5字头
		if(!export511(filePath)){
			return "export511 has a error!";
		}
		if(!export512(filePath)){
			return "export512 has a error!";
		}
		if(!export511(filePath)){
			return "export21 has a error!";
		}
		if(!export513(filePath)){
			return "export513 has a error!";
		}
		if(!export521(filePath)){
			return "export521 has a error!";
		}
		if(!export522(filePath)){
			return "export522 has a error!";
		}
		if(!export531(filePath)){
			return "export531 has a error!";
		}
		if(!export532(filePath)){
			return "export532 has a error!";
		}
		if(!export533(filePath)){
			return "export533 has a error!";
		}
		if(!export534(filePath)){
			return "export534 has a error!";
		}
		if(!export54(filePath)){
			return "export54 has a error!";
		}
		if(!export551(filePath)){
			return "export551 has a error!";
		}
		if(!export552(filePath)){
			return "export552 has a error!";
		}
		if(!export553(filePath)){
			return "export553 has a error!";
		}
		
		//7字头
		if(!export711(filePath)){
			return "export1W has a error!";
		}
		if(!export712(filePath)){
			return "export712 has a error!";
		}
		if(!export721(filePath)){
			return "export721 has a error!";
		}
		if(!export722(filePath)){
			return "export722 has a error!";
		}
		if(!export731(filePath)){
			return "export731 has a error!";
		}
		if(!export732(filePath)){
			return "export732 has a error!";
		}
		if(!export733(filePath)){
			return "export553 has a error!";
		}
		if(!export734(filePath)){
			return "export734 has a error!";
		}
		if(!export735(filePath)){
			return "export735 has a error!";
		}
		if(!export741(filePath)){
			return "export741 has a error!";
		}
		if(!export742(filePath)){
			return "export742 has a error!";
		}
		if(!export743(filePath)){
			return "export743 has a error!";
		}
		if(!export744(filePath)){
			return "export744 has a error!";
		}
		if(!export745(filePath)){
			return "export745 has a error!";
		}
		request.getSession().removeAttribute("allYear");
		return null;
	}
	//6字头

	private boolean export611(String filePath2) {
		
		try {
			
			File file = new File(filePath2,"表6-1-1本专科学生数量基本情况（教务处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T611_Action t611 = new T611_Action();
			InputStream is = t611.getInputStream();
			if(is == null){
				System.out.println("该年数据为空");
				return false;
			}else{
			    int ch = 0;    
			    while((ch=is.read()) != -1){  
			    	fileOutputStream.write(ch);  
			    }
			}

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export612(String filePath2) {
		
		try {
			
			File file = new File(filePath2,"表6-1-2研究生数量基本情况（研究生院）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T612_Action t612 = new T612_Action();
			InputStream is = t612.getInputStream();
			if(is == null){
				System.out.println("该年数据为空");
				return false;
			}else{
			    int ch = 0;    
			    while((ch=is.read()) != -1){  
			    	fileOutputStream.write(ch);  
			    }
			}

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export613(String filePath2) {
		
		try {
			
			File file = new File(filePath2,"表6-1-3留学生数量基本情况（国际交流与合作处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T613_Action t613 = new T613_Action();
			InputStream is = t613.getInputStream();
			if(is == null){
				System.out.println("该年数据为空");
				return false;
			}else{
			    int ch = 0;    
			    while((ch=is.read()) != -1){  
			    	fileOutputStream.write(ch);  
			    }
			}

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export614(String filePath2) {
		
		try {
			
			File file = new File(filePath2,"表6-1-4继续教育学生数量基本情况（继续教育学院）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T614_Action t614 = new T614_Action();
			InputStream is = t614.getInputStream();
			if(is == null){
				System.out.println("该年数据为空");
				return false;
			}else{
			    int ch = 0;    
			    while((ch=is.read()) != -1){  
			    	fileOutputStream.write(ch);  
			    }
			}

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export615(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表6-1-5普通本科分专业（大类）学生数（教务处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T615_Action t615 = new T615_Action();
			InputStream is = t615.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	private boolean export616(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表6-1-6国外及港澳台学生情况（国际交流与合作处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T616_Action t616 = new T616_Action();
			InputStream is = t616.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export617(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表6-1-7专科在校生信息补充表（教务处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T617_Action t617 = new T617_Action();
			InputStream is = t617.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export621(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表6-2-1近一届本科生分专业招生录取情况（招就处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T621_Action t621 = new T621_Action();
			InputStream is = t621.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }
	        //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}

	//1字头
	
	private boolean export11(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表1-1学校基本信息（党院办）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T11_Action t11 = new T11_Action();
			InputStream is = t11.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	

	private boolean export622(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表6-2-2近一届文、理科本科生录取标准及人数（招就处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T622_Action t622 = new T622_Action();
			InputStream is = t622.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export623(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表6-2-3近一届艺术类本科生录取标准及人数（招就处） .xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T623_Action t623 = new T623_Action();
			InputStream is = t623.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}

	private boolean export624(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表6-2-4专科招生信息补充表（招就处） .xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T624_Action t624 = new T624_Action();
			InputStream is = t624.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export631(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表6-3-1分专业应届本科毕业生毕业情况（教务处） .xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T631_Action t631 = new T631_Action();
			InputStream is = t631.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export632(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表6-3-2分专业应届本科毕业生就业情况（招就处） .xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T632_Action t632 = new T632_Action();
			InputStream is = t632.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	private boolean export641(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表6-4-1本科生奖贷补（学工处） .xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T641_Action t641 = new T641_Action();
			InputStream is = t641.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	private boolean export651(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表6-5-1学习成果—本科生竞赛获奖情况（教学单位-团委） .xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T651_Action t651 = new T651_Action();
			InputStream is = t651.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export652(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表6-5-2学习成果——学生发表论文（教学单位-团委） .xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T652_Action t652 = new T652_Action();
			InputStream is = t652.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export653(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表6-5-3学习成果——学生发表作品（教学单位-团委） .xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T653_Action t653 = new T653_Action();
			InputStream is = t653.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export654(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表6-5-4学习成果——学生获准专利软件著作权（教学单位-团委） .xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T654_Action t654 = new T654_Action();
			InputStream is = t654.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export655(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表6-5-5学习成果-英语四六级、省计算机等级考试（教务处） .xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T655_Action t655 = new T655_Action();
			InputStream is = t655.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	private boolean export656(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表6-5-6学习成果-全国计算机等级考试（信息工程学院） .xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T656_Action t656 = new T656_Action();
			InputStream is = t656.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	private boolean export657(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表6-5-7学习成果-体质合格、达标率（体育教学部） .xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T657_Action t657 = new T657_Action();
			InputStream is = t657.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	private boolean export658(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表6-5-8学习成果—参加国际会议（教学单位-国际交流与合作处） .xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T658_Action t658 = new T658_Action();
			InputStream is = t658.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	private boolean export659(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表6-5-9本科生交流情况（教学单位-国际交流与合作处） .xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T659_Action t659 = new T659_Action();
			InputStream is = t659.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export66(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表6-6 学生社团（团委） .xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T66_Action t66 = new T66_Action();
			InputStream is = t66.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export671(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表6-7-1辅修情况汇总表（教务处） .xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T671_Action t671 = new T671_Action();
			InputStream is = t671.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export672(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表6-7-2双学位情况汇总表（教务处） .xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T672_Action t672 = new T672_Action();
			InputStream is = t672.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
//	private boolean export441(String filePath2) {
//		
//		try {			
//			File file = new File(filePath2,"表4-4-1专业带头人（教学单位-教务处）.xls");
//			FileOutputStream fileOutputStream  = new FileOutputStream(file);
//			
//			T441_Action t441 = new T441_Action();
//			InputStream is = t441.getInputStream();
//		    int ch = 0;    
//		    while((ch=is.read()) != -1){  
//		    	fileOutputStream.write(ch);  
//		    }
//
//            //关闭输入流等（略）  
//			fileOutputStream.close();  
//			is.close();
//			
//			return true;
//		} catch (Exception e) {	
//			e.printStackTrace();
//			return false;
//		}
//	}
	
	private boolean export411(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表4-1-1教师基本信息（人事处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T411_Action t411 = new T411_Action();
			InputStream is = t411.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	
	private boolean export412(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表4-1-2各专业专任教师队伍名单（教学单位-人事处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T412_Action t412 = new T412_Action();
			InputStream is = t412.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	
	private boolean export413(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表4-1-3外聘教师基本信息（教学单位-人事处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T413_Action t413 = new T413_Action();
			InputStream is = t413.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	
	private boolean export42(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表4-2校领导基本信息（党院办）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T42_Action t42 = new T42_Action();
			InputStream is = t42.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export431(String filePath2,int flag,String name) {
		
		T411_Service T411_services = new T411_Service();
		
		List<T431_Bean> list = T411_services.getList(flag);

		List<String> columns = new ArrayList<String>();
		columns.add("序号");
		columns.add("姓名");columns.add("教工号");columns.add("所属部门");columns.add("单位号");columns.add("身份代码");

		
		Map<String,Integer> maplist = new HashMap<String,Integer>();
		maplist.put("SeqNum", 0);
		maplist.put("name", 1);maplist.put("teaId", 2);maplist.put("fromDept", 3);maplist.put("unitId", 4);maplist.put("staffType", 5);	
		
		ByteArrayOutputStream byteArrayOutputStream = null;		
		try {
								
			byteArrayOutputStream = ExcelUtil.exportExcel(list, name, maplist,columns);

			File file = new File(filePath2,name+".xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			//写到文件中
			fileOutputStream.write(byteArrayOutputStream.toByteArray());
			
			byteArrayOutputStream.close();
			fileOutputStream.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	

	
	private boolean export435(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表4-3-5就业管理人员（招就处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T435_Action t435 = new T435_Action();
			InputStream is = t435.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	
	private boolean export442(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表4-4-2研究生导师情况（研究生院）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T442_Action t442 = new T442_Action();
			InputStream is = t442.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export443(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表4-4-3高层次人才（人事处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T443_Action t443 = new T443_Action();
			InputStream is = t443.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export444(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表4-4-4高层次研究团队（科研处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T444_Action t444 = new T444_Action();
			InputStream is = t444.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export451(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表4-5-1教师教学发展机构（人事处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T451_Action t451 = new T451_Action();
			InputStream is = t451.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export452(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表4-5-2教师培训进修情况（教学单位-人事处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T452_Action t452 = new T452_Action();
			InputStream is = t452.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export453(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表4-5-3教师交流情况（教学单位-国际交流与合作处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T453_Action t453 = new T453_Action();
			InputStream is = t453.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export461(String flag,String filePath2,String sheetName) {
		
		T461_Service T461_service = new T461_Service();
		
		UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		String fillUnitID = null;
		
		if(flag== "6"){
			fillUnitID = bean.getUnitID();
		}
		
		List<T461_Bean> list = T461_service.totalList(flag,fillUnitID,this.getSelectYear(),Constants.PASS_CHECK);

		List<String> columns = new ArrayList<String>();
		columns.add("序号");
		columns.add("姓名");columns.add("教工号");columns.add("所属教学单位");columns.add("单位号");columns.add("类别");
		columns.add("获奖级别");columns.add("授予单位");columns.add("获奖时间");
		columns.add("批文号");columns.add("其他参与教师人数");columns.add("其他成员");columns.add("备注");
		
		Map<String,Integer> maplist = new HashMap<String,Integer>();
		maplist.put("SeqNum", 0);
		maplist.put("name", 1);maplist.put("teaId", 2);maplist.put("fromTeaUnit", 3);maplist.put("unitId", 4);
		maplist.put("awardType", 5);maplist.put("awardLevel", 6);maplist.put("awardFromUnit", 7);maplist.put("gainAwardTime", 8);
		maplist.put("appvlId", 9);maplist.put("otherTeaNum", 10);maplist.put("otherTeaInfo", 11);maplist.put("note", 12);
		
		ByteArrayOutputStream byteArrayOutputStream = null;		
		try {
								
			byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

			File file = new File(filePath2,sheetName+".xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			//写到文件中
			fileOutputStream.write(byteArrayOutputStream.toByteArray());
			
			byteArrayOutputStream.close();
			fileOutputStream.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export47(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表4-7教学单位所获荣誉（教学单位-校数据采集工作组）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T47_Action t47 = new T47_Action();
			InputStream is = t47.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}

	private boolean export48(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表4-8教学团队（教学单位-教务处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T48_Action t48 = new T48_Action();
			InputStream is = t48.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export49(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表4-9教师出版教材（教学单位-教务处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T49_Action t49 = new T49_Action();
			InputStream is = t49.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export410(String filePath2) {
		
		try {
			
			File file = new File(filePath2,"表4-10教师科研情况（科研处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T410_Action t410 = new T410_Action();
			InputStream is = t410.getInputStream();
			if(is == null){
				System.out.println("该年数据为空");
				return false;
			}else{
			    int ch = 0;    
			    while((ch=is.read()) != -1){  
			    	fileOutputStream.write(ch);  
			    }
			}

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}

		private boolean export12(String filePath2) {
			
			try {			
				File file = new File(filePath2,"表1-2学校行政单位（党院办）.xls");
				FileOutputStream fileOutputStream  = new FileOutputStream(file);
				
				T12_Action t12 = new T12_Action();
				InputStream is = t12.getInputStream();
			    int ch = 0;    
			    while((ch=is.read()) != -1){  
			    	fileOutputStream.write(ch);  
			    }

	            //关闭输入流等（略）  
				fileOutputStream.close();  
				is.close();
				
				return true;
			} catch (Exception e) {	
				e.printStackTrace();
				return false;
			}
		}
		
		private boolean export13(String filePath2) {
					
					try {			
						File file = new File(filePath2,"表1-3学校科研单位（科研处）.xls");
						FileOutputStream fileOutputStream  = new FileOutputStream(file);
						
						T13_Action t13 = new T13_Action();
						InputStream is = t13.getInputStream();
					    int ch = 0;    
					    while((ch=is.read()) != -1){  
					    	fileOutputStream.write(ch);  
					    }
		
			            //关闭输入流等（略）  
						fileOutputStream.close();  
						is.close();
						
						return true;
					} catch (Exception e) {	
						e.printStackTrace();
						return false;
					}
				}
		
		private boolean export14(String filePath2) {
			
			try {			
				File file = new File(filePath2,"表1-4学校教学单位（教务处）.xls");
				FileOutputStream fileOutputStream  = new FileOutputStream(file);
				
				T14_Action t14 = new T14_Action();
				InputStream is = t14.getInputStream();
			    int ch = 0;    
			    while((ch=is.read()) != -1){  
			    	fileOutputStream.write(ch);  
			    }

	            //关闭输入流等（略）  
				fileOutputStream.close();  
				is.close();
				
				return true;
			} catch (Exception e) {	
				e.printStackTrace();
				return false;
			}
		}
		
		private boolean export151(String filePath2) {
			
			try {			
				File file = new File(filePath2,"表1-5-1校级以上科研机构（科研处）.xls");
				FileOutputStream fileOutputStream  = new FileOutputStream(file);
				
				T151_Action t151 = new T151_Action();
				InputStream is = t151.getInputStream();
			    int ch = 0;    
			    while((ch=is.read()) != -1){  
			    	fileOutputStream.write(ch);  
			    }

	            //关闭输入流等（略）  
				fileOutputStream.close();  
				is.close();
				
				return true;
			} catch (Exception e) {	
				e.printStackTrace();
				return false;
			}
		}
		
		private boolean export152(String filePath2) {
			
			try {			
				File file = new File(filePath2,"表1-5-2教学单位科研机构（教学单位-科研处）.xls");
				FileOutputStream fileOutputStream  = new FileOutputStream(file);
				
				T152_Action t152 = new T152_Action();
				InputStream is = t152.getInputStream();
			    int ch = 0;    
			    while((ch=is.read()) != -1){  
			    	fileOutputStream.write(ch);  
			    }
	
	            //关闭输入流等（略）  
				fileOutputStream.close();  
				is.close();
				
				return true;
			} catch (Exception e) {	
				e.printStackTrace();
				return false;
			}
		}
		
		private boolean export16(String filePath2) {
			
			try {			
				File file = new File(filePath2,"表1-6办学指导思想（党院办）.xls");
				FileOutputStream fileOutputStream  = new FileOutputStream(file);
				
				T16_Action t16 = new T16_Action();
				InputStream is = t16.getInputStream();
			    int ch = 0;    
			    while((ch=is.read()) != -1){  
			    	fileOutputStream.write(ch);  
			    }

	            //关闭输入流等（略）  
				fileOutputStream.close();  
				is.close();
				
				return true;
			} catch (Exception e) {	
				e.printStackTrace();
				return false;
			}
		}

		
		private boolean export17(String filePath2) {
			
			try {			
				File file = new File(filePath2,"表1-7-1校友会（党院办）.xls");
				FileOutputStream fileOutputStream  = new FileOutputStream(file);
				
				T17_Action t17 = new T17_Action();
				InputStream is = t17.getInputStream();
			    int ch = 0;    
			    while((ch=is.read()) != -1){  
			    	fileOutputStream.write(ch);  
			    }
	
	            //关闭输入流等（略）  
				fileOutputStream.close();  
				is.close();
				
				return true;
			} catch (Exception e) {	
				e.printStackTrace();
				return false;
			}
		}
		
		
		
		private boolean export172(String filePath2) {
			
			try {			
				File file = new File(filePath2,"表1-7-2校友返校交流情况（党院办）.xls");
				FileOutputStream fileOutputStream  = new FileOutputStream(file);
				
				T172_Action t172 = new T172_Action();
				InputStream is = t172.getInputStream();
			    int ch = 0;    
			    while((ch=is.read()) != -1){  
			    	fileOutputStream.write(ch);  
			    }
	
	            //关闭输入流等（略）  
				fileOutputStream.close();  
				is.close();
				
				return true;
			} catch (Exception e) {	
				e.printStackTrace();
				return false;
			}
		}
		
		private boolean export181(String filePath2) {
			
			try {			
				File file = new File(filePath2,"表1-8-1签订合作协议机构（教务处）.xls");
				FileOutputStream fileOutputStream  = new FileOutputStream(file);
				
				T181_Action t181 = new T181_Action();
				InputStream is = t181.getInputStream();
			    int ch = 0;    
			    while((ch=is.read()) != -1){  
			    	fileOutputStream.write(ch);  
			    }
	
	            //关闭输入流等（略）  
				fileOutputStream.close();  
				is.close();
				
				return true;
			} catch (Exception e) {	
				e.printStackTrace();
				return false;
			}
		}
		

		private boolean export182(String filePath2) {
			
			try {			
				File file = new File(filePath2,"表1-8-2签订合作协议机构（科研处）.xls");
				FileOutputStream fileOutputStream  = new FileOutputStream(file);
				
				T182_Action t182 = new T182_Action();
				InputStream is = t182.getInputStream();
			    int ch = 0;    
			    while((ch=is.read()) != -1){  
			    	fileOutputStream.write(ch);  
			    }
		
		        //关闭输入流等（略）  
				fileOutputStream.close();  
				is.close();
				
				return true;
			} catch (Exception e) {	
				e.printStackTrace();
				return false;
			}
		}
		
		private boolean export183(String filePath2) {
			
			try {			
				File file = new File(filePath2,"表1-8-3签订合作协议机构（招就处）.xls");
				FileOutputStream fileOutputStream  = new FileOutputStream(file);
				
				T183_Action t183 = new T183_Action();
				InputStream is = t183.getInputStream();
			    int ch = 0;    
			    while((ch=is.read()) != -1){  
			    	fileOutputStream.write(ch);  
			    }
			

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	private boolean export4_11(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表4-11教学单位社会服务情况（教学单位）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T4_11_Action t4_11 = new T4_11_Action();
			InputStream is = t4_11.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	
	private boolean exportT311(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表3-1-1博士后流动站（人事处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T311_Action t311 = new T311_Action();
			InputStream is = t311.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	
	private boolean exportT312(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表3-1-2博士点 、硕士点（研究生院）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T312_Action t312 = new T312_Action();
			InputStream is = t312.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean exportT313(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表3-1-3重点学科（科研处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T313_Action t313 = new T313_Action();
			InputStream is = t313.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean exportT321(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表3-2-1大类培养基本情况表（教务处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T321_Action t321 = new T321_Action();
			InputStream is = t321.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean exportT322(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表3-2-2本科专业基本情况（教学单位-教务处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T322_Action t322 = new T322_Action();
			InputStream is = t322.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	
	private boolean exportT33(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表3-3专科专业基本情况（教务处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T33_Action t33 = new T33_Action();
			InputStream is = t33.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	


		

		
		private boolean export19(String filePath2) {
			
			try {			
				File file = new File(filePath2,"表1-9学校获得荣誉（党院办）.xls");
				FileOutputStream fileOutputStream  = new FileOutputStream(file);
				
				T19_Action t19 = new T19_Action();
				InputStream is = t19.getInputStream();
			    int ch = 0;    
			    while((ch=is.read()) != -1){  
			    	fileOutputStream.write(ch);  
			    }
		
		        //关闭输入流等（略）  
				fileOutputStream.close();  
				is.close();
				
				return true;
			} catch (Exception e) {	
				e.printStackTrace();
				return false;
			}
		}

		
		
		
		//2字头


	private boolean export21(String filePath2) {
		
		try {
			
			File file = new File(filePath2,"表2-1占地与建筑面积（后勤处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T21_Action t21 = new T21_Action();
			InputStream is = t21.getInputStream();
			if(is == null){
				System.out.println("该年数据为空");
				return false;
			}else{
			    int ch = 0;    
			    while((ch=is.read()) != -1){  
			    	fileOutputStream.write(ch);  
			    }
			}

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export2101(String filePath2) {
		
		try {
			
			File file = new File(filePath2,"表2-10-1素质教育情况（团委）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T2101_Action t2101 = new T2101_Action();
			InputStream is = t2101.getInputStream();
			if(is == null){
				System.out.println("该年数据为空");
				return false;
			}else{
			    int ch = 0;    
			    while((ch=is.read()) != -1){  
			    	fileOutputStream.write(ch);  
			    }
			}

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export2102(String filePath2) {
		
		try {
			
			File file = new File(filePath2,"表2-10-2职业及创业教育课程（招就处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T2102_Action t2102 = new T2102_Action();
			InputStream is = t2102.getInputStream();
			if(is == null){
				System.out.println("该年数据为空");
				return false;
			}else{
			    int ch = 0;    
			    while((ch=is.read()) != -1){  
			    	fileOutputStream.write(ch);  
			    }
			}

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export2103(String filePath2) {
		
		try {
			
			File file = new File(filePath2,"表2-10-3职业资质培训（职教处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T2103_Action t2103 = new T2103_Action();
			InputStream is = t2103.getInputStream();
			if(is == null){
				System.out.println("该年数据为空");
				return false;
			}else{
			    int ch = 0;    
			    while((ch=is.read()) != -1){  
			    	fileOutputStream.write(ch);  
			    }
			}

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	private boolean export22(String filePath2) {
		
		try {
			
			File file = new File(filePath2,"表2-2用房面积（后勤处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T22_Action t22 = new T22_Action();
			InputStream is = t22.getInputStream();
			if(is == null){
				System.out.println("该年数据为空");
				return false;
			}else{
			    int ch = 0;    
			    while((ch=is.read()) != -1){  
			    	fileOutputStream.write(ch);  
			    }
			}

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export231(String filePath2) {
		
		try {
			
			File file = new File(filePath2,"表2-3-1教室（教务处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T231_Action t231 = new T231_Action();
			InputStream is = t231.getInputStream();
			if(is == null){
				System.out.println("该年数据为空");
				return false;
			}else{
			    int ch = 0;    
			    while((ch=is.read()) != -1){  
			    	fileOutputStream.write(ch);  
			    }
			}

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export232(String filePath2) {
		
		try {
			
			File file = new File(filePath2,"表2-3-2教学计算机数量（设备处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T232_Action t232 = new T232_Action();
			InputStream is = t232.getInputStream();
			if(is == null){
				System.out.println("该年数据为空");
				return false;
			}else{
			    int ch = 0;    
			    while((ch=is.read()) != -1){  
			    	fileOutputStream.write(ch);  
			    }
			}

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export241(String filePath2) {
		
		try {
			
			File file = new File(filePath2,"表2-4-1图书数量（图书馆）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T241_Action t241 = new T241_Action();
			InputStream is = t241.getInputStream();
			if(is == null){
				System.out.println("该年数据为空");
				return false;
			}else{
			    int ch = 0;    
			    while((ch=is.read()) != -1){  
			    	fileOutputStream.write(ch);  
			    }
			}

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export242(String filePath2) {
		
		try {
			
			File file = new File(filePath2,"表2-4-2图书当年新增情况（图书馆）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T242_Action t242 = new T242_Action();
			InputStream is = t242.getInputStream();
			if(is == null){
				System.out.println("该年数据为空");
				return false;
			}else{
			    int ch = 0;    
			    while((ch=is.read()) != -1){  
			    	fileOutputStream.write(ch);  
			    }
			}

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export251(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表2-5-1本科实验、实习、实训场所-基本情况（设备处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T251_Action t251 = new T251_Action();
			InputStream is = t251.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export252(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表2-5-2本科实验、实习、实训场所-教学情况（教学单位-教务处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T252_Action t252 = new T252_Action();
			InputStream is = t252.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export26(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表2-6校外实习、实训基地（教务处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T26_Action t26 = new T26_Action();
			InputStream is = t26.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export27(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表2-7校园网（网络信息中心）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T27_Action t27 = new T27_Action();
			InputStream is = t27.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export281(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表2-8-1固定资产总值-建筑（后勤处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T281_Action t281 = new T281_Action();
			InputStream is = t281.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export282(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表2-8-2固定资产总值-设备（设备处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T282_Action t282 = new T282_Action();
			InputStream is = t282.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export283(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表2-8-3固定资产总值-图书（图书馆）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T283_Action t283 = new T283_Action();
			InputStream is = t283.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export284(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表2-8-4固定资产总值-档案(档案馆).xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T284_Action t284 = new T284_Action();
			InputStream is = t284.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export285(String filePath2) {
		try {			
	File file = new File(filePath2,"表2-8-5教学、科研仪器设备（设备处）.xls");
	FileOutputStream fileOutputStream  = new FileOutputStream(file);
	
	T285_Action t285 = new T285_Action();
	InputStream is = t285.getInputStream();
    int ch = 0;    
    while((ch=is.read()) != -1){  
    	fileOutputStream.write(ch);  
    }

    //关闭输入流等（略）  
	fileOutputStream.close();  
	is.close();
	
	return true;
} catch (Exception e) {	
	e.printStackTrace();
	return false;
}
}
	
	
	private boolean export291(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表2-9-1教学经费概况（计财处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T291_Action t291 = new T291_Action();
			InputStream is = t291.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export292(String filePath2) {
		try {			
			File file = new File(filePath2,"表2-9-2本科教育经费支出情况（计财处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T292_Action t292 = new T292_Action();
			InputStream is = t292.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export293(String filePath2) {
		try {			
			File file = new File(filePath2,"表2-9-3本科教育经费收入情况（计财处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T293_Action t293 = new T293_Action();
			InputStream is = t293.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export294(String filePath2) {
				try {			
			File file = new File(filePath2,"表2-9-4社会捐赠情况（计财处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T294_Action t294 = new T294_Action();
			InputStream is = t294.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export511(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表5-1-1本科课程库.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T511_Action t511 = new T511_Action();
			InputStream is = t511.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	
	private boolean export512(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表5-1-2开课、授课情况（教学单位-教务处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T512_Action t512 = new T512_Action();
			InputStream is = t512.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export513(String filePath2) {
		
		try {
			
			File file = new File(filePath2,"表5-1-3课堂教学质量评估统计表（评估中心）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T513Action t513 = new T513Action();
			InputStream is = t513.getInputStream();
			if(is == null){
				System.out.println("该年数据为空");
				return false;
			}else{
			    int ch = 0;    
			    while((ch=is.read()) != -1){  
			    	fileOutputStream.write(ch);  
			    }
			}

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	
	private boolean export521(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表5-2-1课程建设情况（教务处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T521Action t521 = new T521Action();
			InputStream is = t521.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	
	private boolean export522(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表5-2-2网络课程建设情况（网络信息中心）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T522Action t522 = new T522Action();
			InputStream is = t522.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export531(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表5-3-1人才培养模式创新实验项目（教务处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T531Action t531 = new T531Action();
			InputStream is = t531.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export532(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表5-3-2实验教学示范中心（设备处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T532Action t532 = new T532Action();
			InputStream is = t532.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export533(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表5-3-3分专业实验情况（教学单位-教务处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T533Action t533 = new T533Action();
			InputStream is = t533.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export534(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表5-3-4分专业毕业综合训练情况（教学单位-教务处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T534Action t534 = new T534Action();
			InputStream is = t534.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export54(String filePath2) {
		
		try {
			
			File file = new File(filePath2,"表5-4课外活动、讲座（团委）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T54_Action t54 = new T54_Action();
			InputStream is = t54.getInputStream();
			if(is == null){
				System.out.println("该年数据为空");
				return false;
			}else{
			    int ch = 0;    
			    while((ch=is.read()) != -1){  
			    	fileOutputStream.write(ch);  
			    }
			}

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export551(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表5-5-1学风概况（学工处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T551Action t551 = new T551Action();
			InputStream is = t551.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export552(String filePath2) {
		
		try {
			
			File file = new File(filePath2,"表5-5-2优秀本科班级（学工处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T552_Action t552 = new T552_Action();
			InputStream is = t552.getInputStream();
			if(is == null){
				System.out.println("该年数据为空");
				return false;
			}else{
			    int ch = 0;    
			    while((ch=is.read()) != -1){  
			    	fileOutputStream.write(ch);  
			    }
			}

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export553(String filePath2) {
		
		try {
			
			File file = new File(filePath2,"表5-5-3优秀本科生（学工处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T553_Action t553 = new T553_Action();
			InputStream is = t553.getInputStream();
			if(is == null){
				System.out.println("该年数据为空");
				return false;
			}else{
			    int ch = 0;    
			    while((ch=is.read()) != -1){  
			    	fileOutputStream.write(ch);  
			    }
			}

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export711(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表7-1-1教学管理人员获得教学成果奖情况（教学单位-教务处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T711_Action t711 = new T711_Action();
			InputStream is = t711.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export712(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表7-1-2教学管理人员发表教学论文情况（教学单位-教务处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T712_Action t712 = new T712_Action();
			InputStream is = t712.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export721(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表7-2-1教育教学研究与改革项目（教务处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T721_Action t721 = new T721_Action();
			InputStream is = t721.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export722(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表7-2-2教学成果奖（教务处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T722_Action t722 = new T722_Action();
			InputStream is = t722.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export731(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表7-3-1校领导听课情况（教务处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T731_Action t731 = new T731_Action();
			InputStream is = t731.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export732(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表7-3-2教学单位领导听课情况（教学单位--教务处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T732_Action t732 = new T732_Action();
			InputStream is = t732.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export733(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表7-3-3各单位开展教学研究活动情况.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T733_Action t733 = new T733_Action();
			InputStream is = t733.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export734(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表7-3-4教学事故（教务处）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T734_Action t734 = new T734_Action();
			InputStream is = t734.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export735(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表7-3-5 院（部）教学管理状态考评情况.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T735_Action t735 = new T735_Action();
			InputStream is = t735.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export741(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表7-4-1教师教学能力合格评估（评估中心）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T741_Action t741 = new T741_Action();
			InputStream is = t741.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export742(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表7-4-2教师教学水平评估（评估中心）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T742_Action t742 = new T742_Action();
			InputStream is = t742.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export743(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表7-4-3课程建设评估（评估中心）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T743_Action t743 = new T743_Action();
			InputStream is = t743.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export744(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表7-4-4专业建设评估（评估中心）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T744_Action t744 = new T744_Action();
			InputStream is = t744.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean export745(String filePath2) {
		
		try {			
			File file = new File(filePath2,"表7-4-5院（系）教学工作状态评估（评估中心）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			T745_Action t745 = new T745_Action();
			InputStream is = t745.getInputStream();
		    int ch = 0;    
		    while((ch=is.read()) != -1){  
		    	fileOutputStream.write(ch);  
		    }

            //关闭输入流等（略）  
			fileOutputStream.close();  
			is.close();
			
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	
	

	
	
	

	
	private boolean export441(String filePath2) {
			
			try {			
				File file = new File(filePath2,"表4-4-1专业带头人（教学单位-教务处）.xls");
				FileOutputStream fileOutputStream  = new FileOutputStream(file);
				
				T441_Action t441 = new T441_Action();
				InputStream is = t441.getInputStream();
			    int ch = 0;    
			    while((ch=is.read()) != -1){  
			    	fileOutputStream.write(ch);  
			    }
	
	            //关闭输入流等（略）  
				fileOutputStream.close();  
				is.close();
				
				return true;
			} catch (Exception e) {	
				e.printStackTrace();
				return false;
			}
		}


	public String getFileName() {
		try {
			this.fileName = URLEncoder.encode(fileName, "UTF-8");
			//this.saveFile = new String(saveFile.getBytes("ISO-8859-1"),"UTF-8");// 中文乱码解决
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	public String getFilePath() {
		return filePath;
	}

	public void setSelectYear(String selectYear) {
		this.selectYear = selectYear;
	}

	public String getSelectYear() {
		return selectYear;
	}
}