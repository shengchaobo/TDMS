package cn.nit.service.table7;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import cn.nit.bean.table5.UndergraCSBaseTeaBean;
import cn.nit.bean.table7.T711_Bean;
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table7.T711_DAO;
import cn.nit.pojo.table7.T711POJO;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T711_Service {
	
	private T711_DAO teaManagerAwardInfoTeaTeaDAO=new T711_DAO();
	
	
	public boolean insert(T711_Bean teaManagerAwardInfoTeaTea){
		
		return teaManagerAwardInfoTeaTeaDAO.insert(teaManagerAwardInfoTeaTea);
		
	}
	
	public boolean update(T711_Bean t){
		return teaManagerAwardInfoTeaTeaDAO.update(t);
		
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
		
		int total=teaManagerAwardInfoTeaTeaDAO.totalAuditingData(conditions, fillUnitId);
		
		List<T711POJO> list=teaManagerAwardInfoTeaTeaDAO.auditingData(conditions, fillUnitId, page, rows);
		
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
		
		return teaManagerAwardInfoTeaTeaDAO.deleteCoursesByIds(ids) ;
	}
	
private void setAudit(T711_Bean t){
		
		String audit = DIResourceDAO.getAudit(teaManagerAwardInfoTeaTeaDAO.getTableName()) ;
		
		String audits[] = audit.split(",") ;
		t.setAudit(audits[0]) ;
	}
	
	
	public static void main(String args[]){
		T711_Service ts=new T711_Service();
		ts.auditingData(null, null, 0, 0);
	}

}
