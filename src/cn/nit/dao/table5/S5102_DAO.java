package cn.nit.dao.table5;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import cn.nit.bean.table5.S5101_Bean;
import cn.nit.bean.table5.S5102_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.pojo.table5.S5102POJO;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

/**按教学单位统计*/
public class S5102_DAO {
	
	/**  数据库表名  */
	private String tableName="S511_UndergraCourseInfo";
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "Item,TheoPraNum,TheoPraRatio,InClassNum,InClassRatio,PraNum,PraRatio,ExpNum,ExpRatio,Time,Note";
	
	/**  被统计数据库表名  */
	private String tableName1="T511_UndergraCSBase_Tea$";
	
	/**  被统计数据库表中所有字段  */
	private String field1="UnitID,CSType";
	
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
			 System.out.println("cout=" + count);
		 }catch(Exception e){
			 e.printStackTrace();
			 return count;
		 }finally{
				DBConnection.close(conn);
			}
		 return count;
		 
	}
	
	
	/**
	 * 统计S51-02中的数据：按教学单位统计项目数
	 * */
	public List<S5102_Bean> getOriData(String year){
		
		 List<S5102_Bean> list = new ArrayList<S5102_Bean>();
		 
		 StringBuffer sql = new StringBuffer();
		 	sql.append("select DiDepartment.UnitID as Item,");
//			sql.append(" Count(CSUnit) as SumCS,");
			sql.append(" sum(case when CSType = '22000' then 1 else 0 end) AS TheoPraNum , ");
			sql.append(" sum(case when CSType = '22001' then 1 else 0 end) AS InClassNum,");
			sql.append(" sum(case when CSType = '22002' then 1 else 0 end) AS PraNum,");
			sql.append(" sum(case when CSType = '22003' then 1 else 0 end) AS ExpNum ");
			sql.append(" from DiDepartment ");
			sql.append(" left join T511_UndergraCSBase_Tea$ on DiDepartment.UnitID = T511_UndergraCSBase_Tea$.UnitID ");
			sql.append(" where convert(varchar(4),T511_UndergraCSBase_Tea$.Time,120) =" + year);
			sql.append(" group by DiDepartment.UnitID,UnitName");
		 
		 System.out.println(sql.toString());
		 Connection conn = DBConnection.instance.getConnection();
		 Statement st = null;
		 ResultSet rs = null;
//		 int sumNum = 0; 
//		 int interLevel = 0; int nationLevel = 0; int proviLevel = 0;
//		 int cityLevel = 0; int schLevel = 0;
		 
		 try{
			 
			 st = conn.createStatement();
			 rs = st.executeQuery(sql.toString());
			 int TheoPraNum =0;int InClassNum = 0;int PraNum = 0;int ExpNum = 0;
			 double TheoPraRatio = 0;double  InClassRatio = 0; double PraRatio = 0;double  ExpRatio= 0;
			 while(rs.next()){
				 S5102_Bean bean = new S5102_Bean();
				 String Item = rs.getString("Item");
				 TheoPraNum = rs.getInt("TheoPraNum");
				 InClassNum = rs.getInt("InClassNum");
				 PraNum = rs.getInt("PraNum");
				 ExpNum  = rs.getInt("ExpNum");
				 
				 int sum = TheoPraNum+InClassNum+PraNum+ExpNum;
				 if(sum!=0){
					 TheoPraRatio = this.toDouble(TheoPraNum, sum);
//					 System.out.println(TheoPraRatio);
					 InClassRatio = this.toDouble(InClassNum,sum);
//					 System.out.println(InClassRatio);
					 PraRatio = this.toDouble(PraNum, sum);
//					 System.out.println(PraRatio);
					 ExpRatio = this.toDouble(ExpNum,sum);
//					 System.out.println(ExpRatio);
				 }

				
				 bean.setItem(Item);
				 bean.setExpNum(ExpNum);
				 bean.setExpRatio(ExpRatio);
				 bean.setInClassNum(InClassNum);
				 bean.setInClassRatio(InClassRatio);
				 bean.setPraNum(PraNum);
				 bean.setPraRatio(PraRatio);
				 bean.setTheoPraNum(TheoPraNum);
				 bean.setTheoPraRatio(TheoPraRatio);
				 bean.setTime(TimeUtil.changeDateY(year));
				 list.add(bean);
			 }
//			 //统计合计
//			 
//			 S5301_Bean bean = new S5301_Bean();
////			 sumNum =interLevel+nationLevel+proviLevel+cityLevel+schLevel;
//			 bean.setItem("全校合计：");
//			 bean.setCityLevel(cityLevel);
//			 bean.setInterLevel(interLevel);
//			 bean.setNationLevel(nationLevel);
//			 bean.setProviLevel(proviLevel);
//			 bean.setSchLevel(schLevel);
////			 bean.setSumCSNum(sumNum);
//			 bean.setTime(TimeUtil.changeDateY(year));
//			 	
//			 list.add(0,bean);//将合计数据存放在数据库的 第一个位置
			 
		 }catch (Exception e){
			 e.printStackTrace();
		 }finally{
				DBConnection.close(conn);
			}
		 
		 return list;
	}
	
	/**
	 * @param conditions 查询条件
	 * @param fillUnitId 填报人单位号，如果为空，则查询所有未审核的数据，<br>如果不为空，则查询填报人自己单位的所有的数据
	 * @return
	 */
	public List<S5102POJO> loadInfo(String year){
		
		StringBuffer sql = new StringBuffer() ;
		List<S5102POJO> list = null ;
		sql.append("select t.SeqNumber,did.UnitName as Item,t.Item as ItemID,t.TheoPraNum,t.TheoPraRatio,t.InClassNum,t.InClassRatio,t.PraNum,t.PraRatio," +
				"t.ExpNum,t.ExpRatio,t.Time,t.Note");
		sql.append(" from "+tableName+" as t,DiDepartment as did");
		sql.append(" where did.UnitID = t.Item ");
		sql.append(" and Time like '"+year+"%'");
//		sql.append("select * from "+ tableName);
//		sql.append(" where Time like '"+year+"%'");
//		sql.append("  and Item like '30%'");
//		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			list = DAOUtil.getList(rs, S5102POJO.class) ;
			
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(conn);
		}
		return list ;
	}
	
	/**
	 * 用于excel
	 * */
	public List<S5102POJO> totalList(String year){
		
		List<S5102POJO> list = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select t.SeqNumber,did.UnitName as Item,t.Item as ItemID,t.TheoPraNum,t.TheoPraRatio,t.InClassNum,t.InClassRatio,t.PraNum,t.PraRatio," +
		"t.ExpNum,t.ExpRatio,t.Time,t.Note");
		sql.append(" from "+tableName+" as t,DiDepartment as did");
		sql.append(" where did.UnitID = t.Item ");
		sql.append(" and Time like '"+year+"%'");
		
		Connection conn = DBConnection.instance.getConnection();
		Statement st = null;
		ResultSet rs = null;
		
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			list = DAOUtil.getList(rs, S5102POJO.class);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConnection.close(conn);
		}
		return list;
	}
    
	
	/**
	 * 针对S53-01
	 * 保存数据（两种情况:有数据 ，delete first then batchinsert；无数据，batchinsert）
	 * */
	public boolean save(List<S5102_Bean> list,String year){
 		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year+" and Item like '30%'";
		System.out.println("s5102:"+sql);
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection() ;		
		Statement st = null ;
		ResultSet rs = null ;
		List<S5102_Bean> templist = null ;
		String tempField = "Item,TheoPraNum,TheoPraRatio,InClassNum,InClassRatio,PraNum,PraRatio,ExpNum,ExpRatio,Time,Note";
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			templist = DAOUtil.getList(rs, S5102_Bean.class) ;
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
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
 		
 		return flag ;
 		
 	}
	
	/**转换成保存两位小数的double*/
	public double toDouble(int a,int b){
		
		double d1=((double)a/(double)b)*100;
		DecimalFormat df = new DecimalFormat("0.00");
		String str = df.format(d1);
		double d=Double.parseDouble(str);
		return d;
		
	}
	
	public static void main(String arg[]){
		S5102_DAO dao= new S5102_DAO();
		List<S5102_Bean> list = dao.getOriData("2014");
		for(int i=0;i<list.size();i++){
			S5102_Bean bean = list.get(i);
			System.out.println("项目："+bean.getItem());
			System.out.println("实验课："+bean.getExpNum());
			System.out.println("实验课比列："+bean.getExpRatio());
			System.out.println("理论课（不含实践）："+bean.getInClassNum());
			System.out.println("理论课（含实践）："+bean.getPraNum());
			System.out.println("集中性实践环节："+bean.getTheoPraNum());
		}
	
	}
}
