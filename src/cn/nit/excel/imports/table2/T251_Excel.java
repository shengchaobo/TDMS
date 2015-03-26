package cn.nit.excel.imports.table2;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.SystemUtils;
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
import cn.nit.bean.di.DiCourseCategoriesBean;
import cn.nit.bean.di.DiCourseCharBean;
import cn.nit.bean.di.DiDegreeBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.di.DiEducationBean;
import cn.nit.bean.di.DiIdentiTypeBean;
import cn.nit.bean.di.DiResearchRoomBean;
import cn.nit.bean.di.DiSourceBean;
import cn.nit.bean.di.DiTitleLevelBean;
import cn.nit.bean.di.DiTitleNameBean;
import cn.nit.bean.table2.T251_Bean;
import cn.nit.bean.table4.T42_Bean;
import cn.nit.constants.Constants;
import cn.nit.service.di.DiAwardLevelService;
import cn.nit.service.di.DiCourseCategoriesService;
import cn.nit.service.di.DiCourseCharService;
import cn.nit.service.di.DiDegreeService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.di.DiEducationService;
import cn.nit.service.di.DiIdentiTypeService;
import cn.nit.service.di.DiResearchRoomService;
import cn.nit.service.di.DiSourceService;
import cn.nit.service.di.DiTitleLevelService;
import cn.nit.service.di.DiTitleNameService;
import cn.nit.service.table2.T251_Service;
import cn.nit.service.table4.T42_Service;
import cn.nit.util.TimeUtil;

public class T251_Excel {

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
		T251_Bean T251_bean = null ;
		boolean flag = false ;
		List<T251_Bean> list = new LinkedList<T251_Bean>() ;
		
		
		DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;	
				
		for(Cell[] cell : cellList){
			try{
				if(count<=3){
					count++;
					continue;
				}
				
				String expCenterName = cell[1].getContents() ;
				
				if(expCenterName == null || expCenterName.equals("")){
					return "第" + count + "行，实验中心名称不能为空" ;
				}
								
				String unit = cell[2].getContents() ;
				String unitId = cell[3].getContents() ;
				
				if(unit == null || unit.equals("")){
					return "第" + count + "行，所属单位不能为空" ;
				}
				
				if(unitId == null || unitId.equals("")){
					return "第" + count + "行，所属单位号不能为空" ;
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
							
				String labName = cell[4].getContents() ;
				String buildTime = cell[5].getContents() ;
				
				if((buildTime == null) || buildTime.equals("")){
					return "第" + count + "行，创建时间不能为空" ;
				}else{
					if(!TimeUtil.judgeFormatYM(buildTime)){
						return "第" + count + "行，创建时间格式不正确" ;
					}
				}
				
				String place = cell[6].getContents() ;
				String machNum = cell[7].getContents() ;
				String money = cell[8].getContents() ;
				String area = cell[9].getContents() ;
				String newAddArea = cell[10].getContents() ;
				String nature = cell[11].getContents() ;
				String forMajor = cell[12].getContents() ;
								
				count++ ;
								
				T251_bean = new T251_Bean() ;
				T251_bean.setExpCenterName(expCenterName);
				T251_bean.setTeaUnit(unit);
				T251_bean.setTeaUnitID(unitId);
				T251_bean.setLabName(labName);
				T251_bean.setBuildTimeYM(TimeUtil.changeDateYM(buildTime));
//				T251_bean.setBuildTimeYM(TimeUtil.changeDateYM(buildTime));

				
				T251_bean.setPlace(place);
				
				if((machNum == null) || machNum.equals("")){
					T251_bean.setMachNum(0);
				}else{
					T251_bean.setMachNum(Integer.parseInt(machNum));
				}

				if((money == null) || money.equals("")){
					T251_bean.setMoney(0.0);
				}else{
					T251_bean.setMoney(Double.parseDouble(money));
				}
				
				
				if((area == null) || area.equals("")){
					T251_bean.setArea(0.0);
				}else{
					T251_bean.setArea(Double.parseDouble(area));
				}
				
				
				if((newAddArea == null) || newAddArea.equals("")){
					T251_bean.setNewAddArea(0.0);
				}else{
					T251_bean.setNewAddArea(Double.parseDouble(newAddArea));
				}
				
				T251_bean.setNature(nature);
				T251_bean.setForMajor(forMajor);
				//插入时间
				T251_bean.setTime(TimeUtil.changeDateY(selectYear));
				//插入审核状态
				T251_bean.setCheckState(Constants.WAIT_CHECK);
				list.add(T251_bean);
								
			}catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
		}
		
		flag = false ;
		T251_Service T251_services = new T251_Service() ;
		flag = T251_services.batchInsert(list) ;
		
		if(flag){
			return null ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
	}
}