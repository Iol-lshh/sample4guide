package lshh.sample4guide.infrastructure.item;

import lshh.sample4guide.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemJpaRepository extends JpaRepository<Item, Long>{
}
