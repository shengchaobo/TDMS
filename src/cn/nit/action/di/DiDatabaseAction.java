package cn.nit.action.di;

import java.io.File;  
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;



import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;  
import java.sql.PreparedStatement;

import cn.nit.dbconnection.DBConnection;
  
public class DiDatabaseAction {  
	private FileInputStream inputStream;
	
	private String inputPath;
	
	private String downLoadName;
	
    public String getInputPath(){
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}
	
	public InputStream getInputStream(){
				
		 ActionContext context = ActionContext.getContext();  
	        HttpServletRequest request = (HttpServletRequest) context  
	                .get(ServletActionContext.HTTP_REQUEST);  

	        
	        String path = ServletActionContext.getServletContext().getRealPath(inputPath);
	        //String path = "E://apache-tomcat-6.0.37//webapps//TDMS//WEB-INF//";
	        String name = "TDMS"; //数据库名  
	        try {  
	            //System.out.println(path);  
	            String realpath = path + "\\" + name + ".bak";// name文件名  
	        	//String realpath = "D:\\TDMS.bak";
	            String bakSQL = "backup database [TDMS] to disk=? with init";// SQL语句  
	            PreparedStatement bak = DBConnection.instance.getConnection()  
	                    .prepareStatement(bakSQL);  
	            bak.setString(1, realpath);// path必须是绝对路径  
	            bak.execute(); // 备份数据库  
	            bak.close();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }
	        
	        File dirFile = new File(path);
	        if(dirFile.exists()){
	        	File[] fileList = dirFile.listFiles();
	          	for(File f:fileList){
	          		if("TDMS.bak".equals(f.getName())){
		          		this.setDownLoadName(f.getName());         
		                try {
		                	 inputStream = new FileInputStream(f);
		                } catch (FileNotFoundException e) {
		                 // TODO Auto-generated catch block
		                     e.printStackTrace();
		                }
	          		}
	          	}
	        	
	        }
//      System.out.println(inputStream);
		return inputStream ;
	}
	
	
	

	public String execute() throws Exception{
		return "success" ;
	}
	
	public void setDownLoadName(String downLoadName) {
		this.downLoadName = downLoadName;
	}

	public String getDownLoadName() {
		
		try {
			this.downLoadName = URLEncoder.encode(downLoadName, "UTF-8");
			//this.saveFile = new String(saveFile.getBytes("ISO-8859-1"),"UTF-8");// 中文乱码解决
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downLoadName;
	}
    

}  