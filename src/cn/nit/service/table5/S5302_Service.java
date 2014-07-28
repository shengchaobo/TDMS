package cn.nit.service.table5;

import java.util.List;

import cn.nit.bean.table5.S5302_Bean;
import cn.nit.dao.table5.S5302_DAO;



public class S5302_Service {
	
private S5302_DAO  s5302Dao = new S5302_DAO();
	
	public List<S5302_Bean> loadInfo(String year){
		
		List<S5302_Bean> list = null ;//用作信息输出
		List<S5302_Bean> list1 = null ;//用作统计信息、
		boolean flag = false;
		
		int n = s5302Dao.countOri(year);
		System.out.println("s5301:"+n);
		if( n> 0){//存在统计数据
			
			list1 = s5302Dao.getOriData(year);//统计信息
			flag = s5302Dao.save(list1, year);
			if(flag){
				list = s5302Dao.loadInfo(year);
			}
		}
		return list;
	}


}
