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
import cn.nit.bean.UserinfoBean;
import cn.nit.bean.table3.T313_Bean;
import org.springframework.beans.BeanWrapperImpl;

import cn.nit.bean.di.DiAwardLevelBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.constants.Constants;
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
		UserinfoBean userinfo = (UserinfoBean)request.getSession().getAttribute("userinfo") ;
		DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		
	
		
		for(Cell[] cell : cellList){
			
			T313_Bean t313_Bean = new  T313_Bean();
				
				
			  try{
				  
				    if(count<5){
				    	count++;
				    	continue;
				    }
				 
				    String DiscipName = cell[1].getContents().trim();
				    
				    if(DiscipName == null || DiscipName.equals("")){
				    	return "第" + count + "行，重点学科名称不能为空" ;
				    }

					String DiscipID = cell[2].getContents().trim();
					
					if(DiscipID == null ||DiscipID.equals("")){
						return "第" + count + "行，学科代码不能为空" ;
					}

					String UnitName = cell[3].getContents().trim();
					String UnitID=cell[4].getContents().trim();
					
					if(UnitName == null || UnitName.equals("")){
						return "第" + count + "行，所属教学单位不能为空";
					}
					
					if(UnitID == null || UnitID.equals("")){
						return "第" + count + "行，单位号不能为空";
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
					
				    String DiscipType = cell[5].getContents().trim();
				    
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
				t313_Bean.setCheckState(Constants.WAIT_CHECK);
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
		
	
		flag=false;
		T313_Service t313_Ser = new T313_Service() ;
		flag = t313_Ser.batchInsert(list) ;
		
	

		if(flag){
			return null ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
		
	}
	
	
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
            

             
            ws.addCell(new Label(0,2,"序号",wcf));
            ws.addCell(new Label(1,2,"重点学科名称",wcf));
            ws.addCell(new Label(2,2,"学科代码",wcf));
            ws.addCell(new Label(3,2,"所属教学单位",wcf));
            ws.addCell(new Label(4,2,"单位号",wcf));
            ws.addCell(new Label(5,2,"学科门类",wcf));
            ws.addCell(new Label(6,2,"级别",wcf));
            ws.addCell(new Label(6,3,"国家一级",wcf));
            ws.addCell(new Label(7,3,"国家二级",wcf));
            ws.addCell(new Label(8,3,"国家重点（培育）",wcf));
            ws.addCell(new Label(9,3,"省部一级",wcf));
            ws.addCell(new Label(10,3,"省部二级",wcf));
            ws.addCell(new Label(11,3,"市级",wcf));
            ws.addCell(new Label(12,3,"校级",wcf));
            
            ws.mergeCells(6, 2, 12, 2);
            ws.mergeCells(0, 2, 0, 3);
            ws.mergeCells(1, 2, 1, 3);
            ws.mergeCells(2, 2, 2, 3);
            ws.mergeCells(3, 2, 3, 3);
            ws.mergeCells(4, 2, 4, 3);
            ws.mergeCells(5, 2, 5, 3);
            
  
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
