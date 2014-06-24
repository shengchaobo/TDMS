package cn.nit.excel.imports.table1;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import org.springframework.beans.BeanWrapperImpl;

import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.di.DiResearchTypeBean;
import cn.nit.bean.table1.T11Bean;

import cn.nit.dao.table1.T11DAO;
import cn.nit.dbconnection.DBConnection;
import cn.nit.service.table1.T11Service;
import cn.nit.util.DateUtil;
import cn.nit.util.TimeUtil;
import java.util.regex.Matcher;  

  

public class T11Excel {
	
	 /** 
     *  
     * 这个是读取模板写入数据 导出
     * **/  
	public  ByteArrayOutputStream writeExcel(List<T11Bean> list)
	{
		
		 ByteArrayOutputStream fos = null;
		 String path="/cn/nit/excel/downloads/T11.xls";
		 
		 String realpath = this.getClass().getResource("/" + path).getPath();
		 try {
			 realpath = URLDecoder.decode(realpath	, "UTF-8") ;
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//String realpath="E:\\Workspaces\\MyEclipse 8.5\\TDMS\\src\\cn\\nit\\excel\\downloads\\T11.xls";//选择模板文件
		try
		{
			
			   fos = new ByteArrayOutputStream();
			   Workbook wb = Workbook.getWorkbook(new File(realpath)); 
			  //第二步：通过模板得到一个可写的Workbook：第一个参数是一个输出流对象,第二个参数代表了要读取的模板
//			   File targetFile = new File("D:\\江西项目\\相关表\\try1.xls");
			   WritableWorkbook wwb = Workbook.createWorkbook(fos, wb); 
			   
			   //设置格式
			   WritableCellFormat normalFormat = new WritableCellFormat();
			   normalFormat.setBorder(Border.ALL, BorderLineStyle.THIN,
					     jxl.format.Colour.BLACK);
			   
			   
			   //第三步：选择模板中的Sheet：
			   WritableSheet wws = wwb.getSheet(0);
			   wws.setName("表1-1学校基本信息（党院办）"); // 给sheet页改名
			   wwb.removeSheet(1); // 移除多余的标签页
			   wwb.removeSheet(2);
//			   WritableSheet wws = wwb.getSheet(0); 
			   

	            int count_02=0;
	            List<String> listStr=this.changeTo(list);
			     
	            for(int j=1;j<20;j++){
//	            	   Label label = (Label)wws.getWritableCell(j,3);
//	            	   label.setString(listStr.get(count_02));
	            	Label label=new Label(3,j,listStr.get(count_02),normalFormat);
	            	wws.addCell(label);   
	            	count_02++;
	            }
	            wwb.write();
	            wwb.close();
	            wb.close(); 
			   
		}catch(Exception e){
			e.printStackTrace();
		}
		  return fos ;
		
	}
	
	/**
	 * 批量导入
	 * @param cellList {@link java.util.List<{@link jxl.Cell}>}  Cell是数组
	 * @param request  {@link javax.servlet.http.HttpServletRequest}
	 * @return
	 */
	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request){
		
		if((cellList == null) || (cellList.size() < 2)){
			return "数据不标准，请重新提交" ;
		}
		
		int count ;
		
//		boolean flag = false ;
//		boolean biOpen=false;
//		boolean buildCondi=false;
		List<T11Bean> list = new LinkedList<T11Bean>() ;
		T11Bean t11Bean = new  T11Bean();
		t11Bean.setTime(new Date());
//		UserRoleBean userinfo = (UserRoleBean)request.getSession().getAttribute("userinfo") ;
		
		for(Cell[] cell : cellList){
			
			int n=cellList.indexOf(cell);
			if(n==0){continue;}
			else{
			  try{
				  count=n;
//				  System.out.println("行数："+n);
				  switch(n){
				  case 1: String SchAddress = cell[3].getContents() ;
					 
					 if(SchAddress.equals("")||SchAddress==null){
						 return "第" + count + "行，学校地址不能为空" ;
					 }
					 if(SchAddress.length()>300){
						 return "第" + count + "行，学校地址不能超过150个字！" ;
					 }
					 t11Bean.setSchAddress(SchAddress);
					 break;
					 
				  case 2:String SchTel = cell[3].getContents();
				          
				          if(SchTel==null||SchTel.equals("")){
				        	  return "第" + count + "行，学校电话号码不能为空" ;
				          }
				          if(SchTel.length()<12){
								 return "第" + count + "行，学校电话号码不得少于12个字！" ;
							 }
				          if(!checkPhoneNr(SchTel)){
				        	  return "第" + count + "行，学校号码格式有误，有效格式：0791-82085793" ;
				          }
				          t11Bean.setSchTel(SchTel);
				          break;
				          
				  case 3:String SchFax = cell[3].getContents();
				         
						  if(SchFax==null||SchFax.equals("")){
				        	  return "第" + count + "行，学校传真号码不能为空" ;
				          }
				          if(SchFax.length()<12){
								 return "第" + count + "行，学校传真号码不得少于12个字！" ;
							 }
				          if(!checkPhoneNr(SchFax)){
				        	  return "第" + count + "行，学校传真号码格式有误，有效格式：0791-82085793" ;
				          }
				          t11Bean.setSchFax(SchFax);
				          break;
				          
				  case 4: String SchFillerName =cell[3].getContents();
				  
							  if(SchFillerName.equals("")||SchFillerName==null){
									 return "第" + count + "行，学校填报人名称不能为空" ;
								 }
							  if(SchFillerName.length()>50){
									 return "第" + count + "行，学校填报人名称不能超过25个字！" ;
								 }
								 t11Bean.setSchFillerName(SchFillerName);
								 break;
								 
				  case 5: String SchFillerTel = cell[3].getContents();
			         
						  if(SchFillerTel==null||SchFillerTel.equals("")){
				        	  return "第" + count + "行，填报人号码不能为空" ;
				          }
				          if(SchFillerTel.length()<12){
								 return "第" + count + "行，填报人号码不得少于12个字！" ;
							 }
				          if(!checkPhoneNr(SchFillerTel)){
				        	  return "第" + count + "行，填报人格式有误，有效格式：0791-82085793" ;
				          }
				          t11Bean.setSchFillerTel(SchFillerTel);
				          break;
				          
				  case 6: String SchFillerEmail = cell[3].getContents();
						  if(SchFillerEmail.equals("")||SchFillerEmail==null){
								 return "第" + count + "行，填报人邮箱名称不能为空" ;
							 }
						  if(SchFillerEmail.length()>100){
								 return "第" + count + "行，填报人邮箱不能超过100个字符！" ;
							 }
							 t11Bean.setSchFillerEmail(SchFillerEmail);
							 break;
							 
				  case 7: String SchName=cell[3].getContents();
				  
						  if(SchName.equals("")||SchName==null){
								 return "第" + count + "行，学校名称不能为空" ;
							 }
						  if(SchName.length()>200){
								 return "第" + count + "行，学校名称不能超过100个字符！" ;
							 }
							 t11Bean.setSchName(SchName);
							 break;
				  
				  case 8: String SchID=cell[3].getContents();
				  
						  if(SchID.equals("")||SchID==null){
								 return "第" + count + "行，学校代码不能为空" ;
							 }
						  if(SchID.length()>50){
								 return "第" + count + "行，学校代码不能超过50个字符！" ;
							 }
							 t11Bean.setSchID(SchID);
							 break;
				  
				  case 9: String SchEnName=cell[3].getContents();
				  
						  if(SchEnName.equals("")||SchEnName==null){
								 return "第" + count + "行，学校英文名称不能为空" ;
							 }
						  if(SchEnName.length()>200){
								 return "第" + count + "行，学校英文名称不能超过100个字符！" ;
							 }
							 t11Bean.setSchEnName(SchEnName);
							 break;
				  
				  case 10: String SchType=cell[3].getContents();
				  
						  if(SchType.equals("")||SchType==null){
								 return "第" + count + "行，学校办学类型不能为空" ;
							 }
						  if(SchType.length()>100){
								 return "第" + count + "行，学校办学类型不能超过50个字符！" ;
							 }
							 t11Bean.setSchType(SchType);
							 break;
							 
				  case 11: String SchQuality=cell[3].getContents();
				  
							  if(SchQuality.equals("")||SchQuality==null){
									 return "第" + count + "行，学校性质不能为空" ;
								 }
							  if(SchQuality.length()>100){
									 return "第" + count + "行，学校性质不能超过50个字符！" ;
								 }
								 t11Bean.setSchQuality(SchQuality);
								 break;
				  
				  case 12: String SchBuilder=cell[3].getContents();
				  
							  if(SchBuilder.equals("")||SchBuilder==null){
									 return "第" + count + "举办者不能为空" ;
								 }
							  if(SchBuilder.length()>200){
									 return "第" + count + "行，举办者不能超过100个字符！" ;
								 }
								 t11Bean.setSchBuilder(SchBuilder);
								 break;
								 
				  case 13: String MajDept=cell[3].getContents();
				  
							  if(MajDept.equals("")||MajDept==null){
									 return "第" + count + "助管部门不能为空" ;
								 }
							  if(MajDept.length()>200){
									 return "第" + count + "行，主管部门不能超过100个字符！" ;
								 }
								 t11Bean.setMajDept(MajDept);
								 break;
				  
				  case 14: String SchUrl=cell[3].getContents();
				  
							  if(SchUrl.equals("")||SchUrl==null){
									 return "第" + count + "学校网址不能为空" ;
								 }
							  if(SchUrl.length()>100){
									 return "第" + count + "行，学校网址不能超过100个字符！" ;
								 }
								 t11Bean.setSchUrl(SchUrl);
								 break;
							  
				  case 15: String AdmissonBatch=cell[3].getContents();
				  
							  if(AdmissonBatch.equals("")||AdmissonBatch==null){
									 return "第" + count + "办学批次不能为空" ;
								 }
							  if(AdmissonBatch.length()>300){
									 return "第" + count + "行，办学批次不能超过150个字符！" ;
								 }
								 t11Bean.setAdmissonBatch(AdmissonBatch);
								 break;
				   
				  case 16: String Sch_BeginTime=cell[3].getContents();
				  
							  if(Sch_BeginTime.equals("")||Sch_BeginTime==null){
									 return "第" + count + "开办本科教育年份不能为空" ;
								 }
							  if(!TimeUtil.judgeFormat3(Sch_BeginTime)){
									 return "第" + count + "行，开办本科教育年份格式为：2014" ;
								 }
							    Date Year=TimeUtil.changeDateY(Sch_BeginTime);
								 t11Bean.setSch_BeginTime(Year);
								 break;
								 
				  case 17: String MediaUrl=cell[3].getContents();
				  
							  if(MediaUrl.equals("")||MediaUrl==null){
									 return "第" + count + "多媒体反映不能为空" ;
								 }
							  if(MediaUrl.length()>100){
									 return "第" + count + "行，多媒体反映不能超过100个字符！" ;
								 }
								 t11Bean.setMediaUrl(MediaUrl);
								 break;
								 
				  case 18: String YaohuSchAdd=cell[3].getContents();
				  
							  if(YaohuSchAdd.equals("")||YaohuSchAdd==null){
									 return "第" + count + "瑶湖校区地址不能为空" ;
								 }
							  if(YaohuSchAdd.length()>300){
									 return "第" + count + "行，瑶湖校区地址不能超过150个字符！" ;
								 }
								 t11Bean.setYaohuSchAdd(YaohuSchAdd);
								 break;
								 
				  case 19: String PengHuSchAdd=cell[3].getContents();
				 
							  if(PengHuSchAdd.equals("")||PengHuSchAdd==null){
									 return "第" + count + "彭桥校区地址不能为空" ;
								 }
							  if(PengHuSchAdd.length()>300){
									 return "第" + count + "行，彭桥校区地址不能超过150个字符！" ;
								 }
								 t11Bean.setPengHuSchAdd(PengHuSchAdd);
								 break;
		         default: return "超出行数！" ;
				  }
				
//				count++ ;
			}
			catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
	     }
		}
//		System.out.println(t11Bean.getAdmissonBatch());
//		System.out.println(t11Bean.getMajDept());
//		System.out.println(t11Bean.getMediaUrl());
//		System.out.println(t11Bean.getPengHuSchAdd());
//		System.out.println(t11Bean.getSchAddress());
//		System.out.println(t11Bean.getSchBuilder());
//		System.out.println(t11Bean.getSchEnName());
//		System.out.println(t11Bean.getSchFax());
//		System.out.println(t11Bean.getSchFillerEmail());
//		System.out.println(t11Bean.getSchFillerName());
//		System.out.println(t11Bean.getSchFillerTel());
//		System.out.println(t11Bean.getSchID());
//		System.out.println(t11Bean.getSchName());
//		System.out.println(t11Bean.getSchQuality());
//		System.out.println(t11Bean.getSchTel());
//		System.out.println(t11Bean.getSchType());
//		System.out.println(t11Bean.getSchUrl());
//		System.out.println(t11Bean.getYaohuSchAdd());
//		System.out.println(t11Bean.getSch_BeginTime());
		list.add(t11Bean);
		
		boolean flag=false;
		T11Service t11Ser = new T11Service() ;
		flag = t11Ser.batchInsert(list) ;
		
		if(flag){
			return "数据导入成功" ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
		
	}
    
     /**将list<BEAN>转换成list<String>*/
    public List<String> changeTo(List<T11Bean> list){
    	List<String> listStr=new ArrayList<String>();
    	T11Bean t11Bean=list.get(0);
    	listStr.add(t11Bean.getSchAddress());
    	listStr.add(t11Bean.getSchTel());
    	listStr.add(t11Bean.getSchFax());
    	listStr.add(t11Bean.getSchFillerName());listStr.add(t11Bean.getSchFillerTel());listStr.add(t11Bean.getSchFillerEmail());
    	listStr.add(t11Bean.getSchName());
    	listStr.add(t11Bean.getSchID());listStr.add(t11Bean.getSchEnName());listStr.add(t11Bean.getSchType());
    	listStr.add(t11Bean.getSchQuality());listStr.add(t11Bean.getSchBuilder());listStr.add(t11Bean.getMajDept());
    	listStr.add(t11Bean.getSchUrl());listStr.add(t11Bean.getAdmissonBatch());
    	String beginYear=TimeUtil.changeFormat5(t11Bean.getSch_BeginTime());
//    	String year=beginYear.substring(0, 4);
    	listStr.add(beginYear);listStr.add(t11Bean.getMediaUrl());listStr.add(t11Bean.getYaohuSchAdd());
    	listStr.add(t11Bean.getPengHuSchAdd());
    	return listStr;
    }

    public boolean checkPhoneNr(String phoneNr)  
    {  
    	boolean tem=false;
        String regex="\\d{4}\\-\\d{8}";  
        Pattern reg = Pattern.compile(regex);  
        Matcher matcher=reg.matcher(phoneNr);  
          
        tem=matcher.matches();
        return tem;  
    }  
    
    public static void main(String arg[]){
         T11Excel t11Excel=new T11Excel();
         String str="2012222";
         System.out.println("nihao");
         boolean flag=t11Excel.checkPhoneNr(str);
         if(flag){
        	 System.out.println("格式 正确");
         }else{
        	 System.out.println("格式错误");
         }
//         t11Excel.write();
     }

}
