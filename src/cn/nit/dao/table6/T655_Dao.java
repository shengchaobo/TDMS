package cn.nit.dao.table6;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import net.sf.json.JSON;



import cn.nit.bean.table6.T621_Bean;
import cn.nit.bean.table6.T622_Bean;
import cn.nit.bean.table6.T641_Bean;
import cn.nit.bean.table6.T651_Bean;
import cn.nit.bean.table6.T655_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class T655_Dao {

	/** 数据库表名 */
	private String tableName = "T655_CET46NCRE_Tea$";

	/** 数据自增长字段的主键，必须为自增长字段 */
	private String key = "SeqNumber";

	/** 数据库表中除了自增长字段的所有字段 */
	private String field = "TeaUnit,UnitId,CET4PassRate,CET6PassRate,JiangxiNCREPassRate,Time,Note,CheckState";

	
	private String fieldShow = "SeqNumber,TeaUnit,UnitId,CET4PassRate,CET6PassRate,JiangxiNCREPassRate,Time,Note,CheckState";
		
	/* ,FillTeaID,FillUnitID,audit */

	
	 /**
	 * 获得当年数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T655_Bean> getYearInfo(String year){
		
		String sql = "select " + key + "," + field + " from " + tableName 
				+ " where convert(varchar(4),Time,120)=" + year;		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T655_Bean> list = null ;
		//System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T655_Bean.class) ;
			//System.out.println("没有进if，list长度："+list.size());
			
			//如果当前年表中没有单位列数据，先将单位列数据插入到表中
			if(list.size() == 0){
				String sql1 = "select " + key + "," + "UnitName AS TeaUnit,DiDepartment.UnitID,CET4PassRate,CET6PassRate," +
						"JiangxiNCREPassRate,Time,CheckState," + tableName + ".Note" +
						" from DiDepartment" +
						" left join " + tableName + " on DiDepartment.UnitID = " + tableName + ".UnitId " +
						" and convert(varchar(4),Time,120)=" + "'" + year + "'" +
						" where DiDepartment.UnitID like '30%' ";
				//System.out.println(sql1);
				rs = st.executeQuery(sql1) ;
				list = DAOUtil.getList(rs, T655_Bean.class) ;
				
				T655_Bean totalBean = new T655_Bean();
				totalBean.setUnitId("0000");
				totalBean.setTeaUnit("全校合计");
				list.add(0,totalBean);
				
				for(T655_Bean bean:list){
					bean.setTime(TimeUtil.changeDateY(year));
				}
				
				//插入单位列
				DAOUtil.batchInsert(list, tableName, field, conn) ;	
				
				//再取出来
				Connection conn1 = DBConnection.instance.getConnection() ;
				st = conn1.createStatement() ;
				rs = st.executeQuery(sql) ;
				list = DAOUtil.getList(rs, T655_Bean.class) ;
			}
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
	
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T655_Bean> totalList(String year){
		
		String sql = "select " + key+ "," +field + " from " + tableName 
				+ " where convert(varchar(4),Time,120)=" + year;		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T655_Bean> list = null ;
		System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T655_Bean.class) ;
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
	
	
	/**
	 * 更新数据
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */	
	public boolean update(T655_Bean bean){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{			
			flag = DAOUtil.update(bean, tableName, key, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
		}
		
		return flag ;
	}	
	
	/**
	 * 根据seqNumber找相应bean
	 * @param 
	 * @return
	 */
	public T655_Bean findBySeqNum (int seqNum){
		String sql = "select " + key+ "," +field + " from " + tableName 
		+ " where SeqNumber=" + seqNum;		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T655_Bean> list = null ;
		T655_Bean bean = null;
		System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T655_Bean.class) ;
			bean = list.get(0);		
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
		
		return bean ;
	}
	
	/**
	 * 找当年总计bean
	 * @param 
	 * @return
	 */
	public T655_Bean findSumBean(String name, String year){
		String sql = "select " + key+ "," +field + " from " + tableName 
		+ " where convert(varchar(4),Time,120)=" + year + " and TeaUnit="+ "'" + name + "'";		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T655_Bean> list = null ;
		T655_Bean bean = null;
		System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T655_Bean.class) ;
			bean = list.get(0);

		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
		
		return bean ;
	}
	
	
	
	/**
	 * 更新某条数据的审核状态
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */	
	public boolean updateCheck(String year, String unitName, int checkState){
		
		int flag ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		String sql = "update " + tableName + " set CheckState=" + checkState +
		" where TeaUnit='" + unitName + "' and convert(varchar(4),Time,120)=" + year;			
		System.out.println(sql);
		try{			
			st = conn.createStatement();
			flag = st.executeUpdate(sql);					
		}catch(Exception e){
			e.printStackTrace() ;
			return false;
		}finally{
			DBConnection.close(conn) ;
		}
		
		if (flag == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	
	
	
//	/**
//	 * 将数据表622的实体类插入数据库
//	 * 
//	 * @param UndergraAdmiInfo
//	 * @return
//	 * 
//	 * @time: 2014-6-12
//	 */
//	public boolean insert(T655_Bean CET46AndJiangxiNCRE) {
//
//		// flag判断数据是否插入成功
//		boolean flag = false;
//		Connection conn = DBConnection.instance.getConnection();
//		try {
//			flag = DAOUtil.insert(CET46AndJiangxiNCRE, tableName, field, conn);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return flag;
//		} finally {
//			DBConnection.close(conn);
//		}
//		return flag;
//	}
//
	/**
	 * 讲数据批量插入T511表中
	 * 
	 * @param list
	 *            {@linkplain java.util.List<
	 *            {@link cn.nit.bean.table5.UndergraCSBaseTeaBean}>}
	 * @return true表示插入成功，false表示插入失败
	 */
	public boolean batchInsert(List<T655_Bean> list) {

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
//
//
//	// 删除 ids应书写为"(1,2,3)"
//	public boolean deleteItemsByIds(String ids) {
//
//		int flag = 0;
//		StringBuffer sql = new StringBuffer();
//		sql.append("delete from " + tableName);
//		sql.append(" where " + key + " in " + ids);
//		Connection conn = DBConnection.instance.getConnection();
//		Statement st = null;
//
//		try {
//			st = conn.createStatement();
//			flag = st.executeUpdate(sql.toString());
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//
//		if (flag == 0) {
//			return false;
//		} else {
//			return true;
//		}
//	}
//
//	public String getTableName() {
//		return this.tableName;
//	}
//	
//	public List<T655_Bean> queryPageList(int pagesize, int currentpage) {
//		// TODO Auto-generated method stub
//		
//		
//		String queryPageSql = "select top " + pagesize + 
//		fieldShow
//		+ " from " + tableName + 
//		" where (SeqNumber not in (select top " + pagesize * (currentpage-1) + " SeqNumber from "+
//		tableName + " order by SeqNumber)) order by SeqNumber" ;
//		System.out.println(queryPageSql);
//		Connection conn = DBConnection.instance.getConnection() ;
//		Statement st = null ;
//		ResultSet rs = null ;
//		List<T655_Bean> list = null ;
//		
//		try{
//			st = conn.createStatement() ;
//			rs = st.executeQuery(queryPageSql) ;
//			list = DAOUtil.getList(rs, T655_Bean.class) ;
//		}catch(Exception e){
//			e.printStackTrace() ;
//			return null ;
//		}finally{
//			DBConnection.close(conn);
//			DBConnection.close(rs);
//			DBConnection.close(st);			
//		}
//		
//		return list ;
//	}
//	
//	public List<T655_Bean> queryPageList(String cond, Object object,
//			int pagesize, int currentpage) {
//		// TODO Auto-generated method stub
//		String Cond = "1=1";
//		
//		if(cond != null && !cond.equals("")){
//			Cond = Cond + cond;
//		}
//		String queryPageSql;
//		
//			queryPageSql = "select top " + pagesize + 
//			fieldShow
//			+ " from " + tableName + 
//			" where " + Cond + " and (SeqNumber not in (select top " + pagesize * (currentpage-1) + " SeqNumber from "+
//			tableName + " where " + Cond + " order by SeqNumber)) order by SeqNumber" ;
//	
//
////		System.out.println(queryPageSql);
//		Connection conn = DBConnection.instance.getConnection() ;
//		Statement st = null ;
//		ResultSet rs = null ;
//		List<T655_Bean> list = null ;
//		
//		try{
//			st = conn.createStatement() ;
//			rs = st.executeQuery(queryPageSql) ;
//			list = DAOUtil.getList(rs, T655_Bean.class) ;
//		}catch(Exception e){
//			e.printStackTrace() ;
//			return null ;
//		}finally{
//			DBConnection.close(conn);
//			DBConnection.close(rs);
//			DBConnection.close(st);			
//		}
//		
//		return list ;
//	}
//	
//	public List<T655_Bean> getAllList() {
//		// TODO Auto-generated method stub
//		String sql = "select " + fieldShow + " from " + tableName ;
//		Connection conn = DBConnection.instance.getConnection() ;
//		Statement st = null ;
//		ResultSet rs = null ;
//		List<T655_Bean> list = null ;
//		
//		try{
//			st = conn.createStatement() ;
//			rs = st.executeQuery(sql) ;
//			list = DAOUtil.getList(rs, T655_Bean.class) ;
//		}catch(Exception e){
//			e.printStackTrace() ;
//			return null ;
//		}finally{
//			DBConnection.close(conn);
//			DBConnection.close(rs);
//			DBConnection.close(st);			
//		}
//		
//		return list ;
//	}
//	
//	public List<T655_Bean> getAllList(String cond, Object object) {
//		// TODO Auto-generated method stub
//		String Cond = "1=1";
//		
//		if(cond != null && !cond.equals("")){
//			Cond = Cond + cond;
//		}
//		String sql;
//		
//		sql = "select " + fieldShow + " from " + tableName +" where " + Cond;
////	    System.out.println(sql);
//	
//		Connection conn = DBConnection.instance.getConnection() ;
//		Statement st = null ;
//		ResultSet rs = null ;
//		List<T655_Bean> list = null ;
//		
//		try{
//			st = conn.createStatement() ;
//			rs = st.executeQuery(sql) ;
//			list = DAOUtil.getList(rs, T655_Bean.class) ;
//		}catch(Exception e){
//			e.printStackTrace() ;
//			return null ;
//		}finally{
//			DBConnection.close(conn);
//			DBConnection.close(rs);
//			DBConnection.close(st);			
//		}
//		
//		return list ;
//	}
//	
	public double getCET4PassRate(String year){
		double CET4PassRate = 0;
		StringBuffer sql = new StringBuffer();
		sql.append(" select avg(CET4PassRate) AS CET4PassRate from T655_CET46NCRE_Tea$");
		sql.append(" where convert(varchar(4),T655_CET46NCRE_Tea$.Time,120) =" + year);
		Connection conn=DBConnection.instance.getConnection();
		Statement st=null;
		ResultSet rs=null;
		
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			while(rs.next()){
				CET4PassRate = rs.getDouble("CET4PassRate");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConnection.close(conn);
		}
		
		return CET4PassRate;
	}
	
	public double getCET6PassRate(String year){
		double CET6PassRate = 0;
		StringBuffer sql = new StringBuffer();
		sql.append(" select avg(CET6PassRate) AS CET6PassRate from T655_CET46NCRE_Tea$");
		sql.append(" where convert(varchar(4),T655_CET46NCRE_Tea$.Time,120) =" + year);
		Connection conn=DBConnection.instance.getConnection();
		Statement st=null;
		ResultSet rs=null;
		
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			while(rs.next()){
				CET6PassRate = rs.getDouble("CET6PassRate");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConnection.close(conn);
		}
		
		return CET6PassRate;
	}
	
	public double getJPassRate(String year){
		double JPassRate = 0;
		StringBuffer sql = new StringBuffer();
		sql.append(" select avg(JiangxiNCREPassRate) AS JPassRate from T655_CET46NCRE_Tea$");
		sql.append(" where convert(varchar(4),T655_CET46NCRE_Tea$.Time,120) =" + year);
		Connection conn=DBConnection.instance.getConnection();
		Statement st=null;
		ResultSet rs=null;
		
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			while(rs.next()){
				JPassRate = rs.getDouble("JPassRate");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConnection.close(conn);
		}
		
		return JPassRate;
	}
//	
//	public List<T655_Bean> getYearInfo(String year){
//		
//		String sql = "select " + " " + key + "," +
//		field + " from " + tableName + " where convert(varchar(4),Time,120)=" + year;
//		Connection conn = DBConnection.instance.getConnection() ;
//		Statement st = null ;
//		ResultSet rs = null ;
//		List<T655_Bean> list = null ;
//		//T651_Bean bean = null;
//		try{
//			st = conn.createStatement() ;
//			rs = st.executeQuery(sql) ;
//			list = DAOUtil.getList(rs, T655_Bean.class) ;
//			
//		}catch(Exception e){
//			e.printStackTrace() ;
//			return null ;
//		}finally{
//			DBConnection.close(conn);
//			DBConnection.close(rs);
//			DBConnection.close(st);			
//		}
//		
//		return list ;
//	}
	
	public boolean deleteAll(){
		int flag = 0;
		String sql ="delete from "+tableName;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		try{
			st = conn.createStatement();
			flag = st.executeUpdate(sql);
			
		}catch(SQLException e){
			e.printStackTrace() ;
			return false ;
		}	
		if(flag == 0){
			return false ;
		}else{
			return true ;
		}	
	}

	public static void main(String args[]) {

		T655_Dao CET46AndJiangxiNCREDao = new T655_Dao();
		
//	boolean flag = CET46AndJiangxiNCREDao.deleteAll();
//	System.out.println(flag);
		List<T655_Bean> list = CET46AndJiangxiNCREDao.getYearInfo("2014");
		System.out.println(list.size());
	}







}
