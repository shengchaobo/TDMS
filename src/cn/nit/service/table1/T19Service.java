package cn.nit.service.table1;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import cn.nit.bean.table1.T19Bean;
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table1.T19DAO;

import cn.nit.pojo.table1.T19POJO;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T19Service {
	
	/**  表T1-9的数据库操作类  */
	private T19DAO t19Dao = new T19DAO() ;
	
	/**
	 * 表T1-9的service的插入操作
	 * @param undergraCSBaseTea
	 * @return
	 *
	 * @time: 2014-5-14/上午10:52:05
	 */
	public boolean insert(T19Bean t19Bean){
		
		return t19Dao.insert(t19Bean) ;
	}
	
	/**
	 * 科研处
	 * */
	public String auditingData(String conditions, String fillUnitId, int page, int rows){
			
	    int total = t19Dao.totalAuditingData(conditions, fillUnitId) ;
		List<T19POJO> list = t19Dao.auditingData(conditions, fillUnitId, page, rows) ;
		Pagition pages = new Pagition(total, list) ;

		JSON json = JSONSerializer.toJSON(pages) ;	
		return json.toString() ;
		}
	
	/**
	 * 更新数据
	 * @param t151Bean {@link cn.nit.bean.table1.T151Bean}实体类
	 * @return
	 */
	public boolean update(T19Bean t19Bean){
//	    this.setAudit(t151Bean) ;
		return t19Dao.update(t19Bean) ;
	}
	
//	private void setAudit(T19Bean t19Bean){
//		
//		String audit = DIResourceDAO.getAudit(t19Dao.getTableName()) ;
//		
//		String audits[] = audit.split(",") ;
//		t19Bean.setAudit(audits[0]) ;
//	}
//	
	/**按id删除数据*/
	public boolean deleteCoursesByIds(String ids){
		
		return t19Dao.deleteCoursesByIds(ids) ;
	}
	
	/**批量导入*/
	public boolean batchInsert(List<T19Bean> list){
		
		return t19Dao.batchInsert(list) ;
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
     public static void  main(String arg[]){
    	 T19Service ser=new T19Service();
    	 String info=ser.auditingData(null, null, 1, 8);
    	 System.out.println(info);
     }

}
