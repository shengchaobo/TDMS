package cn.nit.service.table1;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import cn.nit.bean.table1.A15Bean;
import cn.nit.bean.table1.S15Bean;
import cn.nit.bean.table1.T151Bean;
import cn.nit.dao.table1.A15DAO;
import cn.nit.pojo.table1.A15POJO;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;


public class A15Service {
	
	
	/**统计表A15数据库操作类*/
	private A15DAO a15Dao=new A15DAO();
	
	/**统计数据*/
	public  A15Bean getStatic(String year)
	{
		A15Bean a15Bean=new A15Bean();
		List<S15Bean> list=a15Dao.getOriData(year);
		
		/**国家级个数*/
		int num1=0;
		/**国家级所占比列*/
		double ratio1=0.0;
		/**省级个数*/
		int num2=0;
		/**省级所占比列*/
		double ratio2=0.0;
		/**市级个数*/
		int num3=0;
		/**市级所占比列*/
		double ratio3=0.0;
		/**校级个数*/
		int num4=0;
		/**校级所占比列*/
		double ratio4=0.0;
		
//		int nType;
		int totalNum=0;
//		double totalratio=1.0;
		if(!list.isEmpty()&&list!=null){
			for(int i=0;i<list.size();i++)
			{
				S15Bean s15Bean=new S15Bean();
				s15Bean=list.get(i);
				//国家级
				num1=s15Bean.getNationEnginResNum()+s15Bean.getNationKeyResNum()+s15Bean.getNationResNum()+s15Bean.getOtherNationResNum()
				                                 +s15Bean.getHumanNationResNum();
				//省部级
				num2=s15Bean.getHumanProviResNum()+s15Bean.getOtherProviResNum()+s15Bean.getProviLabNum()+s15Bean.getProviResNum();
				//市级
				num3=s15Bean.getCityResNum();
			    //校级
				num4=s15Bean.getTeaUnitResNum()+s15Bean.getOtherSchResNum();
		    }
		}	
		totalNum=num1+num2+num3+num4;
		if(totalNum!=0){
//			double n1=(double)(num1/totalNum);
			ratio1=this.m2((double)(num1/totalNum));
			ratio2=this.m2((double)(num2/totalNum));
			ratio3=this.m2((double)(num3/totalNum));
			ratio4=this.m2((double)(num4/totalNum));
			a15Bean.setCityResNum(num3);
			a15Bean.setCityResRatio(ratio3);
			a15Bean.setNationResNum(num1);
			a15Bean.setNationResRatio(ratio1);
			a15Bean.setProviResNum(num2);
			a15Bean.setProviResRatio(ratio2);
			a15Bean.setSchResNum(num4);
			a15Bean.setSchResRatio(ratio4);
			a15Bean.setTime(new Date());
		}
		return a15Bean;
	}
	
	/**插入数据*/
	public boolean insert(A15Bean a15Bean){
		
		return a15Dao.insert(a15Bean) ;
	}
	
	
	public String autidingdata(String year)
	{
		List<A15POJO> list=null;
		A15POJO a15Pojo=null;
		String str=null;
		boolean flag=false;
		if(a15Dao.delete(year))
		{
			//得到最终统计信息
			A15Bean a15Bean=this.getStatic(year);
//			System.out.println(a15Bean.g);
			if(a15Bean.getSumResNum()==0){
				flag=false;
			}else{flag=true;}
//			System.out.println(flag);
			if(flag){
//				System.out.println("hello!");
				//插入数据库
				if(a15Dao.insert(a15Bean))
				{
					list=a15Dao.auditingData(year);
					a15Pojo=list.get(0);
				}
				JSON json=JSONSerializer.toJSON(a15Pojo) ;
				str=json.toString();
			}
		}
	    return str;
	}
	
	public double m2(double n){
		 BigDecimal bg = new BigDecimal(n);
	     double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//	     System.out.println(f1);
	     return f1;
	}
	
	public static void main(String arg[]){
		double s1=20.33666;
		A15Service ser=new A15Service();
//		System.out.println(ser.m2(s1));
		String str=ser.autidingdata("2014");
		System.out.println(str);
		
	}

}
