package cn.nit.action.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts2.ServletActionContext;

import cn.nit.education.download.J21_Excel;
import cn.nit.education.download.J22_Excel;
import cn.nit.education.download.J10_Excel;
import cn.nit.education.download.J13_Excel;
import cn.nit.education.download.J14_Excel;
import cn.nit.education.download.J15_Excel;
import cn.nit.education.download.J16_Excel;
import cn.nit.education.download.J17_Excel;
import cn.nit.education.download.J411_Excel;
import cn.nit.education.download.J611_Excel;
import cn.nit.util.ZipFileUtil;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 处理多个附件下载
 * @author Luxh
 */
public class MultiFileDownloadAction extends ActionSupport {

	private static final long serialVersionUID = 2743077909387361587L;
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse() ;	
	
	
	private FileInputStream inputStream;
	
	//接收压缩文件的路径
	private String filePath; 
	
	//最终压缩后的zip文件的路径，传递给通用的下载Action
	private String fileName;

	
	/**
	 * 下载多个附件
	 * 实现：将多个附近压缩成zip包,然后再下载zip包
	 */
	public InputStream getDownloadMultiFile() {
		
		SimpleDateFormat sfm = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");  
		String filename = "教育部导出表格";
		
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
        
    	String errorMessasge = preDownloadFile(dir);
    	
	    if (errorMessasge != null) {
	    	request.setAttribute("error", errorMessasge);
	    	System.out.println(errorMessasge);
	    	return "error";	    	 
	    } else {
	    	return "success";
	    }	    	   
    }
	
	
	private String preDownloadFile(String filePath) {
		
		// TODO Auto-generated method stub
		//1字头
/*		if(!J11_Excel.export_J11(filePath)){
			return "J11_export has a error!";
		}*/		
/*		if(!J12_Excel.export_J12(filePath)){
			return "J12_export has a error!";
		}*/
/*		if(!J13_Excel.export_J13(filePath)){
			return "J13_export has a error!";
		}*/
/*		if(!J14_Excel.export_J14(filePath)){
			return "J14_export has a error!";
		}*/
/*		if(!J15_Excel.export_J15(filePath)){
			return "J15_export has a error!";
		}*/
/*		if(!J16_Excel.export_J16(filePath)){
			return "J16_export has a error!";
		}*/
/*		if(!J17_Excel.export_J17(filePath)){
			return "J17_export has a error!";
		}*/
		
		//2字头
		if(!J21_Excel.export_J21(filePath)){
			return "J21_export has a error!";
		}
		if(!J22_Excel.export_J22(filePath)){
			return "J22_export has a error!";
		}
/*		if(!J23_Excel.export_J23(filePath)){
			return "J23_export has a error!";
		}*/
/*		if(!J24_Excel.export_J24(filePath)){
			return "J24_export has a error!";
		}*/
/*		if(!J251_Excel.export_J251(filePath)){
			return "J251_export has a error!";
		}*/
/*		if(!J252_Excel.export_J252(filePath)){
			return "J252_export has a error!";
		}*/
/*		if(!J261_Excel.export_J261(filePath)){
			return "J261_export has a error!";
		}*/
/*		if(!J262_Excel.export_J262(filePath)){
			return "J262_export has a error!";
		}*/
/*		if(!J27_Excel.export_J27(filePath)){
			return "J27_export has a error!";
		}*/
/*		if(!J28_Excel.export_J28(filePath)){
			return "J28_export has a error!";
		}*/
/*		if(!J29_Excel.export_J29(filePath)){
			return "J29_export has a error!";
		}*/
/*		if(!J2101_Excel.export_J2101(filePath)){
			return "J2101_export has a error!";
		}*/
/*		if(!J2102_Excel.export_J2102(filePath)){
			return "J2102_export has a error!";
		}*/
/*		if(!J211_Excel.export_J211(filePath)){
			return "J211_export has a error!";
		}*/
		
		//3字头
/*		if(!J311_Excel.export_J311(filePath)){
			return "J311_export has a error!";
		}*/
/*		if(!J312_Excel.export_J312(filePath)){
			return "J312_export has a error!";
		}*/
/*		if(!J313_Excel.export_J313(filePath)){
			return "J311_export has a error!";
		}*/
/*		if(!J314_Excel.export_J314(filePath)){
			return "J311_export has a error!";
		}*/
/*		if(!J321_Excel.export_J321(filePath)){
			return "J321_export has a error!";
		}*/
/*		if(!J322_Excel.export_J322(filePath)){
			return "J322_export has a error!";
		}*/
/*		if(!J323_Excel.export_J323(filePath)){
			return "J323_export has a error!";
		}*/
		
		//4字头
/*		if(!J411_Excel.export_J411(filePath)){
			return "J411_export has a error!";
		}	*/			
/*		if(!J412_Excel.export_J412(filePath)){
			return "J412_export has a error!";
		}*/
/*		if(!J42_Excel.export_J42(filePath)){
			return "J42_export has a error!";
		}*/
/*		if(!J43_Excel.export_J43(filePath)){
			return "J43_export has a error!";
		}*/
/*		if(!J441_Excel.export_J441(filePath)){
			return "J441_export has a error!";
		}*/		
/*		if(!J442_Excel.export_J442(filePath)){
			return "J442_export has a error!";
		}*/	
/*		if(!J451_Excel.export_J451(filePath)){
			return "J451_export has a error!";
		}
		if(!J452_Excel.export_J452(filePath)){
			return "J452_export has a error!";
		}
		if(!J461_Excel.export_J461(filePath)){
			return "J461_export has a error!";
		}
		if(!J462_Excel.export_J462(filePath)){
			return "J461_export has a error!";
		}
		if(!J463_Excel.export_J463(filePath)){
			return "J463_export has a error!";
		}
		if(!J464_Excel.export_J464(filePath)){
			return "J464_export has a error!";
		}
		if(!J465_Excel.export_J465(filePath)){
			return "J465_export has a error!";
		}
		if(!J466_Excel.export_J466(filePath)){
			return "J466_export has a error!";
		}*/
		
		
		//5字头
/*		if(!J511_Excel.export_J511(filePath)){
			return "J511_export has a error!";
		}*/
/*		if(!J512_Excel.export_J512(filePath)){
			return "J512_export has a error!";
		}*/
/*		if(!J513_Excel.export_J513(filePath)){
			return "J513_export has a error!";
		}*/
/*		if(!J52_Excel.export_J52(filePath)){
			return "J52_export has a error!";
		}*/
/*		if(!J531_Excel.export_J531(filePath)){
			return "J531_export has a error!";
		}*/
/*		if(!J532_Excel.export_J532(filePath)){
			return "J532_export has a error!";
		}*/
/*		if(!J533_Excel.export_J533(filePath)){
			return "J533_export has a error!";
		}*/
/*		if(!J534_Excel.export_J534(filePath)){
			return "J534_export has a error!";
		}	*/	
/*		if(!J54_Excel.export_J54(filePath)){
			return "J54_export has a error!";
		}*/
		
		//6字头
/*		if(!J611_Excel.export_J611(filePath)){
			return "J611_export has a error!";
		}*/
/*		if(!J612_Excel.export_J612(filePath)){
			return "J612_export has a error!";
		}*/
/*		if(!J613_Excel.export_J613(filePath)){
			return "J613_export has a error!";
		}*/
/*		if(!J614_Excel.export_J614(filePath)){
			return "J614_export has a error!";
		}*/
/*		if(!J615_Excel.export_J615(filePath)){
			return "J615_export has a error!";
		}*/
/*		if(!J616_Excel.export_J616(filePath)){
			return "J616_export has a error!";
		}*/
/*		if(!J617_Excel.export_J617(filePath)){
			return "J617_export has a error!";
		}*/
/*		if(!J618_Excel.export_J618(filePath)){
			return "J618_export has a error!";
		}*/
/*		if(!J619_Excel.export_J619(filePath)){
			return "J619_export has a error!";
		}*/
/*		if(!J621_Excel.export_J621(filePath)){
			return "J621_export has a error!";
		}*/
/*		if(!J622_Excel.export_J622(filePath)){
			return "J622_export has a error!";
		}*/
/*		if(!J63_Excel.export_J63(filePath)){
			return "J63_export has a error!";
		}*/
		
		//7字头
/*		if(!J71_Excel.export_J71(filePath)){
			return "J71_export has a error!";
		}*/
/*		if(!J72_Excel.export_J72(filePath)){
			return "J72_export has a error!";
		}*/
/*		if(!J731_Excel.export_J731(filePath)){
			return "J731_export has a error!";
		}*/
/*		if(!J732_Excel.export_J732(filePath)){
			return "J73_export has a error!";
		}*/
/*		if(!J74_Excel.export_J74(filePath)){
			return "J74_export has a error!";
		}*/
		return null;
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
}