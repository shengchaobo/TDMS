package cn.nit.action;

import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Cell;

import org.apache.struts2.ServletActionContext;

//import cn.nit.bean.other.UserRoleBean;
import cn.nit.util.ExcelUtil;

public class UploadAction {

	private File uploadFile ;
	
	private String uploadFileFileName;
	
	private String className ;
	
	private String methodName ;
	
	
	public void uploadFile(){
		
		PrintWriter out = null ;
		try{
			getResponse().setContentType("text/html;charset=UTF-8") ;
			out = getResponse().getWriter() ;
			
			if(className == null || className.equals("") || methodName == null || methodName.equals("")){
				out.print("{success:false,errorMsg:'系统错误，请联系管理员'}") ;
				return ;
			}
			
			System.out.println(this.getUploadFile());
			System.out.println(this.getUploadFileFileName());
			
			List<Cell[]> list = ExcelUtil.readExcel(uploadFile, 0) ;
			Class clazz = Class.forName(className) ;
			Method method = clazz.getDeclaredMethod(methodName, List.class, HttpServletRequest.class) ;
			String errorMsg = (String)method.invoke(clazz.newInstance(), list, getRequest()) ;
			
			if(errorMsg == null || errorMsg.equals("")){
				out.print("{success:true,errorMsg:'数据存储成功'}") ;
			}else{
				out.print("{success:false,errorMsg:'" + errorMsg + "'}") ;
			}
			out.flush() ;
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{success:false,errorMsg:'系统错误'}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public HttpServletRequest getRequest(){
		return ServletActionContext.getRequest() ;
	}
	
	public HttpSession getSession(){
		return getRequest().getSession() ;
	}
	
	private HttpServletResponse getResponse(){
		return ServletActionContext.getResponse() ;
	}	
	
	public void setClassName(String className){
		this.className = className ;
	}
	
	public void setMethodName(String methodName){
		this.methodName = methodName ;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}
}
