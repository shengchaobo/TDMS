package cn.nit.service.table3;

import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import cn.nit.bean.table3.T322_Bean;
import cn.nit.dao.table3.T322_DAO;
import cn.nit.pojo.table3.T322POJO;
import cn.nit.util.Pagition;

public class T322_Service {
	
	/**  表311的数据库操作类  */
	private T322_DAO t322_Dao = new T322_DAO() ;
	
	/**
	 * 表511的service的插入操作
	 * @param undergraCSBaseTea
	 * @return
	 *
	 * @time: 2014-5-14/上午10:52:05
	 */
	public boolean insert(T322_Bean t322_Bean){
		
		return t322_Dao.insert(t322_Bean) ;
	}
	
	public boolean batchInsert(List<T322_Bean> list){
		
		return t322_Dao.batchInsert(list) ;
	}
	
	public String auditingData(String conditions, String fillDept, int page, int rows){
		
	    int total = t322_Dao.totalAuditingData(conditions, fillDept) ;
		List<T322POJO> list = t322_Dao.auditingData(conditions, fillDept, page, rows) ;
	
		System.out.println("怎么样");
		Pagition pages = new Pagition(total, list) ;
		System.out.println("total:"+total);

	
		JSON json = JSONSerializer.toJSON(pages) ;

		System.out.println(json.toString());
		return json.toString() ;
		}
	
	
	/**
	 * 更新数据
	 * @param undergraCSBaseTea {@link cn.nit.bean.table5.UndergraCSBaseTeaBean}实体类
	 * @return
	 */
	public boolean update(T322_Bean t322_Bean){

		return t322_Dao.update(t322_Bean) ;
	}
	
	public boolean deleteCoursesByIds(String ids){
		
		return t322_Dao.deleteCoursesByIds(ids) ;
	}
	
	public int getMajorNum(String year,int flag){
		return t322_Dao.getMajorNum(year,flag);
	}
	
	/**
	 * 得到该条数据审核状态
	 * @param 
	 * @return
	 */
	public int getCheckState(int seqNumber){
		return t322_Dao.getCheckState(seqNumber) ;
	}
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(int seqNum, int checkState){
		return t322_Dao.updateCheck(seqNum,checkState) ;
	}
	
	
	/**
	 * 全部审核通过
	 * @param 
	 * @return
	 */
	public boolean checkAll(){
		return t322_Dao.checkAll() ;
	}

	
	/**
	 * 在招专业总数
	 * @param year
	 * @return
	 */
	public int getTotalFieldNum(String year){
		return t322_Dao.getTotalFieldNum(year);
	}
	
	
	/**
	 * 集中实践环节教学总学分数
	 * @param year
	 * @return
	 */
	public int getSumPraCredit(String year){
		return t322_Dao.getSumPraCredit(year);
	}
	
	/**
	 * 实验教学总学分数
	 * @param year
	 * @return
	 */
	public int getSumExpCredit(String year){
		return t322_Dao.getSumExpCredit(year);
	}
	
	/**
	 * 选修课学总学分数
	 * @param year
	 * @return
	 */
	public int getSumOptionCredit(String year){
		return t322_Dao.getSumOptionCredit(year);
	}
	
	/**
	 * 课外科技活动总学分数
	 * @param year
	 * @return
	 */
	public int getSumOutClassCredit(String year){
		return t322_Dao.getSumOutClassCredit(year);
	}

	
	/**
	 * 总学分数
	 * @param year
	 * @return
	 */
	public int getSumTotalCredit(String year){
		return t322_Dao.getSumTotalCredit(year);
	}
	
	/**用于审核数据导出*/
	public List<T322POJO> totalList( String fillUnitID, String year, int checkState){
		return t322_Dao.totalList(fillUnitID, year, checkState);
	}

	
	public static void main(String args[]){
		T311_Service unser = new T311_Service() ;
		unser.auditingData(null, null, 1, 10) ;
	}

}
