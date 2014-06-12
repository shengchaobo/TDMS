package cn.nit.service.table3;

import java.util.List;

import cn.nit.bean.table3.T312_Bean;
import cn.nit.dao.table3.T312_DAO;





public class T312_Service {

private T312_DAO docAndGraStaDao = new T312_DAO() ;
	
	/**
	 * 加载所有的课程类别数据字典表的内容
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
public boolean insert(T312_Bean docAndGraStaBean){
	
	return docAndGraStaDao.insert(docAndGraStaBean) ;
}
}
