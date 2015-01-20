package cn.nit.dao.table5;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.table5.T553_Bean;
import cn.nit.constants.Constants;
import cn.nit.dbconnection.DBConnection;
import cn.nit.pojo.table5.T553POJO;
import cn.nit.util.DAOUtil;

public class T553_DAO {

    private String tableName="T553_ExcellentUndergra_Stu$";
	
	private String key="SeqNumber";
	
	private String field="AwardName,AwardStuName,StuID,TeaUnit,FromClass,AwardLevel," +
			"AwardTime,Time,Note,FillTeaID,FillUnitID,audit,CheckState";	
	

    public boolean insert(T553_Bean t553_bean)
    {
    	boolean flag = false;
    	Connection conn=DBConnection.instance.getConnection();
    	
    	try {
			flag=DAOUtil.insert(t553_bean, tableName, field, conn);
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
     	sql.append("select count(*) AS Count");
     	sql.append(" from " + tableName + " as t, DiAwardLevel adl");
     	sql.append(" where adl.IndexID=t.AwardLevel ") ; 	
     	int total=0;
     	
//     	if(fillUnitId!=null && !fillUnitId.equals("")){
//     		sql.append("and FillUnit=" + fillUnitId);	
//     	}
     	
     	if(conditions!=null && !conditions.equals("")){
     		sql.append(conditions);
     	}
     	System.out.println(sql);
     	
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
 				total = rs.getInt("Count");
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
   
     public List<T553POJO> auditingData(String conditions,String fillUnitId,int page,int rows){
     	
     	StringBuffer sql=new StringBuffer();
     	
     	List<T553POJO> list=null;
     	
     	sql.append("select t.SeqNumber,t.AwardName,t.AwardStuName,t.StuID,t.TeaUnit," +
     			"t.FromClass,adl.AwardLevel as AwardLevel," +
     			"t.AwardLevel as AwardLevelID,t.AwardTime,t.Time,t.Note,t.CheckState ");
     	sql.append(" from " + tableName + " as t, DiAwardLevel adl");
     	sql.append(" where adl.IndexID=t.AwardLevel ") ;
     	
     	
//     	//System.out.println(123);
//     	if(fillUnitId != null && !fillUnitId.equals("")){
// 			sql.append(" and FillUnitID=" + fillUnitId) ;
// 		}
 		
 		if(conditions != null){
 			sql.append(conditions) ;
 		}
 		
// 		sql.append(" order by SeqNumber desc") ;
 		
 		Connection conn=DBConnection.instance.getConnection();
 		
 		Statement st=null;
 	    ResultSet rs=null;
 	    
 	    try {
 			st=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
 			st.setMaxRows(page * rows);
 			rs=st.executeQuery(sql.toString());
 			rs.absolute((page - 1) * rows);
 			list=DAOUtil.getList(rs, T553POJO.class);
 			
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			return null;
 		}

     	return list;
     	
     	
     }
     /**
 	 * 审核数据导出
 	 * @return
 	 *
 	 * @time: 2014-5-14/下午02:34:42
 	 */
 	public List<T553POJO> totalList(String year,int checkState){
 		StringBuffer sql=new StringBuffer();
 		sql.append("select t.SeqNumber,t.AwardName,t.AwardStuName,t.StuID,t.TeaUnit," +
 				"t.FromClass,adl.AwardLevel as AwardLevel,t.AwardLevel as AwardLevelID," +
 				"t.AwardTime,t.Time,t.Note,t.CheckState ");
     	sql.append(" from " + tableName + " as t, DiAwardLevel adl");
     	sql.append(" where adl.IndexID=t.AwardLevel") ;
     	sql.append(" and t.Time like '"+year+"%' and t.CheckState = "+checkState);
     	
 		Connection conn = DBConnection.instance.getConnection() ;
 		Statement st = null ;
 		ResultSet rs = null ;
 		List<T553POJO> list = null ;
 		System.out.println(sql);
 		try{
 			st = conn.createStatement() ;
 			rs = st.executeQuery(sql.toString()) ;
 			list = DAOUtil.getList(rs, T553POJO.class) ;
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
     
     public boolean update(T553_Bean t553_Bean){
     	boolean flag=false;
     	
     	Connection conn =DBConnection.instance.getConnection();
     	try {
 			flag=DAOUtil.update(t553_Bean, tableName, key, field, conn);
 			
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
     /**
 	 * 模板导入
 	 * @param diCourseCategories
 	 * @return
 	 *
 	 * @time: 2014-5-14/下午02:34:23
 	 */
 	public boolean batchInsert(List<T553_Bean> list){
 		
 		boolean flag = false ;
 		Connection conn = DBConnection.instance.getConnection() ;
 		
// 		String tempfield = "AwardName,AwardStuName,StuID,TeaUnit,FromClass,AwardLevel,AwardTime,Time,Note,FillUnitID";
 		try{
 			flag = DAOUtil.batchInsert(list, tableName, field, conn) ;
 		}catch(Exception e){
 			e.printStackTrace() ;
 			return flag ;
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

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
     
     public static void main(String args[]){
    		 T553_DAO dao = new T553_DAO();
    		 boolean flag = dao.updatCheck();
    		 System.out.println(flag);
     }
     
     
     
     
     
     
}

     
