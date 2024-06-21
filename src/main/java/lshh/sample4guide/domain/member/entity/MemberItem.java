package lshh.sample4guide.domain.member.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lshh.sample4guide.domain.member.Member;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(fluent = true)
@Getter
@Entity
public class MemberItem {
    @EmbeddedId
    private MemberItemId id;
    private int count;

    @MapsId("memberId")
    @ManyToOne
    private Member member;

    public static MemberItem of(Member member, Long itemId, int count) {
        MemberItem memberItem = new MemberItem();
        memberItem.id = new MemberItemId(member.id(), itemId);
        memberItem.count = count;
        memberItem.member = member;
        return memberItem;
    }

    public void addCount(int count) {
        this.count += count;
    }
}
