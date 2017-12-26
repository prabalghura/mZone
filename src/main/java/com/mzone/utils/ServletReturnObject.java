package com.mzone.utils;

import org.springframework.ui.ExtendedModelMap;

public class ServletReturnObject extends ExtendedModelMap{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2656680459913306528L;
	String status;
	String errorMsg;
	Object data;
	
	public ServletReturnObject(Object data, String status)
	{
		this.data = data;
		this.errorMsg = "";
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
