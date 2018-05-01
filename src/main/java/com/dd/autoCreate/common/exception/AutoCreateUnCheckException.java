/**
 * 
 */
package com.dd.autoCreate.common.exception;

import lombok.Data;

/**
 * @author hdd
 * 2018年4月22日
 * 
 */
@Data
public class AutoCreateUnCheckException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private String code;

	private String msg;
	/**
	 * @param string
	 */
	public AutoCreateUnCheckException(String s) {
		super(s);
	}

}
