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
import cn.nit.service.di.DiAwardLevelService;
import cn.nit.service.di.DiAwardTypeService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table4.T461_Service;
import cn.nit.util.TimeUtil;

public class T461_Excel {

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
		T461_Bean T461_bean = null ;
		boolean flag = false ;
		List<T461_Bean> list = new LinkedList<T461_Bean>() ;
		
		DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		
		DiAwardLevelService diAwardLevel = new DiAwardLevelService();
		List<DiAwardLevelBean> diAwardLevelList = diAwardLevel.getList();
		
		DiAwardTypeService diAwardType = new DiAwardTypeService();
		List<DiAwardTypeBean> diAwardTypeList = diAwardType.getList();		
				
		for(Cell[] cell : cellList){
			try{
				if(count<=2){
					count++;
					continue;
				}
				
				String name = cell[1].getContents() ;
				String teaId = cell[2].getContents() ;
				
				if(name == null || name.equals("")){
					return "第" + count + "行，名称不能为空" ;
				}
				
				if((teaId == null) || teaId.equals("")){
					return "第" + count + "行，教工号不能为空" ;
				}
				
				String unit = cell[3].getContents() ;
				String unitId = cell[4].getContents() ;
				
				if(unit == null || unit.equals("")){
					return "第" + count + "行，所属单位不能为空" ;
				}
				
				if(unitId == null || unitId.equals("")){
					return "第" + count + "行，所属单位号不能为空" ;
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
				
				String awardType = cell[5].getContents() ;
				
				if(awardType == null || awardType.equals("")){
					return "第" + count + "行，获奖类型不能为空" ;
				}
				String awardTypeID = null;
				for(DiAwardTypeBean awardTypeBean : diAwardTypeList){
					if(awardTypeBean.getAwardType().equals(awardType)){
						awardTypeID = awardTypeBean.getIndexId() ;
						flag = true  ;
						break ;
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，获奖类型不存在" ;
				}else{
					flag = false ;
				}
				
				String awardLevel = cell[6].getContents() ;
				
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
				
				
				String awardFromUnit = cell[7].getContents();
				
				String gainAwardTime = cell[8].getContents() ;
				if((gainAwardTime == null) || gainAwardTime.equals("")){
					return "第" + count + "行，获奖日期不能为空" ;
				}
												
				String appvlId = cell[9].getContents() ;
				String otherTeaNum = cell[10].getContents() ;
				String otherTeaInfo = cell[11].getContents() ;
				String note = cell[12].getContents() ;
								
				count++ ;
								
				T461_bean = new T461_Bean() ;
				T461_bean.setName(name);
				T461_bean.setTeaId(teaId);
				T461_bean.setFromTeaUnit(unit);
				T461_bean.setUnitId(unitId);
				T461_bean.setAwardType(awardTypeID);
				T461_bean.setAwardLevel(awardLevelID);
				T461_bean.setAwardFromUnit(awardFromUnit);
				T461_bean.setGainAwardTime(TimeUtil.changeDateY(gainAwardTime));
				T461_bean.setAppvlId(appvlId);
				T461_bean.setOtherTeaNum(Integer.parseInt(otherTeaNum));
				T461_bean.setOtherTeaInfo(otherTeaInfo);
				T461_bean.setNote(note);
				//插入时间
				T461_bean.setTime(new Date());
				String fillUnitID = null;
				//char b = fillUnitID.charAt(0);
				//if( b == '3'){
				//	T461_bean.setFillUnitID(fillUnitID);
				//}else{
					T461_bean.setFillUnitID(fillUnitID);
			//	}
				
				list.add(T461_bean);
								
			}catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
		}
		
		flag = false ;
		T461_Service T461_services = new T461_Service() ;
		flag = T461_services.batchInsert(list) ;
		
		if(flag){
			return null ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
	}
}