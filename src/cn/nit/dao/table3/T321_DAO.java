package cn.nit.dao.table3;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.Date;


import cn.nit.bean.table3.T321_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T321_DAO {
	
	/**  数据库表名  */
	private String tableName = "T321_MainTrainBasicInfo_Tea$" ;
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "MainClassName,MainClassID,ByPassTime,MajorNameInSch,MajorID,UnitName,UnitID,Time,Note" ;
	
	/**
	 * 将数据表311的实体类插入数据库
	 * @param undergraCSBase
	 * @return
	 *
	 * @time: 2014-5-14/上午10:53:10
	 */
	
	
	public int getNumofMainTrain(String UnitID){
		
		int num=0;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			DatabaseMetaData metadata=conn.getMetaData();
			ResultSet rs=metadata.getColumns(null,null,null,null);
			while(rs.next()){
				if("(rs.getArray(8))"== UnitID){
					num++;
				}
				
			}
		}catch(Exception e){
			e.printStackTrace() ;
		}finally{
			DBConnection.close(conn) ;
		}
		return num;
		
	}

	public boolean insert(T321_Bean mianTrainBasicInfoBean){
		
		//flag判断数据是否插入成功
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.insert(mianTrainBasicInfoBean, tableName, field, conn) ;
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
