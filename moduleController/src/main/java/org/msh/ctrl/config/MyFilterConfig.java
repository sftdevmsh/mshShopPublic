package org.msh.ctrl.config;

import jakarta.servlet.Filter;
import org.msh.ctrl.config.filter.MyJwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Configuration
public class MyFilterConfig {

    private final MyJwtFilter myJwtFilter;

    @Autowired
    public MyFilterConfig(MyJwtFilter myJwtFilter) {
        this.myJwtFilter = myJwtFilter;
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public FilterRegistrationBean<Filter> myJwtFilterRegisteration()
    {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();

        filterRegistrationBean.setFilter(myJwtFilter);
        filterRegistrationBean.setBeanName("myJwtFilter");
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/api/panel/*");

        return filterRegistrationBean;
    }

}
