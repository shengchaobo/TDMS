package cn.nit.dao.table2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.List;

import cn.nit.bean.table2.T282_Bean;
import cn.nit.constants.Constants;
import cn.nit.dao.CheckDao;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class T282_Dao {
	
	private String tableName = "T282_FixedAsset_EQU$" ;
	private String field = "OnlyEqu,SumFixedAsset,GeneralEqu,Furniture,OtherAsset,Time,Note,CheckState";
	private String keyfield = "SeqNumber";
	
	CheckDao checkDao = new CheckDao();
	
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public T282_Bean getYearInfo(String year){
		
		String sql = "select " + " " + keyfield + "," +
		field + " from " + tableName + " where convert(varchar(4),Time,120)=" + year;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T282_Bean> list = null ;
		T282_Bean bean = null;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T282_Bean.class) ;
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
	public boolean save(T282_Bean bean, String year, String fields){
		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year;		
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection() ;
		
		Statement st = null ;
		ResultSet rs = null ;
		List<T282_Bean> list = null ;
		T282_Bean tempBean = null;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T282_Bean.class) ;
			if(list.size() != 0){
				tempBean = list.get(0);
				bean.setSeqNumber(tempBean.getSeqNumber());
				
				double sum = tempBean.getSumFixedAsset();
				
				if(bean.getOnlyEqu()!=null){
					if(tempBean.getOnlyEqu()==null){
						sum = sum + bean.getOnlyEqu();
					}else{
						sum = sum + (bean.getOnlyEqu()-tempBean.getOnlyEqu());
					}
				}
				
				if(bean.getGeneralEqu()!=null){
					if(tempBean.getGeneralEqu()==null){
						sum = sum + bean.getGeneralEqu();
					}else{
						sum = sum + (bean.getGeneralEqu()-tempBean.getGeneralEqu());
					}
				}
				
				if(bean.getFurniture()!=null){
					if(tempBean.getFurniture()==null){
						sum = sum + bean.getFurniture();
					}else{
						sum = sum + (bean.getFurniture()-tempBean.getFurniture());
					}
				}
				
				if(bean.getOtherAsset()!=null){
					if(tempBean.getOtherAsset()==null){
						sum = sum + bean.getOtherAsset();
					}else{
						sum = sum + (bean.getOtherAsset()-tempBean.getOtherAsset());
					}
				}				
				
				bean.setSumFixedAsset(sum);
				
				String tempfields ="";
				if(tempBean.getCheckState() == Constants.WAIT_CHECK){
					tempfields = fields + ",SumFixedAsset";
					flag = DAOUtil.update(bean, tableName, keyfield, tempfields, conn) ;
				}
				if(tempBean.getCheckState() == Constants.NOPASS_CHECK){
					tempfields = fields + ",SumFixedAsset,CheckState";
					bean.setCheckState(Constants.WAIT_CHECK);
					Calendar cal = Calendar.getInstance();
					cal.setTime(tempBean.getTime());
					int year1 = cal.get(Calendar.YEAR); 
					checkDao.delete("T282", year1 ) ;
					flag = DAOUtil.update(bean, tableName, keyfield, tempfields, conn) ;
				}
			}else{
				bean.setTime(TimeUtil.changeDateY(year));
				bean.setCheckState(Constants.WAIT_CHECK);
				double sum = 0;
				if(bean.getOnlyEqu()!=null){
					sum = sum + bean.getOnlyEqu();
				}
				
				if(bean.getGeneralEqu()!=null){
					sum = sum + bean.getGeneralEqu();
				}
				
				if(bean.getFurniture()!=null){
					sum = sum + bean.getFurniture();
				}
				
				if(bean.getOtherAsset()!=null){
					sum = sum + bean.getOtherAsset();
				}	
				
				bean.setSumFixedAsset(sum);
				
				String tempfields = fields + ",SumFixedAsset,Time,CheckState";
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
		//T23_Dao testDao =  new T23_Dao() ;
	}

}
