package cn.nit.dao.table7;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.table7.T743_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.pojo.table7.T743POJO;
import cn.nit.util.DAOUtil;

public class T743_DAO {
	
	private String tableName="T743_CourseBuildAssess_AC$";
	
	private String key="SeqNumber";
	
	private String field="CSName,CSID,SetCSUnit,UnitID,CSType,CSNature,CSLeader,TeaID,AssessYear,AssessResult,AppvlID,Time,Note,FillTeaID,FillUnitID,audit";

	public boolean insert(T743_Bean t743_B){
		boolean flag=false;
		
		Connection conn=DBConnection.instance.getConnection();
		
		try {
			flag=DAOUtil.insert(t743_B, tableName, field, conn);
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
		  sql.append(" from "+ tableName +" as t, DiCourseChar csn, DiCourseCategories cst");
		  sql.append(" where csn.IndexID=t.CSNature and cst.IndexID=t.CSType") ;
		  int total=0;
		
		  Connection conn=DBConnection.instance.getConnection();
		  Statement st=null;
		  ResultSet rs=null;
		

		if(fillUnitId!=null && !fillUnitId.equals("")){
			sql.append("and FillUnit=" + fillUnitId);	
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
			
		}
		
		return total;

	}
	     
	    /**
		 * @param conditions 查询条件
		 * @param fillUnitId 填报人单位号，如果为空，则查询所有未审核的数据，<br>如果不为空，则查询填报人自己单位的所有的数据
		 * @return
		 */
		public List<T743POJO> auditingData(String conditions,String fillUnitId,int page,int rows){
			List<T743POJO> list=null;
			StringBuffer sql=new StringBuffer();
			
			sql.append("select  t.SeqNumber,t.CSName,t.CSID,t.SetCSUnit,t.UnitID,cst.CourseCategories as CSType,t.CSType as CSTypeID,csn.CourseChar as CSNature,t.CSNature as CSNatureID,t.CSLeader,t.TeaID,t.AssessYear,t.AssessResult,t.AppvlID,t.Time,t.Note");
		    sql.append(" from "+ tableName +" as t, DiCourseChar csn, DiCourseCategories cst");
			sql.append(" where csn.IndexID=t.CSNature and cst.IndexID=t.CSType") ;
			  
			if(fillUnitId != null && !fillUnitId.equals("")){
				sql.append(" and FillUnitID=" + fillUnitId) ;
			}
			
			if(conditions != null){
				sql.append(conditions) ;
			}
			
			sql.append(" order by SeqNumber desc") ;
			
			Connection conn=DBConnection.instance.getConnection();
			
			Statement st=null;
			ResultSet rs=null;
			
			try {
				
				//System.out.println(sql);
				st=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				st.setMaxRows(page * rows);
				rs=st.executeQuery(sql.toString());
				rs.absolute((page - 1) * rows);
				list=DAOUtil.getList(rs, T743POJO.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}		
			return list;	
		}
		 public boolean update(T743_Bean t743_B){
			boolean flag=false;
			
			Connection conn=DBConnection.instance.getConnection();
			
			try {
				flag=DAOUtil.update(t743_B, tableName, key, field, conn);
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
			}
			if (flag==0) {
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
