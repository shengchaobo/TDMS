package cn.nit.dao.table4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


import cn.nit.bean.table4.A412_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class A412_Dao {
	
	
	/**  数据库表名  */
	private String tableName = "A412_AllMajTeaInfo$" ;
	
	/**待统计数据库表名*/
	private String tableName1 = "T411_TeaBasicInfo_Per$";
	
	
	private String field = "SeniorNum,SeniorRatio,SubSenior,SubSeniorRatio,MiddleNum,MiddleRatio," +
	"PrimaryNum,PrimaryRatio,DoctorNum,DoctorRatio,MasterNum,MasterRatio,BachelorNum," +
	"BachelorRatio,NotDegreeNum,NotDegreeRatio,Below35Num,Below35Ratio,In36To45Num,In36To45Ratio,In46To55Num,In46To55Ratio,Above56Num,Above56Ratio," +
	"ThisSchNum,ThisSchRatio,OutSchInNum,OutSchInRatio,OutSchOutNum,OutSchOutRatio,DuTeaNum,DuTeaRatio,IndustryNum," +
	"IndustryRatio,EngineerNum,EngineerRatio,Time,Note";
	private String key = "SeqNumber";

	
	

  /**
 	 * 模板导入
 	 * @param diCourseCategories
 	 * @return
 	 *
 	 * @time: 2014-5-14/下午02:34:23
 	 */

	public boolean save(A412_Bean a412_bean){
		String sql="select * from " + tableName;	
		boolean flag=false;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null;
		ResultSet rs = null;
		List<A412_Bean> list=new ArrayList<A412_Bean>();
		A412_Bean bean=new A412_Bean();
		try{
			st=conn.createStatement();
			rs = st.executeQuery(sql);
			list=DAOUtil.getList(rs, A412_Bean.class);
			if(list.size()!=0){
				bean=list.get(0);
				a412_bean.setSeqNumber(bean.getSeqNumber());
				flag=DAOUtil.update(a412_bean, tableName, key, field, conn);
			}else{
				flag=DAOUtil.insert(a412_bean, tableName, field, conn);				
			}
		}catch (Exception e){
			e.printStackTrace() ;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		return flag;
		
		
	}
	
	
	public A412_Bean getData(){
		
		String sql="select * from "+tableName;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<A412_Bean> list = null ;
		A412_Bean bean = null;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, A412_Bean.class) ;
			if(list.size() != 0){
				bean = list.get(0);
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		return bean ;
		
	}
	
	
	/**得到数据*/
 	


	public A412_Bean getYearInfo(int year)
	{
		Connection conn1 = DBConnection.instance.getConnection() ;
		Statement st1 = null ;
		ResultSet rs1 = null ;
		String sql="select * from "+tableName1;
		
		try{
			st1 = conn1.createStatement();
			rs1 = st1.executeQuery(sql);
			if(rs1.equals(null)){
				return null;
			}
			
		}catch(Exception e){
			e.printStackTrace() ;
			return null;
		}finally{
			DBConnection.close(rs1);
			DBConnection.close(st1);	
			DBConnection.close(conn1);
		}
		
	    String querysql="select " +
	    	"count(distinct teaId) as Sum," +
			"sum (case when majTechTitle = '41000' then 1 else 0 end) as SeniorNum," +
			"sum (case when majTechTitle = '41001' then 1 else 0 end) as SubSenior," +
			"sum (case when majTechTitle = '41002' then 1 else 0 end) as MiddleNum," +
			"sum (case when TopDegree = '81000' then 1 else 0 end) as DoctorNum," +
			"sum (case when TopDegree = '81001' then 1 else 0 end) as MasterNum," +
			"sum (case when TopDegree = '81002' then 1 else 0 end) as BachelorNum," +
			"sum (case when TopDegree = '81003' then 1 else 0 end) as NotDegreeNum," +
			"sum (case when birthday >= '"+(year-36)+"-09-01' and birthday <='"+year+"-08-31' then 1 else 0 end) as Below35Num," +
			"sum (case when birthday >= '"+(year-46)+"-09-01' and birthday <='"+(year-36)+"-08-31' then 1 else 0 end) as In36To45Num," +
			"sum (case when birthday >= '"+(year-56)+"-09-01' and birthday <='"+(year-46)+"-08-31' then 1 else 0 end) as In46To55Num," +
			"sum (case when source = '83000' then 1 else 0 end) as ThisSchNum," +
			"sum (case when source = '83001' or source = '83002' then 1 else 0 end) as OutSchInNum," +
			"sum (case when source = '83003' then 1 else 0 end) as OutSchOutNum," +
			"sum (case when doubleTea = 'true' then 1 else 0 end) as DuTeaNum," +
			"sum (case when industry = 'true' then 1 else 0 end) as IndustryNum," +
			"sum (case when engineer = 'true' then 1 else 0 end) as EngineerNum " +
			" from "+tableName1+" where IDCode = '40000' and (TeaFlag is null or TeaFlag != '外聘')";
   
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		A412_Bean bean = new A412_Bean() ;
		
		int num,num1,num2,num3,num4,num5,num6,num7,num8,num9,num10,num11,num12,num13,num14,num15,num16,num17,num18;
		double ratio1,ratio2,ratio3,ratio4,ratio5,ratio6,ratio7,ratio8,ratio9,ratio10,ratio11,ratio12,ratio13,ratio14,ratio15,ratio16,ratio17,ratio18;
		
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(querysql) ;
			while(rs.next()){
				num = rs.getInt("Sum");
				if(num==0){
					return null;
				}
				num1 = rs.getInt("SeniorNum");
				num2 = rs.getInt("SubSenior");
				num3 = rs.getInt("MiddleNum");
				num4 = num - num1 - num2 - num3;
				num5 = rs.getInt("DoctorNum");
				num6 = rs.getInt("MasterNum");
				num7 = rs.getInt("BachelorNum");
				num8 = rs.getInt("NotDegreeNum");
				num9 = rs.getInt("Below35Num");
				num10 = rs.getInt("In36To45Num");
				num11 = rs.getInt("In46To55Num");
				num12 = num -num9 - num10 - num11;
				num13 = rs.getInt("ThisSchNum");
				num14 = rs.getInt("OutSchInNum");
				num15 = rs.getInt("OutSchOutNum");
				num16 = rs.getInt("DuTeaNum");
				num17 = rs.getInt("IndustryNum");
				num18 = rs.getInt("EngineerNum");

				NumberFormat nf = NumberFormat.getNumberInstance();
				nf.setMaximumFractionDigits(2);
				
				ratio1 = Double.parseDouble(nf.format((double)num1*100/num));
				ratio2 = Double.parseDouble(nf.format((double)num2*100/num));				
				ratio3 = Double.parseDouble(nf.format((double)num3*100/num));
				ratio4 = Double.parseDouble(nf.format((double)num4*100/num));
				ratio5 = Double.parseDouble(nf.format((double)num5*100/num));
				ratio6 = Double.parseDouble(nf.format((double)num6*100/num));
				ratio7 = Double.parseDouble(nf.format((double)num7*100/num));
				ratio8 = Double.parseDouble(nf.format((double)num8*100/num));
				ratio9 = Double.parseDouble(nf.format((double)num9*100/num));
				ratio10 = Double.parseDouble(nf.format((double)num10*100/num));
				ratio11 = Double.parseDouble(nf.format((double)num11*100/num));
				ratio12 = Double.parseDouble(nf.format((double)num12*100/num));
				ratio13 = Double.parseDouble(nf.format((double)num13*100/num));
				ratio14 = Double.parseDouble(nf.format((double)num14*100/num));
				ratio15 = Double.parseDouble(nf.format((double)num15*100/num));
				ratio16 = Double.parseDouble(nf.format((double)num16*100/num));
				ratio17 = Double.parseDouble(nf.format((double)num17*100/num));
				ratio18 = Double.parseDouble(nf.format((double)num18*100/num));
				
				bean.setSeniorNum(num1);
				bean.setSeniorRatio(ratio1);
				bean.setSubSenior(num2);
				bean.setSubSeniorRatio(ratio2);
				bean.setMiddleNum(num3);
				bean.setMiddleRatio(ratio3);
				bean.setPrimaryNum(num4);
				bean.setPrimaryRatio(ratio4);
				bean.setDoctorNum(num5);
				bean.setDoctorRatio(ratio5);
				bean.setMasterNum(num6);
				bean.setMasterRatio(ratio6);
				bean.setBachelorNum(num7);
				bean.setBachelorRatio(ratio7);
				bean.setNotDegreeNum(num8);
				bean.setNotDegreeRatio(ratio8);
				bean.setBelow35Num(num9);
				bean.setBelow35Ratio(ratio9);
				bean.setIn36To45Num(num10);
				bean.setIn36To45Ratio(ratio10);
				bean.setIn46To55Num(num11);
				bean.setIn46To55Ratio(ratio11);
				bean.setAbove56Num(num12);
				bean.setAbove56Ratio(ratio12);
				bean.setThisSchNum(num13);
				bean.setThisSchRatio(ratio13);
				bean.setOutSchInNum(num14);
				bean.setOutSchInRatio(ratio14);
				bean.setOutSchOutNum(num15);
				bean.setOutSchOutRatio(ratio15);
				bean.setDuTeaNum(num16);
				bean.setDuTeaRatio(ratio16);
				bean.setIndustryNum(num17);
				bean.setIndustryRatio(ratio17);
				bean.setEngineerNum(num18);
				bean.setEngineerRatio(ratio18);
				
			}
			
		
		}catch(Exception e){
			e.printStackTrace() ;
			return null;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		
		return bean ;
	}
	

	
	public static void main(String arg[]){
		//S25_DAO t=new S25_DAO();
	   // List<S25_Bean> list =t.getData("2014");
		//System.out.println(list.size());
	}

}
