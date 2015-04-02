package cn.nit.excel.imports.table5;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
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

import cn.nit.bean.UserinfoBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.di.DiMajorTwoBean;
import cn.nit.bean.table5.T533_Bean;
import cn.nit.constants.Constants;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.di.DiMajorTwoService;
import cn.nit.service.table5.T533Service;
import cn.nit.util.TimeUtil;

public class T533Excel {
	
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
//		boolean biOpen=false;
//		boolean buildCondi=false;
		Date Time = new Date();
		List<T533_Bean> list = new LinkedList<T533_Bean>() ;
//		UserRoleBean userinfo = (UserRoleBean)request.getSession().getAttribute("userinfo") ;
		DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		DiMajorTwoService diMajorTwoSer=new DiMajorTwoService();
		List<DiMajorTwoBean> diMajorTwoBeanList=diMajorTwoSer.getList();
		
		for(Cell[] cell : cellList){
			T533_Bean t533Bean = new  T533_Bean();
			int n=cellList.indexOf(cell);
			if(count<4){
				count++;
				continue;
			}
			else{
				
				
			  try{
				 
				 String TeaUnit = cell[1].getContents().trim();
					String UnitID = cell[2].getContents().trim();
					
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
					
					String MajorName = cell[3].getContents().trim() ;
					String MajorID = cell[4].getContents().trim() ;
					
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
					
					String ExpCSNum = cell[5].getContents().trim() ;
					
					if(ExpCSNum == null || ExpCSNum.equals("")){
						ExpCSNum="0";
					}
					if(!this.isNumeric(ExpCSNum)){
						return "第" + count + "行，实验课程门数应为数字" ;
					}
					
					
					String IndepentExpCSNum=cell[6].getContents().trim();
//					System.out.println("BuildCon:"+BuildCon);
					
					if(IndepentExpCSNum == null || IndepentExpCSNum.equals("")){
						IndepentExpCSNum ="0" ;
					}
					
					if (!this.isNumeric(IndepentExpCSNum)){
						return "第" + count + "行，独立实验课程只能填数字" ;
					}
					
					String DesignExpCSNum=cell[7].getContents().trim();
//					System.out.println("BuildCon:"+BuildCon);
					
					if(DesignExpCSNum == null || DesignExpCSNum.equals("")){
						DesignExpCSNum ="0"; ;
					}
					
					if (!this.isNumeric(DesignExpCSNum)){
						return "第" + count + "行，综合型实验教学门数只能填数字" ;
					}
					
					String ExpRatio=cell[8].getContents().trim();
					
					if(ExpRatio == null || ExpRatio.equals("")){
						return "第" + count + "行，实验开出率（%）不能为空" ;
					}
					if(!this.isRatio(ExpRatio)){
						return "第" + count + "行，实验开出率（%）格式有误，请填写百分数" ;
					}
					
				
				count++ ;
//				String fillUnitID="3001";
				t533Bean.setDesignExpCSNum(Integer.parseInt(DesignExpCSNum));
				t533Bean.setExpCSNum(Integer.parseInt(ExpCSNum));
				t533Bean.setExpRatio(this.toDouble(ExpRatio));
				t533Bean.setIndepentExpCSNum(Integer.parseInt(IndepentExpCSNum));
				t533Bean.setMajorID(MajorID);
				t533Bean.setMajorName(MajorName);
				t533Bean.setTeaUnit(TeaUnit);
				t533Bean.setUnitID(UnitID);
				//插入教学单位
				UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
				String fillUnitID = bean.getUnitID();
				t533Bean.setFillUnitID(fillUnitID);
				t533Bean.setCheckState(Constants.WAIT_CHECK);
				//t533Bean.setTime(new Date());
				t533Bean.setTime(TimeUtil.changeDateY(selectYear));
				list.add(t533Bean);
				
							
			}
			catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
	     }
		}
		
		flag = false ;
		T533Service t533Ser = new T533Service() ;
		flag = t533Ser.batchInsert(list) ;
		
		if(flag){
			return null;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
		
	}
	
	/**
	 * 批量导出
	 * @param cellList {@link java.util.List<{@link jxl.Cell}>}
	 * @param request  {@link javax.servlet.http.HttpServletRequest}
	 * @return
	 * @throws Exception 
	 */
	public static ByteArrayOutputStream exportExcel(List list, String sheetName, Map<String,Integer> maplist, List<String> columns) throws Exception{
		
        WritableWorkbook wwb;
        ByteArrayOutputStream fos = null;
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
                    ws.addCell(new Label(i, 2, columns.get(i), wcf));  
                }  
            }
  
                //判断表中是否有数据  
            if (list != null && list.size() > 0) {  
                    //循环写入表中数据  
                	BeanWrapperImpl wrapper = new BeanWrapperImpl() ;
                	int i=3;  
                	for(Object obj : list){  
                		wrapper.setWrappedInstance(obj) ;  
                        //循环输出map中的子集：既列值                         
                        for(String column:maplist.keySet()){
                        	
                        	if(column.equals("SeqNum")){
                        		ws.addCell(new Label(0,i,""+(i-2),wcf1)); 
                        		continue;
                        	}
                        	                        	
        					String type = wrapper.getPropertyType(column).toString() ;
//        					System.out.println(type +"-test：" + column);

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
	    double num= this.m2(num1);
		return num;
	}
	
//	public boolean isDouble
	
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
	
//	public static boolean isCorrectVersion(String odivalue) {  
//	    // TODO Auto-generated method stub  
//	    Pattern pattern = Pattern.compile("d{2}" + "%");  
//	    Matcher isNum = pattern.matcher(odivalue);  
//	    if( !isNum.matches() )  
//	    {  
//	        return false;  
//	    }  
//	        return true;  
//	    }   
	public static void main(String arg[]){
		T533Excel exl= new T533Excel();
		String a = "20.3%";
		String b="20";
		boolean flag=exl.isRatio(a);
		if(flag){
			System.out.println("是");
		}else{
			System.out.println("否");
		}
	}

}
