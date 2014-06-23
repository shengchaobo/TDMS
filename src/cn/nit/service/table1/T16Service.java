package cn.nit.service.table1;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import cn.nit.bean.table1.T16Bean;
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table1.T16DAO;

import cn.nit.pojo.table1.T16POJO;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T16Service {
	
	/**  表T1-6的数据库操作类  */
	private T16DAO t16Dao = new T16DAO() ;
	
	/**
	 * 表T1-6的service的插入操作
	 * @param undergraCSBaseTea
	 * @return
	 *
	 * @time: 2014-5-14/上午10:52:05
	 */
	public boolean insert(T16Bean t16Bean){
		
		return t16Dao.insert(t16Bean) ;
	}
	
	/**
	 * 科研处
	 * */
	public String auditingData(String Year){
			
//	    int total = t16Dao.totalAuditingData(conditions, fillUnitId) ;
		List<T16POJO> list = t16Dao.auditingData1(Year) ;
		String str=null;
		if(list.isEmpty())
		{
			return str;
		}else
		{
			T16POJO t16Pojo=list.get(0);
			JSON json = JSONSerializer.toJSON(t16Pojo) ;
				
			return json.toString() ;
		}
			
  }
	
	/**
	 * 更新数据
	 * @param undergraCSBaseTea {@link cn.nit.bean.table5.UndergraCSBaseTeaBean}实体类
	 * @return
	 */
	public boolean update(T16Bean t16Bean){
//	    this.setAudit(t16Bean) ;
		return t16Dao.update(t16Bean) ;
	}
	
	private void setAudit(T16Bean t16Bean){
		
		String audit = DIResourceDAO.getAudit(t16Dao.getTableName()) ;
		
		String audits[] = audit.split(",") ;
	}
	
	/**按id删除数据*/
	public boolean deleteCoursesByIds(String ids){
		
		return t16Dao.deleteCoursesByIds(ids) ;
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
  public static void main(String arg[])
  {
	  Date currentTi=new Date();
		String str=currentTi.toString();
		String Year=str.substring(str.length()-4, str.length()) ;
		T16Service ser=new T16Service();
		String str1=ser.auditingData(Year);
		System.out.println(str1);
		
  }

}
