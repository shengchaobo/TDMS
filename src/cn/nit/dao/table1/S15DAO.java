package cn.nit.dao.table1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.nit.bean.table1.S15Bean;
import cn.nit.bean.table1.T15Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.pojo.table1.S15POJO;
import cn.nit.util.DAOUtil;

public class S15DAO {
	
	/**数据库表名称*/
	private String tableName="S15_ResIns$";
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**需要查询所有字段*/
	private String field="SumResNum,SumResArea,NationResNum,NationResArea,NationKeyResNum,NationKeyResArea," +
			"NationEnginResNum,NationEnginResArea,OtherNationResNum,OtherNationResArea,ProviResNum," +
			"ProviResArea,ProviLabNum,ProviLabArea,OtherProviResNum,OtherProviResArea,HumanResSumNum," +
			"HumanResSumArea,HumanNationResNum,HumanNationResArea,HumanProviResNum,HumanProviResArea," +
			"CityResNum,CityResArea,TeaUnitResNum,TeaUnitResArea,OtherSchResNum,OtherSchResArea,Time,Note";
	
	/**需要查询的数据库表名称1*/
	private String tableName1="T151_SchResIns_Res$";
	
	/**需要查询的数据库表名称2*/
	private String tableName2="T152_TeaResIns_TeaRes$";
	
	/**得到要显示 的数据*/
	public S15Bean getData()
	{
		List<S15Bean>  list=new ArrayList<S15Bean>();
		
		StringBuffer sql=new StringBuffer();
		sql.append("select * from "+ tableName);
		
		Connection conn=DBConnection.instance.getConnection();
		Statement st=null;
		ResultSet rs=null;
		
		try
		{
			st=conn.createStatement();
			rs=st.executeQuery(sql.toString());
			list=DAOUtil.getList(rs, S15Bean.class);
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return list.get(0);
		
	}
	
	/**按年份删除数据*/
	public boolean deleteByYear(String year)
	{
		boolean flag=false;
		StringBuffer sql=new StringBuffer();
		sql.append("delete from "+tableName);
		sql.append(" where Time like '"+year+"%'");
		
		Connection conn=DBConnection.instance.getConnection();
		Statement st=null;
		
		try
		{
			st=conn.createStatement();
			st.executeUpdate(sql.toString());
			flag=true;
		}catch(Exception e)
		{
			e.printStackTrace();
			return flag;
		}
		return flag;
	}
	
	/**插入数据*/
	public boolean insert(S15POJO s15Pojo)
	{
		//flag判断数据是否插入成功
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.insert(s15Pojo, tableName, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}
		return flag ;
	}
	
	
	/**
	 *Excel數據導出
	 */
	public List<S15Bean> forExcel(String year){
		
		StringBuffer sql = new StringBuffer() ;
		List<S15Bean> list=null;
        
		sql.append("select * from "+ tableName);
		sql.append(" where Time like '"+year+"%'");
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
//		System.out.println(sql.toString());
		
		try{
			st = conn.createStatement() ;
//			ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY
//			st.setMaxRows(1) ;
			rs = st.executeQuery(sql.toString()) ;
//			rs.absolute((page - 1) * rows) ;
			list = DAOUtil.getList(rs, S15Bean.class) ;
			
			
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}
		return list ;
	}
	
	
	/**獲得原始數據*/
	public List<T15Bean> getOriData()
	{
		List<T15Bean> list=new ArrayList<T15Bean>();
		StringBuffer sql1=new StringBuffer();
		sql1.append("select SeqNumber,Type,HouseArea from "+ tableName1);
		StringBuffer sql2=new StringBuffer();
		sql2.append("select SeqNumber,Type,HouseArea from "+ tableName2);
//		sql.append("select * from " +tableName1);
		
		Connection conn=DBConnection.instance.getConnection();
		Statement st1=null;
		ResultSet rs1=null;
		Statement st2=null;
		ResultSet rs2=null;
		 
		try
		{
            st1=conn.createStatement();
            st2=conn.createStatement();
            rs1=st1.executeQuery(sql1.toString());
            rs2=st2.executeQuery(sql2.toString());
            while(rs1.next()){
            	T15Bean t151=new T15Bean();
            	int seq1=rs1.getInt("SeqNumber");
            	String type1=rs1.getString("Type");
            	double area1=rs1.getDouble("HouseArea");
            	t151.setType(type1);
            	t151.setSeqNumber(seq1);
            	t151.setHouseArea(area1);
            	t151.setDataBase("db1");
            	list.add(t151);
            }
            while(rs2.next()){
            	T15Bean t152=new T15Bean();
            	int seq2=rs2.getInt("SeqNumber");
            	String type2=rs2.getString("Type");
            	double area2=rs2.getDouble("HouseArea");
            	t152.setType(type2);
            	t152.setSeqNumber(seq2);
            	t152.setHouseArea(area2);
            	t152.setDataBase("db2");
            	list.add(t152);
            }
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	public static void main(String arg[]){
		S15DAO s15=new S15DAO();
		List<S15Bean> list=s15.forExcel("2014");
		System.out.println(list.size());
	}

}
