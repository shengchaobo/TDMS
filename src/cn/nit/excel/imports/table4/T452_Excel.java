package cn.nit.excel.imports.table4;

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
import cn.nit.bean.UserinfoBean;
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
import cn.nit.bean.table4.T412_Bean;
import cn.nit.bean.table4.T42_Bean;
import cn.nit.bean.table4.T452_Bean;
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
import cn.nit.service.table4.T412_Service;
import cn.nit.service.table4.T42_Service;
import cn.nit.service.table4.T452_Service;
import cn.nit.util.TimeUtil;

public class T452_Excel {

	/**
	 * 批量导入
	 * @param cellList {@link java.util.List<{@link jxl.Cell}>}  Cell是数组
	 * @param request  {@link javax.servlet.http.HttpServletRequest}
	 * @return
	 */
	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request, String selectYear){
		
		if((cellList == null) || (cellList.size() < 2)){
			return "数据不标准，请重新提交" ;
		}
		
		int count = 1 ;
		T452_Bean T452_bean = null ;
		boolean flag = false ;
		List<T452_Bean> list = new LinkedList<T452_Bean>() ;
		
		DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		
				
		for(Cell[] cell : cellList){
			try{
				if(count<=3){
					count++;
					continue;
				}
				
				
				String unit = cell[1].getContents().trim() ;
				String unitId = cell[2].getContents().trim() ;
				
				if(unit == null || unit.equals("")){
					return "第" + count + "行，教学单位不能为空" ;
				}
				
				if(unitId == null || unitId.equals("")){
					return "第" + count + "行，单位号不能为空" ;
				}			
				
				for(DiDepartmentBean diDepartBean : diDepartBeanList){
					if(diDepartBean.getUnitId().equals(unitId)){
						if(diDepartBean.getUnitName().equals(unit)){
							flag = true ;
							break ;
						}else{
							return "第" + count + "行，所属单位与所属单位号不对应" ;
						}
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，没有与之相匹配的单位号" ;
				}else{
					flag = false ;
				}
				
				String name = cell[3].getContents().trim() ;
				String teaId = cell[4].getContents().trim() ;
				
				if(name == null || name.equals("")){
					return "第" + count + "行，名称不能为空" ;
				}
				
				if((teaId == null) || teaId.equals("")){
					return "第" + count + "行，教工号不能为空" ;
				}
				
				String trainType = cell[5].getContents().trim() ;
				String beginTime = cell[6].getContents().trim() ;
				String endTime = cell[7].getContents().trim() ;
				
				if((beginTime == null) || beginTime.equals("")){
					return "第" + count + "行，开始时间不能为空" ;
				}else{
					if(!TimeUtil.judgeFormatYM(beginTime)){
						return "第" + count + "行，开始时间格式不正确" ;
					}
				}
				
				if((endTime == null) || endTime.equals("")){
					return "第" + count + "行，结束时间不能为空" ;
				}else{
					if(!TimeUtil.judgeFormatYM(endTime)){
						return "第" + count + "行，结束时间格式不正确" ;
					}
				}
				
				String inOrOut = cell[8].getContents().trim() ;
				String trainUnit = cell[9].getContents().trim() ;
				String trainMajor = cell[10].getContents().trim() ;
				String note = cell[11].getContents().trim() ;
				
				count++ ;
				
				
				T452_bean = new T452_Bean() ;
				T452_bean.setTeaUnitName(unit);
				T452_bean.setUnitId(unitId);
				T452_bean.setName(name);
				T452_bean.setTeaId(teaId);
				T452_bean.setTrainType(trainType);
				T452_bean.setBeginTime(TimeUtil.changeDateYM(beginTime));
				T452_bean.setEndTime(TimeUtil.changeDateYM(endTime));
				T452_bean.setInOrOut(inOrOut);
				T452_bean.setTrainUnit(trainUnit);
				T452_bean.setTrainMajor(trainMajor);
				//插入审核状态
				T452_bean.setCheckState(Constants.WAIT_CHECK);
				T452_bean.setNote(note);
				
				//插入教学单位
				UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
				String fillUnitID = bean.getUnitID();
				T452_bean.setFillUnitID(fillUnitID);
				//插入时间
				T452_bean.setTime(TimeUtil.changeDateY(selectYear));
				list.add(T452_bean);
								
			}catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
		}
		
		flag = false ;
		T452_Service T452_services = new T452_Service() ;
		flag = T452_services.batchInsert(list) ;
		
		if(flag){
			return null ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
	}
}