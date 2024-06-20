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
public class RegidatedForEntity {
    @Column(nullable = false, updatable = false)
    private LocalDateTime created;
    @Column(nullable = false)
    private LocalDateTime updated;
    @Column(nullable = true)
    private LocalDateTime deleted;

    public static RegidatedForEntity create(LocalDateTime now){
        return RegidatedForEntity.builder()
            .created(now)
            .updated(now)
            .build();
    }
}
