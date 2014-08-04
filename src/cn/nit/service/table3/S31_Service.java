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
   	boolean be=false;



	public S31_Bean getYearInfo(String year){
		boolean flag=false;
		System.out.println("一定输出来2");
		
		T311_Service T311_services = new T311_Service();
		T312_Service T312_services = new T312_Service();
		T322_Service T322_services = new T322_Service();
		T33_Service T33_services = new T33_Service();
		
		
		int postdocStation = T311_services.getStationNum(year);
		int staNameNum1 = T312_services.getStaNameNum(year,"硕士点");
		int staNameNum2 = T312_services.getStaNameNum(year,"博士点");
		int majorNum = T322_services.getMajorNum(year,0);
		int newMajorNum = T322_services.getMajorNum(year,1);
		int majorNum1 = T33_services.getMajorNum(year);
		

		s31_Bean.setPostdocStation(postdocStation);
		s31_Bean.setDocStation(staNameNum1);
		s31_Bean.setMasterStation(staNameNum2);
		s31_Bean.setSumMajor(majorNum);
		s31_Bean.setNewMajor(newMajorNum);
		s31_Bean.setJuniorMajor(majorNum1);
		
		flag=s31_DAO.update(year, s31_Bean);
		if(flag){
			System.out.println("成功");
		}else{
			System.out.println("失败");
		}

		return s31_Bean;


		List<T33_Bean> list4=s31_DAO.getOriData(T33_Bean.class, "T33_JuniorMajInfo_Tea$", Year);
		num6=list4.size();
		s31_Bean.setPostdocStation(num1);
		s31_Bean.setDocStation(num2);
		s31_Bean.setMasterStation(num3);
		s31_Bean.setSumMajor(num4);
		s31_Bean.setNewMajor(num5);
		s31_Bean.setJuniorMajor(num6);	
	   	s31_Bean.setTime(new Date());
	   	s31_DAO.delete(Year);
	   	s31_DAO.insert(s31_Bean);
	   	return s31_Bean;
	}
	
	

	
	

	

	

	
	public static void main(String args[]){
		T311_Service unser = new T311_Service() ;
		unser.auditingData(null, null, 1, 10) ;
	}

}
