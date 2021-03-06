package cn.nit.excel.imports.table1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
import cn.nit.bean.UserinfoBean;
import cn.nit.bean.di.DiAwardLevelBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.table1.T181_Bean;
import cn.nit.bean.table1.T19_Bean;
import cn.nit.constants.Constants;
import cn.nit.service.di.DiAwardLevelService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table1.T19Service;
import cn.nit.util.TimeUtil;

public class T19Excel {
	
	/**
	 * 批量导入
	 * @param cellList {@link java.util.List<{@link jxl.Cell}>}  Cell是数组
	 * @param request  {@link javax.servlet.http.HttpServletRequest}
	 * @return
	 */
	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request,String selectYear){
		
		System.out.println("你好！");
		if((cellList == null) || (cellList.size() < 2)){
			return "数据不标准，请重新提交" ;
		}
		
		int count = 1 ;
		
		boolean flag = false ;
		boolean biOpen=false;
		boolean buildCondi=false;
		List<T19_Bean> list = new LinkedList<T19_Bean>() ;
		UserinfoBean userinfo = (UserinfoBean)request.getSession().getAttribute("userinfo") ;
		DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
	    DiAwardLevelService diAwardLevelSer=new DiAwardLevelService();
	    List<DiAwardLevelBean> diAwardLevelList = diAwardLevelSer.getList();
		
	
		
		for(Cell[] cell : cellList){
			
			T19_Bean t19Bean = new  T19_Bean();
			int n=cellList.indexOf(cell);
			if(count<4){
				count++;
				continue;
			}
			else{
				
				
			  try{
				 
				    String RewardName = cell[1].getContents().trim() ;
				    
				    if(RewardName == null || RewardName.equals("")){
				    	return "第" + count + "行，奖励名称不能为空" ;
				    }
				    if(RewardName.length()>100){
				    	return "第" + count + "行，奖励名称不能超过100字" ;
				    }
				    
				    
					String RewardLevel = cell[2].getContents().trim() ;
					
					
					if(RewardLevel == null || RewardLevel.equals("")){
						return "第" + count + "行，级别不能为空" ;
					}
					if(RewardLevel.equals("省级")){
						RewardLevel = "省部级";
					}
					for(DiAwardLevelBean diAwardLevelBean:diAwardLevelList) {
						if(diAwardLevelBean.getAwardLevel().equals(RewardLevel)){
							RewardLevel=diAwardLevelBean.getIndexId() ;
							flag=true;
							break;
						}
					}
					if(!flag){
						return "第" + count + "行，获奖级别不存在" ;
					}else{
						flag=false;
					}
					
					String RewardFromUnit = cell[3].getContents().trim();
					
					if(RewardFromUnit == null || RewardFromUnit.equals("")){
						return "第" + count + "行，授予单位不能为空" ;
					}
					
					if(RewardFromUnit.length()>100){
						return "第" + count + "行，授予单位字数不能超过100字" ;
					}
					
				 
					String UnitName = cell[4].getContents().trim() ;
					UnitName = UnitName.trim();
					String UnitID = cell[5].getContents().trim();
					
					if(UnitName == null || UnitName.equals("")){
						return "第" + count + "行，获奖单位不能为空" ;
					}
					
					if(UnitID == null || UnitID.equals("")){
						return "第" + count + "行，获奖单位号不能为空" ;
					}
					
					if(UnitID.length() >50){
						return "第" + count + "行，获奖单位号不能超过50" ;
					}
					
					for(DiDepartmentBean diDepartBean : diDepartBeanList){
						if(diDepartBean.getUnitId().equals(UnitID)){
							if(diDepartBean.getUnitName().equals(UnitName)){
								flag = true;  
								break;
							}else{
								return "第" + count + "行，获奖单位与单位号不对应" ;
							}
						}
					}
					if(!flag){
						return "第" + count + "行，没有与之相匹配的单位号" ;
					}else{
						flag=false;
					}
					
					String RewardTime=cell[6].getContents().trim();
					
					if(RewardTime == null || RewardTime.equals("")){
						return "第" + count + "行，获奖时间不能为空" ;
					}
					if(!TimeUtil.judgeFormat3(RewardTime)){
						return "第" + count + "行，获奖时间格式为：“2013”" ;
					}
//					String note=cell[7].getContents();
//					
//					if(note.length()>500){
//						return "第" + count + "行，备注字数不能超过500" ;
//					}
					
				Date rewardTime=TimeUtil.changeDateY(RewardTime);
				count++ ;
				t19Bean.setRewardName(RewardName);
				t19Bean.setRewardLevel(RewardLevel);
				t19Bean.setRewardFromUnit(RewardFromUnit);
//				t19Bean.setNote(note);
				t19Bean.setTime(TimeUtil.changeDateY(selectYear));
				t19Bean.setUnitID(UnitID);
				t19Bean.setUnitName(UnitName);
				t19Bean.setRewardTime(rewardTime);
				t19Bean.setCheckState(Constants.WAIT_CHECK);
				list.add(t19Bean);			
			}
			catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
	     }
		}
		
		flag = false ;
		T19Service t19Ser = new T19Service() ;
		flag = t19Ser.batchInsert(list) ;
		
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
	public static ByteArrayOutputStream batchExport(List<T19_Bean> list, String sheetName, Map<String,Integer> maplist, List<String> columns) throws Exception{
		
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
	
	

}
