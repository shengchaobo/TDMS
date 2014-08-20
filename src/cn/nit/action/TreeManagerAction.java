package cn.nit.action;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.Trees;
import cn.nit.bean.UserinfoBean;
import cn.nit.service.TreeManagerService;

public class TreeManagerAction {

	private Trees tree = new Trees() ;

	private TreeManagerService treeSer = new TreeManagerService() ;

	private int refId ;

	/**
	 * 初始化加载树
	 */
	public void loadTree(){

		UserinfoBean userinfo = (UserinfoBean)getSession().getAttribute("userinfo") ;
		
		String jsonTree;
		if("000".equals(userinfo.getRoleID())){
			jsonTree = treeSer.loadTrees(refId) ;	
		}else{
			jsonTree = treeSer.getDITreeByUserRole(refId, userinfo.getRoleID()) ;	
		}
		
		HttpServletResponse response = ServletActionContext.getResponse() ;
		PrintWriter out = null ;

		if(jsonTree == null){
			
			return ;
		}else{
			try {
				
//				System.out.println(jsonTree) ;
				response.setContentType("text/html;charset=UTF-8") ;
				out = response.getWriter() ;
				out.print(jsonTree) ;
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

	/**  添加树菜单  */
	public void removeTree(){

		HttpServletResponse response = ServletActionContext.getResponse() ;
		PrintWriter out = null ;

		try {			
//			System.out.println(this.getRefId()) ;
			out = response.getWriter() ;
			response.setContentType("text/html;charset=UTF-8") ;
			boolean flag = treeSer.removeTrees(this.getRefId());
			if(flag){				
				out.print("{success:true,successMsg:'删除成功！'}") ;
			} else{
				out.print("{success:false,errorMsg:'删除失败！'}") ;
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
	/**  添加树菜单  */
	public void addTree(){

		HttpServletResponse response = ServletActionContext.getResponse() ;
		PrintWriter out = null ;


		try {
			
//			System.out.println(this.tree) ;
			out = response.getWriter() ;
			response.setContentType("text/html;;charset=UTF-8") ;
			
			if(treeSer.addTree(tree)){				
				out.print("{success:true,successMsg:'添加成功！'}") ;
			} else{
				out.print("{success:false,errorMsg:'添加失败！'}") ;
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
	public void loadFunction(){
		
		UserinfoBean userinfo = (UserinfoBean)getSession().getAttribute("userinfo") ;
		
		String jsonTree = treeSer.getDITreeByUserRole(refId, userinfo.getRoleID()) ;
		HttpServletResponse response = ServletActionContext.getResponse() ;
		PrintWriter out = null ;

		if(jsonTree == null){
			
			return ;
		}else{
			try {
				
//				System.out.println(jsonTree) ;
				response.setContentType("text/html;charset=UTF-8") ;
				out = response.getWriter() ;
				out.print(jsonTree) ;
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
	
	public HttpServletRequest getRequest(){
		return ServletActionContext.getRequest() ;
	}
	
	public HttpSession getSession(){
		return getRequest().getSession() ;
	}
	
	public HttpServletResponse getResponse(){
		return ServletActionContext.getResponse() ;
	}
	
	public void setTrees(Trees tree){
		this.tree = tree ;
	}
	
	public Trees getTree(){
		return this.tree ;
	}
	
	public void setRefId(int refId){
		this.refId = refId ;
	}
	
	public int getRefId(){
		return this.refId ;
	}
}
