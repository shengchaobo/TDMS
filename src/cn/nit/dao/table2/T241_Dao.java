package cn.nit.dao.table2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.List;

import cn.nit.bean.table2.T241_Bean;
import cn.nit.constants.Constants;
import cn.nit.dao.CheckDao;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class T241_Dao {
	
	private String tableName = "T241_BookNum_Lib$" ;
	private String field = "PaperBookNum,PaperJonalNum,PaperJonalType,DigBookType,ChiDigBookType,ForDigBookType,DigBookSize,DigJonalType,DatabaseNum,OtherDatabase,Time,Note,CheckState";
	private String keyfield = "SeqNumber";
	
	CheckDao checkDao = new CheckDao();
	
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public T241_Bean getYearInfo(String year){
		
		String sql = "select " + " " + keyfield + "," +
		field + " from " + tableName + " where convert(varchar(4),Time,120)=" + year;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T241_Bean> list = null ;
		T241_Bean bean = null;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T241_Bean.class) ;
			if(list.size() != 0){
				bean = list.get(0);
			}
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
	 * 保存数据
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */
	public boolean save(T241_Bean bean, String year, String fields){
		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year;		
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection() ;
		
		Statement st = null ;
		ResultSet rs = null ;
		List<T241_Bean> list = null ;
		T241_Bean tempBean = null;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T241_Bean.class) ;
			if(list.size() != 0){
				tempBean = list.get(0);
				bean.setSeqNumber(tempBean.getSeqNumber());
				
				String tempfields = "";
				if(tempBean.getCheckState() == Constants.WAIT_CHECK){
					tempfields = fields + ",DigBookType";
				}
				if(tempBean.getCheckState() == Constants.NOPASS_CHECK){
					tempfields = fields + ",DigBookType,CheckState";
					bean.setCheckState(Constants.WAIT_CHECK);
					Calendar cal = Calendar.getInstance();
					cal.setTime(tempBean.getTime());
					int year1 = cal.get(Calendar.YEAR); 
					checkDao.delete("T241", year1 ) ;
				}
				
				int digBookType = tempBean.getDigBookType();
				
				if(bean.getChiDigBookType()!=null){
					if(tempBean.getChiDigBookType()==null){
						digBookType = digBookType + bean.getChiDigBookType();
					}else{
						digBookType = digBookType + bean.getChiDigBookType() - tempBean.getChiDigBookType();
					}
				}
				
				if(bean.getForDigBookType()!=null){
					if(tempBean.getChiDigBookType()==null){
						digBookType = digBookType + bean.getForDigBookType();
					}else{
						digBookType = digBookType + bean.getForDigBookType() - tempBean.getForDigBookType();
					}
				}
				
				bean.setDigBookType(digBookType);

				
				flag = DAOUtil.update(bean, tableName, keyfield, tempfields, conn) ;
				
			}else{
				bean.setTime(TimeUtil.changeDateY(year));
				bean.setCheckState(Constants.WAIT_CHECK);
				
				int digBookType = 0;

				if(bean.getChiDigBookType()!=null){
					digBookType = digBookType + bean.getChiDigBookType();
				}
				
				if(bean.getForDigBookType()!=null){
					digBookType = digBookType + bean.getForDigBookType();
				}
				bean.setDigBookType(digBookType);
				
				String tempfields = fields + ",DigBookType,Time,CheckState";
				flag = DAOUtil.insert(bean, tableName, tempfields, conn) ;
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return false ;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);
			DBConnection.close(conn);
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
			DBConnection.close(st);
			DBConnection.close(conn);
		}
		if (flag == 0) {
			return false;
		} else {
			return true;
		}
	}
	
		
	public static void main(String args[]){
		//T241_Dao testDao =  new T241_Dao() ;
	}

}
