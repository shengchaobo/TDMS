package cn.nit.excel.imports.table5;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.nit.bean.di.DiCourseCategoriesBean;
import cn.nit.bean.di.DiCourseCharBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.di.DiMajorTwoBean;
import cn.nit.bean.table5.T512_Bean;
import cn.nit.service.di.DiCourseCategoriesService;
import cn.nit.service.di.DiCourseCharService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.di.DiMajorTwoService;
import cn.nit.service.table5.T512_Service;
import cn.nit.util.TimeUtil;

import jxl.Cell;

public class T512_Excel {
	
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
		T512_Bean T512_Bean=new T512_Bean();
		boolean flag=false;
	    List<T512_Bean> list=new LinkedList<T512_Bean>();
	
	    DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
	    
		DiCourseCharService diCourseCharSer=new DiCourseCharService();
		List<DiCourseCharBean> diCourseCharBeanList=diCourseCharSer.getList();
		
		DiCourseCategoriesService diCourseSer=new DiCourseCategoriesService();
		List<DiCourseCategoriesBean>  diCourseBeanList=diCourseSer.getList();
		
		DiMajorTwoService diMajorSer = new DiMajorTwoService() ;
		List<DiMajorTwoBean> diMajorBeanList = diMajorSer.getList() ;
		
		System.out.println(cellList.size());
		
		for(Cell[] cell: cellList){
			
			try {
				
				if(count<4){
					count++;
					continue;
				}
				
				String term = cell[1].getContents() ;
				if(term == null || term.equals("")){
					return "第" + count + "行，学期不能为空" ;
				}
				
				String unit = cell[2].getContents() ;
				if((unit == null) || unit.equals("")){
					return "第" + count + "行，开课单位不能为空" ;
				}
				String unitId = cell[3].getContents() ;
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
				
				String major = cell[4].getContents() ;
				String majorId = cell[5].getContents() ;
				
				if(major == null || major.equals("")){
					return "第" + count + "行，上课专业名称不能为空" ;
				}
				
				if(unitId == null || unitId.equals("")){
					return "第" + count + "行，上课专业代码不能为空" ;
				}			
				
				for(DiMajorTwoBean diMajorBean : diMajorBeanList){
					if(diMajorBean.getMajorNum().equals(majorId)){
						if(diMajorBean.getMajorName().equals(major)){
							flag = true ;
							break ;
						}else{
							return "第" + count + "行，专业名称与专业代码不对应" ;
						}
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，没有与之相匹配的专业代码" ;
				}else{
					flag = false ;
				}
				
				String csName = cell[6].getContents() ;
				if(csName == null || csName.equals("")){
					return "第" + count + "行，课程名称不能为空" ;
				}
				String csID = cell[7].getContents() ;
				if((csID == null) || csID.equals("")){
					return "第" + count + "行，课程编号不能为空" ;
				}
				String csType = cell[8].getContents() ;
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
				String csNature = cell[9].getContents() ;
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
				String pubCSType = cell[10].getContents() ;
				if(pubCSType == null || pubCSType.equals("")){
					return "第" + count + "行，公选课类别不能为空" ;
				}
				String isDoubleCS = cell[11].getContents() ;
				if((isDoubleCS == null) || isDoubleCS.equals("")){
					return "第" + count + "行，是否双语授课不能为空" ;
				}
				String credit = cell[12].getContents() ;
				if((credit == null) || credit.equals("")){
					return "第" + count + "行，学分不能为空" ;
				}
				String sumCSHour = cell[13].getContents() ;
				if((sumCSHour == null) || sumCSHour.equals("")){
					return "第" + count + "行，总学时不能为空" ;
				}
				String theoryCSHour = cell[14].getContents() ;
				if(theoryCSHour == null || theoryCSHour.equals("")){
					return "第" + count + "行，理论学时不能为空" ;
				}
				String praCSHour = cell[15].getContents() ;
				if((praCSHour == null) || praCSHour.equals("")){
					return "第" + count + "行，实践学时不能为空" ;
				}
				String examWay = cell[16].getContents() ;
				if((examWay == null) || examWay.equals("")){
					return "第" + count + "行，考核方式不能为空" ;
				}
				String planTime = cell[17].getContents() ;
				if((planTime == null) || planTime.equals("")){
					return "第" + count + "行，实习、设计时间不能为空" ;
				}
				String cSGrade = cell[18].getContents() ;
				if(cSGrade == null || cSGrade.equals("")){
					return "第" + count + "行，授课年级不能为空" ;
				}
				String cSClass = cell[19].getContents() ;
				if((cSClass == null) || cSClass.equals("")){
					return "第" + count + "行，授课班级不能为空" ;
				}
				String classID = cell[20].getContents() ;
				if((classID == null) || classID.equals("")){
					return "第" + count + "行，开课班号不能为空" ;
				}
				String classInfo = cell[21].getContents() ;
				if((classInfo == null) || classInfo.equals("")){
					return "第" + count + "行，合班情况不能为空" ;
				}
				String stuNum = cell[22].getContents() ;
				if(stuNum == null || stuNum.equals("")){
					return "第" + count + "行，学生人数不能为空" ;
				}
				String cSTea = cell[23].getContents() ;
				if((cSTea == null) || cSTea.equals("")){
					return "第" + count + "行，任课教师不能为空" ;
				}
				String isAccordJob = cell[24].getContents() ;
				if((isAccordJob == null) || isAccordJob.equals("")){
					return "第" + count + "行，是否符合岗位资格不能为空" ;
				}
				String teaTitle = cell[25].getContents() ;
				if((teaTitle == null) || teaTitle.equals("")){
					return "第" + count + "行，教师职称不能为空" ;
				}
				String bookUseInfo = cell[26].getContents() ;
				if((bookUseInfo == null) || bookUseInfo.equals("")){
					return "第" + count + "行，使用情况不能为空" ;
				}
				String isPlanbook = cell[27].getContents() ;
				if((isPlanbook == null) || isPlanbook.equals("")){
					return "第" + count + "行，是否规划教材不能为空" ;
				}
				String isAwardbook = cell[28].getContents() ;
				if((isAwardbook == null) || isAwardbook.equals("")){
					return "第" + count + "行，是否获奖教材不能为空" ;
				}
				
				
				count++ ;

				String fillUnitID = null;
				T512_Bean.setTerm(term);
				T512_Bean.setCSUnit(unit);
				T512_Bean.setUnitID(unitId);
				T512_Bean.setCSMajorName(major);
				T512_Bean.setCSMajorID(majorId);
				T512_Bean.setCSName(csName);
				T512_Bean.setCSID(csID);
				T512_Bean.setCSType(csTypeId);
				T512_Bean.setCSNature(csNatureId);
				T512_Bean.setPubCSType(pubCSType);
				T512_Bean.setIsDoubleCS(Boolean.parseBoolean(isDoubleCS));
				T512_Bean.setCredit(Double.parseDouble(credit));
				T512_Bean.setSumCSHour(Integer.parseInt(sumCSHour));
				T512_Bean.setTheoryCSHour(Integer.parseInt(theoryCSHour));
				T512_Bean.setPraCSHour(Integer.parseInt(praCSHour));
				T512_Bean.setExamWay(examWay);
				T512_Bean.setPlanTime(planTime);
				T512_Bean.setCSGrade(Integer.parseInt(cSGrade));
				T512_Bean.setCSClass(cSClass);
				T512_Bean.setClassID(classID);
				T512_Bean.setClassInfo(classInfo);
				T512_Bean.setStuNum(Integer.parseInt(stuNum));
				T512_Bean.setCSTea(cSTea);
				T512_Bean.setIsAccordJob(Boolean.parseBoolean(isAccordJob));
				T512_Bean.setTeaTitle(teaTitle);
				T512_Bean.setBookUseInfo(bookUseInfo);
				T512_Bean.setIsPlanbook(Boolean.parseBoolean(isPlanbook));
				T512_Bean.setIsAwardbook(Boolean.parseBoolean(isAwardbook));
				T512_Bean.setFillUnitID(fillUnitID);
				T512_Bean.setTime(TimeUtil.changeDateY(selectYear));
	            
				list.add(T512_Bean);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
			
		}
		flag = false ;
		T512_Service t512_Sr=new T512_Service();
		flag=t512_Sr.batchInsert(list);
		if(flag){
			return null ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
	}

}
