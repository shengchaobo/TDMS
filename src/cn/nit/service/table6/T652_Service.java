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
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table6.T624_Dao;
import cn.nit.dao.table6.T641_Dao;
import cn.nit.dao.table6.T651_Dao;
import cn.nit.dao.table6.T652_Dao;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T652_Service {
	
	/**  表624的数据库操作类  */
	private T652_Dao T652_dao = new T652_Dao() ;
	
	/**
	 * 表624的service的插入操作
	 * @param T652_Bean
	 * @return
	 *
	 * @time: 2014-6-12
	 */
	public boolean insert(T652_Bean  T652_bean){
		
		return T652_dao.insert(T652_bean);
	}
	
	/**批量导出*/
	public boolean batchInsert(List<T652_Bean> list){
		
		return T652_dao.batchInsert(list);
	}
	
	/**
	 * 更新数据
	 * @param undergraCSBaseTea {@link cn.nit.bean.table5.UndergraCSBaseTeaBean}实体类
	 * @return
	 */
	public boolean update(T652_Bean T652_bean){
		return T652_dao.update(T652_bean) ;
	}
	
	public boolean deleteItemsByIds(String ids){
		
		return T652_dao.deleteItemsByIds(ids) ;
	}
	
	
	/**
	 * 得到该条数据审核状态
	 * @param 
	 * @return
	 */
	public int getCheckState(int seqNumber){
		return T652_dao.getCheckState(seqNumber) ;
	}
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(int seqNum, int checkState){
		return T652_dao.updateCheck(seqNum,checkState) ;
	}
	
	
	/**
	 * 全部审核通过
	 * @param 
	 * @return
	 */
	public boolean checkAll(){
		return T652_dao.checkAll() ;
	}

	
	public List<T652_Bean> getPageInfoList(String cond, String filledID,
			String rows, String page) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T652_Bean> pageInfo = T652_dao.queryPageList(cond, filledID, pagesize, currentpage);
		
		return pageInfo;	
	}
	
	//总数
	public int getTotal(String cond, String  filledID) {
		// TODO Auto-generated method stub
		return T652_dao.getAllList(cond, filledID).size();
	}
	


	public int getTotal() {
		// TODO Auto-generated method stub
		return T652_dao.getAllList().size();
	}
	
	public int getPaper(String year){
		return T652_dao.getPaper(year);
	}
	
	public List<T652_Bean> getYearInfo(String selectYear){
		
		List<T652_Bean> list=T652_dao.getYearInfo(selectYear);
	
		//System.out.println(list.toString());
         
		return list;				
	}
	
	/**
	 * 用与审核完导出
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T652_Bean> totalList(String fillUnitID, String year, int checkState){
		return T652_dao.totalList(fillUnitID, year, checkState);
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}



}
