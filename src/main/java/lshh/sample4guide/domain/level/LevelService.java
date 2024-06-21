package lshh.sample4guide.domain.level;

import lombok.RequiredArgsConstructor;
import lshh.sample4guide.domain.level.component.LevelRepository;
import lshh.sample4guide.domain.level.dto.LevelSaveCommand;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LevelService {
    private final LevelRepository levelRepository;

    public void save(LevelSaveCommand command){
        Level level = command.toEntity();
        levelRepository.save(level);
    }
}
