package cn.nit.dao.table1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import cn.nit.bean.table1.T151Bean;
import cn.nit.bean.table1.T181Bean;
import cn.nit.constants.Constants;
import cn.nit.dbconnection.DBConnection;

import cn.nit.pojo.table1.T181POJO;
import cn.nit.util.DAOUtil;

public class T18DAO {
	
	/**  数据库表名  */
	private String tableName = "T181_SignedCooperIns_Res$" ;
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "FillDept,CooperInsName,CooperInsType,CooperInsLevel,SignedTime," +
			"UnitName,UnitID,UnitLevel,CheckState,Note,Time" ;
	
	/**
	 * 将数据表T181的实体类插入数据库
	 * @param schResIns
	 * @return
	 *
	 * @time: 2014-5-14/上午10:53:10
	 */
	public boolean insert(T181Bean t181Bean){
		
		//flag判断数据是否插入成功
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.insert(t181Bean, tableName, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
		}
		return flag ;
	}
	
	/**
	 * 讲数据批量插入t181表中
	 * @param list {@linkplain java.util.List<{@link cn.nit.bean.table1.T181Bean}>}
	 * @return true表示插入成功，false表示插入失败
	 */
	public boolean batchInsert(List<T181Bean> list){
		
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
	public int totalAuditingData(String conditions, String fillDept){
		
		StringBuffer sql = new StringBuffer() ;
		sql.append("select count(*)") ;
//		sql.append(" from "+ tableName);
		sql.append(" from " + tableName + " as t,DiDepartment dpt,DiAwardLevel dal") ;
		sql.append(" where dpt.UnitID=t.UnitID and dal.IndexID=t.UnitLevel ");		
		int total = 0 ;

//		System.out.println(sql.toString());
		if(fillDept != null && !fillDept.equals("")){
			sql.append(" and t.FillDept=" + fillDept) ;
		}
		
		if(conditions != null && !conditions.equals("")){
			sql.append(conditions) ;
		}
		System.out.println(sql.toString());
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
	
	/**
	 * @param conditions 查询条件
	 * @param fillUnitId 填报人单位号，如果为空，则查询所有未审核的数据，<br>如果不为空，则查询填报人自己单位的所有的数据
	 * @return
	 */
	public List<T181POJO> auditingData(String conditions, String fillDept, int page, int rows){
		
		StringBuffer sql = new StringBuffer() ;
		List<T181POJO> list =null ;
		sql.append("select t.SeqNumber,t.CooperInsName,t.CooperInsType,dal.AwardLevel as CooperInsLevel, t.CooperInsLevel as CooperInsLevelID" +
				",t.FillDept,t.SignedTime,t.UnitName,t.UnitID,dal.AwardLevel as UnitLevel,t.UnitLevel as UnitLevelID,t.CheckState, t.Note,t.Time");
		sql.append(" from "+tableName + " as t,DiDepartment dpt,DiAwardLevel dal");
		sql.append(" where   dpt.UnitID=t.UnitID and dal.IndexID=t.CooperInsLevel" );

		if(fillDept != null && !fillDept.equals("")){
			sql.append(" and t.FillDept=" + fillDept) ;
		}
		
		if(conditions != null){
			sql.append(conditions) ;
		}
		
		System.out.println(sql.toString());
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
//		System.out.println(sql.toString());
		
		try{
			st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY) ;
			st.setMaxRows(page * rows) ;
			rs = st.executeQuery(sql.toString()) ;
			rs.absolute((page - 1) * rows) ;//将光标移动到此 ResultSet 对象的给定行编号
			list = DAOUtil.getList(rs, T181POJO.class) ;
		
			
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}
		
		return list ;
	}
	
	/**用于数据导出*/
	public List<T181Bean> totalList(String fillUnitID,String year,int checkState){

		StringBuffer sql=new StringBuffer();
		sql.append("select t.SeqNumber,FillDept,t.CooperInsName,t.CooperInsType,dal.AwardLevel as CooperInsLevel,t.CooperInsLevel as CooperInsLevelID" +
				",t.SignedTime,t.UnitName,t.UnitID,dal.AwardLevel as UnitLevel,t.UnitLevel as UnitLevelID,t.Time,t.Note,t.CheckState" );
		sql.append(" from "+tableName + " as t,DiDepartment dpt,DiAwardLevel dal");
		sql.append(" where dpt.UnitID=t.UnitID and dal.IndexID=t.CooperInsLevel");
		sql.append(" and t.FillDept='"+fillUnitID+"'");
		sql.append(" and t.CheckState="+checkState);
		sql.append(" and Time like '"+year+"%'");
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T181Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, T181Bean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null;
		}
		
		return list ;
	}
	
	public boolean update(T181Bean t181Bean){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.update(t181Bean, tableName, key, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
		}
		return flag ;
	}
	
	/**删除某条数据*/
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
	
	public String getTableName(){
		return this.tableName ;
	}
	
	/**
	 * 找到该条数据的审核状态
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */	
	public int getCheckState(int seqNumber){
				
		String queryPageSql = "select CheckState " 
		+ " from " + tableName + 
		" where SeqNumber='" + seqNumber + "';" ;
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		int state = 1;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			
			while(rs.next()){
				state = rs.getInt(1) ;
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return 0 ;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
		
		return state ;
	}
	
	/**
	 * 更新某条数据的审核状态
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */	
	public boolean updateCheck(int seq, int checkState){
		
		int flag ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		String sql = "update " + tableName + " set CheckState=" + checkState +
		" where SeqNumber='" + seq + "';" ;		
		System.out.println(sql);
		try{			
			st = conn.createStatement();
			flag = st.executeUpdate(sql);					
		}catch(Exception e){
			e.printStackTrace() ;
			return false;
		}finally{
			DBConnection.close(conn) ;
		}
		
		if (flag == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 全部审核通过
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */	
	public boolean checkAll(){
		
		int flag ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		String sql = "update " + tableName + " set CheckState=" + Constants.PASS_CHECK +
		" where CheckState=" + Constants.WAIT_CHECK ;		
		
		System.out.println(sql);
		try{			
			st = conn.createStatement();
			flag = st.executeUpdate(sql);					
		}catch(Exception e){
			e.printStackTrace() ;
			return false;
		}finally{
			DBConnection.close(conn) ;
		}
		
		if (flag == 0) {
			return false;
		} else {
			return true;
		}
	}
	//设置审核的状态为1：即未审核状态
	public boolean updatCheck()
	{
		int flag = 0;
		StringBuffer sql = new StringBuffer() ;
		sql.append("update " + tableName+" set CheckState ="+Constants.WAIT_CHECK) ;
//		sql.append(" where " + key + " in " + ids) ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		
		try
		{
			st = conn.createStatement();
			flag = st.executeUpdate(sql.toString());			
		}catch(Exception e){
			e.printStackTrace();
			return false; 
		}finally{
			DBConnection.close(conn) ;
		}
		
		if (flag == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	
	public boolean deleteAll(){
		int flag = 0;
		String sql = "delete from "+tableName;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		try{
			st = conn.createStatement();
			flag = st.executeUpdate(sql);	
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
	
	public static void main(String arg[])
	{
		T18DAO dao=new T18DAO();
//		int n=dao.totalAuditingData(null, null);
//		List<T181Bean> list=dao.totalList();
//		int n=dao.totalAuditingData(null, "1012");
//		System.out.println(list.size());
//		System.out.println(n);
		 List<T181Bean> list = dao.totalList("1012", "2015", 2);
		System.out.println(list.size());
	}

}
