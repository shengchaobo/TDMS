package cn.nit.service.table7;


import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import cn.nit.bean.table7.T731_Bean;
import cn.nit.dao.table7.T731_DAO;
import cn.nit.pojo.table7.T731POJO;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T731_Service {

	T731_DAO schleadInClassTnfoTeaDAO=new T731_DAO();
	
	public boolean insert(T731_Bean schleadInClassTnfo){
		return schleadInClassTnfoTeaDAO.insert(schleadInClassTnfo);
	}
	
	public boolean deleteByIds(String ids){
		return schleadInClassTnfoTeaDAO.deleteByIds(ids);
	}
	public boolean update(T731_Bean t731){
		return schleadInClassTnfoTeaDAO.update(t731);				
	}
	public boolean batchInsert(List<T731_Bean> list){
		return schleadInClassTnfoTeaDAO.batchInsert(list);
		
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
		int total=schleadInClassTnfoTeaDAO.totalAuditingData(conditions, fillUnitId);
		
		List<T731POJO> list=schleadInClassTnfoTeaDAO.auditingData(conditions, fillUnitId, page, rows);
		
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
