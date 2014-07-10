package cn.nit.dao.table1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.nit.bean.table1.T11Bean;
import cn.nit.bean.table1.T152Bean;
import cn.nit.bean.table5.T54_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.pojo.table1.T11POJO;
import cn.nit.pojo.table1.T151POJO;

import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class T11DAO {
	
	/**  数据库表名  */
	private String tableName = "T11_SchBasInfo_PartyOffice$" ;
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "SchAddress,SchTel,SchFax,SchFillerName,SchFillerTel,SchFillerEmail,SchName,SchID,SchEnName,SchType,SchQuality," +
	"SchBuilder,MajDept,SchUrl,AdmissonBatch,Sch_BeginTime,MediaUrl,YaohuSchAdd,PengHuSchAdd,Time,Note" ;
	
	/**
	 * 将数据表11的实体类插入数据库
	 * @param schbasInfo
	 * @return
	 *
	 * @time: 2014-5-14/上午10:53:10
	 */
	public boolean insert(T11Bean t11Bean){
		
		//flag判断数据是否插入成功
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.insert(t11Bean, tableName, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
		}
		return flag ;
	}
	
	/**
	 * 讲数据批量插入11表中
	 * @param list {@linkplain java.util.List<{@link cn.nit.bean.table1.T151Bean}>}
	 * @return true表示插入成功，false表示插入失败
	 */
	public boolean batchInsert(List<T11Bean> list){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		
		try{
			this.delete();
			flag = DAOUtil.batchInsert(list, tableName, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}
		
		return flag ;
	}
	
	/**
	 * @param conditions 查询条件
	 * @param fillUnitId 填报人单位号，如果为空，则查询所有未审核的数据，<br>如果不为空，则查询填报人自己单位的所有的数据
	 * @return
	 */
	public List<T11POJO> auditingData(String year){
		
		StringBuffer sql = new StringBuffer() ;
		List<T11POJO> list=null;
        
		sql.append("select * from "+ tableName);
		sql.append(" where Time like '"+year+"%'");
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
//		System.out.println(sql.toString());
		
		try{
			st = conn.createStatement() ;
//			ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY
//			st.setMaxRows(1) ;
			rs = st.executeQuery(sql.toString()) ;
//			rs.absolute((page - 1) * rows) ;
			list = DAOUtil.getList(rs, T11POJO.class) ;
			
			
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}
		return list ;
	}

	
	/**
	 * @param conditions 查询条件
	 * @param fillUnitId 填报人单位号，如果为空，则查询所有未审核的数据，<br>如果不为空，则查询填报人自己单位的所有的数据
	 * @return
	 */
	public T11Bean loadData(String year){
		
		StringBuffer sql = new StringBuffer() ;
		List<T11Bean> list=null;
		T11Bean bean=null;
        
		sql.append("select * from "+ tableName);
		sql.append(" where Time like '"+year+"%'");
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
//		System.out.println(sql.toString());
		
		try{
			st = conn.createStatement() ;
//			ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY
//			st.setMaxRows(1) ;
			rs = st.executeQuery(sql.toString()) ;
//			rs.absolute((page - 1) * rows) ;
			list = DAOUtil.getList(rs, T11Bean.class) ;
			if(list.size() != 0){
				bean = list.get(0);
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
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
	public boolean save(T11Bean bean, String year, String fields){
		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year;		
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection() ;
		
		Statement st = null ;
		ResultSet rs = null ;
		List<T11Bean> list = null ;
		T11Bean tempBean = null;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T11Bean.class) ;
			if(list.size() != 0){
				tempBean = list.get(0);
				bean.setSeqNumber(tempBean.getSeqNumber());
//				String tempfields = fields + ",LectureSumNum,ActItemSumNum";
//				
//				int lectureSumNum = tempBean.getLectureSumNum();
//				int actItemSumNum = tempBean.getActItemSumNum();
//				
////				学术讲座总数
//				if(bean.getCollegeLecture()!=null){
//					if(tempBean.getCollegeLecture()==null){
//						lectureSumNum = lectureSumNum + bean.getCollegeLecture();
//					}else{
//						lectureSumNum = lectureSumNum + (bean.getCollegeLecture()- tempBean.getCollegeLecture());
//					}
//					
//				}
//				if(bean.getSchLecture()!=null){
//					if(tempBean.getSchLecture()==null){
//						lectureSumNum = lectureSumNum + bean.getSchLecture();
//					}else{
//						lectureSumNum = lectureSumNum + (bean.getSchLecture()- tempBean.getSchLecture());
//					}
//				}
//				bean.setLectureSumNum(lectureSumNum);
//				
//				
////				活动项目总数
//				if(bean.getNationActItem()!=null){
//					if(tempBean.getNationActItem()==null){
//						actItemSumNum = actItemSumNum + bean.getNationActItem();
//					}else{
//						actItemSumNum = actItemSumNum + (bean.getNationActItem()- tempBean.getNationActItem());
//					}
//				}
//				if(bean.getProviActItem()!=null){	
//					if(tempBean.getProviActItem()==null){
//						actItemSumNum = actItemSumNum + bean.getProviActItem();
//					}else{
//						actItemSumNum = actItemSumNum + (bean.getProviActItem()- tempBean.getProviActItem());
//					}
//				}
//				if(bean.getSchActItem()!=null){
//					if(tempBean.getSchActItem()==null){
//						actItemSumNum = actItemSumNum + bean.getSchActItem();
//					}else{
//						actItemSumNum = actItemSumNum + (bean.getSchActItem()- tempBean.getSchActItem());
//					}
//				}
//				bean.setActItemSumNum(actItemSumNum);
				
				flag = DAOUtil.update(bean, tableName, key, fields, conn) ;
				
			}else{
				
//				bean.setTime(TimeUtil.changeDateY(year));
//				
//				int lectureSumNum = 0;
//				int actItemSumNum = 0;
//				
////				学术讲座总数
//				if(bean.getCollegeLecture()!=null){
//					lectureSumNum = lectureSumNum + bean.getCollegeLecture();
//				}
//				if(bean.getSchLecture()!=null){
//					lectureSumNum = lectureSumNum + bean.getSchLecture();
//				}
//				bean.setLectureSumNum(lectureSumNum);
//				
//				
////				活动项目总数
//				if(bean.getNationActItem()!=null){
//					actItemSumNum = actItemSumNum + bean.getNationActItem();
//				}
//				if(bean.getProviActItem()!=null){	
//					actItemSumNum = actItemSumNum + bean.getProviActItem();
//				}
//				if(bean.getSchActItem()!=null){
//					actItemSumNum = actItemSumNum + bean.getSchActItem();
//				}
//				bean.setActItemSumNum(actItemSumNum);
//				
//				String tempfields = fields + ",LectureSumNum,ActItemSumNum,Time";
				flag = DAOUtil.insert(bean, tableName, key, conn) ;
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
	 *Excel數據導出
	 */
	public List<T11Bean> forExcel(String year){
		
		StringBuffer sql = new StringBuffer() ;
		List<T11Bean> list=null;
        
		sql.append("select * from "+ tableName);
		sql.append(" where Time like '"+year+"%'");
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
//		System.out.println(sql.toString());
		
		try{
			st = conn.createStatement() ;
//			ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY
//			st.setMaxRows(1) ;
			rs = st.executeQuery(sql.toString()) ;
//			rs.absolute((page - 1) * rows) ;
			list = DAOUtil.getList(rs, T11Bean.class) ;
			
			
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}
		return list ;
	}
	
	
	public boolean update(T11Bean t11Bean){
		
		boolean flag = false ;
		Connection conn = DBConnection.instance.getConnection() ;
		try{
			flag = DAOUtil.update(t11Bean, tableName, key, field, conn) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return flag ;
		}finally{
			DBConnection.close(conn) ;
		}
		return flag ;
	}
	
	public boolean delete()
	{
		int  flag=0;
		StringBuffer sql=new StringBuffer();
		sql.append("delete from "+ tableName);
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		
		try{
			st = conn.createStatement() ;
			flag = st.executeUpdate(sql.toString()) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return false ;
		}
		
		if(flag == 0){
			return false ;
		}else{
			return true ;
		}
		
	}
	
	public boolean deleteCoursesByIds(String ids){
		
		int flag = 0 ;
		StringBuffer sql = new StringBuffer() ;
		sql.append("delete from " + tableName) ;
		sql.append(" where " + key + " in " + ids) ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		
		try{
			st = conn.createStatement() ;
			flag = st.executeUpdate(sql.toString()) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return false ;
		}
		
		if(flag == 0){
			return false ;
		}else{
			return true ;
		}
	}
	public String getTableName(){
		return this.tableName ;
	}

    public static void main(String arg[])
    {
    	T11DAO dao=new T11DAO();
//    	T11Bean bean=dao.auditingData("2014");
//    	System.out.println(bean.getMajDept());
    }

}
