package cn.nit.dao.table5;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.table5.T534Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.pojo.table5.T534POJO;
import cn.nit.util.DAOUtil;

public class T534DAO {
	
	
	/**  数据库表名  */
	private String tableName = "T534_ByMajGraTrainInfo_TeaTea$" ;
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "TeaUnit,UnitID,MajorName,MajorID,TeaName,TeaID,IsOutEmploy,Education,Degree,Title," +
			"IsExcellent,TrainIssueNum,SocialNum,GuideStuNum,GainBestNum,GainTime,FillUnitID,Time,Note";
	
	
	
	/**
	 * 将数据表551的实体类插入数据库
	 * @param schResIns
	 * @return
	 *
	 * @time: 2014-5-14/上午10:53:10
	 */
	public boolean insert(T534Bean t534Bean){
		
		//flag判断数据是否插入成功
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.insert(t534Bean, tableName, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
		}
		return flag ;
	}
	
	/**
	 * 讲数据批量插入534表中
	 * @param list {@linkplain java.util.List<{@link cn.nit.bean.table1.T151Bean}>}
	 * @return true表示插入成功，false表示插入失败
	 */
	public boolean batchInsert(List<T534Bean> list){
		
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
		sql.append("select count(*) ") ;
		sql.append(" from "+tableName+" as t,DiDepartment as did,DiMajorTwo as dmt,DiDegree as dde,DiEducation as dea,DiTittleName as dtn");
		sql.append(" where dde.IndexID = t.Degree and did.UnitID = t.UnitID and dea.IndexID = t.Education and dmt.MajorNum = t.MajorID" +
				" and  dtn.IndexID = t.t.Title");
		int total = 0 ;
		
		if(fillUnitId != null && !fillUnitId.equals("")){
			sql.append(" and FillUnitID=" + fillUnitId) ;
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
			
			if(rs == null){
				return total ;
			}
			
			while(rs.next()){
				total = rs.getInt(1) ;
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
	public List<T534POJO> auditingData(String conditions, String fillUnitId, int page, int rows){
		
		StringBuffer sql = new StringBuffer() ;
		List<T534POJO> list = null ;
		sql.append("select t.SeqNumber,t.TeaUnit,t.UnitID,t.MajorName,t.MajorID,t.TeaName,t.TeaID,t.IsOutEmploy," +
				"dea.Educaion as Education,t.Education as EducationID,dde.Degree as Degree,t.Degree as DegreeID, dtn.TittleName as Title,t.Title as TitleID," +
				"t.IsExcellent, t.TrainIssueNum, t.SocialNum,t.GuideStuNum,t.GainBestNum,t.GainTime,t.Time,t.Note,t.FillUnitID");
		sql.append(" from "+tableName+" as t,DiDepartment as did,DiMajorTwo as dmt,DiDegree as dde,DiEducation as dea,DiTittleName as dtn");
		sql.append(" where dde.IndexID = t.Degree and did.UnitID = t.UnitID and dea.IndexID = t.Education and dmt.MajorNum = t.MajorID" +
				" and  dtn.IndexID = t.Title");

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
			list = DAOUtil.getList(rs, T534POJO.class) ;
			
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
	public List<T534Bean> totalList(){

		StringBuffer sql=new StringBuffer();
		sql.append("select t.SeqNumber,t.TeaUnit,t.UnitID,t.MajorName,t.MajorID,t.TeaName,t.TeaID,t.IsOutEmploy," +
				"dea.Educaion as Education,dde.Degree as Degree, dtn.TittleName as Title," +
				"t.IsExcellent, t.TrainIssueNum, t.SocialNum,t.GuideStuNum,t.GainBestNum,t.GainTime,t.Time,t.Note,t.FillUnitID");
		sql.append(" from "+tableName+" as t,DiDepartment as did,DiMajorTwo as dmt,DiDegree as dde,DiEducation as dea,DiTittleName as dtn");
		sql.append(" where dde.IndexID = t.Degree and did.UnitID = t.UnitID and dea.IndexID = t.Education and dmt.MajorNum = t.MajorID" +
				" and  dtn.IndexID = t.Title");

		
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T534Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, T534Bean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null;
		}
		
		return list ;
	}
	
	//更新！
	public boolean update(T534Bean t534Bean){
			
			boolean flag = false ;
			Connection conn = DBConnection.instance.getConnection() ;
			try{
//				System.out.println("hello！");
				flag = DAOUtil.update(t534Bean, tableName, key, field, conn) ;
			}catch(Exception e){
				e.printStackTrace() ;
				return flag ;
			}finally{
				DBConnection.close(conn) ;
			}
			return flag ;
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
	public String getTableName(){
		return this.tableName ;
	}

}
