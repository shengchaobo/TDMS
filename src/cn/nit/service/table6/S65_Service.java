package cn.nit.service.table6;




import cn.nit.bean.table6.S65_Bean;
import cn.nit.dao.table6.S65_Dao;


public class S65_Service {
	
	private S65_Dao infoDao = new S65_Dao();
	
	//根据年参数取得相应年数据
	
	public S65_Bean getYearInfo(String selectYear){
				
		S65_Bean bean = infoDao.getYearInfo(selectYear);
		
		return bean;		
	}
	
	
	//保存
	public Boolean save(S65_Bean bean, String year){
		return infoDao.save(bean,year);
	}

}
