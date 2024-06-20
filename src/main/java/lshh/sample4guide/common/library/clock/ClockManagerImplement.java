package lshh.sample4guide.common.library.clock;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ClockManagerImplement implements ClockManager {
    @Override
    public long currentTimeMillis() {
        return Clock.system(ZoneId.of("Asia/Seoul")).millis();
    }

    @Override
    public LocalDateTime now() {
        return LocalDateTime.now(getClock());
    }

    @Override
    public Clock getClock() {
        return Clock.system(ZoneId.of("Asia/Seoul"));
    }
}
