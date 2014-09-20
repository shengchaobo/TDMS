package cn.nit.dao.table6;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import net.sf.json.JSON;



import cn.nit.bean.table6.T611_Bean;
import cn.nit.bean.table6.T615_Bean;
import cn.nit.bean.table6.T621_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T615_Dao {

	/** 数据库表名 */
	private String tableName = "T615_GenUndergraMajStuNum_Tea$";

	/** 数据自增长字段的主键，必须为自增长字段 */
	private String key = "SeqNumber";

	/** 数据库表中除了自增长字段的所有字段 */
	private String field = "MajorName,MajorId,FromUnitId,UnitId,SchLen,SchStuSumNum,FreshmanNum,SophomoreNum,JuniorNum,SeniorNum,OtherGradeNum,MinorNum,DualDegreeNum,ChangeInNum,ChangeOutNum,Time,Note";

	
	private String fieldShow = "SeqNumber,MajorName,MajorId,FromUnitId,UnitId,SchLen,SchStuSumNum,FreshmanNum,SophomoreNum,JuniorNum,SeniorNum,OtherGradeNum,MinorNum,DualDegreeNum,ChangeInNum,ChangeOutNum,Time,Note";


	/* ,FillTeaID,FillUnitID,audit */

	/**
	 * 将数据表621的实体类插入数据库
	 * 
	 * @param UndergraAdmiInfo
	 * @return
	 * 
	 * @time: 2014-6-12
	 */
	public boolean insert(T615_Bean GenUndergraMajStuNum) {

		// flag判断数据是否插入成功
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection();
		try {
			flag = DAOUtil.insert(GenUndergraMajStuNum, tableName, field, conn);
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
	public boolean batchInsert(List<T615_Bean> list) {

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
	public boolean update(T615_Bean GenUndergraMajStuNum) {

		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection();
		try {
			flag = DAOUtil
					.update(GenUndergraMajStuNum, tableName, key, field, conn);
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
	
	public List<T615_Bean> queryPageList(int pagesize, int currentpage) {
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
		List<T615_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T615_Bean.class) ;
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
	
	public List<T615_Bean> queryPageList(String cond, Object object,
			int pagesize, int currentpage) {
		
		String Cond = "1=1";
		
		if(cond != null && !cond.equals("")){
			Cond = Cond + cond;
		}
//		
//		if(fillunitID != null && !fillunitID.equals("")){
//			Cond = Cond + " and FillUnitID=" + fillunitID;
//		}
		// TODO Auto-generated method stub
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
		List<T615_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T615_Bean.class) ;
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
	
	public List<T615_Bean> getAllList() {
		// TODO Auto-generated method stub
		String sql = "select " + fieldShow + " from " + tableName ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T615_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T615_Bean.class) ;
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
	
	public List<T615_Bean> getAllList(String cond, Object object) {
		// TODO Auto-generated method stub
		
		String Cond = "1=1";
		
		int total = 0;
		if(cond != null && !cond.equals("")){
			Cond = Cond + cond;
		}
		
//		if(fillunitID != null && !fillunitID.equals("")){
//			Cond = Cond + " and FillUnitID=" + fillunitID;
//		}
				
		String sql;
		
		sql = "select " + fieldShow + " from " + tableName +" where " + Cond;
	    System.out.println(sql);
	
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T615_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T615_Bean.class) ;
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
	
	/**用于教育部导出，参数添加年份*/
	public List<T615_Bean> getAllList(String year) {
		// TODO Auto-generated method stub
//		
//		String Cond = "1=1";
//		
//		int total = 0;
//		if(cond != null && !cond.equals("")){
//			Cond = Cond + cond;
//		}
//		
//		if(fillunitID != null && !fillunitID.equals("")){
//			Cond = Cond + " and FillUnitID=" + fillunitID;
//		}
				
		String sql;
		
		sql = "select " + fieldShow + " from " + tableName +" where Time like '"+year+"%'" ;
	    System.out.println(sql);
	
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T615_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T615_Bean.class) ;
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

		T615_Dao GenUndergraMajStuNumDao = new T615_Dao();
//		List<T615_Bean> list = GenUndergraMajStuNumDao.queryPageList(null, null, 10, 1);
		List<T615_Bean> list = GenUndergraMajStuNumDao.getAllList("2014");
		System.out.println(list.size());
//		T615_Bean GenUndergraMajStuNum = new T615_Bean();
////		 GenUndergraMajStuNum.setSeqNumber(1);
//		//		
//		GenUndergraMajStuNum.setMajorName("水利水电工程");
//		GenUndergraMajStuNum.setMajorId("081101");
//		GenUndergraMajStuNum.setFromUnitId("水利与生态工程学院");
//		GenUndergraMajStuNum.setUnitId("3001");
//		
//		GenUndergraMajStuNum.setSchLen(4);
//		GenUndergraMajStuNum.setSchStuSumNum(1388);
//		GenUndergraMajStuNum.setFreshmanNum(309);
//		GenUndergraMajStuNum.setSophomoreNum(318);
//		GenUndergraMajStuNum.setJuniorNum(396);
//		GenUndergraMajStuNum.setSeniorNum(365);
//		GenUndergraMajStuNum.setOtherGradeNum(0);
//		GenUndergraMajStuNum.setMinorNum(30);
//		GenUndergraMajStuNum.setDualDegreeNum(30);
//		GenUndergraMajStuNum.setChangeInNum(3);
//		GenUndergraMajStuNum.setChangeOutNum(0);
//
//		
//		GenUndergraMajStuNum.setTime(new Date());
//		GenUndergraMajStuNum.setNote("无");
////		//		
//		GenUndergraMajStuNumDao.insert(GenUndergraMajStuNum);

	}
}
