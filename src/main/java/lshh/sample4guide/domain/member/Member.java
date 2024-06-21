package lshh.sample4guide.domain.member;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lshh.sample4guide.common.library.clock.RegidatedForEntity;
import lshh.sample4guide.domain.member.dto.ItemVo;
import lshh.sample4guide.domain.member.entity.MemberExperience;
import lshh.sample4guide.domain.member.entity.MemberItem;
import lshh.sample4guide.domain.member.entity.MemberLevel;
import org.hibernate.annotations.Cascade;

import java.util.*;

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

    @Cascade(ALL)
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private Set<MemberItem> memberItems;

    public static Member create(String loginId, String name, RegidatedForEntity regidated){
        Member member = Member.builder()
                .loginId(loginId)
                .name(name)
                .regidated(regidated)
                .build();
        member.memberLevel = MemberLevel.builder().member(member).build();
        member.memberExperience = new ArrayList<>();
        member.memberItems = new HashSet<>();
        return member;
    }

    public void addItem(ItemVo item, int count){
        if(existsAnyItem(item.id())){
            memberItems.stream()
                    .filter(memberItem -> memberItem.id().itemId().equals(item.id()))
                    .forEach(memberItem -> memberItem.addCount(count));
        }else{
            MemberItem memberItem = MemberItem.of(this, item.id(), count);
            memberItems.add(memberItem);
        }
    }

    public boolean existsAnyItem(Long itemId){
        return memberItems.stream().anyMatch(memberItem -> memberItem.id().itemId().equals(itemId));
    }

    @Override
    public String toString(){
        return String.format("Member [id=" + id
                             + ", loginId=" + loginId
                             + ", name=" + name + "]");
    }
}
