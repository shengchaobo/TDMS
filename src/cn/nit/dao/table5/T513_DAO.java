package cn.nit.dao.table5;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.nit.bean.table5.T513Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.pojo.table5.T513POJO;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class T513_DAO {
	
	/**  数据库表名  */
	private String tableName = "T513_ClassTeachQuaInfo_AS$" ;
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "Item,Category,ShouldASCSNum,HavedASCSNum,CoverRatio,ExcellentNum,ExcellentRatio,GoodNum," +
			"GoodRatio,AvgNum,AvgRatio,PoorNum,PoorRatio,Time,Note";
	
	
	
	/**
	 * 获得当年数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T513POJO> getYearInfo(String year){
		
//		String sql = "select " + key + "," + field + " from " + tableName 
//				+ " where convert(varchar(4),Time,120)=" + year;
//		
		StringBuffer sql = new StringBuffer();
		sql.append("select t.SeqNumber,die.EvaluType as Item,t.Item as ItemID,t.Category,t.ShouldASCSNum,t.HavedASCSNum,t.CoverRatio,t.ExcellentNum,t.ExcellentRatio" +
		",t.GoodNum,t.GoodRatio,t.AvgNum,t.AvgRatio,t.PoorNum,t.PoorRatio,t.Time,t.Note");
		sql.append(" from "+tableName+" as t,DiEvaluType as die ");
		sql.append(" where die.IndexID = t.Item ");
		sql.append(" and Time ='"+year+"'");
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T513Bean> list1=new ArrayList<T513Bean>();
		List<T513POJO> list= null;
		//System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			list= DAOUtil.getList(rs, T513POJO.class) ;
			System.out.println("daolist:"+list.size());
			
			//如果当前年表中没有单位列数据，先将单位列数据插入到表中
			if(list.size()==0){
			    for(int i =0;i<8;i++){
			    	T513Bean bean = new T513Bean();
			    	switch (i){
			    		 case 0:bean.setItem("54000");
			    		 		bean.setCategory("理论课");
			    		 		bean.setTime(year);
			    		 		break;
			    		 case 1:bean.setItem("54000");
		    		 		bean.setCategory("实践教学");
		    		 		bean.setTime(year);
		    		 		break;
			    		 case 2:bean.setItem("54001");
		    		 		bean.setCategory("理论课");
		    		 		bean.setTime(year);
		    		 		break;
			    		 case 3:bean.setItem("54001");
		    		 		bean.setCategory("实践教学");
		    		 		bean.setTime(year);
		    		 		break;
			    		 case 4:bean.setItem("54002");
		    		 		bean.setCategory("理论课");
		    		 		bean.setTime(year);
		    		 		break;
			    		 case 5:bean.setItem("54002");
		    		 		bean.setCategory("实践教学");
		    		 		bean.setTime(year);
		    		 		break;
			    		 case 6:bean.setItem("54003");
		    		 		bean.setCategory("理论课");
		    		 		bean.setTime(year);
		    		 		break;
			    		 case 7:bean.setItem("54003");
		    		 		bean.setCategory("实践教学");
		    		 		bean.setTime(year);
		    		 		break;		
			    	}
			    	list1.add(bean);
			    }
				//插入单位列
				DAOUtil.batchInsert(list1, tableName, field, conn) ;	
				
				//再取出来
				Connection conn1 = DBConnection.instance.getConnection() ;
				st = conn1.createStatement() ;
				rs = st.executeQuery(sql.toString()) ;
				list = DAOUtil.getList(rs, T513POJO.class) ;
			}
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
	
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T513POJO> totalList(String year){
		
		StringBuffer sql=new StringBuffer();
		sql.append("select t.SeqNumber,die.EvaluType as Item,t.Item as ItemID,t.Category,t.ShouldASCSNum,t.HavedASCSNum,t.CoverRatio,t.ExcellentNum,t.ExcellentRatio" +
		",t.GoodNum,t.GoodRatio,t.AvgNum,t.AvgRatio,t.PoorNum,t.PoorRatio,t.Time,t.Note");
		sql.append(" from "+tableName+" as t,DiEvaluType as die ");
		sql.append(" where die.IndexID = t.Item  ");
		sql.append(" and Time like '"+year+"%'");	
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T513POJO> list = null ;
		System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, T513POJO.class) ;
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
	
	/**
	 * 更新数据
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */	
	public boolean update(T513Bean bean){
		
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
	
	/**
	 * 根据seqNumber找相应bean
	 * @param 
	 * @return
	 */
	public T513Bean findBySeqNum (int seqNum){
		String sql = "select " + key+ "," +field + " from " + tableName 
		+ " where SeqNumber=" + seqNum;		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T513Bean> list = null ;
		T513Bean bean = null;
		System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T513Bean.class) ;
			bean = list.get(0);		
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
		
		return bean ;
	}
	
	
	/**
	 * 将数据表513的实体类插入数据库
	 * @param schResIns
	 * @return
	 *
	 * @time: 2014-5-14/上午10:53:10
	 */
	public boolean insert(T513Bean t513Bean){
		
		//flag判断数据是否插入成功
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.insert(t513Bean, tableName, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
		}
		return flag ;
	}
	
	/**
	 * 讲数据批量插入513表中
	 * @param list {@linkplain java.util.List<{@link cn.nit.bean.table1.T151Bean}>}
	 * @return true表示插入成功，false表示插入失败
	 */
	public boolean batchInsert(List<T513Bean> list,String year){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		StringBuffer sql = new StringBuffer();
		sql.append("select * from "+tableName);
		sql.append(" where Time like '"+year+"%'");
		Statement st = null ;
		ResultSet rs = null ;
		List<T513Bean> templist = null ;
		
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			templist = DAOUtil.getList(rs, T513Bean.class);
			if(templist.size() != 0){
				String delSql = "delete from " + tableName + " where Time like '" + year+"%'";
				int delflag = st.executeUpdate(delSql.toString());
				if(delflag > 0 ){
					flag = DAOUtil.batchInsert(list, tableName, field, conn) ;
				}
			}else{
				flag = DAOUtil.batchInsert(list, tableName, field, conn) ;
			}
//			flag = DAOUtil.batchInsert(list, tableName, field, conn) ;
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
		sql.append(" from "+tableName+" as t,DiEvaluType as die ");
		sql.append(" where die.IndexID = t.Item ");
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
				total+=1;
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
	public List<T513POJO> auditingData(String conditions, String fillUnitId, int page, int rows){
		
		StringBuffer sql = new StringBuffer() ;
		List<T513POJO> list = null ;
		sql.append("select t.SeqNumber,die.EvaluType as Item,t.Item as ItemID,t.Category,t.ShouldASCSNum,t.HavedASCSNum,t.CoverRatio,t.ExcellentNum,t.ExcellentRatio" +
				",t.GoodNum,t.GoodRatio,t.AvgNum,t.AvgRatio,t.PoorNum,t.PoorRatio,t.Time,t.Note");
		sql.append(" from "+tableName+" as t,DiEvaluType as die ");
		sql.append(" where die.IndexID = t.Item  ");

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
			list = DAOUtil.getList(rs, T513POJO.class) ;
			
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}
		
		return list ;
	}
	

//	/**
//	 * 获得的总数（用于导出）
//	 * @return
//	 *
//	 * @time: 2014-5-14/下午02:34:42
//	 */
//	public List<T513POJO> totalList(String year){
//
//		StringBuffer sql=new StringBuffer();
//		sql.append("select t.SeqNumber,die.EvaluType as Item,t.Item as ItemID,t.Category,t.ShouldASCSNum,t.HavedASCSNum,t.CoverRatio,t.ExcellentNum,t.ExcellentRatio" +
//		",t.GoodNum,t.GoodRatio,t.AvgNum,t.AvgRatio,t.PoorNum,t.PoorRatio,t.Time,t.Note");
//		sql.append(" from "+tableName+" as t,DiEvaluType as die ");
//		sql.append(" where die.IndexID = t.Item  ");
//		sql.append(" and Time like '"+year+"%'");
//		
//		Connection conn = DBConnection.instance.getConnection() ;
//		Statement st = null ;
//		ResultSet rs = null ;
//		List<T513POJO> list = null ;
//		
//		try{
//			st = conn.createStatement() ;
//			rs = st.executeQuery(sql.toString()) ;
//			list = DAOUtil.getList(rs, T513POJO.class) ;
//		}catch(Exception e){
//			e.printStackTrace() ;
//			return null;
//		}
//		
//		return list ;
//	}
	
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
	
	public boolean delete(){
		boolean flag = false;
		StringBuffer sql = new StringBuffer();
		sql.append("delete from "+tableName);
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
//		ResultSet rs = null;
		try{
			st = conn.createStatement();
			int delflag = st.executeUpdate(sql.toString());
			if(delflag>0){
				flag = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return flag;
		
	}
	
	public static void main(String arg[]){
		T513_DAO dao = new T513_DAO();
		if(dao.delete()){
			System.out.println("删除完毕");
		}
//		List<T513POJO> list = dao.getYearInfo("2012");
//		System.out.println(list.size());
	}
	

}
