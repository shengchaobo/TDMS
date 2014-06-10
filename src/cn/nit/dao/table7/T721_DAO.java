package cn.nit.dao.table7;

import java.sql.Connection;
import java.util.Date;

import cn.nit.bean.table7.T721_Bean;
import cn.nit.dbconnection.DBConnection;

import cn.nit.util.DAOUtil;

public class T721_DAO {
	
	private String tableName="T721_TeachResItem_Tea$";
	
	private String key="SeqNumber";
	
	private String field="ItemName,TeaUnit,UnitID,Leader,TeaID,OtherTeaNum,OtherTea,ItemLevel,ItemSetUpTime,ReceptTime,ApplvExp,SchSupportExp,AppvlID,Time,Note";

	

	public boolean insert(T721_Bean teachResItemTea){
		
		boolean flag=false;
		
	
		
		Connection conn=DBConnection.instance.getConnection();
		
		try {
			
			flag=DAOUtil.insert(teachResItemTea, tableName, field, conn);
			
		}
		 catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return flag;
		}finally{
			DBConnection.close(conn);
		}
	
		return flag;

	}
	
	
}
