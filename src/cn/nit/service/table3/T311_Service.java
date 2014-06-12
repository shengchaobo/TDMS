package cn.nit.service.table3;

import java.util.List;

import cn.nit.bean.table3.T311_Bean;
import cn.nit.dao.table3.T311_DAO;






public class T311_Service {
	
	/**  表511的数据库操作类  */
	private T311_DAO postDocStaDao = new T311_DAO() ;
	
	/**
	 * 表511的service的插入操作
	 * @param undergraCSBaseTea
	 * @return
	 *
	 * @time: 2014-5-14/上午10:52:05
	 */
	public boolean insert(T311_Bean postDocStaBean){
		
		return postDocStaDao.insert(postDocStaBean) ;
	}

}
