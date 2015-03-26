package cn.nit.dao.di;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import cn.nit.dbconnection.DBConnection;

public class DIResourceDAO {

	private static String tableName = "DiResource" ;
	
	private static String key = "IndexID" ;
	
	private static String field = "TableName,RoleID,RoleFirst,Note" ;
	
	public static String getAudit(String TableName){
		
		String audit = null ;
		StringBuffer sql = new StringBuffer() ;
		sql.append("select RoleFirst from " + tableName) ;
		sql.append(" where TableName='" + TableName + "'") ;
		System.out.print(sql.toString()) ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			
			while(rs.next()){
				audit = rs.getString("RoleFirst") ;
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		
		return audit ;
	}
}
