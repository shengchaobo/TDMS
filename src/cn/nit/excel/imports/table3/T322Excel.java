package cn.nit.excel.imports.table3;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
					
					if(MajorID.length()>50){
						return "第" + count + "行，专业代码长度不能超过50";
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
					
					
					String MajorVersion=cell[3].getContents();
					if(MajorVersion.equals("2012")||MajorVersion.equals("1998")||MajorVersion.equals("99")){
						flag=true;
					}else{
						return  "第" + count + "行，代码版本不对" ;
					}
					
					String MajorField=cell[4].getContents();
					String MajorFieldID=cell[5].getContents();
					
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
				    if(MajorAppvlID.length()>100){
				    	return "第" + count + "行，批文号长度不能超过100" ;
				    }
				    
					
					int MajorDurition=Integer.parseInt(cell[8].getContents());

					if(MajorDurition!=4&&MajorDurition!=5){
						return "第" + count + "行，学制必须是4或5" ;
					}
					
					String MajorDegreeType=cell[9].getContents();
					if(MajorDegreeType.equals("01哲学")||MajorDegreeType.equals("02经济学")||MajorDegreeType.equals("03法学")||MajorDegreeType.equals("04教育学")||MajorDegreeType.equals("05文学")||MajorDegreeType.equals("06历史学")||MajorDegreeType.equals("07理学")||MajorDegreeType.equals("08工学")||MajorDegreeType.equals("09农学")||MajorDegreeType.equals("10医学")||MajorDegreeType.equals("11军事学")||MajorDegreeType.equals("12管理学")||MajorDegreeType.equals("13艺术学")){
						flag=true;
					}else{
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
					if(MajorState.equals("在招")||MajorState.equals("当年停招")){
						flag=true;
					}else{
						return  "第" + count + "行，招生状态必须为 在招 或 当年停招 ";
					}
					
					String StopAdmisTime=cell[12].getContents();
					
					
					if(MajorState.equals("在招")){
						if(!StopAdmisTime.isEmpty()){
							return  "第" + count + "行，在招生状态为在招的情况下，停止招生时间应该不填 ";
						}
					}else{
						if(StopAdmisTime.isEmpty()){
							return  "第" + count + "行，在招生状态为当年停招的情况下，停止招生时间不能为空 ";
						}else{
						    if(!TimeUtil.judgeFormat1(StopAdmisTime)){
								return "第" + count + "行，停止招生时间格式有误（格式如：2013/02）" ;
							}
						}
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
				    if(BuildAppvlID.length()>100){
				    	return "第" + count + "行，建设批文号长度不能超过100" ;
				    }
				    
				    String MajorLevel1=cell[16].getContents();
				    String Majorlevel = null;
					for(DiAwardLevelBean diAwardLevelBean : diAwardLevelList){
						if(diAwardLevelBean.getAwardLevel().equals(MajorLevel1)){
							Majorlevel=diAwardLevelBean.getIndexId();
								flag = true ;
								break ;
							}
							
						}
					if(Majorlevel.equals(null)){
						return "第" + count + "行，所填级别有误" ;
		
					}
					
					
					
					String Type=cell[17].getContents();
					if(Type.equals("特色专业")||Type.equals("品牌专业")||Type.equals("名牌专业")||Type.equals("示范专业")||Type.equals("重点建设专业")||Type.equals("地方优势专业")){
						flag=true;
					}else{
						return "第" + count + "行，类型输入有误" ;
					}
					
				    String Field = cell[18].getContents();
				    
				    if(Field == null || Field.equals("")){
				    	return "第" + count + "行，领域、方向不能为空" ;
				    }
				    if(Field.length()>100){
				    	return "第" + count + "行，领域、方向长度不能超过100" ;
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
								return "第" + count + "行，教工号与建设负责人不对应" ;
							}
						}//if
					}//for
					
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
				    if(CheckAppvlID.length()>100){
				    	return "第" + count + "行，验收批文号长度不能超过100" ;
				    }
				    
				    double SchExp=Double.parseDouble(cell[23].getContents());
				    double EduMinistryExp=Double.parseDouble(cell[24].getContents());
				    
					String FirstAppvlTime = cell[25].getContents() ;
					
					if(FirstAppvlTime == null || FirstAppvlTime.equals("")){
						return "第" + count + "行，首次通过认证时间不能为空" ;
					}
					
				    if(!TimeUtil.judgeFormat1(FirstAppvlTime)){
						return "第" + count + "行，首次通过认证时间格式有误（格式如：2013/02）" ;
					}
				    
					String AppvlTime = cell[26].getContents() ;
					
					if(AppvlTime == null || AppvlTime.equals("")){
						return "第" + count + "行，认证时间不能为空" ;
					}
					
				    if(!TimeUtil.judgeFormat1(AppvlTime)){
						return "第" + count + "行，认证时间格式有误（格式如：2013/02）" ;
					}
				    
				    String AppvlID = cell[27].getContents();
				    
				    if(AppvlID == null || AppvlID.equals("")){
				    	return "第" + count + "行，批文号不能为空" ;
				    }
				    if(AppvlID.length()>100){
				    	return "第" + count + "行，批文号长度不能超过100" ;
				    }
				    
					String AppvlResult=cell[28].getContents();
					if(AppvlResult.equals("通过")||AppvlResult.equals("未通过")||AppvlResult.equals("未参加评估")){
						flag=true;
					}else{
						return "第" + count + "行，认证结果输入有误" ;
					}
				    
					String FromTime = cell[29].getContents() ;
					
					if(FromTime == null || FromTime.equals("")){
						return "第" + count + "行，有效期起不能为空" ;
					}
					
				    if(!TimeUtil.judgeFormat1(FromTime)){
						return "第" + count + "行，有效期起格式有误（格式如：2013/02）" ;
					}
				    
					String EndTime = cell[30].getContents() ;
					
					if(EndTime == null || EndTime.equals("")){
						return "第" + count + "行，有效期止不能为空" ;
					}
					
				    if(!TimeUtil.judgeFormat1(EndTime)){
						return "第" + count + "行，有效期止格式有误（格式如：2013/02）" ;
					}
				    
				    String AppvlAuth = cell[31].getContents();
				    
				    if(AppvlAuth == null || AppvlAuth.equals("")){
				    	return "第" + count + "行，认证机构不能为空" ;
				    }
				    if(AppvlAuth.length()>100){
				    	return "第" + count + "行，认证机构长度不能超过100" ;
				    }
				    
				    int TotalCSHour=Integer.parseInt(cell[32].getContents());
				    int RequireCShour=Integer.parseInt(cell[33].getContents());
				    int OptionCSHour=Integer.parseInt(cell[34].getContents());
				    int InClassCSHour=Integer.parseInt(cell[35].getContents());
				    int ExpCSHour=Integer.parseInt(cell[36].getContents());
				    int PraCSHour=Integer.parseInt(cell[37].getContents());
				    
				    double TotalCredit=Double.parseDouble(cell[38].getContents());
				    double RequireCredit=Double.parseDouble(cell[39].getContents());
				    double OptionCredit=Double.parseDouble(cell[40].getContents());
				    double InClassCredit=Double.parseDouble(cell[41].getContents());
				    double ExpCredit=Double.parseDouble(cell[42].getContents());
				    double PraCredit=Double.parseDouble(cell[43].getContents());
				    double OutClassCredit=Double.parseDouble(cell[44].getContents());
				    

					
				
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
				t322_Bean.setMajorLevel(Majorlevel);
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
