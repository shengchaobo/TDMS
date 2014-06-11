package cn.nit.action.table4;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.table4.T410_Bean;
import cn.nit.dao.table4.T410_Dao;
import cn.nit.service.table4.T410_Service;

public class T410_Action {
	
	private String rows; //每页显示的记录数
	
	private String page; //当前第几页
	
	private T410_Service T410_services = new T410_Service();
	
	private T410_Bean T410_bean = new T410_Bean();
	


	HttpServletResponse response = ServletActionContext.getResponse() ;
	
	
	//查询出所有教师信息
	public void loadResInfo() throws Exception{
		
		HttpServletResponse response = ServletActionContext.getResponse() ;	
		List<T410_Bean> list = T410_services.getPageResList(this.getRows(),this.getPage()) ;
		String TeaInfoJson = this.toBeJson(list,T410_services.getTotal());
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
	private String toBeJson(List<T410_Bean> list, int total) throws Exception{
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

	//查出所有
	public void loadT410() throws Exception{
		
		List<T410_Bean> list = T410_services.getList();
		//将数据转换为json格式
		JSON json = JSONSerializer.toJSON(list) ;
		PrintWriter out = null ;
		
		try {
			//设置输出内容的格式为json
			response.setContentType("application/json; charset=UTF-8") ;
			out = response.getWriter() ;
			//设置数据的内容的编码格式
			String outPrint = URLDecoder.decode(json.toString(), "UTF-8") ;
			out.print(outPrint) ;
			out.flush() ;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
	//插入一个新的教职工
	public void insert(){
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		HttpServletResponse response = ServletActionContext.getResponse();
		T410_bean.setResItemNum(T410_bean.getHresItemNum()+T410_bean.getZresItemNum());
		System.out.println(T410_bean.getHhumanItemFund());
		System.out.println(T410_bean.getZitemFund());
		System.out.println(T410_bean.getHitemFund());
		System.out.println(T410_bean.getSci());
		//T410_bean.setResItemFund(0.0);
		//T410_bean.setIstp(1);
		T410_bean.setResAwardNum(T410_bean.getNationResAward()+T410_bean.getProviResAward()+T410_bean.getCityResAward()+T410_bean.getSchResAward());
		T410_bean.setResItemFund(T410_bean.getHitemFund()+T410_bean.getZitemFund());
		T410_bean.setPaperNum(T410_bean.getSci()+T410_bean.getSsci()+T410_bean.getEi()+T410_bean.getCscd()+T410_bean.getIstp()+T410_bean.getOtherPaper()+T410_bean.getCssci()+T410_bean.getInlandCoreJnal());
		T410_bean.setPublicationNum(T410_bean.getTranslation()+T410_bean.getTreatises());
		T410_bean.setPatentNum(T410_bean.getDesignPatent()+T410_bean.getUtilityPatent()+T410_bean.getInventPatent());
		
		System.out.println("test");
		boolean flag = T410_services.insert(T410_bean);
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
	
	public T410_Bean getT410_bean() {
		return T410_bean;
	}

	public void setT410_bean(T410_Bean T410Bean) {
		T410_bean = T410Bean;
	}
}