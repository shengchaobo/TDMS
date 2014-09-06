package cn.nit.action.upload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class LoadFileAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int start;
	private int end;
	private String inputPath;
	
	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

	public void loadFiles() throws IOException{
		
		PrintWriter out = null ;
		HttpServletResponse response = ServletActionContext.getResponse();
		
		try{
			
	        // 把上传的文件放到指定的路径下     	  
	        String path = ServletActionContext.getServletContext().getRealPath(inputPath);
	        
	        
	        StringBuffer json = new StringBuffer() ;
	        json.append("{");
	        Integer i;
	        for(i=this.getStart();i<=this.getEnd();i++){
	        	String dir = path+"\\"+ i.toString();
	        	 File dirFile = new File(dir);
	        	 if(dirFile.exists()){
	 	        	File[] fileList = dirFile.listFiles();	        	
	 	        	json.append("\"" + i.toString() + "\":\"" + fileList[0].getName()+"\",");
	        	 }else{
	        		json.append("\"" + i.toString() + "\":"+"\"none\","); 
	        	 }
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

	public void setStart(int start) {
		this.start = start;
	}

	public int getStart() {
		return start;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getEnd() {
		return end;
	}

}
