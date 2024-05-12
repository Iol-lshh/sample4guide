package lshh.sample4guide.common.config;

import lshh.sample4guide.common.library.localcache.LocalAdvisoryLockBuffer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoConfig {
    @Bean
    public lshh.sample4guide.common.library.lock.AdvisoryLockBuffer advisoryLockBuffer() {
        return new LocalAdvisoryLockBuffer() {
        };
    }
}
