package com.dd.generator.controller;

import com.dd.generator.common.exception.GeneratorRuntimeException;
import com.dd.generator.model.DataSource;
import com.dd.generator.model.Table;
import com.dd.generator.service.UserDBService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hdd
 * 2018年4月23日
 */
@Controller
@RequestMapping("/userConfig")
public class UserConfigController {
    Logger log = LoggerFactory.getLogger(UserConfigController.class);

    @Autowired
    UserDBService userDBService;

    @RequestMapping(value = "/loadTables")
    @ResponseBody
    public List<Table> loadTables(@RequestBody DataSource d) throws SQLException {
        Map<String, Object> m = new HashMap<>();
        if (d == null) {
            throw new GeneratorRuntimeException("数据库配置不能为空！");
        } else if (StringUtils.isBlank(d.getUrl())) {
            throw new GeneratorRuntimeException("数据库URL不能为空！");
        } else if (StringUtils.isBlank(d.getPort())) {
            throw new GeneratorRuntimeException("数据库端口不能为空！");
        } else if (StringUtils.isBlank(d.getUserName())) {
            throw new GeneratorRuntimeException("数据库用户名不能为空！");
        } else if (StringUtils.isBlank(d.getPassword())) {
            throw new GeneratorRuntimeException("数据库密码不能为空！");
        } else if (StringUtils.isBlank(d.getDbType())) {
            throw new GeneratorRuntimeException("数据库类型不能为空！");
        }

        if ("oracle".equals(d.getDbType())) {
            d.setUrl("jdbc:oracle:thin:@" + d.getUrl() + ":" + d.getPort() + ":" + d.getDbName());
        } else if ("mysql".equals(d.getDbType())) {
            d.setUrl("jdbc:mysql://" + d.getUrl() + ":" + d.getPort() + "/" + d.getDbName() + "?useSSL=false");
        } else {
            throw new GeneratorRuntimeException("暂不支持该数据库类型，请选用MySQL或Oracle！");
        }
        return userDBService.loadTables(d);
    }

}
