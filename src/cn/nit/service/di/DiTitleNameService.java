package cn.nit.service.di;

import java.util.List;

import cn.nit.bean.di.DiTitleNameBean;
import cn.nit.dao.di.DiTitleNameDao;


public class DiTitleNameService {

	private DiTitleNameDao titleNameDao = new DiTitleNameDao() ;
	/**
	 * 加载所有的教学职称
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public List<DiTitleNameBean> getList(){
		return titleNameDao.getList() ;
	}
	
	/**
	 * 新增一个教学职称
	 * @param titleName
	 * @return
	 *
	 * @time: 2014-5-14/下午03:05:00
	 */
	public boolean insert(DiTitleNameBean identiType){
		return titleNameDao.insert(identiType) ;
	}

}
