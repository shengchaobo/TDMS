package cn.nit.excel.imports.table5;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.nit.bean.di.DiAwardLevelBean;
import cn.nit.bean.table5.T552_Bean;
import cn.nit.constants.Constants;
import cn.nit.service.di.DiAwardLevelService;
import cn.nit.service.table5.T552_Service;
import cn.nit.util.TimeUtil;

import jxl.Cell;

public class T552_Excel {
	
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
		
		boolean flag=false;
	    List<T552_Bean> list=new LinkedList<T552_Bean>();
	
		DiAwardLevelService diAwardLeSr = new DiAwardLevelService();
		List<DiAwardLevelBean> diAwardLeBeanList = diAwardLeSr.getList() ;
		
		System.out.println(cellList.size());
		
		
		for(Cell[] cell: cellList){
			T552_Bean T552_Bean=new T552_Bean();
			try {
				
				if(count<4){
					count++;
					continue;
				}
				
				String awardname = cell[1].getContents().trim() ;
				if(awardname == null || awardname.equals("")){
					return "第" + count + "行，获奖名称不能为空" ;
				}
				String awardClass = cell[2].getContents().trim() ;
				if((awardClass == null) || awardClass.equals("")){
					return "第" + count + "行，获奖班级不能为空" ;
				}
				String teaUnit = cell[3].getContents().trim() ;
				if((teaUnit == null) || teaUnit.equals("")){
					return "第" + count + "行，所在教学单位不能为空" ;
				}
				
				String ardLevel = cell[4].getContents().trim() ;
				if((ardLevel == null) || ardLevel.equals("")){
					return "第" + count + "行，级别不能为空" ;
				}
				if(ardLevel.equals("省级")){
					ardLevel = "省部级";
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
				
				String awardTime = cell[5].getContents().trim();
				if(awardTime == null || awardTime.equals("")){
					return "第" + count + "行，获奖时间不能为空" ;
				}
				if(!TimeUtil.judgeFormat3(awardTime)){
					return "第" + count + "行，获奖时间格式不正确，格式为：2014" ;
				}
				
				count++ ;

				String fillUnitID = null;
				
				T552_Bean.setAwardName(awardname);
				T552_Bean.setAwardClass(awardClass);
				T552_Bean.setTeaUnit(teaUnit);
				T552_Bean.setAwardLevel(ardLevelID);
				T552_Bean.setFillUnitID(fillUnitID);
				T552_Bean.setAwardTime(TimeUtil.changeDateY(awardTime));
				T552_Bean.setTime(TimeUtil.changeDateY(selectYear));
				T552_Bean.setCheckState(Constants.WAIT_CHECK);
				list.add(T552_Bean);
				
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
			
			
		}
		flag = false ;
		T552_Service t552_Sr=new T552_Service();
		flag=t552_Sr.batchInsert(list);
		if(flag){
			return null ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
	}
}
