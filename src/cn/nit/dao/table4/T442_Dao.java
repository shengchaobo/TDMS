package cn.nit.dao.table4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.table4.T42_Bean;
import cn.nit.bean.table4.T442_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T442_Dao {
	
	private String tableName = "T442_GraTutorInfo_Gra$" ;
	private String tableName3 = "DiTutorType" ;
	private String field = "TutorName,TeaId,TutorType,SubjectClass,MajorName,MajorId,ResField,FromUnit,UnitId,Time,Note";
	private String keyfield = "SeqNumber";
	
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T442_Bean> totalList(){
		
		String sql = "select " + " " + keyfield + "," + 
		"TutorName,TeaId," + tableName3 + ".TutorType,SubjectClass,MajorName,MajorId,ResField,FromUnit,UnitId,Time,Note" +
		" from " + tableName +
		" left join " + tableName3+ " on " + tableName + ".TutorType=" + tableName3 + ".IndexID " ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T442_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T442_Bean.class) ;
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
	 * 分 页查询总数
	 * 
	 */
	public int totalQueryPageList(String conditions, String fillunitID){
		
		String Cond = "1=1";
		
		int total = 0;
		if(conditions != null && !conditions.equals("")){
			Cond = Cond + conditions;
		}
		
		if(fillunitID != null && !fillunitID.equals("")){
			Cond = Cond + " and FillUnitID=" + fillunitID;
		}
				
		String queryPageSql = "select count(*) " 
		+ " from " + tableName + 
		" left join " + tableName3+ " on " + tableName + ".TutorType=" + tableName3 + ".IndexID " +
		" where " + Cond ;
		System.out.println(queryPageSql);
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			if(rs == null){
				return total ;
			}
			
			while(rs.next()){
				total = rs.getInt(1) ;
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return 0 ;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
		
		return total ;
	}
	
	/**
	 * 分 页查询
	 * 
	 */
	public List<T442_Bean> queryPageList(String conditions, String fillunitID, int pageSize, int showPage){

		
		String Cond = "1=1";
		
		if(conditions != null && !conditions.equals("")){
			Cond = Cond + conditions;
		}
		
		if(fillunitID != null && !fillunitID.equals("")){
			Cond = Cond + " and FillUnitID=" + fillunitID;
		}
		
		String queryPageSql = "select top " + pageSize + 
		" SeqNumber,TutorName,TeaId," + tableName3 + ".TutorType,SubjectClass,MajorName,MajorId,ResField,FromUnit,UnitId,Time,Note"
		+ " from " + tableName + 
		" left join " + tableName3+ " on " + tableName + ".TutorType=" + tableName3 + ".IndexID " +
		" where " + Cond + " and (SeqNumber not in (select top " + pageSize * (showPage-1) + " SeqNumber from "+
		tableName  + " where " + Cond +  "  order by SeqNumber)) order by SeqNumber" ;
		System.out.println(queryPageSql);
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T442_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs,T442_Bean.class) ;
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
	 * 模板导入
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */
	public boolean batchInsert(List<T442_Bean> list){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		
		String tempfield = "TutorName,TeaId,TutorType,SubjectClass,MajorName,MajorId,ResField,FromUnit,UnitId,Time";
		
		try{
			flag = DAOUtil.batchInsert(list, tableName, tempfield, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}
		
		return flag ;
		
	}
	
	
	/**
	 * 插入数据
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */
	public boolean insert(T442_Bean tutor){
		
		Connection conn = DBConnection.instance.getConnection() ;
		return DAOUtil.insert(tutor, tableName, field, conn) ;
	}
	
	/**
	 * 删除数据
	 * @param 
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */
	public boolean deleteByIds(String ids) {

		int flag = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("delete from " + tableName);
		sql.append(" where " + keyfield + " in " + ids);
		System.out.println(sql.toString());
		Connection conn = DBConnection.instance.getConnection();
		Statement st = null;

		try {
			st = conn.createStatement();
			flag = st.executeUpdate(sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (flag == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 更新数据
	 * @param 
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */
	public boolean update(T442_Bean bean){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			
			String updatefield = "TutorName,TeaId,TutorType,SubjectClass,MajorName,MajorId,ResField,FromUnit,UnitId,Note";
			
			String temp1 = updatefield;
			
			if(bean.getTutorType().trim().equals("")){
				String a = "TutorType,";
			    temp1 = updatefield.replaceAll(a , "");
			}
						
			flag = DAOUtil.update(bean, tableName, keyfield, temp1, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
		}
		
		return flag ;
	}
	
	
	public static void main(String args[]){
		T442_Dao testDao =  new T442_Dao() ;
		System.out.println(testDao.totalList().size()) ;
	}

}
