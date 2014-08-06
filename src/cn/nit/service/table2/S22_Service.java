package cn.nit.service.table2;

import java.util.List;


import cn.nit.bean.table2.S22_Bean;
import cn.nit.dao.table2.S22_Dao;


public class S22_Service {
	
	private S22_Dao infoDao = new S22_Dao();
	
	//根据年参数取得相应年数据
	
	public S22_Bean getYearInfo(String selectYear){
				
		S22_Bean bean = infoDao.getYearInfo(selectYear);
		
		return bean;		
	}
	
	
	//保存
	public Boolean save(S22_Bean bean, String year){
		return infoDao.save(bean,year);
	}

}
