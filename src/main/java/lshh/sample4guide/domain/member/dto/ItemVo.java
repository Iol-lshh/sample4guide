package lshh.sample4guide.domain.member.dto;

public record ItemVo(
        Long id,
        String name
) {
    public static ItemVo of(Long itemId, String itemName) {
        return new ItemVo(itemId, itemName);
    }
}
