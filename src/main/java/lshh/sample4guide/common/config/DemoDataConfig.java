package lshh.sample4guide.common.config;

import lshh.sample4guide.domain.item.ItemService;
import lshh.sample4guide.domain.item.dto.ItemCreateCommand;
import lshh.sample4guide.domain.level.LevelService;
import lshh.sample4guide.domain.level.dto.LevelSaveCommand;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoDataConfig {

    private final LevelService levelService;
    private final ItemService itemService;

    public DemoDataConfig(LevelService levelService, ItemService itemService) {
        this.levelService = levelService;
        initDemoData();
        this.itemService = itemService;
    }

    public void initDemoData(){
        initDemoLevelData();
    }

    public void initDemoLevelData(){
        // 레벨 1~10 생성
        for(int i=1; i<=10; i++){
            LevelSaveCommand command = new LevelSaveCommand(i, (i-1)*100, "BRONZE_"+i, 1);
            levelService.save(command);
        }
    }

    public void initDemoItemData(){
        ItemCreateCommand command = new ItemCreateCommand("아이템1");
        itemService.create(command);
    }
}
