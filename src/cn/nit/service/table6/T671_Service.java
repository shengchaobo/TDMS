package cn.nit.service.table6;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import cn.nit.bean.table6.T621_Bean;
import cn.nit.bean.table6.T624_Bean;
import cn.nit.bean.table6.T641_Bean;
import cn.nit.bean.table6.T651_Bean;
import cn.nit.bean.table6.T652_Bean;
import cn.nit.bean.table6.T653_Bean;
import cn.nit.bean.table6.T654_Bean;
import cn.nit.bean.table6.T655_Bean;
import cn.nit.bean.table6.T657_Bean;
import cn.nit.bean.table6.T658_Bean;
import cn.nit.bean.table6.T659_Bean;
import cn.nit.bean.table6.T66_Bean;
import cn.nit.bean.table6.T671_Bean;
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table6.T624_Dao;
import cn.nit.dao.table6.T641_Dao;
import cn.nit.dao.table6.T651_Dao;
import cn.nit.dao.table6.T652_Dao;
import cn.nit.dao.table6.T653_Dao;
import cn.nit.dao.table6.T654_Dao;
import cn.nit.dao.table6.T655_Dao;
import cn.nit.dao.table6.T657_Dao;
import cn.nit.dao.table6.T658_Dao;
import cn.nit.dao.table6.T659_Dao;
import cn.nit.dao.table6.T66_Dao;
import cn.nit.dao.table6.T671_Dao;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T671_Service {
	
	/**  表624的数据库操作类  */
	private T671_Dao T671_dao = new T671_Dao() ;
	
	/**
	 * 表624的service的插入操作
	 * @param T671_Bean
	 * @return
	 *
	 * @time: 2014-6-12
	 */
	public boolean insert(T671_Bean  T671_bean){
		
		return T671_dao.insert(T671_bean);
	}
	
	public boolean batchInsert(List<T671_Bean> list){
		
		return T671_dao.batchInsert(list);
	}
	
	/**
	 * 更新数据
	 * @param undergraCSBaseTea {@link cn.nit.bean.table5.UndergraCSBaseTeaBean}实体类
	 * @return
	 */
	public boolean update(T671_Bean T671_bean){
		return T671_dao.update(T671_bean) ;
	}
	
	public boolean deleteItemsByIds(String ids){
		
		return T671_dao.deleteItemsByIds(ids) ;
	}
	
	
	public List<T671_Bean> getPageInfoList(String rows, String page) {
		// TODO Auto-generated method stub
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T671_Bean> pageInfo = T671_dao.queryPageList(pagesize, currentpage);
		
		return pageInfo;	
	}
	
	/**用以分页显示*/
	public List<T671_Bean> getPageInfoList(String cond, String fillUnitID,
			String rows, String page) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T671_Bean> pageInfo = T671_dao.queryPageList(cond, fillUnitID, pagesize, currentpage);
		
		return pageInfo;	
	}
	
	/**显示总数*/
	public int getTotal(String cond, String fillUnitID) {
		// TODO Auto-generated method stub
		return T671_dao.getAllList(cond, fillUnitID).size();
	}
	
	/**
	 * 得到该条数据审核状态
	 * @param 
	 * @return
	 */
	public int getCheckState(int seqNumber){
		return T671_dao.getCheckState(seqNumber) ;
	}
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(int seqNum, int checkState){
		return T671_dao.updateCheck(seqNum,checkState) ;
	}
	
	
	/**
	 * 全部审核通过
	 * @param 
	 * @return
	 */
	public boolean checkAll(){
		return T671_dao.checkAll() ;
	}


	public int getTotal() {
		// TODO Auto-generated method stub
		return T671_dao.getAllList().size();
	}
	
	/**
	 * 用与审核数据的导出
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T671_Bean> totalList(String year, int checkState){
		return T671_dao.totalList(year, checkState);
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}



}
