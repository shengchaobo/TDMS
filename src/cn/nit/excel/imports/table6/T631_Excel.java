package cn.nit.excel.imports.table6;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
import cn.nit.bean.table6.T631_Bean;
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
import cn.nit.service.table6.T631_Service;

import cn.nit.util.TimeUtil;

public class T631_Excel {

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
		T631_Bean T631_bean = null;
		boolean flag = false;
		List<T631_Bean> list = new LinkedList<T631_Bean>();

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
				
				String fromTeaUnit = cell[1].getContents().trim();
				if (fromTeaUnit == null || fromTeaUnit.equals("")) {
					return "第" + count + "行，所属教学单位不能为空";
				}
				
				String unitId = cell[2].getContents().trim();
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
				

				String majorName = cell[3].getContents().trim();
				if (majorName == null || majorName.equals("")) {
					return "第" + count + "行，专业名称不能为空";
				}

				String majorId = cell[4].getContents().trim();
				if (majorId == null || majorId.equals("")) {
					return "第" + count + "行，专业代码不能为空";
				}
				
				String MajName = null;
				for (DiMajorTwoBean diMaj2Bean : diMaj2List) {
					if (diMaj2Bean.getMajorNum().equals(majorId)) {
						MajName = diMaj2Bean.getMajorName();
						if(MajName.equals(majorName)){
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
				

				String thisYearGraduNum = cell[5].getContents().trim();
				if (thisYearGraduNum == null || thisYearGraduNum.equals("")) {
					return "第" + count + "行，应届毕业生数不能为空";
				}
				if(!this.isNumeric(thisYearGraduNum)){
					return "第" + count + "行，应届毕业生数只能填数字";
				}

				String thisYearNotGraduNum = cell[6].getContents().trim();
				if (thisYearNotGraduNum == null || thisYearNotGraduNum.equals("")) {
					return "第" + count + "行，应届生中未按时毕业数不能为空，没有请添加0";
				}
				if(!this.isNumeric(thisYearNotGraduNum)){
					return "第" + count + "行，应届生中未按时毕业数只能填数字";
				}

				String awardDegreeNum = cell[7].getContents().trim();
				if (awardDegreeNum == null || awardDegreeNum.equals("")) {
					return "第" + count + "行，授予学位数不能为空";
				}
				if(!this.isNumeric(awardDegreeNum)){
					return "第" + count + "行，授予学位数只能填数字";
				}
				
				if(Integer.parseInt(awardDegreeNum) > Integer.parseInt(thisYearGraduNum)){
					return "授予学位数应当小于等于应届毕业生数";
				}
				
				count++;

				T631_bean = new T631_Bean();
							
				T631_bean.setTeaUnit(fromTeaUnit);
				T631_bean.setUnitId(unitId);
				T631_bean.setMajorName(majorName);
				T631_bean.setMajorId(majorId);
				T631_bean.setThisYearGraduNum(Integer.parseInt(thisYearGraduNum));
				T631_bean.setThisYearNotGraduNum(Integer.parseInt(thisYearNotGraduNum));
				T631_bean.setAwardDegreeNum(Integer.parseInt(awardDegreeNum));

				// 插入时间
				T631_bean.setTime(TimeUtil.changeDateY(selectYear));
				list.add(T631_bean);

			} catch (Exception e) {
				e.printStackTrace();
				return "上传文件不合法！！！";
			}
		}

		flag = false;
		T631_Service T631_services = new T631_Service();
		flag = T631_services.batchInsert(list);

		if (flag) {
			return null;
		} else {
			return "数据存储失败，请联系管理员";
		}
	}
	

	/**判断字符串是否是数字*/
	public boolean isNumeric(String str){
		  for (int i = str.length();--i>=0;){   
		   if (!Character.isDigit(str.charAt(i))){
		    return false;
		   }
		  }
		  return true;
		 }
	
}