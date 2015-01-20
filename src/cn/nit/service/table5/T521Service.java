package cn.nit.service.table5;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import cn.nit.bean.table5.T521Bean;
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table5.T521DAO;

import cn.nit.pojo.table5.T521POJO;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T521Service {
	

	/**  表T521的数据库操作类  */
	private T521DAO t521Dao = new T521DAO() ;
	
	/**
	 * 表T522的service的插入操作
	 * @param undergraCSBaseTea
	 * @return
	 *
	 * @time: 2014-5-14/上午10:52:05
	 */
	public boolean insert(T521Bean t521Bean){
		
		return t521Dao.insert(t521Bean) ;
	}
	
	
	public String auditingData(String conditions, String fillUnitId, int page, int rows){
			
	    int total = t521Dao.totalAuditingData(conditions, fillUnitId) ;
		List<T521POJO> list = t521Dao.auditingData(conditions, fillUnitId, page, rows) ;
		Pagition pages = new Pagition(total, list) ;
		JSON json = JSONSerializer.toJSON(pages) ;	
		return json.toString() ;
		}
	
	/**
	 * 更新数据
	 * @param t522Bean {@link cn.nit.bean.table1.T151Bean}实体类
	 * @return
	 */
	public boolean update(T521Bean t521Bean){
//	    this.setAudit(t151Bean) ;
		return t521Dao.update(t521Bean) ;
	}
	
	
	/**按id删除数据*/
	public boolean deleteCoursesByIds(String ids){
		
		return t521Dao.deleteCoursesByIds(ids) ;
	}
	
	/**
	 * 得到该条数据审核状态
	 * @param 
	 * @return
	 */
	public int getCheckState(int seqNumber){
		return t521Dao.getCheckState(seqNumber) ;
	}
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(int seqNum, int checkState){
		return t521Dao.updateCheck(seqNum,checkState) ;
	}
	
	
	/**
	 * 全部审核通过
	 * @param 
	 * @return
	 */
	public boolean checkAll(){
		return t521Dao.checkAll() ;
	}
	
	/**批量导入*/
	public boolean batchInsert(List<T521Bean> list){
		
		return t521Dao.batchInsert(list) ;
	}
	

}
