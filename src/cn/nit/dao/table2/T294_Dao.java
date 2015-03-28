package cn.nit.dao.table2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.nit.bean.table2.T285_Bean;
import cn.nit.bean.table2.T294_Bean;
import cn.nit.constants.Constants;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class T294_Dao {
	
	/**  数据库表名  */
	private String tableName = "T294_SocialDonaInfo_Finance$" ;
	
	/**待统计数据库表名*/
	//private String tableName1="T711_TeaManagerAwardInfo_TeaTea$";
	
	//private String tableName2="T712_TeaManagerPaperInfo_TeaTea$";
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "DonaName,Type,DonaMoney,Time,Note,CheckState" ;

	
    /**
	 * 获得当年数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T294_Bean> getYearInfo(String year){
		
		String sql = "select " + key+ "," +field + " from " + tableName 
				+ " where convert(varchar(4),Time,120)=" + year;		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T294_Bean> list = null ;
		//System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T294_Bean.class) ;
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
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T294_Bean> totalList(String year){
		
		String sql = "select " + key+ "," +field + " from " + tableName 
				+ " where convert(varchar(4),Time,120)=" + year;		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T294_Bean> list = null ;
		System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T294_Bean.class) ;
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
	public boolean insert(T294_Bean bean, String year){
		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year + " and DonaName=" + "'捐赠金额总计'" + ";";		
		boolean flag = false;
		boolean flag1;
		boolean flag2;
		Connection conn = DBConnection.instance.getConnection() ;		
		Statement st = null ;
		ResultSet rs = null ;
		List<T294_Bean> templist = null ;
		System.out.println(sql);
		String tempfield = "DonaName,Type,DonaMoney,Note";
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			templist = DAOUtil.getList(rs, T294_Bean.class) ;
			if(templist.size() == 0){
				bean.setTime(TimeUtil.changeDateY(year));
				T294_Bean T294_Bean = new T294_Bean();			
				T294_Bean.setDonaName("捐赠金额总计");
				T294_Bean.setType("");
				T294_Bean.setDonaMoney(bean.getDonaMoney());
				T294_Bean.setTime(TimeUtil.changeDateY(year));
				T294_Bean.setNote("");
				T294_Bean.setCheckState(Constants.WAIT_CHECK);
				flag1 = DAOUtil.insert(T294_Bean, tableName, field, conn);
				//重新打开数据库连接
				Connection conn1 = DBConnection.instance.getConnection() ;	
				flag2 = DAOUtil.insert(bean, tableName, field, conn1);				
			}else{
				bean.setTime(TimeUtil.changeDateY(year));
				T294_Bean T294_Bean = templist.get(0);
				T294_Bean.setDonaMoney(T294_Bean.getDonaMoney()+bean.getDonaMoney());
				flag1 = DAOUtil.insert(bean, tableName, field, conn);
				//重新打开数据库连接
				Connection conn1 = DBConnection.instance.getConnection() ;	
				flag2 = DAOUtil.update(T294_Bean, tableName, key, tempfield, conn1);
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return false ;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		if(flag1==true&&flag2==true){
			flag = true;
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
	public boolean deleteByIds(String ids, String year) {

		int flag = 0;
		boolean flag1 = false ;
		StringBuffer sql = new StringBuffer();
		sql.append("delete from " + tableName);
		sql.append(" where " + key + " in " + ids);
		
		
		String sql0 = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year + " and DonaName=" + "'捐赠金额总计'" + ";";		
		String sql1 = "select sum(DonaMoney) AS SumDelMoney from " + tableName + " where " + key + " in " + ids;
		
		Connection conn = DBConnection.instance.getConnection();
		Connection conn1 = null;
		Statement st = null ;
		ResultSet rs0 = null ;
		ResultSet rs1 = null ;
		List<T294_Bean> templist = null ;
		
		try {
			st = conn.createStatement();
			rs0 = st.executeQuery(sql0);
			templist = DAOUtil.getList(rs0, T294_Bean.class) ;
			T294_Bean bean = templist.get(0);
			
			rs1 = st.executeQuery(sql1);
			while(rs1.next()) {
				double sumDelMoney = rs1.getDouble("SumDelMoney");
				bean.setDonaMoney(bean.getDonaMoney()-sumDelMoney);
			}
			
			if(bean.getDonaMoney() == 0){
				String sql2 = "delete from " + tableName + " where SeqNumber=" + bean.getSeqNumber();		
				int flag3 = st.executeUpdate(sql2);
				if(flag3 > 0){
					flag1 = true;
				}else{
					flag1 = false;
				}
			}else{
				if(bean.getCheckState() == Constants.NOPASS_CHECK){
					bean.setCheckState(Constants.WAIT_CHECK);
				}				
				flag1 = DAOUtil.update(bean, tableName, key, "DonaMoney,CheckState", conn);
			}

			if(flag1){
				//重新打开数据库连接
				conn1 = DBConnection.instance.getConnection() ;	
				Statement st1 = conn1.createStatement();
				try{						
				flag = st1.executeUpdate(sql.toString());
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					DBConnection.close(st1);
					DBConnection.close(conn1);
				}
			}else{
				flag = 0;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			DBConnection.close(rs0);
			DBConnection.close(rs1);
			DBConnection.close(st);	
			DBConnection.close(conn);
			DBConnection.close(conn1);
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
	public int update(T294_Bean bean, String year){
		
		String sql0 = "select * from " + tableName + " where SeqNumber=" + bean.getSeqNumber();
		String sql1 = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year + " and DonaName=" + "'捐赠金额总计'" + ";";		
		int flag = 0;
		boolean flag0 = false;
		boolean flag1 = false;
		Connection conn = DBConnection.instance.getConnection() ;	
		
		Statement st = null ;
		ResultSet rs = null ;
		Statement st1 = null ;
		ResultSet rs1 = null ;
		List<T294_Bean> templist = null ;
		List<T294_Bean> templist1 = null ;
		String updatefield = "Type,DonaMoney,Note";	
		String updatefield1 = "Type,DonaMoney,Note,CheckState";	
		
		try{
			//求编辑的那条数据
			st = conn.createStatement() ;
			rs = st.executeQuery(sql0) ;
			templist = DAOUtil.getList(rs, T294_Bean.class) ;			
			T294_Bean tempBean = templist.get(0);

			//求捐赠总计bean			
			st1 = conn.createStatement() ;
			rs1 = st1.executeQuery(sql1) ;
			templist1 = DAOUtil.getList(rs1, T294_Bean.class) ;
			T294_Bean tempBean1 = templist1.get(0);
			
			if(tempBean1.getCheckState() == Constants.NOPASS_CHECK){
				tempBean1.setDonaMoney(tempBean1.getDonaMoney()+(bean.getDonaMoney()-tempBean.getDonaMoney()));
				tempBean1.setCheckState(Constants.WAIT_CHECK);
				flag0 = DAOUtil.update(bean, tableName, key, updatefield, conn) ;
				//重新打开数据库连接
				Connection conn1 = DBConnection.instance.getConnection() ;	
				flag1 = DAOUtil.update(tempBean1, tableName, key, updatefield1, conn1) ;					
				
				if(flag0&&flag1){
					flag = 2;
				}
			}else{
				tempBean1.setDonaMoney(tempBean1.getDonaMoney()+(bean.getDonaMoney()-tempBean.getDonaMoney()));
				flag0 = DAOUtil.update(bean, tableName, key, updatefield, conn) ;
				//重新打开数据库连接
				Connection conn1 = DBConnection.instance.getConnection() ;	
				flag1 = DAOUtil.update(tempBean1, tableName, key, updatefield1, conn1) ;	
				if(flag0&&flag1){
					flag = 1;
				}
			}
													
		}catch(Exception e){
			e.printStackTrace() ;
			return 0;
		}finally{
			DBConnection.close(rs1);
			DBConnection.close(st1);
			DBConnection.close(rs);
			DBConnection.close(st);
			DBConnection.close(conn);
		}
		return flag;
	}
	

	public double getYearSumDona(String year){
		
		String sql = "select DonaMoney from " + tableName + " where convert(varchar(4),Time,120)=" + year + " and DonaName=" + "'捐赠金额总计'" + ";";			
		Connection conn = DBConnection.instance.getConnection();
		Statement st = null;
		ResultSet rs = null;
		double donaMoney = 0.0;
		try{
			
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				donaMoney = rs.getDouble("DonaMoney");
			}
						
		}catch(Exception e){
			e.printStackTrace() ;
			return 0;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);
			DBConnection.close(conn);
		}
		return donaMoney;
	}
	
	/**
	 * 更新某条数据的审核状态
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */	
	public boolean updateCheck(String year, String unitName, int checkState){
		
		int flag ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		String sql = "update " + tableName + " set CheckState=" + checkState +
		" where DonaName='" + unitName + "' and convert(varchar(4),Time,120)=" + year;			
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
	
	public static void main(String arg[]){
		//T294_DAO t=new T294_DAO();
	   // List<T294_Bean> list =t.getData("2014");
		//System.out.println(list.size());
	}
}
