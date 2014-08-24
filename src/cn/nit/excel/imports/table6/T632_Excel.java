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
import cn.nit.bean.table6.T632_Bean;
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
import cn.nit.service.table6.T632_Service;

import cn.nit.util.TimeUtil;

public class T632_Excel {

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
		T632_Bean T632_bean = null;
		boolean flag = false;
		List<T632_Bean> list = new LinkedList<T632_Bean>();

		DiDepartmentService diDep = new DiDepartmentService();
		List<DiDepartmentBean> diDepList = diDep.getList();

		DiMajorTwoService diMaj2 = new DiMajorTwoService();
		List<DiMajorTwoBean> diMaj2List = diMaj2.getList();

		for (Cell[] cell : cellList) {
			try {
				if (count <= 6) {
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
				
				String sumEmployNum = cell[5].getContents();
				if (sumEmployNum == null || sumEmployNum.equals("")) {
					return "第" + count + "行，应届就业总人数不能为空";
				}
				if(!this.isNumeric(sumEmployNum)){
					return "第" + count + "行，应届就业总人数只能填数字";
				}

				String govermentNum = cell[6].getContents();
				if (govermentNum == null || govermentNum.equals("")) {
					return "第" + count + "行，政府机构就业人数不能为空，没有请添加0";
				}
				if(!this.isNumeric(govermentNum)){
					return "第" + count + "行，政府机构就业人数只能填数字";
				}

				String pubInstiNum = cell[7].getContents();
				if (pubInstiNum == null || pubInstiNum.equals("")) {
					return "第" + count + "行，事业单位就业人数不能为空，没有请添加0";
				}
				if(!this.isNumeric(pubInstiNum)){
					return "第" + count + "行，事业单位就业人数只能填数字";
				}
				
				String enterpriseNum = cell[8].getContents();
				if (enterpriseNum == null || enterpriseNum.equals("")) {
					return "第" + count + "行，企业就业总人数不能为空，没有请添加0";
				}
				if(!this.isNumeric(enterpriseNum)){
					return "第" + count + "行，企业就业总人数只能填数字";
				}

				String forceNum = cell[9].getContents();
				if (forceNum == null || forceNum.equals("")) {
					return "第" + count + "行，部队人数不能为空，没有请添加0";
				}
				if(!this.isNumeric(forceNum)){
					return "第" + count + "行，部队人数只能填数字";
				}

				String flexibleEmploy = cell[10].getContents();
				if (flexibleEmploy == null || flexibleEmploy.equals("")) {
					return "第" + count + "行，灵活就业人数不能为空，没有请添加0";
				}
				if(!this.isNumeric(flexibleEmploy)){
					return "第" + count + "行，灵活就业人数只能填数字";
				}
				
				
				String goOnHighStudy = cell[11].getContents();
				if (goOnHighStudy == null || goOnHighStudy.equals("")) {
					return "第" + count + "行，升学人数不能为空，没有请添加0";
				}
				if(!this.isNumeric(goOnHighStudy)){
					return "第" + count + "行，升学人数只能填数字";
				}

				String nationItemEmploy = cell[12].getContents();
				if (nationItemEmploy == null || nationItemEmploy.equals("")) {
					return "第" + count + "行，参加国家地方项目就业人数不能为空，没有请添加0";
				}
				if(!this.isNumeric(nationItemEmploy)){
					return "第" + count + "行，参加国家地方项目就业人数只能填数字";
				}

				String otherEmploy = cell[13].getContents();
				if (otherEmploy == null || otherEmploy.equals("")) {
					return "第" + count + "行，其他人数不能为空，没有请添加0";
				}
				if(!this.isNumeric(otherEmploy)){
					return "第" + count + "行，其他人数只能填数字";
				}

				String sumGoOnHighStudyNum = cell[14].getContents();
				if (sumGoOnHighStudyNum == null || sumGoOnHighStudyNum.equals("")) {
					return "第" + count + "行，应届升学总人数不能为空，没有请添加0";
				}
				if(!this.isNumeric(sumGoOnHighStudyNum)){
					return "第" + count + "行，届升学总人数只能填数字";
				}

				String recommendGraNum = cell[15].getContents();
				if (recommendGraNum == null || recommendGraNum.equals("")) {
					return "第" + count + "行，免试推荐研究生人数不能为空，没有请添加0";
				}
				if(!this.isNumeric(recommendGraNum)){
					return "第" + count + "行，免试推荐研究生人数只能填数字";
				}

				String examGraApplyNum = cell[16].getContents();
				if (examGraApplyNum == null || examGraApplyNum.equals("")) {
					return "第" + count + "行，考研报名人数不能为空，没有请添加0";
				}
				if(!this.isNumeric(examGraApplyNum)){
					return "第" + count + "行，考研报名人数只能填数字";
				}

				String examGraEnrollNum = cell[17].getContents();
				if (examGraEnrollNum == null || examGraEnrollNum.equals("")) {
					return "第" + count + "行，考研录取总人数不能为空，没有请添加0";
				}
				if(!this.isNumeric(examGraEnrollNum)){
					return "第" + count + "行，考研录取总人数只能填数字";
				}

				String examGraInSch = cell[18].getContents();
				if (examGraInSch == null || examGraInSch.equals("")) {
					return "第" + count + "行，考取本校人数不能为空，没有请添加0";
				}
				if(!this.isNumeric(examGraInSch)){
					return "第" + count + "行，考取本校人数只能填数字";
				}

				String examGraOutSch = cell[19].getContents();
				if (examGraOutSch == null || examGraOutSch.equals("")) {
					return "第" + count + "行，考取外校人数不能为空，没有请添加0";
				}
				if(!this.isNumeric(examGraOutSch)){
					return "第" + count + "行，考取外校人数只能填数字";
				}

				String abroadNum = cell[20].getContents();
				if (abroadNum == null || abroadNum.equals("")) {
					return "第" + count + "行，出国（境）留学人数不能为空，没有请添加0";
				}
				if(!this.isNumeric(abroadNum)){
					return "第" + count + "行，出国（境）留学人数只能填数字";
				}
				
				count++;

				T632_bean = new T632_Bean();
							
				T632_bean.setTeaUnit(fromTeaUnit);
				T632_bean.setUnitId(unitId);
				T632_bean.setMajorName(majorName);
				T632_bean.setMajorId(majorId);

				T632_bean.setGovermentNum(Integer.parseInt(govermentNum));
				T632_bean.setPubInstiNum(Integer.parseInt(pubInstiNum));
				T632_bean.setEnterpriseNum(Integer.parseInt(enterpriseNum));
				T632_bean.setForceNum(Integer.parseInt(forceNum));
				T632_bean.setFlexibleEmploy(Integer.parseInt(flexibleEmploy));
				
				T632_bean.setNationItemEmploy(Integer.parseInt(nationItemEmploy));
				T632_bean.setOtherEmploy(Integer.parseInt(otherEmploy));
				T632_bean.setRecommendGraNum(Integer.parseInt(recommendGraNum));
				T632_bean.setExamGraApplyNum(Integer.parseInt(examGraApplyNum));
				T632_bean.setExamGraInSch(Integer.parseInt(examGraInSch));
				T632_bean.setExamGraOutSch(Integer.parseInt(examGraOutSch));
				T632_bean.setAbroadNum(Integer.parseInt(abroadNum));

				T632_bean.setSumGoOnHighStudyNum(Integer.parseInt(sumGoOnHighStudyNum));//统计生成-应届升学总人数
				T632_bean.setExamGraEnrollNum(Integer.parseInt(examGraEnrollNum));//统计生成-考研录取总人数
				T632_bean.setGoOnHighStudy(Integer.parseInt(goOnHighStudy));//引用生成--升学
				
				T632_bean.setSumEmployNum(Integer.parseInt(sumEmployNum));//统计生成-应届就业总人数

				// 插入时间
				T632_bean.setTime(TimeUtil.changeDateY(selectYear));
				list.add(T632_bean);

			} catch (Exception e) {
				e.printStackTrace();
				return "上传文件不合法！！！";
			}
		}

		flag = false;
		T632_Service T632_services = new T632_Service();
		flag = T632_services.batchInsert(list);

		if (flag) {
			return "数据存储成功！";
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