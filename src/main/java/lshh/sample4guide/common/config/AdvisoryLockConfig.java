package lshh.sample4guide.common.config;

import lombok.RequiredArgsConstructor;
import lshh.sample4guide.common.library.aop.AopTransaction;
import lshh.sample4guide.common.library.lock.AdvisoryLockBuffer;
import lshh.sample4guide.common.library.lock.AdvisoryLockManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class AdvisoryLockConfig {
    private final AdvisoryLockBuffer advisoryLockBuffer;
    private final AopTransaction aopTransaction;

    @Bean
    public AdvisoryLockManager advisoryLockManager() {
        return new AdvisoryLockManager(advisoryLockBuffer, aopTransaction) {
        };
    }


}
