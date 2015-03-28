package cn.nit.dao.table6;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import net.sf.json.JSON;



import cn.nit.bean.table6.T611_Bean;
import cn.nit.bean.table6.T617_Bean;
import cn.nit.bean.table6.T621_Bean;
import cn.nit.constants.Constants;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class T617_Dao {

	/** 数据库表名 */
	private String tableName = "T617_JuniorInSchStuInfo_Tea$";

	/** 数据自增长字段的主键，必须为自增长字段 */
	private String key = "SeqNumber";

	/** 数据库表中除了自增长字段的所有字段 */
	private String field = "TeaUnit,UnitId,MajorName,MajorId,MajorFieldName," +
			"JuniorStuSumNum,JuniorOneStuNum,JuniorTwoStuNum,JuniorThreeStuNum,Time,Note,CheckState";

	
	private String fieldShow = "SeqNumber,TeaUnit,UnitId,MajorName,MajorId," +
			"MajorFieldName,JuniorStuSumNum,JuniorOneStuNum,JuniorTwoStuNum," +
			"JuniorThreeStuNum,Time,Note,CheckState";

	
	  /**
	 * 获得当年数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T617_Bean> getYearInfo(String year){
		
		String sql = "select " + key+ "," +field + " from " + tableName 
				+ " where convert(varchar(4),Time,120)=" + year;		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T617_Bean> list = null ;
		//System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T617_Bean.class) ;
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
	public List<T617_Bean> totalList(String year){
		
		String sql = "select " + key+ "," +field + " from " + tableName 
				+ " where convert(varchar(4),Time,120)=" + year;		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T617_Bean> list = null ;
		System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T617_Bean.class) ;
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
	public boolean insert(T617_Bean bean, String year){
		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + 
		year + " and TeaUnit=" + "'全校合计'" + ";";		
		boolean flag = false;
		boolean flag1;
		boolean flag2;
		Connection conn = DBConnection.instance.getConnection() ;		
		Statement st = null ;
		ResultSet rs = null ;
		List<T617_Bean> templist = null ;
		System.out.println(sql);
		String tempfield = "JuniorStuSumNum,JuniorOneStuNum,JuniorTwoStuNum,JuniorThreeStuNum";
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			//没有合计 即没有数据
			templist = DAOUtil.getList(rs, T617_Bean.class) ;
			if(templist.size() == 0){
				bean.setTime(TimeUtil.changeDateY(year));
				T617_Bean total_bean = new T617_Bean();			
				total_bean.setTeaUnit("全校合计");
				total_bean.setUnitId("0000");
				total_bean.setMajorFieldName("");
				total_bean.setMajorId("");
				total_bean.setMajorName("");
				total_bean.setJuniorOneStuNum(bean.getJuniorOneStuNum());
				total_bean.setJuniorStuSumNum(bean.getJuniorStuSumNum());
				total_bean.setJuniorThreeStuNum(bean.getJuniorThreeStuNum());
				total_bean.setJuniorTwoStuNum(bean.getJuniorTwoStuNum());
				total_bean.setTime(TimeUtil.changeDateY(year));
				total_bean.setNote("");
				total_bean.setCheckState(Constants.WAIT_CHECK);
				flag1 = DAOUtil.insert(total_bean, tableName, field, conn);
				//重新打开数据库连接
				Connection conn1 = DBConnection.instance.getConnection() ;	
				flag2 = DAOUtil.insert(bean, tableName, field, conn1);				
			}else{
				bean.setTime(TimeUtil.changeDateY(year));
				T617_Bean total_bean = templist.get(0);
				total_bean.setJuniorOneStuNum(total_bean.getJuniorOneStuNum()+bean.getJuniorOneStuNum());
				total_bean.setJuniorStuSumNum(total_bean.getJuniorStuSumNum()+bean.getJuniorStuSumNum());
				total_bean.setJuniorThreeStuNum(total_bean.getJuniorThreeStuNum()+bean.getJuniorThreeStuNum());
				total_bean.setJuniorTwoStuNum(total_bean.getJuniorTwoStuNum()+bean.getJuniorTwoStuNum());
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
		String sql1 = "select sum(JuniorStuSumNum) AS SumJuniorStuSumNum, " +
				" sum(JuniorOneStuNum) AS SumJuniorOneStuNum,sum(JuniorTwoStuNum) AS SumJuniorTwoStuNum," +
				"sum(JuniorThreeStuNum) AS SumJuniorThreeStuNum from " + tableName + " where " + key + " in " + ids;
		
		Connection conn = DBConnection.instance.getConnection();
		Statement st = null ;
		ResultSet rs0 = null ;
		ResultSet rs1 = null ;
		List<T617_Bean> templist = null ;
		
		try {
			st = conn.createStatement();
			rs0 = st.executeQuery(sql0);
			templist = DAOUtil.getList(rs0, T617_Bean.class) ;
			T617_Bean total_bean = templist.get(0);
//			System.out.println(total_bean.getTeaUnit());
//			System.out.println(total_bean.getJuniorStuSumNum());
//			System.out.println("+++");
			
			rs1 = st.executeQuery(sql1);
			while(rs1.next()) {
				
				int SumJuniorStuSumNum = rs1.getInt("SumJuniorStuSumNum");
				total_bean.setJuniorStuSumNum(total_bean.getJuniorStuSumNum()-SumJuniorStuSumNum);
				//System.out.println(total_bean.getJuniorStuSumNum());
				int SumJuniorOneStuNum = rs1.getInt("SumJuniorOneStuNum");
				total_bean.setJuniorOneStuNum(total_bean.getJuniorOneStuNum()-SumJuniorOneStuNum);
				//System.out.println(total_bean.getJuniorOneStuNum());
				int SumJuniorTwoStuNum = rs1.getInt("SumJuniorTwoStuNum");
				total_bean.setJuniorTwoStuNum(total_bean.getJuniorTwoStuNum()-SumJuniorTwoStuNum);
				//System.out.println(total_bean.getJuniorTwoStuNum());
				int SumJuniorThreeStuNum = rs1.getInt("SumJuniorThreeStuNum");
				total_bean.setJuniorThreeStuNum(total_bean.getJuniorThreeStuNum()-SumJuniorThreeStuNum);
				//System.out.println(total_bean.getJuniorThreeStuNum());
			}
			
			if(total_bean.getJuniorStuSumNum() == 0  ){
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
				flag1 = DAOUtil.update(total_bean, tableName, key, "JuniorStuSumNum,JuniorOneStuNum," +
						"JuniorTwoStuNum,JuniorThreeStuNum,CheckState", conn);
			}

			if(flag1){
				//重新打开数据库连接
				Connection conn1 = DBConnection.instance.getConnection() ;	
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
	public int update(T617_Bean bean, String year){
		System.out.println("haha"+bean.getTeaUnit());
		System.out.println("haha"+bean.getUnitId());
		String sql0 = "select * from " + tableName + " where SeqNumber=" + bean.getSeqNumber();
		String sql1 = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year + " and TeaUnit=" + "'全校合计'" + ";";		
		int flag = 0;
		boolean flag0 = false;
		boolean flag1 = false;
		Connection conn = DBConnection.instance.getConnection() ;		
		Statement st = null ;
		ResultSet rs = null ;
		Statement st1 = null ;
		ResultSet rs1 = null ;
		List<T617_Bean> templist = null ;
		List<T617_Bean> templist1 = null ;
//		"TeaUnit,UnitId,MajorName,MajorId,MajorFieldName," +
//		"JuniorStuSumNum,JuniorOneStuNum,JuniorTwoStuNum,JuniorThreeStuNum,Time,Note,CheckState";
		String updatefield = "TeaUnit,UnitId,MajorName,MajorId,MajorFieldName,JuniorStuSumNum,JuniorOneStuNum,JuniorTwoStuNum,JuniorThreeStuNum,Note";	
		String updatefield1 = "MajorName,MajorId,MajorFieldName,JuniorStuSumNum,JuniorOneStuNum,JuniorTwoStuNum,JuniorThreeStuNum,Note,CheckState";	
		
		try{
			//求编辑的那条数据
			st = conn.createStatement() ;
			rs = st.executeQuery(sql0) ;
			templist = DAOUtil.getList(rs, T617_Bean.class) ;			
			T617_Bean tempBean = templist.get(0);

			//求捐赠总计bean			
			st1 = conn.createStatement() ;
			rs1 = st1.executeQuery(sql1) ;
			templist1 = DAOUtil.getList(rs1, T617_Bean.class) ;
			T617_Bean tempBean1 = templist1.get(0);
			
			//总计
			if(tempBean1.getCheckState() == Constants.NOPASS_CHECK){
				tempBean1.setJuniorStuSumNum(tempBean1.getJuniorStuSumNum()+(bean.getJuniorStuSumNum()-tempBean.getJuniorStuSumNum()));//总计+变化的量
				tempBean1.setJuniorOneStuNum(tempBean1.getJuniorOneStuNum()+(bean.getJuniorOneStuNum()-tempBean.getJuniorOneStuNum()));
				tempBean1.setJuniorTwoStuNum(tempBean1.getJuniorTwoStuNum()+(bean.getJuniorTwoStuNum()-tempBean.getJuniorTwoStuNum()));
				tempBean1.setJuniorThreeStuNum(tempBean1.getJuniorThreeStuNum()+(bean.getJuniorThreeStuNum()-tempBean.getJuniorThreeStuNum()));
				tempBean1.setCheckState(Constants.WAIT_CHECK);
				flag0 = DAOUtil.update(bean, tableName, key, updatefield, conn) ;
				//重新打开数据库连接
				Connection conn1 = DBConnection.instance.getConnection() ;	
				flag1 = DAOUtil.update(tempBean1, tableName, key, updatefield1, conn1) ;					
				
				if(flag0&&flag1){
					flag = 2;
				}
			}else{
				tempBean1.setJuniorStuSumNum(tempBean1.getJuniorStuSumNum()+(bean.getJuniorStuSumNum()-tempBean.getJuniorStuSumNum()));
				tempBean1.setJuniorOneStuNum(tempBean1.getJuniorOneStuNum()+(bean.getJuniorOneStuNum()-tempBean.getJuniorOneStuNum()));
				tempBean1.setJuniorTwoStuNum(tempBean1.getJuniorTwoStuNum()+(bean.getJuniorTwoStuNum()-tempBean.getJuniorTwoStuNum()));
				tempBean1.setJuniorThreeStuNum(tempBean1.getJuniorThreeStuNum()+(bean.getJuniorThreeStuNum()-tempBean.getJuniorThreeStuNum()));
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
			DBConnection.close(rs);
			DBConnection.close(st);
			DBConnection.close(rs1);
			DBConnection.close(st1);	
			DBConnection.close(conn);
		}
		return flag;
	}
	
	public int getYearSumDona(String year){
		
		String sql = "select JuniorStuSumNum from " + tableName + " where convert(varchar(4),Time,120)=" + 
		year + " and TeaUnit=" + "'全校合计'" + ";";			
		Connection conn = DBConnection.instance.getConnection();
		Statement st = null;
		ResultSet rs = null;
		int JuniorStuSumNum = 0;
//		int JuniorOneStuNum = 0;
//		int JuniorTwoStuNum = 0;
//		int JuniorThreeStuNum = 0;
		try{
			
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				JuniorStuSumNum = rs.getInt("JuniorStuSumNum");
//				JuniorOneStuNum = rs.getInt("JuniorOneStuNum");
//				JuniorTwoStuNum = rs.getInt("JuniorTwoStuNum");
//				JuniorThreeStuNum = rs.getInt("JuniorThreeStuNum");
			}
						
		}catch(Exception e){
			e.printStackTrace() ;
			return 0;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		return JuniorStuSumNum;
	}
	
	/**
	 * 更新某条数据的审核状态
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */	
	public boolean updateCheck(String year, String unitName, int checkState){
		
		int flag ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		String sql = "update " + tableName + " set CheckState=" + checkState +
		" where TeaUnit='" + unitName + "' and convert(varchar(4),Time,120)=" + year;			
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
	public boolean batchInsert(List<T617_Bean> list) {

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
	
	public List<T617_Bean> queryPageList(int pagesize, int currentpage) {
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
		List<T617_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T617_Bean.class) ;
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
	
	public List<T617_Bean> queryPageList(String cond, Object object,
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
		List<T617_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T617_Bean.class) ;
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
	
	public List<T617_Bean> getAllList() {
		// TODO Auto-generated method stub
		String sql = "select " + fieldShow + " from " + tableName ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T617_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T617_Bean.class) ;
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
	
	public List<T617_Bean> getAllList(String cond, Object object) {
		
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
		List<T617_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T617_Bean.class) ;
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
	
	public boolean deleteAll(){
		int flag = 0;
		String sql ="delete from "+tableName;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		try{
			st = conn.createStatement();
			flag = st.executeUpdate(sql);
			
		}catch(SQLException e){
			e.printStackTrace() ;
			return false ;
		}finally{
			DBConnection.close(st);	
			DBConnection.close(conn);
		}	
		if(flag == 0){
			return false ;
		}else{
			return true ;
		}	
	}
	

	public static void main(String args[]) {

		T617_Dao JuniorInSchStuInfoDao = new T617_Dao();
		boolean flag = JuniorInSchStuInfoDao.deleteAll();
		System.out.println(flag);
	}







}
