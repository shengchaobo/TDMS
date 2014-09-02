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
import cn.nit.bean.table6.T654_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T654_Dao {

	/** 数据库表名 */
	private String tableName = "T654_StuAwardPatent_TeaYLC$";

	/** 数据自增长字段的主键，必须为自增长字段 */
	private String key = "SeqNumber";

	/** 数据库表中除了自增长字段的所有字段 */
	private String field = "TeaUnit,UnitId,JonalName,JonalId,PatentType,AppvlTime,AwardStuName,AwardStuNum,GuideTeaName,GuideTeaNum,Time,Note,FillUnitID";

	
	private String fieldShow = "SeqNumber,TeaUnit,UnitId,JonalName,JonalId,PatentType,AppvlTime,AwardStuName,AwardStuNum,GuideTeaName,GuideTeaNum,Time,Note,FillUnitID";



	
	/* ,FillTeaID,FillUnitID,audit */

	/**
	 * 将数据表622的实体类插入数据库
	 * 
	 * @param UndergraAdmiInfo
	 * @return
	 * 
	 * @time: 2014-6-12
	 */
	public boolean insert(T654_Bean StuAwardPatent) {

		// flag判断数据是否插入成功
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection();
		try {
			flag = DAOUtil.insert(StuAwardPatent, tableName, field, conn);
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
	public boolean batchInsert(List<T654_Bean> list) {

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
	public boolean update(T654_Bean StuAwardPatent) {

		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection();
		try {
			flag = DAOUtil
					.update(StuAwardPatent, tableName, key, field, conn);
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
	
	public List<T654_Bean> queryPageList(int pagesize, int currentpage) {
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
		List<T654_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T654_Bean.class) ;
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
	
	public List<T654_Bean> queryPageList(String cond, String filledID,
			int pagesize, int currentpage) {
		String Cond = "1=1";
		
		if(cond != null && !cond.equals("")){
			Cond = Cond + cond;
		}
		// TODO Auto-generated method stub
		String queryPageSql;
		
			queryPageSql = "select top " + pagesize + 
			fieldShow
			+ " from " + tableName + 
			" where " + Cond +" and FillUnitID="+filledID+
			" and (SeqNumber not in (select top " + pagesize * (currentpage-1) + " SeqNumber from "+
			tableName + " where " + Cond + " order by SeqNumber)) order by SeqNumber" ;
	

		System.out.println(queryPageSql);
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T654_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T654_Bean.class) ;
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
	
	public List<T654_Bean> getAllList() {
		// TODO Auto-generated method stub
		String sql = "select " + fieldShow + " from " + tableName ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T654_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T654_Bean.class) ;
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
	
	public List<T654_Bean> getAllList(String cond, String filledID) {
		// TODO Auto-generated method stub
		String Cond = "1=1";
		
		if(cond != null && !cond.equals("")){
			Cond = Cond + cond;
		}
		String sql;
		
		sql = "select " + fieldShow + " from " + tableName +" where " + Cond
					+" and FillUnitID="+filledID;
	    System.out.println(sql);
	
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T654_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T654_Bean.class) ;
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
	
	public int getPatent(String year){
		int PatentNum = 0;
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) AS PatentNum from T654_StuAwardPatent_TeaYLC$");
		sql.append(" where convert(varchar(4),T654_StuAwardPatent_TeaYLC$.Time,120) =" + year);
		Connection conn=DBConnection.instance.getConnection();
		Statement st=null;
		ResultSet rs=null;
		
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			while(rs.next()){
				PatentNum = rs.getInt("PatentNum");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConnection.close(conn);
		}
		
		return PatentNum;
	}
	

	public static void main(String args[]) {

		T654_Dao StuAwardPatentDao = new T654_Dao();
		T654_Bean StuAwardPatent = new T654_Bean();
//		 StuAwardPatent.setSeqNumber(1);
		//	
	
		StuAwardPatent.setTeaUnit("水利与生态工程学院");
		StuAwardPatent.setUnitId("3001");
		StuAwardPatent.setJonalName("test");
		StuAwardPatent.setJonalId("test");
		StuAwardPatent.setPatentType("test");
		StuAwardPatent.setAppvlTime(new Date());
		StuAwardPatent.setAwardStuName("test");
		StuAwardPatent.setAwardStuNum(2);
		StuAwardPatent.setGuideTeaName("test");
		StuAwardPatent.setGuideTeaNum(2);
		StuAwardPatent.setFillUnitID("1022");
		
				
		StuAwardPatent.setTime(new Date());
		StuAwardPatent.setNote("无");
//		//		
		StuAwardPatentDao.insert(StuAwardPatent);
		//		
		//	
		//		
		// //
		// System.out.println(underCSBaseTeaDao.auditingData("audit='1'",null,2,10).size())
		// ;
		// // System.out.println(StuAwardPatentDao.update(StuAwardPatent)) ;
//		 System.out.println(StuAwardPatentDao.deleteItemsByIds("(8)")) ;

		System.out.println("success!!");
	}







}
