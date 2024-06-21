package lshh.sample4guide.domain.member.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
@Getter
@Embeddable
public class MemberItemId {
    private Long memberId;
    private Long itemId;

}
