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
	public List<Trees> getTrees(int parentId){
		
		return treeDAO.getTrees(parentId) ;
	}
	
	/**
	 * 删除
	 * @param parentId
	 * @return
	 */
	public boolean removeTrees(int nodeid){
		
		return treeDAO.removeTrees(nodeid) ;
	}
	
	
	public String loadTrees(int parentId){
		
		List<Trees> trees = treeDAO.getTrees(parentId) ;
		
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
	
	
	public String getDITreeByUserRole(int parentId,String roleId){
		
		List<Trees> list = treeDAO.getDITreeByUserRole(parentId,roleId) ;
		
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
	
	/**
	 * 获得全部Tree,即所有权限
	 * 
	 */
	public List<Trees> getTreesList(){		
		return treeDAO.getTreesList() ;
	}
	
	/**
	 * 获得全部Tree的总数
	 * 
	 */
	public int getSumTreeNum(){		
		return treeDAO.getSumTreeNum() ;
	}
}
