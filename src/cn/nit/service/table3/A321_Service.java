package cn.nit.service.table3;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;





import cn.nit.bean.table3.A3211_Bean;
import cn.nit.bean.table3.A321_Bean;
import cn.nit.bean.table3.T311_Bean;
import cn.nit.bean.table3.T312_Bean;
import cn.nit.bean.table3.T322_Bean;
import cn.nit.bean.table3.T33_Bean;
import cn.nit.dao.table3.A321_DAO;
import cn.nit.dao.table3.T322_DAO;


import cn.nit.pojo.table3.A321POJO;
import cn.nit.util.TimeUtil;

public class A321_Service {
	
	

	
	/**  表311的数据库操作类  */
	private A321_DAO a321_DAO = new A321_DAO() ;

	private A321POJO a321POJO=new A321POJO();
	

   	String str=null;


	
	public String auditingData(String year){
		

		System.out.println(year);
		System.out.println("一定输出来1");

		List<A321_Bean> list1=new ArrayList<A321_Bean>();
		list1=getInfo(year);
		try{
		if(list1.size()!=0){
			A321_Bean a321_Bean=new A321_Bean();			
			a321_Bean.setTotalNum(list1.get(0).getTotalNum());
			a321_Bean.setFieldNum(list1.get(0).getTotalNum());
			a321_Bean.setDisClass("合计");
			a321_Bean.setArtRatio(100);
			list1.add(0,a321_Bean);
		}	
	}catch(Exception e){
		e.printStackTrace() ;
	}


		JSON json=JSONSerializer.toJSON(list1) ;
		String jsonStr=json.toString();
		return jsonStr;
		}
	
	
	
	public List<A321_Bean> getInfo(String year){

		int total=0;
		String disClass=null;
		int fieldNum=0;
		double artRatio=0.0;
		boolean flag;

		List<A3211_Bean> list=a321_DAO.getOriData(year);
		List<A321_Bean> list1=new ArrayList<A321_Bean>();
		for(int i=0;i<list.size();i++){
			total=total+list.get(i).getFieldNum();
		}
		for(int i=0;i<list.size();i++){
			A321_Bean a321_Bean = new A321_Bean();
			disClass=list.get(i).getMajorDegreeType();
			fieldNum=list.get(i).getFieldNum();
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMinimumFractionDigits(4);
			artRatio=Double.parseDouble(nf.format((double)(fieldNum)/(double)(total)))*100;
			System.out.println(artRatio);
			try{
			a321_Bean.setTotalNum(total);
			a321_Bean.setFieldNum(fieldNum);
			a321_Bean.setArtRatio(artRatio);
			a321_Bean.setDisClass(disClass);
			flag=a321_DAO.update(year, a321_Bean);
			if(flag){
				System.out.println("成功");
			}else{
				System.out.println("失败");
			}
			list1.add(i,a321_Bean);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return list1;

	}


}
