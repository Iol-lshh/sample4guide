package lshh.sample4guide.common.library.clock;

import java.time.Instant;

public class ClockImplement implements Clock{
    @Override
    public long currentTimeMillis() {
        return Instant.now().toEpochMilli();
    }
}
