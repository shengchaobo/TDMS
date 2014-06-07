package cn.nit.dao.di;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.dbconnection.DBConnection;
import cn.nit.bean.di.DiResearchTypeBean;
import cn.nit.util.DAOUtil;

public class DiResearchTypeDao {
	
	private String tableName = "DiResearchType" ;
	
	private String field = "IndexID,ResearchType" ;
	
	/**
	 * 获取DiResearchType字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */	
	public List<DiResearchTypeBean> getList(){
		
		StringBuffer sql = new StringBuffer() ;
		sql.append("select " + field + " from " + tableName) ;
		List<DiResearchTypeBean> list = null ;
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, DiResearchTypeBean.class) ;
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
	 * @param DiResearchType
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */
	public boolean insert(DiResearchTypeBean researchType){
		
		Connection conn = DBConnection.instance.getConnection() ;
		return DAOUtil.insert(researchType, tableName, field, conn) ;
	}
	
	public static void main(String args[]){
		DiResearchTypeDao researchTypeDao =  new DiResearchTypeDao() ;
		System.out.println(researchTypeDao.getList().size()) ;
	}

}
