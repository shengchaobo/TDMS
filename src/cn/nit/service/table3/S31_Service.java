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
		int staNameNum1 = T312_services.getStaNameNum(year,"博士学位授权一级学科点");
		int staNameNum2 = T312_services.getStaNameNum(year,"博士学位授权二级学科点");
		int staNameNum3 = T312_services.getStaNameNum(year,"硕士学位授权一级学科点");
		int staNameNum4 = T312_services.getStaNameNum(year,"硕士学位授权二级学科点");
		int majorNum = T322_services.getMajorNum(year,0);
		int newMajorNum = T322_services.getMajorNum(year,1);
		int majorNum1 = T33_services.getMajorNum(year);
		

		s31_Bean.setPostdocStation(postdocStation);
		s31_Bean.setDocStationOne(staNameNum1);
		s31_Bean.setDocStationTwo(staNameNum2);
		s31_Bean.setMasterStationOne(staNameNum3);
		s31_Bean.setMasterStationTwo(staNameNum4);
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

	}
	
	

	
	

	

	

	
	public static void main(String args[]){
		T311_Service unser = new T311_Service() ;
		unser.auditingData(null, null, 1, 10) ;
	}

}
