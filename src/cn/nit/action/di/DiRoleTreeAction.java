package cn.nit.action.di;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.Trees;
import cn.nit.bean.di.DiRoleTreeBean;
import cn.nit.service.TreeManagerService;
import cn.nit.service.di.DiRoleTreeService;

public class DiRoleTreeAction {

	private DiRoleTreeService diRoleTreeSer = new DiRoleTreeService() ;
	private TreeManagerService treeSer = new TreeManagerService() ;
	
	private String roleID ;
	
	//修改后的treeIDs
	private String treeIDs;
	
	/**
	 * 加载一个角色的权限tree
	 */
	public void loadRoleTree(){
		
		int sumTreeNum = treeSer.getSumTreeNum();
		List<Trees> trees = treeSer.getTreesList();
		List<Integer> treeIDs = diRoleTreeSer.getRoleTreeIDs(this.getRoleID());
		
		
		StringBuffer jsonTree = new StringBuffer() ;
		jsonTree.append("{") ;
		jsonTree.append("\"total\":" + sumTreeNum);
		jsonTree.append(",\"rows\":[") ;
		//"[{\"id\":0,\"text\":\"查询\",\"state\":\"closed\",\"attributes\":{\"url\":\"test\"}},{\"id\":1,\"text\":\"填报\",\"state\":\"closed\",\"attributes\":{\"url\":\"test\"}}]"
		for(Trees tree : trees){
			jsonTree.append("{\"id\":" + tree.getTreeId()) ;
			jsonTree.append(",\"name\":\"" + tree.getTreeName() +"\"") ;
			if(treeIDs.contains(tree.getTreeId())){
				jsonTree.append(",\"ck\":" + true) ;
			}else{
				jsonTree.append(",\"ck\":" + false) ;
			}
			
			if(null == tree.getUrl() || "".equals(tree.getUrl())){				
				jsonTree.append(",\"_parentId\":" + tree.getParentId()) ;
				jsonTree.append(",\"state\":\"closed\"},") ;
			}else{
				jsonTree.append(",\"_parentId\":" + tree.getParentId() + "},") ;
			}
		}
		jsonTree.deleteCharAt(jsonTree.lastIndexOf(",")) ;
		jsonTree.append("]}") ;
		
		String json = jsonTree.toString();
/*		System.out.println(this.getRoleID()+sumTreeNum);
		System.out.println(json);*/
		PrintWriter out = null ;
		
		try{
			//设置输出内容的格式为json
			getResponse().setContentType("application/json; charset=UTF-8") ;
			out = getResponse().getWriter() ;
			out.print(json) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return ;
		}finally{
			out.flush() ;
			
			if(out != null){
				out.close() ;
			}
		}		
	}
	
	/**
	 * 修改一个角色的权限tree
	 */
	public void editRoleTree(){
		
		//System.out.println(this.getRoleID());
		//System.out.println(this.getTreeIDs());
		
		String ids = this.getTreeIDs();
		String[] roleID = ids.split(",");
		
		List<DiRoleTreeBean> roleTreeList = new ArrayList<DiRoleTreeBean>();
		for(String id: roleID){
			DiRoleTreeBean roleTreeBean = new DiRoleTreeBean();
			roleTreeBean.setRoleID(this.getRoleID());
			roleTreeBean.setTreeID(Integer.parseInt(id));
			roleTreeList.add(roleTreeBean);
		}
		
		boolean flag = diRoleTreeSer.save(this.getRoleID(),roleTreeList) ;
		
		PrintWriter out = null ;
	
		try{
			
			getResponse().setContentType("application/json; charset=UTF-8") ;
			out = getResponse().getWriter() ;
			if(flag){
				out.print("{\"state\":true,\"data\":\"保存成功!!!\"}") ;
			}else{
				out.print("{\"state\":true,\"data\":\"保存失败!!!\"}") ;
			}
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,\"data\":\"系统错误，请联系管理员!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
	/**
	 * 获取request
	 * @return
	 *
	 * @time: 2014-5-14/下午03:07:00
	 */
	public HttpServletRequest getRequest(){
		return ServletActionContext.getRequest() ;
	}
	
	/**
	 * 获取session
	 * @return
	 *
	 * @time: 2014-5-14/下午03:07:14
	 */
	public HttpSession getSession(){
		return getRequest().getSession() ;
	}
	
	/**
	 * 获取response
	 * @return
	 *
	 * @time: 2014-5-14/下午03:07:24
	 */
	public HttpServletResponse getResponse(){
		return ServletActionContext.getResponse() ;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	public String getRoleID() {
		return roleID;
	}

	public void setTreeIDs(String treeIDs) {
		this.treeIDs = treeIDs;
	}

	public String getTreeIDs() {
		return treeIDs;
	}
}
