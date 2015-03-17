package cn.nit.service.table1;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;


import cn.nit.bean.table1.S18_Bean;
import cn.nit.bean.table1.T181_Bean;
import cn.nit.dao.table1.S18DAO;
import cn.nit.pojo.table1.S18POJO;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;


public class S18Service {
	
	
   private S18DAO s18Dao=new S18DAO();
   
   
   /**
	 * 数据显示
	 * */
	public S18_Bean loadData(String year){
		
		boolean flag=false;
		S18_Bean s18bean=null;//用作统计信息
		S18_Bean bean=null ;//输出数据
	   	int seq=s18Dao.getSeqNumber(year);

	   	if(seq!=-1){// seq!=-1,说明数据库中有这条数据
//	   		System.out.println("原始数据条数:"+s18Dao.countOri(year));
	   		if(s18Dao.countOri(year)>0){//有统计数据
	   			s18bean = this.getStatic(year);
	   			s18bean.setSeqNumber(seq);
	   			flag = s18Dao.update(s18bean);
	   			if(flag){
	   				bean=s18Dao.loadData(year);
	   			}
	   		}
	   	}
	   	else if(seq == -1){//does not exist the data information
	   		
	   		if(s18Dao.countOri(year)>0){//有条数
	   			s18bean = this.getStatic(year);
	   			flag = s18Dao.insert(s18bean);
	   			if(flag){
	   				bean = s18Dao.loadData(year) ;	
	   			}
	   		}
	   	}

		return bean;
	}
	

	 /**得到json字符串
	 * @throws SQLException */
   public String autidingdata(String year)
	{
//	   System.out.println("gello");
	   	S18_Bean s18bean=null;
	   	S18POJO s18Pojo=null;
//	   	List<S18POJO> list=null;
	   	//先删除信息
	   	if(s18Dao.delete(year))
	   	{
	   		//得到统计信息
	   		
	   		s18bean=this.getStatic(year);
	   		boolean flag=s18Dao.insert(s18bean);
	   		if(flag)
	   		{
	   			List<S18POJO> list=s18Dao.auditingData(year);
	   			s18Pojo=list.get(0);
	   		}
	   	}
			JSON json=JSONSerializer.toJSON(s18Pojo) ;
			String jsonStr=json.toString();
			return jsonStr;
	}
   
	/**得到统计信息*/
   public S18_Bean getStatic(String year)
   {
//	   System.out.println("hello");
	   S18_Bean s18Bean=new S18_Bean();
	   	List<T181_Bean> list=s18Dao.getOriData(year);
	   	System.out.println("s18条数："+list.size());
	   	/**学术机构个数*/
	   	int num1=0;
	   	/**行业机构和企业个数*/
	   	int num2=0;
	   	 /**地方政府个数*/
	   	int num3=0;
	   	 /**协议总计*/
	   	int totalCount=0;
	   	for(int i=0;i<list.size();i++)
	   	{
	   		T181_Bean t181Bean=new T181_Bean();
	   		t181Bean=list.get(i);
	   		String type=t181Bean.getCooperInsType();
	   		if(type.equals("学术机构"))
	   		{
	   			num1=num1+1;
	   		}else if(type.equals("行业机构和企业"))
	   		{
	   			num2=num2+1;
	   		}else if(type.equals("地方政府"))
	   		{
	   			num3=num3+1;
	   		}
	   	}
	   	totalCount=num1+num2+num3;
//	   	System.out.println("totalCount"+totalCount);
//	   	System.out.println("num1"+num1);
//	   	System.out.println("num2"+num2);
//	   	System.out.println("num3"+num3);
	   	s18Bean.setAcademicNum(num1);
	   	s18Bean.setIndustryNum(num2);
	   	s18Bean.setLocalGoverNum(num3);
	   	s18Bean.setSumAgreeNum(totalCount);
	   	s18Bean.setTime(new Date());
	   	
		return s18Bean;
	   }
	   
   
   public static void main(String arg[]) throws SQLException{
	   S18Service ser=new S18Service();
	   S18_Bean bean = ser.loadData("2014");
	  System.out.println(bean.getAcademicNum());
	  System.out.println(bean.getIndustryNum());
	  System.out.println(bean.getLocalGoverNum());
	  System.out.println(bean.getSumAgreeNum());
   }
  
}
