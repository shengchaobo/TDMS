package cn.nit.dao.table4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import cn.nit.bean.table4.S453_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class S453_Dao {
	
	
	
	
	/**  数据库表名  */
	private String tableName = "S453_TeaCommunInfo$" ;
	
	/**待统计数据库表名*/
	private  String tableName1 = "T453_TeaCommunInfo_TeaInter$";
	
	
	private  String tableName2 = "DiDepartment";
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "TeaUnit,UnitID,ComeTalk,GoTalk," +
			"OneMonth,OneToThreeMonth,AboveThreeMonth,InPlace,OutPlace,Time,Note" ;

	

    /**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<S453_Bean> totalList(String year){
		
		String sql = "select " + key+ "," +field + " from " + tableName 
				+ " where convert(varchar(4),Time,120)=" + year;		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<S453_Bean> list = null ;
		System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, S453_Bean.class) ;
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
 	public boolean save(List<S453_Bean> list,String year){
 		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year;		
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection() ;		
		Statement st = null ;
		ResultSet rs = null ;
		List<S453_Bean> templist = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			templist = DAOUtil.getList(rs, S453_Bean.class) ;
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

	public List<S453_Bean> getYearInfo(String year)
	{
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<S453_Bean> list = new ArrayList<S453_Bean>() ;
		String sql="select * from "+tableName1+" where Time like '"+year+"%'";
		
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
		
		
		
	String querysql="select "+tableName2+".UnitName as TeaUnit,"+tableName2+".UnitID as UnitID," +
	"sum(case when "+tableName1+".CommunType = '来访' then 1 else 0 end) as ComeTalk," +
	"sum(case when "+tableName1+".CommunType = '出访' then 1 else 0 end) as GoTalk," +
	"sum(case when DateDiff(day,"+tableName1+".BeginTime ,"+tableName1+".EndTime)<30 then 1 else 0 end) as OneMonth," +
	"sum(case when DateDiff(day,"+tableName1+".BeginTime ,"+tableName1+".EndTime)>=30 and DateDiff(day,"+tableName1+".BeginTime ,"+tableName1+".EndTime)<=90 then 1 else 0 end) as OneToThreeMonth," +
	"sum(case when DateDiff(day,"+tableName1+".BeginTime ,"+tableName1+".EndTime)>90 then 1 else 0 end) as AboveThreeMonth," +
	"sum(case when "+tableName1+".InOrOut = '境内' then 1 else 0 end) as InPlace," +
	"sum(case when "+tableName1+".InOrOut = '境外' then 1 else 0 end) as OutPlace" +	
	" from "+tableName1+
	" right join "+tableName2+" on "+tableName2+".UnitID = "+tableName1+".UnitID "+
	" and Time like '"+year+"%' where "+tableName2+".UnitID like '3%' group by "+tableName2+".UnitName,"+tableName2+".UnitID " +
	" order by "+tableName2+".UnitID";
   
		System.out.println(querysql);


		
		int sum1=0,sum2=0,sum3=0,sum4=0,sum5=0,sum6=0,sum7=0;
		int num1=0,num2=0,num3=0,num4=0,num5=0,num6=0,num7=0;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(querysql) ;
			while(rs.next()){
				
				num1 = rs.getInt("ComeTalk");
				num2 = rs.getInt("GoTalk");
				num3 = rs.getInt("OneMonth");
				num4 = rs.getInt("OneToThreeMonth");
				num5 = rs.getInt("AboveThreeMonth");
				num6 = rs.getInt("InPlace");
				num7 = rs.getInt("OutPlace");
				
				sum1 += num1;
				sum2 += num2;
				sum3 += num3;
				sum4 += num4;
				sum5 += num5;
				sum6 += num6;
				sum7 += num7;
				
				S453_Bean s453_Bean=new S453_Bean();
				s453_Bean.setTeaUnit(rs.getString("TeaUnit"));
				s453_Bean.setUnitID(rs.getString("UnitID"));
				s453_Bean.setComeTalk(num1);
				s453_Bean.setGoTalk(num2);
				s453_Bean.setOneMonth(num3);
				s453_Bean.setOneToThreeMonth(num4);
				s453_Bean.setAboveThreeMonth(num5);
				s453_Bean.setInPlace(num6);
				s453_Bean.setOutPlace(num7);
				s453_Bean.setTime(TimeUtil.changeDateY(year));								
				list.add(s453_Bean);
				
			}
			
			if(list.size()!=0){
				S453_Bean s453_Bean=new S453_Bean();
				s453_Bean.setTeaUnit("合计");
				s453_Bean.setUnitID(null);			
				s453_Bean.setComeTalk(sum1);
				s453_Bean.setGoTalk(sum2);
				s453_Bean.setOneMonth(sum3);
				s453_Bean.setOneToThreeMonth(sum4);
				s453_Bean.setAboveThreeMonth(sum5);
				s453_Bean.setInPlace(sum6);
				s453_Bean.setOutPlace(sum7);	
				s453_Bean.setTime(TimeUtil.changeDateY(year));	
				list.add(0,s453_Bean);
				
				S453_Dao s453_Dao = new S453_Dao();
				s453_Dao.save(list, year);
			}		
		}catch(Exception e){
			e.printStackTrace() ;
			return null;
		}
		
		return list ;
	}

}
