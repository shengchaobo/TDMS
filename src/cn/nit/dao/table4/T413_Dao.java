package cn.nit.dao.table4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.table4.T411_Bean;
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
	"UnitName,Education,TopDegree,TechTitle,SubjectClass,WorkUnitType,TutorType,Region,Note,FillUnitID";
	
	
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T413_Bean> totalList(){
		
		String sql = "select " + 
		"Name,TeaId,Gender,Birthday,HireBeginTime,TeaState,HireTimeLen,UnitId,"+
		"UnitName," + tableName4 + ".Education,Degree AS TopDegree,TitleLevel AS TechTitle,SubjectClass,WorkUnitType," + tableName3 + ".TutorType,Region,Note,FillUnitID"
		+ " from " + tableName + 
		" left join " + tableName1+ " on " + "TopDegree=" + tableName1 + ".IndexID " +
		" left join " + tableName2+ " on " + "TechTitle=" + tableName2 + ".IndexID " +
		" left join " + tableName4+ " on " + tableName + ".Education=" + tableName4 + ".IndexID " +
		" left join " + tableName3+ " on " + tableName + ".TutorType=" + tableName3 + ".IndexID " ;
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
	 * 获得分页查询的总数
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public int totalQueryPageList(String conditions, String fillunitID){
		
		int total = 0;
		String Cond = "1=1";
		
		if(conditions != null && !conditions.equals("")){
			Cond = Cond + conditions;
		}
		
		if(fillunitID != null && !fillunitID.equals("")){
			Cond = Cond + " and FillUnitID=" + fillunitID;
		}
		
		
		String queryPageSql = "select count(*) " 
		+ " from " + tableName + 
		" left join " + tableName1+ " on " + "TopDegree=" + tableName1 + ".IndexID " +
		" left join " + tableName2+ " on " + "TechTitle=" + tableName2 + ".IndexID " +
		" left join " + tableName4+ " on " + tableName + ".Education=" + tableName4 + ".IndexID " +
		" left join " + tableName3+ " on " + tableName + ".TutorType=" + tableName3 + ".IndexID " +
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
	public List<T413_Bean> queryPageList(String conditions, String fillunitID, int pageSize, int showPage){
		
		String Cond = "1=1";
		
		if(conditions != null && !conditions.equals("")){
			Cond = Cond + conditions;
		}
		
		if(fillunitID != null && !fillunitID.equals("")){
			Cond = Cond + " and FillUnitID=" + fillunitID;
		}
		
				
		String queryPageSql = "select top " + pageSize + 
		"Name,TeaId,Gender,Birthday,HireBeginTime,TeaState,HireTimeLen,UnitId,"+
		"UnitName," + tableName4 + ".Education,Degree AS TopDegree,TitleLevel AS TechTitle,SubjectClass,WorkUnitType," + tableName3 + ".TutorType,Region,Note,FillUnitID"
		+ " from " + tableName + 
		" left join " + tableName1+ " on " + "TopDegree=" + tableName1 + ".IndexID " +
		" left join " + tableName2+ " on " + "TechTitle=" + tableName2 + ".IndexID " +
		" left join " + tableName4+ " on " + tableName + ".Education=" + tableName4 + ".IndexID " +
		" left join " + tableName3+ " on " + tableName + ".TutorType=" + tableName3 + ".IndexID " +
		" where " + Cond + " and (TeaID not in (select top " + pageSize * (showPage-1) + " TeaID from "+
		tableName + " where " + Cond +  " order by TeaID)) order by TeaID " ;
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
	
	/**
	 * 模板导入
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */
	public boolean batchInsert(List<T413_Bean> list){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		String tempfield = "Name,TeaId,Gender,Birthday,HireBeginTime,TeaState,HireTimeLen,UnitId,"+
		"UnitName,Education,TopDegree,TechTitle,SubjectClass,WorkUnitType,TutorType,Region,FillUnitID";
		try{
			flag = DAOUtil.batchInsert(list, tableName, tempfield, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}
		
		return flag ;
		
	}
	
	public boolean update(T413_Bean bean){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			
			String tempfield = "Name,TeaId,Gender,Birthday,HireBeginTime,TeaState,HireTimeLen,UnitId,"+
			"UnitName,Education,TopDegree,TechTitle,SubjectClass,WorkUnitType,TutorType,Region,Note";
			
			String temp = tempfield.replaceAll("TeaId,", "");
			
			String temp1 = temp;
			
			if(bean.getEducation().trim().equals("")){
				String a = "Education,";
			    temp1 = temp.replaceAll(a , "");
			}
			
			String temp2 = temp1;
			
			if(bean.getTopDegree().trim().equals("")){
				temp2 = temp1.replaceAll("TopDegree," , "");
			}
			
			String temp3 = temp2;
			if(bean.getTechTitle().trim().equals("")){
				temp3 = temp2.replaceAll("TechTitle," , "");
			}
			
			String temp4 = temp3;
			if(bean.getTutorType().trim().equals("")){
				temp4 = temp3.replaceAll("TutorType,", "");
			}	
			
			//System.out.println(temp5);
			//System.out.println(bean.getTeaId());
			flag = DAOUtil.update(bean, tableName, "teaId", temp4, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
		}
		
		return flag ;
	}
	
	public static void main(String args[]){
		T413_Dao testDao =  new T413_Dao() ;
		System.out.println(testDao.totalList().size()) ;
	}

}
