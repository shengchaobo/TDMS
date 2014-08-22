package cn.nit.dao.table6;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import cn.nit.bean.table6.S65_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
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

	/**统计T651 表中的获奖数据*/
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
	
	
	

	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 显示数据
	 * @param conditions 查询条件
	 * @param fillUnitId 填报人单位号，如果为空，则查询所有未审核的数据，<br>如果不为空，则查询填报人自己单位的所有的数据
	 * @return
	 */
	public S65_Bean getYearInfo(String year){
		
		StringBuffer sql = new StringBuffer() ;
		List<S65_Bean> list=null;
		S65_Bean bean=null;
        
		sql.append("select * from "+ tableName);
		sql.append(" where convert(varchar(4),Time,120)=" + year);
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
//		System.out.println(sql.toString());
		
		try{
			st = conn.createStatement() ;
//			ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY
//			st.setMaxRows(1) ;
			rs = st.executeQuery(sql.toString()) ;
//			rs.absolute((page - 1) * rows) ;
			list = DAOUtil.getList(rs, S65_Bean.class) ;
			if(list.size() != 0){
				bean = list.get(0);
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}
		return bean ;
	}

	public boolean save(S65_Bean bean, String year){
			
			String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year;		
			boolean flag = false;
			Connection conn = DBConnection.instance.getConnection() ;
			
			Statement st = null ;
			ResultSet rs = null ;
			List<S65_Bean> list = null ;
			S65_Bean tempBean = null;
			try{
				st = conn.createStatement() ;
				rs = st.executeQuery(sql) ;
				list = DAOUtil.getList(rs, S65_Bean.class) ;
				if(list.size() != 0){
					tempBean = list.get(0);
					bean.setSeqNumber(tempBean.getSeqNumber());
					String tempfields = "PaperNum,WorkNum,PatentNum,CET4,CET6,NCRE,JingxiNCRE,ConQualify,ConReach,InterConference," +
							"SumDiscipAward,InterD,NationD,ProviD,CityD,SchD,SumActAward,InterA,NationA,ProviA,CityA," +
							"SchA,SumLiterSportAward,InterLS,NationLS,ProviLS,CityLS,SchLS";
					flag = DAOUtil.update(bean, tableName, key, tempfields, conn) ;
				}else{
					bean.setTime(TimeUtil.changeDateY(year));
					String tempfields = "PaperNum,WorkNum,PatentNum,CET4,CET6,NCRE,JingxiNCRE,ConQualify,ConReach,InterConference," +
							"SumDiscipAward,InterD,NationD,ProviD,CityD,SchD,SumActAward,InterA,NationA,ProviA,CityA," +
							"SchA,SumLiterSportAward,InterLS,NationLS,ProviLS,CityLS,SchLS,Time";
					flag = DAOUtil.insert(bean, tableName, tempfields, conn) ;
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
//		S65_Dao dao=new S65_Dao();

		
	}
	
}
