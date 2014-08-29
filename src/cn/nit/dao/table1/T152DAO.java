package cn.nit.dao.table1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.table1.T151Bean;
import cn.nit.bean.table1.T152Bean;
import cn.nit.dbconnection.DBConnection;

import cn.nit.pojo.table1.T152POJO;
import cn.nit.util.DAOUtil;

/**
 * 表152的数据库操作类
 * @author lenovo
 */
public class T152DAO {
	
	/**  数据库表名  */
	private String tableName = "T152_TeaResIns_TeaRes$" ;
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "ResInsName,ResInsID,FillUnitID,Type,BuildCondition,BiOpen,OpenCondition,TeaUnit,UnitID,BeginYear,HouseArea,Time,Note" ;
	
	/**
	 * 将数据表151的实体类插入数据库
	 * @param schResIns
	 * @return
	 *
	 * @time: 2014-5-14/上午10:53:10
	 */
	public boolean insert(T152Bean t152Bean){
		
		//flag判断数据是否插入成功
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.insert(t152Bean, tableName, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
		}
		return flag ;
	}
	
	/**
	 * 讲数据批量插入152表中
	 * @param list {@linkplain java.util.List<{@link cn.nit.bean.table1.T151Bean}>}
	 * @return true表示插入成功，false表示插入失败
	 */
	public boolean batchInsert(List<T152Bean> list){
		
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
//		sql.append(" from " + tableName + " as t,DiCourseChar csn,DiCourseCategories cst") ;
//		sql.append(" where audit!='0' and csn.IndexID=t.CSNature and cst.IndexID=t.CSType") ;
		int total = 0 ;
//		System.out.println(sql.toString());
		
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
				total +=1;
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
	public List<T152POJO> auditingData(String conditions, String fillUnitId, int page, int rows){
		
		StringBuffer sql = new StringBuffer() ;
		List<T152POJO> list = null ;
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
//		System.out.println(sql.toString());
		
		try{
			st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY) ;
			st.setMaxRows(page * rows) ;
			rs = st.executeQuery(sql.toString()) ;
			rs.absolute((page - 1) * rows) ;
			list = DAOUtil.getList(rs, T152POJO.class) ;
			
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
	public List<T152Bean> totalList(String filledID){
		
//		String Cond = "(TeaFlag is null or TeaFlag != '外聘')";
//				
//		String queryPageSql = "select TeaId,TeaName,Gender,Birthday,AdmisTime,TeaState," +
//		"BeginWorkTime,IdentiType AS IDCode,FromOffice,OfficeID,FromUnit,unitID," +
//		"FromTeaResOffice,TeaResOfficeID," + tableName4 + ".Education,Degree AS TopDegree,GraSch,Major," +
//		"AdminLevel," + tableName5 + ".Source,TitleLevel AS MajTechTitle,TitleName AS TeaTitle,NotTeaTitle,SubjectClass," +
//		"DoubleTea,Industry,Engineer,TeaBase,TeaFlag,Note"
//		+ " from " + tableName + 
//		" left join " + tableName1+ " on " + "TopDegree=" + tableName1 + ".IndexID " +
//		" left join " + tableName2+ " on " + "MajTechTitle=" + tableName2 + ".IndexID " +
//		" left join " + tableName3+ " on " + "TeaTitle=" + tableName3 + ".IndexID " +
//		" left join " + tableName4+ " on " + tableName + ".Education=" + tableName4 + ".IndexID " +
//		" left join " + tableName5+ " on " + tableName + ".Source=" + tableName5 + ".IndexID " +
//		" left join " + tableName6+ " on " + tableName + ".IDCode=" + tableName6 + ".IndexID " +
//		" where " + Cond ;
		StringBuffer sql=new StringBuffer();
		sql.append("select t.SeqNumber,t.ResInsName,t.ResInsID,t.FillUnitID,drt.ResearchType as Type, t.BuildCondition,t.BiOpen, t.OpenCondition,t.TeaUnit,t.UnitID,t.BeginYear,t.HouseArea,t.Time,t.Note" );
		sql.append(" from "+tableName + " as t,DiDepartment dpt,DiResearchType drt");
		sql.append(" where dpt.UnitID=t.ResInsID and drt.IndexID=t.Type");
		sql.append(" and t.FillUnitID="+filledID);

		
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T152Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, T152Bean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null;
		}
		
		return list ;
	}
	
	public boolean update(T152Bean t152Bean){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.update(t152Bean, tableName, key, field, conn) ;
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
		T152DAO dao=new T152DAO();
//		int n=dao.totalAuditingData(null, null);
//		System.out.println(n);
//		List<T152POJO> list=dao.auditingData(null, null, 1, 1);
//		System.out.println(list.size());
////	
	}
	
	
}
