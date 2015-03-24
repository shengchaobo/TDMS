package cn.nit.service.table6;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import cn.nit.bean.table6.T631_Bean;
import cn.nit.bean.table6.T632_Bean;
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table6.T631_Dao;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T631_Service {
	
	/**  表631的数据库操作类  */
	private T631_Dao T631_dao = new T631_Dao() ;
	
	public List<T631_Bean> getYearInfo(String selectYear){
		List<T631_Bean> list = T631_dao.getYearInfo(selectYear);
		return list;
	}
	
	//插入一个bean
	public Boolean insert(T631_Bean bean, String year){
		return T631_dao.insert(bean, year);
	}
	
	/**
	 * 更新数据
	 * @param 
	 * @return
	 */
	public int update(T631_Bean bean, String year){
		return T631_dao.update(bean, year) ;
	}
	
	
	/**
	 * 删除数据
	 * @param 
	 * @return
	 */
	
	public boolean deleteByIds(String ids, String year){
		
		return T631_dao.deleteByIds(ids, year) ;
	}


	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(String selectYear, String unitName, int checkState){
		return T631_dao.updateCheck(selectYear, unitName, checkState) ;
	}
	public boolean batchInsert(List<T631_Bean> list){
		
		return T631_dao.batchInsert(list);
	}
	
	public List<T631_Bean> getPageInfoList(String rows, String page) {
		// TODO Auto-generated method stub
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T631_Bean> pageInfo = T631_dao.queryPageList(pagesize, currentpage);
		
		return pageInfo;	
	}

	public int getTotal() {
		// TODO Auto-generated method stub
		return T631_dao.getAllList(null,null).size();
	}

	public List<T631_Bean> getPageInfoList(String cond, Object object,
			String rows, String page) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T631_Bean> pageInfo = T631_dao.queryPageList(cond, object, pagesize, currentpage);
		
		return pageInfo;	
	}
	
	public int getTotal(String cond, Object object) {
		// TODO Auto-generated method stub
		return T631_dao.getAllList(cond, object).size();
	}
	
	/**
	 * 找出某年的总计信息
	 * */
	public T631_Bean getYearInfo(String year, String teaUnit){
		return T631_dao.getYearInfo(year,teaUnit);
	}
	
	public List<T631_Bean> getAllList(String cond, Object object) {
		return T631_dao.getAllList(cond, object);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}



}
