package cn.nit.service.table1;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import cn.nit.bean.table1.T11Bean;
import cn.nit.bean.table1.T152Bean;
import cn.nit.bean.table5.T54_Bean;
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table1.T11DAO;

import cn.nit.pojo.table1.T11POJO;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T11Service {

	/**  表T1-1的数据库操作类  */
	private T11DAO t11Dao = new T11DAO() ;
	
	/**
	 * 表T1-1的service的插入操作
	 * @param undergraCSBaseTea
	 * @return
	 *
	 * @time: 2014-5-14/上午10:52:05
	 */
	public boolean insert(T11Bean t11Bean){
		
		return t11Dao.insert(t11Bean) ;
	}
	
	/**
	 * 科研处
	 * */
	public T11Bean loadData(String year){
			
		T11Bean bean = t11Dao.loadData(year) ;
//		T11POJO t11Pojo=list.get(0);
//		System.out.println(t11Pojo.);
//		System.out.println("total:"+total);
//		System.out.println("list:"+list.size());
//		JSON json = JSONSerializer.toJSON(bean) ;
			
//		System.out.println(json.toString()) ;
			
		return bean;
		}
	
	/**
	 * 科研处
	 * */
	public String auditingData(String year){
			
		List<T11POJO> list = t11Dao.auditingData(year) ;
		T11POJO t11Pojo=list.get(0);
//		System.out.println(t11Pojo.);
//		System.out.println("total:"+total);
//		System.out.println("list:"+list.size());
		JSON json = JSONSerializer.toJSON(t11Pojo) ;
			
//		System.out.println(json.toString()) ;
			
		return json.toString() ;
		}
	
	
	//保存
	public Boolean save(T11Bean bean, String year,	String fields){
		return t11Dao.save(bean,year,fields);
	}
	
	/**
	 * 更新数据
	 * @param undergraCSBaseTea {@link cn.nit.bean.table5.UndergraCSBaseTeaBean}实体类
	 * @return
	 */
	public boolean update(T11Bean t11Bean){
//	    this.setAudit(t151Bean) ;
		return t11Dao.update(t11Bean) ;
	}
	
//	private void setAudit(T11Bean t11Bean){
//		
//		String audit = DIResourceDAO.getAudit(t11Dao.getTableName()) ;
//		
//		String audits[] = audit.split(",") ;
//		t11Bean.setAudit(audits[0]) ;
//	}
	
	/**按id删除数据*/
	public boolean deleteCoursesByIds(String ids){
		
		return t11Dao.deleteCoursesByIds(ids) ;
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
	
	/**批量导入*/
	public boolean batchInsert(List<T11Bean> list){
		
		return t11Dao.batchInsert(list) ;
	}
   
	
	public static void main(String arg[])
	{
		T11Service ser=new T11Service();
//		String info=ser.auditingData("2014") ;
//		System.out.println(info);
	}
	
}
