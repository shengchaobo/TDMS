package cn.nit.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;


public class FileofMain {
	
	private String inputPath;
	
	private String downLoadName;
	
	private FileInputStream inputStream;
	
	private int FileNum;
	
	private String fileName;
	
	private int type;
	
	private int type_1;
	
    private File uploadFile;
    private String uploadFileFileName;  
	
	
	
	
	
	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public int getType_1() {
		return type_1;
	}

	public void setType_1(int type_1) {
		this.type_1 = type_1;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

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

	public String execute() {
     // TODO Auto-generated method stub
     return "success";
    }
	
	public void findFiles(){
		
		PrintWriter out = null ;
		HttpServletResponse response = ServletActionContext.getResponse();
		
		try{
			
	        // 把上传的文件放到指定的路径下  
	        String path = ServletActionContext.getServletContext().getRealPath(inputPath);
	        String dir = path+"\\"+ String.valueOf(type);
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
	      String dir = path+"\\"+ String.valueOf(type_1);
	      File dirFile = new File(dir);
	      
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
	
	
	public void deleteFile() throws IOException{
				
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=UTF-8") ;  
		PrintWriter out = response.getWriter();
		try{
			
	        // 把上传的文件放到指定的路径下     	  
	        String path = ServletActionContext.getServletContext().getRealPath(inputPath);
	        
	        //判断目录是否存
	        String dir = path+"\\"+this.getType_1();
	        File dirFile = new File(dir);

	        if(dirFile.exists()){
	        	
	        	File[] fileList = dirFile.listFiles();	        	
	        	File f = fileList[FileNum];
	        	f.delete();
	        }
	        
	        out.print("{\"state\":true,\"data\":\"删除成功\"}") ;
	        
		}catch (Exception e) {
			e.printStackTrace();
			out.print("{\"state\":false,\"data\":\"删除失败\"}") ;
		}				
					        
	}
	
    public void upload() throws IOException{
    	
    	
		PrintWriter out = null ;
		HttpServletResponse response = ServletActionContext.getResponse();
		
		try{
			
	        // 把上传的文件放到指定的路径下     	  
	        String path = ServletActionContext.getServletContext().getRealPath(inputPath);
	        
	        //判断目录是否存
	        String dir = path+"\\"+this.getType_1();
	        File dirFile = new File(new File(dir), this.getUploadFileFileName());
	        	        
	        FileUtils.copyFile(uploadFile, dirFile);
	        	        
			response.setContentType("text/html; charset=UTF-8") ;
			out = response.getWriter() ;
			out.print("{\"state\":true,\"data\":\"上传成功!!!\",\"fileName\":\"" +this.getUploadFileFileName()+"\",\"fileNum\":\"" +this.getFileNum()+"\"}") ;
			out.flush() ;
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,\"data\":\"上传失败，文件异常!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
        
    }
	
	

}
