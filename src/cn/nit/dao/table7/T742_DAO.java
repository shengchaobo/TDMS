package cn.nit.dao.table7;

import java.sql.Connection;

import cn.nit.bean.table7.T742_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T742_DAO {
	
	private String tableName="T742_TeachLevelAssess_AC$";
	
	private String key="SeqNumber";
	
	private String field="TeaName,TeaID,TeaUnit,UnitID,AssessCS,CSID,CSType,AssessYear,AssessResult,AppvlID,Time,Note";

	
	public boolean insert(T742_Bean t742_B){
boolean flag=false;
		
		Connection conn=DBConnection.instance.getConnection();
		
		try {
			flag=DAOUtil.insert(t742_B, tableName, field, conn);
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

