package cn.nit.dao.table4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;

import cn.nit.bean.table4.T411_Bean;
import cn.nit.bean.table4.T431_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T411_Dao {
	
	private String tableName = "T411_TeaBasicInfo_Per$" ;
	private String tableName1 = "DiDegree" ;
	private String tableName4 = "DiEducation" ;
	private String tableName2 = "DiTitleLevel" ;
	private String tableName3 = "DiTitleName" ;
	private String tableName5 = "DiSource" ;
	private String tableName6 = "DiIdentiType" ;
	private String field = "TeaId,TeaName,Gender,Birthday,AdmisTime,TeaState," +
			"BeginWorkTime,idcode,FromOffice,OfficeID,FromUnit,UnitId," +
			"FromTeaResOffice,TeaResOfficeID,Education,TopDegree,GraSch,Major," +
			"AdminLevel,Source,MajTechTitle,TeaTitle,NotTeaTitle,SubjectClass," +
			"DoubleTea,Industry,Engineer,TeaBase,TeaFlag,Note";
	
	
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T411_Bean> getAllList(){
		
		String sql = "select " + field + " from " + tableName ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T411_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T411_Bean.class) ;
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
	public List<T411_Bean> queryPageList(int pageSize, int showPage){
				
		String queryPageSql = "select top " + pageSize + 
		" TeaId,TeaName,Gender,Birthday,AdmisTime,TeaState," +
		"BeginWorkTime,IdentiType AS IDCode,FromOffice,OfficeID,FromUnit,unitID," +
		"FromTeaResOffice,TeaResOfficeID," + tableName4 + ".Education,Degree AS TopDegree,GraSch,Major," +
		"AdminLevel," + tableName5 + ".Source,TitleLevel AS MajTechTitle,TitleName AS TeaTitle,NotTeaTitle,SubjectClass," +
		"DoubleTea,Industry,Engineer,TeaBase,TeaFlag,Note"
		+ " from " + tableName + 
		" left join " + tableName1+ " on " + "TopDegree=" + tableName1 + ".IndexID " +
		" left join " + tableName2+ " on " + "MajTechTitle=" + tableName2 + ".IndexID " +
		" left join " + tableName3+ " on " + "TeaTitle=" + tableName3 + ".IndexID " +
		" left join " + tableName4+ " on " + tableName + ".Education=" + tableName4 + ".IndexID " +
		" left join " + tableName5+ " on " + tableName + ".Source=" + tableName5 + ".IndexID " +
		" left join " + tableName6+ " on " + tableName + ".IDCode=" + tableName6 + ".IndexID " +
		" where (TeaFlag is null or TeaFlag != '外聘') and (TeaID not in (select top " + pageSize * (showPage-1) + " TeaID from "+
		tableName + " order by TeaID)) order by TeaID " ;
		System.out.println(queryPageSql);
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T411_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T411_Bean.class) ;
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
	public boolean insert(T411_Bean teaInfoBean){
		
		Connection conn = DBConnection.instance.getConnection() ;
		if(teaInfoBean.getIdcode().equals("40009")){
			teaInfoBean.setTeaFlag("外聘");
			return DAOUtil.insert(teaInfoBean, tableName, field, conn) ;
		}else{
			return DAOUtil.insert(teaInfoBean, tableName, field, conn) ;
		}
		
	}
	
	/**
	 * 判断T411表中是否存在该教职工
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */
	public boolean hasPerson(String teaID){
		
		Connection conn = DBConnection.instance.getConnection() ;
		String sql = "select " + teaID + " from " + tableName ;
		
		Statement st = null ;
		ResultSet rs = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			if(rs.next()){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return false ;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
		
		return false;
	}
	
	
	/**
	 * 据参数加载43系统列的
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T431_Bean> getT43List(int flag){
		
		String cond;
		
		if (flag == 1){
			cond = "IDCode = '40003' or IDcode = '40004'";
		}
		else if(flag == 2){
			cond = "IDCode = '40001' or IDcode = '40002'";
		}
		else if(flag == 3){
			cond = "IDCode = '40005' or IDcode = '40006'";
		}		
		else if(flag == 4){
			cond = "IDCode = '40007' or IDcode = '40008'";
		}else{
			cond = "IDCode = '40010'";
		}
		
		String sql = "select " + "TeaId,TeaName AS Name,FromOffice AS FromDept,OfficeID AS UnitID," +
		"IdentiType AS StaffType" + " from " + tableName + 
		" left join " + tableName6 + " on " + tableName + ".IDCode=" + tableName6 + ".IndexID " +
		" where " + cond ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T431_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T431_Bean.class) ;
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
	
	public static void main(String args[]){
		T411_Dao testDao =  new T411_Dao() ;
		System.out.println(testDao.hasPerson("2014000001")) ;
	}

}
