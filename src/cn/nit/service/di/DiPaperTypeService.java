package cn.nit.service.di;

import java.util.List;

import cn.nit.bean.di.DiEvaluTypeBean;
import cn.nit.bean.di.DiPaperTypeBean;
import cn.nit.dao.di.DiPaperTypeDao;




public class DiPaperTypeService {
	
	private DiPaperTypeDao paperTypeDao = new DiPaperTypeDao() ;
	/**
	 * 加载所有的论文类型
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public List<DiPaperTypeBean> getList(){
		return paperTypeDao.getList() ;
	}
	
	/**
	 * 新增一个论文类型
	 * @param PaperType
	 * @return
	 *
	 * @time: 2014-5-14/下午03:05:00
	 */
	public boolean insert(DiPaperTypeBean Type){
		return paperTypeDao.insert(Type) ;
	}
	
	/**
	 * 更新
	 * @param userinfo
	 * @return
	 */
	public boolean update(DiPaperTypeBean typebean){
		
		return paperTypeDao.update(typebean) ;
	}
	
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	public boolean deleteByIds(String ids){
		return paperTypeDao.deleteByIds(ids) ;
	}
	
	
	/**
	 * 判断中是否已包含该数据
	 */
	
	public boolean hasType(String typeID){
		return paperTypeDao.hasType(typeID);
	}


}
