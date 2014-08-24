package cn.nit.excel.imports.table6;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
import cn.nit.bean.di.DiAwardLevelBean;
import cn.nit.bean.di.DiAwardTypeBean;
import cn.nit.bean.di.DiContestLevelBean;
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
import cn.nit.bean.table6.T659_Bean;
import cn.nit.service.di.DiAwardLevelService;
import cn.nit.service.di.DiAwardTypeService;
import cn.nit.service.di.DiContestLevelService;
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
import cn.nit.service.table6.T659_Service;

import cn.nit.util.TimeUtil;

public class T659_Excel {

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
		T659_Bean T659_bean = null;
		boolean flag = false;
		List<T659_Bean> list = new LinkedList<T659_Bean>();

		DiDepartmentService diDep = new DiDepartmentService();
		List<DiDepartmentBean> diDepList = diDep.getList();
		
		DiAwardLevelService diAward = new DiAwardLevelService();
		List<DiAwardLevelBean> diAwardList = diAward.getList();
		
		DiContestLevelService diAwardType = new DiContestLevelService();
		List<DiContestLevelBean> diAwardTypeList = diAwardType.getList();

		for (Cell[] cell : cellList) {
			try {
				if (count <= 5) {
					count++;
					continue;
				}
	
				String teaUnit = cell[1].getContents();
				if (teaUnit == null || teaUnit.equals("")) {
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
						if(unitName.equals(teaUnit)){
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

				String exchangeStuSum = cell[3].getContents();

				if (exchangeStuSum == null || exchangeStuSum.equals("")) {
					return "第" + count + "行，交流学生总数不能为空，没有请添加0";
				}
				if(!this.isNumeric(exchangeStuSum)){
					return "第" + count + "行，交流学生总数只能填数字";
				}

				String fromSchToOverseas = cell[4].getContents();
				if (fromSchToOverseas == null || fromSchToOverseas.equals("")) {
					return "第" + count + "行，本校到境外不能为空，没有请添加0";
				}
				if(!this.isNumeric(fromSchToOverseas)){
					return "第" + count + "行，本校到境外数只能填数字";
				}
				
				String fromSchToDomestic = cell[5].getContents();
				if (fromSchToDomestic == null || fromSchToDomestic.equals("")) {
					return "第" + count + "行，本校到境内不能为空，没有请添加0";
				}
				if(!this.isNumeric(fromSchToDomestic)){
					return "第" + count + "行，交本校到境内数只能填数字";
				}
				
				String fromDomesticToSch = cell[6].getContents();
				if (fromDomesticToSch == null || fromDomesticToSch.equals("")) {
					return "第" + count + "行，境内到本校不能为空，没有请添加0";
				}
				if(!this.isNumeric(fromDomesticToSch)){
					return "第" + count + "行，境内到本校数只能填数字";
				}
				
				String fromOverseasToSch = cell[7].getContents();
				if (fromOverseasToSch == null || fromOverseasToSch.equals("")) {
					return "第" + count + "行，境外到本校不能为空，没有请添加0";
				}
				if(!this.isNumeric(fromOverseasToSch)){
					return "第" + count + "行，境外到本校数只能填数字";
				}
				
				count++;

				T659_bean = new T659_Bean();

				T659_bean.setTeaUnit(teaUnit);
				T659_bean.setUnitId(unitId);
				T659_bean.setExchangeStuSum(Integer.parseInt(exchangeStuSum));
				T659_bean.setFromDomesticToSch(Integer.parseInt(fromDomesticToSch));
				T659_bean.setFromOverseasToSch(Integer.parseInt(fromOverseasToSch));
				T659_bean.setFromSchToDomestic(Integer.parseInt(fromSchToDomestic));
				T659_bean.setFromSchToOverseas(Integer.parseInt(fromSchToOverseas));
				

				// 插入时间
				T659_bean.setTime(TimeUtil.changeDateY(selectYear));
				list.add(T659_bean);

			} catch (Exception e) {
				e.printStackTrace();
				return "上传文件不合法！！！";
			}
		}

		flag = false;
		T659_Service T659_services = new T659_Service();
		flag = T659_services.batchInsert(list);

		if (flag) {
			return "数据存储成功";
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