/**
 * 
 */
package com.hackathon.filters.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hackathon.filters.authentication.AuthenticationFilter;
import com.hackathon.models.analyzerproperties.AnalyzerProperties;
import com.hackathon.services.authentication.AuthService;

/**
 * @author vivekhs
 *
 */
@Configuration
public class AuthFilterConfig {

    @Autowired
    AuthService authService;

    @Autowired
    AnalyzerProperties analyzerProps;

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> loggingFilter() {
        FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new AuthenticationFilter(authService, analyzerProps));

        registrationBean.addUrlPatterns("/code/*");
        return registrationBean;

    }

}
