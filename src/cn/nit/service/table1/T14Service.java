package cn.nit.service.table1;

import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import cn.nit.dao.table1.T14DAO;
import cn.nit.pojo.table1.T14POJO;
import cn.nit.util.Pagition;

public class T14Service {


	/**  表T1-4的数据库操作类  */
	private T14DAO t14Dao = new T14DAO() ;

	
	/**
	 * 科研处
	 * */
	public String auditingData(String conditions, String fillUnitId, int page, int rows){
			
	    int total = t14Dao.totalAuditingData(conditions, fillUnitId) ;
		List<T14POJO> list = t14Dao.auditingData(conditions, fillUnitId, page, rows) ;
		Pagition pages = new Pagition(total, list) ;
		JSON json = JSONSerializer.toJSON(pages) ;	
		return json.toString() ;
		}

}
