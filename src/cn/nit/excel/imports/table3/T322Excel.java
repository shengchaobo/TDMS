package cn.nit.excel.imports.table3;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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
import cn.nit.bean.di.DiMajorTwoBean;
import cn.nit.bean.table3.T322_Bean;
import cn.nit.bean.table4.T411_Bean;
import cn.nit.constants.Constants;
import cn.nit.service.di.DiAwardLevelService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.di.DiMajorTwoService;
import cn.nit.service.table3.T322_Service;
import cn.nit.service.table4.T411_Service;
import cn.nit.util.TimeUtil;

public class T322Excel {
	
	
	/**
	 * 批量导入
	 * @param cellList {@link java.util.List<{@link jxl.Cell}>}  Cell是数组
	 * @param request  {@link javax.servlet.http.HttpServletRequest}
	 * @return
	 */
	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request, String selectYear){

		
		UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		String fillUnitID = bean.getUnitID();
		System.out.println("大小");
		System.out.println(cellList.size());
		if((cellList == null) || (cellList.size() < 2)){
			return "数据不标准，请重新提交" ;
		}
		
		int count = 1 ;
		 Date time=new Date();
		boolean flag = false ;
		List<T322_Bean> list = new LinkedList<T322_Bean>() ;
		UserinfoBean userinfo = (UserinfoBean)request.getSession().getAttribute("userinfo") ;
		DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		DiMajorTwoService diMajorTwoSer=new DiMajorTwoService();
		List<DiMajorTwoBean> diMajorTwoList=diMajorTwoSer.getList();
		DiAwardLevelService diAwardLevelSer=new DiAwardLevelService();
		List<DiAwardLevelBean> diAwardLevelList=diAwardLevelSer.getList();
		T411_Service t411_Ser=new T411_Service();
		List<T411_Bean> t411_BeanList = t411_Ser.getList();
	
		
		for(Cell[] cell : cellList){
			
			T322_Bean t322_Bean = new  T322_Bean();
				
				
			  try{
				  
				    if(count<5){
				    	count++;
				    	continue;
				    }
				    
					String MajorName = cell[1].getContents().trim();
					String MajorID=cell[2].getContents().trim();
					
					if(MajorName == null || MajorName.equals("")){
						return "第" + count + "行，专业名称不能为空";
					}
					
					if(MajorID == null || MajorID.equals("")){
						return "第" + count + "行，专业代码不能为空";
					}

					for(DiMajorTwoBean diMajorTwoBean : diMajorTwoList){
						if(diMajorTwoBean.getMajorNum().equals(MajorID)){
							if(diMajorTwoBean.getMajorName().equals(MajorName)){
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
						flag=false;
					}
					
					
					String MajorVersion=cell[3].getContents().trim();
					if(MajorVersion == null || MajorVersion.equals("")){
						return "第" + count + "行，代码版本不能为空";
					}
					if(!(MajorVersion.equals("2012")||MajorVersion.equals("1998")||MajorVersion.equals("99"))){
						return  "第" + count + "行，代码版本有误" ;
						
					}
					
					String SchMajorName=cell[4].getContents().trim();
					String SchMajorID=cell[5].getContents().trim();
					if(SchMajorName == null || SchMajorName.equals("")){
						return "第" + count + "行，校内专业名称不能为空";
					} 
					
					if(SchMajorID == null || SchMajorID.equals("")){
						return "第" + count + "行，校内专业代码不能为空";
					}
					
					String MajorField=cell[6].getContents().trim();
					String MajorFieldID=cell[7].getContents().trim();
					if(MajorField == null || MajorField.equals("")){
						return "第" + count + "行，专业方向名称没有请填无";
					}
					if(MajorFieldID == null || MajorFieldID.equals("")){
						return "第" + count + "行，专业方向号没有请填无";
					}
					
					String MajorSetTime = cell[8].getContents().trim() ;
					
					if(MajorSetTime == null || MajorSetTime.equals("")){
						return "第" + count + "行，专业设置时间不能为空" ;
					}
					
				    if(!TimeUtil.judgeFormatYM(MajorSetTime)){
						return "第" + count + "行，专业设置时间格式有误（格式如：2013-02）" ;
					}
				    
				    String MajorAppvlID = cell[9].getContents().trim();
				    
				    if(MajorAppvlID == null || MajorAppvlID.equals("")){
				    	return "第" + count + "行，批文号不能为空" ;
				    }

					String MajorDurition1 = cell[10].getContents().trim();
					if(MajorDurition1 == null || MajorDurition1.equals("")){
				    	return "第" + count + "行，学制不能为空" ;
				    }
					int MajorDurition;
					try{
						MajorDurition=Integer.parseInt(MajorDurition1);
						if(MajorDurition!=4&&MajorDurition!=5){
							return "第" + count + "行，学制必须是4或5" ;
						}
					}catch (Exception e){
						return "第" + count + "行，学制必须是4或5" ;
					}
					

					
					
					String MajorDegreeType=cell[11].getContents().trim();
					if(MajorDegreeType == null || MajorDegreeType.equals("")){
				    	return "第" + count + "行，学位授予每类不能为空" ;
				    }
					if(!(MajorDegreeType.equals("01哲学")||MajorDegreeType.equals("02经济学")||MajorDegreeType.equals("03法学")||MajorDegreeType.equals("04教育学")||MajorDegreeType.equals("05文学")||MajorDegreeType.equals("06历史学")||MajorDegreeType.equals("07理学")||MajorDegreeType.equals("08工学")||MajorDegreeType.equals("09农学")||MajorDegreeType.equals("10医学")||MajorDegreeType.equals("11军事学")||MajorDegreeType.equals("12管理学")||MajorDegreeType.equals("13艺术学"))){
						return "第" + count + "行，学位授予门类输入有误" ;
					}
					
					String MajorAdmisTime = cell[12].getContents().trim() ;
					if(MajorAdmisTime == null || MajorAdmisTime.equals("")){
						return "第" + count + "行，开始招生时间不能为空" ;
					}
				    if(!TimeUtil.judgeFormatYM(MajorAdmisTime)){
						return "第" + count + "行，开始招生时间格式有误（格式如：2013-02）" ;
					}
					String MajorState=cell[13].getContents().trim();
					if(MajorState == null || MajorState.equals("")){
						return "第" + count + "行，招生状态不能为空" ;
					}
					String StopAdmisTime=cell[14].getContents().trim();

					if(MajorState.equals("在招")){
						if(!StopAdmisTime.isEmpty()){
							return  "第" + count + "行，在招,停止招生时间应该不填 ";
						}
						
					}else if(MajorState.equals("当年停招")){
						if(StopAdmisTime.isEmpty()){
							return  "第" + count + "行，停招，停止招生时间不能为空 ";
						}else{
						    if(!TimeUtil.judgeFormatYM(StopAdmisTime)){
								return "第" + count + "行，停止招生时间格式有误（格式如：2013-02）" ;
							}
						}
						
					}else{
						return  "第" + count + "行，招生状态必须为'在招'或'当年停招'";
					}
					

					
					boolean IsNewMajor;
					String IsNewMajor1=cell[15].getContents().trim();
				    if(IsNewMajor1.equals("是")){
				    	IsNewMajor=true;
				    }else if(IsNewMajor1.equals("否")){
				    	IsNewMajor=false;
				    }else{
				    	return  "第" + count + "行，是否新办专业必须为是或否 ";
				    }
				    
				    String MajorFeature=cell[16].getContents().trim();
				    String MajorPurpose=cell[17].getContents().trim();
				    
				    if(MajorFeature == null || MajorFeature.equals("")||MajorFeature.length()>1000){
						return "第" + count + "行，专业特色不能为空且字数不能超过1000";
					} 
					
					if(MajorPurpose == null || MajorPurpose.equals("")||MajorPurpose.length()>100){
						return "第" + count + "行，专业培养目标不能为空且字数不能超过1000";
					}
				    
				    
					String AppvlYear = cell[18].getContents().trim() ;
					
					if(!(AppvlYear == null || AppvlYear.equals(""))){
					    if(!TimeUtil.judgeFormatYM(AppvlYear)){
							return "第" + count + "行，批准建设年度格式有误（格式如：2013-02）" ;
						}
					}
					

				    
				    String BuildAppvlID = cell[19].getContents().trim();
				    


				    String MajorLevel=cell[20].getContents().trim();
					if(!(MajorLevel == null || MajorLevel.equals(""))){
						for(DiAwardLevelBean diAwardLevelBean : diAwardLevelList){
							if(diAwardLevelBean.getAwardLevel().equals(MajorLevel)){
								MajorLevel = diAwardLevelBean.getIndexId();
									flag = true ;
									break ;
								}	
							}
						if(flag==false){
							return "第" + count + "行，所填级别有误" ;
			
						}else{
							flag=false;
						}
					}


					
					
					
					String Type=cell[21].getContents().trim();
					if(!(Type == null || Type.equals(""))){
					if(!(Type.equals("特色专业")||Type.equals("品牌专业")||Type.equals("名牌专业")||Type.equals("示范专业")||Type.equals("重点建设专业")||Type.equals("地方优势专业")||Type.equals("其他"))){
						return "第" + count + "行，所填类型有误" ;
					}
					}
					
				    String Field = cell[22].getContents().trim();
				    


					String Leader = cell[23].getContents().trim();
					String TeaID=cell[24].getContents().trim();
					
					if(!((Leader == null || Leader.equals(""))&&(TeaID == null || TeaID.equals("")))){
						for(T411_Bean t411_Bean : t411_BeanList){
							if(t411_Bean.getTeaId().equals(TeaID)){
								if(t411_Bean.getTeaName().equals(Leader)){
									flag = true ;
									break ;
								}else{
									return "第" + count + "行，建设负责人与教工号不对应" ;
									
								}
							}//if
						}//for
						
						if(!flag){
							return "第" + count + "行，没有与之相匹配的教工号" ;
						}else{
							flag=false;
						}
					}



					
					String CheckTime = cell[25].getContents().trim() ;
					
					if(!(CheckTime == null || CheckTime.equals(""))){
						return "第" + count + "行，验收时间格式有误（格式如：2013-02）" ;
					}
					
				    String CheckAppvlID = cell[26].getContents().trim();
				    
//				    if(CheckAppvlID == null || CheckAppvlID.equals("")){
//				    	return "第" + count + "行，验收批文号不能为空" ;
//				    }

				    Pattern pattern = Pattern.compile("([-\\+]?[0-9]([0-9]*)(\\.[0-9]+)?)|(^0$)"); 
				    String SchExp1=cell[27].getContents().trim();
				    if(!(SchExp1 == null || SchExp1.equals(""))){
				    	if(!pattern.matcher(SchExp1).matches()){
					    	return "第" + count + "行，学校经费(万元)应该填数字" ;
					    }
				    }
				    double SchExp = Double.parseDouble(SchExp1);
				    String EduMinistryExp1 = cell[28].getContents().trim();
				    if(!(EduMinistryExp1 == null || EduMinistryExp1.equals(""))){
				    	if(!pattern.matcher(EduMinistryExp1).matches()){
					    	return "第" + count + "行，教育部经费(万元)应该填数字" ;
					    }
				    } 
				    double EduMinistryExp=Double.parseDouble(EduMinistryExp1);
				    
				
					String FirstAppvlTime = cell[29].getContents().trim() ;
					String AppvlTime = cell[30].getContents().trim() ;
				    String AppvlID = cell[31].getContents().trim();
					String AppvlResult=cell[32].getContents().trim();
					String FromTime = cell[33].getContents().trim() ;
					String EndTime = cell[34].getContents().trim() ;
				    String AppvlAuth = cell[35].getContents().trim();
				    
					if(AppvlResult == null || AppvlResult.equals("")){
						return "第" + count + "行，认证结果不能为空" ;
					}
				    
				    if(AppvlResult.equals("通过")){
						if(FirstAppvlTime == null || FirstAppvlTime.equals("")){
							return "第" + count + "行，认证结果为通过，首次通过认证时间不能为空" ;
						}
						
					    if(!TimeUtil.judgeFormatYM(FirstAppvlTime)){
							return "第" + count + "行，首次通过认证时间格式有误（格式如：2013-02）" ;
						}
						if(AppvlTime == null || AppvlTime.equals("")){
							return "第" + count + "行，认证结果为通过，认证时间不能为空" ;
						}
						
					    if(!TimeUtil.judgeFormatYM(AppvlTime)){
							return "第" + count + "行，认证时间格式有误（格式如：2013-02）" ;
						}
					    if(AppvlID == null || AppvlID.equals("")){
					    	return "第" + count + "行，认证结果为通过，批文号不能为空" ;
					    }

						if(FromTime == null || FromTime.equals("")){
							return "第" + count + "行，认证结果为通过，有效期起不能为空" ;
						}
						
					    if(!TimeUtil.judgeFormatYM(FromTime)){
							return "第" + count + "行，有效期起格式有误（格式如：2013-02）" ;
						}
						if(EndTime == null || EndTime.equals("")){
							return "第" + count + "行，认证结果为通过，有效期止不能为空" ;
						}
						
					    if(!TimeUtil.judgeFormatYM(EndTime)){
							return "第" + count + "行，有效期止格式有误（格式如：2013-02）" ;
						}
					    if(AppvlAuth == null || AppvlAuth.equals("")){
					    	return "第" + count + "行，认证结果为通过，认证机构不能为空" ;
					    }

				    }else if(AppvlResult.equals("未通过")){
						if(!(FirstAppvlTime == null || FirstAppvlTime.equals(""))){
						    if(!TimeUtil.judgeFormatYM(FirstAppvlTime)){
								return "第" + count + "行，首次通过认证时间格式有误（格式如：2013-02）" ;
							}
						}
						

						if(AppvlTime == null || AppvlTime.equals("")){
							return "第" + count + "行，认证结果为未通过，认证时间不能为空" ;
						}
						
					    if(!TimeUtil.judgeFormatYM(AppvlTime)){
							return "第" + count + "行，认证时间格式有误（格式如：2013-02）" ;
						}
					    if(AppvlID == null || AppvlID.equals("")){
					    	return "第" + count + "行，认证结果为未通过，批文号不能为空" ;
					    }

						if(!(FromTime == null || FromTime.equals(""))){
							return "第" + count + "行，认证结果为未通过，有效期起应该为空" ;
						}

						if(!(EndTime == null || EndTime.equals(""))){
							return "第" + count + "行，认证结果为未通过，有效期止应该为空" ;
						}

					    if(AppvlAuth == null || AppvlAuth.equals("")){
					    	return "第" + count + "行，认证结果为未通过，认证机构不能为空" ;
					    }

				    }else if(AppvlResult.equals("未参加评估")){
//						if(FirstAppvlTime == null || FirstAppvlTime.equals("")){
//							return "第" + count + "行，首次通过认证时间不能为空" ;
//						}
//						
//					    if(!TimeUtil.judgeFormatYM(FirstAppvlTime)){
//							return "第" + count + "行，首次通过认证时间格式有误（格式如：2013/02）" ;
//						}
						if(!(AppvlTime == null || AppvlTime.equals(""))){
							return "第" + count + "行，未参加评估，认证时间应该为空" ;
						}

					    if(!(AppvlID == null || AppvlID.equals(""))){
					    	return "第" + count + "行，未参加评估，批文号应该为空" ;
					    }
						if(!(FromTime == null || FromTime.equals(""))){
							return "第" + count + "行，未参加评估，有效期起应该为空" ;
						}

						if(!(EndTime == null || EndTime.equals(""))){
							return "第" + count + "行，未参加评估，有效期止应该为空" ;
						}

					    if(!(AppvlAuth == null || AppvlAuth.equals(""))){
					    	return "第" + count + "行，未参加评估，认证机构应该为空" ;
					    }
				    	
				    }else{
				    	return "第"+count+"行，认证结果填写错误";
				    	
				    }
				    

				    String TotalCSHour1 = cell[36].getContents().trim();
				    
				    if(TotalCSHour1 == null || TotalCSHour1.equals("")){
				    	return "第" + count + "行，总学时数不能为空" ;
				    }else if(!pattern.matcher(TotalCSHour1).matches()){
				    	return "第" + count + "行，总学时数应该填数字" ;
				    }
				    int TotalCSHour=Integer.parseInt(TotalCSHour1);
				    
				    String RequireCShour1 = cell[37].getContents().trim();
				 
				    if(RequireCShour1 == null || RequireCShour1.equals("")){
				    	return "第" + count + "行，没有必修课学时数请填0" ;
				    }else if(!pattern.matcher(RequireCShour1).matches()){
				    	return "第" + count + "行，必修课学时数应该填数字" ;
				    }
				    int RequireCShour=Integer.parseInt(RequireCShour1);
				    
				    String OptionCSHour1 = cell[38].getContents().trim();
			
				    if(OptionCSHour1 == null || OptionCSHour1.equals("")){
				    	return "第" + count + "行，没有选修课学时数请填0" ;
				    }else if(!pattern.matcher(OptionCSHour1).matches()){
				    	return "第" + count + "行，选修课学时数应该填数字" ;
				    }
				    int OptionCSHour=Integer.parseInt(OptionCSHour1);
				    
				    String InClassCSHour1 = cell[39].getContents().trim();
				  
				    if(InClassCSHour1 == null || InClassCSHour1.equals("")){
				    	return "第" + count + "行，没有课内教学学时数请填0" ;
				    }else if(!pattern.matcher(InClassCSHour1).matches()){
				    	return "第" + count + "行，课内教学学时数应该填数字" ;
				    }
				    int InClassCSHour=Integer.parseInt(InClassCSHour1);
				    
				    String ExpCSHour1 = cell[40].getContents().trim();
				   
				    if(ExpCSHour1 == null || ExpCSHour1.equals("")){
				    	return "第" + count + "行，没有实验教学学时数请填0" ;
				    }else if(!pattern.matcher(ExpCSHour1).matches()){
				    	return "第" + count + "行，实验教学学时数应该填数字" ;
				    }
				    int ExpCSHour=Integer.parseInt(ExpCSHour1);
				    
				    String PraCSHour1 = cell[41].getContents().trim();
				   
				    if(PraCSHour1 == null || PraCSHour1.equals("")){
				    	return "第" + count + "行，没有集中性实践教学环节学时数请填0" ;
				    }else if(!pattern.matcher(PraCSHour1).matches()){
				    	return "第" + count + "行，集中性实践教学环节学时数应该填数字" ;
				    }
				    int PraCSHour=Integer.parseInt(PraCSHour1);

				    String TotalCredit1 = cell[42].getContents().trim();
				    if(TotalCredit1 == null || TotalCredit1.equals("")){
				    	return "第" + count + "行，总学分数不能为空" ;
				    }else if(!pattern.matcher(TotalCredit1).matches()){
				    	return "第" + count + "行，总学分数应该填数字" ;
				    }
				    double TotalCredit=Double.parseDouble(TotalCredit1);
				    
				    String RequireCredit1 = cell[43].getContents().trim();
				    if(RequireCredit1 == null || RequireCredit1.equals("")){
				    	return "第" + count + "行，没有必修课学分数请填0" ;
				    }else if(!pattern.matcher(RequireCredit1).matches()){
				    	return "第" + count + "行，必修课学分数应该填数字" ;
				    }
				    double RequireCredit=Double.parseDouble(RequireCredit1);
				    
				    String OptionCredit1 = cell[44].getContents().trim();
				    if(OptionCredit1 == null || OptionCredit1.equals("")){
				    	return "第" + count + "行，没有选修课学分数请填0" ;
				    }else if(!pattern.matcher(OptionCredit1).matches()){
				    	return "第" + count + "行，选修课学分数应该填数字" ;
				    }
				    double OptionCredit=Double.parseDouble(OptionCredit1);
				    
				    String InClassCredit1 = cell[45].getContents().trim();
				    if(InClassCredit1 == null || InClassCredit1.equals("")){
				    	return "第" + count + "行，没有课内教学学分数请填0" ;
				    }else if(!pattern.matcher(InClassCredit1).matches()){
				    	return "第" + count + "行，课内教学学分数应该填数字" ;
				    }
				    double InClassCredit=Double.parseDouble(InClassCredit1);
				    
				    String ExpCredit1 = cell[46].getContents().trim();				   
				    if(ExpCredit1 == null || ExpCredit1.equals("")){
				    	return "第" + count + "行，没有实验教学学分数请填0" ;
				    }else if(!pattern.matcher(ExpCredit1).matches()){
				    	return "第" + count + "行，实验教学学分数应该填数字" ;
				    }
				    double ExpCredit=Double.parseDouble(ExpCredit1);
				    
				    String PraCredit1 = cell[47].getContents().trim();
				    if(PraCredit1 == null || PraCredit1.equals("")){
				    	return "第" + count + "行，没有集中实践教学环节学分数请填0" ;
				    }else if(!pattern.matcher(PraCredit1).matches()){
				    	return "第" + count + "行，集中实践教学环节学分数应该填数字" ;
				    }
				    double PraCredit=Double.parseDouble(PraCredit1);
				    
				    String OutClassCredit1 = cell[48].getContents().trim();
				    if(OutClassCredit1 == null || OutClassCredit1.equals("")){
				    	return "第" + count + "行，没有课外科技活动学分数请填0" ;
				    }else if(!pattern.matcher(OutClassCredit1).matches()){
				    	return "第" + count + "行，课外科技活动学分数应该填数字" ;
				    }
				    double OutClassCredit=Double.parseDouble(OutClassCredit1);
				    

				    if(TotalCredit != RequireCredit+OptionCredit+PraCredit+OutClassCredit || TotalCredit != InClassCredit+ExpCredit+PraCredit+OutClassCredit){
				    	return "第"+count+"行，总学分填写错误";
				    }
					
				
				count++ ;
				
				
				t322_Bean.setSchMajorID(SchMajorID);
				t322_Bean.setSchMajorName(SchMajorName);
				t322_Bean.setMajorFeature(MajorFeature);
				t322_Bean.setMajorPurpose(MajorPurpose);
				t322_Bean.setMajorName(MajorName);
				t322_Bean.setMajorID(MajorID);
				t322_Bean.setMajorVersion(MajorVersion);
				t322_Bean.setMajorField(MajorField);
				t322_Bean.setMajorFieldID(MajorFieldID);
				t322_Bean.setMajorSetTime(TimeUtil.changeDateYM(MajorSetTime));
				t322_Bean.setMajorAppvlID(MajorAppvlID);
				t322_Bean.setMajorDurition(MajorDurition);
				t322_Bean.setMajorDegreeType(MajorDegreeType);
				t322_Bean.setMajorAdmisTime(TimeUtil.changeDateYM(MajorAdmisTime));
				t322_Bean.setMajorState(MajorState);
				if(StopAdmisTime.equals("")||StopAdmisTime == null){
					t322_Bean.setStopAdmisTime(null);
				}else{
					t322_Bean.setStopAdmisTime(TimeUtil.changeDateYM(StopAdmisTime));
				}
				t322_Bean.setIsNewMajor(IsNewMajor);
				t322_Bean.setAppvlYear(TimeUtil.changeDateYM(AppvlYear));
				t322_Bean.setBuildAppvlID(BuildAppvlID);
				t322_Bean.setMajorLevel(MajorLevel);
				t322_Bean.setType(Type);
				t322_Bean.setField(Field);
				t322_Bean.setLeader(Leader);
				t322_Bean.setTeaID(TeaID);
				t322_Bean.setCheckTime(TimeUtil.changeDateYM(CheckTime));
				t322_Bean.setCheckAppvlID(CheckAppvlID);
				t322_Bean.setSchExp(SchExp);
				t322_Bean.setEduMinistryExp(EduMinistryExp);
				if(FirstAppvlTime.equals("")||FirstAppvlTime == null){
					t322_Bean.setFirstAppvlTime(null);
				}else{
					t322_Bean.setFirstAppvlTime(TimeUtil.changeDateYM(FirstAppvlTime));

				}
				if(AppvlTime.equals("")||AppvlTime == null){
					t322_Bean.setAppvlTime(null);
				}else{
					t322_Bean.setAppvlTime(TimeUtil.changeDateYM(AppvlTime));
				}
				t322_Bean.setAppvlID(AppvlID);
				t322_Bean.setAppvlResult(AppvlResult);
				if(FromTime.equals("")||FromTime == null){
					t322_Bean.setFromTime(null);
				}else{
					t322_Bean.setFromTime(TimeUtil.changeDateYM(FromTime));
				}
				if(EndTime.equals("")||EndTime == null){
					t322_Bean.setEndTime(null);
				}else{
					t322_Bean.setEndTime(TimeUtil.changeDateYM(EndTime));
				}
				t322_Bean.setAppvlAuth(AppvlAuth);
				t322_Bean.setTotalCSHour(TotalCSHour);
				t322_Bean.setRequireCShour(RequireCShour);
				t322_Bean.setOptionCSHour(OptionCSHour);
				t322_Bean.setInClassCSHour(InClassCSHour);
				t322_Bean.setExpCSHour(ExpCSHour);
				t322_Bean.setPraCSHour(PraCSHour);
				t322_Bean.setTotalCredit(TotalCredit);
				t322_Bean.setRequireCredit(RequireCredit);
				t322_Bean.setOptionCredit(OptionCredit);
				t322_Bean.setInClassCredit(InClassCredit);
				t322_Bean.setExpCredit(ExpCredit);
				t322_Bean.setPraCredit(PraCredit);
				t322_Bean.setOutClassCredit(OutClassCredit);
				t322_Bean.setFillUnitID(fillUnitID);
				t322_Bean.setCheckState(Constants.WAIT_CHECK);
				t322_Bean.setTime(TimeUtil.changeDateY(selectYear));
				list.add(t322_Bean);
				System.out.println("数字");
				System.out.println(count);

			}
			catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
	     }
		
		flag = false ;
		T322_Service t322_Ser = new T322_Service() ;
		flag = t322_Ser.batchInsert(list) ;

		if(flag){
			return null ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}

}
	
	
	
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
                    ws.addCell(new Label(i, 3, columns.get(i), wcf));  
                }  
            }
            ws.addCell(new Label(0,2,"",wcf));
            ws.addCell(new Label(1,2,"1.专业设置情况",wcf));
            ws.addCell(new Label(18,2,"2.优势专业建设情况",wcf));
            ws.addCell(new Label(29,2,"3.专业认证（评估）情况",wcf));
            ws.addCell(new Label(36,2,"1.学时数（学时）",wcf));
            ws.addCell(new Label(42,2,"2.学分数（学分）",wcf));
            ws.mergeCells(1, 2, 17, 2);
            ws.mergeCells(18, 2, 28, 2);
            ws.mergeCells(29, 2, 35, 2);
            ws.mergeCells(36, 2, 41, 2);
            ws.mergeCells(42, 2, 48, 2);

  
                //判断表中是否有数据  
            if (list != null && list.size() > 0) {  
                    //循环写入表中数据  
                	BeanWrapperImpl wrapper = new BeanWrapperImpl() ;
                	int i=0;  
                	for(Object obj : list){  
                		wrapper.setWrappedInstance(obj) ;  
                        //循环输出map中的子集：既列值                         
                        for(String column:maplist.keySet()){
                        	
                        	if(column.equals("SeqNum")){
                        		ws.addCell(new Label(0,i+4,""+i,wcf1)); 
                        		continue;
                        	}
                        	                        	
        					String type = wrapper.getPropertyType(column).toString() ;
//        					System.out.println(type +"-test：" + column);

        					//判断插入数据的类型，并赋�?
        					if(type.endsWith("String")){
        						ws.addCell(new Label(maplist.get(column).intValue(),i+4,(String) wrapper.getPropertyValue(column),wcf1));
        					}else if(type.endsWith("int")||type.endsWith("Integer")){
        						ws.addCell(new Label(maplist.get(column).intValue(),i+4,(String) wrapper.getPropertyValue(column).toString(),wcf1));
        					}else if(type.endsWith("Date")){
        						if((java.util.Date)wrapper.getPropertyValue(column) == null){
        							ws.addCell(new Label(maplist.get(column).intValue(),i+4,null,wcf1));
        						}else{
            						java.util.Date utilDate = (java.util.Date)wrapper.getPropertyValue(column) ;
            						Date sqlDate = new Date(utilDate.getTime()) ;
            						SimpleDateFormat sfEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            						SimpleDateFormat sfStart = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",java.util.Locale.ENGLISH) ;
//            						sfEnd.format(sfStart.parse(sqlDate.toString()));
            						
            						ws.addCell(new Label(maplist.get(column).intValue(),i+4,sfEnd.format(sfStart.parse(sqlDate.toString())).toString().substring(0, 7),wcf1));
        							
        						}
        					}else if(type.endsWith("long")||type.endsWith("Long")){
        						ws.addCell(new Label(maplist.get(column).intValue(),i+4,(String) wrapper.getPropertyValue(column).toString(),wcf1));
        					}else if(type.endsWith("boolean")||type.endsWith("Boolean")){
        						if((Boolean)wrapper.getPropertyValue(column)){
        							ws.addCell(new Label(maplist.get(column).intValue(),i+4,"是",wcf1));
        						}else{
        							ws.addCell(new Label(maplist.get(column).intValue(),i+4,"否",wcf1));
        						}
        					}else if(type.endsWith("double")||type.endsWith("Double")){
        						ws.addCell(new Label(maplist.get(column).intValue(),i+4,(String) wrapper.getPropertyValue(column).toString(),wcf1));
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
}
