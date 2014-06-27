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
import cn.nit.bean.di.DiTalentTypeBean;
import cn.nit.bean.di.DiTutorTypeBean;
import cn.nit.bean.table4.T442_Bean;
import cn.nit.bean.table4.T443_Bean;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.di.DiMajorTwoService;
import cn.nit.service.di.DiTalentTypeService;
import cn.nit.service.di.DiTutorTypeService;
import cn.nit.service.table4.T442_Service;
import cn.nit.service.table4.T443_Service;
import cn.nit.util.TimeUtil;

public class T443_Excel {

	/**
	 * 批量导入
	 * @param cellList {@link java.util.List<{@link jxl.Cell}>}  Cell是数组
	 * @param request  {@link javax.servlet.http.HttpServletRequest}
	 * @return
	 */
	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request){
		
		if((cellList == null) || (cellList.size() < 2)){
			return "数据不标准，请重新提交" ;
		}
		
		int count = 1 ;
		T443_Bean T443_bean = null ;
		boolean flag = false ;
		List<T443_Bean> list = new LinkedList<T443_Bean>() ;
		
		DiTalentTypeService diTalent = new DiTalentTypeService();
		List<DiTalentTypeBean> diTalentList = diTalent.getList();
							
		for(Cell[] cell : cellList){
			try{
				if(count<=2){
					count++;
					continue;
				}
				
				String name = cell[1].getContents() ;
				String teaId = cell[2].getContents() ;
				
				if(name == null || name.equals("")){
					return "第" + count + "行，导师名称不能为空" ;
				}
				
				if((teaId == null) || teaId.equals("")){
					return "第" + count + "行，教工号不能为空" ;
				}
				
				String talentType = cell[3].getContents();			
				if(talentType == null || talentType.equals("")){
					return "第" + count + "行，人才类别不能为空" ;
				}
				String talentID = null;
				for(DiTalentTypeBean talentBean : diTalentList){
					if(talentBean.getTalentType().equals(talentType)){
						talentID = talentBean.getIndexId() ;
						flag = true  ;
						break ;
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，人才类别不存在" ;
				}else{
					flag = false ;
				}
				
				String resField = cell[4].getContents() ;
				
				String gainTime = cell[5].getContents() ;
				if((gainTime == null) || gainTime.equals("")){
					return "第" + count + "行，获得时间不能为空" ;
				}

				String note = cell[6].getContents() ;
							
								
				count++ ;
				
				
				T443_bean = new T443_Bean() ;
				T443_bean.setName(name);
				T443_bean.setTeaId(teaId);
				T443_bean.setType(talentID);
				T443_bean.setGainTime(TimeUtil.changeDateY(gainTime));
				T443_bean.setNote(note);
				T443_bean.setResField(resField);
				//插入时间
				T443_bean.setTime(new Date());
				list.add(T443_bean);
								
			}catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
		}
		
		flag = false ;
		T443_Service T443_services = new T443_Service() ;
		flag = T443_services.batchInsert(list) ;
		
		if(flag){
			return null ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
	}
}