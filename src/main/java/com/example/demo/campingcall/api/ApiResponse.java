package com.example.demo.campingcall.api;

public class ApiResponse<T> {
	private Response<T> response;

	public Response<T> getResponse() {
		return response;
	}

	public void setResponse(Response<T> response) {
		this.response = response;
	}
}
