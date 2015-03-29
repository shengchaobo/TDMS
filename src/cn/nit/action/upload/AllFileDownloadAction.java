package cn.nit.action.upload;

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
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts2.ServletActionContext;

import cn.nit.action.table2.T21_Action;
import cn.nit.action.table4.T441_Action;
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
		//2字头
		if(!export21(filePath)){
			return "export21 has a error!";
		}
		
		
		//4字头
		if(!export441(filePath)){
			return "export441 has a error!";
		}	
		request.getSession().removeAttribute("allYear");
		return null;
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