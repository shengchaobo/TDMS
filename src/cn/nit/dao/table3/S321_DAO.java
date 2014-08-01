package cn.nit.dao.table3;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.table3.S321_Bean;

import cn.nit.dbconnection.DBConnection;

import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class S321_DAO {
	
	/**  数据库表名  */
	private String tableName = "S321_SuperField" ;
	
	/**待统计数据库表名*/

	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "FieldType,Sum,Internation,Nation,Provi,City,School,Time,Note" ;

	
	
    /**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<S321_Bean> totalList(String year){
		
		String sql = "select " + key+ "," +field + " from " + tableName 
				+ " where convert(varchar(4),Time,120)=" + year;		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<S321_Bean> list = null ;
		System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, S321_Bean.class) ;
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
 	public boolean save(List<S321_Bean> list,String year){
 		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year;		
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection() ;		
		Statement st = null ;
		ResultSet rs = null ;
		List<S321_Bean> templist = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			templist = DAOUtil.getList(rs, S321_Bean.class) ;
			if(templist.size() != 0){
				String delSql = "delete from " + tableName + " where convert(varchar(4),Time,120)=" + year;
				int delflag = st.executeUpdate(delSql.toString());
				if(delflag > 0){
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

	public List<S321_Bean> getData(String year)
	{
		
	String querysql=" select a.Type AS FieldType,COUNT(b.Type) AS Sum," +
	" sum(case when b.MajorLevel='50000' then 1 else 0 end) AS Internation, "+
	" sum(case when b.MajorLevel='50001' then 1 else 0 end) AS Nation, "+
	" sum(case when b.MajorLevel='50002' then 1 else 0 end) AS Provi, "+
	" sum(case when b.MajorLevel='50003' then 1 else 0 end) AS City, "+
	" sum(case when b.MajorLevel='50004' then 1 else 0 end) AS School"+
				" from (SELECT distinct Type FROM T322_UndergraMajorInfo_Tea$) a " +
				"left join (select * from T322_UndergraMajorInfo_Tea$) b on a.Type = b.Type where Time like '"+year+"%'group by a.Type";
   
		//System.out.println(querysql);
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<S321_Bean> list = new ArrayList<S321_Bean>() ;
		int sum=0,sumInternation=0,sumNation=0,sumProvi=0,sumCity=0,sumSchool=0;
	
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(querysql) ;
			while(rs.next()){
				String fieldType=rs.getString("FieldType");
				if(fieldType.equals("特色专业")||fieldType.equals("品牌专业")||fieldType.equals("名牌专业")||fieldType.equals("示范专业")||fieldType.equals("重点建设专业")||fieldType.equals("地方优势专业")){
								
				
			

				sum+=rs.getInt("Sum");
				sumInternation += rs.getInt("Internation");
				sumNation += rs.getInt("Nation");
				sumProvi += rs.getInt("Provi");
				sumCity += rs.getInt("City");
				sumSchool  += rs.getInt("School");


			
				S321_Bean s321_Bean=new S321_Bean();	
				s321_Bean.setFieldType(fieldType);
				s321_Bean.setSum(rs.getInt("Sum"));
				s321_Bean.setInternation(rs.getInt("Internation"));
				s321_Bean.setNation(rs.getInt("Nation"));
				s321_Bean.setProvi(rs.getInt("Provi"));
				s321_Bean.setCity(rs.getInt("City"));
				s321_Bean.setSchool(rs.getInt("School"));
				s321_Bean.setTime(TimeUtil.changeDateY(year));	
				
				list.add(s321_Bean);
				}
			}
			
			if(list.size()!=0){
				S321_Bean s321_Bean=new S321_Bean();			
				s321_Bean.setFieldType("全校合计");
				s321_Bean.setSum(sum);
				s321_Bean.setInternation(sumInternation);
				s321_Bean.setNation(sumNation);
				s321_Bean.setProvi(sumProvi);
				s321_Bean.setCity(sumCity);
				s321_Bean.setSchool(sumSchool);
				s321_Bean.setTime(TimeUtil.changeDateY(year));
				list.add(0,s321_Bean);
				
				S321_DAO s321_Dao = new S321_DAO();
				s321_Dao.save(list, year);
			}		
		}catch(Exception e){
			e.printStackTrace() ;
			return null;
		}
		
		return list ;
	}
	

	
	public static void main(String arg[]){
		//S71_DAO t=new S71_DAO();
	   // List<S71_Bean> list =t.getData("2014");
		//System.out.println(list.size());
	}
}
