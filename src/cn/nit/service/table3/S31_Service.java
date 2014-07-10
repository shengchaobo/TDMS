package cn.nit.service.table3;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;




import cn.nit.bean.table3.S31_Bean;
import cn.nit.bean.table3.T311_Bean;
import cn.nit.bean.table3.T312_Bean;
import cn.nit.bean.table3.T322_Bean;
import cn.nit.bean.table3.T33_Bean;
import cn.nit.dao.table3.S31_DAO;

import cn.nit.pojo.table3.S31POJO;
import cn.nit.util.TimeUtil;






public class S31_Service {
	
	/**  表311的数据库操作类  */
	private S31_DAO s31_DAO = new S31_DAO() ;
	private S31_Bean s31_Bean=new S31_Bean();
	private S31POJO s31POJO=new S31POJO();
	

   	String str=null;


	
	public String auditingData(String year){
	
		System.out.println(year);
		System.out.println("一定输出来1");
			s31_DAO.delete(year);
			this.getStatic();
			if(s31_DAO.insert(s31_Bean)){
				List<S31POJO> list = s31_DAO.auditingData(year) ;
				s31POJO=list.get(0);
			}
		JSON json=JSONSerializer.toJSON(s31POJO) ;
		String jsonStr=json.toString();
		return jsonStr;
		}


	public S31_Bean getYearInfo(String Year){
		System.out.println("一定输出来2");
		int num1=0,num2=0,num3 = 0,num4=0,num5=0,num6=0;
		List<T311_Bean> list1=s31_DAO.getOriData(T311_Bean.class, "T311_PostDocSta_Per$", Year);
		num1=list1.size();
		List<T312_Bean> list2=s31_DAO.getOriData(T312_Bean.class, "T312_DocAndGraSta_Gra$",Year);		
		for(int i=0;i<list2.size();i++){
			T312_Bean t312_Bean=new T312_Bean();
			t312_Bean=list2.get(i);
			String StaType= t312_Bean.getStaType();
			if(StaType.equals("硕士点")){
				num3++;
			}else{
				num2++;
			}	
		}
		List<T322_Bean> list3=s31_DAO.getOriData(T322_Bean.class, "T322_UndergraMajorInfo_Tea$",Year);
		num4=list3.size();
		for(int i=0;i<list3.size();i++){
			T322_Bean t322_Bean=new T322_Bean();
			t322_Bean=list3.get(i);
			if(t322_Bean.getIsNewMajor()==true){
				num5++;
			}
			
		}
		List<T33_Bean> list4=s31_DAO.getOriData(T33_Bean.class, "T33_JuniorMajInfo_Tea$", Year);
		num6=list4.size();
		s31_Bean.setPostdocStation(num1);
		s31_Bean.setDocStation(num2);
		s31_Bean.setMasterStation(num3);
		s31_Bean.setSumMajor(num4);
		s31_Bean.setNewMajor(num5);
		s31_Bean.setJuniorMajor(num6);	
	   	s31_Bean.setTime(new Date());
	   	return s31_Bean;
	}
	
	public void getStatic(){
		System.out.println("一定输出来2");
		int num1=0,num2=0,num3 = 0,num4=0,num5=0,num6=0;
		List<T311_Bean> list1=s31_DAO.getOriData(T311_Bean.class, "T311_PostDocSta_Per$");
		num1=list1.size();
		List<T312_Bean> list2=s31_DAO.getOriData(T312_Bean.class, "T312_DocAndGraSta_Gra$");		
		for(int i=0;i<list2.size();i++){
			T312_Bean t312_Bean=new T312_Bean();
			t312_Bean=list2.get(i);
			String StaType= t312_Bean.getStaType();
			if(StaType.equals("硕士点")){
				num3++;
			}else{
				num2++;
			}	
		}
		List<T322_Bean> list3=s31_DAO.getOriData(T322_Bean.class, "T322_UndergraMajorInfo_Tea$");
		num4=list3.size();
		for(int i=0;i<list3.size();i++){
			T322_Bean t322_Bean=new T322_Bean();
			t322_Bean=list3.get(i);
			if(t322_Bean.getIsNewMajor()==true){
				num5++;
			}
			
		}
		List<T33_Bean> list4=s31_DAO.getOriData(T33_Bean.class, "T33_JuniorMajInfo_Tea$");
		num6=list4.size();
		s31_Bean.setPostdocStation(num1);
		s31_Bean.setDocStation(num2);
		s31_Bean.setMasterStation(num3);
		s31_Bean.setSumMajor(num4);
		s31_Bean.setNewMajor(num5);
		s31_Bean.setJuniorMajor(num6);	
	   	s31_Bean.setTime(new Date());
	}
	
	

	

	

	
	public static void main(String args[]){
		T311_Service unser = new T311_Service() ;
		unser.auditingData(null, null, 1, 10) ;
	}

}
