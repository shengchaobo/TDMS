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
import cn.nit.bean.di.DiMajorTwoBean;
import cn.nit.bean.other.UserRoleBean;
import cn.nit.bean.table3.T33_Bean;
import cn.nit.service.di.DiAwardLevelService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.di.DiMajorTwoService;
import cn.nit.service.table3.T33_Service;
import cn.nit.util.TimeUtil;

public class T33Excel {
	
	
	/**
	 * 批量导入
	 * @param cellList {@link java.util.List<{@link jxl.Cell}>}  Cell是数组
	 * @param request  {@link javax.servlet.http.HttpServletRequest}
	 * @return
	 */
	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request){
		System.out.println("大小");
		System.out.println(cellList.size());
		if((cellList == null) || (cellList.size() < 2)){
			return "数据不标准，请重新提交" ;
		}
		
		int count = 1 ;
		 Date time=new Date();
		boolean flag = false ;
		List<T33_Bean> list = new LinkedList<T33_Bean>() ;
		UserRoleBean userinfo = (UserRoleBean)request.getSession().getAttribute("userinfo") ;
		DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		DiMajorTwoService diMajorTwoSer=new DiMajorTwoService();
		List<DiMajorTwoBean> diMajorTwoList=diMajorTwoSer.getList();
	
		
		for(Cell[] cell : cellList){
			
			T33_Bean t33_Bean = new  T33_Bean();
				
				
			  try{
				  
				    if(count<4){
				    	count++;
				    	continue;
				    }
				    
				    if(count!= 4){
				    	continue;
				    }
				    
					String TeaUnit = cell[1].getContents();
					String UnitID=cell[2].getContents();
					
					if(TeaUnit == null || TeaUnit.equals("")){
						return "第" + count + "行，教学单位不能为空";
					}
					
					if(UnitID == null || UnitID.equals("")){
						return "第" + count + "行，单位号不能为空";
					}
					
					if(UnitID.length()>50){
						return "第" + count + "行，单位号长度不能超过50";
					}
					for(DiDepartmentBean diDepartBean : diDepartBeanList){
						if(diDepartBean.getUnitId().equals(UnitID)){
							if(diDepartBean.getUnitName().equals(TeaUnit)){
								flag = true ;
								break ;
							}else{
								return "第" + count + "行，教学单位与单位号不对应" ;
							}
						}//if
					}//for
					
					
					String MajorName = cell[3].getContents();
					String MajorID=cell[4].getContents();
					
					if(MajorName == null || MajorName.equals("")){
						return "第" + count + "行，专业名称不能为空";
					}
					
					if(MajorID == null || MajorID.equals("")){
						return "第" + count + "行，专业代码不能为空";
					}
					
					if(MajorID.length()>50){
						return "第" + count + "行，专业代码长度不能超过50";
					}
					for(DiMajorTwoBean diMajorTwoBean : diMajorTwoList){
						if(diMajorTwoBean.getMajorNum().equals(MajorID)){
							if(diMajorTwoBean.getMajorName().equals(MajorName)){
								flag = true ;
								break ;
							}else{
								return "第" + count + "行，专业名称与专业代码不对应" ;
							}
						}//if
					}//for
				 
				    String MajorFieldName = cell[5].getContents();
				    
				    if(MajorFieldName == null || MajorFieldName.equals("")){
				    	return "第" + count + "行，专业方向名称不能为空" ;
				    }
				    if(MajorFieldName.length()>100){
				    	return "第" + count + "行，专业方向名称长度不能超过100" ;
				    }
				    
					String AppvlSetTime = cell[6].getContents() ;
					
					if(AppvlSetTime == null || AppvlSetTime.equals("")){
						return "第" + count + "行，批准设置时间不能为空" ;
					}
					
				    if(!TimeUtil.judgeFormat1(AppvlSetTime)){
						return "第" + count + "行，批准设置时间格式有误（格式如：2013/02）" ;
					}

					String FirstAdmisTime = cell[7].getContents() ;
					
					if(FirstAdmisTime == null || FirstAdmisTime.equals("")){
						return "第" + count + "行，批准设置时间不能为空" ;
					}
					
				    if(!TimeUtil.judgeFormat1(FirstAdmisTime)){
						return "第" + count + "行，首次招生时间格式有误（格式如：2013/02）" ;
					}
				    
				    
					int MajorYearLimit = 0;
					try{
						MajorYearLimit=Integer.parseInt(cell[8].getContents());
					}catch( NumberFormatException e){
						e.printStackTrace() ;
					}
					boolean IsSepcialMajor1;
					boolean IsKeyMajor1;
				    String  IsSepcialMajor=cell[9].getContents();
				    String  IsKeyMajor=cell[10].getContents();
				    if(IsSepcialMajor=="是"){
				    	IsSepcialMajor1=true;
				    }else{
				    	IsSepcialMajor1=false;
				    }
				    if(IsKeyMajor=="是"){
				    	IsKeyMajor1=true;
				    }else{
				    	IsKeyMajor1=false;
				    }			
				    
					String MajorLeader = cell[11].getContents() ;
					
					if(MajorLeader == null || MajorLeader.equals("")){
						return "第" + count + "行，专业带头人姓名不能为空" ;
					}
					
					boolean LIsFullTime1;
					String LIsFullTime= cell[12].getContents();
				    if(LIsFullTime=="是"){
				    	LIsFullTime1=true;
				    }else{
				    	LIsFullTime1=false;
				    }
					
				    
					String MajorChargeMan = cell[13].getContents() ;
					
					if(MajorChargeMan == null || MajorChargeMan.equals("")){
						return "第" + count + "行，专业负责人姓名不能为空" ;
					}
					
					boolean CIsFullTime1;
					String CIsFullTime= cell[14].getContents();
				    if(CIsFullTime=="是"){
				    	CIsFullTime1=true;
				    }else{
				    	CIsFullTime1=false;
				    }
					String  Note=cell[16].getContents();
					if(Note.length()>1000){
						return "第" + count + "行，备注的长度不能超过500个字符！" ;
					}

					

				
				count++ ;
				
				t33_Bean.setTeaUnit(TeaUnit);
				t33_Bean.setUnitID(UnitID);
				t33_Bean.setMajorName(MajorName);
				t33_Bean.setMajorID(MajorID);
				t33_Bean.setMajorFieldName(MajorFieldName);
				t33_Bean.setAppvlSetTime(TimeUtil.changeDateYM(AppvlSetTime));
				t33_Bean.setFirstAdmisTime(TimeUtil.changeDateYM(FirstAdmisTime));
				t33_Bean.setMajorYearLimit(MajorYearLimit);
				t33_Bean.setIsSepcialMajor(IsSepcialMajor1);
				t33_Bean.setIsKeyMajor(IsKeyMajor1);
				t33_Bean.setMajorLeader(MajorLeader);
				t33_Bean.setLIsFullTime(LIsFullTime1);
				t33_Bean.setMajorChargeMan(MajorChargeMan);
				t33_Bean.setCIsFullTime(CIsFullTime1);
				t33_Bean.setTime(time);
				t33_Bean.setNote(Note);
				list.add(t33_Bean);
				System.out.println("数字");
				System.out.println(count);
			}
			catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
	     }
		
		flag = false ;
		T33_Service t33_Ser = new T33_Service() ;
		flag = t33_Ser.batchInsert(list) ;
		
		if(flag){
			return "数据导入成功" ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
		
	}
	
	/**
	 * 批量导出
	 * @param cellList {@link java.util.List<{@link jxl.Cell}>}
	 * @param request  {@link javax.servlet.http.HttpServletRequest}
	 * @return
	 * @throws Exception 
	 */
	public static ByteArrayOutputStream batchExport(List<T33_Bean> list, String sheetName, Map<String,Integer> maplist, List<String> columns) throws Exception{
		
        WritableWorkbook wwb;//可读写的工作簿
        ByteArrayOutputStream fos = null;
        try {    
            fos = new ByteArrayOutputStream();
            wwb = Workbook.createWorkbook(fos);
            WritableSheet ws = wwb.createSheet(sheetName, 0);        // 创建一个工作表

            //    设置单元格表头的文字格式
            WritableFont wf = new WritableFont(WritableFont.ARIAL,12,WritableFont.BOLD,false,
                    UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
            WritableCellFormat wcf = new WritableCellFormat(wf);
            wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
            wcf.setAlignment(Alignment.CENTRE);
            wcf.setBorder(Border.ALL, BorderLineStyle.THIN,
				     jxl.format.Colour.BLACK);
           ws.setRowView(1, 500);
              //设置格式
			 WritableCellFormat normalFormat = new WritableCellFormat();
			 normalFormat.setBorder(Border.ALL, BorderLineStyle.THIN,
					     jxl.format.Colour.BLACK);
 
           

            //判断一下表头数组是否有数据  
            if (columns != null && columns.size() > 0) {  
  
                //循环写入表头  
                for (int i = 0; i < columns.size(); i++) {  
  
                    /* 
                     * 添加单元格(Cell)内容addCell() 
                     * 添加Label对象Label() 
                     * 数据的类型有很多种、在这里你需要什么类型就导入什么类型 
                     * 如：jxl.write.DateTime 、jxl.write.Number、jxl.write.Label 
                     * Label(i, 0, columns[i], wcf) 
                     * 其中i为列、0为行、columns[i]为数据、wcf为样式 
                     * 合起来就是说将columns[i]添加到第一行(行、列下标都是从0开始)第i列、样式为什么"色"内容居中 
                     */  
                    ws.addCell(new Label(i, 0, columns.get(i), wcf));  
                }  
  
                //判断表中是否有数据  
                if (list != null && list.size() > 0) {  
                    //循环写入表中数据  
                	BeanWrapperImpl wrapper = new BeanWrapperImpl() ;
                	int i=1;  
                	for(Object obj : list){  
                		wrapper.setWrappedInstance(obj) ;  
                        //循环输出map中的子集：既列值                         
                        for(String column:maplist.keySet()){
                        	
                        	if(column.equals("SeqNum")){
                        		ws.addCell(new Label(0,i,""+i,normalFormat)); 
                        		continue;
                        	}
                        	                        	
        					String type = wrapper.getPropertyType(column).toString() ;

        					//判断插入数据的类型，并赋�?
        					if(type.endsWith("String")){
        						ws.addCell(new Label(maplist.get(column).intValue(),i,(String) wrapper.getPropertyValue(column),normalFormat));
        					}else if(type.endsWith("int")||type.endsWith("Integer")){
        						ws.addCell(new Label(maplist.get(column).intValue(),i,(String) wrapper.getPropertyValue(column).toString(),normalFormat));
        					}else if(type.endsWith("Date")){
        						if((java.util.Date)wrapper.getPropertyValue(column) == null){
        							ws.addCell(new Label(maplist.get(column).intValue(),i,null,normalFormat));
        						}else{
            						java.util.Date utilDate = (java.util.Date)wrapper.getPropertyValue(column) ;
            						Date sqlDate = new Date(utilDate.getTime()) ;
            						TimeUtil til=new TimeUtil();
            						String date=til.changeFormat5(sqlDate);
            						ws.addCell(new Label(maplist.get(column).intValue(),i,date,normalFormat));
        						}
        					}else if(type.endsWith("long")||type.endsWith("Long")){
        						ws.addCell(new Label(maplist.get(column).intValue(),i,(String) wrapper.getPropertyValue(column).toString(),normalFormat));
        					}else if(type.endsWith("boolean")||type.endsWith("Boolean")){
        						if((Boolean)wrapper.getPropertyValue(column)){
        							ws.addCell(new Label(maplist.get(column).intValue(),i,"是",normalFormat));
        						}else{
        							ws.addCell(new Label(maplist.get(column).intValue(),i,"否",normalFormat));
        						}
        					}else if(type.endsWith("double")||type.endsWith("Double")){
        						ws.addCell(new Label(maplist.get(column).intValue(),i,(String) wrapper.getPropertyValue(column).toString(),normalFormat));
        					}else{
        						throw new Exception("自行添加对应类型" + type) ;
        					}                       	                         	
                        }
                        i++;
                    }
                }else{
                	System.out.println("后台传入的数据为空");
                }
            }

            wwb.write();
            wwb.close();

        } catch (IOException e){
        } catch (RowsExceededException e){
        } catch (WriteException e){}
        
        return fos ;
    }
	
	
	public static void main(String arg[]){

		
	}

}
