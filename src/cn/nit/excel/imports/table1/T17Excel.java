package cn.nit.excel.imports.table1;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.nit.bean.di.DiCourseCategoriesBean;
import cn.nit.bean.di.DiCourseCharBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.di.DiResearchRoomBean;
import cn.nit.bean.other.UserRoleBean;
import cn.nit.bean.table1.T17Bean;
import cn.nit.service.UndergraCSBaseTeaService;
import cn.nit.service.di.DiCourseCategoriesService;
import cn.nit.service.di.DiCourseCharService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.di.DiResearchRoomService;
import cn.nit.service.table1.T17Service;
import cn.nit.util.TimeUtil;

import jxl.Cell;

public class T17Excel {
	
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
		T17Bean t17Bean = null ;
		boolean flag = false ;
		List<T17Bean> list = new LinkedList<T17Bean>() ;
//		UserRoleBean userinfo = (UserRoleBean)request.getSession().getAttribute("userinfo") ;
//		DiDepartmentService diDepartSer = new DiDepartmentService() ;
//		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
//		DiCourseCategoriesService diCSCateSer = new DiCourseCategoriesService() ;
//		List<DiCourseCategoriesBean> diCSCateBeanList = diCSCateSer.getList() ;
//		DiCourseCharService diCSCharSer = new DiCourseCharService() ;
//		List<DiCourseCharBean> diCSCharBeanList = diCSCharSer.getList() ;
//		DiResearchRoomService diResearchRoomSer = new DiResearchRoomService() ;
//		List<DiResearchRoomBean> diResearchRoomBeanList = diResearchRoomSer.getList() ;
		
		for(Cell[] cell : cellList){
			 int n=cellList.indexOf(cell);
			 if(n==0){continue;}
			 else
			 {
				 
			
			try{
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
				t17Bean = new T17Bean();
				t17Bean.setClubName(ClubName);
				t17Bean.setBuildYear(BuildYear);
				t17Bean.setPlace(Place);
				t17Bean.setNote(note);
				t17Bean.setTime(new Date()) ;
				list.add(t17Bean);
				
			}
			catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
	     }
		}
		
		flag = false ;
		T17Service t17Ser = new T17Service() ;
		flag = t17Ser.batchInsert(list) ;
		
		if(flag){
			return "数据导入成功" ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
		
	}
	
	public static void main(String arg[])
	{
//		List<Integer> list=new ArrayList<Integer>();
//		list.add(1);
//		list.add(2);
//		list.add(3);
//		list.add(4);
//		for(Integer i:list)
//		{
//			int n=list.indexOf(i);
//			if(n==0)
//			{
//				continue;
//			}else
//			{System.out.println(i);}
//		}
    }
}

