package cn.nit.service.table3;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;


import cn.nit.bean.table3.A321_Bean;
import cn.nit.dao.table3.A321_DAO;
import cn.nit.util.TimeUtil;

public class A321_Service {
	
	

	
	/**  表311的数据库操作类  */
	private A321_DAO a321_DAO = new A321_DAO() ;

	

   	String str=null;


   	
   	public  List<A321_Bean> auditingData(String year){
   		
   		List<A321_Bean> list = a321_DAO.getOriData(year);
   		return list;
   	}
   	
	/**用于数据导出*/
	public List<A321_Bean> totalList(String year){
		return a321_DAO.totalList(year);
	}
	


}
