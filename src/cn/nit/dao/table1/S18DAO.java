package cn.nit.dao.table1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import cn.nit.bean.table1.S18Bean;
import cn.nit.bean.table1.T181Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.pojo.table1.S18POJO;
import cn.nit.util.DAOUtil;

public class S18DAO {
	
	/**  数据库表名  */
	private String tableName = "S18_SigedCooperInsNum$" ;
	
	/**待统计数据库表名*/
	private String tableName1="T181_SignedCooperIns_Res$";
	/**需要的字段*/
	private String field2="SeqNumber,CooperInsType";
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "SumAgreeNum,AcademicNum,IndustryNum,LocalGoverNum,Time,Note" ;
	
	/**
	 * 显示数据
	 * @param conditions 查询条件
	 * @param fillUnitId 填报人单位号，如果为空，则查询所有未审核的数据，<br>如果不为空，则查询填报人自己单位的所有的数据
	 * @return
	 */
	public S18Bean loadData(String year){
		
		StringBuffer sql = new StringBuffer() ;
		List<S18Bean> list=null;
		S18Bean bean=null;
        
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
			list = DAOUtil.getList(rs, S18Bean.class) ;
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
	 * 更新数据
	 * */
	public boolean update(S18Bean s18Bean){
			
			boolean flag = false ;
			Connection conn = DBConnection.instance.getConnection() ;
			try{
//				System.out.println("hello！");
				flag = DAOUtil.update(s18Bean, tableName, key, field, conn) ;
			}catch(Exception e){
				e.printStackTrace() ;
				return flag ;
			}finally{
				DBConnection.close(conn) ;
			}
			return flag ;
		}
	
	
	/**
	 * @param conditions 查询条件
	 * @param fillUnitId 填报人单位号，如果为空，则查询所有未审核的数据，<br>如果不为空，则查询填报人自己单位的所有的数据
	 * @return
	 */
	public List<S18POJO> auditingData(String year){
		
		StringBuffer sql = new StringBuffer() ;
		List<S18POJO> list=null;
        
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
			list = DAOUtil.getList(rs, S18POJO.class) ;
			
			
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
	 * 将统计表S18的实体类插入数据库
	 * @param schbasInfo
	 * @return
	 *
	 * @time: 2014-5-14/上午10:53:10
	 */
	public boolean insert(S18Bean s18Bean){
		
		//flag判断数据是否插入成功
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.insert(s18Bean, tableName, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
		}
		return flag ;
	}
	
	/**得到统计信息*/
	public List<T181Bean> getOriData(String year)
	{
		List<T181Bean> list=new ArrayList<T181Bean>();
		
		StringBuffer sql=new StringBuffer();
		sql.append("select * from "+tableName1);
		sql.append(" where Time like '"+year+"%'");
		sql.append(" and FillDept like '1012' or FillDept like '1013' or FillDept like '1017'");
		
		Connection conn=DBConnection.instance.getConnection();
		Statement st=null;
		ResultSet rs=null;
		
		try
		{
			st=conn.createStatement();
			rs=st.executeQuery(sql.toString());
			list = DAOUtil.getList(rs, T181Bean.class) ;
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	
	
	/**
	excel数据导出
	 */
	public List<S18Bean> forExcel(String year){
		
		StringBuffer sql = new StringBuffer() ;
		List<S18Bean> list=null;
        
		sql.append("select * from "+ tableName);
		sql.append(" where Time like '"+year+"%'");
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try{
			st = conn.createStatement() ;

			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, S18Bean.class) ;
			
			
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}
		return list ;
	}
	
	public String getTableName(){
		return this.tableName ;
	}
	
    public static void main(String arg[]){
    	S18DAO dao=new S18DAO();
    	List<T181Bean> list=dao.getOriData("2014");
    	System.out.println(list.size());
    }
	

}
