package cn.nit.dao.di;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.dbconnection.DBConnection;
import cn.nit.bean.di.DiResearchRoomBean;
import cn.nit.util.DAOUtil;

public class DiResearchRoomDao {
	
	private String tableName = "DiResearchRoom" ;
	
	private String field = "UnitID,ParentID,ResearchName" ;
	
	/**
	 * 获取DiResearchRoom字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */	
	public List<DiResearchRoomBean> getList(){
		
		StringBuffer sql = new StringBuffer() ;
		sql.append("select " + field + " from " + tableName) ;
		List<DiResearchRoomBean> list = null ;
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, DiResearchRoomBean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return list ;
		}finally{
			DBConnection.close(rs) ;
			DBConnection.close(st) ;
			DBConnection.close(conn) ;
		}
		return list ;
	}
	
	/**
	 * 插入数据
	 * @param DiResearchRoom
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */
	public boolean insert(DiResearchRoomBean researchRoom){
		
		Connection conn = DBConnection.instance.getConnection() ;
		return DAOUtil.insert(researchRoom, tableName, field, conn) ;
	}
	
	public static void main(String args[]){
		DiResearchRoomDao researchRoomDao =  new DiResearchRoomDao() ;
		System.out.println(researchRoomDao.getList().size()) ;
	}

}
