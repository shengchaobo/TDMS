package cn.nit.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;


public class FileofMain_1 {
	
	private String inputPath;
	
	private String downLoadName;
	
	private FileInputStream inputStream;
	
	private int FileNum;
	
	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}
	
	
    public String getDownLoadName() {
		return downLoadName;
	}

	public void setDownLoadName(String downLoadName) {
		this.downLoadName = downLoadName;
	}
	
	

	public int getFileNum() {
		return FileNum;
	}

	public void setFileNum(int fileNum) {
		FileNum = fileNum;
	}

	public String execute() {
     // TODO Auto-generated method stub
     return "success";
    }
	
	public void findFiles_1(){
		
		PrintWriter out = null ;
		HttpServletResponse response = ServletActionContext.getResponse();
		
		try{
			
	        // 把上传的文件放到指定的路径下     	  
	        String dir = ServletActionContext.getServletContext().getRealPath(inputPath);
	        File dirFile = new File(dir);
	        
	        StringBuffer json = new StringBuffer() ;
	        json.append("{");
	        Integer i;
	        	 if(dirFile.exists()){
	 	        	File[] fileList = dirFile.listFiles();	
	 	        	for(i=0;i<fileList.length;i++){
	 	        		json.append("\"" + i.toString() + "\":\"" + fileList[i].getName()+"\",");
	 	        	}
	        	 }else{
	        		 json.append("\"" + "0" + "\":"+"\"none\","); 
	        	 }
	      
	        json.deleteCharAt(json.lastIndexOf(",")) ;
	        json.append("}") ;
	        System.out.println(json.toString());
	        	        
			response.setContentType("text/html; charset=UTF-8") ;
			out = response.getWriter() ;
			out.print(json.toString()) ;
			out.flush() ;
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,\"data\":\"刷新失败\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}	
	}
	
	public InputStream getDownloadFile() {
	      // TODO Auto-generated method stub
	    	
	      // 把上传的文件放到指定的路径下     	  
	      String path = ServletActionContext.getServletContext().getRealPath(inputPath);
	      File dirFile = new File(path);
	      
	      if(dirFile.exists()){
	      	
	      	File[] fileList = dirFile.listFiles();	        	
	      	File f = fileList[FileNum];
	      		this.setDownLoadName(f.getName());           
	            try {
	            	 inputStream = new FileInputStream(f);
	            } catch (FileNotFoundException e) {
	             // TODO Auto-generated catch block
	                 e.printStackTrace();
	            }
	      	}
	      
	      return inputStream;
	     }
	
	

}
