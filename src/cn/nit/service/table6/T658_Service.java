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
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T658_Service {
	
	/**  表624的数据库操作类  */
	private T658_Dao T658_dao = new T658_Dao() ;
	
	/**
	 * 表624的service的插入操作
	 * @param T658_Bean
	 * @return
	 *
	 * @time: 2014-6-12
	 */
	public boolean insert(T658_Bean  T658_bean){
		
		return T658_dao.insert(T658_bean);
	}
	
	public boolean batchInsert(List<T658_Bean> list){
		
		return T658_dao.batchInsert(list);
	}
	
	/**
	 * 更新数据
	 * @param undergraCSBaseTea {@link cn.nit.bean.table5.UndergraCSBaseTeaBean}实体类
	 * @return
	 */
	public boolean update(T658_Bean T658_bean){
		return T658_dao.update(T658_bean) ;
	}
	
	public boolean deleteItemsByIds(String ids){
		
		return T658_dao.deleteItemsByIds(ids) ;
	}
	
	
	public List<T658_Bean> getPageInfoList(String rows, String page) {
		// TODO Auto-generated method stub
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T658_Bean> pageInfo = T658_dao.queryPageList(pagesize, currentpage);
		
		return pageInfo;	
	}
	
	public List<T658_Bean> getPageInfoList(String cond, String filledID,
			String rows, String page) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T658_Bean> pageInfo = T658_dao.queryPageList(cond, filledID, pagesize, currentpage);
		
		return pageInfo;	
	}
	
	public int getTotal(String cond, Object object) {
		// TODO Auto-generated method stub
		return T658_dao.getAllList(cond, object).size();
	}
	


	public int getTotal() {
		// TODO Auto-generated method stub
		return T658_dao.getAllList().size();
	}
	
	public int getInterConference(String year){
		return T658_dao.getInterConference(year);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}



}
