package cn.nit.action.table1;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.UserinfoBean;
import cn.nit.bean.table1.T151_Bean;
import cn.nit.bean.table1.T181_Bean;
import cn.nit.constants.Constants;
import cn.nit.excel.imports.table1.T181Excel;
import cn.nit.service.CheckService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table1.T181Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;


public class T181Action {
	
	/**  ��181��Service��  */
	private T181Service t181Ser = new T181Service() ;
	
	/**  ��181��Beanʵ����  */
	private T181_Bean t181Bean = new T181_Bean() ;
	
	
	/**  ��181��Excelʵ����  */
	private T181Excel t181Excel = new T181Excel() ;
	
	/**  ���  */
	private CheckService check_services = new CheckService();
	
	/**excel��������*/
	private String excelName; //
	
	public String getExcelName() {
		try {
			this.excelName = URLEncoder.encode(excelName, "UTF-8");
			//this.saveFile = new String(saveFile.getBytes("ISO-8859-1"),"UTF-8");// ����������
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return excelName;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}

	/**  ��������ݵĲ�ѯ�����к�  */
	private Integer seqNum ;
	
	/**  ��������ݲ�ѯ����ʼʱ��  */
	private Date startTime ;
	
	/**  ��������ݲ�ѯ�Ľ���ʱ��  */
	private Date endTime ;
	
	/**  ���ݵ�SeqNumber���  */
	private String ids ;
	
	/**  ��ǰ��ѯ���ǵڼ�ҳ  */
	private String page ;
	
	/**ÿҳ��ʾ������  */
	private String rows ;
	
	/**  ���״̬��ʾ�б��־  */
	private int checkNum ;
	
	/**  ����/�������  */
	private String selectYear ;
	
	/**  ���ͨ�����ݰ���ʱ���ѯ  */
	private String queryYear ;
	
	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	
	/**  ���Ź���Service��  */
	private DiDepartmentService deSer = new DiDepartmentService() ;
	//���ڵ�½���û���Ϣ
	UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
	String fillUnitID = bean.getUnitID();
	
	/**  ������������  */
	public void insert(){
//		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		t181Bean.setTime(new Date()) ;
		t181Bean.setFillDept(fillUnitID);//����
		//�������״̬
		t181Bean.setCheckState(Constants.WAIT_CHECK);


		boolean flag = t181Ser.insert(t181Bean) ;
		PrintWriter out = null ;
		
		try{
			getResponse().setContentType("text/html; charset=UTF-8") ;
//			getResponse().setHeader("Content-type", "text/html");  
			out = getResponse().getWriter() ;
			if(flag){
				out.print("{\"state\":true,data:\"¼��ɹ�!!!\"}") ;
			}else{
				out.print("{\"state\":false,data:\"¼��ʧ��!!!\"}") ;
			}
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"¼��ʧ��!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
		out.flush() ;
	}
	
	/**  Ϊ�����������  */
	public void auditingData(){
			
//			System.out.println("ݔ��ݔ��ݔ��");
			
			if(this.page == null || this.page.equals("") || !page.matches("[\\d]+")){
				return ;
			}
			
			if(this.rows == null || this.rows.equals("") || !rows.matches("[\\d]+")){
				return ;
			}
			
			String cond = null;
			StringBuffer conditions = new StringBuffer();
			
			if(this.getSeqNum() == null && this.getStartTime() == null && this.getEndTime() == null && this.getCheckNum()==0){			
				cond = null;	
			}else{			
				if(this.getSeqNum()!=null){
					conditions.append(" and SeqNumber=" + this.getSeqNum()) ;
				}
				
				if(this.getStartTime() != null){
					conditions.append(" and cast(CONVERT(DATE, Time)as datetime)>=cast(CONVERT(DATE, '" 
							+ TimeUtil.changeFormat4(this.startTime) + "')as datetime)") ;
				}
				
				if(this.getEndTime() != null){
					conditions.append(" and cast(CONVERT(DATE, Time)as datetime)<=cast(CONVERT(DATE, '" 
							+ TimeUtil.changeFormat4(this.getEndTime()) + "')as datetime)") ;
				}
				
				//���״̬�ж�
				if(this.getCheckNum() == Constants.WAIT_CHECK ){
					conditions.append(" and CheckState=" + this.getCheckNum()) ;
				}else if(this.getCheckNum() == (Constants.PASS_CHECK)){
					conditions.append(" and CheckState=" + this.getCheckNum()) ;
					if(this.getQueryYear() != null){
						conditions.append(" and Time like '" + this.queryYear + "%'");
					}else{
						 Calendar now = Calendar.getInstance();  
						 this.setQueryYear(now.get(Calendar.YEAR)+"");
						 conditions.append(" and Time like '" + this.queryYear + "%'");
					}
				}else if(this.getCheckNum() == (Constants.NOPASS_CHECK)){
					conditions.append(" and CheckState=" + this.getCheckNum()) ;
				}else if(this.getCheckNum() == (Constants.NO_CHECK)){
					conditions.append(" and CheckState!=" + Constants.PASS_CHECK) ;
				}
				
				cond = conditions.toString();
			}
			
			
			String tempUnitID = bean.getUnitID();
			if(!tempUnitID.equals("1012")){
				tempUnitID = null;
			}

			String pages = t181Ser.auditingData(cond, tempUnitID, Integer.parseInt(page), Integer.parseInt(rows)) ;
			PrintWriter out = null ;
			
			try{
				getResponse().setContentType("text/html; charset=UTF-8") ;
				out = getResponse().getWriter() ;
				out.print(pages) ;
			}catch(Exception e){
				e.printStackTrace() ;
				return ;
			}finally{
				if(out != null){
					out.close() ;
				}
			}
		}

	/**  �༭����  */
	public void edit(){

        boolean flag = false;
        int tag = 0;
        
        //��ø����������״̬
		int state = t181Ser.getCheckState(t181Bean.getSeqNumber());
		
		//������״̬�Ǵ���ˣ���ֱ���޸�
		if(state == Constants.WAIT_CHECK){
			t181Bean.setCheckState(Constants.WAIT_CHECK);
			flag = t181Ser.update(t181Bean) ;
			if(flag) tag = 1;
		}
		//�������˲�ͨ�������޸ĸ������ݣ��������״̬����Ϊ����ˣ�ͬʱɾ������������checkInfo�����Ϣ
		if(state == Constants.NOPASS_CHECK){
			t181Bean.setCheckState(Constants.WAIT_CHECK);
			boolean flag1 = t181Ser.update(t181Bean) ;
			boolean flag2 = check_services.delete("T181",t181Bean.getSeqNumber());
			if(flag1&&flag2){
				flag = true;
				tag = 2;
			}
		}
        
		PrintWriter out = null ;
		
		try{
			getResponse().setContentType("text/html; charset=UTF-8") ;
			out = getResponse().getWriter() ;
			if(tag == 1){
				out.print("{\"state\":true,data:\"�޸ĳɹ�!!!\"}") ;
			}
			else if(tag == 2){
				out.print("{\"state\":true,data:\"�޸ĳɹ�!!!\",tag:2}") ;	
			}else{
				out.print("{\"state\":true,data:\"�޸�ʧ��!!!\"}") ;
			}
			out.flush() ;
			out.flush() ;
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"ϵͳ��������ϵ����Ա!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
	/**  �޸�ĳ�����ݵ����״̬  */
	public void updateCheck(){
		HttpServletResponse response = ServletActionContext.getResponse();
	
		boolean flag = t181Ser.updateCheck(this.getSeqNum(),this.getCheckNum());
		PrintWriter out = null ;
		
		try{
			response.setContentType("text/html; charset=UTF-8") ;
			out = response.getWriter() ;
			if(flag){
				out.print("{\"state\":true,data:\"�޸����״̬�ɹ�!!!\"}") ;
			}else{
				out.print("{\"state\":false,data:\"�޸����״̬ʧ��!!!\"}") ;
			}
			out.flush() ;
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"�޸����״̬ʧ��!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
	/**  ȫ�����ͨ��  */
	public void checkAll(){
		HttpServletResponse response = ServletActionContext.getResponse();
	
		boolean flag = t181Ser.checkAll();
		
		PrintWriter out = null ;
		
		try{
			response.setContentType("text/html; charset=UTF-8") ;
			out = response.getWriter() ;
			if(flag){
				out.print("{\"state\":true,data:\"һ����˳ɹ�!!!\"}") ;
			}else{
				out.print("{\"state\":false,data:\"һ�����ʧ��!!!\"}") ;
			}
			out.flush() ;
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"һ�����ʧ��!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
	/**  �������ݵ�idɾ������  */
	public void deleteCoursesByIds(){
		System.out.println("ids=" + ids) ;
		
		boolean flag = t181Ser.deleteCoursesByIds(ids) ;
		//ɾ����˲�ͨ����Ϣ
		check_services.delete("T181", ids);
		PrintWriter out = null ;
		
		try{
			out = getResponse().getWriter() ;
			
			if(flag){
				out.print("{\"state\":true,data:\"����ɾ���ɹ�!!!\"}") ;
			}else{
				out.print("{\"state\":false,data:\"����ɾ��ʧ��!!!\"}") ;
			}
			
			out.flush() ;
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"ϵͳ��������ϵ����Ա!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
	/**���ݵ���*/
	public InputStream getInputStream(){

		InputStream inputStream = null ;

		try {
t181Ser.totalList(fillUnitID,this.getSelectYear(),Constants.PASS_CHECK );			
			String sheetName = this.excelName;
			
			List<String> columns = new ArrayList<String>();
			columns.add("���");
			columns.add("������������");columns.add("������������");columns.add("������������");columns.add("ǩ��Э��ʱ��");
			columns.add("�ҷ���λ");columns.add("��λ��");columns.add("�ҷ���λ����");columns.add("��ע");

			
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("SeqNum", 0);
			maplist.put("CooperInsName", 1);maplist.put("CooperInsType", 2);maplist.put("CooperInsLevel", 3);maplist.put("SignedTime", 4);
			maplist.put("UnitName", 5);maplist.put("UnitID", 6);maplist.put("UnitLevel", 7);maplist.put("Note", 8);
			
			//inputStream = new ByteArrayInputStream(ExcelUtil.exportExcel(list, sheetName, maplist,columns).toByteArray());
			inputStream = new ByteArrayInputStream(ExcelUtil.exportExcel(list, sheetName, maplist, columns).toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}

		return inputStream ;
	}
	

	public String execute() throws Exception{
		return "success" ;
	}
	
	
	public HttpServletRequest getRequest(){
		return ServletActionContext.getRequest() ;
	}
	
	public HttpSession getSession(){
		return getRequest().getSession() ;
	}
	
	public HttpServletResponse getResponse(){
		return ServletActionContext.getResponse() ;
	}

	public T181_Bean getT181Bean() {
		return t181Bean;
	}

	public void setT181Bean(T181_Bean t181Bean) {
		this.t181Bean = t181Bean;
	}

	
	
	public Integer getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(Integer seqNum) {
		this.seqNum = seqNum;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public int getCheckNum() {
		return checkNum;
	}

	public void setCheckNum(int checkNum) {
		this.checkNum = checkNum;
	}

	public String getSelectYear() {
		return selectYear;
	}

	public void setSelectYear(String selectYear) {
		this.selectYear = selectYear;
	}
	
	

	public String getQueryYear() {
		return queryYear;
	}

	public void setQueryYear(String queryYear) {
		this.queryYear = queryYear;
	}

	public static void main(String args[]){
		String match = "[\\d]+" ;
		System.out.println("23gfhf4".matches(match)) ;
	}

}
