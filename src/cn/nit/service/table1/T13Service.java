package cn.nit.service.table1;

import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import cn.nit.dao.table1.T13DAO;
import cn.nit.pojo.table1.T13POJO;
import cn.nit.util.Pagition;

public class T13Service {
	
	

	/**  表T1-3的数据库操作类  */
	private T13DAO t13Dao = new T13DAO() ;

	
	/**
	 * 科研处
	 * */
	public String auditingData(String conditions, String fillUnitId, int page, int rows){
			
	    int total = t13Dao.totalAuditingData(conditions, fillUnitId) ;
		List<T13POJO> list = t13Dao.auditingData(conditions, fillUnitId, page, rows) ;
		Pagition pages = new Pagition(total, list) ;
		JSON json = JSONSerializer.toJSON(pages) ;	
		return json.toString() ;
		}

}
