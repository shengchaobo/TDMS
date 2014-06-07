/* 
* @Title: Trees.java
* @Package cn.bjtu.bean
* @Description 
* @author Lei Xia
*      Email: xialei199023@163.com
* @copyright BJTU(C)2014
* @date 2014-5-13 下午09:21:36
* @version V1.0 
*/
package cn.nit.bean;

/**
 * 
 * @author Lei Xia
 * @time: 2014-5-13/下午09:21:36
 */
public class Trees {

	private int TreeId ;
	
	private String TreeName ;
	
	private int ParentId ;
	
	private String Url ;

	public int getTreeId() {
		return TreeId;
	}

	public void setTreeId(int TreeId) {
		this.TreeId = TreeId;
	}

	public String getTreeName() {
		return TreeName;
	}

	public void setTreeName(String TreeName) {
		this.TreeName = TreeName;
	}

	public int getParentId() {
		return ParentId;
	}

	public void setParentId(int ParentId) {
		this.ParentId = ParentId;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String Url) {
		this.Url = Url;
	}
}
