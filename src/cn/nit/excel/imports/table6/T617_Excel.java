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
import cn.nit.bean.table6.T617_Bean;
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
import cn.nit.service.table6.T617_Service;
import cn.nit.util.TimeUtil;

public class T617_Excel {

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
		T617_Bean T617_bean = null;
		boolean flag = false;
		List<T617_Bean> list = new LinkedList<T617_Bean>();

		DiDepartmentService diDep = new DiDepartmentService();
		List<DiDepartmentBean> diDepList = diDep.getList();

		DiMajorOneService diMaj2 = new DiMajorOneService();
		List<DiMajorOneBean> diMaj2List = diMaj2.getList();

		for (Cell[] cell : cellList) {
			try {
				if (count <= 5) {
					count++;
					continue;
				}
	
				String fromTeaUnit = cell[1].getContents();
				if (fromTeaUnit == null || fromTeaUnit.equals("")) {
					return "第" + count + "行，教学单位不能为空";
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
					return "第" + count + "行，单位号和教学单位不匹配";
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


				String juniorStuSumNum = cell[6].getContents();
				System.out.println("juniorStuSumNum:"+juniorStuSumNum);
				if (juniorStuSumNum == null || juniorStuSumNum.equals("")) {
					return "第" + count + "行，在校生总人数不能为空，没有请添加0";
				}
				if(!this.isNumeric(juniorStuSumNum)){
					return "第" + count + "行，在校生总人数只能填数字";
				}

				String juniorOneStuNum = cell[7].getContents();
				if (juniorOneStuNum == null || juniorOneStuNum.equals("")) {
					return "第" + count + "行，一年级生人数不能为空，没有请添加0";
				}
				if(!this.isNumeric(juniorOneStuNum)){
					return "第" + count + "行，一年级生人数只能填数字";
				}

				String juniorTwoStuNum = cell[8].getContents();
				if (juniorTwoStuNum == null || juniorTwoStuNum.equals("")) {
					return "第" + count + "行，二年级生人数不能为空，没有请添加0";
				}
				if(!this.isNumeric(juniorTwoStuNum)){
					return "第" + count + "行，二年级生人数只能填数字";
				}

				String juniorThreeStuNum = cell[9].getContents();
				if (juniorThreeStuNum == null || juniorThreeStuNum.equals("")) {
					return "第" + count + "行，三年级生人数不能为空，没有请添加0";
				}
				if(!this.isNumeric(juniorThreeStuNum)){
					return "第" + count + "行，三年级生人数只能填数字";
				}


				count++;

				T617_bean = new T617_Bean();
							
				T617_bean.setTeaUnit(fromTeaUnit);
				T617_bean.setUnitId(unitId);
				T617_bean.setMajorName(majorName);
				T617_bean.setMajorId(majorId);
				T617_bean.setMajorFieldName(majorFieldName);
				
				T617_bean.setJuniorStuSumNum(Integer.parseInt(juniorStuSumNum));
				T617_bean.setJuniorOneStuNum(Integer.parseInt(juniorOneStuNum));
				T617_bean.setJuniorTwoStuNum(Integer.parseInt(juniorTwoStuNum));
				T617_bean.setJuniorThreeStuNum(Integer.parseInt(juniorThreeStuNum));

		
				// 插入时间
				T617_bean.setTime(TimeUtil.changeDateY(selectYear));
				list.add(T617_bean);

			} catch (Exception e) {
				e.printStackTrace();
				return "上传文件不合法！！！";
			}
		}

		flag = false;
		T617_Service T617_services = new T617_Service();
		flag = T617_services.batchInsert(list);

		if (flag) {
			return "数据存储成功!";
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