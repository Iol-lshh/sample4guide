package lshh.sample4guide.domain.demo.dto;

import lshh.sample4guide.domain.demo.Demo;

import java.util.List;

public class DemoVo {

    private final Long id;
    private final String name;

    public DemoVo(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static List<DemoVo> of(List<Demo> list) {
        return list.stream()
                .map(DemoVo::of)
                .toList();
    }
    public static DemoVo of(Demo demo) {
        return new DemoVo(demo.getId(), demo.getName());
    }
}
