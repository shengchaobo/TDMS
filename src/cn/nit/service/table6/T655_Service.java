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
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table6.T624_Dao;
import cn.nit.dao.table6.T641_Dao;
import cn.nit.dao.table6.T651_Dao;
import cn.nit.dao.table6.T652_Dao;
import cn.nit.dao.table6.T653_Dao;
import cn.nit.dao.table6.T654_Dao;
import cn.nit.dao.table6.T655_Dao;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T655_Service {
	
	/**  表624的数据库操作类  */
	private T655_Dao T655_dao = new T655_Dao() ;
	
	
	
	public List<T655_Bean> getYearInfo(String selectYear){
		List<T655_Bean> list = T655_dao.getYearInfo(selectYear);
		T655_Bean bean = list.get(0);
		return list;
	}
	
	/**
	 * 更新数据
	 * @param 
	 * @return
	 */
	public boolean update(T655_Bean bean){
		return T655_dao.update(bean) ;
	}
	
	/**
	 * 根据seqNumber找相应bean
	 * @param 
	 * @return
	 */
	public T655_Bean findBySeqNum (int seqNum){
		return T655_dao.findBySeqNum(seqNum) ;
	}
	
	/**
	 * 找当年总计bean
	 * @param 
	 * @return
	 */
	public T655_Bean findSumBean(String name, String year){
		return T655_dao.findSumBean(name, year) ;
	}
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(String selectYear, String unitName, int checkState){
		return T655_dao.updateCheck(selectYear, unitName, checkState) ;
	}
	
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T655_Bean> totalList(String year){
		return T655_dao.totalList(year);
	}
	
	public List<T655_Bean> totalList(String year, int checkState){
		return T655_dao.totalList(year,checkState);
	}
	

	
//	/**
//	 * 表624的service的插入操作
//	 * @param T655_Bean
//	 * @return
//	 *
//	 * @time: 2014-6-12
//	 */
//	public boolean insert(T655_Bean  T655_bean){
//		
//		return T655_dao.insert(T655_bean);
//	}
//	
	public boolean batchInsert(List<T655_Bean> list){
		
		return T655_dao.batchInsert(list);
	}
//	
//	/**
//	 * 更新数据
//	 * @param undergraCSBaseTea {@link cn.nit.bean.table5.UndergraCSBaseTeaBean}实体类
//	 * @return
//	 */
//	public boolean update(T655_Bean T655_bean){
//		return T655_dao.update(T655_bean) ;
//	}
//	
//	public boolean deleteItemsByIds(String ids){
//		
//		return T655_dao.deleteItemsByIds(ids) ;
//	}
//	
//	
//	public List<T655_Bean> getPageInfoList(String rows, String page) {
//		// TODO Auto-generated method stub
//		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
//		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
//		
//		List<T655_Bean> pageInfo = T655_dao.queryPageList(pagesize, currentpage);
//		
//		return pageInfo;	
//	}
//	
//	public List<T655_Bean> getPageInfoList(String cond, Object object,
//			String rows, String page) {
//		// TODO Auto-generated method stub
//		// TODO Auto-generated method stub
//		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
//		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
//		
//		List<T655_Bean> pageInfo = T655_dao.queryPageList(cond, object, pagesize, currentpage);
//		
//		return pageInfo;	
//	}
//	
//	public int getTotal(String cond, Object object) {
//		// TODO Auto-generated method stub
//		return T655_dao.getAllList(cond, object).size();
//	}
//	
//
//
//	public int getTotal() {
//		// TODO Auto-generated method stub
//		return T655_dao.getAllList().size();
//	}
//	
	public double getCET4PassRate(String year){
		return T655_dao.getCET4PassRate(year);
	}
	
	public double getCET6PassRate(String year){
		return T655_dao.getCET6PassRate(year);
	}
	
	public double getJPassRate(String year){
		return T655_dao.getJPassRate(year);
	}
//	
//	public List<T655_Bean> getYearInfo(String selectYear){
//		
//		List<T655_Bean> list=T655_dao.getYearInfo(selectYear);
//	
//		//System.out.println(list.toString());
//         
//		return list;				
//	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//         T655_Service ser = new T655_Service();
//         List<T655_Bean>  list = ser.getPageInfoList("1=1", null, "10", "1");
	}



}
