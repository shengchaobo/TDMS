package cn.nit.dao.table5;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.table5.T512_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.pojo.table5.T512POJO;
import cn.nit.util.DAOUtil;

public class T512_DAO {

    private String tableName="T512_CSInfo_TeaTea$";
	
	private String key="SeqNumber";
	
	private String field="Term,CSUnit,UnitID,CSMajorName,CSMajorID,CSName,CSID,CSType,CSNature,PubCSType,IsDoubleCS,Credit,SumCSHour,TheoryCSHour,PraCSHour,ExamWay,PlanTime,CSGrade,CSClass,ClassID,ClassInfo,StuNum,CSTea,IsAccordJob,TeaTitle,BookUseInfo,IsPlanbook,IsAwardbook,Time,Note,FillTeaID,FillUnitID";
	

    public boolean insert(T512_Bean t512_bean)
    {
    	boolean flag = false;
    	Connection conn=DBConnection.instance.getConnection();
    	
    	try {
			flag=DAOUtil.insert(t512_bean, tableName, field, conn);
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
     	sql.append(" from " + tableName + " as t,DiCourseChar csn,DiCourseCategories cst") ;
		sql.append(" where csn.IndexID=t.CSNature and cst.IndexID=t.CSType") ;
     	int total=0;
     	
     	if(fillUnitId!=null && !fillUnitId.equals("")){
     		sql.append(" and FillUnitID=" + fillUnitId);	
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
   
     public List<T512POJO> auditingData(String conditions,String fillUnitId,int page,int rows){
 		
     	
     	StringBuffer sql=new StringBuffer();
     	
     	List<T512POJO> list=null;
     	
    	sql.append("select t.SeqNumber,t.Term,t.CSUnit,t.UnitID,t.CSMajorName,t.CSMajorID,t.CSName,t.CSID,cst.CourseCategories as CSType,t.CSType as CSTypeID,csn.CourseChar as CSNature,t.CSNature as CSNatureID,t.PubCSType,t.IsDoubleCS,t.Credit,t.SumCSHour,t.TheoryCSHour,t.PraCSHour,t.ExamWay,t.PlanTime,t.CSGrade,t.CSClass,t.ClassID,t.ClassInfo,t.StuNum,t.CSTea,t.IsAccordJob,t.TeaTitle,t.BookUseInfo,t.IsPlanbook,t.IsAwardbook,t.Time,t.Note") ;
		sql.append(" from " + tableName + " as t,DiCourseChar csn,DiCourseCategories cst") ;
		sql.append(" where csn.IndexID=t.CSNature and cst.IndexID=t.CSType") ;
     	
     	
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
 			list=DAOUtil.getList(rs, T512POJO.class);
 			
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
 	public List<T512POJO> totalList(String year,String fillUnitID){
 		StringBuffer sql=new StringBuffer();
 		sql.append("select t.SeqNumber,t.Term,t.CSUnit,t.UnitID,t.CSMajorName,t.CSMajorID,t.CSName,t.CSID,cst.CourseCategories as CSType,t.CSType as CSTypeID,csn.CourseChar as CSNature,t.CSNature as CSNatureID,t.PubCSType,t.IsDoubleCS,t.Credit,t.SumCSHour,t.TheoryCSHour,t.PraCSHour,t.ExamWay,t.PlanTime,t.CSGrade,t.CSClass,t.ClassID,t.ClassInfo,t.StuNum,t.CSTea,t.IsAccordJob,t.TeaTitle,t.BookUseInfo,t.IsPlanbook,t.IsAwardbook,t.Time,t.Note") ;
		sql.append(" from " + tableName + " as t,DiCourseChar csn,DiCourseCategories cst") ;
		sql.append(" where csn.IndexID=t.CSNature and cst.IndexID=t.CSType") ;
	 	sql.append(" and FillUnitID=" + "'" + fillUnitID + "'");
 		Connection conn = DBConnection.instance.getConnection() ;
 		Statement st = null ;
 		ResultSet rs = null ;
 		List<T512POJO> list = null ;
 		System.out.println(sql);
 		try{
 			st = conn.createStatement() ;
 			rs = st.executeQuery(sql.toString()) ;
 			list = DAOUtil.getList(rs, T512POJO.class) ;
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
     
     public boolean update(T512_Bean t512_Bean){
     	boolean flag=false;
     	
     	Connection conn =DBConnection.instance.getConnection();
     	try {
 			flag=DAOUtil.update(t512_Bean, tableName, key, field, conn);
 			
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
 	public boolean batchInsert(List<T512_Bean> list){
 		
 		boolean flag = false ;
 		Connection conn = DBConnection.instance.getConnection() ;
 		
 		String tempfield = "Term,CSUnit,UnitID,CSMajorName,CSMajorID,CSName,CSID,CSType,CSNature,PubCSType,IsDoubleCS,Credit,SumCSHour,TheoryCSHour,PraCSHour,ExamWay,PlanTime,CSGrade,CSClass,ClassID,ClassInfo,StuNum,CSTea,IsAccordJob,TeaTitle,BookUseInfo,IsPlanbook,IsAwardbook,Time,Note,FillUnitID";
 		try{
 			flag = DAOUtil.batchInsert(list, tableName, tempfield, conn) ;
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

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
     
     
     
     
     
     
     
     
}

     
