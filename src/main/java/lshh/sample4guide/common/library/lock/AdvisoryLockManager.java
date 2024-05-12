package lshh.sample4guide.common.library.lock;

import lshh.sample4guide.common.library.aop.AopTransaction;
import lshh.sample4guide.common.library.parser.ExpressionLanguageParser;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.concurrent.locks.Lock;

@Aspect
public class AdvisoryLockManager {
    private static final String LOCK_PREFIX = "LOCK:";

    private final AdvisoryLockBuffer advisoryLockBuffer;
    private final AopTransaction aopTransaction;


    public AdvisoryLockManager(AdvisoryLockBuffer advisoryLockBuffer, AopTransaction aopTransaction) {
        this.advisoryLockBuffer = advisoryLockBuffer;
        this.aopTransaction = aopTransaction;
    }

    @Around("@annotation(lshh.sample4guide.common.library.lock.AdvisoryLock)")
    public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        AdvisoryLock advisoryLock = method.getAnnotation(AdvisoryLock.class);

        String key = LOCK_PREFIX + ExpressionLanguageParser.getDynamicValue(signature.getParameterNames(), joinPoint.getArgs(), advisoryLock.key());
        Lock lock = advisoryLockBuffer.getLock(key);

        try {
            lock.lock();
            return aopTransaction.proceed(joinPoint);  // (3)
        } catch (InterruptedException e) {
            throw new InterruptedException();
        } finally {
            lock.unlock();
        }
    }

    @Around("@annotation(lshh.sample4guide.common.library.lock.AdvisoryLock)")
    public Object tryLock(final ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        AdvisoryLock advisoryLock = method.getAnnotation(AdvisoryLock.class);

        String key = LOCK_PREFIX + ExpressionLanguageParser.getDynamicValue(signature.getParameterNames(), joinPoint.getArgs(), advisoryLock.key());
        Lock lock = advisoryLockBuffer.getLock(key);

        try {
            boolean available = lock.tryLock(advisoryLock.waitTime(), advisoryLock.timeUnit());  // (2)
            if (!available) {
                return false;
            }
            return aopTransaction.proceed(joinPoint);  // (3)
        } catch (InterruptedException e) {
            throw new InterruptedException();
        } finally {
            lock.unlock();
        }
    }
}
