package cn.nit.dao.table3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import cn.nit.bean.table3.A323_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class A323_DAO {
	
	
	
	/**  数据库表名  */
	private String tableName = "A323_HourCreditAnaly" ;
	
	/**待统计数据库表名*/

	private static String tableName1 = "T322_UndergraMajorInfo_Tea$";
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "TeaUnit,UnitID,RequireHour,OptionHour,InClassHour,ExpHour,PraHour,RequireCredit,OptionCredit,InClassCredit," +
			"ExpCredit,PraCredit,OutClassCredit,Time,Note" ;

	
	
    /**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<A323_Bean> totalList(String year){
		
		String sql = "select " + key+ "," +field + " from " + tableName 
				+ " where convert(varchar(4),Time,120)=" + year;		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<A323_Bean> list = null ;
		System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, A323_Bean.class) ;
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
 	public boolean save(List<A323_Bean> list,String year){
 		
		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year;		
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection() ;		
		Statement st = null ;
		ResultSet rs = null ;
		List<A323_Bean> templist = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			templist = DAOUtil.getList(rs, A323_Bean.class) ;
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

	public List<A323_Bean> getData(String year)
	{
		

		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<A323_Bean> list = new ArrayList<A323_Bean>() ;
		String sql1="select * from "+tableName1+" where Time like '"+year+"%'";
		
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
		
		String sql = " select DiDepartment.UnitName as TeaUnit, count("+tableName1+".MajorName) as majorNum from DiDepartment right join " +
				""+tableName1+" on DiDepartment.UnitID = "+tableName1+".FillUnitID and Time like '"+year+"%' " +
						" where "  +  "DiDepartment.UnitID like '3%' group by DiDepartment.UnitID,DiDepartment.UnitName order by DiDepartment.UnitID";
		List<String> unitNameList = new ArrayList<String>();
		List<Integer> majorNum = new ArrayList<Integer>();
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				unitNameList.add(rs.getString("TeaUnit"));
				majorNum.add(rs.getInt("majorNum"));
			}
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}	
		int sum=0;
		for(int i=0;i<majorNum.size();i++){
			sum += majorNum.get(i);
		}

		String querysql="select FillUnitID,MajorName,MajorID,TotalCSHour,RequireCShour,OptionCSHour,InClassCSHour,ExpCSHour,PraCSHour," +
				"TotalCredit,RequireCredit,OptionCredit,InClassCredit,ExpCredit,PraCredit,OutClassCredit from "+tableName1+" where Time like " +
						" '"+year+"%' and FillUnitID like '3%' order by FillUnitID";
		System.out.println(querysql);


		int count = 0, count1 = 0,i=0;
		String unitName = null;
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(4);
		int sum1 = 0,sum2 = 0,sum3 = 0,sum4 = 0,sum5 = 0,sum6 = 0,sum7 = 0,sum8 = 0,sum9 = 0,sum10 = 0,sum11 = 0,sumAll1 = 0,sumAll2 = 0;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(querysql) ;
			while(i<sum){
				
				List<A323_Bean> list1 = new ArrayList<A323_Bean>() ;
				int Total1=0,Num1=0,Num2=0,Num3=0,Num4=0,Num5=0;
				double Total2=0,Num6=0,Num7=0,Num8=0,Num9=0,Num10=0,Num11=0;
				int total1=0,num1=0,num2=0,num3=0,num4=0,num5=0;
				double total2=0,num6=0,num7=0,num8=0,num9=0,num10=0,num11=0;
				count = majorNum.get(count1);
				for(int j=0;j<count;j++){
					if(rs.next()){
					i++;
					A323_Bean bean = new A323_Bean ();
					total1 = rs.getInt("TotalCSHour");
					num1 = rs.getInt("RequireCShour");
					num2 = rs.getInt("OptionCSHour");
					num3 = rs.getInt("InClassCSHour");
					num4 = rs.getInt("ExpCSHour");
					num5 = rs.getInt("PraCSHour");

					total2 = rs.getDouble("TotalCredit");	
					num6 = rs.getDouble("RequireCredit");
					num7 = rs.getDouble("OptionCredit");
					num8 = rs.getDouble("InClassCredit");
					num9 = rs.getDouble("ExpCredit");
					num10 = rs.getDouble("PraCredit");
					num11 = rs.getDouble("OutClassCredit");
					
					Total1 += total1;
					Num1 += num1;
					Num2 += num2;
					Num3 += num3;
					Num4 += num4;
					Num5 += num5;
					Total2 += total2;
					Num6 += num6;
					Num7 += num7;
					Num8 += num8;
					Num9 += num9;
					Num10 += num10;
					Num11 += num11;
					unitName = rs.getString("MajorName");
					bean.setTeaUnit(unitName);
					bean.setUnitID(rs.getString("MajorID"));
					bean.setRequireHour(Double.parseDouble(nf.format((double)num1/total1))*100);
					bean.setOptionHour(Double.parseDouble(nf.format((double)num2/total1))*100);
					bean.setInClassHour(Double.parseDouble(nf.format((double)num3/total1))*100);
					bean.setExpHour(Double.parseDouble(nf.format((double)num4/total1))*100);
					bean.setPraHour(Double.parseDouble(nf.format((double)num5/total1))*100);
					
					bean.setRequireCredit(Double.parseDouble(nf.format((double)num6/total2))*100);
					bean.setOptionCredit(Double.parseDouble(nf.format((double)num7/total2))*100);
					bean.setInClassCredit(Double.parseDouble(nf.format((double)num8/total2))*100);
					bean.setExpCredit(Double.parseDouble(nf.format((double)num9/total2))*100);
					bean.setPraCredit(Double.parseDouble(nf.format((double)num10/total2))*100);
					bean.setOutClassCredit(Double.parseDouble(nf.format((double)num11/total2))*100);
					bean.setTime(TimeUtil.changeDateY(year));
				    list1.add(bean);
					}
				}

				    sum1 += Num1;
				    sum2 += Num2;
				    sum3 += Num3;
				    sum4 += Num4;
				    sum5 += Num5;
				    sum6 += Num6;
				    sum7 += Num7;
				    sum8 += Num8;
				    sum9 += Num9;
				    sum10 += Num10;
				    sum11 += Num11;
				    sumAll1 += Total1;
				    sumAll2 += Total2;
					A323_Bean bean = new A323_Bean ();
					bean.setTeaUnit(unitNameList.get(count1));
					bean.setUnitID("");
					bean.setRequireHour(Double.parseDouble(nf.format((double)Num1/Total1))*100);
					bean.setOptionHour(Double.parseDouble(nf.format((double)Num2/Total1))*100);
					bean.setInClassHour(Double.parseDouble(nf.format((double)Num3/Total1))*100);
					bean.setExpHour(Double.parseDouble(nf.format((double)Num4/Total1))*100);
					bean.setPraHour(Double.parseDouble(nf.format((double)Num5/Total1))*100);
					
					bean.setRequireCredit(Double.parseDouble(nf.format((double)Num6/Total2))*100);
					bean.setOptionCredit(Double.parseDouble(nf.format((double)Num7/Total2))*100);
					bean.setInClassCredit(Double.parseDouble(nf.format((double)Num8/Total2))*100);
					bean.setExpCredit(Double.parseDouble(nf.format((double)Num9/Total2))*100);
					bean.setPraCredit(Double.parseDouble(nf.format((double)Num10/Total2))*100);
					bean.setOutClassCredit(Double.parseDouble(nf.format((double)Num11/Total2))*100);
					bean.setTime(TimeUtil.changeDateY(year));
					list.add(bean);
					list.addAll(list1);
					count1++;
				}
			A323_Bean bean = new A323_Bean ();
			bean.setTeaUnit("全校合计");
			bean.setUnitID("");
			bean.setRequireHour(Double.parseDouble(nf.format((double)sum1/sumAll1))*100);
			bean.setOptionHour(Double.parseDouble(nf.format((double)sum2/sumAll1))*100);
			bean.setInClassHour(Double.parseDouble(nf.format((double)sum3/sumAll1))*100);
			bean.setExpHour(Double.parseDouble(nf.format((double)sum4/sumAll1))*100);
			bean.setPraHour(Double.parseDouble(nf.format((double)sum5/sumAll1))*100);
			
			bean.setRequireCredit(Double.parseDouble(nf.format((double)sum6/sumAll2))*100);
			bean.setOptionCredit(Double.parseDouble(nf.format((double)sum7/sumAll2))*100);
			bean.setInClassCredit(Double.parseDouble(nf.format((double)sum8/sumAll2))*100);
			bean.setExpCredit(Double.parseDouble(nf.format((double)sum9/sumAll2))*100);
			bean.setPraCredit(Double.parseDouble(nf.format((double)sum10/sumAll2))*100);
			bean.setOutClassCredit(Double.parseDouble(nf.format((double)sum11/sumAll2))*100);
			bean.setTime(TimeUtil.changeDateY(year));
			list.add(0,bean);
		
			A323_DAO a323_Dao = new A323_DAO();
			a323_Dao.save(list, year);
		
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
		String querysql="select FillUnitID,MajorName,MajorID,TotalCSHour,RequireCShour,OptionCSHour,InClassCSHour,ExpCSHour,PraCSHour," +
		"TotalCredit,RequireCredit,OptionCredit,InClassCredit,ExpCredit,PraCredit,OutClassCredit from "+tableName1+" where Time like " +
				" '"+2014+"%' and FillUnitID like '3%' order by FillUnitID";
		System.out.println(querysql);
	}

}
