package cn.nit.service.table5;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import cn.nit.bean.table5.T533Bean;
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table5.T533DAO;
import cn.nit.pojo.table5.T533POJO;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T533Service {
	
	/**  表T533的数据库操作类  */
	private T533DAO t533Dao = new T533DAO() ;
	
	/**
	 * 表T533的service的插入操作
	 * @param undergraCSBaseTea
	 * @return
	 *
	 * @time: 2014-5-14/上午10:52:05
	 */
	public boolean insert(T533Bean t533Bean){
		
		return t533Dao.insert(t533Bean) ;
	}
	
	
	public String auditingData(String conditions, String fillUnitId, int page, int rows){
			
	    int total = t533Dao.totalAuditingData(conditions, fillUnitId) ;
		List<T533POJO> list = t533Dao.auditingData(conditions, fillUnitId, page, rows) ;
		Pagition pages = new Pagition(total, list) ;
		JSON json = JSONSerializer.toJSON(pages) ;	
		return json.toString() ;
		}
	
	/**
	 * 更新数据
	 * @param t551Bean {@link cn.nit.bean.table1.T151Bean}实体类
	 * @return
	 */
	public boolean update(T533Bean t533Bean){
//	    this.setAudit(t151Bean) ;
		return t533Dao.update(t533Bean) ;
	}
	
	
	
	/**按id删除数据*/
	public boolean deleteCoursesByIds(String ids){
		
		return t533Dao.deleteCoursesByIds(ids) ;
	}
	
	/**批量导入*/
	public boolean batchInsert(List<T533Bean> list){
		
		return t533Dao.batchInsert(list) ;
	}
	
	/**
	 * 得到该条数据审核状态
	 * @param 
	 * @return
	 */
	public int getCheckState(int seqNumber){
		return t533Dao.getCheckState(seqNumber) ;
	}
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(int seqNum, int checkState){
		return t533Dao.updateCheck(seqNum,checkState) ;
	}
	
	
	/**
	 * 全部审核通过
	 * @param 
	 * @return
	 */
	public boolean checkAll(){
		return t533Dao.checkAll() ;
	}



}
