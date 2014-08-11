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

import org.springframework.beans.BeanWrapperImpl;

import cn.nit.bean.di.DiAwardLevelBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.other.UserRoleBean;
import cn.nit.bean.table3.T312_Bean;

import cn.nit.service.di.DiAwardLevelService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table3.T312_Service;

import cn.nit.util.TimeUtil;

public class T312Excel {
	
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
		
		int count = 1 ;
		 Date time=new Date();
		boolean flag = false ;
		List<T312_Bean> list = new LinkedList<T312_Bean>() ;
		UserRoleBean userinfo = (UserRoleBean)request.getSession().getAttribute("userinfo") ;
		DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		
	
		
		for(Cell[] cell : cellList){
			System.out.println(cell.length);
			
			T312_Bean t312_Bean = new  T312_Bean();
				
				
			  try{
				  
				    if(count<4){
				    	count++;
				    	continue;
				    }
				    

				 
				    String StaName = cell[1].getContents();
				    
				    if(StaName == null || StaName.equals("")){
				    	return "第" + count + "行，名称不能为空" ;
				    }
				    if(StaName.length()>100){
				    	return "第" + count + "行，名称长度不能超过100" ;
				    }
				    
				    
					String StaID = cell[2].getContents() ;
					
					if(StaID == null || StaID.equals("")){
						return "第" + count + "行，代码不能为空" ;
					}
					if(StaID.length()>50){
						return "第" + count + "行，代码长度不能超过50";
					}
					
					String UnitName = cell[3].getContents();
					String UnitID=cell[4].getContents();
					
					if(UnitName == null || UnitName.equals("")){
						return "第" + count + "行，所属单位不能为空";
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
								flag = true ;
								break ;
							}else{
								return "第" + count + "行，所属单位与单位号不对应" ;
							}
						}//if
					}//for
					
					if(!flag){
						return "第" + count + "行，没有与之相匹配的单位号" ;
					}else{
						flag = false ;
					}
					
				    String StaType = cell[5].getContents();
				    
				    if(StaType == null || StaType.equals("")){
				    	return "第" + count + "行，类型不能为空" ;
				    }
				    if(!StaType.equals("硕士点")&&!StaType.equals("博士点")){
				    	return "第"+count+"行，类型应该为硕士点或是博士点";
				    }

				
				
					
					
				
				count++ ;
				
				t312_Bean.setStaName(StaName);
				t312_Bean.setStaID(StaID);
				t312_Bean.setUnitName(UnitName);
				t312_Bean.setUnitID(UnitID);
				t312_Bean.setStaType(StaType);
//				t312_Bean.setTime(time);
				t312_Bean.setTime(TimeUtil.changeDateY(selectYear));
				list.add(t312_Bean);
				System.out.println("数字");
				System.out.println(count);
			}
			catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
	     }
		
		flag = false ;
		T312_Service t312_Ser = new T312_Service() ;
		flag = t312_Ser.batchInsert(list) ;
		

		

		if(flag){
			return "数据导入成功" ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
		
	}

}
