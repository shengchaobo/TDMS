package cn.nit.action.table6;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanWrapperImpl;

import cn.nit.bean.UserinfoBean;
import cn.nit.bean.table2.T285_Bean;
import cn.nit.bean.table4.T441_Bean;
import cn.nit.bean.table6.T611_Bean;
import cn.nit.bean.table6.T612_Bean;
import cn.nit.bean.table6.T613_Bean;
import cn.nit.bean.table6.T614_Bean;
import cn.nit.bean.table6.T615_Bean;
import cn.nit.bean.table6.T617_Bean;
import cn.nit.bean.table6.T621_Bean;
import cn.nit.bean.table6.T622_Bean;
import cn.nit.bean.table6.T631_Bean;
import cn.nit.bean.table6.T632_Bean;
import cn.nit.bean.table6.T641_Bean;
import cn.nit.bean.table6.T651_Bean;
import cn.nit.bean.table6.T652_Bean;
import cn.nit.bean.table6.T653_Bean;
import cn.nit.bean.table6.T654_Bean;
import cn.nit.bean.table6.T655_Bean;
import cn.nit.bean.table6.T657_Bean;
import cn.nit.bean.table6.T658_Bean;
import cn.nit.bean.table6.T659_Bean;
import cn.nit.constants.Constants;
import cn.nit.dao.table6.T611_Dao;
import cn.nit.dao.table6.T612_Dao;
import cn.nit.dao.table6.T613_Dao;
import cn.nit.dao.table6.T614_Dao;
import cn.nit.dao.table6.T615_Dao;
import cn.nit.dao.table6.T617_Dao;
import cn.nit.dao.table6.T622_Dao;
import cn.nit.dao.table6.T632_Dao;
import cn.nit.dao.table6.T641_Dao;
import cn.nit.dao.table6.T651_Dao;
import cn.nit.dao.table6.T652_Dao;
import cn.nit.dao.table6.T653_Dao;
import cn.nit.dao.table6.T654_Dao;
import cn.nit.dao.table6.T655_Dao;
import cn.nit.dao.table6.T657_Dao;
import cn.nit.dao.table6.T658_Dao;
import cn.nit.dao.table6.T659_Dao;
import cn.nit.dbconnection.DBConnection;
import cn.nit.service.CheckService;
import cn.nit.service.table6.T611_Service;
import cn.nit.service.table6.T612_Service;
import cn.nit.service.table6.T613_Service;
import cn.nit.service.table6.T614_Service;
import cn.nit.service.table6.T615_Service;
import cn.nit.service.table6.T617_Service;
import cn.nit.service.table6.T622_Service;
import cn.nit.service.table6.T632_Service;
import cn.nit.service.table6.T641_Service;
import cn.nit.service.table6.T651_Service;
import cn.nit.service.table6.T652_Service;
import cn.nit.service.table6.T653_Service;
import cn.nit.service.table6.T654_Service;
import cn.nit.service.table6.T655_Service;
import cn.nit.service.table6.T657_Service;
import cn.nit.service.table6.T658_Service;
import cn.nit.service.table6.T659_Service;
import cn.nit.util.DAOUtil;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;

/**
 * 待完成！！！！！！！
 * 
 * @author Yuan
 */
public class T659_Action {

	/** 表的Service类 */
	private T659_Service T659_service = new T659_Service();
	private CheckService check_services = new CheckService();

	/** 表的Bean实体类 */
	T659_Bean T659_bean = new T659_Bean();
	
//	T659_Dao T659_dao = new T659_Dao();

	/**  哪一年数据  */
	private String selectYear;

	/**  导出的excelName名 */
	private String excelName ;
	
	/**  审核状态显示判别标志  */
	private int checkNum ;
	
	
	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	
	UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
	String fillUnitID = bean.getUnitID();
	
	
		//查询出所有
		public void loadInfo() throws Exception{
			HttpServletResponse response = ServletActionContext.getResponse() ;		
			
			List<T659_Bean> list = T659_service.getYearInfo(this.getSelectYear());
			
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

			int tag = 1;
			
			boolean flag0 = false;
			boolean flag1 = false;
			String fillId=T659_bean.getUnitId();
//			System.out.println("填写的单位："+fillId);
//			System.out.println("用户单位："+fillUnitID);
			if(fillId.equals(fillUnitID)){
				T659_Bean oldBean = T659_service.findBySeqNum(T659_bean.getSeqNumber());
				String teaUnitName = "全校合计：";
				T659_Bean sumBean = T659_service.findSumBean(teaUnitName,this.getSelectYear());
				//首次编辑待审核
				if(sumBean.getCheckState() == 0){
					sumBean.setCheckState(Constants.WAIT_CHECK);
				}
				//审核不通过再编辑待审核
				else if(sumBean.getCheckState() == Constants.NOPASS_CHECK){
					sumBean.setCheckState(Constants.WAIT_CHECK);
					int year = Integer.parseInt(this.getSelectYear());
					check_services.delete("T659", year);
					tag = 2;
				}
				sumBean.setExchangeStuSum(sumBean.getExchangeStuSum()+(T659_bean.getExchangeStuSum()-oldBean.getExchangeStuSum()));
				sumBean.setFromDomesticToSch(sumBean.getFromDomesticToSch()+(T659_bean.getFromDomesticToSch()-oldBean.getFromDomesticToSch()));
				sumBean.setFromOverseasToSch(sumBean.getFromOverseasToSch()+(T659_bean.getFromOverseasToSch()-oldBean.getFromOverseasToSch()));
				sumBean.setFromSchToDomestic(sumBean.getFromSchToDomestic()+(T659_bean.getFromSchToDomestic()-oldBean.getFromSchToDomestic()));
				sumBean.setFromSchToOverseas(sumBean.getFromSchToOverseas()+(T659_bean.getFromSchToOverseas()-oldBean.getFromSchToOverseas()));
				
				T659_bean.setTime(oldBean.getTime());
				flag0 = T659_service.update(T659_bean) ;		
				flag1 = T659_service.update(sumBean) ;
				
			}else{
				tag =3;
			}
			
			PrintWriter out = null ;
		
			try{
				response.setContentType("text/html; charset=UTF-8") ;
				out = response.getWriter() ;
				if(tag == 1){
					if(flag0&&flag1){
						out.print("{\"state\":true,data:\"编辑成功!!!\"}") ;
					}else{
						out.print("{\"state\":true,data:\"编辑成功!!!\"}") ;
					}				
				}
				else if(tag == 2){
					if(flag0&&flag1){
						out.print("{\"state\":true,data:\"修改成功!!!\",tag:2}") ;
					}else{
						out.print("{\"state\":true,data:\"修改成功!!!\"}") ;
					}		
				}else if(tag == 3){
					out.print("{\"state\":true,data:\"您无权限修改该教学单位数据!!!\",tag:3}") ;
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
			String UnitName = "全校合计：";
			boolean flag = T659_service.updateCheck(this.getSelectYear(), UnitName, this.getCheckNum());
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
		

	

		public InputStream getInputStream(){
			
			InputStream inputStream = null ;
			ByteArrayOutputStream fos = new ByteArrayOutputStream();
			
			UserinfoBean userBean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
			String sheetName = null;
			List<T659_Bean> list = null;
			
			if("111".equals(userBean.getRoleID())){
				String year = (String)request.getSession().getAttribute("allYear") ;
				list = T659_service.totalList(year,Constants.PASS_CHECK);
				sheetName = "表6-5-9本科生交流情况（教学单位-国际交流与合作处）";
			}else{					
				list = T659_service.totalList(this.getSelectYear(),Constants.PASS_CHECK);						
				sheetName = this.excelName;
			}
			
			
			try {
				List<String> columns = new ArrayList<String>();
				columns.add("序号");columns.add("教学单位");columns.add("单位号");
				columns.add("总数");columns.add("本校到境外");columns.add("本校到境内");columns.add("境内到本校");
				columns.add("境外到本校");
				Map<String,Integer> maplist = new HashMap<String,Integer>();
				maplist.put("SeqNum", 0);maplist.put("teaUnit", 1);maplist.put("unitId", 2);maplist.put("exchangeStuSum", 3);
				maplist.put("fromSchToOverseas", 4);maplist.put("fromSchToDomestic", 5);maplist.put("fromDomesticToSch", 6);
				maplist.put("fromOverseasToSch", 7);
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
			                	
			                	else if(i>=3 && i<8){
			                		ws.addCell(new Label(i, 3, columns.get(i), wcf));
			                	}               			                	
			                } 
			                ws.addCell(new Label(3, 2, "交流学生数（个）", wcf));
			                ws.mergeCells(3,2,7,2);
			                
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
//			        					System.out.println(type +"-test：" + column);

			        					//判断插入数据的类型，并赋�?
			        					if(type.endsWith("String")){
			        						if(wrapper.getPropertyValue(column) == null){
			        							continue;
			        						}
			        						if(((String) wrapper.getPropertyValue(column)).equals("全校合计：")){
			        							ws.addCell(new Label(maplist.get(column).intValue()-1,i+3,(String) wrapper.getPropertyValue(column),wcf1));
			        							ws.mergeCells(0,i+3,2,i+3);
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
			System.out.println("excelName=============" + this.excelName) ;
			return "success" ;
		}

	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public HttpSession getSession() {
		return getRequest().getSession();
	}

	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	

	public T659_Service getT659_service() {
		return T659_service;
	}

	public void setT659_service(T659_Service t631Service) {
		T659_service = t631Service;
	}

	public T659_Bean getT659_bean() {
		return T659_bean;
	}

	public void setT659_bean(T659_Bean T659Bean) {
		T659_bean = T659Bean;
	}



	public String getSelectYear() {
		return selectYear;
	}

	public void setSelectYear(String selectYear) {
		this.selectYear = selectYear;
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

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}
	public int getCheckNum() {
		return checkNum;
	}

	public void setCheckNum(int checkNum) {
		this.checkNum = checkNum;
	}

	public static void main(String args[]) {
		String match = "[\\d]+";
		System.out.println("23gfhf4".matches(match));
	}




}
