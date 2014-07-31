package cn.nit.dao.table6;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import cn.nit.bean.table6.S65_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.TimeUtil;

public class S65_Dao {
	
	/**  数据库表名  */
	private String tableName="S65_StuAchievement";
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "PaperNum,WorkNum,PatentNum,CET4,CET6,NCRE,JingxiNCRE,ConQualify,ConReach,InterConference," +
			"SumDiscipAward,InterD,NationD,ProviD,CityD,SchD,SumActAward,InterA,NationA,ProviA,CityA," +
			"SchA,SumLiterSportAward,InterLS,NationLS,ProviLS,CityLS,SchLS,Time,Note";
//	
//	/**  被统计数据库表名  */
//	private String tableName1="T511_UndergraCSBase_Tea$";
//	
//	/**  被统计数据库表中所有字段  */
//	private String field1="CSType,CSNature";

	
	/**
	 * 统计S51-01中的数据：按课程性质统计项目数
	 * */
	public List<S65_Bean> getOriData(String year){
		
		 List<S65_Bean> list = null;
		 
		 StringBuffer sql = new StringBuffer();
//		 	sql.append("select DiCourseChar.IndexID as Item,");
////			sql.append(" Count(CSUnit) as SumCS,");
		    
		    sql.append("select sum(case when CompetiType = '55000' then 1 else 0 end ) AS SumDiscipAward,");
		    sql.append("  sum(case when (CompetiType = '55000' and AwardLevel = '50000') then 1 else 0 end ) AS  InterD,");
		    sql.append("  sum(case when (CompetiType = '55000' and AwardLevel = '50001') then 1 else 0 end ) AS  NationD,");
		    sql.append("  sum(case when (CompetiType = '55000' and AwardLevel = '50002') then 1 else 0 end ) AS  ProviD,");
		    sql.append("  sum(case when (CompetiType = '55000' and AwardLevel = '50003') then 1 else 0 end ) AS  CityD,");
		    sql.append("  sum(case when (CompetiType = '55000' and AwardLevel = '50004') then 1 else 0 end ) AS  SchD,");
		    
		    sql.append(" sum(case when CompetiType = '55001' then 1 else 0 end) AS SumActAward, "); 
		    sql.append("  sum(case when (CompetiType = '55001' and AwardLevel = '50000') then 1 else 0 end ) AS  InterA,");
		    sql.append("  sum(case when (CompetiType = '55001' and AwardLevel = '50001') then 1 else 0 end ) AS  NationA,");
		    sql.append("  sum(case when (CompetiType = '55001' and AwardLevel = '50002') then 1 else 0 end ) AS  ProviA,");
		    sql.append("  sum(case when (CompetiType = '55001' and AwardLevel = '50003') then 1 else 0 end ) AS  CityA,");
		    sql.append("  sum(case when (CompetiType = '55001' and AwardLevel = '50004') then 1 else 0 end ) AS  SchA,");
		    
		    sql.append(" sum(case when CompetiType ='55002' then 1 else 0 end) AS SumLiterSportAward, "); 
		    sql.append("  sum(case when (CompetiType = '55002' and AwardLevel = '50000') then 1 else 0 end ) AS  InterLS,");
		    sql.append("  sum(case when (CompetiType = '55002' and AwardLevel = '50001') then 1 else 0 end ) AS  NationLS,");
		    sql.append("  sum(case when (CompetiType = '55002' and AwardLevel = '50002') then 1 else 0 end ) AS  ProviLS,");
		    sql.append("  sum(case when (CompetiType = '55002' and AwardLevel = '50003') then 1 else 0 end ) AS  CityLS,");
		    sql.append("  sum(case when (CompetiType = '55002' and AwardLevel = '50004') then 1 else 0 end ) AS  SchLS,");
		    
		    sql.append(" (select count(SeqNumber) from T652_StuPublishPaper_TeaYLC$ where convert(varchar(4),T652_StuPublishPaper_TeaYLC$.Time,120) = " + year+") AS PaperNum,");
		    sql.append(" (select count(SeqNumber) from T653_StuPublishWord_TeaYLC$ where convert(varchar(4),T653_StuPublishWord_TeaYLC$.Time,120) = " + year+") AS WorkNum,");
		    sql.append(" (select count(SeqNumber) from T654_StuAwardPatent_TeaYLC$ where convert(varchar(4),T654_StuAwardPatent_TeaYLC$.Time,120) = " + year+") AS PatentNum,");
		    sql.append(" (select count(SeqNumber) from T658_InInterConference_TeaInter$ where convert(varchar(4),T658_InInterConference_TeaInter$.Time,120) = " + year+") AS InterConference,");
		    sql.append(" T655_CET46NCRE_Tea$.CET4PassRate AS CET4,T655_CET46NCRE_Tea$.CET6PassRate AS CET6," +
		    		" T655_CET46NCRE_Tea$.JiangxiNCREPassRate AS JingxiNCRE, ");
		    sql.append(" T656_NationNCRE_Info$.NationNCREPassRate AS NCRE,");
		    sql.append(" T657_HabitusQualified_Sport$.HabitusQualifiedRate AS ConQualify,T657_HabitusQualified_Sport$.HabitusTestReachRate AS ConReach");
		    sql.append(" from DiAwardLevel,DiContestLevel");
		    sql.append(" left join T651_StuCompetiAwardInfo_TeaYLC$ on DiAwardLevel.IndexID = T651_StuCompetiAwardInfo_TeaYLC$.AwardLevel," +
		    		"DiContestLevel.IndexID = T651_StuCompetiAwardInfo_TeaYLC$.CompetiType,T655_CET46NCRE_Tea$,T656_NationNCRE_Info$,T657_HabitusQualified_Sport$");
		    sql.append(" where convert(varchar(4),T651_StuCompetiAwardInfo_TeaYLC$.Time,120) =" + year);
		    sql.append(" and convert(varchar(4),T655_CET46NCRE_Tea$.Time,120) = " + year);
		    sql.append(" and T655_CET46NCRE_Tea$.TeaUnit = '全校合计'");
		    sql.append(" and convert(varchar(4),T656_NationNCRE_Info$.Time,120) = " + year);
		    sql.append(" and T656_NationNCRE_Info$.TeaUnit = '全校合计'");
		    sql.append(" and convert(varchar(4),T657_HabitusQualified_Sport$.Time,120) = " + year);
		    sql.append(" and T657_HabitusQualified_Sport$.TeaUnit = '全校合计'");
		    
		    
//			sql.append(" sum(case when CSType = '22000' then 1 else 0 end) AS TheoPraNum , ");
//			sql.append(" sum(case when CSType = '22001' then 1 else 0 end) AS InClassNum,");
//			sql.append(" sum(case when CSType = '22002' then 1 else 0 end) AS PraNum,");
//			sql.append(" sum(case when CSType = '22003' then 1 else 0 end) AS ExpNum,");
//			sql.append(" from DiCourseChar ");
//			sql.append(" left join T511_UndergraCSBase_Tea$ on DiCourseChar.IndexID = T511_UndergraCSBase_Tea$.CSNature ");
//			sql.append(" where convert(varchar(4),T511_UndergraCSBase_Tea$.Time,120) =" + year);
//			sql.append(" and DiCourseChar.IndexID  group by DiCourseChar.IndexID,CourseChar");
		 
		 System.out.println(sql.toString());
		 Connection conn = DBConnection.instance.getConnection();
		 Statement st = null;
		 ResultSet rs = null;
//		 int sumNum = 0; 
//		 int interLevel = 0; int nationLevel = 0; int proviLevel = 0;
//		 int cityLevel = 0; int schLevel = 0;
		 
		 try{
			 
			 st = conn.createStatement();
			 rs = st.executeQuery(sql.toString());
			 S65_Bean bean = new S65_Bean();
			 while(rs.next()){
				
				 
				int PaperNum = rs.getInt("PaperNum");
				int WorkNum = rs.getInt("WorkNum");
				int PatentNum = rs.getInt("PatentNum");
				double CET4 = rs.getDouble("CET4");
				double CET6 = rs.getDouble("CET6");
				double NCRE = rs.getDouble("NCRE");
				double JingxiNCRE = rs.getDouble("JingxiNCRE");
				double ConQualify = rs.getDouble("ConQualify");
				double ConReach = rs.getDouble("ConReach");
				int InterConference = rs.getInt("InterConference");
				int SumDiscipAward = rs.getInt("SumDiscipAward");
				int InterD = rs.getInt("InterD");
				int NationD = rs.getInt("NationD");
				int ProviD = rs.getInt("ProviD");
				int CityD = rs.getInt("CityD");
				int SchD = rs.getInt("SchD");
				int SumActAward = rs.getInt("SumActAward");
				int InterA = rs.getInt("InterA");
				int NationA = rs.getInt("NationA");
				int ProviA = rs.getInt("ProviA");
				int CityA = rs.getInt("CityA");
				int SchA = rs.getInt("SchA");
				int SumLiterSportAward = rs.getInt("SumLiterSportAward");
				int InterLS = rs.getInt("InterLS");
				int NationLS = rs.getInt("NationLS");
				int ProviLS = rs.getInt("ProviLS");
				int CityLS = rs.getInt("CityLS");
				int SchLS = rs.getInt("SchLS");
				
				bean.setCET4(CET4);bean.setCET6(CET6);bean.setCityA(CityA);bean.setCityD(CityD);bean.setCityLS(CityLS);
				bean.setConQualify(ConQualify);bean.setConReach(ConReach);bean.setInterA(InterA);bean.setInterConference(InterConference);
				bean.setInterD(InterD);bean.setInterLS(InterLS);bean.setJingxiNCRE(JingxiNCRE);bean.setNationA(NationA);
				bean.setNationD(NationD);bean.setNationLS(NationLS);bean.setNCRE(NCRE);bean.setPaperNum(PaperNum);
				bean.setPatentNum(PatentNum);bean.setProviA(ProviA);bean.setProviD(ProviD);bean.setProviLS(ProviLS);
				bean.setSchA(SchA);bean.setSchD(SchD);bean.setSchLS(SchLS);bean.setSumActAward(SumActAward);bean.setSumDiscipAward(SumDiscipAward);
				bean.setSumLiterSportAward(SumLiterSportAward);bean.setWorkNum(WorkNum);
				bean.setTime(TimeUtil.changeDateY(year));
				list.add(bean);
				
			 }
			 
		 }catch (Exception e){
			 e.printStackTrace();
		 }finally{
				DBConnection.close(conn);
			}
		 
		 return list;
	}
	
	public static void main(String arg[]){
		S65_Dao dao=new S65_Dao();
		List<S65_Bean> list = dao.getOriData("2014");
		if(list!=null){
			S65_Bean bean = list.get(0);
			System.out.println(bean.getNationA());
			
		}
		
	}
	
}
