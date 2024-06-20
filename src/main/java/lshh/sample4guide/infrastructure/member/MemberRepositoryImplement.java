package lshh.sample4guide.infrastructure.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lshh.sample4guide.domain.member.Member;
import lshh.sample4guide.domain.member.component.MemberRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Repository
public class MemberRepositoryImplement implements MemberRepository {

    private final MemberJpaRepository jpaRepository;

    @Override
    public void save(Member member) {
        log.debug("member save" + member.toString());
        jpaRepository.save(member);
    }

    @Override
    public List<Member> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        return jpaRepository.findByLoginId(loginId);
    }

    @Override
    public Optional<Member> findById(Long id) {
        return jpaRepository.findById(id);
    }
}
