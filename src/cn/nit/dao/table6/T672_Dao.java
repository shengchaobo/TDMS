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
import cn.nit.bean.table6.T672_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T672_Dao {

	/** 数据库表名 */
	private String tableName = "T672_DualDegreeInfo_Tea$";

	/** 数据自增长字段的主键，必须为自增长字段 */
	private String key = "SeqNumber";

	/** 数据库表中除了自增长字段的所有字段 */
	private String field = "StuName,StuId,FromTeaUnit,UnitId,FromMaj,MajId,FromClass,DualDegreeFromTeaUnit,DualDegreeUnitId,DualDegreeMaj,DualDegreeId,BeginTime,GraduateTime,Time,Note";

	
	private String fieldShow = "SeqNumber,StuName,StuId,FromTeaUnit,UnitId,FromMaj,MajId,FromClass,DualDegreeFromTeaUnit,DualDegreeUnitId,DualDegreeMaj,DualDegreeId,BeginTime,GraduateTime,Time,Note";

	
	
	/* ,FillTeaID,FillUnitID,audit */

	/**
	 * 将数据表622的实体类插入数据库
	 * 
	 * @param UndergraAdmiInfo
	 * @return
	 * 
	 * @time: 2014-6-12
	 */
	public boolean insert(T672_Bean DualDegreeInfo) {

		// flag判断数据是否插入成功
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection();
		try {
			flag = DAOUtil.insert(DualDegreeInfo, tableName, field, conn);
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
	public boolean batchInsert(List<T672_Bean> list) {

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
	public boolean update(T672_Bean DualDegreeInfo) {

		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection();
		try {
			flag = DAOUtil
					.update(DualDegreeInfo, tableName, key, field, conn);
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
	
	public List<T672_Bean> queryPageList(int pagesize, int currentpage) {
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
		List<T672_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T672_Bean.class) ;
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
	
	public List<T672_Bean> queryPageList(String cond, Object object,
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
		List<T672_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T672_Bean.class) ;
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
	
	public List<T672_Bean> getAllList() {
		// TODO Auto-generated method stub
		String sql = "select " + fieldShow + " from " + tableName ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T672_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T672_Bean.class) ;
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
	
	public List<T672_Bean> getAllList(String cond, Object object) {
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
		List<T672_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T672_Bean.class) ;
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

		T672_Dao DualDegreeInfoDao = new T672_Dao();
		T672_Bean DualDegreeInfo = new T672_Bean();
//		 DualDegreeInfo.setSeqNumber(1);
		//	
		
		DualDegreeInfo.setStuName("小明");
		DualDegreeInfo.setStuId("2011010123");
		DualDegreeInfo.setFromTeaUnit("水利与生态工程学院");
		DualDegreeInfo.setUnitId("3001");
		DualDegreeInfo.setFromMaj("水利水电工程");
		DualDegreeInfo.setMajId("081101");
		DualDegreeInfo.setFromClass("10水利");
		DualDegreeInfo.setDualDegreeFromTeaUnit("水利与生态工程学院");
		DualDegreeInfo.setDualDegreeUnitId("3001");
		DualDegreeInfo.setDualDegreeMaj("水利水电工程");
		DualDegreeInfo.setDualDegreeId("081101");
		DualDegreeInfo.setBeginTime(new Date());
		DualDegreeInfo.setGraduateTime(new Date());
				
		DualDegreeInfo.setTime(new Date());
		DualDegreeInfo.setNote("无");
//		//		
		DualDegreeInfoDao.insert(DualDegreeInfo);
		//		
		//	
		//		
		// //
		// System.out.println(underCSBaseTeaDao.auditingData("audit='1'",null,2,10).size())
		// ;
		// // System.out.println(DualDegreeInfoDao.update(DualDegreeInfo)) ;
//		 System.out.println(DualDegreeInfoDao.deleteItemsByIds("(8)")) ;

		System.out.println("success!!");
	}







}
