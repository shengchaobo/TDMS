package cn.nit.dao.table1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.nit.bean.table1.S18Bean;
import cn.nit.bean.table1.T181Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.pojo.table1.S18POJO;
import cn.nit.pojo.table1.T11POJO;
import cn.nit.util.DAOUtil;

public class S18DAO {
	
	/**  数据库表名  */
	private String tableName = "S18_SigedCooperInsNum$" ;
	
	/**待统计数据库表名*/
	private String tableName1="T181_SignedCooperIns_Res$";
	/**需要的字段*/
	private String field2="SeqNumber,CooperInsType";
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "SumAgreeNum,AcademicNum,IndustryNum,LocalGoverNum,Time,Note" ;
	
	
	/**
	 * @param conditions 查询条件
	 * @param fillUnitId 填报人单位号，如果为空，则查询所有未审核的数据，<br>如果不为空，则查询填报人自己单位的所有的数据
	 * @return
	 */
	public List<S18POJO> auditingData(String year){
		
		StringBuffer sql = new StringBuffer() ;
		List<S18POJO> list=null;
        
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
			list = DAOUtil.getList(rs, S18POJO.class) ;
			
			
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}
		return list ;
	}
	
	/**插入前先删除原始数据*/
	public boolean delete(String Year){
		Boolean flag=false;
		StringBuffer sql=new StringBuffer();
		sql.append("delete from "+tableName );
		sql.append(" where Time like '"+Year+"%'");
		
		Connection conn=DBConnection.instance.getConnection();
		Statement st=null;
		
		try
		{
			st=conn.createStatement();
			st.executeUpdate(sql.toString());
			flag=true;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 将统计表S18的实体类插入数据库
	 * @param schbasInfo
	 * @return
	 *
	 * @time: 2014-5-14/上午10:53:10
	 */
	public boolean insert(S18Bean s18Bean){
		
		//flag判断数据是否插入成功
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.insert(s18Bean, tableName, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
		}
		return flag ;
	}
	
	/**得到统计信息*/
	public List<T181Bean> getOriData()
	{
		List<T181Bean> list=new ArrayList<T181Bean>();
		
		StringBuffer sql=new StringBuffer();
		sql.append("select * from "+tableName1);
		
		Connection conn=DBConnection.instance.getConnection();
		Statement st=null;
		ResultSet rs=null;
		
		try
		{
			st=conn.createStatement();
			rs=st.executeQuery(sql.toString());
			list = DAOUtil.getList(rs, T181Bean.class) ;
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
//	/**
//	 * 讲数据批量插入11表中
//	 * @param list {@linkplain java.util.List<{@link cn.nit.bean.table1.T151Bean}>}
//	 * @return true表示插入成功，false表示插入失败
//	 */
//	public boolean batchInsert(List<T11Bean> list){
//		
//		boolean flag = false ;
//		Connection conn = DBConnection.instance.getConnection() ;
//		
//		try{
//			flag = DAOUtil.batchInsert(list, tableName, field, conn) ;
//		}catch(Exception e){
//			e.printStackTrace() ;
//			return flag ;
//		}
//		
//		return flag ;
//	}
	
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

//	
//	public boolean update(T11Bean t11Bean){
//		
//		boolean flag = false ;
//		Connection conn = DBConnection.instance.getConnection() ;
//		try{
//			flag = DAOUtil.update(t11Bean, tableName, key, field, conn) ;
//		}catch(Exception e){
//			e.printStackTrace() ;
//			return flag ;
//		}finally{
//			DBConnection.close(conn) ;
//		}
//		return flag ;
//	}
	
//	public boolean delete()
//	{
//		int  flag=0;
//		StringBuffer sql=new StringBuffer();
//		sql.append("delete from "+ tableName);
//		
//		Connection conn = DBConnection.instance.getConnection() ;
//		Statement st = null ;
//		
//		try{
//			st = conn.createStatement() ;
//			flag = st.executeUpdate(sql.toString()) ;
//		}catch(Exception e){
//			e.printStackTrace() ;
//			return false ;
//		}
//		
//		if(flag == 0){
//			return false ;
//		}else{
//			return true ;
//		}
//		
//	}
	
//	

	public String getTableName(){
		return this.tableName ;
	}
	

}
