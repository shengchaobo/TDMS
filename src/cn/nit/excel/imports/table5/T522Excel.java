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
import cn.nit.service.table5.T522Service;
import cn.nit.util.TimeUtil;

public class T522Excel {
	
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
			T521_Bean t522Bean = new  T521_Bean();
			int n=cellList.indexOf(cell);
			if(count<4){
				count++;
				continue;
			}
			else{
				
				
			  try{
				 
				 String CSType = cell[1].getContents().trim() ;
				 
				 if(CSType == null || CSType.equals("")){
						return "第" + count + "行，类型不能为空" ;
					}
				 if(!CSType.equals("网络课程")){
					 return "第" + count + "行，只能填“网络课程”" ;
				 }
				 
					String CSName = cell[2].getContents().trim() ;
					
					if(CSName == null || CSName.equals("")){
						return "第" + count + "行，课程名称不能为空" ;
					}
					
					if(CSName.length()>100){
						return "第" + count + "行，课程名称长度超过25个汉字" ;
					}
					
					String CSID = cell[3].getContents().trim() ;
					
					if(CSID == null || CSID.equals("")){
						return "第" + count + "行，课程编号不能为空" ;
					}
		
					String CSLevel = cell[4].getContents().trim() ;
					
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
					
					String Leader = cell[5].getContents().trim() ;
					String TeaID = cell[6].getContents().trim() ;
					
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
					
					String JoinTeaNum = cell[7].getContents().trim();
					
					if(JoinTeaNum == null || JoinTeaNum.equals("")){
						JoinTeaNum="0";
					}
					if(!this.isNumeric(JoinTeaNum)){
						return "第" + count + "行，参与教工人数只能填数字" ;
					}
					
					String OtherTeaNum=cell[8].getContents().trim();
					
					if(OtherTeaNum != null && OtherTeaNum.length()>200){
						return "第" + count + "行，其他参与教师字数不能超过100字" ;
					}
					
					String CSUrl=cell[9].getContents().trim();
					
					String AppvlTime=cell[10].getContents().trim();
					
					if(AppvlTime ==null || AppvlTime.equals("")){
						return "第" + count + "行，获准时间不能为空" ;
					}
					if(!this.judgeFormat1(AppvlTime)){
						return "第" + count + "行，获准时间格式不正确，格式为：2012-09" ;
					}
					
					String ReceptTime=cell[11].getContents().trim();
					if(ReceptTime ==null || ReceptTime.equals("")){
						return "第" + count + "行，验收时间不能为空" ;
					}
					if(!this.judgeFormat1(ReceptTime)){
						return "第" + count + "行，验收时间格式不正确，格式为：2012-09" ;
					}
					
					String FromTeaUnit = cell[12].getContents().trim() ;
					String UnitID = cell[13].getContents().trim() ;
					
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
					
					String AppvlID=cell[14].getContents().trim();
					
					if(AppvlID == null || AppvlID.equals("")){
						return "第" + count + "行，批文号" ;
					}
					
					String Note=cell[15].getContents().trim();
					
					if(Note != null&&Note.length()>1000){
						return "第" + count + "行，备注不能超过500字" ;
					}
					
					
				count++ ;
				
				t522Bean.setAppvlID(AppvlID);
				t522Bean.setAppvlTime(TimeUtil.changeDateYM(AppvlTime));
				t522Bean.setCSID(CSID);
				t522Bean.setCSLevel(CSLevel);
				t522Bean.setCSName(CSName);
				t522Bean.setCSType(CSType);
				t522Bean.setCSUrl(CSUrl);
				t522Bean.setJoinTeaNum(Integer.parseInt(JoinTeaNum));
				t522Bean.setLeader(Leader);
				t522Bean.setNote(Note);
				t522Bean.setOtherTea(OtherTeaNum);
				t522Bean.setReceptTime(TimeUtil.changeDateYM(ReceptTime));
				t522Bean.setTeaID(TeaID);
				t522Bean.setTeaUnit(FromTeaUnit);
				t522Bean.setTime(Time);
				t522Bean.setCheckState(Constants.WAIT_CHECK);
				t522Bean.setNote(Note);
				t522Bean.setUnitID(UnitID);
			
				list.add(t522Bean);
				
							
			}
			catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
	     }
		}
		
		flag = false ;
		T522Service t522Ser = new T522Service() ;
		flag = t522Ser.batchInsert(list) ;
		
		if(flag){
			return null ;
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
