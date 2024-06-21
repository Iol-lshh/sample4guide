package lshh.sample4guide.infrastructure.item;

import lombok.RequiredArgsConstructor;
import lshh.sample4guide.domain.item.Item;
import lshh.sample4guide.domain.item.component.ItemRepository;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ItemRepositoryImplement implements ItemRepository {
    private final ItemJpaRepository itemJpaRepository;

    @Override
    public void save(Item item) {
        itemJpaRepository.save(item);
    }
}
