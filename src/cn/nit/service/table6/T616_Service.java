package cn.nit.service.table6;

import java.util.List;

import cn.nit.bean.table5.T513_Bean;
import cn.nit.bean.table5.T531_Bean;
import cn.nit.bean.table6.T616_Bean;
import cn.nit.dao.table6.T616_Dao;

public class T616_Service {
	
	T616_Dao T616_Dao=new T616_Dao();
	
	public List<T616_Bean> getYearInfo(String selectYear){
		List<T616_Bean> list = T616_Dao.getYearInfo(selectYear);
		return list;
	}	
	
	/**
	 * 更新数据
	 * @param 
	 * @return
	 */
	public boolean update(T616_Bean bean){
		return T616_Dao.update(bean) ;
	}
	
	/**
	 * 根据seqNumber找相应bean
	 * @param 
	 * @return
	 */
	public T616_Bean findBySeqNum (int seqNum){
		return T616_Dao.findBySeqNum(seqNum) ;
	}
	
	/**
	 * 找当年总计bean
	 * @param 
	 * @return
	 */
	public T616_Bean findSumBean(String name, String year){
		return T616_Dao.findSumBean(name, year) ;
	}
	
	/**批量导入*/
	public boolean batchInsert(List<T616_Bean> list,String year){
		
		return T616_Dao.batchInsert(list,year) ;
	}
	
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(String selectYear, String unitName, int checkState){
		return T616_Dao.updateCheck(selectYear, unitName, checkState) ;
	}
	
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T616_Bean> totalList(String year,int CheckState){
		return T616_Dao.totalList(year,CheckState);
	}
}
