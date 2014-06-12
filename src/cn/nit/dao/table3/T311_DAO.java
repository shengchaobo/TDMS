package cn.nit.dao.table3;

import java.sql.Connection;
import java.util.Date;


import cn.nit.bean.table3.T311_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T311_DAO {
	
	//"SeqNumber,PostDocStaName,SetTime,ResearcherNum,UnitName,UnitID,Time,Note"

	/**  数据库表名  */
	private String tableName = "T311_PostDocSta_Per$" ;
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "PostDocStaName,SetTime,ResearcherNum,UnitName,UnitID,Time,Note" ;
	
	/**
	 * 将数据表311的实体类插入数据库
	 * @param undergraCSBase
	 * @return
	 *
	 * @time: 2014-5-14/上午10:53:10
	 */
	public boolean insert(T311_Bean postDocStaBean){
		
		//flag判断数据是否插入成功
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.insert(postDocStaBean, tableName, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
		}
		return flag ;
	}
	
	public static void main(String args[]){
		

	}


}
