package cn.nit.excel.imports.table3;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import jxl.Cell;
import cn.nit.bean.di.DiAwardLevelBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.di.DiMajorTwoBean;
import cn.nit.bean.other.UserRoleBean;
import cn.nit.bean.table3.T322_Bean;
import cn.nit.bean.table4.T411_Bean;
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

		System.out.println("大小");
		System.out.println(cellList.size());
		if((cellList == null) || (cellList.size() < 2)){
			return "数据不标准，请重新提交" ;
		}
		
		int count = 1 ;
		 Date time=new Date();
		boolean flag = false ;
		List<T322_Bean> list = new LinkedList<T322_Bean>() ;
		UserRoleBean userinfo = (UserRoleBean)request.getSession().getAttribute("userinfo") ;
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
				    
					String MajorName = cell[1].getContents();
					String MajorID=cell[2].getContents();
					
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
					
					
					String MajorVersion=cell[3].getContents();
					if(MajorVersion == null || MajorVersion.equals("")){
						return "第" + count + "行，代码版本不能为空";
					}
					if(!(MajorVersion.equals("2012")||MajorVersion.equals("1998")||MajorVersion.equals("99"))){
						return  "第" + count + "行，代码版本有误" ;
						
					}
					
					String MajorField=cell[4].getContents();
					String MajorFieldID=cell[5].getContents();
					if(MajorField == null || MajorField.equals("")){
						return "第" + count + "行，专业方向名称没有请填无";
					}
					if(MajorFieldID == null || MajorFieldID.equals("")){
						return "第" + count + "行，专业方向号没有请填无";
					}
					
					String MajorSetTime = cell[6].getContents() ;
					
					if(MajorSetTime == null || MajorSetTime.equals("")){
						return "第" + count + "行，专业设置时间不能为空" ;
					}
					
				    if(!TimeUtil.judgeFormat1(MajorSetTime)){
						return "第" + count + "行，专业设置时间格式有误（格式如：2013/02）" ;
					}
				    
				    String MajorAppvlID = cell[7].getContents();
				    
				    if(MajorAppvlID == null || MajorAppvlID.equals("")){
				    	return "第" + count + "行，批文号不能为空" ;
				    }

					String MajorDurition1 = cell[8].getContents();
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
					

					
					
					String MajorDegreeType=cell[9].getContents();
					if(MajorDegreeType == null || MajorDegreeType.equals("")){
				    	return "第" + count + "行，学位授予每类不能为空" ;
				    }
					if(!(MajorDegreeType.equals("01哲学")||MajorDegreeType.equals("02经济学")||MajorDegreeType.equals("03法学")||MajorDegreeType.equals("04教育学")||MajorDegreeType.equals("05文学")||MajorDegreeType.equals("06历史学")||MajorDegreeType.equals("07理学")||MajorDegreeType.equals("08工学")||MajorDegreeType.equals("09农学")||MajorDegreeType.equals("10医学")||MajorDegreeType.equals("11军事学")||MajorDegreeType.equals("12管理学")||MajorDegreeType.equals("13艺术学"))){
						return "第" + count + "行，学位授予门类输入有误" ;
					}
					
					String MajorAdmisTime = cell[10].getContents() ;
					if(MajorAdmisTime == null || MajorAdmisTime.equals("")){
						return "第" + count + "行，开始招生时间不能为空" ;
					}
				    if(!TimeUtil.judgeFormat1(MajorAdmisTime)){
						return "第" + count + "行，开始招生时间格式有误（格式如：2013/02）" ;
					}
					String MajorState=cell[11].getContents();
					if(MajorState == null || MajorState.equals("")){
						return "第" + count + "行，招生状态不能为空" ;
					}
					String StopAdmisTime=cell[12].getContents();

					if(MajorState.equals("在招")){
						if(!StopAdmisTime.isEmpty()){
							return  "第" + count + "行，在招生状态为'在招'的情况下，停止招生时间应该不填 ";
						}
						
					}else if(MajorState.equals("当年停招")){
						if(StopAdmisTime.isEmpty()){
							return  "第" + count + "行，在招生状态为当年停招的情况下，停止招生时间不能为空 ";
						}else{
						    if(!TimeUtil.judgeFormat1(StopAdmisTime)){
								return "第" + count + "行，停止招生时间格式有误（格式如：2013/02）" ;
							}
						}
						
					}else{
						return  "第" + count + "行，招生状态必须为'在招'或'当年停招'";
					}
					

					
					boolean IsNewMajor;
					String IsNewMajor1=cell[13].getContents();
				    if(IsNewMajor1.equals("是")){
				    	IsNewMajor=true;
				    }else if(IsNewMajor1.equals("否")){
				    	IsNewMajor=false;
				    }else{
				    	return  "第" + count + "行，是否新办专业必须为是或否 ";
				    }
				    
					String AppvlYear = cell[14].getContents() ;
					
					if(AppvlYear == null || AppvlYear.equals("")){
						return "第" + count + "行，批准建设年度不能为空" ;
					}
					
				    if(!TimeUtil.judgeFormat1(AppvlYear)){
						return "第" + count + "行，批准建设年度格式有误（格式如：2013/02）" ;
					}
				    
				    String BuildAppvlID = cell[15].getContents();
				    
				    if(BuildAppvlID == null || BuildAppvlID.equals("")){
				    	return "第" + count + "行，建设批文号不能为空" ;
				    }

				    String MajorLevel=cell[16].getContents();
					if(MajorLevel == null || MajorLevel.equals("")){
						return "第" + count + "行，级别不能为空";
					}
					for(DiAwardLevelBean diAwardLevelBean : diAwardLevelList){
						if(diAwardLevelBean.getAwardLevel().equals(MajorLevel)){
								flag = true ;
								break ;
							}	
						}
					if(flag==false){
						return "第" + count + "行，所填级别有误" ;
		
					}else{
						flag=false;
					}
					
					
					
					String Type=cell[17].getContents();
					if(!(Type.equals("特色专业")||Type.equals("品牌专业")||Type.equals("名牌专业")||Type.equals("示范专业")||Type.equals("重点建设专业")||Type.equals("地方优势专业"))){
						return "第" + count + "行，所填类型有误" ;
					}
					
				    String Field = cell[18].getContents();
				    
				    if(Field == null || Field.equals("")){
				    	return "第" + count + "行，领域、方向不能为空" ;
				    }

					String Leader = cell[19].getContents();
					String TeaID=cell[20].getContents();
					
					if(Leader == null || Leader.equals("")){
						return "第" + count + "行，建设负责人不能为空";
					}
					
					if(TeaID == null || TeaID.equals("")){
						return "第" + count + "行，教工号不能为空";
					}

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
					
					String CheckTime = cell[21].getContents() ;
					
					if(CheckTime == null || CheckTime.equals("")){
						return "第" + count + "行，验收时间不能为空" ;
					}
					
				    if(!TimeUtil.judgeFormat1(CheckTime)){
						return "第" + count + "行，验收时间格式有误（格式如：2013/02）" ;
					}
					
				    String CheckAppvlID = cell[22].getContents();
				    
				    if(CheckAppvlID == null || CheckAppvlID.equals("")){
				    	return "第" + count + "行，验收批文号不能为空" ;
				    }

				    Pattern pattern = Pattern.compile("([-\\+]?[1-9]([0-9]*)(\\.[0-9]+)?)|(^0$)"); 
				    String SchExp1=cell[23].getContents();
				    if(SchExp1 == null || SchExp1.equals("")){
				    	return "第" + count + "行，学校经费(万元)不能为空" ;
				    }else if(!pattern.matcher(SchExp1).matches()){
				    	return "第" + count + "行，学校经费(万元)应该填数字" ;
				    }
				    double SchExp = Double.parseDouble(SchExp1);
				    String EduMinistryExp1 = cell[24].getContents();
				    if(EduMinistryExp1 == null || EduMinistryExp1.equals("")){
				    	return "第" + count + "行，教育部经费(万元)不能为空" ;
				    }else if(!pattern.matcher(EduMinistryExp1).matches()){
				    	return "第" + count + "行，教育部经费(万元)应该填数字" ;
				    }
				    double EduMinistryExp=Double.parseDouble(EduMinistryExp1);
				    
				
					String FirstAppvlTime = cell[25].getContents() ;
					String AppvlTime = cell[26].getContents() ;
				    String AppvlID = cell[27].getContents();
					String AppvlResult=cell[28].getContents();
					String FromTime = cell[29].getContents() ;
					String EndTime = cell[30].getContents() ;
				    String AppvlAuth = cell[31].getContents();
				    
					if(AppvlResult == null || AppvlResult.equals("")){
						return "第" + count + "行，认证结果不能为空" ;
					}
				    
				    if(AppvlResult.equals("通过")){
						if(FirstAppvlTime == null || FirstAppvlTime.equals("")){
							return "第" + count + "行，首次通过认证时间不能为空" ;
						}
						
					    if(!TimeUtil.judgeFormat1(FirstAppvlTime)){
							return "第" + count + "行，首次通过认证时间格式有误（格式如：2013/02）" ;
						}
						if(AppvlTime == null || AppvlTime.equals("")){
							return "第" + count + "行，认证时间不能为空" ;
						}
						
					    if(!TimeUtil.judgeFormat1(AppvlTime)){
							return "第" + count + "行，认证时间格式有误（格式如：2013/02）" ;
						}
					    if(AppvlID == null || AppvlID.equals("")){
					    	return "第" + count + "行，批文号不能为空" ;
					    }

						if(FromTime == null || FromTime.equals("")){
							return "第" + count + "行，有效期起不能为空" ;
						}
						
					    if(!TimeUtil.judgeFormat1(FromTime)){
							return "第" + count + "行，有效期起格式有误（格式如：2013/02）" ;
						}
						if(EndTime == null || EndTime.equals("")){
							return "第" + count + "行，有效期止不能为空" ;
						}
						
					    if(!TimeUtil.judgeFormat1(EndTime)){
							return "第" + count + "行，有效期止格式有误（格式如：2013/02）" ;
						}
					    if(AppvlAuth == null || AppvlAuth.equals("")){
					    	return "第" + count + "行，认证机构不能为空" ;
					    }

				    }else if(AppvlResult.equals("未通过")){
						if(FirstAppvlTime == null || FirstAppvlTime.equals("")){
							return "第" + count + "行，首次通过认证时间不能为空" ;
						}
						
					    if(!TimeUtil.judgeFormat1(FirstAppvlTime)){
							return "第" + count + "行，首次通过认证时间格式有误（格式如：2013/02）" ;
						}
						if(AppvlTime == null || AppvlTime.equals("")){
							return "第" + count + "行，认证时间不能为空" ;
						}
						
					    if(!TimeUtil.judgeFormat1(AppvlTime)){
							return "第" + count + "行，认证时间格式有误（格式如：2013/02）" ;
						}
					    if(AppvlID == null || AppvlID.equals("")){
					    	return "第" + count + "行，批文号不能为空" ;
					    }

						if(!(FromTime == null || FromTime.equals(""))){
							return "第" + count + "行，有效期起应该为空" ;
						}

						if(!(EndTime == null || EndTime.equals(""))){
							return "第" + count + "行，有效期止应该为空" ;
						}

					    if(AppvlAuth == null || AppvlAuth.equals("")){
					    	return "第" + count + "行，认证机构不能为空" ;
					    }

				    }else if(AppvlResult.equals("未参加评估")){
//						if(FirstAppvlTime == null || FirstAppvlTime.equals("")){
//							return "第" + count + "行，首次通过认证时间不能为空" ;
//						}
//						
//					    if(!TimeUtil.judgeFormat1(FirstAppvlTime)){
//							return "第" + count + "行，首次通过认证时间格式有误（格式如：2013/02）" ;
//						}
						if(!(AppvlTime == null || AppvlTime.equals(""))){
							return "第" + count + "行，认证时间应该为空" ;
						}

					    if(!(AppvlID == null || AppvlID.equals(""))){
					    	return "第" + count + "行，批文号应该为空" ;
					    }
						if(!(FromTime == null || FromTime.equals(""))){
							return "第" + count + "行，有效期起应该为空" ;
						}

						if(!(EndTime == null || EndTime.equals(""))){
							return "第" + count + "行，有效期止应该为空" ;
						}

					    if(!(AppvlAuth == null || AppvlAuth.equals(""))){
					    	return "第" + count + "行，认证机构应该为空" ;
					    }
				    	
				    }else{
				    	return "第"+count+"行，认证结果填写错误";
				    	
				    }
				    

				    String TotalCSHour1 = cell[32].getContents();
				    
				    if(TotalCSHour1 == null || TotalCSHour1.equals("")){
				    	return "第" + count + "行，总学时数不能为空" ;
				    }else if(!pattern.matcher(TotalCSHour1).matches()){
				    	return "第" + count + "行，总学时数应该填数字" ;
				    }
				    int TotalCSHour=Integer.parseInt(TotalCSHour1);
				    
				    String RequireCShour1 = cell[33].getContents();
				 
				    if(RequireCShour1 == null || RequireCShour1.equals("")){
				    	return "第" + count + "行，没有必修课学时数请填0" ;
				    }else if(!pattern.matcher(RequireCShour1).matches()){
				    	return "第" + count + "行，必修课学时数应该填数字" ;
				    }
				    int RequireCShour=Integer.parseInt(RequireCShour1);
				    
				    String OptionCSHour1 = cell[34].getContents();
			
				    if(OptionCSHour1 == null || OptionCSHour1.equals("")){
				    	return "第" + count + "行，没有选修课学时数请填0" ;
				    }else if(!pattern.matcher(OptionCSHour1).matches()){
				    	return "第" + count + "行，选修课学时数应该填数字" ;
				    }
				    int OptionCSHour=Integer.parseInt(OptionCSHour1);
				    
				    String InClassCSHour1 = cell[35].getContents();
				  
				    if(InClassCSHour1 == null || InClassCSHour1.equals("")){
				    	return "第" + count + "行，没有课内教学学时数请填0" ;
				    }else if(!pattern.matcher(InClassCSHour1).matches()){
				    	return "第" + count + "行，课内教学学时数应该填数字" ;
				    }
				    int InClassCSHour=Integer.parseInt(InClassCSHour1);
				    
				    String ExpCSHour1 = cell[36].getContents();
				   
				    if(ExpCSHour1 == null || ExpCSHour1.equals("")){
				    	return "第" + count + "行，没有实验教学学时数请填0" ;
				    }else if(!pattern.matcher(ExpCSHour1).matches()){
				    	return "第" + count + "行，实验教学学时数应该填数字" ;
				    }
				    int ExpCSHour=Integer.parseInt(ExpCSHour1);
				    
				    String PraCSHour1 = cell[37].getContents();
				   
				    if(PraCSHour1 == null || PraCSHour1.equals("")){
				    	return "第" + count + "行，没有集中性实践教学环节学时数请填0" ;
				    }else if(!pattern.matcher(PraCSHour1).matches()){
				    	return "第" + count + "行，集中性实践教学环节学时数应该填数字" ;
				    }
				    int PraCSHour=Integer.parseInt(PraCSHour1);

				    String TotalCredit1 = cell[38].getContents();
				    if(TotalCredit1 == null || TotalCredit1.equals("")){
				    	return "第" + count + "行，总学分数不能为空" ;
				    }else if(!pattern.matcher(TotalCredit1).matches()){
				    	return "第" + count + "行，总学分数应该填数字" ;
				    }
				    double TotalCredit=Double.parseDouble(TotalCredit1);
				    
				    String RequireCredit1 = cell[39].getContents();
				    if(RequireCredit1 == null || RequireCredit1.equals("")){
				    	return "第" + count + "行，没有必修课学分数请填0" ;
				    }else if(!pattern.matcher(RequireCredit1).matches()){
				    	return "第" + count + "行，必修课学分数应该填数字" ;
				    }
				    double RequireCredit=Double.parseDouble(RequireCredit1);
				    
				    String OptionCredit1 = cell[40].getContents();
				    if(OptionCredit1 == null || OptionCredit1.equals("")){
				    	return "第" + count + "行，没有选修课学分数请填0" ;
				    }else if(!pattern.matcher(OptionCredit1).matches()){
				    	return "第" + count + "行，选修课学分数应该填数字" ;
				    }
				    double OptionCredit=Double.parseDouble(OptionCredit1);
				    
				    String InClassCredit1 = cell[41].getContents();
				    if(InClassCredit1 == null || InClassCredit1.equals("")){
				    	return "第" + count + "行，没有课内教学学分数请填0" ;
				    }else if(!pattern.matcher(InClassCredit1).matches()){
				    	return "第" + count + "行，课内教学学分数应该填数字" ;
				    }
				    double InClassCredit=Double.parseDouble(InClassCredit1);
				    
				    String ExpCredit1 = cell[42].getContents();				   
				    if(ExpCredit1 == null || ExpCredit1.equals("")){
				    	return "第" + count + "行，没有实验教学学分数请填0" ;
				    }else if(!pattern.matcher(ExpCredit1).matches()){
				    	return "第" + count + "行，实验教学学分数应该填数字" ;
				    }
				    double ExpCredit=Double.parseDouble(ExpCredit1);
				    
				    String PraCredit1 = cell[43].getContents();
				    if(PraCredit1 == null || PraCredit1.equals("")){
				    	return "第" + count + "行，没有集中实践教学环节学分数请填0" ;
				    }else if(!pattern.matcher(PraCredit1).matches()){
				    	return "第" + count + "行，集中实践教学环节学分数应该填数字" ;
				    }
				    double PraCredit=Double.parseDouble(PraCredit1);
				    
				    String OutClassCredit1 = cell[44].getContents();
				    if(OutClassCredit1 == null || OutClassCredit1.equals("")){
				    	return "第" + count + "行，没有课外科技活动学分数请填0" ;
				    }else if(!pattern.matcher(OutClassCredit1).matches()){
				    	return "第" + count + "行，课外科技活动学分数应该填数字" ;
				    }
				    double OutClassCredit=Double.parseDouble(OutClassCredit1);
				    
				    if((double)PraCSHour!=PraCredit*16){
				    	return "第"+count+"行，集中性实践教学环节学时数应该是学分数的16倍";
				    }
				    
				    if(TotalCredit != RequireCredit+OptionCredit+PraCredit+OutClassCredit || TotalCredit != InClassCredit+ExpCredit+PraCredit+OutClassCredit){
				    	return "第"+count+"行，学分填写错误";
				    }
					
				
				count++ ;
				
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
				t322_Bean.setStopAdmisTime(TimeUtil.changeDateYM(StopAdmisTime));
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
				t322_Bean.setFirstAppvlTime(TimeUtil.changeDateYM(FirstAppvlTime));
				t322_Bean.setAppvlTime(TimeUtil.changeDateYM(AppvlTime));
				t322_Bean.setAppvlID(AppvlID);
				t322_Bean.setAppvlResult(AppvlResult);
				t322_Bean.setFromTime(TimeUtil.changeDateYM(FromTime));
				t322_Bean.setEndTime(TimeUtil.changeDateYM(EndTime));
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
			return "数据导入成功" ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}

}
}
