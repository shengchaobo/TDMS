/* 
* @Title: LoginUserpsdDao.java
* @Package cn.bjtu.dao
* @Description 
* @author Lei Xia
*      Email: xialei199023@163.com
* @copyright BJTU(C)2014
* @date 2014-2-25 下午03:11:47
* @version V1.0 
*/
package cn.nit.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import cn.nit.bean.LoginUserpsdBean;

/**
 * login_userpsd表的Dao类
 * 
 * @author Lei Xia
 * @time: 2014-2-25/下午03:11:47
 */
public class LoginUserpsdDao extends Dao {
	
	private final String tableName = "login_userpsd";

	/* 
	 * @see cn.bjtu.dao.Dao#delete()
	 *
	 * 2014-3-3 / 下午03:45:05
	 */
	@Override
	public boolean delete(String conditionStr) {
		return super.deleteByCondi(tableName, conditionStr);
	}

	/* 
	 * @see cn.bjtu.dao.Dao#insert(java.lang.Object)
	 *
	 * 2014-3-3 / 下午03:45:05
	 */
	@Override
	public boolean insert(Object object) {
		LoginUserpsdBean bean = (LoginUserpsdBean) object;
		String sql = "INSERT INTO " + tableName
				+ " (`username`, `psd`) " + "VALUES (?, ?);";
		PreparedStatement pst = null;
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1, bean.getUsername());
			pst.setString(2, bean.getPsd());

			if (pst.executeUpdate() == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) {
					pst.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	/* 
	 * @see cn.bjtu.dao.Dao#select(java.lang.String)
	 *
	 * 2014-3-3 / 下午03:45:05
	 */
	@Override
	public Vector<LoginUserpsdBean> select(String conditionStr) {
		Vector<LoginUserpsdBean> beans = new Vector<LoginUserpsdBean>();
		String sql = "SELECT * FROM " + tableName + " WHERE " + conditionStr;
		PreparedStatement pst = null;
		try {
			pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				LoginUserpsdBean bean = new LoginUserpsdBean();
				bean.setUsername(rs.getString("username"));
				bean.setPsd(rs.getString("psd"));

				beans.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) {
					pst.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return beans;
	}

	/* 
	 * @see cn.bjtu.dao.Dao#update(java.lang.Object)
	 *
	 * 2014-3-3 / 下午03:45:05
	 */
	@Override
	public boolean update(Object object) {
		LoginUserpsdBean bean = (LoginUserpsdBean) object;
		String sql = "UPDATE " + tableName
				+ " SET username=?, psd=?"
				+ " WHERE username='xxx'";
		PreparedStatement pst = null;
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1, bean.getUsername());
			pst.setString(2, bean.getPsd());

			if (pst.executeUpdate() == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) {
					pst.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	
}
