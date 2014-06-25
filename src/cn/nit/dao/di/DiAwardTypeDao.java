package cn.nit.dao.di;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.di.DiAwardTypeBean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class DiAwardTypeDao {

	private String tableName = "DiAwardType" ;
	
	private String field = "IndexID,AwardType" ;
	
	
	/**
	 * 获取DiTitleLevel字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<DiAwardTypeBean> getList(){
				
		String sql = "select " + field + " from " + tableName  ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<DiAwardTypeBean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, DiAwardTypeBean.class) ;
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
	 * 获取DiTitleLevel字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<DiAwardTypeBean> getList(String type){
		
		String cond = null;
		if(type.equals("1")){
			cond = "IndexID = '51000'";
		}
		else if(type.equals("2")){
			cond = "IndexID = '51001' or IndexID = '51002'";
		}
		else if(type.equals("3")){
			cond = "IndexID = '51003'";
		}
		else if(type.equals("4")){
			cond = "IndexID = '51004' or IndexID = '51005'";
		}
		else if(type.equals("5")){
			cond = "IndexID = '51006'";
		}
		else if(type.equals("6")){
			cond = "IndexID = '51007'";
		}
		
		String sql = "select " + field + " from " + tableName + " where " + cond ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<DiAwardTypeBean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, DiAwardTypeBean.class) ;
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
	public boolean insert(DiAwardTypeBean awardType){
		
		Connection conn = DBConnection.instance.getConnection() ;
		return DAOUtil.insert(awardType, tableName, field, conn) ;
	}
	
	public static void main(String args[]){
		DiAwardTypeDao awardTypeDao =  new DiAwardTypeDao() ;
		System.out.println(awardTypeDao.getList("1").size()) ;
	}

}
