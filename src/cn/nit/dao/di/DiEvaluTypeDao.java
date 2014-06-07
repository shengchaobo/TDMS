package cn.nit.dao.di;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.di.DiEvaluTypeBean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class DiEvaluTypeDao {

	private String tableName = "DiTitleLevel" ;
	
	private String field = "IndexID,EvaluType" ;
	
	/**
	 * 获取DiTitleLevel字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<DiEvaluTypeBean> getList(){
		
		String sql = "select " + field + " from " + tableName ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<DiEvaluTypeBean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, DiEvaluTypeBean.class) ;
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
	 * @param DiIdentiType
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */
	public boolean insert(DiEvaluTypeBean EvaluType){
		
		Connection conn = DBConnection.instance.getConnection() ;
		return DAOUtil.insert(EvaluType, tableName, field, conn) ;
	}
	
	public static void main(String args[]){
		DiEvaluTypeDao EvaluTypeDao =  new DiEvaluTypeDao() ;
		System.out.println(EvaluTypeDao.getList().size()) ;
	}

}
