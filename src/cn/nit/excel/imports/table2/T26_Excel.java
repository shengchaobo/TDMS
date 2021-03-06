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
import cn.nit.bean.UserinfoBean;
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
import cn.nit.bean.table2.T26_Bean;
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
import cn.nit.service.table2.T26_Service;
import cn.nit.util.TimeUtil;

public class T26_Excel {

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
		T26_Bean T26_bean = null ;
		boolean flag = false ;
		List<T26_Bean> list = new LinkedList<T26_Bean>() ;
		
		DiAwardLevelService diAwardLevel = new DiAwardLevelService();
		List<DiAwardLevelBean> diAwardLevelList = diAwardLevel.getList();
		
		DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;	
				
		for(Cell[] cell : cellList){
			try{
				if(count<=3){
					count++;
					continue;
				}
			
				String practiseBase = cell[1].getContents().trim();
				
				if(practiseBase == null || practiseBase.equals("")){
					return "第" + count + "行，校外实习、实训基地名称不能为空" ;
				}
								
				String unit = cell[2].getContents().trim() ;
				String unitId = cell[3].getContents().trim() ;
				
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
							
				String address = cell[4].getContents().trim() ;
				String forMajor = cell[5].getContents().trim() ;
								
				String stuNumEachTime = cell[6].getContents().trim();
				String stuNumEachYear = cell[7].getContents().trim() ;
				
				String awardLevel = cell[8].getContents().trim() ;
				
				if(awardLevel == null || awardLevel.equals("")){
					return "第" + count + "行，签约级别不能为空" ;
				}
				String awardLevelID = null;
				for(DiAwardLevelBean awardLevelBean : diAwardLevelList){
					if(awardLevelBean.getAwardLevel().equals(awardLevel)){
						awardLevelID = awardLevelBean.getIndexId() ;
						flag = true  ;
						break ;
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，签约级别不存在" ;
				}else{
					flag = false ;
				}
				String baseLevel = cell[9].getContents().trim() ;
								
				count++ ;
				
				
				T26_bean = new T26_Bean() ;
				T26_bean.setPractiseBase(practiseBase);
				T26_bean.setTeaUnit(unit);
				T26_bean.setTeaUnitID(unitId);
				T26_bean.setAddress(address);
				T26_bean.setForMajor(forMajor);
				T26_bean.setStuNumEachTime(Integer.parseInt(stuNumEachTime));
				T26_bean.setStuNumEachYear(Integer.parseInt(stuNumEachYear));
				T26_bean.setSignLevel(awardLevelID);
				T26_bean.setBaseLevel(baseLevel);
				//插入教学单位
				UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
				String fillUnitID = bean.getUnitID();
				T26_bean.setFillUnitID(fillUnitID);
				//插入时间
				T26_bean.setTime(TimeUtil.changeDateY(selectYear));
				//插入审核状态
				T26_bean.setCheckState(Constants.WAIT_CHECK);
				
				list.add(T26_bean);
								
			}catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
		}
		
		flag = false ;
		T26_Service T26_services = new T26_Service() ;
		flag = T26_services.batchInsert(list) ;
		
		if(flag){
			return null ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
	}
}