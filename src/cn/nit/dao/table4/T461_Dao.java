package cn.nit.dao.table4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.table4.T461_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T461_Dao {
	
	private String tableName = "T461_FameTeaAward_Per$" ;
	private String tableName1 = "DiAwardLevel" ;
	private String tableName2 = "DiAwardType" ;
	private String field = "Name,TeaId,FromTeaUnit,UnitId,AwardType,AwardLevel,AwardFromUnit," +
			"GainAwardTime,AppvlID,OtherTeaNum,OtherTeaInfo,Time,Note";
	
	
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T461_Bean> getAllList(String param){
		
		String cond = null;
		
		if(param.equals("1")){
			cond = tableName + ".AwardType = '51000'";
		}
		else if(param.equals("2")){
			cond = tableName + ".AwardType = '51001' or" + tableName + ".AwardType = '51002'";
		}
		else if(param.equals("3")){
			cond = tableName + ".AwardType = '51003'";
		}
		else if(param.equals("4")){
			cond = tableName + ".AwardType = '51004' or" + tableName + ".AwardType = '51005'";
		}
		else if(param.equals("5")){
			cond = tableName + ".AwardType = '51006'";
		}
		else if(param.equals("6")){
			cond = tableName + ".AwardType = '51007'";
		}
		
		String sql = "select " + field + " from " + tableName + " where " + cond ;;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T461_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs,T461_Bean.class) ;
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
	public List<T461_Bean> queryPageList(int pageSize, int showPage, String param){
		
		String cond = null;
		
		if(param.equals("1")){
			cond = "T461_FameTeaAward_Per$.AwardType = '51000'";
		}
		else if(param.equals("2")){
			cond = "T461_FameTeaAward_Per$.AwardType = '51001' or T461_FameTeaAward_Per$.AwardType = '51002'";
		}
		else if(param.equals("3")){
			cond = "T461_FameTeaAward_Per$.AwardType = '51003'";
		}
		else if(param.equals("4")){
			cond = "T461_FameTeaAward_Per$.AwardType = '51004' or T461_FameTeaAward_Per$.AwardType = '51005'";
		}
		else if(param.equals("5")){
			cond = "T461_FameTeaAward_Per$.AwardType = '51006'";
		}
		else if(param.equals("6")){
			cond = "T461_FameTeaAward_Per$.AwardType = '51007'";
		}
				
		String queryPageSql = "select top " + pageSize + 
		" Name,TeaId,FromTeaUnit,UnitId,DiAwardType.AwardType,DiAwardLevel.AwardLevel,AwardFromUnit," +
		"GainAwardTime,AppvlID,OtherTeaNum,OtherTeaInfo,Time,Note"
		+ " from " + tableName + 
		" left join " + tableName1+ " on " + tableName + ".AwardLevel=" + tableName1 + ".IndexID " +
		" left join " + tableName2+ " on " + tableName + ".AwardType=" + tableName2 + ".IndexID " +
		" where " + cond + "and (SeqNumber not in (select top " + pageSize * (showPage-1) + " SeqNumber from "+
		tableName + " where " + cond + " order by SeqNumber)) order by SeqNumber" ;
		System.out.println(queryPageSql);
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T461_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T461_Bean.class) ;
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
	public boolean insert(T461_Bean teaHonor){
		
		Connection conn = DBConnection.instance.getConnection() ;
		return DAOUtil.insert(teaHonor, tableName, field, conn) ;
	}
	
	
	public static void main(String args[]){
		T461_Dao testDao =  new T461_Dao() ;
		System.out.println(testDao.getAllList("1")) ;
	}


}
