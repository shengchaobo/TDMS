package cn.nit.dao.di;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.UserinfoBean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.MD5Util;

public class DIUserManagerDAO {

	/**  用户表表名  */
	private String tableName = "Users" ;
	
	/**  表的主关键字  */
	private String key = "SeqNumber" ;
	
	/**  表的所有字段  */
	private String field = "TeaID,TeaName,TeaPasswd,FromOffice,TeaEmail,UserNote,UnitID" ;
	
	/**
	 * 添加用户
	 * @param userinfo
	 * @return
	 */
	public boolean insert(UserinfoBean userinfo){
		Connection conn = DBConnection.instance.getConnection() ;
		boolean flag = false ;
		
		try{
			flag = DAOUtil.insert(userinfo, tableName, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}
		
		return flag ;
	}
	
	/**
	 * 获取用户的总记录数
	 * @param conditions
	 * @return
	 */
	public int totalUsers(String conditions){
		
		StringBuffer sql = new StringBuffer() ;
		sql.append("select count(*)") ;
		sql.append(" from " + tableName) ;
		sql.append(" where 1=1") ;
		int total = 0 ;
		
		if(conditions != null && !conditions.equals("")){
			sql.append(conditions) ;
		}
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			
			if(rs == null){
				return total ;
			}
			
			while(rs.next()){
				total = rs.getInt(1) ;
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return 0 ;
		}finally{
			DBConnection.close(rs) ;
			DBConnection.close(st) ;
			DBConnection.close(conn) ;
		}
		return total ;
	}
	
	/**
	 * 加载所有用户
	 * @param conditions
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<UserinfoBean> loadUsers(String conditions, int page, int rows){
		List<UserinfoBean> list = null ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		StringBuffer sql = new StringBuffer() ;
		sql.append("select " + key + "," + field) ;
		sql.append(" from " + tableName) ;
		sql.append(" where 1=1") ;
		
		if(conditions != null){
			sql.append(conditions) ;
		}
		
		try{
			st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY) ;
			st.setMaxRows(page * rows) ;
			rs = st.executeQuery(sql.toString()) ;
			rs.absolute((page - 1) * rows) ;
			list = DAOUtil.getList(rs, UserinfoBean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(st) ;
			DBConnection.close(conn) ;
		}
		
		return list ;
	}
	
	/**
	 * 更新用户信息
	 * @param userinfo
	 * @return
	 */
	public boolean update(UserinfoBean userinfo){
		
		String field = "TeaID,TeaName,FromOffice,TeaEmail,UserNote,UnitID" ;
		Connection conn = DBConnection.instance.getConnection() ;
		boolean flag = false ;
		
		try{
			flag = DAOUtil.update(userinfo, tableName, key, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			 return false ;
		}
		
		return flag ;
	}
	
	/**
	 * 根据用户SeqNumber删除用户
	 * @param ids
	 * @return
	 */
	public boolean deleteByIds(String ids){
		
		StringBuffer sql = new StringBuffer() ;
		sql.append("delete from " + tableName) ;
		sql.append(" where " + key + " in " + ids) ;
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
	
	/**
	 * 重置密码
	 * @param ids
	 * @return
	 */
	public boolean resetPassword(String ids){
		
		int flag = 0 ;
		String sql = "update " + tableName + " set TeaPasswd='" + MD5Util.encode("123456") + "' where " + key + " in " + ids ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		
		try{
			st = conn.createStatement() ;
			flag = st.executeUpdate(sql) ;
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
}
