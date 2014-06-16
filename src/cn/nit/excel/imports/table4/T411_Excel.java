package cn.nit.excel.imports.table4;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.Cell;
import cn.nit.bean.di.DiCourseCategoriesBean;
import cn.nit.bean.di.DiCourseCharBean;
import cn.nit.bean.di.DiDegreeBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.di.DiEducationBean;
import cn.nit.bean.di.DiIdentiTypeBean;
import cn.nit.bean.di.DiResearchRoomBean;
import cn.nit.bean.di.DiSourceBean;
import cn.nit.bean.di.DiTitleLevelBean;
import cn.nit.bean.di.DiTitleNameBean;
import cn.nit.bean.table4.T411_Bean;
import cn.nit.service.di.DiCourseCategoriesService;
import cn.nit.service.di.DiCourseCharService;
import cn.nit.service.di.DiDegreeService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.di.DiEducationService;
import cn.nit.service.di.DiIdentiTypeService;
import cn.nit.service.di.DiResearchRoomService;
import cn.nit.service.di.DiSourceService;
import cn.nit.service.di.DiTitleLevelService;
import cn.nit.service.di.DiTitleNameService;
import cn.nit.service.table4.T411_Service;
import cn.nit.util.TimeUtil;

public class T411_Excel {

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
		T411_Bean T411_bean = null ;
		boolean flag = false ;
		List<T411_Bean> list = new LinkedList<T411_Bean>() ;
		
		DiIdentiTypeService diIdenti = new DiIdentiTypeService();
		List<DiIdentiTypeBean> diIdentiBeanList = diIdenti.getList();
		
		DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		
		DiResearchRoomService diResearchRoomSer = new DiResearchRoomService() ;
		List<DiResearchRoomBean> diResearchRoomBeanList = diResearchRoomSer.getList() ;
		
		DiEducationService diEdu = new DiEducationService();
		List<DiEducationBean> diEduList = diEdu.getList();
		
		DiDegreeService diDegree = new DiDegreeService();
		List<DiDegreeBean> diDegreeList = diDegree.getList();
		
		DiSourceService diSource = new DiSourceService();
		List<DiSourceBean> diSourceList = diSource.getList();
		
		DiTitleLevelService diTitle = new DiTitleLevelService();
		List<DiTitleLevelBean> diTitleList = diTitle.getList();
		
		DiTitleNameService diTitleName = new DiTitleNameService();
		List<DiTitleNameBean> diTitleNameList = diTitleName.getList();
				
		for(Cell[] cell : cellList){
			try{
				if(count<=2){
					count++;
					continue;
				}
				
				String name = cell[1].getContents() ;
				String teaId = cell[2].getContents() ;
				
				System.out.println(name);
				System.out.println(teaId);
				if(name == null || name.equals("")){
					return "第" + count + "行，教师名称不能为空" ;
				}
				
				if((teaId == null) || teaId.equals("")){
					return "第" + count + "行，教工号不能为空" ;
				}
				
				String gender = cell[3].getContents();
				if(gender == null || gender.equals("")){
					return "第" + count + "行，教师性别不能为空" ;
				}
				
				String birthday = cell[4].getContents() ;
				if((birthday == null) || birthday.equals("")){
					return "第" + count + "行，教师出生日期不能为空" ;
				}
				
				String admisTime = cell[5].getContents() ;
				if((admisTime == null) || admisTime.equals("")){
					return "第" + count + "行，教师入校时间不能为空" ;
				}
				
				String teaState = cell[6].getContents() ;
				if((teaState == null) || teaState.equals("")){
					return "第" + count + "行，教师任职状态不能为空" ;
				}
				
				String beginTime = cell[7].getContents() ;
				if((beginTime == null) || beginTime.equals("")){
					return "第" + count + "行，教师本科工作开始时间不能为空" ;
				}
				
				String IDCode = cell[8].getContents() ;
				if((IDCode == null) || IDCode.equals("")){
					return "第" + count + "行，教师身份类别不能为空" ;
				}
				
				String id = null;
				for(DiIdentiTypeBean diIdentibean : diIdentiBeanList){
					if(diIdentibean.getIdentiType().equals(IDCode)){
						id = diIdentibean.getIndexId() ;
						flag = true  ;
						break ;
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，教师身份类别不存在" ;
				}else{
					flag = false ;
				}
				
				
				String office = cell[9].getContents() ;
				String officId = cell[10].getContents() ;
				
				if(office == null || office.equals("")){
					return "第" + count + "行，所属部门不能为空" ;
				}
				
				if(officId == null || officId.equals("")){
					return "第" + count + "行，所属部门号不能为空" ;
				}			
				
				for(DiDepartmentBean diDepartBean : diDepartBeanList){
					if(diDepartBean.getUnitId().equals(officId)){
						if(diDepartBean.getUnitName().equals(office)){
							flag = true ;
							break ;
						}else{
							return "第" + count + "行，所属部门与所属部门号不对应" ;
						}
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，没有与之相匹配的部门编号" ;
				}else{
					flag = false ;
				}
				
				String unit = cell[11].getContents() ;
				String unitId = cell[12].getContents() ;
				
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
				
				String fromTeaResOffice = cell[13].getContents() ;
				String teaResOfficeID = cell[14].getContents() ;
				
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
				
				String edu = cell[15].getContents() ;
				
				if(edu == null || edu.equals("")){
					return "第" + count + "行，教师学历不能为空" ;
				}
				String eduID = null;
				for(DiEducationBean eduBean : diEduList){
					if(eduBean.getEducation().equals(edu)){
						eduID = eduBean.getIndexId() ;
						flag = true  ;
						break ;
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，学历类别不存在" ;
				}else{
					flag = false ;
				}
				
				String degree = cell[16].getContents() ;
				
				if(degree == null || degree.equals("")){
					return "第" + count + "行，教师最高学历不能为空" ;
				}
				String degreeID = null;
				for(DiDegreeBean digreeBean : diDegreeList){
					if(digreeBean.getDegree().equals(degree)){
						degreeID = digreeBean.getIndexId() ;
						flag = true  ;
						break ;
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，最高学历不存在" ;
				}else{
					flag = false ;
				}
				
				String graSch = cell[17].getContents() ;
				String major = cell[18].getContents() ;
				
				String source = cell[19].getContents() ;
				
				if(source == null || source.equals("")){
					return "第" + count + "行，教师学缘不能为空" ;
				}
				String sourceID = null;
				for(DiSourceBean SourceBean : diSourceList){
					if(SourceBean.getSource().equals(source)){
						sourceID = SourceBean.getIndexId() ;
						flag = true  ;
						break ;
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，教师学缘不存在" ;
				}else{
					flag = false ;
				}
				
				String adminTitle = cell[20].getContents() ;
				
				
				String majTitle = cell[21].getContents() ;
				
				if(majTitle == null || majTitle.equals("")){
					return "第" + count + "行，教师专业技术职称不能为空" ;
				}
				String majID = null;
				for(DiTitleLevelBean titleLevelBean : diTitleList){
					if(titleLevelBean.getTitleLevel().equals(majTitle)){
						majID = titleLevelBean.getIndexId() ;
						flag = true  ;
						break ;
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，专业技术职称不存在" ;
				}else{
					flag = false ;
				}
				
				String teaTitle = cell[22].getContents() ;
				
				if(teaTitle == null || teaTitle.equals("")){
					return "第" + count + "行，教师教学职称不能为空" ;
				}
				String teaTitleID = null;
				for(DiTitleNameBean titleNameBean : diTitleNameList){
					if(titleNameBean.getTitleName().equals(teaTitle)){
						teaTitleID = titleNameBean.getIndexId() ;
						flag = true  ;
						break ;
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，教学职称不存在" ;
				}else{
					flag = false ;
				}
				
				
				String notTeaTitle = cell[23].getContents() ;
				
				String subject = cell[24].getContents() ;
				
				if(subject == null || subject.equals("")){
					return "第" + count + "行，学科类别不能为空" ;
				}
				
				String doubleTea = cell[25].getContents() ;
				boolean bdoubleTea;
				
				if(doubleTea == null || doubleTea.equals("")){
					return "第" + count + "行，是否是双师型教师不能为空" ;
				}else{
					if(doubleTea == "是"){
						bdoubleTea = true;
					}else{
						bdoubleTea = false;
					}
				}
				
				String industry = cell[26].getContents() ;
				boolean bindustry;
				
				if(industry == null || industry.equals("")){
					return "第" + count + "行，是否具有行业背景不能为空" ;
				}else{
					if(doubleTea == "是"){
						bindustry = true;
					}else{
						bindustry = false;
					}
				}
				
				String engineer = cell[27].getContents() ;
				boolean bengineer;
				
				if(engineer == null || engineer.equals("")){
					return "第" + count + "行，是否具有工程背景不能为空" ;
				}else{
					if(engineer == "是"){
						bengineer = true;
					}else{
						bengineer = false;
					}
				}
				
				String teaBase = cell[28].getContents() ;
				boolean bteaBase;
				
				if(teaBase == null || teaBase.equals("")){
					return "第" + count + "行，是否是教师库教师不能为空" ;
				}else{
					if(teaBase == "是"){
						bteaBase = true;
					}else{
						bteaBase = false;
					}
				}
				
/*				String note = cell[29].getContents() ;
				
				if(note.length() > 1000){
					return "第" + count + "行，备注不能超过500个汉字" ;
				}*/
				
				count++ ;
				
				
				T411_bean = new T411_Bean() ;
				T411_bean.setTeaName(name);
				T411_bean.setTeaId(teaId);
				T411_bean.setGender(gender);
				T411_bean.setBirthday(TimeUtil.changeDateYM(birthday));
				T411_bean.setAdmisTime(TimeUtil.changeDateYM(admisTime));
				T411_bean.setTeaState(teaState);
				T411_bean.setBeginWorkTime(TimeUtil.changeDateY(beginTime));
				T411_bean.setIdcode(id);
				T411_bean.setOfficeID(officId);
				T411_bean.setFromOffice(office);
				T411_bean.setFromUnit(unit);
				T411_bean.setUnitId(unitId);
				T411_bean.setFromTeaResOffice(fromTeaResOffice);
				T411_bean.setTeaResOfficeID(teaResOfficeID);
				T411_bean.setEducation(eduID);
				T411_bean.setTopDegree(degreeID);
				T411_bean.setGraSch(graSch);
				T411_bean.setMajor(major);
				T411_bean.setSource(sourceID);
				T411_bean.setAdminLevel(adminTitle);
				T411_bean.setMajTechTitle(majID);
				T411_bean.setTeaTitle(teaTitleID);
				T411_bean.setNotTeaTitle(notTeaTitle);
				T411_bean.setSubjectClass(subject);
				T411_bean.setDoubleTea(bdoubleTea);
				T411_bean.setIndustry(bindustry);
				T411_bean.setEngineer(bengineer);
				T411_bean.setTeaBase(bteaBase);
				list.add(T411_bean);
								
			}catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
		}
		
		flag = false ;
		T411_Service T411_services = new T411_Service() ;
		flag = T411_services.batchInsert(list) ;
		
		if(flag){
			return null ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
	}

}
