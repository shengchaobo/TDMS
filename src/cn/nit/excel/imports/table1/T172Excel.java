package cn.nit.excel.imports.table1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanWrapperImpl;

import cn.nit.bean.table1.T172_Bean;
import cn.nit.service.table1.T172Service;
import cn.nit.util.DateUtil;
import cn.nit.util.TimeUtil;

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

public class T172Excel {
	
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
		T172_Bean t172Bean = null ;
		boolean flag = false ;
		List<T172_Bean> list = new LinkedList<T172_Bean>() ;
		
		for(Cell[] cell : cellList){
			 int n=cellList.indexOf(cell);
			 if(count<4){
					count++;
					continue;
				}
			 else
			 { 
			
			try{
				String FriName = cell[1].getContents().trim() ;
				if(FriName == null || FriName.equals("")){
					return "第" + count + "行，校友名称不能为空" ;
				}
				
				if(FriName.length() > 200){
					return "第" + count + "行，校友名称字数不超过100个字" ;
				}
				
				String ActName = cell[2].getContents().trim() ;
				
				if((ActName == null) || ActName.equals("")){
					return "第" + count + "行，交流活动名称不能为空" ;
				}
				
				if((ActName.length()>200)){
					return "第" + count + "行，交流活动名称不能超过100字" ;
				}
				
				String ActType = cell[3].getContents().trim() ;
				
				if((ActType == null) || ActType.equals("")){
					return "第" + count + "行，交流类型不能为空" ;
				}
				
				if((ActType.length()>100)){
					return "第" + count + "行，交流活动类型不能超过50字" ;
				}			
				
				String ActTime = cell[4].getContents().trim() ;
			    if(ActTime ==null || ActTime.equals("")){
			    	return "第" + count + "行，交流时间不能为空" ;
			    }
			    if(!TimeUtil.judgeFormatYM(ActTime)){
			    	return "第" + count + "行，交流时间格式应为：2014-05" ;
			    }
				
				String ActPlace = cell[5].getContents().trim() ;
				if(ActPlace == null || ActPlace.equals("")){
					return "第" + count + "行，交流地点不能为空" ;
				}
				if(ActPlace.length()>200){
					return "第" + count + "行，交流地点字数不能超过100字" ;
				}
				
				String UnitName = cell[6].getContents().trim();
				if(UnitName == null || UnitName.equals("")){
					return "第" + count + "行协作单位不能为空" ;
				}
				if(UnitName.length()>200){
					return "第" + count + "行，协作单位字数不能超过100字" ;
				}
				
				String UnitID = cell[7].getContents().trim();
				if(UnitID == null || UnitID.equals("")){
					return "第" + count + "行，单位号不能为空" ;
				}
				if(UnitID.length()>50){
					return "第" + count + "行，单位号字数不能超过50个字符" ;
				}

				String note = cell[8].getContents().trim();
				
				if(note.length() > 1000){
					return "第" + count + "行，备注不能超过500个汉字" ;
				}
//				
				count++ ;
				
				Date actTime=TimeUtil.changeDateYM(ActTime);
				t172Bean = new T172_Bean();
				t172Bean.setActName(ActName);
				t172Bean.setActPlace(ActPlace);
				t172Bean.setActTime(actTime);
				t172Bean.setNote(note);
				t172Bean.setActType(ActType) ;
				t172Bean.setFriName(FriName) ;
				t172Bean.setUnitID(UnitID) ;
				t172Bean.setUnitName(UnitName);
				list.add(t172Bean);
				
			}
			catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
	     }
		}
		
		flag = false ;
		T172Service t172Ser = new T172Service() ;
		flag = t172Ser.batchInsert(list) ;
		
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
	public static ByteArrayOutputStream batchExport(List<T172_Bean> list, String sheetName, Map<String,Integer> maplist, List<String> columns) throws Exception{
		
        WritableWorkbook wwb;
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
          
            ws.addCell(new Label(0,0,sheetName,wcf));

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
                    ws.addCell(new Label(i, 2, columns.get(i), wcf));  
                }  
  
                //判断表中是否有数据  
                if (list != null && list.size() > 0) {  
                    //循环写入表中数据  
                	BeanWrapperImpl wrapper = new BeanWrapperImpl() ;
                	int i=3;  
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
	

}

