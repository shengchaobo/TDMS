/* 
* @Title: DICourseCategoriesDAO.java
* @Package cn.bjtu.di.dao
* @Description 
* @author Lei Xia
*      Email: xialei199023@163.com
* @copyright BJTU(C)2014
* @date 2014-5-14 上午11:41:53
* @version V1.0 
*/
package cn.nit.dao.di;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.di.DiCourseCategoriesBean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

/**
 * 
 * @author Lei Xia
 * @time: 2014-5-14/上午11:41:53
 */
public class DiCourseCategoriesDao {

	private String tableName = "DiCourseCategories" ;
	
	private String field = "IndexID,CourseCategories" ;
	
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<DiCourseCategoriesBean> getList(){
		
		String sql = "select " + field + " from " + tableName ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<DiCourseCategoriesBean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, DiCourseCategoriesBean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(rs) ;
			DBConnection.close(st) ;
			DBConnection.close(conn) ;
		}
		
		return list ;
	}
	
	/**
	 * 插入数据
	 * @param DiCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */
	public boolean insert(DiCourseCategoriesBean DiCourseCategories){
		
		Connection conn = DBConnection.instance.getConnection() ;
		return DAOUtil.insert(DiCourseCategories, tableName, field, conn) ;
	}
	
	public static void main(String args[]){
		DiCourseCategoriesDao dicourseDao =  new DiCourseCategoriesDao() ;
		System.out.println(dicourseDao.getList().size()) ;
	}
}
