package cn.nit.service.table3;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;


import cn.nit.bean.table3.T311_Bean;
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table3.T311_DAO;

import cn.nit.pojo.table3.T311POJO;

import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;






public class T311_Service {
	
	/**  表311的数据库操作类  */
	private T311_DAO postDocStaDao = new T311_DAO() ;
	
	/**
	 * 表511的service的插入操作
	 * @param undergraCSBaseTea
	 * @return
	 *
	 * @time: 2014-5-14/上午10:52:05
	 */
	public boolean insert(T311_Bean postDocStaBean){
		
		return postDocStaDao.insert(postDocStaBean) ;
	}
	
	public boolean batchInsert(List<T311_Bean> list){
		
		return postDocStaDao.batchInsert(list) ;
	}
	
	public String auditingData(String conditions, String fillDept, int page, int rows){
		
	    int total = postDocStaDao.totalAuditingData(conditions, fillDept) ;
		List<T311POJO> list = postDocStaDao.auditingData(conditions, fillDept, page, rows) ;
		System.out.println("替换");
		System.out.println(list.get(0).getUnitName());	
		System.out.println("怎么样");
		Pagition pages = new Pagition(total, list) ;
		System.out.println("total:"+total);

	
		JSON json = JSONSerializer.toJSON(pages) ;
	
			
//		System.out.println(json.toString()) ;
		System.out.println(json.toString());
		return json.toString() ;
		}
	
	
	/**
	 * 更新数据
	 * @param undergraCSBaseTea {@link cn.nit.bean.table5.UndergraCSBaseTeaBean}实体类
	 * @return
	 */
	public boolean update(T311_Bean t311Bean){
//	    this.setAudit(t151Bean) ;
		return postDocStaDao.update(t311Bean) ;
	}
	
	public boolean deleteCoursesByIds(String ids){
		
		return postDocStaDao.deleteCoursesByIds(ids) ;
	}
	
	/**
	 * 统计博士流动站的数量
	 * @param undergraCSBaseTea {@link cn.nit.bean.table5.UndergraCSBaseTeaBean}实体类
	 * @return
	 */
	public int getStationNum(String year){		
		return postDocStaDao.getStationNum(year) ;
	}

	
	public static void main(String args[]){
		T311_Service unser = new T311_Service() ;
		unser.auditingData(null, null, 1, 10) ;
	}


}
