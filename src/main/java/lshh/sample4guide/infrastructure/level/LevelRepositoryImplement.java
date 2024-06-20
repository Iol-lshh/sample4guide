package lshh.sample4guide.infrastructure.level;

import lombok.RequiredArgsConstructor;
import lshh.sample4guide.domain.level.Level;
import lshh.sample4guide.domain.level.component.LevelRepository;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class LevelRepositoryImplement implements LevelRepository {
    private final LevelJpaRepository levelJpaRepository;

    @Override
    public void save(Level level) {
        levelJpaRepository.save(level);
    }
}
