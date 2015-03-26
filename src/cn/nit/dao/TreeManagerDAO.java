package cn.nit.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
	 * @param roleId
	 * @return
	 */
	public List<Trees> getTrees(int parentId){
		
		List<Trees> temp = new ArrayList<Trees>();
		String sql = "select " + getFields() + " from " + tableName + " where parentId=" + parentId + " order by treeName" ;		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try {
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			temp = DAOUtil.getList(rs, Trees.class) ;
			
			//保证2和4系列表的顺序
			if(parentId == 1014) {
				Trees tree1 = temp.remove(0);
				Trees tree2 = temp.remove(0);
				Trees tree3 = temp.remove(0);
				temp.add(19,tree1);
				temp.add(20,tree2);
				temp.add(21,tree3);
			}
			else if(parentId == 1025){
				Trees tree = temp.remove(0);
				Trees tree1 = temp.remove(1);
				temp.add(25,tree);
				temp.add(26,tree1);
			}
			return temp ;
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
	
	/**
	 * 根据角色和父结点获得子结点
	 * @param roleId
	 * @param parentId
	 * @return
	 */
	public List<Trees> getDITreeByUserRole(int parentId, String roleId){
		
		StringBuffer sql = new StringBuffer("select t.TreeId as treeId,t.TreeName as treeName,t.Url as url,t.ParentId as ParentId") ;
		sql.append(" from " + tableName +" as t,DiRoleTree as ra ") ;
		sql.append("where t.TreeID=ra.TreeID and ra.RoleID=" + roleId) ;
		sql.append(" and t.ParentId=" + parentId) ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		System.out.println(sql.toString());
		
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
	
	public boolean removeTrees(int nodeid){
		
		String sql =  "select TreeId from " + tableName + " where ParentId = " + nodeid;
		String removesql =  "delete from " + tableName + " where TreeId = " + nodeid;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try {
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;			
			while(rs.next()){
				removeTrees(rs.getInt("TreeId"));
			}			
			st.execute(removesql) ;
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false ;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
	}
	
	public String getFields(){
		return this.key + "," + field ;
	}
	
	/**
	 * 获得全部的tree
	 * @param roleId
	 * @param parentId
	 * @return
	 */
	public List<Trees> getTreesList(){
		
		StringBuffer sql = new StringBuffer("select *") ;
		sql.append(" from " + tableName + " where treeName!='审核通过数据' and treeName!='审核未通过数据' order by treeName");
		
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
	 * 获得全部tree的总数
	 * @param roleId
	 * @param parentId
	 * @return
	 */
	public int getSumTreeNum(){
		
		int count = 0;
		
		StringBuffer sql = new StringBuffer("select count(*)") ;
		sql.append(" from " + tableName);
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try {
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			while(rs.next()){
				count = rs.getInt(1);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0 ;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		
		return count;
	}
	
	public static void main(String args[]){
		TreeManagerDAO tmdao = new TreeManagerDAO() ;
		System.out.println(tmdao.getDITreeByUserRole(0,"002").size()) ;
	}
}
