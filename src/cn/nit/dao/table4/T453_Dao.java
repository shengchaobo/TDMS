package cn.nit.dao.table4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.table4.T453_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T453_Dao {
	
	private String tableName = "T453_TeaCommunInfo_TeaInter$" ;
	private String field = "TeaUnitName,UnitId,Name,TeaId,CommunType,BeginTime,EndTime,InOrOut,CommunUnit,CommunContent,Time,Note";
	
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T453_Bean> getAllList(){
		
		String sql = "select " + field + " from " + tableName ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T453_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T453_Bean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
		
		return list ;
	}
	
	/**
	 * 分 页查询
	 * 
	 */
	public List<T453_Bean> queryPageList(int pageSize, int showPage){
				
		String queryPageSql = "select top " + pageSize + 
		field
		+ " from " + tableName + 
		" where (SeqNumber not in (select top " + pageSize * (showPage-1) + " SeqNumber from "+
		tableName + " order by SeqNumber)) order by SeqNumber" ;
		System.out.println(queryPageSql);
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T453_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs,T453_Bean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
		
		return list ;
	}
	
	
	/**
	 * 插入数据
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */
	public boolean insert(T453_Bean teaSay){
		
		Connection conn = DBConnection.instance.getConnection() ;
		return DAOUtil.insert(teaSay, tableName, field, conn) ;
	}
	
	
	public static void main(String args[]){
		T453_Dao testDao =  new T453_Dao() ;
		System.out.println(testDao.getAllList().size()) ;
	}

}
