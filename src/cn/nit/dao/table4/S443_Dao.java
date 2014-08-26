package cn.nit.dao.table4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import cn.nit.bean.table4.S443_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class S443_Dao {
	
	
	/**  数据库表名  */
	private String tableName = "S443_HighLevelTalent$" ;
	
	/**待统计数据库表名*/
	private String tableName1 = "T443_HighLevelTalent_Per$";
	
	
	private String tableName2 = "DiTalentType";
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "talentType,talentNum,Time,Note" ;

	

    /**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<S443_Bean> totalList(String year){
		
		String sql = "select " + key+ "," +field + " from " + tableName 
				+ " where convert(varchar(4),Time,120)=" + year;		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<S443_Bean> list = null ;
		System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, S443_Bean.class) ;
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
 	public boolean save(List<S443_Bean> list,String year){
 		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year;		
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection() ;		
		Statement st = null ;
		ResultSet rs = null ;
		List<S443_Bean> templist = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			templist = DAOUtil.getList(rs, S443_Bean.class) ;
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

	public List<S443_Bean> getYearInfo(String year)
	{
		
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<S443_Bean> list = new ArrayList<S443_Bean>() ;
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
		
		
		
	String querysql="select "+tableName2+".TalentType as talentType,count("+tableName1+".Name) as talentNum from "+tableName2+
	" left join "+tableName1+" on "+tableName2+".IndexID = "+tableName1+".Type "+
	" and Time like '"+year+"%' group by "+tableName2+".TalentType,"+tableName2+".IndexID" +
	" order by "+tableName2+".IndexID";

		System.out.println(querysql);


		
		int sum=0,talentNum=0;
		String talentType=null;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(querysql) ;
			while(rs.next()){
				talentType = rs.getString("talentType");				
				talentNum = rs.getInt("talentNum");
				sum += talentNum;

				S443_Bean s443_Bean=new S443_Bean();	
				s443_Bean.setTalentType(talentType);
				s443_Bean.setTalentNum(talentNum);
				s443_Bean.setTime(TimeUtil.changeDateY(year));								
				list.add(s443_Bean);
				
			}
			
			if(list.size()!=0){
				S443_Bean s443_Bean=new S443_Bean();
				s443_Bean.setTalentType("合计");
				s443_Bean.setTalentNum(sum);
				s443_Bean.setTime(TimeUtil.changeDateY(year));
				list.add(0,s443_Bean);
				
				S443_Dao s443_Dao = new S443_Dao();
				s443_Dao.save(list, year);
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
