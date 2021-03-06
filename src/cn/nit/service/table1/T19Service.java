package cn.nit.service.table1;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import cn.nit.bean.table1.T19_Bean;
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table1.T19DAO;

import cn.nit.pojo.table1.T19POJO;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T19Service {
	
	/**  表T1-9的数据库操作类  */
	private T19DAO t19Dao = new T19DAO() ;
	
	/**
	 * 表T1-9的service的插入操作
	 * @param undergraCSBaseTea
	 * @return
	 *
	 * @time: 2014-5-14/上午10:52:05
	 */
	public boolean insert(T19_Bean t19Bean){
		
		return t19Dao.insert(t19Bean) ;
	}
	
	/**
	 * 科研处
	 * */
	public String auditingData(String conditions, String fillUnitId, int page, int rows){
			
	    int total = t19Dao.totalAuditingData(conditions, fillUnitId) ;
	    System.out.println("total:"+total);
		List<T19POJO> list = t19Dao.auditingData(conditions, fillUnitId, page, rows) ;
		Pagition pages = new Pagition(total, list) ;

		JSON json = JSONSerializer.toJSON(pages) ;	
		return json.toString() ;
		}
	
	/**
	 * 更新数据
	 * @param t151Bean {@link cn.nit.bean.table1.T151_Bean}实体类
	 * @return
	 */
	public boolean update(T19_Bean t19Bean){
//	    this.setAudit(t151Bean) ;
		return t19Dao.update(t19Bean) ;
	}
	

//	
	/**按id删除数据*/
	public boolean deleteCoursesByIds(String ids){
		
		return t19Dao.deleteCoursesByIds(ids) ;
	}
	
	/**批量导入*/
	public boolean batchInsert(List<T19_Bean> list){
		
		return t19Dao.batchInsert(list) ;
	}
	/**
	 * 得到该条数据审核状态
	 * @param 
	 * @return
	 */
	public int getCheckState(int seqNumber){
		return t19Dao.getCheckState(seqNumber) ;
	}
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(int seqNum, int checkState){
		return t19Dao.updateCheck(seqNum,checkState) ;
	}
	
	
	/**
	 * 全部审核通过
	 * @param 
	 * @return
	 */
	public boolean checkAll(){
		return t19Dao.checkAll() ;
	}
	
	/**
	 * 审核导出
	 * @param 
	 * @return
	 */
	public List<T19_Bean> totalList(String year,int chekState){
		return t19Dao.totalList(year, chekState) ;
	}
     public static void  main(String arg[]){
    	 T19Service ser=new T19Service();
    	 String info=ser.auditingData(null, null, 1, 8);
    	 System.out.println(info);
     }

}
