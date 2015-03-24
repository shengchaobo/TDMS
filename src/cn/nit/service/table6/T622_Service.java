package cn.nit.service.table6;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import cn.nit.bean.table4.T411_Bean;
import cn.nit.bean.table6.T621_Bean;
import cn.nit.bean.table6.T622_Bean;
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table6.T621_Dao;
import cn.nit.dao.table6.T622_Dao;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T622_Service {
	
	/**  表621的数据库操作类  */
	private T622_Dao T622_dao = new T622_Dao() ;
	
	/**
	 * 表621的service的插入操作
	 * @param undergraCSBaseTea
	 * @return
	 *
	 * @time: 2014-6-12
	 */
	public boolean insert(T622_Bean  T622_bean){
		
		return T622_dao.insert(T622_bean);
	}
	
	public boolean batchInsert(List<T622_Bean> list){
		
		return T622_dao.batchInsert(list);
	}
	
	/**
	 * 更新数据
	 * @param undergraCSBaseTea {@link cn.nit.bean.table5.UndergraCSBaseTeaBean}实体类
	 * @return
	 */
	public boolean update(T622_Bean T622_bean){
		return T622_dao.update(T622_bean) ;
	}
	
	public boolean deleteItemsByIds(String ids){
		
		return T622_dao.deleteItemsByIds(ids) ;
	}
	
	
	public List<T622_Bean> getPageInfoList(String rows, String page) {
		// TODO Auto-generated method stub
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T622_Bean> pageInfo = T622_dao.queryPageList(pagesize, currentpage);
		
		return pageInfo;	
	}
	
	public List<T622_Bean> getPageInfoList(String cond, String fillunitID,
			String rows, String page) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T622_Bean> pageInfo = T622_dao.queryPageList(cond, fillunitID, pagesize, currentpage);
		
		return pageInfo;	
	}
	

	public int getTotal() {
		// TODO Auto-generated method stub
		return T622_dao.getAllList().size();
	}	
	
	public int getTotal(String cond, String fillunitID) {
		// TODO Auto-generated method stub
		return T622_dao.getAllList(cond, fillunitID).size();
	}
	/**
	 * 得到该条数据审核状态
	 * @param 
	 * @return
	 */
	public int getCheckState(int seqNumber){
		return T622_dao.getCheckState(seqNumber) ;
	}
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(int seqNum, int checkState){
		return T622_dao.updateCheck(seqNum,checkState) ;
	}
	
	
	/**
	 * 全部审核通过
	 * @param 
	 * @return
	 */
	public boolean checkAll(){
		return T622_dao.checkAll() ;
	}
	
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T622_Bean> totalList(String year, int checkState){
		return T622_dao.totalList(year, checkState);
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}





}
