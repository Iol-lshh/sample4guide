package lshh.sample4guide.common.library.democache;

import lshh.sample4guide.common.library.lock.AdvisoryLockBuffer;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class DemoAdvisoryLockBuffer extends DemoBuffer<Lock> implements AdvisoryLockBuffer {

    public Lock getLock(String key) {
        cacheMap.putIfAbsent(key, new ReentrantLock());
        return cacheMap.get(key);
    }

    @Override
    public void clear(String key) {
        Lock lock = cacheMap.get(key);
        if(lock == null) {
            return;
        }
        super.clear(key);
    }
}
