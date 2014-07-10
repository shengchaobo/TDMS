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
import cn.nit.bean.table4.T453_Bean;
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
import cn.nit.service.table4.T453_Service;
import cn.nit.util.TimeUtil;

public class T453_Excel {

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
		T453_Bean T453_bean = null ;
		boolean flag = false ;
		List<T453_Bean> list = new LinkedList<T453_Bean>() ;
		
		DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		
				
		for(Cell[] cell : cellList){
			try{
				if(count<4){
					count++;
					continue;
				}
				
				
				String unit = cell[1].getContents() ;
				String unitId = cell[2].getContents() ;
				
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
				
				String name = cell[3].getContents() ;
				String teaId = cell[4].getContents() ;
				
				if(name == null || name.equals("")){
					return "第" + count + "行，名称不能为空" ;
				}
				
				if((teaId == null) || teaId.equals("")){
					return "第" + count + "行，教工号不能为空" ;
				}
				
				String communType = cell[5].getContents() ;
				String beginTime = cell[6].getContents() ;
				String endTime = cell[7].getContents() ;
				
				if((beginTime == null) || beginTime.equals("")){
					return "第" + count + "行，开始时间不能为空" ;
				}
				
				if((endTime == null) || endTime.equals("")){
					return "第" + count + "行，结束时间不能为空" ;
				}
				
				String inOrOut = cell[8].getContents() ;
				String communUnit = cell[9].getContents() ;
				String communContent = cell[10].getContents() ;
				String note = cell[11].getContents() ;
				
				count++ ;
				
				String fillUnitID = null; //从user的session中获得，现在没有
				T453_bean = new T453_Bean() ;
				T453_bean.setTeaUnitName(unit);
				T453_bean.setUnitId(unitId);
				T453_bean.setName(name);
				T453_bean.setTeaId(teaId);
				T453_bean.setCommunType(communType);
				T453_bean.setBeginTime(TimeUtil.changeDateYM(beginTime));
				T453_bean.setEndTime(TimeUtil.changeDateYM(endTime));
				T453_bean.setInOrOut(inOrOut);
				T453_bean.setCommunUnit(communUnit);
				T453_bean.setCommunContent(communContent);
				T453_bean.setNote(note);
				T453_bean.setFillUnitID(fillUnitID);
				//插入时间
				T453_bean.setTime(TimeUtil.changeDateY(selectYear));
				list.add(T453_bean);
								
			}catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
		}
		
		flag = false ;
		T453_Service T453_services = new T453_Service() ;
		flag = T453_services.batchInsert(list) ;
		
		if(flag){
			return null ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
	}
}