package cn.nit.dao.table3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sun.security.krb5.internal.UDPClient;




import cn.nit.bean.table2.T21_Bean;
import cn.nit.bean.table3.S31_Bean;
import cn.nit.bean.table3.S321_Bean;
import cn.nit.dbconnection.DBConnection;



import cn.nit.pojo.table3.S31POJO;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class S31_DAO {
	
	//"SeqNumber,PostDocStaName,SetTime,ResearcherNum,UnitName,UnitID,Time,Note"

	/**  数据库表名  */
	private String tableName = "S31_DiscipBuild$" ;
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "PostdocStation,DocStationOne,DocStationTwo,MasterStationOne,MasterStationTwo,SumMajor,NewMajor,JuniorMajor,Time,Note" ;

	/**
	 * 将数据表311的实体类插入数据库
	 * @param undergraCSBase
	 * @return
	 *
	 * @time: 2014-5-14/上午10:53:10
	 */


	

	public boolean update(String year,S31_Bean s31_bean){
		String sql="select * from " + tableName + " where Time like '"+year+"%'";	
		boolean flag=false;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null;
		ResultSet rs = null;
		List<S31_Bean> list=new ArrayList<S31_Bean>();
		S31_Bean bean=new S31_Bean();
		try{
			st=conn.createStatement();
			rs = st.executeQuery(sql);
			list=DAOUtil.getList(rs, S31_Bean.class);
			if(list.size()!=0){
				bean=list.get(0);
				s31_bean.setSeqNumber(bean.getSeqNumber());
				s31_bean.setTime(TimeUtil.changeDateY(year));
				flag=DAOUtil.update(s31_bean, tableName, key, field, conn);
			}else{
				s31_bean.setTime(TimeUtil.changeDateY(year));
				flag=DAOUtil.insert(s31_bean, tableName, field, conn);
				
			}
		}catch (Exception e){
			e.printStackTrace() ;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
		return flag;
		
		
	}
	
	
	
	
	public S31_Bean exportData(String year){
		
		String sql = "select " + " " + key + "," +
		field + " from " + tableName + " where convert(varchar(4),Time,120)=" + year;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<S31_Bean> list = null ;
		S31_Bean bean = null;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, S31_Bean.class) ;
			if(list.size() != 0){
				bean = list.get(0);
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
		
		return bean ;
	}
	


}
