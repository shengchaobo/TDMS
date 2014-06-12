/* 
* @Title: UndergraCSBaseTeaDAO.java
* @Package cn.bjtu.dao
* @Description 
* @author Lei Xia
*      Email: xialei199023@163.com
* @copyright BJTU(C)2014
* @date 2014-5-14 上午10:05:12
* @version V1.0 
*/
package cn.nit.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import sun.security.krb5.internal.UDPClient;

import cn.nit.bean.table5.UndergraCSBaseTeaBean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.pojo.table5.UndergraCSBaseTeaPOJO;
import cn.nit.util.DAOUtil;

/**
 * 表511的数据库操作类
 * @author lenovo
 */
public class UndergraCSBaseTeaDAO {
	
	/**  数据库表名  */
	private String tableName = "T511_UndergraCSBase_Tea$" ;
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "CSName,CSID,CSUnit,UnitID,FromTeaResOffice,TeaResOfficeID,CSType,CSNature,State,PubCSType,Time,Note,FillTeaID,FillUnitID,audit" ;
	
	/**
	 * 将数据表511的实体类插入数据库
	 * @param undergraCSBase
	 * @return
	 *
	 * @time: 2014-5-14/上午10:53:10
	 */
	public boolean insert(UndergraCSBaseTeaBean undergraCSBase){
		
		//flag判断数据是否插入成功
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.insert(undergraCSBase, tableName, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
		}
		return flag ;
	}
	
	/**
	 * 讲数据批量插入T511表中
	 * @param list {@linkplain java.util.List<{@link cn.nit.bean.table5.UndergraCSBaseTeaBean}>}
	 * @return true表示插入成功，false表示插入失败
	 */
	public boolean batchInsert(List<UndergraCSBaseTeaBean> list){
		
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
	
	/**
	 * 查询待审核数据在数据库中共有多少条
	 * @param conditions 查询条件
	 * @param fillUnitId 填报人单位号，如果为空，则查询所有未审核的数据，<br>如果不为空，则查询填报人自己单位的所有的数据
	 * @return
	 */
	public int totalAuditingData(String conditions, String fillUnitId){
		
		StringBuffer sql = new StringBuffer() ;
		sql.append("select count(*)") ;
		sql.append(" from " + tableName + " as t,DiCourseChar csn,DiCourseCategories cst") ;
		sql.append(" where audit!='0' and csn.IndexID=t.CSNature and cst.IndexID=t.CSType") ;
		int total = 0 ;
		
		
		
		if(fillUnitId != null && !fillUnitId.equals("")){
			sql.append(" and FillUnitID=" + fillUnitId) ;
		}
		
		if(conditions != null && !conditions.equals("")){
			sql.append(conditions) ;
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
		}
		return total ;
	}
	
	/**
	 * @param conditions 查询条件
	 * @param fillUnitId 填报人单位号，如果为空，则查询所有未审核的数据，<br>如果不为空，则查询填报人自己单位的所有的数据
	 * @return
	 */
	public List<UndergraCSBaseTeaPOJO> auditingData(String conditions, String fillUnitId, int page, int rows){
		
		StringBuffer sql = new StringBuffer() ;
		List<UndergraCSBaseTeaPOJO> list = null ;
		sql.append("select t.SeqNumber,t.CSName,t.CSID,t.CSUnit,t.UnitID,t.FromTeaResOffice,t.TeaResOfficeID,cst.CourseCategories as CSType,t.CSType as CSTypeID,csn.CourseChar as CSNature,t.CSNature as CSNatureID,t.State,t.PubCSType,t.Time,t.Note") ;
		sql.append(" from " + tableName + " as t,DiCourseChar csn,DiCourseCategories cst") ;
		sql.append(" where audit!='0' and csn.IndexID=t.CSNature and cst.IndexID=t.CSType") ;
		//
		
		
		
		if(fillUnitId != null && !fillUnitId.equals("")){
			sql.append(" and FillUnitID=" + fillUnitId) ;
		}
		
		if(conditions != null){
			sql.append(conditions) ;
		}
		
		sql.append(" order by SeqNumber desc") ;
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try{
			st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY) ;
			st.setMaxRows(page * rows) ;
			rs = st.executeQuery(sql.toString()) ;
			rs.absolute((page - 1) * rows) ;
			list = DAOUtil.getList(rs, UndergraCSBaseTeaPOJO.class) ;
			
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}
		
		return list ;
	}
	
	public boolean update(UndergraCSBaseTeaBean underCSBaseTeaBean){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.update(underCSBaseTeaBean, tableName, key, field, conn) ;
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
	
	public static void main(String args[]){
		
		UndergraCSBaseTeaDAO underCSBaseTeaDao = new UndergraCSBaseTeaDAO() ;
		UndergraCSBaseTeaBean undergraCSBaseTea = new UndergraCSBaseTeaBean() ;
		undergraCSBaseTea.setSeqNumber(18) ;
		undergraCSBaseTea.setTime(new java.util.Date()) ;
//		undergraCSBaseTea.setCSName("西方经济学（上）") ;
//		undergraCSBaseTea.setCSID("020120001") ;
//		undergraCSBaseTea.setCSUnit("经济贸易学院") ;
////		undergraCSBaseTea.setUnitID("3001") ;
//		undergraCSBaseTea.setFromTeaResOffice("教务处") ;
////		undergraCSBaseTea.setTeaResOfficeID("3001") ;
////		undergraCSBaseTea.setCSType("理论课（含实践）") ;
////		undergraCSBaseTea.setCSNature("学科基础课") ;
//		undergraCSBaseTea.setState("启用") ;
//		undergraCSBaseTea.setPubCSType("人文社科类") ;
//		undergraCSBaseTea.setNote("无") ;
//		undergraCSBaseTea.setTime(new Date()) ;
//		undergraCSBaseTea.setFillTeaID("11020201") ;
//		undergraCSBaseTea.setFillUnitID("3001") ;
		
//		underCSBaseTeaDao.insert(undergraCSBaseTea) ;
		
//		System.out.println(underCSBaseTeaDao.auditingData("audit='1'",null,2,10).size()) ;
		System.out.println(underCSBaseTeaDao.update(undergraCSBaseTea)) ;
		
		
	}
	
	public String getTableName(){
		return this.tableName ;
	}
}
