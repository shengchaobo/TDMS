package cn.nit.service.table5;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import cn.nit.bean.table5.T553_Bean;
import cn.nit.dao.table5.T553_DAO;
import cn.nit.pojo.table5.T553POJO;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T553_Service {
	
	private T553_DAO t553_Dao=new T553_DAO();
	

	public boolean insert(T553_Bean t553){
		
		return t553_Dao.insert(t553);
		
	}
	
	public boolean update(T553_Bean t){
		return t553_Dao.update(t);
		
	}
	
	public boolean batchInsert(List<T553_Bean> list){
		return t553_Dao.batchInsert(list);
		
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
		
		int total=t553_Dao.totalAuditingData(conditions, fillUnitId);
		
		List<T553POJO> list=t553_Dao.auditingData(conditions, fillUnitId, page, rows);
		
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
		
		return t553_Dao.deleteCoursesByIds(ids) ;
	}
	
  
	
	
	
}
