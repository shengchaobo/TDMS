package cn.nit.service.table5;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import cn.nit.bean.table5.T513Bean;
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table5.T513_DAO;

import cn.nit.pojo.table5.T513POJO;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T513Service {
	
	/**  表T513的数据库操作类  */
	private T513_DAO t513Dao = new T513_DAO() ;
	
	
	public List<T513POJO> getYearInfo(String selectYear){
		List<T513POJO> oriList = t513Dao.getYearInfo(selectYear);
		System.out.println("oriLisr："+oriList.size());
		List<T513POJO> list = this.toRealData(oriList);
		return list;
	}
	
	/**将最初中的list重新排序*/
	public List<T513POJO> toRealData(List<T513POJO> oriList){
		List<T513POJO> list = new ArrayList<T513POJO>();
		for(T513POJO pojo:oriList){
			if(pojo.getItemID().equals("54000")){
				if(pojo.getCategory()!=null){
					if(pojo.getCategory().equals("理论课")){
						list.add(0, pojo);
					}else if(pojo.getCategory().equals("实践教学")){
						list.add(1, pojo);
					}
				}
				
			}else if(pojo.getItemID().equals("54001")){
				if(pojo.getCategory()!=null){
					if(pojo.getCategory().equals("理论课")){
						list.add(2, pojo);
					}else if(pojo.getCategory().equals("实践教学")){
						list.add(3, pojo);
					}
				}
			}else if(pojo.getItemID().equals("54002")){
				if(pojo.getCategory()!=null){
					if(pojo.getCategory().equals("理论课")){
						list.add(4, pojo);
					}else if(pojo.getCategory().equals("实践教学")){
						list.add(5, pojo);
					}
				}
			}else if (pojo.getItemID().equals("54003")){
				if(pojo.getCategory()!=null){
					if(pojo.getCategory().equals("理论课")){
						list.add(6, pojo);
					}else if(pojo.getCategory().equals("实践教学")){
						list.add(7, pojo);
					}
				}
			}
		}
		return list;
	}
	
	/**
	 * 更新数据
	 * @param 
	 * @return
	 */
	public boolean update(T513Bean bean){
		
//		String CoverRatio=bean.getCoverRatio();
		
		return t513Dao.update(bean) ;
	}
	
	/**
	 * 根据seqNumber找相应bean
	 * @param 
	 * @return
	 */
	public T513Bean findBySeqNum (int seqNum){
		return t513Dao.findBySeqNum(seqNum) ;
	}
	
	/**将带%的字符串转换成double类型*/
	public double toDouble(String str){
		String dou=str.substring(0,str.length()-1);
	    double num1=Double.parseDouble(dou);
	    double num2=num1/100;
	    double num =(double)Math.round(num1)/100;
		return num;
	}
	
	
	

	/**批量导入*/
	public boolean batchInsert(List<T513Bean> list,String year){
		
		return t513Dao.batchInsert(list,year) ;
	}
	public static void main(String arg[]){
		T513Service ser = new T513Service();
		List<T513POJO> list = ser.getYearInfo("2014");
		System.out.println(list.size());
	}


}
