package cn.nit.dao.table5;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import cn.nit.bean.table5.S52_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

import java.sql.Connection;

public class S52_DAO {
	
	/**  数据库表名  */
	private String tableName="S52_CSBuildInfo$";
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "CSType,SumCSNum,InterLevel,NationLevel,ProviLevel,CityLevel,SchLevel,Time,Note";
	
	/**  被统计数据库表名  */
	private String tableName1="T521_CSBuildInfo_Tea$";
	
	/**  被统计数据库表中所有字段  */
	private String field1="CSType,CSID";
	
	/**
	 * 统计原始数据条数
	 * */
	public int countOri(String year){
		 int count = 0;
		 StringBuffer sql =  new StringBuffer();
		 sql.append("select count(*) AS icount from " +tableName1);
		 sql.append(" where Time like '"+year+"%'");
		 
		 Connection conn = DBConnection.instance.getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 try{
			 st = conn.createStatement();
			 rs = st.executeQuery(sql.toString());
			 while(rs.next()){
				 count = rs.getInt("icount");
			 }
			 System.out.println("cout=" + count);
		 }catch(Exception e){
			 e.printStackTrace();
			 return count;
		 }finally{
				DBConnection.close(conn);
			}
		 return count;
		 
	}
	
	
	/**
	 * 统计T512中的数据
	 * */
	public List<S52_Bean> getOriData(String year){
		
		 List<S52_Bean> list = new ArrayList<S52_Bean>();
		 
		 StringBuffer sql = new StringBuffer();
		 sql.append("select CSType,");
		 sql.append(" count(CSType) as SumCSNum,");
		 sql.append(" sum (case when CSLevel = '50000' then 1 else 0 end) AS InterLevel , ");
		 sql.append(" sum (case when CSLevel = '50001' then 1 else 0 end) AS NationLevel , ");
		 sql.append(" sum (case when CSLevel = '50002' then 1 else 0 end) AS ProviLevel , ");
		 sql.append(" sum (case when CSLevel = '50003' then 1 else 0 end) AS CityLevel , ");
		 sql.append(" sum (case when CSLevel = '50004' then 1 else 0 end) AS SchLevel ");
		 sql.append(" from DiAwardLevel ");
		 sql.append(" left join T521_CSBuildInfo_Tea$ on DiAwardLevel.IndexID = T521_CSBuildInfo_Tea$.CSLevel ");
		 sql.append(" where convert(varchar(4),T521_CSBuildInfo_Tea$.Time,120) =" + year);
		 sql.append(" group by T521_CSBuildInfo_Tea$.CSType");
		 
		 System.out.println(sql.toString());
		 Connection conn = DBConnection.instance.getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 int sumNum = 0; int interLevel = 0; int nationLevel = 0; int proviLevel = 0;
		 int cityLevel = 0; int schLevel = 0;
		 
		 try{
			 
			 st = conn.createStatement();
			 rs = st.executeQuery(sql.toString());
			 
			 while(rs.next()){
				 S52_Bean bean = new S52_Bean();
				 int SumNum = 0;
				 String CSType = rs.getString("CSType");
				 int InterLevel = rs.getInt("InterLevel");
				 interLevel += InterLevel;
				 int NationLevel = rs.getInt("NationLevel");
				 nationLevel += NationLevel;
				 int ProviLevel = rs.getInt("ProviLevel");
				 proviLevel += ProviLevel;
				 int CityLevel  = rs.getInt("CityLevel");
				 cityLevel += CityLevel;
				 int SchLevel = rs.getInt("SchLevel");
				 schLevel += SchLevel;
				 SumNum  =  InterLevel+NationLevel+ProviLevel+CityLevel+SchLevel;
				 bean.setCityLevel(CityLevel);
				 bean.setCSType(CSType);
				 bean.setInterLevel(InterLevel);
				 bean.setNationLevel(NationLevel);
				 bean.setProviLevel(ProviLevel);
				 bean.setSchLevel(SchLevel);
				 bean.setSumCSNum(SumNum);
				 bean.setTime(TimeUtil.changeDateY(year));
				 list.add(bean);
			 }
			 //统计合计
			 
			 S52_Bean bean = new S52_Bean();
			 sumNum =interLevel+nationLevel+proviLevel+cityLevel+schLevel;
			 bean.setCSType("课程门数总计：");
			 bean.setCityLevel(cityLevel);
			 bean.setInterLevel(interLevel);
			 bean.setNationLevel(nationLevel);
			 bean.setProviLevel(proviLevel);
			 bean.setSchLevel(schLevel);
			 bean.setSumCSNum(sumNum);
			 bean.setTime(TimeUtil.changeDateY(year));
			 	
			 list.add(0,bean);//将合计数据存放在数据库的 第一个位置
			 
		 }catch (Exception e){
			 e.printStackTrace();
		 }finally{
				DBConnection.close(conn);
			}
		 
		 return list;
	}
	
	/**
	 * @param conditions 查询条件
	 * @param fillUnitId 填报人单位号，如果为空，则查询所有未审核的数据，<br>如果不为空，则查询填报人自己单位的所有的数据
	 * @return
	 */
	public List<S52_Bean> loadInfo(String year){
		
		StringBuffer sql = new StringBuffer() ;
		List<S52_Bean> list = null ;
		sql.append("select * from "+ tableName);
		sql.append(" where Time like '"+year+"%'");
//		sql.append("select t.SeqNumber,t.TeaUnit,t.UnitID,t.SumCS,t.SmallCSNum,t.SumTeaNum," +
//				"t.QuqlifyTea,t.Professor,t.ViceProfessor,t.JuniorTea,t.JuniorViceProf,t.CSProfNum,t.CSViceProfNum,t.Time,t.Note") ;
//		sql.append(" from "+tableName+" as t,DiDepartment as did ");
//		sql.append(" where did.UnitID = t.UnitID ");
//		sql.append(" and Time like '"+year+"%'");
		
//		sql.append(" order by SeqNumber desc") ;
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			list = DAOUtil.getList(rs, S52_Bean.class) ;
			
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(conn);
		}
		return list ;
	}
	
	/**
	 * 用于excel
	 * */
	public List<S52_Bean> totalList(String year){
		
		List<S52_Bean> list = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select * from "+tableName);
		sql.append(" where Time  like '"+year+"%'");
		
		Connection conn = DBConnection.instance.getConnection();
		Statement st = null;
		ResultSet rs = null;
		
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			list = DAOUtil.getList(rs, S52_Bean.class);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConnection.close(conn);
		}
		return list;
	}
    
	
	/**
	 * 保存数据（两种情况:有数据 ，delete first then batchinsert；无数据，batchinsert）
	 * */
	public boolean save(List<S52_Bean> list,String year){
 		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year;
		System.out.println("s52:"+sql);
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection() ;		
		Statement st = null ;
		ResultSet rs = null ;
		List<S52_Bean> templist = null ;
		String tempField = "CSType,SumCSNum,InterLevel,NationLevel,ProviLevel,CityLevel,SchLevel,Time,Note";
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			templist = DAOUtil.getList(rs, S52_Bean.class) ;
			if(templist.size() != 0){ //存在数据
				String delSql = "delete  from " + tableName + " where convert(varchar(4),Time,120)=" + year;
				int delflag = st.executeUpdate(delSql.toString());
				if(delflag >0 ){
					flag = DAOUtil.batchInsert(list, tableName, tempField, conn) ;
				}
			}else{
				flag = DAOUtil.batchInsert(list, tableName, tempField, conn) ;
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return false ;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
 		
 		return flag ;
 		
 	}
	
	public static void main(String arg[]){
		S52_DAO dao = new S52_DAO();
//		List<S52_Bean> list = dao.getOriData("2014");
		int n  = dao.countOri("2013");
		System.out.println(n);
	}

}
