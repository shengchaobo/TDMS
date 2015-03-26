package cn.nit.dao.di;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.RoleBean;
import cn.nit.bean.di.DiTableMessageBean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class DiTableMessageDAO {
	
	private String tablename="DiTableMessage";
	
	private String field="Tname,Tid,Ttag,Tparent,TFieldFlag";
	
	/**
	 * 获取所有的表名
	 * @return
	 */
	public List<DiTableMessageBean> getlist(String roleID){
		
		List<DiTableMessageBean> list=null;
		StringBuffer sql=new StringBuffer();
		sql.append("select " + field);
		sql.append(" from " + tablename );
		sql.append(" where Ttag=0 and Tparent like '%" + roleID + "%'" );
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st=null;
		ResultSet rs=null;
		System.out.println(sql);
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql.toString());
			list = DAOUtil.getList(rs, DiTableMessageBean.class) ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		return list;
		
	}
	
	
	/**
	 * 获取某表的所有字段名
	 * @return
	 */
	public List<DiTableMessageBean> getFieldlist(String name){
		
		List<DiTableMessageBean> list=null;
		StringBuffer sql=new StringBuffer();
		sql.append("select " + field);
		sql.append(" from " + tablename );
		sql.append(" where Ttag=1 and Tparent ='" + name + "'" );
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st=null;
		ResultSet rs=null;
		System.out.println(sql);
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql.toString());
			list = DAOUtil.getList(rs, DiTableMessageBean.class) ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		return list;
		
	}
}
