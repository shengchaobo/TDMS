package cn.nit.dao.di;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.di.DIRoleTreeBean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class DIRoleTreeDAO {

	private String tableName = "DiRoleTree" ;
	
	private String field = "RoleID,TreeID" ;
	
	public List<DIRoleTreeBean> getList(){
		
		List<DIRoleTreeBean> list = null ;
		StringBuffer sql = new StringBuffer() ;
		sql.append("select " + field) ;
		sql.append(" from " + tableName) ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, DIRoleTreeBean.class) ;
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
	
	public static void main(String args[]){
		DIRoleTreeDAO roleTreeDao = new DIRoleTreeDAO() ;
		System.out.println(roleTreeDao.getList().size()) ;
	}
}
