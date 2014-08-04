package cn.nit.dao.table3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import sun.security.krb5.internal.UDPClient;




import cn.nit.bean.table3.T33_Bean;
import cn.nit.dbconnection.DBConnection;


import cn.nit.pojo.table3.T33POJO;
import cn.nit.util.DAOUtil;

public class T33_DAO {
	
	//"SeqNumber,PostDocStaName,SetTime,ResearcherNum,UnitName,UnitID,Time,Note"

	/**  数据库表名  */
	private String tableName = "T33_JuniorMajInfo_Tea$" ;
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "TeaUnit,UnitID,MajorName,MajorID,MajorFieldName,AppvlSetTime,FirstAdmisTime," +
			"MajorYearLimit,IsSepcialMajor,IsKeyMajor,MajorLeader,LIsFullTime,MajorChargeMan,CIsFullTime,Time,Note" ;
	
	/**
	 * 将数据表311的实体类插入数据库
	 * @param undergraCSBase
	 * @return
	 *
	 * @time: 2014-5-14/上午10:53:10
	 */
	public boolean insert(T33_Bean postDocStaBean){
		
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
	public boolean batchInsert(List<T33_Bean> list){
		
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
		sql.append(" from " + tableName + " as t,DiDepartment dpt,DiMajorOne dmo ") ;
		sql.append(" where dpt.UnitID=t.UnitID and dmo.MajorNum=t.MajorID");		
		int total = 0 ;

//		System.out.println(sql.toString());
		if(fillUnitId != null && !fillUnitId.equals("")){
			sql.append(" and FillDept=" + fillUnitId) ;
		}
		
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
	
	
	public List<T33POJO> auditingData(String conditions, String fillDept, int page, int rows){
		
		StringBuffer sql = new StringBuffer() ;
		List<T33POJO> list =null ;
		sql.append("select t.SeqNumber,t.TeaUnit,t.UnitID,t.MajorName,dmo.MajorNum as MajorID,t.MajorID as MajorIDID,t.MajorFieldName,t.AppvlSetTime,t.FirstAdmisTime," +
			"t.MajorYearLimit,t.IsSepcialMajor,t.IsKeyMajor,t.MajorLeader,t.LIsFullTime,t.MajorChargeMan,t.CIsFullTime,t.Time,t.Note");
		sql.append(" from "+tableName + " as t,DiDepartment dpt,DiMajorOne dmo ");
		sql.append(" where dpt.UnitID=t.UnitID and dmo.MajorNum=t.MajorID" );

		if(fillDept != null && !fillDept.equals("")){
			sql.append(" and FillDept=" + fillDept) ;
		}
		
		if(conditions != null){
			sql.append(conditions) ;
		}
		
		sql.append(" order by UnitID asc") ;
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
//		System.out.println(sql.toString());
		
		try{
			st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY) ;
			st.setMaxRows(page * rows) ;
			rs = st.executeQuery(sql.toString()) ;
			rs.absolute((page - 1) * rows) ;//将光标移动到此 ResultSet 对象的给定行编号
			list = DAOUtil.getList(rs, T33POJO.class) ;
		
			
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}
		
		return list ;
	}
	
	
	/**用于数据导出*/
	public List<T33_Bean> totalList(){

		StringBuffer sql=new StringBuffer();
		sql.append("select t.SeqNumber,t.TeaUnit,t.UnitID,t.MajorName,dmo.MajorNum as MajorID,t.MajorID as MajorIDID,t.MajorFieldName,t.AppvlSetTime,t.FirstAdmisTime," +
		"t.MajorYearLimit,t.IsSepcialMajor,t.IsKeyMajor,t.MajorLeader,t.LIsFullTime,t.MajorChargeMan,t.CIsFullTime,t.Time,t.Note");
	sql.append(" from "+tableName + " as t,DiDepartment dpt,DiMajorOne dmo ");
	sql.append(" where dpt.UnitID=t.UnitID and dmo.MajorNum=t.MajorID");

		
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T33_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, T33_Bean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null;
		}
		
		return list ;
	}
	
	
	public boolean update(T33_Bean t33_Bean){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.update(t33_Bean, tableName, key, field, conn) ;
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
	
	
	public int getMajorNum(String year){
		
		int count = 0;
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT COUNT(DISTINCT MajorName)");
        sql.append(" from "+tableName+" where Time like '"+year+"%'");
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			if(rs == null){
				return count ;
			}
			
			while(rs.next()){
				count = rs.getInt(1) ;
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return 0;
		}
		
		return count ;
		
		
	}
	
	

	
	public static void main(String args[]){
		
//		T311_DAO t311Dao = new T311_DAO() ;
//		T311_Bean t33_Bean = new T311_Bean() ;
//		t33_Bean.setSeqNumber(18) ;
//		t33_Bean.setTime(new java.util.Date()) ;
//
//		System.out.println(t311Dao.update(t33_Bean)) ;
		
		
	}
	
	public String getTableName(){
		return this.tableName ;
	}


}
