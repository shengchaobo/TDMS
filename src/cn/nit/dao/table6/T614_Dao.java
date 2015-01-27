package cn.nit.dao.table6;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import cn.nit.bean.table6.T614_Bean;
import cn.nit.constants.Constants;
import cn.nit.dao.CheckDao;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class T614_Dao {

	/** 数据库表名 */
	private String tableName = "T614_ContinueEduStuNumInfo_TAEF$";

	/** 数据自增长字段的主键，必须为自增长字段 */
	private String key = "SeqNumber";

	/** 数据库表中除了自增长字段的所有字段 */
	private String field = "PreppyLastYearNum,PreppyThisYearNum,AdvStuLastYearNum,AdvStuThisYearNum," +
			"AdultLastYearNum,AdultThisYearNum,NightUniLastYearNum,NightUniThisYearNum,CorrespdCoLastYearNum,CorrespdThisYearNum," +
			"NetStuLastYearNum,NetStuThisYearNum,SelfStudyLastYearNum,SelfStudyThisYearNum,Time,Note,CheckState";

	
	private String fieldShow = "SeqNumber,PreppyLastYearNum,PreppyThisYearNum,AdvStuLastYearNum,AdvStuThisYearNum," +
			"AdultLastYearNum,AdultThisYearNum,NightUniLastYearNum,NightUniThisYearNum,CorrespdCoLastYearNum,CorrespdThisYearNum," +
			"NetStuLastYearNum,NetStuThisYearNum,SelfStudyLastYearNum,SelfStudyThisYearNum,Time,Note,CheckState";
//
	CheckDao checkDao = new CheckDao();
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public T614_Bean getYearInfo(String year){
		
		String sql = "select " + " " + key + "," +
		field + " from " + tableName + " where convert(varchar(4),Time,120)=" + year;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T614_Bean> list = null ;
		T614_Bean bean = null;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T614_Bean.class) ;
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
	public boolean save(T614_Bean bean, String year, String fields){
		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year;		
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection() ;
		
		Statement st = null ;
		ResultSet rs = null ;
		List<T614_Bean> list = null ;
		T614_Bean tempBean = null;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T614_Bean.class) ;
			if(list.size() != 0){
				tempBean = list.get(0);
				bean.setSeqNumber(tempBean.getSeqNumber());
				String tempfields = "";
				if(tempBean.getCheckState() == Constants.WAIT_CHECK){
					tempfields=fields;
				}
				if(tempBean.getCheckState() == Constants.NOPASS_CHECK){
					tempfields = fields + ",CheckState";
					bean.setCheckState(Constants.WAIT_CHECK);
					Calendar cal = Calendar.getInstance();
					cal.setTime(tempBean.getTime());
					int year1 = cal.get(Calendar.YEAR); 
					System.out.println("+++++++++++++++++++++++");
					checkDao.delete("T614", year1 ) ;
				}
				
				flag = DAOUtil.update(bean, tableName, key, fields, conn) ;
			}else{
				bean.setCheckState(Constants.WAIT_CHECK);
				bean.setTime(TimeUtil.changeDateY(year));
				String tempfields = fields + ",Time,CheckState";
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
	
	/**
	 * 更新某条数据的审核状态
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */	
	public boolean updateCheck(String year, int checkState){
		
		int flag ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		String sql = "update " + tableName + " set CheckState=" + checkState +
		" where convert(varchar(4),Time,120)=" + year;			
		//System.out.println(sql);
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
	//设置审核的状态为1：即未审核状态
	public boolean updatCheck()
	{
		int flag = 0;
		StringBuffer sql = new StringBuffer() ;
		sql.append("update " + tableName+" set CheckState ="+Constants.WAIT_CHECK) ;
//		sql.append(" where " + key + " in " + ids) ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		
		try
		{
			st = conn.createStatement();
			flag = st.executeUpdate(sql.toString());			
		}catch(Exception e){
			e.printStackTrace();
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
	
	
	public static void main(String args[]){
	
		T614_Dao testDao =  new T614_Dao() ;
		boolean flag= testDao.updatCheck();
		System.out.println(flag);
	}
	
}
