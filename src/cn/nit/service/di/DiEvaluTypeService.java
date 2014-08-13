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
	
	/**
	 * 新增一个评价类型
	 * @param 
	 * @return
	 *
	 * @time: 2014-5-14/下午03:05:00
	 */
	public boolean insert(DiEvaluTypeBean degree){
		return EvaluTypeDao.insert(degree) ;
	}
     public static void main(String arg[]){
    	 DiEvaluTypeService ser = new DiEvaluTypeService();
    	 List<DiEvaluTypeBean> list = ser.getList();
    	 System.out.println(list.size());
     }

}
