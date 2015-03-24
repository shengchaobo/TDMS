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
	
	T624_Dao T624_Dao=new T624_Dao();
	
	public List<T624_Bean> getYearInfo(String selectYear){
		List<T624_Bean> list = T624_Dao.getYearInfo(selectYear);
		return list;
	}
	
	//插入一个bean
	public Boolean insert(T624_Bean bean, String year){
		return T624_Dao.insert(bean, year);
	}
	
	/**
	 * 更新数据
	 * @param 
	 * @return
	 */
	public int update(T624_Bean bean, String year){
		return T624_Dao.update(bean, year) ;
	}
	
	
	/**
	 * 删除数据
	 * @param 
	 * @return
	 */
	
	public boolean deleteByIds(String ids, String year){
		
		return T624_Dao.deleteByIds(ids, year) ;
	}


	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(String selectYear, String unitName, int checkState){
		return T624_Dao.updateCheck(selectYear, unitName, checkState) ;
	}
	public boolean batchInsert(List<T624_Bean> list){
		
		return T624_Dao.batchInsert(list);
	}
	
	/**导出*/
	public List<T624_Bean> getAllList(String cond, Object object) {
		return T624_Dao.getAllList(cond, object);
	}



}
