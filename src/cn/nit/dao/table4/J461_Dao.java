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
		}
		
		String sql1 = "select " +
		"sum(case when AwardType = '51001' then 1 else 0 end) as sum3 " +
		"from "+tableName1+" where Time like '"+year+"%'";
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql1) ;
			if(rs.next()){
			bean.setSum3(rs.getInt("sum3"));
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}
		
		String sql2 = "select " +
		"count(TeamName) as sum2," +
		"sum(case when TeamLevel = '50001' then 1 else 0 end ) as country2," +
		"sum(case when TeamLevel = '50002' then 1 else 0 end ) as provi2 " +
		"from "+tableName2+" where Time like '"+year+"%'";
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql2) ;
			if(rs.next()){
			bean.setSum2(rs.getInt("sum2"));
			bean.setCountry2(rs.getInt("country2"));
			bean.setProvi2(rs.getInt("provi2"));
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
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
