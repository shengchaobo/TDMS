package cn.nit.service.table1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import cn.nit.bean.table1.S17_Bean;
import cn.nit.bean.table1.T17_Bean;
import cn.nit.dao.table1.S17DAO;
import cn.nit.dbconnection.DBConnection;
import cn.nit.pojo.table1.S17POJO;
import cn.nit.pojo.table1.T11POJO;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;


public class S17Service {
	
	 private S17DAO s17Dao=new S17DAO();
	 
	 /**  数据自增长字段的主键，必须为自增长字段  */
	 private String key = "SeqNumber" ;
	 
	 /**  数据库表中除了自增长字段的所有字段  */
	 private String field = "SumSchFriNum,InlandNum,OutlandNum,Time,Note" ;
	/**  数据库表名  */
	 private String tableName = "S17_SchFriClubNum$" ;
		

		/**
		 * 数据显示
		 * */
		public S17_Bean loadData(String year){
			
			boolean flag=false;
			S17_Bean s17bean=null;//用作统计信息
			S17_Bean bean=null;//输出
		   	int seq=s17Dao.getSeqNumber(year);
		   	
		   	if(seq!=-1){// seq!=-1,说明数据库中有这条数据
		   		if(s17Dao.countOri(year)>0){//存在数据
		   		  s17bean = this.getStatic(year);
		   		  s17bean.setSeqNumber(seq);
		   		  flag = s17Dao.update(s17bean);
		   		  if(flag){
		   			bean=s17Dao.loadData(year);
		   		  }
		   		}
		   	}
		   	else if(seq == -1){//does not exist the data information
		   		
		       if(s17Dao.countOri(year)>0){//有统计数据
		    	   s17bean = this.getStatic(year);
		    	   flag = s17Dao.insert(s17bean);
		    	   if(flag){
		    		   bean = s17Dao.loadData(year);
		    	   }
		       }
		   	}
			return bean;
		}
		
		//保存
		public Boolean save(S17_Bean bean, String year,	String fields){
			return s17Dao.save(bean,year,fields);
		}

//	 /**得到json字符串
//	 * @throws SQLException */
//   public String autidingdata(String year)
//	{
//	   	S17Bean s17bean=null;
//	   	S17POJO s17Pojo=null;
//	   	String str=null;
////	   	List<S18POJO> list=null;
//	   	//先删除信息
//	   	if(s17Dao.delete(year))
//	   	{
//	   		//得到统计信息
//	   	    s17bean=this.getStatic();
//	   		boolean flag=s17Dao.insert(s17bean);
//	   		if(flag)
//	   		{
//	   			List<S17POJO> list=s17Dao.auditingData(year);
//	   			s17Pojo=list.get(0);
//	   		}
//	   	}
//			JSON json=JSONSerializer.toJSON(s17Pojo) ;
//			String jsonStr=json.toString();
//			return jsonStr;
//	}
   
	/**得到统计信息*/
   public S17_Bean getStatic(String year)
   {
//	   System.out.println("hello");
	   S17_Bean s17Bean=new S17_Bean();
	   	List<T17_Bean> list=s17Dao.getOriData(year);
	   	/**境内个数*/
	   	int num1=0;
	   	/**境外个数*/
	   	int num2=0;
	   	 /**总计*/
	   	int totalCount=0;
	   	for(int i=0;i<list.size();i++)
	   	{
	   		T17_Bean t17Bean=new T17_Bean();
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
   
   public static void main(String arg[]){
	   S17Service ser=new S17Service();
	   S17_Bean bean=ser.loadData("2011");
	   if(bean!=null){
		   System.out.println("有数据");
	   }else {
		   System.out.println("无数据");
	   }
   }

}
