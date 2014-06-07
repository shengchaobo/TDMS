/* 
* @Title: DICourseAttriDAO.java
* @Package cn.bjtu.di.dao
* @Description 
* @author Lei Xia
*      Email: xialei199023@163.com
* @copyright BJTU(C)2014
* @date 2014-5-15 上午11:00:26
* @version V1.0 
*/
package cn.nit.dao.di;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.di.DiCourseAttriBean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

/**
 * 
 * @author Lei Xia
 * @time: 2014-5-15/上午11:00:26
 */
public class DiCourseAttriDao {

	private String tableName = "DICourseAttri" ;
	
	private String field = "IndexID,CourseAttri" ;
	
	public List<DiCourseAttriBean> getList(){
		
		List<DiCourseAttriBean> list = null ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		StringBuffer sql = new StringBuffer() ;
		sql.append("select " + field + " from " + tableName) ;
		try{
			st=conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, DiCourseAttriBean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return list ;
		}finally{
			DBConnection.close(rs) ;
			DBConnection.close(st) ;
			DBConnection.close(conn) ;
		}
		return list ;
	}
}
