package cn.nit.dao.table1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import cn.nit.bean.table1.T172Bean;
import cn.nit.dbconnection.DBConnection;

import cn.nit.util.DAOUtil;


/**表1-7数据库操作类*/
public class T172DAO {
	
	/**  数据库表名  */
	private String tableName = "T172_SchFriCom_PartyOffice" ;
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "FriName,ActName,ActType,ActTime,ActPlace,UnitName,UnitID,Note" ;
	
	/**
	 * 将数据表1-7的实体类插入数据库
	 * @param schResIns
	 * @return
	 *
	 * @time: 2014-5-14/上午10:53:10
	 */
	public boolean insert(T172Bean t172Bean){
		
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
	 * @param list {@linkplain java.util.List<{@link cn.nit.bean.table1.T151Bean}>}
	 * @return true表示插入成功，false表示插入失败
	 */
	public boolean batchInsert(List<T172Bean> list){
		
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
		sql.append(" from " + tableName) ;
//		sql.append(" from " + tableName + " as t,DiCourseChar csn,DiCourseCategories cst") ;
//		sql.append(" where audit!='0' and csn.IndexID=t.CSNature and cst.IndexID=t.CSType") ;
		int total = 0 ;
		
//		if(fillUnitId != null && !fillUnitId.equals("")){
//			sql.append(" and FillUnitID=" + fillUnitId) ;
//		}
//		
		if(conditions != null && !conditions.equals("")){
			sql.append(" where "+conditions) ;
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
		}
		return total ;
	}
	
	/**
	 * @param conditions 查询条件
	 * @param fillUnitId 填报人单位号，如果为空，则查询所有未审核的数据，<br>如果不为空，则查询填报人自己单位的所有的数据
	 * @return
	 */
	public List<T172Bean> auditingData(String conditions, String fillUnitId, int page, int rows){
		
		StringBuffer sql = new StringBuffer() ;
		List<T172Bean> list = null ;
		sql.append("select * from "+tableName);
//		sql.append("select t.SeqNumber,t.CSName,t.CSID,t.CSUnit,t.UnitID,t.FromTeaResOffice,t.TeaResOfficeID,cst.CourseCategories as CSType,t.CSType as CSTypeID,csn.CourseChar as CSNature,t.CSNature as CSNatureID,t.State,t.PubCSType,t.Time,t.Note") ;
//		sql.append(" from " + tableName + " as t,DiCourseChar csn,DiCourseCategories cst") ;
//		sql.append(" where audit!='0' and csn.IndexID=t.CSNature and cst.IndexID=t.CSType") ;
		//
//		if(fillUnitId != null && !fillUnitId.equals("")){
//			sql.append(" and FillUnitID=" + fillUnitId) ;
//		}
		
		if(conditions != null){
			sql.append(" where "+conditions) ;
		}
		
		sql.append(" order by SeqNumber desc") ;
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
//		System.out.println(sql.toString());
		
		try{
			st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY) ;
			st.setMaxRows(page * rows) ;
			rs = st.executeQuery(sql.toString()) ;
			rs.absolute((page - 1) * rows) ;
			list = DAOUtil.getList(rs, T172Bean.class) ;
			
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}
		
		return list ;
	}
	
	/**
	 * 获得的总数（用于导出）
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T172Bean> totalList(){
		
		StringBuffer sql=new StringBuffer();
		sql.append("select SeqNumber,FriName,ActName,ActType,ActTime,ActPlace,UnitName,UnitID,Note from "+ tableName);

		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T172Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, T172Bean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null;
		}
		
		return list ;
	}
	
	
	public boolean update(T172Bean t172Bean){
		
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
	
	public static void  main(String arg[])
	{
		T172DAO dao=new T172DAO();
//		int n=dao.totalAuditingData(null, null);
//		System.out.println(n);
//		List<T172Bean> list=dao.totalList();
//		System.out.println(list.size());
		T172Bean bean = new T172Bean();
		bean.setSeqNumber(1);
		bean.setActName("test");
		bean.setActPlace("test");
		bean.setActTime(new Date());
		bean.setActType("hotpot");
		bean.setFriName("nikchun");
		bean.setUnitID("12222");
		bean.setUnitName("土建");
		boolean flag = dao.update(bean);
		if(flag){
			System.out.println("更新完毕");
		}else{
			System.out.println("更新出错");
		}
		
	}

}