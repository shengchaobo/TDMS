package cn.nit.service.table3;

import java.util.Date;
import java.util.List;

import cn.nit.bean.table3.S322_Bean;
import cn.nit.bean.table3.T322_Bean;
import cn.nit.dao.table3.S322_DAO;
import cn.nit.pojo.table3.S322POJO;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;


public class S322_Service {
	
	/**  表311的数据库操作类  */
	private S322_DAO s322_DAO = new S322_DAO() ;
	private S322_Bean s322_Bean=new S322_Bean();
	private S322POJO s322POJO=new S322POJO();
	
	

   	String str=null;


	
	public String auditingData(){
	    String jsonStr1=null;
	    String jsonStr=null;
		System.out.println("一定输出来1");
		s322_DAO.empty();
		this.getStatic();
		List<S322POJO> list = s322_DAO.auditingData() ;
		JSON json=JSONSerializer.toJSON(list) ;
		jsonStr=json.toString();
		jsonStr1=jsonStr1+jsonStr;
		return jsonStr;
		}


	public void getStatic(){
		System.out.println("一定输出来2");
		S322_Bean s322_Bean=new S322_Bean();
		List<T322_Bean> list=s322_DAO.getOriData(T322_Bean.class, "T322_UndergraMajorInfo_Tea$");
		for(int i=0;i<list.size();i++){
			T322_Bean t322_Bean=new T322_Bean();
			t322_Bean=list.get(i);
			if(t322_Bean.getAppvlResult().equals("通过")){
				s322_Bean.setSeqNumber(i);
				s322_Bean.setTeaUnit("hHh");
				s322_Bean.setUnitID("444");
				s322_Bean.setPassedMajor(t322_Bean.getMajorName());
				s322_Bean.setMajorID(t322_Bean.getMajorID());
				s322_Bean.setAssessTime(t322_Bean.getAppvlTime());
				s322_Bean.setValidityBegin(t322_Bean.getFromTime());
				s322_Bean.setValidityEnd(t322_Bean.getEndTime());
				s322_Bean.setAssessOrg(t322_Bean.getAppvlAuth());
				s322_Bean.setTime(new Date());
				System.out.println(JSONSerializer.toJSON(s322_Bean).toString());
				s322_DAO.insert(s322_Bean);
		}
		
	}
		System.out.println("不开心不开心");
	}
	
	

	

	

	
	public static void main(String args[]){
		S322_Service unser = new S322_Service() ;
		unser.getStatic() ;
		
	}

}
