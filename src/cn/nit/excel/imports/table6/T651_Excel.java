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
import cn.nit.bean.table6.T651_Bean;
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
import cn.nit.service.table6.T651_Service;

import cn.nit.util.TimeUtil;

public class T651_Excel {

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
		T651_Bean T651_bean = null;
		boolean flag = false;
		List<T651_Bean> list = new LinkedList<T651_Bean>();

		DiDepartmentService diDep = new DiDepartmentService();
		List<DiDepartmentBean> diDepList = diDep.getList();
		
		DiAwardLevelService diAward = new DiAwardLevelService();
		List<DiAwardLevelBean> diAwardList = diAward.getList();
		
		DiContestLevelService diAwardType = new DiContestLevelService();
		List<DiContestLevelBean> diAwardTypeList = diAwardType.getList();

		for (Cell[] cell : cellList) {
			try {
				if (count <= 3) {
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

				String competiType = cell[3].getContents();
				
				String competiID = null;
			
				for (DiContestLevelBean diAwardBean : diAwardTypeList) {
					if (diAwardBean.getContestLevel().equals(competiType)) {
						competiID = diAwardBean.getIndexId();
						competiType = competiID;
					}// if
				}// for
				
				
				
				if (competiType == null || competiType.equals("")) {
					return "第" + count + "行，竞赛类型不能为空";
				}

				String competiName = cell[4].getContents();
				if (competiName == null || competiName.equals("")) {
					return "第" + count + "行，赛事名称不能为空";
				}
				
				String awardItem = cell[5].getContents();
				if (awardItem == null || awardItem.equals("")) {
					return "第" + count + "行，获奖项目不能为空";
				}

				String awardLevel = cell[6].getContents();
				
				String levelID = null;

				
				for (DiAwardLevelBean diAwardBean : diAwardList) {
					if (diAwardBean.getAwardLevel().equals(awardLevel)) {
						levelID = diAwardBean.getIndexId();
						awardLevel = levelID;
					}// if
				}// for
				
				if (awardLevel == null || awardLevel.equals("")) {
					return "第" + count + "行，级别不能为空或者级别ID与级别不匹配";
				}

				String awardGrade = cell[7].getContents();
				if (awardGrade == null || awardGrade.equals("")) {
					return "第" + count + "行，等级不能为空";
				}
				
				String awardFromUnit = cell[8].getContents();
				if (awardFromUnit == null || awardFromUnit.equals("")) {
					return "第" + count + "行，授予单位不能为空";
				}

				String awardTime = cell[9].getContents();
				if (awardTime == null || awardTime.equals("")) {
					return "第" + count + "行，获奖时间不能为空";
				}

				String awardStuName = cell[10].getContents();
				if (awardStuName == null || awardStuName.equals("")) {
					return "第" + count + "行，获奖学生姓名不能为空";
				}
				
				String awardStuNum = cell[11].getContents();
				if (awardStuNum == null || awardStuNum.equals("")) {
					return "第" + count + "行，获奖学生数不能为空，没有请添加0";
				}

				String guideTeaName = cell[12].getContents();
				if (guideTeaName == null || guideTeaName.equals("")) {
					return "第" + count + "行，指导教师不能为空，没有请添加0";
				}

				String guideTeaNum = cell[13].getContents();
				if (guideTeaNum == null || guideTeaNum.equals("")) {
					return "第" + count + "行，指导教师数不能为空，没有请添加0";
				}

				String note = cell[14].getContents();
				

				count++;

				T651_bean = new T651_Bean();

				T651_bean.setTeaUnit(teaUnit);
				T651_bean.setUnitId(unitId);
				T651_bean.setCompetiType(competiType);//本科生学科竞赛
				T651_bean.setCompetiName(competiName);
				T651_bean.setAwardItem(awardItem);
				T651_bean.setAwardLevel(awardLevel);//国家级
				T651_bean.setAwardGrade(awardGrade);
				T651_bean.setAwardFromUnit(awardFromUnit);
				T651_bean.setAwardTime(new SimpleDateFormat("yyyy-MM-dd").parse(awardTime));
				T651_bean.setAwardStuName(awardStuName);
				T651_bean.setAwardStuNum(Integer.parseInt(awardStuNum));
				T651_bean.setGuideTeaName(guideTeaName);
				T651_bean.setGuideTeaNum(Integer.parseInt(guideTeaNum));
				T651_bean.setNote(note);

				// 插入时间
				T651_bean.setTime(TimeUtil.changeDateY(selectYear));
				list.add(T651_bean);

			} catch (Exception e) {
				e.printStackTrace();
				return "上传文件不合法！！！";
			}
		}

		flag = false;
		T651_Service T651_services = new T651_Service();
		flag = T651_services.batchInsert(list);

		if (flag) {
			return null;
		} else {
			return "数据存储失败，请联系管理员";
		}
	}
}