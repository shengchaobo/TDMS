package cn.nit.excel.imports.table7;

import java.sql.Time;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.nit.bean.UserinfoBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.table7.T735_Bean;
import cn.nit.constants.Constants;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table7.T735_Service;
import cn.nit.util.TimeUtil;

import jxl.Cell;

public class T735_Excel {
	
	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request,String selectYear){
		
		if((cellList == null) || (cellList.size() < 2)){
			return "数据不标准，请重新提交" ;
		}
		
		int  count=1;
		
		boolean flag=false;
	    List<T735_Bean> list=new LinkedList<T735_Bean>();
	
	    DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		for(Cell[] cell: cellList){
			T735_Bean T735_Bean=new T735_Bean();
			try {
				
				if(count<4){
					count++;
					continue;
				}
				String unit = cell[1].getContents().trim() ;
				String unitId = cell[2].getContents().trim() ;
				
				if(unit == null || unit.equals("")){
					return "第" + count + "行，教学单位不能为空" ;
				}
				if(unit.length()>200){
					return "第" + count + "行，教学单位不能超过200个字符" ; 
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
							return "第" + count + "行，教学单位与所属单位号不对应" ;
						}
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，没有与之相匹配的单位号" ;
				}else{
					flag = false ;
				}
				
				String assResult = cell[3].getContents().trim() ;
				if(assResult == null || assResult.equals("")){
					return "第" + count + "行，考评结论不能为空" ;
				}
				if(!assResult.equals("优秀") && !assResult.equals("良好")&& !assResult.equals("合格")&& !assResult.equals("不合格")){
					return "第" + count + "行，考评结论只能是“优秀”或“良好”或“合格”或“不合格”" ;
				}
				String assYear = cell[4].getContents().trim() ;
				if((assYear == null) || assYear.equals("")){
					return "第" + count + "行，考评年份不能为空" ;
				}
				if(!TimeUtil.judgeFormat3(assYear)){
					return "第" + count + "行，考评年份格式不正确！" ;
				}
               String note = cell[5].getContents().trim();
				
				count++ ;
				String fillUnitID=null;	
				T735_Bean.setTeaUnit(unit);
				T735_Bean.setUnitID(unitId);
				T735_Bean.setAssessResult(assResult);
				T735_Bean.setAssessYear(assYear);
//				T735_Bean.setFillUnitID(fillUnitID);
				T735_Bean.setCheckState(Constants.WAIT_CHECK);
				T735_Bean.setTime(TimeUtil.changeDateY(selectYear));
				T735_Bean.setNote(note);
				list.add(T735_Bean);
				
				
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
			
		}
		 flag = false ;
			T735_Service t735_Sr=new T735_Service();
			flag=t735_Sr.batchInsert(list);
			if(flag){
				return null ;
			}else{
				return "数据存储失败，请联系管理员" ;
			}
		}

}
