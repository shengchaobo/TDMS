package cn.nit.dao.table4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.table4.T451_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.pojo.table4.J461POJO;
import cn.nit.util.DAOUtil;

public class J461_Dao {
	
	private static String tableName1 = "T461_FameTeaAward_Per$";
	private String tableName2 = "T48_TeachTeam_TeaTea$";
	
	public J461POJO totalList(String year){
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		J461POJO bean = new J461POJO() ;
		String sql = "select " +
				"sum(case when AwardType = '51000' then 1 else 0 end) as sum1," +
				"sum(case when AwardType = '51000' and AwardLevel = '50001' then 1 else 0 end ) as country1," +
				"sum(case when AwardType = '51000' and AwardLevel = '50002' then 1 else 0 end ) as provi1 " +
				"from "+tableName1+" where Time like '"+year+"%'";
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			if(rs.next()){
			bean.setSum1(rs.getInt("sum1"));
			bean.setCountry1(rs.getInt("country1"));
			bean.setProvi1(rs.getInt("provi1"));
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		
		String sql1 = "select " +
		"sum(case when AwardType = '51001' then 1 else 0 end) as sum3 " +
		"from "+tableName1+" where Time like '"+year+"%'";
		Connection conn1 = DBConnection.instance.getConnection() ;
		Statement st1 = null ;
		ResultSet rs1 = null ;
		
		try{
			st1 = conn1.createStatement() ;
			rs1 = st1.executeQuery(sql1) ;
			if(rs1.next()){
			bean.setSum3(rs1.getInt("sum3"));
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(rs1);
			DBConnection.close(st1);	
			DBConnection.close(conn1);
		}
		
		String sql2 = "select " +
		"count(TeamName) as sum2," +
		"sum(case when TeamLevel = '50001' then 1 else 0 end ) as country2," +
		"sum(case when TeamLevel = '50002' then 1 else 0 end ) as provi2 " +
		"from "+tableName2+" where Time like '"+year+"%'";
		
		Connection conn2 = DBConnection.instance.getConnection() ;
		Statement st2 = null ;
		ResultSet rs2 = null ;
		
		try{
			st2 = conn2.createStatement() ;
			rs2 = st2.executeQuery(sql2) ;
			if(rs2.next()){
			bean.setSum2(rs2.getInt("sum2"));
			bean.setCountry2(rs2.getInt("country2"));
			bean.setProvi2(rs2.getInt("provi2"));
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(rs2);
			DBConnection.close(st2);	
			DBConnection.close(conn2);
		}
		return bean ;
		
	}
	
	public static void main(String args[]){
		String sql = "select " +
		"sum(case when AwardType = '51000' then 1 else 0 end) as sum1," +
		"sum(case when AwardType = '51000' and AwardLevel = '50001' then 1 else 0 end ) as country1," +
		"sum(case when AwardType = '51000' and AwardLevel = '50002' then 1 else 0 end ) as provi1 " +
		"from "+tableName1+" where Time like '"+2014+"%'";
		System.out.println(sql);
		
	}

}
