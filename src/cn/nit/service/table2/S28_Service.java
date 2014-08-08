package cn.nit.service.table2;

import java.util.List;


import cn.nit.bean.table2.S28_Bean;
import cn.nit.dao.table2.S28_Dao;


public class S28_Service {
	
	private S28_Dao infoDao = new S28_Dao();
	
	//根据年参数取得相应年数据
	
	public S28_Bean getYearInfo(String selectYear){
				
		S28_Bean bean = infoDao.getYearInfo(selectYear);
		
		return bean;		
	}
	
	
	//保存
	public Boolean save(S28_Bean bean, String year){
		return infoDao.save(bean,year);
	}

}
