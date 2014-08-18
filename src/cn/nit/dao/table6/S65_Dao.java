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
	/**  数据库表名 (本科生竞赛获奖) */
	private String tableName1="T651_StuCompetiAwardInfo_TeaYLC$";
	
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

	/**统计T651表中的获奖数据*/
	public List<S65_Bean> getStuAward(String year){
		List<S65_Bean> list= new ArrayList<S65_Bean>();
		StringBuffer sql = new StringBuffer();
		//本科生学科竞赛
		sql.append("select sum(case when t.CompetiType = '55000' then 1 else 0 end) AS SumDiscipAward, ");
		sql.append(" sum(case when t.CompetiType = '55000' and t.AwardLevel ='50000' then 1 else 0 end) AS InterD, ");
		sql.append(" sum(case when t.CompetiType = '55000' and t.AwardLevel ='50001' then 1 else 0 end) AS NationD, ");
		sql.append(" sum(case when t.CompetiType = '55000' and t.AwardLevel ='50002' then 1 else 0 end) AS ProviD, ");
		sql.append(" sum(case when t.CompetiType = '55000' and t.AwardLevel ='50003' then 1 else 0 end) AS CityD, ");
		sql.append(" sum(case when t.CompetiType = '55000' and t.AwardLevel ='50004' then 1 else 0 end) AS SchD, ");
		//本科生创新活动
		sql.append(" sum(case when t.CompetiType = '55001' then 1 else 0 end) AS SumActAward, ");
		sql.append(" sum(case when t.CompetiType = '55001' and t.AwardLevel ='50000' then 1 else 0 end) AS InterA, ");
		sql.append(" sum(case when t.CompetiType = '55001' and t.AwardLevel ='50001' then 1 else 0 end) AS NationA, ");
		sql.append(" sum(case when t.CompetiType = '55001' and t.AwardLevel ='50002' then 1 else 0 end) AS ProviA, ");
		sql.append(" sum(case when t.CompetiType = '55001' and t.AwardLevel ='50003' then 1 else 0 end) AS CityA, ");
		sql.append(" sum(case when t.CompetiType = '55001' and t.AwardLevel ='50004' then 1 else 0 end) AS SchA, ");
		//本科生文艺、体育竞赛
		sql.append("  sum(case when t.CompetiType = '55002' then 1 else 0 end) AS SumLiterSportAward, ");
		sql.append(" sum(case when t.CompetiType = '55002' and t.AwardLevel ='50000' then 1 else 0 end) AS InterLS, ");
		sql.append(" sum(case when t.CompetiType = '55002' and t.AwardLevel ='50001' then 1 else 0 end) AS NationLS, ");
		sql.append(" sum(case when t.CompetiType = '55002' and t.AwardLevel ='50002' then 1 else 0 end) AS ProviLS, ");
		sql.append(" sum(case when t.CompetiType = '55002' and t.AwardLevel ='50003' then 1 else 0 end) AS CityLS, ");
		sql.append(" sum(case when t.CompetiType = '55002' and t.AwardLevel ='50004' then 1 else 0 end) AS SchLS ");
		sql.append(" from T651_StuCompetiAwardInfo_TeaYLC$ as t, DiAwardLevel as dia,DiContestLevel as dic");
		sql.append(" where t.AwardLevel =dia.IndexID and t.CompetiType = dic.IndexID  ");
		sql.append(" and t.Time like '" + year+"%'");
//		sql.append(" left join T651_StuCompetiAwardInfo_TeaYLC$ on DiAwardLevel.IndexID = T651_StuCompetiAwardInfo_TeaYLC$.AwardLevel");
//		sql.append("  T651_StuCompetiAwardInfo_TeaYLC$ on DiContestLevel.IndexID = T651_StuCompetiAwardInfo_TeaYLC$.CompetiType ");
//		sql.append(" where convert(varchar(4),T651_StuCompetiAwardInfo_TeaYLC$.Time,120) = "  +  year);
		
//		System.out.println(sql.toString());
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			while(rs.next()){
					System.out.println(" 计数");
					S65_Bean bean = new S65_Bean();
					int SumDiscipAward = rs.getInt("SumDiscipAward"); bean.setSumDiscipAward(SumDiscipAward);
					int InterD = rs.getInt("InterD");  bean.setInterD(InterD);
					System.out.println(InterD);
					int NationD = rs.getInt("NationD");  bean.setNationD(NationD);
					System.out.println(NationD);
					int ProviD = rs.getInt("ProviD");  bean.setProviD(ProviD);
					System.out.println(ProviD);
					int CityD = rs.getInt("CityD");  bean.setCityD(CityD);
					System.out.println(CityD);
					int SchD = rs.getInt("SchD");  bean.setSchD(SchD);
					
					int SumActAward = rs.getInt("SumActAward");  bean.setSumActAward(SumActAward);
					int InterA = rs.getInt("InterA");  bean.setInterA(InterA);
					int NationA = rs.getInt("NationA");  bean.setNationA(NationA);
					int ProviA = rs.getInt("ProviA");   bean.setProviA(ProviA);
					int CityA = rs.getInt("CityA");  bean.setCityA(CityA);
					int SchA= rs.getInt("SchA");  bean.setSchA(SchA);
//					int SumActAward =InterA+NationA+ProviA+CityA+SchA;  bean.setSumDiscipAward(SumActAward);
					
					int SumLiterSportAward = rs.getInt("SumLiterSportAward");  bean.setSumLiterSportAward( SumLiterSportAward);
					int InterLS = rs.getInt("InterLS");  bean.setInterLS(InterLS);
					int NationLS = rs.getInt("NationLS");  bean.setNationLS(NationLS);
					int ProviLS = rs.getInt("ProviLS");  bean.setSumDiscipAward(SumDiscipAward);
					int CityLS = rs.getInt("CityLS");  bean.setCityLS(CityLS);
					int SchLS = rs.getInt("SchLS");  bean.setSchLS(SchLS);
//					int SumLiterSportAward =InterLS+NationLS+ProviLS+CityLS+SchLS;  bean.setSumDiscipAward(SumLiterSportAward);
					bean.setTime(TimeUtil.changeDateY(year));
				    list.add(bean);
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return null;
		}
		return list;
	}
	
	/**T652学生发表论文数*/
	public int getPaper(String year){
		int PaperNum = 0;
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) AS PaperNum from T652_StuPublishPaper_TeaYLC$");
		sql.append(" where convert(varchar(4),T652_StuPublishPaper_TeaYLC$.Time,120) =" + year);
		Connection conn=DBConnection.instance.getConnection();
		Statement st=null;
		ResultSet rs=null;
		
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			while(rs.next()){
				PaperNum = rs.getInt("PaperNum");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConnection.close(conn);
		}
		
		return PaperNum;
	}

	/**T653学生发表作品数*/
	public int getWork(String year){
		int WorkNum = 0;
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) AS WorkNum from T653_StuPublishWord_TeaYLC$");
		sql.append(" where convert(varchar(4),T653_StuPublishWord_TeaYLC$.Time,120) =" + year);
		Connection conn=DBConnection.instance.getConnection();
		Statement st=null;
		ResultSet rs=null;
		
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			while(rs.next()){
				WorkNum = rs.getInt("WorkNum");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConnection.close(conn);
		}
		
		return WorkNum;
	}
	
	/**T653学生专利数*/
	public int getPatent(String year){
		int PatentNum = 0;
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) AS PatentNum from T654_StuAwardPatent_TeaYLC$");
		sql.append(" where convert(varchar(4),T654_StuAwardPatent_TeaYLC$.Time,120) =" + year);
		Connection conn=DBConnection.instance.getConnection();
		Statement st=null;
		ResultSet rs=null;
		
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			while(rs.next()){
				PatentNum = rs.getInt("PatentNum");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConnection.close(conn);
		}
		
		return PatentNum;
	}
	
	/**T658参加国际会议学生人数*/
	public int getInterConference(String year){
		int InterConference = 0;
		StringBuffer sql = new StringBuffer();
		sql.append(" select sum(AwardStuNum) AS InterConference from T658_InInterConference_TeaInter$");
		sql.append(" where convert(varchar(4),T658_InInterConference_TeaInter$.Time,120) =" + year);
		Connection conn=DBConnection.instance.getConnection();
		Statement st=null;
		ResultSet rs=null;
		
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			while(rs.next()){
				InterConference = rs.getInt("InterConference");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConnection.close(conn);
		}
		
		return InterConference;
	}
	
	
	public static void main(String arg[]){
		S65_Dao dao=new S65_Dao();
		/**测试getStuAward*/
//		List<S65_Bean> list = dao.getStuAward("2010");
//		System.out.println("siza:"+list.size());
//		if(list.size()>0){
//			S65_Bean bean = list.get(0);
//			System.out.println("zong "+bean.getSumDiscipAward());
//			System.out.println("zong "+bean.getInterD());
//			System.out.println("zong "+bean.getNationD());
//		}else{
//			System.out.println("list为空");
//		}
		/**测试论文数*/
//		int paperNum = dao.getPaper("2010");
//		System.out.println(paperNum);
		
		/**测试作品数*/
//		int workNum = dao.getPaper("2008");
//		System.out.println(workNum);
		
		/**测试专利数*/
//		int patentNum = dao.getPatent("2014");
//		System.out.println(patentNum);
		
		/**测试参加会议人数*/
		int conferStuNum = dao.getInterConference("2010");
		System.out.println(conferStuNum);
	}
	
}
