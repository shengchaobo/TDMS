package cn.nit.excel.imports.table7;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.table7.T731_Bean;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table7.T731_Service;
import cn.nit.util.TimeUtil;

import jxl.Cell;

public class T731_Excel {
	
	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request,String selectYear){
		
		if((cellList == null) || (cellList.size() < 2)){
			return "数据不标准，请重新提交" ;
		}
		
		int  count=1;
		T731_Bean T731_Bean=new T731_Bean();
		boolean flag=false;
	    List<T731_Bean> list=new LinkedList<T731_Bean>();
	
	    DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		for(Cell[] cell: cellList){
			
			
			try {
				
				if(count<4){
					count++;
					continue;
				}
				
				String attclTerm = cell[1].getContents() ;
				if(attclTerm == null || attclTerm.equals("")){
					return "第" + count + "行，听课学期不能为空" ;
				}
				String leaderName = cell[2].getContents() ;
				if(leaderName == null || leaderName.equals("")){
					return "第" + count + "行，校领导姓名不能为空" ;
				}
				String leaderId = cell[3].getContents() ;
				if(leaderId == null || leaderId.equals("")){
					return "第" + count + "行，校领导教工号不能为空" ;
				}
				String attclTime = cell[4].getContents() ;
				if((attclTime == null) || attclTime.equals("")){
					return "第" + count + "行，听课日期不能为空" ;
				}
				String lecTea = cell[5].getContents() ;
				if((lecTea == null) || lecTea.equals("")){
					return "第" + count + "行，授课教师不能为空" ;
				}
				String lecTeaId = cell[6].getContents() ;
				if((lecTeaId == null) || lecTeaId.equals("")){
					return "第" + count + "行，授课教教工号不能为空" ;
				}
				String lecCS = cell[7].getContents() ;
				if((lecCS == null) || lecCS.equals("")){
					return "第" + count + "行，听课课程不能为空" ;
				}
				
				String csId = cell[8].getContents() ;
				if((csId == null) || csId.equals("")){
					return "第" + count + "行，课程编号不能为空" ;
				}
				String unit = cell[9].getContents() ;
				if((unit == null) || unit.equals("")){
					return "第" + count + "行，开课单位不能为空" ;
				}
				String unitId = cell[10].getContents() ;
				if((unitId == null) || unitId.equals("")){
					return "第" + count + "行，单位号不能为空" ;
				}
				
				for(DiDepartmentBean diDepartBean : diDepartBeanList){
					if(diDepartBean.getUnitId().equals(unitId)){
						if(diDepartBean.getUnitName().equals(unit)){
							flag = true ;
							break ;
						}else{
							return "第" + count + "行，开课单位与所属单位号不对应" ;
						}
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，没有与之相匹配的单位号" ;
				}else{
					flag = false ;
				}
				
				String lecClass = cell[11].getContents() ;
				if((lecClass == null) || lecClass.equals("")){
					return "第" + count + "行，上课班级不能为空" ;
				}
				String eva = cell[12].getContents() ;
				if((eva == null) || eva.equals("")){
					return "第" + count + "行，综合评价不能为空" ;
				}
				
				String note = cell[13].getContents();
				
				count++ ;

				String fillUnitID = null;
				
				T731_Bean.setAttendClassTerm(attclTerm);
				T731_Bean.setLeaderName(leaderName);
				T731_Bean.setLeaderID(leaderId);
				T731_Bean.setAttendClassTime(TimeUtil.changeDateYM(attclTime));
				T731_Bean.setLectureTea(lecTea);
				T731_Bean.setLectureTeaID(lecTeaId);
				T731_Bean.setLectureCS(lecCS);
				T731_Bean.setCSID(csId);
				T731_Bean.setSetCSUnit(unit);
				T731_Bean.setUnitID(unitId);
				T731_Bean.setLectureClass(lecClass);
				T731_Bean.setEvaluate(eva);
				T731_Bean.setFillUnitID(fillUnitID);
				T731_Bean.setTime(TimeUtil.changeDateY(selectYear));
				T731_Bean.setNote(note);
				
				list.add(T731_Bean);
				
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
		
		
		}
		
		 flag = false ;
			T731_Service t731_Sr=new T731_Service();
			flag=t731_Sr.batchInsert(list);
			if(flag){
				return null ;
			}else{
				return "数据存储失败，请联系管理员" ;
			}
		}

}
