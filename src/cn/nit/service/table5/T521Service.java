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
	
	private void setAudit(T521Bean t522Bean){
		
		String audit = DIResourceDAO.getAudit(t521Dao.getTableName()) ;
		
		String audits[] = audit.split(",") ;
//		t151Bean.setAudit(audits[0]) ;
	}
	
	/**按id删除数据*/
	public boolean deleteCoursesByIds(String ids){
		
		return t521Dao.deleteCoursesByIds(ids) ;
	}
	
	/**批量导入*/
	public boolean batchInsert(List<T521Bean> list){
		
		return t521Dao.batchInsert(list) ;
	}
	/**
	 * 生成查条件
	 * @param seqNum
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public String gernateAuditingConditions(int seqNum, Date startTime, Date endTime){
		
		if(seqNum == 0 && startTime == null && endTime == null){
			return null ;
		}
		
		StringBuffer sql = new StringBuffer() ;
		
		if(seqNum != 0){
			sql.append(" SeqNumber=" + seqNum) ;
		}
		
		if(startTime != null){
			sql.append(" and cast(CONVERT(DATE, Time)as datetime)>=cast(CONVERT(DATE, '" 
					+ TimeUtil.changeFormat4(startTime) + "')as datetime)") ;
		}
		
		if(endTime != null){
			sql.append(" and cast(CONVERT(DATE, Time)as datetime)>=cast(CONVERT(DATE, '" 
					+ TimeUtil.changeFormat4(endTime) + "')as datetime)") ;
		}
		
		return sql.toString() ;
	}
	

}
