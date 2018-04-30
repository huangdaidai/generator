package com.dd.autoCreate.controller;

import com.dd.autoCreate.common.exception.AutoCreateUnCheckException;
import com.dd.autoCreate.model.DataSource;
import com.dd.autoCreate.model.Table;
import com.dd.autoCreate.service.UserDBService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hdd
 * 2018年4月23日
 * 
 */
@Controller
@RequestMapping("/userConfig")
public class UserConfigController {
	Logger log=LoggerFactory.getLogger(UserConfigController.class);
	
	@Autowired
	UserDBService userDBService;
	
	@RequestMapping(value="/loadTables")
	@ResponseBody
	public Object loadTables(@RequestBody DataSource d){
		Map<String,Object> m=new HashMap<>();
		try{
			if(d == null ){
				throw new AutoCreateUnCheckException("数据库配置不能为空！");
			}else if(StringUtils.isBlank(d.getUrl())){
				throw new AutoCreateUnCheckException("数据库URL不能为空！");
			}else if(StringUtils.isBlank(d.getPort())){
				throw new AutoCreateUnCheckException("数据库端口不能为空！");
			}else if(StringUtils.isBlank(d.getUserName())){
				throw new AutoCreateUnCheckException("数据库用户名不能为空！");
			}else if(StringUtils.isBlank(d.getPassword())){
				throw new AutoCreateUnCheckException("数据库密码不能为空！");
			}else if(StringUtils.isBlank(d.getDbType())){
				throw new AutoCreateUnCheckException("数据库类型不能为空！");
			}
			
			if("oracle".equals(d.getDbType())){
				d.setUrl("jdbc:oracle:thin:@"+d.getUrl()+":"+d.getPort()+":"+d.getDbName());
			}else{
				d.setUrl("jdbc:mysql://"+d.getUrl()+":"+d.getPort()+"/"+d.getDbName()+"?useSSL=false");
			}
			
			List<Table> tableList=userDBService.loadTables(d);
			m.put("data", tableList);
		}catch(Exception e){
			log.error(e.getMessage(),e);
			m.put("returnCode", "1");
			m.put("errorMsg", e.getMessage());
		}
		return m;
	}
}
