package cn.nit.service.table3;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;


import cn.nit.bean.table3.T321_Bean;
import cn.nit.dao.table3.T321_DAO;


import cn.nit.pojo.table3.T321POJO;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;







public class T321_Service {
	
	/**  表511的数据库操作类  */
	private T321_DAO t321_DAO = new T321_DAO() ;
	
	/**
	 * 表511的service的插入操作
	 * @param undergraCSBaseTea
	 * @return
	 *
	 * @time: 2014-5-14/上午10:52:05
	 */
	public boolean insert(T321_Bean t321_Bean){
		
		return t321_DAO.insert(t321_Bean) ;
	}
	
	public boolean batchInsert(List<T321_Bean> list){
		
		return t321_DAO.batchInsert(list) ;
	}
	
	public String auditingData(String conditions, String fillDept, int page, int rows){
		
	    int total = t321_DAO.totalAuditingData(conditions, fillDept) ;
		List<T321POJO> list = t321_DAO.auditingData(conditions, fillDept, page, rows) ;
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
	public boolean update(T321_Bean t321_Bean){
//	    this.setAudit(t151Bean) ;
		return t321_DAO.update(t321_Bean) ;
	}
	
	public boolean deleteCoursesByIds(String ids){
		
		return t321_DAO.deleteCoursesByIds(ids) ;
	}
	



}
