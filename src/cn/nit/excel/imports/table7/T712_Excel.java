package cn.nit.excel.imports.table7;


import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.table7.T712_Bean;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table7.T712_Service;
import cn.nit.util.TimeUtil;

import jxl.Cell;

public class T712_Excel {
	
	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request,String selectYear){
		
		if((cellList == null) || (cellList.size() < 2)){
			return "数据不标准，请重新提交" ;
		}
		
		int  count=1;
		T712_Bean T712_Bean=new T712_Bean();
		boolean flag=false;
	    List<T712_Bean> list=new LinkedList<T712_Bean>();
	
	    DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		
        for(Cell[] cell: cellList){
			
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
					if(name == null || name.equals("")){
						return "第" + count + "行，姓名不能为空" ;
					}
					String teaId = cell[4].getContents() ;
					if((teaId == null) || teaId.equals("")){
						return "第" + count + "行，教工号不能为空" ;
					}
					String perName = cell[5].getContents() ;
					if((perName == null) || perName.equals("")){
						return "第" + count + "行，论文名称不能为空" ;
					}
					String perType = cell[6].getContents() ;
					if((perType == null) || perType.equals("")){
						return "第" + count + "行，归口类型不能为空" ;
					}
					String FSub = cell[7].getContents();
					if(FSub == null || FSub.equals("")){
						return "第" + count + "行，所属一级学科不能为空" ;
					}
					String JnName = cell[8].getContents();
					if(JnName == null || JnName.equals("")){
						return "第" + count + "行，刊物/会议名称不能为空" ;
					}
					String JnID = cell[9].getContents();
					if(JnID == null || JnID.equals("")){
						return "第" + count + "行，刊号不能为空" ;
					}
					String JnTime = cell[10].getContents();
					if(JnTime == null || JnTime.equals("")){
						return "第" + count + "行，刊期/日期不能为空" ;
					}
					String PWNum = cell[11].getContents();
					if(PWNum == null || PWNum.equals("")){
						return "第" + count + "行，论文字数不能为空" ;
					}
					String CfLevel = cell[12].getContents();
					if(CfLevel == null || CfLevel.equals("")){
						return "第" + count + "行，认定等级不能为空" ;
					}
					String joinTn = cell[13].getContents();
					if(joinTn == null || joinTn.equals("")){
						return "第" + count + "行，合作教师人数不能为空" ;
					}
					String otherJTI = cell[14].getContents();
					if(otherJTI == null || otherJTI.equals("")){
						return "第" + count + "行，其他合作教师不能为空" ;
					}
					
					String note = cell[13].getContents();
					count++ ;

					String fillUnitID = null;
					T712_Bean.setTeaUnit(unit);
					T712_Bean.setUnitID(unitId);
					T712_Bean.setName(name);
					T712_Bean.setTeaID(teaId);
					T712_Bean.setPaperName(perName);
					T712_Bean.setPaperType(perType);
					T712_Bean.setFirstSubject(FSub);
					T712_Bean.setJonalName(JnName);
					T712_Bean.setJonalID(JnID);
					T712_Bean.setJonalTime(TimeUtil.changeDateYM(JnTime));
					T712_Bean.setPaperWordNum(Integer.parseInt(PWNum));
					T712_Bean.setConfirmLevel(CfLevel);
					T712_Bean.setJoinTeaNum(Integer.parseInt(joinTn));
					T712_Bean.setOtherJoinTeaInfo(otherJTI);
					T712_Bean.setFillUnitID(fillUnitID);
					T712_Bean.setTime(TimeUtil.changeDateY(selectYear));
					T712_Bean.setNote(note);
					list.add(T712_Bean);
		    	   
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
        	
        }
			
        flag = false ;
		T712_Service t712_Sr=new T712_Service();
		flag=t712_Sr.batchInsert(list);
		if(flag){
			return null ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
	}

	}
