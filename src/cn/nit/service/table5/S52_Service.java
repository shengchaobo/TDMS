package cn.nit.service.table5;

import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import cn.nit.bean.table5.S52_Bean;
import cn.nit.dao.table5.S52_DAO;

public class S52_Service {
	
	private S52_DAO  s52Dao = new S52_DAO();
	
	public List<S52_Bean> loadInfo(String year){
		
		List<S52_Bean> list = null ;//用作信息输出
		List<S52_Bean> list1 = null ;//用作统计信息、
		boolean flag = false;
		
		int n = s52Dao.countOri(year);
		System.out.println("s52:"+n);
		if( n> 0){//存在统计数据
			
			list1 = s52Dao.getOriData(year);//统计信息
			flag = s52Dao.save(list1, year);
			if(flag){
				list = s52Dao.loadInfo(year);
			}
		}
		return list;
	}
	
	public static void main(String arg[]){
		S52_Service ser = new S52_Service();
		List<S52_Bean> list = ser.loadInfo("2014");
		if(list == null){
			System.out.println("无数据");
		}else{
			S52_Bean bean = list.get(0);

			JSON json = JSONSerializer.toJSON(list) ;
			System.out.println(json.toString());
			System.out.println("有数据");
		}
	}
	

}
