package cn.nit.dao.table3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import sun.security.krb5.internal.UDPClient;

import cn.nit.bean.table1.T181Bean;
import cn.nit.bean.table3.T311_Bean;

import cn.nit.dbconnection.DBConnection;

import cn.nit.pojo.table3.T311POJO;
import cn.nit.util.DAOUtil;

public class T311_DAO {
	
	//"SeqNumber,PostDocStaName,SetTime,ResearcherNum,UnitName,UnitID,Time,Note"

	/**  数据库表名  */
	private String tableName = "T311_PostDocSta_Per$" ;
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "PostDocStaName,SetTime,ResearcherNum,UnitName,UnitID,Time,Note" ;
	
	/**
	 * 将数据表311的实体类插入数据库
	 * @param undergraCSBase
	 * @return
	 *
	 * @time: 2014-5-14/上午10:53:10
	 */
	public boolean insert(T311_Bean postDocStaBean){
		
		//flag判断数据是否插入成功
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.insert(postDocStaBean, tableName, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
		}
		return flag ;
	}
	
	
	/**
	 * 讲数据批量插入T311表中
	 * @param list {@linkplain java.util.List<{@link cn.nit.bean.table5.T311_Bean}>}
	 * @return true表示插入成功，false表示插入失败
	 */
	public boolean batchInsert(List<T311_Bean> list){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		
		try{
			flag = DAOUtil.batchInsert(list, tableName, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}
		
		return flag ;
	}
	
	/**
	 * 查询待审核数据在数据库中共有多少条
	 * @param conditions 查询条件
	 * @param fillUnitId 填报人单位号，如果为空，则查询所有未审核的数据，<br>如果不为空，则查询填报人自己单位的所有的数据
	 * @return
	 */
	public int totalAuditingData(String conditions, String fillUnitId){
		
		StringBuffer sql = new StringBuffer() ;
		sql.append("select count(*)") ;
//		sql.append(" from "+ tableName);
		sql.append(" from " + tableName+" where PostDocStaName is not null") ;
	
		int total = 0 ;

//		System.out.println(sql.toString());
		if(fillUnitId != null && !fillUnitId.equals("")){
			sql.append(" and FillDept=" + fillUnitId) ;
		}
		System.out.println("查询条件");
		System.out.println(conditions);		
		if(conditions != null && !conditions.equals("")){
			sql.append(conditions) ;
		}
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
//			System.out.println(rs);
			if(rs == null){
				return total ;
			}
			
			while(rs.next()){
				total = rs.getInt(1) ;
//			   System.out.println("total:"+total);
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return 0 ;
		}
		return total ;
	}
	
	
	public List<T311POJO> auditingData(String conditions, String fillDept, int page, int rows){
		
		StringBuffer sql = new StringBuffer() ;
		List<T311POJO> list =null ;
		sql.append("select SeqNumber,PostDocStaName,SetTime,ResearcherNum, UnitName," +
				"UnitID,Note,Time");
		sql.append(" from "+tableName+" where PostDocStaName is not null");
		if(fillDept != null && !fillDept.equals("")){
			sql.append(" FillDept=" + fillDept) ;
		}
		
		if(conditions != null){
			sql.append(conditions) ;
		}
		System.out.println(sql.toString());
		sql.append(" order by SeqNumber desc") ;
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
//		System.out.println(sql.toString());
		
		try{
			st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY) ;
			st.setMaxRows(page * rows) ;
			rs = st.executeQuery(sql.toString()) ;
			rs.absolute((page - 1) * rows) ;//将光标移动到此 ResultSet 对象的给定行编号
			list = DAOUtil.getList(rs, T311POJO.class) ;
		
			
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}
		
		return list ;
	}
	
	
	
	public boolean update(T311_Bean t311Bean){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.update(t311Bean, tableName, key, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
		}
		return flag ;
	}
	
	public boolean deleteCoursesByIds(String ids){
		
		int flag = 0 ;
		StringBuffer sql = new StringBuffer() ;
		sql.append("delete from " + tableName) ;
		sql.append(" where " + key + " in " + ids) ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		
		try{
			st = conn.createStatement() ;
			flag = st.executeUpdate(sql.toString()) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return false ;
		}
		
		if(flag == 0){
			return false ;
		}else{
			return true ;
		}
	}
	
	/**用于数据导出*/
	public List<T311_Bean> totalList(){

		StringBuffer sql=new StringBuffer();
		sql.append("select SeqNumber,PostDocStaName,SetTime,ResearcherNum, UnitName," +
		"UnitID,Note,Time");
        sql.append(" from "+tableName);


		
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T311_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, T311_Bean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null;
		}
		
		return list ;
	}
	
	

	
	public static void main(String args[]){
		
		T311_DAO t311Dao = new T311_DAO() ;
		T311_Bean t311Bean = new T311_Bean() ;
		t311Bean.setSeqNumber(18) ;
		t311Bean.setTime(new java.util.Date()) ;

		System.out.println(t311Dao.update(t311Bean)) ;
		
		
	}
	
	public String getTableName(){
		return this.tableName ;
	}





}
