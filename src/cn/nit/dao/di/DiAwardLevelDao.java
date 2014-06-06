package cn.nit.dao.di;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.di.DiAwardLevelBean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class DiAwardLevelDao {

	private String tableName = "DiAwardLevel" ;
	
	private String field = "IndexID,AwardLevel" ;
	
	/**
	 * 获取DiTitleLevel字典表的所有数�?
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<DiAwardLevelBean> getList(){
		
		String sql = "select " + field + " from " + tableName ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<DiAwardLevelBean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, DiAwardLevelBean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(rs) ;
			DBConnection.close(st) ;
			DBConnection.close(conn) ;
		}
		
		return list ;
	}
	
	/**

	 * 得到校级及以上的
	 * */
	public List<DiAwardLevelBean> getListPart(){
			
			List<DiAwardLevelBean> list = null ;
			String s1="50005";
			String s2="50006";
			StringBuffer sql = new StringBuffer() ;
			sql.append("select " + field + " from " + tableName) ;
			sql.append(" where IndexID !="+s1+" and IndexID !="+s2);
			System.out.println(sql.toString());
			Connection conn = DBConnection.instance.getConnection() ;
			Statement st = null ;
			ResultSet rs = null ;
			
			try{
				st = conn.createStatement() ;
				rs = st.executeQuery(sql.toString()) ;
				list = DAOUtil.getList(rs, DiAwardLevelBean.class) ;
			}catch(Exception e){
				e.printStackTrace() ;
				return list ;
			}
			return list ;
		}
	
	/**
	 * 得到校级和系级的
	 * */
	public List<DiAwardLevelBean> getListPartTwo(){
			
			List<DiAwardLevelBean> list = null ;
			StringBuffer sql = new StringBuffer() ;
			sql.append("select " + field + " from " + tableName) ;
			sql.append(" where IndexID='50004' or IndexID='50005'");
			System.out.println(sql.toString());
			Connection conn = DBConnection.instance.getConnection() ;
			Statement st = null ;
			ResultSet rs = null ;
			
			try{
				st = conn.createStatement() ;
				rs = st.executeQuery(sql.toString()) ;
				list = DAOUtil.getList(rs, DiAwardLevelBean.class) ;
			}catch(Exception e){
				e.printStackTrace() ;
				return list ;
			}
			return list ;
		}
	
	/**

	 * 插入数据
	 * @param DiIdentiType
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */
	public boolean insert(DiAwardLevelBean awardLevel){
		
		Connection conn = DBConnection.instance.getConnection() ;
		return DAOUtil.insert(awardLevel, tableName, field, conn) ;
	}
	
	public static void main(String args[]){
		DiAwardLevelDao awardLevelDao =  new DiAwardLevelDao() ;
		System.out.println(awardLevelDao.getList().size()) ;
	}

}
