package cn.nit.service.di;

import java.util.List;

import cn.nit.bean.di.DiResearchTeamBean;
import cn.nit.bean.di.DiTalentTypeBean;
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
	
	/**
	 * 更新
	 * @param userinfo
	 * @return
	 */
	public boolean update(DiResearchTeamBean teambean){
		
		return researchTeamDao.update(teambean) ;
	}
	
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	public boolean deleteByIds(String ids){
		return researchTeamDao.deleteByIds(ids) ;
	}
	
	
	/**
	 * 判断中是否已包含该数据
	 */
	
	public boolean hasTeam(String teamID){
		return researchTeamDao.hasTeam(teamID);
	}


}
