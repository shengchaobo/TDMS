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
import cn.nit.bean.table3.T311_Bean;
import cn.nit.constants.Constants;

import cn.nit.service.di.DiAwardLevelService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table3.T311_Service;

import cn.nit.util.TimeUtil;

public class T311Excel {
	
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
		List<T311_Bean> list = new LinkedList<T311_Bean>() ;
		
		for(Cell[] cell : cellList){
			
			T311_Bean t311_Bean = new  T311_Bean();
				
				
			  try{
				  
				    if(count<4){
				    	count++;
				    	continue;
				    }
				    

				 
				    String PostDocStaName = cell[1].getContents().trim();
				    
				    if(PostDocStaName == null || PostDocStaName.equals("")){
				    	return "第" + count + "行，博士后流动站名称不能为空" ;
				    }

				    
					String setTime = cell[2].getContents().trim();
					
					if(setTime == null || setTime.equals("")){
						return "第" + count + "行，设置时间不能为空" ;
					}
					
				    if(!TimeUtil.judgeFormat3(setTime)){
						return "第" + count + "行，设置时间格式有误（格式如：2013）" ;
					}
				    
				    
					String ResearcherNum = cell[3].getContents().trim() ;
					
					if(ResearcherNum == null || ResearcherNum.equals("")){
						return "第" + count + "行，研究员人数不能为空" ;
					}
					
					boolean isNum = ResearcherNum.matches("[0-9]+"); 
					if(!isNum){
						return "第"+count+"行，研究员人数必须为正整数";
					}
					
					String UnitName = cell[4].getContents().trim();
					String UnitID=cell[5].getContents().trim();
					
					if(UnitName == null || UnitName.equals("")){
						return "第" + count + "行，所属单位不能为空";
					}
					
					if(UnitID == null || UnitID.equals("")){
						return "第" + count + "行，单位号不能为空";
					}
					

					

				
				count++ ;
				
				t311_Bean.setPostDocStaName(PostDocStaName);
				t311_Bean.setSetTime(TimeUtil.changeDateY(setTime));
				t311_Bean.setResearcherNum(Integer.parseInt(ResearcherNum));
				t311_Bean.setUnitName(UnitName);
				t311_Bean.setUnitID(UnitID);
				t311_Bean.setCheckState(Constants.WAIT_CHECK);
				t311_Bean.setTime(TimeUtil.changeDateY(selectYear));
				list.add(t311_Bean);
				System.out.println("数字");
				System.out.println(count);
			}
			catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
	     }
		
		flag = false ;
		T311_Service t311_Ser = new T311_Service() ;
		flag = t311_Ser.batchInsert(list) ;

		if(flag){
			return null ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
		
	}
	

	
	
	public static void main(String arg[]){

		
	}

}
