package cn.nit.dao.table7;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.nit.bean.table7.S71_Bean;
import cn.nit.bean.table7.S72_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class S72_DAO {
	
	/**  数据库表名  */
	private String tableName = "S72_TeaResReformItem$" ;
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "TeaUnit,UnitID,SumTeaResItem,InterItem,NationItem,ProviItem,CityItem,SchItem,SumTeaAward,InterAward,NationAward,ProviAward,CityAward,SchAward,Time,Note" ;
	
	 /**
 	 * 模板导入
 	 * @param diCourseCategories
 	 * @return
 	 *
 	 * @time: 2014-5-14/下午02:34:23
 	 */
 	public boolean save(List<S72_Bean> list,String year){
 		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year;		
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection() ;		
		Statement st = null ;
		ResultSet rs = null ;
		List<S72_Bean> templist = null ;
		String tempField = "TeaUnit,UnitID,SumTeaResItem,InterItem,NationItem,ProviItem,CityItem,SchItem,SumTeaAward,InterAward,NationAward,ProviAward,CityAward,SchAward,Time,Note";
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			templist = DAOUtil.getList(rs, S72_Bean.class) ;
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
 	

    /**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
 	
 	public List<S72_Bean> totalList(String year){
 		 
 		String sql="select " + key + ","  +  field  +  " from " + tableName
 				+ " where convert(varchar(4),Time,120)=" + year;
 		
 		Connection conn=DBConnection.instance.getConnection();
 		Statement st=null;
 		ResultSet rs=null;
 		List<S72_Bean> list=null;
 		
 		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			list=DAOUtil.getList(rs, S72_Bean.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
 		return list;
 		
 		
 	}
	public List<S72_Bean> getData(String year){

		String sql="select UnitName AS TeaUnit,DiDepartment.UnitID," + 
		" count(ItemLevel) AS SumTeaResItem," +
		" sum(case when ItemLevel='50000' then 1 else 0 end) AS InterItem, "+
		" sum(case when ItemLevel='50001' then 1 else 0 end) AS NationItem, "+
		" sum(case when ItemLevel='50002' then 1 else 0 end) AS ProviItem, "+
		" sum(case when ItemLevel='50003' then 1 else 0 end) AS CityItem, "+
		" sum(case when ItemLevel='50004' then 1 else 0 end) AS SchItem, "+
		" COUNT(AwardLevel) AS SumTeaAward,"+
		" sum(case when AwardLevel='50000' then 1 else 0 end) AS InterAward, "+
		" sum(case when AwardLevel='50001' then 1 else 0 end) AS NationAward, "+
		" sum(case when AwardLevel='50002' then 1 else 0 end) AS ProviAward, "+
		" sum(case when AwardLevel='50003' then 1 else 0 end) AS CityAward, "+
		" sum(case when AwardLevel='50004' then 1 else 0 end) AS SchAward "+
		" from DiDepartment "+
		" left join  T721_TeachResItem_Tea$ on DiDepartment.UnitID = T721_TeachResItem_Tea$.UnitID "+
	    " left join T722_TeachAchieveAward_Tea$ on DiDepartment.UnitID = T722_TeachAchieveAward_Tea$.UnitID "+
	    " and  convert(varchar(4),T721_TeachResItem_Tea$.Time,120) = "  +  year  +  
	    " and "  +  "convert(varchar(4),T722_TeachAchieveAward_Tea$.Time,120) = "  +  year  +  
	    " where DiDepartment.UnitID like '3%' group by DiDepartment.UnitID,UnitName;";
		
		System.out.println(sql);
		
		Connection conn=DBConnection.instance.getConnection();
		
		Statement st= null;
		ResultSet rs= null;
		List<S72_Bean> list=new ArrayList<S72_Bean>();
		int sumTeaResItem=0,interItem=0,nationItem=0,proviItem=0,cityItem=0,schItem=0,sumTeaAward=0,
				interAward=0,nationAward=0,proviAward=0,cityAward=0,schAward=0;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				String teaUnit=rs.getString("TeaUnit");
				String unitID=rs.getString("UnitID");
				
				sumTeaResItem +=rs.getInt("SumTeaResItem");
				interItem     +=rs.getInt("InterItem");
				nationItem    +=rs.getInt("NationItem");
				proviItem     +=rs.getInt("ProviItem");
				cityItem      +=rs.getInt("CityItem");
				schItem       +=rs.getInt("SchItem");
				sumTeaAward   +=rs.getInt("SumTeaAward");
				interAward    +=rs.getInt("InterAward");
				nationAward   +=rs.getInt("NationAward");
				proviAward    +=rs.getInt("ProviAward");
				cityAward     +=rs.getInt("CityAward");
				schAward      +=rs.getInt("SchAward");
				//System.out.println(rs.getString("TeaUnit"));
				S72_Bean bean=new S72_Bean();
				
				bean.setTeaUnit(teaUnit);
				bean.setUnitID(unitID);
				bean.setSumTeaResItem(rs.getInt("SumTeaResItem"));
				bean.setInterItem(rs.getInt("InterItem"));
				bean.setNationItem(rs.getInt("NationItem"));
				bean.setProviItem(rs.getInt("ProviItem"));
				bean.setCityItem(rs.getInt("CityItem"));
				bean.setSchItem(rs.getInt("SchItem"));
				bean.setSumTeaAward(rs.getInt("SumTeaAward"));
				bean.setInterAward(rs.getInt("InterAward"));
				bean.setNationAward(rs.getInt("NationAward"));
				bean.setProviAward(rs.getInt("ProviAward"));
				bean.setCityAward(rs.getInt("CityAward"));
				bean.setSchAward(rs.getInt("SchAward"));
				bean.setTime(TimeUtil.changeDateY(year));
				
				list.add(bean);
				//System.out.println(list.size());
			}
			if(list.size()!=0){
				
				S72_Bean s72_Bean=new S72_Bean();
				
				s72_Bean.setTeaUnit("全校合计");
				s72_Bean.setUnitID("");
				s72_Bean.setSumTeaResItem(sumTeaResItem);
				s72_Bean.setInterItem(interItem);
				s72_Bean.setNationItem(nationItem);
				s72_Bean.setProviItem(proviItem);
				s72_Bean.setCityItem(cityItem);
				s72_Bean.setSchItem(schItem);
				s72_Bean.setSumTeaAward(sumTeaAward);
				s72_Bean.setInterAward(interAward);
				s72_Bean.setNationAward(nationAward);
				s72_Bean.setProviAward(proviAward);
				s72_Bean.setCityAward(cityAward);
				s72_Bean.setSchAward(schAward);
				s72_Bean.setTime(TimeUtil.changeDateY(year));
				System.out.println("123");
				list.add(0, s72_Bean);
				S72_DAO s=new S72_DAO();
				s.save(list, year);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			return null;
		}
		
		return list;
		
	}
	public static void main(String arg[]){
		String sql="select UnitName AS TeaUnit,DiDepartment.UnitID," + 
		" count(ItemLevel) AS SumTeaResItem," +
		" sum(case when ItemLevel='50000' then 1 else 0 end) AS InterItem, "+
		" sum(case when ItemLevel='50001' then 1 else 0 end) AS NationItem, "+
		" sum(case when ItemLevel='50002' then 1 else 0 end) AS ProviItem, "+
		" sum(case when ItemLevel='50003' then 1 else 0 end) AS CityItem, "+
		" sum(case when ItemLevel='50004' then 1 else 0 end) AS SchItem, "+
		" COUNT(AwardLevel) AS SumTeaAward,"+
		" sum(case when AwardLevel='50000' then 1 else 0 end) AS InterAward, "+
		" sum(case when AwardLevel='50001' then 1 else 0 end) AS NationAward, "+
		" sum(case when AwardLevel='50002' then 1 else 0 end) AS ProviAward, "+
		" sum(case when AwardLevel='50003' then 1 else 0 end) AS CityAward, "+
		" sum(case when AwardLevel='50004' then 1 else 0 end) AS SchAward "+
		" from DiDepartment "+
		" left join  T721_TeachResItem_Tea$ on DiDepartment.UnitID = T721_TeachResItem_Tea$.UnitID "+
	    " left join T722_TeachAchieveAward_Tea$ on DiDepartment.UnitID = T722_TeachAchieveAward_Tea$.UnitID "+
	    " and convert(varchar(4),T721_TeachResItem_Tea$.Time,120) = "  +  " 2009 "  +  
	    " and "  +  "convert(varchar(4),T722_TeachAchieveAward_Tea$.Time,120) = "  +  " 2009 "  +  
	    " where "  +  "DiDepartment.UnitID like '3%' group by DiDepartment.UnitID,UnitName;";
		
		System.out.println(sql);
		
	}

}
