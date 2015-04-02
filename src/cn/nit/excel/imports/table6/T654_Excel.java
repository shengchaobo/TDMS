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
import cn.nit.bean.table6.T654_Bean;
import cn.nit.constants.Constants;
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
import cn.nit.service.table6.T654_Service;

import cn.nit.util.TimeUtil;

public class T654_Excel {

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
		T654_Bean T654_bean = null;
		boolean flag = false;
		List<T654_Bean> list = new LinkedList<T654_Bean>();
		
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
				
				String teaUnit = cell[1].getContents().trim();
				if (teaUnit == null || teaUnit.equals("")) {
					return "第" + count + "行，教学单位不能为空";
				}
				
				String unitId = cell[2].getContents().trim();
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

				String jonalName = cell[3].getContents().trim();

				if (jonalName == null || jonalName.equals("")) {
					return "第" + count + "行，专利名称不能为空";
				}
				
				String jonalId = cell[4].getContents().trim();
				if (jonalId == null || jonalId.equals("")) {
					return "第" + count + "行，专利号不能为空";
				}
				
				String patentType = cell[5].getContents().trim();
				if (patentType == null || patentType.equals("")) {
					return "第" + count + "行，类别不能为空";
				}
				
				String appvlTime = cell[6].getContents().trim();
				if (appvlTime == null || appvlTime.equals("")) {
					return "第" + count + "行，获批时间不能为空";
				}
				if(!this.judgeFormat1(appvlTime)){
					return "第" + count + "行，获批时间格式为：2013-02";
				}
				
				
				String awardStuName = cell[7].getContents().trim();
				if (awardStuName == null || awardStuName.equals("")) {
					return "第" + count + "行，学生姓名学号不能为空";
				}
				
				String awardStuNum = cell[8].getContents().trim();
				if (awardStuNum == null || awardStuNum.equals("")) {
					return "第" + count + "行，参与学生人数不能为空，没有请添加0";
				}

				String guideTeaName = cell[9].getContents().trim();
				if (guideTeaName == null || guideTeaName.equals("")) {
					return "第" + count + "行，指导教师不能为空，没有请添加0";
				}

				String guideTeaNum = cell[10].getContents().trim();
				if (guideTeaNum == null || guideTeaNum.equals("")) {
					return "第" + count + "行，指导教师数人数不能为空，没有请添加0";
				}

				String note = cell[11].getContents().trim();
				

				count++;

				T654_bean = new T654_Bean();

				T654_bean.setTeaUnit(teaUnit);
				T654_bean.setUnitId(unitId);
				T654_bean.setJonalName(jonalName);
				T654_bean.setPatentType(patentType);
				T654_bean.setJonalId(jonalId);
				T654_bean.setAppvlTime(TimeUtil.changeDateYM(appvlTime));
				T654_bean.setAwardStuName(awardStuName);
				T654_bean.setAwardStuNum(Integer.parseInt(awardStuNum));
				T654_bean.setGuideTeaName(guideTeaName);
				T654_bean.setGuideTeaNum(Integer.parseInt(guideTeaNum));
				T654_bean.setFillUnitID(userinfo.getUnitID());
				T654_bean.setCheckState(Constants.WAIT_CHECK);
				T654_bean.setNote(note);

				// 插入时间
				T654_bean.setTime(TimeUtil.changeDateY(selectYear));
				list.add(T654_bean);

			} catch (Exception e) {
				e.printStackTrace();
				return "上传文件不合法！！！";
			}
		}

		flag = false;
		T654_Service T654_services = new T654_Service();
		flag = T654_services.batchInsert(list);

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