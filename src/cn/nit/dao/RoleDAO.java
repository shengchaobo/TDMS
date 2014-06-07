package cn.nit.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.RoleBean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class RoleDAO {

	private String tableName = "role" ;
	
	private String field = "RoleID,RoleName,UnitName,RoleDest" ;
	
	/**
	 * 获取所有的用户角色
	 * @return
	 */
	public List<RoleBean> getList(){
		
		List<RoleBean> list = null ;
		StringBuffer sql = new StringBuffer() ;
		sql.append("select " + field) ;
		sql.append(" from " + tableName) ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, RoleBean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return list ;
		}
		
		return list ;
	}
	
	public static void main(String args[]){
		RoleDAO userRoleDao = new RoleDAO() ;
		System.out.println(userRoleDao.getList().size()) ;
	}
}
