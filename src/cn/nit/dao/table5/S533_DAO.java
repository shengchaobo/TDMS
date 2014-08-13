package cn.nit.dao.table5;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import cn.nit.bean.table5.S532_Bean;
import cn.nit.bean.table5.S533_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class S533_DAO {
	/**  数据库表名  */
	private String tableName = "S533_ExpInfo$" ;
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "TeaUnit,UnitID,HasExpCourseNum,ExpCourseNum,ExpTeachNum,ExpRatio,Time,Note" ;

	 /**
		 * 获取字典表的所有数据
		 * @return
		 *
		 * @time: 2014-5-14/下午02:34:42
		 */
		public List<S533_Bean> totalList(String year){
			
			String sql = "select " + key + "," +field + " from " + tableName 
					+ " where convert(varchar(4),Time,120)=" + year;		
			Connection conn = DBConnection.instance.getConnection() ;
			Statement st = null ;
			ResultSet rs = null ;
			List<S533_Bean> list = null ;
			System.out.println(sql);
			try{
				st = conn.createStatement() ;
				rs = st.executeQuery(sql) ;
				list = DAOUtil.getList(rs, S533_Bean.class) ;
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
 	public boolean save(List<S533_Bean> list,String year){
 		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year;		
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection() ;		
		Statement st = null ;
		ResultSet rs = null ;
		List<S533_Bean> templist = null ;
		String tempField = "TeaUnit,UnitID,HasExpCourseNum,ExpCourseNum,ExpTeachNum,ExpRatio,Time,Note";
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			templist = DAOUtil.getList(rs, S533_Bean.class) ;
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
	
	
	
	public List<S533_Bean> getData(String year){
		
		String querysql=" select UnitName AS TeaUnit,DiDepartment.UnitID, " +
				        " sum(ExpCSNum) AS HasExpCourseNum, "+
				        " sum(IndepentExpCSNum) AS ExpCourseNum, "+
				        " sum(DesignExpCSNum) AS ExpTeachNum, "+
				        " avg(ExpRatio) AS ExpRatio "+
				        " from DiDepartment "+
				        " left join T533_ByMajExpInfo_TeaTea$ on DiDepartment.UnitID = T533_ByMajExpInfo_TeaTea$.UnitID "+
					    " where convert(varchar(4),T533_ByMajExpInfo_TeaTea$.Time,120) = "  +  year  +  
					    " and "  +  "DiDepartment.UnitID like '3%' group by DiDepartment.UnitID,UnitName;";
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;	
		List<S533_Bean> list = new ArrayList<S533_Bean>() ;
		int hasExpCourseNum=0,expCourseNum=0,expTeachNum=0;
		double expRatio1=0,expRatio=0; 
		try {
			
			st = conn.createStatement() ;
			rs = st.executeQuery(querysql) ;
			
			while(rs.next()){
				String teaUnit=rs.getString("TeaUnit");
				String unitId=rs.getString("UnitID");
				hasExpCourseNum +=rs.getInt("HasExpCourseNum");
				expCourseNum +=rs.getInt("ExpCourseNum");
				expTeachNum +=rs.getInt("ExpTeachNum");
				expRatio1 +=rs.getDouble("ExpRatio");
				
				
				S533_Bean s533_Bean=new S533_Bean();
				s533_Bean.setTeaUnit(teaUnit);
				s533_Bean.setUnitID(unitId);
				s533_Bean.setHasExpCourseNum(rs.getInt("HasExpCourseNum"));
				s533_Bean.setExpCourseNum(rs.getInt("ExpCourseNum"));
				s533_Bean.setExpTeachNum(rs.getInt("ExpTeachNum"));
				s533_Bean.setExpRatio(this.toDouble1(rs.getDouble("ExpRatio")));
				s533_Bean.setTime(TimeUtil.changeDateY(year));
				list.add(s533_Bean);
				
				System.out.println(list.size());
			}
			if(list.size()!=0){
				expRatio   = this.toDouble(expRatio1, (list.size()-1));
				S533_Bean s533_Bean=new S533_Bean();			
				s533_Bean.setTeaUnit("全校合计");
				s533_Bean.setUnitID("");
				s533_Bean.setHasExpCourseNum(hasExpCourseNum);
				s533_Bean.setExpCourseNum(expCourseNum);
				s533_Bean.setExpTeachNum(expTeachNum);
				s533_Bean.setExpRatio(expRatio);
				s533_Bean.setTime(TimeUtil.changeDateY(year));
				list.add(0,s533_Bean);
				
				S533_DAO s533_Dao = new S533_DAO();
				s533_Dao.save(list, year);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			return null;
		}
		return list ;
	}
	
	
	/**转换成保存两位小数的double*/
	public double toDouble(double a,int b){
		
		double d1=(a/(double)b)*100;
		DecimalFormat df = new DecimalFormat("0.00");
		String str = df.format(d1);
		double d=Double.parseDouble(str);
		return d;
	}
	
	/**转换成保存两位小数的double*/
	public double toDouble1(double a){
		a = a*100;
		DecimalFormat df = new DecimalFormat("0.00");
		String str = df.format(a);
		double d=Double.parseDouble(str);
		return d;
	}

}
