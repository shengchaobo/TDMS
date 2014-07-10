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
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.di.DiMajorTwoService;
import cn.nit.service.di.DiResearchTeamService;
import cn.nit.service.di.DiTalentTypeService;
import cn.nit.service.di.DiTutorTypeService;
import cn.nit.service.table4.T442_Service;
import cn.nit.service.table4.T443_Service;
import cn.nit.service.table4.T444_Service;
import cn.nit.util.TimeUtil;

public class T444_Excel {

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
		T444_Bean T444_bean = null ;
		boolean flag = false ;
		List<T444_Bean> list = new LinkedList<T444_Bean>() ;
		
		DiResearchTeamService diTeam = new DiResearchTeamService();
		List<DiResearchTeamBean> diTeamList = diTeam.getList();
							
		for(Cell[] cell : cellList){
			try{
				if(count<4){
					count++;
					continue;
				}
				
				
				String resField = cell[1].getContents() ;
				if((resField == null) || resField.equals("")){
					return "第" + count + "行，研究方向不能为空" ;
				}
				
				String teamType = cell[2].getContents();			
				if(teamType == null || teamType.equals("")){
					return "第" + count + "行，团队类型不能为空" ;
				}
				String teamID = null;
				for(DiResearchTeamBean teamBean : diTeamList){
					if(teamBean.getResearchTeam().equals(teamType)){
						teamID = teamBean.getIndexId() ;
						flag = true  ;
						break ;
					}//if
				}//for
				
				if(!flag){
					return "第" + count + "行，团队类型不存在" ;
				}else{
					flag = false ;
				}
				
				String gainTime = cell[3].getContents() ;
				if((gainTime == null) || gainTime.equals("")){
					return "第" + count + "行，获得时间不能为空" ;
				}
				
				String name = cell[4].getContents() ;
				String teaId = cell[5].getContents() ;
				
				if(name == null || name.equals("")){
					return "第" + count + "行，负责人名称不能为空" ;
				}
				
				if((teaId == null) || teaId.equals("")){
					return "第" + count + "行，教工号不能为空" ;
				}
				
				String otherTeamNum = cell[6].getContents() ;
				String otherTeamPer = cell[7].getContents() ;

				String note = cell[8].getContents() ;
															
				count++ ;
								
				T444_bean = new T444_Bean() ;
				T444_bean.setResField(resField);
				T444_bean.setTeaId(teamID);
				T444_bean.setGainTime(TimeUtil.changeDateY(gainTime));
				T444_bean.setTeaId(teaId);
				T444_bean.setLeader(name);				
				T444_bean.setNote(note);
				T444_bean.setOtherTeamNum(Integer.parseInt(otherTeamNum));
				T444_bean.setOtherTeamPer(otherTeamPer);
				//插入时间
				T444_bean.setTime(TimeUtil.changeDateY(selectYear));
				list.add(T444_bean);
								
			}catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
		}
		
		flag = false ;
		T444_Service T444_services = new T444_Service() ;
		flag = T444_services.batchInsert(list) ;
		
		if(flag){
			return null ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
	}
}