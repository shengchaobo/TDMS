package cn.nit.service.table5;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import cn.nit.bean.table5.S512_Bean;
import cn.nit.dao.table5.S512_DAO;

import cn.nit.pojo.table5.S512POJO;
import cn.nit.util.Pagition;

public class S512_Service {
	
	private S512_DAO s512Dao = new S512_DAO();	
	/**
	 * 显示数据
	 * */
	public List<S512_Bean> loadInfo(String year){
		
		List<S512_Bean> list = null;//输出数据
		List<S512_Bean> list1= null;//原始数据
		boolean flag = false;
//			System.out.println(s512Dao.countOri(year));
		if(s512Dao.countOri(year)>0){//有统计数据
			list1 = s512Dao.getOriData(year);//重新统计数据
			flag = s512Dao.save(list1, year);//保存数据
			if(flag){
				list = s512Dao.loadInfo(year);
			}
		}
		return list;
	}
	
	/**
	 * 找出某年的总计信息
	 * */
	public S512_Bean getYearInfo(String year, String teaUnit){
		return s512Dao.getYearInfo(year,teaUnit);
	}
	
	
	
	public static void main(String arg[]){
		S512_Service ser =  new S512_Service();
		List<S512_Bean> list = ser.loadInfo("2010");
	    if(list==null){
	    	System.out.println("无数据");
	    }else{
	    	System.out.println("有数据");
	    }
		
	}

}
