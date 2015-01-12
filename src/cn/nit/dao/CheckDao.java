package cn.nit.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.CheckInfo;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class CheckDao {
	
	private String tableName = "CheckInfo" ;
	private String field = "TableID,CheckID,CheckInfo";

	/**
	 * 获取该表所有的审核信息
	 * @param parentId
	 * @param roleId
	 * @return
	 */
	public List<CheckInfo> loadInfo(String name){
		
		String sql = "select * from " + tableName + " where TableID='" + name + "' order by CheckID" ;		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try {
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			return DAOUtil.getList(rs, CheckInfo.class) ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}finally{
			DBConnection.close(st) ;
			DBConnection.close(conn) ;
		}
		
	}
	
	
	/**
	 * 删除数据
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */	
	public boolean delete(String tableID, int checkID) {

		int flag = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("delete from " + tableName);
		sql.append(" where TableID='" + tableID + "' and CheckID=" + checkID + ";" );
		System.out.println(sql.toString());
		Connection conn = DBConnection.instance.getConnection();
		Statement st = null;

		try {
			st = conn.createStatement();
			flag = st.executeUpdate(sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (flag == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 添加数据
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */	
	public boolean addInfo(CheckInfo checkInfo) {

		Connection conn = DBConnection.instance.getConnection() ;
		return DAOUtil.insert(checkInfo, tableName, field,  conn) ;
	}
}
