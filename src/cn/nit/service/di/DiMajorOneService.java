package cn.nit.service.di;

import java.util.List;

import cn.nit.bean.di.DiMajorOneBean;
import cn.nit.dao.di.DiMajorOneDao;


public class DiMajorOneService {

	private DiMajorOneDao degreeDao = new DiMajorOneDao() ;
	/**
	 * 加载所有的专业
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public List<DiMajorOneBean> getList(){
		return degreeDao.getList() ;
	}
	
	/**
	 * 新增一个专业
	 * @param majorOne
	 * @return
	 *
	 * @time: 2014-5-14/下午03:05:00
	 */
	public boolean insert(DiMajorOneBean major){
		return degreeDao.insert(major) ;
	}


}
