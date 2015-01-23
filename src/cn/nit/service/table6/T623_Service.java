package cn.nit.service.table6;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import cn.nit.bean.table6.T623_Bean;
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table6.T623_Dao;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T623_Service {
	
	/**  表621的数据库操作类  */
	private T623_Dao T623_dao = new T623_Dao() ;
	
	/**
	 * 表621的service的插入操作
	 * @param undergraCSBaseTea
	 * @return
	 *
	 * @time: 2014-6-12
	 */
	public boolean insert(T623_Bean  T623_bean){
		
		return T623_dao.insert(T623_bean);
	}
	
	public boolean batchInsert(List<T623_Bean> list){
		
		return T623_dao.batchInsert(list);
	}
	
	/**
	 * 更新数据
	 * @param undergraCSBaseTea {@link cn.nit.bean.table5.UndergraCSBaseTeaBean}实体类
	 * @return
	 */
	public boolean update(T623_Bean T623_bean){
		return T623_dao.update(T623_bean) ;
	}
	
	public boolean deleteItemsByIds(String ids){
		
		return T623_dao.deleteItemsByIds(ids) ;
	}
	
	
	public List<T623_Bean> getPageInfoList(String rows, String page) {
		// TODO Auto-generated method stub
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T623_Bean> pageInfo = T623_dao.queryPageList(pagesize, currentpage);
		
		return pageInfo;	
	}
	
	/**分页显示*/
	public List<T623_Bean> getPageInfoList(String cond, String  fillUnitID,
			String rows, String page) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T623_Bean> pageInfo = T623_dao.queryPageList(cond, fillUnitID, pagesize, currentpage);
		
		return pageInfo;	
	}
	

	public int getTotal() {
		// TODO Auto-generated method stub
		return T623_dao.getAllList().size();
	}	
	
	public int getTotal(String cond, String  fillunitID) {
		// TODO Auto-generated method stub
		return T623_dao.getAllList(cond, fillunitID).size();
	}
	
	/**
	 * 得到该条数据审核状态
	 * @param 
	 * @return
	 */
	public int getCheckState(int seqNumber){
		return T623_dao.getCheckState(seqNumber) ;
	}
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(int seqNum, int checkState){
		return T623_dao.updateCheck(seqNum,checkState) ;
	}
	
	
	/**
	 * 全部审核通过
	 * @param 
	 * @return
	 */
	public boolean checkAll(){
		return T623_dao.checkAll() ;
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}





}
