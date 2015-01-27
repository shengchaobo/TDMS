package cn.nit.action.di;

import java.io.File;  
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
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
	        String name = "TDMS"; //数据库名  
	        try {  
	            System.out.println(path);  
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
	          		         
	                try {
	                	 inputStream = new FileInputStream(f);
	                } catch (FileNotFoundException e) {
	                 // TODO Auto-generated catch block
	                     e.printStackTrace();
	                }
	          	}
	        	
	        }
//      System.out.println(inputStream);
		return inputStream ;
	}
	

	public String execute() throws Exception{
		return "success" ;
	}
    

}  
