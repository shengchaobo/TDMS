package cn.nit.dao.table3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


import cn.nit.bean.table3.A322_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class A322_DAO {
	
	/**  数据库表名  */
	private String tableName = "A322_SuperField" ;
	
	/**待统计数据库表名*/

	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "TeaUnit,UnitID,FieldNum,Sum,InternationRatio,NationRatio,ProviRatio,CityRatio,SchoolRatio,Time,Note" ;

	
	
    /**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<A322_Bean> totalList(String year){
		
		String sql = "select " + key+ "," +field + " from " + tableName 
				+ " where convert(varchar(4),Time,120)=" + year;		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<A322_Bean> list = null ;
		System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, A322_Bean.class) ;
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
 	public boolean save(List<A322_Bean> list,String year){
 		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year;		
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection() ;		
		Statement st = null ;
		ResultSet rs = null ;
		List<A322_Bean> templist = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			templist = DAOUtil.getList(rs, A322_Bean.class) ;
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

	public List<A322_Bean> getData(String year)
	{
		

		String querysql=" select DiDepartment.UnitName as TeaUnit,DiDepartment.UnitID as UnitID,COUNT(T322_UndergraMajorInfo_Tea$.MajorName) AS FieldNum," +
		" sum(case when T322_UndergraMajorInfo_Tea$.MajorLevel='50000' then 1 else 0 end) AS Internation, "+
		" sum(case when T322_UndergraMajorInfo_Tea$.MajorLevel='50001' then 1 else 0 end) AS Nation, "+
		" sum(case when T322_UndergraMajorInfo_Tea$.MajorLevel='50002' then 1 else 0 end) AS Provi, "+
		" sum(case when T322_UndergraMajorInfo_Tea$.MajorLevel='50003' then 1 else 0 end) AS City, "+
		" sum(case when T322_UndergraMajorInfo_Tea$.MajorLevel='50004' then 1 else 0 end) AS School"+
					" from DiMajorTwo " +
					"left join T322_UndergraMajorInfo_Tea$ on DiMajorTwo.MajorNum = T322_UndergraMajorInfo_Tea$.MajorID " +
					"left join DiDepartment  on DiMajorTwo.UnitID=DiDepartment.UnitID"+
					" where Time like '"+year+"%'"+
					" and "  +  "DiDepartment.UnitID like '3%' group by DiDepartment.UnitID,DiDepartment.UnitName";
		//System.out.println(querysql);
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<A322_Bean> list = new ArrayList<A322_Bean>() ;
		int sum=0,sumInterNation=0,sumNation=0,sumProvi=0,sumCity=0,sumSchool=0;
		int fieldNum=0,interNation=0,nation=0,provi=0,city=0,school=0;
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(4);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(querysql) ;
			while(rs.next()){

				sum+=rs.getInt("FieldNum");
				sumInterNation += rs.getInt("Internation");
				sumNation += rs.getInt("Nation");
				sumProvi += rs.getInt("Provi");
				sumCity += rs.getInt("City");
				sumSchool  += rs.getInt("School");
				
				fieldNum=rs.getInt("FieldNum");
				interNation=rs.getInt("Internation");
				nation=rs.getInt("Nation");
				provi=rs.getInt("Provi");
				city=rs.getInt("City");
				school=rs.getInt("School");


			
				A322_Bean a322_Bean=new A322_Bean();
				a322_Bean.setTeaUnit(rs.getString("TeaUnit"));
			
				a322_Bean.setUnitID(rs.getString("UnitID"));
				a322_Bean.setFieldNum(fieldNum);

				a322_Bean.setInternationRatio(Double.parseDouble(nf.format((double)interNation/fieldNum))*100);
				a322_Bean.setNationRatio(Double.parseDouble(nf.format((double)nation/fieldNum))*100);
				a322_Bean.setProviRatio(Double.parseDouble(nf.format((double)provi/fieldNum))*100);
				a322_Bean.setCityRatio(Double.parseDouble(nf.format((double)city/fieldNum))*100);
				a322_Bean.setSchoolRatio(Double.parseDouble(nf.format((double)school/fieldNum))*100);
				a322_Bean.setSum(Double.parseDouble(nf.format((double)(interNation+nation+provi+city+school)/fieldNum))*100);
				a322_Bean.setTime(TimeUtil.changeDateY(year));	
				list.add(a322_Bean);
				}
			
			
			if(list.size()!=0){
				A322_Bean a322_Bean=new A322_Bean();	
				a322_Bean.setTeaUnit("合计");
				a322_Bean.setUnitID("");
				a322_Bean.setFieldNum(sum);
				a322_Bean.setInternationRatio(Double.parseDouble(nf.format((double)sumInterNation/sum))*100);
				a322_Bean.setNationRatio(Double.parseDouble(nf.format((double)sumNation/sum))*100);
				a322_Bean.setProviRatio(Double.parseDouble(nf.format((double)sumProvi/sum))*100);
				a322_Bean.setCityRatio(Double.parseDouble(nf.format((double)sumCity/sum))*100);
				a322_Bean.setSchoolRatio(Double.parseDouble(nf.format((double)sumSchool/sum))*100);
				a322_Bean.setSum(Double.parseDouble(nf.format((double)(sumInterNation+sumNation+sumProvi+sumCity+sumSchool)/sum))*100);
				a322_Bean.setTime(TimeUtil.changeDateY(year));	
				list.add(0,a322_Bean);
				
				A322_DAO a322_Dao = new A322_DAO();
				a322_Dao.save(list, year);
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
		//S71_DAO t=new S71_DAO();
	   // List<S71_Bean> list =t.getData("2014");
		//System.out.println(list.size());
	}

}
