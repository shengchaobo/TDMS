package cn.nit.service.table5;

import java.util.List;


import cn.nit.bean.table5.S5302_Bean;
import cn.nit.dao.table5.S5302_DAO;
import cn.nit.pojo.table5.S5302POJO;



public class S5302_Service {
	
private S5302_DAO  s5302Dao = new S5302_DAO();
	
	public List<S5302POJO> loadInfo(String year){
		
		List<S5302POJO> list = null ;//用作信息输出
		List<S5302_Bean> list1 = null ;//用作统计信息、
		
		boolean flag = false;
		
		int n = s5302Dao.countOri(year);
		System.out.println("countser:"+n);
		if( n> 0){//存在统计数据
			System.out.println("hello");
			list1 = s5302Dao.getOriData("2014");//统计信息
			System.out.println("list1:"+list1.size());
			flag = s5302Dao.save(list1, year);
			if(flag){
				list = s5302Dao.loadInfo(year);
				System.out.println(list.size());
				S5302POJO pojo = this.getAll(list);
				list.add(0, pojo);
			}
		}
		return list;
	}
	
	/**得到中的统计数据*/
	public S5302POJO getAll(List<S5302POJO> list){
		S5302POJO pojo = new S5302POJO();
		int inter = 0; int nation = 0;int provi = 0; int city = 0;int sch = 0;
		
		for(int i =0;i<list.size();i++){
			S5302POJO pojo1 = list.get(i);
			inter += pojo1.getInternation();
			nation += pojo1.getNation();
			provi += pojo1.getProvi();
			city += pojo1.getCity();
			sch += pojo1.getSchool();
		}
		pojo.setCity(city);
		pojo.setInternation(inter);
		pojo.setItem("全校合计");
		pojo.setNation(nation);
		pojo.setProvi(provi);
		pojo.setSchool(sch);
		return pojo;
	}
	public static void main(String arg[]){
		S5302_Service ser = new S5302_Service();
		List<S5302POJO> list = ser.loadInfo("2014");
		System.out.println(list.size());
	}


}
