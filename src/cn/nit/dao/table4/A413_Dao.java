package cn.nit.dao.table4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import cn.nit.bean.table4.A413_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class A413_Dao {
	
	
	/**  数据库表名  */
	private String tableName = "A413_HireTeaInfo$" ;
	
	/**待统计数据库表名*/
	private String tableName1 = "T413_HireTeaInfo_TeaPer$";
	
	
	private String field = "SeniorNum,SeniorRatio,SubSenior,SubSeniorRatio,MiddleNum,MiddleRatio," +
	"PrimaryNum,PrimaryRatio,Below35Num,Below35Ratio,In36To45Num,In36To45Ratio,In46To55Num,In46To55Ratio,Above56Num,Above56Ratio," +
	"AdminNum,AdminRatio,ResNum,ResRatio,HighNum,HighRatio,BusinessNum,BusinessRatio,CompanyNum," +
	"CompanyRatio,ArmyNum,ArmyRatio,OtherNum,OtherRatio,DuTutorNum,DuTutorRatio,DocTutorNum,DocTutorRaio,MasterTutorNum,MasterTutorRatio," +
	"NotTutorNum,NotTutorRatio,Time,Note";
	private String key = "SeqNumber";

	
	

	
  /**
 	 * 模板导入
 	 * @param diCourseCategories
 	 * @return
 	 *
 	 * @time: 2014-5-14/下午02:34:23
 	 */
	public boolean save(A413_Bean a413_bean, String year){
		String sql="select * from " + tableName + " where Time like '"+year+"%'";	
		boolean flag=false;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null;
		ResultSet rs = null;
		List<A413_Bean> list=new ArrayList<A413_Bean>();
		A413_Bean bean=new A413_Bean();
		try{
			st=conn.createStatement();
			rs = st.executeQuery(sql);
			list=DAOUtil.getList(rs, A413_Bean.class);
			if(list.size()!=0){
				bean=list.get(0);
				a413_bean.setSeqNumber(bean.getSeqNumber());
				a413_bean.setTime(TimeUtil.changeDateY(year));
				flag=DAOUtil.update(a413_bean, tableName, key, field, conn);
			}else{
				a413_bean.setTime(TimeUtil.changeDateY(year));
				flag=DAOUtil.insert(a413_bean, tableName, field, conn);
				
			}
		}catch (Exception e){
			e.printStackTrace() ;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
		return flag;
		
		
	}
	/**得到数据*/
 	


	public A413_Bean getYearInfo(String selectYear)
	{
		int year = Integer.parseInt(selectYear);
	    String querysql="select " +
	    	"count(distinct TeaID) as Sum," +
			"sum (case when birthday >= '"+(year-36)+"-09-01' and birthday <='"+year+"-08-31' then 1 else 0 end) as Below35Num," +
			"sum (case when birthday >= '"+(year-46)+"-09-01' and birthday <='"+(year-36)+"-08-31' then 1 else 0 end) as In36To45Num," +
			"sum (case when birthday >= '"+(year-56)+"-09-01' and birthday <='"+(year-46)+"-08-31' then 1 else 0 end) as In46To55Num," +
			"sum (case when TechTitle = '41000' then 1 else 0 end) as SeniorNum," +
			"sum (case when TechTitle = '41001' then 1 else 0 end) as SubSenior," +
			"sum (case when TechTitle = '41002' then 1 else 0 end) as MiddleNum," +
			"sum (case when WorkUnitType = '行政单位' then 1 else 0 end) as AdminNum," +
			"sum (case when WorkUnitType = '科研单位' then 1 else 0 end) as ResNum," +
			"sum (case when WorkUnitType = '高等学校' then 1 else 0 end) as HighNum," +
			"sum (case when WorkUnitType = '其他事业单位' then 1 else 0 end) as BusinessNum," +
			"sum (case when WorkUnitType = '企业公司' then 1 else 0 end) as CompanyNum," +
			"sum (case when WorkUnitType = '部队' then 1 else 0 end) as ArmyNum," +
			"sum (case when WorkUnitType = '其他单位' then 1 else 0 end) as OtherNum," +
			"sum (case when TutorType = '43000' then 1 else 0 end) as DuTutorNum," +
			"sum (case when TutorType = '43001' then 1 else 0 end) as DocTutorNum," +
			"sum (case when TutorType = '43002' then 1 else 0 end) as MasterTutorNum," +
			"sum (case when TutorType = '43003' then 1 else 0 end) as NotTutorNum"+
			" from "+tableName1;
   
		System.out.println(querysql);
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		A413_Bean bean = new A413_Bean() ;
		
		int num,num1,num2,num3,num4,num5,num6,num7,num8,num9,num10,num11,num12,num13,num14,num15,num16,num17,num18,num19;
		double ratio1,ratio2,ratio3,ratio4,ratio5,ratio6,ratio7,ratio8,ratio9,ratio10,ratio11,ratio12,ratio13,ratio14,ratio15,ratio16,ratio17,ratio18,ratio19;
		
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(querysql) ;
			while(rs.next()){
				num = rs.getInt("Sum");
				if(num==0){
					return null;
				}
				num1 = rs.getInt("Below35Num");
				num2 = rs.getInt("In36To45Num");
				num3 = rs.getInt("In46To55Num");
				num4 = num - num1 - num2 - num3;
				num5 = rs.getInt("SeniorNum");
				num6 = rs.getInt("SubSenior");
				num7 = rs.getInt("MiddleNum");
				num8 = num - num5 - num6 - num7;
				num9 = rs.getInt("AdminNum");
				num10 = rs.getInt("ResNum");
				num11 = rs.getInt("HighNum");
				num12 = rs.getInt("BusinessNum");
				num13 = rs.getInt("CompanyNum");
				num14 = rs.getInt("ArmyNum");
				num15 = rs.getInt("OtherNum");
				num16 = rs.getInt("DuTutorNum");
				num17 = rs.getInt("DocTutorNum");
				num18 = rs.getInt("MasterTutorNum");
				num19 = rs.getInt("NotTutorNum");

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
				ratio19 = Double.parseDouble(nf.format((double)num19*100/num));
				
				bean.setBelow35Num(num1);
				bean.setBelow35Ratio(ratio1);
				bean.setIn36To45Num(num2);
				bean.setIn36To45Ratio(ratio2);
				bean.setIn46To55Num(num3);
				bean.setIn46To55Ratio(ratio3);
				bean.setAbove56Num(num4);
				bean.setAbove56Ratio(ratio4);
				bean.setSeniorNum(num5);
				bean.setSeniorRatio(ratio5);
				bean.setSubSenior(num6);
				bean.setSubSeniorRatio(ratio6);
				bean.setMiddleNum(num7);
				bean.setMiddleRatio(ratio7);
				bean.setPrimaryNum(num8);
				bean.setPrimaryRatio(ratio8);
				bean.setAdminNum(num9);
				bean.setAdminRatio(ratio9);
				bean.setResNum(num10);
				bean.setResRatio(ratio10);
				bean.setHighNum(num11);
				bean.setHighRatio(ratio11);
				bean.setBusinessNum(num12);
				bean.setBusinessRatio(ratio12);
				bean.setCompanyNum(num13);
				bean.setCompanyRatio(ratio13);
				bean.setArmyNum(num14);
				bean.setArmyRatio(ratio14);
				bean.setOtherNum(num15);
				bean.setOtherRatio(ratio15);
				bean.setDuTutorNum(num16);
				bean.setDuTutorRatio(ratio16);
				bean.setDocTutorNum(num17);
				bean.setDocTutorRaio(ratio17);
				bean.setMasterTutorNum(num18);
				bean.setMasterTutorRatio(ratio18);
				bean.setNotTutorNum(num19);
				bean.setNotTutorRatio(ratio19);
			
				

			}
			
		
		}catch(Exception e){
			e.printStackTrace() ;
			return null;
		}
		
		return bean ;
	}
	

	
	public static void main(String arg[]){
		
		

	}

}

