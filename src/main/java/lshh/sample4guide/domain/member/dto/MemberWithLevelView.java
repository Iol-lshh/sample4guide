package lshh.sample4guide.domain.member.dto;

public record MemberWithLevelView(
        Long id,
        String loginId,
        String name,
        Integer level,
        String grade
) {
}
