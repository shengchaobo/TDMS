package cn.nit.excel.imports.table5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.Cell;
import cn.nit.bean.di.DiAwardLevelBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.table4.T411_Bean;
import cn.nit.bean.table5.T521_Bean;
import cn.nit.constants.Constants;
import cn.nit.service.di.DiAwardLevelService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table4.T411_Service;
import cn.nit.service.table5.T521Service;

import cn.nit.util.TimeUtil;

public class T521Excel {
	
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
		List<T521_Bean> list = new LinkedList<T521_Bean>() ;
//		UserRoleBean userinfo = (UserRoleBean)request.getSession().getAttribute("userinfo") ;
		DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
	    DiAwardLevelService diAwardSer=new DiAwardLevelService();
	    List<DiAwardLevelBean> diAwardList=diAwardSer.getList();
	    T411_Service t411Ser=new T411_Service();
	    List<T411_Bean> t411List=t411Ser.getList();
		
		for(Cell[] cell : cellList){
			T521_Bean t521Bean = new  T521_Bean();
			int n=cellList.indexOf(cell);
			if(count<4){
				count++;
				continue;
			}
			else{
				
				
			  try{
				 
				 String CSType = cell[1].getContents() ;
				 
				 if(CSType == null || CSType.equals("")){
						return "第" + count + "行，类型不能为空" ;
					}
				 if(CSType.equals("网络课程")){
			
					 return "第" + count + "行，不能填“网络课程”" ;
				 }
				 
					String CSName = cell[2].getContents() ;
					
					if(CSName == null || CSName.equals("")){
						return "第" + count + "行，课程名称不能为空" ;
					}
					
					if(CSName.length()>100){
						return "第" + count + "行，课程名称长度超过25个汉字" ;
					}
					
					String CSID = cell[3].getContents() ;
					
					if(CSID == null || CSID.equals("")){
						return "第" + count + "行，课程编号不能为空" ;
					}
		
					String CSLevel = cell[4].getContents() ;
					
					if(CSLevel == null || CSLevel.equals("")){
						return "第" + count + "行，级别不能为空" ;
					}
					for(DiAwardLevelBean diAwardBean:diAwardList){
						if(diAwardBean.getAwardLevel().equals(CSLevel)){
							CSLevel=diAwardBean.getIndexId();
							flag=true;
							break;
						}
					}
					if(!flag){
						return "第" + count + "行，没有该级别" ;
					}
					
					String Leader = cell[5].getContents() ;
					String TeaID = cell[6].getContents() ;
					
					if(Leader == null || Leader.equals("")){
						return "第" + count + "行，负责人不能为空" ;
					}
					
					if(TeaID == null || TeaID.equals("")){
						return "第" + count + "行，教工号不能为空" ;
					}
					
					if(TeaID.length() > 50){
						return "第" + count + "行，教工号字数不超过50个数字或字母" ;
					}
					
					for(T411_Bean t411Bean : t411List){
						if(t411Bean.getTeaId().equals(TeaID)){
							if(t411Bean.getTeaName().equals(Leader)){
								flag = true ;
								break ;
							}else{
								return "第" + count + "行，负责人与教工号不对应" ;
							}
						}//if
					}//for
					
					if(!flag){
						return "第" + count + "行，没有与之相匹配的教工号" ;
					}else{
						flag = false ;
					}
					
					String JoinTeaNum = cell[7].getContents();
					
					if(JoinTeaNum == null || JoinTeaNum.equals("")){
						JoinTeaNum="0";
					}
					if(!this.isNumeric(JoinTeaNum)){
						return "第" + count + "行，参与教工人数只能填数字" ;
					}
					
					String OtherTeaNum=cell[8].getContents();
					System.out.println("OtherTeaNum:"+OtherTeaNum);
					
					if(OtherTeaNum != null && OtherTeaNum.length()>200){
						return "第" + count + "行，其他参与教师字数不能超过100字" ;
					}
					
					String CSUrl=cell[9].getContents();
					
					String AppvlTime=cell[10].getContents();
					
					if(AppvlTime ==null || AppvlTime.equals("")){
						return "第" + count + "行，获准时间不能为空" ;
					}
					if(!this.judgeFormat1(AppvlTime)){
						return "第" + count + "行，获准时间格式不正确，格式为：2012-09" ;
					}
					
					String ReceptTime=cell[11].getContents();
					if(ReceptTime ==null || ReceptTime.equals("")){
						return "第" + count + "行，验收时间不能为空" ;
					}
					if(!this.judgeFormat1(ReceptTime)){
						return "第" + count + "行，验收时间格式不正确，格式为：2012-09" ;
					}
					
					String FromTeaUnit = cell[12].getContents() ;
					String UnitID = cell[13].getContents() ;
					
					if(FromTeaUnit == null || FromTeaUnit.equals("")){
						return "第" + count + "行，所属教学单位不能为空" ;
					}
					
					if(UnitID == null || UnitID.equals("")){
						return "第" + count + "行，单位号不能为空" ;
					}
					
					if(UnitID.length() > 50){
						return "第" + count + "行，单位号字数不超过50个数字或字母" ;
					}
					
					for(DiDepartmentBean diDepartBean : diDepartBeanList){
						if(diDepartBean.getUnitId().equals(UnitID)){
							if(diDepartBean.getUnitName().equals(FromTeaUnit)){
								flag = true ;
								break ;
							}else{
								return "第" + count + "行，所属教学单位与单位号不对应" ;
							}
						}//if
					}//for
					
					if(!flag){
						return "第" + count + "行，没有与之相匹配的单位号" ;
					}else{
						flag = false ;
					}
					
					String AppvlID=cell[14].getContents();
					
					if(AppvlID == null || AppvlID.equals("")){
						return "第" + count + "行，批文号" ;
					}
					
					String Note=cell[15].getContents();
					
					if(Note != null && Note.length()>1000){
						return "第" + count + "行，备注不能超过500字" ;
					}
					
					
				count++ ;
				
				t521Bean.setAppvlID(AppvlID);
				t521Bean.setAppvlTime(TimeUtil.changeDateYM(AppvlTime));
				t521Bean.setCSID(CSID);
				t521Bean.setCSLevel(CSLevel);
				t521Bean.setCSName(CSName);
				t521Bean.setCSType(CSType);
				t521Bean.setCSUrl(CSUrl);
				t521Bean.setJoinTeaNum(Integer.parseInt(JoinTeaNum));
				t521Bean.setLeader(Leader);
				t521Bean.setNote(Note);
				t521Bean.setOtherTea(OtherTeaNum);
				t521Bean.setReceptTime(TimeUtil.changeDateYM(ReceptTime));
				t521Bean.setTeaID(TeaID);
				t521Bean.setTeaUnit(FromTeaUnit);
				t521Bean.setCheckState(Constants.WAIT_CHECK);
				t521Bean.setTime(Time);
				t521Bean.setNote(Note);
				t521Bean.setUnitID(UnitID);
			
				list.add(t521Bean);
				
							
			}
			catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
	     }
		}
		
		flag = false ;
		T521Service t521Ser = new T521Service() ;
		flag = t521Ser.batchInsert(list) ;
		
		if(flag){
			return null;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
		
	}
	
	/**判断字符串格式是否为2013-02*/
	public static boolean judgeFormat1(String dataString){
		boolean flag=false;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM") ;
		Date date=null;
		try{
			date = sf.parse(dataString) ;
			flag = true;
		}catch(ParseException e){
			flag=false;
		}
		return flag;
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
