package cn.nit.dao.table7;

import java.sql.Connection;

import cn.nit.bean.table7.T733_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T733_DAO {
	
	private String tableName="T733_EachUnitTeachResActInfo$";
	
	private String key="SeqNumber";
	
	private String field="UnitName,UnitID,MeetingDate,MeetingMemberInfo,MeetingNum,MeetingTheme,MeetingResult,Time,Note";

	public boolean insert(T733_Bean eachUnitTeacgResAC){
		boolean flag= false;
		
		Connection conn=DBConnection.instance.getConnection();
		
		try {
			flag=DAOUtil.insert(eachUnitTeacgResAC, tableName, field, conn);
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
