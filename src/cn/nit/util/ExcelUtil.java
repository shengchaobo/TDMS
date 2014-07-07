package cn.nit.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanWrapperImpl;

import cn.nit.bean.table4.T411_Bean;

import jxl.Cell;
import jxl.JXLException;
import jxl.Range;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelUtil {
	
	/**
	 * 读取excel文件
	 * @param file   excel文件
	 * @param index  excel的第index个sheet,从第0个开始
	 * @return       按行返回数据
	 * @throws BiffException
	 * @throws IOException
	 */
	public static List<Cell[]> readExcel(File file, int index) throws BiffException, IOException{

		Workbook workbook = Workbook.getWorkbook(file) ;
		Sheet sheet = workbook.getSheet(index) ;
		
		int rows = getRightRows(sheet)+1;
		
		if(rows <= 0){
			return null ;
		}

		List<Cell[]> list = new ArrayList<Cell[]>() ;

		for(int i = 0 ; i < rows ; i++){
			Cell cell[] = sheet.getRow(i) ;
			list.add(cell) ;
		}

		return list ;

	}
	
	public static int[] readMergedCells(File file, int index,int length) throws JXLException, IOException{ 
		
		int[] mergedCells;
		mergedCells=new int [length];
	Workbook wb = Workbook.getWorkbook(file); 
	Sheet sheet = wb.getSheet(index); 
	Range[] ranges = sheet.getMergedCells(); 
	    System.out.println("sheet" + index + "包含" + ranges.length + "个区域"); 
	    for(int i=0;i<ranges.length;i=i+4){ 
	    	int top=ranges[i].getTopLeft().getRow();
	    	int bottom=ranges[i].getBottomRight().getRow();
	    	mergedCells[top]=bottom;
	    	
//	    System.out.print(space.getTopLeft().getRow()+1+"行,"); 
//	    System.out.print(space.getTopLeft().getColumn()+1+"列\t"); 
//	    System.out.print(space.getBottomRight().getRow()+1+"行,"); 
//	    System.out.print(space.getBottomRight().getColumn()+1+"列\n"); 
	    } 
	



	return mergedCells;
	} 
	
	/**
	 * 批量导出
	 * @param cellList {@link java.util.List<{@link jxl.Cell}>}
	 * @param request  {@link javax.servlet.http.HttpServletRequest}
	 * @return
	 * @throws Exception 
	 */
	public static ByteArrayOutputStream exportExcel(List list, String sheetName, Map<String,Integer> maplist, List<String> columns) throws Exception{
		
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
                        		ws.addCell(new Label(0,i,""+i,wcf1)); 
                        		continue;
                        	}
                        	                        	
        					String type = wrapper.getPropertyType(column).toString() ;
        					//System.out.println(type +"test" + column);

        					//判断插入数据的类型，并赋�?
        					if(type.endsWith("String")){
        						ws.addCell(new Label(maplist.get(column).intValue(),i,(String) wrapper.getPropertyValue(column),wcf1));
        					}else if(type.endsWith("int")||type.endsWith("Integer")){
        						ws.addCell(new Label(maplist.get(column).intValue(),i,(String) wrapper.getPropertyValue(column).toString(),wcf1));
        					}else if(type.endsWith("Date")){
        						if((java.util.Date)wrapper.getPropertyValue(column) == null){
        							ws.addCell(new Label(maplist.get(column).intValue(),i,null,wcf1));
        						}else{
            						java.util.Date utilDate = (java.util.Date)wrapper.getPropertyValue(column) ;
            						Date sqlDate = new Date(utilDate.getTime()) ;
            						ws.addCell(new Label(maplist.get(column).intValue(),i,sqlDate.toString(),wcf1));
        						}
        					}else if(type.endsWith("long")||type.endsWith("Long")){
        						ws.addCell(new Label(maplist.get(column).intValue(),i,(String) wrapper.getPropertyValue(column).toString(),wcf1));
        					}else if(type.endsWith("boolean")||type.endsWith("Boolean")){
        						if((Boolean)wrapper.getPropertyValue(column)){
        							ws.addCell(new Label(maplist.get(column).intValue(),i,"是",wcf1));
        						}else{
        							ws.addCell(new Label(maplist.get(column).intValue(),i,"否",wcf1));
        						}
        					}else if(type.endsWith("double")||type.endsWith("Double")){
        						ws.addCell(new Label(maplist.get(column).intValue(),i,(String) wrapper.getPropertyValue(column).toString(),wcf1));
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
	
	
	//返回去掉空行的记录数
    public static int getRightRows(Sheet sheet) {
		int rsCols = sheet.getColumns(); //列数
		int rsRows = sheet.getRows(); //行数
		int nullCellNum;
		int afterRows = rsRows;
		for (int i = 1; i < rsRows; i++) { //统计行中为空的单元格数
		   nullCellNum = 0;
		    for (int j = 0; j < rsCols; j++) {
		        String val = sheet.getCell(j, i).getContents();
		        val = StringUtils.trimToEmpty(val);
		        if (StringUtils.isBlank(val))
		           nullCellNum++;
		    }
		    if (nullCellNum >= rsCols) { //如果nullCellNum大于或等于总的列数
		     afterRows--;          //行数减一
		   }
		}
		return afterRows;
	}



	
}
