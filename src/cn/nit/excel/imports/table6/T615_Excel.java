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
import cn.nit.bean.table6.T615_Bean;
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
import cn.nit.service.table6.T615_Service;
import cn.nit.util.TimeUtil;

public class T615_Excel {

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
		T615_Bean T615_bean = null;
		boolean flag = false;
		List<T615_Bean> list = new LinkedList<T615_Bean>();

		DiDepartmentService diDep = new DiDepartmentService();
		List<DiDepartmentBean> diDepList = diDep.getList();

		DiMajorTwoService diMaj2 = new DiMajorTwoService();
		List<DiMajorTwoBean> diMaj2List = diMaj2.getList();

		for (Cell[] cell : cellList) {
			try {
				if (count <=3) {
					count++;
					continue;
				}

				String majorName = cell[1].getContents();
				if (majorName == null || majorName.equals("")) {
					return "第" + count + "行，校内专业（大类）名称不能为空";
				}

				String majorId = cell[2].getContents();
				if (majorId == null || majorId.equals("")) {
					return "第" + count + "行，校内专业（大类）代码不能为空";
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
			
				String fromTeaUnit = cell[3].getContents();
				if (fromTeaUnit == null || fromTeaUnit.equals("")) {
					return "第" + count + "行，所属教学单位不能为空";
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

				String schLen = cell[5].getContents();
				if (schLen == null || schLen.equals("")) {
					return "第" + count + "行，学制不能为空";
				}
				if(!this.isNumeric(schLen)){
					return "第" + count + "行，学制只能填写数字";
				}

				String schStuSumNum = cell[6].getContents();
				if (schStuSumNum == null || schStuSumNum.equals("")) {
					return "第" + count + "行，在校生总人数不能为空";
				}
				if(!this.isNumeric(schStuSumNum)){
					return "第" + count + "行，在校生总人数只能填写数字";
				}

				String freshmanNum = cell[7].getContents();
				if (freshmanNum == null || freshmanNum.equals("")) {
					return "第" + count + "行，一年级生人数不能为空";
				}
				if(!this.isNumeric(freshmanNum)){
					return "第" + count + "行，一年级生人数只能填写数字";
				}

				String sophomoreNum = cell[8].getContents();
				if (sophomoreNum == null || sophomoreNum.equals("")) {
					return "第" + count + "行，二年级生人数不能为空，没有请添加0";
				}
				if(!this.isNumeric(sophomoreNum)){
					return "第" + count + "行，二年级生人数只能填写数字";
				}

				String juniorNum = cell[9].getContents();
				if (juniorNum == null || juniorNum.equals("")) {
					return "第" + count + "行，三年级生人数不能为空，没有请添加0";
				}
				if(!this.isNumeric(juniorNum)){
					return "第" + count + "行，三年级生人数只能填写数字";
				}

				String seniorNum = cell[10].getContents();
				if (seniorNum == null || seniorNum.equals("")) {
					return "第" + count + "行，四年级生人数不能为空，没有请添加0";
				}
				if(!this.isNumeric(seniorNum)){
					return "第" + count + "行，四年级生人数只能填写数字";
				}

				String otherGradeNum = cell[11].getContents();
				if (otherGradeNum == null || otherGradeNum.equals("")) {
					return "第" + count + "行，五年级生及以上人数不能为空，没有请添加0";
				}
				if(!this.isNumeric(otherGradeNum)){
					return "第" + count + "行，五年级生及以上人数只能填写数字";
				}
				
				String minorNum = cell[11].getContents();
				if (minorNum == null || minorNum.equals("")) {
					return "第" + count + "行，辅修学生人数不能为空，没有请添加0";
				}
				if(!this.isNumeric(minorNum)){
					return "第" + count + "行，辅修学生人数只能填写数字";
				}
				
				String dualDegreeNum = cell[11].getContents();
				if (dualDegreeNum == null || dualDegreeNum.equals("")) {
					return "第" + count + "行，双学位学生人数不能为空，没有请添加0";
				}
				if(!this.isNumeric(dualDegreeNum)){
					return "第" + count + "行，双学位学生人数只能填写数字";
				}
				
				String changeInNum = cell[11].getContents();
				if (changeInNum == null || changeInNum.equals("")) {
					return "第" + count + "行，转入人数不能为空，没有请添加0";
				}
				if(!this.isNumeric(changeInNum)){
					return "第" + count + "行，转入人数只能填写数字";
				}
				
				String changeOutNum = cell[11].getContents();
				if (changeOutNum == null || changeOutNum.equals("")) {
					return "第" + count + "行，转出人数不能为空，没有请添加0";
				}
				if(!this.isNumeric(changeOutNum)){
					return "第" + count + "行，转出人数只能填写数字";
				}

				count++;

				T615_bean = new T615_Bean();
				T615_bean.setMajorName(majorName);
				T615_bean.setMajorId(majorId);
				T615_bean.setFromUnitId(fromTeaUnit);
				T615_bean.setUnitId(unitId);
				
				T615_bean.setSchLen(Integer.parseInt(schLen));
				T615_bean.setSchStuSumNum(Integer.parseInt(schStuSumNum));
				T615_bean.setFreshmanNum(Integer.parseInt(freshmanNum));
				T615_bean.setSophomoreNum(Integer.parseInt(sophomoreNum));
				T615_bean.setJuniorNum(Integer.parseInt(juniorNum));
				T615_bean.setSeniorNum(Integer.parseInt(seniorNum));
				T615_bean.setOtherGradeNum(Integer.parseInt(otherGradeNum));
				T615_bean.setMinorNum(Integer.parseInt(minorNum));
				T615_bean.setDualDegreeNum(Integer.parseInt(dualDegreeNum));
				T615_bean.setChangeInNum(Integer.parseInt(changeInNum));
				T615_bean.setChangeOutNum(Integer.parseInt(changeOutNum));
				T615_bean.setTime(TimeUtil.changeDateY(selectYear));
				list.add(T615_bean);

			} catch (Exception e) {
				e.printStackTrace();
				return "上传文件不合法！！！";
			}
		}

		flag = false;
		T615_Service T615_services = new T615_Service();
		flag = T615_services.batchInsert(list);

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