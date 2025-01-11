package com.example.demo.campingcall.api;

public class Response<T> {
	private HeaderArea header;
	
	private BodyArea<T> body;

	public HeaderArea getHeader() {
		return header;
	}

	public void setHeader(HeaderArea header) {
		this.header = header;
	}

	public BodyArea<T> getBody() {
		return body;
	}

	public void setBody(BodyArea<T> body) {
		this.body = body;
	}	
}
