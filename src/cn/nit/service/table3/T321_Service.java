package cn.nit.service.table3;

import java.util.List;

import cn.nit.bean.table3.T321_Bean;
import cn.nit.dao.table3.T321_DAO;







public class T321_Service {
	
	/**  表511的数据库操作类  */
	private T321_DAO mainTrainBasicInfoDao = new T321_DAO() ;
	
	/**
	 * 表511的service的插入操作
	 * @param undergraCSBaseTea
	 * @return
	 *
	 * @time: 2014-5-14/上午10:52:05
	 */
	public boolean insert(T321_Bean mainTrainBasicInfoBean){
		
		return mainTrainBasicInfoDao.insert(mainTrainBasicInfoBean) ;
	}


}
