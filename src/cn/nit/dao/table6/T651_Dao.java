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
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

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
	private String field = "TeaUnit,UnitId,CompetiType,CompetiName,AwardItem,AwardLevel,AwardGrade,AwardFromUnit,AwardTime,AwardStuName,AwardStuNum,GuideTeaName,GuideTeaNum,Time,Note,FillUnitID";

	
	private String fieldShow = "SeqNumber,TeaUnit,UnitId,CompetiType,CompetiName,AwardItem,AwardLevel,AwardGrade,AwardFromUnit,AwardTime,AwardStuName,AwardStuNum,GuideTeaName,GuideTeaNum,Time,Note,FillUnitID";



	
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
		}

		return flag;
	}

	// 更新
	public boolean update(T651_Bean StuCompetiAwardInfo) {

		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection();
		try {
			flag = DAOUtil
					.update(StuCompetiAwardInfo, tableName, key, field, conn);
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
	
	public List<T651_Bean> queryPageList(int pagesize, int currentpage) {
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
		List<T651_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T651_Bean.class) ;
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
	
	public List<T651_Bean> queryPageList(String cond, Object object,
			int pagesize, int currentpage) {
		// TODO Auto-generated method stub
		String queryPageSql;
		queryPageSql = "select top " + pagesize + 
		" SeqNumber,TeaUnit,UnitID,"+tableName1+".ContestLevel as competiType,"+"CompetiName,AwardItem,"+tableName2+".AwardLevel as AwardLevel,"
		+" AwardGrade,AwardFromUnit,AwardTime,AwardStuName,AwardStuNum,GuideTeaName,GuideTeaNum,Time,Note,FillUnitID"
		+ " from " + tableName +
		" left join "+tableName1+" on "+tableName+".competiType="+tableName1+".IndexID "+
		"left join "+tableName2+" on "+tableName+".AwardLevel="+tableName2+".IndexID " +
		" where " + cond + " and (SeqNumber not in (select top " + pagesize * (currentpage-1) + " SeqNumber from "+
		tableName + " where " + cond + " order by SeqNumber)) order by SeqNumber" ;
	

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
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
		
		return list ;
	}
	
	public List<T651_Bean> getAllList() {
		// TODO Auto-generated method stub
		String sql = "select " + fieldShow + " from " + tableName ;
		System.out.println(sql);
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
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
		
		return list ;
	}
	
	public List<T651_Bean> getAllList(String cond, Object object) {
		// TODO Auto-generated method stub
		String sql;
		
		sql = "select " + fieldShow + " from " + tableName +" where " + cond;
	    System.out.println(sql);
	
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
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
		
		return list ;
	}

	public static void main(String args[]) {

		T651_Dao StuCompetiAwardInfoDao = new T651_Dao();
		T651_Bean StuCompetiAwardInfo = new T651_Bean();
//		 StuCompetiAwardInfo.setSeqNumber(1);

//	
//		StuCompetiAwardInfo.setTeaUnit("水利与生态工程学院");
//		StuCompetiAwardInfo.setUnitId("3001");
//		StuCompetiAwardInfo.setCompetiType("55000");//本科生学科竞赛
//		StuCompetiAwardInfo.setCompetiName("第九届全国周培源大学生力学竞赛");
//		StuCompetiAwardInfo.setAwardItem("第九届全国周培源大学生力学竞赛");
//		StuCompetiAwardInfo.setAwardLevel("50001");//国家级
//		StuCompetiAwardInfo.setAwardGrade("三等");
//		StuCompetiAwardInfo.setAwardFromUnit("教育部高等学校力学基础课程教学指导委员会、中国力学学会、周培源基金会");
//		StuCompetiAwardInfo.setAwardTime(new Date());
//		StuCompetiAwardInfo.setAwardStuName("龚立尧2010980000、龚如2010980000");
//		StuCompetiAwardInfo.setAwardStuNum(2);
//		StuCompetiAwardInfo.setGuideTeaName("张三200698000、李四200698002");
//		StuCompetiAwardInfo.setGuideTeaNum(2);
//		
//		StuCompetiAwardInfo.setFillUnitID("1029");
//		
//				
//		StuCompetiAwardInfo.setTime(new Date());
//		StuCompetiAwardInfo.setNote("无");
////		//		
//		StuCompetiAwardInfoDao.insert(StuCompetiAwardInfo);
		//		
		//	
		//		
		// //
		// System.out.println(underCSBaseTeaDao.auditingData("audit='1'",null,2,10).size())
		// ;
		// // System.out.println(StuCompetiAwardInfoDao.update(StuCompetiAwardInfo)) ;
//		 System.out.println(StuCompetiAwardInfoDao.deleteItemsByIds("(8)")) ;

//		System.out.println("success!!");
		List<T651_Bean> list = StuCompetiAwardInfoDao.queryPageList("1=1", null, 10, 1);
	}







}
