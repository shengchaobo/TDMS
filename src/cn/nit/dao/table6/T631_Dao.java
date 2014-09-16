package cn.nit.dao.table6;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import cn.nit.bean.table6.T631_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.pojo.table6.T631POJO;
import cn.nit.util.DAOUtil;

public class T631_Dao {

	/** 数据库表名 */
	private String tableName = "T631_UndergarGraduateInfo_Tea$";

	/** 数据自增长字段的主键，必须为自增长字段 */
	private String key = "SeqNumber";

	/** 数据库表中除了自增长字段的所有字段 */
	
	 

	
	private String field = "TeaUnit,UnitId,MajorName,MajorId,ThisYearGraduNum,ThisYearNotGraduNum,AwardDegreeNum,Time,Note";

	
	private String fieldShow = "SeqNumber,TeaUnit,UnitId,MajorName,MajorId,ThisYearGraduNum,ThisYearNotGraduNum,AwardDegreeNum,Time,Note";

	/* ,FillTeaID,FillUnitID,audit */

	/**
	 * 将数据表624的实体类插入数据库
	 * 
	 * @param UndergraAdmiInfo
	 * @return
	 * 
	 * @time: 2014-6-12
	 */
	public boolean insert(T631_Bean UndergarGraduateInfo) {

		// flag判断数据是否插入成功
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection();
		try {
			flag = DAOUtil.insert(UndergarGraduateInfo, tableName, field, conn);
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		} finally {
			DBConnection.close(conn);
		}
		return flag;
	}

	/**
	 * 讲数据批量插入T511表中
	 * 
	 * @param list
	 *            {@linkplain java.util.List<
	 *            {@link cn.nit.bean.table5.UndergraCSBaseTeaBean}>}
	 * @return true表示插入成功，false表示插入失败
	 */
	public boolean batchInsert(List<T631_Bean> list) {

		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection();

		try {
			flag = DAOUtil.batchInsert(list, tableName, field, conn);
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}

		return flag;
	}

	// /**
	// * 查询待审核数据在数据库中共有多少条
	// * @param conditions 查询条件
	// * @param fillUnitId 填报人单位号，如果为空，则查询所有未审核的数据，<br>如果不为空，则查询填报人自己单位的所有的数据
	// * @return
	// */
	// public int totalAuditingData(String conditions, String fillUnitId){
	//		
	// StringBuffer sql = new StringBuffer() ;
	// sql.append("select count(*)") ;
	// sql.append(" from " + tableName +
	// " as t,DiCourseChar csn,DiCourseCategories cst") ;
	// sql.append(" where audit!='0' and csn.IndexID=t.CSNature and cst.IndexID=t.CSType")
	// ;
	// int total = 0 ;
	//		
	// if(fillUnitId != null && !fillUnitId.equals("")){
	// sql.append(" and FillUnitID=" + fillUnitId) ;
	// }
	//		
	// if(conditions != null && !conditions.equals("")){
	// sql.append(conditions) ;
	// }
	//		
	// Connection conn = DBConnection.instance.getConnection() ;
	// Statement st = null ;
	// ResultSet rs = null ;
	//		
	// try{
	// st = conn.createStatement() ;
	// rs = st.executeQuery(sql.toString()) ;
	//			
	// if(rs == null){
	// return total ;
	// }
	//			
	// while(rs.next()){
	// total = rs.getInt(1) ;
	// }
	// }catch(Exception e){
	// e.printStackTrace() ;
	// return 0 ;
	// }
	// return total ;
	// }
	//	
	// /**
	// * @param conditions 查询条件
	// * @param fillUnitId 填报人单位号，如果为空，则查询所有未审核的数据，<br>如果不为空，则查询填报人自己单位的所有的数据
	// * @return
	// */
	// public List<UndergraCSBaseTeaPOJO> auditingData(String conditions, String
	// fillUnitId, int page, int rows){
	//		
	// StringBuffer sql = new StringBuffer() ;
	// List<UndergraCSBaseTeaPOJO> list = null ;
	// sql.append("select t.SeqNumber,t.CSName,t.CSID,t.CSUnit,t.UnitID,t.FromTeaResOffice,t.TeaResOfficeID,cst.CourseCategories as CSType,t.CSType as CSTypeID,csn.CourseChar as CSNature,t.CSNature as CSNatureID,t.State,t.PubCSType,t.Time,t.Note")
	// ;
	// sql.append(" from " + tableName +
	// " as t,DiCourseChar csn,DiCourseCategories cst") ;
	// sql.append(" where audit!='0' and csn.IndexID=t.CSNature and cst.IndexID=t.CSType")
	// ;
	// //
	// if(fillUnitId != null && !fillUnitId.equals("")){
	// sql.append(" and FillUnitID=" + fillUnitId) ;
	// }
	//		
	// if(conditions != null){
	// sql.append(conditions) ;
	// }
	//		
	// sql.append(" order by SeqNumber desc") ;
	//		
	// Connection conn = DBConnection.instance.getConnection() ;
	// Statement st = null ;
	// ResultSet rs = null ;
	//		
	// try{
	// st =
	// conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY)
	// ;
	// st.setMaxRows(page * rows) ;
	// rs = st.executeQuery(sql.toString()) ;
	// rs.absolute((page - 1) * rows) ;
	// list = DAOUtil.getList(rs, UndergraCSBaseTeaPOJO.class) ;
	//			
	// }catch(Exception e){
	// e.printStackTrace() ;
	// return null ;
	// }
	//		
	// return list ;
	// }

	// 更新
	public boolean update(T631_Bean UndergarGraduateInfo) {

		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection();
		try {
			flag = DAOUtil
					.update(UndergarGraduateInfo, tableName, key, field, conn);
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		} finally {
			DBConnection.close(conn);
		}

		return flag;
	}

	// 删除 ids应书写为"(1,2,3)"
	public boolean deleteItemsByIds(String ids) {

		int flag = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("delete from " + tableName);
		sql.append(" where " + key + " in " + ids);
		Connection conn = DBConnection.instance.getConnection();
		Statement st = null;

		try {
			st = conn.createStatement();
			flag = st.executeUpdate(sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (flag == 0) {
			return false;
		} else {
			return true;
		}
	}

	public String getTableName() {
		return this.tableName;
	}
	
	public List<T631_Bean> queryPageList(int pagesize, int currentpage) {
		// TODO Auto-generated method stub
		
		
		String queryPageSql = "select top " + pagesize + 
		fieldShow
		+ " from " + tableName + 
		" where (SeqNumber not in (select top " + pagesize * (currentpage-1) + " SeqNumber from "+
		tableName + " order by SeqNumber)) order by SeqNumber" ;
		System.out.println(queryPageSql);
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T631_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T631_Bean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
		
		return list ;
	}
	
	
	/**教育部导出（需要T631，T632连接）*/
	public List<T631POJO> getAllList(String year) {
		// TODO Auto-generated method stub
		
		StringBuffer sql = new StringBuffer();
		sql.append("select "+tableName+".TeaUnit,"+tableName+".UnitID,"+tableName+".MajorName,"+tableName+".MajorID,"+
				tableName+".ThisYearGraduNum,"+tableName+".ThisYearNotGraduNum,"+tableName+".AwardDegreeNum,T632_GraStuEmployInfo_Admission$.SumEmployNum");
		sql.append(" from "+tableName+" left join T632_GraStuEmployInfo_Admission$ on "+tableName+".MajorID = T632_GraStuEmployInfo_Admission$.MajorID");
		sql.append(" where "+tableName+".MajorID = T632_GraStuEmployInfo_Admission$.MajorID");
		sql.append(" and "+tableName+".Time like '"+year+"%' and T632_GraStuEmployInfo_Admission$.Time like '"+year+"%'");
//		sql.append(" and a.Time like '"+year+"%' and b.Time like '"+year+"%'");
		
		System.out.println(sql.toString());
//		String sql = "select " + fieldShow + " from " + tableName ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T631POJO> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, T631POJO.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
		
		return list ;
	}
	
	public List<T631_Bean> queryPageList(String cond, Object object,
			int pagesize, int currentpage) {
		// TODO Auto-generated method stub
		String Cond = "1=1";
		
		if(cond != null && !cond.equals("")){
			Cond = Cond + cond;
		}
		String queryPageSql;
		
			queryPageSql = "select top " + pagesize + 
			fieldShow
			+ " from " + tableName + 
			" where " + Cond + " and (SeqNumber not in (select top " + pagesize * (currentpage-1) + " SeqNumber from "+
			tableName + " where " + Cond + " order by SeqNumber)) order by SeqNumber" ;
	

		System.out.println(queryPageSql);
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T631_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T631_Bean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
		
		return list ;
	}
	
	public List<T631_Bean> getAllList(String cond, Object object) {
		// TODO Auto-generated method stub
		String Cond = "1=1";
		
		if(cond != null && !cond.equals("")){
			Cond = Cond + cond;
		}
		String sql;
		
		sql = "select " + fieldShow + " from " + tableName +" where " + Cond;
	    System.out.println(sql);
	
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T631_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T631_Bean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
		
		return list ;
	}
	
	public static void main(String args[]) {

		T631_Dao UndergarGraduateInfoDao = new T631_Dao();
		T631_Bean UndergarGraduateInfo = new T631_Bean();
//		 UndergraAdmiInfo.setSeqNumber(1);
		//		
		UndergarGraduateInfo.setTeaUnit("水利与生态工程学院");
		UndergarGraduateInfo.setUnitId("3001");
		UndergarGraduateInfo.setMajorName("水利水电工程");
		UndergarGraduateInfo.setMajorId("081101");
		UndergarGraduateInfo.setThisYearGraduNum(168);
		UndergarGraduateInfo.setThisYearNotGraduNum(4);
		UndergarGraduateInfo.setAwardDegreeNum(167);
		UndergarGraduateInfo.setTime(new Date());
		UndergarGraduateInfo.setNote("test");
		
		//		
		UndergarGraduateInfoDao.insert(UndergarGraduateInfo);
		//		
		//	
		//		
		// //
		// System.out.println(underCSBaseTeaDao.auditingData("audit='1'",null,2,10).size())
		// ;
		// // System.out.println(UndergraAdmiInfoDao.update(UndergraAdmiInfo)) ;
//		 System.out.println(JuniorAdmisInfoDao.deleteItemsByIds("(8)")) ;
			List<T631POJO> list = UndergarGraduateInfoDao.getAllList("2014");
		System.out.println(list.size());
	}


}
