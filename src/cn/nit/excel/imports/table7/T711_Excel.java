package cn.nit.excel.imports.table7;


import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.nit.bean.di.DiAwardLevelBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.table7.T711_Bean;
import cn.nit.service.di.DiAwardLevelService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table7.T711_Service;
import cn.nit.util.TimeUtil;

import jxl.Cell;

public class T711_Excel {
	

	/**
	 * 批量导入
	 * @param cellList {@link java.util.List<{@link jxl.Cell}>}  Cell是数组
	 * @param request  {@link javax.servlet.http.HttpServletRequest}
	 * @return
	 */
	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request,String selectYear){
	
		if((cellList == null) || (cellList.size() < 2)){
			return "数据不标准，请重新提交" ;
		}
		
		int  count=1;
		T711_Bean T711_Bean=new T711_Bean();
		boolean flag=false;
	    List<T711_Bean> list=new LinkedList<T711_Bean>();
	
	    DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		
		DiAwardLevelService diAwardLeSr = new DiAwardLevelService();
		List<DiAwardLevelBean> diAwardLeBeanList = diAwardLeSr.getList() ;
		
		System.out.println(cellList.size());
		
		for(Cell[] cell: cellList){
			
			
			try {
				
				if(count<4){
					count++;
					continue;
				}
			
				String unit = cell[1].getContents() ;
				String unitId = cell[2].getContents() ;
				
				if(unit == null || unit.equals("")){
					return "第" + count + "行，教学单位不能为空" ;
				}
				
				if(unitId == null || unitId.equals("")){
					return "第" + count + "行，单位号不能为空" ;
				}			
				
				for(DiDepartmentBean diDepartBean : diDepartBeanList){
					if(diDepartBean.getUnitId().equals(unitId)){
						if(diDepartBean.getUnitName().equals(unit)){
							flag = true ;
							break ;
						}else{
							return "第" + count + "行，所属单位与所属单位号不对应" ;
						}
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，没有与之相匹配的单位号" ;
				}else{
					flag = false ;
				}
				String name = cell[3].getContents() ;
				if(name == null || name.equals("")){
					return "第" + count + "行，名称不能为空" ;
				}
				String teaId = cell[4].getContents() ;
				if((teaId == null) || teaId.equals("")){
					return "第" + count + "行，教工号不能为空" ;
				}
				String ardName = cell[5].getContents() ;
				if((ardName == null) || ardName.equals("")){
					return "第" + count + "行，奖励名称不能为空" ;
				}
				String ardLevel = cell[6].getContents() ;
				if((ardLevel == null) || ardLevel.equals("")){
					return "第" + count + "行，级别不能为空" ;
				}
				String ardLevelID=null;
				for(DiAwardLevelBean ardlevelBean : diAwardLeBeanList){
					if(ardlevelBean.getAwardLevel().equals(ardLevel)){
						ardLevelID=ardlevelBean.getIndexId();
						flag=true;
						break;
					}			
				}
				if(!flag){
					return "第" + count + "行，级别类别不存在" ;
				}else{
					flag = false ;
				}
				
				String ardRank = cell[7].getContents();
				if(ardRank == null || ardRank.equals("")){
					return "第" + count + "行，等级不能为空" ;
				}
				String ardTime = cell[8].getContents();
				if(ardTime == null || ardTime.equals("")){
					return "第" + count + "行，获奖时间不能为空" ;
				}
				String ardFu = cell[9].getContents();
				if(ardFu == null || ardFu.equals("")){
					return "第" + count + "行，授予单位不能为空" ;
				}
				String appID = cell[10].getContents();
				if(appID == null || appID.equals("")){
					return "第" + count + "行，批文号不能为空" ;
				}
				String joinTn = cell[11].getContents();
				if(joinTn == null || joinTn.equals("")){
					return "第" + count + "行，合作教师人数不能为空" ;
				}
				String otherJTI = cell[12].getContents();
				if(otherJTI == null || otherJTI.equals("")){
					return "第" + count + "行，其他合作教师不能为空" ;
				}
				
				String note = cell[13].getContents();
				
				count++ ;

				String fillUnitID = null;
				
				T711_Bean.setTeaUnit(unit);
				T711_Bean.setUnitID(unitId);
				T711_Bean.setName(name);
				T711_Bean.setTeaID(teaId);
				T711_Bean.setAwardName(ardName);
				T711_Bean.setAwardLevel(ardLevelID);
				T711_Bean.setAwardRank(ardRank);
				T711_Bean.setAwardTime(TimeUtil.changeDateYMD(ardTime));
				T711_Bean.setAwardFromUnit(ardFu);
				T711_Bean.setAppvlID(appID);
				T711_Bean.setJoinTeaNum(Integer.parseInt(joinTn));
				T711_Bean.setOtherJoinTeaInfo(otherJTI);
				T711_Bean.setFillUnitID(fillUnitID);
				T711_Bean.setTime(TimeUtil.changeDateY(selectYear));
				T711_Bean.setNote(note);
				list.add(T711_Bean);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
			
			
		}
		flag = false ;
		T711_Service t711_Sr=new T711_Service();
		flag=t711_Sr.batchInsert(list);
		if(flag){
			return null ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
	}
	

}
