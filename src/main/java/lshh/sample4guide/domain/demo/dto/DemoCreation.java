package lshh.sample4guide.domain.demo.dto;

import lshh.sample4guide.domain.demo.Demo;

public class DemoCreation {
    private final String name;

    public DemoCreation(String name) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("name is required");
        }
        this.name = name;
    }

    public Demo toEntity() {
        return Demo.of(name);
    }
}
