package cn.nit.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
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
		int rows = sheet.getRows() ;

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
	
	 public static ByteArrayOutputStream exportExcel() {
	        WritableWorkbook wwb;
	        ByteArrayOutputStream fos = null;
	        try {    
	            fos = new ByteArrayOutputStream();
	            wwb = Workbook.createWorkbook(fos);
	            WritableSheet ws = wwb.createSheet("三国志武将列表", 10);        // 创建一个工作表

	            //    设置单元格的文字格式
	            WritableFont wf = new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD,false,
	                    UnderlineStyle.NO_UNDERLINE,Colour.BLUE);
	            WritableCellFormat wcf = new WritableCellFormat(wf);
	            wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
	            wcf.setAlignment(Alignment.CENTRE);
	            ws.setRowView(1, 500);

	            for (int i = 0; i < 10; i++){
	                ws.addCell(new Label(1, i, "123", wcf));
	                ws.addCell(new Label(2, i, "334", wcf));
	                ws.addCell(new Label(3, i, "567", wcf));
	                ws.addCell(new Label(4, i, "123", wcf));
	                ws.addCell(new Label(5, i, "567", wcf));
	                ws.addCell(new Label(6, i, "789", wcf));
	                ws.addCell(new Label(7, i, "324", wcf));
	                
	                if(i == 0){
	                	ws.addCell(new Label(0, i, "序号", wcf)) ;
	                    wcf = new WritableCellFormat();
	                }else{
	                	ws.addCell(new Label(0, i, "" + i, wcf)) ;
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
