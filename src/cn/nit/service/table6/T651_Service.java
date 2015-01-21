package cn.nit.service.table6;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import cn.nit.bean.table4.S443_Bean;
import cn.nit.bean.table6.S65_Bean;
import cn.nit.bean.table6.T621_Bean;
import cn.nit.bean.table6.T624_Bean;
import cn.nit.bean.table6.T641_Bean;
import cn.nit.bean.table6.T651_Bean;
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table6.T624_Dao;
import cn.nit.dao.table6.T641_Dao;
import cn.nit.dao.table6.T651_Dao;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T651_Service {
	
	/**  表624的数据库操作类  */
	private T651_Dao T651_dao = new T651_Dao() ;
	
	/**
	 * 表624的service的插入操作
	 * @param T651_Bean
	 * @return
	 *
	 * @time: 2014-6-12
	 */
	public boolean insert(T651_Bean  T651_bean){
		
		return T651_dao.insert(T651_bean);
	}
	
	public boolean batchInsert(List<T651_Bean> list){
		
		return T651_dao.batchInsert(list);
	}
	
	/**
	 * 更新数据
	 * @param undergraCSBaseTea {@link cn.nit.bean.table5.UndergraCSBaseTeaBean}实体类
	 * @return
	 */
	public boolean update(T651_Bean T651_bean){
		return T651_dao.update(T651_bean) ;
	}
	
	public boolean deleteItemsByIds(String ids){
		
		return T651_dao.deleteItemsByIds(ids) ;
	}
	
	
//	public List<T651_Bean> getPageInfoList(String rows, String page) {
//		// TODO Auto-generated method stub
//		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
//		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
//		
//		List<T651_Bean> pageInfo = T651_dao.queryPageList(pagesize, currentpage);
//		
//		return pageInfo;	
//	}
	
	public List<T651_Bean> getPageInfoList(String cond, String  filledID,
			String rows, String page) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T651_Bean> pageInfo = T651_dao.queryPageList(cond, filledID, pagesize, currentpage);
		
		return pageInfo;	
	}
	
	public int getTotal(String cond, String filledID) {
		// TODO Auto-generated method stub
		return T651_dao.totalQueryPageList(cond, filledID);
	}
	


//	public int getTotal() {
//		// TODO Auto-generated method stub
//		return T651_dao.getAllList().size();
//	}

	public S65_Bean getStatic(String year){
		//System.out.println("getstatic");
		return T651_dao.getStatic(year);
	}
	
	public List<T651_Bean> getYearInfo(String selectYear){
		
		List<T651_Bean> list=T651_dao.getYearInfo(selectYear);
	
		//System.out.println(list.toString());
         
		return list;				
	}
	
	/**
	 * 得到该条数据审核状态
	 * @param 
	 * @return
	 */
	public int getCheckState(int seqNumber){
		return T651_dao.getCheckState(seqNumber) ;
	}
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(int seqNum, int checkState){
		return T651_dao.updateCheck(seqNum,checkState) ;
	}
	
	
	/**
	 * 全部审核通过
	 * @param 
	 * @return
	 */
	public boolean checkAll(){
		return T651_dao.checkAll() ;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}



}
