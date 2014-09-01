package cn.nit.dao.table6;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import net.sf.json.JSON;



import cn.nit.bean.table6.T621_Bean;
import cn.nit.bean.table6.T622_Bean;
import cn.nit.bean.table6.T641_Bean;
import cn.nit.bean.table6.T657_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T657_Dao {

	/** 数据库表名 */
	private String tableName = "T657_HabitusQualified_Sport$";

	/** 数据自增长字段的主键，必须为自增长字段 */
	private String key = "SeqNumber";

	/** 数据库表中除了自增长字段的所有字段 */
	private String field = "TeaUnit,UnitId,HabitusQualifiedRate,HabitusTestReachRate,Time,Note";

	
	private String fieldShow = "SeqNumber,TeaUnit,UnitId,HabitusQualifiedRate,HabitusTestReachRate,Time,Note";



	/* ,FillTeaID,FillUnitID,audit */

	/**
	 * 将数据表622的实体类插入数据库
	 * 
	 * @param UndergraAdmiInfo
	 * @return
	 * 
	 * @time: 2014-6-12
	 */
	public boolean insert(T657_Bean HabitusQualified) {

		// flag判断数据是否插入成功
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection();
		try {
			flag = DAOUtil.insert(HabitusQualified, tableName, field, conn);
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
	public boolean batchInsert(List<T657_Bean> list) {

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

	// 更新
	public boolean update(T657_Bean HabitusQualified) {

		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection();
		try {
			flag = DAOUtil
					.update(HabitusQualified, tableName, key, field, conn);
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
	
	public List<T657_Bean> queryPageList(int pagesize, int currentpage) {
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
		List<T657_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T657_Bean.class) ;
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
	
	public List<T657_Bean> queryPageList(String cond, Object object,
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
		List<T657_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T657_Bean.class) ;
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
	
	public List<T657_Bean> getAllList() {
		// TODO Auto-generated method stub
		String sql = "select " + fieldShow + " from " + tableName ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T657_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T657_Bean.class) ;
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
	
	public List<T657_Bean> getAllList(String cond, Object object) {
		// TODO Auto-generated method stub
		String Cond = "1=1";
		
		if(cond != null && !cond.equals("")){
			Cond = Cond + cond;
		}
		String sql;
		
		sql = "select " + fieldShow + " from " + tableName +" where " + Cond;
//	    System.out.println(sql);
	
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T657_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T657_Bean.class) ;
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
	
	public double getQualifiedRate(String year){
		double QualifiedRate = 0;
		StringBuffer sql = new StringBuffer();
		sql.append(" select avg(HabitusQualifiedRate) AS QualifiedRate from T657_HabitusQualified_Sport$");
		sql.append(" where convert(varchar(4),T657_HabitusQualified_Sport$.Time,120) =" + year);
		Connection conn=DBConnection.instance.getConnection();
		Statement st=null;
		ResultSet rs=null;
		
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			while(rs.next()){
				QualifiedRate = rs.getDouble("QualifiedRate");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConnection.close(conn);
		}
		
		return QualifiedRate;
	}
	
	public double getTestReachRate(String year){
		double TestReachRate = 0;
		StringBuffer sql = new StringBuffer();
		sql.append(" select avg(HabitusTestReachRate) AS TestReachRate from T657_HabitusQualified_Sport$");
		sql.append(" where convert(varchar(4),T657_HabitusQualified_Sport$.Time,120) =" + year);
		Connection conn=DBConnection.instance.getConnection();
		Statement st=null;
		ResultSet rs=null;
		
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			while(rs.next()){
				TestReachRate = rs.getDouble("TestReachRate");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConnection.close(conn);
		}
		
		return TestReachRate;
	}

	public static void main(String args[]) {

		T657_Dao HabitusQualifiedDao = new T657_Dao();
		T657_Bean HabitusQualified = new T657_Bean();
//		 HabitusQualified.setSeqNumber(1);
		//
		HabitusQualified.setTeaUnit("水利与生态工程学院");
		HabitusQualified.setUnitId("3001");
		HabitusQualified.setHabitusQualifiedRate(96.00);
		HabitusQualified.setHabitusTestReachRate(96.00);
				
		HabitusQualified.setTime(new Date());
		HabitusQualified.setNote("无");
//		//		
		HabitusQualifiedDao.insert(HabitusQualified);
		//		
		//	
		//		
		// //
		// System.out.println(underCSBaseTeaDao.auditingData("audit='1'",null,2,10).size())
		// ;
		// // System.out.println(HabitusQualifiedDao.update(HabitusQualified)) ;
//		 System.out.println(HabitusQualifiedDao.deleteItemsByIds("(8)")) ;

		System.out.println("success!!");
	}







}
