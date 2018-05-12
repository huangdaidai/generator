/**
 * 
 */
package com.dd.generator.common.exception;

import lombok.Data;

/**
 * @author hdd
 * 2018年4月22日
 * 
 */
@Data
public class GeneratorRuntimeException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private String code;

	private String msg;

	public GeneratorRuntimeException(String s) {
		super(s);
	}

    public GeneratorRuntimeException(String s,Exception e){
	    super(s,e);
    }

}
