package cn.nit.service.table1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import cn.nit.bean.table1.T11Bean;
import cn.nit.bean.table1.T16Bean;
import cn.nit.dao.di.DIResourceDAO;
import cn.nit.dao.table1.T16DAO;

import cn.nit.pojo.table1.T16POJO;
import cn.nit.util.Pagition;
import cn.nit.util.TimeUtil;

public class T16Service {
	
	/**  表T1-6的数据库操作类  */
	private T16DAO t16Dao = new T16DAO() ;
	
	/**
	 * 表T1-6的service的插入操作
	 * @param undergraCSBaseTea
	 * @return
	 *
	 * @time: 2014-5-14/上午10:52:05
	 */
	public boolean insert(T16Bean t16Bean){
		
		return t16Dao.insert(t16Bean) ;
	}
	
	/**
	 *取出数据
	 * */
	public String auditingData(String Year){
			
		String str = null;
		int n = t16Dao.countDate(Year);
		if(n>0){
			List<T16POJO> list = t16Dao.auditingData1(Year);
			T16POJO t16Pojo=list.get(0);
			JSON json = JSONSerializer.toJSON(t16Pojo) ;
			str = json.toString() ;
		}
		else if(n==0){
			str = null;
		}
		return str;
  }
	
	/**编辑保存*/
	public Boolean save(String data, String year,	String fields){
		System.out.println("++++++");
		T16Bean bean1 = new T16Bean();
		T16Bean bean2 = new T16Bean();
		boolean flag = false;
		String field[] = data.split("a");

		
		
		for(String s : field){
			String fieldName = s;
			String mapVal[] = s.split("%");
			if(mapVal[0].equals("contents1")){
				    String Item = "1.校训";
				    if(mapVal.length<2){//判断长度，长度唯一，说明content为空，则删除这条
				    	flag = t16Dao.deleteByItem(year, Item);
				    }else{//r若content不为空，则保存数据
				    	bean1.setContents(mapVal[1]);
						bean1.setItem(Item);
						bean1.setTime(TimeUtil.changeDateY(year));
						String dataF = "Contents";
						flag = t16Dao.save(bean1, year, dataF, Item);
				    }
					
			}else if(mapVal[0].equals("contents2")){
				String Item = "2.定位与发展目标";
				if(mapVal.length<2){
					flag = t16Dao.deleteByItem(year, Item);
				}else{
					bean2.setContents(mapVal[1]);
//					System.out.println("2mapVal[1]:"+mapVal[1]);
					bean2.setItem(Item);
					bean2.setTime(TimeUtil.changeDateY(year));
					String dataF = "Contents";
					flag = t16Dao.save(bean2, year, dataF, Item);
				}
			}
		}
		return flag;
	}
	
	public List<T16POJO> forExcel(String year){
		List<T16POJO> list = t16Dao.forExcel(year);
		return list;
	}
	
	/**
	 * 更新数据
	 * @param undergraCSBaseTea {@link cn.nit.bean.table5.UndergraCSBaseTeaBean}实体类
	 * @return
	 */
	public boolean update(T16Bean t16Bean){
//	    this.setAudit(t16Bean) ;
		return t16Dao.update(t16Bean) ;
	}
	
	private void setAudit(T16Bean t16Bean){
		
		String audit = DIResourceDAO.getAudit(t16Dao.getTableName()) ;
		
		String audits[] = audit.split(",") ;
	}
	
	/**按年份删除数据*/
	public boolean deleteByYear(String year){
		
		return t16Dao.deleteByYear(year) ;
	}
	
	/**
	 * 生成查条件
	 * @param seqNum
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public String gernateAuditingConditions(int seqNum, Date startTime, Date endTime){
		
		if(seqNum == 0 && startTime == null && endTime == null){
			return null ;
		}
		
		StringBuffer sql = new StringBuffer() ;
		
		if(seqNum != 0){
			sql.append(" and SeqNumber=" + seqNum) ;
		}
		
		if(startTime != null){
			sql.append(" and cast(CONVERT(DATE, Time)as datetime)>=cast(CONVERT(DATE, '" 
					+ TimeUtil.changeFormat4(startTime) + "')as datetime)") ;
		}
		
		if(endTime != null){
			sql.append(" and cast(CONVERT(DATE, Time)as datetime)>=cast(CONVERT(DATE, '" 
					+ TimeUtil.changeFormat4(endTime) + "')as datetime)") ;
		}
		
		return sql.toString() ;
	}
	
	
  public static void main(String arg[])
  {
	  T16Service ser = new T16Service();
	  String str= "contents1%特色团acontents2%ytest2";
	  boolean flag = false;
	  String field="contents1acontents2";
	  flag = ser.save(str, "2014", field);
	  if(flag){
		  System.out.println("插入成功！");
	  }else{
		  System.out.println("fails");
	  }
		
		
  }

}
