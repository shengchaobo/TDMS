package cn.nit.service.table6;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import cn.nit.bean.table6.T631_Bean;
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table6.T631_Dao;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T631_Service {
	
	/**  表624的数据库操作类  */
	private T631_Dao T631_dao = new T631_Dao() ;
	
	/**
	 * 表631的service的插入操作
	 * @param T631_Bean
	 * @return
	 *
	 * @time: 2014-6-12
	 */
	public boolean insert(T631_Bean  T631_bean){
		
		return T631_dao.insert(T631_bean);
	}
	
	public boolean batchInsert(List<T631_Bean> list){
		
		return T631_dao.batchInsert(list);
	}
	
	/**
	 * 更新数据
	 * @param undergraCSBaseTea {@link cn.nit.bean.table5.UndergraCSBaseTeaBean}实体类
	 * @return
	 */
	public boolean update(T631_Bean T631_bean){
		return T631_dao.update(T631_bean) ;
	}
	
	public boolean deleteItemsByIds(String ids){
		
		return T631_dao.deleteItemsByIds(ids) ;
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
		return T631_dao.getAllList().size();
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
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}



}
