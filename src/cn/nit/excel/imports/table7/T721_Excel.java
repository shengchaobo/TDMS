package cn.nit.excel.imports.table7;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.nit.bean.di.DiAwardLevelBean;
import cn.nit.bean.di.DiDepartmentBean;

import cn.nit.bean.table7.T721_Bean;
import cn.nit.service.di.DiAwardLevelService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table7.T721_Service;
import cn.nit.util.TimeUtil;

import jxl.Cell;

public class T721_Excel {
	
	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request,String selectYear){
		
		
		if((cellList == null) || (cellList.size() < 2)){
			return "数据不标准，请重新提交" ;
		}
		
		int  count=1;
		T721_Bean T721_Bean=new T721_Bean();
		boolean flag=false;
	    List<T721_Bean> list=new LinkedList<T721_Bean>();
	
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
				
				String itemname = cell[1].getContents() ;
				if(itemname == null || itemname.equals("")){
					return "第" + count + "行，项目名称不能为空" ;
				}
				String unit = cell[2].getContents() ;
				String unitId = cell[3].getContents() ;
				
				if(unit == null || unit.equals("")){
					return "第" + count + "行，所属教学单位不能为空" ;
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
				
				String leader = cell[4].getContents() ;
				if((leader == null) || leader.equals("")){
					return "第" + count + "行，负责人不能为空" ;
				}
				String teaId = cell[5].getContents() ;
				if((teaId == null) || teaId.equals("")){
					return "第" + count + "行，教工号不能为空" ;
				}
				String OJTNum = cell[6].getContents() ;
				if((OJTNum == null) || OJTNum.equals("")){
					return "第" + count + "行，其他参与教师人数不能为空" ;
				}
				String otherTea = cell[7].getContents() ;
				if((otherTea == null) || otherTea.equals("")){
					return "第" + count + "行，其他教师不能为空" ;
				}
				
				String itemLevel = cell[8].getContents() ;
				if((itemLevel == null) || itemLevel.equals("")){
					return "第" + count + "行，级别不能为空" ;
				}
				String itemLevelID=null;
				for(DiAwardLevelBean ardlevelBean : diAwardLeBeanList){
					if(ardlevelBean.getAwardLevel().equals(itemLevel)){
						itemLevelID=ardlevelBean.getIndexId();
						flag=true;
						break;
					}			
				}
				if(!flag){
					return "第" + count + "行，级别类别不存在" ;
				}else{
					flag = false ;
				}
				String ISUTime = cell[9].getContents();
				if(ISUTime == null || ISUTime.equals("")){
					return "第" + count + "行，立项时间不能为空" ;
				}
				String recTime = cell[10].getContents();
				if(recTime == null || recTime.equals("")){
					return "第" + count + "行，验收时间不能为空" ;
				}
				String appExp = cell[11].getContents();
				if(appExp == null || appExp.equals("")){
					return "第" + count + "行，批准经费不能为空" ;
				}
				String schExp = cell[12].getContents();
				if(schExp == null || schExp.equals("")){
					return "第" + count + "行，学校配套经费不能为空" ;
				}
				String appID = cell[13].getContents();
				if(appID == null || appID.equals("")){
					return "第" + count + "行，批文号不能为空" ;
				}
				
				String note = cell[14].getContents();
				
				count++ ;

				String fillUnitID = null;
				
				T721_Bean.setItemName(itemname);
				T721_Bean.setTeaUnit(unit);
				T721_Bean.setUnitID(unitId);
				T721_Bean.setLeader(leader);
				T721_Bean.setTeaID(teaId);
				T721_Bean.setOtherTeaNum(Integer.parseInt(OJTNum));
				T721_Bean.setOtherTea(otherTea);
				T721_Bean.setItemLevel(itemLevelID);
				T721_Bean.setItemSetUpTime(TimeUtil.changeDateYM(ISUTime));
				T721_Bean.setReceptTime(TimeUtil.changeDateYM(recTime));
				T721_Bean.setApplvExp(Double.parseDouble(appExp));
				T721_Bean.setSchSupportExp(Double.parseDouble(schExp));
				T721_Bean.setAppvlID(appID);
				T721_Bean.setFillUnitID(fillUnitID);
				T721_Bean.setTime(TimeUtil.changeDateY(selectYear));
				T721_Bean.setNote(note);
				list.add(T721_Bean);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
			
		}
		 flag = false ;
			T721_Service t721_Sr=new T721_Service();
			flag=t721_Sr.batchInsert(list);
			if(flag){
				return null ;
			}else{
				return "数据存储失败，请联系管理员" ;
			}
		}

}
