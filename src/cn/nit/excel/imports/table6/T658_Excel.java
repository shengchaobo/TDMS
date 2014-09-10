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
import cn.nit.bean.UserinfoBean;
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
import cn.nit.bean.table6.T658_Bean;
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
import cn.nit.service.table6.T658_Service;

import cn.nit.util.TimeUtil;

public class T658_Excel {

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
		T658_Bean T658_bean = null;
		boolean flag = false;
		List<T658_Bean> list = new LinkedList<T658_Bean>();
		
		UserinfoBean userinfo = (UserinfoBean)request.getSession().getAttribute("userinfo") ;

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
		

				String conferenceName = cell[3].getContents();

				if (conferenceName == null || conferenceName.equals("")) {
					return "第" + count + "行，会议名称不能为空";
				}

				String paperTitle = cell[4].getContents();
				if (paperTitle == null || paperTitle.equals("")) {
					return "第" + count + "行，发表论文题目不能为空";
				}
				
				String holdTime = cell[5].getContents();
				if (holdTime == null || holdTime.equals("")) {
					return "第" + count + "行，举办时间不能为空";
				}
				if(!this.judgeFormat1(holdTime)){
					return "第" + count + "行，举办时间格式为：2013-02";
				}
				
				String holdPlace = cell[6].getContents();
				if (holdPlace == null || holdPlace.equals("")) {
					return "第" + count + "行，举办地点不能为空";
				}
				
				String holdUnit = cell[7].getContents();
				if (holdUnit == null || holdUnit.equals("")) {
					return "第" + count + "行，举办单位不能为空";
				}
				
				
				String conferenceLevel = cell[8].getContents();
				
				String levelID = null;

				
				for (DiAwardLevelBean diAwardBean : diAwardList) {
					if (diAwardBean.getAwardLevel().equals(conferenceLevel)) {
						levelID = diAwardBean.getIndexId();
						conferenceLevel = levelID;
					}// if
				}// for
				
				if (conferenceLevel == null || conferenceLevel.equals("")) {
					return "第" + count + "行，会议级别不能为空或者级别ID与级别不匹配";
				}
				
				String awardStuName = cell[9].getContents();
				if (awardStuName == null || awardStuName.equals("")) {
					return "第" + count + "行，学生姓名学号不能为空";
				}
				
				String awardStuNum = cell[10].getContents();
				if (awardStuNum == null || awardStuNum.equals("")) {
					return "第" + count + "行，获奖学生数不能为空，没有请添加0";
				}
				
				String guideTeaName = cell[11].getContents();
				if (guideTeaName == null || guideTeaName.equals("")) {
					return "第" + count + "行，指导教师不能为空，没有请添加0";
				}

				String guideTeaNum = cell[12].getContents();
				if (guideTeaNum == null || guideTeaNum.equals("")) {
					return "第" + count + "行，指导教师数不能为空，没有请添加0";
				}

				String note = cell[13].getContents();
				

				count++;

				T658_bean = new T658_Bean();

				T658_bean.setTeaUnit(teaUnit);
				T658_bean.setUnitId(unitId);
				T658_bean.setConferenceName(conferenceName);
				T658_bean.setPaperTitle(paperTitle);
				T658_bean.setHoldTime(TimeUtil.changeDateYM(holdTime));
				T658_bean.setHoldPlace(holdPlace);
				T658_bean.setHoldUnit(holdUnit);
				T658_bean.setConferenceLevel(conferenceLevel);

				T658_bean.setAwardStuName(awardStuName);
				T658_bean.setAwardStuNum(Integer.parseInt(awardStuNum));
				T658_bean.setGuideTeaName(guideTeaName);
				T658_bean.setGuideTeaNum(Integer.parseInt(guideTeaNum));
				T658_bean.setNote(note);
				T658_bean.setFillUnitID(userinfo.getUnitID());

				// 插入时间
				T658_bean.setTime(TimeUtil.changeDateY(selectYear));
				list.add(T658_bean);

			} catch (Exception e) {
				e.printStackTrace();
				return "上传文件不合法！！！";
			}
		}

		flag = false;
		T658_Service T658_services = new T658_Service();
		flag = T658_services.batchInsert(list);

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