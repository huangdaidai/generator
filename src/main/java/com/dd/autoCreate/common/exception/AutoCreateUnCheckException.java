/**
 * 
 */
package com.dd.autoCreate.common.exception;

/**
 * @author hdd
 * 2018年4月22日
 * 
 */
public class AutoCreateUnCheckException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	/**
	 * @param string
	 */
	public AutoCreateUnCheckException(String s) {
		super(s);
	}

}
