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
	 * 获得分页查询的总数
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public int totalQueryPageList(String conditions, String fillunitID){
		
		String Cond = "(TeaFlag is null or TeaFlag != '外聘')";
		int total = 0;
		if(conditions != null && !conditions.equals("")){
			Cond = Cond + conditions;
		}
		
		if(fillunitID != null && !fillunitID.equals("")){
			Cond = Cond + " and FillUnitID=" + fillunitID;
		}
		
		
		String queryPageSql = "select count(*) " 
		+ " from " + tableName + 
		" left join " + tableName1+ " on " + "TopDegree=" + tableName1 + ".IndexID " +
		" left join " + tableName2+ " on " + "MajTechTitle=" + tableName2 + ".IndexID " +
		" left join " + tableName3+ " on " + "TeaTitle=" + tableName3 + ".IndexID " +
		" left join " + tableName4+ " on " + tableName + ".Education=" + tableName4 + ".IndexID " +
		" left join " + tableName5+ " on " + tableName + ".Source=" + tableName5 + ".IndexID " +
		" left join " + tableName6+ " on " + tableName + ".IDCode=" + tableName6 + ".IndexID " +
		" where " + Cond ;
		
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
		}
		
		return total ;
	}
	
	/**
	 * 分 页查询
	 * 
	 */
	public List<T411_Bean> queryPageList(String conditions, String fillunitID, int pageSize, int showPage){
		//这里的fillunitID用于教学单位这一角色，不同学院同属于一个角色，用到相同的报表，但操作的内容不同
		
		String Cond = "(TeaFlag is null or TeaFlag != '外聘')";
		
		if(conditions != null && !conditions.equals("")){
			Cond = Cond + conditions;
		}
		
		if(fillunitID != null && !fillunitID.equals("")){
			Cond = Cond + " and FillUnitID=" + fillunitID;
		}
		
		
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
		" where " + Cond + " and (TeaID not in (select top " + pageSize * (showPage-1) + " TeaID from "+
		tableName + " where " + Cond +  " order by TeaID)) order by TeaID " ;
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
	
	public boolean update(T411_Bean bean){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			
			String temp = field.replaceAll("TeaId,", "");
			String temp0 = temp; 
			if(bean.getIdcode().trim().equals("")){
				String a = "idcode,";
				temp0 = temp.replaceAll(a,"");
			}
			
			String temp1 = temp0;
			
			if(bean.getEducation().trim().equals("")){
				String a = "Education,";
			    temp1 = temp0.replaceAll(a , "");
			}
			
			String temp2 = temp1;
			
			if(bean.getTopDegree().trim().equals("")){
				temp2 = temp1.replaceAll("TopDegree," , "");
			}
			
			String temp3 = temp2;
			if(bean.getMajTechTitle().trim().equals("")){
				temp3 = temp2.replaceAll("MajTechTitle," , "");
			}
			
			String temp4 = temp3;
			if(bean.getSource().trim().equals("")){
				temp4 = temp3.replaceAll("Source,", "");
			}	
			
			String temp5 = temp4;
			if(bean.getTeaTitle().trim().equals("")){
				temp5 = temp4.replaceFirst("TeaTitle,", "");
			}
			//System.out.println(temp5);
			//System.out.println(bean.getTeaId());
			flag = DAOUtil.update(bean, tableName, "teaId", temp5, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
		}
		
		return flag ;
	}
	
/*	public boolean deleteByIds(String ids){
		
		int flag = 0 ;
		StringBuffer sql = new StringBuffer() ;
		sql.append("delete from " + tableName) ;
		sql.append(" where " + "teaId" + " in " + ids) ;
		System.out.println(sql);
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
	}*/
	
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
		
		String a = testDao.field;
		String b = "Education,";
		String c = a.replaceAll(b,"");
		System.out.println(c);
	}

}
