package cn.nit.service;

import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import cn.nit.bean.QueryConditionsBean;
import cn.nit.dao.QueryResultDAO;
import cn.nit.util.Pagition;

public class QueryResultService {

	QueryResultDAO queryResultDAO=new QueryResultDAO();
	public <T> String auditingData(String tablename,QueryConditionsBean queryConditionsBean){
		
		List<T> list=queryResultDAO.auditingData(tablename,queryConditionsBean);
        JSON json=JSONSerializer.toJSON(list);
		System.out.println(json.toString());

		return json.toString();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
