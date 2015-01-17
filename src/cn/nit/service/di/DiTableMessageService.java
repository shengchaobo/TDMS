package cn.nit.service.di;

import java.util.List;

import cn.nit.bean.di.DiTableMessageBean;
import cn.nit.dao.di.DiTableMessageDAO;

public class DiTableMessageService {
	DiTableMessageDAO diTableMessageDAO=new DiTableMessageDAO();
	
	/**
	 * 获取所有的表名
	 * @return
	 */
	public List<DiTableMessageBean> getlist(){
		return diTableMessageDAO.getlist();
	}

}
