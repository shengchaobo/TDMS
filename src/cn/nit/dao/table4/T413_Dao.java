package cn.nit.dao.table4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.table4.T413_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T413_Dao {
	
	private String tableName = "T413_HireTeaInfo_TeaPer$" ;
	private String tableName1 = "DiDegree" ;
	private String tableName4 = "DiEducation" ;
	private String tableName2 = "DiTitleLevel" ;
	private String tableName3 = "DiTutorType" ;
	private String field = "Name,TeaId,Gender,Birthday,HireBeginTime,TeaState,HireTimeLen,UnitId,"+
	"UnitName,Education,TopDegree,TechTitle,SubjectClass,WorkUnitType,TutorType,Region,Note";
	
	
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T413_Bean> getAllList(){
		
		String sql = "select " + field + " from " + tableName ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T413_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T413_Bean.class) ;
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
	 * 分 页查询
	 * 
	 */
	public List<T413_Bean> queryPageList(int pageSize, int showPage){
				
		String queryPageSql = "select top " + pageSize + 
		"Name,TeaId,Gender,Birthday,HireBeginTime,TeaState,HireTimeLen,UnitId,"+
		"UnitName," + tableName4 + ".Education,Degree AS TopDegree,TitleLevel AS TechTitle,SubjectClass,WorkUnitType," + tableName3 + ".TutorType,Region,Note"
		+ " from " + tableName + 
		" left join " + tableName1+ " on " + "TopDegree=" + tableName1 + ".IndexID " +
		" left join " + tableName2+ " on " + "TechTitle=" + tableName2 + ".IndexID " +
		" left join " + tableName4+ " on " + tableName + ".Education=" + tableName4 + ".IndexID " +
		" left join " + tableName3+ " on " + tableName + ".TutorType=" + tableName3 + ".IndexID " +
		" where (SeqNumber not in (select top " + pageSize * (showPage-1) + " SeqNumber from "+
		tableName + " order by SeqNumber)) order by SeqNumber" ;
		System.out.println(queryPageSql);
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T413_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T413_Bean.class) ;
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
	 * 插入数据
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */
	public boolean insert(T413_Bean majorteaBean){
		
		Connection conn = DBConnection.instance.getConnection() ;
		return DAOUtil.insert(majorteaBean, tableName, field, conn) ;
	}
	
	
	public static void main(String args[]){
		T413_Dao testDao =  new T413_Dao() ;
		System.out.println(testDao.getAllList().size()) ;
	}


}
