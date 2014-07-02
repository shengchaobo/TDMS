package cn.nit.dao.table6;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import cn.nit.bean.table6.T614_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T614_Dao {

	/** 数据库表名 */
	private String tableName = "T614_ContinueEduStuNumInfo_TAEF$";

	/** 数据自增长字段的主键，必须为自增长字段 */
	private String key = "SeqNumber";

	/** 数据库表中除了自增长字段的所有字段 */
	private String field = "PreppyLastYearNum,PreppyThisYearNum,AdvStuLastYearNum,AdvStuThisYearNum," +
			"AdultLastYearNum,AdultThisYearNum,NightUniLastYearNum,NightUniThisYearNum,CorrespdCoLastYearNum,CorrespdThisYearNum," +
			"NetStuLastYearNum,NetStuThisYearNum,SelfStudyLastYearNum,SelfStudyThisYearNum,Time,Note";

	
	private String fieldShow = "SeqNumber,PreppyLastYearNum,PreppyThisYearNum,AdvStuLastYearNum,AdvStuThisYearNum," +
			"AdultLastYearNum,AdultThisYearNum,NightUniLastYearNum,NightUniThisYearNum,CorrespdCoLastYearNum,CorrespdThisYearNum," +
			"NetStuLastYearNum,NetStuThisYearNum,SelfStudyLastYearNum,SelfStudyThisYearNum,Time,Note";

	/* ,FillTeaID,FillUnitID,audit */

	/**
	 * 将数据表621的实体类插入数据库
	 * 
	 * @param UndergraAdmiInfo
	 * @return
	 * 
	 * @time: 2014-6-12
	 */
	public boolean insert(T614_Bean ContinueEduStuNumInfo) {

		// flag判断数据是否插入成功
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection();
		try {
			flag = DAOUtil.insert(ContinueEduStuNumInfo, tableName, field, conn);
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
	public boolean batchInsert(List<T614_Bean> list) {

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
	public boolean update(T614_Bean ContinueEduStuNumInfo) {

		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection();
		try {
			flag = DAOUtil
					.update(ContinueEduStuNumInfo, tableName, key, field, conn);
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
	
	public List<T614_Bean> queryPageList(int pagesize, int currentpage) {
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
		List<T614_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T614_Bean.class) ;
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
	
	public List<T614_Bean> queryPageList(String cond, Object object,
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
		List<T614_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T614_Bean.class) ;
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
	
	public List<T614_Bean> getAllList() {
		// TODO Auto-generated method stub
		String sql = "select " + fieldShow + " from " + tableName ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T614_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T614_Bean.class) ;
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
	
	public List<T614_Bean> getAllList(String cond, Object object) {
		// TODO Auto-generated method stub
		String sql;
		
		sql = "select " + fieldShow + " from " + tableName +" where " + cond;
	    System.out.println(sql);
	
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T614_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T614_Bean.class) ;
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

		T614_Dao ContinueEduStuNumInfoDao = new T614_Dao();
		T614_Bean ContinueEduStuNumInfo = new T614_Bean();
//		 ContinueEduStuNumInfo.setSeqNumber(1);
		//		
		ContinueEduStuNumInfo.setPreppyLastYearNum(0);
		ContinueEduStuNumInfo.setPreppyThisYearNum(0);
		ContinueEduStuNumInfo.setAdvStuLastYearNum(0);
		ContinueEduStuNumInfo.setAdvStuThisYearNum(0);
		ContinueEduStuNumInfo.setAdultLastYearNum(0);
		ContinueEduStuNumInfo.setAdultThisYearNum(0);
		ContinueEduStuNumInfo.setNightUniLastYearNum(0);
		ContinueEduStuNumInfo.setNightUniThisYearNum(0);
		ContinueEduStuNumInfo.setCorrespdCoLastYearNum(6262);
		ContinueEduStuNumInfo.setCorrespdThisYearNum(6781);
		ContinueEduStuNumInfo.setNetStuLastYearNum(0);
		ContinueEduStuNumInfo.setNetStuThisYearNum(0);
		ContinueEduStuNumInfo.setSelfStudyLastYearNum(0);
		ContinueEduStuNumInfo.setSelfStudyThisYearNum(0);
		
		
		ContinueEduStuNumInfo.setTime(new Date());
		ContinueEduStuNumInfo.setNote("无");
//		//		
		ContinueEduStuNumInfoDao.insert(ContinueEduStuNumInfo);
		//		
		//	
		//		
		// //
		// System.out.println(underCSBaseTeaDao.auditingData("audit='1'",null,2,10).size())
		// ;
		// // System.out.println(ContinueEduStuNumInfoDao.update(ContinueEduStuNumInfo)) ;
//		 System.out.println(ContinueEduStuNumInfoDao.deleteItemsByIds("(8)")) ;

		System.out.println("success!!");
	}







}
