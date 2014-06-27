package cn.nit.excel.imports.table1;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.Cell;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.di.DiResearchTypeBean;
import cn.nit.bean.table1.T12Bean;
import cn.nit.bean.table4.T411_Bean;

import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.di.DiResearchTypeService;
import cn.nit.service.table1.T12Service;
import cn.nit.service.table4.T411_Service;

import cn.nit.util.DateUtil;
import cn.nit.util.TimeUtil;

public class T12Excel {
	
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
		
		boolean flag = false ;
        Date time=new Date();
		List<T12Bean> list = new LinkedList<T12Bean>() ;
//		UserRoleBean userinfo = (UserRoleBean)request.getSession().getAttribute("userinfo") ;
		DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		T411_Service t411_Ser=new T411_Service();
		List<T411_Bean> t411List=t411_Ser.getList();
		
		for(Cell[] cell : cellList){
			T12Bean t12Bean = new  T12Bean();
			int n=cellList.indexOf(cell);
			if(n==0||n==1){continue;}
			else{
				
				
			  try{
				 
				 String UnitName=cell[1].getContents();
				 String UnitID=cell[2].getContents();
				 
				 if(UnitName == null || UnitName.equals("")){
					 return "第" + count + "行，行政单位名称不能为空！";
				 }
				 if(UnitID == null || UnitID.equals("")){
					 return "第" + count + "行，单位号不能为空！";
				 }
				 if(UnitID.length()>50){
					 return "第" + count + "行，单位编号字数不超过50个数字或字母" ;
				 }
				 
				 for(DiDepartmentBean diDepartBean:diDepartBeanList){
					 if(diDepartBean.getUnitId().equals(UnitID)){
						 if(diDepartBean.getUnitName().equals(UnitName)){
							 flag = true;
							 break;
						 }else{
							 return "第" + count + "行， 行政单位名称与单位编号不对应！" ; 
						 }
					 }
				 }
					
					if(!flag){
						return "第" + count + "行，没有与之相匹配的单位编号！" ;
					}else{
						flag = false ;
					}
				 
					String Function = cell[3].getContents() ;
					
					if(Function == null || Function.equals("")){
						return "第" + count + "行，单位职能不能为空！" ;
					}
					if(Function.length()>300){
						return "第" + count + "行，单位职能字数不能超过150个字！" ;
					}
					
					String Leader=cell[4].getContents();
					String TeaID=cell[5].getContents();
					
					if(Leader == null || Leader.equals("")){
						return "第" + count + "行，单位负责人名称不能为空！" ;
					}
					if(Leader.length()>50){
						return "第" + count + "行，单位负责人名称不能超过25个字！ ";
					}
					if(TeaID == null || TeaID.equals("")){
						return "第" + count + "行，教工号不能为空！" ;
			        }
					if(TeaID.length()>50){
						return "第" + count + "行，教工号长度不能超过50个字符！" ;
					}
					
					for(T411_Bean t411Bean: t411List){
						if(t411Bean.getTeaId().equals(TeaID)){
							if(t411Bean.getTeaName().equals(Leader)){
								flag=true;
								break;
							}
						}else{
							return "第" + count + "行，教工号不正确！" ;
						}
					}
					if(!flag){
						return "第" + count + "行，没有与负责人相匹配的教工号" ;
					}else{
						flag=false;
					}
	
					String  Note=cell[6].getContents();
					if(Note.length()>1000){
						return "第" + count + "行，备注的长度不能超过500个字符！" ;
					}
					
				count++ ;
				
				t12Bean.setUnitName(UnitName);
				t12Bean.setUnitID(UnitID);
				t12Bean.setFunctions(Function);
				t12Bean.setLeader(Leader);
				t12Bean.setTeaID(TeaID);
				t12Bean.setNote(Note);
				t12Bean.setTime(time);
				
				list.add(t12Bean);
							
			}
			catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
	     }
		}
		
		flag = false ;
		T12Service t12Ser = new T12Service() ;
		flag = t12Ser.batchInsert(list) ;
		
		if(flag){
			return "数据导入成功" ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
		
	}

}
