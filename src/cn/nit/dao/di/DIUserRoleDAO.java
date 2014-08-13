package cn.nit.dao.di;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.di.DiUserRoleBean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class DIUserRoleDAO {

	private String tableName = "DiUserRole" ;
	
	private String field = "TeaID,RoleID" ;
	
	public List<DiUserRoleBean> getList(){
		
		List<DiUserRoleBean> list = null ;
		StringBuffer sql = new StringBuffer() ;
		sql.append("select " + field) ;
		sql.append(" from " + tableName) ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, DiUserRoleBean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return list ;
		}
		
		return list ;
	}
	
	public boolean insert(DiUserRoleBean userRole){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.insert(userRole, tableName, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}
		
		return flag ;
	}
	
	public boolean update(DiUserRoleBean userRole){
		
		Connection conn = DBConnection.instance.getConnection() ;
		boolean flag = false ;
		
		try{
			flag = DAOUtil.update(userRole, tableName, "TeaID", "RoleID", conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			 return false ;
		}
		
		return flag ;
	}
	
	/**
	 * 根据用户删除用户角色对
	 * @param ids
	 * @return
	 */
	public boolean deleteByIds(String ids){
		
		StringBuffer sql = new StringBuffer() ;
		sql.append("delete from " + tableName) ;
		sql.append(" where TeaID  in " + ids) ;
		int flag = 0 ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		
		try{
			st = conn.createStatement() ;
			flag = st.executeUpdate(sql.toString()) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return false ;
		}finally{
			DBConnection.close(st) ;
			DBConnection.close(conn) ;
		}
		
		if(flag > 0){
			return true ;
		}else{
			return false ;
		}
		
	}
	
	public static void main(String args[]){
		DIUserRoleDAO userRoleDao = new DIUserRoleDAO() ;
		System.out.println(userRoleDao.getList().size()) ;
	}
}
