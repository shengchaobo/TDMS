package cn.nit.service.di;

import java.util.List;

import cn.nit.bean.di.DiTalentTypeBean;
import cn.nit.dao.di.DiTalentTypeDao;

public class DiTalentTypeService {
	
	private DiTalentTypeDao talentTypeDao = new DiTalentTypeDao() ;
	/**
	 * 加载所有的学位
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public List<DiTalentTypeBean> getList(){
		return talentTypeDao.getList() ;
	}
	
	/**
	 * 新增一个学位
	 * @param degree
	 * @return
	 *
	 * @time: 2014-5-14/下午03:05:00
	 */
	public boolean insert(DiTalentTypeBean degree){
		return talentTypeDao.insert(degree) ;
	}


}
