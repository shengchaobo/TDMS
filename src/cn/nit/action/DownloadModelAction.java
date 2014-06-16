package cn.nit.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.struts2.ServletActionContext;

public class DownloadModelAction {

	public String fileName ;
	public String saveFile;

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
		System.out.println("saveFile=============" + saveFile) ;
		return "success" ;
	}

	public String getFileName(){
		return this.fileName ;
	}

	public void setFileName(String fileName){
		this.fileName = fileName ;
	}

	public String getSaveFile() {

		try {
			this.saveFile = URLEncoder.encode(saveFile, "UTF-8");
			//this.saveFile = new String(saveFile.getBytes("ISO-8859-1"),"UTF-8");// 中文乱码解决
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return saveFile;
	}

	public void setSaveFile(String saveFile) {
		this.saveFile = saveFile;
	}
	
}
