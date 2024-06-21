package lshh.sample4guide.domain.member.dto;

public record MemberAddItemCommand(
    Long memberId,
    Long itemId,
    int count
) {
}
