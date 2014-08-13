package cn.nit.excel.imports.table5;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import jxl.Cell;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.di.DiMajorTwoBean;
import cn.nit.bean.table5.T54_Bean;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.di.DiMajorTwoService;
import cn.nit.service.table5.T54_Service;
import cn.nit.util.TimeUtil;

public class T54_Excel {
	
	/**
	 * 批量导入
	 * @param cellList {@link java.util.List<{@link jxl.Cell}>}  Cell是数组
	 * @param request  {@link javax.servlet.http.HttpServletRequest}
	 * @return
	 * @throws Exception 
	 */
	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request,String selectYear) throws Exception{
		
		PrintWriter out = null ;
		if((cellList == null) || (cellList.size() < 2)){
			return "数据不标准，请重新提交" ;
		}
		
		int count = 1 ;
		T54_Bean t54Bean = new  T54_Bean();
		boolean flag = false ;
		List<T54_Bean> list = new LinkedList<T54_Bean>() ;
//		UserRoleBean userinfo = (UserRoleBean)request.getSession().getAttribute("userinfo") ;
		DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		DiMajorTwoService diMajorTwoSer=new DiMajorTwoService();
		List<DiMajorTwoBean> diMajorTwoBeanList=diMajorTwoSer.getList();
		
		for(Cell[] cell : cellList){
			
			int n=cellList.indexOf(cell);
			if(count<4){//忽略合计的哪一行
				count++;
				continue;
			}
			else{
				
				
			  try{
				  switch(n){
				  case 3:
					  String LectureSumNum = cell[2].getContents();
							if(LectureSumNum == null||LectureSumNum.equals("")){
							LectureSumNum = "0";
						}
						if(!this.isNumber(LectureSumNum)){
							 return "第" + count + "行，文化、学术讲座数总数格式不正确，请填写数字" ;
						}
						t54Bean.setLectureSumNum(Integer.parseInt(LectureSumNum));
						break;
				  case 4:
					    String SchLecture = cell[2].getContents();
						 if(SchLecture == null || SchLecture.equals("")){
							 SchLecture = "0"; 
						 }
						 if(!this.isNumber(SchLecture)){
							 return "第" + count + "行，其中：（文化）校级个数格式不正确，请填写数字" ;
						 }
							t54Bean.setSchLecture(Integer.parseInt(SchLecture));
							break;
				  case 5:
					  String CollegeLecture = cell[2].getContents();
						 if(CollegeLecture == null || CollegeLecture.equals(CollegeLecture)){
							 CollegeLecture = "0"; 
						 }
						 if(!this.isNumber(CollegeLecture)){
							 return "第" + count + "行，其中：（文化）院（系）级个数格式不正确，请填写数字" ;
						 }
						 t54Bean.setCollegeLecture(Integer.parseInt(CollegeLecture));
					   break;
				  case 6:
					    String ActItemSumNum = cell[2].getContents();
						 if(ActItemSumNum == null || ActItemSumNum.equals("")){
							 ActItemSumNum = "0";
						 }
						 if(!this.isNumber(ActItemSumNum)){
							 return "第" + count + "行，本科生课外科技、文化活动项目总数格式不正确，请填写数字" ;
						 }
						 t54Bean.setActItemSumNum(Integer.parseInt(ActItemSumNum));
						 break;
				  case 7:
					  	 String NationActItem = cell[2].getContents();
						 if(NationActItem == null || NationActItem.equals("")){
							 NationActItem = "0";
						 }
						 if(!this.isNumber(NationActItem)){
							 return "第" + count + "行，大学生创新实验项目个数格式不正确，请填写数字" ;
						 }
						 t54Bean.setNationActItem(Integer.parseInt(NationActItem));
						 break;
				  case 8:
					  
					     String ProviActItem = cell[2].getContents();
						 if(ProviActItem == null || ProviActItem.equals("")){
							 ProviActItem = "0";
						 }
						 if(!this.isNumber(ProviActItem)){
							 return "第" + count + "行，省部级项目个数格式不正确，请填写数字" ;
						 }
						 t54Bean.setProviActItem(Integer.parseInt(ProviActItem));
						 break;	
				  case 9:
					  	String SchActItem = cell[2].getContents();
						 if(SchActItem == null || SchActItem.equals("")){
							 SchActItem = "0";
						 }
						 if(!this.isNumber(SchActItem)){
							 return "第" + count + "行，学校项目个数格式不正确，请填写数字" ;
						 }
						 t54Bean.setSchActItem(Integer.parseInt(SchActItem));
						 break;	
					  
				  }			
			}
			catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
	     }
		}
		System.out.println("selectYear:"+selectYear);
		t54Bean.setTime(TimeUtil.changeDateY(selectYear));
		list.add(t54Bean);
		flag = false ;
		T54_Service t54Ser = new T54_Service() ;
		flag = t54Ser.batchInsert(list,selectYear) ;
//		PrintWriter out = null ;
		
//		getResponse().setContentType("text/html; charset=UTF-8") ; 
//		out = getResponse().getWriter() ;
		if(flag){
//			out.print("{\"state\":true,data:\"录入成功!!!\"}") ;
			return "数据导入成功" ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
	}
	
	/**判断是否是数字*/
	public boolean isNumber(String str){
		boolean flag = false;
		try{
			Integer.parseInt(str);
			flag = true;
		}catch(NumberFormatException ex){
			flag = false;
		}
		return flag;
	}

	public HttpServletResponse getResponse(){
		return ServletActionContext.getResponse() ;
	}
}
