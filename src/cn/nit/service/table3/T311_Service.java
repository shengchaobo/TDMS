package cn.nit.service.table3;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;


import cn.nit.bean.table3.T311_Bean;
import cn.nit.dao.table3.T311_DAO;

import cn.nit.pojo.table3.T311POJO;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;






public class T311_Service {
	
	/**  表311的数据库操作类  */
	private T311_DAO postDocStaDao = new T311_DAO() ;
	
	/**
	 * 表511的service的插入操作
	 * @param undergraCSBaseTea
	 * @return
	 *
	 * @time: 2014-5-14/上午10:52:05
	 */
	public boolean insert(T311_Bean postDocStaBean){
		
		return postDocStaDao.insert(postDocStaBean) ;
	}
	
	public String auditingData(String conditions, String fillDept, int page, int rows){
		
	    int total = postDocStaDao.totalAuditingData(conditions, fillDept) ;
		List<T311POJO> list = postDocStaDao.auditingData(conditions, fillDept, page, rows) ;
		Pagition pages = new Pagition(total, list) ;
//		System.out.println("total:"+total);
//		System.out.println("list:"+list.size());
		JSON json = JSONSerializer.toJSON(pages) ;
			
//		System.out.println(json.toString()) ;
			
		return json.toString() ;
		}
	
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
	
	/**
	 * 更新数据
	 * @param undergraCSBaseTea {@link cn.nit.bean.table5.UndergraCSBaseTeaBean}实体类
	 * @return
	 */
	public boolean update(T311_Bean t311Bean){
//	    this.setAudit(t151Bean) ;
		return postDocStaDao.update(t311Bean) ;
	}
	
	public boolean deleteCoursesByIds(String ids){
		
		return postDocStaDao.deleteCoursesByIds(ids) ;
	}


}
