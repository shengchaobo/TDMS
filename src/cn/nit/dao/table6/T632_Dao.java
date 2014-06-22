package cn.nit.dao.table6;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import cn.nit.bean.table6.T632_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T632_Dao {

	/** 数据库表名 */
	private String tableName = "T632_GraStuEmployInfo_Admission$";

	/** 数据自增长字段的主键，必须为自增长字段 */
	private String key = "SeqNumber";

	/** 数据库表中除了自增长字段的所有字段 */

	
	private String field = "TeaUnit,UnitId,MajorName,MajorId,SumEmployNum,govermentNum,PubInstiNum,EnterpriseNum,ForceNum,FlexibleEmploy,GoOnHighStudy,NationItemEmploy,OtherEmploy," +
			"SumGoOnHighStudyNum,RecommendGraNum,ExamGraApplyNum,ExamGraEnrollNum,ExamGraInSch,ExamGraOutSch,AbroadNum,Time,Note";

	
	private String fieldShow = "SeqNumber,TeaUnit,UnitId,MajorName,MajorId,SumEmployNum,govermentNum,PubInstiNum,EnterpriseNum,ForceNum,FlexibleEmploy,GoOnHighStudy,NationItemEmploy,OtherEmploy," +
			"SumGoOnHighStudyNum,RecommendGraNum,ExamGraApplyNum,ExamGraEnrollNum,ExamGraInSch,ExamGraOutSch,AbroadNum,Time,Note";

	/* ,FillTeaID,FillUnitID,audit */

	/**
	 * 将数据表624的实体类插入数据库
	 * 
	 * @param UndergraAdmiInfo
	 * @return
	 * 
	 * @time: 2014-6-12
	 */
	public boolean insert(T632_Bean GraStuEmployInfo) {

		// flag判断数据是否插入成功
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection();
		try {
			flag = DAOUtil.insert(GraStuEmployInfo, tableName, field, conn);
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
	public boolean batchInsert(List<T632_Bean> list) {

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
	public boolean update(T632_Bean GraStuEmployInfo) {

		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection();
		try {
			flag = DAOUtil
					.update(GraStuEmployInfo, tableName, key, field, conn);
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
	
	public List<T632_Bean> queryPageList(int pagesize, int currentpage) {
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
		List<T632_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T632_Bean.class) ;
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
	
	public List<T632_Bean> getAllList() {
		// TODO Auto-generated method stub
		String sql = "select " + fieldShow + " from " + tableName ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T632_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T632_Bean.class) ;
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
	
	public List<T632_Bean> queryPageList(String cond, Object object,
			int pagesize, int currentpage) {
		// TODO Auto-generated method stub
		String queryPageSql;
		
			queryPageSql = "select top " + pagesize + 
			fieldShow
			+ " from " + tableName + 
			" where " + cond + " and (SeqNumber not in (select top " + pagesize * (currentpage-1) + " SeqNumber from "+
			tableName + " where " + cond + " order by SeqNumber)) order by SeqNumber" ;
	

		System.out.println(queryPageSql);
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T632_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T632_Bean.class) ;
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
	
	public List<T632_Bean> getAllList(String cond, Object object) {
		// TODO Auto-generated method stub
		String sql;
		
		sql = "select " + fieldShow + " from " + tableName +" where " + cond;
	    System.out.println(sql);
	
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T632_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T632_Bean.class) ;
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

		T632_Dao GraStuEmployInfoDao = new T632_Dao();
		T632_Bean GraStuEmployInfo = new T632_Bean();
//		 UndergraAdmiInfo.setSeqNumber(1);

	
		GraStuEmployInfo.setTeaUnit("土木与建筑工程学院");
		GraStuEmployInfo.setUnitId("3002");
		GraStuEmployInfo.setMajorName("水利水电工程");
		GraStuEmployInfo.setMajorId("081101");

		GraStuEmployInfo.setGovermentNum(10);
		GraStuEmployInfo.setPubInstiNum(10);
		GraStuEmployInfo.setEnterpriseNum(10);
		GraStuEmployInfo.setForceNum(10);
		GraStuEmployInfo.setFlexibleEmploy(10);
		
		GraStuEmployInfo.setNationItemEmploy(10);
		GraStuEmployInfo.setOtherEmploy(10);
		GraStuEmployInfo.setRecommendGraNum(2);
		GraStuEmployInfo.setExamGraApplyNum(10);
		GraStuEmployInfo.setExamGraInSch(10);
		GraStuEmployInfo.setExamGraOutSch(10);
		GraStuEmployInfo.setAbroadNum(1);
		GraStuEmployInfo.setTime(new Date());
		GraStuEmployInfo.setNote("test");
//		GraStuEmployInfo.setSumGoOnHighStudyNum(GraStuEmployInfo.getRecommendGraNum()+GraStuEmployInfo.getExamGraInSch()+GraStuEmployInfo.getExamGraOutSch()+GraStuEmployInfo.getAbroadNum());//统计生成-应届升学总人数
//		GraStuEmployInfo.setExamGraEnrollNum(GraStuEmployInfo.getExamGraInSch()+GraStuEmployInfo.getExamGraOutSch());//统计生成-考研录取总人数
//		GraStuEmployInfo.setGoOnHighStudy(GraStuEmployInfo.getSumGoOnHighStudyNum());//引用生成--升学
//		
//		GraStuEmployInfo.setSumEmployNum(GraStuEmployInfo.getGovermentNum()+GraStuEmployInfo.getPubInstiNum()+GraStuEmployInfo.getEnterpriseNum()+GraStuEmployInfo.getForceNum()
//				+GraStuEmployInfo.getFlexibleEmploy()+GraStuEmployInfo.getNationItemEmploy()+GraStuEmployInfo.getOtherEmploy()+GraStuEmployInfo.getGoOnHighStudy());//统计生成-应届就业总人数
		
		
		GraStuEmployInfo.setSumGoOnHighStudyNum(0);//统计生成-应届升学总人数
		GraStuEmployInfo.setExamGraEnrollNum(0);//统计生成-考研录取总人数
		GraStuEmployInfo.setGoOnHighStudy(0);//引用生成--升学
		
		GraStuEmployInfo.setSumEmployNum(0);//统计生成-应届就业总人数
		
		//		
		GraStuEmployInfoDao.insert(GraStuEmployInfo);
		//		
		//	
		//		
		// //
		// System.out.println(underCSBaseTeaDao.auditingData("audit='1'",null,2,10).size())
		// ;
		// // System.out.println(UndergraAdmiInfoDao.update(UndergraAdmiInfo)) ;
//		 System.out.println(JuniorAdmisInfoDao.deleteItemsByIds("(8)")) ;

		System.out.println("success!!");
	}





}
