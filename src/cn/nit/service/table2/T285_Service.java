package cn.nit.service.table2;

import java.util.List;

import cn.nit.bean.table2.T285_Bean;
import cn.nit.dao.table2.T285_Dao;

public class T285_Service {
	
	T285_Dao T285_Dao=new T285_Dao();
	
	public List<T285_Bean> getYearInfo(String selectYear){
		List<T285_Bean> list = T285_Dao.getYearInfo(selectYear);
		return list;
	}	
	
	/**
	 * 更新数据
	 * @param 
	 * @return
	 */
	public boolean update(T285_Bean bean){
		return T285_Dao.update(bean) ;
	}
	
	/**
	 * 根据seqNumber找相应bean
	 * @param 
	 * @return
	 */
	public T285_Bean findBySeqNum (int seqNum){
		return T285_Dao.findBySeqNum(seqNum) ;
	}
	
	/**
	 * 找当年总计bean
	 * @param 
	 * @return
	 */
	public T285_Bean findSumBean(String name, String year){
		return T285_Dao.findSumBean(name, year) ;
	}
	
}
