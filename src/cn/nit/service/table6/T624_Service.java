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
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table6.T624_Dao;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T624_Service {
	
	/**  表624的数据库操作类  */
	private T624_Dao T624_dao = new T624_Dao() ;
	
	/**
	 * 表624的service的插入操作
	 * @param T624_Bean
	 * @return
	 *
	 * @time: 2014-6-12
	 */
	public boolean insert(T624_Bean  T624_bean){
		
		return T624_dao.insert(T624_bean);
	}
	
	public boolean batchInsert(List<T624_Bean> list){
		
		return T624_dao.batchInsert(list);
	}
	
	/**
	 * 更新数据
	 * @param undergraCSBaseTea {@link cn.nit.bean.table5.UndergraCSBaseTeaBean}实体类
	 * @return
	 */
	public boolean update(T624_Bean T624_bean){
		return T624_dao.update(T624_bean) ;
	}
	
	public boolean deleteItemsByIds(String ids){
		
		return T624_dao.deleteItemsByIds(ids) ;
	}
	
	
	public List<T624_Bean> getPageInfoList(String rows, String page) {
		// TODO Auto-generated method stub
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T624_Bean> pageInfo = T624_dao.queryPageList(pagesize, currentpage);
		
		return pageInfo;	
	}
	
	public List<T624_Bean> getPageInfoList(String cond, Object object,
			String rows, String page) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T624_Bean> pageInfo = T624_dao.queryPageList(cond, object, pagesize, currentpage);
		
		return pageInfo;	
	}
	
	public int getTotal(String cond, Object object) {
		// TODO Auto-generated method stub
		return T624_dao.getAllList(cond, object).size();
	}
	


	public int getTotal() {
		// TODO Auto-generated method stub
		return T624_dao.getAllList().size();
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}



}