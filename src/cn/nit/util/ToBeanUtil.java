package cn.nit.util;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import cn.nit.dbconnection.DBConnection;

public class ToBeanUtil {
	
	/**

	 * 将获取到的数据转换bean
	 * @param <T>
	 * @param rs  ResultSet 结果�?
	 * @param cla           实体�?
	 * @return 
	 * @return
	 *
	 * @time: 2014-4-18/下午10:17:57
	 */
	public static <T> T toBean(String data, Class<T> cla){
		
		BeanWrapper wrapper = null ;
		try{	
			String fields[] = data.split(",");
			
			Class clazz = null ;
			//初始化实体类
			clazz = Class.forName(cla.getName()) ;
			T t = (T)clazz.newInstance() ;
			wrapper = new BeanWrapperImpl(t) ;
			
			for(String s : fields){
				String fieldName = s;
				String mapVal[] = s.split("%");
				Class clazzType = wrapper.getPropertyType(mapVal[0]) ;
				//System.out.println(wrapper.getPropertyType(fieldName));
				//System.out.println(fieldName);
				String type = clazzType.getName() ;

				//给实体类的相关属性赋
				if(type.endsWith("String")){
					wrapper.setPropertyValue(mapVal[0], mapVal[1]) ;
				}else if(type.endsWith("Integer")||type.endsWith("int")){
					if(mapVal.length==1){
						wrapper.setPropertyValue(mapVal[0], 0) ;
					}else{
						wrapper.setPropertyValue(mapVal[0], Integer.parseInt(mapVal[1])) ;
					}				
				}else if(type.endsWith("Double")||type.endsWith("double")){
					if(mapVal.length==1){
						wrapper.setPropertyValue(mapVal[0], 0.0) ;
					}else{
						wrapper.setPropertyValue(mapVal[0], Double.parseDouble(mapVal[1])) ;
					}					
				}else if(type.endsWith("Date")){
//					wrapper.setPropertyValue(mapVal[0], TimeUtil.changeDateYMD(mapVal[1])) ;
					if(mapVal[1].length() == 4){
						wrapper.setPropertyValue(mapVal[0], TimeUtil.changeDateY(mapVal[1])) ;				
					}
					else if(mapVal[1].length() == 7){
						wrapper.setPropertyValue(mapVal[0], TimeUtil.changeDateYM(mapVal[1])) ;
					}
					else if(mapVal[1].length() == 10){
						wrapper.setPropertyValue(mapVal[0], TimeUtil.changeDateYMD(mapVal[1])) ;	
					}
				}else if(type.endsWith("long")||type.endsWith("Long")){
					if(mapVal.length==1){
						wrapper.setPropertyValue(mapVal[0], 0) ;
					}else{
						wrapper.setPropertyValue(mapVal[0], Long.parseLong(mapVal[1])) ;
					}					
				}else if(type.endsWith("Boolean")||type.endsWith("boolean")){
					wrapper.setPropertyValue(mapVal[0], Boolean.parseBoolean(mapVal[1])) ;
				}else{
					throw new Exception("该类型不存在，请自己添加 " + type) ;
				}
			}
			
			return t ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}
		
		
	}

}
