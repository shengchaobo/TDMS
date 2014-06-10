package cn.nit.action.table4;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.table4.T49_Bean;
import cn.nit.service.table4.T49_Service;


public class T49_Action {
	
	private String rows; //每页显示的记录数
	
	private String page; //当前第几页
	
	private T49_Service T49_services = new T49_Service();
	
	private T49_Bean T49_bean = new T49_Bean();
	


	HttpServletResponse response = ServletActionContext.getResponse() ;
	
	
	//查询出所有
	public void loadTextInfo() throws Exception{
		
		HttpServletResponse response = ServletActionContext.getResponse() ;	
		List<T49_Bean> list = T49_services.getPagetextList(this.getRows(),this.getPage()) ;
		String TeaInfoJson = this.toBeJson(list,T49_services.getTotal());
		//private JSONObject jsonObj;
		
		PrintWriter out = null ;

		if(TeaInfoJson == null){			
			return ;
		}else{
			try {
				
				System.out.println(TeaInfoJson) ;
				response.setContentType("application/json;charset=UTF-8") ;
				out = response.getWriter() ;
				out.print(TeaInfoJson) ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(out != null){
					out.flush() ;
					out.close() ;
				}
			}
		}
	}

    //将分页系统的总数以及当前页的list转化一个json传页面显示
	private String toBeJson(List<T49_Bean> list, int total) throws Exception{
		// TODO Auto-generated method stub
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		 

		JSONObject testjson = new JSONObject();
		testjson.accumulate("total", total);
		testjson.accumulate("rows", list);
		
        String json = testjson.toString();
        System.out.println(json) ;
		return json;
	}
	
	//插入一个新的
	public void insert(){
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		HttpServletResponse response = ServletActionContext.getResponse();
		
		T49_bean.setSumPlanBook(T49_bean.getInterPlanBook()+T49_bean.getNationPlanBook()+T49_bean.getProviPlanBook()+T49_bean.getCityPlanBook()+T49_bean.getSchPlanBook());
		T49_bean.setSumAwardBook(T49_bean.getInterAwardBook()+T49_bean.getNationAwardBook()+T49_bean.getProviAwardBook()+T49_bean.getCityAwardBook()+T49_bean.getSchAwardBook());	
		boolean flag = T49_services.insert(T49_bean);
		PrintWriter out = null ;
		
		try{
			response.setContentType("text/html; charset=UTF-8") ;
			out = response.getWriter() ;
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

	public String getRows() {
		return rows;
	}


	public void setRows(String rows) {
		this.rows = rows;
	}


	public String getPage() {
		return page;
	}


	public void setPage(String page) {
		this.page = page;
	}
	
	public T49_Bean getT49_bean() {
		return T49_bean;
	}

	public void setT49_bean(T49_Bean T49Bean) {
		T49_bean = T49Bean;
	}
}