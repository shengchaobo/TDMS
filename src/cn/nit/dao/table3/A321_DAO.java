package cn.nit.dao.table3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sun.security.krb5.internal.UDPClient;





import cn.nit.bean.table3.A3211_Bean;
import cn.nit.bean.table3.A321_Bean;
import cn.nit.dbconnection.DBConnection;



import cn.nit.pojo.table3.A321POJO;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class A321_DAO {
	



	/**  数据库表名  */
	private String tableName = "A321_MajorDiscipInfo$" ;
	
	private String tableName1 = "T322_UndergraMajorInfo_Tea$" ;
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "TotalNum,DisClass,FieldNum,ArtRatio,Time,Note" ;
	

	
	
	public boolean update(String year,A321_Bean a321_bean){
		String sql="select * from " + tableName + " where Time like '"+year+"%' and DisClass="+"'"+a321_bean.getDisClass()+"'";	
		boolean flag=false;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null;
		ResultSet rs = null;
		List<A321_Bean> list=new ArrayList<A321_Bean>();
		A321_Bean bean=new A321_Bean();
		try{
			st=conn.createStatement();
			rs = st.executeQuery(sql);
			list=DAOUtil.getList(rs, A321_Bean.class);
			if(list.size()!=0){
				bean=list.get(0);
				System.out.println("haha");
				System.out.println(bean.getArtRatio());
				a321_bean.setSeqNumber(bean.getSeqNumber());
				a321_bean.setTime(TimeUtil.changeDateY(year));
				flag=DAOUtil.update(a321_bean, tableName, key, field, conn);
			}else{
				a321_bean.setTime(TimeUtil.changeDateY(year));
				flag=DAOUtil.insert(a321_bean, tableName, field, conn);
				
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
	
	
	
	public  List<A3211_Bean> getOriData(String year)
	{
		List<A3211_Bean> list=new ArrayList<A3211_Bean>();
		
		StringBuffer sql=new StringBuffer();
		sql.append("select a.MajorDegreeType,COUNT(b.MajorDegreeType) AS FieldNum" +
				" from (SELECT distinct MajorDegreeType FROM T322_UndergraMajorInfo_Tea$) a " +
				"left join (select * from T322_UndergraMajorInfo_Tea$) b on a.MajorDegreeType = b.MajorDegreeType where Time like '"+year+"%'group by a.MajorDegreeType");
	
		
		Connection conn=DBConnection.instance.getConnection();
		Statement st=null;
		ResultSet rs=null;
		
		try
		{
			st=conn.createStatement();
			rs=st.executeQuery(sql.toString());
			list = DAOUtil.getList (rs, A3211_Bean.class) ;
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	/**用于数据导出*/
	public List<A321_Bean> totalList(String year){

		StringBuffer sql=new StringBuffer();
		sql.append("select SeqNumber,TotalNum,DisClass,FieldNum,ArtRatio,Time,Note");
        sql.append(" from "+tableName+ " where Time like '"+year+"%'");
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<A321_Bean> list = null ;		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, A321_Bean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null;
		}		
		return list ;
	}

}
