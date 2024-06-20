package lshh.sample4guide.common.config;

import jakarta.servlet.Filter;
import lshh.sample4guide.common.library.clock.ClockManager;
import lshh.sample4guide.common.library.log.HttpRequestLoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<Filter> loggingFilter(ClockManager clockManager) {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new HttpRequestLoggingFilter(clockManager));
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
