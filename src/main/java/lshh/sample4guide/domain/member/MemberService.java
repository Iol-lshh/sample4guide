package lshh.sample4guide.domain.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lshh.sample4guide.common.library.clock.ClockManager;
import lshh.sample4guide.common.library.lock.AdvisoryLock;
import lshh.sample4guide.domain.member.component.ItemRepositoryForMember;
import lshh.sample4guide.domain.member.component.LevelRepositoryForMember;
import lshh.sample4guide.domain.member.component.MemberRepository;
import lshh.sample4guide.domain.member.dto.*;
import lshh.sample4guide.domain.member.entity.MemberItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository repository;
    private final ClockManager clockManager;
    private final LevelRepositoryForMember levelRepository;
    private final ItemRepositoryForMember itemRepository;

    @AdvisoryLock(key = "#command.loginId()")
    @Transactional
    public void join(MemberJoinCommand command){
        Member member = command.toEntity(clockManager.now());
        repository.findByLoginId(member.loginId())
                .ifPresent(m -> {
                    throw new IllegalArgumentException("Duplicated loginId");
                });
        repository.save(member);
    }

    @Transactional(readOnly = true)
    public List<MemberSampleView> findAll() {
        List<Member> list = repository.findAll();
        return MemberSampleView.of(list);
    }

    @Transactional(readOnly = true)
    public Optional<MemberWithLevelView> findWithLevel(Long id) {
        Member member = repository.findById(id).orElseThrow(()->
                new IllegalArgumentException("Not found member"));
        log.debug("findWithLevel member level: {}", member.memberLevel().level());
        MemberWithLevelView view = levelRepository.findByMember(member).orElseThrow(()->
                new IllegalArgumentException("Not found level"));
        return Optional.of(view);
    }

    @Transactional
    public void addItem(MemberAddItemCommand command){
        Member member = repository.findById(command.memberId()).orElseThrow(()->
                new IllegalArgumentException("Not found member"));
        ItemVo item = itemRepository.findById(command.itemId()).orElseThrow(()->
                new IllegalArgumentException("Not found item"));
        member.addItem(item, command.count());
        repository.save(member);

    }

    @Transactional(readOnly = true)
    public List<MemberItemView> findWithItems(Long id) {
        Member member = repository.findById(id).orElseThrow(()->
                new IllegalArgumentException("Not found member"));
        Set<MemberItem> items = member.memberItems();
        return itemRepository.findAll(id, items);
    }
}
