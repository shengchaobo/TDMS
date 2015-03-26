package cn.nit.dao.table4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.nit.bean.table3.S31_Bean;
import cn.nit.bean.table4.J413_Bean;
import cn.nit.bean.table4.T411_Bean;
import cn.nit.bean.table4.T431_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class T411_Dao {
	
	private static String tableName = "T411_TeaBasicInfo_Per$" ;
	private String tableName1 = "DiDegree" ;
	private static String tableName4 = "DiEducation" ;
	private static String tableName2 = "DiTitleLevel" ;
	private String tableName3 = "DiTitleName" ;
	private String tableName5 = "DiSource" ;
	private static String tableName6 = "DiIdentiType" ;
	private static String tableName7 = "T442_GraTutorInfo_Gra$";
	private static String tableName8 = "DiEducation";
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
	 * 获得的总数（用于导出）
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T411_Bean> totalList(){
		
		String Cond = "(TeaFlag is null or TeaFlag != '外聘')";
				
		String queryPageSql = "select TeaId,TeaName,Gender,Birthday,AdmisTime,TeaState," +
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
		" where " + Cond ;
		
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
			return null;
		}
		
		return list ;
	}
	
	
	/**
	 * 获得431到436的导出（用于431到436导出）
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T431_Bean> getList(int param){
		
		String cond = null;
		
		if (param == 1){
			cond = "(IDCode = '40003' or IDcode = '40004')";
		}
		else if(param == 2){
			cond = "(IDCode = '40001' or IDcode = '40002')";
		}
		else if(param == 3){
			cond = "(IDCode = '40005' or IDcode = '40006')";
		}		
		else if(param == 4){
			cond = "(IDCode = '40007' or IDcode = '40008')";
		}else{
			cond = "(IDCode = '40010')";
		}
				
		String queryPageSql = "select TeaId,TeaName AS Name,IdentiType AS staffType,FromOffice AS FromDept,OfficeID AS UnitId"
		+ " from " + tableName + 
		" left join " + tableName6+ " on " + tableName + ".IDCode=" + tableName6 + ".IndexID " +
		" where " + cond ;
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T431_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T431_Bean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null;
		}
		
		return list ;
	}
	
	
	/**
	 * 获得的总数（用于教育部导出）
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T411_Bean> totalList1(){
		
				
		String queryPageSql = "select TeaId,TeaName,Gender,Birthday,AdmisTime,TeaState," +
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
		" where "+tableName+".IDCode = '40001' or "+tableName+".IDCode = '40002' or "+tableName+".IDCode = '40005' " +
		"or "+tableName+".IDCode = '40006'"  ;
		
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
			return null;
		}
		
		return list ;
	}
	
	
	//教育部表查询
	
	public List<J413_Bean> getTotalList(String year){
		
		
		String queryPageSql = "select "+tableName+".teaName as teaName,"+tableName+".teaId as teaId,"+tableName+".Gender as gender," +
		tableName+".Birthday as birthday,"+tableName+".AdmisTime as admisTime,"+tableName+".TeaState as teaState," +
		"IdentiType AS IDCode,"+tableName+".unitId as unitId,"+tableName+".fromUnit as fromUnit," +
		tableName4+".Education as education,t.Education AS topDegree," +
		tableName2+".TitleLevel as majTechTitle,"+tableName+".subjectClass as subjectClass,tt.TutorType AS tutorType " +
		" from " + tableName + 
		" left join (select TeaID,Time,DiTutorType.tutorType from " + tableName7+ " left join DiTutorType on "+tableName7+".TutorType = IndexID) as tt on " +tableName+ ".TeaID = tt.TeaID " +
		" left join " + tableName2+ " on " + "MajTechTitle=" + tableName2 + ".IndexID " +
		" left join " + tableName6+ " on " + tableName + ".IDCode=" + tableName6 + ".IndexID " +
		" left join " + tableName4+ " on " + tableName + ".Education=" + tableName4 + ".IndexID " +
		" left join " + tableName8+ " as t on " + tableName + ".TopDegree = t.IndexID " +
		" where ("+tableName+".IDCode = '40001' or "+tableName+".IDCode = '40002' or "+tableName+".IDCode = '40003' or " +
		tableName+".IDCode = '40004' or "+tableName+".IDCode = '40005' or "+tableName+".IDCode = '40006' or " +
		tableName+".IDCode = '40007' or "+tableName+".IDCode = '40008' or "+tableName+".IDCode = '40010' ) " +
		"and Time like '"+year+"%'" ;
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<J413_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, J413_Bean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null;
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
	 * 模板导入
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */
	public boolean batchInsert(List<T411_Bean> list){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		
		try{
			flag = DAOUtil.batchInsert(list, tableName, field, conn) ;
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
	public boolean insert(T411_Bean teaInfoBean){
		
		Connection conn = DBConnection.instance.getConnection() ;
		return DAOUtil.insert(teaInfoBean, tableName, field, conn) ;
		
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
	 * @param 
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */
	public boolean hasPerson(String teaID){
		
		Connection conn = DBConnection.instance.getConnection() ;
		String sql = "select " + teaID + " from " + tableName  + " where TeaID=" + "'" + teaID + "'" ;
		
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

	//获得相应职称的教师数
	public int getTitleNum(String title){
		String Cond = "(TeaFlag is null or TeaFlag != '外聘')";
		int count = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("select count(distinct teaId)");
		sql.append(" from " + tableName +" where "+Cond+" and majTechTitle =" + title);
		
		Connection conn = DBConnection.instance.getConnection();
		Statement st = null;
		ResultSet rs = null;
		
		try{
			
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			if(rs == null){
				return 0;
			}
			
			while(rs.next()){
				count = rs.getInt(1);
			}
			
			
		}catch (Exception e){
			e.printStackTrace();
			return 0;
				
		}
		return count;
	}
	
	//获得相应学位教师数
	public int getDegreeNum(String degree){
		String Cond = "(TeaFlag is null or TeaFlag != '外聘')";
		int count = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("select count(distinct teaId)");
		sql.append(" from " + tableName +" where "+Cond+" and topDegree =" + degree);
		
		Connection conn = DBConnection.instance.getConnection();
		Statement st = null;
		ResultSet rs = null;
		
		try{
			
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			if(rs == null){
				return 0;
			}
			
			while(rs.next()){
				count = rs.getInt(1);
			}
			
			
		}catch (Exception e){
			e.printStackTrace();
			return 0;
				
		}
		return count;
		
	}
	
	//获得相应年龄教师数
	public int getAgeNum(String selectYear, int age1,int age2){
		String Cond = "(TeaFlag is null or TeaFlag != '外聘')";
		int year = Integer.parseInt(selectYear);
		int front = year - age2-1;
		int end = year - age1;
		int count = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("Select count(distinct teaId) from " + tableName +
				" where "+Cond+" and birthday >= '"+front+"-09-01' and birthday <= '"+end+"-08-31'");
		System.out.println(sql.toString());
		Connection conn = DBConnection.instance.getConnection();
		Statement st = null;
		ResultSet rs = null;
		
		try{
			
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			if(rs == null){
				return 0;
			}
			
			while(rs.next()){
				count = rs.getInt(1);
			}
			
			
		}catch (Exception e){
			e.printStackTrace();
			return 0;
				
		}
		return count;
		
	}
	
	//获得相应学源教师数
	public int getSourceNum(String source){
		String Cond = "(TeaFlag is null or TeaFlag != '外聘')";
		int count = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("select count(distinct teaId)");
		sql.append(" from " + tableName +" where "+Cond+" and source =" + source);
		
		Connection conn = DBConnection.instance.getConnection();
		Statement st = null;
		ResultSet rs = null;
		
		try{
			
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			if(rs == null){
				return 0;
			}
			
			while(rs.next()){
				count = rs.getInt(1);
			}
			
			
		}catch (Exception e){
			e.printStackTrace();
			return 0;
				
		}
		return count;
	}
	
	//获得双师型教师人数
	public int getDoubleNum(String type){
		String Cond = "(TeaFlag is null or TeaFlag != '外聘')";
		int count = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("select count(distinct teaId)");
		sql.append(" from " + tableName +" where " + type+" = 'true' and "+Cond);
		
		Connection conn = DBConnection.instance.getConnection();
		Statement st = null;
		ResultSet rs = null;
		
		try{
			
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			if(rs == null){
				return 0;
			}
			
			while(rs.next()){
				count = rs.getInt(1);
			}
			
			
		}catch (Exception e){
			e.printStackTrace();
			return 0;
				
		}
		return count;
	}
	
	//获得相应任职类别教师数
	public int getIDNum(int flag){
		String Cond = "(TeaFlag is null or TeaFlag != '外聘')";
		int count = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("select count(distinct teaId) from "+tableName+" where "+Cond+" and ");
		if(flag == 0){
			sql.append(" idcode = 40000");
		}else if(flag == 1){
			sql.append(" idcode = 40001 or idcode = 40002");
		}else if(flag == 2){
			sql.append(" idcode = 40005 or idcode = 40006");
		}else if(flag == 3){
			sql.append(" idcode = 40007 or idcode = 40008");
		}else if(flag == 4){
			sql.append(" idcode = 40003 or idcode = 40004");
		}else if(flag == 5){
			sql.append(" idcode = 40010");
		}
		System.out.println(sql.toString());
		Connection conn = DBConnection.instance.getConnection();
		Statement st = null;
		ResultSet rs = null;
		
		try{
			
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			if(rs == null){
				return 0;
			}
			
			while(rs.next()){
				count = rs.getInt(1);
			}
			
			
		}catch (Exception e){
			e.printStackTrace();
			return 0;
				
		}
		return count;
		
	}
	
	//获得教师数
	public int getTotalNum(){
		String Cond = "(TeaFlag is null or TeaFlag != '外聘')";
		int count = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("select count(distinct teaId)");
		sql.append(" from " + tableName +" where "+Cond);
		
		Connection conn = DBConnection.instance.getConnection();
		Statement st = null;
		ResultSet rs = null;
		
		try{
			
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			if(rs == null){
				return 0;
			}
			
			while(rs.next()){
				count = rs.getInt(1);
			}
			
			
		}catch (Exception e){
			e.printStackTrace();
			return 0;
				
		}
		return count;
	}
	

	
	
	
	
	/**
	 * 据参数加载43系统列的
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T431_Bean> getT43List(int flag, String queryWord){
		
		String cond;
		
		if (flag == 1){
			cond = "(IDCode = '40003' or IDcode = '40004')";
		}
		else if(flag == 2){
			cond = "(IDCode = '40001' or IDcode = '40002')";
		}
		else if(flag == 3){
			cond = "(IDCode = '40005' or IDcode = '40006')";
		}		
		else if(flag == 4){
			cond = "(IDCode = '40007' or IDcode = '40008')";
		}else{
			cond = "(IDCode = '40010')";
		}
		
		if(queryWord != null){
			cond = cond + "and TeaId LIKE '" + queryWord + "%'";
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
	
	
	/**
	 * 获取教育部表导出J43
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T411_Bean> getJ43List(){
		
		String Cond = "(TeaFlag is null or TeaFlag != '外聘') and T411_TeaBasicInfo_Per$.IDCode > '40000' and T411_TeaBasicInfo_Per$.IDCode < '40009'";
				
		String queryPageSql = "select TeaId,TeaName,Gender,Birthday,AdmisTime,TeaState," +
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
		" where " + Cond;
		
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
			return null;
		}
		
		return list ;
	}
	
	
	public static void main(String args[]){
		String queryPageSql = "select "+tableName+".teaName as teaName,"+tableName+".teaId as teaId,"+tableName+".Gender as gender," +
		tableName+".Birthday as birthday,"+tableName+".AdmisTime as admisTime,"+tableName+".TeaState as teaState," +
		"IdentiType AS IDCode,"+tableName+".unitId as unitId,"+tableName+".fromUnit as fromUnit," +
		tableName4+".Education as education,t.Education AS topDegree," +
		tableName2+".TitleLevel as majTechTitle,"+tableName+".subjectClass as subjectClass,tt.TutorType AS tutorType " +
		" from " + tableName + 
		" left join (select TeaID,Time,DiTutorType.tutorType from " + tableName7+ " left join DiTutorType on "+tableName7+".TutorType = IndexID) as tt on " +tableName+ ".TeaID = tt.TeaID " +
		" left join " + tableName2+ " on " + "MajTechTitle=" + tableName2 + ".IndexID " +
		" left join " + tableName6+ " on " + tableName + ".IDCode=" + tableName6 + ".IndexID " +
		" left join " + tableName4+ " on " + tableName + ".Education=" + tableName4 + ".IndexID " +
		" left join " + tableName8+ " as t on " + tableName + ".TopDegree = t.IndexID " +
		" where "+tableName+".IDCode = '40001' or "+tableName+".IDCode = '40002' or "+tableName+".IDCode = '40003' or " +
		tableName+".IDCode = '40004' or "+tableName+".IDCode = '40005' or "+tableName+".IDCode = '40006' or " +
		tableName+".IDCode = '40007' or "+tableName+".IDCode = '40008' or "+tableName+".IDCode = '400010' " +
		"and Time like '"+2014+"%'" ;
		System.out.println(queryPageSql);
	}
	

}
