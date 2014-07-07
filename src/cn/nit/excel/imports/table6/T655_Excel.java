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
import cn.nit.bean.table6.T655_Bean;
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
import cn.nit.service.table6.T655_Service;

import cn.nit.util.TimeUtil;

public class T655_Excel {

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
		T655_Bean T655_bean = null;
		boolean flag = false;
		List<T655_Bean> list = new LinkedList<T655_Bean>();

		DiDepartmentService diDep = new DiDepartmentService();
		List<DiDepartmentBean> diDepList = diDep.getList();
		
		DiAwardLevelService diAward = new DiAwardLevelService();
		List<DiAwardLevelBean> diAwardList = diAward.getList();
		
		DiContestLevelService diAwardType = new DiContestLevelService();
		List<DiContestLevelBean> diAwardTypeList = diAwardType.getList();

		for (Cell[] cell : cellList) {
			try {
				if (count <= 4) {
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

				String CET4PassRate = cell[3].getContents();

				if (CET4PassRate == null || CET4PassRate.equals("")) {
					return "第" + count + "行，英语四级考试累计通过率不能为空";
				}
				
				String CET6PassRate = cell[4].getContents();
				if (CET6PassRate == null || CET6PassRate.equals("")) {
					return "第" + count + "行，英语六级考试累计通过率不能为空";
				}
				
				String jiangxiNCREPassRate = cell[5].getContents();
				if (jiangxiNCREPassRate == null || jiangxiNCREPassRate.equals("")) {
					return "第" + count + "行，江西省高校计算机等级考试累计通过率不能为空";
				}
				
				String nationNCREPassRate = cell[6].getContents();
				if (nationNCREPassRate == null || nationNCREPassRate.equals("")) {
					return "第" + count + "行，全国高校计算机等级考试累计通过率不能为空";
				}

				count++;

				T655_bean = new T655_Bean();

				T655_bean.setTeaUnit(teaUnit);
				T655_bean.setUnitId(unitId);
				if(CET4PassRate.contains("%")){
					CET4PassRate = CET4PassRate.substring(0,CET4PassRate.toString().length()-1);
				}
				T655_bean.setCET4PassRate(Double.parseDouble(CET4PassRate));
				
				if(CET6PassRate.contains("%")){
					CET6PassRate = CET6PassRate.substring(0,CET6PassRate.toString().length()-1);
				}
				T655_bean.setCET6PassRate(Double.parseDouble(CET6PassRate));
				
				if(jiangxiNCREPassRate.contains("%")){
					jiangxiNCREPassRate = jiangxiNCREPassRate.substring(0,jiangxiNCREPassRate.toString().length()-1);
				}
				T655_bean.setJiangxiNCREPassRate(Double.parseDouble(jiangxiNCREPassRate));
				
				if(nationNCREPassRate.contains("%")){
					nationNCREPassRate = nationNCREPassRate.substring(0,nationNCREPassRate.toString().length()-1);
				}
				T655_bean.setNationNCREPassRate(Double.parseDouble(nationNCREPassRate));

				// 插入时间
				T655_bean.setTime(TimeUtil.changeDateY(selectYear));
				list.add(T655_bean);

			} catch (Exception e) {
				e.printStackTrace();
				return "上传文件不合法！！！";
			}
		}

		flag = false;
		T655_Service T655_services = new T655_Service();
		flag = T655_services.batchInsert(list);

		if (flag) {
			return null;
		} else {
			return "数据存储失败，请联系管理员";
		}
	}
}