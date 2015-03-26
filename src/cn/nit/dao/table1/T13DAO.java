package cn.nit.dao.table1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.table1.T12_Bean;
import cn.nit.bean.table1.T13_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.pojo.table1.T13POJO;
import cn.nit.util.DAOUtil;

public class T13DAO {
	
	/**  数据库表名  */
	private String tableName = "DiDepartment" ;
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "UnitID,UnitName,Leader,TeaID,Note" ;
	
	
	
	/**
	 * 查询待审核数据在数据库中共有多少条
	 * @param conditions 查询条件
	 * @param fillUnitId 填报人单位号，如果为空，则查询所有未审核的数据，<br>如果不为空，则查询填报人自己单位的所有的数据
	 * @return
	 */
	public int totalAuditingData(String conditions, String fillUnitId){
		
		StringBuffer sql = new StringBuffer() ;
		sql.append("select t.SeqNumber,t.UnitID,t.UnitName,t.Leader,t.TeaID,t.Time,t.Note") ;
		sql.append(" from " + tableName + " as t,DiDepartment dpt,T411_TeaBasicInfo_Per$ tea") ;
		sql.append(" where dpt.UnitID=t.UnitID and tea.TeaID=t.TeaID");
		int total = 0 ;
		if(conditions==null){
			sql.append(" where UnitID like '"+fillUnitId+"%'") ;
		}else{
			sql.append( conditions);
		}
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			
			if(rs == null){
				return total ;
			}
			
			while(rs.next()){
				total = rs.getInt(1) ;
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return 0 ;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);
			DBConnection.close(conn);
		}
		return total ;
	}
	
	/**
	 * @param conditions 查询条件
	 * @param fillUnitId 填报人单位号，如果为空，则查询所有未审核的数据，<br>如果不为空，则查询填报人自己单位的所有的数据
	 * @return
	 */
	public List<T13POJO> auditingData(String conditions, String fillUnitId, int page, int rows){
		
		StringBuffer sql = new StringBuffer() ;
		List<T13POJO> list = null ;
		
		sql.append("select t.SeqNumber,t.UnitID,t.UnitName,t.UnitType,t.Functions,t.Leader,t.TeaID,t.Time,t.Note") ;
		sql.append(" from " + tableName + " as t,DiDepartment dpt,T411_TeaBasicInfo_Per$ tea") ;
		sql.append(" where dpt.UnitID=t.UnitID and tea.TeaID=t.TeaID");

		if(conditions==null){
			sql.append(" where UnitID like '"+fillUnitId+"%'") ;
		}else{
			sql.append( conditions);
		}
		
//		sql.append(" order by SeqNumber desc") ;
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try{
			st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY) ;
			st.setMaxRows(page * rows) ;
			rs = st.executeQuery(sql.toString()) ;
			rs.absolute((page - 1) * rows) ;
			list = DAOUtil.getList(rs, T13POJO.class) ;
			
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
	
	
	public List<T12_Bean> totalListAll(){
		
		StringBuffer sql=new StringBuffer();
		sql.append("select UnitName,UnitID,Leader,Functions,TeaID,Note" );
		sql.append(" from "+tableName);
		sql.append(" where UnitID not like '10%'");
//		System.out.println(sql.toString());

		
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T12_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, T12_Bean.class) ;
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
	
	/**
	 * 获得的总数（用于导出）T13
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T12_Bean> totalList(){

		StringBuffer sql=new StringBuffer();
		sql.append("select UnitName,UnitID,Leader,Functions,TeaID,Note" );
		sql.append(" from "+tableName );
		sql.append(" where UnitID like '20%'");
//		System.out.println(sql.toString());

		
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T12_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, T12_Bean.class) ;
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
	
	/**
	 * 讲数据批量插入13表中
	 * @param list {@linkplain java.util.List<{@link cn.nit.bean.table1.T151_Bean}>}
	 * @return true表示插入成功，false表示插入失败
	 */
	public boolean batchInsert(List<T13_Bean> list){
		
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
	
	public String getTableName(){
		return this.tableName ;
	}
	
	public static void  main(String arg[]){
		T13DAO dao=new T13DAO();
		List<T12_Bean> list=dao.totalListAll();
		System.out.println(list.size());
	} 


}
