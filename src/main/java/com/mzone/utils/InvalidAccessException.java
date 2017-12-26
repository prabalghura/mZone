package com.mzone.utils;

public class InvalidAccessException extends RuntimeException {

	private static final long serialVersionUID = 6811167654628008258L;
	
	private String errCode;
	private String errMsg;

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public InvalidAccessException(String errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
}
