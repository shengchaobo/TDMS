package cn.nit.dao.table3;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;




import cn.nit.bean.table3.T322_Bean;
import cn.nit.dbconnection.DBConnection;


import cn.nit.pojo.table3.T322POJO;
import cn.nit.util.DAOUtil;

public class T322_DAO {
	/**  数据库表名  */
	private String tableName = "T322_UndergraMajorInfo_Tea$" ;
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "FillUnitID,MajorName,MajorID,MajorVersion,MajorField,MajorFieldID,MajorSetTime,MajorAppvlID,MajorDurition," +
			"MajorDegreeType,MajorAdmisTime,MajorState,StopAdmisTime,IsNewMajor,AppvlYear,BuildAppvlID,MajorLevel,Type," +
			"Field,Leader,TeaID,CheckTime,CheckAppvlID,SchExp,EduMinistryExp,FirstAppvlTime,AppvlTime," +
			"AppvlID,AppvlResult,FromTime,EndTime,AppvlAuth,TotalCSHour,RequireCShour,OptionCSHour,InClassCSHour," +
			"ExpCSHour,PraCSHour,TotalCredit,RequireCredit,OptionCredit,InClassCredit,ExpCredit,PraCredit,OutClassCredit,Time,Note" ;
	
	/**
	 * 将数据表311的实体类插入数据库
	 * @param undergraCSBase
	 * @return
	 *
	 * @time: 2014-5-14/上午10:53:10
	 */
	
	
//	public int getNumofMainTrain(String UnitID){
//		
//		int num=0;
//		Connection conn = DBConnection.instance.getConnection() ;
//		try{
//			DatabaseMetaData metadata=conn.getMetaData();
//			ResultSet rs=metadata.getColumns(null,null,null,null);
//			while(rs.next()){
//				if("(rs.getArray(8))"== UnitID){
//					num++;
//				}
//				
//			}
//		}catch(Exception e){
//			e.printStackTrace() ;
//		}finally{
//			DBConnection.close(conn) ;
//		}
//		return num;
//		
//	}

	public boolean insert(T322_Bean T322_Bean){
		
		//flag判断数据是否插入成功
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.insert(T322_Bean, tableName, field, conn) ;
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
	public boolean batchInsert(List<T322_Bean> list){
		
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
		sql.append(" from " + tableName + " as t,DiDepartment dpt,DiMajorTwo dmt ") ;
		sql.append(" where dpt.UnitID=t.UnitID and dmt.MajorNum=t.MajorID");		
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
	
	
	public List<T322POJO> auditingData(String conditions, String fillDept, int page, int rows){
		
		StringBuffer sql = new StringBuffer() ;
		List<T322POJO> list =null ;
		sql.append("select t.SeqNumber,t.MainClassName,t.MainClassID,t.ByPassTime," +
				"t.MajorNameInSch,dmt.MajorNum as MajorID,t.MajorID as MajorIDID,t.UnitName,t.UnitID,"+
				"t.Note,t.Time");
		sql.append(" from " + tableName + " as t,DiDepartment dpt,DiMajorTwo dmt ");
		sql.append(" where dpt.UnitID=t.UnitID and dmt.MajorNum=t.MajorID" );
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
			list = DAOUtil.getList(rs, T322POJO.class) ;
		
			
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}
		
		return list ;
	}
	
	
	
	public boolean update(T322_Bean t322_Bean){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.update(t322_Bean, tableName, key, field, conn) ;
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

	
	public static void main(String args[]){
		

	}

}
