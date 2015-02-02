package cn.nit.service.table2;

import java.util.List;

import cn.nit.bean.table2.T285_Bean;
import cn.nit.bean.table2.T294_Bean;
import cn.nit.dao.table2.T294_Dao;

public class T294_Service {
	
	T294_Dao T294_Dao=new T294_Dao();
	
	public List<T294_Bean> getYearInfo(String selectYear){
		List<T294_Bean> list = T294_Dao.getYearInfo(selectYear);
		return list;
	}
	
	//插入一个bean
	public Boolean insert(T294_Bean bean, String year){
		return T294_Dao.insert(bean, year);
	}
	
	/**
	 * 更新数据
	 * @param 
	 * @return
	 */
	public int update(T294_Bean bean, String year){
		return T294_Dao.update(bean, year) ;
	}
	
	
	/**
	 * 删除数据
	 * @param 
	 * @return
	 */
	
	public boolean deleteByIds(String ids, String year){
		
		return T294_Dao.deleteByIds(ids, year) ;
	}

	/**
	 * 获得某年的捐赠总计
	 * 
	 */
	public double getYearSumDona(String year){
		return T294_Dao.getYearSumDona(year);
	}
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(String selectYear, String unitName, int checkState){
		return T294_Dao.updateCheck(selectYear, unitName, checkState) ;
	}
}
