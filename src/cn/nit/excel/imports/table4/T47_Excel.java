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
import cn.nit.bean.UserinfoBean;
import cn.nit.bean.di.DiAwardLevelBean;
import cn.nit.bean.di.DiAwardTypeBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.table4.T461_Bean;
import cn.nit.bean.table4.T47_Bean;
import cn.nit.constants.Constants;
import cn.nit.service.di.DiAwardLevelService;
import cn.nit.service.di.DiAwardTypeService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table4.T461_Service;
import cn.nit.service.table4.T47_Service;
import cn.nit.util.TimeUtil;

public class T47_Excel {

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
		T47_Bean T47_bean = null ;
		boolean flag = false ;
		List<T47_Bean> list = new LinkedList<T47_Bean>() ;
		
		DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		
		DiAwardLevelService diAwardLevel = new DiAwardLevelService();
		List<DiAwardLevelBean> diAwardLevelList = diAwardLevel.getList();	
				
		for(Cell[] cell : cellList){
			try{
				if(count<=3){
					count++;
					continue;
				}
				
				String unit = cell[1].getContents().trim() ;
				String unitId = cell[2].getContents().trim() ;
				
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
				
				String awardName = cell[3].getContents().trim() ;
				
				if(awardName == null || awardName.equals("")){
					return "第" + count + "行，获奖名称不能为空" ;
				}

				String awardLevel = cell[4].getContents().trim() ;
				
				if(awardLevel == null || awardLevel.equals("")){
					return "第" + count + "行，获奖级别不能为空" ;
				}
				String awardLevelID = null;
				for(DiAwardLevelBean awardLevelBean : diAwardLevelList){
					if(awardLevelBean.getAwardLevel().equals(awardLevel)){
						awardLevelID = awardLevelBean.getIndexId() ;
						flag = true  ;
						break ;
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，获奖级别不存在" ;
				}else{
					flag = false ;
				}
				
				
				String awardFromUnit = cell[5].getContents().trim();
				
				String gainAwardTime = cell[6].getContents().trim() ;
				if((gainAwardTime == null) || gainAwardTime.equals("")){
					return "第" + count + "行，获奖日期不能为空" ;
				}else{
					if(!TimeUtil.judgeFormatY(gainAwardTime)){
						return "第" + count + "行，获得时间格式不正确" ;
					}
				}
												
				String appvlId = cell[7].getContents().trim() ;
				String note = cell[8].getContents().trim();
								
				count++ ;
								
				T47_bean = new T47_Bean() ;
				T47_bean.setTeaUnit(unit);
				T47_bean.setUnitId(unitId);
				T47_bean.setAwardName(awardName);
				T47_bean.setAwardLevel(awardLevelID);
				T47_bean.setAwardFromUnit(awardFromUnit);
				T47_bean.setGainAwardTime(TimeUtil.changeDateY(gainAwardTime));
				T47_bean.setAppvlId(appvlId);
				T47_bean.setNote(note);
				//插入时间
				T47_bean.setTime(TimeUtil.changeDateY(selectYear));
				//插入审核状态
				T47_bean.setCheckState(Constants.WAIT_CHECK);
				
				//插入教学单位
				UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
				String fillUnitID = bean.getUnitID();
				T47_bean.setFillUnitID(fillUnitID);

				list.add(T47_bean);
								
			}catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
		}
		
		flag = false ;
		T47_Service T47_services = new T47_Service() ;
		flag = T47_services.batchInsert(list) ;
		
		if(flag){
			return null ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
	}
}