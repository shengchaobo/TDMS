package cn.nit.dao.table5;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.List;

import cn.nit.bean.table5.S5302_Bean;
import cn.nit.bean.table5.T54_Bean;
import cn.nit.constants.Constants;

import cn.nit.dao.CheckDao;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class T54_Dao {
	
	private String tableName = "T54_OutClassActAndLeture_YLC$" ;
	private String field = "lectureSumNum,schLecture,collegeLecture,actItemSumNum," +
			"nationActItem,proviActItem,schActItem,Time,Note,CheckState";
	private String keyfield = "SeqNumber";
	
	CheckDao checkDao = new CheckDao();
	
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public T54_Bean getYearInfo(String year){
		
		String sql = "select " + " " + keyfield + "," +
		field + " from " + tableName + " where convert(varchar(4),Time,120)=" + year;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T54_Bean> list = null ;
		T54_Bean bean = null;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T54_Bean.class) ;
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
	public boolean save(T54_Bean bean, String year, String fields){
		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year;		
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection() ;
		
		Statement st = null ;
		ResultSet rs = null ;
		List<T54_Bean> list = null ;
		T54_Bean tempBean = null;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T54_Bean.class) ;
			if(list.size() != 0){
				tempBean = list.get(0);
				bean.setSeqNumber(tempBean.getSeqNumber());
				String tempfields = "";
				if(tempBean.getCheckState() == Constants.WAIT_CHECK){
					tempfields = fields + ",LectureSumNum,ActItemSumNum";
				}
				if(tempBean.getCheckState() == Constants.NOPASS_CHECK){
					tempfields = fields + ",LectureSumNum,ActItemSumNum,CheckState";
					bean.setCheckState(Constants.WAIT_CHECK);
					Calendar cal = Calendar.getInstance();
					cal.setTime(tempBean.getTime());
					int year1 = cal.get(Calendar.YEAR); 
					checkDao.delete("T54", year1 ) ;//year1为checkID
				}
				
				int lectureSumNum = tempBean.getLectureSumNum();
				int actItemSumNum = tempBean.getActItemSumNum();
				
//				学术讲座总数
				if(bean.getCollegeLecture()!=null){
					if(tempBean.getCollegeLecture()==null){
						lectureSumNum = lectureSumNum + bean.getCollegeLecture();
					}else{
						lectureSumNum = lectureSumNum + (bean.getCollegeLecture()- tempBean.getCollegeLecture());
					}
					
				}
				if(bean.getSchLecture()!=null){
					if(tempBean.getSchLecture()==null){
						lectureSumNum = lectureSumNum + bean.getSchLecture();
					}else{
						lectureSumNum = lectureSumNum + (bean.getSchLecture()- tempBean.getSchLecture());
					}
				}
				bean.setLectureSumNum(lectureSumNum);
				
				
//				活动项目总数
				if(bean.getNationActItem()!=null){
					if(tempBean.getNationActItem()==null){
						actItemSumNum = actItemSumNum + bean.getNationActItem();
					}else{
						actItemSumNum = actItemSumNum + (bean.getNationActItem()- tempBean.getNationActItem());
					}
				}
				if(bean.getProviActItem()!=null){	
					if(tempBean.getProviActItem()==null){
						actItemSumNum = actItemSumNum + bean.getProviActItem();
					}else{
						actItemSumNum = actItemSumNum + (bean.getProviActItem()- tempBean.getProviActItem());
					}
				}
				if(bean.getSchActItem()!=null){
					if(tempBean.getSchActItem()==null){
						actItemSumNum = actItemSumNum + bean.getSchActItem();
					}else{
						actItemSumNum = actItemSumNum + (bean.getSchActItem()- tempBean.getSchActItem());
					}
				}
				bean.setActItemSumNum(actItemSumNum);
				
				flag = DAOUtil.update(bean, tableName, keyfield, tempfields, conn) ;
				
			}else{
				
				bean.setTime(TimeUtil.changeDateY(year));
				bean.setCheckState(Constants.WAIT_CHECK);
				int lectureSumNum = 0;
				int actItemSumNum = 0;
				
//				学术讲座总数
				if(bean.getCollegeLecture()!=null){
					lectureSumNum = lectureSumNum + bean.getCollegeLecture();
				}
				if(bean.getSchLecture()!=null){
					lectureSumNum = lectureSumNum + bean.getSchLecture();
				}
				bean.setLectureSumNum(lectureSumNum);
				
				
//				活动项目总数
				if(bean.getNationActItem()!=null){
					actItemSumNum = actItemSumNum + bean.getNationActItem();
				}
				if(bean.getProviActItem()!=null){	
					actItemSumNum = actItemSumNum + bean.getProviActItem();
				}
				if(bean.getSchActItem()!=null){
					actItemSumNum = actItemSumNum + bean.getSchActItem();
				}
				bean.setActItemSumNum(actItemSumNum);
				
				String tempfields = fields + ",LectureSumNum,ActItemSumNum,Time,CheckState";
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
		T54_Dao testDao =  new T54_Dao() ;
		boolean flag = testDao.updatCheck();
		System.out.println(flag);
	}

}
