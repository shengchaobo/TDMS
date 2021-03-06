package cn.nit.service.table6;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import cn.nit.bean.table6.T651_Bean;
import cn.nit.bean.table6.T656_Bean;
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table6.T656_Dao;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T656_Service {
	
	/**  表624的数据库操作类  */
	private T656_Dao T656_dao = new T656_Dao() ;
	
	
	/**
	 * 更新数据
	 * @param 
	 * @return
	 */
	public boolean update(T656_Bean bean){
		return T656_dao.update(bean) ;
	}
	
	/**
	 * 根据seqNumber找相应bean
	 * @param 
	 * @return
	 */
	public T656_Bean findBySeqNum (int seqNum){
		return T656_dao.findBySeqNum(seqNum) ;
	}
	
	/**
	 * 找当年总计bean
	 * @param 
	 * @return
	 */
	public T656_Bean findSumBean(String name, String year){
		return T656_dao.findSumBean(name, year) ;
	}
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(String selectYear, String unitName, int checkState){
		return T656_dao.updateCheck(selectYear, unitName, checkState) ;
	}
	
	/**
	 * 表624的service的插入操作
	 * @param T656_Bean
	 * @return
	 *
	 * @time: 2014-6-12
	 */
	public boolean insert(T656_Bean  T656_bean){
		
		return T656_dao.insert(T656_bean);
	}
	
	public boolean batchInsert(List<T656_Bean> list){
		
		return T656_dao.batchInsert(list);
	}
	
	
	public boolean deleteItemsByIds(String ids){
		
		return T656_dao.deleteItemsByIds(ids) ;
	}
	
	
	public List<T656_Bean> getPageInfoList(String rows, String page) {
		// TODO Auto-generated method stub
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T656_Bean> pageInfo = T656_dao.queryPageList(pagesize, currentpage);
		
		return pageInfo;	
	}
	
	public List<T656_Bean> getPageInfoList(String cond, Object object,
			String rows, String page) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T656_Bean> pageInfo = T656_dao.queryPageList(cond, object, pagesize, currentpage);
		
		return pageInfo;	
	}
	
	public int getTotal(String cond, Object object) {
		// TODO Auto-generated method stub
		return T656_dao.getAllList(cond, object).size();
	}
	


	public int getTotal() {
		// TODO Auto-generated method stub
		return T656_dao.getAllList().size();
	}
	
	public double getNPassRate(String year){
		return T656_dao.getNPassRate(year);
	}
	
	public List<T656_Bean> getYearInfo(String selectYear){
		
		List<T656_Bean> list=T656_dao.getYearInfo(selectYear);
	
		//System.out.println(list.toString());
         
		return list;				
	}
	
	/**
	 * 获取字典表的所有数据（导出）
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T656_Bean> totalList(String year){
		return T656_dao.totalList(year);
	}

	
	public List<T656_Bean> totalList(String year,int checkState){
		return T656_dao.totalList(year,checkState);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}



}
