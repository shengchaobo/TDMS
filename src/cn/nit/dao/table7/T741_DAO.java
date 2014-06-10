package cn.nit.dao.table7;

import java.sql.Connection;

import cn.nit.bean.table7.T741_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T741_DAO {
	
	private String tableName="T741_TeachAbilityAssess_AC$";
	
	private String key="SeqNumber";
	
	private String field="TeaName,TeaID,TeaUnit,UnitID,AssessCS,CSID,CSType,AssessYear,AccessResult,AppvlID,Time,Note";

	
	public boolean insert(T741_Bean teachability){
		
		boolean flag=false;
		
		Connection conn=DBConnection.instance.getConnection();
		
		try {
			flag=DAOUtil.insert(teachability, tableName, field, conn);
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
