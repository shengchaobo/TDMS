package cn.nit.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cn.nit.bean.UserinfoBean;


public class LoginFilter implements Filter{

	private FilterConfig config ;
	
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req ;
		HttpServletResponse response = (HttpServletResponse) res ;
		HttpSession session = request.getSession() ;
		UserinfoBean user = (UserinfoBean) session.getAttribute("userinfo") ;
		
		if(user == null){
			response.sendRedirect(config.getInitParameter("login")) ;
		}else{
			chain.doFilter(request, response) ;
		}
		
	}

	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.config = config ;
		System.out.println(config.getServletContext()) ;
	}

}
