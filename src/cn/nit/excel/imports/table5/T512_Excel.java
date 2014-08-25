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
			T512_Bean T512_Bean=new T512_Bean();
			try {
				
				if(count<5){
					count++;
					continue;
				}
				
				String term = cell[1].getContents() ;
				if(term == null || term.equals("")){
					return "第" + count + "行，学期不能为空" ;
				}
				if(term.length()>50){
					return "第" + count + "行，学期不能超过50个字符" ;
				}
				String unit = cell[2].getContents() ;
				if((unit == null) || unit.equals("")){
					return "第" + count + "行，开课单位不能为空" ;
				}
				String unitId = cell[3].getContents() ;
				if((unitId == null) || unitId.equals("")){
					return "第" + count + "行，单位号不能为空" ;
				}
				if(unit.length()>200){
					return "第" + count + "行，开课单位字数不能超过100个字" ;
				}
				if(unitId.length()>50){
					return "第" + count + "行，单位号不能超过50个字符" ;
				}
				for(DiDepartmentBean diDepartBean : diDepartBeanList){
					if(diDepartBean.getUnitId().equals(unitId)){
						if(diDepartBean.getUnitName().equals(unit)){
							flag = true ;
							break ;
						}
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，没有与开课单位相匹配的单位号" ;
				}else{
					flag = false ;
				}
				
				String major = cell[4].getContents() ;
				String majorId = cell[5].getContents() ;
				
				if(major == null || major.equals("")){
					return "第" + count + "行，上课专业名称不能为空" ;
				}
				if(major.length()>100){
					return "第" + count + "行，上课专业名称不能超过50个字" ;
				}
				if(unitId == null || unitId.equals("")){
					return "第" + count + "行，上课专业代码不能为空" ;
				}
				if(unitId.length()>50){
					return "第" + count + "行，上课专业代码不能超过50个字符" ;
				}
				
				for(DiMajorTwoBean diMajorBean : diMajorBeanList){
					if(diMajorBean.getMajorNum().equals(majorId)){
						if(diMajorBean.getMajorName().equals(major)){
							flag = true ;
							break ;
						}
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，没有与专业名称相匹配的专业代码" ;
					
				}else{
					flag = false ;
				}
				
				String csName = cell[6].getContents() ;
				if(csName == null || csName.equals("")){
					return "第" + count + "行，课程名称不能为空" ;
				}
				if(csName.length()>100){
					return "第" + count + "行，课程名称不能超过50个字" ;
				}
				String csID = cell[7].getContents() ;
				if((csID == null) || csID.equals("")){
					return "第" + count + "行，课程编号不能为空" ;
				}
				if(csID.length()>50){
					return "第" + count + "行，课程编号不能超过50个字符" ;
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
				if(pubCSType.length()>60){
					return "第" + count + "行，公选课类别不能超过30个字" ;
				}
				
				String isDoubleCS = cell[11].getContents() ;
				isDoubleCS = isDoubleCS.trim();
				boolean IsDoubleCS = false;
				if((isDoubleCS == null) || isDoubleCS.equals("")){
					return "第" + count + "行，是否双语授课不能为空" ;
				}
				if(isDoubleCS.equals("是")){
					IsDoubleCS = true;
				}
				if(isDoubleCS.equals("否")){
					IsDoubleCS = false;
				}
				if(!isDoubleCS.equals("是")&&!isDoubleCS.equals("否")){
					return "第" + count + "行，是否双语授课填写格式错误，只能填写“是”或者“否”" ;
				}
				
				String credit = cell[12].getContents() ;
				if((credit == null) || credit.equals("")){
					return "第" + count + "行，学分不能为空" ;
				}
				if(!this.iDouble(credit)){
					return "第" + count + "行，学分格式有误，只能填写数字或小数" ;
				}
				
				String sumCSHour = cell[13].getContents() ;
				if((sumCSHour == null) || sumCSHour.equals("")){
					return "第" + count + "行，总学时不能为空" ;
				}
				if(!this.isNumber(sumCSHour)){
					return "第" + count + "行，总学时格式有误，只能填数字" ;
				}
				
				String theoryCSHour = cell[14].getContents() ;
				if(theoryCSHour == null || theoryCSHour.equals("")){
					return "第" + count + "行，理论学时不能为空" ;
				}
				if(!this.isNumber(theoryCSHour)){
					return "第" + count + "行，理论学时格式有误，只能填数字" ;
				}
				
				String praCSHour = cell[15].getContents() ;
				if((praCSHour == null) || praCSHour.equals("")){
					return "第" + count + "行，实践学时不能为空" ;
				}
				if(!this.isNumber(praCSHour)){
					return "第" + count + "行，实践学时格式有误，只能填写数字" ;
				}
				
				String examWay = cell[16].getContents() ;
				if((examWay == null) || examWay.equals("")){
					return "第" + count + "行，考核方式不能为空" ;
				}
				if(!examWay.equals("考试")&&!examWay.equals("考查")){
					return "第" + count + "行，考核方式格式有误 ，只能填写“考试”或“考查”" ;
				}
				
				String planTime = cell[17].getContents() ;
				if((planTime == null) || planTime.equals("")){
					planTime = "0";
				}
//				if(planTime.length()>10){
//					return "第" + count + "行，考核方式不能为空" ;
//				}
				
				String cSGrade = cell[18].getContents() ;
				if(cSGrade == null || cSGrade.equals("")){
					return "第" + count + "行，授课年级不能为空" ;
				}
				if(!this.isNumber(cSGrade)){
					return "第" + count + "行，授课年级格式有误，只能填写数字" ;
				}
				
				String cSClass = cell[19].getContents() ;
				if((cSClass == null) || cSClass.equals("")){
					return "第" + count + "行，授课班级不能为空" ;
				}
				if(cSClass.length()>100){
					return "第" + count + "行，授课班级字数不能超过50个字" ;
				}
				String classID = cell[20].getContents() ;
				if((classID == null) || classID.equals("")){
					return "第" + count + "行，开课班号不能为空" ;
				}
				if(classID.length()>50){
					return "第" + count + "行，开课班号不能超过50个字符" ;
				}
				
				String classInfo = cell[21].getContents() ;
				if((classInfo == null) || classInfo.equals("")){
					return "第" + count + "行，合班情况不能为空" ;
				}
				if(classInfo.length()>100){
					return "第" + count + "行，合班情况字数不能超过50个字" ;
				}
				String stuNum = cell[22].getContents() ;
				if(stuNum == null || stuNum.equals("")){
					return "第" + count + "行，学生人数不能为空" ;
				}
				if(!this.isNumber(stuNum)){
					return "第" + count + "行，学生人数格式有误，只能填数字" ;
				}
				
				String cSTea = cell[23].getContents() ;
				if((cSTea == null) || cSTea.equals("")){
					return "第" + count + "行，任课教师不能为空" ;
				}
				if(cSTea.length()>200){
					return "第" + count + "行，任课教师所填字符不能超过200（一个汉字等于两个字符）" ;
				}
				
				String isAccordJob = cell[24].getContents() ;
				Boolean IsAccordJob = false;
				isAccordJob = isAccordJob.trim();
				if((isAccordJob == null) || isAccordJob.equals("")){
					return "第" + count + "行，是否符合岗位资格不能为空" ;
				}
				if(isAccordJob.equals("否")){
					IsAccordJob = false; 
				}
				if(isAccordJob.equals("是")){
					IsAccordJob = true; 
				}
				if(!isAccordJob.equals("是")&&!isAccordJob.equals("否")){
					return "第" + count + "行， 是否符合岗位资格填写格式有误，只能填写“是”或者“否”" ;
				}
				
				String teaTitle = cell[25].getContents() ;
				if((teaTitle == null) || teaTitle.equals("")){
					return "第" + count + "行，教师职称不能为空" ;
				}
				if(!teaTitle.equals("正高级")&&!teaTitle.equals("副高级")&&!teaTitle.equals("中级")&&!teaTitle.equals("初级")&&!teaTitle.equals("未定级")){
					return "第" + count + "行，教师职称格式有误，只能填写“正高级”或“副高级”或“中级”或“初级”或“未定级”";
				}
				
				String bookUseInfo = cell[26].getContents() ;
				if((bookUseInfo == null) || bookUseInfo.equals("")){
					return "第" + count + "行，使用情况不能为空" ;
				}
				if(!bookUseInfo.equals("选用")&&!bookUseInfo.equals("自编")){
					return "第" + count + "行，使用情况填写格式有误，只能填写“先用”或“自编”" ;
				}
				
				String isPlanbook = cell[27].getContents() ;
				boolean IsPlanbook = false;
				isPlanbook =isPlanbook.trim();
				if((isPlanbook == null) || isPlanbook.equals("")){
					return "第" + count + "行，是否规划教材不能为空" ;
				}
				
				if(isPlanbook.equals("否")){
					IsPlanbook = false; 
				}
				if(isPlanbook.equals("是")){
					IsPlanbook = true; 
				}
				if(!isPlanbook.equals("是")&&!isPlanbook.equals("否")){
					return "第" + count + "行，是否规划教材填写格式有误，只能填写“是”或者“否”" ;
				}
				
				
				String isAwardbook = cell[28].getContents() ;
				boolean IsAwardbook = false;
				isAwardbook = isAwardbook.trim();
				if((isAwardbook == null) || isAwardbook.equals("")){
					return "第" + count + "行，是否获奖教材不能为空" ;
				}
				if(isAwardbook.equals("否")){
					IsAwardbook = false; 
				}
				if(isAwardbook.equals("是")){
					IsAwardbook = true; 
				}
				if(!isAwardbook.equals("是")&&!isAwardbook.equals("否")){
					return "第" + count + "行，是否获奖教材填写格式有误，只能填写“是”或者“否”" ;
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
				T512_Bean.setIsDoubleCS(IsDoubleCS);
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
				T512_Bean.setIsAccordJob(IsAccordJob);
				T512_Bean.setTeaTitle(teaTitle);
				T512_Bean.setBookUseInfo(bookUseInfo);
				T512_Bean.setIsPlanbook(IsPlanbook);
				T512_Bean.setIsAwardbook(IsAwardbook);
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
	
	/**判断是否是double类型*/
	public boolean iDouble(String str){
		boolean flag = false;
		try{
			Double.parseDouble(str);
			flag = true;
		}catch(NumberFormatException ex){
			flag = false;
		}
		return flag;
	}
	
	/**判断是否是数字*/
	public boolean isNumber(String str){
		boolean flag = false;
		try{
			Integer.parseInt(str);
			flag = true;
		}catch(NumberFormatException ex){
			flag = false;
		}
		return flag;
	}
	
	public static void main(String arg[]){
		String s = "是";
		String s1 = "你";
	if(!s.equals("是")&&!s.equals("否")){
		
		System.out.println("格式错误");
	}else{
		System.out.println("格式正确");
	}
	}

}
