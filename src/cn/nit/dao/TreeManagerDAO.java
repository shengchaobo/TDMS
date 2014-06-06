package cn.nit.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.Trees;
import cn.nit.dao.Dao;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class TreeManagerDAO extends Dao{
	
	private String key = "TreeId" ;
	
	private String tableName = "DiTrees" ;
	
	private String field = "TreeName,Url,ParentId" ;
	
	public boolean insert(Trees tree){
		
		Connection conn = DBConnection.instance.getConnection() ;
		
		return DAOUtil.insert(tree, tableName, field, conn) ;
	}
	
	/**
	 * 根据树节点的父节点获取子树节点
	 * @param parentId
	 * @return
	 */
	public List<Trees> getTrees(int parentId){
		
		String sql = "select " + getFields() + " from " + tableName + " where parentId=" + parentId + " order by treeName" ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try {
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
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
	
	/**
	 * 获取角色不存在的功能列表
	 * @param userRoleId
	 * @param roleId
	 * @return
	 */
	public List<Trees> getNotExistFunctions(int userRoleId,int roleId){
		StringBuffer sql = new StringBuffer("select t.treeId as treeId,t.treeName as treeName,t.url as url,t.parentId as ParentId") ;
		sql.append(" from " + tableName +" as t,roles_auth as ra ") ;
		sql.append("where t.treeId=ra.treeId and roleId=" + roleId) ;
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
	
	/**
	 * 获取角色已存在的功能列表
	 * @param userRoleId
	 * @param roleId
	 * @return
	 */
	public List<Trees> getExistFunction(String roleId){
		StringBuffer sql = new StringBuffer("select t.TreeId as treeId,t.TreeName as treeName,t.Url as url,t.ParentId as ParentId") ;
		sql.append(" from " + tableName +" as t,DiRoleTree as ra ") ;
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
	
	public List<Trees> getDITreeByUserRole(String roleId, int parentId){
		
		StringBuffer sql = new StringBuffer("select t.TreeId as treeId,t.TreeName as treeName,t.Url as url,t.ParentId as ParentId") ;
		sql.append(" from " + tableName +" as t,DiRoleTree as ra ") ;
		sql.append("where t.TreeID=ra.TreeID and ra.RoleID=" + roleId) ;
		sql.append(" and t.ParentId=" + parentId) ;
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
	
	public String getFields(){
		return this.key + "," + field ;
	}
	
	public static void main(String args[]){
		TreeManagerDAO tmdao = new TreeManagerDAO() ;
		System.out.println(tmdao.getExistFunction("002").size()) ;
	}
}
