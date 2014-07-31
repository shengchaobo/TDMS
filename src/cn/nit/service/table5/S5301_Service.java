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
				S5301_Bean bean = this.getAll(list);
				list.add(0, bean);
			}
		}
		return list;
	}
	
	/**得到中的总的统计数据*/
	public S5301_Bean getAll(List<S5301_Bean> list){
		S5301_Bean bean = new S5301_Bean();
		int inter = 0; int nation = 0;int provi = 0; int city = 0;int sch = 0;
		
		for(int i =0;i<list.size();i++){
			S5301_Bean bean1 = list.get(i);
			inter += bean1.getInternation();
			nation += bean1.getNation();
			provi += bean1.getProvi();
			city += bean1.getCity();
			sch += bean1.getSchool();
		}
		bean.setCity(city);
		bean.setInternation(inter);
		bean.setItem("全校合计");
		bean.setNation(nation);
		bean.setProvi(provi);
		bean.setSchool(sch);
		return bean;
	}
	
	public List<S5301_Bean> getAll(String year){
		List<S5301_Bean> list = null;
		list = s5301Dao.loadInfo(year);
		S5301_Bean bean = this.getAll(list);
		list.add(0, bean);
		return list;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
