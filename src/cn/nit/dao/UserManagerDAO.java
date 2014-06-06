/* 
* @Title: UserManagerDAO.java
* @Package cn.bjtu.dao
* @Description 
* @author Lei Xia
*      Email: xialei199023@163.com
* @copyright BJTU(C)2014
* @date 2014-4-18 下午09:32:26
* @version V1.0 
*/
package cn.nit.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.nit.bean.UserinfoBean;
import cn.nit.bean.other.UserRoleBean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.MD5Util;

/**
 * 用户管理�?
 * @author Lei Xia
 * @time: 2014-4-18/下午09:32:26
 */
public class UserManagerDAO {

	private String field = "TeaID,TeaPasswd,TeaName,FromOffice,TeaEmail,UserNote" ;
	
	private String tableName = "Users" ;
	
	/**
	 * 新增用户
	 * @param user
	 * @return
	 *
	 * @time: 2014-4-18/下午10:33:31
	 */
	public boolean insertUser(UserinfoBean user){
		
		Connection conn = DBConnection.instance.getConnection() ;
		return DAOUtil.insert(user, tableName, field, conn) ;
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
		StringBuffer sql = new StringBuffer("select " + field) ;
		sql.append(" from " + tableName) ;

		sql.append(" where TeaID='" + userId + "'") ;

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
	
	public List<UserRoleBean> getUserRoleById(String TeaID){
		Connection conn = DBConnection.instance.getConnection() ;
		List<UserRoleBean> list = null ;
		StringBuffer sql = new StringBuffer("select u.TeaID,u.TeaPasswd,u.TeaName,u.FromOffice,u.TeaEmail,ur.RoleID") ;
		sql.append(" from Users as u,DiUserRole as ur") ;
<<<<<<< HEAD
		sql.append(" where u.TeaID='" + TeaID + "'") ;
=======
		sql.append(" where u.TeaID=" + TeaID) ;
>>>>>>> 8f91a9f7c9fcbb508bbfcac5d03bb75c20188fab
		Statement st = null ;
		ResultSet rs = null ;
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, UserRoleBean.class) ;
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
	
	public static void main(String args[]){
		List<UserinfoBean> list = new ArrayList<UserinfoBean>() ;
		UserManagerDAO userDao = new UserManagerDAO() ;
		UserinfoBean user = new UserinfoBean() ;
		user.setTeaID("") ;
		System.out.println(user.getTeaID() == null) ;
		user.setTeaName("测试") ;
		user.setFromOffice("教务�?) ;
		user.setTeaPasswd(MD5Util.encode("123456")) ;
		user.setTeaEmail("123456@qq.com") ;
		list.add(user) ;
		UserinfoBean user2 = new UserinfoBean() ;
		user2.setTeaID("12313") ;
		user2.setTeaName("测试") ;
		user2.setFromOffice("教务�?) ;
		user2.setTeaPasswd(MD5Util.encode("123456")) ;
		user2.setTeaEmail("123456@qq.com") ;
		list.add(user2) ;
		userDao.batchInsert(list) ;
//		userDao.insertUser(user) ;
//		System.out.println(userDao.getList().size()) ;
//		user.setTeaID("2014002") ;
//		user.setTeaName("测试") ;
//		user.setFromOffice("教务�?) ;
//		user.setTeaPasswd(MD5Util.encode("123456")) ;
//		user.setTeaEmail("123456@qq.com") ;
//		userDao.insertUser(user) ;
//		System.out.println(userDao.getUserRoleById("2014001").get(0).getRoleId()) ;
	}
}
