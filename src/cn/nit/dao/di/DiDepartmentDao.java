package cn.nit.dao.di;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;


import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;

public class DiDepartmentDao {
	
	private String tableName = "DiDepartment" ;
	
	private String key = "UnitId";
	/**除了住建的其他字段*/
	private String field1 = "UnitName,Class1,Class2,Functions,Leader,TeaId,Note";
	private String field = "UnitId,UnitName,Class1,Class2,Functions,Leader,TeaId,Note" ;
	
	/**
	 * 获取DiDepartment字典表的所有数�?
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<DiDepartmentBean> getList(){
		
		String sql = "select " + field + " from " + tableName ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<DiDepartmentBean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, DiDepartmentBean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(rs) ;
			DBConnection.close(st) ;
			DBConnection.close(conn) ;
		}
		
		return list ;
	}
	
	/**

	 * 获取科研室部门名称和id
	 * */
		public List<DiDepartmentBean> getListSci(){
				
				List<DiDepartmentBean> list = null ;
				StringBuffer sql = new StringBuffer() ;
				sql.append("select " + field + " from " + tableName) ;
				sql.append(" where UnitID like '20%'");
				Connection conn = DBConnection.instance.getConnection() ;
				Statement st = null ;
				ResultSet rs = null ;
				
				try{
					st = conn.createStatement() ;
					rs = st.executeQuery(sql.toString()) ;
					list = DAOUtil.getList(rs, DiDepartmentBean.class) ;
				}catch(Exception e){
					e.printStackTrace() ;
					return list ;
				}finally{
					DBConnection.close(st);	
					DBConnection.close(conn);
				}
				return list ;
			}
		
		/**

		 * 获取学院名称和id
		 * */
			public List<DiDepartmentBean> getListAca(){
					
					List<DiDepartmentBean> list = null ;
					StringBuffer sql = new StringBuffer() ;
					sql.append("select " + field + " from " + tableName) ;
					sql.append(" where UnitID like '30%'");
					Connection conn = DBConnection.instance.getConnection() ;
					Statement st = null ;
					ResultSet rs = null ;
					
					try{
						st = conn.createStatement() ;
						rs = st.executeQuery(sql.toString()) ;
						list = DAOUtil.getList(rs, DiDepartmentBean.class) ;
					}catch(Exception e){
						e.printStackTrace() ;
						return list ;
					}finally{
						DBConnection.close(st);	
						DBConnection.close(conn);
					}
					return list ;
				}
		
		
		/**
		 * 加载所有部门
		 * @param conditions
		 * @param page
		 * @param rows
		 * @return
		 */
		public List<DiDepartmentBean> loadDes(String conditions, int page, int rows){
			List<DiDepartmentBean> list = null ;
			Connection conn = DBConnection.instance.getConnection() ;
			Statement st = null ;
			ResultSet rs = null ;
			StringBuffer sql = new StringBuffer() ;
			sql.append("select " + field) ;
			sql.append(" from " + tableName) ;
			sql.append(" where 1=1" ) ;
			
			System.out.println(sql.toString());
			if(conditions != null){
				sql.append(conditions) ;
			}
			
			try{
				st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY) ;
				st.setMaxRows(page * rows) ;
				rs = st.executeQuery(sql.toString()) ;
				rs.absolute((page - 1) * rows) ;
				list = DAOUtil.getList(rs, DiDepartmentBean.class) ;
			}catch(Exception e){
				e.printStackTrace() ;
				return null ;
			}finally{
				DBConnection.close(st) ;
				DBConnection.close(conn) ;
			}
			
			return list ;
		}
		
		/**
		 * 获取部门的总记录数
		 * @param conditions
		 * @return
		 */
		public int totalDes(String conditions){
			
			StringBuffer sql = new StringBuffer() ;
			sql.append("select count(*)") ;
			sql.append(" from " + tableName) ;
			sql.append(" where 1=1") ;
			int total = 0 ;
			
			if(conditions != null && !conditions.equals("")){
				sql.append(conditions) ;
			}
			
			Connection conn = DBConnection.instance.getConnection() ;
			Statement st = null ;
			ResultSet rs = null ;
			
			try{
				st = conn.createStatement() ;
				rs = st.executeQuery(sql.toString()) ;
				
				if(rs == null){
					return total ;
				}
				
				while(rs.next()){
					total = rs.getInt(1) ;
				}
			}catch(Exception e){
				e.printStackTrace() ;
				return 0 ;
			}finally{
				DBConnection.close(rs) ;
				DBConnection.close(st) ;
				DBConnection.close(conn) ;
			}
			return total ;
		}
		
		/**
		 * 更新部门信息
		 * @param userinfo
		 * @return
		 */
		public boolean update(DiDepartmentBean deBean){
			
			Connection conn = DBConnection.instance.getConnection() ;
			boolean flag = false ;
			String field = "UnitName,Class1,Class2,Functions,Leader,TeaID,Note" ;
			
			try{
				flag = DAOUtil.update(deBean, tableName, key, field1, conn) ;
			}catch(Exception e){
				e.printStackTrace() ;
				 return false ;
			}
			
			return flag ;
		}
		
		/**
		 * 根据id删除部门
		 * @param ids
		 * @return
		 */
		public boolean deleteByIds(String ids){
			
			
			StringBuffer sql = new StringBuffer() ;
			sql.append("delete from " + tableName) ;
			sql.append(" where UnitID  in " + ids) ;
			int flag = 0 ;
			Connection conn = DBConnection.instance.getConnection() ;
			Statement st = null ;
			
			try{
				st = conn.createStatement() ;
				flag = st.executeUpdate(sql.toString()) ;
			}catch(Exception e){
				e.printStackTrace() ;
				return false ;
			}finally{
				DBConnection.close(st) ;
				DBConnection.close(conn) ;
			}
			
			if(flag > 0){
				return true ;
			}else{
				return false ;
			}
			
		}
		
		/**
		 * 判断中是否已包含该部门
		 */
		public boolean hasDe(String deID){
			String sql = "select * from " + tableName + " where UnitID='"+ deID	+ "'";
			Connection conn = DBConnection.instance.getConnection();
			Statement st = null;
			ResultSet rs = null;
			boolean flag = false;
			try{
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				
				while(rs.next()){
					flag = true;
				}
			}catch(Exception e){
				e.printStackTrace() ;
				return false ;
			}finally{
				DBConnection.close(rs);
				DBConnection.close(st);	
				DBConnection.close(conn);
			}
			return flag;
		}
	
		/**
		 * 添加部门
		 * @param userinfo
		 * @return
		 */
		public boolean insert(DiDepartmentBean deBean){
			Connection conn = DBConnection.instance.getConnection() ;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
			boolean flag = false ;
			try{
				flag = DAOUtil.insert(deBean, tableName, field, conn) ;
			}catch(Exception e){
				e.printStackTrace() ;
				return flag ;
			}
			
			return flag ;
		}
		
		
		/**
		 * 根据部门ID获得部门名字
		 * @param conditions
		 * @return
		 */
		public String getName(String unitID){
			
			StringBuffer sql = new StringBuffer() ;
			sql.append("select UnitName") ;
			sql.append(" from " + tableName) ;
			sql.append(" where UnitID='" + unitID +"';") ;
			
			String unitName = null;
			Connection conn = DBConnection.instance.getConnection() ;
			Statement st = null ;
			ResultSet rs = null ;
			
			try{
				st = conn.createStatement() ;
				rs = st.executeQuery(sql.toString()) ;
				
				while(rs.next()){
					unitName = rs.getString(1) ;
				}
			}catch(Exception e){
				e.printStackTrace() ;
			}finally{
				DBConnection.close(rs) ;
				DBConnection.close(st) ;
				DBConnection.close(conn) ;
			}
			
			return unitName;
		}
		
		


}
