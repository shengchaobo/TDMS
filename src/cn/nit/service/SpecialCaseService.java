package cn.nit.service;

import java.util.List;

import cn.nit.bean.SpecialCaseBean;
import cn.nit.dao.SpecialCaseDao;


public class SpecialCaseService {
	
	private SpecialCaseDao caseDao = new SpecialCaseDao();
	
	//根据第几页获取，每页几行获取数据
	
	public List<SpecialCaseBean> getCaseInfo(String conditions, String rows, String page){
		
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<SpecialCaseBean> caseList = caseDao.getCaseInfo(conditions, pagesize, currentpage);
		
		return caseList;		
	}
	
	//取得总数
	public int getTotal(String cond){
		return caseDao.totalListCount(cond);
	}
	
	//插入一个bean
	public Boolean insert(SpecialCaseBean bean){
		return caseDao.insert(bean);
	}
	
	/**
	 * 更新数据
	 * @param 
	 * @return
	 */
	public boolean update(SpecialCaseBean bean){
		return caseDao.update(bean) ;
	}
	
	
	/**
	 * 删除数据
	 * @param 
	 * @return
	 */
	
	public boolean deleteByIds(String ids){
		
		return caseDao.deleteByIds(ids) ;
	}
	
	/**
	 * 导出数据
	 * @param 
	 * @return
	 */
	
	public List<SpecialCaseBean> totalList(String RoleID, String fillUnitID){
		
		return caseDao.totalList(RoleID, fillUnitID) ;
	}

}
