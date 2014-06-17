package cn.nit.service.table3;

import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import cn.nit.bean.table3.T312_Bean;
import cn.nit.dao.table3.T312_DAO;

import cn.nit.pojo.table3.T312POJO;
import cn.nit.util.Pagition;





public class T312_Service {

private T312_DAO docAndGraStaDao = new T312_DAO() ;


	
	/**
	 * 加载所有的课程类别数据字典表的内容
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
public boolean insert(T312_Bean docAndGraStaBean){
	
	return docAndGraStaDao.insert(docAndGraStaBean) ;
}

public String auditingData(String conditions, String fillDept, int page, int rows){
	
    int total = docAndGraStaDao.totalAuditingData(conditions, fillDept) ;
	List<T312POJO> list = docAndGraStaDao.auditingData(conditions, fillDept, page, rows) ;
	Pagition pages = new Pagition(total, list) ;
//	System.out.println("total:"+total);
//	System.out.println("list:"+list.size());
	JSON json = JSONSerializer.toJSON(pages) ;
		
//	System.out.println(json.toString()) ;
		
	return json.toString() ;
	}

     public static void main(String arg[]){
    	 T312_Service ser=new T312_Service();
    	 String info=ser.auditingData(null, null, 1, 9);
    	 System.out.println(info);
     }
}
