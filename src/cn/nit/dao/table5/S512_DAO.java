package cn.nit.dao.table5;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.nit.bean.table5.S512_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.pojo.table5.S512POJO;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class S512_DAO {
	
	/**  数据库表名  */
	private String tableName="S512_CSAndLessonInfo$";
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "TeaUnit,UnitID,SumCS,SmallCSNum,SumTeaNum,QuqlifyTea,Professor,ViceProfessor,JuniorTea,JuniorViceProf,CSProfNum,CSViceProfNum," +
			"Time,Note";
	
	/**  被统计数据库表名  */
	private String tableName1="T512_CSInfo_TeaTea$";
	
	/**  被统计数据库表中所有字段  */
	private String field1="SeqNumber,Term,CSUnit,UnitID,CSMajorName,CSMajorID,CSName,CSID,CSType,CSNature,PubCSType,IsDoubleCS," +
			"Credit,SumCSHour,TheoryCSHour,PraCSHour,ExamWay,PlanTime,CSGrade,CSClass,ClassID,ClassInfo,StuNum,CSTea,IsAccordJob," +
			"TeaTitle,BookUseInfo,IsPlanbook,IsAwardbook,Time,Note,FillTeaID,FillUnitID";
	
	
	/**
	 * 查看是否有符合年份的数据
	 * */
	public int getSeqNum(String year){
		
		int seq=-1;
		
		StringBuffer sql=new StringBuffer();
		sql.append(" select SeqNumber from "+tableName);
		sql.append(" from where Time like '"+year+"%'");
		
		Connection conn = DBConnection.instance.getConnection();
		Statement st = null;
		ResultSet rs = null;
		
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			while(rs.next()){
				seq = rs.getInt("SeqNumber");
			}
		}catch (Exception e){
			e.printStackTrace();
			return seq;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		
		return seq;
	}
	
	/**
	 * @param conditions 查询条件
	 * @param fillUnitId 填报人单位号，如果为空，则查询所有未审核的数据，<br>如果不为空，则查询填报人自己单位的所有的数据
	 * @return
	 */
	public List<S512_Bean> loadInfo(String year){
		
		StringBuffer sql = new StringBuffer() ;
		List<S512_Bean> list = null ;
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
			list = DAOUtil.getList(rs, S512_Bean.class) ;
			
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		return list ;
	}
    
	/**
	 * 将统计数据插入数据库
	 * */
	public boolean insert(S512_Bean s512bean){
		boolean flag = false;
		
		Connection conn = DBConnection.instance.getConnection();
		
		try{
			flag = DAOUtil.insert(s512bean, tableName, field, conn);
		} catch (Exception e){
			e.printStackTrace();
			return flag;
		} finally
		{
			DBConnection.close(conn);
		}
		 return flag;
	}
	
	/**
	 * 统计原始数据条数
	 * */
	public int countOri(String year){
		 int count = 0;
		 StringBuffer sql =  new StringBuffer();
		 sql.append("select count(*) AS icount from " +tableName1);
		 sql.append(" where Time like '"+year+"%'");
		 sql.append(" and "+tableName1+".UnitID like '3%'");
		 
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
				DBConnection.close(rs);
				DBConnection.close(st);	
				DBConnection.close(conn);
			}
		 return count;
		 
	}
	
	/**
	 * 对原始数据进行统计
	 * */
	public List<S512_Bean> getOriData(String year){
		
		List<S512_Bean> list=new ArrayList<S512_Bean>();
		StringBuffer sql = new StringBuffer();
		sql.append("select UnitName as TeaUnit,DiDepartment.UnitID,");
		sql.append(" Count(CSUnit) as SumCS,");
		sql.append(" sum(case when StuNum <= 30 then 1 else 0 end) AS SmallCSNum , ");
		sql.append(" sum(case when IsAccordJob = '1' then 1 else 0 end) AS QuqlifyTea,");
		sql.append(" sum(case when TeaTitle = '正高级' then 1 else 0 end) AS Professor,");
		sql.append(" sum(case when TeaTitle = '副高级' then 1 else 0 end) AS ViceProfessor,");
		sql.append(" sum(case when TeaTitle = '正高级' and (CSGrade = '1' or  CSGrade = '2') then 1 else 0 end) AS JuniorTea,");
		sql.append(" sum(case when TeaTitle = '副高级' and (CSGrade = '1' or  CSGrade = '2') then 1 else 0 end) AS JuniorViceProf,");
		sql.append(" sum(case when TeaTitle = '正高级' then 1 else 0 end) AS CSProfNum,");
		sql.append(" sum(case when TeaTitle = '副高级' then 1 else 0 end) AS CSViceProfNum");
		sql.append(" from DiDepartment ");
		sql.append(" left join T512_CSInfo_TeaTea$ on DiDepartment.UnitID = T512_CSInfo_TeaTea$.UnitID ");
		sql.append(" where convert(varchar(4),T512_CSInfo_TeaTea$.Time,120) =" + year);
		sql.append(" and DiDepartment.UnitID like '3%' group by DiDepartment.UnitID,UnitName");
		
//		System.out.println(sql.toString());
		Connection conn=DBConnection.instance.getConnection();
		Statement st=null;
		ResultSet rs=null;
		int sumCS = 0; int smallCSNum = 0; int sumTeaNum = 0; int quqlifyTea = 0; int professor = 0;
		int viceProfessor = 0; int juniorTea = 0; int juniorViceProf = 0; int csProfNum = 0 ; 
		int csViceProfNum = 0 ;
		
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			
			while(rs.next()){
			
				S512_Bean bean = new S512_Bean();
				String TeaUnit = rs.getString("TeaUnit");
			
				String UnitID = rs.getString("UnitID");
		
				int Sumcs = rs.getInt("SumCS");
				sumCS += Sumcs;
				int SmallCSNum = rs.getInt("SmallCSNum");
				smallCSNum += SmallCSNum;
				int QuqlifyTea = rs.getInt("QuqlifyTea");
				quqlifyTea += QuqlifyTea;
				int Professor = rs.getInt("Professor");
				professor += Professor;
				int ViceProfessor = rs.getInt("ViceProfessor");
				viceProfessor += ViceProfessor;
				int JuniorTea = rs.getInt("JuniorTea");
				juniorTea += JuniorTea;
				int JuniorViceProf = rs.getInt("JuniorViceProf");
				juniorViceProf += JuniorViceProf;
				int CSProfNum = rs.getInt("CSProfNum");
				csProfNum += CSProfNum;
				int CSViceProfNum = rs.getInt("CSViceProfNum");
				csViceProfNum += CSViceProfNum;
				
				bean.setCSProfNum(CSProfNum);
				bean.setCSViceProfNum(CSViceProfNum);
				bean.setJuniorTea(JuniorTea);
				bean.setJuniorViceProf(JuniorViceProf);
				bean.setProfessor(Professor);
				bean.setQuqlifyTea(QuqlifyTea);
				bean.setSmallCSNum(SmallCSNum);
				bean.setSumCS(Sumcs);
				bean.setTeaUnit(TeaUnit);
				bean.setUnitID(UnitID);
				bean.setViceProfessor(ViceProfessor);
				bean.setTime(TimeUtil.changeDateY(year));
				
				list.add(bean);
			}
			
			
			S512_Bean beanAll = new S512_Bean();
			beanAll.setCSProfNum(csProfNum);
			beanAll.setCSViceProfNum(csViceProfNum);
			beanAll.setJuniorTea(juniorTea);
			beanAll.setJuniorViceProf(juniorViceProf);
			beanAll.setProfessor(professor);
			beanAll.setQuqlifyTea(quqlifyTea);
			beanAll.setSmallCSNum(smallCSNum);
			beanAll.setSumCS(sumCS);
			beanAll.setSumTeaNum(sumTeaNum);
			beanAll.setViceProfessor(viceProfessor);
			beanAll.setTeaUnit("总计：");
			beanAll.setUnitID("");
			beanAll.setTime(TimeUtil.changeDateY(year));
			
			list.add(0, beanAll);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}

		return list;
	}
	
	public boolean delete(){
		
		boolean flag = false;
		StringBuffer sql = new StringBuffer();
		sql.append("delete from " +tableName);
		Connection conn=DBConnection.instance.getConnection();
		Statement st=null;
		try{
			st = conn.createStatement();
			int n = st.executeUpdate(sql.toString());
			if(n>0){
				flag = true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		
		return flag;
		
	}
	
	/**
	 * 保存数据（两种情况:有数据 ，delete first then batchinsert；无数据，batchinsert）
	 * */
	public boolean save(List<S512_Bean> list,String year){
 		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year;		
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection() ;		
		Statement st = null ;
		ResultSet rs = null ;
		List<S512_Bean> templist = null ;
		String tempField = "TeaUnit,UnitID,SumCS,SmallCSNum,SumTeaNum,QuqlifyTea,Professor,ViceProfessor,JuniorTea,JuniorViceProf,CSProfNum,CSViceProfNum,Time,Note";
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			templist = DAOUtil.getList(rs, S512_Bean.class) ;
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
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
 		
 		return flag ;
 		
 	}
	
	/**
	 * 用于excel
	 * */
	public List<S512_Bean> totalList(String year){
		
		List<S512_Bean> list = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select * from "+tableName);
		sql.append(" where Time  like '"+year+"%'");
		
		Connection conn = DBConnection.instance.getConnection();
		Statement st = null;
		ResultSet rs = null;
		
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			list = DAOUtil.getList(rs, S512_Bean.class);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		return list;
	}
	
	/**
	 * @param 查询某年的合计信息
	 * @return
	 */
	public S512_Bean getYearInfo(String year,String teaUnit){
		
		StringBuffer sql = new StringBuffer() ;
		List<S512_Bean> list = null ;
		sql.append("select * from "+ tableName);
		sql.append(" where TeaUnit='" + teaUnit + "' and Time like '"+year+"%'");
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			list = DAOUtil.getList(rs, S512_Bean.class) ;
			if(list.size()!=0){
				S512_Bean bean = list.get(0);
				return bean;
			}else{
				return null;
			}
			
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
	}
	
	public static void main(String arg[]){
		S512_DAO dao=new S512_DAO();
		boolean flag = dao.delete();
		System.out.println(flag);
	}
	
}
