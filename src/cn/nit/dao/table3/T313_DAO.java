package cn.nit.dao.table3;

import java.sql.Connection;
import java.util.Date;


import cn.nit.bean.table3.T313_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T313_DAO {
	
	/**  数据库表名  */
	private String tableName = "T313_Discip_Res$" ;
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "DiscipName,DiscipID,UnitName,UnitID,DiscipType,NationLevelOne,NationLevelTwo,NationLevelKey,ProvinceLevelOne,ProvinceLevelTwo,CityLevel,SchLevel,Time,Note" ;
	
	/**
	 * 将数据表511的实体类插入数据库
	 * @param undergraCSBase
	 * @return
	 *
	 * @time: 2014-5-14/上午10:53:10
	 */
	public boolean insert(T313_Bean discipBean){
		
		//flag判断数据是否插入成功
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.insert(discipBean, tableName, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
		}
		return flag ;
	}
	
	public static void main(String args[]){
		T313_DAO dis = new T313_DAO() ;
		T313_Bean discipBean = new T313_Bean() ;
		discipBean.setDiscipName("ewr") ;
		discipBean.setDiscipID("dsf") ;
		discipBean.setUnitID("0000") ;
		discipBean.setNationLevelKey(true) ;
		discipBean.setTime(new java.util.Date()) ;
		dis.insert(discipBean) ;
		
	}

}
