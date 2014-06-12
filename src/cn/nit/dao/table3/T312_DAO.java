package cn.nit.dao.table3;

import java.sql.Connection;
import java.util.Date;



import cn.nit.bean.table3.T312_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;


public class T312_DAO {
	//SeqNumber,StaName,StaID,UnitName,UnitID,StaType,Time,Note
	
	/**  数据库表名  */
	private String tableName = "T312_DocAndGraSta_Gra$" ;
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "StaName,StaID,UnitName,UnitID,StaType,Time,Note" ;
	
	/**
	 * 将数据表312的实体类插入数据库
	 * @param undergraCSBase
	 * @return
	 *
	 * @time: 2014-5-14/上午10:53:10
	 */
	public boolean insert(T312_Bean docAndGraStaBean){
		
		//flag判断数据是否插入成功
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.insert(docAndGraStaBean, tableName, field, conn) ;
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
