package cn.nit.excel.imports.table7;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.nit.bean.di.DiCourseCategoriesBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.table7.T742_Bean;
import cn.nit.service.di.DiCourseCategoriesService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table7.T742_Service;
import cn.nit.util.TimeUtil;

import jxl.Cell;

public class T742_Excel {
	
	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request,String selectYear){
		
		if((cellList == null) || (cellList.size() < 2)){
			return "数据不标准，请重新提交" ;
		}
		
		int  count=1;
		T742_Bean T742_Bean=new T742_Bean();
		boolean flag=false;
	    List<T742_Bean> list=new LinkedList<T742_Bean>();
	
	    DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		
		DiCourseCategoriesService diCourseSer=new DiCourseCategoriesService();
		List<DiCourseCategoriesBean>  diCourseBeanList=diCourseSer.getList();
		
		for(Cell[] cell: cellList){
			try {
				if(count<4){
					count++;
					continue;
				}
				
				
				String teaName = cell[1].getContents() ;
				if(teaName == null || teaName.equals("")){
					return "第" + count + "行，教师姓名不能为空" ;
				}
				String teaId = cell[2].getContents() ;
				if(teaId == null || teaId.equals("")){
					return "第" + count + "行，教工号不能为空" ;
				}
				String unit = cell[3].getContents() ;
				if((unit == null) || unit.equals("")){
					return "第" + count + "行，所属教学单位不能为空" ;
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
							return "第" + count + "行，所属教学单位与所属单位号不对应" ;
						}
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，没有与之相匹配的单位号" ;
				}else{
					flag = false ;
				}
				
				String assCs = cell[5].getContents() ;
				if(assCs == null || assCs.equals("")){
					return "第" + count + "行，参评课程不能为空" ;
				}
				String csId = cell[6].getContents() ;
				if((csId == null) || csId.equals("")){
					return "第" + count + "行，课程编号不能为空" ;
				}
				String csType = cell[7].getContents() ;
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
				String assYear = cell[8].getContents() ;
				if((assYear == null) || assYear.equals("")){
					return "第" + count + "行，评估年份不能为空" ;
				}
				String assResult = cell[9].getContents() ;
				if(assResult == null || assResult.equals("")){
					return "第" + count + "行，评估结果不能为空" ;
				}
				String appID = cell[10].getContents() ;
				if(appID == null || appID.equals("")){
					return "第" + count + "行，批文号不能为空" ;
				}
				
				 String note = cell[11].getContents();
					
					count++ ;

					String fillUnitID = null;
					
					T742_Bean.setTeaName(teaName);
					T742_Bean.setTeaID(teaId);
					T742_Bean.setTeaUnit(unit);
					T742_Bean.setUnitID(unitId);
					T742_Bean.setAssessCS(assCs);
					T742_Bean.setCSID(csId);
					T742_Bean.setCSType(csTypeId);
					T742_Bean.setAssessYear(assYear);
					T742_Bean.setAssessResult(assResult);
					T742_Bean.setAppvlID(appID);
					T742_Bean.setFillUnitID(fillUnitID);
					T742_Bean.setTime(TimeUtil.changeDateY(selectYear));
					T742_Bean.setNote(note);
					
					list.add(T742_Bean);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
			
		}
		flag = false ;
		T742_Service t742_Sr=new T742_Service();
		flag=t742_Sr.batchInsert(list);
		if(flag){
			return null ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
	}

}