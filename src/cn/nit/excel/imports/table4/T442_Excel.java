package cn.nit.excel.imports.table4;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import jxl.Cell;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.di.DiMajorTwoBean;
import cn.nit.bean.di.DiTutorTypeBean;
import cn.nit.bean.table4.T442_Bean;
import cn.nit.constants.Constants;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.di.DiMajorTwoService;
import cn.nit.service.di.DiTutorTypeService;
import cn.nit.service.table4.T442_Service;
import cn.nit.util.TimeUtil;

public class T442_Excel {

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
		T442_Bean T442_bean = null ;
		boolean flag = false ;
		List<T442_Bean> list = new LinkedList<T442_Bean>() ;
		
		DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		
		DiMajorTwoService diMajor = new DiMajorTwoService() ;
		List<DiMajorTwoBean> diMajorBeanList = diMajor.getList() ;
		
		DiTutorTypeService diTutor = new DiTutorTypeService();
		List<DiTutorTypeBean> diTutorList = diTutor.getList();
							
		for(Cell[] cell : cellList){
			try{
				if(count<=3){
					count++;
					continue;
				}
				
				String name = cell[1].getContents().trim() ;
				String teaId = cell[2].getContents().trim() ;
				
				if(name == null || name.equals("")){
					return "第" + count + "行，导师名称不能为空" ;
				}
				
				if((teaId == null) || teaId.equals("")){
					return "第" + count + "行，教工号不能为空" ;
				}
				
				String tutorType = cell[3].getContents().trim();			
				if(tutorType == null || tutorType.equals("")){
					return "第" + count + "行，导师类别不能为空" ;
				}
				String tutorID = null;
				for(DiTutorTypeBean tutorBean : diTutorList){
					if(tutorBean.getTutorType().equals(tutorType)){
						tutorID = tutorBean.getIndexId() ;
						flag = true  ;
						break ;
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，导师类别不存在" ;
				}else{
					flag = false ;
				}
				
				String subjectClass = cell[4].getContents().trim();
				if(subjectClass == null || subjectClass.equals("")){
					return "第" + count + "行，学科门类不能为空" ;
				}
				
				String MajorName = cell[5].getContents().trim() ;
				String MajorId = cell[6].getContents().trim() ;
				
				if(MajorName == null || MajorName.equals("")){
					return "第" + count + "行，专业名称不能为空" ;
				}
				
				if(MajorId == null || MajorId.equals("")){
					return "第" + count + "行，专业代码不能为空" ;
				}			
				
				for(DiMajorTwoBean diMajorBean : diMajorBeanList){
					if(diMajorBean.getMajorNum().equals(MajorId)){
						if(diMajorBean.getMajorName().equals(MajorName)){
							flag = true ;
							break ;
						}else{
							return "第" + count + "行，专业名称与专业代码不对应" ;
						}
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，没有与之相匹配的专业代码" ;
				}else{
					flag = false ;
				}
								
				String resField = cell[7].getContents().trim() ;
				
				String unit = cell[8].getContents().trim();
				String unitId = cell[9].getContents().trim();
				
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
								
				count++ ;
				
				
				T442_bean = new T442_Bean() ;
				T442_bean.setTutorName(name);
				T442_bean.setTeaId(teaId);
				T442_bean.setTutorType(tutorID);
				T442_bean.setSubjectClass(subjectClass);
				T442_bean.setMajorName(MajorName);
				T442_bean.setMajorId(MajorId);
				T442_bean.setResField(resField);
				T442_bean.setFromUnit(unit);
				T442_bean.setUnitId(unitId);
				//插入时间
				T442_bean.setTime(TimeUtil.changeDateY(selectYear));
				//插入审核状态
				T442_bean.setCheckState(Constants.WAIT_CHECK);
				list.add(T442_bean);
								
			}catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
		}
		
		flag = false ;
		T442_Service T442_services = new T442_Service() ;
		flag = T442_services.batchInsert(list) ;
		
		if(flag){
			return null ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
	}
}