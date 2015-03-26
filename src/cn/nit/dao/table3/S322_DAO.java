package cn.nit.dao.table3;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.table3.S322_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class S322_DAO {
	
	/**  数据库表名  */
	private String tableName = "S322_MajorAssessInfo$" ;
	
	/**待统计数据库表名*/
	private String tableName1="T322_UndergraMajorInfo_Tea$";
		
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "TeaUnit,UnitID,PassedMajor,MajorID,AssessTime,ValidityBegin,ValidityEnd,AssessOrg,Time,Note" ;

	
	
    /**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<S322_Bean> totalList(String year){
		
		String sql = "select " + key+ "," +field + " from " + tableName 
				+ " where convert(varchar(4),Time,120)=" + year;		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<S322_Bean> list = null ;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, S322_Bean.class) ;
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
 	public boolean save(List<S322_Bean> list,String year){
 		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year;		
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection() ;		
		Statement st = null ;
		ResultSet rs = null ;
		List<S322_Bean> templist = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			templist = DAOUtil.getList(rs, S322_Bean.class) ;
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
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
 		return flag ;
 		
 	}
	/**得到数据*/

	public List<S322_Bean> getData(String year)
	{
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<S322_Bean> list = new ArrayList<S322_Bean>() ;
		String sql = "select * from T322_UndergraMajorInfo_Tea$ where time like '"+ year +"%'";
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(!rs.next()){
				System.out.println("统计数据不全啊  ");
				return list;
			}
			
		}catch (Exception e){
			e.printStackTrace() ;
			return null;
		}
		
		String querysql=" select DiDepartment.UnitName as TeaUnit,DiDepartment.UnitID as UnitID," +
		"T322_UndergraMajorInfo_Tea$.MajorName as PassedMajor, "+
		"T322_UndergraMajorInfo_Tea$.MajorID as MajorID, "+
		"T322_UndergraMajorInfo_Tea$.AppvlTime as AssessTime, "+
		"T322_UndergraMajorInfo_Tea$.FromTime as ValidityBegin, "+
		"T322_UndergraMajorInfo_Tea$.EndTime as ValidityEnd,"+
		"T322_UndergraMajorInfo_Tea$.AppvlAuth as AssessOrg"+
					" from DiMajorTwo " +
					"left join T322_UndergraMajorInfo_Tea$ on DiMajorTwo.MajorNum = T322_UndergraMajorInfo_Tea$.MajorID " +
					"left join DiDepartment  on DiMajorTwo.UnitID=DiDepartment.UnitID"+
					" where Time like '"+year+"%' and T322_UndergraMajorInfo_Tea$.AppvlResult = '通过' "+
					" and "  +  "DiDepartment.UnitID like '3%' group by DiDepartment.UnitID,DiDepartment.UnitName,T322_UndergraMajorInfo_Tea$.MajorName,T322_UndergraMajorInfo_Tea$.MajorID," +
							" T322_UndergraMajorInfo_Tea$.AppvlTime,T322_UndergraMajorInfo_Tea$.FromTime,T322_UndergraMajorInfo_Tea$.EndTime,T322_UndergraMajorInfo_Tea$.AppvlAuth";


		System.out.println(querysql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(querysql) ;
			while(rs.next()){
				S322_Bean s322_Bean=new S322_Bean();			
				s322_Bean.setTeaUnit(rs.getString("TeaUnit"));
				s322_Bean.setUnitID(rs.getString("UnitID"));
				s322_Bean.setPassedMajor(rs.getString("PassedMajor"));
				s322_Bean.setMajorID(rs.getString("MajorID"));
				s322_Bean.setAssessTime(new java.util.Date((rs.getDate("AssessTime").getTime())));
				s322_Bean.setValidityBegin(new java.util.Date(rs.getDate("ValidityBegin").getTime()));
				s322_Bean.setValidityEnd(new java.util.Date(rs.getDate("ValidityEnd").getTime()));
				s322_Bean.setAssessOrg(rs.getString("AssessOrg"));
				s322_Bean.setTime(TimeUtil.changeDateY(year));			
				list.add(s322_Bean);
			}
			if(list.size()!=0){
			S322_DAO s322_Dao = new S322_DAO();
			s322_Dao.save(list, year);
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return null;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		
		return list ;
	}
		
	public static void main(String arg[]){
		S322_DAO t=new S322_DAO();
	    List<S322_Bean> list =t.getData("2014");
		System.out.println(list.size());
		
	}
}
