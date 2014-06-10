package cn.nit.dao.table7;

import java.sql.Connection;
import java.util.Date;

import cn.nit.bean.table7.T735_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T735_DAO {
	
	private String tableName="T735_TeachManageAssessInfo_Tea$";
	
	private String key="SeqNumber";
	
	private String field="TeaUnit,UnitID,AssessResult,AssessYear,Time,Note";
	
	public boolean insert(T735_Bean teachManageAssessInfoTea){
		boolean flag=false;
		
		Connection conn=DBConnection.instance.getConnection();
		
		try {
			flag=DAOUtil.insert(teachManageAssessInfoTea, tableName, field, conn);
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
