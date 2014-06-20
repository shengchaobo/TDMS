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
		
		int count = 1 ;
		
		boolean flag = false ;
		boolean biOpen=false;
		boolean buildCondi=false;
		List<T11Bean> list = new LinkedList<T11Bean>() ;
//		UserRoleBean userinfo = (UserRoleBean)request.getSession().getAttribute("userinfo") ;
		
		for(Cell[] cell : cellList){
			T11Bean t151Bean = new  T11Bean();
			int n=cellList.indexOf(cell);
			if(n==0){continue;}
			else{
			  try{
				  
				  switch(n){
				  case 1: String SchAddress = cell[3].getContents() ;
					 
					 if(SchAddress.equals("")||SchAddress==null){
						 return "第" + count + "行，学校地址不能为空" ;
					 }
					 if(SchAddress.length()>300){
						 return "第" + count + "行，学校地址不能超过150个字！" ;
					 }
					 t151Bean.setSchAddress(SchAddress);
					 break;
				  case 2:String SchTel = cell[3].getContents();
				          
				          if(SchTel==null||SchTel.equals("")){
				        	  return "第" + count + "行，学校电话号码不能为空" ;
				          }
				          
				  }
				 
				
				
				count++ ;
				
//				Date BeginYear=TimeUtil.changeDateY(BeginYearStr);
////				System.out.println(BeginYear);
//				double houseArea=DateUtil.doubleTwo(HouseArea);
//				t151Bean.setBeginYear(TimeUtil.changeDateY(BeginYearStr));
////				System.out.println("BeginYear:"+t151Bean.getBeginYear());
//				t151Bean.setBiOpen(biOpen);
//				t151Bean.setBuildCondition(buildCondi);
//				t151Bean.setHouseArea(houseArea);
//				t151Bean.setNote(note);
//				t151Bean.setOpenCondition(OpenCondition);
//				t151Bean.setResInsID(ResInsID);
//				t151Bean.setResInsName(ResInsName);
//				t151Bean.setTeaUnit(TeaUnit);
//				t151Bean.setTime(new Date());
//				t151Bean.setType(Type);
//				t151Bean.setUnitID(UnitID);
//				list.add(t151Bean);
				
//				Date BuildYear=TimeUtil.changeDateY(BuildYearStr);
//				t17Bean = new T17Bean();
//				t17Bean.setClubName(ClubName);
//				t17Bean.setBuildYear(BuildYear);
//				t17Bean.setPlace(Place);
//				t17Bean.setNote(note);
//				t17Bean.setTime(new Date()) ;
//				list.add(t17Bean);
//				
			}
			catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
	     }
		}
		
		flag = false ;
		T11Service t11Ser = new T11Service() ;
//		flag = t11Ser.batchInsert(list) ;
		
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
    	String beginYear=t11Bean.getSchFillerTel();
    	String year=beginYear.substring(0, 4);
    	listStr.add(year);listStr.add(t11Bean.getMediaUrl());listStr.add(t11Bean.getYaohuSchAdd());
    	listStr.add(t11Bean.getPengHuSchAdd());
    	return listStr;
    }
    
    public static void main(String arg[]){
//    	 Date time=new Date();
//         String time1=time.toString();
//         String year=time1.substring(time1.length()-4, time1.length());
//    	String str="1990-05-18";
//    	String year=str.substring(0, 4);
//         System.out.println(year);
         T11Excel t11Excel=new T11Excel();
//         t11Excel.write();
     }

}
