package lshh.sample4guide.common.config;

import jakarta.servlet.Filter;
import lshh.sample4guide.common.library.clock.Clock;
import lshh.sample4guide.common.library.log.HttpRequestLoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<Filter> loggingFilter(Clock clock) {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new HttpRequestLoggingFilter(clock));
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
