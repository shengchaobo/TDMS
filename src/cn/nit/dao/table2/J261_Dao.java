package cn.nit.dao.table2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.dbconnection.DBConnection;
import cn.nit.pojo.table2.J261_POJO;
import cn.nit.util.DAOUtil;

public class J261_Dao {
	
	private String tableName = "T251_PractisePlaceInfo_EQU$" ;
	private String tableName1 = "T252_PractisePlaceInfo_Tea$" ;
	private String field = "T251_PractisePlaceInfo_EQU$.ExpCenterName,T251_PractisePlaceInfo_EQU$.TeaUnit,T251_PractisePlaceInfo_EQU$.TeaUnitID,Area,Nature,ForMajor,ExpHour,ExpTimes,PractiseItemNum,StuNum";

	
	/**
	 * 获得当年所有数据
	 * @return
	 */
	public List<J261_POJO> getYearInfo(String year){
		
		String sql = "select " + field + 				
		" from " + tableName +
		" left join " + tableName1+ " on " + tableName + ".ExpCenterName=" + tableName1 + ".ExpCenterName " +
		" where convert(varchar(4),T251_PractisePlaceInfo_EQU$.Time,120)=" + year;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<J261_POJO> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, J261_POJO.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);
			DBConnection.close(conn);
		}
		
		return list ;
	}

}
