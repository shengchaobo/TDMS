package cn.nit.dao.table3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sun.security.krb5.internal.UDPClient;




import cn.nit.bean.table3.S31_Bean;
import cn.nit.dbconnection.DBConnection;



import cn.nit.pojo.table3.S31POJO;
import cn.nit.util.DAOUtil;

public class S31_DAO {
	
	//"SeqNumber,PostDocStaName,SetTime,ResearcherNum,UnitName,UnitID,Time,Note"

	/**  数据库表名  */
	private String tableName = "S31_DiscipBuild$" ;
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "PostdocStation,DocStation,MasterStation,SumMajor,NewMajor,JuniorMajor,Time,Note" ;
	
	/**
	 * 将数据表311的实体类插入数据库
	 * @param undergraCSBase
	 * @return
	 *
	 * @time: 2014-5-14/上午10:53:10
	 */
	public boolean insert(S31_Bean s31_Bean){
		
		//flag判断数据是否插入成功
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.insert(s31_Bean, tableName, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
		}
		return flag ;
	}
	
	public int beTheYear (String year){
		System.out.println("一定输出来3");
		StringBuffer sql = new StringBuffer() ;
		sql.append("select count(*)") ;
		
		sql.append(" from " + tableName+" where Time like '"+year+"%'") ;
//		System.out.println(sql.toString());
//		if(fillUnitId != null && !fillUnitId.equals("")){
//			sql.append(" and FillDept=" + fillUnitId) ;
//		}
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
//			System.out.println(rs);
			
		}catch(Exception e){
			e.printStackTrace() ;
		}
		if(rs == null){
			return 0 ;
		}else{
			return 1;
		}

	}
	
	public void delete(String year){
		StringBuffer sql=new StringBuffer();
		sql.append("delete from "+tableName+" where Time like '"+year+"%'");
		Connection conn=DBConnection.instance.getConnection();
		Statement st=null;
		
		try{
			st=conn.createStatement();
			st.executeUpdate(sql.toString());
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public List<S31POJO> exportData(String year){
		StringBuffer sql=new StringBuffer();
		List<S31POJO> list=null;
		sql.append("select * from "+tableName+" where Time like '"+year+"%'");
		Connection conn=DBConnection.instance.getConnection();
		Statement st=null;
		ResultSet rs=null;
		try{
			st=conn.createStatement();
			rs=st.executeQuery(sql.toString());
			list=DAOUtil.getList(rs, S31POJO.class);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
//		System.out.println("神马都是浮云a ");
//		System.out.println(list.get(0).getAgronomyNum());
		return list;	
	}
	


	
	
	public List<S31POJO> auditingData(String year){
		
		StringBuffer sql = new StringBuffer() ;
		List<S31POJO> list =null ;
		sql.append("select * from "+tableName+" where Time like '"+year+"%'");		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
//		System.out.println(sql.toString());
		
		try{
			st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY) ;
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, S31POJO.class) ;		
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}
		
		return list ;
	}
	
	
	
	public <T> List<T> getOriData(Class<T> cla ,String tableName1)
	{
		List<T> list=new ArrayList<T>();
		
		StringBuffer sql=new StringBuffer();
		sql.append("select * from "+tableName1);
		
		Connection conn=DBConnection.instance.getConnection();
		Statement st=null;
		ResultSet rs=null;
		
		try
		{
			st=conn.createStatement();
			rs=st.executeQuery(sql.toString());
			list = DAOUtil.getList(rs, cla) ;
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	public <T> List<T> getOriData(Class<T> cla ,String tableName1, String year)
	{
		List<T> list=new ArrayList<T>();
		
		StringBuffer sql=new StringBuffer();
		sql.append("select * from "+tableName1+" where Time like '"+year+"%'");
		
		Connection conn=DBConnection.instance.getConnection();
		Statement st=null;
		ResultSet rs=null;
		
		try
		{
			st=conn.createStatement();
			rs=st.executeQuery(sql.toString());
			list = DAOUtil.getList(rs, cla) ;
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return list;
	}
	


}
