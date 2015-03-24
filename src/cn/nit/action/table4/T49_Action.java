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
import java.util.Calendar;
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
import cn.nit.bean.table4.T48_Bean;
import cn.nit.bean.table4.T49_Bean;
import cn.nit.constants.Constants;
import cn.nit.dao.table4.T48_Dao;
import cn.nit.dao.table4.T49_Dao;
import cn.nit.service.CheckService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table4.T49_Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;


public class T49_Action {
	
	private String rows; //每页显示的记录数
	
	private String page; //当前第几页
	
	private T49_Service T49_services = new T49_Service();
	
	private CheckService check_services = new CheckService();
	
	/**  部门管理Service类  */
	private DiDepartmentService deSer = new DiDepartmentService() ;
	
	private T49_Bean T49_bean = new T49_Bean();
	
//	private T49_Dao T49_dao = new T49_Dao();
	
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
	
	/**  审核状态显示判别标志  */
	private int checkNum ;
	
	/**  导出时间  */
	private String selectYear ;
	
	/**  审核通过数据按年时间查询  */
	private String queryYear ;
	public String getQueryYear() {
		return queryYear;
	}

	public void setQueryYear(String queryYear) {
		this.queryYear = queryYear;
	}



	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	
	
	//查询出所有
	public void loadTextInfo() throws Exception{
		
		HttpServletResponse response = ServletActionContext.getResponse() ;	
		
		String cond = null;
		StringBuffer conditions = new StringBuffer();
		
		if(this.getSeqNum() == null && this.getStartTime() == null && this.getEndTime() == null&& this.getCheckNum() == 0){							
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
			
			//审核状态判断
			if(this.getCheckNum() == Constants.WAIT_CHECK ){
				conditions.append(" and CheckState=" + this.getCheckNum()) ;
			}else if(this.getCheckNum() == (Constants.PASS_CHECK)){
				conditions.append(" and CheckState=" + this.getCheckNum()) ;
				if(this.getQueryYear() != null){
					conditions.append(" and Time like '" + this.queryYear + "%'");
				}else{
					 Calendar now = Calendar.getInstance();  
					 this.setQueryYear(now.get(Calendar.YEAR)+"");
					 conditions.append(" and Time like '" + this.queryYear + "%'");
				}
			}else if(this.getCheckNum() == (Constants.NOPASS_CHECK)){
				conditions.append(" and CheckState=" + this.getCheckNum()) ;
			}else if(this.getCheckNum() == (Constants.NO_CHECK)){
				conditions.append(" and CheckState!=" + Constants.PASS_CHECK) ;
			}
			
			cond = conditions.toString();
		}
		
		//具体教学单位
		UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		String fillUnitID;
		String tempUnitID = bean.getUnitID().substring(0,1);
		if("3".equals(tempUnitID)){
			fillUnitID = bean.getUnitID();
		}else{
			fillUnitID = null;
		}
		
		List<T49_Bean> list = T49_services.getPagetextList(cond, fillUnitID, this.getRows(), this.getPage()) ;
		String TeaInfoJson = this.toBeJson(list,T49_services.getTotal(cond, fillUnitID));
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
	private String toBeJson(List<T49_Bean> list, int total) throws Exception{
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
		T49_bean.setTime(new Date());
		
		//插入审核状态
		T49_bean.setCheckState(Constants.WAIT_CHECK);
		//插入教学单位
		UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		String fillUnitID = bean.getUnitID();
		T49_bean.setFillUnitID(fillUnitID);
		
		String unitName = deSer.getName(fillUnitID);
		T49_bean.setTeaUnit(unitName);
		T49_bean.setUnitId(fillUnitID);
		
		T49_bean.setSumPlanBook(T49_bean.getInterPlanBook()+T49_bean.getNationPlanBook()+T49_bean.getProviPlanBook()+T49_bean.getCityPlanBook()+T49_bean.getSchPlanBook());
		T49_bean.setSumAwardBook(T49_bean.getInterAwardBook()+T49_bean.getNationAwardBook()+T49_bean.getProviAwardBook()+T49_bean.getCityAwardBook()+T49_bean.getSchAwardBook());	
		boolean flag = T49_services.insert(T49_bean);
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
		
		T49_bean.setSumPlanBook(T49_bean.getInterPlanBook()+T49_bean.getNationPlanBook()+T49_bean.getProviPlanBook()+T49_bean.getCityPlanBook()+T49_bean.getSchPlanBook());
		T49_bean.setSumAwardBook(T49_bean.getInterAwardBook()+T49_bean.getNationAwardBook()+T49_bean.getProviAwardBook()+T49_bean.getCityAwardBook()+T49_bean.getSchAwardBook());
			
		boolean flag = false;
		
		int tag = 0;
		//获得该条数据审核状态
		int state = T49_services.getCheckState(T49_bean.getSeqNumber());
		//如果审核状态是待审核，则直接修改
		if(state == Constants.WAIT_CHECK){
			T49_bean.setCheckState(Constants.WAIT_CHECK);
			flag = T49_services.update(T49_bean) ;
			if(flag) tag = 1;
		}
		//如果是审核不通过，则修改该条数据，并将审核状态调节为待审核，同时删除该条数据在checkInfo表的信息
		if(state == Constants.NOPASS_CHECK){
			T49_bean.setCheckState(Constants.WAIT_CHECK);
			boolean flag1 = T49_services.update(T49_bean) ;
			boolean flag2 = check_services.delete("T49",T49_bean.getSeqNumber());
			if(flag1&&flag2){
				flag = true;
				tag = 2;
			}
		}
		
		PrintWriter out = null ;
	
		try{
			response.setContentType("text/html; charset=UTF-8") ;
			out = response.getWriter() ;
			if(tag == 1){
				out.print("{\"state\":true,data:\"修改成功!!!\"}") ;
			}
			else if(tag == 2){
				out.print("{\"state\":true,data:\"修改成功!!!\",tag:2}") ;
			}
			else{
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
		boolean flag = T49_services.deleteByIds(ids) ;
		//删除审核不通过信息
		check_services.delete("T49", ids);
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
	
	/**  修改某条数据的审核状态  */
	public void updateCheck(){
		HttpServletResponse response = ServletActionContext.getResponse();
	
		boolean flag = T49_services.updateCheck(this.getSeqNum(),this.getCheckNum());
		PrintWriter out = null ;
		
		try{
			response.setContentType("text/html; charset=UTF-8") ;
			out = response.getWriter() ;
			if(flag){
				out.print("{\"state\":true,data:\"修改审核状态成功!!!\"}") ;
			}else{
				out.print("{\"state\":false,data:\"修改审核状态失败!!!\"}") ;
			}
			out.flush() ;
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"修改审核状态失败!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
	/**  全部审核通过  */
	public void checkAll(){
		HttpServletResponse response = ServletActionContext.getResponse();
	
		boolean flag = T49_services.checkAll();
		PrintWriter out = null ;
		
		try{
			response.setContentType("text/html; charset=UTF-8") ;
			out = response.getWriter() ;
			if(flag){
				out.print("{\"state\":true,data:\"一键审核成功!!!\"}") ;
			}else{
				out.print("{\"state\":false,data:\"一键审核失败!!!\"}") ;
			}
			out.flush() ;
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"一键审核失败!!!\"}") ;
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
			
		List<T49_Bean> list = T49_services.totalList(fillUnitID,this.getSelectYear(),Constants.PASS_CHECK);
						
		String sheetName = this.excelName;
			
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
	            	   if(i<5){
	            		   ws.addCell(new Label(i, 2, columns.get(i), wcf)); 
	            		   ws.mergeCells(i, 2, i, 3);
	            	   }
	                    	                    
	                   if(i>=5){
	                	   if(i==5){
	                		   ws.addCell(new Label(i, 2, "其中规划教材", wcf));
	                		   ws.mergeCells(5, 2, 10, 2);
	                	   }
	                	   
	                	   ws.addCell(new Label(i, 3, columns.get(i), wcf)); 	
	                	   
	                	   if(i==11){
	                		   ws.addCell(new Label(i, 2, "其中获奖教材", wcf));
	                		   ws.mergeCells(11, 2, 16, 2);
	                	   }
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
	
	public T49_Bean getT49_bean() {
		return T49_bean;
	}

	public void setT49_bean(T49_Bean T49Bean) {
		T49_bean = T49Bean;
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

	public void setCheckNum(int checkNum) {
		this.checkNum = checkNum;
	}

	public int getCheckNum() {
		return checkNum;
	}

	public void setSelectYear(String selectYear) {
		this.selectYear = selectYear;
	}

	public String getSelectYear() {
		return selectYear;
	}
}