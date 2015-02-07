package cn.nit.service.table6;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import cn.nit.bean.table6.T617_Bean;
import cn.nit.bean.table6.T621_Bean;
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table6.T621_Dao;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T621_Service {
	
	/**  表621的数据库操作类  */
	private T621_Dao UndergraAdmiInfoDao = new T621_Dao() ;
	
	public List<T621_Bean> getYearInfo(String selectYear){
		List<T621_Bean> list = UndergraAdmiInfoDao.getYearInfo(selectYear);
		return list;
	}
	
	public Boolean insert(T621_Bean bean, String year){
		return UndergraAdmiInfoDao.insert(bean, year);
	}
	
	
	/**
	 * 更新数据
	 * @param 
	 * @return
	 */
	public int update(T621_Bean bean, String year){
		return UndergraAdmiInfoDao.update(bean, year) ;
	}
	public boolean deleteByIds(String ids, String year){
		
		return UndergraAdmiInfoDao.deleteByIds(ids, year) ;
	}
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(String selectYear, String FromTeaUnit, int checkState){
		return UndergraAdmiInfoDao.updateCheck(selectYear, FromTeaUnit, checkState) ;
	}
	
	
	

	
	public boolean batchInsert(List<T621_Bean> list){
		
		return UndergraAdmiInfoDao.batchInsert(list);
	}
	

	

	
	
	public List<T621_Bean> getPageInfoList(String rows, String page) {
		// TODO Auto-generated method stub
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T621_Bean> pageInfo = UndergraAdmiInfoDao.queryPageList(pagesize, currentpage);
		
		return pageInfo;	
	}
	
	public List<T621_Bean> getPageInfoList(String cond, Object object,
			String rows, String page) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T621_Bean> pageInfo = UndergraAdmiInfoDao.queryPageList(cond, object, pagesize, currentpage);
		
		return pageInfo;	
	}
	

	public int getTotal() {
		// TODO Auto-generated method stub
		return UndergraAdmiInfoDao.getAllList().size();
	}	
	
	public int getTotal(String cond, Object object) {
		// TODO Auto-generated method stub
		return UndergraAdmiInfoDao.getAllList(cond, object).size();
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}





}
