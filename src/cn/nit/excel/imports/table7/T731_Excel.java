package cn.nit.excel.imports.table7;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import cn.nit.bean.UserinfoBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.table4.T411_Bean;
import cn.nit.bean.table7.T731_Bean;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table4.T411_Service;
import cn.nit.service.table7.T731_Service;
import cn.nit.util.TimeUtil;

import jxl.Cell;

public class T731_Excel {
	
	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request,String selectYear){
		
		if((cellList == null) || (cellList.size() < 2)){
			return "数据不标准，请重新提交" ;
		}
		
		int  count=1;
		boolean flag=false;
	    List<T731_Bean> list=new LinkedList<T731_Bean>();
	    
		T411_Service t411_Ser=new T411_Service();
		List<T411_Bean> t411_BeanList = t411_Ser.getList();
	
	    DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		for(Cell[] cell: cellList){
			T731_Bean T731_Bean=new T731_Bean();	
			try {		
				if(count<4){
					count++;
					continue;
				}
				
				String attclTerm = cell[1].getContents().trim();
				if(attclTerm == null || attclTerm.equals("")){
					return "第" + count + "行，听课学期不能为空" ;
				}
				if(attclTerm.length()>50){
					return "第" + count + "行，听课学期不能超过50个字符" ; 
				}
				String leaderName = cell[2].getContents().trim() ;
				if(leaderName == null || leaderName.equals("")){
					return "第" + count + "行，校领导姓名不能为空" ;
				}
				if(leaderName.length()>50){
					return "第" + count + "行，校领导姓名不能超过50个字符" ; 
				}
				String leaderId = cell[3].getContents().trim() ;
				if(leaderId == null || leaderId.equals("")){
					return "第" + count + "行，校领导教工号不能为空" ;
				}
				if(leaderId.length()>50){
					return "第" + count + "行，校领导教工号不能超过50个字符" ; 
				}
				String attclTime = cell[4].getContents().trim();
				if((attclTime == null) || attclTime.equals("")){
					return "第" + count + "行，听课日期不能为空" ;
				}
				if(!TimeUtil.judgeFormatYM(attclTime)){
					return "第" + count + "行，听课日期格式不正确，格式为：2012-09" ;
				}
				String lecTea = cell[5].getContents().trim() ;
				String lecTeaId = cell[6].getContents().trim() ;
				if((lecTea == null) || lecTea.equals("")){
					return "第" + count + "行，授课教师不能为空" ;
				}
				if(lecTea.length()>50){
					return "第" + count + "行，授课教师不能超过50个字符" ; 
				}
				
				if((lecTeaId == null) || lecTeaId.equals("")){
					return "第" + count + "行，授课教教工号不能为空" ;
				}
				if(lecTeaId.length()>50){
					return "第" + count + "行，授课教教工号不能超过50个字符" ; 
				}
				
				for(T411_Bean t411_Bean : t411_BeanList){
					if(t411_Bean.getTeaId().equals(lecTeaId)){
						if(t411_Bean.getTeaName().equals(lecTea)){
							flag = true ;
							break ;
						}else{
							return "第" + count + "行，建设负责人与教工号不对应" ;
							
						}
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，没有与之相匹配的教工号" ;
				}else{
					flag=false;
				}
				String lecCS = cell[7].getContents().trim() ;
				if((lecCS == null) || lecCS.equals("")){
					return "第" + count + "行，听课课程不能为空" ;
				}
				if(lecCS.length()>100){
					return "第" + count + "行，听课课程不能超过100个字符" ; 
				}
				
				String csId = cell[8].getContents().trim() ;
				if((csId == null) || csId.equals("")){
					return "第" + count + "行，课程编号不能为空" ;
				}
				if(csId.length()>50){
					return "第" + count + "行，课程编号不能超过50个字符" ; 
				}
				String unit = cell[9].getContents().trim();
				if((unit == null) || unit.equals("")){
					return "第" + count + "行，开课单位不能为空" ;
				}
				if(unit.length()>200){
					return "第" + count + "行，开课单位不能超过200个字符" ; 
				}
				String unitId = cell[10].getContents().trim() ;
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
							return "第" + count + "行，开课单位与所属单位号不对应" ;
						}
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，没有与之相匹配的单位号" ;
				}else{
					flag = false ;
				}
				
				String lecClass = cell[11].getContents().trim() ;
				if((lecClass == null) || lecClass.equals("")){
					return "第" + count + "行，上课班级不能为空" ;
				}
				if(lecClass.length()>100){
					return "第" + count + "行，上课班级不能超过100个字符" ; 
				}
				String eva = cell[12].getContents().trim();
				if((eva == null) || eva.equals("")){
					return "第" + count + "行，综合评价不能为空" ;
				}
				if(!eva.equals("优") && !eva.equals("良")&& !eva.equals("中")&& !eva.equals("合格")&& !eva.equals("不合格")){
					return "第" + count + "行，综合评价格式有误，只能填写“优”或“良”或“中”或“合格”或“不合格”";
				}
				String note = cell[13].getContents().trim();
				
				count++ ;
				String fillUnitID=null;	
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
//				T731_Bean.setFillUnitID(fillUnitID);
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
