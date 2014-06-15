package cn.nit.excel.imports.table1;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.Cell;
import cn.nit.bean.di.DiAwardLevelBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.other.UserRoleBean;
import cn.nit.bean.table1.T19Bean;
import cn.nit.service.di.DiAwardLevelService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table1.T19Service;
import cn.nit.util.TimeUtil;

public class T19Excel {
	
	/**
	 * 批量导入
	 * @param cellList {@link java.util.List<{@link jxl.Cell}>}  Cell是数组
	 * @param request  {@link javax.servlet.http.HttpServletRequest}
	 * @return
	 */
	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request){
		
		if((cellList == null) || (cellList.size() < 2)){
			return "数据不标准，请重新提交" ;
		}
		
		int count = 1 ;
		
		boolean flag = false ;
		boolean biOpen=false;
		boolean buildCondi=false;
		List<T19Bean> list = new LinkedList<T19Bean>() ;
		UserRoleBean userinfo = (UserRoleBean)request.getSession().getAttribute("userinfo") ;
		DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
	    DiAwardLevelService diAwardLevelSer=new DiAwardLevelService();
	    List<DiAwardLevelBean> diAwardLevelList = diAwardLevelSer.getList();
		
	
		
		for(Cell[] cell : cellList){
			
			T19Bean t19Bean = new  T19Bean();
			int n=cellList.indexOf(cell);
			if(n==0){continue;}
			else{
				
				
			  try{
				 
				    String RewardName = cell[1].getContents() ;
				    
				    if(RewardName == null || RewardName.equals("")){
				    	return "第" + count + "行，奖励名称不能为空" ;
				    }
				    if(RewardName.length()>100){
				    	return "第" + count + "行，奖励名称不能超过100字" ;
				    }
				    
				    
					String RewardLevel = cell[2].getContents() ;
					
					if(RewardLevel == null || RewardLevel.equals("")){
						return "第" + count + "行，级别不能为空" ;
					}
					
					for(DiAwardLevelBean diAwardLevelBean:diAwardLevelList) {
						if(diAwardLevelBean.getAwardLevel().equals(RewardLevel)){
							RewardLevel=diAwardLevelBean.getIndexId() ;
							flag=true;
							break;
						}
					}
					if(!flag){
						return "第" + count + "行，获奖级别不存在" ;
					}else{
						flag=false;
					}
					
					String RewardFromUnit = cell[3].getContents();
					
					if(RewardFromUnit == null || RewardFromUnit.equals("")){
						return "第" + count + "行，授予单位不能为空" ;
					}
					
					if(RewardFromUnit.length()>100){
						return "第" + count + "行，授予单位字数不能超过100字" ;
					}
					
				 
					String UnitName = cell[4].getContents() ;
					String UnitID = cell[5].getContents();
					
					if(UnitName == null || UnitName.equals("")){
						return "第" + count + "行，获奖单位不能为空" ;
					}
					
					if(UnitID == null || UnitID.equals("")){
						return "第" + count + "行，获奖单位号不能为空" ;
					}
					
					if(UnitID.length() >50){
						return "第" + count + "行，获奖单位号不能超过50" ;
					}
					
					for(DiDepartmentBean diDepartBean : diDepartBeanList){
						if(diDepartBean.getUnitId().equals(UnitID)){
							if(diDepartBean.getUnitName().equals(UnitName)){
								flag = true;  
								break;
							}else{
								return "第" + count + "行，获奖单位与单位编号不对应" ;
							}
						}
					}
					if(!flag){
						return "第" + count + "行，没有与之相匹配的单位编号" ;
					}else{
						flag=false;
					}
					
					String RewardTime=cell[6].getContents();
					
					if(RewardTime == null || RewardTime.equals("")){
						return "第" + count + "行，获奖时间不能为空" ;
					}
					if(!TimeUtil.judgeFormat3(RewardTime)){
						return "第" + count + "行，获奖时间格式为：“2013”" ;
					}
					String note=cell[7].getContents();
					
					if(note.length()>500){
						return "第" + count + "行，备注字数不能超过500" ;
					}
					
				Date rewardTime=TimeUtil.changeDateY(RewardTime);
				count++ ;
				t19Bean.setRewardName(RewardName);
				t19Bean.setRewardLevel(RewardLevel);
				t19Bean.setRewardFromUnit(RewardFromUnit);
				t19Bean.setNote(note);
				t19Bean.setTime(new Date());
				t19Bean.setUnitID(UnitID);
				t19Bean.setUnitName(UnitName);
				t19Bean.setRewardTime(rewardTime);
				list.add(t19Bean);			
			}
			catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
	     }
		}
		
		flag = false ;
		T19Service t19Ser = new T19Service() ;
		flag = t19Ser.batchInsert(list) ;
		
		if(flag){
			return "数据导入成功" ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
		
	}
	

}
