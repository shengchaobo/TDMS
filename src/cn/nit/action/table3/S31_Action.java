package cn.nit.action.table3;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;


import cn.nit.bean.other.UserRoleBean;
import cn.nit.bean.table2.T21_Bean;
import cn.nit.bean.table3.S31_Bean;
import cn.nit.dao.table3.S31_DAO;
import cn.nit.excel.imports.table3.S31Excel;




import cn.nit.pojo.table3.S31POJO;
import cn.nit.service.table3.S31_Service;
import cn.nit.util.JsonUtil;


public class S31_Action {
	
private S31_Service s31_Service = new S31_Service() ;
	
	private S31_Bean s31_Bean = new S31_Bean() ;
	
	private S31_DAO  s31_DAO=new S31_DAO();

	
	private S31Excel s31Excel = new S31Excel() ;
	

	/**  哪一年数据  */
	private String selectYear; //删除的id
	
	/**  导出的excelName名 */
	private String excelName ;
	
	/**  前台获数据 */
	private String data ;
	
	//save的字段
	private String fields;
	
	
	//查询出所有
	public void loadInfo() throws Exception{
		
		HttpServletResponse response = ServletActionContext.getResponse() ;		
		
		S31_Bean bean = s31_Service.getYearInfo(this.getSelectYear()) ;
		
		//private JSONObject jsonObj;
		bean.setTime(null);
		String json = JsonUtil.beanToJson(bean);
		
		PrintWriter out = null ;

		if(bean == null){
			response.setContentType("text/html;charset=UTF-8") ;
			out = response.getWriter() ;
			out.println( "<script language='javascript'>window.alert('无该年数据');</script>" ); 
		}else{
			try {				
				System.out.println(json) ;
				response.setContentType("application/json;charset=UTF-8") ;
				out = response.getWriter() ;
				out.print(json) ;
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

	

	
	/**  为界面加载数据  */
	public void auditingData(){
		
		System.out.println("一定输出来");
		Date date=new Date();	
		String sdate=date.toString();
		String year=sdate.substring(sdate.length()-4, sdate.length());
		
		
		String result = s31_Service.auditingData(year) ;
		PrintWriter out = null ;
		
		try{
			getResponse().setContentType("text/html; charset=UTF-8") ;
			out = getResponse().getWriter() ;
			out.print(result) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
	
	
	
	
	public InputStream getInputStream(){
		
		InputStream inputStream =null;
		
		try{
			System.out.println("愁死了");	
			String year=this.getSelectYear();
			List<S31POJO> list = s31_DAO.exportData(year);
			inputStream=new ByteArrayInputStream(s31Excel.exportExcel(list).toByteArray());
			
			
		}catch (Exception e){
			e.printStackTrace();
			return null;
			
			
		}
		return inputStream;
		
	}













	public String execute() throws Exception{

		getResponse().setContentType("application/octet-stream;charset=UTF-8") ;
		return "success" ;
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
	
	public UserRoleBean getUserinfo(){
		return (UserRoleBean)getSession().getAttribute("userinfo") ;
	}



	
	public S31_Service getS31_Service() {
		return s31_Service;
	}




	public void setS31_Service(S31_Service s31Service) {
		s31_Service = s31Service;
	}




	public S31_Bean getS31_Bean() {
		return s31_Bean;
	}




	public void setS31_Bean(S31_Bean s31Bean) {
		s31_Bean = s31Bean;
	}




	public static void main(String args[]){
		String match = "[\\d]+" ;
		System.out.println("23gfhf4".matches(match)) ;
	}





	public void setSelectYear(String selectYear) {
		this.selectYear = selectYear;
	}





	public String getSelectYear() {
		return selectYear;
	}





	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}





	public String getExcelName() {
		return excelName;
	}





	public void setData(String data) {
		this.data = data;
	}





	public String getData() {
		return data;
	}





	public void setFields(String fields) {
		this.fields = fields;
	}





	public String getFields() {
		return fields;
	}

}
