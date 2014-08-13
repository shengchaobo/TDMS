package cn.nit.service.table1;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
	
	
	/**
	 * 数据显示
	 * */
	public A15Bean loadData(String year){
		
		boolean flag=false;
		A15Bean a15bean=new A15Bean();//用作统计信息
		A15Bean bean=null;//用于输出
//	   	String str=null;
	   	int seq=a15Dao.getSeqNumber(year);
//	   	System.out.println(seq);
	   	if(seq!=-1){// seq!=-1,说明数据库中有这条数据
	   		
	   		if(a15Dao.countOri(year)>0){//存在统计信息
	   			System.out.println("1:"+a15Dao.countOri(year));
	   			a15bean = this.getStatic(year);//通过原始数据计算值！
	   			a15bean.setSeqNumber(seq);
	   			flag = a15Dao.update(a15bean);
	   		  if(flag){
			    	 bean=a15Dao.loadData(year);
			     }else{
			    	 bean = null;
			     }
	   		}
	   	}
	   	else if(seq == -1){//does not exist the data information
	   		if(a15Dao.countOri(year)>0){
	   			System.out.println("2:"+a15Dao.countOri(year));
	   			a15bean = this.getStatic(year);
	   			flag = a15Dao.insert(a15bean);
	   			if(flag){
	   				bean = a15Dao.loadData(year);
	   			}
	   		}
	   	}
		return bean;
	}
	
	
//	//输出数据
//	public String autidingdata(String year)
//	{
//		List<A15POJO> list=new ArrayList<A15POJO>();
//		A15POJO a15Pojo=new A15POJO();
//		String str=null;
//		boolean flag=false;
//		if(a15Dao.delete(year))
//		{
//			//得到最终统计信息
//			A15Bean a15Bean=this.getStatic(year);
//			
//			if(a15Bean.getSumResNum()==0){
//				flag=false;
//			}else{flag=true;}
////			System.out.println(flag);
//			if(flag){
////				System.out.println("hello!");
//				//插入数据库
//				if(a15Dao.insert(a15Bean))
//				{
//					list=a15Dao.auditingData(year);
//					a15Pojo=list.get(0);
//				}
//				JSON json=JSONSerializer.toJSON(a15Pojo) ;
//				str=json.toString();
////				System.out.println(str);
//			}
//		}
//	    return str;
//	}
	
	
	/**统计数据*/
	public  A15Bean getStatic(String year)
	{
		A15Bean a15Bean=new A15Bean();
		List<S15Bean> list=a15Dao.getOriData(year);
//		System.out.println(list.size());
		
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
//		System.out.println(num1);
//		System.out.println(num2);
//		System.out.println(num3);
//		System.out.println(num4);
//		System.out.println(totalNum);
		if(totalNum!=0){
//			double n1=(double)(num1/totalNum);
			
			ratio1=this.m2(((double)num1/(double)totalNum)*100);
//			ratio1=ratio1*100;
//			System.out.println(ratio1);
			ratio2=this.m2(((double)num2/(double)totalNum)*100);
//			ratio2=ratio2*100;
//			System.out.println((num2/totalNum));
//			System.out.println(ratio2);
			ratio3=this.m2(((double)num3/(double)totalNum)*100);
//			ratio3=ratio3*100;
//			System.out.println(ratio3);
			ratio4=this.m2(((double)num4/(double)totalNum)*100);
//			ratio4=ratio4*100;
//			System.out.println(ratio4);
			a15Bean.setCityResNum(num3);
			a15Bean.setCityResRatio(ratio3);
			a15Bean.setNationResNum(num1);
			a15Bean.setNationResRatio(ratio1);
			a15Bean.setProviResNum(num2);
			a15Bean.setProviResRatio(ratio2);
			a15Bean.setSchResNum(num4);
			a15Bean.setSchResRatio(ratio4);
			a15Bean.setSumResNum(totalNum);
			a15Bean.setTime(new Date());
		}
		return a15Bean;
	}
	
	/**插入数据*/
	public boolean insert(A15Bean a15Bean){
		
		return a15Dao.insert(a15Bean) ;
	}
	
	
	public A15POJO beanToPojo(A15Bean bean){
		A15POJO pojo = new A15POJO();
		pojo.setCityResNum(bean.getCityResNum());
		pojo.setNationResNum(bean.getNationResNum());
		pojo.setProviResNum(bean.getProviResNum());
		pojo.setSchResNum(bean.getSchResNum());
		pojo.setSumResNum(bean.getSumResNum());
		pojo.setCityResRatio(this.doubleToStr(bean.getCityResRatio()));
		pojo.setNationResRatio(this.doubleToStr(bean.getNationResRatio()));
		pojo.setProviResRatio(this.doubleToStr(bean.getProviResRatio()));
		pojo.setSchResRatio(this.doubleToStr(bean.getSchResRatio()));
		pojo.setNote(bean.getNote());
		pojo.setTime(bean.getTime());
		
		return pojo;
	}
	
	/**吧double类型转换成百分数*/
	public String doubleToStr(double num){
		String str=null;
//		num=num*100;
//		num=this.m2(num);
//		System.out.println(num);
		str=""+num+"%";
		System.out.println(str);
		return str;
	}
	
	//保留两位小数
	public double m2(double n){
		 BigDecimal bg = new BigDecimal(n);
	     double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//	     System.out.println(f1);
	     return f1;
	}
	
	
	
	public static void main(String arg[]){
		double s1=0.0;
		A15Service ser=new A15Service();
	   int a = 1;
	   int b = 3;
	   double ratio4=ser.m2(((double)a/(double)b)*100);
		System.out.println(ratio4);
		System.out.println(ser.doubleToStr(ratio4));
//		
	}

}
