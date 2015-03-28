package cn.nit.dao.table5;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import cn.nit.bean.table5.S534_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class S534_DAO {
	
	/**  数据库表名  */
	private String tableName="S534_GraTrainInfo$";
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "TeaUnit,UnitID,SumTeaNum,OutHireTeaNum,HighTitle,ViceHighTitle,MidTitle,GraDegree,AboveGraDegree," +
			"SchGoodTea,IssueNum,PraIssueNum,StuNum,GoodStuNum,Time,Note";
	
	/**  被统计数据库表名  */
	private String tableName1="T534_ByMajGraTrainInfo_TeaTea$";
	
	/**  被统计数据库表中所有字段  */
	private String field1="TeaUnit,UnitID,IsOutEmploy,Title,Education,Degree,IsExcellent,TrainIssueNum,SocialNum,GuideStuNum,GainBestNum,Time,Note";
	
	
	/**
	 * 统计原始数据条数
	 * */
	public int countOri(String year){
		 int count = 0;
		 StringBuffer sql =  new StringBuffer();
		 sql.append("select count(*) AS icount from " +tableName1);
		 sql.append(" where Time like '"+year+"%'");
		 sql.append(" and UnitID like '3%'");
		 
		 Connection conn = DBConnection.instance.getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 try{
			 st = conn.createStatement();
			 rs = st.executeQuery(sql.toString());
			 while(rs.next()){
				 count = rs.getInt("icount");
			 }
//			 System.out.println("cout=" + count);
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
	 * 统计原始数据
	 * */
	public List<S534_Bean> getOriData(String year){
		
		 List<S534_Bean> list = new ArrayList<S534_Bean>();
		 
		 StringBuffer sql = new StringBuffer();
		 sql.append("select UnitName AS TeaUnit,DiDepartment.UnitID,");
		 sql.append(" count(TeaName) as SumTeaNum, ");
		 sql.append("  sum(case when IsOutEmploy = '1' then 1 else 0 end) AS OutHireTeaNum,  ");
		 sql.append(" sum(case when Title = '42000' then 1 else 0 end) AS HighTitle, ");
		 sql.append(" sum(case when Title = '42001' then 1 else 0 end) AS ViceHighTitle, ");
		 sql.append(" sum(case when Title = '42002' then 1 else 0 end) AS MidTitle, ");
		 sql.append(" sum(case when Education = '81001' then 1 else 0 end) AS GraDegree, ");
		 sql.append(" sum(case when (Degree = '81001' or Degree = '81000')  then 1 else 0 end) AS AboveGraDegree,");
		 sql.append(" sum(case when IsExcellent = '1'  then 1 else 0 end) AS SchGoodTea,");
		 sql.append(" sum(TrainIssueNum) AS IssueNum,");
		 sql.append(" sum(SocialNum) AS PraIssueNum,");
		 sql.append(" sum(GuideStuNum) AS StuNum,");
		 sql.append(" sum(GainBestNum) AS GoodStuNum");
		 sql.append(" from DiDepartment ");
		 sql.append(" left join T534_ByMajGraTrainInfo_TeaTea$ on DiDepartment.UnitID = T534_ByMajGraTrainInfo_TeaTea$.UnitID ");
		 sql.append(" where convert(varchar(4),T534_ByMajGraTrainInfo_TeaTea$.Time,120) =" + year);
		 sql.append(" and DiDepartment.UnitID like '3%' group by DiDepartment.UnitID,UnitName");
		 
		 Connection conn=DBConnection.instance.getConnection();
		 Statement st=null;
		 ResultSet rs=null;
		 int sumTeaNum = 0; int outHire = 0 ;int highTitle = 0; int viceHigh = 0;int midTitle = 0 ;
		 int graDegree = 0; int aboveGra = 0 ; int schGood = 0; int issueNum  = 0; int praIssue = 0;
		 int stuNum = 0; int goodStu = 0;
		 
		 try{
			 st = conn.createStatement();
			 rs = st.executeQuery(sql.toString());
			 while (rs.next()){
				 S534_Bean bean = new S534_Bean();
				 String TeaUnit = rs.getString("TeaUnit");
				 String UnitID = rs.getString("UnitID"); 
				 int SumTeaNum = rs.getInt("SumTeaNum");
				 sumTeaNum += SumTeaNum;
				 int OutHireTeaNum = rs.getInt("OutHireTeaNum");
				 outHire +=OutHireTeaNum;
				 int HighTitle = rs.getInt("HighTitle");
				 highTitle +=HighTitle;
				 int ViceHighTitle = rs.getInt("ViceHighTitle");
				 viceHigh +=ViceHighTitle;
				 int MidTitle = rs.getInt("MidTitle");
				 midTitle +=MidTitle;
				 int GraDegree = rs.getInt("GraDegree");
				 graDegree +=GraDegree;
				 int AboveGraDegree = rs.getInt("AboveGraDegree");
				 aboveGra +=AboveGraDegree;
				 int SchGoodTea = rs.getInt("SchGoodTea");
				 schGood +=SchGoodTea;
				 int IssueNum = rs.getInt("IssueNum");
				 issueNum +=IssueNum;
				 int PraIssueNum = rs.getInt("PraIssueNum");
				 praIssue +=PraIssueNum;
				 int StuNum = rs.getInt("StuNum");
				 stuNum +=StuNum;
				 int GoodStuNum = rs.getInt("GoodStuNum");
				 goodStu += GoodStuNum;
				 bean.setAboveGraDegree(AboveGraDegree);
				 bean.setGoodStuNum(GoodStuNum);
				 bean.setGraDegree(GraDegree);
				 bean.setHighTitle(HighTitle);
				 bean.setIssueNum(IssueNum);
				 bean.setMidTitle(MidTitle);
				 bean.setOutHireTeaNum(OutHireTeaNum);
				 bean.setPraIssueNum(PraIssueNum);
				 bean.setSchGoodTea(SchGoodTea);
				 bean.setStuNum(StuNum);
				 bean.setSumTeaNum(SumTeaNum);
				 bean.setTeaUnit(TeaUnit);
				 bean.setUnitID(UnitID);
				 bean.setViceHighTitle(ViceHighTitle);
				 bean.setTime(TimeUtil.changeDateY(year));
				 list.add(bean);
			 }
			 
			 	S534_Bean bean1 = new S534_Bean();
			 	bean1.setAboveGraDegree(aboveGra);
			 	bean1.setGoodStuNum(goodStu);
			 	bean1.setGraDegree(graDegree);
			 	bean1.setHighTitle(highTitle);
			 	bean1.setIssueNum(issueNum);
			 	bean1.setMidTitle(midTitle);
			 	bean1.setOutHireTeaNum(outHire);
			 	bean1.setPraIssueNum(praIssue);
			 	bean1.setSchGoodTea(schGood);
			 	bean1.setStuNum(stuNum);
			 	bean1.setSumTeaNum(sumTeaNum);
			 	bean1.setViceHighTitle(viceHigh);
			 	bean1.setTime(TimeUtil.changeDateY(year));
			 	bean1.setUnitID("");
			 	bean1.setTeaUnit("全校总计");
			 	list.add(0, bean1);
			 
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
	 * @param conditions 查询条件
	 * @param fillUnitId 填报人单位号，如果为空，则查询所有未审核的数据，<br>如果不为空，则查询填报人自己单位的所有的数据
	 * @return
	 */
	public List<S534_Bean> loadInfo(String year){
		
		StringBuffer sql = new StringBuffer() ;
		List<S534_Bean> list = null ;
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
			list = DAOUtil.getList(rs, S534_Bean.class) ;
			
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
	 * 用于excel
	 * */
	public List<S534_Bean> totalList(String year){
		
		List<S534_Bean> list = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select * from "+tableName);
		sql.append(" where Time  like '"+year+"%'");
		
		Connection conn = DBConnection.instance.getConnection();
		Statement st = null;
		ResultSet rs = null;
		
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			list = DAOUtil.getList(rs, S534_Bean.class);
			
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
	 * 保存数据（两种情况:有数据 ，delete first then batchinsert；无数据，batchinsert）
	 * */
	public boolean save(List<S534_Bean> list,String year){
 		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year;
		System.out.println("s52:"+sql);
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection() ;		
		Statement st = null ;
		ResultSet rs = null ;
		List<S534_Bean> templist = null ;
		String tempField = "TeaUnit,UnitID,SumTeaNum,OutHireTeaNum,HighTitle,ViceHighTitle,MidTitle,GraDegree,AboveGraDegree," +
			"SchGoodTea,IssueNum,PraIssueNum,StuNum,GoodStuNum,Time,Note";
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			templist = DAOUtil.getList(rs, S534_Bean.class) ;
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
	
	
	public static void main(String arg[]){
		S534_DAO dao = new S534_DAO();
		List<S534_Bean> list = dao.getOriData("2014");
		System.out.println(list.size());
	}

}
