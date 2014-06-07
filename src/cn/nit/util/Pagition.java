/* 
* @Title: Pagition.java
* @Package cn.bjtu.util
* @Description 
* @author Lei Xia
*      Email: xialei199023@163.com
* @copyright BJTU(C)2014
* @date 2014-5-14 下午03:38:04
* @version V1.0 
*/
package cn.nit.util;

import java.util.List;

/**
 * easyui的json分页
 * @author Lei Xia
 * @time: 2014-5-14/下午03:38:04
 */
public class Pagition {

	private int total ;
	
	private List rows ;

	public Pagition(int total, List rows){
		this.total = total ;
		this.rows = rows ;
	}
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}
}
