package cn.nit.dao.table1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.nit.bean.table1.A15_Bean;
import cn.nit.bean.table1.S15_Bean;
import cn.nit.bean.table1.T151_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.pojo.table1.A15POJO;
import cn.nit.util.DAOUtil;

public class A15DAO {
	
	/**  数据库表名  */
	private String tableName = "A15_ResIns_Res$" ;
	/**  统计需要的数据库表名  */
	private String tableName1 = "S15_ResIns$" ;
	
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "NationResNum,NationResRatio,ProviResNum,ProviResRatio,CityResNum,CityResRatio,SchResNum,SchResRatio,SumResNum" +
			",Time,Note" ;
	
	
	/**
	 * 查询相关年份的SeqNumber
	 * */
	public int getSeqNumber(String year){
		int seq=-1;
		StringBuffer sql=new StringBuffer();
		sql.append("select SeqNumber from "+tableName+" where Time like '"+year+"%'");
		
		Connection conn=DBConnection.instance.getConnection();
		Statement st=null;
		ResultSet rs=null;
		
		try{
			st=conn.createStatement();
			rs=st.executeQuery(sql.toString());
			while(rs.next()){
				seq=rs.getInt("SeqNumber");
			}
		}catch(Exception e){
			e.printStackTrace();
			return seq;
		}
		return seq;
	}
	
	/**
	 * 查询相关年份的SeqNumber
	 * */
	public int getSeqNumber1(String year){
		int seq=-1;
		StringBuffer sql=new StringBuffer();
		sql.append("select SeqNumber from "+tableName1+" where Time like '"+year+"%'");
		
		Connection conn=DBConnection.instance.getConnection();
		Statement st=null;
		ResultSet rs=null;
		
		try{
			st=conn.createStatement();
			rs=st.executeQuery(sql.toString());
			while(rs.next()){
				seq=rs.getInt("SeqNumber");
			}
		}catch(Exception e){
			e.printStackTrace();
			return seq;
		}
		return seq;
	}
	
	/**
	 * 符合条件原始数据条数
	 * */
	public int countOri(String year){
		
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) AS icount from "+ tableName1);
		sql.append(" where Time like '"+year+"%'");
		int count = 0;
		
		Connection conn=DBConnection.instance.getConnection();
		Statement st=null;
		ResultSet rs=null;
		
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			while(rs.next()){
				count = rs.getInt("icount");
				System.out.println(count);
			}
		}catch(Exception e){
			e.printStackTrace();
			return count;
		}
		
		return count;
	}
	
	/**
	 * 显示数据
	 * @param conditions 查询条件
	 * @param fillUnitId 填报人单位号，如果为空，则查询所有未审核的数据，<br>如果不为空，则查询填报人自己单位的所有的数据
	 * @return
	 */
	public A15_Bean loadData(String year){
		
		StringBuffer sql = new StringBuffer() ;
		List<A15_Bean> list=null;
		A15_Bean bean=null;
        
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
			list = DAOUtil.getList(rs, A15_Bean.class) ;
			if(list.size() != 0){
				bean = list.get(0);
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}
		return bean ;
	}
	
	/**
	 * 更新数据
	 * */
	public boolean update(A15_Bean a15Bean){
			
			boolean flag = false ;
			Connection conn = DBConnection.instance.getConnection() ;
			try{
//				System.out.println("hello！");
				flag = DAOUtil.update(a15Bean, tableName, key, field, conn) ;
			}catch(Exception e){
				e.printStackTrace() ;
				return flag ;
			}finally{
				DBConnection.close(conn) ;
			}
			return flag ;
		}
	

	
	/**
	 * 将数据表A15的实体类插入数据库
	 * @param schResIns
	 * @return
	 *
	 * @time: 2014-5-14/上午10:53:10
	 */
	public boolean insert(A15_Bean a15Bean){
		
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
		
		/**
		excel数据导出
		 */
		public List<A15_Bean> forExcel(String year){
			
			StringBuffer sql = new StringBuffer() ;
			List<A15_Bean> list=null;
	        
			sql.append("select * from "+ tableName);
			sql.append(" where Time like '"+year+"%'");
			
			Connection conn = DBConnection.instance.getConnection() ;
			Statement st = null ;
			ResultSet rs = null ;
			
			try{
				st = conn.createStatement() ;

				rs = st.executeQuery(sql.toString()) ;
				list = DAOUtil.getList(rs, A15_Bean.class) ;
				
				
			}catch(Exception e){
				e.printStackTrace() ;
				return null ;
			}
			return list ;
		}
		      
		/**得到统计信息*/
		public List<S15_Bean> getOriData(String year)
		{
			List<S15_Bean> list=new ArrayList<S15_Bean>();
			
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
				list = DAOUtil.getList(rs, S15_Bean.class) ;
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
		
		public static void main(String arg[]){
			A15DAO dao=new A15DAO();
			 List<S15_Bean> list=dao.getOriData("2014");
			 System.out.println(list.size());
		}


}
