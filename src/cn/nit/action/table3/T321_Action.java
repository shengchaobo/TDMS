package cn.nit.action.table3;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;


import cn.nit.service.di.DiDepartmentService;
import cn.nit.bean.di.DiDepartmentBean;


import cn.nit.bean.table3.T321_Bean;

import cn.nit.service.table3.T321_Service;



public class T321_Action {
	
//	private T321_DAO mainTrainBasicInfoDao=new T321_DAO();
	
	
	private T321_Service mainTrainBasicInfoSer = new T321_Service() ;


	
	int num=0;
	
	private T321_Bean mainTrainBasicInfoBean = new T321_Bean() ;
	
	private DiDepartmentService diDepartmentSer = new DiDepartmentService() ;
	
	public List<Integer> getNumofMainTrain(){
		List<Integer> list1=new ArrayList<Integer> ();
		List<DiDepartmentBean> list = diDepartmentSer.getList() ;
		for(int i=0;i<=list.size();i++){
			//num=mainTrainBasicInfoDao.getNumofMainTrain(list.get(i).getUnitID());
			list1.add(num);
			
		}
		return list1;
		
	}
	
	public void insert(){
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		mainTrainBasicInfoBean.setTime(new Date()) ;
		//这还没确定,设置填报者的职工号与部门号
		//UserInfo userinfo = (UserInfo)getSession().getAttribute("userinfo") ;
		//discipBean.setFillTeaID(userinfo.getTeaID()) ;
		boolean flag = mainTrainBasicInfoSer.insert(mainTrainBasicInfoBean) ;
		PrintWriter out = null ;
		
		try{
			getResponse().setContentType("text/html; charset=UTF-8") ;
//			getResponse().setHeader("Content-type", "text/html");  
			out = getResponse().getWriter() ;
			if(flag){
				out.print("{\"state\":true,data:\"录入成功!!!\"}") ;
			}else{
				out.print("{\"state\":false,data:\"录入失败!!!\"}") ;
			}
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"录入失败!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
		out.flush() ;
	}

	public HttpServletRequest getRequest(){
		return ServletActionContext.getRequest() ;
	}
	
	public HttpSession getSession(){
		return getRequest().getSession() ;
	}
	
	public HttpServletResponse getResponse(){
		return ServletActionContext.getResponse() ;
	}
	
	public T321_Bean getMainTrainBasicInfoBean() {
		return mainTrainBasicInfoBean;
	}

	public void setMainTrainBasicInfoBean(T321_Bean mainTrainBasicInfoBean) {
		this.mainTrainBasicInfoBean = mainTrainBasicInfoBean;
	}


}
