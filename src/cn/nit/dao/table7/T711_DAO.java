package cn.nit.dao.table7;

import java.sql.Connection;
import java.util.Date;

import cn.nit.bean.table7.T711_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T711_DAO {

	
	private String tableName="T711_TeaManagerAwardInfo_TeaTea$";
	
	private String key="SeqNumber";
	
	private String field="TeaUnit,UnitID,Name,TeaID,AwardName,AwardLevel,AwardRank,AwardTime,AwardFromUnit,AppvlID,JoinTeaNum,OtherJoinTeaInfo,Time,Note";


    public boolean insert(T711_Bean teaManagerAwardInfo)
    {
    	boolean flag = false;
    	Connection conn=DBConnection.instance.getConnection();
    	
    	try {
			flag=DAOUtil.insert(teaManagerAwardInfo, tableName, field, conn);
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
