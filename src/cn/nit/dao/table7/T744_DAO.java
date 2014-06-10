package cn.nit.dao.table7;

import java.sql.Connection;


import cn.nit.bean.table7.T744_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T744_DAO {
	
    private String tableName="T744_MajBuildAssess_AC$";
	
	private String key="SeqNumber";
	
	private String field="TeaUnit,UnitID,MajorName,MajorID,DegreeType,LeaderName,TeaID,SetYear,AssessYear,AssessResult,AppvlID,Time,Note";
	public boolean insert(T744_Bean t744_B){
		boolean flag=false;
		
		Connection conn=DBConnection.instance.getConnection();
		
		try {
			flag=DAOUtil.insert(t744_B, tableName, field, conn);
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
