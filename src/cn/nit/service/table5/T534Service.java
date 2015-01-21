package cn.nit.service.table5;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import cn.nit.bean.table5.T534Bean;
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table5.T534DAO;
import cn.nit.pojo.table5.T534POJO;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T534Service {
	
	/**  表T534的数据库操作类  */
	private T534DAO t534Dao = new T534DAO() ;
	
	/**
	 * 表T534的service的插入操作
	 * @param undergraCSBaseTea
	 * @return
	 *
	 * @time: 2014-5-14/上午10:52:05
	 */
	public boolean insert(T534Bean t534Bean){
		
		return t534Dao.insert(t534Bean) ;
	}
	
	
	public String auditingData(String conditions, String fillUnitId, int page, int rows){
			
	    int total = t534Dao.totalAuditingData(conditions, fillUnitId) ;
		List<T534POJO> list = t534Dao.auditingData(conditions, fillUnitId, page, rows) ;
		Pagition pages = new Pagition(total, list) ;
		JSON json = JSONSerializer.toJSON(pages) ;	
		return json.toString() ;
		}
	
	/**
	 * 更新数据
	 * @param t551Bean {@link cn.nit.bean.table1.T151Bean}实体类
	 * @return
	 */
	public boolean update(T534Bean t534Bean){
//	    this.setAudit(t151Bean) ;
		return t534Dao.update(t534Bean) ;
	}
	
	
	/**按id删除数据*/
	public boolean deleteCoursesByIds(String ids){
		
		return t534Dao.deleteCoursesByIds(ids) ;
	}
	
	/**批量导入*/
	public boolean batchInsert(List<T534Bean> list){
		
		return t534Dao.batchInsert(list) ;
	}
	
	/**
	 * 得到该条数据审核状态
	 * @param 
	 * @return
	 */
	public int getCheckState(int seqNumber){
		return t534Dao.getCheckState(seqNumber) ;
	}
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(int seqNum, int checkState){
		return t534Dao.updateCheck(seqNum,checkState) ;
	}
	
	
	/**
	 * 全部审核通过
	 * @param 
	 * @return
	 */
	public boolean checkAll(){
		return t534Dao.checkAll() ;
	}



}
