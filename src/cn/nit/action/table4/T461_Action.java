package cn.nit.action.table4;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.table4.T461_Bean;
import cn.nit.service.table4.T461_Service;


public class T461_Action {
	
	private String rows; //每页显示的记录数
	
	private String page; //当前第几页
	
	private String param; //页面传递参数
	
	private T461_Service T461_services = new T461_Service();
	
	private T461_Bean T461_bean = new T461_Bean();
	


	HttpServletResponse response = ServletActionContext.getResponse() ;
	
	
	//查询出所有
	public void loadHonorInfo() throws Exception{
		
		HttpServletResponse response = ServletActionContext.getResponse() ;	
		List<T461_Bean> list = T461_services.getPagehonorList(this.getRows(),this.getPage(), this.getParam()) ;
		String TeaInfoJson = this.toBeJson(list,T461_services.getTotal(this.getParam()));
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
	private String toBeJson(List<T461_Bean> list, int total) throws Exception{
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
	
	//插入一个新的教职工
	public void insert(){
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		HttpServletResponse response = ServletActionContext.getResponse();
				
		boolean flag = T461_services.insert(T461_bean);
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
	
	public T461_Bean getT461_bean() {
		return T461_bean;
	}

	public void setT461_bean(T461_Bean T461Bean) {
		T461_bean = T461Bean;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getParam() {
		return param;
	}
}