package cn.nit.service.table7;

import java.util.List;

import cn.nit.bean.table7.S72_Bean;
import cn.nit.dao.table7.S72_DAO;

public class S72_Service {
	
	S72_DAO s72_Dao=new S72_DAO();
	
	public List<S72_Bean> getYearInfo(String selectYear){
		List<S72_Bean> list=s72_Dao.getData(selectYear);
		return list;
	}

}
