package cn.nit.service.table4;

import java.util.List;


import cn.nit.bean.table4.T42_Bean;
import cn.nit.bean.table4.T443_Bean;
import cn.nit.dao.table4.T443_Dao;

public class T443_Service {
	
	private T443_Dao talentDao = new T443_Dao();
	
	//根据第几页获取，每页几行获取数据
	
	public List<T443_Bean> getPagetalentList(String conditions, String fillunitID, String rows, String page){
		
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T443_Bean> talent = talentDao.queryPageList(conditions, fillunitID, pagesize, currentpage);
		
		return talent;		
	}
	
	//取得总数
	public int getTotal(String cond, String fillUnitID){
		return talentDao.totalQueryPageList(cond, fillUnitID);
	}
	
	//插入一个bean
	public Boolean insert(T443_Bean bean){
		return talentDao.insert(bean);
	}
	
	//模板导入
	public Boolean batchInsert(List<T443_Bean> list){
		return talentDao.batchInsert(list);
	}
	
	/**
	 * 更新数据
	 * @param 
	 * @return
	 */
	public boolean update(T443_Bean bean){
		return talentDao.update(bean) ;
	}
	
	
	/**
	 * 删除数据
	 * @param 
	 * @return
	 */
	
	public boolean deleteByIds(String ids){
		
		return talentDao.deleteByIds(ids) ;
	}
	
	/**
	 * 得到该条数据审核状态
	 * @param 
	 * @return
	 */
	public int getCheckState(int seqNumber){
		return talentDao.getCheckState(seqNumber) ;
	}
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(int seqNum, int checkState){
		return talentDao.updateCheck(seqNum,checkState) ;
	}
	
	
	/**
	 * 全部审核通过
	 * @param 
	 * @return
	 */
	public boolean checkAll(){
		return talentDao.checkAll() ;
	}
	
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T443_Bean> totalList(String year, int checkState){
		return talentDao.totalList(year, checkState);
	}

}
