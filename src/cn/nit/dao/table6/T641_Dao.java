package cn.nit.dao.table6;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.json.JSON;

import cn.nit.bean.table6.T621_Bean;
import cn.nit.bean.table6.T622_Bean;
import cn.nit.bean.table6.T641_Bean;
import cn.nit.constants.Constants;
import cn.nit.dao.CheckDao;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class T641_Dao {

	/** 数据库表名 */
	private String tableName = "T641_ScholarLoanSubsidy_Stu$";

	/** 数据自增长字段的主键，必须为自增长字段 */
	private String key = "SeqNumber";

	/** 数据库表中除了自增长字段的所有字段 */
	private String field = "SumAidFund,SumAidNum,GovAidFund,GovAidNum,SocialAidFund,SocialAidNum,SchAidFund," +
			"SchAidNum,NationAidFund,NationAidNum,WorkStudyFund,WorkStudyNum,TuitionWaiberFund,TuitionWaiberNum," +
			"TempFund,TempNum,Time,Note,CheckState";

	CheckDao checkDao = new CheckDao();
	
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public T641_Bean getYearInfo(String year){
		
		String sql = "select " + " " + key + "," +
		field + " from " + tableName + " where convert(varchar(4),Time,120)=" + year;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T641_Bean> list = null ;
		T641_Bean bean = null;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T641_Bean.class) ;
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
	public boolean save(T641_Bean bean, String year, String fields){
		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year;		
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection() ;
		
		Statement st = null ;
		ResultSet rs = null ;
		List<T641_Bean> list = null ;
		T641_Bean tempBean = null;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T641_Bean.class) ;
			if(list.size() != 0){
				tempBean = list.get(0);
				bean.setSeqNumber(tempBean.getSeqNumber());
				String tempfields = "";
				if(tempBean.getCheckState() == Constants.WAIT_CHECK){
					tempfields = fields + ",SumAidFund,SumAidNum";
				}
				if(tempBean.getCheckState() == Constants.NOPASS_CHECK){
					tempfields = fields + ",SumAidFund,SumAidNum,CheckState";
					bean.setCheckState(Constants.WAIT_CHECK);
					Calendar cal = Calendar.getInstance();
					cal.setTime(tempBean.getTime());
					int year1 = cal.get(Calendar.YEAR); 
					checkDao.delete("T641", year1 ) ;
				}
				
				double sumAidFund = tempBean.getSumAidFund();
				int sumAidNum = tempBean.getSumAidNum();
				
//				资助金额
				if(bean.getGovAidFund()!=null){
					if(tempBean.getGovAidFund()==null){
						sumAidFund = sumAidFund + bean.getGovAidFund();
					}else{
						sumAidFund = sumAidFund + (bean.getGovAidFund()-tempBean.getGovAidFund());
					}
				}
				if(bean.getSocialAidFund()!=null){
					if(tempBean.getSocialAidFund()==null){
						sumAidFund = sumAidFund + bean.getSocialAidFund();
					}else{
						sumAidFund = sumAidFund + (bean.getSocialAidFund()-tempBean.getSocialAidFund());
					}
				}
				if(bean.getSchAidFund()!=null){
					if(tempBean.getSchAidFund()==null){
						sumAidFund = sumAidFund + bean.getSchAidFund();
					}else{
						sumAidFund = sumAidFund + (bean.getSchAidFund()-tempBean.getSchAidFund());
					}
				}
				if(bean.getNationAidFund()!=null){
					if(tempBean.getNationAidFund()==null){
						sumAidFund = sumAidFund + bean.getNationAidFund();
					}else{
						sumAidFund = sumAidFund + (bean.getNationAidFund()-tempBean.getNationAidFund());
					}
				}
				if(bean.getWorkStudyFund()!=null){
					if(tempBean.getWorkStudyFund()==null){
						sumAidFund = sumAidFund + bean.getWorkStudyFund();
					}else{
						sumAidFund = sumAidFund + (bean.getWorkStudyFund()-tempBean.getWorkStudyFund());
					}
				}
				if(bean.getTuitionWaiberFund()!=null){
					if(tempBean.getTuitionWaiberFund()==null){
						sumAidFund = sumAidFund + bean.getTuitionWaiberFund();
					}else{
						sumAidFund = sumAidFund + (bean.getTuitionWaiberFund()-tempBean.getTuitionWaiberFund());
					}
				}
				if(bean.getTempFund()!=null){
					if(tempBean.getTempFund()==null){
						sumAidFund = sumAidFund + bean.getTempFund();
					}else{
						sumAidFund = sumAidFund + (bean.getTempFund()-tempBean.getTempFund());
					}
				}
				bean.setSumAidFund(sumAidFund);
				
				
//				资助学生数
				if(bean.getGovAidFund()!=null){
					if(tempBean.getGovAidNum()==null){
						sumAidNum = sumAidNum + bean.getGovAidNum();
					}else{
						sumAidNum = sumAidNum + (bean.getGovAidNum()-tempBean.getGovAidNum());
					}
				}
				if(bean.getSocialAidNum()!=null){
					if(tempBean.getSocialAidNum()==null){
						sumAidNum =sumAidNum + bean.getSocialAidNum();
					}else{
						sumAidNum = sumAidNum + (bean.getSocialAidNum()-tempBean.getSocialAidNum());
					}
				}
				if(bean.getSchAidNum()!=null){
					if(tempBean.getSchAidNum()==null){
						sumAidNum = sumAidNum + bean.getSchAidNum();
					}else{
						sumAidNum = sumAidNum + (bean.getSchAidNum()-tempBean.getSchAidNum());
					}
				}
				if(bean.getNationAidNum()!=null){
					if(tempBean.getNationAidNum()==null){
						sumAidNum = sumAidNum + bean.getNationAidNum();
					}else{
						sumAidNum = sumAidNum + (bean.getNationAidNum()-tempBean.getNationAidNum());
					}
				}
				if(bean.getWorkStudyNum()!=null){
					if(tempBean.getWorkStudyFund()==null){
						sumAidNum = sumAidNum + bean.getWorkStudyNum();
					}else{
						sumAidNum = sumAidNum + (bean.getWorkStudyNum()-tempBean.getWorkStudyNum());
					}
				}
				if(bean.getTuitionWaiberNum()!=null){
					if(tempBean.getTuitionWaiberNum()==null){
						sumAidNum = sumAidNum + bean.getTuitionWaiberNum();
					}else{
						sumAidNum = sumAidNum + (bean.getTuitionWaiberNum()-tempBean.getTuitionWaiberNum());
					}
				}
				if(bean.getTempNum()!=null){
					if(tempBean.getTempNum()==null){
						sumAidNum = sumAidNum + bean.getTempNum();
					}else{
						sumAidNum = sumAidNum + (bean.getTempNum()-tempBean.getTempNum());
					}
				}
				bean.setSumAidNum(sumAidNum);						
				flag = DAOUtil.update(bean, tableName, key, tempfields, conn) ;
			}else{
				bean.setCheckState(Constants.WAIT_CHECK);
				bean.setTime(TimeUtil.changeDateY(year));
				double sumAidFund =0;
				int sumAidNum = 0;				
				
//				资助金额
				if(bean.getGovAidFund()!=null){
					sumAidFund = sumAidFund + bean.getGovAidFund();
				}
				if(bean.getSocialAidFund()!=null){
					sumAidFund = sumAidFund + bean.getSocialAidFund();
				}
				if(bean.getSchAidFund()!=null){
					sumAidFund = sumAidFund + bean.getSchAidFund();
				}
				if(bean.getNationAidFund()!=null){
					sumAidFund = sumAidFund + bean.getNationAidFund();
				}
				if(bean.getWorkStudyFund()!=null){
					sumAidFund = sumAidFund + bean.getWorkStudyFund();
				}
				if(bean.getTuitionWaiberFund()!=null){
					sumAidFund = sumAidFund + bean.getTuitionWaiberFund();
				}
				if(bean.getTempFund()!=null){
					sumAidFund = sumAidFund + bean.getTempFund();
				}
				bean.setSumAidFund(sumAidFund);
				
				
//				资助学生数
				if(bean.getGovAidFund()!=null){
					sumAidNum = sumAidNum + bean.getGovAidNum();
				}
				if(bean.getSocialAidNum()!=null){
					sumAidNum =sumAidNum + bean.getSocialAidNum();
				}
				if(bean.getSchAidNum()!=null){
					sumAidNum = sumAidNum + bean.getSchAidNum();
				}
				if(bean.getNationAidNum()!=null){
					sumAidNum = sumAidNum + bean.getNationAidNum();
				}
				if(bean.getWorkStudyNum()!=null){
					sumAidNum = sumAidNum + bean.getWorkStudyNum();
				}
				if(bean.getTuitionWaiberNum()!=null){
					sumAidNum = sumAidNum + bean.getTuitionWaiberNum();
				}
				if(bean.getTempNum()!=null){
					sumAidNum = sumAidNum + bean.getTempNum();
				}
				bean.setSumAidNum(sumAidNum);	
				
				String tempfields = fields + ",SumAidFund,SumAidNum,Time,CheckState";
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
		T641_Dao testDao =  new T641_Dao() ;
		boolean flag = testDao.updatCheck();
		System.out.println(flag);
	}

}
