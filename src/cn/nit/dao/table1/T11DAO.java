package cn.nit.dao.table1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.nit.bean.table1.T11Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.pojo.table1.T11POJO;
import cn.nit.pojo.table1.T151POJO;

import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class T11DAO {
	
	/**  数据库表名  */
	private String tableName = "T11_SchBasInfo_PartyOffice$" ;
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "SchAddress,SchTel,SchFax,SchFillerName,SchFillerTel,SchFillerEmail,SchName,SchID,SchEnName,SchType,SchQuality," +
	"SchBuilder,MajDept,SchUrl,AdmissonBatch,Sch_BeginTime,MediaUrl,YaohuSchAdd,PengHuSchAdd,Time,Note" ;
	
	/**
	 * 将数据表11的实体类插入数据库
	 * @param schbasInfo
	 * @return
	 *
	 * @time: 2014-5-14/上午10:53:10
	 */
	public boolean insert(T11Bean t11Bean){
		
		//flag判断数据是否插入成功
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.insert(t11Bean, tableName, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
		}
		return flag ;
	}
	
	/**
	 * 讲数据批量插入11表中
	 * @param list {@linkplain java.util.List<{@link cn.nit.bean.table1.T151Bean}>}
	 * @return true表示插入成功，false表示插入失败
	 */
	public boolean batchInsert(List<T11Bean> list){
		
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
	
//	/**
//	 * 查询待审核数据在数据库中共有多少条
//	 * @param conditions 查询条件
//	 * @param fillUnitId 填报人单位号，如果为空，则查询所有未审核的数据，<br>如果不为空，则查询填报人自己单位的所有的数据
//	 * @return
//	 */
//	public int totalAuditingData(String conditions, String fillUnitId){
//		
//		StringBuffer sql = new StringBuffer() ;
//		sql.append("select count(*)") ;
//		sql.append(" from " + tableName + " as t,DiDepartment dpt,DiResearchType drt") ;
//		sql.append(" where dpt.UnitID=t.ResInsID and drt.IndexID=t.Type");
////		sql.append(" from " + tableName + " as t,DiCourseChar csn,DiCourseCategories cst") ;
////		sql.append(" where audit!='0' and csn.IndexID=t.CSNature and cst.IndexID=t.CSType") ;
//		int total = 0 ;
//		
//		if(fillUnitId != null && !fillUnitId.equals("")){
//			sql.append(" and FillUnitID=" + fillUnitId) ;
//		}
//		
//		if(conditions != null && !conditions.equals("")){
//			sql.append(conditions) ;
//		}
//		
//		Connection conn = DBConnection.instance.getConnection() ;
//		Statement st = null ;
//		ResultSet rs = null ;
//		
//		try{
//			st = conn.createStatement() ;
//			rs = st.executeQuery(sql.toString()) ;
//			
//			if(rs == null){
//				return total ;
//			}
//			
//			while(rs.next()){
//				total = rs.getInt(1) ;
//			}
//		}catch(Exception e){
//			e.printStackTrace() ;
//			return 0 ;
//		}
//		return total ;
//	}
//	
	/**
	 * @param conditions 查询条件
	 * @param fillUnitId 填报人单位号，如果为空，则查询所有未审核的数据，<br>如果不为空，则查询填报人自己单位的所有的数据
	 * @return
	 */
	public List<T11POJO> auditingData(String year){
		
		StringBuffer sql = new StringBuffer() ;
		List<T11POJO> list=null;
        
		sql.append("select * from "+ tableName);
		sql.append(" where Time like '"+year+"%'");
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
//		System.out.println(sql.toString());
		
		try{
			st = conn.createStatement() ;
//			ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY
//			st.setMaxRows(1) ;
			rs = st.executeQuery(sql.toString()) ;
//			rs.absolute((page - 1) * rows) ;
			list = DAOUtil.getList(rs, T11POJO.class) ;
			
			
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}
		return list ;
	}
	
	public boolean update(T11Bean t11Bean){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.update(t11Bean, tableName, key, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
		}
		return flag ;
	}
	
	public boolean delete()
	{
		int  flag=0;
		StringBuffer sql=new StringBuffer();
		sql.append("delete from "+ tableName);
		
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
	
    public static void main(String arg[])
    {
    	T11DAO dao=new T11DAO();
    	List<T11POJO> list=dao.auditingData("2014");
    	System.out.println(list.size());
    }
}
