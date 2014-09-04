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
import cn.nit.bean.table6.T651_Bean;
import cn.nit.bean.table6.T652_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T652_Dao {

	/** 数据库表名 */
	private String tableName = "T652_StuPublishPaper_TeaYLC$";

	/** 数据自增长字段的主键，必须为自增长字段 */
	private String key = "SeqNumber";

	/** 数据库表中除了自增长字段的所有字段 */
	private String field = "TeaUnit,UnitId,PaperTitle,JonalName,JonalId,JonalDate,AwardStuName,AwardStuNum,GuideTeaName,GuideTeaNum,IsAward,AwardLevel,AwardName,AwardFromUnit,Time,Note,FillUnitID";

	
	private String fieldShow = "SeqNumber,TeaUnit,UnitId,PaperTitle,JonalName,JonalId,JonalDate,AwardStuName,AwardStuNum,GuideTeaName,GuideTeaNum,IsAward,AwardLevel,AwardName,AwardFromUnit,Time,Note,FillUnitID";


	
	/* ,FillTeaID,FillUnitID,audit */

	/**
	 * 将数据表622的实体类插入数据库
	 * 
	 * @param UndergraAdmiInfo
	 * @return
	 * 
	 * @time: 2014-6-12
	 */
	public boolean insert(T652_Bean StuPublishPaper) {

		// flag判断数据是否插入成功
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection();
		try {
			flag = DAOUtil.insert(StuPublishPaper, tableName, field, conn);
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
	public boolean batchInsert(List<T652_Bean> list) {

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
	public boolean update(T652_Bean StuPublishPaper) {

		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection();
		try {
			flag = DAOUtil
					.update(StuPublishPaper, tableName, key, field, conn);
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
	
	public List<T652_Bean> queryPageList(int pagesize, int currentpage) {
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
		List<T652_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T652_Bean.class) ;
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
	
	public List<T652_Bean> queryPageList(String cond, String filledID,
			int pagesize, int currentpage) {
		// TODO Auto-generated method stub
		
		String Cond = "1=1";
		
		if(cond != null && !cond.equals("")){
			Cond = Cond + cond;
		}
		String queryPageSql;
		
			queryPageSql = "select top " + pagesize + 
			"SeqNumber,TeaUnit,UnitID,PaperTitle,JonalName,JonalID,JonalDate,AwardStuName,AwardStuNum,GuideTeaName," +
			"GuideTeaNum,IsAward,DiAwardLevel.AwardLevel,AwardName,AwardFromUnit,Time,Note,FillUnitID "+
//			fieldShow
			" from " + tableName + 
			" left join DiAwardLevel on "+tableName+".AwardLevel = DiAwardLevel.IndexID "+
			" where " + Cond + " and FillUnitID="+filledID+
			" and (SeqNumber not in (select top " + pagesize * (currentpage-1) + " SeqNumber from "+
			tableName + " where " + Cond + " order by SeqNumber)) order by SeqNumber" ;
	

		System.out.println(queryPageSql);
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T652_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T652_Bean.class) ;
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
	
	public List<T652_Bean> getAllList() {
		// TODO Auto-generated method stub
		String sql = "select " + fieldShow + " from " + tableName ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T652_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T652_Bean.class) ;
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
	
	public List<T652_Bean> getAllList(String cond, String filledID) {
		// TODO Auto-generated method stub
		String Cond = "1=1";
		
		if(cond != null && !cond.equals("")){
			Cond = Cond + cond;
		}
		String sql;
		
		sql = "select " + fieldShow + " from " + tableName +" where " + Cond
			 +" and FillUnitID="+filledID;
//	    System.out.println(sql);
	
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T652_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T652_Bean.class) ;
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
	
	public int getPaper(String year){
		int PaperNum = 0;
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) AS PaperNum from T652_StuPublishPaper_TeaYLC$");
		sql.append(" where convert(varchar(4),T652_StuPublishPaper_TeaYLC$.Time,120) =" + year);
		Connection conn=DBConnection.instance.getConnection();
		Statement st=null;
		ResultSet rs=null;
		
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			while(rs.next()){
				PaperNum = rs.getInt("PaperNum");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConnection.close(conn);
		}
		
		return PaperNum;
	}
	
	public List<T652_Bean> getYearInfo(String year){
		
		String sql = "select " + " " + key + "," +
		field + " from " + tableName + " where convert(varchar(4),Time,120)=" + year;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T652_Bean> list = null ;
		T652_Bean bean = null;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T652_Bean.class) ;
			
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

//		T652_Dao StuPublishPaperDao = new T652_Dao();
//		T652_Bean StuPublishPaper = new T652_Bean();
//		List<T652_Bean> list = StuPublishPaperDao.getYearInfo("2014");
//		
//		System.out.println(list.size());
//		 StuPublishPaper.setSeqNumber(1);
		//	
		//TeaUnit,UnitId,PaperTitle,JonalName,JonalID,JonalDate,AwardStuName,
		//AwardStuNum,GuideTeaName,GuideTeaNum,IsAward,AwardLevel,AwardName,AwardFromUnit,Time,Note,FillUnitID
	
//		StuPublishPaper.setTeaUnit("水利与生态工程学院");
//		StuPublishPaper.setUnitId("3001");
//		StuPublishPaper.setPaperTitle("test");
//		StuPublishPaper.setJonalName("test");
//		StuPublishPaper.setJonalId("test");
//		StuPublishPaper.setJonalDate(new Date());
//		StuPublishPaper.setAwardStuName("test");
//		StuPublishPaper.setAwardStuNum(2);
//		StuPublishPaper.setGuideTeaName("test");
//		StuPublishPaper.setGuideTeaNum(2);
//		StuPublishPaper.setIsAward(true);
//		StuPublishPaper.setAwardLevel("50001");
//		StuPublishPaper.setAwardName("test");
//		StuPublishPaper.setAwardFromUnit("test");
//		StuPublishPaper.setFillUnitID("1029");
//		
//				
//		StuPublishPaper.setTime(new Date());
//		StuPublishPaper.setNote("无");
////		//		
//		StuPublishPaperDao.insert(StuPublishPaper);

	}







}
