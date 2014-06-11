/* 
 * @Title: DAOUtil.java
 * @Package cn.bjtu.util
 * @Description 
 * @author Lei Xia
 *      Email: xialei199023@163.com
 * @copyright BJTU(C)2014
 * @date 2014-4-18 下午09:38:42
 * @version V1.0 
 */
package cn.nit.util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import cn.nit.dbconnection.DBConnection;

/**
 * 数据库操作工具包
 * @author lenovo
 *
 */
public class DAOUtil {

	/**
	 * 数据库数据的插入
	 * @param obj       插入数据的实体类
	 * @param tableName 要插入数据对数据库的表名
	 * @param field     数据库对应的字段
	 * @param conn      数据库连�?
	 * @return
	 *
	 * @time: 2014-4-18/下午10:02:27
	 */
	public static boolean insert(Object obj, String tableName, String field, Connection conn){

		//构建SQL语句
		StringBuffer sql = new StringBuffer("insert into " + tableName) ;
		sql.append("(" + field + ") values(") ;
		String fields[] = field.split(",") ;
		int length = fields.length ;

		for(int i = 0; i < length; i++){
			if(i == (length - 1)){
				sql.append("?)") ;
			}else{
				sql.append("?,") ;
			}
		}

		System.out.println(sql.toString()) ;
		BeanWrapper wrapper = new BeanWrapperImpl(obj) ;
		//判断数据插入的条数，0代表数据插入失败
		int flag = 0 ;
		PreparedStatement pst = null ;
		try{
			pst = conn.prepareStatement(sql.toString()) ;
//			System.out.println(sql.toString());

			for(int i = 0; i < length; i++){
				String type = wrapper.getPropertyType(fields[i]).toString() ;


				//判断插入数据的类型，并赋�?
				if(type.endsWith("String")){
					pst.setString(i + 1, (String) wrapper.getPropertyValue(fields[i])) ;
				}else if(type.endsWith("int")||type.endsWith("Integer")){
					pst.setInt(i + 1, (Integer) wrapper.getPropertyValue(fields[i])) ;
				}else if(type.endsWith("Date")){
					java.util.Date utilDate = (java.util.Date)wrapper.getPropertyValue(fields[i]) ;
					Date sqlDate = new Date(utilDate.getTime()) ;
					pst.setDate(i + 1, sqlDate ) ;
				}else if(type.endsWith("long")||type.endsWith("Long")){
					pst.setLong(i + 1, (Long) wrapper.getPropertyValue(fields[i])) ;

				}else if(type.endsWith("boolean")||type.endsWith("Boolean")){
					pst.setBoolean(i+1, (Boolean) wrapper.getPropertyValue(fields[i])) ;
				}else if(type.endsWith("double")||type.endsWith("Double")){
					pst.setDouble(i+1, (Double) wrapper.getPropertyValue(fields[i])) ;

				}else{
					throw new Exception("自行添加对应类型" + type) ;

				}
			}

			flag = pst.executeUpdate() ;
		}catch(Exception e){
			e.printStackTrace() ;
			return false ;
		}finally{
			DBConnection.close(pst) ;
			DBConnection.close(conn) ;
		}

		if(flag > 0){
			return true ;
		}else{
			return false ;
		}
	}

	/**
	 * 将数据中所取出的数据转换为实际应用的类�?
	 * @param <T>
	 * @param rs  ResultSet 结果�?
	 * @param cla           实体�?
	 * @return
	 *
	 * @time: 2014-4-18/下午10:17:57
	 */
	public static <T> List<T> getList(ResultSet rs, Class<T> cla){

		List<T> list = null ;
		BeanWrapper wrapper = null ;
		try{
			if(rs != null){
				list = new ArrayList<T>() ;
			}else{
				return null ;
			}

			while(rs.next()){
				Class clazz = null ;
				//初始化实体类
				clazz = Class.forName(cla.getName()) ;
				T t = (T)clazz.newInstance() ;
				wrapper = new BeanWrapperImpl(t) ;
				//获取实体类的属�?
				Field fields[] = clazz.getDeclaredFields() ;

				for(Field field : fields){
					String fieldName = field.getName() ;
					Class clazzType = wrapper.getPropertyType(fieldName) ;
					String type = clazzType.getName() ;

					//给实体类的相关属性赋�?
					if(type.endsWith("String")){
						wrapper.setPropertyValue(fieldName, rs.getString(fieldName)) ;
					}else if(type.endsWith("int")){
						wrapper.setPropertyValue(fieldName, rs.getInt(fieldName)) ;
					}else if(type.endsWith("Date")){
						wrapper.setPropertyValue(fieldName, new java.util.Date(rs.getDate(fieldName).getTime())) ;
					}else if(type.endsWith("long")){
						wrapper.setPropertyValue(fieldName, rs.getLong(fieldName)) ;

					}else if(type.endsWith("boolean")){
						wrapper.setPropertyValue(fieldName, rs.getBoolean(fieldName)) ;
					}else if(type.endsWith("double")){
						wrapper.setPropertyValue(fieldName, rs.getDouble(fieldName)) ;
					}
					else{

						throw new Exception("该类型不存在，请自己添加 " + type) ;
					}
				}

				list.add(t) ;
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(rs) ;
		}

		return list ;
	}

	public static boolean batchInsert(List list, String tableName, String field, Connection conn) throws Exception{
		//构建SQL语句
		StringBuffer sql = new StringBuffer("insert into " + tableName) ;
		sql.append("(" + field + ") values(") ;
		String fields[] = field.split(",") ;
		int length = fields.length ;

		for(int i = 0; i < length; i++){
			if(i == (length - 1)){
				sql.append("?)") ;
			}else{
				sql.append("?,") ;
			}
		}

		System.out.println(sql.toString()) ;
		BeanWrapperImpl wrapper = new BeanWrapperImpl() ;
		//判断数据插入的条数，0代表数据插入失败
		int flag[] ;
		PreparedStatement pst = null ;
		try{
			conn.setAutoCommit(false) ;
			pst = conn.prepareStatement(sql.toString()) ;
			for(Object obj : list){
				wrapper.setWrappedInstance(obj) ;
				for(int i = 0; i < length; i++){
					String type = wrapper.getPropertyType(fields[i]).toString() ;

					//判断插入数据的类型，并赋�?
					if(type.endsWith("String")){
						pst.setString(i + 1, (String) wrapper.getPropertyValue(fields[i])) ;
					}else if(type.endsWith("int")){
						pst.setInt(i + 1, (Integer) wrapper.getPropertyValue(fields[i])) ;
					}else if(type.endsWith("Date")){
						java.util.Date utilDate = (java.util.Date)wrapper.getPropertyValue(fields[i]) ;
						Date sqlDate = new Date(utilDate.getTime()) ;
						pst.setDate(i + 1, sqlDate ) ;
					}else if(type.endsWith("long")){
						pst.setLong(i + 1, (Long) wrapper.getPropertyValue(fields[i])) ;
					}else{


						throw new Exception("自行添加对应类型" + type) ;

					}
				}
				pst.addBatch() ;
			}
			flag = pst.executeBatch() ;
			conn.commit() ;
			conn.setAutoCommit(true) ;
		}catch(Exception e){
			e.printStackTrace() ;
			conn.rollback() ;
			return false ;
		}finally{
			DBConnection.close(pst) ;
			DBConnection.close(conn) ;
		}
		
		if(flag[0] > 0){
			return true ;
		}else{
			return false ;
		}
	}
	
	/**
	 * 数据库数据的插入
	 * @param obj       插入数据的实体类
	 * @param tableName 要插入数据对数据库的表名
	 * @param field     数据库对应的字段
	 * @param conn      数据库连�?
	 * @return
	 *
	 * @time: 2014-4-18/下午10:02:27
	 */
	public static boolean update(Object obj, String tableName, String key, String field, Connection conn){

		//构建SQL语句
		StringBuffer sql = new StringBuffer("update " + tableName) ;
		sql.append(" set ") ;
		String fields[] = field.split(",") ;
		int length = fields.length ;

		for(int i = 0; i < length; i++){
			if(i == (length - 1)){
				sql.append(fields[i] + "=?") ;
			}else{
				sql.append(fields[i] + "=?,") ;
			}
		}
		
		if(key == null){
			return false ;
		}
		
		sql.append(" where ") ;
		String keyFields[] = key.split(",") ;
		
		for(int i = 0; i < keyFields.length; i++){
				
			if(i == (keyFields.length - 1)){
				sql.append(keyFields[i] + "=?") ;
			}else{
				sql.append(keyFields[i] + "=? and ") ;
			}
		}
		
		length = length + keyFields.length ;
//		System.out.println(sql.toString()) ;
		BeanWrapper wrapper = new BeanWrapperImpl(obj) ;
		//判断数据插入的条数，0代表数据插入失败
		int flag = 0 ;
		int j = 0 ;
		PreparedStatement pst = null ;
		try{
			pst = conn.prepareStatement(sql.toString()) ;

			for(; j < length; j++){
				String type = null ;
				String vField = null ;
				
				if(j < fields.length){
					type = wrapper.getPropertyType(fields[j]).toString() ;
					vField = fields[j] ;
				}else{
					type = wrapper.getPropertyType(keyFields[j - fields.length]).toString() ;
					vField = keyFields[j - fields.length] ;
				}


				//判断插入数据的类型，并赋�?
				if(type.endsWith("String")){
					pst.setString(j + 1, (String) wrapper.getPropertyValue(vField)) ;
				}else if(type.endsWith("int") || type.endsWith("Integer")){
					pst.setInt(j + 1, (Integer) wrapper.getPropertyValue(vField)) ;
				}else if(type.endsWith("Date")){
					java.util.Date utilDate = (java.util.Date)wrapper.getPropertyValue(vField) ;
					Date sqlDate = new Date(utilDate.getTime()) ;
					pst.setDate(j + 1, sqlDate ) ;
				}else if(type.endsWith("long") || type.endsWith("Long")){
					pst.setLong(j + 1, (Long) wrapper.getPropertyValue(vField)) ;
				}else if(type.endsWith("Boolean") || type.endsWith("boolean")){
					pst.setBoolean(j + 1, (Boolean) wrapper.getPropertyValue(vField)) ;
				}else if(type.endsWith("double") || type.endsWith("Double")){
					pst.setDouble(j + 1, (Double) wrapper.getPropertyValue(vField)) ;
				}else{

					throw new Exception("自行添加对应类型" + type) ;

				}
			}

			flag = pst.executeUpdate() ;
		}catch(Exception e){
			e.printStackTrace() ;
			return false ;
		}finally{
			DBConnection.close(pst) ;
			DBConnection.close(conn) ;
		}

		if(flag > 0){
			return true ;
		}else{
			return false ;
		}
	}
	
}
