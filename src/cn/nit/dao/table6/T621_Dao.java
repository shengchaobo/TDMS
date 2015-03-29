package cn.nit.dao.table6;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import cn.nit.bean.table6.T621_Bean;
import cn.nit.constants.Constants;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class T621_Dao {

	/** 数据库表名 */
	private static String tableName = "T621_UndergraAdmiInfo_Admission$";

	/** 数据自增长字段的主键，必须为自增长字段 */
	private String key = "SeqNumber";

	/** 数据库表中除了自增长字段的所有字段 */
	private String field = "FromTeaUnit,UnitId,MajorName,MajorId,AmisPlanNum,ActulEnrollNum,ActulRegisterNum,AutoEnrollNum,SpecialtyEnrollNum,InProviEnrollNum,NewMajEnrollNum,Time,Note,AvgScore,CheckState";

	
	private String fieldShow = "SeqNumber,FromTeaUnit,UnitId,MajorName,MajorId,AmisPlanNum,ActulEnrollNum,ActulRegisterNum,AutoEnrollNum,SpecialtyEnrollNum,InProviEnrollNum,NewMajEnrollNum,Time,Note,AvgScore,CheckState";

	/* ,FillTeaID,FillUnitID,audit */
	
	
	
	  /**
	 * 获得当年数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T621_Bean> getYearInfo(String year){
		
		String sql = "select " + key+ "," +field + " from " + tableName 
				+ " where convert(varchar(4),Time,120)=" + year;		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T621_Bean> list = null ;
		System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T621_Bean.class) ;
			System.out.println("该年长度"+list.size());
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
	

	
	
	/**
	 * 插入数据
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */
	public boolean insert(T621_Bean bean, String year){
		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + 
		year + " and FromTeaUnit=" + "'全校合计'";	
		String sq2 = "select count(distinct FromTeaUnit) as num from "+tableName+" where Time like '"+year+"%'";
		boolean flag = false;
		boolean flag1;
		boolean flag2;
		Connection conn = DBConnection.instance.getConnection() ;		
		Statement st = null ;
		ResultSet rs = null ;
		List<T621_Bean> templist = null ;
		String tempfield = "AmisPlanNum,ActulEnrollNum,ActulRegisterNum,AutoEnrollNum,SpecialtyEnrollNum,InProviEnrollNum,NewMajEnrollNum,AvgScore";
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			//没有合计 即没有数据
			templist = DAOUtil.getList(rs, T621_Bean.class) ;
			if(templist.size() == 0){
				bean.setTime(TimeUtil.changeDateY(year));
				T621_Bean total_bean = new T621_Bean();			
				total_bean.setFromTeaUnit("全校合计");
				total_bean.setUnitId("");
				total_bean.setMajorName("");
				total_bean.setMajorId("");
				total_bean.setAmisPlanNum(bean.getAmisPlanNum());
				total_bean.setActulEnrollNum(bean.getActulEnrollNum());
				total_bean.setActulRegisterNum(bean.getActulRegisterNum());
				total_bean.setAutoEnrollNum(bean.getAutoEnrollNum());
				total_bean.setSpecialtyEnrollNum(bean.getSpecialtyEnrollNum());
				total_bean.setInProviEnrollNum(bean.getInProviEnrollNum());
				total_bean.setNewMajEnrollNum(bean.getNewMajEnrollNum());
				total_bean.setAvgScore(bean.getAvgScore());
				total_bean.setTime(TimeUtil.changeDateY(year));
				total_bean.setNote("");
				total_bean.setCheckState(Constants.WAIT_CHECK);
				flag1 = DAOUtil.insert(total_bean, tableName, field, conn);
				//重新打开数据库连接
				Connection conn1 = DBConnection.instance.getConnection() ;	
				flag2 = DAOUtil.insert(bean, tableName, field, conn1);				
			}else{
				st = conn.createStatement();
				rs = st.executeQuery(sq2);
				int sum=0;
				while(rs.next()){
					sum = rs.getInt("num")-1;
				}
				System.out.println(sum);
				bean.setTime(TimeUtil.changeDateY(year));
				T621_Bean total_bean = templist.get(0);
				total_bean.setAmisPlanNum(total_bean.getAmisPlanNum()+bean.getAmisPlanNum());
				total_bean.setActulEnrollNum(total_bean.getActulEnrollNum()+bean.getActulEnrollNum());
				total_bean.setActulRegisterNum(total_bean.getActulRegisterNum()+bean.getActulRegisterNum());
				total_bean.setAutoEnrollNum(total_bean.getAutoEnrollNum()+bean.getAutoEnrollNum());
				total_bean.setSpecialtyEnrollNum(total_bean.getSpecialtyEnrollNum()+bean.getSpecialtyEnrollNum());
				total_bean.setInProviEnrollNum(total_bean.getInProviEnrollNum()+bean.getInProviEnrollNum());
				total_bean.setNewMajEnrollNum(total_bean.getNewMajEnrollNum()+bean.getNewMajEnrollNum());
				System.out.println(total_bean.getAvgScore()+bean.getAvgScore());
				System.out.println((sum*total_bean.getAvgScore()+bean.getAvgScore())/(sum+1));
				total_bean.setAvgScore((sum*total_bean.getAvgScore()+bean.getAvgScore())/(sum+1));
				flag1 = DAOUtil.insert(bean, tableName, field, conn);
				//重新打开数据库连接
				Connection conn1 = DBConnection.instance.getConnection() ;	
				
				flag2 = DAOUtil.update(total_bean, tableName, key, tempfield, conn1);
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return false ;
		}finally{	
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		
		if(flag1==true&&flag2==true){
			flag = true;
		}
 		return flag ;
		
	}
	
	
	/**
	 * 删除数据
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */	
	public boolean deleteByIds(String ids, String year) {

		int flag = 0;
		boolean flag1 = false ;
		
		StringBuffer sql = new StringBuffer();
		sql.append("delete from " + tableName);
		sql.append(" where " + key + " in " + ids);
		
		
		String sql0 = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year + " and FromTeaUnit=" + "'全校合计'" ;	
		String sql1 = "select count(FromTeaUnit) as count,sum(amisPlanNum) AS sumamisPlanNum,sum(actulEnrollNum) AS sumactulEnrollNum, sum(actulRegisterNum)as sumactulRegisterNum," +
				"sum(autoEnrollNum)as sumautoEnrollNum, sum(specialtyEnrollNum) as sumspecialtyEnrollNum,sum(inProviEnrollNum) as suminProviEnrollNum," +
				"sum(newMajEnrollNum) as sumnewMajEnrollNum ,sum (AvgScore) as sumaveraScore from " + tableName + " where " + key + " in " + ids;
		String sql3 = "select count(FromTeaUnit) from "+tableName+" where convert(varchar(4),Time,120)=" + year;
		
		Connection conn = DBConnection.instance.getConnection();
		Connection conn1 = null;
		Statement st = null ;
		Statement st1 = null;
		Statement st2 = null;
		ResultSet rs0 = null ;
		ResultSet rs1 = null ;
		ResultSet rs3 = null;
		List<T621_Bean> templist = null ;
		
		try {
			st = conn.createStatement();
			st1 = conn.createStatement();
			st2 = conn.createStatement();;
			rs0 = st.executeQuery(sql0);
			templist = DAOUtil.getList(rs0, T621_Bean.class) ;
			T621_Bean bean = templist.get(0);
			
			rs1 = st1.executeQuery(sql1);
			rs3 = st2.executeQuery(sql3);
			int sum =0;
			while(rs3.next()){
				sum = rs3.getInt(1)-1;
			}
			System.out.println(sum);
			while(rs1.next()) {
				int count = rs1.getInt("count");
				int sumamisPlanNum = rs1.getInt("sumamisPlanNum");
				bean.setAmisPlanNum(bean.getAmisPlanNum()-sumamisPlanNum);
				int sumactulEnrollNum = rs1.getInt("sumactulEnrollNum");
				bean.setActulEnrollNum(bean.getActulEnrollNum()- sumactulEnrollNum);
				int sumactulRegisterNum = rs1.getInt("sumactulRegisterNum");
				bean.setActulRegisterNum(bean.getActulRegisterNum()-sumactulRegisterNum);
				int sumautoEnrollNum = rs1.getInt("sumautoEnrollNum");
				bean.setAutoEnrollNum(bean.getAutoEnrollNum()-sumautoEnrollNum);
				int sumspecialtyEnrollNum = rs1.getInt("sumspecialtyEnrollNum");
				bean.setSpecialtyEnrollNum(bean.getSpecialtyEnrollNum()-sumspecialtyEnrollNum);
				int suminProviEnrollNum = rs1.getInt("suminProviEnrollNum");
				bean.setInProviEnrollNum(bean.getInProviEnrollNum()-suminProviEnrollNum);
				int sumnewMajEnrollNum = rs1.getInt("sumnewMajEnrollNum");
				bean.setNewMajEnrollNum(bean.getNewMajEnrollNum()-sumnewMajEnrollNum);
				int sumaveraScore = rs1.getInt("sumaveraScore");
				bean.setAvgScore((bean.getAvgScore()*sum-sumaveraScore)/(sum-count));
			}
			
			if(bean.getAmisPlanNum() == 0 && bean.getActulEnrollNum()==0&& bean.getActulRegisterNum()==0&& bean.getAutoEnrollNum()==0&& bean.getSpecialtyEnrollNum()==0
					&& bean.getInProviEnrollNum()==0&& bean.getNewMajEnrollNum()==0){
				String sql2 = "delete from " + tableName + " where SeqNumber=" + bean.getSeqNumber();		
				int flag3 = st.executeUpdate(sql2);
				if(flag3 > 0){
					flag1 = true;
				}else{
					flag1 = false;
				}
			}else{
				if(bean.getCheckState() == Constants.NOPASS_CHECK){
					bean.setCheckState(Constants.WAIT_CHECK);
				}				
				flag1 = DAOUtil.update(bean, tableName, key, "AmisPlanNum,ActulEnrollNum,ActulRegisterNum,AutoEnrollNum,SpecialtyEnrollNum,InProviEnrollNum,NewMajEnrollNum,AvgScore,CheckState", conn);
			}

			if(flag1){
				//重新打开数据库连接
				conn1 = DBConnection.instance.getConnection() ;	
				Statement st4 = conn1.createStatement();
				flag = st4.executeUpdate(sql.toString());
			}else{
				flag = 0;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			DBConnection.close(rs3);
			DBConnection.close(st2);	
			DBConnection.close(rs1);
			DBConnection.close(st1);	
			DBConnection.close(rs0);
			DBConnection.close(st);
			DBConnection.close(conn1);
			DBConnection.close(conn);
		}

		if (flag == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	
	

	/**
	 * 讲数据批量插入T511表中
	 * 
	 * @param list
	 *            {@linkplain java.util.List<
	 *            {@link cn.nit.bean.table5.UndergraCSBaseTeaBean}>}
	 * @return true表示插入成功，false表示插入失败
	 */
	public boolean batchInsert(List<T621_Bean> list) {

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

	// /**
	// * 查询待审核数据在数据库中共有多少条
	// * @param conditions 查询条件
	// * @param fillUnitId 填报人单位号，如果为空，则查询所有未审核的数据，<br>如果不为空，则查询填报人自己单位的所有的数据
	// * @return
	// */
	// public int totalAuditingData(String conditions, String fillUnitId){
	//		
	// StringBuffer sql = new StringBuffer() ;
	// sql.append("select count(*)") ;
	// sql.append(" from " + tableName +
	// " as t,DiCourseChar csn,DiCourseCategories cst") ;
	// sql.append(" where audit!='0' and csn.IndexID=t.CSNature and cst.IndexID=t.CSType")
	// ;
	// int total = 0 ;
	//		
	// if(fillUnitId != null && !fillUnitId.equals("")){
	// sql.append(" and FillUnitID=" + fillUnitId) ;
	// }
	//		
	// if(conditions != null && !conditions.equals("")){
	// sql.append(conditions) ;
	// }
	//		
	// Connection conn = DBConnection.instance.getConnection() ;
	// Statement st = null ;
	// ResultSet rs = null ;
	//		
	// try{
	// st = conn.createStatement() ;
	// rs = st.executeQuery(sql.toString()) ;
	//			
	// if(rs == null){
	// return total ;
	// }
	//			
	// while(rs.next()){
	// total = rs.getInt(1) ;
	// }
	// }catch(Exception e){
	// e.printStackTrace() ;
	// return 0 ;
	// }
	// return total ;
	// }
	//	
	// /**
	// * @param conditions 查询条件
	// * @param fillUnitId 填报人单位号，如果为空，则查询所有未审核的数据，<br>如果不为空，则查询填报人自己单位的所有的数据
	// * @return
	// */
	// public List<UndergraCSBaseTeaPOJO> auditingData(String conditions, String
	// fillUnitId, int page, int rows){
	//		
	// StringBuffer sql = new StringBuffer() ;
	// List<UndergraCSBaseTeaPOJO> list = null ;
	// sql.append("select t.SeqNumber,t.CSName,t.CSID,t.CSUnit,t.UnitID,t.FromTeaResOffice,t.TeaResOfficeID,cst.CourseCategories as CSType,t.CSType as CSTypeID,csn.CourseChar as CSNature,t.CSNature as CSNatureID,t.State,t.PubCSType,t.Time,t.Note")
	// ;
	// sql.append(" from " + tableName +
	// " as t,DiCourseChar csn,DiCourseCategories cst") ;
	// sql.append(" where audit!='0' and csn.IndexID=t.CSNature and cst.IndexID=t.CSType")
	// ;
	// //
	// if(fillUnitId != null && !fillUnitId.equals("")){
	// sql.append(" and FillUnitID=" + fillUnitId) ;
	// }
	//		
	// if(conditions != null){
	// sql.append(conditions) ;
	// }
	//		
	// sql.append(" order by SeqNumber desc") ;
	//		
	// Connection conn = DBConnection.instance.getConnection() ;
	// Statement st = null ;
	// ResultSet rs = null ;
	//		
	// try{
	// st =
	// conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY)
	// ;
	// st.setMaxRows(page * rows) ;
	// rs = st.executeQuery(sql.toString()) ;
	// rs.absolute((page - 1) * rows) ;
	// list = DAOUtil.getList(rs, UndergraCSBaseTeaPOJO.class) ;
	//			
	// }catch(Exception e){
	// e.printStackTrace() ;
	// return null ;
	// }
	//		
	// return list ;
	// }

	
	
	/**
	 * 更新数据
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */	
	public int update(T621_Bean bean, String year){
		
		String sql0 = "select * from " + tableName + " where SeqNumber=" + bean.getSeqNumber();
		String sql1 = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year + " and FromTeaUnit=" + "'全校合计'" ;		
		String sql2 = "select count(distinct FromTeaUnit) from "+tableName+" where convert(varchar(4),Time,120)=" + year;
		int flag = 0;
		boolean flag0 = false;
		boolean flag1 = false;
		Connection conn = DBConnection.instance.getConnection() ;		
		Statement st = null ;
		ResultSet rs = null ;
		Statement st1 = null ;
		ResultSet rs1 = null ;
		Statement st2 = null;
		ResultSet rs2 = null;
		List<T621_Bean> templist = null ;
		List<T621_Bean> templist1 = null ;
		String updatefield = "FromTeaUnit,UnitId,MajorName,MajorId,AmisPlanNum,ActulEnrollNum,ActulRegisterNum,AutoEnrollNum,SpecialtyEnrollNum,InProviEnrollNum,NewMajEnrollNum,Note,AvgScore";	
		String updatefield1 = "AmisPlanNum,ActulEnrollNum,ActulRegisterNum,AutoEnrollNum,SpecialtyEnrollNum,InProviEnrollNum,NewMajEnrollNum,Note,AvgScore,CheckState";	
		
		try{
			//求编辑的那条数据
			st = conn.createStatement() ;
			rs = st.executeQuery(sql0) ;
			templist = DAOUtil.getList(rs, T621_Bean.class) ;			
			T621_Bean tempBean = templist.get(0);

			//求捐赠总计bean			
			st1 = conn.createStatement() ;
			rs1 = st1.executeQuery(sql1) ;
			templist1 = DAOUtil.getList(rs1, T621_Bean.class) ;
			T621_Bean tempBean1 = templist1.get(0);
			
			st2 = conn.createStatement();
			rs2 = st2.executeQuery(sql2);
			int sum =0 ;
			while(rs2.next()){
				sum = rs2.getInt(1)-1;
			}
			System.out.println(sum);
			if(tempBean1.getCheckState() == Constants.NOPASS_CHECK){
				tempBean1.setAmisPlanNum(tempBean1.getAmisPlanNum()+(bean.getAmisPlanNum()-tempBean.getAmisPlanNum()));
				tempBean1.setActulEnrollNum(tempBean1.getActulEnrollNum()+(bean.getActulEnrollNum()-tempBean.getActulEnrollNum()));
				tempBean1.setActulRegisterNum(tempBean1.getActulRegisterNum()+(bean.getAmisPlanNum()-tempBean.getAmisPlanNum()));
				tempBean1.setAutoEnrollNum(tempBean1.getAutoEnrollNum()+(bean.getAutoEnrollNum()-tempBean.getAutoEnrollNum()));
				tempBean1.setSpecialtyEnrollNum(tempBean1.getSpecialtyEnrollNum()+(bean.getSpecialtyEnrollNum()-tempBean.getSpecialtyEnrollNum()));
				tempBean1.setInProviEnrollNum(tempBean1.getInProviEnrollNum()+(bean.getInProviEnrollNum()-tempBean.getInProviEnrollNum()));
				tempBean1.setNewMajEnrollNum(tempBean1.getNewMajEnrollNum()+(bean.getNewMajEnrollNum()-tempBean.getNewMajEnrollNum()));	
				tempBean1.setAvgScore((tempBean1.getAvgScore()*sum+bean.getAvgScore()-tempBean.getAvgScore())/sum);
				tempBean1.setCheckState(Constants.WAIT_CHECK);
				flag0 = DAOUtil.update(bean, tableName, key, updatefield, conn) ;
				//重新打开数据库连接
				Connection conn1 = DBConnection.instance.getConnection() ;	
				flag1 = DAOUtil.update(tempBean1, tableName, key, updatefield1, conn1) ;					
				
				if(flag0&&flag1){
					flag = 2;
				}
			}else{
				tempBean1.setAmisPlanNum(tempBean1.getAmisPlanNum()+(bean.getAmisPlanNum()-tempBean.getAmisPlanNum()));
				tempBean1.setActulEnrollNum(tempBean1.getActulEnrollNum()+(bean.getActulEnrollNum()-tempBean.getActulEnrollNum()));
				tempBean1.setActulRegisterNum(tempBean1.getActulRegisterNum()+(bean.getAmisPlanNum()-tempBean.getAmisPlanNum()));
				tempBean1.setAutoEnrollNum(tempBean1.getAutoEnrollNum()+(bean.getAutoEnrollNum()-tempBean.getAutoEnrollNum()));
				tempBean1.setSpecialtyEnrollNum(tempBean1.getSpecialtyEnrollNum()+(bean.getSpecialtyEnrollNum()-tempBean.getSpecialtyEnrollNum()));
				tempBean1.setInProviEnrollNum(tempBean1.getInProviEnrollNum()+(bean.getInProviEnrollNum()-tempBean.getInProviEnrollNum()));
				tempBean1.setNewMajEnrollNum(tempBean1.getNewMajEnrollNum()+(bean.getNewMajEnrollNum()-tempBean.getNewMajEnrollNum()));			
				tempBean1.setAvgScore((tempBean1.getAvgScore()*sum+bean.getAvgScore()-tempBean.getAvgScore())/sum);
				flag0 = DAOUtil.update(bean, tableName, key, updatefield, conn) ;
				//重新打开数据库连接
				Connection conn1 = DBConnection.instance.getConnection() ;	
				flag1 = DAOUtil.update(tempBean1, tableName, key, updatefield1, conn1) ;	
				if(flag0&&flag1){
					flag = 1;
				}
			}
													
		}catch(Exception e){
			e.printStackTrace() ;
			return 0;
		}finally{
			DBConnection.close(rs2);
			DBConnection.close(st2);	
			DBConnection.close(rs1);
			DBConnection.close(st1);	
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		return flag;
	}
	
	
//	// 更新
//	public boolean update(T621_Bean UndergraAdmiInfo) {
//
//		boolean flag = false;
//		Connection conn = DBConnection.instance.getConnection();
//		try {
//			flag = DAOUtil
//					.update(UndergraAdmiInfo, tableName, key, field, conn);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return flag;
//		} finally {
//			DBConnection.close(conn);
//		}
//
//		return flag;
//	}

//	// 删除 ids应书写为"(1,2,3)"
//	public boolean deleteItemsByIds(String ids) {
//
//		int flag = 0;
//		StringBuffer sql = new StringBuffer();
//		sql.append("delete from " + tableName);
//		sql.append(" where " + key + " in " + ids);
//		Connection conn = DBConnection.instance.getConnection();
//		Statement st = null;
//
//		try {
//			st = conn.createStatement();
//			flag = st.executeUpdate(sql.toString());
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//
//		if (flag == 0) {
//			return false;
//		} else {
//			return true;
//		}
//	}
	
	
	/**
	 * 更新某条数据的审核状态
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */	
	public boolean updateCheck(String year, String FromTeaUnit, int checkState){
		
		int flag ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		String sql = "update " + tableName + " set CheckState=" + checkState +
		" where FromTeaUnit='" + FromTeaUnit + "' and convert(varchar(4),Time,120)=" + year;			
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

	public String getTableName() {
		return this.tableName;
	}
	
	public List<T621_Bean> queryPageList(int pagesize, int currentpage) {
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
		List<T621_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T621_Bean.class) ;
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
	
	public List<T621_Bean> queryPageList(String cond, Object object,
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
			" where " + Cond + " and (SeqNumber not in (select top " + pagesize * (currentpage-1) + " SeqNumber from "+
			tableName + " where " + Cond + " order by SeqNumber)) order by SeqNumber" ;
	

		System.out.println(queryPageSql);
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T621_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T621_Bean.class) ;
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
	
	public List<T621_Bean> getAllList() {
		// TODO Auto-generated method stub
		String sql = "select " + fieldShow + " from " + tableName ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T621_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T621_Bean.class) ;
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
	
	public List<T621_Bean> getAllList(String cond, Object object) {

		String Cond = "1=1";
		
		if(cond != null && !cond.equals("")){
			Cond = Cond + cond;
		}
		// TODO Auto-generated method stub
		String sql;
		
		sql = "select " + fieldShow + " from " + tableName +" where " + Cond;
	    System.out.println(sql);
	
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T621_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T621_Bean.class) ;
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
	
	/**用于教育部导出*/
	public List<T621_Bean> getAllList(String year) {

//		String Cond = "1=1";
//		
//		if(cond != null && !cond.equals("")){
//			Cond = Cond + cond;
//		}
		// TODO Auto-generated method stub
		String sql;
		
		sql = "select " + fieldShow + " from " + tableName +" where Time like '"+year+"%' and CheckState = "+Constants.PASS_CHECK; 
	    System.out.println(sql);
	
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T621_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T621_Bean.class) ;
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

	public static void main(String args[]) {
		String sql = "select count(FromTeaUnit) as count,sum(amisPlanNum) AS sumamisPlanNum,sum(actulEnrollNum) AS sumactulEnrollNum, sum(actulRegisterNum)as sumactulRegisterNum," +
				"sum(autoEnrollNum)as sumautoEnrollNum, sum(specialtyEnrollNum) as sumspecialtyEnrollNum,sum(inProviEnrollNum) as suminProviEnrollNum," +
				"sum(newMajEnrollNum) as sumnewMajEnrollNum sum (AvgScore) as sumaveraScore from " + tableName;
		System.out.println(sql);
	}







}
