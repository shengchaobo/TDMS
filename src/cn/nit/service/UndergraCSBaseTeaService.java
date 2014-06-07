/* 
* @Title: UndergraCSBaseTeaService.java
* @Package cn.bjtu.service
* @Description 
* @author Lei Xia
*      Email: xialei199023@163.com
* @copyright BJTU(C)2014
* @date 2014-5-14 上午10:34:05
* @version V1.0 
*/
package cn.nit.service;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import cn.nit.bean.table5.UndergraCSBaseTeaBean;
import cn.nit.dao.UndergraCSBaseTeaDAO;
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.pojo.table5.UndergraCSBaseTeaPOJO;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;


/**
 * 表511的service类
 * @author lenovo
 */
public class UndergraCSBaseTeaService {

	/**  表511的数据库操作类  */
	private UndergraCSBaseTeaDAO undergraCSBaseTeaDao = new UndergraCSBaseTeaDAO() ;
	
	/**
	 * 表511的service的插入操作
	 * @param undergraCSBaseTea
	 * @return
	 *
	 * @time: 2014-5-14/上午10:52:05
	 */
	public boolean insert(UndergraCSBaseTeaBean undergraCSBaseTea){
		
		setAudit(undergraCSBaseTea) ;
		
		return undergraCSBaseTeaDao.insert(undergraCSBaseTea) ;
	}
	
	public boolean batchInsert(List<UndergraCSBaseTeaBean> list){
		
		return undergraCSBaseTeaDao.batchInsert(list) ;
	}
	
	/**
	 * 获取正在审核的数据
	 * @param conditions  查询条件
	 * @param fillUnitId  教学单位ID号
	 * @param page        当前页
	 * @param rows        页面显示的条数
	 * @return
	 */
	public String auditingData(String conditions, String fillUnitId, int page, int rows){
		
		int total = undergraCSBaseTeaDao.totalAuditingData(conditions, fillUnitId) ;
		List<UndergraCSBaseTeaPOJO> list = undergraCSBaseTeaDao.auditingData(conditions, fillUnitId, page, rows) ;
		Pagition pages = new Pagition(total, list) ;
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
	public boolean update(UndergraCSBaseTeaBean undergraCSBaseTea){
		return undergraCSBaseTeaDao.update(undergraCSBaseTea) ;
	}
	
	public boolean deleteCoursesByIds(String ids){
		
		return undergraCSBaseTeaDao.deleteCoursesByIds(ids) ;
	}
	
	private void setAudit(UndergraCSBaseTeaBean undergraCSBaseTea){
		
		String audit = DIResourceDAO.getAudit(undergraCSBaseTeaDao.getTableName()) ;
		
		String audits[] = audit.split(",") ;
		undergraCSBaseTea.setAudit(audits[0]) ;
	}
	
	public static void main(String args[]){
		UndergraCSBaseTeaService unser = new UndergraCSBaseTeaService() ;
		unser.auditingData(null, null, 1, 10) ;
	}
}
