package cn.nit.excel.imports.table5;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.nit.bean.di.DiAwardLevelBean;
import cn.nit.bean.table5.T553_Bean;
import cn.nit.constants.Constants;
import cn.nit.service.di.DiAwardLevelService;
import cn.nit.service.table5.T553_Service;
import cn.nit.util.TimeUtil;

import jxl.Cell;

public class T553_Excel {
	
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
	    List<T553_Bean> list=new LinkedList<T553_Bean>();
	
		DiAwardLevelService diAwardLeSr = new DiAwardLevelService();
		List<DiAwardLevelBean> diAwardLeBeanList = diAwardLeSr.getList() ;
		
		System.out.println(cellList.size());
		
		
		for(Cell[] cell: cellList){
			T553_Bean T553_Bean=new T553_Bean();
			try {
				
				if(count<4){
					count++;
					continue;
				}
				
				String awardname = cell[1].getContents() ;
				if(awardname == null || awardname.equals("")){
					return "第" + count + "行，获奖名称不能为空" ;
				}
				if(awardname.length()>100){
					return "第" + count + "行，获奖名称字数不能超过50字" ;
				}
				String awardstuname = cell[2].getContents() ;
				if((awardstuname == null) || awardstuname.equals("")){
					return "第" + count + "行，获奖学生姓名不能为空" ;
				}
				if(awardstuname.length()>50){
					return "第" + count + "行，获奖学生姓名字数不能超过50字" ;
				}
				
				String stuID = cell[3].getContents() ;
				if((stuID == null) || stuID.equals("")){
					return "第" + count + "行，学号不能为空" ;
				}
				if(stuID.length()>50){
					return "第" + count + "行，学号字数不能超过50字" ;
				}
				
				String teaUnit = cell[4].getContents() ;
				if((teaUnit == null) || teaUnit.equals("")){
					return "第" + count + "行，所在教学单位不能为空" ;
				}	
				if(teaUnit.length()>200){
					return "第" + count + "行，所在教学单位字数不能超过100字" ;
				}
				
				String fromClass = cell[5].getContents() ;
				if((fromClass == null) || fromClass.equals("")){
					return "第" + count + "行，所在班级不能为空" ;
				}
				if(fromClass.length()>100){
					return "第" + count + "行，所在班级字数不能超过50字" ;
				}
				
				String ardLevel = cell[6].getContents() ;
				if((ardLevel == null) || ardLevel.equals("")){
					return "第" + count + "行，级别不能为空" ;
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
				String awardTime = cell[7].getContents();
				if(awardTime == null || awardTime.equals("")){
					return "第" + count + "行，获奖时间不能为空" ;
				}
				if(!TimeUtil.judgeFormat3(awardTime)){
					return "第" + count + "行，获奖时间格式不正确" ;
				}
				count++ ;

				String fillUnitID = null;
				
				T553_Bean.setAwardName(awardname);
				T553_Bean.setAwardStuName(awardstuname);
				T553_Bean.setStuID(stuID);
				T553_Bean.setTeaUnit(teaUnit);
				T553_Bean.setFromClass(fromClass);
				T553_Bean.setAwardLevel(ardLevelID);
				T553_Bean.setFillUnitID(fillUnitID);
				T553_Bean.setAwardTime(TimeUtil.changeDateY(awardTime));
				T553_Bean.setTime(TimeUtil.changeDateY(selectYear));
				T553_Bean.setCheckState(Constants.WAIT_CHECK);
				list.add(T553_Bean);
				
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
			
			
		}
		flag = false ;
		T553_Service t553_Sr=new T553_Service();
		flag=t553_Sr.batchInsert(list);
		if(flag){
			return null;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
	}
}
