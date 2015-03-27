package cn.nit.service.table6;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;


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
	
	
	
	public List<T659_Bean> getYearInfo(String selectYear){
		List<T659_Bean> list = T659_dao.getYearInfo(selectYear);
		return list;
	}	
	
	/**
	 * 更新数据
	 * @param 
	 * @return
	 */
	public boolean update(T659_Bean bean){
		return T659_dao.update(bean) ;
	}
	
	/**
	 * 根据seqNumber找相应bean
	 * @param 
	 * @return
	 */
	public T659_Bean findBySeqNum (int seqNum){
		return T659_dao.findBySeqNum(seqNum) ;
	}
	
	/**
	 * 找当年总计bean
	 * @param 
	 * @return
	 */
	public T659_Bean findSumBean(String name, String year){
		return T659_dao.findSumBean(name, year) ;
	}
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(String selectYear, String unitName, int checkState){
		return T659_dao.updateCheck(selectYear, unitName, checkState) ;
	}
	
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T659_Bean> totalList(String year){
		return T659_dao.totalList(year);
	}
	

	
	public boolean batchInsert(List<T659_Bean> list){
		
		return T659_dao.batchInsert(list);
	}
	



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}



}
