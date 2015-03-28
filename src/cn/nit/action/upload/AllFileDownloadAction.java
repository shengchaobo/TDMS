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

import cn.nit.education.download.J2101_Excel;
import cn.nit.education.download.J2102_Excel;
import cn.nit.education.download.J211_Excel;
import cn.nit.education.download.J21_Excel;
import cn.nit.education.download.J22_Excel;
import cn.nit.education.download.J11_Excel;
import cn.nit.education.download.J13_Excel;
import cn.nit.education.download.J14_Excel;
import cn.nit.education.download.J15_Excel;
import cn.nit.education.download.J16_Excel;
import cn.nit.education.download.J17_Excel;
import cn.nit.education.download.J23_Excel;
import cn.nit.education.download.J24_Excel;
import cn.nit.education.download.J251_Excel;
import cn.nit.education.download.J252_Excel;
import cn.nit.education.download.J261_Excel;
import cn.nit.education.download.J262_Excel;
import cn.nit.education.download.J27_Excel;
import cn.nit.education.download.J28_Excel;
import cn.nit.education.download.J29_Excel;
import cn.nit.education.download.J311_Excel;
import cn.nit.education.download.J312_Excel;
import cn.nit.education.download.J313_Excel;
import cn.nit.education.download.J314_Excel;
import cn.nit.education.download.J321_Excel;
import cn.nit.education.download.J322_Excel;
import cn.nit.education.download.J323_Excel;
import cn.nit.education.download.J411_Excel;
import cn.nit.education.download.J412_Excel;
import cn.nit.education.download.J42_Excel;
import cn.nit.education.download.J43_Excel;
import cn.nit.education.download.J441_Excel;
import cn.nit.education.download.J442_Excel;
import cn.nit.education.download.J451_Excel;
import cn.nit.education.download.J452_Excel;
import cn.nit.education.download.J461_Excel;
import cn.nit.education.download.J462_Excel;
import cn.nit.education.download.J463_Excel;
import cn.nit.education.download.J464_Excel;
import cn.nit.education.download.J465_Excel;
import cn.nit.education.download.J466_Excel;
import cn.nit.education.download.J511_Excel;
import cn.nit.education.download.J512_Excel;
import cn.nit.education.download.J513_Excel;
import cn.nit.education.download.J52_Excel;
import cn.nit.education.download.J531_Excel;
import cn.nit.education.download.J532_Excel;
import cn.nit.education.download.J533_Excel;
import cn.nit.education.download.J534_Excel;
import cn.nit.education.download.J54_Excel;
import cn.nit.education.download.J611_Excel;
import cn.nit.education.download.J612_Excel;
import cn.nit.education.download.J613_Excel;
import cn.nit.education.download.J614_Excel;
import cn.nit.education.download.J615_Excel;
import cn.nit.education.download.J616_Excel;
import cn.nit.education.download.J617_Excel;
import cn.nit.education.download.J618_Excel;
import cn.nit.education.download.J619_Excel;
import cn.nit.education.download.J621_Excel;
import cn.nit.education.download.J622_Excel;
import cn.nit.education.download.J63_Excel;
import cn.nit.education.download.J71_Excel;
import cn.nit.education.download.J72_Excel;
import cn.nit.education.download.J731_Excel;
import cn.nit.education.download.J732_Excel;
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
        
        System.out.println(this.getSelectYear());
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
		
		// TODO Auto-generated method stub
		//1字头
		if(!J11_Excel.export_J11(filePath)){
			return "J11_export has a error!";
		}			

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

	public void setSelectYear(String selectYear) {
		this.selectYear = selectYear;
	}

	public String getSelectYear() {
		return selectYear;
	}
}