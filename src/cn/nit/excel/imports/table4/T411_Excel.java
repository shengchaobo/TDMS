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
import cn.nit.bean.table4.T411_Bean;
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
import cn.nit.service.table4.T411_Service;
import cn.nit.util.TimeUtil;

public class T411_Excel {

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
		boolean flag = false ;
		List<T411_Bean> list = new LinkedList<T411_Bean>() ;
		
		DiIdentiTypeService diIdenti = new DiIdentiTypeService();
		List<DiIdentiTypeBean> diIdentiBeanList = diIdenti.getList();
		
		DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		
		DiResearchRoomService diResearchRoomSer = new DiResearchRoomService() ;
		List<DiResearchRoomBean> diResearchRoomBeanList = diResearchRoomSer.getList() ;
		
		DiEducationService diEdu = new DiEducationService();
		List<DiEducationBean> diEduList = diEdu.getList();
		
		DiDegreeService diDegree = new DiDegreeService();
		List<DiDegreeBean> diDegreeList = diDegree.getList();
		
		DiSourceService diSource = new DiSourceService();
		List<DiSourceBean> diSourceList = diSource.getList();
		
		DiTitleLevelService diTitle = new DiTitleLevelService();
		List<DiTitleLevelBean> diTitleList = diTitle.getList();
		
		DiTitleNameService diTitleName = new DiTitleNameService();
		List<DiTitleNameBean> diTitleNameList = diTitleName.getList();
				
		for(Cell[] cell : cellList){
			try{
				if(count<=3){
					count++;
					continue;
				}
				
				String name = cell[1].getContents().trim() ;
				String teaId = cell[2].getContents().trim() ;
				
				System.out.println(name);
				System.out.println(teaId);
				if(name == null || name.equals("")){
					return "第" + count + "行，教师名称不能为空" ;
				}
				
				if((teaId == null) || teaId.equals("")){
					return "第" + count + "行，教工号不能为空" ;
				}
				
				String gender = cell[3].getContents().trim();
				if(gender == null || gender.equals("")){
					return "第" + count + "行，教师性别不能为空" ;
				}
				
				String birthday = cell[4].getContents().trim() ;
				if((birthday == null) || birthday.equals("")){
					return "第" + count + "行，教师出生日期不能为空" ;
				}else{
					if(!TimeUtil.judgeFormatYM(birthday)){
						return "第" + count + "行，教师出生日期格式不正确" ;
					}
				}
				
				String admisTime = cell[5].getContents().trim() ;
				if((admisTime == null) || admisTime.equals("")){
					return "第" + count + "行，教师入校时间不能为空" ;
				}else{
					if(!TimeUtil.judgeFormatYM(admisTime)){
						return "第" + count + "行，教师入校时间格式不正确" ;
					}
				}
				
				String teaState = cell[6].getContents().trim() ;
				if((teaState == null) || teaState.equals("")){
					return "第" + count + "行，教师任职状态不能为空" ;
				}
				
				String beginTime = cell[7].getContents().trim() ;
/*				if((beginTime == null) || beginTime.equals("")){
					return "第" + count + "行，教师本科工作开始时间不能为空" ;
				}else{
					if(!TimeUtil.judgeFormatY(beginTime)){
						return "第" + count + "行，教师本科工作开始时间格式不正确" ;
					}
				}*/
				
				String IDCode = cell[8].getContents().trim() ;
				if((IDCode == null) || IDCode.equals("")){
					return "第" + count + "行，教师身份类别不能为空" ;
				}
				
				String id = null;
				for(DiIdentiTypeBean diIdentibean : diIdentiBeanList){
					if(diIdentibean.getIdentiType().equals(IDCode)){
						id = diIdentibean.getIndexId() ;
						flag = true  ;
						break ;
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，教师身份类别不存在" ;
				}else{
					flag = false ;
				}
				
				
				String office = cell[9].getContents().trim() ;
				String officId = cell[10].getContents().trim();
				
/*				if(office == null || office.equals("")){
					return "第" + count + "行，所属部门不能为空" ;
				}
				
				if(officId == null || officId.equals("")){
					return "第" + count + "行，所属部门号不能为空" ;
				}	*/		
				
/*				for(DiDepartmentBean diDepartBean : diDepartBeanList){
					if(diDepartBean.getUnitId().equals(officId)){
						if(diDepartBean.getUnitName().equals(office)){
							flag = true ;
							break ;
						}else{
							return "第" + count + "行，所属部门与所属部门号不对应" ;
						}
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，没有与之相匹配的部门编号" ;
				}else{
					flag = false ;
				}*/
				
				String unit = cell[11].getContents().trim() ;
				String unitId = cell[12].getContents().trim() ;
				
/*				if(unit == null || unit.equals("")){
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
				}*/
				
				String fromTeaResOffice = cell[13].getContents().trim() ;
				String teaResOfficeID = cell[14].getContents().trim();
				
/*				if(fromTeaResOffice == null || fromTeaResOffice.equals("")){
					return "第" + count + "行，所属教研室不能为空" ;
				}
				
				if(teaResOfficeID == null || teaResOfficeID.equals("")){
					return "第" + count + "行，所属教研室编号不能为空" ; 
				}
				
				if(teaResOfficeID.length() > 50){
					return "第" + count + "行，教研室编号字数不超过50个数字或字母" ;
				}
				
				for(DiResearchRoomBean diResearchRoomBean : diResearchRoomBeanList){
					if(diResearchRoomBean.getUnitId().equals(teaResOfficeID)){
						if(diResearchRoomBean.getResearchName().equals(fromTeaResOffice)){
							flag = true ;
							break ;
						}else{
							return "第" + count + "行，教研室编号与教研室名称不一致" ;
						}
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，教研室编号不存在" ; 
				}else{
					flag = false ;
				}*/
				
				String edu = cell[15].getContents().trim() ;
				
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
				
				String degree = cell[16].getContents().trim().trim() ;
				
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
				
				String graSch = cell[17].getContents().trim() ;
				String major = cell[18].getContents().trim() ;
				
				String source = cell[19].getContents().trim() ;
				
/*				if(source == null || source.equals("")){
					return "第" + count + "行，教师学缘不能为空" ;
				}
				String sourceID = null;
				for(DiSourceBean SourceBean : diSourceList){
					if(SourceBean.getSource().equals(source)){
						sourceID = SourceBean.getIndexId() ;
						flag = true  ;
						break ;
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，教师学缘不存在" ;
				}else{
					flag = false ;
				}*/
				
				String adminTitle = cell[20].getContents().trim() ;
				
				
				String majTitle = cell[21].getContents().trim();
				
				if(majTitle == null || majTitle.equals("")){
					majTitle = "未定级";
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
				
				String teaTitle = cell[22].getContents().trim() ;
				
				if(teaTitle == null || teaTitle.equals("")){
					teaTitle = "未评级";
				}
				String teaTitleID = null;
				for(DiTitleNameBean titleNameBean : diTitleNameList){
					if(titleNameBean.getTitleName().equals(teaTitle)){
						teaTitleID = titleNameBean.getIndexId() ;
						flag = true  ;
						break ;
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，教学职称不存在" ;
				}else{
					flag = false ;
				}
				
				String notTeaTitle = cell[23].getContents().trim() ;
				
				String subject = cell[24].getContents().trim() ;
				
/*				if(subject == null || subject.equals("")){
					return "第" + count + "行，学科类别不能为空" ;
				}*/
				
				String doubleTea = cell[25].getContents().trim() ;
				boolean bdoubleTea;
				
				if(doubleTea == null || doubleTea.equals("")){
					bdoubleTea = false;
				}else{
					if(doubleTea == "是"){
						bdoubleTea = true;
					}else{
						bdoubleTea = false;
					}
				}
				
				String industry = cell[26].getContents().trim();
				boolean bindustry;
				
				if(industry == null || industry.equals("")){
					bindustry = false;
				}else{
					if(doubleTea == "是"){
						bindustry = true;
					}else{
						bindustry = false;
					}
				}
				
				String engineer = cell[27].getContents().trim() ;
				boolean bengineer;
				
				if(engineer == null || engineer.equals("")){
					bengineer = false;
				}else{
					if(engineer == "是"){
						bengineer = true;
					}else{
						bengineer = false;
					}
				}
				
				String teaBase = cell[28].getContents().trim() ;
				Boolean bteaBase = null;
				
				if(teaBase == null && "".equals(teaBase)){
					bteaBase = false;
				}else{
					if(teaBase == "是"){
						bteaBase = true;
					}else{
						bteaBase = false;
					}
				}
				
/*				String note = cell[29].getContents() ;
				
				if(note.length() > 1000){
					return "第" + count + "行，备注不能超过500个汉字" ;
				}*/
				
				count++ ;
				
				
				T411_bean = new T411_Bean() ;
				T411_bean.setTeaName(name);
				T411_bean.setTeaId(teaId);
				T411_bean.setGender(gender);
				T411_bean.setBirthday(TimeUtil.changeDateYM(birthday));
				T411_bean.setAdmisTime(TimeUtil.changeDateYM(admisTime));
				T411_bean.setTeaState(teaState);
				T411_bean.setBeginWorkTime(TimeUtil.changeDateY(beginTime));
				T411_bean.setIdcode(id);
				T411_bean.setOfficeID(officId);
				T411_bean.setFromOffice(office);
				T411_bean.setFromUnit(unit);
				T411_bean.setUnitId(unitId);
				T411_bean.setFromTeaResOffice(fromTeaResOffice);
				T411_bean.setTeaResOfficeID(teaResOfficeID);
				T411_bean.setEducation(eduID);
				T411_bean.setTopDegree(degreeID);
				T411_bean.setGraSch(graSch);
				T411_bean.setMajor(major);
				T411_bean.setSource(source);
				T411_bean.setAdminLevel(adminTitle);
				T411_bean.setMajTechTitle(majID);
				T411_bean.setTeaTitle(teaTitleID);
				T411_bean.setNotTeaTitle(notTeaTitle);
				T411_bean.setSubjectClass(subject);
				T411_bean.setDoubleTea(bdoubleTea);
				T411_bean.setIndustry(bindustry);
				T411_bean.setEngineer(bengineer);
				T411_bean.setTeaBase(bteaBase);
				list.add(T411_bean);
								
			}catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
		}
		
		flag = false ;
		T411_Service T411_services = new T411_Service() ;
		flag = T411_services.batchInsert(list) ;
		
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

