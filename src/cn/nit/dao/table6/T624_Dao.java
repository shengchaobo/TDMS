package cn.nit.dao.table6;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import net.sf.json.JSON;

import cn.nit.bean.table6.T624_Bean;
import cn.nit.constants.Constants;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class T624_Dao {

	/** 数据库表名 */
	private String tableName = "T624_JuniorAdmisInfo_Admission$";

	/** 数据自增长字段的主键，必须为自增长字段 */
	private String key = "SeqNumber";

	/** 数据库表中除了自增长字段的所有字段 */

	
	private String field = "TeaUnit,UnitId,MajorName,MajorId,MajorFieldName,IsCurrentYearAdmis,PlanAdmisNum,ActualAdmisNum,ActualRegisterNum,GenHignSchNum,SecondVocationNum,OtherNum,Time,Note,CheckState";

	
	private String fieldShow = "SeqNumber,TeaUnit,UnitId,MajorName,MajorId,MajorFieldName,IsCurrentYearAdmis,PlanAdmisNum,ActualAdmisNum,ActualRegisterNum,GenHignSchNum,SecondVocationNum,OtherNum,Time,Note,CheckState";

	/* ,FillTeaID,FillUnitID,audit */
	
	
	  /**
	 * 获得当年数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T624_Bean> getYearInfo(String year){
		
		String sql = "select " + key+ "," +field + " from " + tableName 
				+ " where convert(varchar(4),Time,120)=" + year;		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T624_Bean> list = null ;
		System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T624_Bean.class) ;
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
	 * 获取字典表的所有数据(导出)
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T624_Bean> totalList(String year,int checkState){
		
		String sql = "select " + key+ "," +field + " from " + tableName 
				+ " where convert(varchar(4),Time,120)=" + year+" and CheckState="+checkState;		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T624_Bean> list = null ;
		System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T624_Bean.class) ;
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
	public boolean insert(T624_Bean bean, String year){
		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + 
		year + " and TeaUnit=" + "'全校合计'" + ";";		
		boolean flag = false;
		boolean flag1;
		boolean flag2;
		Connection conn = DBConnection.instance.getConnection() ;		
		Statement st = null ;
		ResultSet rs = null ;
		List<T624_Bean> templist = null ;
		System.out.println(sql);
		String tempfield = "PlanAdmisNum,ActualAdmisNum,ActualRegisterNum,GenHignSchNum,SecondVocationNum,OtherNum,";
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			//没有合计 即没有数据
			templist = DAOUtil.getList(rs, T624_Bean.class) ;
			if(templist.size() == 0){
				bean.setTime(TimeUtil.changeDateY(year));
				T624_Bean total_bean = new T624_Bean();			
				total_bean.setTeaUnit("全校合计");
				total_bean.setUnitId("");
				total_bean.setMajorFieldName("");
				total_bean.setMajorId("");
				total_bean.setMajorName("");
				total_bean.setIsCurrentYearAdmis(true);
				total_bean.setPlanAdmisNum(bean.getPlanAdmisNum());
				total_bean.setActualAdmisNum(bean.getActualAdmisNum());
				total_bean.setActualRegisterNum(bean.getActualRegisterNum());
				total_bean.setGenHignSchNum(bean.getGenHignSchNum());
				total_bean.setSecondVocationNum(bean.getSecondVocationNum());
				total_bean.setOtherNum(bean.getOtherNum());
				total_bean.setTime(TimeUtil.changeDateY(year));
				total_bean.setNote("");
				total_bean.setCheckState(Constants.WAIT_CHECK);
				flag1 = DAOUtil.insert(total_bean, tableName, field, conn);
				//重新打开数据库连接
				Connection conn1 = DBConnection.instance.getConnection() ;	
				flag2 = DAOUtil.insert(bean, tableName, field, conn1);				
			}else{
				bean.setTime(TimeUtil.changeDateY(year));
				T624_Bean total_bean = templist.get(0);
				total_bean.setPlanAdmisNum(total_bean.getPlanAdmisNum()+bean.getPlanAdmisNum());
				total_bean.setActualAdmisNum(total_bean.getActualAdmisNum()+bean.getActualAdmisNum());
				total_bean.setActualRegisterNum(total_bean.getActualRegisterNum()+bean.getActualRegisterNum());
				total_bean.setGenHignSchNum(total_bean.getGenHignSchNum()+bean.getGenHignSchNum());
				total_bean.setSecondVocationNum(total_bean.getSecondVocationNum()+bean.getSecondVocationNum());
				total_bean.setOtherNum(total_bean.getOtherNum()+bean.getOtherNum());
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
		//JuniorStuSumNum,JuniorOneStuNum,JuniorTwoStuNum,JuniorThreeStuNum
		
		String sql0 = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year + " and TeaUnit=" + "'全校合计'" + ";";		
		String sql1 = "select sum(planAdmisNum) AS sumplanAdmisNum, " +
				" sum(actualAdmisNum) AS sumactualAdmisNum,sum(actualRegisterNum) AS sumactualRegisterNum," +
				"sum(genHignSchNum) AS sumgenHignSchNum ,sum(secondVocationNum) AS sumsecondVocationNum, sum(otherNum) as sumotherNum from " + tableName + " where " + key + " in " + ids;
		
		Connection conn = DBConnection.instance.getConnection();
		Connection conn1 = null;
		Statement st = null ;
		Statement st1 = null;
		ResultSet rs0 = null ;
		ResultSet rs1 = null ;
		List<T624_Bean> templist = null ;
		
		try {
			st = conn.createStatement();
			rs0 = st.executeQuery(sql0);
			templist = DAOUtil.getList(rs0, T624_Bean.class) ;
			T624_Bean total_bean = templist.get(0);
//			System.out.println(total_bean.getTeaUnit());
//			System.out.println(total_bean.getJuniorStuSumNum());
//			System.out.println("+++");
			
			rs1 = st.executeQuery(sql1);
			while(rs1.next()) {
				
				int sumplanAdmisNum = rs1.getInt("sumplanAdmisNum");
				total_bean.setPlanAdmisNum(total_bean.getPlanAdmisNum()-sumplanAdmisNum);
				//System.out.println(total_bean.getJuniorStuSumNum());
				int sumactualAdmisNum = rs1.getInt("sumactualAdmisNum");
				total_bean.setActualAdmisNum(total_bean.getActualAdmisNum()-sumactualAdmisNum);
				//System.out.println(total_bean.getJuniorOneStuNum());
				int sumactualRegisterNum = rs1.getInt("sumactualRegisterNum");
				total_bean.setActualRegisterNum(total_bean.getActualRegisterNum()-sumactualRegisterNum);
				//System.out.println(total_bean.getJuniorTwoStuNum());
				int sumgenHignSchNum = rs1.getInt("sumgenHignSchNum");
				total_bean.setGenHignSchNum(total_bean.getGenHignSchNum()-sumgenHignSchNum);
				//System.out.println(total_bean.getJuniorThreeStuNum());
				int sumsecondVocationNum = rs1.getInt("sumsecondVocationNum");
				total_bean.setSecondVocationNum(total_bean.getSecondVocationNum()-sumsecondVocationNum);
				//System.out.println(total_bean.getJuniorTwoStuNum());
				int sumotherNum = rs1.getInt("sumotherNum");
				total_bean.setOtherNum(total_bean.getOtherNum()-sumotherNum);
			}
			
			if(total_bean.getPlanAdmisNum() == 0&&total_bean.getActualAdmisNum()==0&& total_bean.getActualRegisterNum()==0
					&&total_bean.getGenHignSchNum()==0&& total_bean.getSecondVocationNum()==0 && total_bean.getOtherNum() ==0){
				String sql2 = "delete from " + tableName + " where SeqNumber=" + total_bean.getSeqNumber();		
				int flag3 = st.executeUpdate(sql2);
				if(flag3 > 0){
					flag1 = true;
				}else{
					flag1 = false;
				}
			}else{
				if(total_bean.getCheckState() == Constants.NOPASS_CHECK){
					total_bean.setCheckState(Constants.WAIT_CHECK);
				}	
				String field1 = "PlanAdmisNum,ActualAdmisNum,ActualRegisterNum,GenHignSchNum,SecondVocationNum,OtherNum,CheckState";
				flag1 = DAOUtil.update(total_bean, tableName, key, field1, conn);
			}

			if(flag1){
				//重新打开数据库连接
				conn1 = DBConnection.instance.getConnection() ;	
				st1 = conn1.createStatement();
				flag = st1.executeUpdate(sql.toString());
			}else{
				flag = 0;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			DBConnection.close(rs1);
			DBConnection.close(rs0);
			DBConnection.close(st1);
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
	
	
//
//	/**
//	 * 将数据表624的实体类插入数据库
//	 * 
//	 * @param UndergraAdmiInfo
//	 * @return
//	 * 
//	 * @time: 2014-6-12
//	 */
//	public boolean insert(T624_Bean JuniorAdmisInfo) {
//
//		// flag判断数据是否插入成功
//		boolean flag = false;
//		Connection conn = DBConnection.instance.getConnection();
//		try {
//			flag = DAOUtil.insert(JuniorAdmisInfo, tableName, field, conn);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return flag;
//		} finally {
//			DBConnection.close(conn);
//		}
//		return flag;
//	}

	/**
	 * 讲数据批量插入T511表中
	 * 
	 * @param list
	 *            {@linkplain java.util.List<
	 *            {@link cn.nit.bean.table5.UndergraCSBaseTeaBean}>}
	 * @return true表示插入成功，false表示插入失败
	 */
	public boolean batchInsert(List<T624_Bean> list) {

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
	public int update(T624_Bean bean, String year){
		String sql0 = "select * from " + tableName + " where SeqNumber=" + bean.getSeqNumber();
		String sql1 = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year + " and TeaUnit=" + "'全校合计'" + ";";		
		int flag = 0;
		boolean flag0 = false;
		boolean flag1 = false;
		Connection conn = DBConnection.instance.getConnection() ;	
		Connection conn1 = null;
		Statement st = null ;
		ResultSet rs = null ;
		Statement st1 = null ;
		ResultSet rs1 = null ;
		List<T624_Bean> templist = null ;
		List<T624_Bean> templist1 = null ;
//		"TeaUnit,UnitId,MajorName,MajorId,MajorFieldName," +
//		"JuniorStuSumNum,JuniorOneStuNum,JuniorTwoStuNum,JuniorThreeStuNum,Time,Note,CheckState";
		String updatefield = "TeaUnit,UnitId,MajorName,MajorId,MajorFieldName,IsCurrentYearAdmis,PlanAdmisNum,ActualAdmisNum,ActualRegisterNum,GenHignSchNum,SecondVocationNum,OtherNum,Note";	
		String updatefield1 = "IsCurrentYearAdmis,PlanAdmisNum,ActualAdmisNum,ActualRegisterNum,GenHignSchNum,SecondVocationNum,OtherNum,Note,CheckState";	
		
		try{
			//求编辑的那条数据
			st = conn.createStatement() ;
			rs = st.executeQuery(sql0) ;
			templist = DAOUtil.getList(rs, T624_Bean.class) ;			
			T624_Bean tempBean = templist.get(0);

			//求捐赠总计bean			
			st1 = conn.createStatement() ;
			rs1 = st1.executeQuery(sql1) ;
			templist1 = DAOUtil.getList(rs1, T624_Bean.class) ;
			T624_Bean tempBean1 = templist1.get(0);
			
			//总计
			if(tempBean1.getCheckState() == Constants.NOPASS_CHECK){
				tempBean1.setPlanAdmisNum(tempBean1.getPlanAdmisNum()+(bean.getPlanAdmisNum()-tempBean.getPlanAdmisNum()));//总计+变化的量
				tempBean1.setActualAdmisNum(tempBean1.getActualAdmisNum()+(bean.getActualAdmisNum()-tempBean.getActualAdmisNum()));
				tempBean1.setActualRegisterNum(tempBean1.getActualRegisterNum()+(bean.getActualRegisterNum()-tempBean.getActualRegisterNum()));
				tempBean1.setGenHignSchNum(tempBean1.getGenHignSchNum()+(bean.getGenHignSchNum()-tempBean.getGenHignSchNum()));
				tempBean1.setSecondVocationNum(tempBean1.getSecondVocationNum()+(bean.getSecondVocationNum()-tempBean.getSecondVocationNum()));
				tempBean1.setOtherNum(tempBean1.getOtherNum()+(bean.getOtherNum()-tempBean.getOtherNum()));
				tempBean1.setCheckState(Constants.WAIT_CHECK);
				flag0 = DAOUtil.update(bean, tableName, key, updatefield, conn) ;
				//重新打开数据库连接
				conn1 = DBConnection.instance.getConnection() ;	
				flag1 = DAOUtil.update(tempBean1, tableName, key, updatefield1, conn1) ;					
				
				if(flag0&&flag1){
					flag = 2;
				}
			}else{
				tempBean1.setPlanAdmisNum(tempBean1.getPlanAdmisNum()+(bean.getPlanAdmisNum()-tempBean.getPlanAdmisNum()));//总计+变化的量
				tempBean1.setActualAdmisNum(tempBean1.getActualAdmisNum()+(bean.getActualAdmisNum()-tempBean.getActualAdmisNum()));
				tempBean1.setActualRegisterNum(tempBean1.getActualRegisterNum()+(bean.getActualRegisterNum()-tempBean.getActualRegisterNum()));
				tempBean1.setGenHignSchNum(tempBean1.getGenHignSchNum()+(bean.getGenHignSchNum()-tempBean.getGenHignSchNum()));
				tempBean1.setSecondVocationNum(tempBean1.getSecondVocationNum()+(bean.getSecondVocationNum()-tempBean.getSecondVocationNum()));
				tempBean1.setOtherNum(tempBean1.getOtherNum()+(bean.getOtherNum()-tempBean.getOtherNum()));
				flag0 = DAOUtil.update(bean, tableName, key, updatefield, conn) ;
				//重新打开数据库连接
				conn1 = DBConnection.instance.getConnection() ;	
				flag1 = DAOUtil.update(tempBean1, tableName, key, updatefield1, conn1) ;	
				if(flag0&&flag1){
					flag = 1;
				}
			}
													
		}catch(Exception e){
			e.printStackTrace() ;
			return 0;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(rs1);	
			DBConnection.close(st);
			DBConnection.close(st1);
			DBConnection.close(conn1);	
			DBConnection.close(conn);
		}
		return flag;
	}

	/**
	 * 更新某条数据的审核状态
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */	
	public boolean updateCheck(String year, String TeaUnit, int checkState){
		
		int flag ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		String sql = "update " + tableName + " set CheckState=" + checkState +
		" where TeaUnit='" + TeaUnit + "' and convert(varchar(4),Time,120)=" + year;			
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
	
	public List<T624_Bean> queryPageList(int pagesize, int currentpage) {
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
		List<T624_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T624_Bean.class) ;
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
	
	
	public List<T624_Bean> queryPageList(String cond, Object object,
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
		List<T624_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T624_Bean.class) ;
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
	
	public List<T624_Bean> getAllList(String cond, Object object) {
		
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
		List<T624_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T624_Bean.class) ;
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
	
	public List<T624_Bean> getAllList() {
		// TODO Auto-generated method stub
		String sql = "select " + fieldShow + " from " + tableName ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T624_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T624_Bean.class) ;
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
	
	






}
