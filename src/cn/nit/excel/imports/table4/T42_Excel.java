package cn.nit.excel.imports.table4;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.SystemUtils;
import org.springframework.beans.BeanWrapperImpl;

import jxl.Cell;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
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
import cn.nit.bean.table4.T42_Bean;
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
import cn.nit.service.table4.T42_Service;
import cn.nit.util.TimeUtil;

public class T42_Excel {

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
		T42_Bean T42_bean = null ;
		boolean flag = false ;
		List<T42_Bean> list = new LinkedList<T42_Bean>() ;
		
		
		DiEducationService diEdu = new DiEducationService();
		List<DiEducationBean> diEduList = diEdu.getList();
		
		DiDegreeService diDegree = new DiDegreeService();
		List<DiDegreeBean> diDegreeList = diDegree.getList();		
				
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
				
				String duty = cell[3].getContents();
				if(duty == null || duty.equals("")){
					return "第" + count + "行，领导职务不能为空" ;
				}
				
				String gender = cell[4].getContents();
				if(gender == null || gender.equals("")){
					return "第" + count + "行，领导性别不能为空" ;
				}
				
				String birthday = cell[5].getContents() ;
				if((birthday == null) || birthday.equals("")){
					return "第" + count + "行，领导出生日期不能为空" ;
				}
				
				String admisTime = cell[6].getContents() ;
				if((admisTime == null) || admisTime.equals("")){
					return "第" + count + "行，领导入校时间不能为空" ;
				}
								
				String edu = cell[7].getContents() ;
				
				if(edu == null || edu.equals("")){
					return "第" + count + "行，领导学历不能为空" ;
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
				
				String degree = cell[8].getContents() ;
				
				if(degree == null || degree.equals("")){
					return "第" + count + "行，领导最高学位不能为空" ;
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
					return "第" + count + "行，最高学位不存在" ;
				}else{
					flag = false ;
				}
				
				String majTechTitle = cell[9].getContents() ;
				String forCharge = cell[10].getContents() ;
				String resume = cell[11].getContents() ;


				
				if(!flag){
					return "第" + count + "行，教师学缘不存在" ;
				}else{
					flag = false ;
				}
								
				count++ ;
				
				
				T42_bean = new T42_Bean() ;
				T42_bean.setName(name);
				T42_bean.setTeaId(teaId);
				T42_bean.setDuty(duty);
				T42_bean.setGender(gender);
				T42_bean.setBirthday(TimeUtil.changeDateYM(birthday));
				T42_bean.setJoinSchTime(TimeUtil.changeDateYM(admisTime));
				T42_bean.setEducation(eduID);
				T42_bean.setTopDegree(degreeID);
				T42_bean.setMajTechTitle(majTechTitle);
				T42_bean.setResume(resume);
				T42_bean.setForCharge(forCharge);
				//插入时间
				T42_bean.setTime(TimeUtil.changeDateY(selectYear));
				list.add(T42_bean);
								
			}catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
		}
		
		flag = false ;
		T42_Service T42_services = new T42_Service() ;
		flag = T42_services.batchInsert(list) ;
		
		if(flag){
			return null ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
	}
}