package cn.nit.excel.imports.table5;

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

import cn.nit.bean.di.DiDegreeBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.di.DiEducationBean;
import cn.nit.bean.di.DiMajorTwoBean;
import cn.nit.bean.di.DiTitleNameBean;
import cn.nit.bean.table4.T411_Bean;
import cn.nit.bean.table5.T534Bean;
import cn.nit.service.di.DiDegreeService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.di.DiEducationService;
import cn.nit.service.di.DiMajorTwoService;
import cn.nit.service.di.DiTitleNameService;
import cn.nit.service.table4.T411_Service;
import cn.nit.service.table5.T534Service;
import cn.nit.util.TimeUtil;

public class T534Excel {
	
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
		 
		Date Time=new Date();
		boolean flag = false ;
		boolean IsExcellent=false;
		boolean IsOutEmploy=false;
		List<T534Bean> list = new LinkedList<T534Bean>() ;
//		UserRoleBean userinfo = (UserRoleBean)request.getSession().getAttribute("userinfo") ;
		DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		DiMajorTwoService diMajorTwoSer=new DiMajorTwoService();
		List<DiMajorTwoBean> diMajorTwoBeanList=diMajorTwoSer.getList();
		T411_Service t411Ser=new T411_Service();
		List<T411_Bean> t411BeanList=t411Ser.getList();
		DiEducationService diEducationSer=new DiEducationService();
		List<DiEducationBean> diEducationList=diEducationSer.getList();
		DiDegreeService diDegreeSer=new DiDegreeService();
		List<DiDegreeBean> diDegreeList=diDegreeSer.getList();
		DiTitleNameService diTitleSer=new DiTitleNameService();
		List<DiTitleNameBean> diTitelList=diTitleSer.getList();
		
		for(Cell[] cell : cellList){
			T534Bean t534Bean = new  T534Bean();
			int n=cellList.indexOf(cell);
			if(count<4){//忽略合计的哪一行
				count++;
				continue;
			}
			else{
				
				
			  try{
				 
				  	String TeaUnit = cell[1].getContents() ;
					String UnitID = cell[2].getContents() ;
					
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
					
					String MajorName = cell[3].getContents() ;
					String MajorID = cell[4].getContents() ;
					
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
					
					String TeaName = cell[5].getContents() ;
					String TeaID = cell[6].getContents() ;
					
					if(TeaName == null || TeaName.equals("")){
						return "第" + count + "行，教师姓名不能为空" ;
					}
					
					if(TeaID == null || TeaID.equals("")){
						return "第" + count + "行，教工号不能为空" ;
					}
					
					if(TeaID.length() > 50){
						return "第" + count + "行，教工号字数不超过50个数字或字母" ;
					}
					
					for(T411_Bean t411Bean : t411BeanList){
						if(t411Bean.getTeaId().equals(TeaID)){
							if(t411Bean.getTeaName().equals(TeaName)){
								flag = true ;
								break ;
							}else{
								return "第" + count + "行，教师名称与教工号不对应" ;
							}
						}//if
					}//for
					
					if(!flag){
						return "第" + count + "行，没有与之相匹配的教工号" ;
					}else{
						flag = false ;
					}
					
					String isOutEmploy = cell[7].getContents() ;
					
					if(isOutEmploy == null || isOutEmploy.equals("")){
						return "第" + count + "行，是否外聘不能为空" ;
					}
					if(isOutEmploy=="是"){
						
						IsOutEmploy=true;
					}
					if(isOutEmploy=="否"){
						
						 IsOutEmploy=false;
					}
					if(!isOutEmploy.equals("是")&&!isOutEmploy.equals("否")){
						return "第" + count + "行，是否外聘填写格式有误，请填写”是“或者”否“ ";
					}
					
					
					String Education=cell[8].getContents();
//					System.out.println("BuildCon:"+BuildCon);
					
					if(Education == null || Education.equals("")){
						return "第" + count + "行，学历不能为空" ;
					}
					
					for(DiEducationBean diEducateBean : diEducationList){
						if(diEducateBean.getEducation().equals(Education)){
							Education=diEducateBean.getIndexId();
							flag = true;
							break;
						}//if
					}//for
					if(!flag){
						return "第" + count + "行，学历不存在" ;
					}else{
						flag = false ;
					}
					
					String Degree=cell[9].getContents();
//					System.out.println("BuildCon:"+BuildCon);
					
					if(Degree == null || Degree.equals("")){
						return "第" + count + "行，学位不能为空" ;
					}
					
					for(DiDegreeBean diDegreeBean : diDegreeList){
						if(diDegreeBean.getDegree().equals(Degree)){
							Degree=diDegreeBean.getIndexId();
							flag = true;
							break;
						}//if
					}//for
					if(!flag){
						return "第" + count + "行，学位不存在" ;
					}else{
						flag = false ;
					}
					
					String Title=cell[10].getContents();
//					System.out.println("BuildCon:"+BuildCon);
					
					if(Title == null || Title.equals("")){
						return "第" + count + "行，职称不能为空" ;
					}
					
					for(DiTitleNameBean diTitleBean : diTitelList){
						if(diTitleBean.getTitleName().equals(Title)){
							Title=diTitleBean.getIndexId();
							flag = true;
							break;
						}//if
					}//for
					if(!flag){
						return "第" + count + "行，职称不存在" ;
					}else{
						flag = false ;
					}
					
					String isExcellent = cell[11].getContents() ;
					
					if(isExcellent == null || isExcellent.equals("")){
						return "第" + count + "行，是否获评校级优秀指导教师不能为空" ;
					}
					if(isExcellent=="是"){
						
						 IsExcellent=true;
					}
					if(isExcellent=="否"){
						
						 IsExcellent=false;
					}
					if(!isExcellent.equals("是")&&!isExcellent.equals("否")){
						return "第" + count + "行，是否获评校级优秀指导教师不格式不正确,只能填”是“或”否“" ;
					}
					
					String TrainIssueNum=cell[12].getContents();
					
					if(TrainIssueNum == null || TrainIssueNum.equals("")){
						TrainIssueNum = "0";
					}
					if(!this.isNumeric(TrainIssueNum)){
						return "第" + count + "行，格式不正确,课题数量只能为数字" ;
					}
					
					String SociaPraFinishNum=cell[13].getContents();
					
					if(SociaPraFinishNum == null || SociaPraFinishNum.equals("")){
						TrainIssueNum = "0";
					}
					if(!this.isNumeric(SociaPraFinishNum)){
						return "第" + count + "行，格式不正确,实践完成数只能为数字" ;
					}
					
					String GuideStuNum=cell[14].getContents();
					
					if(GuideStuNum == null || GuideStuNum.equals("")){
						TrainIssueNum = "0";
					}
					if(!this.isNumeric(GuideStuNum)){
						return "第" + count + "行，格式不正确,指导学生人数只能为数字" ;
					}
					
					String GainBestGraDesinNum=cell[15].getContents();
					
					if(GainBestGraDesinNum == null || GainBestGraDesinNum.equals("")){
						TrainIssueNum = "0";
					}
					if(!this.isNumeric(GainBestGraDesinNum)){
						return "第" + count + "行，格式不正确,获优秀毕业人数只能为数字" ;
					}
					
					String GainTime=cell[16].getContents();
					if(GainTime == null || GainTime.equals("")){
						return "第" + count + "行，获评时间不能为空" ;
					}
					if(!TimeUtil.judgeFormat1(GainTime)){
						return "第" + count + "行，获评时间格式不正确，应为：2012/09" ;
					}
					String Note=cell[17].getContents();
					if(Note!=null&&Note.length()>1000){
						return "第" + count + "行，备注长度不能超过500字" ;
					}
					
						
					
				
				count++ ;
				
				t534Bean.setDegree(Degree);
				t534Bean.setEducation(Education);
				t534Bean.setGainBestNum(Integer.parseInt(GainBestGraDesinNum));
				t534Bean.setGainTime(TimeUtil.changeDateYM(GainTime));
				t534Bean.setIsExcellent(IsExcellent);
				t534Bean.setIsOutEmploy(IsOutEmploy);
				t534Bean.setMajorID(MajorID);
				t534Bean.setMajorName(MajorName);
				t534Bean.setNote(Note);
				t534Bean.setSocialNum(Integer.parseInt(SociaPraFinishNum));
				t534Bean.setTeaID(TeaID);
				t534Bean.setTeaName(TeaName);
				t534Bean.setTeaUnit(TeaUnit);
				t534Bean.setTitle(Title);
				t534Bean.setTrainIssueNum(Integer.parseInt(TrainIssueNum));
				t534Bean.setUnitID(UnitID);
				t534Bean.setTime(Time);
				t534Bean.setGuideStuNum(Integer.parseInt(GuideStuNum));
				list.add(t534Bean);
				
							
			}
			catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
	     }
		}
		
		flag = false ;
		T534Service t534Ser = new T534Service() ;
		flag = t534Ser.batchInsert(list) ;
		
		if(flag){
			return null ;
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
