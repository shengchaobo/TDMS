package cn.nit.dao.di;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.nit.bean.di.DiRoleTreeBean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class DIRoleTreeDAO {

	private String tableName = "DiRoleTree" ;
	
	private String field = "RoleID,TreeID" ;
	
	public List<DiRoleTreeBean> getList(){
		
		List<DiRoleTreeBean> list = null ;
		StringBuffer sql = new StringBuffer() ;
		sql.append("select " + field) ;
		sql.append(" from " + tableName) ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, DiRoleTreeBean.class) ;
		}catch (Exception e){
			e.printStackTrace() ;
			return list ;
		}finally{
			DBConnection.close(rs) ;
			DBConnection.close(st) ;
			DBConnection.close(conn) ;
		}
		
		return list ;
	}
	
	/**
	 * 得到一个角色的所有权限,即treeID
	 * @param roleId
	 * @return
	 */
	public List<Integer> getRoleTreeIDs(String roleId){
		List<Integer> list = new ArrayList<Integer>();
		StringBuffer sql = new StringBuffer() ;
		sql.append("select TreeID ");
		sql.append("from " + tableName);
		sql.append(" where RoleID=" + "'" + roleId + "'");
		
		Connection conn = DBConnection.instance.getConnection();
		Statement st = null;
		ResultSet rs = null;
		
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			while(rs.next()){
				Integer temp = rs.getInt("TreeID");
				list.add(temp);
			}			
		}catch (Exception e){
			e.printStackTrace() ;
			return list ;
		}finally{
			DBConnection.close(rs) ;
			DBConnection.close(st) ;
			DBConnection.close(conn) ;
		}
		
		return list ;
	}
	
	public boolean save(String roleId,List<DiRoleTreeBean> list){
		
		boolean flag = false;
		
		StringBuffer sql = new StringBuffer() ;
		sql.append("delete from " + tableName);
		sql.append(" where RoleID=" + "'" + roleId + "'");
		
		Connection conn = DBConnection.instance.getConnection();
		Statement st = null;

		try{
			st = conn.createStatement();
			st.executeUpdate(sql.toString());
			flag = DAOUtil.batchInsert(list, tableName, field, conn) ;
			
		}catch (Exception e){
			e.printStackTrace() ;
			return flag;
		}finally{
			DBConnection.close(st) ;
			DBConnection.close(conn) ;
		}
		
		return flag;
	}
	
	public static void main(String args[]){
		DIRoleTreeDAO roleTreeDao = new DIRoleTreeDAO() ;
		System.out.println(roleTreeDao.getList().size()) ;
	}
}
