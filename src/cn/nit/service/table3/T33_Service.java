package cn.nit.service.table3;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;



import cn.nit.bean.table3.T33_Bean;
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table3.T33_DAO;


import cn.nit.pojo.table3.T33POJO;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;



public class T33_Service {
	
	/**  表311的数据库操作类  */
	private T33_DAO t33_DAO = new T33_DAO() ;
	
	/**
	 * 表511的service的插入操作
	 * @param undergraCSBaseTea
	 * @return
	 *
	 * @time: 2014-5-14/上午10:52:05
	 */
	public boolean insert(T33_Bean t33_Bean){
		
		return t33_DAO.insert(t33_Bean) ;
	}
	
	public boolean batchInsert(List<T33_Bean> list){
		
		return t33_DAO.batchInsert(list) ;
	}
	
	public String auditingData(String conditions, String fillDept, int page, int rows){
		
	    int total = t33_DAO.totalAuditingData(conditions, fillDept) ;
		List<T33POJO> list = t33_DAO.auditingData(conditions, fillDept, page, rows) ;
		Pagition pages = new Pagition(total, list) ;
//		System.out.println("total:"+total);
//		System.out.println("list:"+list.size());
		JSON json = JSONSerializer.toJSON(pages) ;
			
//		System.out.println(json.toString()) ;
		System.out.print(json.toString());
		return json.toString() ;
		
		}
	

	
	/**
	 * 更新数据
	 * @param undergraCSBaseTea {@link cn.nit.bean.table5.UndergraCSBaseTeaBean}实体类
	 * @return
	 */
	public boolean update(T33_Bean T33Bean){
//	    this.setAudit(t151Bean) ;
		return t33_DAO.update(T33Bean) ;
	}
	
	public boolean deleteCoursesByIds(String ids){
		
		return t33_DAO.deleteCoursesByIds(ids) ;
	}
	

	
	public static void main(String args[]){
		T33_Service unser = new T33_Service() ;
		unser.auditingData(null, null, 1, 10) ;
	}

}
