package cn.nit.excel.imports.table3;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
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

import cn.nit.bean.UserinfoBean;
import cn.nit.bean.di.DiAwardLevelBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.di.DiMajorOneBean;
import cn.nit.bean.table3.T33_Bean;
import cn.nit.constants.Constants;
import cn.nit.service.di.DiAwardLevelService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.di.DiMajorOneService;
import cn.nit.service.table3.T33_Service;
import cn.nit.util.TimeUtil;

public class T33Excel {
	
	
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
		List<T33_Bean> list = new LinkedList<T33_Bean>() ;
		UserinfoBean userinfo = (UserinfoBean)request.getSession().getAttribute("userinfo") ;
		DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		DiMajorOneService diMajorOneSer=new DiMajorOneService();
		List<DiMajorOneBean> diMajorOneList=diMajorOneSer.getList();
	
		
		for(Cell[] cell : cellList){
			
			T33_Bean t33_Bean = new  T33_Bean();
				
				
			  try{
				  
				    if(count<5){
				    	count++;
				    	continue;
				    }
				    

				    
					String TeaUnit = cell[1].getContents().trim();
					String UnitID=cell[2].getContents().trim();
					
					if(TeaUnit == null || TeaUnit.equals("")){
						return "第" + count + "行，教学单位不能为空";
					}
					
					if(UnitID == null || UnitID.equals("")){
						return "第" + count + "行，单位号不能为空";
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
					
					if(!flag){
						return "第" + count + "行，没有与之相匹配的单位号" ;
					}else{
						flag=false;
					}
					
					
					String MajorName = cell[3].getContents().trim();
					String MajorID=cell[4].getContents().trim();
					
					if(MajorName == null || MajorName.equals("")){
						return "第" + count + "行，专业名称不能为空";
					}
					
					if(MajorID == null || MajorID.equals("")){
						return "第" + count + "行，专业代码不能为空";
					}

					for(DiMajorOneBean diMajorOneBean : diMajorOneList){
						if(diMajorOneBean.getMajorName().equals(MajorName)){
							if(diMajorOneBean.getMajorNum().equals(MajorID)){
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
						flag=false;
					}
				 
				    String MajorFieldName = cell[5].getContents().trim();
				    
				    if(MajorFieldName == null || MajorFieldName.equals("")){
				    	return "第" + count + "行，专业方向名称不能为空" ;
				    }

					String AppvlSetTime = cell[6].getContents().trim() ;
					
					if(AppvlSetTime == null || AppvlSetTime.equals("")){
						return "第" + count + "行，批准设置时间不能为空" ;
					}
					
				    if(!TimeUtil.judgeFormatYM(AppvlSetTime)){
						return "第" + count + "行，批准设置时间格式有误（格式如：2013-02）" ;
					}

					String FirstAdmisTime = cell[7].getContents().trim() ;
					
					if(FirstAdmisTime == null || FirstAdmisTime.equals("")){
						return "第" + count + "行，首次招生时间不能为空" ;
					}
					
				    if(!TimeUtil.judgeFormatYM(FirstAdmisTime)){
						return "第" + count + "行，首次招生时间格式有误（格式如：2013-02）" ;
					}
				    
				    
					
					String MajorYearLimit1 = cell[8].getContents().trim();
					if(MajorYearLimit1 == null || MajorYearLimit1.equals("")){
				    	return "第" + count + "行，修业年限不能为空" ;
				    }
					boolean isNum = MajorYearLimit1.matches("[0-9]+"); 
					if(!isNum){
						return "第"+count+"行，修业年限必须为正整数";
					}
					int MajorYearLimit = 0;
					try{
						MajorYearLimit=Integer.parseInt(MajorYearLimit1);
					}catch( NumberFormatException e){
						e.printStackTrace() ;
					}
					boolean IsSepcialMajor1;
					boolean IsKeyMajor1;
				    String  IsSepcialMajor=cell[9].getContents().trim();
				    String  IsKeyMajor=cell[10].getContents().trim();
				    
				    
				    if(IsSepcialMajor.equals("是")){
				    	IsSepcialMajor1=true;
				    }else if(IsSepcialMajor.equals("否")){
				    	IsSepcialMajor1=false;
				    }else{
				    	return "第" + count + "行，特色专业应该填是或否" ; 
				    }
				    
				    
				    
				    if(IsKeyMajor.equals("是")){
				    	IsKeyMajor1=true;
				    }else if(IsKeyMajor.equals("否")){
				    	IsKeyMajor1=false;
				    }else{
				    	return "第" + count + "行，重点专业应该填 是或否" ; 
				    }
				    
					String MajorLeader = cell[11].getContents().trim() ;
					
					if(MajorLeader == null || MajorLeader.equals("")){
						return "第" + count + "行，专业带头人姓名不能为空" ;
					}
					
					boolean LIsFullTime1;
					String LIsFullTime= cell[12].getContents().trim();
				    if(LIsFullTime.equals("是")){
				    	LIsFullTime1=true;
				    }else if(LIsFullTime.equals("否")){
				    	LIsFullTime1=false;
				    }else{
				    	return "第" + count + "行，专业带头人是否专职应该填是或否" ; 
				    }
					
				    
					String MajorChargeMan = cell[13].getContents().trim() ;
					
					if(MajorChargeMan == null || MajorChargeMan.equals("")){
						return "第" + count + "行，专业负责人姓名不能为空" ;
					}
					
					boolean CIsFullTime1;
					String CIsFullTime= cell[14].getContents().trim();
				    if(CIsFullTime.equals("是")){
				    	CIsFullTime1=true;
				    }else if(CIsFullTime.equals("否")){
				    	CIsFullTime1=false;
				    }else{
				    	return "第" + count + "行，专业负责人是否专职应该填是或否" ; 
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
//				t33_Bean.setTime(time);
				t33_Bean.setCheckState(Constants.WAIT_CHECK);
				t33_Bean.setTime(TimeUtil.changeDateY(selectYear));
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
			return null ;
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
	public static ByteArrayOutputStream exportExcel(List list, String sheetName, Map<String,Integer> maplist) throws Exception{
		
        WritableWorkbook wwb;
        ByteArrayOutputStream fos = null;
        try {    
            fos = new ByteArrayOutputStream();
            wwb = Workbook.createWorkbook(fos);
            WritableSheet ws = wwb.createSheet(sheetName, 0);        // 创建一个工作表

            //    设置表头的文字格式
            
            WritableFont wf = new WritableFont(WritableFont.ARIAL,12,WritableFont.BOLD,false,
                    UnderlineStyle.NO_UNDERLINE,Colour.BLACK);    
            WritableCellFormat wcf = new WritableCellFormat(wf);
            wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
            wcf.setAlignment(Alignment.CENTRE);
            wcf.setBorder(Border.ALL, BorderLineStyle.THIN,
	        		     jxl.format.Colour.BLACK);
            
            //    设置内容单无格的文字格式
            WritableFont wf1 = new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD,false,
	                    UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
            WritableCellFormat wcf1 = new WritableCellFormat(wf1);       
            wcf1.setVerticalAlignment(VerticalAlignment.CENTRE);
            wcf1.setAlignment(Alignment.CENTRE);
            wcf1.setBorder(Border.ALL, BorderLineStyle.THIN,
	        		     jxl.format.Colour.BLACK);
            ws.setRowView(1, 500);
            
            
            ws.addCell(new Label(0, 0, sheetName, wcf)); 
            
            ws.addCell(new Label(0,2,"1.专业基本情况",wcf));
            ws.addCell(new Label(9,2,"2.特色专业",wcf));
            ws.addCell(new Label(10,2,"3.重点专业",wcf));
            ws.addCell(new Label(11,2,"4.专业带头人",wcf));
            ws.addCell(new Label(13,2,"5.专业负责人",wcf));
            ws.addCell(new Label(0,3,"序号",wcf));
            ws.addCell(new Label(1,3,"教学单位",wcf));
            ws.addCell(new Label(2,3,"单位号",wcf));
            ws.addCell(new Label(3,3,"专业名称",wcf));
            ws.addCell(new Label(4,3,"专业代码",wcf));
            ws.addCell(new Label(5,3,"专业方向名称",wcf));
            ws.addCell(new Label(6,3,"批准设置时间",wcf));
            ws.addCell(new Label(7,3,"首次招生时间",wcf));
            ws.addCell(new Label(8,3,"修业年限",wcf));
            ws.addCell(new Label(11,3,"姓名",wcf));
            ws.addCell(new Label(12,3,"是否专职",wcf));
            ws.addCell(new Label(13,3,"姓名",wcf));
            ws.addCell(new Label(14,3,"是否专职",wcf));
            
            ws.mergeCells(0, 2, 8, 2);
            ws.mergeCells(11, 2, 12, 2);
            ws.mergeCells(13, 2, 14, 2);
            ws.mergeCells(9, 2, 9, 3);
            ws.mergeCells(10, 2, 10, 3);
 
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
                        		ws.addCell(new Label(0,i+3,""+i,wcf1)); 
                        		continue;
                        	}
                        	                        	
        					String type = wrapper.getPropertyType(column).toString() ;
//        					System.out.println(type +"-test：" + column);

        					//判断插入数据的类型，并赋�?
        					if(type.endsWith("String")){
        						ws.addCell(new Label(maplist.get(column).intValue(),i+3,(String) wrapper.getPropertyValue(column),wcf1));
        					}else if(type.endsWith("int")||type.endsWith("Integer")){
        						ws.addCell(new Label(maplist.get(column).intValue(),i+3,(String) wrapper.getPropertyValue(column).toString(),wcf1));
        					}else if(type.endsWith("Date")){
        						if((java.util.Date)wrapper.getPropertyValue(column) == null){
        							ws.addCell(new Label(maplist.get(column).intValue(),i+3,null,wcf1));
        						}else{
            						java.util.Date utilDate = (java.util.Date)wrapper.getPropertyValue(column) ;
            						Date sqlDate = new Date(utilDate.getTime()) ;
            						SimpleDateFormat sfEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            						SimpleDateFormat sfStart = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",java.util.Locale.ENGLISH) ;
//            						sfEnd.format(sfStart.parse(sqlDate.toString()));
            						
            						ws.addCell(new Label(maplist.get(column).intValue(),i+3,sfEnd.format(sfStart.parse(sqlDate.toString())).toString().substring(0, 7),wcf1));
        							
        						}
        					}else if(type.endsWith("long")||type.endsWith("Long")){
        						ws.addCell(new Label(maplist.get(column).intValue(),i+3,(String) wrapper.getPropertyValue(column).toString(),wcf1));
        					}else if(type.endsWith("boolean")||type.endsWith("Boolean")){
        						if((Boolean)wrapper.getPropertyValue(column)){
        							ws.addCell(new Label(maplist.get(column).intValue(),i+3,"是",wcf1));
        						}else{
        							ws.addCell(new Label(maplist.get(column).intValue(),i+3,"否",wcf1));
        						}
        					}else if(type.endsWith("double")||type.endsWith("Double")){
        						ws.addCell(new Label(maplist.get(column).intValue(),i+3,(String) wrapper.getPropertyValue(column).toString(),wcf1));
        					}else{
        						throw new Exception("自行添加对应类型" + type) ;
        					}                       	                         	
                    }
                    i++;
                }
            }else{
            	System.out.println("后台传入的数据为空");
            }

            wwb.write();
            wwb.close();

        } catch (IOException e){
        } catch (RowsExceededException e){
        } catch (WriteException e){}
        
        return fos ;
    }
	


}
