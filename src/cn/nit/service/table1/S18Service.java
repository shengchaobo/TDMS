package cn.nit.service.table1;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import cn.nit.bean.table1.S18Bean;
import cn.nit.bean.table1.T181Bean;
import cn.nit.dao.table1.S18DAO;
import cn.nit.pojo.table1.S18POJO;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;


public class S18Service {
	
	
   private S18DAO s18Dao=new S18DAO();
	

	 /**得到json字符串
	 * @throws SQLException */
   public String autidingdata(String year)
	{
//	   System.out.println("gello");
	   	S18Bean s18bean=null;
	   	S18POJO s18Pojo=null;
//	   	List<S18POJO> list=null;
	   	//先删除信息
	   	if(s18Dao.delete(year))
	   	{
	   		//得到统计信息
	   		
	   		s18bean=this.getStatic();
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
   public S18Bean getStatic()
   {
//	   System.out.println("hello");
	   S18Bean s18Bean=new S18Bean();
	   	List<T181Bean> list=s18Dao.getOriData();
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
	   		T181Bean t181Bean=new T181Bean();
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
//	   	System.out.println(totalCount);
//	   	System.out.println(num1);
//	   	System.out.println(num2);
//	   	System.out.println(num3);
	   	s18Bean.setAcademicNum(num1);
	   	s18Bean.setIndustryNum(num2);
	   	s18Bean.setLocalGoverNum(num3);
	   	s18Bean.setSumAgreeNum(totalCount);
	   	s18Bean.setTime(new Date());
	   	
		return s18Bean;
	   }
	   
   
   public static void main(String arg[]) throws SQLException{
	   S18Service ser=new S18Service();
	   String info=ser.autidingdata("2014");
	   System.out.println(info);
   }
  
}
