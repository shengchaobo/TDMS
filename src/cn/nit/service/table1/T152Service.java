package cn.nit.service.table1;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import cn.nit.bean.table1.T151_Bean;
import cn.nit.bean.table1.T152_Bean;
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table1.T152DAO;
import cn.nit.pojo.table1.T152POJO;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T152Service {
	/**
	 * 表T1-5-2的service类
	 * @author Lei Xia
	 * @time: 2014-5-14/上午10:34:05
	 */
			/**  表T1-5-2的数据库操作类  */
			private T152DAO t152Dao = new T152DAO() ;
			
			/**
			 * 表T1-5的service的插入操作
			 * @param undergraCSBaseTea
			 * @return
			 *
			 * @time: 2014-5-14/上午10:52:05
			 */
			public boolean insert(T152_Bean t152Bean){
				
				return t152Dao.insert(t152Bean) ;
			}
			
			/**
			 * 教学单位
			 * */
			public String auditingData(String conditions, String fillUnitId, int page, int rows){
					
			    int total = t152Dao.totalAuditingData(conditions, fillUnitId) ;
			    
				List<T152POJO> list = t152Dao.auditingData(conditions, fillUnitId, page, rows) ;
				Pagition pages = new Pagition(total, list) ;
				System.out.println("total:"+total);
				System.out.println("list:"+list.size());
				JSON json = JSONSerializer.toJSON(pages) ;
					
				System.out.println(json.toString()) ;
					
				return json.toString() ;
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

			
			/**
			 * 更新数据
			 * @param undergraCSBaseTea {@link cn.nit.bean.table5.UndergraCSBaseTeaBean}实体类
			 * @return
			 */
			public boolean update(T152_Bean t152Bean){
//			    this.setAudit(t151Bean) ;
				return t152Dao.update(t152Bean) ;
			}
			
			/**按id删除数据*/
			public boolean deleteCoursesByIds(String ids){
				
				return t152Dao.deleteCoursesByIds(ids) ;
			}
			
			/**
			 * 更新该条数据审核状态
			 * @param 
			 * @return
			 */
			public boolean updateCheck(int seqNum, int checkState){
				return t152Dao.updateCheck(seqNum,checkState) ;
			}
			
			/**
			 * 全部审核通过
			 * @param 
			 * @return
			 */
			public boolean checkAll(){
				return t152Dao.checkAll() ;
			}
			
			/**
			 * 得到该条数据审核状态
			 * @param 
			 * @return
			 */
			public int getCheckState(int seqNumber){
				return t152Dao.getCheckState(seqNumber) ;
			}
			
			/**批量导入*/
			public boolean batchInsert(List<T152_Bean> list){
				
				return t152Dao.batchInsert(list) ;
			}

			
			/**审核数据导出*/
			public List<T152_Bean> totalList(String filledID,String year,int CheckState){
				
				return t152Dao.totalList(filledID,year,CheckState) ;
			}
			
			private void setAudit(T152_Bean t152Bean){
				
				String audit = DIResourceDAO.getAudit(t152Dao.getTableName()) ;
				
				String audits[] = audit.split(",") ;
//				t152Bean.setAudit(audits[0]) ;
			}
	
			public static void main(String arg[])
			{
				T152Service ser=new T152Service();
				String string=ser.auditingData(null, null, 1, 1);			
				System.out.println(string);
			}
			

}
