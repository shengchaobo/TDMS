package cn.nit.service.table5;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import cn.nit.bean.table5.T512_Bean;
import cn.nit.dao.table5.T512_DAO;
import cn.nit.pojo.table5.T512POJO;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T512_Service {
	
	private T512_DAO t512_Dao=new T512_DAO();
	

	public boolean insert(T512_Bean t512){
		
		return t512_Dao.insert(t512);
		
	}
	
	public boolean update(T512_Bean t){
		return t512_Dao.update(t);
		
	}
	
	public boolean batchInsert(List<T512_Bean> list){
		return t512_Dao.batchInsert(list);
		
	}
	
	
	/**
	 * 获取正在审核的数据
	 * @param conditions  查询条件
	 * @param fillUnitId  教学单位ID号
	 * @param page        当前页
	 * @param rows        页面显示的条数
	 * @return
	 */
	public String auditingData(String conditions,String fillUnitId,int page,int rows){
		
		int total=t512_Dao.totalAuditingData(conditions, fillUnitId);
		
		List<T512POJO> list=t512_Dao.auditingData(conditions, fillUnitId, page, rows);
		
		Pagition pages=new Pagition(total, list);
		JSON json=JSONSerializer.toJSON(pages);
		
		System.out.println(json.toString());

		return json.toString();
		
	}
	/**
	 * 生成查条件
	 * @param seqNum
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	
	public String generateauditingConditions(int seqNum,Date startTime,Date endTime){
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
	
	return sql.toString();
			
	}
	
	/**按id删除数据*/
	public boolean deleteCoursesByIds(String ids){
		
		return t512_Dao.deleteCoursesByIds(ids) ;
	}
	
  
	
	
	
}
