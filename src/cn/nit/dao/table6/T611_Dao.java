package cn.nit.dao.table6;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import net.sf.json.JSON;



import cn.nit.bean.table2.T21_Bean;
import cn.nit.bean.table6.T611_Bean;
import cn.nit.bean.table6.T621_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class T611_Dao {

	/** 数据库表名 */
	private String tableName = "T611_UnderGraStuNuminfo_Tea$";

	/** 数据自增长字段的主键，必须为自增长字段 */
	private String key = "SeqNumber";

	/** 数据库表中除了自增长字段的所有字段 */
	private String field = "StuInfoBaseUrl,LastYearSumNum,ThisYearSumNum,UndergraLastYearNum,UndergraThisYearNum,JuniorLastYearNum,JuniorThisYearNum,Time,Note";

	
	private String fieldShow = "SeqNumber,StuInfoBaseUrl,LastYearSumNum,ThisYearSumNum,UndergraLastYearNum,UndergraThisYearNum,JuniorLastYearNum,JuniorThisYearNum,Time,Note";

	/* ,FillTeaID,FillUnitID,audit */

	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public T611_Bean getYearInfo(String year){
		
		String sql = "select " + " " + key + "," +
		field + " from " + tableName + " where convert(varchar(4),Time,120)=" + year;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T611_Bean> list = null ;
		T611_Bean bean = null;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T611_Bean.class) ;
			if(list.size() != 0){
				bean = list.get(0);
			}
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
	 * 保存数据
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */
	public boolean save(T611_Bean bean, String year, String fields){
		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year;		
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection() ;
		
		Statement st = null ;
		ResultSet rs = null ;
		List<T611_Bean> list = null ;
		T611_Bean tempBean = null;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T611_Bean.class) ;
			if(list.size() != 0){
				tempBean = list.get(0);
				bean.setSeqNumber(tempBean.getSeqNumber());
				flag = DAOUtil.update(bean, tableName, key, fields, conn) ;
			}else{
				bean.setTime(TimeUtil.changeDateY(year));
				String tempfields = fields + ",Time";
				flag = DAOUtil.insert(bean, tableName, tempfields, conn) ;
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return false ;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
				
		return flag ;
	}

	public static void main(String args[]) {

		T611_Dao UnderGraStuNuminfoDao = new T611_Dao();
		T611_Bean UnderGraStuNuminfo = new T611_Bean();
//		 UnderGraStuNuminfo.setSeqNumber(1);
		//		
		UnderGraStuNuminfo.setStuInfoBaseUrl("test//123.456.789");
		UnderGraStuNuminfo.setLastYearSumNum(18782);
		UnderGraStuNuminfo.setThisYearSumNum(19018);
		UnderGraStuNuminfo.setUndergraLastYearNum(13038);
		UnderGraStuNuminfo.setUndergraThisYearNum(13775);
		UnderGraStuNuminfo.setJuniorLastYearNum(5744);
		UnderGraStuNuminfo.setJuniorThisYearNum(5243);
		
		UnderGraStuNuminfo.setTime(new Date());
		UnderGraStuNuminfo.setNote("无");
//		//		
//		UnderGraStuNuminfoDao.insert(UnderGraStuNuminfo);
		//		
		//	
		//		
		// //
		// System.out.println(underCSBaseTeaDao.auditingData("audit='1'",null,2,10).size())
		// ;
		// // System.out.println(UnderGraStuNuminfoDao.update(UnderGraStuNuminfo)) ;
//		 System.out.println(UnderGraStuNuminfoDao.deleteItemsByIds("(8)")) ;

		System.out.println("success!!");
	}







}
