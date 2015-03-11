package cn.nit.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.QueryConditionsBean;
import cn.nit.dao.QueryResultDAO;
import cn.nit.service.QueryResultService;

public class QueryResultAction {
    private String tablename;
    private QueryResultService queryResultService=new QueryResultService();
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	
	private String LeftJoin;
	
	private String FieldName;
	
	private String LogicRelation;
	
	private String ParamValue;
	
	private String RightJoin;
	
	private String JoinRelation;
	
    private String LeftJoin1;
	
	private String FieldName1;
	
	private String LogicRelation1;
	
	private String ParamValue1;
	
	private String RightJoin1;
	
	public String getLeftJoin() {
		return LeftJoin;
	}

	public void setLeftJoin(String leftJoin) {
		LeftJoin = leftJoin;
	}

	public String getFieldName() {
		return FieldName;
	}

	public void setFieldName(String fieldName) {
		FieldName = fieldName;
	}

	public String getParamValue() {
		 try {
			 ParamValue = URLEncoder.encode(ParamValue, "UTF-8");
			 //this.saveFile = new String(saveFile.getBytes("ISO-8859-1"),"UTF-8");// 中文乱码解决
			 } catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
			 }
			 return ParamValue;
	}

	public void setParamValue(String paramValue) {
		ParamValue=paramValue;
	}

	public String getRightJoin() {
		return RightJoin;
	}

	public void setRightJoin(String rightJoin) {
		RightJoin = rightJoin;
	}

	public String getJoinRelation() {
		return JoinRelation;
	}

	public void setJoinRelation(String joinRelation) {
		JoinRelation = joinRelation;
	}

	public String getLeftJoin1() {
		return LeftJoin1;
	}

	public void setLeftJoin1(String leftJoin1) {
		LeftJoin1 = leftJoin1;
	}

	public String getFieldName1() {
		return FieldName1;
	}

	public void setFieldName1(String fieldName1) {
		FieldName1 = fieldName1;
	}

	public String getParamValue1() {
		 try {
			 ParamValue1 = URLEncoder.encode(ParamValue1, "UTF-8");
			 //this.saveFile = new String(saveFile.getBytes("ISO-8859-1"),"UTF-8");// 中文乱码解决
			 } catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
			 }
			 return ParamValue1;
	}

	public void setParamValue1(String paramValue1) {
		ParamValue1 = paramValue1;
	}

	public String getRightJoin1() {
		return RightJoin1;
	}

	public void setRightJoin1(String rightJoin1) {
		RightJoin1 = rightJoin1;
	}

	public String getLogicRelation() {
		return LogicRelation;
	}

	public void setLogicRelation(String logicRelation) {
		LogicRelation = logicRelation;
	}

	public String getLogicRelation1() {
		return LogicRelation1;
	}

	public void setLogicRelation1(String logicRelation1) {
		LogicRelation1 = logicRelation1;
	}
	
    public void loadQueryResult(){
    	System.out.println(getParamValue());
    	QueryConditionsBean bean=new QueryConditionsBean();
    	bean.setLeftJoin(getLeftJoin());
    	bean.setFieldName(getFieldName());
    	bean.setLogicRelation(getLogicRelation());
    	bean.setParamValue(getParamValue());
    	bean.setRightJoin(getRightJoin());
    	bean.setJoinRelation(getJoinRelation());
    	bean.setLeftJoin1(getLeftJoin1());
    	bean.setFieldName1(getFieldName1());
    	bean.setLogicRelation1(getLogicRelation1());
    	bean.setParamValue1(getParamValue1());
    	bean.setRightJoin1(getRightJoin1());
    	System.out.println(bean.getParamValue());
		String pages=queryResultService.auditingData(tablename,bean);
	    PrintWriter out = null ;
		
		try{
			getResponse().setContentType("text/html; charset=UTF-8") ;
			out = getResponse().getWriter() ;
			out.print(pages) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
    }
    
    
    
	public String getTablename() {
		return tablename;
	}



	public void setTablename(String tablename) {
		this.tablename = tablename;
	}



	public HttpServletRequest getRequest() {
		return request;
	}



	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}



	public HttpServletResponse getResponse() {
		return response;
	}



	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public static void main(String[] args) {
	
	}

}
