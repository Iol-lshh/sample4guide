package lshh.sample4guide.domain.level.dto;

import lshh.sample4guide.domain.level.Level;
import lshh.sample4guide.domain.level.entity.Grade;

public record LevelSaveCommand(
    Integer level,
    Integer minimumExperience,
    String gradeName,
    Integer gradeLevel
) {
    public Level toEntity() {
        Grade grade = Grade.builder()
            .name(gradeName)
            .gradeLevel(gradeLevel)
            .build();
        return Level.builder()
            .level(level)
            .minimumExperience(minimumExperience)
            .grade(grade)
            .build();
    }
}
