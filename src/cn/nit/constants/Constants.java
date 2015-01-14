package cn.nit.constants;


public class Constants{
	
	//审核状态
	public final static int WAIT_CHECK = 1;//待审核
	public final static int PASS_CHECK = 2;//审核通过
	public final static int NOPASS_CHECK = 4;//审核未通过	
	public final static int NO_CHECK = 14;//待审核或未通过	
	
	
	//审核类型分为三种，一种是直接审核，第二种教学单位的审核，第三种是但单表按年审核	
	public final static int CTypeOne = 1;
	public final static int CTypeTwo = 2;
	public final static int CTypeThree = 3;

 }
