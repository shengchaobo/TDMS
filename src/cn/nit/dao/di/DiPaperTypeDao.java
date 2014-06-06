package cn.nit.dao.di;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.dbconnection.DBConnection;
import cn.nit.bean.di.DiPaperTypeBean;
import cn.nit.bean.di.DiResearchTypeBean;
import cn.nit.util.DAOUtil;

public class DiPaperTypeDao {
	
	private String tableName = "DiResearchType" ;
	
	private String field = "IndexID,PaperType" ;
	
	/**
	 * 获取DiResearchType字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */	
	public List<DiPaperTypeBean> getList(){
		
		StringBuffer sql = new StringBuffer() ;
		sql.append("select " + field + " from " + tableName) ;
		List<DiPaperTypeBean> list = null ;
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, DiPaperTypeBean.class) ;
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
	 * @param DiPaperType
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */
	public boolean insert(DiPaperTypeBean PaperType){
		
		Connection conn = DBConnection.instance.getConnection() ;
		return DAOUtil.insert(PaperType, tableName, field, conn) ;
	}
	
	public static void main(String args[]){
		DiPaperTypeDao PaperTypeDao =  new DiPaperTypeDao() ;
		System.out.println(PaperTypeDao.getList().size()) ;
	}

}
