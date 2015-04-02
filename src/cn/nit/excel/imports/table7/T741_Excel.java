package cn.nit.excel.imports.table7;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.nit.bean.UserinfoBean;
import cn.nit.bean.di.DiCourseCategoriesBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.table7.T741_Bean;
import cn.nit.constants.Constants;
import cn.nit.service.di.DiCourseCategoriesService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table7.T741_Service;
import cn.nit.util.TimeUtil;

import jxl.Cell;

public class T741_Excel {
	
	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request,String selectYear){
		
		if((cellList == null) || (cellList.size() < 2)){
			return "数据不标准，请重新提交" ;
		}
		
		int  count=1;
		
		boolean flag=false;
	    List<T741_Bean> list=new LinkedList<T741_Bean>();
	
	    DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		
		DiCourseCategoriesService diCourseSer=new DiCourseCategoriesService();
		List<DiCourseCategoriesBean>  diCourseBeanList=diCourseSer.getList();
		
		System.out.println(cellList.size());
		
		for(Cell[] cell: cellList){
			T741_Bean T741_Bean=new T741_Bean();
			try {
				
				if(count<4){
					count++;
					continue;
				}
				
				String teaName = cell[1].getContents().trim();
				if(teaName == null || teaName.equals("")){
					return "第" + count + "行，教师姓名不能为空" ;
				}
				if(teaName.length()>50){
					return "第" + count + "行，教师姓名不能超过50个字符" ;
				}
				String teaId = cell[2].getContents().trim() ;
				if(teaId == null || teaId.equals("")){
					return "第" + count + "行，教工号不能为空" ;
				}
				if(teaId.length()>50){
					return "第" + count + "行，教工号不能超过50个字符" ;
				}
				String unit = cell[3].getContents().trim() ;
				if((unit == null) || unit.equals("")){
					return "第" + count + "行，所属教学单位不能为空" ;
				}
				if(unit.length()>200){
					return "第" + count + "行，所属教学单位不能超过200个字符" ;
				}
				String unitId = cell[4].getContents().trim() ;
				if((unitId == null) || unitId.equals("")){
					return "第" + count + "行，单位号不能为空" ;
				}
				if(unitId.length()>50){
					return "第" + count + "行，单位号不能超过50个字符" ;
				}
				for(DiDepartmentBean diDepartBean : diDepartBeanList){
					if(diDepartBean.getUnitId().equals(unitId)){
						if(diDepartBean.getUnitName().equals(unit)){
							flag = true ;
							break ;
						}else{
							return "第" + count + "行，所属教学单位与所属单位号不对应" ;
						}
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，没有与之相匹配的单位号" ;
				}else{
					flag = false ;
				}
				String assCs = cell[5].getContents().trim() ;
				if(assCs == null || assCs.equals("")){
					return "第" + count + "行，参评课程不能为空" ;
				}
				if(assCs.length()>100){
					return "第" + count + "行，参评课程不能超过100个字符" ;
				}
				String csId = cell[6].getContents().trim() ;
				if((csId == null) || csId.equals("")){
					return "第" + count + "行，课程编号不能为空" ;
				}
				if(csId.length()>50){
					return "第" + count + "行，课程编号不能超过50个字符" ;
				}
				String csType = cell[7].getContents().trim() ;
				if((csType == null) || csType.equals("")){
					return "第" + count + "行，课程类别不能为空" ;
				}
				if(!csType.equals("理论课（含实践）") && !csType.equals("理论课（不含实践）")&& !csType.equals("集中性实践环节")&& !csType.equals("实验课")&& !csType.equals("其他")){
					return "第" + count + "行，课程类别只能是“理论课（含实践）”或“理论课（不含实践）”或“集中性实践环节”或“实验课”或“其他”" ;
				}
				String csTypeId=null;
				for(DiCourseCategoriesBean diCorCateBean : diCourseBeanList){
					if(diCorCateBean.getCourseCategories().equals(csType)){
						csTypeId=diCorCateBean.getIndexId();
						flag=true;
						break;
					}
					
				}
				if(!flag){
					return "第" + count + "行，课程类别不存在" ;
				}else{
					flag = false ;
				}
				String assYear = cell[8].getContents().trim() ;
				if((assYear == null) || assYear.equals("")){
					return "第" + count + "行，评估年份不能为空" ;
				}
				if(!TimeUtil.judgeFormat3(assYear)){
					return "第" + count + "行，评估年份格式错误" ;
				}
				String accResult = cell[9].getContents().trim() ;
				if(accResult == null || accResult.equals("")){
					return "第" + count + "行，评估结果不能为空" ;
				}
				if(accResult.length()>50){
					return "第" + count + "行，评估结果不能超过50个字符" ;
				}
				String appID = cell[10].getContents().trim() ;
				if(appID == null || appID.equals("")){
					return "第" + count + "行，批文号不能为空" ;
				}
				if(appID.length()>100){
					return "第" + count + "行，批文号不能超过100个字符" ;
				}
				 String note = cell[11].getContents().trim();
					
					count++ ;
					String fillUnitID=null;	
					T741_Bean.setTeaName(teaName);
					T741_Bean.setTeaID(teaId);
					T741_Bean.setTeaUnit(unit);
					T741_Bean.setUnitID(unitId);
					T741_Bean.setAssessCS(assCs);
					T741_Bean.setCSID(csId);
					T741_Bean.setCSType(csTypeId);
					T741_Bean.setAssessYear(assYear);
					T741_Bean.setAccessResult(accResult);
					T741_Bean.setAppvlID(appID);
					T741_Bean.setFillUnitID(fillUnitID);
					T741_Bean.setCheckState(Constants.WAIT_CHECK);
					T741_Bean.setTime(TimeUtil.changeDateY(selectYear));
					T741_Bean.setNote(note);
					
					list.add(T741_Bean);
					
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
			
		}
		flag = false ;
		T741_Service t741_Sr=new T741_Service();
		flag=t741_Sr.batchInsert(list);
		if(flag){
			return null ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
	}

}
