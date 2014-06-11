package cn.nit.dao.table4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.table4.T444_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T444_Dao {
	
	private String tableName = "T444_HighLevelResTeam_Res$" ;
	private String tableName2 = "DiResearchTeam" ;
	private String field = "ResField,Type,GainTime,Leader,TeaId,OtherTeamNum,OtherTeamPer,Time,Note";
	
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T444_Bean> getAllList(){
		
		String sql = "select " + field + " from " + tableName ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T444_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T444_Bean.class) ;
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
	public List<T444_Bean> queryPageList(int pageSize, int showPage){
				
		String queryPageSql = "select top " + pageSize + 
		" ResField,ResearchTeam AS Type,GainTime,Leader,TeaId,OtherTeamNum,OtherTeamPer,Time,Note"
		+ " from " + tableName + 
		" left join " + tableName2+ " on " + "Type=" + tableName2 + ".IndexId " +
		" where (SeqNumber not in (select top " + pageSize * (showPage-1) + " SeqNumber from "+
		tableName + " order by SeqNumber)) order by SeqNumber" ;
		System.out.println(queryPageSql);
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T444_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs,T444_Bean.class) ;
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
	public boolean insert(T444_Bean team){
		
		Connection conn = DBConnection.instance.getConnection() ;
		return DAOUtil.insert(team, tableName, field, conn) ;
	}
	
	
	public static void main(String args[]){
		T444_Dao testDao =  new T444_Dao() ;
		System.out.println(testDao.getAllList().size()) ;
	}

}
