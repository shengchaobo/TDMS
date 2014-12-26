package cn.nit.excel.imports.table4;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanWrapperImpl;

import jxl.Cell;
import cn.nit.bean.UserinfoBean;
import cn.nit.bean.di.DiAwardLevelBean;
import cn.nit.bean.di.DiAwardTypeBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.table4.T4_11_Bean;
import cn.nit.service.di.DiAwardLevelService;
import cn.nit.service.di.DiAwardTypeService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table4.T4_11_Service;
import cn.nit.util.TimeUtil;

public class T4_11_Excel {

	/**
	 * 批量导入
	 * @param cellList {@link java.util.List<{@link jxl.Cell}>}  Cell是数组
	 * @param request  {@link javax.servlet.http.HttpServletRequest}
	 * @return
	 */
	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request, String selectYear){
		
		if((cellList == null) || (cellList.size() < 2)){
			return "数据不标准，请重新提交" ;
		}
		
		int count = 1 ;
		T4_11_Bean T4_11_bean = null ;
		boolean flag = false ;
		List<T4_11_Bean> list = new LinkedList<T4_11_Bean>() ;
		
		DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;		
				
		for(Cell[] cell : cellList){
			try{
				if(count<=4){
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
				
				String patentNum = cell[3].getContents() ;					
				String achieNum = cell[4].getContents() ;				
				String consNum = cell[5].getContents();				
				String partJobNum = cell[6].getContents() ;												
				String judgeNum = cell[7].getContents() ;
				String note = cell[8].getContents() ;
								
				count++ ;
								
				T4_11_bean = new T4_11_Bean() ;
				T4_11_bean.setUnitName(unit);
				T4_11_bean.setUnitId(unitId);
				T4_11_bean.setPatentNum(Integer.parseInt(patentNum));
				T4_11_bean.setAchieNum(Integer.parseInt(achieNum));
				T4_11_bean.setConsNum(Integer.parseInt(consNum));				
				T4_11_bean.setPartJobNum(Integer.parseInt(partJobNum));				
				T4_11_bean.setJudgeNum(Integer.parseInt(judgeNum));
				T4_11_bean.setNote(note);
				//插入时间
				T4_11_bean.setTime(TimeUtil.changeDateY(selectYear));
				//插入教学单位
				UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
				String fillUnitID = bean.getUnitID();
				T4_11_bean.setFillUnitID(fillUnitID);

				list.add(T4_11_bean);
								
			}catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
		}
		
		flag = false ;
		T4_11_Service T4_11_services = new T4_11_Service() ;
		flag = T4_11_services.batchInsert(list) ;
		
		if(flag){
			return null ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
	}
}