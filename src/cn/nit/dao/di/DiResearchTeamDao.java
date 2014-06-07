package cn.nit.dao.di;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.dbconnection.DBConnection;
import cn.nit.bean.di.DiResearchTeamBean;
import cn.nit.util.DAOUtil;

public class DiResearchTeamDao {
	
	private String tableName = "DiResearchTeam" ;
	
	private String field = "IndexID,ResearchTeam" ;

	
	/**
	 * 获取DiResearchTeam字典表的所有数�?
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */	
	public List<DiResearchTeamBean> getList(){
		
		StringBuffer sql = new StringBuffer() ;
		sql.append("select " + field + " from " + tableName) ;
		List<DiResearchTeamBean> list = null ;
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql.toString()) ;
			list = DAOUtil.getList(rs, DiResearchTeamBean.class) ;
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
	 * @param DiResearchTeam
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */
	public boolean insert(DiResearchTeamBean researchTeam){
		
		Connection conn = DBConnection.instance.getConnection() ;
		return DAOUtil.insert(researchTeam, tableName, field, conn) ;
	}
	
	public static void main(String args[]){
		DiResearchTeamDao researchTeamDao =  new DiResearchTeamDao() ;
		System.out.println(researchTeamDao.getList().size()) ;
	}

}
