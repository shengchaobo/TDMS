package cn.nit.dao.table2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import cn.nit.bean.table2.S25_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class S25_Dao {
	
	/**  数据库表名  */
	private String tableName = "S25_PractisePlace$" ;
	
	/**待统计数据库表名*/
	private String tableName1 = "T251_PractisePlaceInfo_EQU$";
	
	private String tableName2 = "T252_PractisePlaceInfo_Tea$";
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "expCenterName,teaUnit,unitID,machNum,money,area,newArea,labHour,labStuNum,yearHour,yearTimes," +
			"itemNum,Time,Note" ;

	
	
    /**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<S25_Bean> totalList(String year){
		
		String sql = "select " + key+ "," +field + " from " + tableName 
				+ " where convert(varchar(4),Time,120)=" + year;		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<S25_Bean> list = null ;
		System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, S25_Bean.class) ;
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
 	public boolean save(List<S25_Bean> list,String year){
 		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year;		
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection() ;		
		Statement st = null ;
		ResultSet rs = null ;
		List<S25_Bean> templist = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			templist = DAOUtil.getList(rs, S25_Bean.class) ;
			if(templist.size() != 0){
				String delSql = "delete from " + tableName + " where convert(varchar(4),Time,120)=" + year;
				int delflag = st.executeUpdate(delSql.toString());
				if(delflag > 0 ){
					flag = DAOUtil.batchInsert(list, tableName, field, conn) ;
				}
			}else{
				flag = DAOUtil.batchInsert(list, tableName, field, conn) ;
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

	public List<S25_Bean> getYearInfo(String year)
	{
		
	String querysql="Select " + tableName1 + ".ExpCenterName," + tableName1 + ".TeaUnit,"
					+ tableName1 + ".TeaUnitID AS UnitID,MachNum,Money,Area,NewAddArea AS NewArea,"
					+ "ExpClassHour AS LabHour,StuNum AS LabStuNum,ExpHour AS YearHour,"
					+ "ExpTimes AS YearTimes,PractiseItemNum AS ItemNum," + tableName1 + ".Time," + tableName1 + ".Note"
					+ " from " + tableName1 + "," + tableName2 
					+ " where " + tableName1 + ".ExpCenterName=" + tableName2 + ".ExpCenterName" 
					+ " and convert(varchar(4),T251_PractisePlaceInfo_EQU$.Time,120)=" + year
					+ " and convert(varchar(4),T252_PractisePlaceInfo_Tea$.Time,120)=" + year;
   
		System.out.println(querysql);
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<S25_Bean> list = new ArrayList<S25_Bean>() ;
		
		int sumMachNum = 0,sumLabStuNum=0,sumLabHour=0,sumYearHour=0,sumYearTimes=0,sumItemNum=0;
		double sumMoney = 0,sumArea=0,sumNewArea=0;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(querysql) ;
			while(rs.next()){
				String expCenterName = rs.getString("ExpCenterName");				
				String teaUnit=rs.getString("TeaUnit");
				String unitId=rs.getString("UnitID");

				sumMachNum += rs.getInt("MachNum");
				sumMoney += rs.getDouble("Money");
				sumArea += rs.getDouble("Area");
				sumNewArea += rs.getDouble("NewArea");
				sumLabStuNum  += rs.getInt("LabStuNum");
				sumLabHour += rs.getInt("LabHour");
				sumYearHour += rs.getInt("YearHour");
				sumYearTimes += rs.getInt("YearTimes");
				sumItemNum += rs.getInt("ItemNum");

				S25_Bean s25_Bean=new S25_Bean();	
				s25_Bean.setExpCenterName(expCenterName);
				s25_Bean.setTeaUnit(teaUnit);
				s25_Bean.setUnitID(unitId);
				s25_Bean.setMachNum(rs.getInt("MachNum"));
				s25_Bean.setMoney(rs.getDouble("Money"));
				s25_Bean.setArea(rs.getDouble("Area"));
				s25_Bean.setNewArea(rs.getDouble("NewArea"));
				s25_Bean.setLabStuNum(rs.getInt("LabStuNum"));
				s25_Bean.setLabHour(rs.getInt("LabHour"));
				s25_Bean.setYearHour(rs.getInt("YearHour"));
				s25_Bean.setYearTimes(rs.getInt("YearTimes"));
				s25_Bean.setItemNum(rs.getInt("ItemNum"));
				s25_Bean.setTime(TimeUtil.changeDateY(year));
								
				list.add(s25_Bean);
				
			}
			
			if(list.size()!=0){
				S25_Bean s25_Bean=new S25_Bean();
				s25_Bean.setExpCenterName("全校合计：");
				s25_Bean.setTeaUnit("");
				s25_Bean.setUnitID("");
				s25_Bean.setMachNum(sumMachNum);
				s25_Bean.setMoney(sumMoney);
				s25_Bean.setArea(sumArea);
				s25_Bean.setNewArea(sumNewArea);
				s25_Bean.setLabStuNum(sumLabStuNum);
				s25_Bean.setLabHour(sumLabHour);
				s25_Bean.setYearHour(sumYearHour);
				s25_Bean.setYearTimes(sumYearTimes);
				s25_Bean.setItemNum(sumItemNum);
				s25_Bean.setTime(TimeUtil.changeDateY(year));
				list.add(0,s25_Bean);
				
				S25_Dao s25_Dao = new S25_Dao();
				s25_Dao.save(list, year);
			}		
		}catch(Exception e){
			e.printStackTrace() ;
			return null;
		}
		
		return list ;
	}
	

	
	public static void main(String arg[]){
		//S25_DAO t=new S25_DAO();
	   // List<S25_Bean> list =t.getData("2014");
		//System.out.println(list.size());
	}
}
