package cn.nit.service.table1;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import cn.nit.bean.table1.S15_Bean;
import cn.nit.bean.table1.T15_Bean;
import cn.nit.dao.table1.S15DAO;
import cn.nit.pojo.table1.S15POJO;

public class S15Service {
	
		private S15DAO s15Dao=new S15DAO();
		
		
		/**
		 * 数据显示
		 * */
		public S15_Bean loadData(String year){
			
			boolean flag=false;
			S15_Bean s15bean=new S15_Bean();//用作统计信息
			S15_Bean bean=new S15_Bean();
		//   	String str=null;
		   	int seq=s15Dao.getSeqNumber(year);
		   	//System.out.println("seq:"+seq);
		 // seq!=-1,说明数据库中有这条数据
		   	if(seq!=-1){
		   		
		   		if(s15Dao.countOriDate(year)>0){//被统计表中有数据
			   		 s15bean = this.getStatic(year);//获得统计信息
			   		 s15bean.setSeqNumber(seq);
				     flag = s15Dao.update(s15bean);
				     if(flag){
				    	 bean=s15Dao.loadData(year);
				     }else{
				    	 bean = null;
				     }
		   		}
		   	}
		   	//不存在数据 直接统计
		   	else if(seq == -1){//does not exist the data information
		   		//System.out.println("hello");
		   		//System.out.println(s15Dao.countOriDate(year));
		   		if(s15Dao.countOriDate(year)>0){//有数据 统计
		   			s15bean = this.getStatic(year);
		   			flag = s15Dao.insert(s15bean);
		   		}
		   		if(flag){
		   			bean = s15Dao.loadData(year);
		   		}else{
		   			bean = null;
			   		}
		   	}
		        
			return bean;
		}
	
//		/**輸出數據*/
//	public String autidingdata(String year)
//	{
//		S15Bean s15Bean=new S15Bean();
//		if(s15Dao.deleteByYear(year))
//		{
//			//得到最终统计信息
//			S15Bean s15Pojo=this.getStatic(year);
//			//插入数据库
//			if(s15Dao.insert(s15Pojo))
//			{
//				s15Bean=s15Dao.getData();
//			}
//			
//		}
//		JSON json=JSONSerializer.toJSON(s15Bean) ;
//	    return json.toString();
//		
//	} 
	
	/**得到统计信息*/
	public S15_Bean getStatic(String year)
	{
		List<T15_Bean> list=s15Dao.getOriData(year);
		S15_Bean s15bean=new S15_Bean();
		
		/**总个数*/
	    int SumResNum=0;
		/**总面积*/
		double SumResArea=0.0;
		
		/**1.国家实验室个数*/
		int NationResNum=0;
		double NationResArea=0.0;
		
		/**2.国家重点实验室个数*/
	    int NationKeyResNum=0;
		double NationKeyResArea=0.0;
		
		/**3.国家工程研究中心个数*/
		int NationEnginResNum=0;
		double NationEnginResArea=0.0;
		
		/**4.其他国家级研究中心个数*/
		int OtherNationResNum=0;
		double OtherNationResArea=0.0;
		
		/**5.省、部级设置的研究所个数*/
		int ProviResNum=0;
		double ProviResArea=0.0;
		
		/**6.省、部级设置的实验室个数*/
		int ProviLabNum=0;
		double ProviLabArea=0.0;
		
		/**7.其他省级科研单位个数*/
		int OtherProviResNum=0;
	    double OtherProviResArea=0.0;
		
		/**8.人文重点学科研究基地总个数*/
		int HumanResSumNum=0;
		double HumanResSumArea=0.0;
		
		/**8.1 人文重点学科研究基地（国家级）个数*/
		int HumanNationResNum=0;
		double HumanNationResArea=0.0;
		
		/**8.2人文重点学科研究基地（省部级）个数*/
	    int HumanProviResNum=0;
		double HumanProviResArea=0.0;
		
		/**9.市级科研机构个数*/
		int CityResNum=0;
		double CityResArea=0.0;
		
		/**10.教学单位科研实验室个数*/
		int TeaUnitResNum=0;
		double TeaUnitResArea=0.0;
		
		/**11.其他校级科研实验室个数*/
		int OtherSchResNum=0;
		double OtherSchResArea=0.0;
		
		for(int i=0;i<list.size();i++)
		{
			T15_Bean t15Bean=new T15_Bean();
			t15Bean=list.get(i);
			String type=t15Bean.getType();
			double area=t15Bean.getHouseArea();
			if(type.equals("33000"))
			{
				NationResNum=NationResNum+1;
				NationResArea=NationResArea+area;
			}else if(type.equals("33001"))
			{
				NationKeyResNum=NationKeyResNum+1;
				NationKeyResArea=NationKeyResArea+area;
			}else if(type.equals("33002"))
			{
				NationEnginResNum=NationEnginResNum+1;
				NationEnginResArea+=area;
			}else if(type.equals("33004"))
			{
				OtherNationResNum=OtherNationResNum+1;
				OtherNationResArea+=area;
			}else if(type.equals("33005"))
			{
				ProviResNum+=1;
				ProviResArea+=area;
			}else if(type.equals("33006"))
			{
				ProviLabNum+=1;
				ProviLabArea+=area;
			}else if(type.equals("33008"))
			{
				OtherProviResNum+=1;
				OtherProviResArea+=area;
			}else if(type.equals("33007"))
			{
				HumanProviResNum+=1;
				HumanProviResArea+=area;
			}else if(type.equals("33003"))
			{
				HumanNationResNum+=1;
				HumanNationResArea+=area;
			}else if(type.equals("33009"))
			{
				CityResNum+=1;
				CityResArea+=area;
			}else if(type.equals("33011"))
			{
				TeaUnitResNum+=1;
				TeaUnitResArea+=area;
			}else if(type.equals("33010"))
			{
				OtherSchResNum+=1;
				OtherSchResArea+=area;
			}
		}
			
			HumanResSumNum=HumanProviResNum+HumanNationResNum;
			HumanResSumArea=HumanProviResArea+HumanNationResArea;
			
			SumResNum=NationResNum+NationKeyResNum+NationEnginResNum+OtherNationResNum+ProviResNum
			         +ProviLabNum+OtherProviResNum+HumanProviResNum+HumanNationResNum+CityResNum+TeaUnitResNum+OtherSchResNum;
//			System.out.println();
			SumResArea=NationResArea+NationKeyResArea+NationEnginResArea+OtherNationResArea+ProviResArea
			          +ProviLabArea+OtherProviResArea+HumanProviResArea+HumanNationResArea+CityResArea+TeaUnitResArea+OtherSchResArea;
			
			s15bean.setCityResArea(CityResArea);  s15bean.setCityResNum(CityResNum);
			s15bean.setHumanNationResArea(HumanNationResArea); s15bean.setHumanNationResNum(HumanNationResNum);
			s15bean.setHumanProviResArea(HumanProviResArea); s15bean.setHumanProviResNum(HumanProviResNum);
			s15bean.setHumanResSumArea(HumanResSumArea); s15bean.setHumanResSumNum(HumanResSumNum);
			s15bean.setNationEnginResArea(NationEnginResArea); s15bean.setNationEnginResNum(NationEnginResNum);
			s15bean.setNationKeyResArea(NationKeyResArea); s15bean.setNationKeyResNum(NationKeyResNum);
			s15bean.setNationResArea(NationResArea); s15bean.setNationResNum(NationResNum);
			s15bean.setOtherNationResArea(OtherNationResArea); s15bean.setOtherNationResNum(OtherNationResNum);
			s15bean.setOtherProviResArea(OtherProviResArea); s15bean.setOtherProviResNum(OtherProviResNum);
			s15bean.setOtherSchResArea(OtherSchResArea); s15bean.setOtherSchResNum(OtherSchResNum);
			s15bean.setProviLabArea(ProviLabArea); s15bean.setProviLabNum(ProviLabNum);
			s15bean.setProviResArea(ProviResArea); s15bean.setProviResNum(ProviResNum);
			s15bean.setSumResArea(SumResArea); s15bean.setSumResNum(SumResNum);
			s15bean.setTeaUnitResArea(TeaUnitResArea); s15bean.setTeaUnitResNum(TeaUnitResNum);
			s15bean.setTime(new Date());
		
		
		return s15bean;
	}
	
	/**
	 *Excel數據導出
	 */
	public List<S15_Bean> forExcel(String year){
		return s15Dao.forExcel(year);
	}
		
	
	public static void main(String arg[]){
		S15Service ser=new S15Service();
		S15_Bean bean = ser.loadData("2009");
		if(bean == null){
			System.out.println("无数据");
		}else {
			System.out.println("有数据");
		}
	}

}
