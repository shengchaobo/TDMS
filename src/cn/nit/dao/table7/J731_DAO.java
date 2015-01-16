package cn.nit.dao.table7;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import cn.nit.dbconnection.DBConnection;
import cn.nit.pojo.table7.J731POJO;
import cn.nit.util.DAOUtil;

public class J731_DAO {
	String tableName="T721_TeachResItem_Tea$";
	
	String field="ItemName,Leader,TeaID,ItemLevel,ItemSetUpTime,ReceptTime,ApplvExp,OtherTeaNum";
		
	/**
	 * 获得当年所有数据
	 * @return
	 */
	public List<J731POJO> getYearInfo(String year){
		String sql="select " +  field  + " from " + tableName +
				    " where convert(varchar(4),T721_TeachResItem_Tea$.Time,120)=" + year;
		Connection conn=DBConnection.instance.getConnection();
		Statement st=null;
		ResultSet rs=null;
		List<J731POJO> list=null;
		System.out.println(sql);
		
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			list=DAOUtil.getList(rs, J731POJO.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
				DBConnection.close(conn);
				DBConnection.close(rs);
				DBConnection.close(st);
		}
		return list;
	}

}
