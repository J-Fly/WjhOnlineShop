package utils;

import java.io.Serializable;

public class ResponseData implements Serializable{
	private boolean flag;
	private String message;
	private Object data;
	
	public boolean isFlag() {
		return flag;
	}
	public String getMessage() {
		return message;
	}
	public Object getData() {
		return data;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
