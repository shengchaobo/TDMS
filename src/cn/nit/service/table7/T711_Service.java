package cn.nit.service.table7;

import java.util.Date;
import java.util.List;

import cn.nit.bean.table7.T711_Bean;
import cn.nit.dao.table7.T711_DAO;
import cn.nit.pojo.table7.T711POJO;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;



public class T711_Service {
	
	private T711_DAO teaManagerAwardInfoTeaTeaDAO=new T711_DAO();
	
	
	public boolean insert(T711_Bean teaManagerAwardInfoTeaTea){
		
		return teaManagerAwardInfoTeaTeaDAO.insert(teaManagerAwardInfoTeaTea);
		
	}
	
	public boolean update(T711_Bean t){
		return teaManagerAwardInfoTeaTeaDAO.update(t);
		
	}
	
	public boolean batchInsert(List<T711_Bean> list){
		return teaManagerAwardInfoTeaTeaDAO.batchInsert(list);
		
	}
	
	
	
	/**
	 * 得到该条数据审核状态
	 * @param 
	 * @return
	 */
	public int getCheckState(int seqNumber){
		return teaManagerAwardInfoTeaTeaDAO.getCheckState(seqNumber) ;
	}
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(int seqNum, int checkState){
		return teaManagerAwardInfoTeaTeaDAO.updateCheck(seqNum,checkState) ;
	}
	
	
	/**
	 * 全部审核通过
	 * @param 
	 * @return
	 */
	public boolean checkAll(){
		return teaManagerAwardInfoTeaTeaDAO.checkAll() ;
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
	
	 /**
		 * 获取字典表的所有数据(审核导出)
		 * @return
		 *
		 * @time: 2014-5-14/下午02:34:42
		 */
		public List<T711POJO> totalList(String fillUnitID,String year,int checkState){
			return teaManagerAwardInfoTeaTeaDAO.totalList(fillUnitID, year, checkState);
		}
	
  
	
	
	public static void main(String args[]){
		T711_Service ts=new T711_Service();
		ts.auditingData(null, null, 0, 0);
	}

}
