package cn.nit.dao.table1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.table1.T151Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.pojo.table1.T151POJO;
import cn.nit.util.DAOUtil;

/**
 * 表151的数据库操作类
 * @author lenovo
 */
public class T151DAO {
	
	/**  数据库表名  */
	private String tableName = "T151_SchResIns_Res$" ;
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "ResInsName,ResInsID,Type,BuildCondition,BiOpen,OpenCondition,TeaUnit,UnitID,BeginYear,HouseArea,Time,Note,audit,position" ;
	
	/**
	 * 将数据表151的实体类插入数据库
	 * @param schResIns
	 * @return
	 *
	 * @time: 2014-5-14/上午10:53:10
	 */
	public boolean insert(T151Bean schResIns){
		
		//flag判断数据是否插入成功
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.insert(schResIns, tableName, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
		}
		return flag ;
	}
	
	/**
	 * 讲数据批量插入151表中
	 * @param list {@linkplain java.util.List<{@link cn.nit.bean.table1.T151Bean}>}
	 * @return true表示插入成功，false表示插入失败
	 */
	public boolean batchInsert(List<T151Bean> list){
		
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
		sql.append(" from " + tableName + " as t,DiDepartment dpt,DiResearchType drt") ;
		sql.append(" where dpt.UnitID=t.ResInsID and drt.IndexID=t.Type");
		int total = 0 ;
		
		if(fillUnitId != null && !fillUnitId.equals("")){
			sql.append(" and FillUnitID=" + fillUnitId) ;
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
	public List<T151POJO> auditingData(String conditions, String fillUnitId, int page, int rows){
		
		StringBuffer sql = new StringBuffer() ;
		List<T151POJO> list = null ;
		sql.append("select t.SeqNumber,t.ResInsName,t.ResInsID,drt.ResearchType as Type,t.Type as TypeID , t.BuildCondition,t.BiOpen, t.OpenCondition,t.TeaUnit,t.UnitID,t.BeginYear,t.HouseArea,t.Time,t.Note");
		sql.append(" from "+tableName + " as t,DiDepartment dpt,DiResearchType drt");
		sql.append(" where dpt.UnitID=t.ResInsID and drt.IndexID=t.Type");

		if(fillUnitId != null && !fillUnitId.equals("")){
			sql.append(" and FillUnitID=" + fillUnitId) ;
		}
		
		if(conditions != null){
			sql.append(conditions) ;
		}
		
		sql.append(" order by SeqNumber desc") ;
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try{
			st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY) ;
			st.setMaxRows(page * rows) ;
			rs = st.executeQuery(sql.toString()) ;
			rs.absolute((page - 1) * rows) ;
			list = DAOUtil.getList(rs, T151POJO.class) ;
			
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}
		
		return list ;
	}
	
	public boolean update(T151Bean schResIns){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.update(schResIns, tableName, key, field, conn) ;
			

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
	
	public static void main(String args[])
	{
		T151DAO dao=new T151DAO();
//		int n=dao.totalAuditingData(null, null);
//		System.out.println(n);
		List<T151POJO> list=dao.auditingData(null, null, 1, 2);
		System.out.println(list.size());
	
//	
	}
	
	
}
