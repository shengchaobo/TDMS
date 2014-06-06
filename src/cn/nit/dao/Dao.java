/* 
 * @Title: Dao.java
 * @Package cn.bjtu.dao
 * @Description 
 * @author Lei Xia
 *      Email: xialei199023@163.com
 * @copyright BJTU(C)2014
 * @date 2014-2-25 下午03:12:09
 * @version V1.0 
 */
package cn.nit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import cn.nit.dbconnection.DBConnection;

/**
 * 数据操作类（Dao），建议所有表的Dao类都继承这个类，并重写其中的所有方法。
 * 如果要执行的操作不属于其中的 任何一类，则需自定义新的方法实现，如多表查询。
 * 
 * @author Lei Xia
 * @time: 2014-2-25/下午03:12:09
 */
public class Dao {

	protected Connection connection;

	public Dao() {
		connection = DBConnection.instance.getConnection();
	}

	/**
	 * 执行SQL语句（可以执行INSERT、UPDATE和DELETE语句）
	 * 
	 * @param sqlStr
	 * @return
	 * 
	 * @time: 2014-3-3/下午03:50:26
	 */
	public final boolean excute(String sqlStr) {
		Statement st = null;
		try {
			st = this.connection.createStatement();
			if (st.executeUpdate(sqlStr) == 1)
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	protected final boolean deleteByCondi(String tableName, String conditionStr) {
		String sql = "DELETE FROM " + tableName + " WHERE " + conditionStr;
		PreparedStatement pst = null;
		try {
			pst = this.connection.prepareStatement(sql);

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

	/**
	 * 往数据库中插入一条数据
	 * 
	 * @param object
	 *            待出入的记录
	 * @return true 插入成功
	 * 
	 * @time: 2014-3-3/下午04:07:18
	 */
	public boolean insert(Object object) {
		return false;
	}

	/**
	 * 删除指定条件的记录
	 * 
	 * @param 删除条件
	 * @return true 删除成功
	 * 
	 * @time: 2014-3-3/下午04:07:40
	 */
	public boolean delete(String conditionStr) {
		return false;
	}

	/**
	 * 修改一条记录
	 * 
	 * @param object
	 *            新的记录
	 * @return true 如果修改成功
	 * 
	 * @time: 2014-3-3/下午04:15:10
	 */
	public boolean update(Object object) {
		return false;
	}

	/**
	 * 查询数据
	 * 
	 * @param conditionStr
	 *            查询条件
	 * @return 查询结果集合
	 * 
	 * @time: 2014-3-3/下午04:15:35
	 */
	public Vector select(String conditionStr) {
		return null;
	}
}
