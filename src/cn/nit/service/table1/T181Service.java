package cn.nit.service.table1;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import cn.nit.bean.table1.T181Bean;
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table1.T18DAO;

import cn.nit.pojo.table1.T181POJO;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

/**
 * 表T181的service类
 * @author 
 * @time: 2014-5-14/上午10:34:05
 */

	public class T181Service {

			/**  表T181的数据库操作类  */
			private T18DAO t181Dao = new T18DAO() ;
			
			/**
			 * 表T1-5的service的插入操作
			 * @param undergraCSBaseTea
			 * @return
			 *
			 * @time: 2014-5-14/上午10:52:05
			 */
			public boolean insert(T181Bean t181Bean){
				
				return t181Dao.insert(t181Bean) ;
			}
			
			/**
			 * 科研处
			 * */
			public String auditingData(String conditions, String fillDept, int page, int rows){
					
			    int total = t181Dao.totalAuditingData(conditions, fillDept) ;
				List<T181POJO> list = t181Dao.auditingData(conditions, fillDept, page, rows) ;
				Pagition pages = new Pagition(total, list) ;
//				System.out.println("total:"+total);
//				System.out.println("list:"+list.size());
				JSON json = JSONSerializer.toJSON(pages) ;
					
//				System.out.println(json.toString()) ;
					
				return json.toString() ;
				}
			
			/**
			 * 更新数据
			 * @param undergraCSBaseTea {@link cn.nit.bean.table5.UndergraCSBaseTeaBean}实体类
			 * @return
			 */
			public boolean update(T181Bean t181Bean){
//			    this.setAudit(t151Bean) ;
				return t181Dao.update(t181Bean) ;
			}
			
			private void setAudit(T181Bean t181Bean){
				
				String audit = DIResourceDAO.getAudit(t181Dao.getTableName()) ;
				
				String audits[] = audit.split(",") ;
				t181Bean.setAudit(audits[0]) ;
			}
			
			/**按id删除数据*/
			public boolean deleteCoursesByIds(String ids){
				
				return t181Dao.deleteCoursesByIds(ids) ;
			}
			
			/**
			 * 生成查条件
			 * @param seqNum
			 * @param startDate
			 * @param endDate
			 * @return
			 */
			public String gernateAuditingConditions(int seqNum, Date startTime, Date endTime){
				
				if(seqNum == 0 && startTime == null && endTime == null){
					return null ;
				}
				
				StringBuffer sql = new StringBuffer() ;
				
				if(seqNum != 0){
					sql.append(" and SeqNumber=" + seqNum) ;
				}
				
				if(startTime != null){
					sql.append(" and cast(CONVERT(DATE, Time)as datetime)>=cast(CONVERT(DATE, '" 
							+ TimeUtil.changeFormat4(startTime) + "')as datetime)") ;
				}
				
				if(endTime != null){
					sql.append(" and cast(CONVERT(DATE, Time)as datetime)>=cast(CONVERT(DATE, '" 
							+ TimeUtil.changeFormat4(endTime) + "')as datetime)") ;
				}
				
				return sql.toString() ;
			}

			public static void main(String arg[])
			{
				T181Service ser=new T181Service();
				String string=ser.auditingData(null, "1012", 1, 1);			
				System.out.println(string);
			}

			
			
}

	
