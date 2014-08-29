package cn.nit.service.di;

import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import cn.nit.bean.di.DiMajorTwoBean;
import cn.nit.dao.di.DiMajorTwoDao;
import cn.nit.util.Pagition;


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
	
	/**
	 * 加载所有部门
	 * @param conditions
	 * @param page
	 * @param rows
	 * @return
	 */
	public String loadMajors(String conditions, int page, int rows){
		
		int total = degreeDao.totalMajors(conditions) ;
		List<DiMajorTwoBean> list = degreeDao.loadMajors(conditions, page, rows) ;
		Pagition pages = new Pagition(total, list) ;
		JSON json = JSONSerializer.toJSON(pages) ;
		
		return json.toString() ;
	}
	
	/**
	 * 更新角色
	 * @param userinfo
	 * @return
	 */
	public boolean update(DiMajorTwoBean majorBean){
		
		return degreeDao.update(majorBean) ;
	}
	
	/**
	 * 根据角色编号删除角色
	 * @param ids
	 * @return
	 */
	public boolean deleteByIds(String ids){
		return degreeDao.deleteByIds(ids) ;
	}
	
	
	
	/**
	 * 判断中是否已包含该部门
	 */
	
	public boolean hasMajor(String majorID){
		return degreeDao.hasMajor(majorID);
	}


}
