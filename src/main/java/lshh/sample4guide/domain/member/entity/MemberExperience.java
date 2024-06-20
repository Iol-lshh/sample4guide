package lshh.sample4guide.domain.member.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lshh.sample4guide.common.library.clock.RegidatedForHistory;
import lshh.sample4guide.domain.member.Member;

@Entity
public class MemberExperience {
    @Id
    private Long id;
    private Integer experience;
    @Embedded
    private RegidatedForHistory regidated;

    @ManyToOne
    private Member member;
}
