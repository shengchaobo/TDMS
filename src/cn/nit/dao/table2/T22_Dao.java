package cn.nit.dao.table2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.table2.T22_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class T22_Dao {
	
	private String tableName = "T22_FloorArea_Log$" ;
	private String field = "AdmOfficeArea,AdmOfficeNum,LibArea,LibNum,LibRoomArea,LibRoomSitNum,MuseumArea,MuseumNum,SchHisHallArea," +
			"SchHisHallNum,GymArea,GymNum,SportArea,SportNum,StuCenterArea,StuCenterNum,HallArea,HallNum,StuCanteenArea,StuCanteenNum," +
			"StuDormiArea,StuDormiNum,OtherArea,OtherNum,Time,Note";
	private String keyfield = "SeqNumber";
	
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public T22_Bean getYearInfo(String year){
		
		String sql = "select " + " " + keyfield + "," +
		field + " from " + tableName + " where convert(varchar(4),Time,120)=" + year;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T22_Bean> list = null ;
		T22_Bean bean = null;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T22_Bean.class) ;
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
	public boolean save(T22_Bean bean, String year, String fields){
		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year;		
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection() ;
		
		Statement st = null ;
		ResultSet rs = null ;
		List<T22_Bean> list = null ;
		T22_Bean tempBean = null;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T22_Bean.class) ;
			if(list.size() != 0){
				tempBean = list.get(0);
				bean.setSeqNumber(tempBean.getSeqNumber());
				flag = DAOUtil.update(bean, tableName, keyfield, fields, conn) ;
			}else{
				bean.setTime(TimeUtil.changeDateY(year));
				String tempfields = fields + ",Time";
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
	
	
	
	public static void main(String args[]){
		//T22_Dao testDao =  new T22_Dao() ;
	}

}