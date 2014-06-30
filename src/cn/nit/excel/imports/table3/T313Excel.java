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
import cn.nit.bean.table3.T313_Bean;
import org.springframework.beans.BeanWrapperImpl;

import cn.nit.bean.di.DiAwardLevelBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.other.UserRoleBean;
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
	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request){
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
				  
				    if(count<4){
				    	count++;
				    	continue;
				    }
				    
				    if(count!= 4){
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
								flag = true ;
								break ;
							}else{
								return "第" + count + "行，所属教学单位与单位号不对应" ;
							}
						}//if
					}//for
					
				    String DiscipType = cell[5].getContents();
				    
				    if(DiscipType == null || DiscipType.equals("")){
				    	return "第" + count + "行，类型不能为空" ;
				    }
				    if(DiscipType.length()>100){
				    	return "第" + count + "行，类型长度不能超过100" ;
				    }
					
				    String  NationLevelOne=cell[6].getContents();
				    String  NationLevelTwo=cell[7].getContents();
				    String  NationLevelKey=cell[8].getContents();
				    String  ProvinceLevelOne=cell[9].getContents();
				    String  ProvinceLevelTwo=cell[10].getContents();
				    String  CityLevel=cell[11].getContents();
				    String  SchLevel=cell[12].getContents();
				    if(NationLevelOne=="是"){
				    	NationLevelOne1=true;
				    }else{
				    	NationLevelOne1=false;
				    }
				    if(NationLevelTwo=="是"){
				    	NationLevelTwo1=true;
				    }else{
				    	NationLevelTwo1=false;
				    }
				    if(NationLevelKey=="是"){
				    	NationLevelKey1=true;
				    }else{
				    	NationLevelKey1=false;
				    }
				    if(ProvinceLevelOne=="是"){
				    	ProvinceLevelOne1=true;
				    }else{
				    	ProvinceLevelOne1=false;
				    }
				    if(ProvinceLevelTwo=="是"){
				    	ProvinceLevelTwo1=true;
				    }else{
				    	ProvinceLevelTwo1=false;
				    }
				    if(CityLevel=="是"){
				    	CityLevel1=true;
				    }else{
				    	CityLevel1=false;
				    }
				    if(SchLevel=="是"){
				    	SchLevel1=true;
				    }else{
				    	SchLevel1=false;
				    }

				    String  Note=cell[14].getContents();
					if(Note.length()>1000){
						return "第" + count + "行，备注的长度不能超过500个字符！" ;
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
				t313_Bean.setTime(time);
				t313_Bean.setNote(Note);
				list.add(t313_Bean);
				System.out.println("数字");
				System.out.println(count);
			}
			catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
	     }
		
		flag = false ;
		T313_Service t313_Ser = new T313_Service() ;
		flag = t313_Ser.batchInsert(list) ;
		
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
	public static ByteArrayOutputStream batchExport(List<T313_Bean> list, String sheetName, Map<String,Integer> maplist, List<String> columns) throws Exception{
		
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
