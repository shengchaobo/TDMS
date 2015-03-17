package cn.nit.service.table6;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import cn.nit.bean.table5.S512_Bean;
import cn.nit.bean.table6.T632_Bean;
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table6.T632_Dao;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T632_Service {
	
	/**  表632的数据库操作类  */
	private T632_Dao T632_dao = new T632_Dao() ;
	
	
	public List<T632_Bean> getYearInfo(String selectYear){
		List<T632_Bean> list = T632_dao.getYearInfo(selectYear);
		return list;
	}
	
	//插入一个bean
	public Boolean insert(T632_Bean bean, String year){
		return T632_dao.insert(bean, year);
	}
	
	/**
	 * 更新数据
	 * @param 
	 * @return
	 */
	public int update(T632_Bean bean, String year){
		return T632_dao.update(bean, year) ;
	}
	
	
	/**
	 * 删除数据
	 * @param 
	 * @return
	 */
	
	public boolean deleteByIds(String ids, String year){
		
		return T632_dao.deleteByIds(ids, year) ;
	}


	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(String selectYear, String unitName, int checkState){
		return T632_dao.updateCheck(selectYear, unitName, checkState) ;
	}
	

	
	public boolean batchInsert(List<T632_Bean> list){
		
		return T632_dao.batchInsert(list);
	}
	


	
	
	public List<T632_Bean> getPageInfoList(String rows, String page) {
		// TODO Auto-generated method stub
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T632_Bean> pageInfo = T632_dao.queryPageList(pagesize, currentpage);
		
		return pageInfo;	
	}

	public int getTotal() {
		// TODO Auto-generated method stub
		return T632_dao.getAllList().size();
	}

	public List<T632_Bean> getPageInfoList(String cond, Object object,
			String rows, String page) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T632_Bean> pageInfo = T632_dao.queryPageList(cond, object, pagesize, currentpage);
		
		return pageInfo;	
	}
	
	public int getTotal(String cond, Object object) {
		// TODO Auto-generated method stub
		return T632_dao.getAllList(cond, object).size();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 找出某年的总计信息
	 * */
	public T632_Bean getYearInfo(String year, String teaUnit){
		return T632_dao.getYearInfo(year,teaUnit);
	}



}
