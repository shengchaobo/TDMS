package cn.nit.dao.di;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.UserinfoBean;
import cn.nit.bean.UsersBean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.MD5Util;

public class DIUserManagerDAO {

	/**  用户表表名  */
	private String tableName = "Users" ;
	
	/**  用户角色名  */
	private String tableName1 = "DiUserRole" ;
	
	/**  角色表  */
	private String tableName2 = "Role" ;
	
	/**  表的主关键字  */
	private String key = "SeqNumber" ;
	
	/**  表的所有字段  */
	private String field = "Users.TeaID,TeaName,TeaPasswd,FromOffice,TeaEmail,UserNote,UnitID,Role.RoleID,RoleName" ;
	
	/**
	 * 添加用户
	 * @param userinfo
	 * @return
	 */
	public boolean insert(UsersBean userinfo){
		Connection conn = DBConnection.instance.getConnection() ;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
		boolean flag = false ;
		String tempField = "TeaID,TeaName,TeaPasswd,FromOffice,TeaEmail,UserNote,UnitID";
		try{
			flag = DAOUtil.insert(userinfo, tableName, tempField, conn) ;
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
		sql.append(" from " + tableName + "," + tableName1 + "," + tableName2) ;
		sql.append(" where 1=1 and " + tableName + ".TeaID=" + tableName1 + ".TeaID and " + tableName1 + ".RoleID=" + tableName2 + ".RoleID " ) ;
		
		System.out.println(sql.toString());
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
	public boolean update(UsersBean userinfo){
		
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
	 * 根据用户删除用户
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
	
	/**
	 * 重置密码
	 * @param ids
	 * @return
	 */
	public boolean resetPassword(String ids){
		
		int flag = 0 ;
		String sql = "update " + tableName + " set TeaPasswd='" + MD5Util.encode("123456") + "' where TeaID in " + ids ;
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
	
	/**
	 * 修改密码
	 * @param ids
	 * @return
	 */
	public boolean alertPassword(String teaID , String newPsd){
		
		int flag = 0 ;
		String sql = "update " + tableName + " set TeaPasswd='" + MD5Util.encode(newPsd) + "' where TeaID = " + teaID;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		//System.out.println(sql);
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
	
	/**
	 * 判断users表中是否已包含该用户
	 */
	public boolean hasUser(String userID){
		String sql = "select * from " + tableName + " where TeaID='" + userID + "'"	;
		Connection conn = DBConnection.instance.getConnection();
		Statement st = null;
		ResultSet rs = null;
		boolean flag = false;
		
		//System.out.println(sql);
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return false ;
		}finally{
			DBConnection.close(st) ;
			DBConnection.close(conn) ;
		}
		return flag;
	}
	
	/**
	 * 根据职工编号查询数据
	 * @param userId
	 * @return
	 *
	 * @time: 2014-4-21/上午08:44:09
	 */
	public List<UserinfoBean> getUserById(String userId){
		
		Connection conn = DBConnection.instance.getConnection() ;
		List<UserinfoBean> list = null ;
		
		StringBuffer sql = new StringBuffer() ;
		
		sql.append("select " + key + "," + field) ;
		sql.append(" from " + tableName + "," + tableName1 + "," + tableName2) ;
		sql.append(" where 1=1 and " + tableName + ".TeaID=" + tableName1 + ".TeaID and " + tableName1 + ".RoleID=" + tableName2 + ".RoleID " ) ;
		sql.append(" and " + tableName + ".TeaID='" + userId + "'") ;

		Statement st = null ;
		ResultSet rs = null ;
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, UserinfoBean.class) ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}finally{
			DBConnection.close(rs) ;
			DBConnection.close(st) ;
			DBConnection.close(conn) ;
		}
		
		return list ;
	}
	
	public List<UserinfoBean> getUserRoleById(String TeaID){
		Connection conn = DBConnection.instance.getConnection() ;
		List<UserinfoBean> list = null ;
		StringBuffer sql = new StringBuffer() ;
		
		sql.append("select " + key + "," + field) ;
		sql.append(" from " + tableName + "," + tableName1 + "," + tableName2) ;
		sql.append(" where 1=1 and " + tableName + ".TeaID=" + tableName1 + ".TeaID and " + tableName1 + ".RoleID=" + tableName2 + ".RoleID " ) ;
		sql.append(" and " + tableName + ".TeaID='" + TeaID + "'") ;

		Statement st = null ;
		ResultSet rs = null ;
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, UserinfoBean.class) ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}finally{
			DBConnection.close(rs) ;
			DBConnection.close(st) ;
			DBConnection.close(conn) ;
		}
		
		return list ;
	}
	
	public List<UserinfoBean> getList(){
		
		List<UserinfoBean> list = null ;
		StringBuffer sql = new StringBuffer() ;
		sql.append("select " + field) ;
		sql.append(" from " + tableName) ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, UserinfoBean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return list ;
		}
		return list ;
	}
	
	public boolean batchInsert(List<UserinfoBean> list){
		boolean flag = false ;
		Connection conn =DBConnection.instance.getConnection() ;
		
		try{
			flag = DAOUtil.batchInsert(list, tableName, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}
		return flag ;
	}
	
	/**
	 * 导出所有用户
	 * @param conditions
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<UserinfoBean> totalList(){
		List<UserinfoBean> list = null ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		StringBuffer sql = new StringBuffer() ;
		sql.append("select " + key + "," + field) ;
		sql.append(" from " + tableName + "," + tableName1 + "," + tableName2) ;
		sql.append(" where 1=1 and " + tableName + ".TeaID=" + tableName1 + ".TeaID and " + tableName1 + ".RoleID=" + tableName2 + ".RoleID " ) ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
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
}
