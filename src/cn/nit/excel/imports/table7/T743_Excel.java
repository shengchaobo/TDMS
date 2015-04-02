package cn.nit.excel.imports.table7;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.nit.bean.UserinfoBean;
import cn.nit.bean.di.DiCourseCategoriesBean;
import cn.nit.bean.di.DiCourseCharBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.table4.T411_Bean;
import cn.nit.bean.table7.T743_Bean;
import cn.nit.constants.Constants;
import cn.nit.service.di.DiCourseCategoriesService;
import cn.nit.service.di.DiCourseCharService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table4.T411_Service;
import cn.nit.service.table7.T743_Service;
import cn.nit.util.TimeUtil;

import jxl.Cell;

public class T743_Excel {

	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request,String selectYear){
		
		if((cellList == null) || (cellList.size() < 2)){
			return "数据不标准，请重新提交" ;
		}
		
		int  count=1;
		
		boolean flag=false;
	    List<T743_Bean> list=new LinkedList<T743_Bean>();
	
	    DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		
		DiCourseCategoriesService diCourseSer=new DiCourseCategoriesService();
		List<DiCourseCategoriesBean>  diCourseBeanList=diCourseSer.getList();
		
		DiCourseCharService diCourseCharSer=new DiCourseCharService();
		List<DiCourseCharBean> diCourseCharBeanList=diCourseCharSer.getList();
		
		T411_Service t411_Ser=new T411_Service();
		List<T411_Bean> t411_BeanList = t411_Ser.getList();
		
		for(Cell[] cell: cellList){
			T743_Bean T743_Bean=new T743_Bean();
			try {		
				if(count<4){
					count++;
					continue;
				}
				String csName = cell[1].getContents().trim() ;
				if(csName == null || csName.equals("")){
					return "第" + count + "行，课程名称不能为空" ;
				}
				if(csName.length()>100){
					return "第" + count + "行，课程名称不能超过100个字符" ;
				}
				String csId = cell[2].getContents().trim() ;
				if(csId == null || csId.equals("")){
					return "第" + count + "行，课程编号不能为空" ;
				}
				if(csId.length()>50){
					return "第" + count + "行，课程编号不能超过50个字符" ;
				}
				String unit = cell[3].getContents().trim() ;
				if((unit == null) || unit.equals("")){
					return "第" + count + "行，开课单位不能为空" ;
				}
				if(unit.length()>200){
					return "第" + count + "行，开课单位不能超过200个字符" ;
				}
				String unitId = cell[4].getContents().trim() ;
				if((unitId == null) || unitId.equals("")){
					return "第" + count + "行，单位号不能为空" ;
				}
				if(unitId.length()>50){
					return "第" + count + "行，单位号不能超过50个字符" ;
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
				String csType = cell[5].getContents().trim() ;
				if((csType == null) || csType.equals("")){
					return "第" + count + "行，课程类别不能为空" ;
				}
				if(!csType.equals("理论课（含实践）") && !csType.equals("理论课（不含实践）")&& !csType.equals("集中性实践环节")&& !csType.equals("实验课")&& !csType.equals("其他")){
					return "第" + count + "行，课程类别只能是“理论课（含实践）”或“理论课（不含实践）”或“集中性实践环节”或“实验课”或“其他”" ;
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
				String csNature = cell[6].getContents().trim() ;
				if((csNature == null) || csNature.equals("")){
					return "第" + count + "行，课程性质不能为空" ;
				}
				if(!csNature.equals("大学外语课") && !csNature.equals("公共任选课")&& !csNature.equals("集中实践教学环节")&& !csNature.equals("通识必修课")
						   && !csNature.equals("通识任选课")&& !csNature.equals("通识限选课")&& !csNature.equals("学科基础课")&& !csNature.equals("专业必修课")
						   && !csNature.equals("专业基础课")&& !csNature.equals("专业课必修")&& !csNature.equals("专业任选课")&& !csNature.equals("专业限选课")&& !csNature.equals("专业选修课")){
							return "第" + count + "行，课程性质格式有误，只能填写“大学外语课”或“公共任选课”或“集中实践教学环节”或“通识必修课”或“通识任选课”或“通识限选课”或“学科基础课”或“专业必修课”或“专业基础课”或“专业课必修”或“专业任选课”或“专业限选课”或“专业选修课”";
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
				
				String csLeader = cell[7].getContents().trim() ;
				String teaId = cell[8].getContents().trim() ;
				if((csLeader == null) || csLeader.equals("")){
					return "第" + count + "行，课程负责人不能为空" ;
				}
				if(csLeader.length()>50){
					return "第" + count + "行，课程负责人不能超过50个字符" ;
				}

			
				if((teaId == null) || teaId.equals("")){
					return "第" + count + "行，教工号不能为空" ;
				}
				if(teaId.length()>50){
					return "第" + count + "行，教工号不能超过50个字符" ;
				}
				for(T411_Bean t411_Bean : t411_BeanList){
					if(t411_Bean.getTeaId().equals(teaId)){
						if(t411_Bean.getTeaName().equals(csLeader)){
							flag = true ;
							break ;
						}else{
							return "第" + count + "行，建设负责人与教工号不对应" ;
							
						}
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，没有与之相匹配的教工号" ;
				}else{
					flag=false;
				}
				String assYear = cell[9].getContents().trim() ;
				if((assYear == null) || assYear.equals("")){
					return "第" + count + "行，评估年份不能为空" ;
				}
				if(!TimeUtil.judgeFormat3(assYear)){
					return "第" + count + "行，评估年份格式错误!" ;
				}
				String assResult = cell[10].getContents().trim() ;
				if(assResult == null || assResult.equals("")){
					return "第" + count + "行，评估结果不能为空" ;
				}
				if(!assResult.equals("校级优秀") && !assResult.equals("校级良好")&& !assResult.equals("校级合格")&& !assResult.equals("校级不合格")){
					return "第" + count + "行，考评结论只能是“校级优秀”或“校级良好”或“校级合格”或“校级不合格”" ;
				}
				String appID = cell[11].getContents().trim();
				if(appID == null || appID.equals("")){
					return "第" + count + "行，批文号不能为空" ;
				}
				if(appID.length()>100){
					return "第" + count + "行，批文号不能超过100个字符" ;
				}
				String note = cell[12].getContents().trim();
					
				count++ ;
				String fillUnitID=null;	
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
				T743_Bean.setCheckState(Constants.WAIT_CHECK);
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
