package cn.nit.dao.table4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.table4.T48_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T48_Dao {
	private String tableName = "T48_TeachTeam_TeaTea$" ;
	private String tableName1 = "DiAwardLevel" ;
	private String field = "TeaUnit,UnitId,TeamName,TeamLevel,Leader,TeaID,GroupNum,GroupInfo,GainTime,AppvlID,Time,Note";
		
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T48_Bean> getAllList(){
		
		String sql = "select " + field + " from " + tableName ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T48_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs,T48_Bean.class) ;
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
	public List<T48_Bean> queryPageList(int pageSize, int showPage){
				
		String queryPageSql = "select top " + pageSize + 
		" TeaUnit,UnitId,TeamName,AwardLevel AS TeamLevel,Leader,TeaID,GroupNum,GroupInfo,GainTime,AppvlID,Time,Note"
		+ " from " + tableName + 
		" left join " + tableName1+ " on " + "TeamLevel=" + tableName1 + ".IndexID " +
		" where (SeqNumber not in (select top " + pageSize * (showPage-1) + " SeqNumber from "+
		tableName + " order by SeqNumber)) order by SeqNumber" ;
		System.out.println(queryPageSql);
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T48_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T48_Bean.class) ;
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
	 * @param 
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */
	public boolean insert(T48_Bean teaTeam){
		
		Connection conn = DBConnection.instance.getConnection() ;
		return DAOUtil.insert(teaTeam, tableName, field, conn) ;
	}
	
	
	public static void main(String args[]){
		T48_Dao testDao =  new T48_Dao() ;
		System.out.println(testDao.getAllList()) ;
	}

}
