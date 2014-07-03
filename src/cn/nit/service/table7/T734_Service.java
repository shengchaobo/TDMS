package cn.nit.service.table7;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import cn.nit.bean.table7.T734_Bean;
import cn.nit.dao.table7.T734_DAO;
import cn.nit.pojo.table7.T734POJO;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T734_Service {
	
	T734_DAO t734_DAO=new T734_DAO();
	
	public boolean insert(T734_Bean teachAccident){
		
		return t734_DAO.insert(teachAccident);
	}
	public boolean deleteByIds(String ids){
		return t734_DAO.deleteByIds(ids);
	}
	public boolean update(T734_Bean t734){
		return t734_DAO.update(t734);				
	}
	public boolean batchInsert(List<T734_Bean> list){
		return t734_DAO.batchInsert(list);
		
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
		int total=t734_DAO.totalAuditingData(conditions, fillUnitId);
		
		List<T734POJO> list=t734_DAO.auditingData(conditions, fillUnitId, page, rows);
		
		Pagition pages=new Pagition(total, list);
		JSON json=JSONSerializer.toJSON(pages);
		
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
	
	

}
