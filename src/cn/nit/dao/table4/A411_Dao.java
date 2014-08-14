package cn.nit.dao.table4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.nit.bean.table2.S28_Bean;
import cn.nit.bean.table4.A411_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class A411_Dao {
	
	private String tableName = "A411_TeaInfo$" ;
	private String field = "SeniorNum,SeniorRatio,SubSenior,SubSeniorRatio,MiddleNum,MiddleRatio," +
			"PrimaryNum,PrimaryRatio,DoctorNum,DoctorRatio,MasterNum,MasterRatio,BachelorNum," +
			"BachelorRatio,NotDegreeNum,NotDegreeRatio,Below35Num,Below35Ratio,In36To45Num,In36To45Ratio,In46To55Num,In46To55Ratio,Above56Num,Above56Ratio," +
			"ThisSchNum,ThisSchRatio,OutSchInNum,OutSchInRatio,OutSchOutNum,OutSchOutRatio,DuTeaNum,DuTeaRatio,IndustryNum," +
			"IndustryRatio,EngineerNum,EngineerRatio,FullTimeTeaNum,FullTimeRatio,TeaManageNum,TeaManageRatio,StuManageNum,StuManageRatio," +
			"TeaMonitorNum,TeaMonitorRatio,ExpTeaNum,ExpTeaRatio,OtherTeaNum,OtherTeaRatio,Time,Note";
	private String key = "SeqNumber";
	
	
	//保存
	public boolean save(A411_Bean a411_bean, String year){
		String sql="select * from " + tableName + " where Time like '"+year+"%'";	
		boolean flag=false;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null;
		ResultSet rs = null;
		List<A411_Bean> list=new ArrayList<A411_Bean>();
		A411_Bean bean=new A411_Bean();
		try{
			st=conn.createStatement();
			rs = st.executeQuery(sql);
			list=DAOUtil.getList(rs, A411_Bean.class);
			if(list.size()!=0){
				bean=list.get(0);
				a411_bean.setSeqNumber(bean.getSeqNumber());
				a411_bean.setTime(TimeUtil.changeDateY(year));
				flag=DAOUtil.update(a411_bean, tableName, key, field, conn);
			}else{
				a411_bean.setTime(TimeUtil.changeDateY(year));
				flag=DAOUtil.insert(a411_bean, tableName, field, conn);
				
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
	
	public A411_Bean getYearInfo(String year){
		
		String sql = "select " + " " + key + "," +
		field + " from " + tableName + " where convert(varchar(4),Time,120)=" + year;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<A411_Bean> list = null ;
		A411_Bean bean = null;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, A411_Bean.class) ;
			if(list.size() != 0){
				bean = list.get(0);
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
		
		return bean ;
	}

}
