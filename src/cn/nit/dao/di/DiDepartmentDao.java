package cn.nit.dao.di;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class DiDepartmentDao {
	
	private String tableName = "DiDepartment" ;
	
	private String field = "UnitID,UnitName,Class1,Class2,Functions,Leader,TeaID,Note" ;
	
	/**
	 * 获取DiDepartment字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<DiDepartmentBean> getList(){
		
		String sql = "select " + field + " from " + tableName ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<DiDepartmentBean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, DiDepartmentBean.class) ;
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
	 * @param DiDepartment
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */
	public boolean insert(DiDepartmentBean department){
		
		Connection conn = DBConnection.instance.getConnection() ;
		return DAOUtil.insert(department, tableName, field, conn) ;
	}
	
	public static void main(String args[]){
		DiDepartmentDao departmentDao =  new DiDepartmentDao() ;
		System.out.println(departmentDao.getList().size()) ;
	}


}
