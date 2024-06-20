package lshh.sample4guide.domain.member.entity;


import jakarta.persistence.*;
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
public class MemberLevel {
    @Id
    private Long memberId;

    private Integer level;

    @OneToOne
    @MapsId
    private Member member;

    @PrePersist
    public void prePersist() {
        if(memberId == null) {
            memberId = member.id();
        }
        if (level == null) {
            level = 1;
        }
    }
}
