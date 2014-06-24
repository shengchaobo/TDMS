package cn.nit.service.table1;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import cn.nit.bean.table1.S15Bean;
import cn.nit.bean.table1.T15Bean;
import cn.nit.dao.table1.S15DAO;
import cn.nit.pojo.table1.S15POJO;

public class S15Service {
	
private S15DAO s15Dao=new S15DAO();
	
		/**輸出數據*/
	public String autidingdata(String year)
	{
		S15Bean s15Bean=new S15Bean();
		if(s15Dao.deleteByYear(year))
		{
			//得到最终统计信息
			S15POJO s15Pojo=this.getStatic();
			//插入数据库
			if(s15Dao.insert(s15Pojo))
			{
				s15Bean=s15Dao.getData();
			}
			
		}
		JSON json=JSONSerializer.toJSON(s15Bean) ;
	    return json.toString();
		
	} 
	
	/**得到统计信息*/
	public S15POJO getStatic()
	{
		List<T15Bean> list=s15Dao.getOriData();
		S15POJO s15Pojo=new S15POJO();
		
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
			T15Bean t15Bean=new T15Bean();
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
			
			HumanResSumNum=HumanProviResNum+HumanNationResNum;
			HumanResSumArea=HumanProviResArea+HumanNationResArea;
			
			SumResNum=NationResNum+NationKeyResNum+NationEnginResNum+OtherNationResNum+ProviResNum
			         +ProviLabNum+OtherProviResNum+HumanResSumNum+CityResNum+TeaUnitResNum+OtherSchResNum;
			SumResArea=NationResArea+NationKeyResArea+NationEnginResArea+OtherNationResArea+ProviResArea
			          +ProviLabArea+OtherProviResArea+HumanResSumArea+CityResArea+TeaUnitResArea+OtherSchResArea;
			
			s15Pojo.setCityResArea(CityResArea);  s15Pojo.setCityResNum(CityResNum);
			s15Pojo.setHumanNationResArea(HumanNationResArea); s15Pojo.setHumanNationResNum(HumanNationResNum);
			s15Pojo.setHumanProviResArea(HumanProviResArea); s15Pojo.setHumanProviResNum(HumanProviResNum);
			s15Pojo.setHumanResSumArea(HumanResSumArea); s15Pojo.setHumanResSumNum(HumanResSumNum);
			s15Pojo.setNationEnginResArea(NationEnginResArea); s15Pojo.setNationEnginResNum(NationEnginResNum);
			s15Pojo.setNationKeyResArea(NationKeyResArea); s15Pojo.setNationKeyResNum(NationKeyResNum);
			s15Pojo.setNationResArea(HumanNationResArea); s15Pojo.setNationResNum(HumanNationResNum);
			s15Pojo.setOtherNationResArea(OtherNationResArea); s15Pojo.setOtherNationResNum(OtherNationResNum);
			s15Pojo.setOtherProviResArea(OtherProviResArea); s15Pojo.setOtherProviResNum(OtherProviResNum);
			s15Pojo.setOtherSchResArea(OtherSchResArea); s15Pojo.setOtherSchResNum(OtherSchResNum);
			s15Pojo.setProviLabArea(ProviLabArea); s15Pojo.setProviLabNum(ProviLabNum);
			s15Pojo.setProviResArea(ProviResArea); s15Pojo.setProviResNum(ProviResNum);
			s15Pojo.setSumResArea(SumResArea); s15Pojo.setSumResNum(SumResNum);
			s15Pojo.setTeaUnitResArea(TeaUnitResArea); s15Pojo.setTeaUnitResNum(TeaUnitResNum);
			s15Pojo.setTime(new Date());
		}
		
		return s15Pojo;
	}
	public static void main(String arg[]){
		S15Service ser=new S15Service();
		String info=ser.autidingdata("2014");
		System.out.println(info);
	}

}
