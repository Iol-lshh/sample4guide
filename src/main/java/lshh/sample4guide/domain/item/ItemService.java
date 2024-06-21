package lshh.sample4guide.domain.item;

import lombok.RequiredArgsConstructor;
import lshh.sample4guide.domain.item.component.ItemRepository;
import lshh.sample4guide.domain.item.dto.ItemCreateCommand;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public void create(ItemCreateCommand command) {
        Item item = command.toEntity();
        itemRepository.save(item);
    }
}
