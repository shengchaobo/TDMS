package cn.nit.dao.table7;

import java.sql.Connection;
import java.util.Date;

import cn.nit.bean.table7.T745_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T745_DAO {
	
	private String tableName="T745_TeachWorkAssess_AC$";
	
	private String key="SeqNumber";
	
	private String field="TeaUnit,UnitID,AssessYear,AssessResult,AppvlID,Time,Note";

	public boolean insert(T745_Bean teachWorkAssessAC){
		boolean flag=false;
		
	    Connection conn=DBConnection.instance.getConnection();
	    
	    
	    try {
			flag=DAOUtil.insert(teachWorkAssessAC, tableName, field, conn);
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
