package cn.nit.service.table6;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import cn.nit.bean.table6.T632_Bean;
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table6.T632_Dao;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T632_Service {
	
	/**  表632的数据库操作类  */
	private T632_Dao T632_Dao = new T632_Dao() ;
	
	/**
	 * 表631的service的插入操作
	 * @param T631_Bean
	 * @return
	 *
	 * @time: 2014-6-12
	 */
	public boolean insert(T632_Bean  T632_bean){
		
		return T632_Dao.insert(T632_bean);
	}
	
	public boolean batchInsert(List<T632_Bean> list){
		
		return T632_Dao.batchInsert(list);
	}
	
	/**
	 * 更新数据
	 * @param undergraCSBaseTea {@link cn.nit.bean.table5.UndergraCSBaseTeaBean}实体类
	 * @return
	 */
	public boolean update(T632_Bean T632_bean){
		return T632_Dao.update(T632_bean) ;
	}
	
	public boolean deleteItemsByIds(String ids){
		
		return T632_Dao.deleteItemsByIds(ids) ;
	}
	
	
	public List<T632_Bean> getPageInfoList(String rows, String page) {
		// TODO Auto-generated method stub
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T632_Bean> pageInfo = T632_Dao.queryPageList(pagesize, currentpage);
		
		return pageInfo;	
	}

	public int getTotal() {
		// TODO Auto-generated method stub
		return T632_Dao.getAllList().size();
	}

	public List<T632_Bean> getPageInfoList(String cond, Object object,
			String rows, String page) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T632_Bean> pageInfo = T632_Dao.queryPageList(cond, object, pagesize, currentpage);
		
		return pageInfo;	
	}
	
	public int getTotal(String cond, Object object) {
		// TODO Auto-generated method stub
		return T632_Dao.getAllList(cond, object).size();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}



}
