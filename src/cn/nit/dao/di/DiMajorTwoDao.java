package cn.nit.dao.di;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.di.DiMajorTwoBean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class DiMajorTwoDao {
	
	private String tableName = "DiMajorTwo" ;
	
	private String field = "MajorNum,MajorName,Version,Duration,Direction,UnitId" ;
	
	/**
	 * 获取DiDegree字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<DiMajorTwoBean> getList(){
		
		String sql = "select " + field + " from " + tableName ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<DiMajorTwoBean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, DiMajorTwoBean.class) ;
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
	 * @param DiDegree
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */
	public boolean insert(DiMajorTwoBean major){
		
		Connection conn = DBConnection.instance.getConnection() ;
		return DAOUtil.insert(major, tableName, field, conn) ;
	}
	
	public static void main(String args[]){
		DiMajorTwoDao major =  new DiMajorTwoDao() ;
		System.out.println(major.getList().size()) ;
	}


}
