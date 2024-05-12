package lshh.sample4guide.common.config;

import lshh.sample4guide.common.library.democache.DemoAdvisoryLockBuffer;
import lshh.sample4guide.common.library.lock.AdvisoryLockBuffer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoConfig {
    @Bean
    public AdvisoryLockBuffer advisoryLockBuffer() {
        return new DemoAdvisoryLockBuffer() {
        };
    }
}
