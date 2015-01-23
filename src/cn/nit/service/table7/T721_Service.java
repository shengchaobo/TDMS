package cn.nit.service.table7;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;


import cn.nit.bean.table7.T721_Bean;
import cn.nit.dao.table7.T721_DAO;
import cn.nit.pojo.table7.T721POJO;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T721_Service {

	T721_DAO teachResItemTeaDAO=new T721_DAO();
	
	public boolean insert(T721_Bean teachResItemTea){
		
		return teachResItemTeaDAO.insert(teachResItemTea);
		
	}
	
	public boolean deleteByIds(String ids){
		return teachResItemTeaDAO.deleteByIds(ids);
	}
	public boolean update(T721_Bean t721){
		return teachResItemTeaDAO.update(t721);
				
	}
	public boolean batchInsert(List<T721_Bean> list){
		return teachResItemTeaDAO.batchInsert(list);
		
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
		int total=teachResItemTeaDAO.totalAuditingData(conditions, fillUnitId);
		
		List<T721POJO> list=teachResItemTeaDAO.auditingData(conditions, fillUnitId, page, rows);
		
		Pagition pages=new Pagition(total, list);
		JSON json=JSONSerializer.toJSON(pages);
		
		return json.toString();
	
	}
	
	/**
	 * 得到该条数据审核状态
	 * @param 
	 * @return
	 */
	public int getCheckState(int seqNumber){
		return teachResItemTeaDAO.getCheckState(seqNumber) ;
	}
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(int seqNum, int checkState){
		return teachResItemTeaDAO.updateCheck(seqNum,checkState) ;
	}
	
	
	/**
	 * 全部审核通过
	 * @param 
	 * @return
	 */
	public boolean checkAll(){
		return teachResItemTeaDAO.checkAll() ;
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
