package lshh.sample4guide.common.library.clock;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class RegidatedForHistory {
    @Column(nullable = false, updatable = false)
    private LocalDateTime registered;

    public static RegidatedForHistory register(LocalDateTime now){
        return RegidatedForHistory.builder()
                .registered(now)
                .build();
    }
}