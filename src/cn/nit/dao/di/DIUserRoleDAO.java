package cn.nit.dao.di;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.di.DIUserRoleBean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class DIUserRoleDAO {

	private String tableName = "DiUserRole" ;
	
	private String field = "TeaID,RoleID" ;
	
	public List<DIUserRoleBean> getList(){
		
		List<DIUserRoleBean> list = null ;
		StringBuffer sql = new StringBuffer() ;
		sql.append("select " + field) ;
		sql.append(" from " + tableName) ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, DIUserRoleBean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return list ;
		}
		
		return list ;
	}
	
	public boolean insert(DIUserRoleBean userRole){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.insert(DIUserRoleBean.class, tableName,field,conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}
		
		return flag ;
	}
	
	public static void main(String args[]){
		DIUserRoleDAO userRoleDao = new DIUserRoleDAO() ;
		System.out.println(userRoleDao.getList().size()) ;
	}
}
