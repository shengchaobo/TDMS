package cn.nit.dao.table7;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.table7.T721_Bean;
import cn.nit.dbconnection.DBConnection;

import cn.nit.pojo.table7.T721POJO;
import cn.nit.util.DAOUtil;

public class T721_DAO {
	
	private String tableName="T721_TeachResItem_Tea$";
	
	private String key="SeqNumber";
	
	private String field="ItemName,TeaUnit,UnitID,Leader,TeaID,OtherTeaNum,OtherTea,ItemLevel,ItemSetUpTime,ReceptTime,ApplvExp,SchSupportExp,AppvlID,Time,Note,FillTeaID,FillUnitID,audit";

	

	public boolean insert(T721_Bean teachResItemTea){
		
		boolean flag=false;
		
	
		
		Connection conn=DBConnection.instance.getConnection();
		
		try {
			
			flag=DAOUtil.insert(teachResItemTea, tableName, field, conn);
			
		}
		 catch (Exception e) {
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
		sql.append(" from " + tableName + " as t, DiAwardLevel adl");
		sql.append(" where adl.IndexID=t.ItemLevel ");
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
			
		}
		
		return total;
	
	}
	  /**
			 * @param conditions 查询条件
			 * @param fillUnitId 填报人单位号，如果为空，则查询所有未审核的数据，<br>如果不为空，则查询填报人自己单位的所有的数据
			 * @return
			 */
	
	public List<T721POJO> auditingData(String conditions,String fillUnitId,int page,int rows){
		List<T721POJO> list=null;
		StringBuffer sql=new StringBuffer();
		
		sql.append("select t.SeqNumber,t.ItemName,t.TeaUnit,t.UnitID,t.Leader,t.TeaID,t.OtherTeaNum,t.OtherTea,adl.AwardLevel as ItemLevel,t.ItemLevel as ItemLevelID,t.ItemSetUpTime,t.ReceptTime,t.ApplvExp,t.SchSupportExp,t.AppvlID,t.Time,t.Note");
		sql.append(" from "+ tableName +" as t, DiAwardLevel adl");
		sql.append(" where adl.IndexID=t.ItemLevel ");
		
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
			st=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st.setMaxRows(page * rows);
			rs=st.executeQuery(sql.toString());
			rs.absolute((page - 1) * rows);
			list=DAOUtil.getList(rs, T721POJO.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
		return list;
		
	}
	
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T721POJO> totalList(String year){
		
     StringBuffer sql=new StringBuffer();
		
		sql.append("select t.SeqNumber,t.ItemName,t.TeaUnit,t.UnitID,t.Leader,t.TeaID,t.OtherTeaNum,t.OtherTea,adl.AwardLevel as ItemLevel,t.ItemLevel as ItemLevelID,t.ItemSetUpTime,t.ReceptTime,t.ApplvExp,t.SchSupportExp,t.AppvlID,t.Time,t.Note");
		sql.append(" from "+ tableName +" as t, DiAwardLevel adl");
		sql.append(" where adl.IndexID=t.ItemLevel ");
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T721POJO> list = null ;
		System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, T721POJO.class) ;
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
	public boolean update(T721_Bean t721_B){
		boolean flag=false;
		
		Connection conn=DBConnection.instance.getConnection();
		
		try {
			flag=DAOUtil.update(t721_B, tableName, key, field, conn);
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
	public boolean batchInsert(List<T721_Bean> list){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		
		String tempfield = "ItemName,TeaUnit,UnitID,Leader,TeaID,OtherTeaNum,OtherTea,ItemLevel,ItemSetUpTime,ReceptTime,ApplvExp,SchSupportExp,AppvlID,Time,Note,FillUnitID";
		try{
			flag = DAOUtil.batchInsert(list, tableName, tempfield, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
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
