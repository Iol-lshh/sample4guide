package lshh.sample4guide.domain.member;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lshh.sample4guide.common.library.clock.RegidatedForEntity;
import lshh.sample4guide.domain.member.entity.MemberExperience;
import lshh.sample4guide.domain.member.entity.MemberLevel;
import org.hibernate.annotations.Cascade;

import java.util.List;

import static org.hibernate.annotations.CascadeType.ALL;

@Accessors(fluent = true)
@Getter()
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String loginId;
    private String name;

    @Embedded
    private RegidatedForEntity regidated;

    @Cascade(ALL)
    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    private MemberLevel memberLevel;

    @Cascade(ALL)
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<MemberExperience> memberExperience;

    public static Member create(String loginId, String name, RegidatedForEntity regidated){
        Member member = Member.builder()
                .loginId(loginId)
                .name(name)
                .regidated(regidated)
                .build();
        member.memberLevel = MemberLevel.builder().member(member).build();
        return member;
    }

    @Override
    public String toString(){
        return String.format("Member [id=" + id
                             + ", loginId=" + loginId
                             + ", name=" + name + "]");
    }
}
