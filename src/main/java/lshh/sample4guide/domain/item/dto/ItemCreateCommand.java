package lshh.sample4guide.domain.item.dto;

import lshh.sample4guide.domain.item.Item;

public record ItemCreateCommand(
    String name
) {

    public Item toEntity() {
        return Item.builder()
            .name(name)
            .build();
    }
}
