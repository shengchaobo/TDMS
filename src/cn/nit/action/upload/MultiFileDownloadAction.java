package cn.nit.action.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.apache.struts2.ServletActionContext;

import cn.nit.education.download.J411_Excel;
import cn.nit.util.ZipFileUtil;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 处理多个附件下载
 * @author Luxh
 */
public class MultiFileDownloadAction extends ActionSupport {

	private static final long serialVersionUID = 2743077909387361587L;
	
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
	    	System.out.println(errorMessasge);
	    	return "error";	    	 
	    } else {
	    	return "success";
	    }	    	   
    }
	
	
	private String preDownloadFile(String filePath) {
		// TODO Auto-generated method stub
		
		if(!J411_Excel.export_J411(filePath)){
			return "J411_export has a error!";
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
}