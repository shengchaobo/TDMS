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
import cn.nit.bean.table6.T658_Bean;
import cn.nit.bean.table6.T659_Bean;
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table6.T624_Dao;
import cn.nit.dao.table6.T641_Dao;
import cn.nit.dao.table6.T651_Dao;
import cn.nit.dao.table6.T652_Dao;
import cn.nit.dao.table6.T653_Dao;
import cn.nit.dao.table6.T654_Dao;
import cn.nit.dao.table6.T655_Dao;
import cn.nit.dao.table6.T657_Dao;
import cn.nit.dao.table6.T658_Dao;
import cn.nit.dao.table6.T659_Dao;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T659_Service {
	
	/**  表624的数据库操作类  */
	private T659_Dao T659_dao = new T659_Dao() ;
	
	/**
	 * 表624的service的插入操作
	 * @param T659_Bean
	 * @return
	 *
	 * @time: 2014-6-12
	 */
	public boolean insert(T659_Bean  T659_bean){
		
		return T659_dao.insert(T659_bean);
	}
	
	public boolean batchInsert(List<T659_Bean> list){
		
		return T659_dao.batchInsert(list);
	}
	
	/**
	 * 更新数据
	 * @param undergraCSBaseTea {@link cn.nit.bean.table5.UndergraCSBaseTeaBean}实体类
	 * @return
	 */
	public boolean update(T659_Bean T659_bean){
		return T659_dao.update(T659_bean) ;
	}
	
	public boolean deleteItemsByIds(String ids){
		
		return T659_dao.deleteItemsByIds(ids) ;
	}
	
	
	public List<T659_Bean> getPageInfoList(String rows, String page) {
		// TODO Auto-generated method stub
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T659_Bean> pageInfo = T659_dao.queryPageList(pagesize, currentpage);
		
		return pageInfo;	
	}
	
	public List<T659_Bean> getPageInfoList(String cond, String filledID,
			String rows, String page) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T659_Bean> pageInfo = T659_dao.queryPageList(cond, filledID, pagesize, currentpage);
		
		return pageInfo;	
	}
	
	public int getTotal(String cond, String fillUnitID) {
		// TODO Auto-generated method stub
		return T659_dao.getAllList(cond, fillUnitID).size();
	}
	


	public int getTotal() {
		// TODO Auto-generated method stub
		return T659_dao.getAllList().size();
	}
	
	public List<T659_Bean> getAllList(String cond, String fillUnitID) {
		return T659_dao.getAllList(cond, fillUnitID);
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}



}
