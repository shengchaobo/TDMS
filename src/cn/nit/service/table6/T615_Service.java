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
import cn.nit.bean.table6.T621_Bean;
import cn.nit.bean.table6.T624_Bean;
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table6.T611_Dao;
import cn.nit.dao.table6.T613_Dao;
import cn.nit.dao.table6.T614_Dao;
import cn.nit.dao.table6.T615_Dao;
import cn.nit.dao.table6.T624_Dao;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T615_Service {
	
	/**  表614的数据库操作类  */
	private T615_Dao T615_Dao = new T615_Dao() ;
	
	/**
	 * 表614的service的插入操作
	 * @param T614_Bean
	 * @return
	 *
	 * @time: 2014-6-12
	 */
	public boolean insert(T615_Bean  T615_Bean){
		
		return T615_Dao.insert(T615_Bean);
	}
	
	public boolean batchInsert(List<T615_Bean> list){
		
		return T615_Dao.batchInsert(list);
	}
	
	/**
	 * 更新数据
	 * @param undergraCSBaseTea {@link cn.nit.bean.table5.UndergraCSBaseTeaBean}实体类
	 * @return
	 */
	public boolean update(T615_Bean T615_Bean){
		return T615_Dao.update(T615_Bean) ;
	}
	
	public boolean deleteItemsByIds(String ids){
		
		return T615_Dao.deleteItemsByIds(ids) ;
	}
	
	
	public List<T615_Bean> getPageInfoList(String rows, String page) {
		// TODO Auto-generated method stub
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T615_Bean> pageInfo = T615_Dao.queryPageList(pagesize, currentpage);
		
		return pageInfo;	
	}
	
	public List<T615_Bean> getPageInfoList(String cond, Object object,
			String rows, String page) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T615_Bean> pageInfo = T615_Dao.queryPageList(cond, object, pagesize, currentpage);
		
		return pageInfo;	
	}
	
	public int getTotal(String cond, Object object) {
		// TODO Auto-generated method stub
		return T615_Dao.getAllList(cond, object).size();
	}
	


	public int getTotal() {
		// TODO Auto-generated method stub
		return T615_Dao.getAllList().size();
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}



}
