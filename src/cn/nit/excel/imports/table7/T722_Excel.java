package cn.nit.excel.imports.table7;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.nit.bean.UserinfoBean;
import cn.nit.bean.di.DiAwardLevelBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.table4.T411_Bean;
import cn.nit.bean.table7.T722_Bean;
import cn.nit.constants.Constants;
import cn.nit.service.di.DiAwardLevelService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table4.T411_Service;
import cn.nit.service.table7.T722_Service;
import cn.nit.util.TimeUtil;

import jxl.Cell;

public class T722_Excel {
	
	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request,String selectYear){
		
			if((cellList == null) || (cellList.size() < 2)){
				return "数据不标准，请重新提交" ;
			}
			
			int  count=1;
			boolean flag=false;
		    List<T722_Bean> list=new LinkedList<T722_Bean>();
		
		    DiDepartmentService diDepartSer = new DiDepartmentService() ;
			List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
			
			DiAwardLevelService diAwardLeSr = new DiAwardLevelService();
			List<DiAwardLevelBean> diAwardLeBeanList = diAwardLeSr.getList() ;
			
			T411_Service t411_Ser=new T411_Service();
			List<T411_Bean> t411_BeanList = t411_Ser.getList();
			
			//System.out.println(cellList.size());
			
			for(Cell[] cell: cellList){
				T722_Bean T722_Bean=new T722_Bean();
				try {
					if(count<4){
						count++;
						continue;
					}
					String ardname = cell[1].getContents().trim() ;
					if(ardname == null || ardname.equals("")){
						return "第" + count + "行，奖励名称不能为空" ;
					}
					if(ardname.length()>200){
						return "第" + count + "行，奖励名称不能超过200个字符" ; 
					}
					String unit = cell[2].getContents().trim() ;
					String unitId = cell[3].getContents().trim() ;
					
					if(unit == null || unit.equals("")){
						return "第" + count + "行，所属教学单位不能为空" ;
					}
					if(unit.length()>200){
						return "第" + count + "行，所属教学单位不能超过200个字符" ; 
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
					
					String leader = cell[4].getContents().trim() ;
					String teaId = cell[5].getContents().trim() ;
					if((leader == null) || leader.equals("")){
						return "第" + count + "行，负责人不能为空" ;
					}
					if(leader.length()>50){
						return "第" + count + "行，负责人不能超过50个字符" ;
					}
					if((teaId == null) || teaId.equals("")){
						return "第" + count + "行，教工号不能为空" ;
					}
					if(teaId.length()>50){
						return "第" + count + "行，教工号不能超过50个字符" ;
					}
					for(T411_Bean t411_Bean : t411_BeanList){
						if(t411_Bean.getTeaId().equals(teaId)){
							if(t411_Bean.getTeaName().equals(leader)){
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
					
					
					String OJTNum = cell[6].getContents().trim() ;
					if((OJTNum == null) || OJTNum.equals("")){
						return "第" + count + "行，其他参与教师人数不能为空" ;
					}
					if(!this.isNumeric(OJTNum)){
						return "第" + count + "行，其他参与教师人数只能填数字" ;
					}
					String otherTea = cell[7].getContents().trim() ;
					if((otherTea == null) || otherTea.equals("")){
						return "第" + count + "行，其他教师不能为空" ;
					}
					if(otherTea.length()>300){
						return "第" + count + "行，其他合作教师不能超过300个字符" ; 
					}
					
					String ardLevel = cell[8].getContents().trim() ;
					if((ardLevel == null) || ardLevel.equals("")){
						return "第" + count + "行，级别不能为空" ;
					}
					if(ardLevel.length()>5){
						return "第" + count + "行，级别不能超过5个字符" ; 
					}
					String ardLevelID=null;
					for(DiAwardLevelBean ardlevelBean : diAwardLeBeanList){
						if(ardlevelBean.getAwardLevel().equals(ardLevel)){
							ardLevelID=ardlevelBean.getIndexId();
							flag=true;
							break;
						}			
					}
					if(!flag){
						return "第" + count + "行，级别类别不存在" ;
					}else{
						flag = false ;
					}
					
					String ardTime = cell[9].getContents().trim();
					if(ardTime == null || ardTime.equals("")){
						return "第" + count + "行，获奖时间不能为空" ;
					}
					if(!TimeUtil.judgeFormatYM(ardTime)){
						return "第" + count + "行，获准时间格式不正确，格式为：2012-09" ;
					}
					String ardfUnit = cell[10].getContents().trim();
					if(ardfUnit == null || ardfUnit.equals("")){
						return "第" + count + "行，授予单位不能为空" ;
					}
					if(ardfUnit.length()>200){
						return "第" + count + "行，授予单位不能超过200个字符" ; 
					}
					
					String appID = cell[11].getContents().trim();
					if(appID == null || appID.equals("")){
						return "第" + count + "行，批文号不能为空" ;
					}
					if(appID.length()>100){
						return "第" + count + "行，批文号不能超过100个字符" ; 
					}
					
					String note = cell[12].getContents().trim();
					
					count++ ;
					String fillUnitID=null;	
					T722_Bean.setAwardName(ardname);
					T722_Bean.setTeaUnit(unit);
					T722_Bean.setUnitID(unitId);
					T722_Bean.setLeader(leader);
					T722_Bean.setTeaID(teaId);
					T722_Bean.setOtherTeaNum(Integer.parseInt(OJTNum));
					T722_Bean.setOtherTea(otherTea);
					T722_Bean.setAwardLevel(ardLevelID);
					T722_Bean.setAwardTime(TimeUtil.changeDateYM(ardTime));
					T722_Bean.setAwardFromUnit(ardfUnit);
					T722_Bean.setAppvlID(appID);
//					T722_Bean.setFillUnitID(fillUnitID);
					T722_Bean.setCheckState(Constants.WAIT_CHECK);
					T722_Bean.setTime(TimeUtil.changeDateY(selectYear));
					T722_Bean.setNote(note);
					
					list.add(T722_Bean);
					
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace() ;
					return "上传文件不合法！！！" ;
				}
				
			}
			 flag = false ;
				T722_Service t722_Sr=new T722_Service();
				flag=t722_Sr.batchInsert(list);
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
