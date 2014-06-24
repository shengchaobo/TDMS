package cn.nit.dao.table1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import cn.nit.bean.table1.T151Bean;
import cn.nit.bean.table1.T16Bean;
import cn.nit.dbconnection.DBConnection;

import cn.nit.pojo.table1.T151POJO;
import cn.nit.pojo.table1.T16POJO;
import cn.nit.util.DAOUtil;


public class T16DAO {
	
	/**  数据库表名  */
	private String tableName = "T16_SchGuiIdeology_PartyOffice$" ;
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "Item,Contents,Time,Note" ;
	
	/**
	 * 将数据表16的实体类插入数据库
	 * @param schResIns
	 * @return
	 *
	 * @time: 2014-5-14/上午10:53:10
	 */
	public boolean insert(T16Bean t16Bean){
		
		//flag判断数据是否插入成功
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		
//		T16Bean1 bean1=null;
//		T16Bean1 bean2=null;
//	
		try{
			flag = DAOUtil.insert(t16Bean, tableName, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
		}
		return flag ;
	}
	
	/**
	 * 數據導出
	 */
	public List<T16POJO> forExcel(String year){
		
		StringBuffer sql = new StringBuffer() ;
		List<T16POJO> list = new ArrayList<T16POJO>() ;
		sql.append("select * from "+tableName) ;
		sql.append(" where Time like '"+year+"%'") ;
		T16POJO t16Pojo =new T16POJO();
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString()) ;
			while(rs.next())
			{	
				String item=rs.getString("Item");
				int seqNumber=rs.getInt("SeqNumber") ;
				String contents=rs.getString("Contents") ;
				String note=rs.getString("Note") ;
				if(item.equals("1.校训"))
				{
					t16Pojo.setContents1(contents) ;
					t16Pojo.setItem1(item);
					t16Pojo.setNote1(note);
					t16Pojo.setSeqNumber1(seqNumber);
				}else
				{
					t16Pojo.setContents2(contents) ;
					t16Pojo.setItem2(item);
					t16Pojo.setNote2(note);
					t16Pojo.setSeqNumber2(seqNumber);
				}
			}
               list.add(t16Pojo) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}
		
		return list ;
	}
	/**
	 * 讲数据批量插入16表中
	 * @param list {@linkplain java.util.List<{@link cn.nit.bean.table1.T151Bean}>}
	 * @return true表示插入成功，false表示插入失败
	 */
	public boolean batchInsert(List<T16Bean> list){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		
		try{
			flag = DAOUtil.batchInsert(list, tableName, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}
		
		return flag ;
	}
	
//	/**
//	 * 查询待审核数据在数据库中共有多少条
//	 * @param conditions 查询条件
//	 * @param fillUnitId 填报人单位号，如果为空，则查询所有未审核的数据，<br>如果不为空，则查询填报人自己单位的所有的数据
//	 * @return
//	 */
//	public int totalAuditingData(String Year){
//		
//		StringBuffer sql = new StringBuffer() ;
//		sql.append("select from "+ tableName) ;
//		sql.append(" where Time like '"+Year+"%'") ;
////		sql.append(" from " + tableName + " as t,DiCourseChar csn,DiCourseCategories cst") ;
////		sql.append(" where audit!='0' and csn.IndexID=t.CSNature and cst.IndexID=t.CSType") ;
//		int total = 0 ;
//		
//		Connection conn = DBConnection.instance.getConnection() ;
//		Statement st = null ;
//		ResultSet rs = null ;
//		
//		try{
//			st = conn.createStatement() ;
//			rs = st.executeQuery(sql.toString()) ;
//			
//			if(rs == null){
//				return total ;
//			}
//			
//			while(rs.next()){
//				total = rs.getInt(1) ;
//			}
//		}catch(Exception e){
//			e.printStackTrace() ;
//			return 0 ;
//		}
//		return total ;
//	}
	
	
//    public List<T16POJO> auditingData(String Year){
//		
//		StringBuffer sql = new StringBuffer() ;
//		List<T16POJO> list = new ArrayList<T16POJO>() ;
//		sql.append("select * from "+tableName) ;
//		sql.append(" where Time like '"+Year+"%'") ;
//		
//		Connection conn = DBConnection.instance.getConnection() ;
//		Statement st = null ;
//		ResultSet rs = null ;
////		System.out.println(sql.toString());
//		
//		try{
//			st = conn.createStatement() ;
//			rs = st.executeQuery(sql.toString()) ;
//			list = DAOUtil.getList(rs, T16POJO.class) ;
//			
//		}catch(Exception e){
//			e.printStackTrace() ;
//			return null ;
//		}
//		
//		return list ;
//	}
	/**
	 * @param conditions 查询条件
	 * @param fillUnitId 填报人单位号，如果为空，则查询所有未审核的数据，<br>如果不为空，则查询填报人自己单位的所有的数据
	 * @return
	 */
	public List<T16POJO> auditingData1(String Year){
		
		StringBuffer sql = new StringBuffer() ;
		List<T16POJO> list = new ArrayList<T16POJO>() ;
		sql.append("select * from "+tableName) ;
		sql.append(" where Time like '"+Year+"%'") ;
//		System.out.println(sql.toString());
		T16POJO t16Pojo =new T16POJO();
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString()) ;
			while(rs.next())
			{	
				String item=rs.getString("Item");
				int seqNumber=rs.getInt("SeqNumber") ;
				String contents=rs.getString("Contents") ;
				String note=rs.getString("Note") ;
				if(item.equals("1.校训"))
				{
					t16Pojo.setContents1(contents) ;
					t16Pojo.setItem1(item);
					t16Pojo.setNote1(note);
					t16Pojo.setSeqNumber1(seqNumber);
				}else
				{
					t16Pojo.setContents2(contents) ;
					t16Pojo.setItem2(item);
					t16Pojo.setNote2(note);
					t16Pojo.setSeqNumber2(seqNumber);
				}
			}
               list.add(t16Pojo) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}
		
		return list ;
	}
	
	public boolean update(T16Bean t16Bean){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.update(t16Bean, tableName, key, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
		}
		return flag ;
	}
	
	public boolean deleteCoursesByIds(String ids){
		
		int flag = 0 ;
		StringBuffer sql = new StringBuffer() ;
		sql.append("delete from " + tableName) ;
		sql.append(" where " + key + " in " + ids) ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		
		try{
			st = conn.createStatement() ;
			flag = st.executeUpdate(sql.toString()) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return false ;
		}
		
		if(flag == 0){
			return false ;
		}else{
			return true ;
		}
	}
	public String getTableName(){
		return this.tableName ;
	}
	
	public static void main(String arg[])
	{
//		T16DAO dao=new T16DAO();
//		List<T16POJO> list=dao.forExcel();
//		T16POJO t16=list.get(0);
//		System.out.println(t16.getNote1());
//		System.out.println(t16.getNote2());
	}

}
