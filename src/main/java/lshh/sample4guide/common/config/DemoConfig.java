package lshh.sample4guide.common.config;

import lshh.sample4guide.common.library.clock.Clock;
import lshh.sample4guide.common.library.clock.ClockImplement;
import lshh.sample4guide.common.library.localcache.LocalAdvisoryLockBuffer;
import lshh.sample4guide.common.library.lock.AdvisoryLockBuffer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoConfig {
    @Bean
    public AdvisoryLockBuffer advisoryLockBuffer() {
        return new LocalAdvisoryLockBuffer() {
        };
    }

    @Bean
    public Clock clock(){
        return new ClockImplement();
    }
}
