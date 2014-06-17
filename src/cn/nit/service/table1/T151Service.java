package cn.nit.service.table1;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import cn.nit.bean.table1.T151Bean;
import cn.nit.bean.table1.T17Bean;
import cn.nit.bean.table5.UndergraCSBaseTeaBean;
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
		public boolean insert(T151Bean t151Bean){
			
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
		 * @param t151Bean {@link cn.nit.bean.table1.T151Bean}实体类
		 * @return
		 */
		public boolean update(T151Bean t151Bean){
//		    this.setAudit(t151Bean) ;
			return t151Dao.update(t151Bean) ;
		}
		
		private void setAudit(T151Bean t151Bean){
			
			String audit = DIResourceDAO.getAudit(t151Dao.getTableName()) ;
			
			String audits[] = audit.split(",") ;
			t151Bean.setAudit(audits[0]) ;
		}
		
		/**按id删除数据*/
		public boolean deleteCoursesByIds(String ids){
			
			return t151Dao.deleteCoursesByIds(ids) ;
		}
		
		/**批量导入*/
		public boolean batchInsert(List<T151Bean> list){
			
			return t151Dao.batchInsert(list) ;
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
//			String string=ser.auditingData(null, null, 1, 2);			
//			System.out.println(string);
			T151Bean t151Bean=new T151Bean();
			t151Bean.setBeginYear(new Date());
			t151Bean.setBiOpen(false);
			t151Bean.setBuildCondition(true);
			t151Bean.setHouseArea(20.32);
			t151Bean.setNote("维尼夫妇");
			t151Bean.setOpenCondition("宋茜最好看");
			t151Bean.setResInsID("200101");
			t151Bean.setResInsName("江西省水文水资源与水环境重点实验室");
			t151Bean.setSeqNumber(5);
			t151Bean.setTeaUnit("党委办公室（院长办公室）");
			t151Bean.setTime(new Date());
			t151Bean.setType("33001");
			t151Bean.setUnitID("1001");
			boolean flag=ser.update(t151Bean);
			System.out.println(flag);
			
		}

		
		
	}
