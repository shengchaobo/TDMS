/* 
* @Title: MD5Util.java
* @Package cn.bjtu.util
* @Description 
* @author Lei Xia
*      Email: xialei199023@163.com
* @copyright BJTU(C)2014
* @date 2014-4-18 下午08:53:15
* @version V1.0 
*/
package cn.nit.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 
 * @author Lei Xia
 * @time: 2014-4-18/下午08:53:15
 */
public class MD5Util {
	
	public static String encode(String data){
		
		return DigestUtils.md5Hex(data) ;
	}
	
	public static void main(String args[]){
		System.out.println(encode("123456123456123123")) ;
	}
}
