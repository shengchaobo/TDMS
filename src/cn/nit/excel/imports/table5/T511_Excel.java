package cn.nit.excel.imports.table5;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.nit.bean.di.DiCourseCategoriesBean;
import cn.nit.bean.di.DiCourseCharBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.di.DiResearchRoomBean;
import cn.nit.bean.table5.T511_Bean;
import cn.nit.service.di.DiCourseCategoriesService;
import cn.nit.service.di.DiCourseCharService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.di.DiResearchRoomService;
import cn.nit.service.table5.T511_Service;
import cn.nit.util.TimeUtil;

import jxl.Cell;

public class T511_Excel {
	
	/**
	 * 批量导入
	 * @param cellList {@link java.util.List<{@link jxl.Cell}>}  Cell是数组
	 * @param request  {@link javax.servlet.http.HttpServletRequest}
	 * @return
	 */
	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request,String selectYear){
		
		if((cellList == null) || (cellList.size() < 2)){
			return "数据不标准，请重新提交" ;
		}
		
		int  count=1;
	
		boolean flag=false;
	    List<T511_Bean> list=new LinkedList<T511_Bean>();
	
	    DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
	    
		DiCourseCharService diCourseCharSer=new DiCourseCharService();
		List<DiCourseCharBean> diCourseCharBeanList=diCourseCharSer.getList();
		
		DiCourseCategoriesService diCourseSer=new DiCourseCategoriesService();
		List<DiCourseCategoriesBean>  diCourseBeanList=diCourseSer.getList();
		
		DiResearchRoomService diResearchRoomSr=new DiResearchRoomService();
		List<DiResearchRoomBean>     diResearchBeanList=diResearchRoomSr.getList();
		
		System.out.println(cellList.size());
		
		
		for(Cell[] cell: cellList){
			T511_Bean T511_Bean=new T511_Bean();
			try {
				
				if(count<4){
					count++;
					continue;
				}
				
				String csName = cell[1].getContents() ;
				System.out.println("csName:"+csName);
				if(csName == null || csName.equals("")){
					return "第" + count + "行，课程名称不能为空" ;
				}
				String csID = cell[2].getContents() ;
				System.out.println("csID:"+csID);
				if((csID == null) || csID.equals("")){
					return "第" + count + "行，课程编号不能为空" ;
				}
				String unit = cell[3].getContents() ;
				System.out.println("unit:"+unit);
				if((unit == null) || unit.equals("")){
					return "第" + count + "行，开课单位不能为空" ;
				}
				String unitId = cell[4].getContents() ;
				System.out.println("unitId:"+unitId);
				if((unitId == null) || unitId.equals("")){
					return "第" + count + "行，单位号不能为空" ;
				}
				for(DiDepartmentBean diDepartBean : diDepartBeanList){
					if(diDepartBean.getUnitId().equals(unitId)){
						if(diDepartBean.getUnitName().equals(unit)){
							flag = true ;
							break ;
						}else{
							return "第" + count + "行，开课单位与所属单位号不对应" ;
						}
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，没有与之相匹配的单位号" ;
				}else{
					flag = false ;
				}
				String fromTeaResOffice = cell[5].getContents();
				System.out.println("fromTeaResOffice:"+fromTeaResOffice);
				if(fromTeaResOffice == null || fromTeaResOffice.equals("")){
					return "第" + count + "行，所属教研室不能为空" ;
				}
				
				String teaResOfficeID = cell[6].getContents();
				System.out.println("teaResOfficeID:"+teaResOfficeID);
				if(teaResOfficeID == null || teaResOfficeID.equals("")){
					return "第" + count + "行，教研室号不能为空" ;
				}
				
				for(DiResearchRoomBean diResearchRoomBean : diResearchBeanList){
					if(diResearchRoomBean.getResearchName().equals(fromTeaResOffice)){
						if(diResearchRoomBean.getUnitId().equals(teaResOfficeID)){
							flag = true ;
							break ;
						}else{
							return "第" + count + "行，所属教研室与教研室号不对应" ;
						}
						
					}
						
					
				}
				String csType = cell[7].getContents() ;
				System.out.println("csType:"+csType);
				if((csType == null) || csType.equals("")){
					return "第" + count + "行，课程类别不能为空" ;
				}
				String csTypeId=null;
				for(DiCourseCategoriesBean diCorCateBean : diCourseBeanList){
					if(diCorCateBean.getCourseCategories().equals(csType)){
						csTypeId=diCorCateBean.getIndexId();
						flag=true;
						break;
					}
					
				}
				if(!flag){
					return "第" + count + "行，课程类别不存在" ;
				}else{
					flag = false ;
				}
				
				String csNature = cell[8].getContents() ;
				System.out.println("csNature:"+csNature);
				if((csNature == null) || csNature.equals("")){
					return "第" + count + "行，课程性质不能为空" ;
				}
				String csNatureId=null;
				for(DiCourseCharBean diCorBean : diCourseCharBeanList){
					if(diCorBean.getCourseChar().equals(csNature)){
						csNatureId=diCorBean.getIndexId();
						flag=true;
						break;
					}
					
				}
				if(!flag){
					return "第" + count + "行，课程性质不存在" ;
				}else{
					flag = false ;
				}
				
				String state = cell[9].getContents() ;
				System.out.println("state:"+state);
				if((state == null) || state.equals("")){
					return "第" + count + "行，状态不能为空" ;
				}
				String pubcs = cell[10].getContents() ;
				System.out.println("pubcs:"+pubcs);
				if((pubcs == null) || pubcs.equals("")){
					return "第" + count + "行，公选课类别不能为空" ;
				}
				String note = cell[11].getContents();
				
				count++ ;

				String fillUnitID = null;
			
				
				T511_Bean.setCSName(csName);
				T511_Bean.setCSID(csID);
				T511_Bean.setCSUnit(unit);
				T511_Bean.setUnitID(unitId);
				T511_Bean.setFromTeaResOffice(fromTeaResOffice);
				T511_Bean.setTeaResOfficeID(teaResOfficeID);
				T511_Bean.setCSType(csTypeId);
				T511_Bean.setCSNature(csNatureId);
				T511_Bean.setState(state);
				T511_Bean.setPubCSType(pubcs);
				T511_Bean.setFillUnitID(fillUnitID);
				T511_Bean.setTime(TimeUtil.changeDateY(selectYear));
				T511_Bean.setNote(note);
				list.add(T511_Bean);
				
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
			
			
		}
		flag = false ;
		T511_Service t511_Sr=new T511_Service();
		flag=t511_Sr.batchInsert(list);
		if(flag){
			return null ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
	}
}
