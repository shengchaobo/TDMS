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
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T641_Dao {

	/** 数据库表名 */
	private String tableName = "T641_ScholarLoanSubsidy_Stu$";

	/** 数据自增长字段的主键，必须为自增长字段 */
	private String key = "SeqNumber";

	/** 数据库表中除了自增长字段的所有字段 */
	private String field = "Item,AidFund,AidStuNum,Time,Note";

	
	private String fieldShow = "SeqNumber,Item,AidFund,AidStuNum,Time,Note";

	
	/* ,FillTeaID,FillUnitID,audit */

	/**
	 * 将数据表622的实体类插入数据库
	 * 
	 * @param UndergraAdmiInfo
	 * @return
	 * 
	 * @time: 2014-6-12
	 */
	public boolean insert(T641_Bean ScholarLoanSubsidy) {

		// flag判断数据是否插入成功
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection();
		try {
			flag = DAOUtil.insert(ScholarLoanSubsidy, tableName, field, conn);
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
	public boolean batchInsert(List<T641_Bean> list) {

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
	public boolean update(T641_Bean ScholarLoanSubsidy) {

		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection();
		try {
			flag = DAOUtil
					.update(ScholarLoanSubsidy, tableName, key, field, conn);
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
	
	public List<T641_Bean> queryPageList(int pagesize, int currentpage) {
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
		List<T641_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T641_Bean.class) ;
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
	
	public List<T641_Bean> queryPageList(String cond, Object object,
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
		List<T641_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T641_Bean.class) ;
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
	
	public List<T641_Bean> getAllList() {
		// TODO Auto-generated method stub
		String sql = "select " + fieldShow + " from " + tableName ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T641_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T641_Bean.class) ;
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
	
	public List<T641_Bean> getAllList(String cond, Object object) {
		// TODO Auto-generated method stub
		String sql;
		
		sql = "select " + fieldShow + " from " + tableName +" where " + cond;
	    System.out.println(sql);
	
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T641_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T641_Bean.class) ;
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

		T641_Dao ScholarLoanSubsidyDao = new T641_Dao();
		T641_Bean ScholarLoanSubsidy = new T641_Bean();
//		 ScholarLoanSubsidy.setSeqNumber(1);
		//	
		/**3.学校奖学金	203.7	5333
		4.国家助学贷款	1465	2459
		5.勤工助学金	81	820
		6.减免学费	4.77	67
		7.临时困难补助	7.9	325*/
	
		ScholarLoanSubsidy.setItem("7.临时困难补助");
		ScholarLoanSubsidy.setAidFund(7.9);
		ScholarLoanSubsidy.setAidStuNum(325);
				
		ScholarLoanSubsidy.setTime(new Date());
		ScholarLoanSubsidy.setNote("无");
//		//		
		ScholarLoanSubsidyDao.insert(ScholarLoanSubsidy);
		//		
		//	
		//		
		// //
		// System.out.println(underCSBaseTeaDao.auditingData("audit='1'",null,2,10).size())
		// ;
		// // System.out.println(ScholarLoanSubsidyDao.update(ScholarLoanSubsidy)) ;
//		 System.out.println(ScholarLoanSubsidyDao.deleteItemsByIds("(8)")) ;

		System.out.println("success!!");
	}







}
