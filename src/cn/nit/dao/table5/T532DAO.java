package cn.nit.dao.table5;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.table5.T532Bean;
import cn.nit.constants.Constants;
import cn.nit.dbconnection.DBConnection;

import cn.nit.pojo.table5.T532POJO;
import cn.nit.util.DAOUtil;

public class T532DAO {
	
	/**  数据库表名  */
	private String tableName = "T532_ExpTeachShowCenter_EQU$" ;
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "CenterName,FromSubject,CenterLevel,FromTeaUnit,UnitID,CenterLeader,TeaID,TeaTitle,BuildTime," +
			"BuildAppvlID,ReceptTime,ReceptAppvlID,ValidTime,Fund,Time,Note,SubjectID,CheckState";
	
	
	
	/**
	 * 将数据表532的实体类插入数据库
	 * @param schResIns
	 * @return
	 *
	 * @time: 2014-5-14/上午10:53:10
	 */
	public boolean insert(T532Bean t532Bean){
		
		//flag判断数据是否插入成功
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.insert(t532Bean, tableName, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
		}
		return flag ;
	}
	
	/**
	 * 讲数据批量插入533表中
	 * @param list {@linkplain java.util.List<{@link cn.nit.bean.table1.T151Bean}>}
	 * @return true表示插入成功，false表示插入失败
	 */
	public boolean batchInsert(List<T532Bean> list){
		
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
		sql.append("select count(*) AS Count") ;
		sql.append(" from "+tableName+" as t,DiDepartment as did,DiAwardLevel as dal,DiTitleName as dtn");
		sql.append(" where did.UnitID = t.UnitID and dal.IndexID = t.CenterLevel and  dtn.IndexID=t.TeaTitle ");
		int total = 0 ;
	
//		if(fillUnitId != null && !fillUnitId.equals("")){
//			sql.append(" and FillUnitID=" + fillUnitId) ;
//		}
//		
		if(conditions != null && !conditions.equals("")){
			sql.append(conditions) ;
		}
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			
			if(rs == null){
				return total ;
			}
			
			while(rs.next()){
				total = rs.getInt("Count");
//				System.out.println("total:"+total);
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return 0 ;
		}
		return total ;
	}
	
	/**
	 * @param conditions 查询条件
	 * @param fillUnitId 填报人单位号，如果为空，则查询所有未审核的数据，<br>如果不为空，则查询填报人自己单位的所有的数据
	 * @return
	 */
	public List<T532POJO> auditingData(String conditions, String fillUnitId, int page, int rows){
		
		StringBuffer sql = new StringBuffer() ;
		List<T532POJO> list = null ;
		sql.append("select t.SeqNumber,t.CenterName,t.FromSubject,t.SubjectID,dal.AwardLevel CenterLevel,t.CenterLevel as CenterLevelID,t.FromTeaUnit,t.UnitID,t.CenterLeader," +
				"t.TeaID,dtn.TitleName as TeaTitle,t.TeaTitle as TeaTitleID,t.BuildTime,t.BuildAppvlID," +
				"t.ReceptTime,t.ReceptAppvlID,t.ValidTime,t.Fund,t.Time,t.Note,t.SubjectID,t.CheckState ") ;
		sql.append(" from "+tableName+" as t,DiDepartment as did,DiAwardLevel as dal,DiTitleName as dtn");
		sql.append(" where did.UnitID = t.UnitID and dal.IndexID = t.CenterLevel and  dtn.IndexID=t.TeaTitle ");

//		if(fillUnitId != null && !fillUnitId.equals("")){
//			sql.append(" and FillUnitID=" + fillUnitId) ;
//		}
//		
		if(conditions != null){
			sql.append(conditions) ;
		}
		
//		sql.append(" order by SeqNumber desc") ;
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try{
			st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY) ;
			st.setMaxRows(page * rows) ;
			rs = st.executeQuery(sql.toString()) ;
			rs.absolute((page - 1) * rows) ;
			list = DAOUtil.getList(rs, T532POJO.class) ;
			
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}
		
		return list ;
	}
	

	/**
	 * 获得的总数（用于导出）
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T532Bean> totalList(String year,int checkState){

		StringBuffer sql=new StringBuffer();
		sql.append("select t.SeqNumber,t.CenterName,t.FromSubject,t.SubjectID,dal.AwardLevel CenterLevel,t.CenterLevel as CenterLevelID,t.FromTeaUnit,t.UnitID,t.CenterLeader," +
		"t.TeaID,dtn.TitleName as TeaTitle,t.TeaTitle as TeaTitleID,t.BuildTime," +
		"t.BuildAppvlID,t.ReceptTime,t.ReceptAppvlID,t.ValidTime,t.Fund,t.Time,t.Note,t.SubjectID,t.CheckState ") ;
		sql.append(" from "+tableName+" as t,DiDepartment as did,DiAwardLevel as dal,DiTitleName as dtn");
		sql.append(" where did.UnitID = t.UnitID and dal.IndexID = t.CenterLevel and  dtn.IndexID=t.TeaTitle");
		sql.append(" and t.Time like '"+year+"%' and t.CheckState="+checkState);
		
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T532Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, T532Bean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null;
		}
		
		return list ;
	}
	
	
	/**用于教育部导出处*/
	public List<T532Bean> totalList(String year){

		StringBuffer sql=new StringBuffer();
		sql.append("select t.SeqNumber,t.CenterName,t.FromSubject,t.SubjectID,dal.AwardLevel CenterLevel,t.CenterLevel as CenterLevelID,t.FromTeaUnit,t.UnitID,t.CenterLeader," +
		"t.TeaID,dtn.TitleName as TeaTitle,t.TeaTitle as TeaTitleID,t.BuildTime," +
		"t.BuildAppvlID,t.ReceptTime,t.ReceptAppvlID,t.ValidTime,t.Fund,t.Time,t.Note,t.CheckState") ;
		sql.append(" from "+tableName+" as t,DiDepartment as did,DiAwardLevel as dal,DiTitleName as dtn");
		sql.append(" where did.UnitID = t.UnitID and dal.IndexID = t.CenterLevel and  dtn.IndexID=t.TeaTitle");
		sql.append(" and t.Time like '"+year+"%' and t.CheckState="+Constants.PASS_CHECK);

		
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T532Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, T532Bean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null;
		}
		
		return list ;
	}
	
	//更新！
	public boolean update(T532Bean t532Bean){
			
			boolean flag = false ;
			Connection conn = DBConnection.instance.getConnection() ;
			try{
//				System.out.println("hello！");
				flag = DAOUtil.update(t532Bean, tableName, key, field, conn) ;
			}catch(Exception e){
				e.printStackTrace() ;
				return flag ;
			}finally{
				DBConnection.close(conn) ;
			}
			return flag ;
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
	
	//删除
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
	
	public String getTableName(){
		return this.tableName ;
	}
	
	public static void main(String arg[]){
		T532DAO dao= new T532DAO();
//		boolean flag = dao.updatCheck();
		 List<T532Bean> list = dao.totalList("2014", 2);
		System.out.println(list.size());
	}
	


}
