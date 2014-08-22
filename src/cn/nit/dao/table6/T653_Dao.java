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
import cn.nit.bean.table6.T653_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T653_Dao {

	/** 数据库表名 */
	private String tableName = "T653_StuPublishWord_TeaYLC$";

	/** 数据自增长字段的主键，必须为自增长字段 */
	private String key = "SeqNumber";

	/** 数据库表中除了自增长字段的所有字段 */
	private String field = "TeaUnit,UnitId,WorkName,JonalName,JonalId,JonalDate,AwardStuName,AwardStuNum,GuideTeaName,GuideTeaNum,IsAward,AwardLevel,AwardName,AwardFromUnit,Time,Note,FillUnitID";

	
	private String fieldShow = "SeqNumber,TeaUnit,UnitId,WorkName,JonalName,JonalId,JonalDate,AwardStuName,AwardStuNum,GuideTeaName,GuideTeaNum,IsAward,AwardLevel,AwardName,AwardFromUnit,Time,Note,FillUnitID";

	/* ,FillTeaID,FillUnitID,audit */

	/**
	 * 将数据表653的实体类插入数据库
	 * 
	 * @param UndergraAdmiInfo
	 * @return
	 * 
	 * @time: 2014-6-12
	 */
	public boolean insert(T653_Bean StuPublishWord) {

		// flag判断数据是否插入成功
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection();
		try {
			flag = DAOUtil.insert(StuPublishWord, tableName, field, conn);
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
	public boolean batchInsert(List<T653_Bean> list) {

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
	public boolean update(T653_Bean StuPublishWord) {

		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection();
		try {
			flag = DAOUtil
					.update(StuPublishWord, tableName, key, field, conn);
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
	
	public List<T653_Bean> queryPageList(int pagesize, int currentpage) {
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
		List<T653_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T653_Bean.class) ;
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
	
	public List<T653_Bean> queryPageList(String cond, Object object,
			int pagesize, int currentpage) {
		// TODO Auto-generated method stub
		String queryPageSql;
		
			queryPageSql = "select top " + pagesize +
			"SeqNumber,TeaUnit,UnitID,WorkName,JonalName,JonalID,JonalDate,AwardStuName,AwardStuNum,GuideTeaName,GuideTeaNum," +
			"DiAwardLevel.AwardLevel,AwardName,AwardFromUnit,Time,Note,FillUnitID,IsAward"
			//fieldShow
			+ " from " + tableName + 
			" left join DiAwardLevel on "+tableName+".AwardLevel = DiAwardLevel.IndexID "+
			" where " + cond + " and (SeqNumber not in (select top " + pagesize * (currentpage-1) + " SeqNumber from "+
			tableName + " where " + cond + " order by SeqNumber)) order by SeqNumber" ;
	

		System.out.println(queryPageSql);
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T653_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T653_Bean.class) ;
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
	
	public List<T653_Bean> getAllList() {
		// TODO Auto-generated method stub
		String sql = "select " + fieldShow + " from " + tableName ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T653_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T653_Bean.class) ;
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
	
	public List<T653_Bean> getAllList(String cond, Object object) {
		// TODO Auto-generated method stub
		String sql;
		sql = "select SeqNumber,TeaUnit,UnitID,WorkName,JonalName,JonalID,JonalDate,AwardStuName,AwardStuNum,"+
		"GuideTeaName,GuideTeaNum,DiAwardLevel.AwardLevel,AwardName,AwardFromUnit,Time,Note,FillUnitID,IsAward"+
		" from "+tableName+
		" left join DiAwardLevel on "+tableName+".AwardLevel = DiAwardLevel.IndexID"+
		" where "+cond;
//		sql = "select " + fieldShow + " from " + tableName +" where " + cond;
	    System.out.println(sql);
	
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T653_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T653_Bean.class) ;
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
	
	public int getWork(String year){
		int WorkNum = 0;
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) AS WorkNum from T653_StuPublishWord_TeaYLC$");
		sql.append(" where convert(varchar(4),T653_StuPublishWord_TeaYLC$.Time,120) =" + year);
		Connection conn=DBConnection.instance.getConnection();
		Statement st=null;
		ResultSet rs=null;
		
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			while(rs.next()){
				WorkNum = rs.getInt("WorkNum");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConnection.close(conn);
		}
		
		return WorkNum;
	}

	public static void main(String args[]) {

		T653_Dao StuPublishWordDao = new T653_Dao();
//		T653_Bean StuPublishWord = new T653_Bean();
////		 StuPublishWord.setSeqNumber(1);
//		//	
//
//		StuPublishWord.setTeaUnit("水利与生态工程学院");
//		StuPublishWord.setUnitId("3001");
//		
//		StuPublishWord.setWorkName("test");
//		StuPublishWord.setJonalName("test");
//		StuPublishWord.setJonalId("test");
//		StuPublishWord.setJonalDate(new Date());
//		StuPublishWord.setAwardStuName("test");
//		StuPublishWord.setAwardStuNum(2);
//		StuPublishWord.setGuideTeaName("test");
//		StuPublishWord.setGuideTeaNum(2);
//		StuPublishWord.setIsAward(true);
//		StuPublishWord.setAwardLevel("50002");
//		StuPublishWord.setAwardName("test");
//		StuPublishWord.setAwardFromUnit("test");
//		StuPublishWord.setFillUnitID("1022");
//		
//				
//		StuPublishWord.setTime(new Date());
//		StuPublishWord.setNote("无");
////		//		
//		StuPublishWordDao.insert(StuPublishWord);
		//		
		//	
		//		
		// //
		// System.out.println(underCSBaseTeaDao.auditingData("audit='1'",null,2,10).size())
		// ;
		// // System.out.println(StuPublishWordDao.update(StuPublishWord)) ;
//		 System.out.println(StuPublishWordDao.deleteItemsByIds("(8)")) ;

//		System.out.println("success!!");
		List<T653_Bean> list = StuPublishWordDao.getAllList("1=1", null);
		System.out.println(list.size());
	}







}
