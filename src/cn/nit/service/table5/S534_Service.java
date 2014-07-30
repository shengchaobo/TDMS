package cn.nit.service.table5;

import java.util.List;


import cn.nit.bean.table5.S534_Bean;
import cn.nit.dao.table5.S534_DAO;

public class S534_Service {
	
	private S534_DAO s534Dao = new S534_DAO();
	
	/**
	 * 显示数据
	 * */
	public List<S534_Bean> loadInfo(String year){
		
		List<S534_Bean> list = null;//输出数据
		List<S534_Bean> list1= null;//原始数据
		boolean flag = false;
//			System.out.println(s512Dao.countOri(year));
		if(s534Dao.countOri(year)>0){//有统计数据
			list1 = s534Dao.getOriData(year);//重新统计数据
			flag = s534Dao.save(list1, year);//保存数据
			if(flag){
				list = s534Dao.loadInfo(year);
			}
		}
		return list;
	}
	
	public static void main(String arg[]){
		S534_Service ser =  new S534_Service();
		List<S534_Bean> list = ser.loadInfo("2014");
	    if(list==null){
	    	System.out.println("无数据");
	    }else{
	    	System.out.println("有数据");
	    }
	}
	

}
