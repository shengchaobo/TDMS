package cn.nit.dao.table7;

import java.sql.Connection;


import cn.nit.bean.table7.T722_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T722_DAO {
	
	private String tableName="T722_TeachAchieveAward_Tea$";
	
	private String key="SeqNumber";
	
	private String field="AwardName,TeaUnit,UnitID,Leader,TeaID,OtherTeaNum,OtherTea,AwardLevel,AwardTime,AwardFromUnit,AppvlID,Time,Note";

	public boolean insert(T722_Bean teachAchieveAwardTea){
		boolean flag=false;
		
		Connection conn=DBConnection.instance.getConnection();
		
		try {
			flag=DAOUtil.insert(teachAchieveAwardTea, tableName, field, conn);
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
