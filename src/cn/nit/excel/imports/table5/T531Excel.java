package cn.nit.excel.imports.table5;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.Cell;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.di.DiMajorTwoBean;
import cn.nit.bean.table5.T531Bean;

import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.di.DiMajorTwoService;
import cn.nit.service.table5.T531Service;
import cn.nit.util.TimeUtil;


public class T531Excel {
	
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
		
		int count = 1 ;
		
		boolean flag = false ;
//		boolean biOpen=false;
//		boolean buildCondi=false;
		Date Time = new Date();
		List<T531Bean> list = new LinkedList<T531Bean>() ;
//		UserRoleBean userinfo = (UserRoleBean)request.getSession().getAttribute("userinfo") ;
//		DiDepartmentService diDepartSer = new DiDepartmentService() ;
//		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
//		DiMajorTwoService diMajorTwoSer=new DiMajorTwoService();
//		List<DiMajorTwoBean> diMajorTwoBeanList=diMajorTwoSer.getList();
		
		for(Cell[] cell : cellList){
			T531Bean t531Bean = new  T531Bean();
			int n=cellList.indexOf(cell);
			if(count<4){
				count++;
				continue;
			}
			else{
				
				
			  try{
				 
				 String Name = cell[1].getContents() ;
				 
				 if(Name == null || Name.equals("")){
						return "第" + count + "行，名称不能为空" ;
					}
				 if(Name.length()>1000){
					 return "第" + count + "行，名称不能超过500个字" ;
				 }
				 
					String Type = cell[2].getContents() ;
					
					if(Type == null || Type.equals("")){
						return "第" + count + "行，类型不能为空" ;
					}
					
					if(Type.length()>50){
						return "第" + count + "行，类型不能超过25个汉字" ;
					}
					
					String ItemLevel = cell[3].getContents() ;
					
					if(ItemLevel == null || ItemLevel.equals("")){
						return "第" + count + "行，级别不能为空" ;
					}
		
					String buildTime = cell[4].getContents() ;
					
					if(buildTime == null || buildTime.equals("")){
						return "第" + count + "行，设立时间不能为空" ;
					}
					if(!TimeUtil.judgeFormat3(buildTime)){
						return "第" + count + "行，时间格式错误，格式为：：2014" ;
					}
					
					String TeaUnit = cell[5].getContents();
					
					if(TeaUnit == null || TeaUnit.equals("")){
						return "第" + count + "行，所属教学单位不能为空" ;
					}
					
					String JoinStuNum=cell[6].getContents();
					
					if(JoinStuNum == null || JoinStuNum.equals("")){
						JoinStuNum ="0" ;
					}
					
					if (!this.isNumeric(JoinStuNum)){
						return "第" + count + "行，参与人数只能填数字" ;
					}
					
				
				count++ ;
				Date BuildTime=TimeUtil.changeDateY(buildTime);
				t531Bean.setBuildTime(BuildTime);
				t531Bean.setItemLevel(ItemLevel);
				t531Bean.setJoinStuNum(Integer.parseInt(JoinStuNum));
				t531Bean.setName(Name);
				t531Bean.setTeaUnit(TeaUnit);
				t531Bean.setTime(Time);
				t531Bean.setType(Type);
				list.add(t531Bean);
				
							
			}
			catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
	     }
		}
		
		flag = false ;
		T531Service t531Ser = new T531Service() ;
		flag = t531Ser.batchInsert(list) ;
		
		if(flag){
			return "数据导入成功" ;
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
	
	
	/**将带%的字符串转换成double类型*/
	public double toDouble(String str){
		String dou=str.substring(0,str.length()-1);
	    double num1=Double.parseDouble(dou);
	    double num2=num1/100;
	    double num =(double)Math.round(num1)/100;
		return num;
	}
	

}
