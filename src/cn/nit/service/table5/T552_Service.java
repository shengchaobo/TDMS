package cn.nit.service.table5;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import cn.nit.bean.table5.T552_Bean;
import cn.nit.dao.table5.T552_DAO;
import cn.nit.pojo.table5.T552POJO;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T552_Service {
	
	private T552_DAO t552_Dao=new T552_DAO();
	

	public boolean insert(T552_Bean t552){
		
		return t552_Dao.insert(t552);
		
	}
	
	public boolean update(T552_Bean t){
		return t552_Dao.update(t);
		
	}
	
	public boolean batchInsert(List<T552_Bean> list){
		return t552_Dao.batchInsert(list);
		
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
		
		int total=t552_Dao.totalAuditingData(conditions, fillUnitId);
		
		List<T552POJO> list=t552_Dao.auditingData(conditions, fillUnitId, page, rows);
		
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
		return t552_Dao.getCheckState(seqNumber) ;
	}
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(int seqNum, int checkState){
		return t552_Dao.updateCheck(seqNum,checkState) ;
	}
	
	
	/**
	 * 全部审核通过
	 * @param 
	 * @return
	 */
	public boolean checkAll(){
		return t552_Dao.checkAll() ;
	}
	
	/**按id删除数据*/
	public boolean deleteCoursesByIds(String ids){
		
		return t552_Dao.deleteCoursesByIds(ids) ;
	}
	
  
	
	
	
}
