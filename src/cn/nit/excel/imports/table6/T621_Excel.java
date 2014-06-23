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
import cn.nit.bean.di.DiMajorTwoBean;
import cn.nit.bean.di.DiResearchRoomBean;
import cn.nit.bean.di.DiSourceBean;
import cn.nit.bean.di.DiTitleLevelBean;
import cn.nit.bean.di.DiTitleNameBean;
import cn.nit.bean.table6.T621_Bean;
import cn.nit.service.di.DiCourseCategoriesService;
import cn.nit.service.di.DiCourseCharService;
import cn.nit.service.di.DiDegreeService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.di.DiEducationService;
import cn.nit.service.di.DiIdentiTypeService;
import cn.nit.service.di.DiMajorTwoService;
import cn.nit.service.di.DiResearchRoomService;
import cn.nit.service.di.DiSourceService;
import cn.nit.service.di.DiTitleLevelService;
import cn.nit.service.di.DiTitleNameService;
import cn.nit.service.table6.T621_Service;
import cn.nit.util.TimeUtil;

public class T621_Excel {

	/**
	 * 批量导入
	 * 
	 * @param cellList
	 *            {@link java.util.List<{@link jxl.Cell}>} Cell是数组
	 * @param request
	 *            {@link javax.servlet.http.HttpServletRequest}
	 * @return
	 */
	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request) {

		if ((cellList == null) || (cellList.size() < 2)) {
			return "数据不标准，请重新提交";
		}

		int count = 1;
		T621_Bean T621_bean = null;
		boolean flag = false;
		List<T621_Bean> list = new LinkedList<T621_Bean>();

		DiDepartmentService diDep = new DiDepartmentService();
		List<DiDepartmentBean> diDepList = diDep.getList();

		DiMajorTwoService diMaj2 = new DiMajorTwoService();
		List<DiMajorTwoBean> diMaj2List = diMaj2.getList();

		for (Cell[] cell : cellList) {
			try {
				if (count <= 3) {
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

				String amisPlanNum = cell[5].getContents();
				if (amisPlanNum == null || amisPlanNum.equals("")) {
					return "第" + count + "行，招生计划数不能为空";
				}

				String actulEnrollNum = cell[6].getContents();
				if (actulEnrollNum == null || actulEnrollNum.equals("")) {
					return "第" + count + "行，实际录取数不能为空";
				}

				String actulRegisterNum = cell[7].getContents();
				if (actulRegisterNum == null || actulRegisterNum.equals("")) {
					return "第" + count + "行，实际报到数不能为空";
				}

				String autoEnrollNum = cell[8].getContents();
				if (autoEnrollNum == null || autoEnrollNum.equals("")) {
					return "第" + count + "行，自主招生数不能为空，没有请添加0";
				}

				String specialtyEnrollNum = cell[9].getContents();
				if (specialtyEnrollNum == null || specialtyEnrollNum.equals("")) {
					return "第" + count + "行，招收特长生数不能为空，没有请添加0";
				}

				String inProviEnrollNum = cell[10].getContents();
				if (inProviEnrollNum == null || inProviEnrollNum.equals("")) {
					return "第" + count + "行，招收本省学生数不能为空";
				}

				String newMajEnrollNum = cell[11].getContents();
				if (newMajEnrollNum == null || newMajEnrollNum.equals("")) {
					return "第" + count + "行，新办专业招生数不能为空，没有请添加0";
				}

				count++;

				T621_bean = new T621_Bean();
				T621_bean.setFromTeaUnit(fromTeaUnit);
				T621_bean.setUnitId(unitId);
				T621_bean.setMajorName(majorName);
				T621_bean.setMajorId(majorId);
				T621_bean.setAmisPlanNum(Integer.parseInt(amisPlanNum));
				T621_bean.setActulEnrollNum(Integer.parseInt(actulEnrollNum));
				T621_bean.setActulRegisterNum(Integer.parseInt(actulRegisterNum));
				T621_bean.setAutoEnrollNum(Integer.parseInt(autoEnrollNum));
				T621_bean.setSpecialtyEnrollNum(Integer.parseInt(specialtyEnrollNum));
				T621_bean.setInProviEnrollNum(Integer.parseInt(inProviEnrollNum));
				T621_bean.setNewMajEnrollNum(Integer.parseInt(newMajEnrollNum));
				// 插入时间
				T621_bean.setTime(new Date());
				list.add(T621_bean);

			} catch (Exception e) {
				e.printStackTrace();
				return "上传文件不合法！！！";
			}
		}

		flag = false;
		T621_Service T621_services = new T621_Service();
		flag = T621_services.batchInsert(list);

		if (flag) {
			return null;
		} else {
			return "数据存储失败，请联系管理员";
		}
	}
}