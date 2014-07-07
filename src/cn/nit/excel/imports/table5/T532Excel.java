package cn.nit.excel.imports.table5;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.Cell;
import cn.nit.bean.di.DiAwardLevelBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.di.DiTitleNameBean;
import cn.nit.bean.table4.T411_Bean;
import cn.nit.bean.table5.T532Bean;
import cn.nit.service.di.DiAwardLevelService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.di.DiTitleNameService;
import cn.nit.service.table4.T411_Service;
import cn.nit.service.table5.T532Service;
import cn.nit.util.TimeUtil;

public class T532Excel {
	
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
		List<T532Bean> list = new LinkedList<T532Bean>() ;
//		UserRoleBean userinfo = (UserRoleBean)request.getSession().getAttribute("userinfo") ;
		DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
        DiAwardLevelService diAwardSer=new DiAwardLevelService();
        List<DiAwardLevelBean> diAwardList=diAwardSer.getList();
        DiTitleNameService diTitleSer=new DiTitleNameService();
        List<DiTitleNameBean> diTitleList=diTitleSer.getList();
        T411_Service t411Ser=new T411_Service();
        List<T411_Bean> t411List=t411Ser.getList();
        
        
		for(Cell[] cell : cellList){
			T532Bean t532Bean = new  T532Bean();
			int n=cellList.indexOf(cell);
			if(count<4){
				count++;
				continue;
			}
			else{
				
				
			  try{
				 
				 String CenterName = cell[1].getContents() ;
				 
				 if(CenterName == null || CenterName.equals("")){
						return "第" + count + "行，中心名称不能为空" ;
					}
				 if(CenterName.length()>100){
					 return "第" + count + "行，中心名称不能超过50个字" ;
				 }
				 
					String FromSubject = cell[2].getContents() ;
					
					if(FromSubject == null || FromSubject.equals("")){
						return "第" + count + "行，所属学科不能为空" ;
					}
					
					String CenterLevel = cell[3].getContents() ;
					
					if(CenterLevel == null || CenterLevel.equals("")){
						return "第" + count + "行，级别不能为空" ;
					}
					for(DiAwardLevelBean diAwardBean :diAwardList){
						if(diAwardBean.getAwardLevel().equals(CenterLevel)){
							CenterLevel = diAwardBean.getIndexId();
							flag=true;
							break;
						}
					}
					if(!flag){
						return "第" + count + "行，没有相对应的级别" ;
					}
					
					String FromTeaUnit = cell[4].getContents() ;
					String UnitID = cell[5].getContents() ;
					
					if(FromTeaUnit == null || FromTeaUnit.equals("")){
						return "第" + count + "行，所属教学单位不能为空" ;
					}
					
					if(UnitID == null || UnitID.equals("")){
						return "第" + count + "行，单位编号不能为空" ;
					}
					
					if(UnitID.length() > 50){
						return "第" + count + "行，单位编号字数不超过50个数字或字母" ;
					}
					
					for(DiDepartmentBean diDepartBean : diDepartBeanList){
						if(diDepartBean.getUnitId().equals(UnitID)){
							if(diDepartBean.getUnitName().equals(FromTeaUnit)){
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
					
					String CenterLeader = cell[6].getContents() ;
					String TeaID = cell[7].getContents() ;
					
					if(CenterLeader == null || CenterLeader.equals("")){
						return "第" + count + "行，中心主任不能为空" ;
					}
					
					if(TeaID == null || TeaID.equals("")){
						return "第" + count + "行，教工号不能为空" ;
					}
					
					if(TeaID.length() > 50){
						return "第" + count + "行，教工号字数不超过50个数字或字母" ;
					}
					
					for(T411_Bean t411Bean : t411List){
						if(t411Bean.getTeaId().equals(TeaID)){
							if(t411Bean.getTeaName().equals(CenterLeader)){
								flag = true ;
								break ;
							}else{
								return "第" + count + "行，中心主任与教工号不对应" ;
							}
						}//if
					}//for
					
					if(!flag){
						return "第" + count + "行，没有与之相匹配的教工号" ;
					}else{
						flag = false ;
					}
		
					String TeaTitle = cell[8].getContents() ;
					
					if(TeaTitle == null || TeaTitle.equals("")){
						return "第" + count + "行，职称不能为空" ;
					}
					for(DiTitleNameBean diTitleBean:diTitleList){
						if(diTitleBean.getTitleName().equals(TeaTitle)){
							TeaTitle=diTitleBean.getIndexId();
							flag=true;
							break;
						}
					}
					if(!flag){
						return "第" + count + "行，没有相对应的职称" ;
					}
					
					String BuildTime = cell[9].getContents();
					
					if(BuildTime == null || BuildTime.equals("")){
						return "第" + count + "行，设立时间不能为空" ;
					}
					if(!TimeUtil.judgeFormat1(BuildTime)){
						return "第" + count + "行，时间格式不对，格式应为：2005/02" ;
					}
					
					String BuildAppvlID=cell[10].getContents();
					
					if(BuildAppvlID == null || BuildAppvlID.equals("")){
						return "第" + count + "行，建设批文号不能为空" ;
					}
					
					String ReceptTime = cell[11].getContents();
					
					if(ReceptTime == null || ReceptTime.equals("")){
						return "第" + count + "行，验收时间不能为空" ;
					}
					if(!TimeUtil.judgeFormat1(ReceptTime)){
						return "第" + count + "行，时间格式不对，格式应为：2005/02" ;
					}
					
					String ReceptAppvlID=cell[12].getContents();
					
					if(ReceptAppvlID == null || ReceptAppvlID.equals("")){
						return "第" + count + "行，验收批文号不能为空" ;
					}
					
					String ValidTime=cell[12].getContents();
					
					if(ValidTime == null || ValidTime.equals("")){
						return "第" + count + "行，有效期（年）不能为空" ;
					}
					if(!this.isNumeric(ValidTime)){
						return "第" + count + "行，有效期（年）只能填数字" ;
					}
					
					String Fund=cell[13].getContents();
					
					if(Fund == null || Fund.equals("")){
						return "第" + count + "行，经费(万元)不能为空" ;
					}
					if(!this.isNumeric(Fund)){
						return "第" + count + "行，经费(万元)只能填数字" ;
					}
					
					String Note=cell[14].getContents();
					
					if(Note != null || Fund.length()>1000){
						return "第" + count + "行，备注字数不能超过500字" ;
					}	
				
				count++ ;
				t532Bean.setBuildAppvlID(BuildAppvlID);
				t532Bean.setBuildTime(TimeUtil.changeDateYM(BuildTime));
				t532Bean.setCenterLeader(CenterLeader);
				t532Bean.setCenterLevel(CenterLevel);
				t532Bean.setCenterName(CenterName);
				t532Bean.setFromSubject(FromSubject);
				t532Bean.setFromTeaUnit(FromTeaUnit);
				t532Bean.setFund(Double.parseDouble(Fund));
				t532Bean.setNote(Note);
				t532Bean.setReceptAppvlID(ReceptAppvlID);
				t532Bean.setReceptTime(TimeUtil.changeDateYM(ReceptTime));
				t532Bean.setTeaID(TeaID);
				t532Bean.setTeaTitle(TeaTitle);
				t532Bean.setTime(Time);
				t532Bean.setUnitID(UnitID);
				t532Bean.setValidTime(Integer.parseInt(ValidTime));
				list.add(t532Bean);
				
							
			}
			catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
	     }
		}
		
		flag = false ;
		T532Service t532Ser = new T532Service() ;
		flag = t532Ser.batchInsert(list) ;
		
		if(flag){
			return "数据导入成功" ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
		
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
	
	
	/**将带%的字符串转换成double类型*/
	public double toDouble(String str){
		String dou=str.substring(0,str.length()-1);
	    double num1=Double.parseDouble(dou);
	    double num2=num1/100;
	    double num =(double)Math.round(num1)/100;
		return num;
	}
	

}
