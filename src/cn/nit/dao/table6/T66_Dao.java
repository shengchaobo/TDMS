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
import cn.nit.bean.table6.T66_Bean;
import cn.nit.constants.Constants;
import cn.nit.dao.CheckDao;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class T66_Dao {

	/** 数据库表名 */
	private String tableName = "T66_StuClub_YLC$";

	/** 数据自增长字段的主键，必须为自增长字段 */
	private String key = "SeqNumber";

	/** 数据库表中除了自增长字段的所有字段 */
	private String field = "StuClubSum,StuClubSciNum,StuClubHumanNum,StuClubSportNum," +
			"StuClubArtNum,OtherStuClub,JoinStuSum,JoinClubSciNum,JoinClubHumanNum," +
			"JoinClubSportNum,JoinClubArtNum,JoinOtherClub,Time,Note,CheckState";	

	CheckDao checkDao = new CheckDao();
	
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public T66_Bean getYearInfo(String year){
		
		String sql = "select " + " " + key + "," +
		field + " from " + tableName + " where convert(varchar(4),Time,120)=" + year;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T66_Bean> list = null ;
		T66_Bean bean = null;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T66_Bean.class) ;
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
	 *教育部导出
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public T66_Bean getYearInfo(String year,int CheckState){
		
		String sql = "select " + " " + key + "," +
		field + " from " + tableName + " where convert(varchar(4),Time,120)=" + year+
		" and CheckState="+CheckState;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T66_Bean> list = null ;
		T66_Bean bean = null;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T66_Bean.class) ;
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
	public boolean save(T66_Bean bean, String year, String fields){
		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year;		
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection() ;
		
		Statement st = null ;
		ResultSet rs = null ;
		List<T66_Bean> list = null ;
		T66_Bean tempBean = null;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T66_Bean.class) ;
			if(list.size() != 0){
				tempBean = list.get(0);
				bean.setSeqNumber(tempBean.getSeqNumber());
				String tempfields = "";
				if(tempBean.getCheckState() == Constants.WAIT_CHECK){
					tempfields = fields +  ",StuClubSum,JoinStuSum";
				}
				if(tempBean.getCheckState() == Constants.NOPASS_CHECK){
					tempfields = fields + ",StuClubSum,JoinStuSum,CheckState";
					bean.setCheckState(Constants.WAIT_CHECK);
					Calendar cal = Calendar.getInstance();
					cal.setTime(tempBean.getTime());
					int year1 = cal.get(Calendar.YEAR); 
					checkDao.delete("T66", year1 ) ;
				}
				
				int stuClubSum = tempBean.getStuClubSum();
				int joinStuSum = tempBean.getJoinStuSum();
				
//				学生社团总数
				if(bean.getStuClubSciNum()!=null){
					if(tempBean.getStuClubSciNum()==null){
						stuClubSum = stuClubSum + bean.getStuClubSciNum();
					}else{
						stuClubSum = stuClubSum + (bean.getStuClubSciNum()-tempBean.getStuClubSciNum());
					}
				}
				if(bean.getStuClubHumanNum()!=null){
					if(tempBean.getStuClubHumanNum()==null){
						stuClubSum = stuClubSum + bean.getStuClubHumanNum();
					}else{
						stuClubSum = stuClubSum + (bean.getStuClubHumanNum()-tempBean.getStuClubHumanNum());
					}
				}
				if(bean.getStuClubSportNum()!=null){
					if(tempBean.getStuClubSportNum()==null){
						stuClubSum = stuClubSum + bean.getStuClubSportNum();
					}else{
						stuClubSum = stuClubSum + (bean.getStuClubSportNum()-tempBean.getStuClubSportNum());
					}
				}
				if(bean.getStuClubArtNum()!=null){
					if(tempBean.getStuClubArtNum()==null){
						stuClubSum = stuClubSum + bean.getStuClubArtNum();
					}else{
						stuClubSum = stuClubSum + (bean.getStuClubArtNum()-tempBean.getStuClubArtNum());
					}
				}
				if(bean.getOtherStuClub()!=null){
					if(tempBean.getOtherStuClub()==null){
						stuClubSum = stuClubSum + bean.getOtherStuClub();
					}else{
						stuClubSum = stuClubSum + (bean.getOtherStuClub()-tempBean.getOtherStuClub());
					}
				}
				bean.setStuClubSum(stuClubSum);
				
				
//				参与人次数
				if(bean.getJoinClubSciNum()!=null){
					if(tempBean.getJoinClubSciNum()==null){
						joinStuSum = joinStuSum + bean.getJoinClubSciNum();
					}else{
						joinStuSum = joinStuSum + (bean.getJoinClubSciNum()-tempBean.getJoinClubSciNum());
					}
				}
				if(bean.getJoinClubHumanNum()!=null){
					if(tempBean.getJoinClubHumanNum()==null){
						joinStuSum = joinStuSum + bean.getJoinClubHumanNum();
					}else{
						joinStuSum = joinStuSum + (bean.getJoinClubHumanNum()-tempBean.getJoinClubHumanNum());
					}
				}
				if(bean.getJoinClubSportNum()!=null){
					if(tempBean.getJoinClubSportNum()==null){
						joinStuSum = joinStuSum + bean.getJoinClubSportNum();
					}else{
						joinStuSum = joinStuSum + (bean.getJoinClubSportNum()-tempBean.getJoinClubSportNum());
					}
				}
				if(bean.getJoinClubArtNum()!=null){
					if(tempBean.getJoinClubArtNum()==null){
						joinStuSum = joinStuSum + bean.getJoinClubArtNum();
					}else{
						joinStuSum = joinStuSum + (bean.getJoinClubArtNum()-tempBean.getJoinClubArtNum());
					}
				}
				if(bean.getJoinOtherClub()!=null){
					if(tempBean.getJoinOtherClub()==null){
						joinStuSum = joinStuSum + bean.getJoinOtherClub();
					}else{
						joinStuSum = joinStuSum + (bean.getJoinOtherClub()-tempBean.getJoinOtherClub());
					}
				}
				bean.setJoinStuSum(joinStuSum);									
				flag = DAOUtil.update(bean, tableName, key, tempfields, conn) ;
			}else{
				bean.setTime(TimeUtil.changeDateY(year));
				bean.setCheckState(Constants.WAIT_CHECK);
				int stuClubSum =0;
				int joinStuSum = 0;
				
				
//				学生社团总数
				if(bean.getStuClubSciNum()!=null){
					stuClubSum = stuClubSum + bean.getStuClubSciNum();
				}
				if(bean.getStuClubHumanNum()!=null){
					stuClubSum = stuClubSum + bean.getStuClubHumanNum();
				}
				if(bean.getStuClubSportNum()!=null){
					stuClubSum = stuClubSum + bean.getStuClubSportNum();
				}
				if(bean.getStuClubArtNum()!=null){
					stuClubSum = stuClubSum + bean.getStuClubArtNum();
				}
				if(bean.getOtherStuClub()!=null){
					stuClubSum = stuClubSum + bean.getOtherStuClub();
				}
				bean.setStuClubSum(stuClubSum);
				
				
//				参与人次数
				if(bean.getJoinClubSciNum()!=null){
					joinStuSum = joinStuSum + bean.getJoinClubSciNum();
				}
				if(bean.getJoinClubHumanNum()!=null){
					joinStuSum = joinStuSum + bean.getJoinClubHumanNum();
				}
				if(bean.getJoinClubSportNum()!=null){
					joinStuSum = joinStuSum + bean.getJoinClubSportNum();
				}
				if(bean.getJoinClubArtNum()!=null){
					joinStuSum = joinStuSum + bean.getJoinClubArtNum();
				}
				if(bean.getJoinOtherClub()!=null){
					joinStuSum = joinStuSum + bean.getJoinOtherClub();
				}
				bean.setJoinStuSum(joinStuSum);	
				
				String tempfields = fields + ",StuClubSum,JoinStuSum,Time,CheckState";
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
		T66_Dao testDao =  new T66_Dao() ;
		boolean flag = testDao.updatCheck();
		System.out.println(flag);
	}

}
