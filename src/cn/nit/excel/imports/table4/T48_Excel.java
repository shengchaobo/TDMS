package cn.nit.excel.imports.table4;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanWrapperImpl;

import jxl.Cell;
import cn.nit.bean.di.DiAwardLevelBean;
import cn.nit.bean.di.DiAwardTypeBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.table4.T461_Bean;
import cn.nit.bean.table4.T47_Bean;
import cn.nit.bean.table4.T48_Bean;
import cn.nit.service.di.DiAwardLevelService;
import cn.nit.service.di.DiAwardTypeService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table4.T461_Service;
import cn.nit.service.table4.T47_Service;
import cn.nit.service.table4.T48_Service;
import cn.nit.util.TimeUtil;

public class T48_Excel {

	/**
	 * 批量导入
	 * @param cellList {@link java.util.List<{@link jxl.Cell}>}  Cell是数组
	 * @param request  {@link javax.servlet.http.HttpServletRequest}
	 * @return
	 */
	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request, String selectYear){
		
		if((cellList == null) || (cellList.size() < 2)){
			return "数据不标准，请重新提交" ;
		}
		
		int count = 1 ;
		T48_Bean T48_bean = null ;
		boolean flag = false ;
		List<T48_Bean> list = new LinkedList<T48_Bean>() ;
		
		DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		
		DiAwardLevelService diAwardLevel = new DiAwardLevelService();
		List<DiAwardLevelBean> diAwardLevelList = diAwardLevel.getList();	
				
		for(Cell[] cell : cellList){
			try{
				if(count<4){
					count++;
					continue;
				}
				
				String unit = cell[1].getContents() ;
				String unitId = cell[2].getContents() ;
				
				if(unit == null || unit.equals("")){
					return "第" + count + "行，教学单位不能为空" ;
				}
				
				if(unitId == null || unitId.equals("")){
					return "第" + count + "行，单位号不能为空" ;
				}			
				
				for(DiDepartmentBean diDepartBean : diDepartBeanList){
					if(diDepartBean.getUnitId().equals(unitId)){
						if(diDepartBean.getUnitName().equals(unit)){
							flag = true ;
							break ;
						}else{
							return "第" + count + "行，所属单位与所属单位号不对应" ;
						}
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，没有与之相匹配的单位号" ;
				}else{
					flag = false ;
				}
				
				String teamName = cell[3].getContents() ;
				
				if(teamName == null || teamName.equals("")){
					return "第" + count + "行，团队名称不能为空" ;
				}

				String teamLevel = cell[4].getContents() ;
				
				if(teamLevel == null || teamLevel.equals("")){
					return "第" + count + "行，团队级别不能为空" ;
				}
				String awardLevelID = null;
				for(DiAwardLevelBean awardLevelBean : diAwardLevelList){
					if(awardLevelBean.getAwardLevel().equals(teamLevel)){
						awardLevelID = awardLevelBean.getIndexId() ;
						flag = true  ;
						break ;
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，团队级别不存在" ;
				}else{
					flag = false ;
				}
				
				String leader = cell[5].getContents() ;
				String teaId = cell[6].getContents() ;
				
				if(leader == null || leader.equals("")){
					return "第" + count + "行，负责人名称不能为空" ;
				}
				
				if((teaId == null) || teaId.equals("")){
					return "第" + count + "行，教工号不能为空" ;
				}
				
				String groupNum = cell[7].getContents() ;
				String groupInfo = cell[8].getContents() ;
				
				String gainTime = cell[9].getContents() ;
				if((gainTime == null) || gainTime.equals("")){
					return "第" + count + "行，获奖日期不能为空" ;
				}
												
				String appvlId = cell[10].getContents() ;
				String note = cell[11].getContents() ;
								
				count++ ;
								
				T48_bean = new T48_Bean() ;
				T48_bean.setTeaUnit(unit);
				T48_bean.setUnitId(unitId);
				T48_bean.setTeamName(teamName);
				T48_bean.setTeamLevel(awardLevelID);
				T48_bean.setLeader(leader);
				T48_bean.setTeaId(teaId);
				T48_bean.setGroupNum(Integer.parseInt(groupNum));
				T48_bean.setGroupInfo(groupInfo);
				T48_bean.setGainTime(TimeUtil.changeDateY(gainTime));
				T48_bean.setAppvlId(appvlId);
				T48_bean.setNote(note);
				//插入时间
				T48_bean.setTime(TimeUtil.changeDateY(selectYear));
				String fillUnitID = null;

				T48_bean.setFillUnitID(fillUnitID);

				list.add(T48_bean);
								
			}catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
		}
		
		flag = false ;
		T48_Service T48_services = new T48_Service() ;
		flag = T48_services.batchInsert(list) ;
		
		if(flag){
			return null ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
	}
}