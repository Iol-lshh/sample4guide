package lshh.sample4guide.common.config;

import lshh.sample4guide.domain.level.LevelService;
import lshh.sample4guide.domain.level.dto.LevelSaveCommand;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoDataConfig {

    private final LevelService levelService;

    public DemoDataConfig(LevelService levelService) {
        this.levelService = levelService;
        initDemoData();
    }

    public void initDemoData(){
        initDemoLevelData();
    }

    public void initDemoLevelData(){
        // 레벨 1~10 생성
        for(int i=1; i<=10; i++){
            LevelSaveCommand command = new LevelSaveCommand(i, (i-1)*100, "BRONZE_"+i, 1);
            levelService.saveLevel(command);
        }
    }
}
