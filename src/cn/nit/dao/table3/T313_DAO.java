package cn.nit.dao.table3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;




import cn.nit.bean.table3.T311_Bean;
import cn.nit.bean.table3.T313_Bean;
import cn.nit.constants.Constants;
import cn.nit.dbconnection.DBConnection;
import cn.nit.pojo.table3.T313POJO;
import cn.nit.util.DAOUtil;

public class T313_DAO {
	
	/**  数据库表名  */
	private String tableName = "T313_Discip_Res$" ;
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "DiscipName,DiscipID,UnitName,UnitID,DiscipType," +
			"NationLevelOne,NationLevelTwo,NationLevelKey,ProvinceLevelOne," +
			"ProvinceLevelTwo,CityLevel,SchLevel,Time,Note,CheckState" ;
	
	/**
	 * 将数据表511的实体类插入数据库
	 * @param undergraCSBase
	 * @return
	 *
	 * @time: 2014-5-14/上午10:53:10
	 */
	public boolean insert(T313_Bean discipBean){
		
		//flag判断数据是否插入成功
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.insert(discipBean, tableName, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
		}
		return flag ;
	}
	
	/**
	 * 讲数据批量插入T311表中
	 * @param list {@linkplain java.util.List<{@link cn.nit.bean.table5.T311_Bean}>}
	 * @return true表示插入成功，false表示插入失败
	 */
	public boolean batchInsert(List<T313_Bean> list){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		
		try{
			flag = DAOUtil.batchInsert(list, tableName, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}
		
		return flag ;
	}
	
	/**
	 * 查询待审核数据在数据库中共有多少条
	 * @param conditions 查询条件
	 * @param fillUnitId 填报人单位号，如果为空，则查询所有未审核的数据，<br>如果不为空，则查询填报人自己单位的所有的数据
	 * @return
	 */
	public int totalAuditingData(String conditions, String fillUnitId){
		
		StringBuffer sql = new StringBuffer() ;
		sql.append("select count(*)") ;
//		sql.append(" from "+ tableName);
		sql.append(" from " + tableName + " as t,DiDepartment dpt ") ;
		sql.append(" where dpt.UnitID=t.UnitID ");		
		int total = 0 ;

//		System.out.println(sql.toString());
		if(fillUnitId != null && !fillUnitId.equals("")){
			sql.append(" and FillDept=" + fillUnitId) ;
		}
		
		if(conditions != null && !conditions.equals("")){
			sql.append(conditions) ;
		}
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
//			System.out.println(rs);
			if(rs == null){
				return total ;
			}
			
			while(rs.next()){
				total = rs.getInt(1) ;
//			   System.out.println("total:"+total);
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return 0 ;
		}
		return total ;
	}
	
	
	public List<T313_Bean> auditingData(String conditions, String fillDept, int page, int rows){
		
		StringBuffer sql = new StringBuffer() ;
		List<T313_Bean> list =null ;
		sql.append("select t.SeqNumber,t.DiscipName,t.DiscipID,t.UnitName," +
				"t.UnitID,t.DiscipType,t.NationLevelOne,t.NationLevelTwo,t.NationLevelKey,t.ProvinceLevelOne,"+
				"t.ProvinceLevelTwo,t.CityLevel,t.SchLevel,t.Note,t.Time,t.CheckState");
		sql.append(" from "+tableName + " as t,DiDepartment dpt ");
		sql.append(" where   dpt.UnitID=t.UnitID" );
//		sql.append(" where dpt.UnitID=t.UnitID and dal.IndexID=t.UnitLevel and dal.IndexID=t.CooperInsLevel");
//		sql.append("select t.SeqNumber,t.CSName,t.CSID,t.CSUnit,t.UnitID,t.FromTeaResOffice,t.TeaResOfficeID,cst.CourseCategories as CSType,t.CSType as CSTypeID,csn.CourseChar as CSNature,t.CSNature as CSNatureID,t.State,t.PubCSType,t.Time,t.Note") ;
//		sql.append(" from " + tableName + " as t,DiCourseChar csn,DiCourseCategories cst") ;
//		sql.append(" where audit!='0' and csn.IndexID=t.CSNature and cst.IndexID=t.CSType") ;
		//
//		System.out.println(sql.toString());
		if(fillDept != null && !fillDept.equals("")){
			sql.append(" and FillDept=" + fillDept) ;
		}
		
		if(conditions != null){
			sql.append(conditions) ;
		}
		
		//sql.append(" order by SeqNumber desc") ;
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
//		System.out.println(sql.toString());
		
		try{
			st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY) ;
			st.setMaxRows(page * rows) ;
			rs = st.executeQuery(sql.toString()) ;
			rs.absolute((page - 1) * rows) ;//将光标移动到此 ResultSet 对象的给定行编号
			list = DAOUtil.getList(rs, T313_Bean.class) ;
		
			
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}
		
		return list ;
	}
	
	/**用于数据导出*/
	public List<T313_Bean> totalList(String year,int checkState){

		StringBuffer sql=new StringBuffer();
		sql.append("select t.SeqNumber,t.DiscipName,t.DiscipID,t.UnitName," +
				"t.UnitID,t.DiscipType,t.NationLevelOne,t.NationLevelTwo,t.NationLevelKey,t.ProvinceLevelOne,"+
				"t.ProvinceLevelTwo,t.CityLevel,t.SchLevel,t.Note,t.Time,t.CheckState");
		sql.append(" from "+tableName + " as t,DiDepartment dpt ");
		sql.append(" where   dpt.UnitID=t.UnitID");
		sql.append(" and t.Time like '"+year+"%' and t.CheckState="+checkState);
		
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T313_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, T313_Bean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null;
		}
		
		return list ;
	}
	
	/**用于教育部数据导出*/
	public List<T313POJO> totalList(String year){

		StringBuffer sql=new StringBuffer();
		sql.append("select t.SeqNumber,t.DiscipName,t.DiscipID,t.UnitName," +
				"t.UnitID,t.DiscipType,t.NationLevelOne,t.NationLevelTwo,t.NationLevelKey,t.ProvinceLevelOne,"+
				"t.ProvinceLevelTwo,t.CityLevel,t.SchLevel,t.Note,t.Time,t.CheckState");
		sql.append(" from "+tableName + " as t,DiDepartment dpt ");
		sql.append(" where   dpt.UnitID=t.UnitID and t.Time like '"+year+"%'");
		sql.append(" and t.CheckState="+Constants.WAIT_CHECK);
			
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T313POJO> list = new ArrayList <T313POJO> ();

		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			while (rs.next()){
				T313POJO pojo =new T313POJO();
				StringBuffer level = new StringBuffer();
				pojo.setDiscipName(rs.getString("DiscipName"));
				pojo.setDiscipID(rs.getString("DiscipID"));
				pojo.setUnitName(rs.getString("UnitName"));
				pojo.setUnitID(rs.getString("UnitID"));
				pojo.setDiscipType(rs.getString("DiscipType"));
				if(rs.getBoolean("NationLevelOne")){
					level.append("国家一级，");		
				}
				if(rs.getBoolean("NationLevelTwo")){
					level.append("国家二级，");		
				}
				if(rs.getBoolean("NationLevelKey")){
					level.append("国家重点（培育），");		
				}
				if(rs.getBoolean("ProvinceLevelOne")){
					level.append("省部一级，");		
				}
				if(rs.getBoolean("ProvinceLevelTwo")){
					level.append("省部二级，");		
				}
				if(rs.getBoolean("CityLevel")){
					level.append("市级，");		
				}
				if(rs.getBoolean("SchLevel")){
					level.append("校级，");		
				}
				String level1 = level.toString();
				level1 = level1.substring(0,level.length()-1);
				pojo.setLevel(level1.toString());
				list.add(pojo);
				
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return null;
		}	
		return list ;
	}
	

	
	
	
	public boolean update(T313_Bean t313Bean){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.update(t313Bean, tableName, key, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
		}
		return flag ;
	}
	
	public boolean deleteCoursesByIds(String ids){
		
		int flag = 0 ;
		StringBuffer sql = new StringBuffer() ;
		sql.append("delete from " + tableName) ;
		sql.append(" where " + key + " in " + ids) ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		
		try{
			st = conn.createStatement() ;
			flag = st.executeUpdate(sql.toString()) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return false ;
		}
		
		if(flag == 0){
			return false ;
		}else{
			return true ;
		}
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
			DBConnection.close(conn) ;
		}
		
		if (flag == 0) {
			return false;
		} else {
			return true;
		}
	}
	

	
	public static void main(String args[]){
		
		T313_DAO dao=new T313_DAO();
		 List<T313_Bean> list = dao.totalList("2014", 2);
		 System.out.println(list.size());
	}
	
	public String getTableName(){
		return this.tableName ;
	}



}
