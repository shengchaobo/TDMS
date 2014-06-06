/* 
 * @Title: DBConnection.java
 * @Package org.dd.dbconnection
 * @Description 连接数据库操作
 * @author Lei Xia
 *      Email: xialei199023@163.com
 * @copyright BJTU(C)2013
 * @date 2013-5-9 上午09:57:05
 * @version V1.0 
 */
package cn.nit.dbconnection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;


/**
 * 数据库链接类
 * @author Lei Xia
 * @time: 2014-4-18/下午07:52:27
 */
public class DBConnection {

	/**  properties文件流  */
	private InputStream inStream ;

	/**  property实体类  */
	private static Properties property = null ;

	/**  数据库链接源  */
	private DataSource datasource ;

	/**  初始化链接类  */
	public static DBConnection instance = new DBConnection() ;



	private DBConnection(){

		inStream = this.getClass().getResourceAsStream("/cn/nit/dbconnection/dbConf.properties") ;
		property = new Properties() ;

		try {
			property.load(inStream) ;
			datasource = BasicDataSourceFactory.createDataSource(property) ;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 *获取数据库链接
	 * @return
	 *
	 * @time: 2014-4-18/下午07:43:40
	 */
	public Connection getConnection(){

		try {
			return datasource.getConnection() ;
		} catch (SQLException e) {
			e.printStackTrace() ;
			throw new RuntimeException("得到数据库连接失败！") ;
		}
	}

	/**
	 *关闭数据库连接
	 * @param conn
	 *
	 * @time: 2014-4-18/下午07:44:08
	 */
	@SuppressWarnings("null")
	public static void close(Connection conn){

		try{
			if(conn != null || !conn.isClosed()){
				conn.close() ;
			}

		}catch(SQLException e){
//			e.printStackTrace() ;
			return ;
		}
	}

	/**
	 *关闭statement
	 * @param pstmt
	 *
	 * @time: 2014-4-18/下午07:44:24
	 */
	@SuppressWarnings("null")
	public static void close(Statement pstmt){

		try{
			if(pstmt != null || !pstmt.isClosed()){
				pstmt.close() ;
			}

		}catch(SQLException e){
//			e.printStackTrace() ;
			return ;
		}
	}

	/**
	 *关闭resultset
	 * @param rs
	 *
	 * @time: 2014-4-18/下午07:44:43
	 */
	@SuppressWarnings("null")
	public static void close(ResultSet rs){

		try{
			if(rs != null || !rs.isClosed()){
				rs.close() ;
			}
		}catch(SQLException e){
//			e.printStackTrace() ;
			return ;
		}
	}

	public static void main(String args[]){
		InputStream inStream = DBConnection.class.getResourceAsStream("/cn/bjtu/dbconnection/dbConf.properties") ;
		System.out.println(DBConnection.class.getClass().getResource("/cn/bjtu/dbconnection/dbConf.properties")) ;
		DataSource datasource ; 
		Properties property = new Properties() ;

		try {
			property.load(inStream) ;
			datasource=BasicDataSourceFactory.createDataSource(property) ;
			Connection conn = datasource.getConnection() ;
			System.out.println(conn == null) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
