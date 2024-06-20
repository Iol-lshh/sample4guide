package lshh.sample4guide.domain.member.component;

import lshh.sample4guide.domain.member.Member;
import lshh.sample4guide.domain.member.dto.MemberWithLevelView;

import java.util.Optional;

public interface LevelRepositoryForMember {
    Optional<MemberWithLevelView> findByMember(Member member);
}
