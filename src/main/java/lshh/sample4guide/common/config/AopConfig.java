package lshh.sample4guide.common.config;

import lshh.sample4guide.common.library.aop.AopTransaction;
import lshh.sample4guide.common.library.log.RestControllerLoggingAop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AopConfig {

    @Bean
    public AopTransaction aopTransaction() {
        return new AopTransaction();
    }

    @Bean
    public RestControllerLoggingAop restControllerLogger() {
        return new RestControllerLoggingAop();
    }
}
