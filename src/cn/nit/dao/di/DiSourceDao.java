package cn.nit.dao.di;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.di.DiSourceBean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class DiSourceDao {

	private String tableName = "DiSource" ;
	
	private String field = "IndexID,Source" ;
	
	/**
	 * 获取DiSource字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<DiSourceBean> getList(){
		
		String sql = "select " + field + " from " + tableName ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<DiSourceBean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, DiSourceBean.class) ;
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
	 * @param DiSource
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */
	public boolean insert(DiSourceBean source){
		
		Connection conn = DBConnection.instance.getConnection() ;
		return DAOUtil.insert(source, tableName, field, conn) ;
	}
	
	public static void main(String args[]){
		DiSourceDao sourceDao =  new DiSourceDao() ;
		System.out.println(sourceDao.getList().size()) ;
	}

}
