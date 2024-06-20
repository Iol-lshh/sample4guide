package lshh.sample4guide.domain.level.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
@Getter
@Embeddable
public class Grade {
    private String name;
    private Integer gradeLevel;
}
