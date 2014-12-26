package cn.nit.action.table4;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
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

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanWrapperImpl;

import cn.nit.bean.UserinfoBean;
import cn.nit.bean.table4.T4_11_Bean;
import cn.nit.dao.table4.T4_11_Dao;
import cn.nit.service.table4.T4_11_Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;


public class T4_11_Action {
	
	private String rows; //每页显示的记录数
	
	private String page; //当前第几页
	
	private T4_11_Service T4_11_services = new T4_11_Service();
	
	private T4_11_Bean T4_11_bean = new T4_11_Bean();
	
	private T4_11_Dao T4_11_dao = new T4_11_Dao();
	
	/**  待审核数据的要删除的序列集  */
	private String ids; //删除的id
	
	/**  待审核数据的查询的序列号  */
	private Integer seqNum ;
	
	/**  待审核数据查询的起始时间  */
	private Date startTime ;
	
	/**  待审核数据查询的结束时间  */
	private Date endTime ;
	
	/**  下载的excelName  */
	private String excelName ;

	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	
	
	//查询出所有
	public void loadSerInfo() throws Exception{
		
		HttpServletResponse response = ServletActionContext.getResponse() ;	
		
		String cond = null;
		StringBuffer conditions = new StringBuffer();
		
		if(this.getSeqNum() == null && this.getStartTime() == null && this.getEndTime() == null){			
			cond = null;	
		}else{			
			if(this.getSeqNum()!=null){
				conditions.append(" and SeqNumber=" + this.getSeqNum()) ;
			}
			
			if(this.getStartTime() != null){
				conditions.append(" and cast(CONVERT(DATE, Time)as datetime)>=cast(CONVERT(DATE, '" 
						+ TimeUtil.changeFormat4(this.startTime) + "')as datetime)") ;
			}
			
			if(this.getEndTime() != null){
				conditions.append(" and cast(CONVERT(DATE, Time)as datetime)<=cast(CONVERT(DATE, '" 
						+ TimeUtil.changeFormat4(this.getEndTime()) + "')as datetime)") ;
			}
			cond = conditions.toString();
		}
		
		//具体教学单位
		UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		String fillUnitID = bean.getUnitID();
		List<T4_11_Bean> list = T4_11_services.getPagetextList(cond, fillUnitID, this.getRows(), this.getPage()) ;
		String TeaInfoJson = this.toBeJson(list,T4_11_services.getTotal(cond, fillUnitID));
		//private JSONObject jsonObj;
		
		PrintWriter out = null ;

		if(TeaInfoJson == null){			
			return ;
		}else{
			try {
				
				System.out.println(TeaInfoJson) ;
				response.setContentType("application/json;charset=UTF-8") ;
				out = response.getWriter() ;
				out.print(TeaInfoJson) ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(out != null){
					out.flush() ;
					out.close() ;
				}
			}
		}
	}

    //将分页系统的总数以及当前页的list转化一个json传页面显示
	private String toBeJson(List<T4_11_Bean> list, int total) throws Exception{
		// TODO Auto-generated method stub
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		 

		JSONObject testjson = new JSONObject();
		testjson.accumulate("total", total);
		testjson.accumulate("rows", list);
		
        String json = testjson.toString();
        System.out.println(json) ;
		return json;
	}
	
	//插入一个新的
	public void insert(){
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		HttpServletResponse response = ServletActionContext.getResponse();
		
		//插入时间
		T4_11_bean.setTime(new Date());
		
		//具体教学单位
		UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		String fillUnitID = bean.getUnitID();
		
		T4_11_bean.setFillUnitID(fillUnitID);
			
		boolean flag = T4_11_services.insert(T4_11_bean);
		PrintWriter out = null ;
		
		try{
			response.setContentType("text/html; charset=UTF-8") ;
			out = response.getWriter() ;
			if(flag){
				out.print("{\"state\":true,data:\"录入成功!!!\"}") ;
			}else{
				out.print("{\"state\":false,data:\"录入失败!!!\"}") ;
			}
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"录入失败!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
		out.flush() ;
	}
	
	/**  编辑数据  */
	public void edit(){
		
		boolean flag = T4_11_services.update(T4_11_bean) ;
		PrintWriter out = null ;
	
		try{
			response.setContentType("text/html; charset=UTF-8") ;
			out = response.getWriter() ;
			if(flag){
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

	/**  根据数据的id删除数据  */
	public void deleteByIds(){
		System.out.println("ids=" + this.getIds()) ;
		boolean flag = T4_11_services.deleteByIds(ids) ;
		PrintWriter out = null ;
		
		try{
			
			
			response.setContentType("application/json; charset=UTF-8") ;
			out = response.getWriter() ;			
			if(flag){
				out.print("{\"state\":true,data:\"数据删除成功!!!\"}") ;
			}else{
				out.print("{\"state\":false,data:\"数据删除失败!!!\"}") ;
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
	
	public InputStream getInputStream() throws Exception{
		
		
		//具体教学单位
		UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		String fillUnitID = bean.getUnitID();
			
		List<T4_11_Bean> list = T4_11_dao.totalList(fillUnitID);
						
		String sheetName = this.excelName;
			
		List<String> columns = new ArrayList<String>();
		columns.add("序号");
		columns.add("教学单位");columns.add("单位号");columns.add("专利转让数量");columns.add("科技成果转化数量");
		columns.add("技术咨询采用次数");columns.add("兼任协（学）会职务人次数");columns.add("受聘学科竞赛评委/裁判人次数");
		columns.add("备注");
			
		Map<String,Integer> maplist = new HashMap<String,Integer>();
		maplist.put("SeqNum", 0);
		maplist.put("unitName", 1);maplist.put("unitId", 2);maplist.put("patentNum", 3);maplist.put("achieNum", 4);
		maplist.put("consNum", 5);maplist.put("partJobNum", 6);maplist.put("judgeNum", 7);maplist.put("note", 8);
			
	    WritableWorkbook wwb;
	    ByteArrayOutputStream fos = null;
	    try {    
	           fos = new ByteArrayOutputStream();
	           wwb = Workbook.createWorkbook(fos);
	           WritableSheet ws = wwb.createSheet(sheetName, 0);        // 创建一个工作表

	            //    设置单元格的文字格式
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
	                    	                    
	                   if(i>=3&&i<8){
	                	   if(i==3){
	                		   ws.addCell(new Label(i, 2, "1.成果转化", wcf));
	                		   ws.mergeCells(3, 2, 4, 2);
	                	   }
	                	   
	                	   ws.addCell(new Label(i, 3, columns.get(i), wcf)); 	
	                	   
	                	   if(i==5){
	                		   ws.addCell(new Label(i, 2, "2.社会工作", wcf));
	                		   ws.mergeCells(5, 2, 7, 2);
	                	   }	                	   
	                   }
	                   
	            	   if(i==8){
	            		   ws.addCell(new Label(i, 2, columns.get(i), wcf)); 
	            		   ws.mergeCells(i, 2, i, 3);
	            	   }
	                   	                   
	                }
	               	
	           }
	  
	                //判断表中是否有数据  
	            if (list != null && list.size() > 0) {  
	                    //循环写入表中数据  
	                	BeanWrapperImpl wrapper = new BeanWrapperImpl() ;
	                	int i=4;  
	                	for(Object obj : list){  
	                		wrapper.setWrappedInstance(obj) ;  
	                        //循环输出map中的子集：既列值                         
	                        for(String column:maplist.keySet()){
	                        	
	                        	if(column.equals("SeqNum")){
	                        		ws.addCell(new Label(0,i,""+(i-3),wcf1)); 
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
		return new ByteArrayInputStream(fos.toByteArray());
	}
	
	public String execute() throws Exception{
		System.out.println("excelName=============" + this.excelName) ;
		return "success" ;
	}


	public String getRows() {
		return rows;
	}


	public void setRows(String rows) {
		this.rows = rows;
	}


	public String getPage() {
		return page;
	}


	public void setPage(String page) {
		this.page = page;
	}
	
	public T4_11_Bean getT4_11_bean() {
		return T4_11_bean;
	}

	public void setT4_11_bean(T4_11_Bean T4_11Bean) {
		T4_11_bean = T4_11Bean;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getIds() {
		return ids;
	}

	public void setSeqNum(Integer seqNum) {
		this.seqNum = seqNum;
	}

	public Integer getSeqNum() {
		return seqNum;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}

	public String getExcelName() {
		try {
			this.excelName = URLEncoder.encode(excelName, "UTF-8");
			//this.saveFile = new String(saveFile.getBytes("ISO-8859-1"),"UTF-8");// 中文乱码解决
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return excelName;
	}
}