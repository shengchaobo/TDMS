package cn.nit.service.di;

import java.util.List;

import cn.nit.bean.di.DiDegreeBean;
import cn.nit.bean.di.DiEducationBean;
import cn.nit.dao.di.DiEducationDao;


public class DiEducationService {
	
	private DiEducationDao educationDao = new DiEducationDao() ;
	/**
	 * 加载所有的学历
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public List<DiEducationBean> getList(){
		return educationDao.getList() ;
	}
	
	/**
	 * 新增一个学历
	 * @param education
	 * @return
	 *
	 * @time: 2014-5-14/下午03:05:00
	 */
	public boolean insert(DiEducationBean identiType){
		return educationDao.insert(identiType) ;
	}
	
	/**
	 * 更新
	 * @param userinfo
	 * @return
	 */
	public boolean update(DiEducationBean edubean){
		
		return educationDao.update(edubean) ;
	}
	
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	public boolean deleteByIds(String ids){
		return educationDao.deleteByIds(ids) ;
	}
	
	
	/**
	 * 判断中是否已包含该数据
	 */
	
	public boolean hasDegree(String eduID){
		return educationDao.hasEdu(eduID);
	}


}
