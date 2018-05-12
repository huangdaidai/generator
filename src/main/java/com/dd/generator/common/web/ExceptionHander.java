package com.dd.generator.common.web;


import com.dd.generator.common.exception.GeneratorRuntimeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHander {

    @ResponseBody
    @ExceptionHandler(value=Exception.class)
    public Map<String,Object> errorHandle(Exception e){
        e.printStackTrace();
        Map<String,Object> m=new HashMap<>();
        m.put("status","-1");
        m.put("msg",e.getMessage());
        return m;
    }

    @ResponseBody
    @ExceptionHandler(value = GeneratorRuntimeException.class)
    public Map<String,Object> errorHandle(GeneratorRuntimeException e){
        Map<String,Object> m=new HashMap<>();
        m.put("status","-1");
        m.put("msg",e.getMessage());
        return m;
    }
}