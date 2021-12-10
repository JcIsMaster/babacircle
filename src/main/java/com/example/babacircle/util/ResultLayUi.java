package com.example.babacircle.util;

import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class ResultLayUi{

	/**
	 * 消息状态码
	 */
	private Integer code;

	/**
	 * 记录
	 */
	private int count;

	/**
	 * 返回数据
	 */
	private Object data;

	/**
	 * 消息
	 */
	private String msg;

	public ResultLayUi() {
	}

	public ResultLayUi(Integer code, int count, Object data, String msg) {
		this.code = code;
		this.count = count;
		this.data = data;
		this.msg = msg;
	}
}
