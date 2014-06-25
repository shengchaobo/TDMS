package cn.nit.dao.table7;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


import cn.nit.bean.table4.T412_Bean;
import cn.nit.bean.table7.T711_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.pojo.table7.T711POJO;
import cn.nit.util.DAOUtil;

public class T711_DAO {

	
	private String tableName="T711_TeaManagerAwardInfo_TeaTea$";
	
	private String key="SeqNumber";
	
	private String field="TeaUnit,UnitID,Name,TeaID,AwardName,AwardLevel,AwardRank,AwardTime,AwardFromUnit,AppvlID,JoinTeaNum,OtherJoinTeaInfo,Time,Note,FillTeaID,FillUnitID,audit";


    public boolean insert(T711_Bean teaManagerAwardInfo)
    {
    	boolean flag = false;
    	Connection conn=DBConnection.instance.getConnection();
    	
    	try {
			flag=DAOUtil.insert(teaManagerAwardInfo, tableName, field, conn);
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
    	sql.append("select count(*)");
    	sql.append(" from " + tableName + " as t, DiAwardLevel adl");
    	sql.append(" where adl.IndexID=t.AwardLevel") ; 	
    	int total=0;
    	
    	if(fillUnitId!=null && !fillUnitId.equals("")){
    		sql.append("and FillUnit=" + fillUnitId);	
    	}
    	
    	if(conditions!=null && !conditions.equals("")){
    		sql.append(conditions);
    	}
    	
    	
    	Connection conn=DBConnection.instance.getConnection();
    	    	
    	Statement st=null;
    	ResultSet rs=null;
    	        
    	        
    	try {
			st=conn.createStatement();
			rs=st.executeQuery(sql.toString());
			
			if(rs == null){
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
  
    public List<T711POJO> auditingData(String conditions,String fillUnitId,int page,int rows){
		
    	
    	StringBuffer sql=new StringBuffer();
    	
    	List<T711POJO> list=null;
    	
    	sql.append("select t.SeqNumber,t.TeaUnit,t.UnitID,t.Name,t.TeaID,t.AwardName,adl.AwardLevel as AwardLevel,t.AwardLevel as AwardLevelID,t.AwardRank,t.AwardTime,t.AwardFromUnit,t.AppvlID,t.JoinTeaNum,t.OtherJoinTeaInfo,t.Time,t.Note");
    	sql.append(" from " + tableName + " as t, DiAwardLevel adl");
    	sql.append(" where adl.IndexID=t.AwardLevel") ;
    	
    	
    	//System.out.println(123);
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
			list=DAOUtil.getList(rs, T711POJO.class);
			
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
	public List<T711_Bean> totalList(){
		
		String sql = "select " + key+ "," +field + " from " + tableName;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T711_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T711_Bean.class) ;
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
    
    public boolean update(T711_Bean t711_Bean){
    	boolean flag=false;
    	
    	Connection conn =DBConnection.instance.getConnection();
    	try {
			flag=DAOUtil.update(t711_Bean, tableName, key, field, conn);
			
			System.out.println(flag);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return flag;
		}finally{
			DBConnection.close(conn);
		}
    	  	
    	return flag;
    	
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
    
 public String getTableName() {
	return tableName;
}

 public void setTableName(String tableName) {
	this.tableName = tableName;
}

	public static void main(String args[]){
    	T711_DAO t=new T711_DAO();
 
    	
    	System.out.println(t.auditingData(null, null, 0, 0));
    	
    }

}
