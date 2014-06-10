package cn.nit.dao.table4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.table4.T42_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T42_Dao {
	
	private String tableName = "T42_SchLeaderInfo_PartyOffice$" ;
	private String tableName1 = "DiDegree" ;
	private String tableName2 = "DiEducation" ;
	private String field = "Name,TeaId,Duty,Gender,Birthday,JoinSchTime,Education,TopDegree,"+
	"MajTechTitle,ForCharge,Resume,Time,Note";
	
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T42_Bean> getAllList(){
		
		String sql = "select " + field + " from " + tableName ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T42_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T42_Bean.class) ;
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
	public List<T42_Bean> queryPageList(int pageSize, int showPage){
				
		String queryPageSql = "select top " + pageSize + 
		"Name,TeaId,Duty,Gender,Birthday,JoinSchTime," + tableName2 + ".Education,Degree AS TopDegree,"+
		"MajTechTitle,ForCharge,Resume,Time,Note"
		+ " from " + tableName + 
		" left join " + tableName1+ " on " + "TopDegree=" + tableName1 + ".IndexID " +
		" left join " + tableName2+ " on " + tableName + ".Education=" + tableName2 + ".IndexID " +
		" where (SeqNumber not in (select top " + pageSize * (showPage-1) + " SeqNumber from "+
		tableName + " order by SeqNumber)) order by SeqNumber" ;
		System.out.println(queryPageSql);
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T42_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs,T42_Bean.class) ;
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
	public boolean insert(T42_Bean schLeader){
		
		Connection conn = DBConnection.instance.getConnection() ;
		return DAOUtil.insert(schLeader, tableName, field, conn) ;
	}
	
	
	public static void main(String args[]){
		T42_Dao testDao =  new T42_Dao() ;
		System.out.println(testDao.getAllList().size()) ;
	}


}
