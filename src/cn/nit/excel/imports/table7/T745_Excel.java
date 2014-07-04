package cn.nit.excel.imports.table7;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.table7.T745_Bean;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table7.T745_Service;
import cn.nit.util.TimeUtil;

import jxl.Cell;

public class T745_Excel {
	
	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request,String selectYear){
		
		
		if((cellList == null) || (cellList.size() < 2)){
			return "数据不标准，请重新提交" ;
		}
		
		int  count=1;
		T745_Bean T745_Bean=new T745_Bean();
		boolean flag=false;
	    List<T745_Bean> list=new LinkedList<T745_Bean>();
	
	    DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		
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
				
				String assYear = cell[3].getContents() ;
				if((assYear == null) || assYear.equals("")){
					return "第" + count + "行，设置年份不能为空" ;
				}
				String assResult = cell[4].getContents() ;
				if(assResult == null || assResult.equals("")){
					return "第" + count + "行，评估结果不能为空" ;
				}
				String appID = cell[5].getContents() ;
				if(appID == null || appID.equals("")){
					return "第" + count + "行，批文号不能为空" ;
				}
				
				String note = cell[6].getContents();
					
				count++ ;

				String fillUnitID = null;
				
				
				T745_Bean.setTeaUnit(unit);
				T745_Bean.setUnitID(unitId);
				T745_Bean.setAssessYear(assYear);
				T745_Bean.setAssessResult(assResult);
				T745_Bean.setAppvlID(appID);
				T745_Bean.setFillUnitID(fillUnitID);
				T745_Bean.setTime(TimeUtil.changeDateY(selectYear));
				T745_Bean.setNote(note);
				
				list.add(T745_Bean);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
			
		}
		flag = false ;
		T745_Service t745_Sr=new T745_Service();
		flag=t745_Sr.batchInsert(list);
		if(flag){
			return null ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
	}

}