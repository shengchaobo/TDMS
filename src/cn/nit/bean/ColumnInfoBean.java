package cn.nit.bean;

public class ColumnInfoBean {
	private String field;
	private String title;
	private int width;
	private String align;
	private String formatter;
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public String getAlign() {
		return align;
	}
	public void setAlign(String align) {
		this.align = align;
	}
	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}
	public String getFormatter() {
		return formatter;
	}
}
