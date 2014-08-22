package cn.nit.dao.table5;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.nit.bean.table5.S532_Bean;
import cn.nit.bean.table7.S71_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class S532_DAO {
	
	/**  数据库表名  */
	private String tableName = "S532_ExpTeaCenter$" ;
	
	private String key="SeqNumber";
	
	private String field="TeaUnit,UnitID,InterNum,NationNum,ProviNum,CityNum,SchNum,Time,Note";
	
	
	

    /**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<S532_Bean> totalList(String year){
		
		String sql = "select " + key + "," +field + " from " + tableName 
				+ " where convert(varchar(4),Time,120)=" + year;		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<S532_Bean> list = null ;
		System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, S532_Bean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
		
		return list ;
	}
	
	
	/**
 	 * 模板导入
 	 * @param diCourseCategories
 	 * @return
 	 *
 	 * @time: 2014-5-14/下午02:34:23
 	 */
 	public boolean save(List<S532_Bean> list,String year){
 		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year;		
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection() ;		
		Statement st = null ;
		ResultSet rs = null ;
		List<S532_Bean> templist = null ;
		String tempField = "TeaUnit,UnitID,InterNum,NationNum,ProviNum,CityNum,SchNum,Time,Note";
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			templist = DAOUtil.getList(rs, S532_Bean.class) ;
			if(templist.size() != 0){
				String delSql = "delete from " + tableName + " where convert(varchar(4),Time,120)=" + year;
				int delflag = st.executeUpdate(delSql.toString());
				if(delflag > 0 ){
					flag = DAOUtil.batchInsert(list, tableName, tempField, conn) ;
				}
			}else{
				flag = DAOUtil.batchInsert(list, tableName, tempField, conn) ;
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
	
	
	public List<S532_Bean> getData(String year){
		
	String querysql="select UnitName AS TeaUnit, " +
		" sum(case when CenterLevel='50000' then 1 else 0 end) AS InterNum, "+
		" sum(case when CenterLevel='50001' then 1 else 0 end) AS NationNum, "+
		" sum(case when CenterLevel='50002' then 1 else 0 end) AS ProviNum, "+
		" sum(case when CenterLevel='50003' then 1 else 0 end) AS CityNum, "+
		" sum(case when CenterLevel='50004' then 1 else 0 end) AS SchNum "+
	    " from DiDepartment "+
	    " left join T532_ExpTeachShowCenter_EQU$ on DiDepartment.UnitID = T532_ExpTeachShowCenter_EQU$.UnitID "+
	    " and convert(varchar(4),T532_ExpTeachShowCenter_EQU$.Time,120) = "  +  year  +  
	    " where DiDepartment.UnitID like '3%' group by DiDepartment.UnitID,UnitName;";
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<S532_Bean> list = new ArrayList<S532_Bean>() ;
        int interNum=0,nationNum=0,proviNum=0,cityNum=0,schNum=0;
		try {
			st = conn.createStatement() ;
			rs = st.executeQuery(querysql) ;
			while(rs.next()){
				String teaUnit=rs.getString("TeaUnit");
				
				interNum += rs.getInt("InterNum");
				nationNum += rs.getInt("NationNum");
				proviNum += rs.getInt("ProviNum");
				cityNum  += rs.getInt("CityNum");
				schNum += rs.getInt("SchNum");
				
				S532_Bean s532_Bean=new S532_Bean();			
				s532_Bean.setTeaUnit(teaUnit);
				s532_Bean.setInterNum(rs.getInt("InterNum"));
				s532_Bean.setNationNum(rs.getInt("NationNum"));
				s532_Bean.setProviNum(rs.getInt("ProviNum"));
				s532_Bean.setCityNum(rs.getInt("CityNum"));
				s532_Bean.setSchNum(rs.getInt("SchNum"));
				s532_Bean.setTime(TimeUtil.changeDateY(year));
                list.add(s532_Bean);
				
				System.out.println(list.size());
			}
			if(list.size()!=0){
				S532_Bean s532_Bean=new S532_Bean();			
				s532_Bean.setTeaUnit("全校合计");
				s532_Bean.setInterNum(interNum);
				s532_Bean.setNationNum(nationNum);
				s532_Bean.setProviNum(proviNum);
				s532_Bean.setCityNum(cityNum);
				s532_Bean.setSchNum(schNum);
				s532_Bean.setTime(TimeUtil.changeDateY(year));
				list.add(0,s532_Bean);
				
				S532_DAO s532_Dao = new S532_DAO();
				s532_Dao.save(list, year);
			}		
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			return null;
		}
		return list ;
	}
	

}
