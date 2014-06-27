package cn.nit.action.table4;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
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
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanWrapperImpl;

import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.table4.T410_Bean;
import cn.nit.bean.table4.T49_Bean;
import cn.nit.dao.table4.T410_Dao;
import cn.nit.dao.table4.T49_Dao;
import cn.nit.service.table4.T410_Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;

public class T410_Action {
	
	private String rows; //每页显示的记录数
	
	private String page; //当前第几页
	
	private T410_Service T410_services = new T410_Service();
	
	private T410_Bean T410_bean = new T410_Bean();
	
	private T410_Dao T410_dao = new T410_Dao();
	
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
	
	
	//查询出所有教师信息
	public void loadResInfo() throws Exception{
		
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
		List<T410_Bean> list = T410_services.getPageResList(cond, null, this.getRows(), this.getPage()) ;
		String TeaInfoJson = this.toBeJson(list,T410_services.getTotal(cond, null));
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
	private String toBeJson(List<T410_Bean> list, int total) throws Exception{
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
	
/*
	//查出所有
	public void loadT410() throws Exception{
		
		List<T410_Bean> list = T410_services.getList();
		//将数据转换为json格式
		JSON json = JSONSerializer.toJSON(list) ;
		PrintWriter out = null ;
		
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
	}*/
	
	//插入一个新的教职工
	public void insert(){
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		HttpServletResponse response = ServletActionContext.getResponse();
		//插入时间
		T410_bean.setTime(new Date());
		
		T410_bean.setResItemNum(T410_bean.getHresItemNum()+T410_bean.getZresItemNum());
/*		System.out.println(T410_bean.getHhumanItemFund());
		System.out.println(T410_bean.getZitemFund());
		System.out.println(T410_bean.getHitemFund());
		System.out.println(T410_bean.getSci());*/
		//T410_bean.setResItemFund(0.0);
		//T410_bean.setIstp(1);
		T410_bean.setResAwardNum(T410_bean.getNationResAward()+T410_bean.getProviResAward()+T410_bean.getCityResAward()+T410_bean.getSchResAward());
		T410_bean.setResItemFund(T410_bean.getHitemFund()+T410_bean.getZitemFund());
		T410_bean.setPaperNum(T410_bean.getSci()+T410_bean.getSsci()+T410_bean.getEi()+T410_bean.getCscd()+T410_bean.getIstp()+T410_bean.getOtherPaper()+T410_bean.getCssci()+T410_bean.getInlandCoreJnal());
		T410_bean.setPublicationNum(T410_bean.getTranslation()+T410_bean.getTreatises());
		T410_bean.setPatentNum(T410_bean.getDesignPatent()+T410_bean.getUtilityPatent()+T410_bean.getInventPatent());
		
		System.out.println("test");
		boolean flag = T410_services.insert(T410_bean);
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
		
		T410_bean.setResItemNum(T410_bean.getHresItemNum()+T410_bean.getZresItemNum());
		/*		System.out.println(T410_bean.getHhumanItemFund());
				System.out.println(T410_bean.getZitemFund());
				System.out.println(T410_bean.getHitemFund());
				System.out.println(T410_bean.getSci());*/
				//T410_bean.setResItemFund(0.0);
				//T410_bean.setIstp(1);
		T410_bean.setResAwardNum(T410_bean.getNationResAward()+T410_bean.getProviResAward()+T410_bean.getCityResAward()+T410_bean.getSchResAward());
		T410_bean.setResItemFund(T410_bean.getHitemFund()+T410_bean.getZitemFund());
		T410_bean.setPaperNum(T410_bean.getSci()+T410_bean.getSsci()+T410_bean.getEi()+T410_bean.getCscd()+T410_bean.getIstp()+T410_bean.getOtherPaper()+T410_bean.getCssci()+T410_bean.getInlandCoreJnal());
		T410_bean.setPublicationNum(T410_bean.getTranslation()+T410_bean.getTreatises());
		T410_bean.setPatentNum(T410_bean.getDesignPatent()+T410_bean.getUtilityPatent()+T410_bean.getInventPatent());
		boolean flag = T410_services.update(T410_bean) ;
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
		boolean flag = T410_services.deleteByIds(ids) ;
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

		List<T410_Bean> list = T410_dao.totalList();
		
		String sheetName = this.getExcelName();
			
		List<String> columns = new ArrayList<String>();
		columns.add("序号");
		columns.add("教学单位");columns.add("单位号");columns.add("教师编著教材数");columns.add("教师编写教材数");
		columns.add("小计");columns.add("国际级");columns.add("国家级");columns.add("省部级");
		columns.add("市级");columns.add("校级");
		columns.add("小计");columns.add("国际级");columns.add("国家级");columns.add("省部级");
		columns.add("市级");columns.add("校级");
			
		Map<String,Integer> maplist = new HashMap<String,Integer>();
		maplist.put("SeqNum", 0);
		maplist.put("teaUnit", 1);maplist.put("unitId", 2);maplist.put("complileBookNum", 3);maplist.put("writeBookNum", 4);
		maplist.put("sumPlanBook", 5);maplist.put("interPlanBook", 6);maplist.put("nationPlanBook", 7);maplist.put("proviPlanBook", 8);
		maplist.put("cityPlanBook", 9);maplist.put("schPlanBook", 10);
		maplist.put("sumAwardBook", 11);maplist.put("interAwardBook", 12);maplist.put("nationAwardBook", 13);maplist.put("proviAwardBook", 14);
		maplist.put("cityAwardBook", 15);maplist.put("schAwardBook", 16);
			
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
	            	   if(i<5){
	            		   ws.addCell(new Label(i, 0, columns.get(i), wcf)); 
	            		   ws.mergeCells(i, 0, i, 1);
	            	   }
	                    	                    
	                   if(i>=5){
	                	   if(i==5){
	                		   ws.addCell(new Label(i, 0, "其中规划教材", wcf));
	                		   ws.mergeCells(5, 0, 10, 0);
	                	   }
	                	   
	                	   ws.addCell(new Label(i, 1, columns.get(i), wcf)); 	
	                	   
	                	   if(i==11){
	                		   ws.addCell(new Label(i, 0, "其中获奖教材", wcf));
	                		   ws.mergeCells(11, 0, 16, 0);
	                	   }
	                   }
	                   	                   
	                }
	               	
	           }
	  
	                //判断表中是否有数据  
	            if (list != null && list.size() > 0) {  
	                    //循环写入表中数据  
	                	BeanWrapperImpl wrapper = new BeanWrapperImpl() ;
	                	int i=2;  
	                	for(Object obj : list){  
	                		wrapper.setWrappedInstance(obj) ;  
	                        //循环输出map中的子集：既列值                         
	                        for(String column:maplist.keySet()){
	                        	
	                        	if(column.equals("SeqNum")){
	                        		ws.addCell(new Label(0,i,""+(i-1))); 
	                        		continue;
	                        	}
	                        	                        	
	        					String type = wrapper.getPropertyType(column).toString() ;
	        					//System.out.println(type +"test" + column);

	        					//判断插入数据的类型，并赋�?
	        					if(type.endsWith("String")){
	        						ws.addCell(new Label(maplist.get(column).intValue(),i,(String) wrapper.getPropertyValue(column)));
	        					}else if(type.endsWith("int")||type.endsWith("Integer")){
	        						ws.addCell(new Label(maplist.get(column).intValue(),i,(String) wrapper.getPropertyValue(column).toString()));
	        					}else if(type.endsWith("Date")){
	        						if((java.util.Date)wrapper.getPropertyValue(column) == null){
	        							ws.addCell(new Label(maplist.get(column).intValue(),i,null));
	        						}else{
	            						java.util.Date utilDate = (java.util.Date)wrapper.getPropertyValue(column) ;
	            						Date sqlDate = new Date(utilDate.getTime()) ;
	            						ws.addCell(new Label(maplist.get(column).intValue(),i,sqlDate.toString()));
	        						}
	        					}else if(type.endsWith("long")||type.endsWith("Long")){
	        						ws.addCell(new Label(maplist.get(column).intValue(),i,(String) wrapper.getPropertyValue(column).toString()));
	        					}else if(type.endsWith("boolean")||type.endsWith("Boolean")){
	        						if((Boolean)wrapper.getPropertyValue(column)){
	        							ws.addCell(new Label(maplist.get(column).intValue(),i,"是"));
	        						}else{
	        							ws.addCell(new Label(maplist.get(column).intValue(),i,"否"));
	        						}
	        					}else if(type.endsWith("double")||type.endsWith("Double")){
	        						ws.addCell(new Label(maplist.get(column).intValue(),i,(String) wrapper.getPropertyValue(column).toString()));
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
		request.setCharacterEncoding("UTF-8") ;
		System.out.println("excelName=============" + excelName) ;
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
	
	public T410_Bean getT410_bean() {
		return T410_bean;
	}

	public void setT410_bean(T410_Bean T410Bean) {
		T410_bean = T410Bean;
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
		return excelName;
	}
}