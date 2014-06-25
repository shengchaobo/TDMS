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
		boolean biOpen=false;
		boolean buildCondi=false;
		List<T151Bean> list = new LinkedList<T151Bean>() ;
//		UserRoleBean userinfo = (UserRoleBean)request.getSession().getAttribute("userinfo") ;
		DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		DiResearchTypeService diResearchSer=new DiResearchTypeService();
		List<DiResearchTypeBean> diResearchBeanList=diResearchSer.getList();
		
		for(Cell[] cell : cellList){
			T151Bean t151Bean = new  T151Bean();
			int n=cellList.indexOf(cell);
			if(n==0){continue;}
			else{
				
				
			  try{
				 
				 String ResInsName = cell[1].getContents() ;
					String ResInsID = cell[2].getContents() ;
					
					if(ResInsName == null || ResInsName.equals("")){
						return "第" + count + "行，科研机构不能为空" ;
					}
					
					if(ResInsID == null || ResInsID.equals("")){
						return "第" + count + "行，科研机构单位编号不能为空" ;
					}
					
					if(ResInsID.length() > 50){
						return "第" + count + "行，单位编号字数不超过50个数字或字母" ;
					}
					
					for(DiDepartmentBean diDepartBean : diDepartBeanList){
						if(diDepartBean.getUnitId().equals(ResInsID)){
							if(diDepartBean.getUnitName().equals(ResInsName)){
								flag = true ;
								break ;
							}else{
								return "第" + count + "行，科研机构与单位编号不对应" ;
							}
						}//if
					}//for
					
					if(!flag){
						return "第" + count + "行，没有与之相匹配的单位编号" ;
					}else{
						flag = false ;
					}
				 
					String Type = cell[3].getContents() ;
					
					if(Type == null || Type.equals("")){
						return "第" + count + "行，科研机构类别不能为空" ;
					}
					
					for(DiResearchTypeBean diResearchBean : diResearchBeanList){
						if(diResearchBean.getResearchType().equals(Type)){
							Type = diResearchBean.getIndexId() ;
							flag = true  ;
							break ;
						}//if
					}//for
					if(!flag){
						return "第" + count + "行，科研机构类别不存在" ;
					}else{
						flag = false ;
					}
					
					String BuildCon=cell[4].getContents();
//					System.out.println("BuildCon:"+BuildCon);
					
					if(BuildCon == null || BuildCon.equals("")){
						return "第" + count + "行，共建情况不能为空" ;
					}
					
					if(!BuildCon.equals("是") && !BuildCon.equals("否")){
			        	return "第" + count + "行，只能填“是”或者“否”" ;
//			        	System.out.println("flag:"+flag);
			        }
					else if(BuildCon.equals("是") || BuildCon.equals("否")){
						flag=true;
					}
					if(flag){
								
						if(BuildCon.equals("是")){	buildCondi=true;}
					    else if (BuildCon.equals("否")){buildCondi=false;}
						flag=false;
					}

					String BiOpen=cell[5].getContents();
//					System.out.println("BiOpen:"+BiOpen);
					
					if(BiOpen == null || BiOpen.equals("")){
						return "第" + count + "行，共建情况不能为空" ;
			        }
					
					if(!BiOpen.equals("是") && !BiOpen.equals("否")){
			        	return "第" + count + "行，只能填“是”或者“否”" ;
			        }else if(BiOpen.equals("是") || BiOpen.equals("否")){
			        	flag=true;
			        }
					
					if(flag)
			        {
			        	if(BiOpen.equals("是")){	biOpen=true;}
			        	else if (BiOpen.equals("否")){biOpen=false;}
			        	flag=false;
			        }
					
					String OpenCondition=cell[6].getContents();
					
					if(OpenCondition ==null || OpenCondition.equals("")){
						return "第" + count + "行，对本科生开放情况不能为空" ;
					}
					
					if(OpenCondition.length()>1000){
						return "第" + count + "行，对本科生开放情况字数不能超过500！" ;
					}
					
					String TeaUnit=cell[7].getContents();
					String UnitID=cell[8].getContents();
					
					if(TeaUnit == null || TeaUnit.equals("")){
						return "第" + count + "行，所属教学单位不能为空" ;
					}
					
					if(UnitID == null || UnitID.equals("")){
						return "第" + count + "行，所属教学单位编号不能为空" ;
					}
					
					if(UnitID.length() > 50){
						return "第" + count + "行，所属教学单位编号字数不超过50个数字或字母" ;
					}
					
					for(DiDepartmentBean diDepartBean : diDepartBeanList){
						if(diDepartBean.getUnitId().equals(UnitID)){
							if(diDepartBean.getUnitName().equals(TeaUnit)){
								flag = true ;
								break ;
							}else{
								return "第" + count + "行，所属教学单位与单位编号不对应" ;
							}
						}//if
					}//for
					
					if(!flag){
						return "第" + count + "行，没有与之相匹配的单位编号" ;
					}else{
						flag = false ;
					}
					
					String BeginYearStr=cell[9].getContents();
//					System.out.println(BeginYearStr);
					
					if(BeginYearStr == null || BeginYearStr.equals("")){
						return "第" + count + "行，年份不能为空" ;
					}
					
					if(!DateUtil.isNumeric(BeginYearStr))
					{
						return "第" + count + "行，年份只能为数字" ;
					} 
					if (BeginYearStr.length() >5){
						return "第" + count + "行，年份只能为4位" ;
					}
					
					String HouseArea=cell[10].getContents();
					
					if(HouseArea == null|| HouseArea.equals("")){
						return "第" + count + "行，用房面积不能为空" ;
					}
					
					if(!DateUtil.isDouble(HouseArea)){
						return "第" + count + "行，用房面积只能为保留两位的整型数" ;
					}
					String  note=cell[11].getContents();
				 
				
				
				count++ ;
				
				Date BeginYear=TimeUtil.changeDateY(BeginYearStr);
//				System.out.println(BeginYear);
				double houseArea=DateUtil.doubleTwo(HouseArea);
				t151Bean.setBeginYear(TimeUtil.changeDateY(BeginYearStr));
//				System.out.println("BeginYear:"+t151Bean.getBeginYear());
				t151Bean.setBiOpen(biOpen);
				t151Bean.setBuildCondition(buildCondi);
				t151Bean.setHouseArea(houseArea);
				t151Bean.setNote(note);
				t151Bean.setOpenCondition(OpenCondition);
				t151Bean.setResInsID(ResInsID);
				t151Bean.setResInsName(ResInsName);
				t151Bean.setTeaUnit(TeaUnit);
				t151Bean.setTime(new Date());
				t151Bean.setType(Type);
				t151Bean.setUnitID(UnitID);
				list.add(t151Bean);
							
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

}
