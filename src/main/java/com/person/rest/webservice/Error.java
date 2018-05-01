package com.person.rest.webservice;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Error {
	int statusCode;
	String statusMessage;
	String path;
	
	public Error() {
		
	}
	
	public Error(int statusCode, String statusMessage, String path) {
		
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.path = path;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
	

}
