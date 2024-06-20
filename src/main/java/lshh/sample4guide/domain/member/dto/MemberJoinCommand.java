package lshh.sample4guide.domain.member.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lshh.sample4guide.common.library.clock.RegidatedForEntity;
import lshh.sample4guide.domain.member.Member;

import java.time.LocalDateTime;

public record MemberJoinCommand(
    @NotNull String loginId,
    @NotNull @Size(min = 4) String name
) {
    public Member toEntity(LocalDateTime now) {
        RegidatedForEntity regidated = RegidatedForEntity.create(now);
        return Member.create(loginId, name, regidated);
    }
}
