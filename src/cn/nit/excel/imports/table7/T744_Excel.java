package cn.nit.excel.imports.table7;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.di.DiMajorTwoBean;
import cn.nit.bean.table7.T744_Bean;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.di.DiMajorTwoService;
import cn.nit.service.table7.T744_Service;
import cn.nit.util.TimeUtil;

import jxl.Cell;

public class T744_Excel {
	
	
	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request, String selectYear){
		
		if((cellList == null) || (cellList.size() < 2)){
			return "数据不标准，请重新提交" ;
		}
		
		int count = 1 ;
		T744_Bean T744_Bean =new T744_Bean();
		boolean flag = false ;
		List<T744_Bean> list = new LinkedList<T744_Bean>() ;
		
		DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		
		DiMajorTwoService diMajorSer = new DiMajorTwoService() ;
		List<DiMajorTwoBean> diMajorBeanList = diMajorSer.getList() ;
		
		for(Cell[] cell : cellList){
			try {
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
					return "第" + count + "行，所属单位号不能为空" ;
				}			
				
				for(DiDepartmentBean diDepartBean : diDepartBeanList){
					if(diDepartBean.getUnitId().equals(unitId)){
						if(diDepartBean.getUnitName().equals(unit)){
							flag = true ;
							break ;
						}else{
							return "第" + count + "行，教学单位与所属单位号不对应" ;
						}
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，没有与之相匹配的单位号" ;
				}else{
					flag = false ;
				}

				String major = cell[3].getContents() ;
				String majorId = cell[4].getContents() ;
				
				if(major == null || major.equals("")){
					return "第" + count + "行，专业名称不能为空" ;
				}
				
				if(unitId == null || unitId.equals("")){
					return "第" + count + "行，专业代码不能为空" ;
				}			
				
				for(DiMajorTwoBean diMajorBean : diMajorBeanList){
					if(diMajorBean.getMajorNum().equals(majorId)){
						if(diMajorBean.getMajorName().equals(major)){
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
				
				String deType = cell[5].getContents() ;
				if((deType == null) || deType.equals("")){
					return "第" + count + "行，学位授予门类不能为空" ;
				}
				String leaderName = cell[6].getContents() ;
				if((leaderName == null) || leaderName.equals("")){
					return "第" + count + "行，负责人姓名不能为空" ;
				}
				
				String teaId = cell[7].getContents() ;
				if((teaId == null) || teaId.equals("")){
					return "第" + count + "行，教工号不能为空" ;
				}
				String assYear = cell[8].getContents() ;
				if((assYear == null) || assYear.equals("")){
					return "第" + count + "行，设置年份不能为空" ;
				}
				String assResult = cell[9].getContents() ;
				if(assResult == null || assResult.equals("")){
					return "第" + count + "行，评估结果不能为空" ;
				}
				String appID = cell[10].getContents() ;
				if(appID == null || appID.equals("")){
					return "第" + count + "行，批文号不能为空" ;
				}
				
				String note = cell[11].getContents();
					
				count++ ;

				String fillUnitID = null;
				
				T744_Bean.setTeaUnit(unit);
				T744_Bean.setUnitID(unitId);
				T744_Bean.setMajorName(major);
				T744_Bean.setMajorID(majorId);
				T744_Bean.setDegreeType(deType);
				T744_Bean.setLeaderName(leaderName);
				T744_Bean.setTeaID(teaId);
				T744_Bean.setAssessYear(assYear);
				T744_Bean.setAssessResult(assResult);
				T744_Bean.setAppvlID(appID);
				T744_Bean.setFillUnitID(fillUnitID);
				T744_Bean.setTime(TimeUtil.changeDateY(selectYear));
				T744_Bean.setNote(note);
				
				list.add(T744_Bean);
					
				
				
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
			
		}
		flag = false ;
		T744_Service t744_Sr=new T744_Service();
		flag=t744_Sr.batchInsert(list);
		if(flag){
			return null ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
	}

}
