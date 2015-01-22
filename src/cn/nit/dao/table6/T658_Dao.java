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
import cn.nit.bean.table6.T658_Bean;
import cn.nit.constants.Constants;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T658_Dao {

	/** 数据库表名 */
	private String tableName = "T658_InInterConference_TeaInter$";

	/** 数据自增长字段的主键，必须为自增长字段 */
	private String key = "SeqNumber";

	/** 数据库表中除了自增长字段的所有字段 */
	private String field = "TeaUnit,UnitId,ConferenceName,PaperTitle,HoldTime," +
			"HoldPlace,HoldUnit,ConferenceLevel,AwardStuName,AwardStuNum," +
			"GuideTeaName,GuideTeaNum,Time,Note,FillUnitID,CheckState";

	
	private String fieldShow = "SeqNumber,TeaUnit,UnitId,ConferenceName," +
			"PaperTitle,HoldTime,HoldPlace,HoldUnit,ConferenceLevel,AwardStuName," +
			"AwardStuNum,GuideTeaName,GuideTeaNum,Time,Note,FillUnitID,CheckState";
	
	
	/* ,FillTeaID,FillUnitID,audit */

	/**
	 * 将数据表622的实体类插入数据库
	 * 
	 * @param UndergraAdmiInfo
	 * @return
	 * 
	 * @time: 2014-6-12
	 */
	public boolean insert(T658_Bean InInterConference) {

		// flag判断数据是否插入成功
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection();
		try {
			flag = DAOUtil.insert(InInterConference, tableName, field, conn);
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
	public boolean batchInsert(List<T658_Bean> list) {

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
	public boolean update(T658_Bean InInterConference) {

		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection();
		try {
			
			String updatefield = "TeaUnit,UnitId,ConferenceName,PaperTitle,HoldTime," +
			"HoldPlace,HoldUnit,ConferenceLevel,AwardStuName,AwardStuNum," +
			"GuideTeaName,GuideTeaNum,Time,Note,FillUnitID,CheckState";
			
			String temp1 = updatefield;
			
			if(InInterConference.getConferenceLevel().trim().equals("")){
				String a = "ConferenceLevel,";
			    temp1 = updatefield.replaceAll(a , "");
			}
			flag = DAOUtil
					.update(InInterConference, tableName, key, temp1, conn);
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
	
	public List<T658_Bean> queryPageList(int pagesize, int currentpage) {
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
		List<T658_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T658_Bean.class) ;
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
	
	/**分页显示*/
	public List<T658_Bean> queryPageList(String cond, String filledID,
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
			"SeqNumber,TeaUnit,UnitID,ConferenceName,PaperTitle,HoldTime,HoldPlace," +
			"HoldUnit,DiAwardLevel.AwardLevel as ConferenceLevel,AwardStuName" +
			",AwardStuNum,GuideTeaName,GuideTeaNum,Time,Note,FillUnitID,CheckState"
//			fieldShow
			+ " from " + tableName + 
			" left join DiAwardLevel on "+tableName+".ConferenceLevel = DiAwardLevel.IndexID"+
			" where " + Cond + " and (SeqNumber not in (select top " + pagesize * (currentpage-1) + " SeqNumber from "+
			tableName + " where " + Cond + " order by SeqNumber)) order by SeqNumber" ;
	

		System.out.println(queryPageSql);
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T658_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T658_Bean.class) ;
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
	
	public List<T658_Bean> getAllList() {
		// TODO Auto-generated method stub
		String sql = "select " + fieldShow + " from " + tableName ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T658_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T658_Bean.class) ;
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
	
	/**分页显示数据总条数*/
	public List<T658_Bean> getAllList(String cond, String fillUnitID) {
		// TODO Auto-generated method stub
		String Cond = "1=1";
		
		if(cond != null && !cond.equals("")){
			Cond = Cond + cond;
		}
		if(fillUnitID != null && !fillUnitID.equals("")){
			Cond = Cond + " and FillUnitID=" + fillUnitID;
		}
		
		String sql;
		sql = "select SeqNumber,TeaUnit,UnitID,ConferenceName,PaperTitle,HoldTime," +
				"HoldPlace,HoldUnit,DiAwardLevel.AwardLevel as ConferenceLevel,AwardStuName" +
		",AwardStuNum,GuideTeaName,GuideTeaNum,Time,Note,FillUnitID,CheckState"
//		fieldShow
		+ " from " + tableName + 
		" left join DiAwardLevel on "+tableName+".ConferenceLevel = DiAwardLevel.IndexID"+
		" where "+Cond;
		
//		sql = "select " + fieldShow + " from " + tableName +" where " + cond;
	    System.out.println(sql);
	
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T658_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T658_Bean.class) ;
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
	
	public int getInterConference(String year){
		int InterConference = 0;
		StringBuffer sql = new StringBuffer();
		sql.append(" select sum(AwardStuNum) AS InterConference from T658_InInterConference_TeaInter$");
		sql.append(" where convert(varchar(4),T658_InInterConference_TeaInter$.Time,120) =" + year);
		Connection conn=DBConnection.instance.getConnection();
		Statement st=null;
		ResultSet rs=null;
		
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			while(rs.next()){
				InterConference = rs.getInt("InterConference");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConnection.close(conn);
		}
		
		return InterConference;
	}
	
	public List<T658_Bean> getYearInfo(String year){
		
		String sql = "select " + " " + key + "," +
		field + " from " + tableName + " where convert(varchar(4),Time,120)=" + year;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T658_Bean> list = null ;
		//T651_Bean bean = null;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T658_Bean.class) ;
			
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
	public List<T658_Bean> totalList(String fillUnitID, String year, int checkState){
		
		
		String sql = "select SeqNumber,TeaUnit,UnitID,ConferenceName,PaperTitle,HoldTime," +
				"HoldPlace,HoldUnit,DiAwardLevel.AwardLevel as ConferenceLevel,AwardStuName" +
		",AwardStuNum,GuideTeaName,GuideTeaNum,Time,Note,FillUnitID,CheckState"
		+ " from " + tableName + 
		" left join DiAwardLevel on "+tableName+".ConferenceLevel = DiAwardLevel.IndexID"+
		" where FillUnitID=" + "'" + fillUnitID + "'" 
						+ " and CheckState=" + checkState + " and Time like '"+year+"%'";
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T658_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T658_Bean.class) ;
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

		T658_Dao InInterConferenceDao = new T658_Dao();
		T658_Bean InInterConference = new T658_Bean();
//		 InInterConference.setSeqNumber(1);
//		//	
//	
//		InInterConference.setTeaUnit("水利与生态工程学院");
//		InInterConference.setUnitId("3001");
//		InInterConference.setConferenceName("test");
//		InInterConference.setPaperTitle("test");
//		InInterConference.setHoldTime(new Date());
//		InInterConference.setHoldPlace("test");
//		InInterConference.setHoldUnit("test");
//		InInterConference.setConferenceLevel("50001");
//		InInterConference.setAwardStuNum(2);
//		InInterConference.setAwardStuName("test");
//		InInterConference.setGuideTeaName("test");
//		InInterConference.setGuideTeaNum(2);
//		InInterConference.setFillUnitID("1022");
//				
//		InInterConference.setTime(new Date());
//		InInterConference.setNote("无");
////		//		
//		InInterConferenceDao.insert(InInterConference);
//		//		
//		//	
//		//		
//		// //
//		// System.out.println(underCSBaseTeaDao.auditingData("audit='1'",null,2,10).size())
//		// ;
//		// // System.out.println(InInterConferenceDao.update(InInterConference)) ;
////		 System.out.println(InInterConferenceDao.deleteItemsByIds("(8)")) ;
//
//		System.out.println("success!!");
		List<T658_Bean> list = InInterConferenceDao.getAllList("1=1", null);
		System.out.println(list.size());
	}







}
