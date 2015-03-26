package cn.nit.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import cn.nit.bean.SCondiMornit;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class CondiDao {
	
	private String tableName = "S_CondiMornit" ;
	private String field = "undergraNum,fulltimeStuNum,undergraRatio,totalStuNum,inNum,outNum,inOutRatio,minorNum,minorNumRatio," +
			"graduRatio,degreeRatio,stuEmployRatio,fullTimeTeachNum,graduDegreeRatio,adminLevelRatio,outHireTeaNum,teacherNum," +
			"stuTeaRatio,totalFieldNum,totalScoreNum,praRatio,optionRatio,profNumRatio,profCourseRatio,areaPerStu,housePerStu," +
			"labPerStu,dormPerStu,equPerStu,newEquRatio,booksPerStu,inbooksPerStu,comPerStu,mediaPerStu,teachFeePerStu,labFeePerStu,practiceFeePerStu,Time,Note";
	private String keyfield = "SeqNumber";
	
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public SCondiMornit getYearInfo(String year){
		
		String sql = "select " + " " + keyfield + "," +
		field + " from " + tableName + " where convert(varchar(4),Time,120)=" + year;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<SCondiMornit> list = null ;
		SCondiMornit bean = null;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, SCondiMornit.class) ;
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
	public boolean save(SCondiMornit bean, String year){
		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year;		
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection() ;
		
		Statement st = null ;
		ResultSet rs = null ;
		List<SCondiMornit> list = null ;
		SCondiMornit tempBean = null;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, SCondiMornit.class) ;
			if(list.size() != 0){
				tempBean = list.get(0);
				bean.setSeqNumber(tempBean.getSeqNumber());
				String tempfields = "undergraNum,fulltimeStuNum,undergraRatio,totalStuNum,inNum,outNum,inOutRatio,minorNum,minorNumRatio," +
			"graduRatio,degreeRatio,stuEmployRatio,fullTimeTeachNum,graduDegreeRatio,adminLevelRatio,outHireTeaNum,teacherNum," +
			"stuTeaRatio,totalFieldNum,totalScoreNum,praRatio,optionRatio,profNumRatio,profCourseRatio,areaPerStu,housePerStu," +
			"labPerStu,dormPerStu,equPerStu,newEquRatio,booksPerStu,inbooksPerStu,comPerStu,mediaPerStu,teachFeePerStu,labFeePerStu,practiceFeePerStu";
				flag = DAOUtil.update(bean, tableName, keyfield, tempfields, conn) ;
			}else{
				bean.setTime(TimeUtil.changeDateY(year));
				String tempfields = "undergraNum,fulltimeStuNum,undergraRatio,totalStuNum,inNum,outNum,inOutRatio,minorNum,minorNumRatio," +
			"graduRatio,degreeRatio,stuEmployRatio,fullTimeTeachNum,graduDegreeRatio,adminLevelRatio,outHireTeaNum,teacherNum," +
			"stuTeaRatio,totalFieldNum,totalScoreNum,praRatio,optionRatio,profNumRatio,profCourseRatio,areaPerStu,housePerStu," +
			"labPerStu,dormPerStu,equPerStu,newEquRatio,booksPerStu,inbooksPerStu,comPerStu,mediaPerStu,teachFeePerStu,labFeePerStu,practiceFeePerStu,Time";;
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
	
	
	
	public static void main(String args[]){
		//S22_Dao testDao =  new S22_Dao() ;
	}

}
