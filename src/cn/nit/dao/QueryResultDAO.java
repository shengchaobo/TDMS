package cn.nit.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.nit.bean.QueryConditionsBean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.bean.table7.T711_Bean;
import cn.nit.util.DAOUtil;

public class QueryResultDAO {
    private QueryConditionsBean queryConditionsBean=new QueryConditionsBean();
    
    
	@SuppressWarnings("unchecked")
	public  <T> List<T> auditingData(String tablename,QueryConditionsBean tt){
		QueryResultDAO q=new QueryResultDAO();
		String bean=q.getPojo(tablename);	
		System.out.println(bean);
		String tableN=null;
		if(bean.charAt(1)=='1'){
			 tableN="table1";
		}
		if(bean.charAt(1)=='2'){
			 tableN="table2";
		}
		if(bean.charAt(1)=='3'){
			 tableN="table3";
		}
		if(bean.charAt(1)=='4'){
			 tableN="table4";
		}
		if(bean.charAt(1)=='5'){
			 tableN="table5";
		}
		if(bean.charAt(1)=='6'){
			 tableN="table6";
		}
		if(bean.charAt(1)=='7'){
			 tableN="table7";
		}
		
		
		System.out.println(tt.getParamValue());
		StringBuffer sql=new StringBuffer();
		List<T> list= new ArrayList<T>();
		sql.append("select * ");
		sql.append("  from   " +"  "+ tablename);
		sql.append("  where  "   +" "+ tt.getLeftJoin() +" "+ tt.getFieldName() + " "+  tt.getLogicRelation() +  " "+ "'" + tt.getParamValue() + "'" +" "+ tt.getRightJoin() + 
				" "+   tt.getJoinRelation() + " "+  tt.getLeftJoin1() +" "+  tt.getFieldName1() +" "+  tt.getLogicRelation1() + " "+ "'" + tt.getParamValue1() + "'" + " "+ tt.getRightJoin1());
		
		System.out.println(sql.toString());
		Statement st=null;
		ResultSet rs=null;
		
		Connection conn=DBConnection.instance.getConnection();
		try {
			st=conn.createStatement();		
			System.out.println(123);
			rs=st.executeQuery(sql.toString());
			System.out.println(456);	    
			list=(List<T>) DAOUtil.getList(rs,Class.forName("cn.nit.bean."+tableN+"."+ bean));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return list;
	}
	
	public String getPojo(String tablename){
		String po=null;
		StringBuffer sql=new StringBuffer();
		sql.append("select distinct OPPojo");
		sql.append("  from DiMapField ");
		sql.append("  where OPTableName= " + "'"+ tablename +"'");
		System.out.println(sql.toString());
		Statement st=null;
		ResultSet rs=null;	
		Connection conn=DBConnection.instance.getConnection();
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql.toString());	

			while(rs.next()){
			System.out.println(rs.getString(1));
			 po=rs.getString(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return po;
	}
   
	public QueryConditionsBean getQueryConditionsBean() {
		return queryConditionsBean;
	}


	public void setQueryConditionsBean(QueryConditionsBean queryConditionsBean) {
		this.queryConditionsBean = queryConditionsBean;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
