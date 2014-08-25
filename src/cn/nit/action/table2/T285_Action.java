package cn.nit.action.table2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Date;
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

import cn.nit.bean.table2.T285_Bean;
import cn.nit.dao.table2.T285_Dao;
import cn.nit.service.table2.T285_Service;
import cn.nit.util.JsonUtil;

public class T285_Action {
	
	private T285_Service T285_Service = new T285_Service();
	
	private T285_Bean T285_bean = new T285_Bean();
	
	private T285_Dao T285_Dao = new T285_Dao();
	
	/**  哪一年数据  */
	private String selectYear;

	/**  导出的excelName名 */
	private String excelName ;

	
	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	
	//查询出所有
	
	public void loadInfo() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse() ;		
		
		List<T285_Bean> list = T285_Service.getYearInfo(this.getSelectYear());
		
		//System.out.println(this.getSelectYear());
		//System.out.println(list.size());
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
	
	/**  编辑数据  */
	public void edit(){

		T285_Bean oldBean = T285_Service.findBySeqNum(T285_bean.getSeqNumber());
		String teaUnitName = "全校合计：";
		T285_Bean sumBean = T285_Service.findSumBean(teaUnitName,this.getSelectYear());
		
		sumBean.setSumEquNum(sumBean.getSumEquNum()+(T285_bean.getSumEquNum()-oldBean.getSumEquNum()));
		sumBean.setAboveTenEquNum(sumBean.getAboveTenEquNum()+(T285_bean.getAboveTenEquNum()-oldBean.getAboveTenEquNum()));
		sumBean.setSumEquAsset(sumBean.getSumEquAsset()+(T285_bean.getSumEquAsset()-oldBean.getSumEquAsset()));
		sumBean.setNewAddAsset(sumBean.getNewAddAsset()+(T285_bean.getNewAddAsset()-oldBean.getNewAddAsset()));
		sumBean.setAboveTenEquAsset(sumBean.getAboveTenEquAsset()+(T285_bean.getAboveTenEquAsset()-oldBean.getAboveTenEquAsset()));
		
		T285_bean.setTime(oldBean.getTime());
		boolean flag = T285_Service.update(T285_bean) ;		
		boolean flag0 = T285_Service.update(sumBean) ;
		
		PrintWriter out = null ;
	
		try{
			response.setContentType("text/html; charset=UTF-8") ;
			out = response.getWriter() ;
			if(flag&&flag0){
				out.print("{\"state\":true,data:\"修改成功!!!\"}") ;
			}else{
				out.print("{\"state\":true,data:\"修改失败!!!\"}") ;
			}
			out.flush() ;
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"系统错误，请联系管理员!!!\"}") ;
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
			List<T285_Bean> list=T285_Dao.totalList(this.getSelectYear());
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
			columns.add("序号");columns.add("教学单位");columns.add("单位号");columns.add("总量");
			columns.add("单价10万元以上");columns.add("总量");columns.add("当年新增值");columns.add("单价10万元以上");
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("SeqNum", 0);maplist.put("teaUnit", 1);maplist.put("unitID", 2);maplist.put("sumEquNum", 3);
			maplist.put("aboveTenEquNum", 4);maplist.put("sumEquAsset", 5);maplist.put("newAddAsset", 6);
			maplist.put("aboveTenEquAsset", 7);
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
		                	
		                	if(i<3){
		                		ws.addCell(new Label(i, 2, columns.get(i), wcf));
		                		ws.mergeCells(i, 2, i, 3);
		                	}
		                	else if(i==3){
		                		ws.addCell(new Label(i, 2, "1.教学、科研仪器设备台数（台）", wcf));
		                		ws.mergeCells(3, 2, 4, 2);
		                		ws.addCell(new Label(i, 3, columns.get(i), wcf));
		                	}
		                	else if(i==5){
		                		ws.addCell(new Label(i, 2, "2.教学、科研仪器设备值（万元）", wcf));
		                		ws.mergeCells(5, 2, 7, 2);
		                		ws.addCell(new Label(i, 3, columns.get(i), wcf));
		                	}
		                	else{
		                		ws.addCell(new Label(i, 3, columns.get(i), wcf));
		                	}		                			                	
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
		                        		ws.addCell(new Label(0,i+3,""+(i-1),wcf1)); 
		                        		continue;
		                        	}
		                        	                        	
		        					String type = wrapper.getPropertyType(column).toString() ;
//		        					System.out.println(type +"-test：" + column);

		        					//判断插入数据的类型，并赋�?
		        					if(type.endsWith("String")){
		        						if(wrapper.getPropertyValue(column) == null){
		        							continue;
		        						}
		        						if(((String) wrapper.getPropertyValue(column)).equals("全校合计：")){
		        							ws.addCell(new Label(maplist.get(column).intValue()-1,i+3,(String) wrapper.getPropertyValue(column),wcf1));
		        							ws.mergeCells(0,i+3,3,i+3);
		        						}else{
		        							ws.addCell(new Label(maplist.get(column).intValue(),i+3,(String) wrapper.getPropertyValue(column),wcf1));
		        						}
		        						
		        					}else if(type.endsWith("int")||type.endsWith("Integer")){
		        						ws.addCell(new Label(maplist.get(column).intValue(),i+3,(String) wrapper.getPropertyValue(column).toString(),wcf1));
		        					}
		        					else if(type.endsWith("double")||type.endsWith("Double")){
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

	public T285_Bean getT285_bean() {
		return T285_bean;
	}

	public void setT285_bean(T285_Bean T285Bean) {
		T285_bean = T285Bean;
	}

	public static void main(String arg[]){
		T285_Action s=new T285_Action();
		 		
	}
	
	
}