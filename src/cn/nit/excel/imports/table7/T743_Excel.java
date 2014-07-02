package cn.nit.excel.imports.table7;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.nit.bean.di.DiCourseCategoriesBean;
import cn.nit.bean.di.DiCourseCharBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.table7.T743_Bean;
import cn.nit.service.di.DiCourseCategoriesService;
import cn.nit.service.di.DiCourseCharService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table7.T743_Service;
import cn.nit.util.TimeUtil;

import jxl.Cell;

public class T743_Excel {

	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request,String selectYear){
		
		if((cellList == null) || (cellList.size() < 2)){
			return "数据不标准，请重新提交" ;
		}
		
		int  count=1;
		T743_Bean T743_Bean=new T743_Bean();
		boolean flag=false;
	    List<T743_Bean> list=new LinkedList<T743_Bean>();
	
	    DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		
		DiCourseCategoriesService diCourseSer=new DiCourseCategoriesService();
		List<DiCourseCategoriesBean>  diCourseBeanList=diCourseSer.getList();
		
		DiCourseCharService diCourseCharSer=new DiCourseCharService();
		List<DiCourseCharBean> diCourseCharBeanList=diCourseCharSer.getList();
		
		for(Cell[] cell: cellList){
			
			try {		
				if(count<4){
					count++;
					continue;
				}
				String csName = cell[1].getContents() ;
				if(csName == null || csName.equals("")){
					return "第" + count + "行，课程名称不能为空" ;
				}
				String csId = cell[2].getContents() ;
				if(csId == null || csId.equals("")){
					return "第" + count + "行，课程编号不能为空" ;
				}
				String unit = cell[3].getContents() ;
				if((unit == null) || unit.equals("")){
					return "第" + count + "行，开课单位不能为空" ;
				}
				String unitId = cell[4].getContents() ;
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
				String csType = cell[5].getContents() ;
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
				String csNature = cell[6].getContents() ;
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
				
				String csLeader = cell[7].getContents() ;
				if((csLeader == null) || csLeader.equals("")){
					return "第" + count + "行，课程负责人不能为空" ;
				}
				String teaId = cell[8].getContents() ;
				if((teaId == null) || teaId.equals("")){
					return "第" + count + "行，教工号不能为空" ;
				}
				String assYear = cell[9].getContents() ;
				if((assYear == null) || assYear.equals("")){
					return "第" + count + "行，评估年份不能为空" ;
				}
				String assResult = cell[10].getContents() ;
				if(assResult == null || assResult.equals("")){
					return "第" + count + "行，评估结果不能为空" ;
				}
				String appID = cell[11].getContents() ;
				if(appID == null || appID.equals("")){
					return "第" + count + "行，批文号不能为空" ;
				}
				
				String note = cell[12].getContents();
					
				count++ ;

				String fillUnitID = null;
					
				T743_Bean.setCSName(csName);
				T743_Bean.setCSID(csId);
				T743_Bean.setSetCSUnit(unit);
				T743_Bean.setUnitID(unitId);
				T743_Bean.setCSType(csTypeId);
				T743_Bean.setCSNature(csNatureId);
				T743_Bean.setCSLeader(csLeader);
				T743_Bean.setTeaID(teaId);
				T743_Bean.setAssessYear(assYear);
				T743_Bean.setAssessResult(assResult);
				T743_Bean.setAppvlID(appID);
				T743_Bean.setFillUnitID(fillUnitID);
				T743_Bean.setTime(TimeUtil.changeDateY(selectYear));
				T743_Bean.setNote(note);
					
				list.add(T743_Bean);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
			
			
		}
		
		flag = false ;
		T743_Service t743_Sr=new T743_Service();
		flag=t743_Sr.batchInsert(list);
		if(flag){
			return null ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
	}
}
