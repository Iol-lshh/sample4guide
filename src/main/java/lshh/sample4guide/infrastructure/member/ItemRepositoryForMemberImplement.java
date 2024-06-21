package lshh.sample4guide.infrastructure.member;

import lombok.RequiredArgsConstructor;
import lshh.sample4guide.domain.item.Item;
import lshh.sample4guide.domain.member.component.ItemRepositoryForMember;
import lshh.sample4guide.domain.member.dto.ItemVo;
import lshh.sample4guide.domain.member.dto.MemberItemView;
import lshh.sample4guide.domain.member.entity.MemberItem;
import lshh.sample4guide.infrastructure.item.ItemJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Repository
public class ItemRepositoryForMemberImplement implements ItemRepositoryForMember {

    private final ItemJpaRepository itemJpaRepository;

    @Override
    public Optional<ItemVo> findById(Long itemId) {
        Optional<Item> item = itemJpaRepository.findById(itemId);
        return item.map(entity -> ItemVo.of(entity.id(), entity.name()));
    }

    @Override
    public List<MemberItemView> findAll(Long memberId, Set<MemberItem> items) {
        List<Item> itemList = itemJpaRepository.findAllById(items.stream().map(_item -> _item.id().itemId()).toList());
        return items.stream()
                .map(memberItem -> {
                    Item item = itemList.stream()
                            .filter(_item -> _item.id().equals(memberItem.id().itemId()))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Not found item"));
                    return new MemberItemView(memberId, item.id(), item.name(), memberItem.count());
                })
                .toList();
    }
}
