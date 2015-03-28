package cn.nit.dao.table6;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.json.JSON;

import cn.nit.bean.table6.T612_Bean;
import cn.nit.constants.Constants;
import cn.nit.dao.CheckDao;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class T612_Dao {

	/** 数据库表名 */
	private String tableName = "T612_MasterNumInfo_Gra$";

	/** 数据自增长字段的主键，必须为自增长字段 */
	private String key = "SeqNumber";

	/** 数据库表中除了自增长字段的所有字段 */
	private String field = "MasterLastYearNum,MasterThisYearNum," +
			"FullTimeMasterLastYearNum,FullTimeMasterThisYearNum," +
			"PartTimeMasterLastYearNum,PartTimeMasterThisYearNum,DoctorLastYearNum," +
			"DoctorThisYearNum,FullTimeDoctorLastYearNum,FullTimeDoctorThisYearNum," +
			"PartTimeDoctorLastYearNum,PartTimeDoctorThisYearNum,Time,Note,CheckState";


	CheckDao checkDao = new CheckDao();

	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public T612_Bean getYearInfo(String year){
		
		String sql = "select " + " " + key + "," +
		field + " from " + tableName + " where convert(varchar(4),Time,120)=" + year;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T612_Bean> list = null ;
		T612_Bean bean = null;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T612_Bean.class) ;
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
	public boolean save(T612_Bean bean, String year, String fields){
		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year;		
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection() ;
		
		Statement st = null ;
		ResultSet rs = null ;
		List<T612_Bean> list = null ;
		T612_Bean tempBean = null;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T612_Bean.class) ;
			if(list.size() != 0){
				tempBean = list.get(0);
				bean.setSeqNumber(tempBean.getSeqNumber());
				String tempfields = "";
				System.out.println("+++++++++++++++");
				System.out.println("checkState:"+tempBean.getCheckState());
				if(tempBean.getCheckState() == Constants.WAIT_CHECK){
					tempfields = fields + ",MasterLastYearNum,MasterThisYearNum,DoctorLastYearNum,DoctorThisYearNum";
				}
				if(tempBean.getCheckState() == Constants.NOPASS_CHECK){
					tempfields = fields + ",MasterLastYearNum,MasterThisYearNum,DoctorLastYearNum,DoctorThisYearNum,CheckState";
					bean.setCheckState(Constants.WAIT_CHECK);
					Calendar cal = Calendar.getInstance();
					cal.setTime(tempBean.getTime());
					int year1 = cal.get(Calendar.YEAR); 
					checkDao.delete("T612", year1 ) ;
				}
				
				int masterLastYearNum = tempBean.getMasterLastYearNum();
				int masterThisYearNum = tempBean.getMasterThisYearNum();
				int doctorLastYearNum = tempBean.getDoctorLastYearNum();
				int doctorThisYearNum = tempBean.getDoctorThisYearNum();
				
//				研究生上学年总人数
				if(bean.getFullTimeMasterLastYearNum()!=null){
					if(tempBean.getFullTimeMasterLastYearNum()==null){
						masterLastYearNum = masterLastYearNum + bean.getFullTimeMasterLastYearNum();
					}else{
						masterLastYearNum = masterLastYearNum + (bean.getFullTimeMasterLastYearNum()-tempBean.getFullTimeMasterLastYearNum());
					}
				}
				if(bean.getPartTimeMasterLastYearNum()!=null){
					if(tempBean.getPartTimeMasterLastYearNum()==null){
						masterLastYearNum = masterLastYearNum + bean.getPartTimeMasterLastYearNum();
					}else{
						masterLastYearNum = masterLastYearNum + (bean.getPartTimeMasterLastYearNum()-tempBean.getPartTimeMasterLastYearNum());
					}
				}
				bean.setMasterLastYearNum(masterLastYearNum);
				
//				研究生本学年总人数
				if(bean.getFullTimeMasterThisYearNum()!=null){
					if(tempBean.getFullTimeMasterThisYearNum()==null){
						masterThisYearNum = masterThisYearNum + bean.getFullTimeMasterThisYearNum();
					}else{
						masterThisYearNum = masterThisYearNum + (bean.getFullTimeMasterThisYearNum()-tempBean.getFullTimeMasterThisYearNum());
					}
				}
				if(bean.getPartTimeMasterThisYearNum()!=null){
					if(tempBean.getPartTimeMasterThisYearNum()==null){
						masterThisYearNum = masterThisYearNum + bean.getPartTimeMasterThisYearNum();
					}else{
						masterThisYearNum = masterThisYearNum + (bean.getPartTimeMasterThisYearNum()-tempBean.getPartTimeMasterThisYearNum());
					}
				}
				bean.setMasterThisYearNum(masterThisYearNum);
				
				
//				博士生上学年总人数
				if(bean.getFullTimeDoctorLastYearNum()!=null){
					if(tempBean.getFullTimeMasterLastYearNum()==null){
						doctorLastYearNum = doctorLastYearNum + bean.getFullTimeDoctorLastYearNum();
					}else{
						doctorLastYearNum = doctorLastYearNum + (bean.getFullTimeDoctorLastYearNum()-tempBean.getFullTimeMasterLastYearNum());
					}
				}
				if(bean.getPartTimeDoctorLastYearNum()!=null){
					if(tempBean.getPartTimeDoctorLastYearNum()==null){
						doctorLastYearNum = doctorLastYearNum + bean.getPartTimeDoctorLastYearNum();
					}else{
						doctorLastYearNum = doctorLastYearNum + (bean.getPartTimeDoctorLastYearNum()-tempBean.getPartTimeMasterLastYearNum());
					}
				}
				bean.setDoctorLastYearNum(doctorLastYearNum);
				
//				博士本学年总人数
				if(bean.getFullTimeDoctorThisYearNum()!=null){
					if(tempBean.getFullTimeDoctorThisYearNum()==null){
						doctorThisYearNum = doctorThisYearNum + bean.getFullTimeDoctorThisYearNum();
					}else{
						doctorThisYearNum = doctorThisYearNum + (bean.getFullTimeDoctorThisYearNum()-tempBean.getFullTimeDoctorThisYearNum());
					}
				}
				if(bean.getPartTimeDoctorThisYearNum()!=null){
					if(tempBean.getPartTimeDoctorThisYearNum()==null){
						doctorThisYearNum = doctorThisYearNum + bean.getPartTimeDoctorThisYearNum();
					}else{
						doctorThisYearNum = doctorThisYearNum + (bean.getPartTimeDoctorThisYearNum()-tempBean.getPartTimeDoctorThisYearNum());
					}
				}
				bean.setDoctorThisYearNum(doctorThisYearNum);
				
				flag = DAOUtil.update(bean, tableName, key, tempfields, conn) ;
			}else{
				bean.setTime(TimeUtil.changeDateY(year));
				bean.setCheckState(Constants.WAIT_CHECK);
				String tempfields = fields + ",MasterLastYearNum,MasterThisYearNum,DoctorLastYearNum,DoctorThisYearNum,Time,CheckState";
				
				int masterLastYearNum = 0;
				int masterThisYearNum = 0;
				int doctorLastYearNum = 0;
				int doctorThisYearNum = 0;
				
//				研究生上学年总人数
				if(bean.getFullTimeMasterLastYearNum()!=null){
					masterLastYearNum = masterLastYearNum + bean.getFullTimeMasterLastYearNum();
				}
				if(bean.getPartTimeMasterLastYearNum()!=null){
					masterLastYearNum = masterLastYearNum + bean.getPartTimeMasterLastYearNum();
				}
				bean.setMasterLastYearNum(masterLastYearNum);
				
//				研究生本学年总人数
				if(bean.getFullTimeMasterThisYearNum()!=null){
					masterThisYearNum = masterThisYearNum + bean.getFullTimeMasterThisYearNum();
				}
				if(bean.getPartTimeMasterThisYearNum()!=null){
					masterThisYearNum = masterThisYearNum + bean.getPartTimeMasterThisYearNum();
				}
				bean.setMasterThisYearNum(masterThisYearNum);
				
				
//				博士生上学年总人数
				if(bean.getFullTimeDoctorLastYearNum()!=null){
					doctorLastYearNum = doctorLastYearNum + bean.getFullTimeDoctorLastYearNum();
				}
				if(bean.getPartTimeDoctorLastYearNum()!=null){
					doctorLastYearNum = doctorLastYearNum + bean.getPartTimeDoctorLastYearNum();
				}
				bean.setDoctorLastYearNum(doctorLastYearNum);
				
//				博士本学年总人数
				if(bean.getFullTimeDoctorThisYearNum()!=null){
					doctorThisYearNum = doctorThisYearNum + bean.getFullTimeDoctorThisYearNum();
				}
				if(bean.getPartTimeDoctorThisYearNum()!=null){
					doctorThisYearNum = doctorThisYearNum + bean.getPartTimeDoctorThisYearNum();
				}
				bean.setDoctorThisYearNum(doctorThisYearNum);
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


}
