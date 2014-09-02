package cn.nit.excel.imports.table4;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import jxl.Cell;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.di.DiMajorTwoBean;
import cn.nit.bean.di.DiResearchTeamBean;
import cn.nit.bean.di.DiTalentTypeBean;
import cn.nit.bean.di.DiTutorTypeBean;
import cn.nit.bean.table4.T442_Bean;
import cn.nit.bean.table4.T443_Bean;
import cn.nit.bean.table4.T444_Bean;
import cn.nit.bean.table4.T451_Bean;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.di.DiMajorTwoService;
import cn.nit.service.di.DiResearchTeamService;
import cn.nit.service.di.DiTalentTypeService;
import cn.nit.service.di.DiTutorTypeService;
import cn.nit.service.table4.T442_Service;
import cn.nit.service.table4.T443_Service;
import cn.nit.service.table4.T444_Service;
import cn.nit.service.table4.T451_Service;
import cn.nit.util.TimeUtil;

public class T451_Excel {

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
		T451_Bean T451_bean = null ;
		boolean flag = false ;
		List<T451_Bean> list = new LinkedList<T451_Bean>() ;
							
		for(Cell[] cell : cellList){
			try{
				if(count<=3){
					count++;
					continue;
				}
				
				
				String orgName = cell[1].getContents() ;
				if((orgName == null) || orgName.equals("")){
					return "第" + count + "行，机构名称不能为空" ;
				}
				
				String unitId = cell[2].getContents();			
				if(unitId == null || unitId.equals("")){
					return "第" + count + "行，单位号不能为空" ;
				}
				
				String orgType = cell[3].getContents() ;
				if((orgName == null) || orgName.equals("")){
					return "第" + count + "行，机构类型不能为空" ;
				}
				
				String trainTime = cell[4].getContents();			
				String trainPerTime = cell[5].getContents();

															
				count++ ;
								
				T451_bean = new T451_Bean() ;
				T451_bean.setOrgName(orgName);
				T451_bean.setOrgType(orgType);
				T451_bean.setUnitId(unitId);
				T451_bean.setTrainTimes(Integer.parseInt(trainTime));
				T451_bean.setTrainPerTimes(Integer.parseInt(trainPerTime));
				//插入时间
				T451_bean.setTime(TimeUtil.changeDateY(selectYear));
				list.add(T451_bean);
								
			}catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
		}
		
		flag = false ;
		T451_Service T451_services = new T451_Service() ;
		flag = T451_services.batchInsert(list) ;
		
		if(flag){
			return null ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
	}
}