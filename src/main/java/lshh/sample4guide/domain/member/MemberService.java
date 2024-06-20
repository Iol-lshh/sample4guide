package lshh.sample4guide.domain.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lshh.sample4guide.common.library.clock.ClockManager;
import lshh.sample4guide.common.library.lock.AdvisoryLock;
import lshh.sample4guide.domain.member.component.LevelRepositoryForMember;
import lshh.sample4guide.domain.member.component.MemberRepository;
import lshh.sample4guide.domain.member.dto.MemberJoinCommand;
import lshh.sample4guide.domain.member.dto.MemberWithLevelView;
import lshh.sample4guide.domain.member.dto.MemberSampleView;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository repository;
    private final ClockManager clockManager;
    private final LevelRepositoryForMember levelRepository;

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
}
