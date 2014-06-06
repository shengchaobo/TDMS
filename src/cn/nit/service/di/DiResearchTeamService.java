package cn.nit.service.di;

import java.util.List;

import cn.nit.bean.di.DiResearchTeamBean;
import cn.nit.dao.di.DiResearchTeamDao;



public class DiResearchTeamService {
	
	private DiResearchTeamDao researchTeamDao = new DiResearchTeamDao() ;
	/**
	 * 加载所有的教研室
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public List<DiResearchTeamBean> getList(){
		return researchTeamDao.getList() ;
	}
	
	/**
	 * 新增一个教研室
	 * @param researchTeam
	 * @return
	 *
	 * @time: 2014-5-14/下午03:05:00
	 */
	public boolean insert(DiResearchTeamBean identiType){
		return researchTeamDao.insert(identiType) ;
	}


}
