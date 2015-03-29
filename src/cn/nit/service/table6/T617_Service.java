package cn.nit.service.table6;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import cn.nit.bean.table6.T611_Bean;
import cn.nit.bean.table6.T613_Bean;
import cn.nit.bean.table6.T614_Bean;
import cn.nit.bean.table6.T615_Bean;
import cn.nit.bean.table6.T617_Bean;
import cn.nit.bean.table6.T621_Bean;
import cn.nit.bean.table6.T624_Bean;
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table6.T611_Dao;
import cn.nit.dao.table6.T613_Dao;
import cn.nit.dao.table6.T614_Dao;
import cn.nit.dao.table6.T615_Dao;
import cn.nit.dao.table6.T617_Dao;
import cn.nit.dao.table6.T624_Dao;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T617_Service {
	
	/**  表614的数据库操作类  */
	private T617_Dao T617_Dao = new T617_Dao() ;
	
	
	
	public List<T617_Bean> getYearInfo(String selectYear){
		List<T617_Bean> list = T617_Dao.getYearInfo(selectYear);
		return list;
	}
	
	//插入一个bean
	public Boolean insert(T617_Bean bean, String year){
		return T617_Dao.insert(bean, year);
	}
	
	/**
	 * 更新数据
	 * @param 
	 * @return
	 */
	public int update(T617_Bean bean, String year){
		return T617_Dao.update(bean, year) ;
	}
	
	
	/**
	 * 删除数据
	 * @param 
	 * @return
	 */
	
	public boolean deleteByIds(String ids, String year){
		
		return T617_Dao.deleteByIds(ids, year) ;
	}

	/**
	 * 获得某年的捐赠总计
	 * 
	 */
	public double getYearSumDona(String year){
		return T617_Dao.getYearSumDona(year);
	}
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(String selectYear, String unitName, int checkState){
		return T617_Dao.updateCheck(selectYear, unitName, checkState) ;
	}
	
	
	
	public List<T617_Bean> getPageInfoList(String rows, String page) {
		// TODO Auto-generated method stub
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T617_Bean> pageInfo = T617_Dao.queryPageList(pagesize, currentpage);
		
		return pageInfo;	
	}
	
	public List<T617_Bean> getPageInfoList(String cond, Object object,
			String rows, String page) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T617_Bean> pageInfo = T617_Dao.queryPageList(cond, object, pagesize, currentpage);
		
		return pageInfo;	
	}
	
	public int getTotal(String cond, Object object) {
		// TODO Auto-generated method stub
		return T617_Dao.getAllList(cond, object).size();
	}
	


	public int getTotal() {
		// TODO Auto-generated method stub
		return T617_Dao.getAllList().size();
	}
	
	public boolean batchInsert(List<T617_Bean> list){
		
		return T617_Dao.batchInsert(list);
	}
	
	 /**
		 * 获取字典表的所有数据(导出)
		 * @return
		 *
		 * @time: 2014-5-14/下午02:34:42
		 */
		public List<T617_Bean> totalList(String year,int checkstate){
			return T617_Dao.totalList(year,checkstate);
		}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}



}
