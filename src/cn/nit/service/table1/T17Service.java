package cn.nit.service.table1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import cn.nit.bean.table1.T17_Bean;
import cn.nit.constants.Constants;
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table1.T17DAO;
import cn.nit.pojo.table1.T17POJO;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

/**表1-7操作类*/
public class T17Service {
	
	/**  表T1-7的数据库操作类  */
	private T17DAO t17Dao = new T17DAO() ;
	
	/**
	 * 表T1-5的service的插入操作
	 * @param undergraCSBaseTea
	 * @return
	 *
	 * @time: 2014-5-14/上午10:52:05
	 */
	public boolean insert(T17_Bean t17Bean){
		
		return t17Dao.insert(t17Bean) ;
	}
	
	
	public String auditingData(String conditions, String fillUnitId, int page, int rows){
			
	    int total = t17Dao.totalAuditingData(conditions, fillUnitId) ;
		List<T17POJO> list = t17Dao.auditingData(conditions, fillUnitId, page, rows) ;
		Pagition pages = new Pagition(total, list) ;
//		System.out.println("total:"+total);
//		System.out.println("list:"+list.size());
		JSON json = JSONSerializer.toJSON(pages) ;
			
//		System.out.println(json.toString()) ;
			
		return json.toString() ;
		}
	
	/**
	 * 更新数据
	 * @param undergraCSBaseTea {@link cn.nit.bean.table5.UndergraCSBaseTeaBean}实体类
	 * @return
	 */
	public boolean update(T17_Bean t17Bean){
//	    this.setAudit(t151Bean) ;
		return t17Dao.update(t17Bean) ;
	}

	
	/**导入数据*/
	public boolean batchInsert(List<T17_Bean> list){
			
			return t17Dao.batchInsert(list) ;
		}
	
	/**按id删除数据*/
	public boolean deleteCoursesByIds(String ids){
		
		return t17Dao.deleteCoursesByIds(ids) ;
	}
	
	/**
	 * 得到该条数据审核状态
	 * @param 
	 * @return
	 */
	public int getCheckState(int seqNumber){
		return t17Dao.getCheckState(seqNumber) ;
	}
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(int seqNum, int checkState){
		return t17Dao.updateCheck(seqNum,checkState) ;
	}
	
	
	/**
	 * 全部审核通过
	 * @param 
	 * @return
	 */
	public boolean checkAll(){
		return t17Dao.checkAll() ;
	}
	
	/**
	 *审核导出
	 * @param 
	 * @return
	 */
	public List<T17_Bean> totalList(String year,int checkState){
		return t17Dao.totalList(year, checkState) ;
	}
	
	
	public static void main(String arh[]) throws ParseException
	{
		T17Service ser=new T17Service();
		String info=ser.auditingData(null, null, 1, 4);
		System.out.println(info);
	
		
	}


}
