package cn.nit.excel.imports.table7;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.table7.T734_Bean;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table7.T734_Service;
import cn.nit.util.TimeUtil;

import jxl.Cell;

public class T734_Excel {
	
	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request,String selectYear){
		
		if((cellList == null) || (cellList.size() < 2)){
			return "数据不标准，请重新提交" ;
		}
		
		int  count=1;
		T734_Bean T734_Bean=new T734_Bean();
		boolean flag=false;
	    List<T734_Bean> list=new LinkedList<T734_Bean>();
	
	    DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		
		for(Cell[] cell: cellList){
			
			try {
				
				if(count<4){
					count++;
					continue;
				}
				String teaName = cell[1].getContents() ;
				if(teaName == null || teaName.equals("")){
					return "第" + count + "行，教师不能为空" ;
				}
				String teaId = cell[2].getContents() ;
				if(teaId == null || teaId.equals("")){
					return "第" + count + "行，教工号不能为空" ;
				}
				String unit = cell[3].getContents() ;
				if((unit == null) || unit.equals("")){
					return "第" + count + "行，所属部门不能为空" ;
				}
				String unitId = cell[4].getContents() ;
				if((unitId == null) || unitId.equals("")){
					return "第" + count + "行，单位号不能为空" ;
				}
				
				for(DiDepartmentBean diDepartBean : diDepartBeanList){
					if(diDepartBean.getUnitId().equals(unitId)){
						if(diDepartBean.getUnitName().equals(unit)){
							flag = true ;
							break ;
						}else{
							return "第" + count + "行，所属部门与所属单位号不对应" ;
						}
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，没有与之相匹配的单位号" ;
				}else{
					flag = false ;
				}
				String accsite = cell[5].getContents() ;
				if((accsite == null) || accsite.equals("")){
					return "第" + count + "行，事故发生地点不能为空" ;
				}
				String cause = cell[6].getContents() ;
				if((cause == null) || cause.equals("")){
					return "第" + count + "行，事由不能为空" ;
				}
				String handTime = cell[7].getContents() ;
				if((handTime == null) || handTime.equals("")){
					return "第" + count + "行，处理时间不能为空" ;
				}
				
				String accLevel = cell[8].getContents() ;
				if((accLevel == null) || accLevel.equals("")){
					return "第" + count + "行，教学事故等级不能为空" ;
				}
				String handId = cell[9].getContents() ;
				if((handId == null) || handId.equals("")){
					return "第" + count + "行，处理文号不能为空" ;
				}
                String note = cell[10].getContents();
				
				count++ ;

				String fillUnitID = null;
				
				T734_Bean.setTeaName(teaName);
				T734_Bean.setTeaID(teaId);
				T734_Bean.setFromDept(unit);
				T734_Bean.setUnitID(unitId);
				T734_Bean.setAccidentSite(accsite);
				T734_Bean.setCause(cause);
				T734_Bean.setHandingTime(TimeUtil.changeDateYMD(handTime));
				T734_Bean.setAccidentLevel(accLevel);
				T734_Bean.setHandingID(handId);
				T734_Bean.setFillUnitID(fillUnitID);
				T734_Bean.setTime(TimeUtil.changeDateY(selectYear));
				T734_Bean.setNote(note);
				
				list.add(T734_Bean);
				
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
		}
		flag=false;
		T734_Service t734_Sr=new T734_Service();
		flag=t734_Sr.batchInsert(list);
		if(flag){
			return null;
			}else{
				return "数据存储失败，请联系管理员" ;
			}
		}
		

}
