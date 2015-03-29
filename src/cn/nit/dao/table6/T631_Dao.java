package cn.nit.dao.table6;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import cn.nit.bean.table6.T631_Bean;
import cn.nit.bean.table6.T632_Bean;
import cn.nit.constants.Constants;
import cn.nit.dbconnection.DBConnection;
import cn.nit.pojo.table6.T631POJO;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class T631_Dao {

	/** 数据库表名 */
	private String tableName = "T631_UndergarGraduateInfo_Tea$";

	/** 数据自增长字段的主键，必须为自增长字段 */
	private String key = "SeqNumber";

	/** 数据库表中除了自增长字段的所有字段 */
	
	 

	
	private String field = "TeaUnit,UnitId,MajorName,MajorId,ThisYearGraduNum,ThisYearNotGraduNum,AwardDegreeNum,Time,Note,CheckState";

	
	private String fieldShow = "SeqNumber,TeaUnit,UnitId,MajorName,MajorId,ThisYearGraduNum,ThisYearNotGraduNum,AwardDegreeNum,Time,Note,CheckState";

	/* ,FillTeaID,FillUnitID,audit */
	
    /**
	 * 获得当年数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T631_Bean> getYearInfo(String year){
		
		String sql = "select " + key+ "," +field + " from " + tableName 
				+ " where convert(varchar(4),Time,120)=" + year;		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T631_Bean> list = null ;
		//System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T631_Bean.class) ;
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
	public boolean insert(T631_Bean bean, String year){
		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year + " and TeaUnit=" + "'全校合计'" + ";";		
		boolean flag = false;
		boolean flag1;
		boolean flag2;
		Connection conn = DBConnection.instance.getConnection() ;		
		Statement st = null ;
		ResultSet rs = null ;
		List<T631_Bean> templist = null ;
		System.out.println(sql);
		String tempfield = "ThisYearGraduNum,ThisYearNotGraduNum,AwardDegreeNum";
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			templist = DAOUtil.getList(rs, T631_Bean.class) ;
			if(templist.size() == 0){
				bean.setTime(TimeUtil.changeDateY(year));
				T631_Bean T631_Bean = new T631_Bean();			
				T631_Bean.setTeaUnit("全校合计");
				T631_Bean.setUnitId("");
				T631_Bean.setMajorName("");
				T631_Bean.setMajorId("");
				T631_Bean.setThisYearGraduNum(bean.getThisYearGraduNum());
				T631_Bean.setThisYearNotGraduNum(bean.getThisYearNotGraduNum());
				T631_Bean.setAwardDegreeNum(bean.getAwardDegreeNum());
				T631_Bean.setTime(TimeUtil.changeDateY(year));
				T631_Bean.setNote("");
				T631_Bean.setCheckState(Constants.WAIT_CHECK);
				flag1 = DAOUtil.insert(T631_Bean, tableName, field, conn);
				//重新打开数据库连接
				Connection conn1 = DBConnection.instance.getConnection() ;	
				flag2 = DAOUtil.insert(bean, tableName, field, conn1);				
			}else{
				bean.setTime(TimeUtil.changeDateY(year));
				T631_Bean T631_Bean = templist.get(0);
				T631_Bean.setThisYearGraduNum(T631_Bean.getThisYearGraduNum()+bean.getThisYearGraduNum());
				T631_Bean.setThisYearNotGraduNum(T631_Bean.getThisYearNotGraduNum()+bean.getThisYearNotGraduNum());
				T631_Bean.setAwardDegreeNum(T631_Bean.getAwardDegreeNum()+bean.getAwardDegreeNum());
				flag1 = DAOUtil.insert(bean, tableName, field, conn);
				//重新打开数据库连接
				Connection conn1 = DBConnection.instance.getConnection() ;	
				flag2 = DAOUtil.update(T631_Bean, tableName, key, tempfield, conn1);
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
		String sql1 = "select  " +
				" sum(thisYearGraduNum) AS sumthisYearGraduNum,sum(thisYearNotGraduNum) AS sumthisYearNotGraduNum," +
				"sum(awardDegreeNum) AS sumawardDegreeNum  from " + tableName + " where " + key + " in " + ids;
		
		Connection conn = DBConnection.instance.getConnection();
		Connection conn1 = null;
		Statement st = null ;
		ResultSet rs0 = null ;
		ResultSet rs1 = null ;
		List<T631_Bean> templist = null ;
		
		try {
			st = conn.createStatement();
			rs0 = st.executeQuery(sql0);
			templist = DAOUtil.getList(rs0, T631_Bean.class) ;
			T631_Bean total_bean = templist.get(0);
//			System.out.println(total_bean.getTeaUnit());
//			System.out.println(total_bean.getJuniorStuSumNum());
//			System.out.println("+++");
			
			rs1 = st.executeQuery(sql1);
			while(rs1.next()) {
				
				int sumthisYearGraduNum = rs1.getInt("sumthisYearGraduNum");
				total_bean.setThisYearGraduNum(total_bean.getThisYearGraduNum()-sumthisYearGraduNum);
				//System.out.println(total_bean.getJuniorStuSumNum());
				int sumthisYearNotGraduNum = rs1.getInt("sumthisYearNotGraduNum");
				total_bean.setThisYearNotGraduNum(total_bean.getThisYearNotGraduNum()-sumthisYearNotGraduNum);
				//System.out.println(total_bean.getJuniorOneStuNum());
				int sumawardDegreeNum = rs1.getInt("sumawardDegreeNum");
				total_bean.setAwardDegreeNum(total_bean.getAwardDegreeNum()-sumawardDegreeNum);
			}
			
			if(total_bean.getThisYearGraduNum() == 0&&total_bean.getThisYearNotGraduNum()==0&& total_bean.getAwardDegreeNum()==0){
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
				String field1 = "ThisYearGraduNum,ThisYearNotGraduNum,AwardDegreeNum,CheckState";
				flag1 = DAOUtil.update(total_bean, tableName, key, field1, conn);
			}

			if(flag1){
				//重新打开数据库连接
				conn1 = DBConnection.instance.getConnection() ;	
				Statement st1 = conn1.createStatement();
				flag = st1.executeUpdate(sql.toString());
			}else{
				flag = 0;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			DBConnection.close(rs0);
			DBConnection.close(rs1);
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
	 * 更新数据
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */	
	public int update(T631_Bean bean, String year){
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
		List<T631_Bean> templist = null ;
		List<T631_Bean> templist1 = null ;
//		"TeaUnit,UnitId,MajorName,MajorId,MajorFieldName," +
//		"JuniorStuSumNum,JuniorOneStuNum,JuniorTwoStuNum,JuniorThreeStuNum,Time,Note,CheckState";
		String updatefield = "TeaUnit,UnitId,MajorName,MajorId,ThisYearGraduNum,ThisYearNotGraduNum,AwardDegreeNum,Note";	
		String updatefield1 = "ThisYearGraduNum,ThisYearNotGraduNum,AwardDegreeNum,CheckState";	
		
		try{
			//求编辑的那条数据
			st = conn.createStatement() ;
			rs = st.executeQuery(sql0) ;
			templist = DAOUtil.getList(rs, T631_Bean.class) ;			
			T631_Bean tempBean = templist.get(0);

			//求捐赠总计bean			
			st1 = conn.createStatement() ;
			rs1 = st1.executeQuery(sql1) ;
			templist1 = DAOUtil.getList(rs1, T631_Bean.class) ;
			T631_Bean tempBean1 = templist1.get(0);
			
			//总计
			if(tempBean1.getCheckState() == Constants.NOPASS_CHECK){
				tempBean1.setThisYearGraduNum(tempBean1.getThisYearGraduNum()+(bean.getThisYearGraduNum()-tempBean.getThisYearGraduNum()));//总计+变化的量
				tempBean1.setThisYearNotGraduNum(tempBean1.getThisYearNotGraduNum()+(bean.getThisYearNotGraduNum()-tempBean.getThisYearNotGraduNum()));
				tempBean1.setAwardDegreeNum(tempBean1.getAwardDegreeNum()+(bean.getAwardDegreeNum()-tempBean.getAwardDegreeNum()));
				tempBean1.setCheckState(Constants.WAIT_CHECK);
				flag0 = DAOUtil.update(bean, tableName, key, updatefield, conn) ;
				//重新打开数据库连接
				conn1 = DBConnection.instance.getConnection() ;	
				flag1 = DAOUtil.update(tempBean1, tableName, key, updatefield1, conn1) ;					
				
				if(flag0&&flag1){
					flag = 2;
				}
			}else{
				tempBean1.setThisYearGraduNum(tempBean1.getThisYearGraduNum()+(bean.getThisYearGraduNum()-tempBean.getThisYearGraduNum()));//总计+变化的量
				tempBean1.setThisYearNotGraduNum(tempBean1.getThisYearNotGraduNum()+(bean.getThisYearNotGraduNum()-tempBean.getThisYearNotGraduNum()));
				tempBean1.setAwardDegreeNum(tempBean1.getAwardDegreeNum()+(bean.getAwardDegreeNum()-tempBean.getAwardDegreeNum()));
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
			DBConnection.close(rs1);
			DBConnection.close(rs);
			DBConnection.close(st1);
			DBConnection.close(st);
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
	

	/**
	 * 讲数据批量插入T511表中
	 * 
	 * @param list
	 *            {@linkplain java.util.List<
	 *            {@link cn.nit.bean.table5.UndergraCSBaseTeaBean}>}
	 * @return true表示插入成功，false表示插入失败
	 */
	public boolean batchInsert(List<T631_Bean> list) {

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

	public String getTableName() {
		return this.tableName;
	}
	
	public List<T631_Bean> queryPageList(int pagesize, int currentpage) {
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
		List<T631_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T631_Bean.class) ;
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
	
	
	/**教育部导出（需要T631，T632连接）*/
	public List<T631POJO> getAllList(String year) {
		// TODO Auto-generated method stub
		
		StringBuffer sql = new StringBuffer();
		sql.append("select "+tableName+".TeaUnit,"+tableName+".UnitID,"+tableName+".MajorName,"+tableName+".MajorID,"+
				tableName+".ThisYearGraduNum,"+tableName+".ThisYearNotGraduNum,"+tableName+".AwardDegreeNum,T632_GraStuEmployInfo_Admission$.SumEmployNum");
		sql.append(" from "+tableName+" left join T632_GraStuEmployInfo_Admission$ on "+tableName+".MajorID = T632_GraStuEmployInfo_Admission$.MajorID");
		sql.append(" where "+tableName+".MajorID = T632_GraStuEmployInfo_Admission$.MajorID");
		sql.append(" and "+tableName+".Time like '"+year+"%' and T632_GraStuEmployInfo_Admission$.Time like '"+year+"%'");
//		sql.append(" and a.Time like '"+year+"%' and b.Time like '"+year+"%'");
		
		System.out.println(sql.toString());
//		String sql = "select " + fieldShow + " from " + tableName ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T631POJO> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, T631POJO.class) ;
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
	
	public List<T631_Bean> queryPageList(String cond, Object object,
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
		List<T631_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T631_Bean.class) ;
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
	public List<T631_Bean> totalList(String year){
		
		String sql = "select " + key+ "," +field + " from " + tableName 
				+ " where convert(varchar(4),Time,120)=" + year;		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T631_Bean> list = null ;
		System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T631_Bean.class) ;
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
	
	
	
	public List<T631_Bean> getAllList(String cond, Object object) {
		// TODO Auto-generated method stub
		String Cond = "1=1";
		
		if(cond != null && !cond.equals("")){
			Cond = Cond + cond;
		}
		String sql;
		
		sql = "select " + fieldShow + " from " + tableName +" where " + Cond;
	    System.out.println(sql);
	
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T631_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T631_Bean.class) ;
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
	 * @param 查询某年的合计信息
	 * @return
	 */
	public T631_Bean getYearInfo(String year,String teaUnit){
		
		StringBuffer sql = new StringBuffer() ;
		List<T631_Bean> list = null ;
		sql.append("select * from "+ tableName);
		sql.append(" where TeaUnit='" + teaUnit + "' and Time like '"+year+"%'");
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			list = DAOUtil.getList(rs, T631_Bean.class) ;
			if(list.size()!=0){
				T631_Bean bean = list.get(0);
				return bean;
			}else{
				return null;
			}
			
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
	}
	



}
