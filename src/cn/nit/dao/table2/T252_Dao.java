package cn.nit.dao.table2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.table2.T252_Bean;
import cn.nit.constants.Constants;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T252_Dao {
	
	private String tableName = "T252_PractisePlaceInfo_Tea$" ;
	private String field = "ExpCenterName,TeaUnit,TeaUnitID,LabName,ExpClassHour,StuNum,ExpHour,ExpTimes,PractiseItemNum,Time,Note,FillUnitID,CheckState";
	private String keyfield = "SeqNumber";
	
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T252_Bean> totalList(String fillUnitID, String year, int checkState){
		
		String sql = null;
		
		if("111".equals(fillUnitID)){
			sql = "select " + keyfield+ "," + field + " from " + tableName +
		     " where CheckState=" + checkState + " and Time like '"+year+"%'";
		}else{
			sql = "select " + " " + keyfield + "," + field 
					+ " from " + tableName + " where FillUnitID=" + "'" + fillUnitID + "'"
					+ " and CheckState=" + checkState + " and Time like '"+year+"%'";
		}
		
//		String sql = "select " + " " + keyfield + "," + field 
//		+ " from " + tableName + " where FillUnitID=" + "'" + fillUnitID + "'"
//		+ " and CheckState=" + checkState + " and Time like '"+year+"%'";
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T252_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T252_Bean.class) ;
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
	
	
	/**
	 * 分 页查询总数
	 * 
	 */
	public int totalQueryPageList(String conditions, String fillunitID){
		
		String Cond = "1=1";
		
		int total = 0;
		if(conditions != null && !conditions.equals("")){
			Cond = Cond + conditions;
		}
		
		if(fillunitID != null && !fillunitID.equals("")){
			Cond = Cond + " and FillUnitID=" + fillunitID;
		}
				
		String queryPageSql = "select count(*) " 
		+ " from " + tableName + 
		" where " + Cond ;
		System.out.println(queryPageSql);
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
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
	 * 分 页查询
	 * 
	 */
	public List<T252_Bean> queryPageList(String conditions, String fillunitID, int pageSize, int showPage){

		
		String Cond = "1=1";
		
		if(conditions != null && !conditions.equals("")){
			Cond = Cond + conditions;
		}
		
		if(fillunitID != null && !fillunitID.equals("")){
			Cond = Cond + " and FillUnitID=" + fillunitID;
		}
				
		String queryPageSql = "select top " + pageSize + " " + keyfield + "," +
		field + " from " + tableName + 
		" where " + Cond + " and (SeqNumber not in (select top " + pageSize * (showPage-1) + " SeqNumber from "+
		tableName  + " where " + Cond +  "  order by SeqNumber)) order by SeqNumber" ;
		System.out.println(queryPageSql);
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T252_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs,T252_Bean.class) ;
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
	
	
	/**
	 * 模板导入
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */
	public boolean batchInsert(List<T252_Bean> list){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		
		String tempfield = "ExpCenterName,TeaUnit,TeaUnitID,LabName,ExpClassHour,StuNum,ExpHour,ExpTimes,PractiseItemNum,Time,FillUnitID,CheckState";
		try{
			flag = DAOUtil.batchInsert(list, tableName, tempfield, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}
		
		return flag ;
		
	}
	
	
	/**
	 * 插入数据
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */
	public boolean insert(T252_Bean schLeader){
		
		Connection conn = DBConnection.instance.getConnection() ;
		return DAOUtil.insert(schLeader, tableName, field, conn) ;
	}
	
	/**
	 * 删除数据
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */	
	public boolean deleteByIds(String ids) {

		int flag = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("delete from " + tableName);
		sql.append(" where " + keyfield + " in " + ids);
		System.out.println(sql.toString());
		Connection conn = DBConnection.instance.getConnection();
		Statement st = null;

		try {
			st = conn.createStatement();
			flag = st.executeUpdate(sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			DBConnection.close(st);
			DBConnection.close(conn);
		}

		if (flag == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 更新数据
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */	
	public boolean update(T252_Bean bean){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			String updatefield = "ExpCenterName,TeaUnit,TeaUnitID,LabName,ExpClassHour,StuNum,ExpHour,ExpTimes,PractiseItemNum,Note,CheckState";			
			
			flag = DAOUtil.update(bean, tableName, keyfield, updatefield, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
		}
		
		return flag ;
	}
	
	
	/**
	 * 找到该条数据的审核状态
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */	
	public int getCheckState(int seqNumber){
				
		String queryPageSql = "select CheckState " 
		+ " from " + tableName + 
		" where SeqNumber='" + seqNumber + "';" ;
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		int state = 1;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			
			while(rs.next()){
				state = rs.getInt(1) ;
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return 0 ;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);
			DBConnection.close(conn);
		}
		return state ;
	}
	
	/**
	 * 更新某条数据的审核状态
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */	
	public boolean updateCheck(int seq, int checkState){
		
		int flag ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		String sql = "update " + tableName + " set CheckState=" + checkState +
		" where SeqNumber='" + seq + "';" ;		
		System.out.println(sql);
		try{			
			st = conn.createStatement();
			flag = st.executeUpdate(sql);					
		}catch(Exception e){
			e.printStackTrace() ;
			return false;
		}finally{
			DBConnection.close(st);
			DBConnection.close(conn);
		}
		if (flag == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 全部审核通过
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */	
	public boolean checkAll(){
		
		int flag ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		String sql = "update " + tableName + " set CheckState=" + Constants.PASS_CHECK +
		" where CheckState=" + Constants.WAIT_CHECK ;		
		
		System.out.println(sql);
		try{			
			st = conn.createStatement();
			flag = st.executeUpdate(sql);					
		}catch(Exception e){
			e.printStackTrace() ;
			return false;
		}finally{
			DBConnection.close(st);
			DBConnection.close(conn);
		}
		
		if (flag == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public static void main(String args[]){
		T252_Dao testDao =  new T252_Dao() ;
		System.out.println("test") ;
	}

}
