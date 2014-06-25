package cn.nit.dao.table3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import cn.nit.bean.table3.S322_Bean;
import cn.nit.dbconnection.DBConnection;

import cn.nit.pojo.table3.S322POJO;
import cn.nit.util.DAOUtil;

public class S322_DAO {
	

	/**  数据库表名  */
	private String tableName = "S322_MajorAssessInfo$" ;
	
	private String tableName1 = "T322_UndergraMajorInfo_Tea$" ;
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "TeaUnit,UnitID,PassedMajor,MajorID,AssessTime," +
			"ValidityBegin,ValidityEnd,AssessOrg,Time,Note" ;
	
	public boolean empty(){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;

		StringBuffer sql = new StringBuffer() ;
		sql.append(" delete from "+tableName) ;
		Statement st = null ;
		try{
			st = conn.createStatement() ;
			flag = st.execute(sql.toString()) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
		}
		return flag ;
	}
	

	
	public <T> List<T> getOriData(Class<T> cla ,String tableName1)
	{
		List<T> list=new ArrayList<T>();
		
		StringBuffer sql=new StringBuffer();
		sql.append("select * from "+tableName1);
		
		Connection conn=DBConnection.instance.getConnection();
		Statement st=null;
		ResultSet rs=null;
		
		try
		{
			st=conn.createStatement();
			rs=st.executeQuery(sql.toString());
			list = DAOUtil.getList(rs, cla) ;
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	
	public boolean insert(S322_Bean s322_Bean){
		
		//flag判断数据是否插入成功
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.insert(s322_Bean, tableName, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
		}
		return flag ;
	}
	
	public List<S322POJO> auditingData(){
		
		StringBuffer sql = new StringBuffer() ;
		List<S322POJO> list =null ;
		sql.append("select SeqNumber,TeaUnit,UnitID,PassedMajor,MajorID," +
				"AssessTime,ValidityBegin,ValidityEnd,AssessOrg,Note,Time");
		sql.append(" from "+tableName );
		sql.append(" order by SeqNumber desc") ;
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
//		System.out.println(sql.toString());
		
		try{
			st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY) ;
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, S322POJO.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}
		return list ;
	}
	
	

}
