package cn.nit.dao.table6;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSON;



import cn.nit.bean.table2.T21_Bean;
import cn.nit.bean.table6.S65_Bean;
import cn.nit.bean.table6.T621_Bean;
import cn.nit.bean.table6.T622_Bean;
import cn.nit.bean.table6.T641_Bean;
import cn.nit.bean.table6.T651_Bean;
import cn.nit.constants.Constants;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class T651_Dao {

	/** 数据库表名 */
	private String tableName = "T651_StuCompetiAwardInfo_TeaYLC$";

	/**外键表1*/
    private String tableName1="DiContestLevel";

    /**外键表2*/
    private String tableName2="DiAwardLevel";

	/** 数据自增长字段的主键，必须为自增长字段 */
	private String key = "SeqNumber";

	/** 数据库表中除了自增长字段的所有字段 */
	private String field = "TeaUnit,UnitId,CompetiType,CompetiName,AwardItem,AwardLevel,AwardGrade," +
			"AwardFromUnit,AwardTime,AwardStuName,AwardStuNum,GuideTeaName,GuideTeaNum,Time,Note,FillUnitID,CheckState";


	private String fieldShow = "SeqNumber,TeaUnit,UnitId,CompetiType,CompetiName,AwardItem,AwardLevel," +
			"AwardGrade,AwardFromUnit,AwardTime,AwardStuName,AwardStuNum,GuideTeaName,GuideTeaNum,Time,Note,FillUnitID,CheckState";




	/* ,FillTeaID,FillUnitID,audit */

	/**
	 * 将数据表622的实体类插入数据库
	 *
	 * @param UndergraAdmiInfo
	 * @return
	 *
	 * @time: 2014-6-12
	 */
	public boolean insert(T651_Bean StuCompetiAwardInfo) {

		// flag判断数据是否插入成功
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection();
		try {
			flag = DAOUtil.insert(StuCompetiAwardInfo, tableName, field, conn);
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
	public boolean batchInsert(List<T651_Bean> list) {

		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection();

		try {
			flag = DAOUtil.batchInsert(list, tableName, field, conn);
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}finally{
			DBConnection.close(conn);
		}

		return flag;
	}

	// 更新
	public boolean update(T651_Bean StuCompetiAwardInfo) {

		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection();
		try {
			
			String updatefield = "TeaUnit,UnitId,CompetiType,CompetiName,AwardItem,AwardLevel,AwardGrade," +
			"AwardFromUnit,AwardTime,AwardStuName,AwardStuNum,GuideTeaName,GuideTeaNum,Time,Note,FillUnitID,CheckState";
			
			String temp1 = updatefield;
			if(StuCompetiAwardInfo.getAwardLevel().trim().equals("")){
				System.out.println("++++++++++++++++++++++AwardLevel");
				String a = "AwardLevel,";
			    temp1 = updatefield.replaceAll(a , "");
			}
			String temp2 = temp1;
			if(StuCompetiAwardInfo.getCompetiType().trim().equals("")){
				String b = "CompetiType,";
			    temp2 = temp1.replaceAll(b , "");
			}
			System.out.println("=============================temp1:"+temp2);
			flag = DAOUtil.update(StuCompetiAwardInfo, tableName, key, temp2, conn);
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
		}finally{
			DBConnection.close(st);	
			DBConnection.close(conn);
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

	
//	/**分页查询*/
//	public List<T651_Bean> queryPageList(String conditions,String fillunitID,int pagesize, int showPage) {
//		// TODO Auto-generated method stub
//
//		String Cond = "1=1";
//		
//		if(conditions != null && !conditions.equals("")){
//			Cond = Cond + conditions;
//		}
//		
//		if(fillunitID != null && !fillunitID.equals("")){
//			Cond = Cond + " and FillUnitID=" + fillunitID;
//		}
//				
//
//		String queryPageSql = "select top " + pagesize +
//		fieldShow
//		+ " from " + tableName +
//		" where"+ Cond+" and (SeqNumber not in (select top " + pagesize * (showPage-1) + " SeqNumber from "+
//		tableName + "where"+Cond+" order by SeqNumber)) order by SeqNumber" ;
//		System.out.println(queryPageSql);
//		Connection conn = DBConnection.instance.getConnection() ;
//		Statement st = null ;
//		ResultSet rs = null ;
//		List<T651_Bean> list = null ;
//
//		try{
//			st = conn.createStatement() ;
//			rs = st.executeQuery(queryPageSql) ;
//			list = DAOUtil.getList(rs, T651_Bean.class) ;
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

	public List<T651_Bean> queryPageList(String cond, String fillUnitID,
			int pagesize, int currentpage) {
		// TODO Auto-generated method stub
		String Cond = "1=1";

		if(cond != null && !cond.equals("")){
			Cond = Cond + cond;
		}
		if(fillUnitID != null && !fillUnitID.equals("")){
			Cond = Cond + " and FillUnitID=" + fillUnitID;
		}
		String queryPageSql;
		
		queryPageSql = "select top " + pagesize +
		" SeqNumber,TeaUnit,UnitID,"+tableName1+".ContestLevel as CompetiType,"+"CompetiName,AwardItem,"+tableName2+".AwardLevel as AwardLevel,"
		+" AwardGrade,AwardFromUnit,AwardTime,AwardStuName,AwardStuNum,GuideTeaName,GuideTeaNum,Time,Note,FillUnitID,CheckState"
		+ " from " + tableName +
		" left join "+tableName1+" on "+tableName+".CompetiType="+tableName1+".IndexID "+
		"left join "+tableName2+" on "+tableName+".AwardLevel="+tableName2+".IndexID " +
		" where " + Cond +" and (SeqNumber not in (select top " + pagesize * (currentpage-1) + " SeqNumber from "+
		tableName + " where " + Cond + " order by SeqNumber)) order by SeqNumber" ;


		System.out.println(queryPageSql);
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T651_Bean> list = null ;

		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T651_Bean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}

		return list ;
	}

	/**审核数据导出*/
	public List<T651_Bean> totalList(String fillUnitID, String year, int checkState){
		String sql = null;
		
		if("111".equals(fillUnitID)){
		sql = "select SeqNumber,TeaUnit,UnitID,"+tableName1+".ContestLevel as CompetiType,"+"CompetiName,AwardItem,"+tableName2+".AwardLevel as AwardLevel,"
		+" AwardGrade,AwardFromUnit,AwardTime,AwardStuName,AwardStuNum,GuideTeaName,GuideTeaNum,Time,Note,FillUnitID,CheckState"
		+ " from " + tableName +
		" left join "+tableName1+" on "+tableName+".CompetiType="+tableName1+".IndexID "+
		"left join "+tableName2+" on "+tableName+".AwardLevel="+tableName2+".IndexID " 
		+ " where CheckState=" + checkState + " and Time like '"+year+"%'";
		}else{
			sql = "select SeqNumber,TeaUnit,UnitID,"+tableName1+".ContestLevel as CompetiType,"+"CompetiName,AwardItem,"+tableName2+".AwardLevel as AwardLevel,"
			+" AwardGrade,AwardFromUnit,AwardTime,AwardStuName,AwardStuNum,GuideTeaName,GuideTeaNum,Time,Note,FillUnitID,CheckState"
			+ " from " + tableName +
			" left join "+tableName1+" on "+tableName+".CompetiType="+tableName1+".IndexID "+
			"left join "+tableName2+" on "+tableName+".AwardLevel="+tableName2+".IndexID " 
			+ " where FillUnitID=" + "'" + fillUnitID + "'" 
			+ " and CheckState=" + checkState + " and Time like '"+year+"%'";
		}
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T651_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T651_Bean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		
		return list ;
	}

	
	/**分页查询总数*/
	public int totalQueryPageList(String conditions, String fillunitID) {
		
			
		String Cond = "1=1";
		
		int total = 0;
		if(conditions != null && !conditions.equals("")){
			Cond = Cond + conditions;
		}
		
		if(fillunitID != null && !fillunitID.equals("")){
			Cond = Cond + " and FillUnitID=" + fillunitID;
		}
				
		String queryPageSql = "select count(*) " 
		+ " from " + tableName + 
		" where " + Cond ;
		System.out.println(queryPageSql);
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			if(rs == null){
				return total ;
			}
			
			while(rs.next()){
				total = rs.getInt(1) ;
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return 0 ;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		return total ;
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
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
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
			DBConnection.close(st);	
			DBConnection.close(conn);
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
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		
		if (flag == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	
	public S65_Bean getStatic(String year){
		S65_Bean bean = new S65_Bean();
		StringBuffer sql = new StringBuffer();
		//本科生学科竞赛
		sql.append("select sum(case when t.CompetiType = '55000' then 1 else 0 end) AS SumDiscipAward, ");
		sql.append(" sum(case when t.CompetiType = '55000' and t.AwardLevel ='50000' then 1 else 0 end) AS InterD, ");
		sql.append(" sum(case when t.CompetiType = '55000' and t.AwardLevel ='50001' then 1 else 0 end) AS NationD, ");
		sql.append(" sum(case when t.CompetiType = '55000' and t.AwardLevel ='50002' then 1 else 0 end) AS ProviD, ");
		sql.append(" sum(case when t.CompetiType = '55000' and t.AwardLevel ='50003' then 1 else 0 end) AS CityD, ");
		sql.append(" sum(case when t.CompetiType = '55000' and t.AwardLevel ='50004' then 1 else 0 end) AS SchD, ");
		//本科生创新活动
		sql.append(" sum(case when t.CompetiType = '55001' then 1 else 0 end) AS SumActAward, ");
		sql.append(" sum(case when t.CompetiType = '55001' and t.AwardLevel ='50000' then 1 else 0 end) AS InterA, ");
		sql.append(" sum(case when t.CompetiType = '55001' and t.AwardLevel ='50001' then 1 else 0 end) AS NationA, ");
		sql.append(" sum(case when t.CompetiType = '55001' and t.AwardLevel ='50002' then 1 else 0 end) AS ProviA, ");
		sql.append(" sum(case when t.CompetiType = '55001' and t.AwardLevel ='50003' then 1 else 0 end) AS CityA, ");
		sql.append(" sum(case when t.CompetiType = '55001' and t.AwardLevel ='50004' then 1 else 0 end) AS SchA, ");
		//本科生文艺、体育竞赛
		sql.append("  sum(case when t.CompetiType = '55002' then 1 else 0 end) AS SumLiterSportAward, ");
		sql.append(" sum(case when t.CompetiType = '55002' and t.AwardLevel ='50000' then 1 else 0 end) AS InterLS, ");
		sql.append(" sum(case when t.CompetiType = '55002' and t.AwardLevel ='50001' then 1 else 0 end) AS NationLS, ");
		sql.append(" sum(case when t.CompetiType = '55002' and t.AwardLevel ='50002' then 1 else 0 end) AS ProviLS, ");
		sql.append(" sum(case when t.CompetiType = '55002' and t.AwardLevel ='50003' then 1 else 0 end) AS CityLS, ");
		sql.append(" sum(case when t.CompetiType = '55002' and t.AwardLevel ='50004' then 1 else 0 end) AS SchLS ");
		sql.append(" from T651_StuCompetiAwardInfo_TeaYLC$ as t, DiAwardLevel as dia,DiContestLevel as dic");
		sql.append(" where t.AwardLevel =dia.IndexID and t.CompetiType = dic.IndexID  ");
		sql.append(" and t.Time like '" + year+"%'");

		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;

		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;

			while(rs.next()){
				System.out.println(" 计数");

				int SumDiscipAward = rs.getInt("SumDiscipAward"); bean.setSumDiscipAward(SumDiscipAward);
				int InterD = rs.getInt("InterD");  bean.setInterD(InterD);
				System.out.println(InterD);
				int NationD = rs.getInt("NationD");  bean.setNationD(NationD);
				System.out.println(NationD);
				int ProviD = rs.getInt("ProviD");  bean.setProviD(ProviD);
				System.out.println(ProviD);
				int CityD = rs.getInt("CityD");  bean.setCityD(CityD);
				System.out.println(CityD);
				int SchD = rs.getInt("SchD");  bean.setSchD(SchD);

				int SumActAward = rs.getInt("SumActAward");  bean.setSumActAward(SumActAward);
				int InterA = rs.getInt("InterA");  bean.setInterA(InterA);
				int NationA = rs.getInt("NationA");  bean.setNationA(NationA);
				int ProviA = rs.getInt("ProviA");   bean.setProviA(ProviA);
				int CityA = rs.getInt("CityA");  bean.setCityA(CityA);
				int SchA= rs.getInt("SchA");  bean.setSchA(SchA);


				int SumLiterSportAward = rs.getInt("SumLiterSportAward");  bean.setSumLiterSportAward( SumLiterSportAward);
				int InterLS = rs.getInt("InterLS");  bean.setInterLS(InterLS);
				int NationLS = rs.getInt("NationLS");  bean.setNationLS(NationLS);
				int ProviLS = rs.getInt("ProviLS");  bean.setProviLS(ProviLS);
				int CityLS = rs.getInt("CityLS");  bean.setCityLS(CityLS);
				int SchLS = rs.getInt("SchLS");  bean.setSchLS(SchLS);

				bean.setTime(TimeUtil.changeDateY(year));

			}
		}catch(Exception e){
			e.printStackTrace() ;
			return null;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		return bean;
	}

	public List<T651_Bean> getYearInfo(String year){

		String sql = "select " + " " + key + "," +
		field + " from " + tableName + " where convert(varchar(4),Time,120)=" + year;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T651_Bean> list = null ;
		//T651_Bean bean = null;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T651_Bean.class) ;

		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}

		return list ;
	}
	
	//设置审核的状态为1：即未审核状态
	public boolean updatCheck()
	{
		int flag = 0;
		StringBuffer sql = new StringBuffer() ;
		sql.append("update " + tableName+" set CheckState ="+Constants.WAIT_CHECK) ;
//		sql.append(" where " + key + " in " + ids) ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		
		try
		{
			st = conn.createStatement();
			flag = st.executeUpdate(sql.toString());			
		}catch(Exception e){
			e.printStackTrace();
			return false; 
		}finally{
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		
		if (flag == 0) {
			return false;
		} else {
			return true;
		}
	}

	public static void main(String args[]) {


		T651_Dao StuCompetiAwardInfoDao = new T651_Dao();
		List<T651_Bean> list  = StuCompetiAwardInfoDao.totalList("3002", "2015", 2);
		System.out.println(list.size());
//		boolean flag = StuCompetiAwardInfoDao.updatCheck();
//		System.out.println(flag);

	}







}
