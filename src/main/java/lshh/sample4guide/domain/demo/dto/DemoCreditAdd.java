package lshh.sample4guide.domain.demo.dto;

import lombok.Getter;

@Getter
public class DemoCreditAdd {
    private final Long id;
    private final Integer credit;

    public DemoCreditAdd(Long id, Integer credit) {
        this.id = id;

        if(credit == null || credit <= 0) {
            throw new IllegalArgumentException("credit is required");
        }
        this.credit = credit;
    }
}
