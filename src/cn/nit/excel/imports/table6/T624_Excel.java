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
import cn.nit.bean.table6.T624_Bean;
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
import cn.nit.service.table6.T624_Service;
import cn.nit.util.TimeUtil;

public class T624_Excel {

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
		T624_Bean T624_bean = null;
		boolean flag = false;
		List<T624_Bean> list = new LinkedList<T624_Bean>();

		DiDepartmentService diDep = new DiDepartmentService();
		List<DiDepartmentBean> diDepList = diDep.getList();

		DiMajorOneService diMaj2 = new DiMajorOneService();
		List<DiMajorOneBean> diMaj2List = diMaj2.getList();

		for (Cell[] cell : cellList) {
			try {
				if (count <= 4) {
					count++;
					continue;
				}
				
				String fromTeaUnit = cell[1].getContents();
				if (fromTeaUnit == null || fromTeaUnit.equals("")) {
					return "第" + count + "行，所属教学单位不能为空";
				}
				
				String unitId = cell[2].getContents();
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
				

				String majorName = cell[3].getContents();
				if (majorName == null || majorName.equals("")) {
					return "第" + count + "行，专业名称不能为空";
				}

				String majorId = cell[4].getContents();
				if (majorId == null || majorId.equals("")) {
					return "第" + count + "行，专业代码不能为空";
				}
				
				String MajName = null;
				for (DiMajorOneBean diMaj2Bean : diMaj2List) {
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

				String majorFieldName = cell[5].getContents();
				if (majorFieldName == null || majorFieldName.equals("")) {
					return "第" + count + "行，专业方向名称不能为空";
				}

				String isCurrentYearAdmis = cell[6].getContents();
				if (isCurrentYearAdmis == null || isCurrentYearAdmis.equals("")) {
					return "第" + count + "行，当年是否招生（含方向）不能为空";
				}

				String planAdmisNum = cell[7].getContents();
				if (planAdmisNum == null || planAdmisNum.equals("")) {
					return "第" + count + "行，当年计划招生数不能为空";
				}

				String actualAdmisNum = cell[8].getContents();
				if (actualAdmisNum == null || actualAdmisNum.equals("")) {
					return "第" + count + "行，实际录取数不能为空";
				}

				String actualRegisterNum = cell[9].getContents();
				if (actualRegisterNum == null || actualRegisterNum.equals("")) {
					return "第" + count + "行，实际报到数不能为空，没有请添加0";
				}

				String genHignSchNum = cell[10].getContents();
				if (genHignSchNum == null || genHignSchNum.equals("")) {
					return "第" + count + "行，普通高中起点数不能为空";
				}

				String secondVocationNum = cell[11].getContents();
				if (secondVocationNum == null || secondVocationNum.equals("")) {
					return "第" + count + "行，中职起点数不能为空，没有请添加0";
				}
				
				String otherNum = cell[11].getContents();
				if (otherNum == null || otherNum.equals("")) {
					return "第" + count + "行，其他人数不能为空，没有请添加0";
				}

				count++;

				T624_bean = new T624_Bean();
							
				T624_bean.setTeaUnit(fromTeaUnit);
				T624_bean.setUnitId(unitId);
				T624_bean.setMajorName(majorName);
				T624_bean.setMajorId(majorId);
				T624_bean.setMajorFieldName(majorFieldName);
				if(isCurrentYearAdmis.equals("是")){
					isCurrentYearAdmis = "true";
				}else{
					isCurrentYearAdmis = "false";
				}
				T624_bean.setIsCurrentYearAdmis(Boolean.parseBoolean(isCurrentYearAdmis));
				T624_bean.setPlanAdmisNum(Integer.parseInt(planAdmisNum));
				T624_bean.setActualAdmisNum(Integer.parseInt(actualAdmisNum));
				T624_bean.setActualRegisterNum(Integer.parseInt(actualRegisterNum));
				T624_bean.setGenHignSchNum(Integer.parseInt(genHignSchNum));
				T624_bean.setSecondVocationNum(Integer.parseInt(secondVocationNum));
				T624_bean.setOtherNum(Integer.parseInt(otherNum));
				// 插入时间
				T624_bean.setTime(TimeUtil.changeDateY(selectYear));
				list.add(T624_bean);

			} catch (Exception e) {
				e.printStackTrace();
				return "上传文件不合法！！！";
			}
		}

		flag = false;
		T624_Service T624_services = new T624_Service();
		flag = T624_services.batchInsert(list);

		if (flag) {
			return null;
		} else {
			return "数据存储失败，请联系管理员";
		}
	}
}