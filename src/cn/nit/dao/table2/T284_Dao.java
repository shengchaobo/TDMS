package cn.nit.dao.table2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.nit.bean.table2.T284_Bean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class T284_Dao {
	
	private String tableName = "T284_FixedAsset_File$" ;
	private String field = "SumFixedAsset,FileAsset,OtherAsset,Time,Note";
	private String keyfield = "SeqNumber";
	
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public T284_Bean getYearInfo(String year){
		
		String sql = "select " + " " + keyfield + "," +
		field + " from " + tableName + " where convert(varchar(4),Time,120)=" + year;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T284_Bean> list = null ;
		T284_Bean bean = null;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T284_Bean.class) ;
			if(list.size() != 0){
				bean = list.get(0);
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
		
		return bean ;
	}
		
	/**
	 * 保存数据
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */
	public boolean save(T284_Bean bean, String year, String fields){
		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year;		
		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection() ;
		
		Statement st = null ;
		ResultSet rs = null ;
		List<T284_Bean> list = null ;
		T284_Bean tempBean = null;
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T284_Bean.class) ;
			if(list.size() != 0){
				tempBean = list.get(0);
				bean.setSeqNumber(tempBean.getSeqNumber());
				String tempfields = fields + ",SumFixedAsset";
				
				double sum = tempBean.getSumFixedAsset();
				if(bean.getFileAsset()!=null){
					if(tempBean.getFileAsset()==null){
						sum = sum + bean.getFileAsset();
					}else{
						sum = sum + (bean.getFileAsset()-tempBean.getFileAsset());
					}
				}
				
				if(bean.getOtherAsset()!=null){
					if(tempBean.getOtherAsset()==null){
						sum = sum + bean.getOtherAsset();
					}else{
						sum = sum + (bean.getOtherAsset()-tempBean.getOtherAsset());
					}
				}				
				
				bean.setSumFixedAsset(sum);
				flag = DAOUtil.update(bean, tableName, keyfield, tempfields, conn) ;
			}else{
				bean.setTime(TimeUtil.changeDateY(year));
				double sum = 0;
				if(bean.getFileAsset()!=null){
					sum = sum + bean.getFileAsset();
				}
							
				if(bean.getOtherAsset()!=null){
					sum = sum + bean.getOtherAsset();
				}	
				
				bean.setSumFixedAsset(sum);
				String tempfields = fields + ",SumFixedAsset,Time";
				flag = DAOUtil.insert(bean, tableName, tempfields, conn) ;
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return false ;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
				
		return flag ;
	}
	
	
	
	public static void main(String args[]){
		//T23_Dao testDao =  new T23_Dao() ;
	}

}
