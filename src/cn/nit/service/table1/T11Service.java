package cn.nit.service.table1;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import cn.nit.bean.table1.T11_Bean;
import cn.nit.bean.table1.T152_Bean;
import cn.nit.bean.table5.T54_Bean;
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table1.T11DAO;

import cn.nit.pojo.table1.T11POJO;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T11Service {

	/**  表T1-1的数据库操作类  */
	private T11DAO t11Dao = new T11DAO() ;
	
	/**
	 * 表T1-1的service的插入操作
	 * @param undergraCSBaseTea
	 * @return
	 *
	 * @time: 2014-5-14/上午10:52:05
	 */
	public boolean insert(T11_Bean t11Bean){
		
		return t11Dao.insert(t11Bean) ;
	}
	
	/**
	 *取出数据
	 * */
	public String loadData(String Year){
			
		String str = null;
		int n = t11Dao.countDate(Year);
		if(n>0){
			T11_Bean t11Bean = t11Dao.loadData(Year);
			//T11POJO t11pojo = this.pojoToBean(t11Bean);
			JSON json = JSONSerializer.toJSON(t11Bean) ;
			str = json.toString() ;
		}
		else if(n==0){
			str = null;
		}
		return str;
  }
	/**
//	 *取出数据
//	 * */
//	public String loadData(String Year){
//			
//		String str = null;
//		int n = t11Dao.countDate(Year);
//		if(n>0){
//			T11Bean t11Bean = t11Dao.loadData(Year);
//			T11POJO t11pojo = this.pojoToBean(t11Bean);
////			System.out.println(list.size());
////			T11Bean t11Bean=list.get(0);
//			JSON json = JSONSerializer.toJSON(t11pojo) ;
//			str = json.toString() ;
//		}
//		else if(n==0){
//			str = null;
//		}
//		return str;
//  }
	
//	/**
//	 * 数据录入
//	 * */
//	public T11POJO loadData(String year){
//			
//		String str = null;
//		int n = t11Dao.countDate(year);
//		
//		T11Bean bean = t11Dao.loadData(year) ;
//		T11POJO t11Pojo=new T11POJO();
//		t11Pojo.setAdmissonBatch(bean.getAdmissonBatch());
//		t11Pojo.setMajDept(bean.getMajDept());
//		t11Pojo.setMediaUrl(bean.getMediaUrl());
//		t11Pojo.setNote(bean.getNote());
//		t11Pojo.setPengHuSchAdd(bean.getPengHuSchAdd());
//		t11Pojo.setSch_BeginTime(TimeUtil.changeFormat5(bean.getSch_BeginTime()));
//		t11Pojo.setSchAddress(bean.getSchAddress());
//		t11Pojo.setSchBuilder(bean.getSchBuilder());
//		t11Pojo.setSchEnName(bean.getSchEnName());
//		t11Pojo.setSchFax(bean.getSchFax());
//		t11Pojo.setSchFillerEmail(bean.getSchFillerEmail());
//		t11Pojo.setSchFillerName(bean.getSchFillerName());
//		t11Pojo.setSchFillerTel(bean.getSchFillerTel());
//		t11Pojo.setSchID(bean.getSchID());
//		t11Pojo.setSchName(bean.getSchName());
//		t11Pojo.setSchQuality(bean.getSchQuality());
//		t11Pojo.setSchTel(bean.getSchTel());
//		t11Pojo.setSchType(bean.getSchType());
//		t11Pojo.setSchUrl(bean.getSchUrl());
//		t11Pojo.setYaohuSchAdd(bean.getYaohuSchAdd());
//		t11Pojo.setTime(bean.getTime());
//		t11Pojo.setSeqNumber(bean.getSeqNumber());
////		System.out.println(t11Pojo.);
////		System.out.println("total:"+total);
////		System.out.println("list:"+list.size());
////		JSON json = JSONSerializer.toJSON(bean) ;
//			
////		System.out.println(json.toString()) ;
//			
//		return t11Pojo;
//		}
//	
	
	
	/**
	 * 科研处
	 * */
	public T11POJO auditingData(String year){
			
		List<T11POJO> list = t11Dao.auditingData(year) ;
		T11POJO t11Pojo=list.get(0);
//		System.out.println(t11Pojo.);
//		System.out.println("total:"+total);
//		System.out.println("list:"+list.size());
//		JSON json = JSONSerializer.toJSON(t11Pojo) ;
			
//		System.out.println(json.toString()) ;
			
		return t11Pojo ;
		}
	
	
	//保存
	public Boolean save(T11_Bean bean, String year,	String fields){
		return t11Dao.save(bean,year,fields);
	}
	
	/**
	 * 更新数据
	 * @param undergraCSBaseTea {@link cn.nit.bean.table5.UndergraCSBaseTeaBean}实体类
	 * @return
	 */
	public boolean update(T11_Bean t11Bean){
//	    this.setAudit(t151Bean) ;
		return t11Dao.update(t11Bean) ;
	}
	
//	private void setAudit(T11Bean t11Bean){
//		
//		String audit = DIResourceDAO.getAudit(t11Dao.getTableName()) ;
//		
//		String audits[] = audit.split(",") ;
//		t11Bean.setAudit(audits[0]) ;
//	}
	
	/**按id删除数据*/
	public boolean deleteCoursesByIds(String ids){
		
		return t11Dao.deleteCoursesByIds(ids) ;
	}
	
	public T11_Bean getBean(){
		return t11Dao.getBean();
	}
	/**批量导入*/
	public boolean batchInsert(List<T11_Bean> list){
		return t11Dao.batchInsert(list) ;
	}
	
 public T11POJO pojoToBean(T11_Bean bean){
		 
		 T11POJO t11Pojo=new T11POJO();
			t11Pojo.setAdmissonBatch(bean.getAdmissonBatch());
			t11Pojo.setMajDept(bean.getMajDept());
			t11Pojo.setMediaUrl(bean.getMediaUrl());
			t11Pojo.setNote(bean.getNote());
			t11Pojo.setPengHuSchAdd(bean.getPengHuSchAdd());
			t11Pojo.setSch_BeginTime(bean.getSch_BeginTime());
			t11Pojo.setSchAddress(bean.getSchAddress());
			t11Pojo.setSchBuilder(bean.getSchBuilder());
			t11Pojo.setSchEnName(bean.getSchEnName());
			t11Pojo.setSchFax(bean.getSchFax());
			t11Pojo.setSchFillerEmail(bean.getSchFillerEmail());
			t11Pojo.setSchFillerName(bean.getSchFillerName());
			t11Pojo.setSchFillerTel(bean.getSchFillerTel());
			t11Pojo.setSchID(bean.getSchID());
			t11Pojo.setSchName(bean.getSchName());
			t11Pojo.setSchQuality(bean.getSchQuality());
			t11Pojo.setSchTel(bean.getSchTel());
			t11Pojo.setSchType(bean.getSchType());
			t11Pojo.setSchUrl(bean.getSchUrl());
			t11Pojo.setYaohuSchAdd(bean.getYaohuSchAdd());
			t11Pojo.setTime(bean.getTime());
			t11Pojo.setSeqNumber(bean.getSeqNumber());
		return t11Pojo;
	}
 
 /**
	 *Excel數據導出
	 */
	public T11_Bean getYearInfo(String year){
		return t11Dao.getYearInfo(year);
	}
   
	
	public static void main(String arg[])
	{
//		T11Service ser=new T11Service();
//        String bean=ser.auditingData("2014");
//        System.out.println(bean);
//        if(bean == null){
//        	System.out.println("无数据！");
//        }else{
//        	System.out.println("有");
//        }
	}
	
}
