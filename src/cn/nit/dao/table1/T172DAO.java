package cn.nit.dao.table1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import cn.nit.bean.table1.T172_Bean;
import cn.nit.dbconnection.DBConnection;

import cn.nit.util.DAOUtil;


/**表1-7数据库操作类*/
public class T172DAO {
	
	/**  数据库表名  */
	private String tableName = "T172_SchFriCom_PartyOffice" ;
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "FriName,ActName,ActType,ActTime,ActPlace,UnitName,UnitID,Note,Time" ;
	
	/**
	 * 将数据表1-7的实体类插入数据库
	 * @param schResIns
	 * @return
	 *
	 * @time: 2014-5-14/上午10:53:10
	 */
	public boolean insert(T172_Bean t172Bean){
		
		//flag判断数据是否插入成功
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.insert(t172Bean, tableName, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
		}
		return flag ;
	}
	
	/**
	 * 讲数据批量插入172表中
	 * @param list {@linkplain java.util.List<{@link cn.nit.bean.table1.T151_Bean}>}
	 * @return true表示插入成功，false表示插入失败
	 */
	public boolean batchInsert(List<T172_Bean> list){
		
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
		
		
		String Cond = "1=1";
		
		if(conditions != null && !conditions.equals("")){
			Cond = Cond + conditions;
		}
		StringBuffer sql = new StringBuffer() ;
		sql.append("select count(*)") ;
		sql.append(" from " + tableName) ;
//		sql.append(" from " + tableName + " as t,DiCourseChar csn,DiCourseCategories cst") ;
//		sql.append(" where audit!='0' and csn.IndexID=t.CSNature and cst.IndexID=t.CSType") ;
		int total = 0 ;
		
		
//		if(fillUnitId != null && !fillUnitId.equals("")){
//			sql.append(" and FillUnitID=" + fillUnitId) ;
//		}
//		
		if(conditions != null && !conditions.equals("")){
			sql.append(" where  "+Cond) ;
		}
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			
			if(rs == null){
				return total ;
			}
			
			while(rs.next()){
				total = rs.getInt(1) ;
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
	
	/**
	 * @param conditions 查询条件
	 * @param fillUnitId 填报人单位号，如果为空，则查询所有未审核的数据，<br>如果不为空，则查询填报人自己单位的所有的数据
	 * @return
	 */
	public List<T172_Bean> auditingData(String conditions, String fillUnitId, int page, int rows){
		
		
		String Cond = "1=1";
		
		if(conditions != null && !conditions.equals("")){
			Cond = Cond + conditions;
		}
		
		StringBuffer sql = new StringBuffer() ;
		List<T172_Bean> list = null ;
		sql.append("select * from "+tableName);
//		sql.append("select t.SeqNumber,t.CSName,t.CSID,t.CSUnit,t.UnitID,t.FromTeaResOffice,t.TeaResOfficeID,cst.CourseCategories as CSType,t.CSType as CSTypeID,csn.CourseChar as CSNature,t.CSNature as CSNatureID,t.State,t.PubCSType,t.Time,t.Note") ;
//		sql.append(" from " + tableName + " as t,DiCourseChar csn,DiCourseCategories cst") ;
//		sql.append(" where audit!='0' and csn.IndexID=t.CSNature and cst.IndexID=t.CSType") ;
		//
//		if(fillUnitId != null && !fillUnitId.equals("")){
//			sql.append(" and FillUnitID=" + fillUnitId) ;
//		}
		
		if(conditions != null){
			sql.append(" where "+Cond) ;
		}
		
//		sql.append(" order by SeqNumber desc") ;
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
//		System.out.println(sql.toString());
		
		try{
			st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY) ;
			st.setMaxRows(page * rows) ;
			rs = st.executeQuery(sql.toString()) ;
			rs.absolute((page - 1) * rows) ;
			list = DAOUtil.getList(rs, T172_Bean.class) ;
			
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
	
	/**
	 * 获得的总数（用于导出）
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T172_Bean> totalList(String year){
		
		StringBuffer sql=new StringBuffer();
		sql.append("select SeqNumber,FriName,ActName,ActType,ActTime,ActPlace,UnitName,UnitID,Note,Time from "+ tableName);
		sql.append(" where Time like '"+year+"%'");
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T172_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, T172_Bean.class) ;
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
	
	
	public boolean update(T172_Bean t172Bean){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.update(t172Bean, tableName, key, field, conn) ;
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
	
	public String getTableName(){
		return this.tableName ;
	}
	
	/**设置数据库time字段*/
	public boolean updateTime()
	{
		int flag = 0;
		Date year = new Date();
		StringBuffer sql = new StringBuffer() ;
		sql.append("update "+tableName+" set Time='2015-01-01'");
		System.out.println(sql.toString());
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
	
	public static void main(String args[]){
		T172DAO dao = new T172DAO();
		boolean flag = dao.updateTime();
		System.out.println(flag);
				
	}
}
