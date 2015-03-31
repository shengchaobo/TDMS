package cn.nit.dao.table6;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import net.sf.json.JSON;


import cn.nit.bean.table2.T285_Bean;
import cn.nit.bean.table6.T659_Bean;
import cn.nit.constants.Constants;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class T659_Dao {

	/** 数据库表名 */
	private String tableName = "T659_StuExchangeInfo_TeaInter$";

	/** 数据自增长字段的主键，必须为自增长字段 */
	private String key = "SeqNumber";

	/** 数据库表中除了自增长字段的所有字段 */
	private String field = "TeaUnit,UnitId,ExchangeStuSum,FromSchToOverseas,FromSchToDomestic,FromDomesticToSch," +
			"FromOverseasToSch,Time,Note,FillUnitID,CheckState";

	
	private String fieldShow = "SeqNumber,TeaUnit,UnitId,ExchangeStuSum,FromSchToOverseas,FromSchToDomestic," +
			"FromDomesticToSch,FromOverseasToSch,Time,Note,FillUnitID,CheckState";


	
	
	/**
	 * 获得当年数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T659_Bean> getYearInfo(String year){
		
		String sql = "select " + key + "," + field + " from " + tableName 
				+ " where convert(varchar(4),Time,120)=" + year;		
		Connection conn = DBConnection.instance.getConnection() ;
		Connection conn1 = null;
		Statement st = null ;
		ResultSet rs = null ;
		List<T659_Bean> list = null ;
		System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T659_Bean.class) ;
			
			//如果当前年表中没有单位列数据，先将单位列数据插入到表中
			if(list.size() == 0){
				String sql1 = "select " + key + "," + "UnitName AS TeaUnit,DiDepartment.UnitID,ExchangeStuSum,FromSchToOverseas," +
						"FromSchToDomestic,FromDomesticToSch,FromOverseasToSch,Time,FillUnitID,CheckState," + tableName + ".Note" +
						" from DiDepartment" +
						" left join " + tableName + " on DiDepartment.UnitID = " + tableName + ".UnitId " +
						" and convert(varchar(4),Time,120)=" + "'" + year + "'" +
						" where DiDepartment.UnitID like '30%' ";
				System.out.println(sql1);
				rs = st.executeQuery(sql1) ;
				list = DAOUtil.getList(rs, T659_Bean.class) ;
				
				T659_Bean sumBean = new T659_Bean();
				sumBean.setTeaUnit("全校合计：");
				list.add(0,sumBean);
				
				for(T659_Bean bean:list){
					bean.setTime(TimeUtil.changeDateY(year));
				}
				
				//插入单位列
				DAOUtil.batchInsert(list, tableName, field, conn) ;	
				
				//再取出来
				conn1 = DBConnection.instance.getConnection() ;
				st = conn1.createStatement() ;
				rs = st.executeQuery(sql) ;
				list = DAOUtil.getList(rs, T659_Bean.class) ;
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);
			DBConnection.close(conn1);
			DBConnection.close(conn);
		}
		return list ;
	}
	
	
	 /**
		 * 获取字典表的所有数据
		 * @return
		 *
		 * @time: 2014-5-14/下午02:34:42
		 */
		public List<T659_Bean> totalList(String year,int checkState){
			
			String sql = "select " + key+ "," +field + " from " + tableName 
					+ " where convert(varchar(4),Time,120)=" + year
					+" and CheckState=" + checkState;		
			Connection conn = DBConnection.instance.getConnection() ;
			Statement st = null ;
			ResultSet rs = null ;
			List<T659_Bean> list = null ;
			System.out.println(sql);
			try{
				st = conn.createStatement() ;
				rs = st.executeQuery(sql) ;
				list = DAOUtil.getList(rs, T659_Bean.class) ;
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
		 * 更新数据
		 * @param diCourseCategories
		 * @return
		 *
		 * @time: 2014-5-14/下午02:34:23
		 */	
		public boolean update(T659_Bean bean){
			
			boolean flag = false ;
			Connection conn = DBConnection.instance.getConnection() ;
			try{			
				flag = DAOUtil.update(bean, tableName, key, field, conn) ;
			}catch(Exception e){
				e.printStackTrace() ;
				return flag ;
			}finally{
				DBConnection.close(conn) ;
			}
			
			return flag ;
		}	
		
	
	
	/* ,FillTeaID,FillUnitID,audit */

	/**
	 * 将数据表622的实体类插入数据库
	 * 
	 * @param UndergraAdmiInfo
	 * @return
	 * 
	 * @time: 2014-6-12
	 */
	public boolean insert(T659_Bean StuExchangeInfo) {

		// flag判断数据是否插入成功
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection();
		try {
			flag = DAOUtil.insert(StuExchangeInfo, tableName, field, conn);
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		} finally {
			DBConnection.close(conn);
		}
		return flag;
	}

	

	
	
	/**
	 * 根据seqNumber找相应bean
	 * @param 
	 * @return
	 */
	public T659_Bean findBySeqNum (int seqNum){
		String sql = "select " + key+ "," +field + " from " + tableName 
		+ " where SeqNumber=" + seqNum;		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T659_Bean> list = null ;
		T659_Bean bean = null;
		System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T659_Bean.class) ;
			bean = list.get(0);		
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);
			DBConnection.close(conn);
		}
		return bean ;
	}
	
	
	
	/**
	 * 找当年总计bean
	 * @param 
	 * @return
	 */
	public T659_Bean findSumBean(String name, String year){
		String sql = "select " + key+ "," +field + " from " + tableName 
		+ " where convert(varchar(4),Time,120)=" + year + " and TeaUnit="+ "'" + name + "'";		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T659_Bean> list = null ;
		T659_Bean bean = null;
		System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T659_Bean.class) ;
			bean = list.get(0);

		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);
			DBConnection.close(conn);
		}
		return bean ;
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
	public boolean batchInsert(List<T659_Bean> list) {

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

	/**教育部导出*/
	public List<T659_Bean> getAllList(String year) {
		// TODO Auto-generated method stub
		String sql = "select " + fieldShow + " from " + tableName
				+" where convert(varchar(4),Time,120)=" + year +" and CheckState="+Constants.PASS_CHECK;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T659_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T659_Bean.class) ;
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
	
	public List<T659_Bean> getAllList() {
		// TODO Auto-generated method stub
		String sql = "select " + fieldShow + " from " + tableName ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T659_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T659_Bean.class) ;
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
	
	public List<T659_Bean> getAllList(String cond, String fillUnitID) {
		// TODO Auto-generated method stub
		String Cond = "1=1";
		
		if(cond != null && !cond.equals("")){
			Cond = Cond + cond;
		}
		String sql;
		
		sql = "select " + fieldShow + " from " + tableName +" where " + Cond
				+" and FillUnitID="+fillUnitID;
//	    System.out.println(sql);
	
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T659_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T659_Bean.class) ;
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

		T659_Dao StuExchangeInfoDao = new T659_Dao();
		boolean flag = StuExchangeInfoDao.deleteAll();
		System.out.println(flag);
	}







}
