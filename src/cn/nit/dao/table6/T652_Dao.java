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
import cn.nit.constants.Constants;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T652_Dao {

	/** 数据库表名 */
	private String tableName = "T652_StuPublishPaper_TeaYLC$";

	/** 数据自增长字段的主键，必须为自增长字段 */
	private String key = "SeqNumber";

	/** 数据库表中除了自增长字段的所有字段 */
	private String field = "TeaUnit,UnitId,PaperTitle,JonalName,JonalId,JonalDate,AwardStuName," +
			"AwardStuNum,GuideTeaName,GuideTeaNum,IsAward," +
			"AwardLevel,AwardName,AwardFromUnit,Time,Note,FillUnitID,CheckState";

	
	private String fieldShow = "SeqNumber,TeaUnit,UnitId,PaperTitle,JonalName,JonalId," +
			"JonalDate,AwardStuName,AwardStuNum,GuideTeaName,GuideTeaNum,IsAward," +
			"AwardLevel,AwardName,AwardFromUnit,Time,Note,FillUnitID,CheckState";


	
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
			
			String updatefield = "TeaUnit,UnitId,PaperTitle,JonalName,JonalId,JonalDate,AwardStuName," +
			"AwardStuNum,GuideTeaName,GuideTeaNum,IsAward," +
			"AwardLevel,AwardName,AwardFromUnit,Time,Note,FillUnitID,CheckState";
			
			String temp1 = updatefield;
			
			if(StuPublishPaper.getAwardLevel().trim().equals("")){
				String a = "AwardLevel,";
			    temp1 = updatefield.replaceAll(a , "");
			}
			
			flag = DAOUtil
					.update(StuPublishPaper, tableName, key, temp1, conn);
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
	
	public List<T652_Bean> queryPageList(String cond, String filledID,
			int pagesize, int currentpage) {
		// TODO Auto-generated method stub
		
		String Cond = "1=1";
		
		if(cond != null && !cond.equals("")){
			Cond = Cond + cond;
		}
		
		if(filledID != null && !filledID.equals("")){
			Cond = Cond + " and FillUnitID=" + filledID;
		}
			
		
		String queryPageSql;
		
			queryPageSql = "select top " + pagesize + 
			"SeqNumber,TeaUnit,UnitID,PaperTitle,JonalName,JonalID,JonalDate,AwardStuName,AwardStuNum,GuideTeaName," +
			"GuideTeaNum,IsAward,DiAwardLevel.AwardLevel,AwardName,AwardFromUnit,Time,Note,FillUnitID,CheckState"+
//			fieldShow
			" from " + tableName + 
			" left join DiAwardLevel on "+tableName+".AwardLevel = DiAwardLevel.IndexID "+
			" where " + Cond + " and (SeqNumber not in (select top " + pagesize * (currentpage-1) + " SeqNumber from "+
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
	
	/**显示总条数*/
	public List<T652_Bean> getAllList(String cond, String filledID) {
		// TODO Auto-generated method stub
		String Cond = "1=1";
		
		if(cond != null && !cond.equals("")){
			Cond = Cond + cond;
		}
		if(filledID != null && !filledID.equals("")){
			Cond = Cond + " and FillUnitID=" + filledID;
		}
		
		String sql; 
		
		sql = "select t.SeqNumber,t.TeaUnit,t.UnitId,t.PaperTitle,t.JonalName,t.JonalId,t.JonalDate,t.AwardStuName," +
				"t.AwardStuNum,t.GuideTeaName,t.GuideTeaNum,t.IsAward,dia.AwardLevel,t.AwardName,t.AwardFromUnit," +
				"t.Time,t.Note,t.FillUnitID,t.CheckState from "+tableName+" as t,DiAwardLevel as dia "
				+" where t.AwardLevel = dia.IndexID and "+Cond;
		
//		sql = "select " + fieldShow + " from " + tableName +" where " + Cond
//			 +" and FillUnitID="+filledID;
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
	
	
	/**
	 * 用与审核完导出
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T652_Bean> totalList(String fillUnitID, String year, int checkState){
		
		String sql = "select " + key+ "," +field + " from " + tableName
						+ " where FillUnitID=" + "'" + fillUnitID + "'" 
						+ " and CheckState=" + checkState + " and Time like '"+year+"%'";
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
	
	/**
	 * 找到该条数据的审核状态
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */	
	public int getCheckState(int seqNumber){
				
		String queryPageSql = "select CheckState " 
		+ " from " + tableName + 
		" where SeqNumber='" + seqNumber + "';" ;
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		int state = 1;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			
			while(rs.next()){
				state = rs.getInt(1) ;
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return 0 ;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
		
		return state ;
	}
	
	/**
	 * 更新某条数据的审核状态
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */	
	public boolean updateCheck(int seq, int checkState){
		
		int flag ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		String sql = "update " + tableName + " set CheckState=" + checkState +
		" where SeqNumber='" + seq + "';" ;		
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
	
	/**
	 * 全部审核通过
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */	
	public boolean checkAll(){
		
		int flag ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		String sql = "update " + tableName + " set CheckState=" + Constants.PASS_CHECK +
		" where CheckState=" + Constants.WAIT_CHECK ;		
		
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

		T652_Dao StuPublishPaperDao = new T652_Dao();
		List<T652_Bean> list = StuPublishPaperDao.getAllList(null, "1012");
		System.out.println(list.size());
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
