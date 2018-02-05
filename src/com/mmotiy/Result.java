package com.mmotiy;

public class Result<T> {
	static final public String SUCCESS = "1";
	static final public String ERROR = "0";
	public String status;
	public String msg;
	public T data;

	public Result(T data) {
		this.data = data;
		this.status = SUCCESS;
	}
}
