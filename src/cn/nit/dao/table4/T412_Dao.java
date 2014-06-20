package cn.nit.dao.table4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;

import cn.nit.bean.table4.T412_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T412_Dao {
	
	private String tableName = "T412_AllMajTeaInfo_TeaPer$" ;
	private String field = "FromTeaUnit,TeaUnitID,MajorID,MajorName,TeaId,TeaName,Time,Note,FillUnitID";
	private String keyfield = "SeqNumber";
	
	
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T412_Bean> getAllList(){
		
		String sql = "select " + keyfield+ "," +field + " from " + tableName ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T412_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T412_Bean.class) ;
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
	public List<T412_Bean> queryPageList(int pageSize, int showPage){
				
		String queryPageSql = "select top " + pageSize + keyfield+ "," +
		"FromTeaUnit,TeaUnitID,MajorID,MajorName,TeaId,TeaName,Time,Note"
		+ " from " + tableName + 
		" where (SeqNumber not in (select top " + pageSize * (showPage-1) + " SeqNumber from "+
		tableName + " order by SeqNumber)) order by SeqNumber" ;
		System.out.println(queryPageSql);
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T412_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T412_Bean.class) ;
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
	public boolean insert(T412_Bean majorteaBean){
		
		Connection conn = DBConnection.instance.getConnection() ;
		return DAOUtil.insert(majorteaBean, tableName, field, conn) ;
	}
	
	
	public static void main(String args[]){
		T412_Dao testDao =  new T412_Dao() ;
		System.out.println(testDao.getAllList().size()) ;
	}

}
