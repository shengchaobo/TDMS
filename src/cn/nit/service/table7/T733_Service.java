package cn.nit.service.table7;


import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import cn.nit.bean.table7.T733_Bean;
import cn.nit.dao.table7.T733_DAO;
import cn.nit.pojo.table7.T733POJO;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T733_Service {
	
	T733_DAO t733_DAO=new T733_DAO();
	
	public boolean insert(T733_Bean eachUnit){
		
		return t733_DAO.insert(eachUnit);
	}
	public boolean deleteByIds(String ids){
		return t733_DAO.deleteByIds(ids);
	}
	public boolean update(T733_Bean t733){
		return t733_DAO.update(t733);				
	}
	public boolean batchInsert(List<T733_Bean> list){
		return t733_DAO.batchInsert(list);
		
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
		int total=t733_DAO.totalAuditingData(conditions, fillUnitId);
		
		List<T733POJO> list=t733_DAO.auditingData(conditions, fillUnitId, page, rows);
		
		Pagition pages=new Pagition(total, list);
		JSON json=JSONSerializer.toJSON(pages);
		
		return json.toString();
	
	}
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T733POJO> totalList(String year){	
		return t733_DAO.totalList(year);
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
