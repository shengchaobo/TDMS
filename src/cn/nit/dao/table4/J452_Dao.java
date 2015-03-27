package cn.nit.dao.table4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.nit.dbconnection.DBConnection;
import cn.nit.pojo.table4.J452POJO;

public class J452_Dao {
	
	private static String tableName1 = "T452_TeaTrainInfo_TeaPer$";
	private static String tableName2 = "T453_TeaCommunInfo_TeaInter$";
	
	
	//DateDiff(day,"+tableName1+".BeginTime ,"+tableName1+".EndTime)<30
	public List<J452POJO> totalList(String year){
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st1 = null ;
		ResultSet rs1 = null ;
		Statement st2 = null ;
		ResultSet rs2 = null ;
		List<J452POJO> list = new ArrayList<J452POJO>();
		String sql1 = "select DiDepartment.UnitName as teaUnit,DiDepartment.UnitID as unitID," +
				"sum(case when "+tableName1+".InOrOut = '境内' then 1 else 0 end) as inPlace, " +
				"sum(case when "+tableName1+".InOrOut = '境外' then 1 else 0 end) as outPlaceTotal, " +
				"sum(case when "+tableName1+".InOrOut = '境外' and DateDiff(day,"+tableName1+".BeginTime,"+tableName1+".EndTime)>90 then 1 else 0 end ) as outPlaceLong, " +
				"sum(case when "+tableName1+".TrainType = '到行业培训' then 1 else 0 end ) as industryTrain, " +
				"sum(case when "+tableName1+".TrainType = '攻读博士学位' or "+tableName1+".TrainType = '攻读硕士学位' then 1 else 0 end ) as degreeTrain, " +
				"sum(case when "+tableName1+".TrainType = '攻读博士学位' then 1 else 0 end ) as doctorTrain, " +
				"sum(case when "+tableName1+".TrainType = '攻读硕士学位' then 1 else 0 end ) as masterTrain " +
				"from DiDepartment left join "+tableName1+" on DiDepartment.UnitID = "+tableName1+".UnitID " +
				"and "+tableName1+".Time like '"+year+"%' " +
				"group by DiDepartment.UnitName,DiDepartment.UnitID" ;
		String sql2 = "select DiDepartment.UnitName as teaUnit,DiDepartment.UnitID as unitID," +
		"sum(case when DateDiff(day,"+tableName2+".beginTime,"+tableName2+".endTime)>90 and "+tableName2+".communType = '来访' and "+tableName2+".inOrOut = '境内' then 1 else 0 end) as comeInPlace, " +
		"sum(case when DateDiff(day,"+tableName2+".beginTime,"+tableName2+".endTime)>90 and "+tableName2+".communType = '来访' and "+tableName2+".inOrOut = '境外' then 1 else 0 end) as comeOutPlace, " +
		"sum(case when DateDiff(day,"+tableName2+".beginTime,"+tableName2+".endTime)>90 and "+tableName2+".communType = '出访' and "+tableName2+".inOrOut = '境内' then 1 else 0 end) as goInPlace, " +
		"sum(case when DateDiff(day,"+tableName2+".beginTime,"+tableName2+".endTime)>90 and "+tableName2+".communType = '出访' and "+tableName2+".inOrOut = '境外' then 1 else 0 end) as goOutPlace " +
		"from DiDepartment left join "+tableName2+" on DiDepartment.UnitID = "+tableName2+".UnitID " +
		"and "+tableName2+".Time like '"+year+"%' " +
		"group by DiDepartment.UnitName,DiDepartment.UnitID" ;
		try{
			st1 = conn.createStatement();
			rs1 = st1.executeQuery(sql1);
			st2 = conn.createStatement();
			rs2 = st2.executeQuery(sql2);
			while(rs1.next()&&rs2.next()){
				J452POJO pojo = new J452POJO();
				pojo.setTeaUnit(rs1.getString("teaUnit"));
				pojo.setUnitID(rs1.getString("unitID"));
				pojo.setInPlace(rs1.getInt("inPlace"));
				pojo.setOutPlaceTotal(rs1.getInt("outPlaceTotal"));
				pojo.setOutPlaceLong(rs1.getInt("outPlaceLong"));
				pojo.setIndustryTrain(rs1.getInt("industryTrain"));
				pojo.setDegreeTrain(rs1.getInt("degreeTrain"));
				pojo.setDoctorTrain(rs1.getInt("doctorTrain"));
				pojo.setMasterTrain(rs1.getInt("masterTrain"));
				pojo.setComeInPlace(rs2.getInt("comeInPlace"));
				pojo.setComeOutPlace(rs2.getInt("comeOutPlace"));
				pojo.setGoInPlace(rs2.getInt("goInPlace"));
				pojo.setGoOutPlace(rs2.getInt("goOutPlace"));
				list.add(pojo);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			
			DBConnection.close(rs1);
			DBConnection.close(st1);
			DBConnection.close(rs2);
			DBConnection.close(st2);
			DBConnection.close(conn);
		}
		return list;
	}
	
	public static void main(String args[]){
		String sql1 = "select DiDepartment.UnitName as teaUnit,DiDepartment.UnitID as unitID," +
		"sum(case when "+tableName1+".InOrOut = '境内' then 1 else 0 end) as inPlace, " +
		"sum(case when "+tableName1+".InOrOut = '境外' then 1 else 0 end) as outPlaceTotal, " +
		"sum(case when "+tableName1+".InOrOut = '境外' and DateDiff(day,"+tableName1+".BeginTime,"+tableName1+".EndTime)>90 then 1 else 0 end ) as outPlaceLong, " +
		"sum(case when "+tableName1+".TrainType = '到行业培训' then 1 else 0 end ) as industryTrain, " +
		"sum(case when "+tableName1+".TrainType = '攻读博士学位' or "+tableName1+".TrainType = '攻读硕士学位' then 1 else 0 end ) as degreeTrain, " +
		"sum(case when "+tableName1+".TrainType = '攻读博士学位' then 1 else 0 end ) as doctorTrain, " +
		"sum(case when "+tableName1+".TrainType = '攻读硕士学位' then 1 else 0 end ) as masterTrain " +
		"from DiDepartment left join "+tableName1+" on DiDepartment.UnitID = "+tableName1+".UnitID " +
		"and "+tableName1+".Time like '"+"2014"+"%' " +
		"group by DiDepartment.UnitName,DiDepartment.UnitID" ;
		System.out.println(sql1);
	}

}
