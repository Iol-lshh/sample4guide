package lshh.sample4guide.common.library.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class RestControllerLoggingAop {
    @Pointcut("within(org.springframework.web.bind.annotation.RestController)")
    public void controller() {
    }

    @Around("controller()")
    public Object logController(ProceedingJoinPoint joinPoint) throws Throwable {
        LogForm logForm = LogForm.of("traceId", "spanId", joinPoint);
        log.info(logForm.toString());
        return joinPoint.proceed();
    }

}
