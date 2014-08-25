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
