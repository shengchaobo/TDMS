package cn.nit.dao.table2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.List;

import cn.nit.bean.table2.T293_Bean;
import cn.nit.constants.Constants;
import cn.nit.dao.CheckDao;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class T293_Dao {
	
	private String tableName = "T293_UndergraTeaIncome_Finance$" ;
	private String field = "SumIncome,SumUndergraIncome,AllocateFund,NationFund,LocalFund,UndergraTuition,EduReformFund," +
			"SumOtherIncome,JuniorAllocateFund,OtherAllocateFund,OtherNationFund,OtherLocalFund,OtherTuition,GraTuition,JuniorTuition," +
			"NetTeaTuition,Donation,OtherIncome,Time,Note,CheckState";
	private String keyfield = "SeqNumber";
	
	CheckDao checkDao = new CheckDao();
	
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public T293_Bean getYearInfo(String year){
		
		String sql = "select " + " " + keyfield + "," +
		field + " from " + tableName + " where convert(varchar(4),Time,120)=" + year;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T293_Bean> list = null ;
		T293_Bean bean = null;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T293_Bean.class) ;
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
	public boolean save(T293_Bean bean, String year, String fields){
		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year;		
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection() ;
		
		Statement st = null ;
		ResultSet rs = null ;
		List<T293_Bean> list = null ;
		T293_Bean tempBean = null;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T293_Bean.class) ;
			if(list.size() != 0){
				tempBean = list.get(0);
				bean.setSeqNumber(tempBean.getSeqNumber());
				
				double sumIncome = tempBean.getSumIncome();
				double sumUndergraIncome = tempBean.getSumUndergraIncome();
				double allocateFund = tempBean.getAllocateFund();
				double sumOtherIncome = tempBean.getSumOtherIncome();
				double otherAllocateFund = tempBean.getOtherAllocateFund();
				double otherTuition = tempBean.getOtherTuition();
				
//				统计本科拨款总额
				if(bean.getNationFund()!=null){
					if(tempBean.getNationFund()==null){
						allocateFund = allocateFund + bean.getNationFund();
					}else{
						allocateFund = allocateFund + (bean.getNationFund()-tempBean.getNationFund());
					}
				}
				if(bean.getLocalFund()!=null){
					if(tempBean.getLocalFund()==null){
						allocateFund = allocateFund + bean.getLocalFund();
					}else{
						allocateFund = allocateFund + (bean.getLocalFund()-tempBean.getLocalFund());
					}
				}
				bean.setAllocateFund(allocateFund);
				
				
//				统计本科生收入总计
				sumUndergraIncome = sumUndergraIncome + allocateFund - tempBean.getAllocateFund();				
				if(bean.getEduReformFund()!= null){
					if(tempBean.getEduReformFund() == null){
						sumUndergraIncome = sumUndergraIncome + bean.getEduReformFund();
					}else{
						sumUndergraIncome = sumUndergraIncome + (bean.getEduReformFund()-tempBean.getEduReformFund());
					}				
				}				
				if(bean.getUndergraTuition()!= null){
					if(tempBean.getUndergraTuition() == null){
						sumUndergraIncome = sumUndergraIncome + bean.getUndergraTuition();
					}else{
						sumUndergraIncome = sumUndergraIncome + (bean.getUndergraTuition()-tempBean.getUndergraTuition());
					}	
				}
				bean.setSumUndergraIncome(sumUndergraIncome);
				
//				其他拨款总数				
				if(bean.getOtherNationFund()!= null){
					if(tempBean.getOtherNationFund() == null){
						otherAllocateFund = otherAllocateFund + bean.getOtherNationFund();
					}else{
						otherAllocateFund = otherAllocateFund + (bean.getOtherNationFund()-tempBean.getOtherNationFund());
					}	
				}
				if(bean.getOtherLocalFund()!=null){
					if(tempBean.getOtherNationFund() == null){
						otherAllocateFund = otherAllocateFund + bean.getOtherLocalFund();
					}else{
						otherAllocateFund = otherAllocateFund + (bean.getOtherLocalFund()-tempBean.getOtherLocalFund());
					}
				}
				bean.setOtherAllocateFund(otherAllocateFund);
				
//				其他学费总额
				if(bean.getGraTuition()!=null){
					if(tempBean.getGraTuition() == null){
						otherTuition = otherTuition + bean.getGraTuition();
					}else{
						otherTuition = otherTuition + (bean.getGraTuition()-tempBean.getGraTuition());
					}
				}
				if(bean.getNetTeaTuition()!=null){
					if(tempBean.getNetTeaTuition() == null){
						otherTuition = otherTuition + bean.getNetTeaTuition();
					}else{
						otherTuition = otherTuition + (bean.getNetTeaTuition()-tempBean.getNetTeaTuition());
					}
				}
				if(bean.getJuniorTuition()!=null){
					if(tempBean.getJuniorTuition() == null){
						otherTuition = otherTuition + bean.getJuniorTuition();
					}else{
						otherTuition = otherTuition + (bean.getJuniorTuition()-tempBean.getJuniorTuition());
					}
				}
				bean.setOtherTuition(otherTuition);
				
//				其他收入总额
				sumOtherIncome = sumOtherIncome + otherTuition + otherAllocateFund - tempBean.getOtherTuition() - tempBean.getOtherAllocateFund();
				if(bean.getJuniorAllocateFund()!= null){
					if(tempBean.getJuniorAllocateFund() == null){
						sumOtherIncome = sumOtherIncome + bean.getJuniorAllocateFund();
					}else{
						sumOtherIncome = sumOtherIncome + (bean.getJuniorAllocateFund()-tempBean.getJuniorAllocateFund());
					}	
				}	
/*				if(bean.getDonation()!= null){
					if(tempBean.getDonation() == null){
						sumOtherIncome = sumOtherIncome + bean.getDonation();
					}else{
						sumOtherIncome = sumOtherIncome + (bean.getDonation()-tempBean.getDonation());
					}
				}*/
				if(bean.getOtherIncome()!= null){
					if(tempBean.getOtherIncome() == null){
						sumOtherIncome = sumOtherIncome + bean.getOtherIncome();
					}else{
						sumOtherIncome = sumOtherIncome + (bean.getOtherIncome()-tempBean.getOtherIncome());
					}
				}
				bean.setSumOtherIncome(sumOtherIncome);

//				总收入
				sumIncome = sumUndergraIncome + sumOtherIncome;
				bean.setSumIncome(sumIncome);			
				
				String tempfields ="";
				if(tempBean.getCheckState() == Constants.WAIT_CHECK){
					tempfields = fields + ",SumIncome,SumUndergraIncome,AllocateFund,SumOtherIncome,OtherAllocateFund,OtherTuition";
					flag = DAOUtil.update(bean, tableName, keyfield, tempfields, conn) ;
				}
				if(tempBean.getCheckState() == Constants.NOPASS_CHECK){
					tempfields = fields + ",SumIncome,SumUndergraIncome,AllocateFund,SumOtherIncome,OtherAllocateFund,OtherTuition,CheckState";
					bean.setCheckState(Constants.WAIT_CHECK);
					Calendar cal = Calendar.getInstance();
					cal.setTime(tempBean.getTime());
					int year1 = cal.get(Calendar.YEAR); 
					checkDao.delete("T293", year1 ) ;
					flag = DAOUtil.update(bean, tableName, keyfield, tempfields, conn) ;
				}
			}else{
				bean.setTime(TimeUtil.changeDateY(year));
				bean.setCheckState(Constants.WAIT_CHECK);
				double sumIncome = 0;
				double sumUndergraIncome = 0;
				double allocateFund = 0;
				double sumOtherIncome = 0;
				double otherAllocateFund = 0;
				double otherTuition = 0;
				
				
//				统计本科拨款总额
				if(bean.getNationFund()!=null){
					allocateFund = allocateFund + bean.getNationFund();
				}				
				if(bean.getLocalFund()!=null){
					allocateFund = allocateFund + bean.getLocalFund();
				}
				bean.setAllocateFund(allocateFund);
				
//				统计本科生收入总计
				sumUndergraIncome = sumUndergraIncome + allocateFund;				
				if(bean.getEduReformFund()!=null){
					sumUndergraIncome = sumUndergraIncome + bean.getEduReformFund();
				}				
				if(bean.getUndergraTuition()!=null){
					sumUndergraIncome = sumUndergraIncome + bean.getUndergraTuition();
				}
				bean.setSumUndergraIncome(sumUndergraIncome);
				
//				其他拨款总数					
				if(bean.getOtherNationFund()!=null){
					otherAllocateFund = otherAllocateFund + bean.getOtherNationFund();
				}
				if(bean.getOtherLocalFund()!=null){
					otherAllocateFund = otherAllocateFund + bean.getOtherLocalFund();
				}
				bean.setOtherAllocateFund(otherAllocateFund);
				
//				其他学费总额
				if(bean.getGraTuition()!=null){
					otherTuition = otherTuition + bean.getGraTuition();
				}
				if(bean.getNetTeaTuition()!=null){
					otherTuition = otherTuition + bean.getNetTeaTuition();
				}
				if(bean.getJuniorTuition()!=null){
					otherTuition = otherTuition + bean.getJuniorTuition();
				}
				bean.setOtherTuition(otherTuition);
				
//				其他收入总额
				sumOtherIncome = otherTuition + otherAllocateFund;
				if(bean.getJuniorAllocateFund()!= null){
					sumOtherIncome = sumOtherIncome + bean.getJuniorAllocateFund();
				}
/*				if(bean.getDonation()!= null){
					sumOtherIncome = sumOtherIncome + bean.getDonation();
				}*/
				if(bean.getOtherIncome()!= null){
					sumOtherIncome = sumOtherIncome + bean.getOtherIncome();
				}
				bean.setSumOtherIncome(sumOtherIncome);

//				总收入
				sumIncome = sumUndergraIncome + sumOtherIncome;
				bean.setSumIncome(sumIncome);
				
				String tempfields = fields + ",SumIncome,SumUndergraIncome,AllocateFund,SumOtherIncome,OtherAllocateFund,OtherTuition,Time,CheckState";
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
			DBConnection.close(st);	;	
			DBConnection.close(conn);
		}
		if (flag == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 更新捐赠收入
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public boolean update(T293_Bean bean, String year){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			String updatefield = "Donation,SumOtherIncome,SumIncome";							
			flag = DAOUtil.update(bean, tableName, keyfield, updatefield, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
		}
		
		return flag ;
	}
	
	
	
	public static void main(String args[]){
		//T23_Dao testDao =  new T23_Dao() ;
	}

}
