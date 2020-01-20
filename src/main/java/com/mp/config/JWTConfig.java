package com.mp.config;

import com.mp.filter.JwtFilter;
import com.mp.util.JwtHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class JWTConfig {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expire}")
    private long expire;

    @Value("${jwt.authorised-urls}")
    private String[] authorisedUrls;

    @Bean
    public JwtHelper jwtHelperBean() {
        return new JwtHelper(secret, expire);
    }

    @Bean
    public FilterRegistrationBean basicFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        JwtFilter filter = new JwtFilter(jwtHelperBean(), authorisedUrls);
        registrationBean.setFilter(filter);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }
}
