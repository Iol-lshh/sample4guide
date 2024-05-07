package lshh.sample4guide.domain.demo.dto;

import lshh.sample4guide.domain.demo.Demo;

import java.util.List;

public class DemoView {

    private Long id;

    public static List<DemoView> of(List<Demo> list) {
        return list.stream()
                .map(DemoView::of)
                .toList();
    }
    public static DemoView of(Demo demo) {
        DemoView view = new DemoView();
        view.id = demo.getId();
        return view;
    }
}
