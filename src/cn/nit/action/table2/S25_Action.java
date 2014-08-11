package cn.nit.action.table2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanWrapperImpl;

import cn.nit.bean.table2.S25_Bean;
import cn.nit.dao.table2.S25_Dao;
import cn.nit.service.table2.S25_Service;

public class S25_Action {
	
	private S25_Service s25_Service=new S25_Service();
	
	private S25_Bean s25_Bean=new S25_Bean();
	
	private S25_Dao s25_Dao=new S25_Dao();
	
	/**  哪一年数据  */
	private String selectYear;

	/**  导出的excelName名 */
	private String excelName ;
	

	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	
	//查询出所有
	
	public void loadInfo() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse() ;		
		
		List<S25_Bean> list=s25_Service.getYearInfo(this.getSelectYear());
		System.out.println(this.getSelectYear());
		System.out.println(list.size());
		JSON json = JSONSerializer.toJSON(list) ;
		PrintWriter out = null ;
		//System.out.println(json.toString());
		try {
			//设置输出内容的格式为json
			response.setContentType("application/json; charset=UTF-8") ;
			out = response.getWriter() ;
			//设置数据的内容的编码格式
			String outPrint = URLDecoder.decode(json.toString(), "UTF-8") ;
			out.print(outPrint) ;
			out.flush() ;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
	public InputStream getInputStream(){
		
		InputStream inputStream = null ;
		ByteArrayOutputStream fos = new ByteArrayOutputStream();
		try {
			List<S25_Bean> list=s25_Dao.totalList(this.getSelectYear());
			if(list.size()==0){
				PrintWriter out = null ;
				response.setContentType("text/html;charset=utf-8") ;
				out = response.getWriter() ;
				out.print("后台传入的数据为空") ;
				System.out.println("后台传入的数据为空");
				return null;
			}
			String sheetName = this.getExcelName();
			
			List<String> columns = new ArrayList<String>();
			columns.add("序号");
			columns.add("实验中心名称");columns.add("所属教学单位");columns.add("教学单位号");
			columns.add("台件数量");columns.add("金额（元）");columns.add("面积（平方米）");columns.add("其中当年新增面积（平方米）");
			columns.add("每次实验教学学时数");columns.add("每次可容纳的学生数（个）");columns.add("学年度承担的实验教学人时数（人时）");
			columns.add("学年度承担的实验教学人次数（人次）");columns.add("本科生实验、实习、实训项目数（个）");
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("SeqNum", 0);
			maplist.put("expCenterName", 1);maplist.put("teaUnit", 2);maplist.put("unitID", 3);
			maplist.put("machNum", 4);maplist.put("money", 5);maplist.put("area", 6);maplist.put("newArea", 7);
			maplist.put("labHour", 8);maplist.put("labStuNum", 9);maplist.put("yearHour", 10);maplist.put("yearTimes", 11);
			maplist.put("itemNum", 12);	
		
			WritableWorkbook wwb;
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
					//第一行存表名
					ws.addCell(new Label(0, 0, sheetName, wcf)); 
					ws.mergeCells(0, 0, 1, 0);
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
		                
		            }
		            //判断表中是否有数据  
		            if (list != null && list.size() > 0) {  
		                    //循环写入表中数据  
		                	BeanWrapperImpl wrapper = new BeanWrapperImpl() ;
		                	int i=0;  
		                	for(Object obj : list){  
		                		wrapper.setWrappedInstance(obj) ;  
		                        //循环输出map中的子集：既列值                         
		                        for(String column:maplist.keySet()){
		                        	
		                        	if(column.equals("SeqNum")){
		                        		if(i!=0) {
		                        			ws.addCell(new Label(0,i+3,""+i,wcf1)); 
		                        		}		                        		
		                        		continue;
		                        	}
		                        	                        	
		        					String type = wrapper.getPropertyType(column).toString() ;
//		        					System.out.println(type +"-test：" + column);

		        					//判断插入数据的类型，并赋�?
		        					if(type.endsWith("String")){
		        						if(((String) wrapper.getPropertyValue(column)).equals("全校合计：")){
		        							ws.addCell(new Label(maplist.get(column).intValue()-1,i+3,(String) wrapper.getPropertyValue(column),wcf1));
		        							ws.mergeCells(0,i+3,3,i+3);
		        						}else{
		        							ws.addCell(new Label(maplist.get(column).intValue(),i+3,(String) wrapper.getPropertyValue(column),wcf1));
		        						}		        						
		        					}else if(type.endsWith("int")||type.endsWith("Integer")){
		        						ws.addCell(new Label(maplist.get(column).intValue(),i+3,(String) wrapper.getPropertyValue(column).toString(),wcf1));
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
		        
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null ;
		}
		inputStream = new ByteArrayInputStream(fos.toByteArray());
		return inputStream ;
	}
	public String execute() throws Exception{
		request.setCharacterEncoding("UTF-8") ;
		System.out.println("excelName=============" + excelName) ;
		return "success" ;
	}

	public String getSelectYear() {
		return selectYear;
	}

	public void setSelectYear(String selectYear) {
		this.selectYear = selectYear;
	}

	public String getExcelName() {
		return excelName;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}

	public S25_Bean getS25_Bean() {
		return s25_Bean;
	}

	public void setS25_Bean(S25_Bean s25_Bean) {
		this.s25_Bean = s25_Bean;
	}
	public static void main(String arg[]){
		S25_Action s=new S25_Action();
		 
		
	}
	
	
}
