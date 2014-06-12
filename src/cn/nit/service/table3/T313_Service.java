package cn.nit.service.table3;

import java.util.List;

import cn.nit.bean.table3.T313_Bean;
import cn.nit.dao.table3.T313_DAO;





public class T313_Service {
	
private T313_DAO discipDAO = new T313_DAO() ;
	
	/**
	 * 加载所有的课程类别数据字典表的内容
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
public boolean insert(T313_Bean discipBean){
	
	return discipDAO.insert(discipBean) ;
}

}
