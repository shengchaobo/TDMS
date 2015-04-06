package cn.nit.service.table3;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;


import cn.nit.bean.table3.T321_Bean;
import cn.nit.dao.table3.T321_DAO;


import cn.nit.pojo.table3.T321POJO;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;


public class T321_Service {
	
	/**  表511的数据库操作类  */
	private T321_DAO t321_DAO = new T321_DAO() ;
	
	/**
	 * 表511的service的插入操作
	 * @param undergraCSBaseTea
	 * @return
	 *
	 * @time: 2014-5-14/上午10:52:05
	 */
	public boolean insert(T321_Bean t321_Bean){
		
		return t321_DAO.insert(t321_Bean) ;
	}
	
	public boolean batchInsert(List<T321_Bean> list){
		
		return t321_DAO.batchInsert(list) ;
	}
	
	public String auditingData(String conditions, String fillDept, int page, int rows){
		
	    int total = t321_DAO.totalAuditingData(conditions, fillDept) ;
		List<T321_Bean> list = t321_DAO.auditingData(conditions, fillDept, page, rows) ;
		Pagition pages = new Pagition(total, list) ;
//		System.out.println("total:"+total);
//		System.out.println("list:"+list.size());
		JSON json = JSONSerializer.toJSON(pages) ;
			
//		System.out.println(json.toString()) ;
			
		return json.toString() ;
		}
	

	
	/**
	 * 更新数据
	 * @param undergraCSBaseTea {@link cn.nit.bean.table5.UndergraCSBaseTeaBean}实体类
	 * @return
	 */
	public boolean update(T321_Bean t321_Bean){
//	    this.setAudit(t151Bean) ;
		return t321_DAO.update(t321_Bean) ;
	}
	
	public boolean deleteCoursesByIds(String ids){
		
		return t321_DAO.deleteCoursesByIds(ids) ;
	}
	
	/**
	 * 得到该条数据审核状态
	 * @param 
	 * @return
	 */
	public int getCheckState(int seqNumber){
		return t321_DAO.getCheckState(seqNumber) ;
	}
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(int seqNum, int checkState){
		return t321_DAO.updateCheck(seqNum,checkState) ;
	}
	
	
	/**
	 * 全部审核通过
	 * @param 
	 * @return
	 */
	public boolean checkAll(){
		return t321_DAO.checkAll() ;
	}
	
	/**用于审核数据导出*/
	public List<T321_Bean> totalList(String year, int checkState){
		return t321_DAO.totalList(year, checkState);
	}




}
