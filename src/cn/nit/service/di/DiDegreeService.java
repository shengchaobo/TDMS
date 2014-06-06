package cn.nit.service.di;

import java.util.List;

import cn.nit.bean.di.DiDegreeBean;
import cn.nit.dao.di.DiDegreeDao;


public class DiDegreeService {

	private DiDegreeDao degreeDao = new DiDegreeDao() ;
	/**
	 * 加载所有的学位
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public List<DiDegreeBean> getList(){
		return degreeDao.getList() ;
	}
	
	/**
	 * 新增一个学位
	 * @param degree
	 * @return
	 *
	 * @time: 2014-5-14/下午03:05:00
	 */
	public boolean insert(DiDegreeBean degree){
		return degreeDao.insert(degree) ;
	}


}
