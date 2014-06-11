package cn.nit.dao.table7;

import java.sql.Connection;

import cn.nit.bean.table7.T734_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T734_DAO {
 
	
	private String tableName="T734_TeachAccident_Tea$";
	
	private String key="SeqNumber";
	
	private String field="TeaName,TeaID,FromDept,UnitID,AccidentSite,Cause,HandingTime,AccidentLevel,HandingID,Time,Note";

    public boolean insert(T734_Bean teachAcc){
    	boolean flag=false;
    	
    	Connection conn=DBConnection.instance.getConnection();
    	
    	try {
			flag=DAOUtil.insert(teachAcc, tableName, field, conn);
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
