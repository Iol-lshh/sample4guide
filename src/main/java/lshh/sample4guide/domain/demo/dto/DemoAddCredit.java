package lshh.sample4guide.domain.demo.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class DemoAddCredit {
    private final Long id;
    private final Integer credit;

    @JsonCreator
    public DemoAddCredit(@JsonProperty("id") Long id, @JsonProperty("credit") Integer credit) {
        this.id = id;

        if(credit == null || credit <= 0) {
            throw new IllegalArgumentException("credit is required");
        }
        this.credit = credit;
    }
}
