package com.karle.CRUDspringMySQL.response;

import java.net.InetAddress;

public class Response {

	public String filename;
	public String filetype;
	public String status;
	public String downloadUrl;
	
	
	
	
	public Response(){}

	public Response(String filename, String filetype, String status, String downloadUrl) {
		super();
		this.filename = filename;
		this.filetype = filetype;
		this.status = status;
		this.downloadUrl = downloadUrl;
	}

	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public String getFiletype() {
		return filetype;
	}


	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getDownloadUrl() {
		return downloadUrl;
	}


	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}


		
	
}
