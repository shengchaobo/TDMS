package cn.nit.dao.table2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.List;

import cn.nit.bean.table2.T21_Bean;
import cn.nit.constants.Constants;
import cn.nit.dao.CheckDao;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class T21_Dao {
	
	private String tableName = "T21_OccupyAndCoverArea_Log$" ;
	private String field = "SumArea,SchProArea,GreenArea,NotSchProArea,GreenAreaNotInSch,OnlyUseArea,CoUseArea,SumCoverArea,SchProCovArea," +
			"NotSchProCovArea,OnlyUseCovArea,CoUseCovArea,Time,Note,CheckState";
	private String keyfield = "SeqNumber";
	
	CheckDao checkDao = new CheckDao();
	
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public T21_Bean getYearInfo(String year){
		
		String sql = "select " + " " + keyfield + "," +
		field + " from " + tableName + " where convert(varchar(4),Time,120)=" + year;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T21_Bean> list = null ;
		T21_Bean bean = null;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T21_Bean.class) ;
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
	public boolean save(T21_Bean bean, String year, String fields){
		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year;		
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection() ;
		
		Statement st = null ;
		ResultSet rs = null ;
		List<T21_Bean> list = null ;
		T21_Bean tempBean = null;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T21_Bean.class) ;
			if(list.size() != 0){
				tempBean = list.get(0);
				bean.setSeqNumber(tempBean.getSeqNumber());
				String tempfields = "";
				if(tempBean.getCheckState() == Constants.WAIT_CHECK){
					tempfields = fields + ",SumArea,NotSchProArea,SumCoverArea,NotSchProCovArea";
				}
				if(tempBean.getCheckState() == Constants.NOPASS_CHECK){
					tempfields = fields + ",SumArea,NotSchProArea,SumCoverArea,NotSchProCovArea,CheckState";
					bean.setCheckState(Constants.WAIT_CHECK);
					Calendar cal = Calendar.getInstance();
					cal.setTime(tempBean.getTime());
					int year1 = cal.get(Calendar.YEAR); 
					checkDao.delete("T21", year1 ) ;
				}
												
				double notSchProArea = tempBean.getNotSchProArea();				
				double notSchProCovArea = tempBean.getNotSchProCovArea();
				
				
				if(bean.getOnlyUseArea()!=null){
					if(tempBean.getOnlyUseArea()==null){
						notSchProArea = notSchProArea + bean.getOnlyUseArea();
					}else{
						notSchProArea = notSchProArea + bean.getOnlyUseArea() - tempBean.getOnlyUseArea();
					}
					
				}
				if(bean.getCoUseArea()!=null){
					if(tempBean.getCoUseArea()==null){
						notSchProArea = notSchProArea + bean.getCoUseArea();
					}else{
						notSchProArea = notSchProArea + bean.getCoUseArea() - tempBean.getCoUseArea();
					}					
				}
				bean.setNotSchProArea(notSchProArea);
				
				double sumArea = tempBean.getSumArea();
				sumArea = sumArea + notSchProArea - tempBean.getNotSchProArea();				
				if(bean.getSchProArea()!=null){
					if(tempBean.getSchProArea()==null){
						sumArea = sumArea + bean.getSchProArea();
					}else{				
						sumArea = sumArea + bean.getSchProArea() - tempBean.getSchProArea();
					}
				}
				bean.setSumArea(sumArea);
				
				
				if(bean.getOnlyUseCovArea()!=null){
					if(tempBean.getOnlyUseCovArea()==null){
						notSchProCovArea = notSchProCovArea + bean.getOnlyUseCovArea();
					}else{
						notSchProCovArea = notSchProCovArea + bean.getOnlyUseCovArea() - tempBean.getOnlyUseCovArea();
					}
					
				}				
				if(bean.getCoUseCovArea()!=null){
					if(tempBean.getCoUseCovArea()==null){
						notSchProCovArea = notSchProCovArea + bean.getCoUseCovArea();
					}else{
						notSchProCovArea = notSchProCovArea + bean.getCoUseCovArea() - tempBean.getCoUseCovArea();
					}					
				}
				bean.setNotSchProCovArea(notSchProCovArea);
				
				double sumCoverArea = tempBean.getSumCoverArea();
				sumCoverArea = sumCoverArea + notSchProCovArea - tempBean.getNotSchProCovArea();
				if(bean.getSchProCovArea()!=null){
					if(tempBean.getSchProCovArea()==null){
						sumCoverArea = sumCoverArea + bean.getSchProCovArea();
					}else{			
						sumCoverArea = sumCoverArea + bean.getSchProCovArea() - tempBean.getSchProCovArea();
					}
				}
				bean.setSumCoverArea(sumCoverArea);
				
				flag = DAOUtil.update(bean, tableName, keyfield, tempfields, conn) ;
			}else{
				bean.setTime(TimeUtil.changeDateY(year));
				bean.setCheckState(Constants.WAIT_CHECK);
				
				double sumArea = 0;
				double notSchProArea = 0;
				double sumCoverArea = 0;
				double notSchProCovArea = 0;
				
				
				if(bean.getOnlyUseArea()!=null){
					notSchProArea = notSchProArea + bean.getOnlyUseArea();
				}
				if(bean.getCoUseArea()!=null){
					notSchProArea = notSchProArea + bean.getCoUseArea();
				}
				bean.setNotSchProArea(notSchProArea);
				
				sumArea = sumArea + notSchProArea;				
				if(bean.getSchProArea()!=null){
					sumArea = sumArea + bean.getSchProArea();
				}
				bean.setSumArea(sumArea);
				
				
				if(bean.getOnlyUseCovArea()!=null){
					notSchProCovArea = notSchProCovArea + bean.getOnlyUseCovArea();
				}				
				if(bean.getCoUseCovArea()!=null){
					notSchProCovArea = notSchProCovArea + bean.getCoUseCovArea();
				}
				bean.setNotSchProCovArea(notSchProCovArea);
				
				sumCoverArea = sumCoverArea + notSchProCovArea;
				if(bean.getSchProCovArea()!=null){
					sumCoverArea = sumCoverArea + bean.getSchProCovArea();
				}	
				bean.setSumCoverArea(sumCoverArea);
				
				String tempfields = fields + ",SumArea,NotSchProArea,SumCoverArea,NotSchProCovArea,Time,CheckState";
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
	
	public static void main(String args[]){
		//T21_Dao testDao =  new T21_Dao() ;
	}

}
