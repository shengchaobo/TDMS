package cn.nit.service.table5;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import cn.nit.bean.table5.T531_Bean;
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table5.T531DAO;

import cn.nit.pojo.table5.T531POJO;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T531Service {
	
	/**  表T531的数据库操作类  */
	private T531DAO t531Dao = new T531DAO() ;
	
	/**
	 * 表T531的service的插入操作
	 * @param undergraCSBaseTea
	 * @return
	 *
	 * @time: 2014-5-14/上午10:52:05
	 */
	public boolean insert(T531_Bean t531Bean){
		
		return t531Dao.insert(t531Bean) ;
	}
	
	
	public String auditingData(String conditions, String fillUnitId, int page, int rows){
			
	    int total = t531Dao.totalAuditingData(conditions, fillUnitId) ;
		List<T531POJO> list = t531Dao.auditingData(conditions, fillUnitId, page, rows) ;
		Pagition pages = new Pagition(total, list) ;
		JSON json = JSONSerializer.toJSON(pages) ;	
		return json.toString() ;
		}
	
	/**
	 * 更新数据
	 * @param t531Bean {@link cn.nit.bean.table1.T151_Bean}实体类
	 * @return
	 */
	public boolean update(T531_Bean t531Bean){
//	    this.setAudit(t151Bean) ;
		return t531Dao.update(t531Bean) ;
	}
	
	
	/**按id删除数据*/
	public boolean deleteCoursesByIds(String ids){
		
		return t531Dao.deleteCoursesByIds(ids) ;
	}
	
	/**批量导入*/
	public boolean batchInsert(List<T531_Bean> list){
		
		return t531Dao.batchInsert(list) ;
	}
	
	/**
	 * 得到该条数据审核状态
	 * @param 
	 * @return
	 */
	public int getCheckState(int seqNumber){
		return t531Dao.getCheckState(seqNumber) ;
	}
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(int seqNum, int checkState){
		return t531Dao.updateCheck(seqNum,checkState) ;
	}
	
	
	/**
	 * 全部审核通过
	 * @param 
	 * @return
	 */
	public boolean checkAll(){
		return t531Dao.checkAll() ;
	}


}
