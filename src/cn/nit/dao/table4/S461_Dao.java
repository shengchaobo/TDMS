package cn.nit.dao.table4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.nit.bean.table4.S46_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class S461_Dao {
	
	
	
	
	
	/**  数据库表名  */
	private  String tableName = "S46_TeaHonorInfo$" ;
	
	/**待统计数据库表名*/
	private  String tableName1 = "T461_FameTeaAward_Per$";
	
	
	private  String tableName2 = "DiAwardLevel";
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private  String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private  String field = "Item,FameTeaAward,AdvanceTeaAward,WorkAward," +
			"StuWordAward,OutstdTeaAward,OutWorkAward,TeathAward,OtherAward,Time,Note" ;

	

    /**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<S46_Bean> totalList(String year){
		
		String sql = "select " + key+ "," +field + " from " + tableName2 
				+ " left join "+tableName+" on "+tableName2+".AwardLevel = "+tableName+".Item" +
				" where convert(varchar(4),Time,120)=" + year;		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<S46_Bean> list = null ;
		System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, S46_Bean.class) ;
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
 	public boolean save(List<S46_Bean> list,String year){
 		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year;		
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection() ;		
		Statement st = null ;
		ResultSet rs = null ;
		List<S46_Bean> templist = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			templist = DAOUtil.getList(rs, S46_Bean.class) ;
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

	public List<S46_Bean> getYearInfo(String year)
	{
		
	String querysql="select "+tableName2+".AwardLevel as Item," +
	"sum(case when "+tableName1+".AwardType = '51000' then 1 else 0 end) as FameTeaAward," +
	"sum(case when "+tableName1+".AwardType = '51001' then 1 else 0 end) as AdvanceTeaAward," +
	"sum(case when "+tableName1+".AwardType = '51002' then 1 else 0 end) as WorkAward," +
	"sum(case when "+tableName1+".AwardType = '51003' then 1 else 0 end) as StuWordAward," +
	"sum(case when "+tableName1+".AwardType = '51004' then 1 else 0 end) as OutstdTeaAward," +
	"sum(case when "+tableName1+".AwardType = '51005' then 1 else 0 end) as OutWorkAward," +
	"sum(case when "+tableName1+".AwardType = '51006' then 1 else 0 end) as TeathAward," +
	"sum(case when "+tableName1+".AwardType = '51007' then 1 else 0 end) as OtherAward" +
	" from "+tableName2+
	" left join "+tableName1+" on "+tableName2+".IndexID = "+tableName1+".AwardLevel "+
	" and Time like '"+year+"%' group by "+tableName2+".AwardLevel " +
	" order by "+tableName2+".AwardLevel";
   
		System.out.println(querysql);
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<S46_Bean> list = new ArrayList<S46_Bean>() ;
		
		int sum1=0,sum2=0,sum3=0,sum4=0,sum5=0,sum6=0,sum7=0,num8=0;
		int num1=0,num2=0,num3=0,num4=0,num5=0,num6=0,num7=0,sum8=0;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(querysql) ;
			while(rs.next()){
				
				num1 = rs.getInt("FameTeaAward");
				num2 = rs.getInt("AdvanceTeaAward");
				num3 = rs.getInt("WorkAward");
				num4 = rs.getInt("StuWordAward");
				num5 = rs.getInt("OutstdTeaAward");
				num6 = rs.getInt("OutWorkAward");
				num7 = rs.getInt("TeathAward");
				num8 = rs.getInt("OtherAward");
				
				sum1 += num1;
				sum2 += num2;
				sum3 += num3;
				sum4 += num4;
				sum5 += num5;
				sum6 += num6;
				sum7 += num7;
				sum8 += num8;
				
				S46_Bean s46_Bean=new S46_Bean();
				s46_Bean.setItem(rs.getString("Item"));
				s46_Bean.setFameTeaAward(num1);
				s46_Bean.setAdvanceTeaAward(num2);
				s46_Bean.setWorkAward(num3);
				s46_Bean.setStuWordAward(num4);
				s46_Bean.setOutstdTeaAward(num5);
				s46_Bean.setOutWorkAward(num6);
				s46_Bean.setTeathAward(num7);
				s46_Bean.setOtherAward(num8);
				s46_Bean.setTime(TimeUtil.changeDateY(year));								
				list.add(s46_Bean);
				
			}
			
			if(list.size()!=0){
				S46_Bean s46_Bean=new S46_Bean();
				s46_Bean.setItem("合计");
				s46_Bean.setFameTeaAward(sum1);
				s46_Bean.setAdvanceTeaAward(sum2);
				s46_Bean.setWorkAward(sum3);
				s46_Bean.setStuWordAward(sum4);
				s46_Bean.setOutstdTeaAward(sum5);
				s46_Bean.setOutWorkAward(sum6);
				s46_Bean.setTeathAward(sum7);
				s46_Bean.setOtherAward(sum8);
				s46_Bean.setTime(TimeUtil.changeDateY(year));	
				list.add(0,s46_Bean);
				
				S461_Dao s461_Dao = new S461_Dao();
				s461_Dao.save(list, year);
			}		
		}catch(Exception e){
			e.printStackTrace() ;
			return null;
		}
		
		return list ;
	}
	
	
	public static void main(String args[]){
//		String querysql="select "+tableName2+".AwardLevel as Item," +
//		"sum(case when "+tableName1+".AwardType = '51000' then 1 else 0 end) as FameTeaAward," +
//		"sum(case when "+tableName1+".AwardType = '51001' then 1 else 0 end) as AdvanceTeaAward," +
//		"sum(case when "+tableName1+".AwardType = '51002' then 1 else 0 end) as WorkAward," +
//		"sum(case when "+tableName1+".AwardType = '51003' then 1 else 0 end) as StuWordAward," +
//		"sum(case when "+tableName1+".AwardType = '51004' then 1 else 0 end) as OutstdTeaAward," +
//		"sum(case when "+tableName1+".AwardType = '51005' then 1 else 0 end) as OutWorkAward," +
//		"sum(case when "+tableName1+".AwardType = '51006' then 1 else 0 end) as TeathAward," +
//		"sum(case when "+tableName1+".AwardType = '51007' then 1 else 0 end) as OtherAward," +
//		" from "+tableName2+
//		" left join "+tableName1+" on "+tableName2+".IndexID = "+tableName1+".AwardLevel "+
//		" and Time like '"+2014+"%' group by "+tableName2+".AwardLevel " +
//		" order by "+tableName2+".AwardLevel";
//		System.out.println(querysql);
//		
	}

}
