package com.wafasalaf.controller.bean;

public class Result {
	
	private String ice;
	private int codeRetour;
	private String message;
	
	public String getIce() {
		return ice;
	}
	public void setIce(String ice) {
		this.ice = ice;
	}
	public int getCodeRetour() {
		return codeRetour;
	}
	public void setCodeRetour(int codeRetour) {
		this.codeRetour = codeRetour;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Result(String ice, int codeRetour, String message) {
		super();
		this.ice = ice;
		this.codeRetour = codeRetour;
		this.message = message;
	}
	
	
	

}
