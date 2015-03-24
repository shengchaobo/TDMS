package cn.nit.service.table5;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import cn.nit.bean.table5.T511_Bean;
import cn.nit.dao.table5.T511_DAO;
import cn.nit.pojo.table5.T511POJO;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T511_Service {
	
	private T511_DAO t511_Dao=new T511_DAO();
	

	public boolean insert(T511_Bean t511){
		
		return t511_Dao.insert(t511);
		
	}
	
	public boolean update(T511_Bean t){
		return t511_Dao.update(t);
		
	}
	
	public boolean batchInsert(List<T511_Bean> list){
		return t511_Dao.batchInsert(list);
		
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
		
		int total=t511_Dao.totalAuditingData(conditions, fillUnitId);
		
		List<T511POJO> list=t511_Dao.auditingData(conditions, fillUnitId, page, rows);
		
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
		return t511_Dao.getCheckState(seqNumber) ;
	}
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(int seqNum, int checkState){
		return t511_Dao.updateCheck(seqNum,checkState) ;
	}
	
	
	/**
	 * 全部审核通过
	 * @param 
	 * @return
	 */
	public boolean checkAll(){
		return t511_Dao.checkAll() ;
	}
	
	/**按id删除数据*/
	public boolean deleteCoursesByIds(String ids){
		
		return t511_Dao.deleteCoursesByIds(ids) ;
	}
	
	 /**
 	 * 审核数据导出
 	 * @return
 	 *
 	 * @time: 2014-5-14/下午02:34:42
 	 */
 	public List<T511POJO> totalList(String year,int checkState){
 		return t511_Dao.totalList(year, checkState);
 	}
	
  
	
	
	
}
