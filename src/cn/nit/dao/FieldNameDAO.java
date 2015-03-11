package cn.nit.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.junit.runner.Request;

import cn.nit.bean.di.DiFieldNameBean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class FieldNameDAO {
	
	public  List<DiFieldNameBean> getlist(String tablename){
		List<DiFieldNameBean> list= new ArrayList<DiFieldNameBean>();
		StringBuffer sql=new StringBuffer();
		sql.append("select EFieldName,CFieldName from DiMapField" + 
		" where OPTableName=");
		sql.append("'"+ tablename + "'");
		Connection conn=DBConnection.instance.getConnection();
		Statement st=null;
		ResultSet rs=null;	
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql.toString());	
			list=DAOUtil.getList(rs, DiFieldNameBean.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;
		}
		return list;
	}
	
	public static void main(String[] args){
	}
}


