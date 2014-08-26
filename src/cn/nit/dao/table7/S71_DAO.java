package cn.nit.dao.table7;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import cn.nit.bean.table7.S71_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class S71_DAO {
	
	/**  数据库表名  */
	private String tableName = "S71_TeaManagerAchieve$" ;
	
	/**待统计数据库表名*/
	//private String tableName1="T711_TeaManagerAwardInfo_TeaTea$";
	
	//private String tableName2="T712_TeaManagerPaperInfo_TeaTea$";
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "TeaUnit,UnitID,SumTeaAward,InterAward,NationAward,ProviAward,CityAward,SchAward,SumTeaPaper,TeaResPaper,TeaManagePaper,Time,Note" ;

	
	
    /**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<S71_Bean> totalList(String year){
		
		String sql = "select " + key+ "," +field + " from " + tableName 
				+ " where convert(varchar(4),Time,120)=" + year;		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<S71_Bean> list = null ;
		System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, S71_Bean.class) ;
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
 	public boolean save(List<S71_Bean> list,String year){
 		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year;		
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection() ;		
		Statement st = null ;
		ResultSet rs = null ;
		List<S71_Bean> templist = null ;
		String tempField = "TeaUnit,UnitID,SumTeaAward,InterAward,NationAward,ProviAward,CityAward,SchAward,SumTeaPaper,TeaResPaper,TeaManagePaper,Time,Note";
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			templist = DAOUtil.getList(rs, S71_Bean.class) ;
			if(templist.size() != 0){
				String delSql = "delete from " + tableName + " where convert(varchar(4),Time,120)=" + year;
				int delflag = st.executeUpdate(delSql.toString());
				if(delflag > 0 ){
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
	/**得到数据*/

	public List<S71_Bean> getData(String year)
	{
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<S71_Bean> list = new ArrayList<S71_Bean>() ;
		String sql1 = "select * from DiDepartment "+
		 " left join T711_TeaManagerAwardInfo_TeaTea$ on DiDepartment.UnitID = T711_TeaManagerAwardInfo_TeaTea$.UnitID "+
		 " left join T712_TeaManagerPaperInfo_TeaTea$ on DiDepartment.UnitID = T712_TeaManagerPaperInfo_TeaTea$.UnitID "+
		 " where convert(varchar(4),T711_TeaManagerAwardInfo_TeaTea$.Time,120) = "  +  year  +  
		 " and "  +  "convert(varchar(4),T712_TeaManagerPaperInfo_TeaTea$.Time,120) = "  +  year+
		 " and DiDepartment.UnitID like '3%'";;
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql1);
			if(!rs.next()){
				System.out.println("统计数据不全啊  ");
				return list;
			}
			
		}catch(Exception e){
			e.printStackTrace() ;
			return null;
		}

		
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<S71_Bean> list = new ArrayList<S71_Bean>() ;
		String sql = "select * from DiDepartment "+
		 " left join T711_TeaManagerAwardInfo_TeaTea$ on DiDepartment.UnitID = T711_TeaManagerAwardInfo_TeaTea$.UnitID "+
		 " left join T712_TeaManagerPaperInfo_TeaTea$ on DiDepartment.UnitID = T712_TeaManagerPaperInfo_TeaTea$.UnitID "+
		 " where convert(varchar(4),T711_TeaManagerAwardInfo_TeaTea$.Time,120) = "  +  year  +  
		 " and "  +  "convert(varchar(4),T712_TeaManagerPaperInfo_TeaTea$.Time,120) = "  +  year+
		 " and DiDepartment.UnitID like '3%'";;
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(!rs.next()){
				System.out.println("统计数据不全啊  ");
				return list;
			}
			
		}catch(Exception e){
			e.printStackTrace() ;
			return null;
		}
		
		
		
		
	String querysql=" select UnitName AS TeaUnit,DiDepartment.UnitID, " +
    " Count(AwardLevel) AS SumTeaAward, "+
	" sum(case when AwardLevel='50000' then 1 else 0 end) AS InterAward, "+
	" sum(case when AwardLevel='50001' then 1 else 0 end) AS NationAward, "+
	" sum(case when AwardLevel='50002' then 1 else 0 end) AS ProviAward, "+
	" sum(case when AwardLevel='50003' then 1 else 0 end) AS CityAward, "+
	" sum(case when AwardLevel='50004' then 1 else 0 end) AS SchAward, "+
    " COUNT(PaperType) AS SumTeaPaper,"+
    " sum(case when PaperType='教学研究' then 1 else 0 end) AS TeaResPaper,"+
    " sum(case when PaperType='教学管理' then 1 else 0 end) AS TeaManagePaper"+
    " from DiDepartment "+
    " left join T711_TeaManagerAwardInfo_TeaTea$ on DiDepartment.UnitID = T711_TeaManagerAwardInfo_TeaTea$.UnitID "+
    " and convert(varchar(4),T711_TeaManagerAwardInfo_TeaTea$.Time,120) = "  +  year  +  
    " left join T712_TeaManagerPaperInfo_TeaTea$ on DiDepartment.UnitID = T712_TeaManagerPaperInfo_TeaTea$.UnitID "+
    " and "  +  "convert(varchar(4),T712_TeaManagerPaperInfo_TeaTea$.Time,120) = "  +  year  +  
    " where DiDepartment.UnitID like '3%' group by DiDepartment.UnitID,UnitName;";
   

		//System.out.println(querysql);
		int sumTeaAward=0,interAward=0,nationAward=0,proviAward=0,
				cityAward=0,schAward=0,sumTeaPaper=0,teaResPaper=0,teaManagePaper=0;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(querysql) ;
			while(rs.next()){
								
				String teaUnit=rs.getString("TeaUnit");
				String unitId=rs.getString("UnitID");

				sumTeaAward += rs.getInt("SumTeaAward");
				interAward += rs.getInt("InterAward");
				nationAward += rs.getInt("NationAward");
				proviAward += rs.getInt("ProviAward");
				cityAward  += rs.getInt("CityAward");
				schAward += rs.getInt("SchAward");
				sumTeaPaper += rs.getInt("SumTeaPaper");
				teaResPaper += rs.getInt("TeaResPaper");
				teaManagePaper += rs.getInt("TeaManagePaper");
				

				S71_Bean s71_Bean=new S71_Bean();			
				s71_Bean.setTeaUnit(teaUnit);
				s71_Bean.setUnitID(unitId);
				s71_Bean.setSumTeaAward(rs.getInt("SumTeaAward"));
				s71_Bean.setInterAward(rs.getInt("InterAward"));
				s71_Bean.setNationAward(rs.getInt("NationAward"));
				s71_Bean.setProviAward(rs.getInt("ProviAward"));
				s71_Bean.setCityAward(rs.getInt("CityAward"));
				s71_Bean.setSchAward(rs.getInt("SchAward"));
				s71_Bean.setSumTeaPaper(rs.getInt("SumTeaPaper"));
				s71_Bean.setTeaResPaper(rs.getInt("TeaResPaper"));
				s71_Bean.setTeaManagePaper(rs.getInt("TeaManagePaper"));
				s71_Bean.setTime(TimeUtil.changeDateY(year));
								
				list.add(s71_Bean);
				
				System.out.println(list.size());
			}
			
			if(list.size()!=0){
				S71_Bean s71_Bean=new S71_Bean();			
				s71_Bean.setTeaUnit("全校合计");
				s71_Bean.setUnitID("");
				s71_Bean.setSumTeaAward(sumTeaAward);
				s71_Bean.setInterAward(interAward);
				s71_Bean.setNationAward(nationAward);
				s71_Bean.setProviAward(proviAward);
				s71_Bean.setCityAward(cityAward);
				s71_Bean.setSchAward(schAward);
				s71_Bean.setSumTeaPaper(sumTeaPaper);
				s71_Bean.setTeaResPaper(teaResPaper);
				s71_Bean.setTeaManagePaper(teaManagePaper);
				s71_Bean.setTime(TimeUtil.changeDateY(year));
				list.add(0,s71_Bean);
				
				S71_DAO s71_Dao = new S71_DAO();
				s71_Dao.save(list, year);
			}		
		}catch(Exception e){
			e.printStackTrace() ;
			return null;
		}
		
		return list ;
	}
	

	
	public static void main(String arg[]){
		String sql = "select * from DiDepartment "+
		 " left join T711_TeaManagerAwardInfo_TeaTea$ on DiDepartment.UnitID = T711_TeaManagerAwardInfo_TeaTea$.UnitID "+
		 " left join T712_TeaManagerPaperInfo_TeaTea$ on DiDepartment.UnitID = T712_TeaManagerPaperInfo_TeaTea$.UnitID "+
		 " where convert(varchar(4),T711_TeaManagerAwardInfo_TeaTea$.Time,120) = "  +  " 2010 "  +  
		 " and "  +  "convert(varchar(4),T712_TeaManagerPaperInfo_TeaTea$.Time,120) = "  +  " 2010 "  ;
		System.out.println(sql);
	}
}
