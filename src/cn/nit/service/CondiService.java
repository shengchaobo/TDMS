package cn.nit.service;

import java.util.List;


import cn.nit.bean.SCondiMornit;
import cn.nit.dao.CondiDao;

public class CondiService {
	
	private CondiDao condiDao = new CondiDao();
	
	//根据年参数取得相应年数据
	
	public SCondiMornit getYearInfo(String selectYear){
				
		SCondiMornit bean = condiDao.getYearInfo(selectYear);
		
		return bean;		
	}
	
	
	//保存
	public Boolean save(SCondiMornit bean, String year){
		return condiDao.save(bean,year);
	}

}
