package cn.nit.dao.table7;

import java.sql.Connection;

import cn.nit.bean.table7.T732_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T732_DAO {
	
	private String tableName="T732_TeaLeadInClassInfo_TeaTea$";
	
	private String key="SeqNumber";
	
	private String field="AttendClassTerm,LeaderName,LeaderTeaID,AdminTitle,AttendClassTime,LectureTea,LectureTeaID,LectureCS,CSID,SetCSUnit,UnitID,LectureClass,Evaluate,Time,Note";

	public boolean insert(T732_Bean teaLeadInClassInfo){
		boolean flag=false;
		
		Connection conn=DBConnection.instance.getConnection();
		
		try {
			flag=DAOUtil.insert(teaLeadInClassInfo, tableName, field, conn);
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
