package cn.nit.dao.table7;

import java.sql.Connection;

import cn.nit.bean.table7.T731_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T731_DAO {
	
	private String tableName="T731_SchLeadInClassInfo_Tea$";
	
	private String key="SeqNumber"; 
	
	private String field="AttendClassTerm,LeaderName,LeaderID,AttendClassTime,LectureTea,LectureTeaID,LectureCS,CSID,SetCSUnit,UnitID,LectureClass,Evaluate,Time,Note";

	public boolean insert(T731_Bean schleadInClassTnfoTea){
		
		boolean flag=false;
		Connection conn=DBConnection.instance.getConnection();
		
		try {
			flag=DAOUtil.insert(schleadInClassTnfoTea, tableName, field, conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return flag;
		}finally{
			DBConnection.close(conn);
		}
		
		return flag;
		
		
	}
}
