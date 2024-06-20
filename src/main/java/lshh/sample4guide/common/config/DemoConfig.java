package lshh.sample4guide.common.config;

import lshh.sample4guide.common.library.clock.ClockManager;
import lshh.sample4guide.common.library.clock.ClockManagerImplement;
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
    public ClockManager clock(){
        return new ClockManagerImplement();
    }

}
