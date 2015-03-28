package cn.nit.dao.table5;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.nit.bean.table5.S5301_Bean;
import cn.nit.bean.table5.S5302_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.pojo.table5.S5302POJO;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class S5302_DAO {

	
	/**  数据库表名  */
	private String tableName="S531_TalentTrainItem";
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "Item,Internation,Nation,Provi,City,School,Time,Note";
	
	/**  被统计数据库表名  */
	private String tableName1="T531_TalentTrainItem_Tea$";
	
	/**  被统计数据库表中所有字段  */
	private String field1="Type,ItemLevel,TeaUnit";
	
	/**
	 * 统计原始数据条数
	 * */
	public int countOri(String year){
		 int count = 0;
		 StringBuffer sql =  new StringBuffer();
		 sql.append("select count(*) AS icount from " +tableName1);
		 sql.append(" where Time like '"+year+"%'");
		 
		 Connection conn = DBConnection.instance.getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 try{
			 st = conn.createStatement();
			 rs = st.executeQuery(sql.toString());
			 while(rs.next()){
				 count = rs.getInt("icount");
			 }
//			 System.out.println("cout=" + count);
		 }catch(Exception e){
			 e.printStackTrace();
			 return count;
		 }finally{
				DBConnection.close(rs);
				DBConnection.close(st);	
				DBConnection.close(conn);
			}
		 return count;
		 
	}
	
	
	/**
	 * 统计S53-02中的数据：按类型统计项目数
	 * */
	public List<S5302_Bean> getOriData(String year){
	
		System.out.println("oridata");
		 List<S5302_Bean> list = new ArrayList<S5302_Bean>();
		 
		 StringBuffer sql = new StringBuffer();
//		 sql.append("select TeaUnit AS Item,");
		 sql.append("select DiDepartment.UnitID AS Item,");
		 sql.append(" sum (case when ItemLevel = '国际级' then 1 else 0 end) AS InterLevel , ");
		 sql.append(" sum (case when ItemLevel = '国家级' then 1 else 0 end) AS NationLevel , ");
		 sql.append(" sum (case when ItemLevel = '省级' then 1 else 0 end) AS ProviLevel , ");
		 sql.append(" sum (case when ItemLevel = '市级' then 1 else 0 end) AS CityLevel , ");
		 sql.append(" sum (case when ItemLevel = '校级' then 1 else 0 end) AS SchLevel ");
		 sql.append(" from DiDepartment");
		 sql.append(" left join T531_TalentTrainItem_Tea$ on DiDepartment.UnitID = T531_TalentTrainItem_Tea$.TeaUnit ");
		 sql.append(" where convert(varchar(4),T531_TalentTrainItem_Tea$.Time,120) =" + year);
		 sql.append(" group by DiDepartment.UnitID,UnitName");
		 
//		 System.out.println(sql.toString());
		 Connection conn = DBConnection.instance.getConnection();
		 Statement st = null;
		 ResultSet rs = null;
//		 int sumNum = 0; 
		 int interLevel = 0; int nationLevel = 0; int proviLevel = 0;
		 int cityLevel = 0; int schLevel = 0;
		 
		 try{
			 
			 st = conn.createStatement();
			 rs = st.executeQuery(sql.toString());
			 
			 while(rs.next()){
				 S5302_Bean bean = new S5302_Bean();
//				 int SumNum = 0;
				 String Item = rs.getString("Item");
				 int InterLevel = rs.getInt("InterLevel");
				 interLevel += InterLevel;
				 int NationLevel = rs.getInt("NationLevel");
				 nationLevel += NationLevel;
				 int ProviLevel = rs.getInt("ProviLevel");
				 proviLevel += ProviLevel;
				 int CityLevel  = rs.getInt("CityLevel");
				 cityLevel += CityLevel;
				 int SchLevel = rs.getInt("SchLevel");
				 schLevel += SchLevel;
//				 SumNum  =  InterLevel+NationLevel+ProviLevel+CityLevel+SchLevel;
				
				 bean.setItem(Item);
				 bean.setInternation(InterLevel);
				 bean.setNation(NationLevel);
				 bean.setProvi(ProviLevel);
				 bean.setSchool(SchLevel);
				 bean.setCity(CityLevel);
				 bean.setTime(TimeUtil.changeDateY(year));
				 list.add(bean);
			 }
			 //统计合计
			 
//			 S5302_Bean bean = new S5302_Bean();
////			 sumNum =interLevel+nationLevel+proviLevel+cityLevel+schLevel;
//			 bean.setItem("全校合计：");
//			 bean.setCity(cityLevel);
//			 bean.setInternation(interLevel);
//			 bean.setNation(nationLevel);
//			 bean.setProvi(proviLevel);
//			 bean.setSchool(schLevel);
////			 bean.setSumCSNum(sumNum);
//			 bean.setTime(TimeUtil.changeDateY(year));
//			 	
//			 list.add(0,bean);//将合计数据存放在数据库的 第一个位置
			 
		 }catch (Exception e){
			 e.printStackTrace();
		 }finally{
				DBConnection.close(rs);
				DBConnection.close(st);	
				DBConnection.close(conn);
			}
		 
		 return list;
	}
	
	/**
	 * @param conditions 查询条件
	 * @param fillUnitId 填报人单位号，如果为空，则查询所有未审核的数据，<br>如果不为空，则查询填报人自己单位的所有的数据
	 * @return
	 */
	public List<S5302POJO> loadInfo(String year){
		
		StringBuffer sql = new StringBuffer() ;
		List<S5302POJO> list = null ;
//		sql.append("select * from "+ tableName);
		sql.append("select t.SeqNumber,did.UnitName as Item,t.Item as ItemID,t.Internation,t.Nation," +
				"t.Provi,t.City,t.School,t.Time,t.Note");
		sql.append(" from "+tableName+" as t,DiDepartment as did");
		sql.append(" where did.UnitID = t.Item ");
		sql.append(" and Time like '"+year+"%'");
//		sql.append("select t.SeqNumber,t.TeaUnit,t.UnitID,t.SumCS,t.SmallCSNum,t.SumTeaNum," +
//				"t.QuqlifyTea,t.Professor,t.ViceProfessor,t.JuniorTea,t.JuniorViceProf,t.CSProfNum,t.CSViceProfNum,t.Time,t.Note") ;
//		sql.append(" from "+tableName+" as t,DiDepartment as did ");
//		sql.append(" where did.UnitID = t.UnitID ");
//		sql.append(" and Time like '"+year+"%'");
		
//		sql.append(" order by SeqNumber desc") ;
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			list = DAOUtil.getList(rs, S5302POJO.class) ;
			
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		return list ;
	}
	
	/**
	 * 用于excel
	 * */
	public List<S5302_Bean> totalList(String year){
		
		List<S5302_Bean> list = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select * from "+tableName);
		sql.append(" where Time  like '"+year+"%'");
		sql.append(" and  Item  like '30%'");
		
		Connection conn = DBConnection.instance.getConnection();
		Statement st = null;
		ResultSet rs = null;
		
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			list = DAOUtil.getList(rs, S5302_Bean.class);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConnection.close(rs);
			DBConnection.close(st);	
			DBConnection.close(conn);
		}
		return list;
	}
    
	
	/**
	 * 针对S53-02
	 * 保存数据（两种情况:有数据 ，delete first then batchinsert；无数据，batchinsert）
	 * */
	public boolean save(List<S5302_Bean> list,String year){
 		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year+" and Item like '30%'";
		System.out.println("s5302:"+sql);
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection() ;		
		Statement st = null ;
		ResultSet rs = null ;
		List<S5302_Bean> templist = null ;
		String tempField = "Item,Internation,Nation,Provi,City,School,Time,Note";
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			templist = DAOUtil.getList(rs, S5302_Bean.class) ;
			if(templist.size() != 0){ //存在数据
				String delSql = "delete  from " + tableName + " where convert(varchar(4),Time,120)=" + year+" and Item like '30%'";
				int delflag = st.executeUpdate(delSql.toString());
				if(delflag >0 ){
					flag = DAOUtil.batchInsert(list, tableName, tempField, conn) ;
				}
			}else{
				flag = DAOUtil.batchInsert(list, tableName, tempField, conn) ;
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
 		public static void main(String arg[]){
 			S5302_DAO dao = new S5302_DAO();
 			List<S5302_Bean> list = dao.getOriData("2014");
 			System.out.println(list.size());
// 			for(int i =0;i<list.size();i++){
// 				 S5302_Bean bean = list.get(i);
// 				 System.out.println(bean.getItem());
// 				 System.out.println("市级："+bean.getCity());
// 				 System.out.println("国际级："+bean.getInternation());
// 				 System.out.println("省级："+bean.getProvi());
// 				 System.out.println("校级"+bean.getSchool());
// 				 System.out.println("国家级："+bean.getNation());
// 			 }
 		}

}
