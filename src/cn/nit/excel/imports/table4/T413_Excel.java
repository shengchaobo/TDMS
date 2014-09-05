package cn.nit.excel.imports.table4;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanWrapperImpl;

import jxl.Cell;
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
import cn.nit.bean.UserinfoBean;
import cn.nit.bean.di.DiCourseCategoriesBean;
import cn.nit.bean.di.DiCourseCharBean;
import cn.nit.bean.di.DiDegreeBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.di.DiEducationBean;
import cn.nit.bean.di.DiIdentiTypeBean;
import cn.nit.bean.di.DiResearchRoomBean;
import cn.nit.bean.di.DiSourceBean;
import cn.nit.bean.di.DiTitleLevelBean;
import cn.nit.bean.di.DiTitleNameBean;
import cn.nit.bean.di.DiTutorTypeBean;
import cn.nit.bean.table4.T411_Bean;
import cn.nit.bean.table4.T413_Bean;
import cn.nit.service.di.DiCourseCategoriesService;
import cn.nit.service.di.DiCourseCharService;
import cn.nit.service.di.DiDegreeService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.di.DiEducationService;
import cn.nit.service.di.DiIdentiTypeService;
import cn.nit.service.di.DiResearchRoomService;
import cn.nit.service.di.DiSourceService;
import cn.nit.service.di.DiTitleLevelService;
import cn.nit.service.di.DiTitleNameService;
import cn.nit.service.di.DiTutorTypeService;
import cn.nit.service.table4.T411_Service;
import cn.nit.service.table4.T413_Service;
import cn.nit.util.TimeUtil;

public class T413_Excel {

	/**
	 * 批量导入
	 * @param cellList {@link java.util.List<{@link jxl.Cell}>}  Cell是数组
	 * @param request  {@link javax.servlet.http.HttpServletRequest}
	 * @return
	 */
	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request, String selectYear){
		
		if((cellList == null) || (cellList.size() < 2)){
			return "数据不标准，请重新提交" ;
		}
		
		int count = 1 ;
		T411_Bean T411_bean = null ;
		T413_Bean T413_bean = null ;
		boolean flag = false ;
		boolean flag1 = false ;
		List<T411_Bean> list = new LinkedList<T411_Bean>() ;
		List<T413_Bean> list1 = new LinkedList<T413_Bean>() ;
				
		DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
				
		DiEducationService diEdu = new DiEducationService();
		List<DiEducationBean> diEduList = diEdu.getList();
		
		DiDegreeService diDegree = new DiDegreeService();
		List<DiDegreeBean> diDegreeList = diDegree.getList();
				
		DiTitleLevelService diTitle = new DiTitleLevelService();
		List<DiTitleLevelBean> diTitleList = diTitle.getList();
				
		DiTutorTypeService diTutor = new DiTutorTypeService();
		List<DiTutorTypeBean> diTutorList = diTutor.getList();
				
		for(Cell[] cell : cellList){
			try{
				if(count<4){
					count++;
					continue;
				}

				String unit = cell[1].getContents() ;
				String unitId = cell[2].getContents() ;
				
				if(unit == null || unit.equals("")){
					return "第" + count + "行，所属单位不能为空" ;
				}
				
				if(unitId == null || unitId.equals("")){
					return "第" + count + "行，所属单位号不能为空" ;
				}			
				
				for(DiDepartmentBean diDepartBean : diDepartBeanList){
					if(diDepartBean.getUnitId().equals(unitId)){
						if(diDepartBean.getUnitName().equals(unit)){
							flag = true ;
							break ;
						}else{
							return "第" + count + "行，所属单位与所属单位号不对应" ;
						}
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，没有与之相匹配的单位号" ;
				}else{
					flag = false ;
				}
				
				String name = cell[3].getContents() ;
				String teaId = cell[4].getContents() ;
				
				if(name == null || name.equals("")){
					return "第" + count + "行，教师名称不能为空" ;
				}
				
				if((teaId == null) || teaId.equals("")){
					return "第" + count + "行，教工号不能为空" ;
				}
				
				String gender = cell[5].getContents();
				if(gender == null || gender.equals("")){
					return "第" + count + "行，教师性别不能为空" ;
				}
				
				String birthday = cell[6].getContents() ;
				if((birthday == null) || birthday.equals("")){
					return "第" + count + "行，教师出生日期不能为空" ;
				}else{
					if(!TimeUtil.judgeFormatYM(birthday)){
						return "第" + count + "行，教师出生日期格式不正确" ;
					}
				}
				
				
				String hireBeginTime = cell[7].getContents() ;
				if((hireBeginTime == null) || hireBeginTime.equals("")){
					return "第" + count + "行，教师聘任时间不能为空" ;
				}else{
					if(!TimeUtil.judgeFormatYM(hireBeginTime)){
						return "第" + count + "行，教师聘任时间格式不正确" ;
					}
				}
				
				
				String teaState = cell[8].getContents() ;
				if((teaState == null) || teaState.equals("")){
					return "第" + count + "行，教师任职状态不能为空" ;
				}
				
				String hireTimeLen = cell[9].getContents() ;
				if((hireTimeLen == null) || hireTimeLen.equals("")){
					return "第" + count + "行，教师聘期（个月）不能为空" ;
				}
				
				String edu = cell[10].getContents() ;
				
				if(edu == null || edu.equals("")){
					return "第" + count + "行，教师学历不能为空" ;
				}
				String eduID = null;
				for(DiEducationBean eduBean : diEduList){
					if(eduBean.getEducation().equals(edu)){
						eduID = eduBean.getIndexId() ;
						flag = true  ;
						break ;
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，学历类别不存在" ;
				}else{
					flag = false ;
				}
				
				String degree = cell[11].getContents() ;
				
				if(degree == null || degree.equals("")){
					return "第" + count + "行，教师最高学位不能为空" ;
				}
				String degreeID = null;
				for(DiDegreeBean digreeBean : diDegreeList){
					if(digreeBean.getDegree().equals(degree)){
						degreeID = digreeBean.getIndexId() ;
						flag = true  ;
						break ;
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，最高学位不存在" ;
				}else{
					flag = false ;
				}
				
					
				String majTitle = cell[12].getContents() ;
				
				if(majTitle == null || majTitle.equals("")){
					return "第" + count + "行，教师专业技术职称不能为空" ;
				}
				String majID = null;
				for(DiTitleLevelBean titleLevelBean : diTitleList){
					if(titleLevelBean.getTitleLevel().equals(majTitle)){
						majID = titleLevelBean.getIndexId() ;
						flag = true  ;
						break ;
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，专业技术职称不存在" ;
				}else{
					flag = false ;
				}
				
				String subject = cell[13].getContents() ;
				
				if(subject == null || subject.equals("")){
					return "第" + count + "行，学科类别不能为空" ;
				}
				
				String workType = cell[14].getContents() ;
				
				String tutorType = cell[15].getContents() ;
				
				if(tutorType == null || tutorType.equals("")){
					return "第" + count + "行，导师类型不能为空" ;
				}
				String tutorID = null;
				for(DiTutorTypeBean tutorTypeBean : diTutorList){
					if(tutorTypeBean.getTutorType().equals(tutorType)){
						tutorID = tutorTypeBean.getIndexId() ;
						flag = true  ;
						break ;
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，导师类型不存在" ;
				}else{
					flag = false ;
				}
				
				
				if(subject == null || subject.equals("")){
					return "第" + count + "行，学科类别不能为空" ;
				}
				
				String region = cell[16].getContents() ;
				
				count++ ;
				
				T413_bean = new T413_Bean() ;
				T413_bean.setUnitName(unit);
				T413_bean.setUnitId(unitId);
				T413_bean.setName(name);
				T413_bean.setTeaId(teaId);
				T413_bean.setGender(gender);
				T413_bean.setBirthday(TimeUtil.changeDateYM(birthday));
				T413_bean.setHireBeginTime(TimeUtil.changeDateYM(hireBeginTime));
				T413_bean.setTeaState(teaState);
				T413_bean.setHireTimeLen(Integer.parseInt(hireTimeLen));
				T413_bean.setEducation(eduID);
				T413_bean.setTopDegree(degreeID);
				T413_bean.setTechTitle(majID);
				T413_bean.setSubjectClass(subject);
				T413_bean.setWorkUnitType(workType);
				T413_bean.setTutorType(tutorID);
				T413_bean.setRegion(region);
				
				//插入教学单位
				UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
				String fillUnitID = bean.getUnitID();
				T413_bean.setFillUnitID(fillUnitID);
				list1.add(T413_bean);	
				
				T411_bean = new T411_Bean() ;
				T411_bean.setTeaName(name);
				T411_bean.setTeaId(teaId);
				T411_bean.setGender(gender);
				T411_bean.setBirthday(TimeUtil.changeDateYM(birthday));
				T411_bean.setAdmisTime(null);
				T411_bean.setTeaState(T413_bean.getTeaState());
				T411_bean.setBeginWorkTime(null);
				T411_bean.setIdcode("40009");
				T411_bean.setTeaFlag("外聘");
				T411_bean.setOfficeID(unitId);
				T411_bean.setFromOffice(unit);
				T411_bean.setUnitId(unitId);
				T411_bean.setFromUnit(unit);
				T411_bean.setFromTeaResOffice(null);
				T411_bean.setTeaResOfficeID(null);
				T411_bean.setEducation(eduID);
				T411_bean.setTopDegree(degreeID);
				T411_bean.setGraSch(null);
				T411_bean.setSource(null);
				T411_bean.setAdminLevel(null);
				T411_bean.setMajTechTitle(majID);
				T411_bean.setTeaTitle(null);
				T411_bean.setNotTeaTitle(null);
				T411_bean.setSubjectClass(subject);
				T411_bean.setIndustry(false);
				T411_bean.setDoubleTea(false);
				T411_bean.setEngineer(false);
				T411_bean.setTeaBase(false);			
								
			}catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
		}
		
		flag = false ;
		flag1 = false ;
		T413_Service T413_services = new T413_Service() ;
		flag1 = T413_services.batchInsert(list1) ;
		T411_Service T411_services = new T411_Service() ;
		flag = T411_services.batchInsert(list) ;
		
		if(flag&&flag1){
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
	public static ByteArrayOutputStream batchExport(List<T411_Bean> list, String sheetName, Map<String,Integer> maplist, List<String> columns) throws Exception{
		
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
                    ws.addCell(new Label(i, 0, columns.get(i), wcf));  
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
                        		ws.addCell(new Label(0,i,""+i)); 
                        		continue;
                        	}
                        	                        	
        					String type = wrapper.getPropertyType(column).toString() ;

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
            }

            wwb.write();
            wwb.close();

        } catch (IOException e){
        } catch (RowsExceededException e){
        } catch (WriteException e){}
        
        return fos ;
    }
}

