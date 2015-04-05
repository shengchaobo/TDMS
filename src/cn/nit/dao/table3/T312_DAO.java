package cn.nit.dao.table3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;



import cn.nit.bean.table3.T311_Bean;
import cn.nit.bean.table3.T312_Bean;
import cn.nit.constants.Constants;

import cn.nit.dbconnection.DBConnection;

import cn.nit.pojo.table3.T312POJO;
import cn.nit.util.DAOUtil;


public class T312_DAO {
	//SeqNumber,StaName,StaID,UnitName,UnitID,StaType,Time,Note
	
	/**  数据库表名  */
	private String tableName = "T312_DocAndGraSta_Gra$" ;
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "StaName,StaID,UnitName,UnitID,StaType,Time,Note,CheckState" ;
	
	/**
	 * 将数据表312的实体类插入数据库
	 * @param undergraCSBase
	 * @return
	 *
	 * @time: 2014-5-14/上午10:53:10
	 */
	public boolean insert(T312_Bean docAndGraStaBean){
		
		//flag判断数据是否插入成功
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.insert(docAndGraStaBean, tableName, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
		}
		return flag ;
	}
	
	
	/**
	 * 讲数据批量插入T511表中
	 * @param list {@linkplain java.util.List<{@link cn.nit.bean.table5.T312_Bean}>}
	 * @return true表示插入成功，false表示插入失败
	 */
	public boolean batchInsert(List<T312_Bean> list){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		
		try{
			flag = DAOUtil.batchInsert(list, tableName, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
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
		sql.append(" from " + tableName +" where 1=1") ;		
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
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		return total ;
	}
	
	
	
	public List<T312POJO> auditingData(String conditions, String fillDept, int page, int rows){
		
		StringBuffer sql = new StringBuffer() ;
		List<T312POJO> list =null ;
		sql.append("select SeqNumber,StaName,StaID,UnitName,UnitID, StaType," +
				"Time,Note,CheckState");
		sql.append(" from "+tableName+" where 1=1 ");
//		sql.append(" where dpt.UnitID=t.UnitID and dal.IndexID=t.UnitLevel and dal.IndexID=t.CooperInsLevel");
//		sql.append("select t.SeqNumber,t.CSName,t.CSID,t.CSUnit,t.UnitID,t.FromTeaResOffice,t.TeaResOfficeID,cst.CourseCategories as CSType,t.CSType as CSTypeID,csn.CourseChar as CSNature,t.CSNature as CSNatureID,t.State,t.PubCSType,t.Time,t.Note") ;
//		sql.append(" from " + tableName + " as t,DiCourseChar csn,DiCourseCategories cst") ;
//		sql.append(" where audit!='0' and csn.IndexID=t.CSNature and cst.IndexID=t.CSType") ;
		//
//		System.out.println(sql.toString());
		if(fillDept != null && !fillDept.equals("")){
			sql.append(" and FillDept=" + fillDept) ;
		}
		
		if(conditions != null){
			sql.append(conditions) ;
		}
		
		//sql.append(" order by SeqNumber desc") ;
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
//		System.out.println(sql.toString());
		
		try{
			st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY) ;
			st.setMaxRows(page * rows) ;
			rs = st.executeQuery(sql.toString()) ;
			rs.absolute((page - 1) * rows) ;//将光标移动到此 ResultSet 对象的给定行编号
			list = DAOUtil.getList(rs, T312POJO.class) ;
		
			
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		
		return list ;
	}
	
	
	public boolean update(T312_Bean docAndGraStaBean){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.update(docAndGraStaBean, tableName, key, field, conn) ;
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
		}finally{
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		
		if(flag == 0){
			return false ;
		}else{
			return true ;
		}
	}
	
	
	
	/**用于数据导出*/
	public List<T312_Bean> totalList(String year,int checkState){

		StringBuffer sql=new StringBuffer();
		sql.append("select SeqNumber,StaName,StaID,UnitName,UnitID, StaType," +
		"Time,Note,CheckState");
        sql.append(" from "+tableName);
        sql.append(" where Time like '"+year+"%' and CheckState="+checkState);

		
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T312_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, T312_Bean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		
		return list ;
	}
	
	
	/**用于教育部数据导出*/
	public List<T312_Bean> totalList(String year){

		StringBuffer sql=new StringBuffer();
		sql.append("select SeqNumber,StaName,StaID,UnitName,UnitID, StaType," +
		"Time,Note,CheckState");
        sql.append(" from "+tableName);
        sql.append(" where Time like '"+year+"%'" );
        sql.append(" and CheckState ="+Constants.PASS_CHECK);

		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T312_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, T312_Bean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		
		return list ;
	}
	
	
	public int getStaNameNum(String year,String staType){
		Connection conn1 = DBConnection.instance.getConnection() ;
		Statement st1 = null ;
		ResultSet rs1 = null ;
		StringBuffer sql1 = new StringBuffer();
		sql1.append("select * from "+tableName+" where Time like '"+year+"%'");
		try{
			st1 = conn1.createStatement();
			rs1 = st1.executeQuery(sql1.toString());
			while(!rs1.next()) return -1;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}finally{
			DBConnection.close(rs1);
			DBConnection.close(st1);	
			DBConnection.close(conn1);
		}
		
		
		int count = 0;
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT COUNT(DISTINCT StaID)");
        sql.append(" from "+tableName+" where Time like '"+year+"%'");
        sql.append(" and StaType="+"'" + staType +"'");
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
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		
		return count ;
		
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
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
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
			DBConnection.close(st);	
			DBConnection.close(conn);
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
			DBConnection.close(st);	
			DBConnection.close(conn);
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
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		
		if (flag == 0) {
			return false;
		} else {
			return true;
		}
	}



	
	public static void main(String args[]){
		
		T312_DAO dao=new T312_DAO();
		boolean flag = dao.updatCheck();
				System.out.println(flag);
		
	}



}
