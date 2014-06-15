package cn.nit.excel.imports.table1;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.Cell;
import cn.nit.bean.di.DiAwardLevelBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.other.UserRoleBean;
import cn.nit.bean.table1.T181Bean;
import cn.nit.service.di.DiAwardLevelService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table1.T181Service;
import cn.nit.util.TimeUtil;

public class T183Excel {


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
		boolean biOpen=false;
		boolean buildCondi=false;
		List<T181Bean> list = new LinkedList<T181Bean>() ;
		UserRoleBean userinfo = (UserRoleBean)request.getSession().getAttribute("userinfo") ;
		DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
	    DiAwardLevelService diAwardLevelSer=new DiAwardLevelService();
	    List<DiAwardLevelBean> diAwardLevelList = diAwardLevelSer.getList();
		
	
		
		for(Cell[] cell : cellList){
			
			T181Bean t181Bean = new  T181Bean();
			int n=cellList.indexOf(cell);
			if(n==0){continue;}
			else{
				
				
			  try{
				 
				    String CooperInsName = cell[1].getContents() ;
				    
				    if(CooperInsName == null || CooperInsName.equals("")){
				    	return "第" + count + "行，合作机构名称不能为空" ;
				    }
				    if(CooperInsName.length()>100){
				    	return "第" + count + "行，合作机构名称不能为空" ;
				    }
				    
				    
					String CooperInsType = cell[2].getContents() ;
					
					if(CooperInsType == null || CooperInsType.equals("")){
						return "第" + count + "行，合作机构类型不能为空" ;
					}
					
					if(!CooperInsType.equals("学术机构")&&!CooperInsType.equals("行业机构和企业")&&!CooperInsType.equals("地方政府")){
						return "第" + count + "行，合作机构类型只能为“学术机构”或者“行业机构和企业”或者“地方政府”" ;
					}
					
					String CooperInsLevel = cell[3].getContents();
					
					if(CooperInsLevel == null || CooperInsLevel.equals("")){
						return "第" + count + "行，合作机构级别不能为空" ;
					}
					
					for(DiAwardLevelBean diAwardLevelBean:diAwardLevelList)	{
						if(diAwardLevelBean.getAwardLevel().equals(CooperInsLevel)){
							CooperInsLevel = diAwardLevelBean.getIndexId() ;
							flag = true;
							break ;
						}
					}
					if(!flag){
						return "第" + count + "行，合作机构级别不存在" ;
					}else{
						flag=false;
					}
					
				 
					String SignedTime = cell[4].getContents() ;
					
					if(SignedTime == null || SignedTime.equals("")){
						return "第" + count + "行，签订协议时间不能为空" ;
					}
					
					if(TimeUtil.judgeFormat1(SignedTime)&&TimeUtil.judgeFormat2(SignedTime)){
						return "第" + count + "行，签订协议时间格式有误（格式如：2013/03或者2013/03/01）" ;
					}
					
					String UnitName = cell[5].getContents();
					String UnitID=cell[6].getContents();
					
					if(UnitName == null || UnitName.equals("")){
						return "第" + count + "行，我方不能为空";
					}
					
					if(UnitID == null || UnitID.equals("")){
						return "第" + count + "行，我方单位号不能为空";
					}
					
					if(UnitID.length()>50){
						return "第" + count + "行，我方单位号长度不能超过50";
					}
					for(DiDepartmentBean diDepartBean : diDepartBeanList){
						if(diDepartBean.getUnitId().equals(UnitID)){
							if(diDepartBean.getUnitName().equals(UnitName)){
								flag = true ;
								break ;
							}else{
								return "第" + count + "行，我方单位与单位编号不对应" ;
							}
						}//if
					}//for
					
					if(!flag){
						return "第" + count + "行，没有与之相匹配的单位编号" ;
					}else{
						flag = false ;	}
						
					
					String UnitLevel=cell[7].getContents();
					
					if(UnitLevel == null || UnitLevel.equals("")){
						return "第" + count + "行，我方单位级别不能为空" ;
					}
					 for(DiAwardLevelBean diAwardLevelBean:diAwardLevelList){
						 if(diAwardLevelBean.getAwardLevel().equals(UnitLevel)){
							 	UnitLevel = diAwardLevelBean.getIndexId() ;
								flag = true;
								break ;
							}
					 }
					 if(!flag){
							return "第" + count + "行，我方单位级别不存在" ;
						}else{
							flag=false;
						}
					
					String note=cell[8].getContents();
					
					if(note.length()>500){
						return "第" + count + "行，备注字数不能超过500" ;
					}
					
				
				count++ ;
				
				String FillDept=userinfo.getTeaID();
				Date signedTime=TimeUtil.changeDate4(SignedTime);
				
				t181Bean.setCooperInsLevel(CooperInsLevel);
				t181Bean.setCooperInsName(CooperInsName);
				t181Bean.setCooperInsType(CooperInsType);
				t181Bean.setFillDept(FillDept);
				t181Bean.setNote(note);
				t181Bean.setSignedTime(signedTime);
				t181Bean.setTime(new Date());
				t181Bean.setUnitID(UnitID);
				t181Bean.setUnitLevel(UnitLevel);
				t181Bean.setUnitName(UnitName);
				
				list.add(t181Bean);			
			}
			catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
	     }
		}
		
		flag = false ;
		T181Service t181Ser = new T181Service() ;
		flag = t181Ser.batchInsert(list) ;
		
		if(flag){
			return "数据导入成功" ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
		
	}
	
}
