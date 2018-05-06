package com.dd.generator.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by arnold.zhu on 6/13/2017.
 */
@Configuration
public class DruidConfig {

    @Value("${spring.datasource.druid.loginUsername}")
    private String loginUsername;

    @Value("${spring.datasource.druid.loginPassword}")
    private String loginPassword;

    @Value("${spring.datasource.druid.loginUrl}")
    private String loginUrl;

    @Configuration
    public class configuration {

        @Bean
        public ServletRegistrationBean DruidStatViewServlet() {
            System.out.println("servletRegistrationBean configure start.");
            //org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
            ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), loginUrl);
            //添加初始化参数
            servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
            servletRegistrationBean.addInitParameter("loginUsername", loginUsername);
            servletRegistrationBean.addInitParameter("loginPassword", loginPassword);
            //是否可以重置
            servletRegistrationBean.addInitParameter("resetEnable", "fase");
            return servletRegistrationBean;
        }

        /**
         * 注册一个：filterRegistrationBean
         *
         * @return
         */
        @Bean
        public FilterRegistrationBean druidStatFilter2() {
            System.out.println("filterRegistrationBean configure start.");
            FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
            //添加过滤规则.
            filterRegistrationBean.addUrlPatterns("/*");
            //添加不需要忽略的格式信息.
            filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
            return filterRegistrationBean;
        }
    }

}
