package cn.nit.service.di;

import java.util.List;

import cn.nit.bean.di.DiAwardLevelBean;
import cn.nit.dao.di.DiAwardLevelDao;

public class DiAwardLevelService {
	
	private DiAwardLevelDao awardLevelDao = new DiAwardLevelDao() ;
	/**
	 * 加载所有的学位
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public List<DiAwardLevelBean> getList(){
		return awardLevelDao.getList() ;
	}
	
	/**

	 * 校级及以�?
	 * */
	public List<DiAwardLevelBean> getListPart(){
		return awardLevelDao.getListPart() ;
	}
	
	/**
	 * 校级、系�?
	 * */
	public List<DiAwardLevelBean> getListPartTwo(){
		return awardLevelDao.getListPartTwo() ;
	}
	
	/**

	 * 新增一个学�?
	 * @param degree
	 * @return
	 *
	 * @time: 2014-5-14/下午03:05:00
	 */
	public boolean insert(DiAwardLevelBean degree){
		return awardLevelDao.insert(degree) ;
	}


}
