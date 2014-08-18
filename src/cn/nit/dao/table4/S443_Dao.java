package cn.nit.dao.table4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import cn.nit.bean.table4.S443_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class S443_Dao {
	
	
	/**  数据库表名  */
	private String tableName = "S443_HighLevelTalent$" ;
	
	/**待统计数据库表名*/
	private String tableName1 = "T443_HighLevelTalent_Per$";
	
	/**  数据自增长字段的主键，必须为自增长字段  */
	private String key = "SeqNumber" ;
	
	/**  数据库表中除了自增长字段的所有字段  */
	private String field = "sumTalent,scienceTalent,engneerTalent,overseasTalent,yangtzeTalent," +
			"youthTalent,expertTalent,excellentTalent,youthTeaTalent,highLevelTalent,youthOverseas," +
			"Time,Note" ;

	

	  /**
	 	 * 模板导入
	 	 * @param diCourseCategories
	 	 * @return
	 	 *
	 	 * @time: 2014-5-14/下午02:34:23
	 	 */

		public boolean save(S443_Bean s443_bean, String year){
			String sql="select * from " + tableName + " where Time like '"+year+"%'";	
			boolean flag=false;
			Connection conn = DBConnection.instance.getConnection() ;
			Statement st = null;
			ResultSet rs = null;
			List<S443_Bean> list=new ArrayList<S443_Bean>();
			S443_Bean bean=new S443_Bean();
			try{
				st=conn.createStatement();
				rs = st.executeQuery(sql);
				list=DAOUtil.getList(rs, S443_Bean.class);
				if(list.size()!=0){
					bean=list.get(0);
					s443_bean.setSeqNumber(bean.getSeqNumber());
					s443_bean.setTime(TimeUtil.changeDateY(year));
					flag=DAOUtil.update(s443_bean, tableName, key, field, conn);
				}else{
					s443_bean.setTime(TimeUtil.changeDateY(year));
					flag=DAOUtil.insert(s443_bean, tableName, field, conn);
					
				}
			}catch (Exception e){
				e.printStackTrace() ;
			}finally{
				DBConnection.close(conn);
				DBConnection.close(rs);
				DBConnection.close(st);			
			}
			return flag;
			
			
		}
		/**得到数据*/
		
		
		public S443_Bean getData(String year){
			
			String sql="select * from "+tableName+" where convert(varchar(4),Time,120)=" + year;
			Connection conn = DBConnection.instance.getConnection() ;
			Statement st = null ;
			ResultSet rs = null ;
			List<S443_Bean> list = null ;
			S443_Bean bean = null;
			try{
				st = conn.createStatement() ;
				rs = st.executeQuery(sql) ;
				list = DAOUtil.getList(rs, S443_Bean.class) ;
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
	 	


		public S443_Bean getYearInfo(String selectYear)
		{
			
		    String querysql="select " +
				"sum (case when Type = '52000' then 1 else 0 end) as scienceTalent," +
				"sum (case when Type = '52001' then 1 else 0 end) as engneerTalent," +
				"sum (case when Type = '52002' then 1 else 0 end) as overseasTalent," +
				"sum (case when Type = '52003' then 1 else 0 end) as yangtzeTalent," +
				"sum (case when Type = '52004' then 1 else 0 end) as youthTalent," +
				"sum (case when Type = '52005' then 1 else 0 end) as expertTalent," +
				"sum (case when Type = '52006' then 1 else 0 end) as excellentTalent," +
				"sum (case when Type = '52007' then 1 else 0 end) as youthTeaTalent," +
				"sum (case when Type = '52008' then 1 else 0 end) as highLevelTalent," +
				"sum (case when Type = '52009' then 1 else 0 end) as youthOverseas " +
				"from "+tableName1+" where convert(varchar(4),Time,120)=" + selectYear;

	   
			System.out.println(querysql);
			Connection conn = DBConnection.instance.getConnection() ;
			Statement st = null ;
			ResultSet rs = null ;
			S443_Bean bean = new S443_Bean() ;
			
			int num1,num2,num3,num4,num5,num6,num7,num8,num9,num10,num11;
			
			try{
				st = conn.createStatement() ;
				rs = st.executeQuery(querysql) ;
				while(rs.next()){
					num2 = rs.getInt("scienceTalent");
					num3 = rs.getInt("engneerTalent");
					num4 = rs.getInt("overseasTalent");
					num5 = rs.getInt("yangtzeTalent");
					num6 = rs.getInt("youthTalent");
					num7 = rs.getInt("expertTalent");
					num8 = rs.getInt("excellentTalent");
					num9 = rs.getInt("youthTeaTalent");
					num10 = rs.getInt("highLevelTalent");
					num11 = rs.getInt("youthOverseas");
					num1 = num2 + num3 + num4 +num5 + num6 +num7 +num8 + num9 + num10 + num11;
					if(num1 == 0){
						return null;
					}
					bean.setSumTalent(num1);
					bean.setScienceTalent(num2);
					bean.setEngneerTalent(num3);
					bean.setOverseasTalent(num4);
					bean.setYangtzeTalent(num5);
					bean.setYouthTalent(num6);
					bean.setExpertTalent(num7);
					bean.setExcellentTalent(num8);
					bean.setYouthTeaTalent(num9);
					bean.setHighLevelTalent(num10);
					bean.setYouthOverseas(num11);


					
				}
				
			
			}catch(Exception e){
				e.printStackTrace() ;
				return null;
			}
			
			return bean ;
		}
		

		
		public static void main(String arg[]){
			//S25_DAO t=new S25_DAO();
		   // List<S25_Bean> list =t.getData("2014");
			//System.out.println(list.size());
		}


}
