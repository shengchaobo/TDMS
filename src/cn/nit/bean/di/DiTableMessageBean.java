package cn.nit.bean.di;

public class DiTableMessageBean {
	
	private String Tname;
	
	private String Tid;
	
	private int Ttag;
	
	private String Tparent;

	public String getTname() {
		return Tname;
	}

	public void setTname(String tname) {
		Tname = tname;
	}

	public String getTid() {
		return Tid;
	}

	public void setTid(String tid) {
		Tid = tid;
	}

	public void setTtag(int ttag) {
		Ttag = ttag;
	}

	public int getTtag() {
		return Ttag;
	}

	public void setTparent(String tparent) {
		Tparent = tparent;
	}

	public String getTparent() {
		return Tparent;
	}	
}
