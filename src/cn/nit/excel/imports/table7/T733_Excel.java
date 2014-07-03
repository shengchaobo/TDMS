package cn.nit.excel.imports.table7;

import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.table7.T733_Bean;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table7.T733_Service;
import cn.nit.util.TimeUtil;

import jxl.Cell;

public class T733_Excel {
	
	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request,String selectYear){
		
		if((cellList == null) || (cellList.size() < 2)){
			return "数据不标准，请重新提交" ;
		}
		
		int  count=1;
		T733_Bean T733_Bean=new T733_Bean();
		boolean flag=false;
	    List<T733_Bean> list=new LinkedList<T733_Bean>();
	
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
					return "第" + count + "行，单位名称不能为空" ;
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
				
				String meetDate = cell[3].getContents() ;
				if(meetDate == null || meetDate.equals("")){
					return "第" + count + "行，会议日期不能为空" ;
				}
				String meetmeinfo = cell[4].getContents() ;
				if((meetmeinfo == null) || meetmeinfo.equals("")){
					return "第" + count + "行，参会人员情况不能为空" ;
				}
				String meetNum = cell[5].getContents() ;
				if((meetNum == null) || meetNum.equals("")){
					return "第" + count + "行，参会人数不能为空" ;
				}
				String meetTheme = cell[6].getContents() ;
				if((meetTheme == null) || meetTheme.equals("")){
					return "第" + count + "行，会议主要议题或内容不能为空" ;
				}
				String meetResult = cell[7].getContents() ;
				if((meetResult == null) || meetResult.equals("")){
					return "第" + count + "行，会议形成的主要决议或共识内容不能为空" ;
				}
	            String note = cell[8].getContents();
				
				count++ ;

				String fillUnitID = null;
				
				T733_Bean.setUnitName(unit);
				T733_Bean.setUnitID(unitId);
				T733_Bean.setMeetingDate(TimeUtil.changeDateYM(meetDate));
				T733_Bean.setMeetingMemberInfo(meetmeinfo);
				T733_Bean.setMeetingNum(Integer.parseInt(meetNum));
				T733_Bean.setMeetingTheme(meetTheme);
				T733_Bean.setMeetingResult(meetResult);
				T733_Bean.setFillUnitID(fillUnitID);
				T733_Bean.setTime(TimeUtil.changeDateY(selectYear));
				T733_Bean.setNote(note);
				
				list.add(T733_Bean);				
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
		}
		 flag = false ;
			T733_Service t733_Sr=new T733_Service();
			flag=t733_Sr.batchInsert(list);
			if(flag){
				return null ;
			}else{
				return "数据存储失败，请联系管理员" ;
			}
		}

}
