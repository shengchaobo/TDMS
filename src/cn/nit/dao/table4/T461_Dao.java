package cn.nit.dao.table4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.table4.T42_Bean;
import cn.nit.bean.table4.T461_Bean;
import cn.nit.constants.Constants;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class T461_Dao {
	
	private String tableName = "T461_FameTeaAward_Per$" ;
	private String tableName1 = "DiAwardLevel" ;
	private String tableName2 = "DiAwardType" ;
	private String field = "Name,TeaId,FromTeaUnit,UnitId,AwardType,AwardLevel,AwardFromUnit," +
			"GainAwardTime,AppvlId,OtherTeaNum,OtherTeaInfo,Time,Note,FillUnitID,CheckState";
	private String keyfield = "SeqNumber";
	
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T461_Bean> totalList(String param,String fillUnitID, String year, int checkState){
		
		String cond = null;
		if(fillUnitID != null&&!"111".equals(fillUnitID)){
			cond = " FillUnitID=" + "'" + fillUnitID + "' and ";
		}
				
		if(param.equals("1")){
			cond = "(" + tableName + ".AwardType = '51000'" + ")";
		}
		else if(param.equals("2")){
			cond = "(" + tableName + ".AwardType = '51001' or " + tableName + ".AwardType = '51002'" + ")";;
		}
		else if(param.equals("3")){
			cond = "(" + tableName + ".AwardType = '51003'" + ")";
		}
		else if(param.equals("4")){
			cond = "(" + tableName + ".AwardType = '51004' or " + tableName + ".AwardType = '51005'" + ")";;
		}
		else if(param.equals("5")){
			cond = "(" + tableName + ".AwardType = '51006'" + ")";;
		}
		else if(param.equals("6")){
			cond = "(" + tableName + ".AwardType = '51007'" + ")";;
		}
		
		String sql = "select " +
			"SeqNumber,Name,TeaId,FromTeaUnit,UnitId,DiAwardType.AwardType,DiAwardLevel.AwardLevel,AwardFromUnit," +
			"GainAwardTime,AppvlId,OtherTeaNum,OtherTeaInfo,Time,Note,FillUnitID,CheckState"
			+ " from " + tableName + 
			" left join " + tableName1+ " on " + tableName + ".AwardLevel=" + tableName1 + ".IndexID " +
			" left join " + tableName2+ " on " + tableName + ".AwardType=" + tableName2 + ".IndexID " + 
			" where " + cond +
			" and CheckState=" + checkState + " and Time like '"+year+"%'";;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T461_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs,T461_Bean.class) ;
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
	public int totalQueryPageList(String conditions, String fillunitID, String param){
		
		String Cond = null;
		
		int total = 0;
		
		if(param.equals("1")){
			Cond = "(T461_FameTeaAward_Per$.AwardType = '51000')";
		}
		else if(param.equals("2")){
			Cond = "(T461_FameTeaAward_Per$.AwardType = '51001' or T461_FameTeaAward_Per$.AwardType = '51002')";
		}
		else if(param.equals("3")){
			Cond = "(T461_FameTeaAward_Per$.AwardType = '51003')";
		}
		else if(param.equals("4")){
			Cond = "(T461_FameTeaAward_Per$.AwardType = '51004' or T461_FameTeaAward_Per$.AwardType = '51005')";
		}
		else if(param.equals("5")){
			Cond = "(T461_FameTeaAward_Per$.AwardType = '51006')";
		}
		else if(param.equals("6")){
			Cond = "(T461_FameTeaAward_Per$.AwardType = '51007')";
		}
		
		if(conditions != null && !conditions.equals("")){
			Cond = Cond + conditions;
		}
		
		if(fillunitID != null && !fillunitID.equals("")){
			Cond = Cond + " and FillUnitID='" + fillunitID + "' ";
		}
				
		String queryPageSql = "select count(*) " 
		+ " from " + tableName + 
		" left join " + tableName1+ " on " + tableName + ".AwardLevel=" + tableName1 + ".IndexID " +
		" left join " + tableName2+ " on " + tableName + ".AwardType=" + tableName2 + ".IndexID " +
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
	public List<T461_Bean> queryPageList(String conditions, String fillunitID, int pageSize, int showPage, String param){
		
		String cond = null;
		
		if(param.equals("1")){
			cond = "(T461_FameTeaAward_Per$.AwardType = '51000')";
		}
		else if(param.equals("2")){
			cond = "(T461_FameTeaAward_Per$.AwardType = '51001' or T461_FameTeaAward_Per$.AwardType = '51002')";
		}
		else if(param.equals("3")){
			cond = "(T461_FameTeaAward_Per$.AwardType = '51003')";
		}
		else if(param.equals("4")){
			cond = "(T461_FameTeaAward_Per$.AwardType = '51004' or T461_FameTeaAward_Per$.AwardType = '51005')";
		}
		else if(param.equals("5")){
			cond = "(T461_FameTeaAward_Per$.AwardType = '51006')";
		}
		else if(param.equals("6")){
			cond = "(T461_FameTeaAward_Per$.AwardType = '51007')";
		}
		
		if(conditions != null && !conditions.equals("")){
			cond = cond + conditions;
		}
		
		if(fillunitID != null && !fillunitID.equals("")){
			cond = cond + " and FillUnitID='" + fillunitID + "' ";
		}
				
		String queryPageSql = "select top " + pageSize + " " + keyfield + "," +
		"Name,TeaId,FromTeaUnit,UnitId,DiAwardType.AwardType,DiAwardLevel.AwardLevel,AwardFromUnit," +
		"GainAwardTime,AppvlId,OtherTeaNum,OtherTeaInfo,Time,Note,FillUnitID,CheckState"
		+ " from " + tableName + 
		" left join " + tableName1+ " on " + tableName + ".AwardLevel=" + tableName1 + ".IndexID " +
		" left join " + tableName2+ " on " + tableName + ".AwardType=" + tableName2 + ".IndexID " +
		" where " + cond + "and (SeqNumber not in (select top " + pageSize * (showPage-1) + " SeqNumber from "+
		tableName + " where " + cond + " order by SeqNumber)) order by SeqNumber" ;
		System.out.println(queryPageSql);
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T461_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T461_Bean.class) ;
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
	 * 插入数据
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */
	public boolean insert(T461_Bean teaHonor){
		
		Connection conn = DBConnection.instance.getConnection() ;
		return DAOUtil.insert(teaHonor, tableName, field, conn) ;
	}

	/**
	 * 模板导入
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */
	public boolean batchInsert(List<T461_Bean> list){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		
		String tempfield = "Name,TeaId,FromTeaUnit,UnitId,AwardType,AwardLevel,AwardFromUnit," +
		"GainAwardTime,AppvlId,OtherTeaNum,OtherTeaInfo,Time,Note,FillUnitID,CheckState";
		
		try{
			flag = DAOUtil.batchInsert(list, tableName, tempfield, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{	
			DBConnection.close(conn);
		}
		
		return flag ;
		
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
	public boolean update(T461_Bean bean){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			String updatefield = "Name,TeaId,FromTeaUnit,UnitId,AwardType,AwardLevel,AwardFromUnit," +
			"GainAwardTime,AppvlId,OtherTeaNum,OtherTeaInfo,Note,CheckState";
			
			String temp1 = updatefield;
			
			if(bean.getAwardType().trim().equals("")){
				String a = "AwardType,";
			    temp1 = updatefield.replaceAll(a , "");
			}
			
			String temp2 = temp1;
			
			if(bean.getAwardLevel().trim().equals("")){
				temp2 = temp1.replaceAll("AwardLevel," , "");
			}
			
			flag = DAOUtil.update(bean, tableName, keyfield, temp2, conn) ;
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
	public boolean checkAll(String param){
		
		String cond = null;
				
		if(param.equals("1")){
			cond = "(" + tableName + ".AwardType = '51000'" + ")";
		}
		else if(param.equals("2")){
			cond = "(" + tableName + ".AwardType = '51001' or " + tableName + ".AwardType = '51002'" + ")";;
		}
		else if(param.equals("3")){
			cond = "(" + tableName + ".AwardType = '51003'" + ")";
		}
		else if(param.equals("4")){
			cond = "(" + tableName + ".AwardType = '51004' or " + tableName + ".AwardType = '51005'" + ")";;
		}
		else if(param.equals("5")){
			cond = "(" + tableName + ".AwardType = '51006'" + ")";;
		}
		else if(param.equals("6")){
			cond = "(" + tableName + ".AwardType = '51007'" + ")";;
		}
		
		int flag ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		String sql = "update " + tableName + " set CheckState=" + Constants.PASS_CHECK +
		" where " + cond + "and CheckState=" + Constants.WAIT_CHECK ;		
		
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
		T461_Dao testDao =  new T461_Dao() ;
		//System.out.println(testDao.totalList("1")) ;
	}


}
