package lshh.sample4guide.infrastructure.member;

import lombok.RequiredArgsConstructor;
import lshh.sample4guide.domain.level.Level;
import lshh.sample4guide.domain.member.component.LevelRepositoryForMember;
import lshh.sample4guide.domain.member.Member;
import lshh.sample4guide.domain.member.dto.MemberWithLevelView;
import lshh.sample4guide.domain.member.entity.MemberLevel;
import lshh.sample4guide.infrastructure.level.LevelJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class LevelRepositoryForMemberImplement implements LevelRepositoryForMember {

    private final LevelJpaRepository levelJpaRepository;

    @Override
    public Optional<MemberWithLevelView> findByMember(Member member) {
        MemberLevel memberLevel = member.memberLevel();
        Optional<Level> level = levelJpaRepository.findById(memberLevel.level());
        return level.map(l -> new MemberWithLevelView(
                member.id(),
                member.loginId(),
                member.name(),
                l.level(),
                l.grade().name()
        ));
    }
}
