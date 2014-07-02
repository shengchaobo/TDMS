package cn.nit.service.table7;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import cn.nit.bean.table7.T711_Bean;
import cn.nit.bean.table7.T712_Bean;

import cn.nit.dao.table7.T712_DAO;
import cn.nit.pojo.table7.T712POJO;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T712_Service {
	
	T712_DAO  t712_dao=new T712_DAO();
	
	public boolean insert(T712_Bean teaManagerPaperInfoTeaTea){
		
		return t712_dao.insert(teaManagerPaperInfoTeaTea);
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
		int total=t712_dao.totalAuditingData(conditions, fillUnitId);
		
		List<T712POJO> list=t712_dao.auditingData(conditions, fillUnitId, page, rows);
		
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
	
	public boolean deleteByIds(String ids){
	    
		return  t712_dao.deleteByIds(ids);
	}
	
	public boolean update(T712_Bean t){
		return t712_dao.update(t);
		
	}
	public boolean batchInsert(List<T712_Bean> list){
		return t712_dao.batchInsert(list);
		
	}

}
