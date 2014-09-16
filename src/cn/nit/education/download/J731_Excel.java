package cn.nit.education.download;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.nit.service.table7.T721_Service;

public class J731_Excel {
	public static boolean export_J731(){
		T721_Service T721_Sr=new T721_Service();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		String year = dateFormat.format(new Date());
		return true;
	} 

}
