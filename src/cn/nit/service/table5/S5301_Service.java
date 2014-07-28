package cn.nit.service.table5;

import java.util.List;

import cn.nit.bean.table5.S5301_Bean;
import cn.nit.dao.table5.S5301_DAO;



public class S5301_Service {
	
	
	private S5301_DAO  s5301Dao = new S5301_DAO();
	
	public List<S5301_Bean> loadInfo(String year){
		
		List<S5301_Bean> list = null ;//用作信息输出
		List<S5301_Bean> list1 = null ;//用作统计信息、
		boolean flag = false;
		
		int n = s5301Dao.countOri(year);
		System.out.println("s5301:"+n);
		if( n> 0){//存在统计数据
			
			list1 = s5301Dao.getOriData1(year);//统计信息
			flag = s5301Dao.save1(list1, year);
			if(flag){
				list = s5301Dao.loadInfo(year);
			}
		}
		return list;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
