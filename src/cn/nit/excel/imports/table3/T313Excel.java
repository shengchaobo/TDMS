package cn.nit.excel.imports.table3;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jxl.Cell;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import cn.nit.bean.other.UserRoleBean;
import cn.nit.bean.table3.T313_Bean;
import org.springframework.beans.BeanWrapperImpl;

import cn.nit.bean.di.DiAwardLevelBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.service.di.DiAwardLevelService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table3.T313_Service;
import cn.nit.util.TimeUtil;

public class T313Excel {
	
	/**
	 * 批量导入
	 * @param cellList {@link java.util.List<{@link jxl.Cell}>}  Cell是数组
	 * @param request  {@link javax.servlet.http.HttpServletRequest}
	 * @return
	 */
	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request, String selectYear){

		System.out.println("大小");
		System.out.println(cellList.size());
		if((cellList == null) || (cellList.size() < 2)){
			return "数据不标准，请重新提交" ;
		}
		boolean NationLevelOne1,NationLevelTwo1,NationLevelKey1,ProvinceLevelOne1,ProvinceLevelTwo1,CityLevel1,SchLevel1;
		int count = 1 ;
		 Date time=new Date();
		boolean flag = false ;
		List<T313_Bean> list = new LinkedList<T313_Bean>() ;
		UserRoleBean userinfo = (UserRoleBean)request.getSession().getAttribute("userinfo") ;
		DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		
	
		
		for(Cell[] cell : cellList){
			
			T313_Bean t313_Bean = new  T313_Bean();
				
				
			  try{
				  
				    if(count<5){
				    	count++;
				    	continue;
				    }
				 
				    String DiscipName = cell[1].getContents();
				    
				    if(DiscipName == null || DiscipName.equals("")){
				    	return "第" + count + "行，重点学科名称不能为空" ;
				    }
				    if(DiscipName.length()>100){
				    	return "第" + count + "行，重点学科名称长度不能超过100" ;
				    }
				    
					String DiscipID = cell[2].getContents() ;
					
					if(DiscipID == null ||DiscipID.equals("")){
						return "第" + count + "行，学科代码不能为空" ;
					}
					if(DiscipID.length()>50){
						return "第" + count + "行，学科代码长度不能超过50";
					}
					
					String UnitName = cell[3].getContents();
					String UnitID=cell[4].getContents();
					
					if(UnitName == null || UnitName.equals("")){
						return "第" + count + "行，所属教学单位不能为空";
					}
					
					if(UnitID == null || UnitID.equals("")){
						return "第" + count + "行，单位号不能为空";
					}
					
					if(UnitID.length()>50){
						return "第" + count + "行，单位号长度不能超过50";
					}
					for(DiDepartmentBean diDepartBean : diDepartBeanList){
						if(diDepartBean.getUnitId().equals(UnitID)){
							if(diDepartBean.getUnitName().equals(UnitName)){
								flag = true;
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
					
				    String DiscipType = cell[5].getContents();
				    
					if(DiscipType.equals("01哲学")||DiscipType.equals("02经济学")||DiscipType.equals("03法学")||DiscipType.equals("04教育学")||DiscipType.equals("05文学")||DiscipType.equals("06历史学")||DiscipType.equals("07理学")||DiscipType.equals("08工学")||DiscipType.equals("09农学")||DiscipType.equals("10医学")||DiscipType.equals("11军事学")||DiscipType.equals("12管理学")||DiscipType.equals("13艺术学")){
					
					}else{
						return "第" + count + "行，学科门类输入有误" ;
					}

					
				    String  NationLevelOne=cell[6].getContents();
				    String  NationLevelTwo=cell[7].getContents();
				    String  NationLevelKey=cell[8].getContents();
				    String  ProvinceLevelOne=cell[9].getContents();
				    String  ProvinceLevelTwo=cell[10].getContents();
				    String  CityLevel=cell[11].getContents();
				    String  SchLevel=cell[12].getContents();
				    if(NationLevelOne.equals("是")){
				    	NationLevelOne1=true;
				    }else if(NationLevelOne.equals("否")){
				    	NationLevelOne1=false;
				    }else{
				    	return "第" + count + "行，国家一级应为是或否" ;
				    }
				    
				    if(NationLevelTwo.equals("是")){
				    	NationLevelTwo1=true;
				    }else if(NationLevelTwo.equals("否")){
				    	NationLevelTwo1=false;
				    }else{
				    	return "第" + count + "行，国家二级应为是或否" ;
				    }
				    
				    if(NationLevelKey.equals("是")){
				    	NationLevelKey1=true;
				    }else if(NationLevelKey.equals("否")){
				    	NationLevelKey1=false;
				    }else{
				    	return "第" + count + "行，国家重点应为是或否" ;
				    }

				    if(ProvinceLevelOne.equals("是")){
				    	ProvinceLevelOne1=true;
				    }else if(ProvinceLevelOne.equals("否")){
				    	ProvinceLevelOne1=false;
				    }else{
				    	return "第" + count + "行，省部一级应为是或否" ;
				    }

				    if(ProvinceLevelTwo.equals("是")){
				    	ProvinceLevelTwo1=true;
				    }else if(ProvinceLevelTwo.equals("否")){
				    	ProvinceLevelTwo1=false;
				    }else{
				    	return "第" + count + "行，省部二级级应为是或否" ;
				    }
				    
				    if(CityLevel.equals("是")){
				    	CityLevel1=true;
				    }else if(CityLevel.equals("否")){
				    	CityLevel1=false;
				    }else{
				    	return "第" + count + "行，市一级应为是或否" ;
				    }

				    if(SchLevel.equals("是")){
				    	SchLevel1=true;
				    }else if(SchLevel.equals("否")){
				    	SchLevel1=false;
				    }else{
				    	return "第" + count + "行，校一级应为是或否" ;
				    }

					
				
				count++ ;
				
				t313_Bean.setDiscipName(DiscipName);
				t313_Bean.setDiscipID(DiscipID);
				t313_Bean.setUnitName(UnitName);
				t313_Bean.setUnitID(UnitID);
				t313_Bean.setDiscipType(DiscipType);
				t313_Bean.setNationLevelOne(NationLevelOne1);
				t313_Bean.setNationLevelTwo(NationLevelTwo1);
				t313_Bean.setNationLevelKey(NationLevelKey1);
				t313_Bean.setProvinceLevelOne(ProvinceLevelOne1);
				t313_Bean.setProvinceLevelTwo(ProvinceLevelTwo1);
				t313_Bean.setCityLevel(CityLevel1);
				t313_Bean.setSchLevel(SchLevel1);
//				t313_Bean.setTime(time);
				t313_Bean.setTime(TimeUtil.changeDateY(selectYear));
				list.add(t313_Bean);
				System.out.println("数字");
				System.out.println(count);
			}
			catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
	     }
		
	
		T313_Service t313_Ser = new T313_Service() ;
		flag = t313_Ser.batchInsert(list) ;
		
	

		if(flag){
			return "数据导入成功" ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
		
	}

}
