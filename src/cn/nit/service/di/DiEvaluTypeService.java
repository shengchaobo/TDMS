package cn.nit.service.di;

import java.util.List;

import cn.nit.bean.di.DiEvaluTypeBean;
import cn.nit.dao.di.DiEvaluTypeDao;

public class DiEvaluTypeService {
	
	private DiEvaluTypeDao EvaluTypeDao = new DiEvaluTypeDao() ;
	/**
	 * 加载所有的评教类型
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public List<DiEvaluTypeBean> getList(){
		return EvaluTypeDao.getList() ;
	}
	
	public boolean insert(DiEvaluTypeBean typebean){
		return EvaluTypeDao.insert(typebean) ;
	}
	
	/**
	 * 更新
	 * @param userinfo
	 * @return
	 */
	public boolean update(DiEvaluTypeBean typebean){
		
		return EvaluTypeDao.update(typebean) ;
	}
	
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	public boolean deleteByIds(String ids){
		return EvaluTypeDao.deleteByIds(ids) ;
	}
	
	
	/**
	 * 判断中是否已包含该数据
	 */
	
	public boolean hasType(String typeID){
		return EvaluTypeDao.hasType(typeID);
	}

}
