package cn.nit.service.table3;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;


import cn.nit.bean.table3.T313_Bean;
import cn.nit.dao.table3.T313_DAO;

import cn.nit.pojo.table3.T313POJO;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;





public class T313_Service {
	private T313_DAO discipDAO = new T313_DAO() ;
	
	/**
	 * 加载所有的课程类别数据字典表的内容
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public boolean insert(T313_Bean discipBean){
		return discipDAO.insert(discipBean) ;
		}
	
	
	public boolean batchInsert(List<T313_Bean> list){
		
		return discipDAO.batchInsert(list) ;
	}
	
	public String auditingData(String conditions, String fillDept, int page, int rows){
		
	    int total = discipDAO.totalAuditingData(conditions, fillDept) ;
		List<T313_Bean> list = discipDAO.auditingData(conditions, fillDept, page, rows) ;
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
	public boolean update(T313_Bean t313Bean){
//	    this.setAudit(t151Bean) ;
		return discipDAO.update(t313Bean) ;
	}
	
	public boolean deleteCoursesByIds(String ids){
		
		return discipDAO.deleteCoursesByIds(ids) ;
	}
	

	
    public static void main(String arg[]){
   	 T313_Service ser=new T313_Service();
   	 String info=ser.auditingData(null, null, 1, 4);
   	 System.out.println(info);
    }






}
