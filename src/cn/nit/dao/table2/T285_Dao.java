package cn.nit.dao.table2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.table2.T285_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class T285_Dao {
	
	/**  数据库表名  */
	private String tableName = "T285_ResEqu_EQU$" ;
	
	/**待统计数据库表名*/
	//private String tableName1="T711_TeaManagerAwardInfo_TeaTea$";
	
	//private String tableName2="T712_TeaManagerPaperInfo_TeaTea$";
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "TeaUnit,UnitID,SumEquNum,AboveTenEquNum,SumEquAsset,NewAddAsset,AboveTenEquAsset,Time,Note,checkState" ;

	
    /**
	 * 获得当年数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T285_Bean> getYearInfo(String year){
		
		String sql = "select " + key + "," + field + " from " + tableName 
				+ " where convert(varchar(4),Time,120)=" + year;		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T285_Bean> list = null ;
		System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T285_Bean.class) ;
			
			//如果当前年表中没有单位列数据，先将单位列数据插入到表中
			if(list.size() == 0){
				String sql1 = "select " + key + "," + "UnitName AS TeaUnit,DiDepartment.UnitID,SumEquNum,AboveTenEquNum," +
						"SumEquAsset,NewAddAsset,AboveTenEquAsset,Time,checkState," + tableName + ".Note" +
						" from DiDepartment" +
						" left join " + tableName + " on DiDepartment.UnitID = " + tableName + ".UnitID " +
						" and convert(varchar(4),Time,120)=" + "'" + year + "'" +
						" where DiDepartment.UnitID like '30%' ";
				System.out.println(sql1);
				rs = st.executeQuery(sql1) ;
				list = DAOUtil.getList(rs, T285_Bean.class) ;
				
				T285_Bean sumBean = new T285_Bean();
				sumBean.setTeaUnit("全校合计：");
				list.add(0,sumBean);
				
				for(T285_Bean bean:list){
					bean.setTime(TimeUtil.changeDateY(year));
				}
				
				//插入单位列
				DAOUtil.batchInsert(list, tableName, field, conn) ;	
				
				//再取出来
				Connection conn1 = DBConnection.instance.getConnection() ;
				st = conn1.createStatement() ;
				rs = st.executeQuery(sql) ;
				list = DAOUtil.getList(rs, T285_Bean.class) ;
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
		
		return list ;
	}
	
	
    /**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T285_Bean> totalList(String year){
		
		String sql = "select " + key+ "," +field + " from " + tableName 
				+ " where convert(varchar(4),Time,120)=" + year;		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T285_Bean> list = null ;
		System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T285_Bean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
		
		return list ;
	}
	
	/**
	 * 更新数据
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */	
	public boolean update(T285_Bean bean){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{			
			flag = DAOUtil.update(bean, tableName, key, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
		}
		
		return flag ;
	}	
	
	/**
	 * 根据seqNumber找相应bean
	 * @param 
	 * @return
	 */
	public T285_Bean findBySeqNum (int seqNum){
		String sql = "select " + key+ "," +field + " from " + tableName 
		+ " where SeqNumber=" + seqNum;		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T285_Bean> list = null ;
		T285_Bean bean = null;
		System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T285_Bean.class) ;
			bean = list.get(0);		
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
		
		return bean ;
	}
	
	/**
	 * 找当年总计bean
	 * @param 
	 * @return
	 */
	public T285_Bean findSumBean(String name, String year){
		String sql = "select " + key+ "," +field + " from " + tableName 
		+ " where convert(varchar(4),Time,120)=" + year + " and TeaUnit="+ "'" + name + "'";		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T285_Bean> list = null ;
		T285_Bean bean = null;
		System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T285_Bean.class) ;
			bean = list.get(0);

		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
		
		return bean ;
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
		ResultSet rs = null ;
		String sql = "update " + tableName + " set CheckState=" + checkState +
		" where TeaUnit='" + unitName + "' and convert(varchar(4),Time,120)=" + year;			
		System.out.println(sql);
		try{			
			st = conn.createStatement();
			flag = st.executeUpdate(sql);					
		}catch(Exception e){
			e.printStackTrace() ;
			return false;
		}finally{
			DBConnection.close(conn) ;
		}
		
		if (flag == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public static void main(String arg[]){
		//T285_DAO t=new T285_DAO();
	   // List<T285_Bean> list =t.getData("2014");
		//System.out.println(list.size());
	}
}
