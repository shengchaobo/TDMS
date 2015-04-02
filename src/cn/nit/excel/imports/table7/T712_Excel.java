package cn.nit.excel.imports.table7;


import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.nit.bean.UserinfoBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.table4.T411_Bean;
import cn.nit.bean.table7.T712_Bean;
import cn.nit.constants.Constants;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table4.T411_Service;
import cn.nit.service.table7.T712_Service;
import cn.nit.util.TimeUtil;

import jxl.Cell;

public class T712_Excel {
	
	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request,String selectYear){
		
		if((cellList == null) || (cellList.size() < 2)){
			return "数据不标准，请重新提交" ;
		}
		
		int  count=1;
		boolean flag=false;
	    List<T712_Bean> list=new LinkedList<T712_Bean>();
	
	    DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		
		T411_Service t411_Ser=new T411_Service();
		List<T411_Bean> t411_BeanList = t411_Ser.getList();
		
        for(Cell[] cell: cellList){
        	T712_Bean T712_Bean=new T712_Bean();
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
								return "第" + count + "行，所属单位与所属单位号不对应" ;
							}
						}//if
					}//for
					
					if(!flag){
						return "第" + count + "行，没有与之相匹配的单位号" ;
					}else{
						flag = false ;
					}
					String name = cell[3].getContents().trim() ;
					String teaId = cell[4].getContents().trim() ;
					if(name == null || name.equals("")){
						return "第" + count + "行，姓名不能为空" ;
					}
					if(name.length()>50){
						return "第" + count + "行，姓名不能超过50个字符" ;
					}
					if((teaId == null) || teaId.equals("")){
						return "第" + count + "行，教工号不能为空" ;
					}
					if(teaId.length()>50){
						return "第" + count + "行，教工号不能超过50个字符" ;
					}
					
					for(T411_Bean t411_Bean : t411_BeanList){
						if(t411_Bean.getTeaId().equals(teaId)){
							if(t411_Bean.getTeaName().equals(name)){
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
					String perName = cell[5].getContents().trim();
					if((perName == null) || perName.equals("")){
						return "第" + count + "行，论文名称不能为空" ;
					}
					if(perName.length()>200){
						return "第" + count + "行，论文名称不能超过200个字符" ; 
					}
					String perType = cell[6].getContents().trim() ;
					if((perType == null) || perType.equals("")){
						return "第" + count + "行，归口类型不能为空" ;
					}
					if(!perType.equals("教学研究") && !perType.equals("教学管理")){
						return "第" + count + "行，归口类型格式有误，只能填写“教学研究”或“教学管理”";
					}
					String FSub = cell[7].getContents().trim();
					if(FSub == null || FSub.equals("")){
						return "第" + count + "行，所属一级学科不能为空" ;
					}
					if(!FSub.equals("01哲学") && !FSub.equals("02经济学")&& !FSub.equals("03法学")&& !FSub.equals("04教育学")
					   && !FSub.equals("05文学")&& !FSub.equals("06历史学")&& !FSub.equals("07理学")&& !FSub.equals("08工学")
					   && !FSub.equals("09农学")&& !FSub.equals("10医学")&& !FSub.equals("11军事学")&& !FSub.equals("12管理学")&& !FSub.equals("13艺术学")){
						return "第" + count + "行，所属一级学科格式有误，只能填写“01哲学”或“02经济学”或“03法学”或“04教育学”或“05文学”或“06历史学”或“07理学”或“08工学”或“09农学”或“10医学”或“11军事学”或“12管理学”或“13艺术学”";
					}
					String JnName = cell[8].getContents().trim();
					if(JnName == null || JnName.equals("")){
						return "第" + count + "行，刊物/会议名称不能为空" ;
					}
					if(JnName.length()>200){
						return "第" + count + "行，刊物/会议名称不能超过200个字符" ; 
					}
					
					String JnID = cell[9].getContents().trim();
					if(JnID == null || JnID.equals("")){
						return "第" + count + "行，刊号不能为空" ;
					}
					if(JnID.length()>50){
						return "第" + count + "行，刊号不能超过50个字符" ;
					}
					String JnTime = cell[10].getContents().trim();
					if(JnTime == null || JnTime.equals("")){
						return "第" + count + "行，刊期/日期不能为空" ;
					}
					if(!TimeUtil.judgeFormatYM(JnTime)){
						return "第" + count + "行，刊期/日期格式不正确，格式为：2012-09" ;
					}
					String PWNum = cell[11].getContents().trim();
					if(PWNum == null || PWNum.equals("")){
						return "第" + count + "行，论文字数不能为空" ;
					}
					if(!this.isNumeric(PWNum)){
						return "第" + count + "行，论文字数只能填数字" ;
					}
					String CfLevel = cell[12].getContents().trim();
					if(CfLevel == null || CfLevel.equals("")){
						return "第" + count + "行，认定等级不能为空" ;
					}
					if(CfLevel.length()>50){
						return "第" + count + "行，认定等级不能超过50个字符" ;
					}
					String joinTn = cell[13].getContents().trim();
					if(joinTn == null || joinTn.equals("")){
						return "第" + count + "行，合作教师人数不能为空" ;
					}
					if(!this.isNumeric(joinTn)){
						return "第" + count + "行，合作教师人数只能填数字" ;
					}
					
					String otherJTI = cell[14].getContents().trim();
					if(otherJTI == null || otherJTI.equals("")){
						return "第" + count + "行，其他合作教师不能为空" ;
					}
					if(otherJTI.length()>300){
						return "第" + count + "行，其他合作教师不能超过300个字符" ; 
					}
					
					String note = cell[15].getContents().trim();
					//插入教学单位
					UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
					String fillUnitID = bean.getUnitID();
					T712_Bean.setFillUnitID(fillUnitID);
					T712_Bean.setTeaUnit(unit);
					T712_Bean.setUnitID(unitId);
					T712_Bean.setName(name);
					T712_Bean.setTeaID(teaId);
					T712_Bean.setPaperName(perName);
					T712_Bean.setPaperType(perType);
					T712_Bean.setFirstSubject(FSub);
					T712_Bean.setJonalName(JnName);
					T712_Bean.setJonalID(JnID);
					T712_Bean.setJonalTime(TimeUtil.changeDateYM(JnTime));
					T712_Bean.setPaperWordNum(Integer.parseInt(PWNum));
					T712_Bean.setConfirmLevel(CfLevel);
					T712_Bean.setJoinTeaNum(Integer.parseInt(joinTn));
					T712_Bean.setOtherJoinTeaInfo(otherJTI);
					T712_Bean.setFillUnitID(fillUnitID);
					T712_Bean.setCheckState(Constants.WAIT_CHECK);
					T712_Bean.setTime(TimeUtil.changeDateY(selectYear));
					T712_Bean.setNote(note);
					list.add(T712_Bean);
		    	   
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
        	
        }
			
        flag = false ;
		T712_Service t712_Sr=new T712_Service();
		flag=t712_Sr.batchInsert(list);
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
