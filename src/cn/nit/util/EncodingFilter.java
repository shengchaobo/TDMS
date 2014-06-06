/* 
 * @Title: EncodingFilter.java
 * @Package cn.bjtu.util
 * @Description 
 * @author Lei Xia
 *      Email: xialei199023@163.com
 * @copyright BJTU(C)2014
 * @date 2014-4-21 上午11:08:49
 * @version V1.0 
 */
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

/**
 * 编码格式Filter类
 * @author Lei Xia
 * @time: 2014-4-21/上午11:08:49
 */
public class EncodingFilter implements Filter{
	private String encoding = null;  
	/* 
	 * @see javax.servlet.Filter#destroy()
	 *
	 * 2014-4-21 / 上午11:09:09
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		encoding = null ;
	}

	/* 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 *
	 * 2014-4-21 / 上午11:09:09
	 */
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)req ;
		HttpServletResponse response = (HttpServletResponse)resp ;

		if(this.encoding != null && !this.encoding.equals("")){
			request.setCharacterEncoding(this.encoding) ;
			response.setCharacterEncoding(this.encoding) ;
		}else{
			request.setCharacterEncoding("UTF-8") ;
			response.setCharacterEncoding("UTF-8") ;
		}
		
		chain.doFilter(request, response) ;

	}

	/* 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 *
	 * 2014-4-21 / 上午11:09:09
	 */
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		encoding = config.getInitParameter("encoding") ;
	}

}
