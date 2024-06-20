package lshh.sample4guide.domain.member.component;

import lshh.sample4guide.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    void save(Member member);

    List<Member> findAll();

    Optional<Member> findByLoginId(String loginId);

    Optional<Member> findById(Long id);
}
