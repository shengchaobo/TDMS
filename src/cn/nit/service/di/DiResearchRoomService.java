package cn.nit.service.di;

import java.util.List;

import cn.nit.bean.di.DiResearchRoomBean;
import cn.nit.dao.di.DiResearchRoomDao;


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
	 * 新增一个教研室
	 * @param researchRoom
	 * @return
	 *
	 * @time: 2014-5-14/下午03:05:00
	 */
	public boolean insert(DiResearchRoomBean identiType){
		return researchRoomDao.insert(identiType) ;
	}

	  public static void main(String arg[])
	  {
		  DiResearchRoomService ser =new DiResearchRoomService();
		  List<DiResearchRoomBean> list=ser.getList();
		  System.out.println(list.size());
	  }

}
