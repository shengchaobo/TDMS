package cn.nit.dao.table7;

import java.sql.Connection;
import java.util.Date;

import cn.nit.bean.table7.T712_Bean;
import cn.nit.dbconnection.DBConnection;


import cn.nit.util.DAOUtil;

public class T712_DAO {
	
	private String tableName="T712_TeaManagerPaperInfo_TeaTea$";
	
	private String key="SeqNumber";
	
	private String field="TeaUnit,UnitID,Name,TeaID,PaperName,PaperType,FirstSubject,JonalName,JonalID,JonalTime,PaperWordNum,ConfirmLevel,JoinTeaNum,OtherJoinTeaInfo,Time,Note";

	

	
	public boolean insert(T712_Bean teaManagerPaperInfoTeaTea){
		
		boolean flag=false;
		
	   
		Connection conn=DBConnection.instance.getConnection();
		
		try {
			
			flag=DAOUtil.insert(teaManagerPaperInfoTeaTea, tableName, field, conn);
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
