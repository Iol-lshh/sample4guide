package lshh.sample4guide.domain.level;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lshh.sample4guide.domain.level.entity.Grade;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(fluent = true)
@Getter
@Entity
public class Level {
    @Id
    private Integer level;
    private Integer minimumExperience;
    @Embedded
    private Grade grade;
}
