package cn.nit.service.di;

import java.util.List;

import cn.nit.bean.di.DiMajorTwoBean;
import cn.nit.dao.di.DiMajorTwoDao;


public class DiMajorTwoService {

	private DiMajorTwoDao degreeDao = new DiMajorTwoDao() ;
	/**
	 * 加载所有的专业
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public List<DiMajorTwoBean> getList(){
		return degreeDao.getList() ;
	}
	
	/**
	 * 新增一个专业
	 * @param majorTwo
	 * @return
	 *
	 * @time: 2014-5-14/下午03:05:00
	 */
	public boolean insert(DiMajorTwoBean major){
		return degreeDao.insert(major) ;
	}


}
