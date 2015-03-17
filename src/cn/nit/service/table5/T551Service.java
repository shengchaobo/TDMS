package cn.nit.service.table5;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import cn.nit.bean.table5.T551_Bean;
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table5.T551DAO;


import cn.nit.pojo.table5.T551POJO;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T551Service {
	
	/**  表T551的数据库操作类  */
	private T551DAO t551Dao = new T551DAO() ;
	
	/**
	 * 表T551的service的插入操作
	 * @param undergraCSBaseTea
	 * @return
	 *
	 * @time: 2014-5-14/上午10:52:05
	 */
	public boolean insert(T551_Bean t551Bean){
		
		return t551Dao.insert(t551Bean) ;
	}
	
	
	public String auditingData(String conditions, String fillUnitId, int page, int rows){
			
	    int total = t551Dao.totalAuditingData(conditions, fillUnitId) ;
		List<T551POJO> list = t551Dao.auditingData(conditions, fillUnitId, page, rows) ;
		Pagition pages = new Pagition(total, list) ;
		JSON json = JSONSerializer.toJSON(pages) ;	
		return json.toString() ;
		}
	
	/**
	 * 更新数据
	 * @param t551Bean {@link cn.nit.bean.table1.T151_Bean}实体类
	 * @return
	 */
	public boolean update(T551_Bean t551Bean){
//	    this.setAudit(t151Bean) ;
		return t551Dao.update(t551Bean) ;
	}
	
	private void setAudit(T551_Bean t551Bean){
		
		String audit = DIResourceDAO.getAudit(t551Dao.getTableName()) ;
		
		String audits[] = audit.split(",") ;
//		t151Bean.setAudit(audits[0]) ;
	}
	
	/**按id删除数据*/
	public boolean deleteCoursesByIds(String ids){
		
		return t551Dao.deleteCoursesByIds(ids) ;
	}
	
	/**批量导入*/
	public boolean batchInsert(List<T551_Bean> list){
		
		return t551Dao.batchInsert(list) ;
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
	
	public static void main(String arg[]){
		T551Service ser=new T551Service();
		String info=ser.auditingData(null, null, 1, 2);
		System.out.println(info);
	}


}
