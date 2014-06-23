package cn.nit.service.table1;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import cn.nit.bean.table1.T12Bean;
import cn.nit.dao.table1.T12DAO;
import cn.nit.pojo.table1.T12POJO;
import cn.nit.pojo.table1.T151POJO;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T12Service {
	

	/**  表T1-2的数据库操作类  */
	private T12DAO t12Dao = new T12DAO() ;
//	
//	/**
//	 * 表T1-2的service的插入操作
//	 * @param undergraCSBaseTea
//	 * @return
//	 *
//	 * @time: 2014-5-14/上午10:52:05
////	 */
//	public boolean insert(T12Bean t12Bean){
//		
//		return t12Dao.insert(t12Bean) ;
//	}
	
	/**
	 * 科研处
	 * */
	public String auditingData(String conditions, String fillUnitId, int page, int rows){
			
	    int total = t12Dao.totalAuditingData(conditions, fillUnitId) ;
		List<T12POJO> list = t12Dao.auditingData(conditions, fillUnitId, page, rows) ;
		Pagition pages = new Pagition(total, list) ;
		JSON json = JSONSerializer.toJSON(pages) ;	
		return json.toString() ;
		}
	
	public static void main(String arg[]){
		T12Service ser=new T12Service();
		String info=ser.auditingData(null, "1001", 1, 1);
		System.out.println(info);
	}

	
	

}
