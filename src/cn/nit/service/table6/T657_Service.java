package cn.nit.service.table6;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import cn.nit.bean.table6.T621_Bean;
import cn.nit.bean.table6.T624_Bean;
import cn.nit.bean.table6.T641_Bean;
import cn.nit.bean.table6.T651_Bean;
import cn.nit.bean.table6.T652_Bean;
import cn.nit.bean.table6.T653_Bean;
import cn.nit.bean.table6.T654_Bean;
import cn.nit.bean.table6.T655_Bean;
import cn.nit.bean.table6.T657_Bean;
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table6.T624_Dao;
import cn.nit.dao.table6.T641_Dao;
import cn.nit.dao.table6.T651_Dao;
import cn.nit.dao.table6.T652_Dao;
import cn.nit.dao.table6.T653_Dao;
import cn.nit.dao.table6.T654_Dao;
import cn.nit.dao.table6.T655_Dao;
import cn.nit.dao.table6.T657_Dao;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T657_Service {
	
	/**  表624的数据库操作类  */
	private T657_Dao T657_dao = new T657_Dao() ;
	

	
	/**
	 * 更新数据
	 * @param 
	 * @return
	 */
	public boolean update(T657_Bean bean){
		return T657_dao.update(bean) ;
	}
	
	/**
	 * 根据seqNumber找相应bean
	 * @param 
	 * @return
	 */
	public T657_Bean findBySeqNum (int seqNum){
		return T657_dao.findBySeqNum(seqNum) ;
	}
	
	/**
	 * 找当年总计bean
	 * @param 
	 * @return
	 */
	public T657_Bean findSumBean(String name, String year){
		return T657_dao.findSumBean(name, year) ;
	}
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(String selectYear, String unitName, int checkState){
		return T657_dao.updateCheck(selectYear, unitName, checkState) ;
	}
	
	public boolean batchInsert(List<T657_Bean> list){
		
		return T657_dao.batchInsert(list);
	}
	
	
	
	public boolean deleteItemsByIds(String ids){
		
		return T657_dao.deleteItemsByIds(ids) ;
	}
	
	/**
	 * 获取字典表的所有数据(导出)
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T657_Bean> totalList(String year){
		return T657_dao.totalList(year);
	}
	
	public List<T657_Bean> totalList(String year,int checkState){
		return T657_dao.totalList(year,checkState);
	}
	
	
	public List<T657_Bean> getPageInfoList(String rows, String page) {
		// TODO Auto-generated method stub
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T657_Bean> pageInfo = T657_dao.queryPageList(pagesize, currentpage);
		
		return pageInfo;	
	}
	
	public List<T657_Bean> getPageInfoList(String cond, Object object,
			String rows, String page) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T657_Bean> pageInfo = T657_dao.queryPageList(cond, object, pagesize, currentpage);
		
		return pageInfo;	
	}
	
	public int getTotal(String cond, Object object) {
		// TODO Auto-generated method stub
		return T657_dao.getAllList(cond, object).size();
	}
	


	public int getTotal() {
		// TODO Auto-generated method stub
		return T657_dao.getAllList().size();
	}
	
	public double getQualifiedRate(String year){
		return T657_dao.getQualifiedRate(year);
	}
	
	public double getTestReachRate(String year){
		return T657_dao.getTestReachRate(year);
	}
	
	public List<T657_Bean> getYearInfo(String selectYear){
		
		List<T657_Bean> list=T657_dao.getYearInfo(selectYear);
	
		//System.out.println(list.toString());
         
		return list;				
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}



}
