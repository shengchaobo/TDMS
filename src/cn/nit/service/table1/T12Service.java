package cn.nit.service.table1;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import cn.nit.bean.table1.T12_Bean;
import cn.nit.bean.table1.T151_Bean;
import cn.nit.dao.table1.T12DAO;
import cn.nit.dao.table1.T13DAO;
import cn.nit.dao.table1.T14DAO;
import cn.nit.pojo.table1.T12POJO;
import cn.nit.pojo.table1.T151POJO;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T12Service {
	

	/**  表T1-2的数据库操作类  */
	private T12DAO t12Dao = new T12DAO() ;
	/**  表T1-3的数据库操作类  */
	private T13DAO t13Dao = new T13DAO() ;
	/**  表T1-4的数据库操作类  */
	private T14DAO t14Dao = new T14DAO() ;
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
	 * 科研处(显示数据)
	 * */
	public String auditingData(String conditions, String fillUnitId, int page, int rows){
			
	    int total = t12Dao.totalAuditingData(conditions, fillUnitId) ;
		List<T12POJO> list = t12Dao.auditingData(conditions, fillUnitId, page, rows) ;
		Pagition pages = new Pagition(total, list) ;
		JSON json = JSONSerializer.toJSON(pages) ;	
		return json.toString() ;
	}
	
	/**批量导入*/
	public boolean batchInsert(List<T12_Bean> list){
		
		return t12Dao.batchInsert(list) ;
	}
	
	/**
	 * 获得的总数（用于导出）T12
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T12_Bean> totalList(){
		return t12Dao.totalList();
	}
	
	/**
	 * 获得的总数（用于导出）T13
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T12_Bean> totalList1(){
		return t13Dao.totalList();
	}
	
	/**
	 * 获得的总数（用于导出）T14
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T12_Bean> totalList2(){
		return t14Dao.totalList();
	}
	
	
	
	public static void main(String arg[]){
		T12Service ser=new T12Service();
		String info=ser.auditingData(null, "10", 1, 10);
		System.out.println(info);
	}

	
	

}
