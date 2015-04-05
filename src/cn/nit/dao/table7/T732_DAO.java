package cn.nit.dao.table7;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;


import cn.nit.bean.table7.T732_Bean;
import cn.nit.constants.Constants;
import cn.nit.dbconnection.DBConnection;

import cn.nit.pojo.table7.T732POJO;
import cn.nit.util.DAOUtil;

public class T732_DAO {
	
	private String tableName="T732_TeaLeadInClassInfo_TeaTea$";
	
	private String key="SeqNumber";
	
	private String field="AttendClassTerm,LeaderName,LeaderTeaID,AdminTitle," +
			"AttendClassTime,LectureTea,LectureTeaID,LectureCS,CSID," +
			"SetCSUnit,UnitID,LectureClass,Evaluate,Time,Note,FillUnitID,CheckState";

	public boolean insert(T732_Bean teaLeadInClassInfo){
		boolean flag=false;
		
		Connection conn=DBConnection.instance.getConnection();
		
		try {
			flag=DAOUtil.insert(teaLeadInClassInfo, tableName, field, conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return flag;
		}finally{
			DBConnection.close(conn);
		}
		return flag;
	}
	
	 /**
		 * 查询待审核数据在数据库中共有多少条
		 * @param conditions 查询条件
		 * @param fillUnitId 填报人单位号，如果为空，则查询所有未审核的数据，<br>如果不为空，则查询填报人自己单位的所有的数据
		 * @return
		 */
		    
	    public int totalAuditingData(String conditions,String fillUnitId){
		
		StringBuffer sql=new StringBuffer();
		sql.append(" select count(*)");
		sql.append(" from " + tableName + " as t, T411_TeaBasicInfo_Per$  tbp");
		sql.append(" where tbp.TeaID=t.LectureTeaID ");
		int total=0;
		
		Connection conn=DBConnection.instance.getConnection();
		Statement st=null;
		ResultSet rs=null;
		

		if(fillUnitId!=null && !fillUnitId.equals("")){
			sql.append(" and FillUnitID=" + fillUnitId);	
		}
		
		if(conditions!=null && !conditions.equals("")){
			sql.append(conditions);
		}
		
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql.toString());
			if(rs==null){
				return total;
			}
			while(rs.next()){
				total=rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
			
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		
		return total;

	}
	     
	    /**
		 * @param conditions 查询条件
		 * @param fillUnitId 填报人单位号，如果为空，则查询所有未审核的数据，<br>如果不为空，则查询填报人自己单位的所有的数据
		 * @return
		 */
		public List<T732POJO> auditingData(String conditions,String fillUnitId,int page,int rows){
			List<T732POJO> list=null;
			StringBuffer sql=new StringBuffer();
			
			sql.append("select  t.SeqNumber,t.AttendClassTerm,t.LeaderName," +
					"t.LeaderTeaID,t.AdminTitle,t.AttendClassTime," +
					"t.LectureTea,tbp.TeaID as LectureTeaID,t.LectureTeaID as LectureTeaIDD," +
					"t.LectureCS,t.CSID,t.SetCSUnit,t.UnitID,t.LectureClass,t.Evaluate,t.Time,t.Note,t.FillUnitID,t.CheckState");
			sql.append(" from " +  tableName  + " as t, T411_TeaBasicInfo_Per$  tbp");
			sql.append(" where tbp.TeaID=t.LectureTeaID ");
			
			if(fillUnitId != null && !fillUnitId.equals("")){
				sql.append(" and FillUnitID=" + fillUnitId) ;
			}
			
			if(conditions != null){
				sql.append(conditions) ;
			}
			
			//sql.append(" order by SeqNumber desc") ;
			
			Connection conn=DBConnection.instance.getConnection();
			
			Statement st=null;
			ResultSet rs=null;
			
			try {
				
				//System.out.println(sql);
				st=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				st.setMaxRows(page * rows);
				rs=st.executeQuery(sql.toString());
				rs.absolute((page - 1) * rows);
				list=DAOUtil.getList(rs, T732POJO.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}finally{
				DBConnection.close(rs);
				DBConnection.close(st);	
				DBConnection.close(conn);
			}		
			return list;	
		}
		/**
		 * 获取字典表的所有数据
		 * @return
		 *
		 * @time: 2014-5-14/下午02:34:42
		 */
		public List<T732POJO> totalList(String fillUnitID, String year, int checkState){
            StringBuffer sql=new StringBuffer();
            
            if("111".equals(fillUnitID)){
            	
            	sql.append("select  t.SeqNumber,t.AttendClassTerm,t.LeaderName," +
    					"t.LeaderTeaID,t.AdminTitle,t.AttendClassTime,t.LectureTea," +
    					"tbp.TeaID as LectureTeaID,t.LectureTeaID as LectureTeaIDD," +
    					"t.LectureCS,t.CSID,t.SetCSUnit,t.UnitID,t.LectureClass,t.Evaluate,t.Time,t.Note,t.CheckState,t.FillUnitID");
    			sql.append(" from " +  tableName  + " as t, T411_TeaBasicInfo_Per$  tbp");
    			sql.append(" where tbp.TeaID=t.LectureTeaID ");
    			sql.append(" and t.Time like '"+year+"%' and t.CheckState="+checkState);
            	
//    			sql = "select " + keyfield+ "," + field + " from " + tableName +
//    		     " where CheckState=" + checkState + " and Time like '"+year+"%'";
    		}else{
    			sql.append("select  t.SeqNumber,t.AttendClassTerm,t.LeaderName," +
    					"t.LeaderTeaID,t.AdminTitle,t.AttendClassTime,t.LectureTea," +
    					"tbp.TeaID as LectureTeaID,t.LectureTeaID as LectureTeaIDD," +
    					"t.LectureCS,t.CSID,t.SetCSUnit,t.UnitID,t.LectureClass,t.Evaluate,t.Time,t.Note,t.CheckState,t.FillUnitID");
    			sql.append(" from " +  tableName  + " as t, T411_TeaBasicInfo_Per$  tbp");
    			sql.append(" where tbp.TeaID=t.LectureTeaID ");
    			sql.append(" and t.FillUnitID=" + "'" + fillUnitID + "'");
    			sql.append(" and t.Time like '"+year+"%' and t.CheckState="+checkState);
    		}
			
			
			Connection conn = DBConnection.instance.getConnection() ;
			Statement st = null ;
			ResultSet rs = null ;
			List<T732POJO> list = null ;
			
			try{
				st = conn.createStatement() ;
				rs = st.executeQuery(sql.toString()) ;
				list = DAOUtil.getList(rs, T732POJO.class) ;
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
		 public boolean update(T732_Bean t732_B){
			boolean flag=false;
			
			Connection conn=DBConnection.instance.getConnection();
			
			try {
				flag=DAOUtil.update(t732_B, tableName, key, field, conn);
				System.out.println(flag);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}finally{
				DBConnection.close(conn);
			}
			return flag;
		}
		 
		 /**
			 * 模板导入
			 * @param diCourseCategories
			 * @return
			 *
			 * @time: 2014-5-14/下午02:34:23
			 */
			public boolean batchInsert(List<T732_Bean> list){
				
				boolean flag = false ;
				Connection conn = DBConnection.instance.getConnection() ;
				
				String tempfield = "AttendClassTerm,LeaderName,LeaderTeaID,AdminTitle," +
						"AttendClassTime,LectureTea,LectureTeaID,LectureCS," +
						"CSID,SetCSUnit,UnitID,LectureClass,Evaluate,Time,Note,FillUnitID,CheckState";
				try{
					flag = DAOUtil.batchInsert(list, tableName, tempfield, conn) ;
				}catch(Exception e){
					e.printStackTrace() ;
					return flag ;
				}finally{
					DBConnection.close(conn);
				}
				
				return flag ;
				
			}
		
		public boolean deleteByIds(String ids){
			int  flag=0;
			StringBuffer sql=new StringBuffer();
			sql.append(" delete from " + tableName);
			sql.append(" where " +  key  + " in " + ids);
			
			Connection conn=DBConnection.instance.getConnection();
			Statement st=null;
			
			try {
				st=conn.createStatement();
				flag=st.executeUpdate(sql.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}finally{
				DBConnection.close(st);	
				DBConnection.close(conn);
			}
			if (flag==0) {
				return false;
			} else {
	            return true;
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

		public String getTableName() {
			return tableName;
		}

		public void setTableName(String tableName) {
			this.tableName = tableName;
		}

		
		
		
}
