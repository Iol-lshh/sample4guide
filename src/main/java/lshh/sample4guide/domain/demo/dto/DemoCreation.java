package lshh.sample4guide.domain.demo.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lshh.sample4guide.domain.demo.Demo;

public class DemoCreation {

    private final String name;

    @JsonCreator
    public DemoCreation(@JsonProperty("name") String name) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("name is required");
        }
        this.name = name;
    }

    public Demo toEntity() {
        return Demo.of(name);
    }
}
