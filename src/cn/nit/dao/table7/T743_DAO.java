package cn.nit.dao.table7;

import java.sql.Connection;

import cn.nit.bean.table7.T743_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T743_DAO {
	
	private String tableName="T743_CourseBuildAssess_AC$";
	
	private String key="SeqNumber";
	
	private String field="CSName,CSID,SetCSUnit,UnitID,CSType,CSNature,CSLeader,TeaID,AssessYear,AssessResult,AppvlID,Time,Note";

	public boolean insert(T743_Bean t743_B){
		boolean flag=false;
		
		Connection conn=DBConnection.instance.getConnection();
		
		try {
			flag=DAOUtil.insert(t743_B, tableName, field, conn);
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
