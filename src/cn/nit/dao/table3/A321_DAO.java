package cn.nit.dao.table3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sun.security.krb5.internal.UDPClient;





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
	private String field = "TotalNum,DisClass,FieldNum,Ratio,Time,Note" ;
	


	
 	public boolean save(List<A321_Bean> list,String year){
 		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year;		
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection() ;		
		Statement st = null ;
		ResultSet rs = null ;
		List<A321_Bean> templist = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			templist = DAOUtil.getList(rs, A321_Bean.class) ;
			if(templist.size() != 0){
				String delSql = "delete from " + tableName + " where convert(varchar(4),Time,120)=" + year;
				int delflag = st.executeUpdate(delSql.toString());
				if(delflag > 0 ){
					flag = DAOUtil.batchInsert(list, tableName, field, conn) ;
				}
			}else{
				flag = DAOUtil.batchInsert(list, tableName, field, conn) ;
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return false ;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
 		
 		return flag ;
 		
 	}
	
	
	
	public  List<A321_Bean> getOriData(String year)
	{
		Connection conn=DBConnection.instance.getConnection();
		Statement st=null;
		ResultSet rs=null;
		List<A321_Bean> list = new ArrayList<A321_Bean>() ;
		
		String sql = "select * from "+tableName1+" where time like '"+year+"%'";
		
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(!rs.next()){
				System.out.println("统计数据不全啊  ");
				return list;
			}
			
		}catch(Exception e){
			e.printStackTrace() ;
			return null;
		}
		
		StringBuffer sql1=new StringBuffer();
		sql1.append("select count(distinct b.MajorName) as sum,a.MajorDegreeType as DisClass,COUNT(b.MajorDegreeType) AS FieldNum" +
				" from (SELECT distinct MajorDegreeType FROM T322_UndergraMajorInfo_Tea$) a " +
				"left join  T322_UndergraMajorInfo_Tea$  b on a.MajorDegreeType = b.MajorDegreeType where Time like '"+year+"%'group by a.MajorDegreeType");
	System.out.println(sql1);
		

		int num=0,total=0;
		double ratio=0.0;
		try
		{
			st=conn.createStatement();
			rs=st.executeQuery(sql1.toString());
			while(rs.next()){
				
				num = rs.getInt("FieldNum");
				total += num;
				

				
				A321_Bean bean = new A321_Bean();
				bean.setDisClass(rs.getString("DisClass"));
				bean.setFieldNum(num);
				bean.setTime(TimeUtil.changeDateY(year));
				list.add(bean);
				
			}
			if(list.size()!=0){
				A321_Bean bean = new A321_Bean ();
				bean.setDisClass("合计");
				bean.setFieldNum(total);
				bean.setTotalNum(total);
				bean.setRatio(100);
				bean.setTime(TimeUtil.changeDateY(year));
				list.add(0,bean);
				
				for(int i=1;i<list.size();i++){
					NumberFormat nf = NumberFormat.getNumberInstance();
					nf.setMaximumFractionDigits(2);	
					num = list.get(i).getFieldNum();
					ratio = Double.parseDouble(nf.format((double)num*100/total));
					list.get(i).setTotalNum(total);
					list.get(i).setRatio(ratio);
					
					}
				
				A321_DAO a321_dao = new A321_DAO();
				a321_dao.save(list, year);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		return list;
	}
	
	/**用于数据导出*/
	public List<A321_Bean> totalList(String year){

		StringBuffer sql=new StringBuffer();
		sql.append("select SeqNumber,TotalNum,DisClass,FieldNum,Ratio,Time,Note");
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
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}		
		return list ;
	}

}
