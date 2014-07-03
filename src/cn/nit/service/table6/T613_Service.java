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
import cn.nit.bean.table6.T621_Bean;
import cn.nit.bean.table6.T624_Bean;
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table6.T611_Dao;
import cn.nit.dao.table6.T613_Dao;
import cn.nit.dao.table6.T624_Dao;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T613_Service {
	
	/**  表613的数据库操作类  */
	private T613_Dao T613_Dao = new T613_Dao() ;
	
	/**
	 * 表613的service的插入操作
	 * @param T624_Bean
	 * @return
	 *
	 * @time: 2014-6-12
	 */
	public boolean insert(T613_Bean  T613_Bean){
		
		return T613_Dao.insert(T613_Bean);
	}
	
	public boolean batchInsert(List<T613_Bean> list){
		
		return T613_Dao.batchInsert(list);
	}
	
	/**
	 * 更新数据
	 * @param undergraCSBaseTea {@link cn.nit.bean.table5.UndergraCSBaseTeaBean}实体类
	 * @return
	 */
	public boolean update(T613_Bean T613_Bean){
		return T613_Dao.update(T613_Bean) ;
	}
	
	public boolean deleteItemsByIds(String ids){
		
		return T613_Dao.deleteItemsByIds(ids) ;
	}
	
	
	public List<T613_Bean> getPageInfoList(String rows, String page) {
		// TODO Auto-generated method stub
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T613_Bean> pageInfo = T613_Dao.queryPageList(pagesize, currentpage);
		
		return pageInfo;	
	}
	
	public List<T613_Bean> getPageInfoList(String cond, Object object,
			String rows, String page) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T613_Bean> pageInfo = T613_Dao.queryPageList(cond, object, pagesize, currentpage);
		
		return pageInfo;	
	}
	
	public int getTotal(String cond, Object object) {
		// TODO Auto-generated method stub
		return T613_Dao.getAllList(cond, object).size();
	}
	


	public int getTotal() {
		// TODO Auto-generated method stub
		return T613_Dao.getAllList().size();
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}



}
