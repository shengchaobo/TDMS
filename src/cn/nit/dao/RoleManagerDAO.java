package cn.nit.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.Trees;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class RoleManagerDAO {

	public List<Trees> getRoleAuth(String roleId){
		StringBuffer sql = new StringBuffer("select t.TreeId as treeId,t.TreeName as treeName,t.Url as url,t.ParentId as ParentId") ;
		sql.append(" from DiTrees as t,DiRoleTree as ra ") ;
		sql.append("where t.TreeID=ra.TreeID and ra.RoleID=" + roleId) ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try {
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			return DAOUtil.getList(rs, Trees.class) ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}finally{
			DBConnection.close(st) ;
			DBConnection.close(conn) ;
		}
	}
	
}
