package cn.nit.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

public class DownloadModelAction {

	public String fileName ;

	public InputStream getInputStream(){

		String path = this.getClass().getResource("/" + fileName).getPath();
		InputStream inputStream = null ;

		try {
			inputStream = new FileInputStream(new File( path)) ;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null ;
		}

		return inputStream ;
	}

	public String execute() throws Exception{

		ServletActionContext.getRequest().setCharacterEncoding("UTF-8") ;
		System.out.println("fileName=============" + fileName) ;
		return "success" ;
	}

	public String getFileName(){
		return this.fileName ;
	}

	public void setFileName(String fileName){
		this.fileName = fileName ;
	}
	
}
