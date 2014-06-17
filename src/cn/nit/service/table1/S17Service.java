package cn.nit.service.table1;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import cn.nit.bean.table1.S17Bean;
import cn.nit.bean.table1.T17Bean;
import cn.nit.dao.table1.S17DAO;
import cn.nit.pojo.table1.S17POJO;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;


public class S17Service {
	
	 private S17DAO s17Dao=new S17DAO();
		

	 /**得到json字符串
	 * @throws SQLException */
   public String autidingdata(String year)
	{
	   	S17Bean s17bean=null;
	   	S17POJO s17Pojo=null;
	   	String str=null;
//	   	List<S18POJO> list=null;
	   	//先删除信息
	   	if(s17Dao.delete(year))
	   	{
	   		//得到统计信息
	   	    s17bean=this.getStatic();
	   		boolean flag=s17Dao.insert(s17bean);
	   		if(flag)
	   		{
	   			List<S17POJO> list=s17Dao.auditingData(year);
	   			s17Pojo=list.get(0);
	   		}
	   	}
			JSON json=JSONSerializer.toJSON(s17Pojo) ;
			String jsonStr=json.toString();
			return jsonStr;
	}
   
	/**得到统计信息*/
   public S17Bean getStatic()
   {
//	   System.out.println("hello");
	   S17Bean s17Bean=new S17Bean();
	   	List<T17Bean> list=s17Dao.getOriData();
	   	/**境内个数*/
	   	int num1=0;
	   	/**境外个数*/
	   	int num2=0;
	   	 /**总计*/
	   	int totalCount=0;
	   	for(int i=0;i<list.size();i++)
	   	{
	   		T17Bean t17Bean=new T17Bean();
	   		t17Bean=list.get(i);
	   		String place=t17Bean.getPlace();
	   		if(place.equals("境内"))
	   		{
	   			num1=num1+1;
	   		}else if(place.equals("境外"))
	   		{
	   			num2=num2+1;
	   		}
	   	}
	   	//计算总计
	   	totalCount=num1+num2;
	   	
	   	s17Bean.setInlandNum(num1);
	   	s17Bean.setOutlandNum(num2);
	   	s17Bean.setSumSchFriNum(totalCount);
	   	s17Bean.setTime(new Date());
	   	
		return s17Bean;
	   }

}
