﻿package cn.nit.excel.imports.table1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanWrapperImpl;

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

import cn.nit.bean.di.DiCourseCategoriesBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.di.DiResearchTypeBean;
import cn.nit.bean.table1.T151_Bean;
import cn.nit.bean.table4.T411_Bean;
import cn.nit.constants.Constants;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.di.DiResearchTypeService;
import cn.nit.service.table1.T151Service;
import cn.nit.util.DateUtil;
import cn.nit.util.TimeUtil;


public class T151Excel {
	
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
		List<T151_Bean> list = new LinkedList<T151_Bean>() ;
//		UserRoleBean userinfo = (UserRoleBean)request.getSession().getAttribute("userinfo") ;
		DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		DiResearchTypeService diResearchSer=new DiResearchTypeService();
		List<DiResearchTypeBean> diResearchBeanList=diResearchSer.getList();
		
		for(Cell[] cell : cellList){
			T151_Bean t151Bean = new  T151_Bean();
			int n=cellList.indexOf(cell);
			if(count<4){
				count++;
				continue;
			}
			else{
				
				
			  try{
				 
				 String ResInsName = cell[1].getContents().trim() ;
					String ResInsID = cell[2].getContents().trim() ;
					
					if(ResInsName == null || ResInsName.equals("")){
						return "第" + count + "行，科研机构不能为空" ;
					}
					
					if(ResInsID == null || ResInsID.equals("")){
						return "第" + count + "行，科研机构单位编号不能为空" ;
					}
					
					if(ResInsID.length() > 50){
						return "第" + count + "行，单位编号字数不超过50个数字或字母" ;
					}
					
					for(DiDepartmentBean diDepartBean : diDepartBeanList){
						if(diDepartBean.getUnitId().equals(ResInsID)){
							if(diDepartBean.getUnitName().equals(ResInsName)){
								flag = true ;
								break ;
							}else{
								return "第" + count + "行，科研机构与单位编号不对应" ;
							}
						}//if
					}//for
					
					if(!flag){
						return "第" + count + "行，没有与之相匹配的单位编号" ;
					}else{
						flag = false ;
					}
				 
					String Type = cell[3].getContents().trim() ;
					
					if(Type == null || Type.equals("")){
						return "第" + count + "行，科研机构类别不能为空" ;
					}
					
					for(DiResearchTypeBean diResearchBean : diResearchBeanList){
						if(diResearchBean.getResearchType().equals(Type)){
							Type = diResearchBean.getIndexId() ;
							flag = true  ;
							break ;
						}//if
					}//for
					if(!flag){
						return "第" + count + "行，科研机构类别不存在" ;
					}else{
						flag = false ;
					}
					
					String BuildCon=cell[4].getContents().trim();
//					System.out.println("BuildCon:"+BuildCon);
					
					if(BuildCon == null || BuildCon.equals("")){
						return "第" + count + "行，共建情况不能为空" ;
					}
					
					if(!BuildCon.equals("是") && !BuildCon.equals("否")){
			        	return "第" + count + "行，只能填“是”或者“否”" ;
//			        	System.out.println("flag:"+flag);
			        }
					else if(BuildCon.equals("是") || BuildCon.equals("否")){
						flag=true;
					}
					if(flag){
								
						if(BuildCon.equals("是")){	buildCondi=true;}
					    else if (BuildCon.equals("否")){buildCondi=false;}
						flag=false;
					}

					String BiOpen=cell[5].getContents().trim();
//					System.out.println("BiOpen:"+BiOpen);
					
					if(BiOpen == null || BiOpen.equals("")){
						return "第" + count + "行，共建情况不能为空" ;
			        }
					
					if(!BiOpen.equals("是") && !BiOpen.equals("否")){
			        	return "第" + count + "行，只能填“是”或者“否”" ;
			        }else if(BiOpen.equals("是") || BiOpen.equals("否")){
			        	flag=true;
			        }
					
					if(flag)
			        {
			        	if(BiOpen.equals("是")){	biOpen=true;}
			        	else if (BiOpen.equals("否")){biOpen=false;}
			        	flag=false;
			        }
					
					String OpenCondition=cell[6].getContents().trim();
					
					if(OpenCondition ==null || OpenCondition.equals("")){
						return "第" + count + "行，对本科生开放情况不能为空" ;
					}
					
					if(OpenCondition.length()>1000){
						return "第" + count + "行，对本科生开放情况字数不能超过500！" ;
					}
					
					String TeaUnit=cell[7].getContents().trim();
					String UnitID=cell[8].getContents().trim();
					
					if(TeaUnit == null || TeaUnit.equals("")){
						return "第" + count + "行，所属教学单位不能为空" ;
					}
					
					if(UnitID == null || UnitID.equals("")){
						return "第" + count + "行，所属教学单位编号不能为空" ;
					}
					
					if(UnitID.length() > 50){
						return "第" + count + "行，所属教学单位编号字数不超过50个数字或字母" ;
					}
					
					for(DiDepartmentBean diDepartBean : diDepartBeanList){
						if(diDepartBean.getUnitId().equals(UnitID)){
							if(diDepartBean.getUnitName().equals(TeaUnit)){
								flag = true ;
								break ;
							}else{
								return "第" + count + "行，所属教学单位与单位编号不对应" ;
							}
						}//if
					}//for
					
					if(!flag){
						return "第" + count + "行，没有与之相匹配的单位编号" ;
					}else{
						flag = false ;
					}
					
					String BeginYearStr=cell[9].getContents().trim();
//					System.out.println(BeginYearStr);
					
					if(BeginYearStr == null || BeginYearStr.equals("")){
						return "第" + count + "行，年份不能为空" ;
					}
					
//					if(!DateUtil.isNumeric(BeginYearStr))
//					{
//						return "第" + count + "行，年份只能为数字" ;
//					} 
					if(!TimeUtil.judgeFormatY(BeginYearStr)){

						return "第" + count + "行，年份的填写格式为：“2014”" ;
					}
					String HouseArea=cell[10].getContents().trim();
					
					if(HouseArea == null|| HouseArea.equals("")){
						return "第" + count + "行，用房面积不能为空" ;
					}
					
					if(!DateUtil.isDouble(HouseArea)){
						return "第" + count + "行，用房面积只能为保留两位的整型数" ;
					}
					
				count++ ;
				
				Date BeginYear=TimeUtil.changeDateY(BeginYearStr);
//				System.out.println(BeginYear);
				double houseArea=DateUtil.doubleTwo(HouseArea);
				t151Bean.setBeginYear(TimeUtil.changeDateY(BeginYearStr));
//				System.out.println("BeginYear:"+t151Bean.getBeginYear());
				t151Bean.setBiOpen(biOpen);
				t151Bean.setBuildCondition(buildCondi);
				t151Bean.setHouseArea(houseArea);
				//t151Bean.setNote(note);
				t151Bean.setOpenCondition(OpenCondition);
				t151Bean.setResInsID(ResInsID);
				t151Bean.setResInsName(ResInsName);
				t151Bean.setTeaUnit(TeaUnit);
				t151Bean.setTime(TimeUtil.changeDateY(selectYear));
				t151Bean.setType(Type);
				t151Bean.setUnitID(UnitID);
				t151Bean.setCheckState(Constants.WAIT_CHECK);
				list.add(t151Bean);
							
			}
			catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
	     }
		}
		
		flag = false ;
		T151Service t151Ser = new T151Service() ;
		flag = t151Ser.batchInsert(list) ;
		
		if(flag){
			return null ;
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
	public static ByteArrayOutputStream batchExport(List<T151_Bean> list, String sheetName, Map<String,Integer> maplist, List<String> columns) throws Exception{
		
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
 
           ws.addCell(new Label(0,0,sheetName,wcf));

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
        						ws.addCell(new Label(maplist.get(column).intValue(),i,(String) wrapper.getPropertyValue(column).toString(),normalFormat));
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
	
	public static void main(String arg[])
	{
        String str="否";
        if(!str.equals("是")&&!str.equals("否"))
        {
        	System.out.println("不匹配");
        }else{System.out.println("匹配");}
    }
}
