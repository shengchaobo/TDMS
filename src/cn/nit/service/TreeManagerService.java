package cn.nit.service;

import java.util.List;

import cn.nit.bean.Trees;
import cn.nit.dao.TreeManagerDAO;

/**
 * 树的逻辑处理类
 * @author lenovo
 *
 */
public class TreeManagerService {
	
	private TreeManagerDAO treeDAO = new TreeManagerDAO() ;
	
	/**
	 * 添加树
	 * @param tree
	 * @return
	 */
	public boolean addTree(Trees tree){
		
		return treeDAO.insert(tree) ;
	}
	
	/**
	 * 获取树
	 * @param parentId
	 * @return
	 */
	public List<Trees> getTrees(int parentId, String roleId){
		
		return treeDAO.getTrees(parentId, roleId) ;
	}
	
	/**
	 * 删除
	 * @param parentId
	 * @return
	 */
	public boolean removeTrees(int nodeid){
		
		return treeDAO.removeTrees(nodeid) ;
	}
	
	
	public String loadTrees(int parentId, String roleId){
		
		List<Trees> trees = treeDAO.getTrees(parentId, roleId) ;
		
		if(trees == null || trees.isEmpty()){
			return null ;
		}
		
		StringBuffer jsonTree = new StringBuffer() ;
		jsonTree.append("[") ;
		//"[{\"id\":0,\"text\":\"查询\",\"state\":\"closed\",\"attributes\":{\"url\":\"test\"}},{\"id\":1,\"text\":\"填报\",\"state\":\"closed\",\"attributes\":{\"url\":\"test\"}}]"
		for(Trees tree : trees){
			jsonTree.append("{\"id\":" + tree.getTreeId()) ;
			jsonTree.append(",\"text\":\"" + tree.getTreeName() +"\"") ;
			
			if(null == tree.getUrl() || "".equals(tree.getUrl())){
				jsonTree.append(",\"state\":\"closed\"},") ;
			}else{
				jsonTree.append(",\"attributes\":{\"url\":\"" + tree.getUrl() + "\"}},") ;
			}
		}
		jsonTree.deleteCharAt(jsonTree.lastIndexOf(",")) ;
		jsonTree.append("]") ;
		
		return jsonTree.toString() ;
	}
	
	public String getDITreeByUserRole(String roleId, int parentId){
		
		List<Trees> list = treeDAO.getDITreeByUserRole(roleId, parentId) ;
		
		if(list == null || list.isEmpty()){
			return null ;
		}
		
		StringBuffer jsonTree = new StringBuffer() ;
		jsonTree.append("[") ;
		//"[{\"id\":0,\"text\":\"查询\",\"state\":\"closed\",\"attributes\":{\"url\":\"test\"}},{\"id\":1,\"text\":\"填报\",\"state\":\"closed\",\"attributes\":{\"url\":\"test\"}}]"
		for(Trees tree : list){
			jsonTree.append("{\"id\":" + tree.getTreeId()) ;
			jsonTree.append(",\"text\":\"" + tree.getTreeName() +"\"") ;
			
			if(null == tree.getUrl() || "".equals(tree.getUrl())){
				jsonTree.append(",\"state\":\"closed\"},") ;
			}else{
				jsonTree.append(",\"attributes\":{\"url\":\"" + tree.getUrl() + "\"}},") ;
			}
		}
		
		jsonTree.deleteCharAt(jsonTree.lastIndexOf(",")) ;
		jsonTree.append("]") ;
		
		return jsonTree.toString() ;
	}
}
