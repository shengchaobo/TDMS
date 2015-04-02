package cn.nit.excel.imports.table7;

import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import cn.nit.bean.UserinfoBean;
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
		
		boolean flag=false;
	    List<T733_Bean> list=new LinkedList<T733_Bean>();
	
	    DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		
		for(Cell[] cell: cellList){
			T733_Bean T733_Bean=new T733_Bean();
			try {
				if(count<4){
					count++;
					continue;
				}
				
				String unit = cell[1].getContents().trim();
				String unitId = cell[2].getContents().trim() ;
				
				if(unit == null || unit.equals("")){
					return "第" + count + "行，单位名称不能为空" ;
				}
				if(unit.length()>200){
					return "第" + count + "行，单位名称不能超过200个字符" ; 
				}
				
				if(unitId == null || unitId.equals("")){
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
							return "第" + count + "行，所属单位与所属单位号不对应" ;
						}
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，没有与之相匹配的单位号" ;
				}else{
					flag = false ;
				}
				
				String meetDate = cell[3].getContents().trim() ;
				if(meetDate == null || meetDate.equals("")){
					return "第" + count + "行，会议日期不能为空" ;
				}
				if(!TimeUtil.judgeFormatYM(meetDate)){
					return "第" + count + "行，听课日期格式不正确，格式为：2012-09" ;
				}
				String meetmeinfo = cell[4].getContents().trim() ;
				if((meetmeinfo == null) || meetmeinfo.equals("")){
					return "第" + count + "行，参会人员情况不能为空" ;
				}
				if(meetmeinfo.length()>300){
					return "第" + count + "行，参会人员情况不能超过300个字符" ; 
				}
				String meetNum = cell[5].getContents().trim();
				if((meetNum == null) || meetNum.equals("")){
					return "第" + count + "行，参会人数不能为空" ;
				}
				if(!isNumeric(meetNum)){
					return "第" + count + "行，参会人数只能填数字" ;
				}
				String meetTheme = cell[6].getContents().trim();
				if((meetTheme == null) || meetTheme.equals("")){
					return "第" + count + "行，会议主要议题或内容不能为空" ;
				}
				if(meetTheme.length()>200){
					return "第" + count + "行，会议主要议题或内容不能超过200个字符" ; 
				}
				String meetResult = cell[7].getContents().trim() ;
				if((meetResult == null) || meetResult.equals("")){
					return "第" + count + "行，会议形成的主要决议或共识内容不能为空" ;
				}
				if(meetResult.length()>200){
					return "第" + count + "行，会议形成的主要决议或共识内容不能超过1000个字符" ; 
				}
	            String note = cell[8].getContents().trim();
				
				count++ ;
				String fillUnitID=null;	
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
	
	/**判断字符串是否是数字*/
	public boolean isNumeric(String str){
		  for (int i = str.length();--i>=0;){   
		   if (!Character.isDigit(str.charAt(i))){
		    return false;
		   }
		  }
		  return true;
		 }

}
