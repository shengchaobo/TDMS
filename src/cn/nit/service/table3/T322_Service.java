package cn.nit.service.table3;

import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import cn.nit.bean.table3.T322_Bean;
import cn.nit.dao.table3.T322_DAO;
import cn.nit.pojo.table3.T322POJO;
import cn.nit.util.Pagition;

public class T322_Service {
	
	/**  表311的数据库操作类  */
	private T322_DAO t322_Dao = new T322_DAO() ;
	
	/**
	 * 表511的service的插入操作
	 * @param undergraCSBaseTea
	 * @return
	 *
	 * @time: 2014-5-14/上午10:52:05
	 */
	public boolean insert(T322_Bean t322_Bean){
		
		return t322_Dao.insert(t322_Bean) ;
	}
	
	public boolean batchInsert(List<T322_Bean> list){
		
		return t322_Dao.batchInsert(list) ;
	}
	
	public String auditingData(String conditions, String fillDept, int page, int rows){
		
	    int total = t322_Dao.totalAuditingData(conditions, fillDept) ;
		List<T322POJO> list = t322_Dao.auditingData(conditions, fillDept, page, rows) ;
	
		System.out.println("怎么样");
		Pagition pages = new Pagition(total, list) ;
		System.out.println("total:"+total);

	
		JSON json = JSONSerializer.toJSON(pages) ;

		System.out.println(json.toString());
		return json.toString() ;
		}
	
	
	/**
	 * 更新数据
	 * @param undergraCSBaseTea {@link cn.nit.bean.table5.UndergraCSBaseTeaBean}实体类
	 * @return
	 */
	public boolean update(T322_Bean t322_Bean){
//	    this.setAudit(t151Bean) ;
		return t322_Dao.update(t322_Bean) ;
	}
	
	public boolean deleteCoursesByIds(String ids){
		
		return t322_Dao.deleteCoursesByIds(ids) ;
	}
	

	
	public static void main(String args[]){
		T311_Service unser = new T311_Service() ;
		unser.auditingData(null, null, 1, 10) ;
	}

}
