package cn.nit.excel.imports.table7;


import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.nit.bean.UserinfoBean;
import cn.nit.bean.di.DiAwardLevelBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.table4.T411_Bean;
import cn.nit.bean.table7.T711_Bean;
import cn.nit.constants.Constants;
import cn.nit.service.di.DiAwardLevelService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table4.T411_Service;
import cn.nit.service.table7.T711_Service;
import cn.nit.util.TimeUtil;

import jxl.Cell;

public class T711_Excel {
	

	/**
	 * 批量导入
	 * @param cellList {@link java.util.List<{@link jxl.Cell}>}  Cell是数组
	 * @param request  {@link javax.servlet.http.HttpServletRequest}
	 * @return
	 */
	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request,String selectYear){
	
		if((cellList == null) || (cellList.size() < 2)){
			return "数据不标准，请重新提交" ;
		}
		
		int  count=1;
		
		boolean flag=false;
	    List<T711_Bean> list=new LinkedList<T711_Bean>();	
	    DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		
		DiAwardLevelService diAwardLeSr = new DiAwardLevelService();
		List<DiAwardLevelBean> diAwardLeBeanList = diAwardLeSr.getList() ;
		
		 T411_Service t411Ser=new T411_Service();
		 List<T411_Bean> t411List=t411Ser.getList();
		
		System.out.println(cellList.size());
		
		for(Cell[] cell: cellList){		
			T711_Bean T711_Bean=new T711_Bean();
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
				String teaId = cell[4].getContents() ;
				
				if(name == null || name.equals("")){
					return "第" + count + "行，名称不能为空" ;
				}
				
				if(teaId == null || teaId.equals("")){
					return "第" + count + "行，教工号不能为空" ;
				}
				
				if(teaId.length() > 50){
					return "第" + count + "行，教工号字数不超过50个数字或字母" ;
				}
				
				for(T411_Bean t411Bean : t411List){
					if(t411Bean.getTeaId().equals(teaId)){
						if(t411Bean.getTeaName().equals(name)){
							flag = true ;
							break ;
						}else{
							return "第" + count + "行，名称与教工号不对应" ;
						}
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，没有与之相匹配的教工号" ;
				}else{
					flag = false ;
				}
//				String name = cell[3].getContents() ;
//				if(name == null || name.equals("")){
//					return "第" + count + "行，名称不能为空" ;
//				}
//				if(name.length()>50){
//					return "第" + count + "行，名称不能超过50个字符" ;
//				}
//				String teaId = cell[4].getContents() ;
//				if((teaId == null) || teaId.equals("")){
//					return "第" + count + "行，教工号不能为空" ;
//				}
//				if(teaId.length()>50){
//					return "第" + count + "行，教工号不能超过50个字符" ;
//				}
				String ardName = cell[5].getContents() ;
				if((ardName == null) || ardName.equals("")){
					return "第" + count + "行，奖励名称不能为空" ;
				}
				if(ardName.length()>200){
					return "第" + count + "行，奖励名称不能超过200个字符" ; 
				}
				String ardLevel = cell[6].getContents() ;
				if((ardLevel == null) || ardLevel.equals("")){
					return "第" + count + "行，级别不能为空" ;
				}
				if(ardLevel.length()>5){
					return "第" + count + "行，级别不能超过5个字符" ; 
				}
				if(ardLevel.equals("省级")){
					ardLevel = "省部级";
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
				
				String ardRank = cell[7].getContents();
				if(ardRank == null || ardRank.equals("")){
					return "第" + count + "行，等级不能为空" ;
				}
				if(!ardRank.equals("一等奖") && !ardRank.equals("二等奖")&& !ardRank.equals("三等奖")&& !ardRank.equals("优秀奖")&& !ardRank.equals("其他")){
					return "第" + count + "行，等级格式有误，只能填写“一等奖”或“二等奖”或“三等奖”或“优秀奖”或“其他”";
				}
				String ardTime = cell[8].getContents();
				if(ardTime == null || ardTime.equals("")){
					return "第" + count + "行，获奖时间不能为空" ;
				}
				if(!TimeUtil.judgeFormatYM(ardTime)){
					return "第" + count + "行，获准时间格式不正确，格式为：2012-09" ;
				}
				String ardFu = cell[9].getContents();
				if(ardFu == null || ardFu.equals("")){
					return "第" + count + "行，授予单位不能为空" ;
				}
				if(ardFu.length()>200){
					return "第" + count + "行，授予单位不能超过200个字符" ; 
				}
				String appID = cell[10].getContents();
				if(appID == null || appID.equals("")){
					return "第" + count + "行，批文号不能为空" ;
				}
				if(appID.length()>100){
					return "第" + count + "行，批文号不能超过100个字符" ; 
				}
				String joinTn = cell[11].getContents();
				if(joinTn == null || joinTn.equals("")){
					return "第" + count + "行，合作教师人数不能为空" ;
				}
				if(!this.isNumeric(joinTn)){
					return "第" + count + "行，合作教师人数只能填数字" ;
				}
				
				String otherJTI = cell[12].getContents();
				if(otherJTI == null || otherJTI.equals("")){
					return "第" + count + "行，其他合作教师不能为空" ;
				}
				if(otherJTI.length()>300){
					return "第" + count + "行，其他合作教师不能超过300个字符" ; 
				}
				
				String note = cell[13].getContents();
				
				count++ ;
				//插入教学单位
				UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
				String fillUnitID = bean.getUnitID();
				T711_Bean.setFillUnitID(fillUnitID);
				
				T711_Bean.setTeaUnit(unit);
				T711_Bean.setUnitID(unitId);
				T711_Bean.setName(name);
				T711_Bean.setTeaID(teaId);
				T711_Bean.setAwardName(ardName);
				T711_Bean.setAwardLevel(ardLevelID);
				T711_Bean.setAwardRank(ardRank);
				T711_Bean.setAwardTime7(TimeUtil.changeDateYM(ardTime));
				T711_Bean.setAwardFromUnit(ardFu);
				T711_Bean.setAppvlID(appID);
				T711_Bean.setJoinTeaNum(Integer.parseInt(joinTn));
				T711_Bean.setOtherJoinTeaInfo(otherJTI);
				T711_Bean.setFillUnitID(fillUnitID);
				T711_Bean.setCheckState(Constants.WAIT_CHECK);
				T711_Bean.setTime(TimeUtil.changeDateY(selectYear));
				T711_Bean.setNote(note);
				
				list.add(T711_Bean);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
			
			
		}
		flag = false ;
		T711_Service t711_Sr=new T711_Service();
		flag=t711_Sr.batchInsert(list);
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
