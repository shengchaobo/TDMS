package cn.nit.service.table3;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;





import cn.nit.bean.table3.A321_Bean;
import cn.nit.bean.table3.T311_Bean;
import cn.nit.bean.table3.T312_Bean;
import cn.nit.bean.table3.T322_Bean;
import cn.nit.bean.table3.T33_Bean;
import cn.nit.dao.table3.A321_DAO;


import cn.nit.pojo.table3.A321POJO;
import cn.nit.util.TimeUtil;

public class A321_Service {
	
	/**  表311的数据库操作类  */
	private A321_DAO a321_DAO = new A321_DAO() ;
	private A321_Bean a321_Bean=new A321_Bean();
	private A321POJO a321POJO=new A321POJO();
	

   	String str=null;


	
	public String auditingData(String year){
	
		System.out.println(year);
		System.out.println("一定输出来1");
			a321_DAO.delete(year);
			this.getStatic();
			if(a321_DAO.insert(a321_Bean)){
				List<A321POJO> list = a321_DAO.auditingData(year) ;
				a321POJO=list.get(0);
			}
		JSON json=JSONSerializer.toJSON(a321POJO) ;
		String jsonStr=json.toString();
		return jsonStr;
		}


	public void getStatic(){
		System.out.println("一定输出来2");
		int num1=0,num2=0,num3 = 0,num4=0,num5=0,num6=0,num7=0;
		double total=0.0;
		List<T322_Bean> list=a321_DAO.getOriData(T322_Bean.class, "T322_UndergraMajorInfo_Tea$");
		total=list.size();
		for(int i=0;i<list.size();i++){
			T322_Bean t322_Bean=new T322_Bean();
			t322_Bean=list.get(i);
			System.out.println(t322_Bean.getMajorDegreeType());
			if(t322_Bean.getMajorDegreeType().equals("02经济学")){
				num1++;
			}else if(t322_Bean.getMajorDegreeType().equals("05文学")){
				num2++;
			}else if(t322_Bean.getMajorDegreeType().equals("07理学")){
				num3++;
			}else if(t322_Bean.getMajorDegreeType().equals("08工学")){
				num4++;
			}else if(t322_Bean.getMajorDegreeType().equals("09农学")){
				num5++;
			}else if(t322_Bean.getMajorDegreeType().equals("12管理学")){
				num6++;
			}else if(t322_Bean.getMajorDegreeType().equals("13艺术学")){
				num7++;
			}	
		}
		a321_Bean.setEconomicNum(num1);
		a321_Bean.setLiteratureNum(num2);
		a321_Bean.setScienceNum(num3);
		a321_Bean.setEngineerNum(num4);
		a321_Bean.setAgronomyNum(num5);
		a321_Bean.setManageNum(num6);	
		a321_Bean.setArtNum(num7);	
		a321_Bean.setEconomicRatio(num1/total);
		a321_Bean.setLiteratureRatio(num2/total);
		a321_Bean.setScienceRatio(num3/total);
		a321_Bean.setEngineerRatio(num4/total);
		a321_Bean.setAgronomyRatio(num5/total);
		a321_Bean.setManageRatio(num6/total);	
		a321_Bean.setArtRatio(num7/total);
		a321_Bean.setTime(new Date());
	}

}
