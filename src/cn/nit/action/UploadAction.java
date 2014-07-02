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
	
	private String selectYear;
	
	private int[] mergedCells;
	
	
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
			System.out.println(this.getSelectYear());
			
			List<Cell[]> list = ExcelUtil.readExcel(uploadFile, 0) ;
			Class<?> clazz = Class.forName(className) ;
			mergedCells=ExcelUtil.readMergedCells(uploadFile, 0,list.size()) ;

		
			Method method = clazz.getDeclaredMethod(methodName, List.class, HttpServletRequest.class, String.class,int[].class) ;
			String errorMsg = (String)method.invoke(clazz.newInstance(), list, getRequest(),this.getSelectYear(),mergedCells) ;
			
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
	
	public int[] getMergedCells() {
		return mergedCells;
	}

	public void setMergedCells(int[] mergedCells) {
		this.mergedCells = mergedCells;
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

	public void setSelectYear(String selectYear) {
		this.selectYear = selectYear;
	}

	public String getSelectYear() {
		return selectYear;
	}
}
