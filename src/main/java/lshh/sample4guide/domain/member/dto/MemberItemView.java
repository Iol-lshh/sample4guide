package lshh.sample4guide.domain.member.dto;

public record MemberItemView(
        Long id,
        Long itemId,
        String itemName,
        int count
) {

}
