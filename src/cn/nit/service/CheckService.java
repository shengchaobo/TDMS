package cn.nit.service;

import java.util.List;

import cn.nit.bean.CheckInfo;
import cn.nit.constants.Constants;
import cn.nit.dao.CheckDao;

public class CheckService {
	
	private CheckDao checkDao = new CheckDao();
	
	//取得某个表的审核信息
	public String loadInfo(String tableName, int checkType){
		
		List<CheckInfo> checkInfo = checkDao.loadInfo(tableName,checkType);
		
		StringBuffer infoList = new StringBuffer() ;
		
		if(checkInfo == null || checkInfo.isEmpty()){
			return null ;
		}
		
		if(checkType!=Constants.CTypeThree){
			for(CheckInfo info : checkInfo){
				infoList.append("编号为"+ info.getCheckID()+ "：  " + info.getCheckInfo().replace("\r\n", "") + "<br>") ;
			}
		}else{
			for(CheckInfo info : checkInfo){
				infoList.append("第"+ info.getCheckID()+ "年数据：  " + info.getCheckInfo().replace("\r\n", "") + "<br>") ;
			}
		}

		//System.out.println("infoList.toString()："+infoList.toString());
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
	 * 删除相应多条数据的checkInfo
	 * @param 
	 * @return
	 */
	
	public boolean delete(String tableID, String checkIDs){		
		return checkDao.delete(tableID, checkIDs) ;
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
