package cn.nit.dao.table7;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.table7.T734_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.pojo.table7.T734POJO;
import cn.nit.util.DAOUtil;

public class T734_DAO {
 
	
	private String tableName="T734_TeachAccident_Tea$";
	
	private String key="SeqNumber";
	
	private String field="TeaName,TeaID,FromDept,UnitID,AccidentSite,Cause,HandingTime,AccidentLevel,HandingID,Time,Note,FillTeaID,FillUnitID,audit";

    public boolean insert(T734_Bean teachAcc){
    	boolean flag=false;
    	
    	Connection conn=DBConnection.instance.getConnection();
    	
    	try {
			flag=DAOUtil.insert(teachAcc, tableName, field, conn);
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
		sql.append(" from "+ tableName +" as t, DiDepartment dt");
		sql.append(" where dt.UnitID=t.UnitID") ;
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
		public List<T734POJO> auditingData(String conditions,String fillUnitId,int page,int rows){
			List<T734POJO> list=null;
			StringBuffer sql=new StringBuffer();
			
			sql.append("select  t.SeqNumber,t.TeaName,t.TeaID,t.FromDept,dt.UnitID as UnitID,t.UnitID as UnitIDD,t.AccidentSite,t.Cause,t.HandingTime,t.AccidentLevel,t.HandingID,t.Time,t.Note");
			sql.append(" from "+ tableName +" as t, DiDepartment dt");
			sql.append(" where dt.UnitID=t.UnitID") ;
			
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
				list=DAOUtil.getList(rs, T734POJO.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}		
			return list;	
		}
		 public boolean update(T734_Bean t734_B){
			boolean flag=false;
			
			Connection conn=DBConnection.instance.getConnection();
			
			try {
				flag=DAOUtil.update(t734_B, tableName, key, field, conn);
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
