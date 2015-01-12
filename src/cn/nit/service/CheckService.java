package cn.nit.service;

import java.util.List;

import cn.nit.bean.CheckInfo;
import cn.nit.dao.CheckDao;

public class CheckService {
	
	private CheckDao checkDao = new CheckDao();
	
	//取得某个表的审核信息
	public String loadInfo(String tableName){
		
		List<CheckInfo> checkInfo = checkDao.loadInfo(tableName);
		
		StringBuffer infoList = new StringBuffer() ;
		
		if(checkInfo == null || checkInfo.isEmpty()){
			return null ;
		}
		
		//"[{\"id\":0,\"text\":\"查询\",\"state\":\"closed\",\"attributes\":{\"url\":\"test\"}},{\"id\":1,\"text\":\"填报\",\"state\":\"closed\",\"attributes\":{\"url\":\"test\"}}]"
		for(CheckInfo info : checkInfo){
			infoList.append("编号为"+ info.getCheckID()+ "：  " + info.getCheckInfo() + "<br>") ;
		}
		
		return infoList.toString();
	}
	
	/**
	 * 删除相应某条数据的checkInfo
	 * @param 
	 * @return
	 */
	
	public boolean delete(String tableID, int checkID){		
		return checkDao.delete(tableID, checkID) ;
	}
	
	/**
	 * 添加相应某条数据的checkInfo
	 * @param 
	 * @return
	 */
	
	public boolean addInfo(CheckInfo checkInfo){		
		return checkDao.addInfo(checkInfo) ;
	}
}
