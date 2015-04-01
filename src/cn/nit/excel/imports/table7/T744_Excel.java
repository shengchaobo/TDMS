package cn.nit.excel.imports.table7;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.nit.bean.UserinfoBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.di.DiMajorTwoBean;
import cn.nit.bean.table7.T744_Bean;
import cn.nit.constants.Constants;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.di.DiMajorTwoService;
import cn.nit.service.table7.T744_Service;
import cn.nit.util.TimeUtil;

import jxl.Cell;

public class T744_Excel {
	
	
	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request, String selectYear){
		
		if((cellList == null) || (cellList.size() < 2)){
			return "数据不标准，请重新提交" ;
		}
		
		int count = 1 ;
		
		boolean flag = false ;
		List<T744_Bean> list = new LinkedList<T744_Bean>() ;
		
		DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		
		DiMajorTwoService diMajorSer = new DiMajorTwoService() ;
		List<DiMajorTwoBean> diMajorBeanList = diMajorSer.getList() ;
		
		for(Cell[] cell : cellList){
			T744_Bean T744_Bean =new T744_Bean();
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
					return "第" + count + "行，所属单位号不能为空" ;
				}			
				if(unitId.length()>50){
					return "第" + count + "行，所属单位号不能超过50个字符" ;
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

				String major = cell[3].getContents().trim() ;
				String majorId = cell[4].getContents().trim() ;
				
				if(major == null || major.equals("")){
					return "第" + count + "行，专业名称不能为空" ;
				}
				if(major.length()>100){
					return "第" + count + "行，专业名称不能超过100个字符" ;
				}
				if(majorId == null || majorId.equals("")){
					return "第" + count + "行，专业代码不能为空" ;
				}			
				if(majorId.length()>50){
					return "第" + count + "行，专业代码不能超过50个字符" ;
				}
				for(DiMajorTwoBean diMajorBean : diMajorBeanList){
					if(diMajorBean.getMajorNum().equals(majorId)){
						if(diMajorBean.getMajorName().equals(major)){
							flag = true ;
							break ;
						}else{
							return "第" + count + "行，专业名称与专业代码不对应" ;
						}
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，没有与之相匹配的专业代码" ;
				}else{
					flag = false ;
				}
				
				String deType = cell[5].getContents().trim() ;
				if((deType == null) || deType.equals("")){
					return "第" + count + "行，学位授予门类不能为空" ;
				}
				if(!deType.equals("01哲学") && !deType.equals("02经济学")&& !deType.equals("03法学")&& !deType.equals("04教育学")
						   && !deType.equals("05文学")&& !deType.equals("06历史学")&& !deType.equals("07理学")&& !deType.equals("08工学")
						   && !deType.equals("09农学")&& !deType.equals("10医学")&& !deType.equals("11军事学")&& !deType.equals("12管理学")&& !deType.equals("13艺术学")){
							return "第" + count + "行，学位授予门类格式有误，只能填写“01哲学”或“02经济学”或“03法学”或“04教育学”或“05文学”或“06历史学”或“07理学”或“08工学”或“09农学”或“10医学”或“11军事学”或“12管理学”或“13艺术学”";
						}
				String leaderName = cell[6].getContents().trim() ;
				if((leaderName == null) || leaderName.equals("")){
					return "第" + count + "行，负责人姓名不能为空" ;
				}
				if(leaderName.length()>50){
					return "第" + count + "行，负责人姓名不能超过50个字符" ;
				}
				String teaId = cell[7].getContents().trim() ;
				if((teaId == null) || teaId.equals("")){
					return "第" + count + "行，教工号不能为空" ;
				}
				if(teaId.length()>50){
					return "第" + count + "行，教工号不能超过50个字符" ;
				}
				String setYear = cell[8].getContents().trim() ;
				if((setYear == null) || setYear.equals("")){
					return "第" + count + "行，设置年份不能为空" ;
				}
				if(!TimeUtil.judgeFormat3(setYear)){
					return "第" + count + "行，设置年份格式错误！" ;
				}
				String assYear = cell[9].getContents().trim() ;
				if((assYear == null) || assYear.equals("")){
					return "第" + count + "行，评估年份不能为空" ;
				}
				if(!TimeUtil.judgeFormat3(assYear)){
					return "第" + count + "行，评估年份格式错误！" ;
				}
				String assResult = cell[10].getContents().trim() ;
				if(assResult == null || assResult.equals("")){
					return "第" + count + "行，评估结果不能为空" ;
				}
				if(!assResult.equals("校级优秀") && !assResult.equals("校级良好")&& !assResult.equals("校级合格")&& !assResult.equals("校级不合格")){
					return "第" + count + "行，评估结果只能是“校级优秀”或“校级良好”或“校级合格”或“校级不合格”" ;
				}
				String appID = cell[11].getContents().trim() ;
				if(appID == null || appID.equals("")){
					return "第" + count + "行，批文号不能为空" ;
				}
				if(appID.length()>100){
					return "第" + count + "行，批文号不能超过100个字符" ;
				}
				String note = cell[12].getContents().trim();
					
				count++ ;
				String fillUnitID=null;	
				T744_Bean.setTeaUnit(unit);
				T744_Bean.setUnitID(unitId);
				T744_Bean.setMajorName(major);
				T744_Bean.setMajorID(majorId);
				T744_Bean.setDegreeType(deType);
				T744_Bean.setLeaderName(leaderName);
				T744_Bean.setTeaID(teaId);
				T744_Bean.setSetYear(setYear);
				T744_Bean.setAssessYear(assYear);
				T744_Bean.setAssessResult(assResult);
				T744_Bean.setAppvlID(appID);
				T744_Bean.setFillUnitID(fillUnitID);
				T744_Bean.setCheckState(Constants.WAIT_CHECK);
				T744_Bean.setTime(TimeUtil.changeDateY(selectYear));
				T744_Bean.setNote(note);
				
				list.add(T744_Bean);
					
				
				
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
			
		}
		flag = false ;
		T744_Service t744_Sr=new T744_Service();
		flag=t744_Sr.batchInsert(list);
		if(flag){
			return null ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
	}

}
