package com.dd.autoCreate.controller;

import com.dd.autoCreate.common.exception.AutoCreateUnCheckException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionController {

    @ResponseBody
    @ExceptionHandler(value=Exception.class)
    public Map<String,Object> errorHandle(Exception e){
        e.printStackTrace();
        Map<String,Object> m=new HashMap<>();
        m.put("code","-1");
        m.put("msg",e.getMessage());
        return m;
    }

    @ResponseBody
    @ExceptionHandler(value = AutoCreateUnCheckException.class)
    public Map<String,Object> errorHandle(AutoCreateUnCheckException e){
        Map<String,Object> m=new HashMap<>();
        m.put("code","-1");
        m.put("msg",e.getMessage());
        return m;
    }
}
