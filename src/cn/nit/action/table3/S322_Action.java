package cn.nit.action.table3;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.other.UserRoleBean;
import cn.nit.bean.table3.S322_Bean;




import cn.nit.service.table3.S322_Service;
import cn.nit.util.ExcelUtil;

public class S322_Action {
	
	
	private S322_Service s322_Service = new S322_Service() ;
		
		private S322_Bean s322_Bean = new S322_Bean() ;
			
		public S322_Service getS322_Service() {
			return s322_Service;
		}

		public void setS322_Service(S322_Service s322Service) {
			s322_Service = s322Service;
		}

		public S322_Bean getS322_Bean() {
			return s322_Bean;
		}
		
		public void setS322_Bean(S322_Bean s322Bean) {
			s322_Bean = s322Bean;
		}
		
		/**  为界面加载数据  */
	public void auditingData(){
			
			System.out.println("一定输出来");
//			Date date=new Date();	
//			String sdate=date.toString();
//			String year=sdate.substring(sdate.length()-4, sdate.length());
			
			
			String result = s322_Service.auditingData() ;
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







//
//		public InputStream getInputStream(){
//
//			InputStream inputStream = null ;
//
//			try {
//				inputStream = new ByteArrayInputStream(ExcelUtil.exportExcel().toByteArray()) ;
//			} catch (Exception e) {
//				e.printStackTrace();
//				return null ;
//			}
//
//			return inputStream ;
//		}

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



		





		public static void main(String args[]){
			String match = "[\\d]+" ;
			System.out.println("23gfhf4".matches(match)) ;
		}

}
