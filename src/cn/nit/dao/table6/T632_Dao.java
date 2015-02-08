package cn.nit.dao.table6;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import cn.nit.bean.table5.S512_Bean;
import cn.nit.bean.table6.T632_Bean;
import cn.nit.constants.Constants;
import cn.nit.dbconnection.DBConnection;
import cn.nit.util.DAOUtil;
import cn.nit.util.TimeUtil;

public class T632_Dao {

	/** 数据库表名 */
	private String tableName = "T632_GraStuEmployInfo_Admission$";

	/** 数据自增长字段的主键，必须为自增长字段 */
	private String key = "SeqNumber";

	/** 数据库表中除了自增长字段的所有字段 */

	
	private String field = "TeaUnit,UnitId,MajorName,MajorId,SumEmployNum,govermentNum,PubInstiNum,EnterpriseNum,ForceNum,FlexibleEmploy,GoOnHighStudy,NationItemEmploy,OtherEmploy," +
			"SumGoOnHighStudyNum,RecommendGraNum,ExamGraApplyNum,ExamGraEnrollNum,ExamGraInSch,ExamGraOutSch,AbroadNum,Time,Note,CheckState";

	
	private String fieldShow = "SeqNumber,TeaUnit,UnitId,MajorName,MajorId,SumEmployNum,govermentNum,PubInstiNum,EnterpriseNum,ForceNum,FlexibleEmploy,GoOnHighStudy,NationItemEmploy,OtherEmploy," +
			"SumGoOnHighStudyNum,RecommendGraNum,ExamGraApplyNum,ExamGraEnrollNum,ExamGraInSch,ExamGraOutSch,AbroadNum,Time,Note,CheckState";

	/* ,FillTeaID,FillUnitID,audit */
	
	
	  /**
	 * 获得当年数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T632_Bean> getYearInfo(String year){
		
		String sql = "select " + key+ "," +field + " from " + tableName 
				+ " where convert(varchar(4),Time,120)=" + year;		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T632_Bean> list = null ;
		//System.out.println(sql);
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T632_Bean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
		
		return list ;
	}
	
	
	/**
	 * 插入数据
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */
	public boolean insert(T632_Bean bean, String year){
		
		String sql = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + 
		year + " and TeaUnit=" + "'全校合计'" + ";";		
		boolean flag = false;
		boolean flag1;
		boolean flag2;
		Connection conn = DBConnection.instance.getConnection() ;		
		Statement st = null ;
		ResultSet rs = null ;
		List<T632_Bean> templist = null ;
		System.out.println(sql);
		String tempfield = "SumEmployNum,govermentNum,PubInstiNum,EnterpriseNum,ForceNum,FlexibleEmploy,GoOnHighStudy,NationItemEmploy,OtherEmploy," +
		"SumGoOnHighStudyNum,RecommendGraNum,ExamGraApplyNum,ExamGraEnrollNum,ExamGraInSch,ExamGraOutSch,AbroadNum";
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			//没有合计 即没有数据
			templist = DAOUtil.getList(rs, T632_Bean.class) ;
			if(templist.size() == 0){
				bean.setTime(TimeUtil.changeDateY(year));
				T632_Bean total_bean = new T632_Bean();			
				total_bean.setTeaUnit("全校合计");
				total_bean.setUnitId("");
				total_bean.setMajorId("");
				total_bean.setMajorName("");
				total_bean.setSumEmployNum(bean.getSumEmployNum());
				total_bean.setGovermentNum(bean.getGovermentNum());
				total_bean.setPubInstiNum(bean.getPubInstiNum());
				total_bean.setEnterpriseNum(bean.getEnterpriseNum());
				
				total_bean.setForceNum(bean.getForceNum());
				total_bean.setFlexibleEmploy(bean.getFlexibleEmploy());
				total_bean.setGoOnHighStudy(bean.getGoOnHighStudy());
				total_bean.setNationItemEmploy(bean.getNationItemEmploy());
				
				total_bean.setOtherEmploy(bean.getOtherEmploy());
				total_bean.setSumGoOnHighStudyNum(bean.getSumGoOnHighStudyNum());
				total_bean.setRecommendGraNum(bean.getRecommendGraNum());
				total_bean.setExamGraApplyNum(bean.getExamGraApplyNum());
				
				total_bean.setExamGraEnrollNum(bean.getExamGraEnrollNum());
				total_bean.setExamGraInSch(bean.getExamGraInSch());
				total_bean.setExamGraOutSch(bean.getExamGraOutSch());
				total_bean.setAbroadNum(bean.getAbroadNum());
				
				
				total_bean.setTime(TimeUtil.changeDateY(year));
				total_bean.setNote("");
				total_bean.setCheckState(Constants.WAIT_CHECK);
				flag1 = DAOUtil.insert(total_bean, tableName, field, conn);
				//重新打开数据库连接
				Connection conn1 = DBConnection.instance.getConnection() ;	
				flag2 = DAOUtil.insert(bean, tableName, field, conn1);				
			}else{
				bean.setTime(TimeUtil.changeDateY(year));
				T632_Bean total_bean = templist.get(0);
				total_bean.setSumEmployNum(total_bean.getSumEmployNum()+bean.getSumEmployNum());
				total_bean.setGovermentNum(total_bean.getGovermentNum()+bean.getGovermentNum());
				total_bean.setPubInstiNum(total_bean.getPubInstiNum()+bean.getPubInstiNum());
				total_bean.setEnterpriseNum(total_bean.getEnterpriseNum()+bean.getEnterpriseNum());
				
				total_bean.setForceNum(total_bean.getForceNum()+bean.getForceNum());
				total_bean.setFlexibleEmploy(total_bean.getFlexibleEmploy()+bean.getFlexibleEmploy());
				total_bean.setGoOnHighStudy(total_bean.getGoOnHighStudy()+bean.getGoOnHighStudy());
				total_bean.setNationItemEmploy(total_bean.getNationItemEmploy()+bean.getNationItemEmploy());
				
				total_bean.setOtherEmploy(total_bean.getOtherEmploy()+bean.getOtherEmploy());
				total_bean.setSumGoOnHighStudyNum(total_bean.getSumGoOnHighStudyNum()+bean.getSumGoOnHighStudyNum());
				total_bean.setRecommendGraNum(total_bean.getRecommendGraNum()+bean.getRecommendGraNum());
				total_bean.setExamGraApplyNum(total_bean.getExamGraApplyNum()+bean.getExamGraApplyNum());
				
				total_bean.setExamGraEnrollNum(total_bean.getExamGraEnrollNum()+bean.getExamGraEnrollNum());
				total_bean.setExamGraInSch(total_bean.getExamGraInSch()+bean.getExamGraInSch());
				total_bean.setExamGraOutSch(total_bean.getExamGraOutSch()+bean.getExamGraOutSch());
				total_bean.setAbroadNum(total_bean.getAbroadNum()+bean.getAbroadNum());
				
				flag1 = DAOUtil.insert(bean, tableName, field, conn);
				//重新打开数据库连接
				Connection conn1 = DBConnection.instance.getConnection() ;	
				flag2 = DAOUtil.update(total_bean, tableName, key, tempfield, conn1);
			}
		}catch(Exception e){
			e.printStackTrace() ;
			return false ;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
		
		if(flag1==true&&flag2==true){
			flag = true;
		}
 		return flag ;
		
	}
	
	
	
//
//	/**
//	 * 将数据表624的实体类插入数据库
//	 * 
//	 * @param UndergraAdmiInfo
//	 * @return
//	 * 
//	 * @time: 2014-6-12
//	 */
//	public boolean insert(T632_Bean GraStuEmployInfo) {
//
//		// flag判断数据是否插入成功
//		boolean flag = false;
//		Connection conn = DBConnection.instance.getConnection();
//		try {
//			flag = DAOUtil.insert(GraStuEmployInfo, tableName, field, conn);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return flag;
//		} finally {
//			DBConnection.close(conn);
//		}
//		return flag;
//	}

	
	/**
	 * 删除数据
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */	
	public boolean deleteByIds(String ids, String year) {

		int flag = 0;
		boolean flag1 = false ;
		StringBuffer sql = new StringBuffer();
		sql.append("delete from " + tableName);
		sql.append(" where " + key + " in " + ids);
		//JuniorStuSumNum,JuniorOneStuNum,JuniorTwoStuNum,JuniorThreeStuNum
		
		String sql0 = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year + " and TeaUnit=" + "'全校合计'" + ";";		
		String sql1 = "select sum(sumEmployNum) AS SumsumEmployNum, " +
				" sum(govermentNum) AS SumgovermentNum,sum(pubInstiNum) AS SumpubInstiNum," +
				"sum(enterpriseNum) AS SumenterpriseNum,sum(forceNum) as sumforceNum,sum(flexibleEmploy) as sumflexibleEmploy," +
				"sum (goOnHighStudy) as sumgoOnHighStudy, sum(nationItemEmploy) as sumnationItemEmploy, sum(otherEmploy) as sumotherEmploy," +
				"sum (sumGoOnHighStudyNum) as sumsumGoOnHighStudyNum, sum(recommendGraNum) as sumrecommendGraNum, sum (examGraApplyNum) as sumexamGraApplyNum," +
				"sum (examGraEnrollNum) as sumexamGraEnrollNum,sum(examGraInSch) as sumexamGraInSch, sum(examGraOutSch) as sumexamGraOutSch,sum(abroadNum) as sumabroadNum" +
				" from " + tableName + " where " + key + " in " + ids;
		
		Connection conn = DBConnection.instance.getConnection();
		Statement st = null ;
		ResultSet rs0 = null ;
		ResultSet rs1 = null ;
		List<T632_Bean> templist = null ;
		
		try {
			st = conn.createStatement();
			rs0 = st.executeQuery(sql0);
			templist = DAOUtil.getList(rs0, T632_Bean.class) ;
			T632_Bean total_bean = templist.get(0);
//			System.out.println(total_bean.getTeaUnit());
//			System.out.println(total_bean.getJuniorStuSumNum());
//			System.out.println("+++");
			
			rs1 = st.executeQuery(sql1);
			while(rs1.next()) {
				
				int SumsumEmployNum = rs1.getInt("SumsumEmployNum");
				total_bean.setSumEmployNum(total_bean.getSumEmployNum()-SumsumEmployNum);
				int SumgovermentNum = rs1.getInt("SumgovermentNum");
				total_bean.setGovermentNum(total_bean.getGovermentNum()-SumgovermentNum);
				int SumpubInstiNum = rs1.getInt("SumpubInstiNum");
				total_bean.setPubInstiNum(total_bean.getPubInstiNum()-SumpubInstiNum);
				int SumenterpriseNum = rs1.getInt("SumenterpriseNum");
				total_bean.setEnterpriseNum(total_bean.getEnterpriseNum()-SumenterpriseNum);
				
				int sumforceNum = rs1.getInt("sumforceNum");
				total_bean.setForceNum(total_bean.getForceNum()-sumforceNum);
				int sumflexibleEmploy = rs1.getInt("sumflexibleEmploy");
				total_bean.setFlexibleEmploy(total_bean.getFlexibleEmploy()-sumflexibleEmploy);
				int sumgoOnHighStudy = rs1.getInt("sumgoOnHighStudy");
				total_bean.setGoOnHighStudy(total_bean.getGoOnHighStudy()-sumgoOnHighStudy);
				int sumnationItemEmploy = rs1.getInt("sumnationItemEmploy");
				total_bean.setNationItemEmploy(total_bean.getNationItemEmploy()-sumnationItemEmploy);
				
				int sumotherEmploy = rs1.getInt("sumotherEmploy");
				total_bean.setOtherEmploy(total_bean.getOtherEmploy()-sumotherEmploy);
				int sumsumGoOnHighStudyNum = rs1.getInt("sumsumGoOnHighStudyNum");
				total_bean.setSumGoOnHighStudyNum(total_bean.getSumGoOnHighStudyNum()-sumsumGoOnHighStudyNum);
				int sumrecommendGraNum = rs1.getInt("sumrecommendGraNum");
				total_bean.setRecommendGraNum(total_bean.getRecommendGraNum()-sumrecommendGraNum);
				int sumexamGraApplyNum = rs1.getInt("sumexamGraApplyNum");
				total_bean.setExamGraApplyNum(total_bean.getExamGraApplyNum()-sumexamGraApplyNum);
				
				int sumexamGraEnrollNum = rs1.getInt("sumexamGraEnrollNum");
				total_bean.setExamGraEnrollNum(total_bean.getExamGraEnrollNum()-sumexamGraEnrollNum);
				int sumexamGraInSch = rs1.getInt("sumexamGraInSch");
				total_bean.setExamGraInSch(total_bean.getExamGraInSch()-sumexamGraInSch);
				int sumexamGraOutSch = rs1.getInt("sumexamGraOutSch");
				total_bean.setExamGraOutSch(total_bean.getExamGraOutSch()-sumexamGraOutSch);
				int sumabroadNum = rs1.getInt("sumabroadNum");
				total_bean.setAbroadNum(total_bean.getAbroadNum()-sumabroadNum);
			}
			
			if(total_bean.getSumEmployNum()==0&&total_bean.getGovermentNum()==0&&total_bean.getPubInstiNum()==0&&total_bean.getEnterpriseNum()==0
					&&total_bean.getForceNum()==0&&total_bean.getFlexibleEmploy()==0&&total_bean.getGoOnHighStudy()==0&&total_bean.getNationItemEmploy()==0
					&&total_bean.getOtherEmploy()==0&&total_bean.getSumGoOnHighStudyNum()==0&&total_bean.getRecommendGraNum()==0&&total_bean.getExamGraApplyNum()==0
					&&total_bean.getExamGraEnrollNum()==0&&total_bean.getExamGraInSch()==0&&total_bean.getExamGraOutSch()==0&&total_bean.getAbroadNum()==0){
				String sql2 = "delete from " + tableName + " where SeqNumber=" + total_bean.getSeqNumber();		
				int flag3 = st.executeUpdate(sql2);
				if(flag3 > 0){
					flag1 = true;
				}else{
					flag1 = false;
				}
			}else{
				if(total_bean.getCheckState() == Constants.NOPASS_CHECK){
					total_bean.setCheckState(Constants.WAIT_CHECK);
				}				
				String field1 = "SumEmployNum,govermentNum,PubInstiNum,EnterpriseNum,ForceNum,FlexibleEmploy,GoOnHighStudy,NationItemEmploy,OtherEmploy," +
				"SumGoOnHighStudyNum,RecommendGraNum,ExamGraApplyNum,ExamGraEnrollNum,ExamGraInSch,ExamGraOutSch,AbroadNum,CheckState";
				flag1 = DAOUtil.update(total_bean, tableName, key, field1, conn);
			}

			if(flag1){
				//重新打开数据库连接
				Connection conn1 = DBConnection.instance.getConnection() ;	
				Statement st1 = conn1.createStatement();
				flag = st1.executeUpdate(sql.toString());
			}else{
				flag = 0;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (flag == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 更新数据
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */	
	public int update(T632_Bean bean, String year){
		String sql0 = "select * from " + tableName + " where SeqNumber=" + bean.getSeqNumber();
		String sql1 = "select * from " + tableName + " where convert(varchar(4),Time,120)=" + year + " and TeaUnit=" + "'全校合计'" + ";";		
		int flag = 0;
		boolean flag0 = false;
		boolean flag1 = false;
		Connection conn = DBConnection.instance.getConnection() ;		
		Statement st = null ;
		ResultSet rs = null ;
		Statement st1 = null ;
		ResultSet rs1 = null ;
		List<T632_Bean> templist = null ;
		List<T632_Bean> templist1 = null ;
//		"TeaUnit,UnitId,MajorName,MajorId,MajorFieldName," +
//		"JuniorStuSumNum,JuniorOneStuNum,JuniorTwoStuNum,JuniorThreeStuNum,Time,Note,CheckState";
		String updatefield = "TeaUnit,UnitId,MajorName,MajorId,SumEmployNum,govermentNum,PubInstiNum,EnterpriseNum,ForceNum,FlexibleEmploy,GoOnHighStudy,NationItemEmploy,OtherEmploy," +
		"SumGoOnHighStudyNum,RecommendGraNum,ExamGraApplyNum,ExamGraEnrollNum,ExamGraInSch,ExamGraOutSch,AbroadNum,Note";
		String updatefield1 = "SumEmployNum,govermentNum,PubInstiNum,EnterpriseNum,ForceNum,FlexibleEmploy,GoOnHighStudy,NationItemEmploy,OtherEmploy," +
		"SumGoOnHighStudyNum,RecommendGraNum,ExamGraApplyNum,ExamGraEnrollNum,ExamGraInSch,ExamGraOutSch,AbroadNum,CheckState";
		
		try{
			//求编辑的那条数据
			st = conn.createStatement() ;
			rs = st.executeQuery(sql0) ;
			templist = DAOUtil.getList(rs, T632_Bean.class) ;			
			T632_Bean tempBean = templist.get(0);

			//求捐赠总计bean			
			st1 = conn.createStatement() ;
			rs1 = st1.executeQuery(sql1) ;
			templist1 = DAOUtil.getList(rs1, T632_Bean.class) ;
			T632_Bean tempBean1 = templist1.get(0);
			
			//总计
			if(tempBean1.getCheckState() == Constants.NOPASS_CHECK){
				tempBean1.setSumEmployNum(tempBean1.getSumEmployNum()+(bean.getSumEmployNum()-tempBean.getSumEmployNum()));//总计+变化的量
				tempBean1.setGovermentNum(tempBean1.getGovermentNum()+(bean.getGovermentNum()-tempBean.getGovermentNum()));
				tempBean1.setPubInstiNum(tempBean1.getPubInstiNum()+(bean.getPubInstiNum()-tempBean.getPubInstiNum()));
				tempBean1.setEnterpriseNum(tempBean1.getEnterpriseNum()+(bean.getEnterpriseNum()-tempBean.getEnterpriseNum()));
				
				tempBean1.setForceNum(tempBean1.getForceNum()+(bean.getForceNum()-tempBean.getForceNum()));//总计+变化的量
				tempBean1.setFlexibleEmploy(tempBean1.getFlexibleEmploy()+(bean.getFlexibleEmploy()-tempBean.getFlexibleEmploy()));
				tempBean1.setGoOnHighStudy(tempBean1.getGoOnHighStudy()+(bean.getGoOnHighStudy()-tempBean.getGoOnHighStudy()));
				tempBean1.setNationItemEmploy(tempBean1.getNationItemEmploy()+(bean.getNationItemEmploy()-tempBean.getNationItemEmploy()));
				
				tempBean1.setOtherEmploy(tempBean1.getOtherEmploy()+(bean.getOtherEmploy()-tempBean.getOtherEmploy()));//总计+变化的量
				tempBean1.setSumGoOnHighStudyNum(tempBean1.getSumGoOnHighStudyNum()+(bean.getSumGoOnHighStudyNum()-tempBean.getSumGoOnHighStudyNum()));
				tempBean1.setRecommendGraNum(tempBean1.getRecommendGraNum()+(bean.getRecommendGraNum()-tempBean.getRecommendGraNum()));
				tempBean1.setExamGraApplyNum(tempBean1.getExamGraApplyNum()+(bean.getExamGraApplyNum()-tempBean.getExamGraApplyNum()));
				
				tempBean1.setExamGraEnrollNum(tempBean1.getExamGraEnrollNum()+(bean.getExamGraEnrollNum()-tempBean.getExamGraEnrollNum()));//总计+变化的量
				tempBean1.setExamGraInSch(tempBean1.getExamGraInSch()+(bean.getExamGraInSch()-tempBean.getExamGraInSch()));
				tempBean1.setExamGraOutSch(tempBean1.getExamGraOutSch()+(bean.getExamGraOutSch()-tempBean.getExamGraOutSch()));
				tempBean1.setAbroadNum(tempBean1.getAbroadNum()+(bean.getAbroadNum()-tempBean.getAbroadNum()));
				
				tempBean1.setCheckState(Constants.WAIT_CHECK);
				flag0 = DAOUtil.update(bean, tableName, key, updatefield, conn) ;
				//重新打开数据库连接
				Connection conn1 = DBConnection.instance.getConnection() ;	
				flag1 = DAOUtil.update(tempBean1, tableName, key, updatefield1, conn1) ;					
				
				if(flag0&&flag1){
					flag = 2;
				}
			}else{
				tempBean1.setSumEmployNum(tempBean1.getSumEmployNum()+(bean.getSumEmployNum()-tempBean.getSumEmployNum()));//总计+变化的量
				tempBean1.setGovermentNum(tempBean1.getGovermentNum()+(bean.getGovermentNum()-tempBean.getGovermentNum()));
				tempBean1.setPubInstiNum(tempBean1.getPubInstiNum()+(bean.getPubInstiNum()-tempBean.getPubInstiNum()));
				tempBean1.setEnterpriseNum(tempBean1.getEnterpriseNum()+(bean.getEnterpriseNum()-tempBean.getEnterpriseNum()));
				
				tempBean1.setForceNum(tempBean1.getForceNum()+(bean.getForceNum()-tempBean.getForceNum()));//总计+变化的量
				tempBean1.setFlexibleEmploy(tempBean1.getFlexibleEmploy()+(bean.getFlexibleEmploy()-tempBean.getFlexibleEmploy()));
				tempBean1.setGoOnHighStudy(tempBean1.getGoOnHighStudy()+(bean.getGoOnHighStudy()-tempBean.getGoOnHighStudy()));
				tempBean1.setNationItemEmploy(tempBean1.getNationItemEmploy()+(bean.getNationItemEmploy()-tempBean.getNationItemEmploy()));
				
				tempBean1.setOtherEmploy(tempBean1.getOtherEmploy()+(bean.getOtherEmploy()-tempBean.getOtherEmploy()));//总计+变化的量
				tempBean1.setSumGoOnHighStudyNum(tempBean1.getSumGoOnHighStudyNum()+(bean.getSumGoOnHighStudyNum()-tempBean.getSumGoOnHighStudyNum()));
				tempBean1.setRecommendGraNum(tempBean1.getRecommendGraNum()+(bean.getRecommendGraNum()-tempBean.getRecommendGraNum()));
				tempBean1.setExamGraApplyNum(tempBean1.getExamGraApplyNum()+(bean.getExamGraApplyNum()-tempBean.getExamGraApplyNum()));
				
				tempBean1.setExamGraEnrollNum(tempBean1.getExamGraEnrollNum()+(bean.getExamGraEnrollNum()-tempBean.getExamGraEnrollNum()));//总计+变化的量
				tempBean1.setExamGraInSch(tempBean1.getExamGraInSch()+(bean.getExamGraInSch()-tempBean.getExamGraInSch()));
				tempBean1.setExamGraOutSch(tempBean1.getExamGraOutSch()+(bean.getExamGraOutSch()-tempBean.getExamGraOutSch()));
				tempBean1.setAbroadNum(tempBean1.getAbroadNum()+(bean.getAbroadNum()-tempBean.getAbroadNum()));
				flag0 = DAOUtil.update(bean, tableName, key, updatefield, conn) ;
				//重新打开数据库连接
				Connection conn1 = DBConnection.instance.getConnection() ;	
				flag1 = DAOUtil.update(tempBean1, tableName, key, updatefield1, conn1) ;	
				if(flag0&&flag1){
					flag = 1;
				}
			}
													
		}catch(Exception e){
			e.printStackTrace() ;
			return 0;
		}finally{
			DBConnection.close(conn) ;
		}
		return flag;
	}
	
	/**
	 * 更新某条数据的审核状态
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:23
	 */	
	public boolean updateCheck(String year, String unitName, int checkState){
		
		int flag ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		String sql = "update " + tableName + " set CheckState=" + checkState +
		" where TeaUnit='" + unitName + "' and convert(varchar(4),Time,120)=" + year;			
		System.out.println(sql);
		try{			
			st = conn.createStatement();
			flag = st.executeUpdate(sql);					
		}catch(Exception e){
			e.printStackTrace() ;
			return false;
		}finally{
			DBConnection.close(conn) ;
		}
		
		if (flag == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	
	/**
	 * 
	 * @param list
	 *            {@linkplain java.util.List<
	 *            {@link cn.nit.bean.table5.UndergraCSBaseTeaBean}>}
	 * @return true表示插入成功，false表示插入失败
	 */
	public boolean batchInsert(List<T632_Bean> list) {

		boolean flag = false;
		Connection conn = DBConnection.instance.getConnection();

		try {
			flag = DAOUtil.batchInsert(list, tableName, field, conn);
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}

		return flag;
	}

//	// 更新
//	public boolean update(T632_Bean GraStuEmployInfo) {
//
//		boolean flag = false;
//		Connection conn = DBConnection.instance.getConnection();
//		try {
//			flag = DAOUtil
//					.update(GraStuEmployInfo, tableName, key, field, conn);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return flag;
//		} finally {
//			DBConnection.close(conn);
//		}
//
//		return flag;
//	}
//
//	// 删除 ids应书写为"(1,2,3)"
//	public boolean deleteItemsByIds(String ids) {
//
//		int flag = 0;
//		StringBuffer sql = new StringBuffer();
//		sql.append("delete from " + tableName);
//		sql.append(" where " + key + " in " + ids);
//		Connection conn = DBConnection.instance.getConnection();
//		Statement st = null;
//
//		try {
//			st = conn.createStatement();
//			flag = st.executeUpdate(sql.toString());
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//
//		if (flag == 0) {
//			return false;
//		} else {
//			return true;
//		}
//	}

	public String getTableName() {
		return this.tableName;
	}
	
	public List<T632_Bean> queryPageList(int pagesize, int currentpage) {
		// TODO Auto-generated method stub
		
		
		String queryPageSql = "select top " + pagesize + 
		fieldShow
		+ " from " + tableName + 
		" where (SeqNumber not in (select top " + pagesize * (currentpage-1) + " SeqNumber from "+
		tableName + " order by SeqNumber)) order by SeqNumber" ;
		System.out.println(queryPageSql);
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T632_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T632_Bean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
		
		return list ;
	}
	
	public List<T632_Bean> getAllList() {
		// TODO Auto-generated method stub
		String sql = "select " + fieldShow + " from " + tableName ;
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T632_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T632_Bean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
		
		return list ;
	}
	
	public List<T632_Bean> queryPageList(String cond, Object object,
			int pagesize, int currentpage) {
		// TODO Auto-generated method stub
		String Cond = "1=1";
		
		if(cond != null && !cond.equals("")){
			Cond = Cond + cond;
		}
		String queryPageSql;
		
			queryPageSql = "select top " + pagesize + 
			fieldShow
			+ " from " + tableName + 
			" where " + Cond + " and (SeqNumber not in (select top " + pagesize * (currentpage-1) + " SeqNumber from "+
			tableName + " where " + Cond + " order by SeqNumber)) order by SeqNumber" ;
	

		System.out.println(queryPageSql);
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T632_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(queryPageSql) ;
			list = DAOUtil.getList(rs, T632_Bean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
		
		return list ;
	}
	
	public List<T632_Bean> getAllList(String cond, Object object) {
		// TODO Auto-generated method stub
		String Cond = "1=1";
		
		if(cond != null && !cond.equals("")){
			Cond = Cond + cond;
		}
		String sql;
		
		sql = "select " + fieldShow + " from " + tableName +" where " + Cond;
//	    System.out.println(sql);
	
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		List<T632_Bean> list = null ;
		
		try{
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			list = DAOUtil.getList(rs, T632_Bean.class) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(rs);
			DBConnection.close(st);			
		}
		
		return list ;
	}
	
	/**
	 * @param 查询某年的合计信息
	 * @return
	 */
	public T632_Bean getYearInfo(String year,String teaUnit){
		
		StringBuffer sql = new StringBuffer() ;
		List<T632_Bean> list = null ;
		sql.append("select * from "+ tableName);
		sql.append(" where TeaUnit='" + teaUnit + "' and Time like '"+year+"%'");
		
		Connection conn = DBConnection.instance.getConnection() ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			list = DAOUtil.getList(rs, T632_Bean.class) ;
			if(list.size()!=0){
				T632_Bean bean = list.get(0);
				return bean;
			}else{
				return null;
			}
			
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}
	}





}
