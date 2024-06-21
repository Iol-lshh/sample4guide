package lshh.sample4guide.domain.member.component;

import lshh.sample4guide.domain.member.dto.ItemVo;
import lshh.sample4guide.domain.member.dto.MemberItemView;
import lshh.sample4guide.domain.member.entity.MemberItem;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ItemRepositoryForMember {
    Optional<ItemVo> findById(Long itemId);

    List<MemberItemView> findAll(Long memberId, Set<MemberItem> items);
}
