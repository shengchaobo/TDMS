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
	
	private void setAudit(T533Bean t533Bean){
		
		String audit = DIResourceDAO.getAudit(t533Dao.getTableName()) ;
		
		String audits[] = audit.split(",") ;
//		t151Bean.setAudit(audits[0]) ;
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
			sql.append(" and SeqNumber=" + seqNum) ;
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
