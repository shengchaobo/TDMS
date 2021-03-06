package cn.nit.dao.table4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.nit.bean.table4.S444_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class S444_Dao {
	
	
	
	/**  数据库表名  */
	private String tableName = "S444_HighLevelResTeam" ;
	
	/**待统计数据库表名*/
	private String tableName1 = "T444_HighLevelResTeam_Res$";
	
	
	private String tableName2 = "DiResearchTeam";
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "TeamType,TeamNum,Time,Note" ;

	

    /**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<S444_Bean> totalList(String year){
		
		String sql = "select " + key+ "," +field + " from " + tableName 
				+ " where convert(varchar(4),Time,120)=" + year;		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<S444_Bean> list = null ;
		System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, S444_Bean.class) ;
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
 	 * 模板导入
 	 * @param diCourseCategories
 	 * @return
 	 *
 	 * @time: 2014-5-14/下午02:34:23
 	 */
 	public boolean save(List<S444_Bean> list,String year){
 		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year;		
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection() ;		
		Statement st = null ;
		ResultSet rs = null ;
		List<S444_Bean> templist = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			templist = DAOUtil.getList(rs, S444_Bean.class) ;
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
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		
 		
 		return flag ;
 		
 	}
	/**得到数据*/

	public List<S444_Bean> getYearInfo(String year)
	{
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<S444_Bean> list = new ArrayList<S444_Bean>() ;
		String sql = "select * from "+tableName1+" where time like '"+year+"%'";
		
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
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		
		
		
	String querysql="select "+tableName2+".ResearchTeam as TeamType,count("+tableName1+".ResField) as TeamNum from "+tableName1+
	" right join "+tableName2+" on "+tableName2+".IndexID = "+tableName1+".Type "+
	" and Time like '"+year+"%' group by "+tableName2+".ResearchTeam,"+tableName2+".IndexID " +
	" order by "+tableName2+".IndexID";
   
		System.out.println(querysql);

		Connection conn1 = DBConnection.instance.getConnection() ;
		Statement st1 = null ;
		ResultSet rs1 = null ;
		int sum=0,teamNum=0;
		String teamType=null;
		
		try{
			st1 = conn1.createStatement() ;
			rs1 = st1.executeQuery(querysql) ;
			while(rs1.next()){
				teamType = rs1.getString("TeamType");				
				teamNum = rs1.getInt("TeamNum");
				sum += teamNum;

				S444_Bean s444_Bean=new S444_Bean();	
				s444_Bean.setTeamType(teamType);
				s444_Bean.setTeamNum(teamNum);
				s444_Bean.setTime(TimeUtil.changeDateY(year));								
				list.add(s444_Bean);
				
			}
			
			if(list.size()!=0){
				S444_Bean s444_Bean=new S444_Bean();
				s444_Bean.setTeamType("合计");
				s444_Bean.setTeamNum(sum);
				s444_Bean.setTime(TimeUtil.changeDateY(year));
				list.add(0,s444_Bean);
				
				S444_Dao s444_Dao = new S444_Dao();
				s444_Dao.save(list, year);
			}		
		}catch(Exception e){
			e.printStackTrace() ;
			return null;
		}finally{
			DBConnection.close(rs1);
			DBConnection.close(st1);	
			DBConnection.close(conn1);
		}
		
		return list ;
	}
	

	
	public static void main(String arg[]){
		//S25_DAO t=new S25_DAO();
	   // List<S25_Bean> list =t.getData("2014");
		//System.out.println(list.size());
	}

}
