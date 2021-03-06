package cn.nit.dao.table3;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;


import cn.nit.bean.table3.T322_Bean;
import cn.nit.constants.Constants;
import cn.nit.dbconnection.DBConnection;


import cn.nit.pojo.table3.T322POJO;
import cn.nit.util.DAOUtil;

public class T322_DAO {
	/**  数据库表名  */
	private String tableName = "T322_UndergraMajorInfo_Tea$" ;
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "MajorName,MajorID,MajorVersion,SchMajorName,SchMajorID,MajorField,MajorFieldID,MajorSetTime,MajorAppvlID,MajorDurition," +
			"MajorDegreeType,MajorAdmisTime,MajorState,StopAdmisTime,IsNewMajor,AppvlYear,BuildAppvlID,MajorLevel,Type," +
			"Field,Leader,TeaID,CheckTime,CheckAppvlID,SchExp,EduMinistryExp,FirstAppvlTime,AppvlTime," +
			"AppvlID,AppvlResult,FromTime,EndTime,AppvlAuth,MajorFeature,MajorPurpose,TotalCSHour,RequireCShour,OptionCSHour,InClassCSHour," +
			"ExpCSHour,PraCSHour,TotalCredit,RequireCredit,OptionCredit,InClassCredit," +
			"ExpCredit,PraCredit,OutClassCredit,FillUnitID,Time,Note,CheckState";
	
	/**
	 * 将数据表311的实体类插入数据库
	 * @param undergraCSBase
	 * @return
	 *
	 * @time: 2014-5-14/上午10:53:10
	 */
	


	public boolean insert(T322_Bean t322_Bean){
		
		//flag判断数据是否插入成功
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.insert(t322_Bean, tableName, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
			
		}finally{
			DBConnection.close(conn) ;
		}
		System.out.println(flag);
		return flag ;
	}
	
	/**
	 * 讲数据批量插入T311表中
	 * @param list {@linkplain java.util.List<{@link cn.nit.bean.table5.T311_Bean}>}
	 * @return true表示插入成功，false表示插入失败
	 */
	public boolean batchInsert(List<T322_Bean> list){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		
		try{
			flag = DAOUtil.batchInsert(list, tableName, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
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
		int total = 0 ;
		sql.append("select count(*)") ;
		sql.append(" from " + tableName +" where 1=1 ") ;	
		if(fillUnitId != null && !fillUnitId.equals("")){
			sql.append(" and fillUnitId=" + fillUnitId) ;
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
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		return total ;
	}
	
	
	public List<T322_Bean> auditingData(String conditions, String fillUnitId, int page, int rows){
		
		StringBuffer sql = new StringBuffer() ;
		List<T322_Bean> list =null ;
		sql.append("select t.SeqNumber,t.MajorName,t.MajorID,t.MajorVersion,t.SchMajorName," +
				"t.SchMajorID,t.MajorField,t.MajorFieldID,t.MajorSetTime,t.MajorAppvlID,t.MajorDurition," +
			"t.MajorDegreeType,t.MajorAdmisTime,t.MajorState,t.StopAdmisTime,t.IsNewMajor,t.AppvlYear," +
			"t.BuildAppvlID,dal.AwardLevel as MajorLevel,t.Type," +
			"t.Field,t.Leader,t.TeaID,t.CheckTime,t.CheckAppvlID,t.SchExp,t.EduMinistryExp,t.FirstAppvlTime,t.AppvlTime," +
			"t.AppvlID,t.AppvlResult,t.FromTime,t.EndTime,t.AppvlAuth,t.MajorFeature," +
			"t.MajorPurpose,t.TotalCSHour,t.RequireCShour,t.OptionCSHour,t.InClassCSHour," +
			"t.ExpCSHour,t.PraCSHour,t.TotalCredit,t.RequireCredit,t.OptionCredit,t.InClassCredit," +
			"t.ExpCredit,t.PraCredit,t.OutClassCredit,t.Time,t.Note,t.fillUnitID,t.CheckState");
		sql.append(" from " + tableName + " as t,DiAwardLevel dal ");
		sql.append(" where dal.IndexID=t.MajorLevel " );
		if(fillUnitId != null && !fillUnitId.equals("")){
			sql.append(" and t.fillUnitID=" + fillUnitId) ;
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
			list = DAOUtil.getList(rs, T322_Bean.class) ;
		
			
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
	
	
	
	public boolean update(T322_Bean t322_Bean){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.update(t322_Bean,tableName,key,field,conn) ;
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
	
	
	/**用于数据导出*/
	public List<T322_Bean> totalList( String fillUnitID, String year, int checkState){

		StringBuffer sql=new StringBuffer();
		if("111".equals(fillUnitID)){
			sql.append("select t.SeqNumber,t.MajorName,t.MajorID ," +
					"t.MajorVersion,t.SchMajorName,t.SchMajorID,t.MajorField,t.MajorFieldID,t.MajorSetTime," +
					"t.MajorAppvlID,t.MajorDurition," +
					"t.MajorDegreeType,t.MajorAdmisTime,t.MajorState,t.StopAdmisTime,t.IsNewMajor,t.AppvlYear,t.BuildAppvlID,dal.AwardLevel as MajorLevel,t.Type," +
					"t.Field,t.Leader,t.TeaID,t.CheckTime,t.CheckAppvlID,t.SchExp,t.EduMinistryExp,t.FirstAppvlTime,t.AppvlTime," +
					"t.AppvlID,t.AppvlResult,t.FromTime,t.EndTime,t.AppvlAuth,t.MajorFeature,t.MajorPurpose,t.TotalCSHour,t.RequireCShour,t.OptionCSHour,t.InClassCSHour," +
					"t.ExpCSHour,t.PraCSHour,t.TotalCredit,t.RequireCredit,t.OptionCredit," +
					"t.InClassCredit,t.ExpCredit,t.PraCredit,t.OutClassCredit,t.Time,t.Note,t.FillUnitID,t.CheckState ");
				sql.append(" from " + tableName + " as t,DiAwardLevel dal");
				sql.append(" where dal.IndexID=t.MajorLevel ");
				sql.append(" and t.Time like '"+year+"%'");
				sql.append(" and t.CheckState="+checkState);
			
		}else{
			sql.append("select t.SeqNumber,t.MajorName,t.MajorID ," +
					"t.MajorVersion,t.SchMajorName,t.SchMajorID,t.MajorField,t.MajorFieldID,t.MajorSetTime," +
					"t.MajorAppvlID,t.MajorDurition," +
					"t.MajorDegreeType,t.MajorAdmisTime,t.MajorState,t.StopAdmisTime,t.IsNewMajor,t.AppvlYear,t.BuildAppvlID,dal.AwardLevel as MajorLevel,t.Type," +
					"t.Field,t.Leader,t.TeaID,t.CheckTime,t.CheckAppvlID,t.SchExp,t.EduMinistryExp,t.FirstAppvlTime,t.AppvlTime," +
					"t.AppvlID,t.AppvlResult,t.FromTime,t.EndTime,t.AppvlAuth,t.MajorFeature,t.MajorPurpose,t.TotalCSHour,t.RequireCShour,t.OptionCSHour,t.InClassCSHour," +
					"t.ExpCSHour,t.PraCSHour,t.TotalCredit,t.RequireCredit,t.OptionCredit," +
					"t.InClassCredit,t.ExpCredit,t.PraCredit,t.OutClassCredit,t.Time,t.Note,t.FillUnitID,t.CheckState ");
				sql.append(" from " + tableName + " as t,DiAwardLevel dal");
				sql.append(" where dal.IndexID=t.MajorLevel ");
				sql.append(" and t.FillUnitID="+fillUnitID+" and t.Time like '"+year+"%'");
				sql.append(" and t.CheckState="+checkState);
		}

//		if(fillUnitID != null && !fillUnitID.equals("")){
//				sql.append(" and t.fillUnitId=" + fillUnitID) ;
//			}

		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T322_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, T322_Bean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		
		return list ;
	}
	
	
	/**用于教育部数据导出*/
	public List<T322_Bean> totalList1( String year){

		StringBuffer sql=new StringBuffer();
		sql.append("select t.SeqNumber,t.MajorName,t.MajorID,t.MajorVersion,t.SchMajorName,t.SchMajorID,t.MajorField,t.MajorFieldID,t.MajorSetTime,t.MajorAppvlID,t.MajorDurition," +
				"t.MajorDegreeType,t.MajorAdmisTime,t.MajorState,t.StopAdmisTime,t.IsNewMajor,t.AppvlYear,t.BuildAppvlID,dal.AwardLevel as MajorLevel,t.Type," +
				"t.Field,t.Leader,t.TeaID,t.CheckTime,t.CheckAppvlID,t.SchExp,t.EduMinistryExp,t.FirstAppvlTime,t.AppvlTime," +
				"t.AppvlID,t.AppvlResult,t.FromTime,t.EndTime,t.AppvlAuth,t.MajorFeature,t.MajorPurpose,t.TotalCSHour,t.RequireCShour,t.OptionCSHour,t.InClassCSHour," +
				"t.ExpCSHour,t.PraCSHour,t.TotalCredit,t.RequireCredit,t.OptionCredit,t.InClassCredit,t.ExpCredit," +
				"t.PraCredit,t.OutClassCredit,t.Time,t.Note,t.FillUnitID ,t.CheckState");
			sql.append(" from " + tableName + " as t,DiAwardLevel dal");
			sql.append(" where dal.IndexID=t.MajorLevel ");
			sql.append(" and t.CheckState="+Constants.PASS_CHECK +"and (t.Type='特色专业' or t.Type='品牌专业' or t.Type='名牌专业' or t.Type='示范专业' or t.Type='重点建设专业' or t.Type='地方优势专业')");
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T322_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, T322_Bean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		
		return list ;
	}
	
	public int getMajorNum(String year,int flag){
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		StringBuffer sql1 = new StringBuffer();
		sql1.append("select * from "+tableName+" where Time like '"+year+"%'");
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql1.toString());
			while(!rs.next()) return 0;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		int count = 0;
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT COUNT(DISTINCT MajorName)");
        sql.append(" from "+tableName+" where Time like '"+year+"%'");
        if(flag==1){
        	   sql.append(" and IsNewMajor='true'");
        }
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			if(rs == null){
				return count ;
			}
			
			while(rs.next()){
				count = rs.getInt(1) ;
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return 0;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		
		return count ;
		
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
	
	
	/**
	 * 在招专业总数
	 * @param year
	 * @return
	 */
	public int getTotalFieldNum(String year){
		 int count = 0;
		 StringBuffer sql =  new StringBuffer();
		 sql.append("select count(MajorState) AS MajorState from " +tableName);
		 sql.append(" where MajorState='在招' and Time like '"+year+"%'");
		 
		 Connection conn = DBConnection.instance.getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 try{
			 st = conn.createStatement();
			 rs = st.executeQuery(sql.toString());
			 while(rs.next()){
				 count = rs.getInt("MajorState");
			 }
			 System.out.println("cout=" + count);
		 }catch(Exception e){
			 e.printStackTrace();
			 return count;
		 }finally{
				DBConnection.close(rs);
				DBConnection.close(st);	
				DBConnection.close(conn);
			}
		 return count;		 
	}
	
	
	/**
	 * 集中实践环节教学总学分数
	 * @param year
	 * @return
	 */
	public int getSumPraCredit(String year){
		 int count = 0;
		 StringBuffer sql =  new StringBuffer();
		 sql.append("select sum(PraCredit) AS PraCredit from " +tableName);
		 sql.append(" where Time like '"+year+"%'");
		 
		 Connection conn = DBConnection.instance.getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 try{
			 st = conn.createStatement();
			 rs = st.executeQuery(sql.toString());
			 while(rs.next()){
				 count = rs.getInt("PraCredit");
			 }
			 System.out.println("cout=" + count);
		 }catch(Exception e){
			 e.printStackTrace();
			 return count;
		 }finally{
				DBConnection.close(rs);
				DBConnection.close(st);	
				DBConnection.close(conn);
			}
		 return count;		 
	}
	
	/**
	 * 实验教学总学分数
	 * @param year
	 * @return
	 */
	public int getSumExpCredit(String year){
		 int count = 0;
		 StringBuffer sql =  new StringBuffer();
		 sql.append("select sum(OptionCredit) AS OptionCredit from " +tableName);
		 sql.append(" where Time like '"+year+"%'");
		 
		 Connection conn = DBConnection.instance.getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 try{
			 st = conn.createStatement();
			 rs = st.executeQuery(sql.toString());
			 while(rs.next()){
				 count = rs.getInt("OptionCredit");
			 }
			 System.out.println("cout=" + count);
		 }catch(Exception e){
			 e.printStackTrace();
			 return count;
		 }finally{
				DBConnection.close(rs);
				DBConnection.close(st);	
				DBConnection.close(conn);
			}
		 return count;		 
	}
	
	/**
	 * 课外科技活动总学分数
	 * @param year
	 * @return
	 */
	public int getSumOutClassCredit(String year){
		 int count = 0;
		 StringBuffer sql =  new StringBuffer();
		 sql.append("select sum(OutClassCredit) AS OutClassCredit from " +tableName);
		 sql.append(" where Time like '"+year+"%'");
		 
		 Connection conn = DBConnection.instance.getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 try{
			 st = conn.createStatement();
			 rs = st.executeQuery(sql.toString());
			 while(rs.next()){
				 count = rs.getInt("OutClassCredit");
			 }
			 System.out.println("cout=" + count);
		 }catch(Exception e){
			 e.printStackTrace();
			 return count;
		 }finally{
				DBConnection.close(rs);
				DBConnection.close(st);	
				DBConnection.close(conn);
			}
		 return count;		 
	}
	
	/**
	 * 选修课学总学分数
	 * @param year
	 * @return
	 */
	public int getSumOptionCredit(String year){
		 int count = 0;
		 StringBuffer sql =  new StringBuffer();
		 sql.append("select sum(ExpCredit) AS ExpCredit from " +tableName);
		 sql.append(" where Time like '"+year+"%'");
		 
		 Connection conn = DBConnection.instance.getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 try{
			 st = conn.createStatement();
			 rs = st.executeQuery(sql.toString());
			 while(rs.next()){
				 count = rs.getInt("ExpCredit");
			 }
			 System.out.println("cout=" + count);
		 }catch(Exception e){
			 e.printStackTrace();
			 return count;
		 }finally{
				DBConnection.close(rs);
				DBConnection.close(st);	
				DBConnection.close(conn);
			}
		 return count;		 
	}
	
	/**
	 * 总学分数
	 * @param year
	 * @return
	 */
	public int getSumTotalCredit(String year){
		 int count = 0;
		 StringBuffer sql =  new StringBuffer();
		 sql.append("select sum(TotalCredit) AS TotalCredit from " +tableName);
		 sql.append(" where Time like '"+year+"%'");
		 
		 Connection conn = DBConnection.instance.getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 try{
			 st = conn.createStatement();
			 rs = st.executeQuery(sql.toString());
			 while(rs.next()){
				 count = rs.getInt("TotalCredit");
			 }
			 System.out.println("cout=" + count);
		 }catch(Exception e){
			 e.printStackTrace();
			 return count;
		 }finally{
				DBConnection.close(rs);
				DBConnection.close(st);	
				DBConnection.close(conn);
			}
		 return count;		 
	}

	
	
	public String getTableName(){
		return this.tableName ;
	}

}
