package cn.nit.excel.imports.table3;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

import cn.nit.bean.di.DiAwardLevelBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.di.DiMajorTwoBean;
import cn.nit.bean.other.UserRoleBean;
import cn.nit.bean.table3.T321_Bean;

import cn.nit.service.di.DiAwardLevelService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.di.DiMajorTwoService;
import cn.nit.service.table3.T321_Service;

import cn.nit.util.TimeUtil;

public class T321Excel {
	
	/**
	 * 批量导入
	 * @param cellList {@link java.util.List<{@link jxl.Cell}>}  Cell是数组
	 * @param request  {@link javax.servlet.http.HttpServletRequest}
	 * @return
	 */
	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request, String selectYear,int [] mergedCells){
		System.out.println("大小");
		System.out.println(cellList.size());
		if((cellList == null) || (cellList.size() < 2)){
			return "数据不标准，请重新提交" ;
		}
		
		int count = 1 ;
		 Date time=new Date();
		boolean flag = false ;
		List<T321_Bean> list = new LinkedList<T321_Bean>() ;
		UserRoleBean userinfo = (UserRoleBean)request.getSession().getAttribute("userinfo") ;
		DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		DiMajorTwoService diMajorTwoSer=new DiMajorTwoService();
		List<DiMajorTwoBean> diMajorTwoList=diMajorTwoSer.getList();
		String MainClassName = null;
		String MainClassID=null;
		int ByPassTime = 0;
	
		
		for(Cell[] cell : cellList){
			
			T321_Bean t321_Bean = new  T321_Bean();
				
				
			  try{
				  
				    if(count<4){
				    	count++;
				    	continue;
				    }
				    
				    
				    
                    if(mergedCells[count-1]!=0){
				    MainClassName = cell[1].getContents();
				    
				    if(MainClassName == null || MainClassName.equals("")){
				    	return "第" + count + "行，大类名称不能为空" ;
				    }
				    if(MainClassName.length()>100){
				    	return "第" + count + "行，大类名称长度不能超过100" ;
				    }
				    
				    
					MainClassID = cell[2].getContents() ;
					
					if(MainClassID == null || MainClassID.equals("")){
						return "第" + count + "行，大类代码不能为空" ;
					}
					if(MainClassID.length()>50){
						return "第" + count + "行，大类代码长度不能超过50";
					}
					
					try{
						ByPassTime=Integer.parseInt(cell[3].getContents());
					}catch( NumberFormatException e){
						e.printStackTrace() ;
					}
					if(ByPassTime>10||ByPassTime<1){
						return "第" + count + "行，分流时间必须在1与10之间" ;
					}
					
					String MajorNameInSch = cell[4].getContents();
					String MajorID=cell[5].getContents();
					
					if(MajorNameInSch == null || MajorNameInSch.equals("")){
						return "第" + count + "行，包含校内专业名称不能为空";
					}
					
					if(MajorID == null || MajorID.equals("")){
						return "第" + count + "行，校内专业代码不能为空";
					}
					
					if(MajorID.length()>50){
						return "第" + count + "行，校内专业代码长度不能超过50";
					}
					for(DiMajorTwoBean diMajorTwoBean : diMajorTwoList){
						if(diMajorTwoBean.getMajorNum().equals(MajorID)){
							if(diMajorTwoBean.getMajorName().equals(MajorNameInSch)){
								flag = true ;
								break ;
							}else{
								return "第" + count + "行，包含校内专业名称与校内专业代码不对应" ;
							}
						}//if
					}//for
					
					String UnitName = cell[6].getContents();
					String UnitID=cell[7].getContents();
					
					if(UnitName == null || UnitName.equals("")){
						return "第" + count + "行，所属单位不能为空";
					}
					
					if(UnitID == null || UnitID.equals("")){
						return "第" + count + "行，单位号不能为空";
					}
					
					if(UnitID.length()>50){
						return "第" + count + "行，单位号长度不能超过50";
					}
					for(DiDepartmentBean diDepartBean : diDepartBeanList){
						if(diDepartBean.getUnitId().equals(UnitID)){
							if(diDepartBean.getUnitName().equals(UnitName)){
								flag = true ;
								break ;
							}else{
								return "第" + count + "行，所属单位与单位号不对应" ;
							}
						}//if
					}//for
					

					
					
				
				count++ ;
				
				t321_Bean.setMainClassName(MainClassName);
				t321_Bean.setMainClassID(MainClassID);
				t321_Bean.setByPassTime(ByPassTime);
				t321_Bean.setMajorNameInSch(MajorNameInSch);
				t321_Bean.setMajorID(MajorID);
				t321_Bean.setUnitName(UnitName);
				t321_Bean.setUnitID(UnitID);
				t321_Bean.setTime(TimeUtil.changeDateY(selectYear));
				list.add(t321_Bean);
				System.out.println("数字");
				System.out.println(count);
			}else{	
				String MajorNameInSch = cell[4].getContents();
				String MajorID=cell[5].getContents();
				
				if(MajorNameInSch == null || MajorNameInSch.equals("")){
					return "第" + count + "行，包含校内专业名称不能为空";
				}
				
				if(MajorID == null || MajorID.equals("")){
					return "第" + count + "行，校内专业代码不能为空";
				}
				
				if(MajorID.length()>50){
					return "第" + count + "行，校内专业代码长度不能超过50";
				}
				for(DiMajorTwoBean diMajorTwoBean : diMajorTwoList){
					if(diMajorTwoBean.getMajorNum().equals(MajorID)){
						if(diMajorTwoBean.getMajorName().equals(MajorNameInSch)){
							flag = true ;
							break ;
						}else{
							return "第" + count + "行，包含校内专业名称与校内专业代码不对应" ;
						}
					}//if
				}//for
				
				String UnitName = cell[6].getContents();
				String UnitID=cell[7].getContents();
				
				if(UnitName == null || UnitName.equals("")){
					return "第" + count + "行，所属单位不能为空";
				}
				
				if(UnitID == null || UnitID.equals("")){
					return "第" + count + "行，单位号不能为空";
				}
				
				if(UnitID.length()>50){
					return "第" + count + "行，单位号长度不能超过50";
				}
				for(DiDepartmentBean diDepartBean : diDepartBeanList){
					if(diDepartBean.getUnitId().equals(UnitID)){
						if(diDepartBean.getUnitName().equals(UnitName)){
							flag = true ;
							break ;
						}else{
							return "第" + count + "行，所属单位与单位号不对应" ;
						}
					}//if
				}//for
				

				
				String  Note=cell[9].getContents();
				if(Note.length()>1000){
					return "第" + count + "行，备注的长度不能超过500个字符！" ;
				}
				
				
			
			count++ ;
			
			t321_Bean.setMainClassName(MainClassName);
			t321_Bean.setMainClassID(MainClassID);
			t321_Bean.setByPassTime(ByPassTime);
			t321_Bean.setMajorNameInSch(MajorNameInSch);
			t321_Bean.setMajorID(MajorID);
			t321_Bean.setUnitName(UnitName);
			t321_Bean.setUnitID(UnitID);
			t321_Bean.setNote(Note);
//			t321_Bean.setTime(time);
			t321_Bean.setTime(TimeUtil.changeDateY(selectYear));
			list.add(t321_Bean);
			System.out.println("数字");
			System.out.println(count);
		}
			}
			  
			catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
	     }
			  
		
		flag = false ;
		T321_Service t321_Ser = new T321_Service() ;
		flag = t321_Ser.batchInsert(list) ;
		
		if(flag){
			return "数据导入成功" ;
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
	public static ByteArrayOutputStream batchExport(List<T321_Bean> list, String sheetName, Map<String,Integer> maplist, List<String> columns) throws Exception{
		int count1=0,count2=1,count=1;
		int[] mergedCells;
		mergedCells=new int [list.size()];
        WritableWorkbook wwb;//可读写的工作簿
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
	                    ws.addCell(new Label(i, 0, columns.get(i), wcf));  
	                }  
	            }
                
                while(count2<list.size()){
                	if(list.get(count1).getMainClassID().equals(list.get(count2).getMainClassID())){
                		count2++;
                		
                	}else{
                        for(int c=count1;c<count2;c++){
                        	mergedCells[c]=count2-1;
                        }
                		count1=count2;
                		count2=count1+1;
                		
                	}
                	
                	
                }
                for(int c=count1;c<count2;c++){
                	mergedCells[c]=count2-1;
                }

  
                //判断表中是否有数据  
                if (list != null && list.size() > 0) {  
                	int mergedNumCell;
                	int mergedNum = 0;
                    //循环写入表中数据  
                	BeanWrapperImpl wrapper = new BeanWrapperImpl() ;
                	int i=1;  
                	for(Object obj : list){  
                		if(mergedCells[i-1]==i-1){
                			if(i!=1&&mergedCells[i-2]!=i-2){
                				wrapper.setWrappedInstance(obj) ;  
                                //循环输出map中的子集：既列值                         
                                for(String column:maplist.keySet()){
                                	
                                	if(column.equals("MajorNameInSch")){
                                		ws.addCell(new Label(4,i,(String) wrapper.getPropertyValue(column),normalFormat));
                                	}else if(column.equals("MajorID")){
                                		ws.addCell(new Label(5,i,(String) wrapper.getPropertyValue(column),normalFormat));
                                	}else if(column.equals("UnitName")){
                                		ws.addCell(new Label(6,i,(String) wrapper.getPropertyValue(column),normalFormat));
                                	}else if(column.equals("UnitID")){
                                		ws.addCell(new Label(7,i,(String) wrapper.getPropertyValue(column),normalFormat));
                                	}

                				
                			}
                    		
                                i++;  
                				
                			}else {
                    		wrapper.setWrappedInstance(obj) ;  
                            //循环输出map中的子集：既列值                         
                            for(String column:maplist.keySet()){
                            	
                            	
                            	
                            	if(column.equals("SeqNum")){
                            		ws.addCell(new Label(0,i,""+count,normalFormat));
                            		
                            	}else if(column.equals("MainClassName")){
                            		ws.addCell(new Label(1,i,(String) wrapper.getPropertyValue(column),normalFormat));
                            		
                      
                            	}else if(column.equals("MainClassID")){
                            		ws.addCell(new Label(2,i,(String) wrapper.getPropertyValue(column),normalFormat));
                            	
                            	}else if(column.equals("ByPassTime")){
                            		ws.addCell(new Label(3,i,(String) wrapper.getPropertyValue(column).toString(),normalFormat));
                            	
                            	}else if(column.equals("MajorNameInSch")){
                            		ws.addCell(new Label(4,i,(String) wrapper.getPropertyValue(column),normalFormat));
                            	}else if(column.equals("MajorID")){
                            		ws.addCell(new Label(5,i,(String) wrapper.getPropertyValue(column),normalFormat));
                            	}else if(column.equals("UnitName")){
                            		ws.addCell(new Label(6,i,(String) wrapper.getPropertyValue(column),normalFormat));
                            	}else if(column.equals("UnitID")){
                            		ws.addCell(new Label(7,i,(String) wrapper.getPropertyValue(column),normalFormat));
                            	}
                             	                         	
                            }
                            i++;
                            count++;
                			}
                		}else if(mergedCells[i-2]==i-2){
                    		wrapper.setWrappedInstance(obj) ;  
                        	mergedNum=mergedCells[i-1];
                        	 mergedNumCell=mergedNum-i+1;
                            //循环输出map中的子集：既列值                         
                            for(String column:maplist.keySet()){

                            	
                            	if(column.equals("SeqNum")){
                            		ws.addCell(new Label(0,i,""+count,normalFormat));
                            		 ws.mergeCells(0, i,0,mergedNum+1);
                            	}else if(column.equals("MainClassName")){
                            		ws.addCell(new Label(1,i,(String) wrapper.getPropertyValue(column),normalFormat));
                            		ws.mergeCells(1, i,1,mergedNum+1);
                      
                            	}else if(column.equals("MainClassID")){
                            		ws.addCell(new Label(2,i,(String) wrapper.getPropertyValue(column),normalFormat));
                            		ws.mergeCells(2, i,2,mergedNum+1);
                            	}else if(column.equals("ByPassTime")){
                            		ws.addCell(new Label(3,i,(String) wrapper.getPropertyValue(column).toString(),normalFormat));
                            		ws.mergeCells(3, i,3,mergedNum+1);
                            	}else if(column.equals("MajorNameInSch")){
                            		ws.addCell(new Label(4,i,(String) wrapper.getPropertyValue(column),normalFormat));
                            	}else if(column.equals("MajorID")){
                            		ws.addCell(new Label(5,i,(String) wrapper.getPropertyValue(column),normalFormat));
                            	}else if(column.equals("UnitName")){
                            		ws.addCell(new Label(6,i,(String) wrapper.getPropertyValue(column),normalFormat));
                            	}else if(column.equals("UnitID")){
                            		ws.addCell(new Label(7,i,(String) wrapper.getPropertyValue(column),normalFormat));
                            	}
                            	
                            }
                            count++;
                            i++;
                		}else{
                			
                				wrapper.setWrappedInstance(obj) ;  
                                //循环输出map中的子集：既列值                         
                                for(String column:maplist.keySet()){
                                	
                                	if(column.equals("MajorNameInSch")){
                                		ws.addCell(new Label(4,i,(String) wrapper.getPropertyValue(column),normalFormat));
                                	}else if(column.equals("MajorID")){
                                		ws.addCell(new Label(5,i,(String) wrapper.getPropertyValue(column),normalFormat));
                                	}else if(column.equals("UnitName")){
                                		ws.addCell(new Label(6,i,(String) wrapper.getPropertyValue(column),normalFormat));
                                	}else if(column.equals("UnitID")){
                                		ws.addCell(new Label(7,i,(String) wrapper.getPropertyValue(column),normalFormat));
                                	}

                				
                			}
                    		
                                i++;                   	
                            
                    
                			
                			
                		

                    }
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
	
	

	
	
	public static void main(String arg[]){

		
	}

}
