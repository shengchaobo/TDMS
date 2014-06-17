package cn.nit.dao.table1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.nit.bean.table1.A15Bean;
import cn.nit.bean.table1.S15Bean;
import cn.nit.bean.table1.T151Bean;
import cn.nit.bean.table1.T17Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.pojo.table1.A15POJO;
import cn.nit.pojo.table1.T11POJO;
import cn.nit.util.DAOUtil;

public class A15DAO {
	
	/**  数据库表名  */
	private String tableName = "A15_ResIns_Res$" ;
	/**  统计需要的数据库表名  */
	private String tableName1 = "S15_ResIns$" ;
	
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "NationResNum,NationResRatio, ProviResNum,ProviResRatio,CityResNum,CityResRatio,SchResNum,SchResRatio,SumResNum" +
			",Time,Note" ;
	
	
	/**
	 * 将数据表A15的实体类插入数据库
	 * @param schResIns
	 * @return
	 *
	 * @time: 2014-5-14/上午10:53:10
	 */
	public boolean insert(A15Bean a15Bean){
		
		//flag判断数据是否插入成功
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.insert(a15Bean, tableName, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
		}
		return flag ;
	}
	
	
			/**取出数据*/
		public List<A15POJO> auditingData(String year){
				
				StringBuffer sql = new StringBuffer() ;
				List<A15POJO> list=null;
		        
				sql.append("select * from "+ tableName);
				sql.append(" where Time like '"+year+"%'");
				
				Connection conn = DBConnection.instance.getConnection() ;
				Statement st = null ;
				ResultSet rs = null ;
		//		System.out.println(sql.toString());
				
				try{
					st = conn.createStatement() ;
		//			ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY
		//			st.setMaxRows(1) ;
					rs = st.executeQuery(sql.toString()) ;
		//			rs.absolute((page - 1) * rows) ;
					list = DAOUtil.getList(rs, A15POJO.class) ;
					
					
				}catch(Exception e){
					e.printStackTrace() ;
					return null ;
				}
				return list ;
			}
		      
		/**得到统计信息*/
		public List<S15Bean> getOriData(String year)
		{
			List<S15Bean> list=new ArrayList<S15Bean>();
			
			StringBuffer sql=new StringBuffer();
			sql.append("select * from "+tableName1);
			sql.append(" where Time like '"+year+"%'");
			
			Connection conn=DBConnection.instance.getConnection();
			Statement st=null;
			ResultSet rs=null;
			
			try
			{
				st=conn.createStatement();
				rs=st.executeQuery(sql.toString());
				list = DAOUtil.getList(rs, S15Bean.class) ;
			}catch(Exception e)
			{
				e.printStackTrace();
				return null;
			}
			return list;
		}
		
		/**插入前先删除原始数据*/
		public boolean delete(String Year){
			Boolean flag=false;
			StringBuffer sql=new StringBuffer();
			sql.append("delete from "+tableName );
			sql.append(" where Time like '"+Year+"%'");
			
			Connection conn=DBConnection.instance.getConnection();
			Statement st=null;
			
			try
			{
				st=conn.createStatement();
				st.executeUpdate(sql.toString());
				flag=true;
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return flag;
		}


}
