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
import cn.nit.bean.table6.T623_Bean;
import cn.nit.constants.Constants;
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
import cn.nit.service.table6.T623_Service;
import cn.nit.util.TimeUtil;

public class T623_Excel {

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

		System.out.println("cellList:"+cellList.size());
		if ((cellList == null) || (cellList.size() < 2)) {
			return "数据不标准，请重新提交";
		}

		int count = 1;
		T623_Bean T623_bean = null;
		boolean flag = false;
		List<T623_Bean> list = new LinkedList<T623_Bean>();

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
				
				String artType = cell[2].getContents();
				if (artType == null || artType.equals("")) {
					return "第" + count + "行，类型不能为空";
				}
	
				
				String batch = cell[3].getContents();
				if (batch == null || batch.equals("")) {
					return "第" + count + "行，批次不能为空";
				}
	
				String libEnrollNum = cell[4].getContents();
				if (libEnrollNum == null || libEnrollNum.equals("")) {
					return "第" + count + "行，文科录取数不能为空";
				}
				if(!this.isNumeric(libEnrollNum)){
					return "第" + count + "行，文科录取数只能填写数字";
				}

				String sciEnrollNum = cell[5].getContents();
				if (sciEnrollNum == null || sciEnrollNum.equals("")) {
					return "第" + count + "行，理科录取数不能为空";
				}
				if(!this.isNumeric(sciEnrollNum)){
					return "第" + count + "行，理科录取数只能填写数字";
				}
				
				String sumEnrollNum = cell[6].getContents();
				if (sumEnrollNum == null || sumEnrollNum.equals("")) {
					return "第" + count + "行，综合录取数不能为空";
				}
				if(!this.isNumeric(sumEnrollNum)){
					return "第" + count + "行，综合录取数只能填写数字";
				}

				String libLowestScore = cell[7].getContents();
				if (libLowestScore == null || libLowestScore.equals("")) {
					return "第" + count + "行，文化批次最低控制线（分）不能为空";
				}
				if(!this.isNumeric(libLowestScore)){
					return "第" + count + "行，文化批次最低控制线（分）只能填写数字";
				}

				String sciLowestScore = cell[8].getContents();
				if (sciLowestScore == null || sciLowestScore.equals("")) {
					return "第" + count + "行，专业批次最低控制线（分）不能为空";
				}
				if(!this.isNumeric(sciLowestScore)){
					return "第" + count + "行，专业批次最低控制线（分）只能填写数字";
				}
				
				String sumLowestScore = cell[9].getContents();
				if (sumLowestScore == null || sumLowestScore.equals("")) {
					return "第" + count + "行，综合批次最低控制线（分）不能为空";
				}
				if(!this.isNumeric(sumLowestScore)){
					return "第" + count + "行，综合批次最低控制线（分）只能填写数字";
				}

				String libAvgScore = cell[10].getContents();
				if (libAvgScore == null || libAvgScore.equals("")) {
					return "第" + count + "行，文科当年录取平均分数（分）不能为空";
				}
				if(!this.isNumeric(libAvgScore)){
					return "第" + count + "行，文科当年录取平均分数（分）只能填写数字";
				}

				String sciAvgScore = cell[11].getContents();
				if (sciAvgScore == null || sciAvgScore.equals("")) {
					return "第" + count + "行，理科当年录取平均分数（分）不能为空";
				}
				if(!this.isNumeric(sciAvgScore)){
					return "第" + count + "行，文理科当年录取平均分数（分）只能填写数字";
				}
				
				String sumAvgScore = cell[12].getContents();
				if (sumAvgScore == null || sumAvgScore.equals("")) {
					return "第" + count + "行，综合当年录取平均分数（分）不能为空";
				}
				if(!this.isNumeric(sumAvgScore)){
					return "第" + count + "行，综合当年录取平均分数（分）只能填写数字";
				}
				
				String note = cell[13].getContents();
				count++;

				T623_bean = new T623_Bean();
				T623_bean.setProvince(province);
				T623_bean.setArtType(artType);
				T623_bean.setBatch(batch);
				
				if(!libEnrollNum.equals("——")){
					T623_bean.setLibEnrollNum(Integer.parseInt(libEnrollNum));
				}else{
					T623_bean.setLibEnrollNum(0);
				}
				
				if(!sciEnrollNum.equals("——")){
					T623_bean.setSciEnrollNum(Integer.parseInt(sciEnrollNum));
				}else{
					T623_bean.setSciEnrollNum(0);
				}
			
				if(!sumEnrollNum.equals("——")){
					T623_bean.setSumEnrollNum(Integer.parseInt(sumEnrollNum));
				}else{
					T623_bean.setSumEnrollNum(0);
				}
				
				if(!libLowestScore.equals("——")){
					T623_bean.setLibLowestScore(Integer.parseInt(libLowestScore));
				}else{
					T623_bean.setLibLowestScore(0);
				}
				
				if(!sciLowestScore.equals("——")){
					T623_bean.setSciLowestScore(Integer.parseInt(sciLowestScore));
				}else{
					T623_bean.setSciLowestScore(0);
				}
				
				if(!sumLowestScore.equals("——")){
					T623_bean.setSumLowestScore(Integer.parseInt(sumLowestScore));
				}else{
					T623_bean.setSumLowestScore(0);
				}
				
				if(!libAvgScore.equals("——")){
					T623_bean.setLibAvgScore(Integer.parseInt(libAvgScore));
				}else{
					T623_bean.setLibAvgScore(0);
				}
				
				if(!sciAvgScore.equals("——")){
					T623_bean.setSciAvgScore(Integer.parseInt(sciAvgScore));
				}else{
					T623_bean.setSciAvgScore(0);
				}
				
				if(!sumAvgScore.equals("——")){
					T623_bean.setSumAvgScore(Integer.parseInt(sumAvgScore));
				}else{
					T623_bean.setSumAvgScore(0);
				}

				T623_bean.setNote(note);
				T623_bean.setCheckState(Constants.WAIT_CHECK);
				// 插入时间
				T623_bean.setTime(TimeUtil.changeDateY(selectYear));
				list.add(T623_bean);

			} catch (Exception e) {
				e.printStackTrace();
				return "上传文件不合法！！！";
			}
		}

		flag = false;
		T623_Service T623_services = new T623_Service();
		flag = T623_services.batchInsert(list);

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