package cn.nit.service.table4;

import java.util.List;


import cn.nit.bean.table4.T42_Bean;
import cn.nit.bean.table4.T442_Bean;
import cn.nit.dao.table4.T442_Dao;

public class T442_Service {
	
	private T442_Dao tutorDao = new T442_Dao();
	
	//根据第几页获取，每页几行获取数据
	
	public List<T442_Bean> getPagetutorList(String conditions, String fillunitID, String rows, String page){
		
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T442_Bean> tutor = tutorDao.queryPageList(conditions, fillunitID, pagesize, currentpage);
		
		return tutor;		
	}
	
	//取得总数
	public int getTotal(String cond, String fillUnitID){
		return tutorDao.totalQueryPageList(cond, fillUnitID);
	}
	
	//插入一个bean
	public Boolean insert(T442_Bean bean){
		return tutorDao.insert(bean);
	}
	
	//模板导入
	public Boolean batchInsert(List<T442_Bean> list){
		return tutorDao.batchInsert(list);
	}
	
	/**
	 * 更新数据
	 * @param 
	 * @return
	 */
	public boolean update(T442_Bean bean){
		return tutorDao.update(bean) ;
	}
	
	
	/**
	 * 删除数据
	 * @param 
	 * @return
	 */
	
	public boolean deleteByIds(String ids){
		
		return tutorDao.deleteByIds(ids) ;
	}
	
	/**
	 * 得到该条数据审核状态
	 * @param 
	 * @return
	 */
	public int getCheckState(int seqNumber){
		return tutorDao.getCheckState(seqNumber) ;
	}
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(int seqNum, int checkState){
		return tutorDao.updateCheck(seqNum,checkState) ;
	}
	
	
	/**
	 * 全部审核通过
	 * @param 
	 * @return
	 */
	public boolean checkAll(){
		return tutorDao.checkAll() ;
	}
	
	/**
	 * 审核数据导出
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T442_Bean> totalList(String year, int checkState){
		return tutorDao.totalList(year, checkState);
	}


}
