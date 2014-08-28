package cn.nit.service.di;

import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import cn.nit.bean.di.DiResearchRoomBean;
import cn.nit.dao.di.DiResearchRoomDao;
import cn.nit.util.Pagition;


public class DiResearchRoomService {
	
	private DiResearchRoomDao researchRoomDao = new DiResearchRoomDao() ;
	/**
	 * 加载所有的教研�?
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public List<DiResearchRoomBean> getList(){
		return researchRoomDao.getList() ;
	}
	
	/**

	 * 新增一个部门或单位
	 * @param department
	 * @return
	 *
	 * @time: 2014-5-14/下午03:05:00
	 */
	public boolean insert(DiResearchRoomBean identiType){
		return researchRoomDao.insert(identiType) ;
	}
	
	/**
	 * 加载所有部门
	 * @param conditions
	 * @param page
	 * @param rows
	 * @return
	 */
	public String loadRooms(String conditions, int page, int rows){
		
		int total = researchRoomDao.totalRooms(conditions) ;
		List<DiResearchRoomBean> list = researchRoomDao.loadRooms(conditions, page, rows) ;
		Pagition pages = new Pagition(total, list) ;
		JSON json = JSONSerializer.toJSON(pages) ;
		
		return json.toString() ;
	}
	
	/**
	 * 更新角色
	 * @param userinfo
	 * @return
	 */
	public boolean update(DiResearchRoomBean roomBean){
		
		return researchRoomDao.update(roomBean) ;
	}
	
	/**
	 * 根据角色编号删除角色
	 * @param ids
	 * @return
	 */
	public boolean deleteByIds(String ids){
		return researchRoomDao.deleteByIds(ids) ;
	}
	
	
	
	/**
	 * 判断中是否已包含该部门
	 */
	
	public boolean hasRoom(String roomID){
		return researchRoomDao.hasRoom(roomID);
	}

	  public static void main(String arg[])
	  {
		  DiResearchRoomService ser =new DiResearchRoomService();
		  List<DiResearchRoomBean> list=ser.getList();
		  System.out.println(list.size());
	  }

}
