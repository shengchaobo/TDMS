package cn.nit.dao.table1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.nit.bean.table1.S17Bean;
import cn.nit.bean.table1.T17Bean;
import cn.nit.dbconnection.DBConnection;

import cn.nit.pojo.table1.S17POJO;
import cn.nit.util.DAOUtil;

public class S17DAO {
	
	/**  数据库表名  */
	private String tableName = "S17_SchFriClubNum$" ;
	
	/**待统计数据库表名*/
	private String tableName1="T17_SchFriClub_PartyOffice$";
	/**需要的字段*/
	private String field2="SeqNumber,Place";
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "SumSchFriNum,InlandNum,OutlandNum,Time,Note" ;
	
	
	
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
	 * 显示数据
	 * @param conditions 查询条件
	 * @param fillUnitId 填报人单位号，如果为空，则查询所有未审核的数据，<br>如果不为空，则查询填报人自己单位的所有的数据
	 * @return
	 */
	public S17Bean loadData(String year){
		
		StringBuffer sql = new StringBuffer() ;
		List<S17Bean> list=null;
		S17Bean bean=null;
        
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
			list = DAOUtil.getList(rs, S17Bean.class) ;
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
	public boolean update(S17Bean s17Bean){
			
			boolean flag = false ;
			Connection conn = DBConnection.instance.getConnection() ;
			try{
//				System.out.println("hello！");
				flag = DAOUtil.update(s17Bean, tableName, key, field, conn) ;
			}catch(Exception e){
				e.printStackTrace() ;
				return flag ;
			}finally{
				DBConnection.close(conn) ;
			}
			return flag ;
		}

	/**
	 * 保存数据
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */
	public boolean save(S17Bean bean, String year, String fields){
		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year;		
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection() ;
		
		Statement st = null ;
		ResultSet rs = null ;
		List<S17Bean> list = null ;
		S17Bean tempBean = null;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, S17Bean.class) ;
			if(list.size() != 0){
				tempBean = list.get(0);
				bean.setSeqNumber(tempBean.getSeqNumber());
				int InlandNum = bean.getInlandNum();
				int OutlandNum = bean.getOutlandNum();
				int SumSchFriNum = InlandNum+OutlandNum;
				 
				bean.setSumSchFriNum(SumSchFriNum);
				
				flag = DAOUtil.update(bean, tableName, key, fields, conn) ;
				
			}else{
				flag = DAOUtil.insert(bean, tableName, key, conn) ;
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return false ;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
				
		return flag ;
	}
	
	/**
	 * @param conditions 查询条件
	 * @param fillUnitId 填报人单位号，如果为空，则查询所有未审核的数据，<br>如果不为空，则查询填报人自己单位的所有的数据
	 * @return
	 */
	public List<S17POJO> auditingData(String year){
		
		StringBuffer sql = new StringBuffer() ;
		List<S17POJO> list=null;
        
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
			list = DAOUtil.getList(rs, S17POJO.class) ;
			
			
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}
		return list ;
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
	
	
	/**
	excel数据导出
	 */
	public List<S17Bean> forExcel(String year){
		
		StringBuffer sql = new StringBuffer() ;
		List<S17Bean> list=null;
        
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
			list = DAOUtil.getList(rs, S17Bean.class) ;
			
			
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}
		return list ;
	}
	
	/**
	 * 将统计表S17的实体类插入数据库
	 * @param schbasInfo
	 * @return
	 *
	 * @time: 2014-5-14/上午10:53:10
	 */
	public boolean insert(S17Bean s17Bean){
		
		//flag判断数据是否插入成功
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.insert(s17Bean, tableName, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
		}
		return flag ;
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
	
	/**得到统计信息*/
	public List<T17Bean> getOriData(String year)
	{
		List<T17Bean> list=new ArrayList<T17Bean>();
		
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
			list = DAOUtil.getList(rs, T17Bean.class) ;
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	public static void main(String arg[]){
		S17DAO dao=new S17DAO();
//		S17Bean bean=dao.loadData("2014");
//		if(bean!=null){
//			System.out.println("yews");
//		}
//		int count=dao.getCount("2013");
//		System.out.println(count);
	}
	

}
