package cn.nit.excel.imports.table6;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

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
import cn.nit.bean.di.DiMajorOneBean;
import cn.nit.bean.di.DiMajorTwoBean;
import cn.nit.bean.di.DiResearchRoomBean;
import cn.nit.bean.di.DiSourceBean;
import cn.nit.bean.di.DiTitleLevelBean;
import cn.nit.bean.di.DiTitleNameBean;
import cn.nit.bean.table6.T672_Bean;
import cn.nit.service.di.DiCourseCategoriesService;
import cn.nit.service.di.DiCourseCharService;
import cn.nit.service.di.DiDegreeService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.di.DiEducationService;
import cn.nit.service.di.DiIdentiTypeService;
import cn.nit.service.di.DiMajorOneService;
import cn.nit.service.di.DiMajorTwoService;
import cn.nit.service.di.DiResearchRoomService;
import cn.nit.service.di.DiSourceService;
import cn.nit.service.di.DiTitleLevelService;
import cn.nit.service.di.DiTitleNameService;
import cn.nit.service.table6.T672_Service;

import cn.nit.util.TimeUtil;

public class T672_Excel {

	/**
	 * 批量导入
	 * 
	 * @param cellList
	 *            {@link java.util.List<{@link jxl.Cell}>} Cell是数组
	 * @param request
	 *            {@link javax.servlet.http.HttpServletRequest}
	 * @return
	 */
	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request, String selectYear) {

		if ((cellList == null) || (cellList.size() < 2)) {
			return "数据不标准，请重新提交";
		}

		int count = 1;
		T672_Bean T672_bean = null;
		boolean flag = false;
		List<T672_Bean> list = new LinkedList<T672_Bean>();

		DiDepartmentService diDep = new DiDepartmentService();
		List<DiDepartmentBean> diDepList = diDep.getList();

		DiMajorTwoService diMaj2 = new DiMajorTwoService();
		List<DiMajorTwoBean> diMaj2List = diMaj2.getList();

		for (Cell[] cell : cellList) {
			try {
				if (count <= 4) {
					count++;
					continue;
				}
				
	
				String stuName = cell[1].getContents();
				if (stuName == null || stuName.equals("")) {
					return "第" + count + "行，学生姓名不能为空";
				}
				
				String stuId = cell[2].getContents();
				if (stuId == null || stuId.equals("")) {
					return "第" + count + "行，学号不能为空";
				}


				String fromTeaUnit = cell[3].getContents();
				if (fromTeaUnit == null || fromTeaUnit.equals("")) {
					return "第" + count + "行，所在教学单位不能为空";
				}
				
				String unitId = cell[4].getContents();
				if (unitId == null || unitId.equals("")) {
					return "第" + count + "行，单位号不能为空";
				}
				
		
				String unitName = null;
				for (DiDepartmentBean diDepBean : diDepList) {
					if (diDepBean.getUnitId().equals(unitId)) {
						unitName = diDepBean.getUnitName();
						if(unitName.equals(fromTeaUnit)){
							flag = true;
							break;
						}
						
					}// if
				}// for
	

				if (!flag) {
					return "第" + count + "行，单位号和所属教学单位不匹配";
				} else {
					flag = false;
				}
				


				String fromMaj = cell[5].getContents();
				if (fromMaj == null || fromMaj.equals("")) {
					return "第" + count + "行，所在专业不能为空";
				}

				String majId = cell[6].getContents();
				if (majId == null || majId.equals("")) {
					return "第" + count + "行，所在专业代码不能为空";
				}
				
				String MajName = null;
				for (DiMajorTwoBean diMaj2Bean : diMaj2List) {
					if (diMaj2Bean.getMajorNum().equals(majId)) {
						MajName = diMaj2Bean.getMajorName();
						if(MajName.equals(fromMaj)){
							flag = true;
							break;
						}
						
					}// if
				}// for
	

				if (!flag) {
					return "第" + count + "行，专业名称和专业代码不匹配";
				} else {
					flag = false;
				}
				

		

				
				
				String fromClass = cell[7].getContents();
				if (fromClass == null || fromClass.equals("")) {
					return "第" + count + "行，所在班级不能为空";
				}
	

				
				String dualDegreeFromTeaUnit = cell[8].getContents();
				if (dualDegreeFromTeaUnit == null || dualDegreeFromTeaUnit.equals("")) {
					return "第" + count + "行，双学位所在教学单位不能为空";
				}
				
				String dualDegreeUnitId = cell[9].getContents();
				if (dualDegreeUnitId == null || dualDegreeUnitId.equals("")) {
					return "第" + count + "行，单位号不能为空";
				}
				
		
				String minorUnitName = null;
				for (DiDepartmentBean diDepBean : diDepList) {
					if (diDepBean.getUnitId().equals(dualDegreeUnitId)) {
						minorUnitName = diDepBean.getUnitName();
						if(minorUnitName.equals(dualDegreeFromTeaUnit)){
							flag = true;
							break;
						}
						
					}// if
				}// for
	

				if (!flag) {
					return "第" + count + "行，单位号和所属教学单位不匹配";
				} else {
					flag = false;
				}
				
				String dualDegreeMaj = cell[10].getContents();
				if (dualDegreeMaj == null || dualDegreeMaj.equals("")) {
					return "第" + count + "行，双学位专业不能为空";
				}

				String dualDegreeId = cell[11].getContents();
				if (dualDegreeId == null || dualDegreeId.equals("")) {
					return "第" + count + "行，双学位专业代码不能为空";
				}
				
				String minorMajName = null;
				for (DiMajorTwoBean diMaj2Bean : diMaj2List) {
					if (diMaj2Bean.getMajorNum().equals(dualDegreeId)) {
						minorMajName = diMaj2Bean.getMajorName();
						if(minorMajName.equals(dualDegreeMaj)){
							flag = true;
							break;
						}
						
					}// if
				}// for
	

				if (!flag) {
					return "第" + count + "行，专业名称和专业代码不匹配";
				} else {
					flag = false;
				}
				
				String beginTime = cell[12].getContents();
				if (beginTime == null || beginTime.equals("")) {
					return "第" + count + "行，起始时间不能为空";
				}
				if(!this.judgeFormat1(beginTime)){
					return "第" + count + "行，起始时间格式为：2013-02";
				}

				String graduateTime = cell[13].getContents();
				if (graduateTime == null || graduateTime.equals("")) {
					return "第" + count + "行，预计毕业时间不能为空";
				}
				if(!this.judgeFormat1(graduateTime)){
					return "第" + count + "行，预计毕业时间格式为：2013-02";
				}
				
				
				count++;

				T672_bean = new T672_Bean();
							
				T672_bean.setStuName(stuName);
				T672_bean.setStuId(stuId);
				T672_bean.setFromTeaUnit(fromTeaUnit);
				T672_bean.setUnitId(unitId);
				T672_bean.setFromMaj(fromMaj);
				T672_bean.setMajId(majId);
				T672_bean.setFromClass(fromClass);
				
				T672_bean.setDualDegreeFromTeaUnit(dualDegreeFromTeaUnit);
				T672_bean.setDualDegreeUnitId(dualDegreeUnitId);
				T672_bean.setDualDegreeMaj(dualDegreeMaj);
				T672_bean.setDualDegreeId(dualDegreeId);
				T672_bean.setBeginTime(new SimpleDateFormat("yyyy-MM-dd").parse(beginTime));
				T672_bean.setGraduateTime(new SimpleDateFormat("yyyy-MM-dd").parse(graduateTime));

				// 插入时间
				T672_bean.setTime(TimeUtil.changeDateY(selectYear));
				list.add(T672_bean);

			} catch (Exception e) {
				e.printStackTrace();
				return "上传文件不合法！！！";
			}
		}

		flag = false;
		T672_Service T672_services = new T672_Service();
		flag = T672_services.batchInsert(list);

		if (flag) {
			return null;
		} else {
			return "数据存储失败，请联系管理员";
		}
	}
	
	/**判断字符串格式是否为2013-02*/
	public static boolean judgeFormat1(String dataString){
		boolean flag=false;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM") ;
		Date date=null;
		try{
			date = sf.parse(dataString) ;
			flag = true;
		}catch(ParseException e){
			flag=false;
		}
		return flag;
	}


}