package cn.nit.service.table1;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import cn.nit.bean.table1.T151_Bean;
import cn.nit.bean.table1.T17_Bean;
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table1.T151DAO;
import cn.nit.pojo.table1.T151POJO;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

/**
 * 表T1-5的service类
 * @author Lei Xia
 * @time: 2014-5-14/上午10:34:05
 */
    public class T151Service {
		/**  表T1-5的数据库操作类  */
		private T151DAO t151Dao = new T151DAO() ;
		
		/**
		 * 表T1-5的service的插入操作
		 * @param undergraCSBaseTea
		 * @return
		 *
		 * @time: 2014-5-14/上午10:52:05
		 */
		public boolean insert(T151_Bean t151Bean){
			
			return t151Dao.insert(t151Bean) ;
		}
		
		/**
		 * 科研处
		 * */
		public String auditingData(String conditions, String fillUnitId, int page, int rows){
				
		    int total = t151Dao.totalAuditingData(conditions, fillUnitId) ;
			List<T151POJO> list = t151Dao.auditingData(conditions, fillUnitId, page, rows) ;
			Pagition pages = new Pagition(total, list) ;
			JSON json = JSONSerializer.toJSON(pages) ;	
			return json.toString() ;
			}
		
		/**
		 * 更新数据
		 * @param t151Bean {@link cn.nit.bean.table1.T151_Bean}实体类
		 * @return
		 */
		public boolean update(T151_Bean t151Bean){
//		    this.setAudit(t151Bean) ;
			return t151Dao.update(t151Bean) ;
		}
		
//		private void setAudit(T151Bean t151Bean){
//			
//			String audit = DIResourceDAO.getAudit(t151Dao.getTableName()) ;
//			
//			String audits[] = audit.split(",") ;
////			t151Bean.setAudit(audits[0]) ;
//		}
		
		/**按id删除数据*/
		public boolean deleteCoursesByIds(String ids){
			
			return t151Dao.deleteCoursesByIds(ids) ;
		}
		
		/**
		 * 更新该条数据审核状态
		 * @param 
		 * @return
		 */
		public boolean updateCheck(int seqNum, int checkState){
			return t151Dao.updateCheck(seqNum,checkState) ;
		}
		
		/**
		 * 全部审核通过
		 * @param 
		 * @return
		 */
		public boolean checkAll(){
			return t151Dao.checkAll() ;
		}
		

		/**
		 * 得到该条数据审核状态
		 * @param 
		 * @return
		 */
		public int getCheckState(int seqNumber){
			return t151Dao.getCheckState(seqNumber) ;
		}
		

		
		/**批量导入*/
		public boolean batchInsert(List<T151_Bean> list){
			
			return t151Dao.batchInsert(list) ;
		}
		
		/**审核数据导出*/
		public List<T151_Bean> totalList(String year){
			
			return t151Dao.totalList(year) ;
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
			T151Service ser=new T151Service();
           String flag=ser.auditingData(null, null, 1, 1);	
			System.out.println(flag);
			
		}

		
		
	}
