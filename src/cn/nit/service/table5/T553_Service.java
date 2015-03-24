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
	 * 得到该条数据审核状态
	 * @param 
	 * @return
	 */
	public int getCheckState(int seqNumber){
		return t553_Dao.getCheckState(seqNumber) ;
	}
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(int seqNum, int checkState){
		return t553_Dao.updateCheck(seqNum,checkState) ;
	}
	
	
	/**
	 * 全部审核通过
	 * @param 
	 * @return
	 */
	public boolean checkAll(){
		return t553_Dao.checkAll() ;
	}
	
	/**按id删除数据*/
	public boolean deleteCoursesByIds(String ids){
		
		return t553_Dao.deleteCoursesByIds(ids) ;
	}
	 /**
 	 * 审核数据导出
 	 * @return
 	 *
 	 * @time: 2014-5-14/下午02:34:42
 	 */
 	public List<T553POJO> totalList(String year,int checkState){
 		return t553_Dao.totalList(year, checkState);
 	}
	
  
	
	
	
}
