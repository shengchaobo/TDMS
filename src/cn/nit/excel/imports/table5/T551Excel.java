package cn.nit.excel.imports.table5;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
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
import cn.nit.bean.di.DiMajorTwoBean;
import cn.nit.bean.di.DiResearchTypeBean;
import cn.nit.bean.table5.T551_Bean;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.di.DiMajorTwoService;
import cn.nit.service.di.DiResearchTypeService;
import cn.nit.service.table5.T551Service;
import cn.nit.util.DateUtil;
import cn.nit.util.TimeUtil;

public class T551Excel {
	
	/**
	 * 批量导入
	 * @param cellList {@link java.util.List<{@link jxl.Cell}>}  Cell是数组
	 * @param request  {@link javax.servlet.http.HttpServletRequest}
	 * @return
	 */
	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request,String selectYear){
		
		if((cellList == null) || (cellList.size() < 2)){
			return "数据不标准，请重新提交" ;
		}
		
		int count = 1 ;
		
		boolean flag = false ;
		boolean biOpen=false;
		boolean buildCondi=false;
		List<T551_Bean> list = new LinkedList<T551_Bean>() ;
//		UserRoleBean userinfo = (UserRoleBean)request.getSession().getAttribute("userinfo") ;
		DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		DiMajorTwoService diMajorTwoSer=new DiMajorTwoService();
		List<DiMajorTwoBean> diMajorTwoBeanList=diMajorTwoSer.getList();
		
		for(Cell[] cell : cellList){
			T551_Bean t551Bean = new  T551_Bean();
			int n=cellList.indexOf(cell);
			if(count<5){//忽略合计的哪一行
				count++;
				continue;
			}
			else{
				
				
			  try{
				 
				 String TeaUnit = cell[1].getContents() ;
					String UnitID = cell[2].getContents() ;
					
					if(TeaUnit == null || TeaUnit.equals("")){
						return "第" + count + "行，教学单位不能为空" ;
					}
					
					if(UnitID == null || UnitID.equals("")){
						return "第" + count + "行，单位编号不能为空" ;
					}
					
					if(UnitID.length() > 50){
						return "第" + count + "行，单位编号字数不超过50个数字或字母" ;
					}
					
					for(DiDepartmentBean diDepartBean : diDepartBeanList){
						if(diDepartBean.getUnitId().equals(UnitID)){
							if(diDepartBean.getUnitName().equals(TeaUnit)){
								flag = true ;
								break ;
							}else{
								return "第" + count + "行，教学单位与单位编号不对应" ;
							}
						}//if
					}//for
					
					if(!flag){
						return "第" + count + "行，没有与之相匹配的单位编号" ;
					}else{
						flag = false ;
					}
					
					String MajorName = cell[3].getContents() ;
					String MajorID = cell[4].getContents() ;
					
					if(MajorName == null || MajorName.equals("")){
						return "第" + count + "行，专业名称不能为空" ;
					}
					
					if(MajorID == null || MajorID.equals("")){
						return "第" + count + "行，专业代码不能为空" ;
					}
					
					if(MajorID.length() > 50){
						return "第" + count + "行，专业代码字数不超过50个数字或字母" ;
					}
					
					for(DiMajorTwoBean diDMajorTwoBean : diMajorTwoBeanList){
						if(diDMajorTwoBean.getMajorNum().equals(MajorID)){
							if(diDMajorTwoBean.getMajorName().equals(MajorName)){
								flag = true ;
								break ;
							}else{
								return "第" + count + "行，专业名称与专业代码不对应" ;
							}
						}//if
					}//for
					
					if(!flag){
						return "第" + count + "行，没有与之相匹配的专业代码" ;
					}else{
						flag = false ;
					}
					
					String AdmisSchYear = cell[5].getContents() ;
					
					if(AdmisSchYear == null || AdmisSchYear.equals("")){
						return "第" + count + "行，入学年份不能为空" ;
					}
					if(!TimeUtil.judgeFormat3(AdmisSchYear)){
						return "第" + count + "行，入学年份格式有误，格式应为:2013" ;
					}
					
					
					String PartyMemNum=cell[6].getContents();
//					System.out.println("BuildCon:"+BuildCon);
					
					if(PartyMemNum == null || PartyMemNum.equals("")){
						PartyMemNum ="0" ;
					}
					
					if (!this.isNumeric(PartyMemNum)){
						return "第" + count + "行，本科生党员个数只能填数字" ;
					}
					
					String CheatNum=cell[7].getContents();
//					System.out.println("BuildCon:"+BuildCon);
					
					if(CheatNum == null || CheatNum.equals("")){
						CheatNum ="0"; ;
					}
					
					if (!this.isNumeric(CheatNum)){
						return "第" + count + "行，违纪人数只能填数字" ;
					}
					
					String GoodClassRatio=cell[8].getContents();
					
					if(GoodClassRatio == null || GoodClassRatio.equals("")){
						return "第" + count + "行，优良学风班的比例不能为空" ;
					}
					if(!this.isRatio(GoodClassRatio)){
						return "第" + count + "行，优良学风班的比例格式不正确，请填写百分数" ;
					}
				
				count++ ;
				
				t551Bean.setAdmisSchYear(AdmisSchYear);
				t551Bean.setCheatNum(Integer.valueOf(CheatNum));
				t551Bean.setGoodClassRatio(this.toDouble(GoodClassRatio));
				t551Bean.setMajorID(MajorID);
				t551Bean.setMajorName(MajorName);
				t551Bean.setPartyMemNum(Integer.parseInt(PartyMemNum));
				t551Bean.setTeaUnit(TeaUnit);
				t551Bean.setUnitID(UnitID);
				t551Bean.setTime(TimeUtil.changeDateY(selectYear));
				list.add(t551Bean);
				
							
			}
			catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
	     }
		}
		
		flag = false ;
		T551Service t551Ser = new T551Service() ;
		flag = t551Ser.batchInsert(list) ;
		
		if(flag){
			return null ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
		
	}
	
	/**判断字符串是否为百分数*/
	public boolean isRatio(String str){
		boolean flag = false;
		if(str.indexOf("%")!=-1){
			String s = str.substring(0, str.indexOf("%"));
			try{
				double d=Double.parseDouble(s);
				if(d<=100&&d>=0){
					flag = true;
				}
			}catch(NumberFormatException ex){
				flag = false;
			}
		}
		return flag;
		
	}
	
	
	/**
	 * 批量导出
	 * @param cellList {@link java.util.List<{@link jxl.Cell}>}
	 * @param request  {@link javax.servlet.http.HttpServletRequest}
	 * @return
	 * @throws Exception 
	 */
	public static ByteArrayOutputStream batchExport(List<T551_Bean> list, String sheetName, Map<String,Integer> maplist, List<String> columns) throws Exception{
		
        WritableWorkbook wwb;
        ByteArrayOutputStream fos = null;
        try {    
            fos = new ByteArrayOutputStream();
            wwb = Workbook.createWorkbook(fos);
            WritableSheet ws = wwb.createSheet(sheetName, 0);        // 创建一个工作表

            //    设置单元格表头的文字格式
            WritableFont wf = new WritableFont(WritableFont.ARIAL,12,WritableFont.BOLD,false,
                    UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
            WritableCellFormat wcf = new WritableCellFormat(wf);
            wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
            wcf.setAlignment(Alignment.CENTRE);
            wcf.setBorder(Border.ALL, BorderLineStyle.THIN,
				     jxl.format.Colour.BLACK);
           ws.setRowView(1, 500);
              //设置格式
			 WritableCellFormat normalFormat = new WritableCellFormat();
			 normalFormat.setBorder(Border.ALL, BorderLineStyle.THIN,
					     jxl.format.Colour.BLACK);
 
           

            //判断一下表头数组是否有数据  
            if (columns != null && columns.size() > 0) {  
  
                //循环写入表头  (第0行 i列)
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
//                ws.addCell(new Label(0,1,"全校合计：",wcf));
//                ws.mergeCells(0, 1, 5, 0);
//  
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
                        		ws.addCell(new Label(0,i,""+i,normalFormat)); 
                        		continue;
                        	}
                        	                        	
        					String type = wrapper.getPropertyType(column).toString() ;

        					//判断插入数据的类型，并赋�?
        					if(type.endsWith("String")){
        						ws.addCell(new Label(maplist.get(column).intValue(),i,(String) wrapper.getPropertyValue(column),normalFormat));
        					}else if(type.endsWith("int")||type.endsWith("Integer")){
        						ws.addCell(new Label(maplist.get(column).intValue(),i,(String) wrapper.getPropertyValue(column).toString(),normalFormat));
        					}else if(type.endsWith("Date")){
        						if((java.util.Date)wrapper.getPropertyValue(column) == null){
        							ws.addCell(new Label(maplist.get(column).intValue(),i,null,normalFormat));
        						}else{
            						java.util.Date utilDate = (java.util.Date)wrapper.getPropertyValue(column) ;
            						Date sqlDate = new Date(utilDate.getTime()) ;
            						TimeUtil til=new TimeUtil();
            						String date=til.changeFormat5(sqlDate);
            						ws.addCell(new Label(maplist.get(column).intValue(),i,date,normalFormat));
        						}
        					}else if(type.endsWith("long")||type.endsWith("Long")){
        						ws.addCell(new Label(maplist.get(column).intValue(),i,(String) wrapper.getPropertyValue(column).toString(),normalFormat));
        					}else if(type.endsWith("boolean")||type.endsWith("Boolean")){
        						if((Boolean)wrapper.getPropertyValue(column)){
        							ws.addCell(new Label(maplist.get(column).intValue(),i,"是",normalFormat));
        						}else{
        							ws.addCell(new Label(maplist.get(column).intValue(),i,"否",normalFormat));
        						}
        					}else if(type.endsWith("double")||type.endsWith("Double")){
        						ws.addCell(new Label(maplist.get(column).intValue(),i,(String) wrapper.getPropertyValue(column).toString()+"%",normalFormat));
        					}else{
        						throw new Exception("自行添加对应类型" + type) ;
        					}                       	                         	
                        }
                        i++;
                    }
                }else{
                	System.out.println("后台传入的数据为空");
                }
            }

            wwb.write();
            wwb.close();

        } catch (IOException e){
        } catch (RowsExceededException e){
        } catch (WriteException e){}
        
        return fos ;
    }
	
	/**判断字符串是否是数字*/
	public boolean isNumeric(String str){
		  for (int i = str.length();--i>=0;){   
		   if (!Character.isDigit(str.charAt(i))){
		    return false;
		   }
		  }
		  return true;
		 }
	

	//保留两位小数
	public double m2(double n){
		 BigDecimal bg = new BigDecimal(n);
	     double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//	     System.out.println(f1);
	     return f1;
	}
	
	
	/**将带%的字符串转换成double类型*/
	public double toDouble(String str){
		String dou=str.substring(0,str.length()-1);
	    double num1=Double.parseDouble(dou);
	    double num =this.m2(num1);
		return num;
	}
	
	 public static void main(String arg[]){
		 T551Excel excel=new T551Excel();
		 String str="21%";
		double num=excel.toDouble(str);
		System.out.println(num);
	 }

}
