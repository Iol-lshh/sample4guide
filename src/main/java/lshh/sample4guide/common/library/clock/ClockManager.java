package lshh.sample4guide.common.library.clock;


import java.time.Clock;
import java.time.LocalDateTime;

public interface ClockManager {
    long currentTimeMillis();
    LocalDateTime now();
    Clock getClock();
}
