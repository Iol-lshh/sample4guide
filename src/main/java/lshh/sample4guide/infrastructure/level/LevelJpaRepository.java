package lshh.sample4guide.infrastructure.level;

import lshh.sample4guide.domain.level.Level;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LevelJpaRepository extends JpaRepository<Level, Integer> {
}
