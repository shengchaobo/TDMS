package cn.nit.dao.table6;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.table5.T513_Bean;
import cn.nit.bean.table6.T616_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class T616_Dao {
	
	/**  数据库表名  */
	private String tableName = "T616_ForgeinStu_Intra$" ;
	
	/**待统计数据库表名*/
	//private String tableName1="T711_TeaManagerAwardInfo_TeaTea$";
	
	//private String tableName2="T712_TeaManagerPaperInfo_TeaTea$";
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "StuType,SumGraNum,GraOutNum,GraHongNum,GraAoNum,GraTaiNum,SumDegreeNum,DegreeOutNum,DegreeHongNum,DegreeAoNum,DegreeTaiNum,SumAdmisNum," +
			"AdmisHongNum,AdmisAoNum,AdmisOutNum,AdmisTaiNum,SumInSchNum,InSchHongNum,InSchAoNum,InSchTaiNum,InSchOutNum,Time,Note,CheckState" ;

	
    /**
	 * 获得当年数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T616_Bean> getYearInfo(String year){
		
		String sql = "select " + key + "," + field + " from " + tableName 
				+ " where convert(varchar(4),Time,120)=" + year;		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T616_Bean> list = null ;
		//System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T616_Bean.class) ;
			
			//如果当前年表中没有学位列数据，先将学位列数据插入到表中
			if(list.size()==0){

				
				//System.out.println(list);
				
				T616_Bean sumBean = new T616_Bean();
				sumBean.setStuType("总计");
				list.add(0,sumBean);
				
				T616_Bean docBean = new T616_Bean();
				docBean.setStuType("博士");
				list.add(1,docBean);
				
				T616_Bean masBean = new T616_Bean();
				masBean.setStuType("硕士");
				list.add(2,masBean);
				
				T616_Bean underBean = new T616_Bean();
				underBean.setStuType("本科生");
				list.add(3,underBean);
				
				T616_Bean collBean = new T616_Bean();
				collBean.setStuType("专科生");
				list.add(4,collBean);
				
				T616_Bean traBean = new T616_Bean();
				traBean.setStuType("培训生");
				list.add(5,traBean);
				
				for(T616_Bean bean:list){
					bean.setTime(TimeUtil.changeDateY(year));
				}
				//System.out.println(list);
				//插入学位列
				DAOUtil.batchInsert(list, tableName, field, conn) ;	
				
				
				//再取出来
				Connection conn1 = DBConnection.instance.getConnection() ;
				st = conn1.createStatement() ;
				rs = st.executeQuery(sql) ;
				list = DAOUtil.getList(rs, T616_Bean.class) ;
			}
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
	 * 讲数据批量插入616表中
	 * @param list {@linkplain java.util.List<{@link cn.nit.bean.table6.T616_Bean}>}
	 * @return true表示插入成功，false表示插入失败
	 */
	public boolean batchInsert(List<T616_Bean> list,String year){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		StringBuffer sql = new StringBuffer();
		sql.append("select * from "+tableName);
		sql.append("  where convert(varchar(4),Time,120)=" + year);
		Statement st = null ;
		ResultSet rs = null ;
		List<T616_Bean> templist = null ;
		
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			templist = DAOUtil.getList(rs, T616_Bean.class);
			if(templist.size() != 0){
				String delSql = "delete from " + tableName + "  where convert(varchar(4),Time,120)=" + year;
				int delflag = st.executeUpdate(delSql.toString());
				if(delflag > 0 ){
					for(T616_Bean bean:list){
						bean.setTime(TimeUtil.changeDateY(year));
					}
					flag = DAOUtil.batchInsert(list, tableName, field, conn) ;
				}
			}else{
				for(T616_Bean bean:list){
					bean.setTime(TimeUtil.changeDateY(year));
				}
				flag = DAOUtil.batchInsert(list, tableName, field, conn) ;
			}
			
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		
		return flag ;
	}
	
	
    /**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T616_Bean> totalList(String year,int CheckState){

		String sql = "select " + key+ "," +field + " from " + tableName 
				+ " where convert(varchar(4),Time,120)=" + year+" and CheckState="+CheckState;		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T616_Bean> list = null ;
		System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T616_Bean.class) ;
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
	 * 更新数据
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */	
	public boolean update(T616_Bean bean){
		
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
	public T616_Bean findBySeqNum (int seqNum){
		String sql = "select " + key+ "," +field + " from " + tableName 
		+ " where SeqNumber=" + seqNum;		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T616_Bean> list = null ;
		T616_Bean bean = null;
		System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T616_Bean.class) ;
			bean = list.get(0);		
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		return bean ;
	}
	
	/**
	 * 找当年总计bean
	 * @param 
	 * @return
	 */
	public T616_Bean findSumBean(String name, String year){
		String sql = "select " + key+ "," +field + " from " + tableName 
		+ " where convert(varchar(4),Time,120)=" + year + " and StuType="+ "'" + name + "'";		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T616_Bean> list = null ;
		T616_Bean bean = null;
		System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T616_Bean.class) ;
			bean = list.get(0);

		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		return bean ;
	}
	
	public boolean updateCheck(String year, String StuType, int checkState){
		
		int flag ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		String sql = "update " + tableName + " set CheckState=" + checkState +
		" where stuType='" + StuType + "' and convert(varchar(4),Time,120)=" + year;			
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

}

