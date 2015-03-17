package cn.nit.service.table1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import cn.nit.bean.table1.T172_Bean;
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table1.T172DAO;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

/**表1-7-2操作类*/
public class T172Service {
	
	/**  表T1-7-2的数据库操作类  */
	private T172DAO t172Dao = new T172DAO() ;
	
	/**
	 * 表T1-5的service的插入操作
	 * @param undergraCSBaseTea
	 * @return
	 *
	 * @time: 2014-5-14/上午10:52:05
	 */
	public boolean insert(T172_Bean t172Bean){
		
		return t172Dao.insert(t172Bean) ;
	}
	
	
	public String auditingData(String conditions, String fillUnitId, int page, int rows){
			
	    int total = t172Dao.totalAuditingData(conditions, fillUnitId) ;
		List<T172_Bean> list = t172Dao.auditingData(conditions, fillUnitId, page, rows) ;
		Pagition pages = new Pagition(total, list) ;
//		System.out.println("total:"+total);
//		System.out.println("list:"+list.size());
		JSON json = JSONSerializer.toJSON(pages) ;
			
//		System.out.println(json.toString()) ;
			
		return json.toString() ;
		}
	
	/**
	 * 更新数据
	 * @param undergraCSBaseTea {@link cn.nit.bean.table5.UndergraCSBaseTeaBean}实体类
	 * @return
	 */
	public boolean update(T172_Bean t172Bean){
//	    this.setAudit(t151Bean) ;
		return t172Dao.update(t172Bean) ;
	}
	
//	private void setAudit(T17Bean t17Bean){
//		
//		String audit = DIResourceDAO.getAudit(t17Dao.getTableName()) ;
//		
//		String audits[] = audit.split(",") ;
//		t17Bean.setAudit(audits[0]) ;
//	}
	
	/**导入数据*/
	public boolean batchInsert(List<T172_Bean> list){
			
			return t172Dao.batchInsert(list) ;
		}
	
	/**按id删除数据*/
	public boolean deleteCoursesByIds(String ids){
		
		return t172Dao.deleteCoursesByIds(ids) ;
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
	
	public static void main(String arh[]) throws ParseException
	{
		T17Service ser=new T17Service();
		String info=ser.auditingData(null, null, 1, 4);
		System.out.println(info);
	
		
	}


}
