package cn.nit.service.table5;

import java.text.DecimalFormat;
import java.util.List;

import cn.nit.bean.table5.S5102_Bean;
import cn.nit.dao.table5.S5102_DAO;
import cn.nit.pojo.table5.S5101POJO;
import cn.nit.pojo.table5.S5102POJO;


public class S5102_Service {
	private S5102_DAO s5102Dao = new S5102_DAO();
	
public List<S5102POJO> loadInfo(String year){
		
		List<S5102POJO> list = null ;//用作信息输出
		List<S5102_Bean> list1 = null ;//用作统计信息、
		boolean flag = false;
		
		int n = s5102Dao.countOri(year);
//		System.out.println("s5301:"+n);
		if( n> 0){//存在统计数据
			
			list1 = s5102Dao.getOriData(year);//统计信息
			flag = s5102Dao.save(list1, year);
			if(flag){
				list = s5102Dao.loadInfo(year);
				S5102POJO pojo = this.getAll(list);
				list.add(0, pojo);
			}
		}
		return list;
	}
	
	/**得到中的统计数据*/
	public S5102POJO getAll(List<S5102POJO> list){
		S5102POJO pojo = new S5102POJO();
		int TheoPraNum = 0; int InClassNum = 0;int PraNum = 0; int ExpNum = 0;
		double  TheoPraRatio = 0.0;double InClassRatio=0; double PraRatio=0;double ExpRatio=0;
		
		for(int i =0;i<list.size();i++){
			S5102POJO pojo1 = list.get(i);
			TheoPraNum += pojo1.getTheoPraNum();
			InClassNum += pojo1.getInClassNum();
			PraNum += pojo1.getPraNum();
			ExpNum += pojo1.getExpNum();
		}
		int sum = TheoPraNum+InClassNum+PraNum+ExpNum;
		if(sum>0){
			TheoPraRatio = this.toDouble(TheoPraNum, sum);
			 InClassRatio = this.toDouble(InClassNum,sum);
			 PraRatio = this.toDouble(PraNum, sum);
			 ExpRatio = this.toDouble(ExpNum,sum);
			}
		pojo.setExpNum(ExpNum);
		pojo.setExpRatio(ExpRatio);
		pojo.setInClassNum(InClassNum);
		pojo.setInClassRatio(InClassRatio);
		pojo.setPraNum(PraNum);
		pojo.setPraRatio(PraRatio);
		pojo.setTheoPraNum(TheoPraNum);
		pojo.setTheoPraRatio(TheoPraRatio);
		pojo.setItem("全校合计");
		return pojo;
	}
	
	/**用于Excel导出*/
	public List<S5102POJO> getAll(String year){
		List<S5102POJO> list = null;
		list = s5102Dao.loadInfo(year);
		S5102POJO pojo = this.getAll(list);
		list.add(0, pojo);
		return list;
	}
	
	/**转换成保存两位小数的double*/
	public double toDouble(int a,int b){
		
		double d1=((double)a/(double)b)*100;
		DecimalFormat df = new DecimalFormat("0.00");
		String str = df.format(d1);
		double d=Double.parseDouble(str);
		return d;
		
	}

}
