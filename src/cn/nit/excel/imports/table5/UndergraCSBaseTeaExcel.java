package cn.nit.excel.imports.table5;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.nit.bean.di.DiCourseCategoriesBean;
import cn.nit.bean.di.DiCourseCharBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.di.DiResearchRoomBean;
import cn.nit.bean.other.UserRoleBean;
import cn.nit.bean.table5.UndergraCSBaseTeaBean;
import cn.nit.service.UndergraCSBaseTeaService;
import cn.nit.service.di.DiCourseCategoriesService;
import cn.nit.service.di.DiCourseCharService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.di.DiResearchRoomService;

import jxl.Cell;

public class UndergraCSBaseTeaExcel {
	
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
		UndergraCSBaseTeaBean undergraCSBaseTeaBean = null ;
		boolean flag = false ;
		List<UndergraCSBaseTeaBean> list = new LinkedList<UndergraCSBaseTeaBean>() ;
		UserRoleBean userinfo = (UserRoleBean)request.getSession().getAttribute("userinfo") ;
		
		DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		
		DiCourseCategoriesService diCSCateSer = new DiCourseCategoriesService() ;
		List<DiCourseCategoriesBean> diCSCateBeanList = diCSCateSer.getList() ;
		
		DiCourseCharService diCSCharSer = new DiCourseCharService() ;
		List<DiCourseCharBean> diCSCharBeanList = diCSCharSer.getList() ;
		
		DiResearchRoomService diResearchRoomSer = new DiResearchRoomService() ;
		List<DiResearchRoomBean> diResearchRoomBeanList = diResearchRoomSer.getList() ;
		
		for(Cell[] cell : cellList){
			try{
				String csName = cell[1].getContents() ;
				
				if(csName == null || csName.equals("")){
					return "第" + count + "行，课程名称不能为空" ;
				}
				
				if(csName.length() > 100){
					return "第" + count + "行，课程名称字数不超过50个汉字" ;
				}
				
				String csId = cell[2].getContents() ;
				
				if((csId == null) || csId.equals("")){
					return "第" + count + "行，课程编号不能为空" ;
				}
				
				if(csId.length() > 50){
					return "第" + count + "行，课程编号字数不超过50个数字或字母" ;
				}
				
				String csUnit = cell[3].getContents() ;
				String unitId = cell[4].getContents() ;
				
				if(csUnit == null || csUnit.equals("")){
					return "第" + count + "行，开课单位不能为空" ;
				}
				
				if(unitId == null || unitId.equals("")){
					return "第" + count + "行，单位编号不能为空" ;
				}
				
				if(unitId.length() > 50){
					return "第" + count + "行，单位编号字数不超过50个数字或字母" ;
				}
				
				for(DiDepartmentBean diDepartBean : diDepartBeanList){
					if(diDepartBean.getUnitId().equals(unitId)){
						if(diDepartBean.getUnitName().equals(csUnit)){
							flag = true ;
							break ;
						}else{
							return "第" + count + "行，开课单位与单位编号不对应" ;
						}
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，没有与之相匹配的单位编号" ;
				}else{
					flag = false ;
				}
				
				String fromTeaResOffice = cell[5].getContents() ;
				String teaResOfficeID = cell[6].getContents() ;
				
				if(fromTeaResOffice == null || fromTeaResOffice.equals("")){
					return "第" + count + "行，所属教研室不能为空" ;
				}
				
				if(teaResOfficeID == null || teaResOfficeID.equals("")){
					return "第" + count + "行，所属教研室编号不能为空" ; 
				}
				
				if(teaResOfficeID.length() > 50){
					return "第" + count + "行，教研室编号字数不超过50个数字或字母" ;
				}
				
				for(DiResearchRoomBean diResearchRoomBean : diResearchRoomBeanList){
					if(diResearchRoomBean.getUnitId().equals(teaResOfficeID)){
						if(diResearchRoomBean.getResearchName().equals(fromTeaResOffice)){
							flag = true ;
							break ;
						}else{
							return "第" + count + "行，教研室编号与教研室名称不一致" ;
						}
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，教研室编号不存在" ; 
				}else{
					flag = false ;
				}
				
				String csType = cell[7].getContents() ;
				
				if(csType == null || csType.equals("")){
					return "第" + count + "行，课程类别不能为空" ;
				}
				
				for(DiCourseCategoriesBean diCSCateBean : diCSCateBeanList){
					if(diCSCateBean.getCourseCategories().equals(csType)){
						csType = diCSCateBean.getIndexId() ;
						flag = true  ;
						break ;
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，课程类别不存在" ;
				}else{
					flag = false ;
				}
				
				String csNature = cell[8].getContents() ;
				
				if(csNature == null || csNature.equals("")){
					return "第" + count + "行，课程性质不能为空" ;
				}
				
				for(DiCourseCharBean diCSCharBean : diCSCharBeanList){
					if(diCSCharBean.getCourseChar().equals(csNature)){
						csNature = diCSCharBean.getIndexId() ;
						flag = true  ;
						break ;
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，课程性质不存在" ;
				}else{
					flag = false ;
				}
				
				String state = cell[9].getContents() ;
				
				if(state == null || state.equals("")){
					return "第" + count + "行，状态不能为空" ;
				}
				
				String pubCSType = cell[10].getContents() ;
				
				if(pubCSType == null || pubCSType.equals("")){
					return "第" + count + "行，公选课类别不能为空" ;
				}
				
				String note = cell[11].getContents() ;
				
				if(note.length() > 1000){
					return "第" + count + "行，备注不能超过500个汉字" ;
				}
				
				count++ ;
				undergraCSBaseTeaBean = new UndergraCSBaseTeaBean() ;
				undergraCSBaseTeaBean.setCSID(csId) ;
				undergraCSBaseTeaBean.setCSName(csName) ;
				undergraCSBaseTeaBean.setUnitID(unitId) ;
				undergraCSBaseTeaBean.setCSUnit(csUnit) ;
				undergraCSBaseTeaBean.setFromTeaResOffice(fromTeaResOffice) ;
				undergraCSBaseTeaBean.setTeaResOfficeID(teaResOfficeID) ;
				undergraCSBaseTeaBean.setCSType(csType) ;
				undergraCSBaseTeaBean.setCSNature(csNature) ;
				undergraCSBaseTeaBean.setState(state) ;
				undergraCSBaseTeaBean.setPubCSType(pubCSType) ;
				undergraCSBaseTeaBean.setNote(note) ;
				undergraCSBaseTeaBean.setFillTeaID(userinfo.getTeaID()) ;
				list.add(undergraCSBaseTeaBean) ;
				
			}catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
		}
		
		flag = false ;
		UndergraCSBaseTeaService undergraCSBaseTeaSer = new UndergraCSBaseTeaService() ;
		flag = undergraCSBaseTeaSer.batchInsert(list) ;
		
		if(flag){
			return null ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
	}
	
}
