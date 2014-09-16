package cn.nit.dao.table7;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import cn.nit.dbconnection.DBConnection;
import cn.nit.pojo.table7.J732POJO;
import cn.nit.util.DAOUtil;

public class J732_DAO {
	String tableName="T722_TeachAchieveAward_Tea";
	String field="AwardName,Leader,TeaID,AwardLevel,AwardTime,AwardFromUnit";
	
	public List<J732POJO> getYearInfo(String year){
		
		String sql="select" + field + "from" + tableName + 
				 "where convert(varchar(4),T722_TeachAchieveAward_Tea$.Time,120)=" + year;
		Connection conn =DBConnection.instance.getConnection();
		Statement st=null;
		ResultSet rs=null;
		List<J732POJO> list=null;
		
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			list=DAOUtil.getList(rs, J732POJO.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(st);
			DBConnection.close(rs);
			
		}
		return list;
		           
	}
	

}
