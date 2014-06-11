package cn.nit.excel.imports.table1;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.Cell;

import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.di.DiResearchTypeBean;
import cn.nit.bean.table1.T151Bean;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.di.DiResearchTypeService;
import cn.nit.service.table1.T151Service;
import cn.nit.util.TimeUtil;


public class T151Excel {
	
	/**
	 * 批量导入
	 * @param cellList {@link java.util.List<{@link jxl.Cell}>}  Cell是数组
	 * @param request  {@link javax.servlet.http.HttpServletRequest}
	 * @return
	 */
	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request){
		
		if((cellList == null) || (cellList.size() < 2)){
			return "数据不标准，请重新提交" ;
		}
		
		int count = 1 ;
		T151Bean t151Bean = null ;
		boolean flag = false ;
		List<T151Bean> list = new LinkedList<T151Bean>() ;
//		UserRoleBean userinfo = (UserRoleBean)request.getSession().getAttribute("userinfo") ;
		DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		DiResearchTypeService diResearchSer=new DiResearchTypeService();
		List<DiResearchTypeBean> diResearchBeanList=diResearchSer.getList();
		
		for(Cell[] cell : cellList){
			int n=cellList.indexOf(cell);
			if(n==0){continue;}
			else
			 {
			 try{
				 
				 String csUnit = cell[3].getContents() ;
					String unitId = cell[4].getContents() ;
					
					if(csUnit == null || csUnit.equals("")){
						return "第" + count + "行，开课单位不能为空" ;
					}
					
					if(unitId == null || unitId.equals("")){
						return "第" + count + "行，单位编号不能为空" ;
					}
					
					if(unitId.length() > 50){
						return "第" + count + "行，单位编号字数不超过50个数字或字母" ;
					}
					
					for(DiDepartmentBean diDepartBean : diDepartBeanList){
						if(diDepartBean.getUnitId().equals(unitId)){
							if(diDepartBean.getUnitName().equals(csUnit)){
								flag = true ;
								break ;
							}else{
								return "第" + count + "行，开课单位与单位编号不对应" ;
							}
						}//if
					}//for
					
					if(!flag){
						return "第" + count + "行，没有与之相匹配的单位编号" ;
					}else{
						flag = false ;
					}
				 
				 
				String ClubName = cell[1].getContents() ;
				if(ClubName == null || ClubName.equals("")){
					return "第" + count + "行，校友会名称不能为空" ;
				}
				
				if(ClubName.length() > 100){
					return "第" + count + "行，课程名称字数不超过100个字" ;
				}
				
				String BuildYearStr = cell[2].getContents() ;
				
				if((BuildYearStr == null) || BuildYearStr.equals("")){
					return "第" + count + "行，设立时间不能为空" ;
				}
				
				String Place = cell[3].getContents() ;
				if(Place == null || Place.equals("")){
					return "第" + count + "行，地点不能为空" ;
				}
//				if(!Place.equals("境外")||!Place.equals("境内")){
//					return "第" + count + "行，地点只能为“境内”或“境外”" ;
//				}
				String note = cell[4].getContents() ;
				
				if(note.length() > 1000){
					return "第" + count + "行，备注不能超过500个汉字" ;
				}
				
				count++ ;
				
				Date BuildYear=TimeUtil.changeDateY(BuildYearStr);
//				t17Bean = new T17Bean();
//				t17Bean.setClubName(ClubName);
//				t17Bean.setBuildYear(BuildYear);
//				t17Bean.setPlace(Place);
//				t17Bean.setNote(note);
//				t17Bean.setTime(new Date()) ;
//				list.add(t17Bean);
//				
			}
			catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
	     }
		}
		
		flag = false ;
		T151Service t151Ser = new T151Service() ;
		flag = t151Ser.batchInsert(list) ;
		
		if(flag){
			return "数据导入成功" ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
		
	}
	
	public static void main(String arg[])
	{

    }
}
