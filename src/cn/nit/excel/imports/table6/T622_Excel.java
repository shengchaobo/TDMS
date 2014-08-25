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
import cn.nit.bean.table6.T622_Bean;
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
import cn.nit.service.table6.T622_Service;
import cn.nit.util.TimeUtil;

public class T622_Excel {

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
		T622_Bean T622_bean = null;
		boolean flag = false;
		List<T622_Bean> list = new LinkedList<T622_Bean>();

		for (Cell[] cell : cellList) {
			try {
				if (count <= 4) {
					count++;
					continue;
				}
				
				String province = cell[1].getContents();
				if (province == null || province.equals("")) {
					return "第" + count + "行，省份不能为空";
				}
				
				String batch = cell[2].getContents();
				if (batch == null || batch.equals("")) {
					return "第" + count + "行，批次不能为空";
				}

				String libEnrollNum = cell[3].getContents();
				if (libEnrollNum == null || libEnrollNum.equals("")) {
					return "第" + count + "行，文科录取数不能为空";
				}
				if(!this.isNumeric(libEnrollNum)){
					return "第" + count + "行，文科录取数只能填数字";
				}

				String sciEnrollNum = cell[4].getContents();
				if (sciEnrollNum == null || sciEnrollNum.equals("")) {
					return "第" + count + "行，理科录取数不能为空";
				}
				if(!this.isNumeric(sciEnrollNum)){
					return "第" + count + "行，理科录取数只能填数字";
				}

				String libLowestScore = cell[5].getContents();
				if (libLowestScore == null || libLowestScore.equals("")) {
					return "第" + count + "行，文科批次最低控制线（分）不能为空";
				}
				if(!this.isNumeric(libLowestScore)){
					return "第" + count + "行，文科批次最低控制线（分）只能填数字";
				}

				String sciLowestScore = cell[6].getContents();
				if (sciLowestScore == null || sciLowestScore.equals("")) {
					return "第" + count + "行，理科批次最低控制线（分）不能为空";
				}
				if(!this.isNumeric(sciLowestScore)){
					return "第" + count + "行，理科批次最低控制线（分）只能填数字";
				}
				
				

				String libAvgScore = cell[7].getContents();
				if (libAvgScore == null || libAvgScore.equals("")) {
					return "第" + count + "行，文科当年录取平均分数（分）不能为空";
				}
				if(!this.isNumeric(libAvgScore)){
					return "第" + count + "行，文科当年录取平均分数（分）只能填数字";
				}

				String sciAvgScore = cell[8].getContents();
				if (sciAvgScore == null || sciAvgScore.equals("")) {
					return "第" + count + "行，理科当年录取平均分数（分）不能为空";
				}
				if(!this.isNumeric(sciAvgScore)){
					return "第" + count + "行，理科当年录取平均分数（分）只能填数字";
				}
				
				String note = cell[9].getContents();
		


				count++;

				T622_bean = new T622_Bean();
				T622_bean.setProvince(province);
				T622_bean.setBatch(batch);
				T622_bean.setLibEnrollNum(Integer.parseInt(libEnrollNum));
				T622_bean.setSciEnrollNum(Integer.parseInt(sciEnrollNum));
				T622_bean.setLibLowestScore(Integer.parseInt(libLowestScore));
				T622_bean.setSciLowestScore(Integer.parseInt(sciLowestScore));
				T622_bean.setLibAvgScore(Integer.parseInt(libAvgScore));
				T622_bean.setSciAvgScore(Integer.parseInt(sciAvgScore));
				T622_bean.setNote(note);
				// 插入时间
				T622_bean.setTime(TimeUtil.changeDateY(selectYear));
				list.add(T622_bean);

			} catch (Exception e) {
				e.printStackTrace();
				return "上传文件不合法！！！";
			}
		}

		flag = false;
		T622_Service T622_services = new T622_Service();
		flag = T622_services.batchInsert(list);

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